package org.work.rpicontrol.sshutils;

import android.os.Handler;
import android.util.Log;
import android.widget.EditText;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Controller for SSH shell-like process between local device and remote SSH
 * server. Sustains an open channel to remote server and streams data between
 * local device and remote.
 * <p/>
 * Created by Jon Hough on 5/17/14.
 */
public class ShellController {

  public static final String TAG = "ShellController";
  /**
   *
   */
  private BufferedReader mBufferedReader;

  /**
   *
   */
  private DataOutputStream mDataOutputStream;

  /**
   *
   */
  private Channel mChannel;

  /**
   *
   */
  private String mSshText = null;

  public ShellController() {
    // nothing
  }

  /**
   * Gets the dataoutputstream (IO stream to remote server)
   * 
   * @return dataOutputStream
   */
  public DataOutputStream getDataOutputStream() {
    return mDataOutputStream;
  }

  /**
   * Disconnects shell and closes streams.
   *
   * @throws java.io.IOException
   */
  public synchronized void disconnect() throws IOException {
    try {
      Log.v(TAG, "close shell channel");
      // disconnect channel
      if (mChannel != null)
        mChannel.disconnect();

      Log.v(TAG, "close streams");
      // close streams
      if (mDataOutputStream != null) {
        mDataOutputStream.flush();
        mDataOutputStream.close();
      }
      if (mBufferedReader != null)
        mBufferedReader.close();
    }
    catch (Throwable t) {
      Log.e(TAG, "Exception: " + t.getMessage());
      t.printStackTrace();
    }
  }

  /**
   * Writes to the outputstream, to remote server. Input should ideally come from
   * an EditText, to which the shell response output will also be written, to
   * simulate a shell terminal.
   *
   * @param command commands string.
   */
  public void writeToOutput(String command) {
    if (mDataOutputStream != null) {
      try {
        mDataOutputStream.writeBytes(command + "\r\n");
        mDataOutputStream.flush();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


  public void openShell(final Session session, final ExecTaskCallbackHandler callback, final String command) throws JSchException, IOException {
    if (session == null) throw new NullPointerException("Session cannot be null!");
    if (!session.isConnected()) throw new IllegalStateException("Session must be connected.");
    Log.d(TAG, "openShell()");
    
    new Thread(new Runnable() {
      public void run() {
        try {
          Log.d(TAG, "openShell()--run()");
          
          ChannelExec channel = (ChannelExec) session.openChannel("exec");
          channel.setCommand(command);

          channel.setInputStream(null);
          InputStream in = channel.getInputStream();
          // InputStream err = channel.getErrStream();

          channel.connect();
          int bufLen = 1024;
          byte[] buf = new byte[bufLen];
          ByteArrayOutputStream out = new ByteArrayOutputStream();
          
          // channel.setErrStream(System.err);
          channel.setErrStream(out);

          while (true) {
            while (in.available() > 0) {
              int len = in.read(buf);
              if (len < 0) break;
              out.write(buf, 0, len);
              // System.out.println("-next portion :: session.isConnected(): " + session.isConnected() + ", channel.isClosed(): " + channel.isClosed());
            }
            
            if (channel.isClosed()) {
              System.out.println("exit-status: " + channel.getExitStatus());
              break;
            }
            
            try {
              Thread.sleep(1000);
            }
            catch (Exception ee) {
              ee.printStackTrace();
            }
          }
          
          String res = out.toString("UTF-8");
          
          // Log.d(TAG, "== after input read");
          // Log.d(TAG, "== res:");
          // Log.d(TAG, res);
          
          if (callback != null) {
            callback.onComplete(res);
          }
          
          channel.disconnect();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();
  }


  /**
   * Gets the prompt text from the returned string.
   * 
   * @param returnedString
   * @return
   */
  public static String fetchPrompt(String returnedString) {
    return "";
  }

  /**
   * Removes the prompt string from the user input command.
   * 
   * @param command
   * @return
   */
  public static String removePrompt(String command) {
    if (command != null && command.trim().split("\\$").length > 1) {
      String[] split = command.trim().split("\\$");
      String s = "";
      for (int i = 1; i < split.length; i++) {
        s += split[i];
      }
      return s;
    }
    return command;
  }
}
