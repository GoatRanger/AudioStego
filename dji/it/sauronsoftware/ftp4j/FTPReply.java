package it.sauronsoftware.ftp4j;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.pilot.usercenter.protocol.d;

public class FTPReply {
    private int code = 0;
    private String[] messages;

    FTPReply(int i, String[] strArr) {
        this.code = i;
        this.messages = strArr;
    }

    public int getCode() {
        return this.code;
    }

    public boolean isSuccessCode() {
        int i = this.code - 200;
        return i >= 0 && i < 100;
    }

    public String[] getMessages() {
        return this.messages;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getClass().getName());
        stringBuffer.append(" [code=");
        stringBuffer.append(this.code);
        stringBuffer.append(", message=");
        for (int i = 0; i < this.messages.length; i++) {
            if (i > 0) {
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
            stringBuffer.append(this.messages[i]);
        }
        stringBuffer.append(d.H);
        return stringBuffer.toString();
    }
}
