package com.a.a;

import dji.pilot.fpv.control.f;
import dji.pilot.usercenter.protocol.d;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

class l implements Closeable {
    private static final Logger c = Logger.getLogger(l.class.getName());
    private static final byte[] d = new byte[4096];
    final RandomAccessFile a;
    int b;
    private int e;
    private a f;
    private a g;
    private final byte[] h = new byte[16];

    interface c {
        boolean a(InputStream inputStream, int i) throws IOException;
    }

    static class a {
        static final a a = new a(0, 0);
        final int b;
        final int c;

        a(int i, int i2) {
            this.b = i;
            this.c = i2;
        }

        public String toString() {
            return getClass().getSimpleName() + d.G + "position = " + this.b + ", length = " + this.c + d.H;
        }
    }

    private final class b extends InputStream {
        final /* synthetic */ l a;
        private int b;
        private int c;

        private b(l lVar, a aVar) {
            this.a = lVar;
            this.b = lVar.c(aVar.b + 4);
            this.c = aVar.c;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            if ((i | i2) < 0 || i2 > bArr.length - i) {
                throw new ArrayIndexOutOfBoundsException();
            } else if (this.c == 0) {
                return -1;
            } else {
                if (i2 > this.c) {
                    i2 = this.c;
                }
                this.a.b(this.b, bArr, i, i2);
                this.b = this.a.c(this.b + i2);
                this.c -= i2;
                return i2;
            }
        }

        public int read() throws IOException {
            if (this.c == 0) {
                return -1;
            }
            this.a.a.seek((long) this.b);
            int read = this.a.a.read();
            this.b = this.a.c(this.b + 1);
            this.c--;
            return read;
        }
    }

    l(File file) throws IOException {
        if (!file.exists()) {
            a(file);
        }
        this.a = b(file);
        e();
    }

    private static void b(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    private static int a(byte[] bArr, int i) {
        return ((((bArr[i] & 255) << 24) + ((bArr[i + 1] & 255) << 16)) + ((bArr[i + 2] & 255) << 8)) + (bArr[i + 3] & 255);
    }

    private void e() throws IOException {
        this.a.seek(0);
        this.a.readFully(this.h);
        this.b = a(this.h, 0);
        if (((long) this.b) > this.a.length()) {
            throw new IOException("File is truncated. Expected length: " + this.b + ", Actual length: " + this.a.length());
        } else if (this.b <= 0) {
            throw new IOException("File is corrupt; length stored in header (" + this.b + ") is invalid.");
        } else {
            this.e = a(this.h, 4);
            int a = a(this.h, 8);
            int a2 = a(this.h, 12);
            this.f = b(a);
            this.g = b(a2);
        }
    }

    private void a(int i, int i2, int i3, int i4) throws IOException {
        b(this.h, 0, i);
        b(this.h, 4, i2);
        b(this.h, 8, i3);
        b(this.h, 12, i4);
        this.a.seek(0);
        this.a.write(this.h);
    }

    private a b(int i) throws IOException {
        if (i == 0) {
            return a.a;
        }
        b(i, this.h, 0, 4);
        return new a(i, a(this.h, 0));
    }

    private static void a(File file) throws IOException {
        File file2 = new File(file.getPath() + f.b);
        RandomAccessFile b = b(file2);
        try {
            b.setLength(4096);
            b.seek(0);
            byte[] bArr = new byte[16];
            b(bArr, 0, 4096);
            b.write(bArr);
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } finally {
            b.close();
        }
    }

    private static RandomAccessFile b(File file) throws FileNotFoundException {
        return new RandomAccessFile(file, "rwd");
    }

    private int c(int i) {
        return i < this.b ? i : (i + 16) - this.b;
    }

    private void a(int i, byte[] bArr, int i2, int i3) throws IOException {
        int c = c(i);
        if (c + i3 <= this.b) {
            this.a.seek((long) c);
            this.a.write(bArr, i2, i3);
            return;
        }
        int i4 = this.b - c;
        this.a.seek((long) c);
        this.a.write(bArr, i2, i4);
        this.a.seek(16);
        this.a.write(bArr, i2 + i4, i3 - i4);
    }

    private void a(int i, int i2) throws IOException {
        while (i2 > 0) {
            int min = Math.min(i2, d.length);
            a(i, d, 0, min);
            i2 -= min;
            i += min;
        }
    }

    private void b(int i, byte[] bArr, int i2, int i3) throws IOException {
        int c = c(i);
        if (c + i3 <= this.b) {
            this.a.seek((long) c);
            this.a.readFully(bArr, i2, i3);
            return;
        }
        int i4 = this.b - c;
        this.a.seek((long) c);
        this.a.readFully(bArr, i2, i4);
        this.a.seek(16);
        this.a.readFully(bArr, i2 + i4, i3 - i4);
    }

    void a(byte[] bArr) throws IOException {
        a(bArr, 0, bArr.length);
    }

    synchronized void a(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            throw new NullPointerException("data == null");
        }
        if ((i | i2) >= 0) {
            if (i2 <= bArr.length - i) {
                int i3;
                d(i2);
                boolean a = a();
                if (a) {
                    i3 = 16;
                } else {
                    i3 = c((this.g.b + 4) + this.g.c);
                }
                a aVar = new a(i3, i2);
                b(this.h, 0, i2);
                a(aVar.b, this.h, 0, 4);
                a(aVar.b + 4, bArr, i, i2);
                a(this.b, this.e + 1, a ? aVar.b : this.f.b, aVar.b);
                this.g = aVar;
                this.e++;
                if (a) {
                    this.f = this.g;
                }
            }
        }
        throw new IndexOutOfBoundsException();
    }

    private int f() {
        if (this.e == 0) {
            return 16;
        }
        if (this.g.b >= this.f.b) {
            return (((this.g.b - this.f.b) + 4) + this.g.c) + 16;
        }
        return (((this.g.b + 4) + this.g.c) + this.b) - this.f.b;
    }

    private int g() {
        return this.b - f();
    }

    synchronized boolean a() {
        return this.e == 0;
    }

    private void d(int i) throws IOException {
        int i2 = i + 4;
        int g = g();
        if (g < i2) {
            int i3 = this.b;
            do {
                g += i3;
                i3 <<= 1;
            } while (g < i2);
            e(i3);
            i2 = c((this.g.b + 4) + this.g.c);
            if (i2 <= this.f.b) {
                FileChannel channel = this.a.getChannel();
                channel.position((long) this.b);
                int i4 = i2 - 16;
                if (channel.transferTo(16, (long) i4, channel) != ((long) i4)) {
                    throw new AssertionError("Copied insufficient number of bytes!");
                }
                a(16, i4);
            }
            if (this.g.b < this.f.b) {
                g = (this.b + this.g.b) - 16;
                a(i3, this.e, this.f.b, g);
                this.g = new a(g, this.g.c);
            } else {
                a(i3, this.e, this.f.b, this.g.b);
            }
            this.b = i3;
        }
    }

    private void e(int i) throws IOException {
        this.a.setLength((long) i);
        this.a.getChannel().force(true);
    }

    synchronized int a(c cVar) throws IOException {
        int i;
        int i2 = this.f.b;
        for (i = 0; i < this.e; i++) {
            a b = b(i2);
            if (!cVar.a(new b(b), b.c)) {
                i++;
                break;
            }
            i2 = c(b.c + (b.b + 4));
        }
        i = this.e;
        return i;
    }

    synchronized int b() {
        return this.e;
    }

    synchronized void c() throws IOException {
        a(1);
    }

    synchronized void a(int i) throws IOException {
        int i2 = 0;
        synchronized (this) {
            if (a()) {
                throw new NoSuchElementException();
            } else if (i > this.e) {
                throw new IllegalArgumentException("Cannot remove more elements (" + i + ") than present in queue (" + this.e + ").");
            } else if (i < 1) {
                throw new IllegalArgumentException("Cannot remove a non-positive (" + i + ") number of elements.");
            } else {
                if (i == this.e) {
                    d();
                } else {
                    int i3 = this.f.b;
                    int i4 = this.f.b;
                    int i5 = this.f.c;
                    int i6 = 0;
                    while (i2 < i) {
                        i6 += i5 + 4;
                        i4 = c(i5 + (i4 + 4));
                        b(i4, this.h, 0, 4);
                        i5 = a(this.h, 0);
                        i2++;
                    }
                    a(this.b, this.e - i, i4, this.g.b);
                    this.e -= i;
                    this.f = new a(i4, i5);
                    a(i3, i6);
                }
            }
        }
    }

    synchronized void d() throws IOException {
        a(4096, 0, 0, 0);
        this.a.seek(16);
        this.a.write(d, 0, 4080);
        this.e = 0;
        this.f = a.a;
        this.g = a.a;
        if (this.b > 4096) {
            e(4096);
        }
        this.b = 4096;
    }

    public synchronized void close() throws IOException {
        this.a.close();
    }

    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName()).append('[');
        stringBuilder.append("fileLength=").append(this.b);
        stringBuilder.append(", size=").append(this.e);
        stringBuilder.append(", first=").append(this.f);
        stringBuilder.append(", last=").append(this.g);
        stringBuilder.append(", element lengths=[");
        try {
            a(new c(this) {
                boolean a = true;
                final /* synthetic */ l c;

                public boolean a(InputStream inputStream, int i) throws IOException {
                    if (this.a) {
                        this.a = false;
                    } else {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(i);
                    return true;
                }
            });
        } catch (Throwable e) {
            c.log(Level.WARNING, "read error", e);
        }
        stringBuilder.append("]]");
        return stringBuilder.toString();
    }
}
