package com.e;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class co {
    protected File a;
    protected ArrayList<byte[]> b;
    protected int[] c;
    protected boolean d = false;

    protected co(File file, ArrayList<byte[]> arrayList, int[] iArr) {
        this.a = file;
        this.b = arrayList;
        this.c = iArr;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public byte[] a() {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                byte[] bArr = (byte[]) it.next();
                try {
                    dataOutputStream.writeInt(bArr.length);
                    dataOutputStream.write(bArr);
                } catch (Throwable th) {
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                } catch (Throwable th2) {
                }
            }
        } catch (Throwable th3) {
            bc.a(th3, "WrapData", "getReport");
        }
        return byteArrayOutputStream.toByteArray();
    }

    protected boolean b() {
        return this.d;
    }

    protected int c() {
        Throwable th;
        int i;
        try {
            if (this.b == null) {
                return 0;
            }
            int i2 = 0;
            int i3 = 0;
            while (i2 < this.b.size()) {
                try {
                    i3 += this.b.get(i2) != null ? ((byte[]) this.b.get(i2)).length : 0;
                    i2++;
                } catch (Throwable th2) {
                    th = th2;
                    i = i3;
                }
            }
            i = i3;
            return i;
        } catch (Throwable th22) {
            Throwable th3 = th22;
            i = 0;
            th = th3;
            bc.a(th, "WrapData", "getDataLength");
            return i;
        }
    }
}
