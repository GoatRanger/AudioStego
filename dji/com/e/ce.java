package com.e;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.BitSet;

public class ce {
    protected long a;
    private RandomAccessFile b;
    private bx c;
    private String d = "";
    private File e = null;

    protected ce(bx bxVar) {
        this.c = bxVar;
    }

    protected synchronized void a(long j, byte[] bArr) {
        int i = 0;
        synchronized (this) {
            this.e = this.c.a(j);
            if (this.e != null) {
                try {
                    this.b = new RandomAccessFile(this.e, "rw");
                    byte[] bArr2 = new byte[this.c.a()];
                    int readInt = this.b.read(bArr2) == -1 ? 0 : this.b.readInt();
                    BitSet a = cb.a(bArr2);
                    int a2 = (this.c.a() + 4) + (readInt * 1500);
                    if (readInt < 0 || readInt > this.c.a() * 8) {
                        this.b.close();
                        this.e.delete();
                        if (this.b != null) {
                            try {
                                this.b.close();
                            } catch (Throwable th) {
                            }
                        }
                    } else {
                        this.b.seek((long) a2);
                        byte[] a3 = br.a(bArr);
                        this.b.writeInt(a3.length);
                        this.b.writeLong(j);
                        this.b.write(a3);
                        a.set(readInt, true);
                        this.b.seek(0);
                        this.b.write(cb.a(a));
                        readInt++;
                        if (readInt != this.c.a() * 8) {
                            i = readInt;
                        }
                        this.b.writeInt(i);
                        if (!this.d.equalsIgnoreCase(this.e.getName())) {
                            this.d = this.e.getName();
                        }
                        this.a = this.e.length();
                        if (this.b != null) {
                            this.b.close();
                        }
                        this.e = null;
                    }
                } catch (Throwable th2) {
                }
            }
        }
    }
}
