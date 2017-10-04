package com.e;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class ci implements Serializable {
    protected short a = (short) 0;
    protected byte[] b = new byte[16];
    protected byte[] c = new byte[16];
    protected byte[] d = new byte[16];
    protected short e = (short) 0;
    protected short f = (short) 0;
    protected byte g = (byte) 0;
    protected byte[] h = new byte[16];
    protected byte[] i = new byte[32];
    protected short j = (short) 0;
    protected ArrayList<cg> k = new ArrayList();
    private byte l = (byte) 41;

    ci() {
    }

    protected Boolean a(DataOutputStream dataOutputStream) {
        Boolean valueOf = Boolean.valueOf(true);
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream2.flush();
            dataOutputStream2.write(this.b);
            dataOutputStream2.write(this.c);
            dataOutputStream2.write(this.d);
            dataOutputStream2.writeShort(this.e);
            dataOutputStream2.writeShort(this.f);
            dataOutputStream2.writeByte(this.g);
            this.h[15] = (byte) 0;
            dataOutputStream2.write(bt.a(this.h, this.h.length));
            this.i[31] = (byte) 0;
            dataOutputStream2.write(bt.a(this.i, this.i.length));
            dataOutputStream2.writeShort(this.j);
            for (short s = (short) 0; s < this.j; s = (short) (s + 1)) {
                OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream3 = new DataOutputStream(byteArrayOutputStream2);
                dataOutputStream3.flush();
                cg cgVar = (cg) this.k.get(s);
                if (!(cgVar.c == null || cgVar.c.a(dataOutputStream3).booleanValue())) {
                    valueOf = Boolean.valueOf(false);
                }
                if (!(cgVar.d == null || cgVar.d.a(dataOutputStream3).booleanValue())) {
                    valueOf = Boolean.valueOf(false);
                }
                if (!(cgVar.e == null || cgVar.e.a(dataOutputStream3).booleanValue())) {
                    valueOf = Boolean.valueOf(false);
                }
                if (!(cgVar.f == null || cgVar.f.a(dataOutputStream3).booleanValue())) {
                    valueOf = Boolean.valueOf(false);
                }
                if (!(cgVar.g == null || cgVar.g.a(dataOutputStream3).booleanValue())) {
                    valueOf = Boolean.valueOf(false);
                }
                cgVar.a = Integer.valueOf(byteArrayOutputStream2.size() + 4).shortValue();
                dataOutputStream2.writeShort(cgVar.a);
                dataOutputStream2.writeInt(cgVar.b);
                dataOutputStream2.write(byteArrayOutputStream2.toByteArray());
            }
            this.a = Integer.valueOf(byteArrayOutputStream.size()).shortValue();
            dataOutputStream.writeByte(this.l);
            dataOutputStream.writeShort(this.a);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
            return Boolean.valueOf(true);
        } catch (Throwable th) {
            Throwable th2 = th;
            Boolean bool = valueOf;
            bc.a(th2, "Record", "toBinary");
            return bool;
        }
    }

    protected byte[] a() {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(new DataOutputStream(byteArrayOutputStream));
        return byteArrayOutputStream.toByteArray();
    }
}
