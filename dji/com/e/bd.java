package com.e;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class bd {
    private ByteArrayInputStream a;
    private long b;
    private boolean c = false;
    private RandomAccessFile d = null;
    private boolean e = false;
    private final byte[] f = new byte[8];
    private a g;
    private String h = null;

    public static class a {
        public boolean a = true;
        public boolean b = true;
    }

    public bd(File file, a aVar) throws IOException, FileNotFoundException, OutOfMemoryError {
        if (aVar != null) {
            if (aVar.a) {
                byte[] a = br.a(file);
                this.a = new ByteArrayInputStream(a);
                this.b = (long) a.length;
                this.c = false;
                this.h = file.getAbsolutePath();
            } else {
                this.d = new RandomAccessFile(file, "r");
                this.c = true;
            }
            this.g = aVar;
        }
    }

    private void h() throws IOException {
        if (this.e) {
            throw new IOException("file closed");
        }
    }

    public void a(long j) throws IOException {
        if (j < 0) {
            throw new IOException("offset < 0: " + j);
        }
        h();
        if (this.c) {
            this.d.seek(j);
            return;
        }
        this.a.reset();
        this.a.skip(j);
    }

    public boolean a() {
        return this.g == null ? false : this.g.a;
    }

    public void b() throws IOException {
        synchronized (this) {
            if (this.c) {
                if (this.d != null) {
                    this.d.close();
                    this.d = null;
                }
            } else if (this.a != null) {
                this.a.close();
                this.a = null;
            }
            this.e = true;
        }
    }

    public final long c() throws IOException {
        h();
        if (this.c) {
            return this.d.readLong();
        }
        this.a.read(this.f);
        return br.b(this.f);
    }

    public final int d() throws IOException {
        h();
        if (this.c) {
            return this.d.readUnsignedShort();
        }
        this.a.read(this.f, 0, 2);
        return br.c(this.f);
    }

    public final int e() throws IOException {
        h();
        if (this.c) {
            return this.d.readInt();
        }
        this.a.read(this.f, 0, 4);
        return br.d(this.f);
    }

    public final int f() throws IOException {
        h();
        return this.c ? this.d.readUnsignedByte() : this.a.read();
    }

    protected void finalize() throws Throwable {
        b();
        super.finalize();
    }

    public long g() throws IOException {
        if (!this.e) {
            return this.c ? this.d.length() : this.b;
        } else {
            throw new IOException("file closed");
        }
    }
}
