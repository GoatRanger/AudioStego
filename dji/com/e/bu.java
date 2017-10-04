package com.e;

import java.io.DataOutputStream;
import java.io.Serializable;

class bu implements Serializable {
    protected int a = 0;
    protected int b = 0;
    protected short c = (short) 0;
    protected short d = (short) 0;
    protected int e = 0;
    protected byte f = (byte) 0;
    private byte g = (byte) 4;

    bu() {
    }

    protected Boolean a(DataOutputStream dataOutputStream) {
        Boolean valueOf = Boolean.valueOf(false);
        try {
            dataOutputStream.writeByte(this.g);
            dataOutputStream.writeInt(this.a);
            dataOutputStream.writeInt(this.b);
            dataOutputStream.writeShort(this.c);
            dataOutputStream.writeShort(this.d);
            dataOutputStream.writeInt(this.e);
            dataOutputStream.writeByte(this.f);
            valueOf = Boolean.valueOf(true);
        } catch (Throwable th) {
        }
        return valueOf;
    }
}
