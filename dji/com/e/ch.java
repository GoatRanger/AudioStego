package com.e;

import java.io.DataOutputStream;
import java.io.Serializable;

class ch implements Serializable {
    protected int a = 0;
    protected int b = 0;
    protected int c = 0;
    protected int d = 0;
    protected int e = 0;
    protected short f = (short) 0;
    protected byte g = (byte) 0;
    protected byte h = (byte) 0;
    protected long i = 0;
    protected long j = 0;
    private byte k = (byte) 1;

    ch() {
    }

    protected Boolean a(DataOutputStream dataOutputStream) {
        Boolean valueOf = Boolean.valueOf(false);
        if (dataOutputStream != null) {
            try {
                dataOutputStream.writeByte(this.k);
                dataOutputStream.writeInt(this.a);
                dataOutputStream.writeInt(this.b);
                dataOutputStream.writeInt(this.c);
                dataOutputStream.writeInt(this.d);
                dataOutputStream.writeInt(this.e);
                dataOutputStream.writeShort(this.f);
                dataOutputStream.writeByte(this.g);
                dataOutputStream.writeByte(this.h);
                dataOutputStream.writeLong(this.i);
                dataOutputStream.writeLong(this.j);
                valueOf = Boolean.valueOf(true);
            } catch (Throwable th) {
                bc.a(th, "PositionData", "toBinary");
            }
        }
        return valueOf;
    }
}
