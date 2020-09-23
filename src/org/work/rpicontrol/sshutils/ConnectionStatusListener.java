package org.work.rpicontrol.sshutils;

public interface ConnectionStatusListener {
   public void onDisconnected();
   public void onConnected();
}
