package it.sauronsoftware.ftp4j;

import dji.pilot.usercenter.protocol.d;

public class FTPException extends Exception {
    private static final long serialVersionUID = 1;
    private int code;
    private String message;

    public FTPException(int i) {
        this.code = i;
    }

    public FTPException(int i, String str) {
        this.code = i;
        this.message = str;
    }

    public FTPException(FTPReply fTPReply) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] messages = fTPReply.getMessages();
        for (int i = 0; i < messages.length; i++) {
            if (i > 0) {
                stringBuffer.append(System.getProperty("line.separator"));
            }
            stringBuffer.append(messages[i]);
        }
        this.code = fTPReply.getCode();
        this.message = stringBuffer.toString();
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return new StringBuffer().append(getClass().getName()).append(" [code=").append(this.code).append(", message= ").append(this.message).append(d.H).toString();
    }
}
