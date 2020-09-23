package org.work.rpicontrol.sshutils;


public interface ExecTaskCallbackHandler {
    void onFail();
    void onComplete(String completeString);
}
