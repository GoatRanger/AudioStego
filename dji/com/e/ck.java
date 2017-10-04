package com.e;

import java.io.DataOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class ck implements Serializable {
    protected byte a = (byte) 0;
    protected ArrayList<cl> b = new ArrayList();
    private byte c = (byte) 3;

    ck() {
    }

    protected Boolean a(DataOutputStream dataOutputStream) {
        Boolean valueOf = Boolean.valueOf(false);
        try {
            dataOutputStream.writeByte(this.c);
            dataOutputStream.writeByte(this.a);
            for (int i = 0; i < this.b.size(); i++) {
                cl clVar = (cl) this.b.get(i);
                dataOutputStream.writeByte(clVar.a);
                Object obj = new byte[clVar.a];
                System.arraycopy(clVar.b, 0, obj, 0, clVar.a < clVar.b.length ? clVar.a : clVar.b.length);
                dataOutputStream.write(obj);
                dataOutputStream.writeDouble(clVar.c);
                dataOutputStream.writeInt(clVar.d);
                dataOutputStream.writeInt(clVar.e);
                dataOutputStream.writeDouble(clVar.f);
                dataOutputStream.writeByte(clVar.g);
                dataOutputStream.writeByte(clVar.h);
                obj = new byte[clVar.h];
                System.arraycopy(clVar.i, 0, obj, 0, clVar.h < clVar.i.length ? clVar.h : clVar.i.length);
                dataOutputStream.write(obj);
                dataOutputStream.writeByte(clVar.j);
            }
            return Boolean.valueOf(true);
        } catch (Throwable th) {
            bc.a(th, "SensorData", "toBinary");
            return valueOf;
        }
    }
}
