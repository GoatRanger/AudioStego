package com.e;

import java.io.DataOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class cm implements Serializable {
    protected byte a = (byte) 0;
    protected ArrayList<cn> b = new ArrayList();
    private byte c = (byte) 8;

    cm() {
    }

    protected Boolean a(DataOutputStream dataOutputStream) {
        Boolean valueOf = Boolean.valueOf(false);
        try {
            dataOutputStream.writeByte(this.c);
            dataOutputStream.writeByte(this.a);
            for (byte b = (byte) 0; b < this.a; b++) {
                cn cnVar = (cn) this.b.get(b);
                dataOutputStream.write(cnVar.a);
                dataOutputStream.writeShort(cnVar.b);
                dataOutputStream.write(bt.a(cnVar.c, cnVar.c.length));
            }
            return Boolean.valueOf(true);
        } catch (Throwable th) {
            bc.a(th, "WifiData", "toBinary");
            return valueOf;
        }
    }
}
