package com.e;

import java.io.DataOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class cf implements Serializable {
    protected short a = (short) 0;
    protected int b = 0;
    protected byte c = (byte) 0;
    protected byte d = (byte) 0;
    protected ArrayList<bv> e = new ArrayList();
    private byte f = (byte) 2;

    cf() {
    }

    protected Boolean a(DataOutputStream dataOutputStream) {
        Boolean valueOf = Boolean.valueOf(false);
        try {
            dataOutputStream.writeByte(this.f);
            dataOutputStream.writeShort(this.a);
            dataOutputStream.writeInt(this.b);
            dataOutputStream.writeByte(this.c);
            dataOutputStream.writeByte(this.d);
            for (byte b = (byte) 0; b < this.d; b++) {
                dataOutputStream.writeShort(((bv) this.e.get(b)).a);
                dataOutputStream.writeInt(((bv) this.e.get(b)).b);
                dataOutputStream.writeByte(((bv) this.e.get(b)).c);
            }
            return Boolean.valueOf(true);
        } catch (Throwable th) {
            bc.a(th, "GTWData", "toBinary");
            return valueOf;
        }
    }
}
