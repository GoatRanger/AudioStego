package dji.thirdparty.afinal.b.a;

import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.zip.Adler32;

public class g implements Closeable {
    private static final String a = g.class.getSimpleName();
    private static final int b = -1289277392;
    private static final int c = -1121680112;
    private static final int d = 0;
    private static final int e = 4;
    private static final int f = 8;
    private static final int g = 12;
    private static final int h = 16;
    private static final int i = 20;
    private static final int j = 24;
    private static final int k = 28;
    private static final int l = 32;
    private static final int m = 4;
    private static final int n = 0;
    private static final int o = 8;
    private static final int p = 12;
    private static final int q = 16;
    private static final int r = 20;
    private int A;
    private int B;
    private int C;
    private RandomAccessFile D;
    private RandomAccessFile E;
    private int F;
    private int G;
    private byte[] H;
    private byte[] I;
    private Adler32 J;
    private String K;
    private a L;
    private int M;
    private int N;
    private RandomAccessFile s;
    private RandomAccessFile t;
    private RandomAccessFile u;
    private FileChannel v;
    private MappedByteBuffer w;
    private int x;
    private int y;
    private int z;

    public static class a {
        public long a;
        public byte[] b;
        public int c;
    }

    public g(String str, int i, int i2, boolean z) throws IOException {
        this(str, i, i2, z, 0);
    }

    public g(String str, int i, int i2, boolean z, int i3) throws IOException {
        this.H = new byte[32];
        this.I = new byte[20];
        this.J = new Adler32();
        this.L = new a();
        File file = new File(str);
        if (file.exists() || file.mkdirs()) {
            this.K = str;
            this.s = new RandomAccessFile(str + ".idx", "rw");
            this.t = new RandomAccessFile(str + ".0", "rw");
            this.u = new RandomAccessFile(str + ".1", "rw");
            this.C = i3;
            if (z || !f()) {
                a(i, i2);
                if (!f()) {
                    e();
                    throw new IOException("unable to load index");
                }
                return;
            }
            return;
        }
        throw new IOException("unable to make dirs");
    }

    public void a() {
        a(this.K + ".idx");
        a(this.K + ".0");
        a(this.K + ".1");
    }

    private static void a(String str) {
        try {
            new File(str).delete();
        } catch (Throwable th) {
        }
    }

    public void close() {
        c();
        e();
    }

    private void e() {
        a(this.v);
        a(this.s);
        a(this.t);
        a(this.u);
    }

    private boolean f() {
        try {
            this.s.seek(0);
            this.t.seek(0);
            this.u.seek(0);
            byte[] bArr = this.H;
            if (this.s.read(bArr) != 32) {
                Log.w(a, "cannot read header");
                return false;
            } else if (a(bArr, 0) != b) {
                Log.w(a, "cannot read header magic");
                return false;
            } else if (a(bArr, 24) != this.C) {
                Log.w(a, "version mismatch");
                return false;
            } else {
                this.x = a(bArr, 4);
                this.y = a(bArr, 8);
                this.z = a(bArr, 12);
                this.A = a(bArr, 16);
                this.B = a(bArr, 20);
                if (a(bArr, 0, 28) != a(bArr, 28)) {
                    Log.w(a, "header checksum does not match");
                    return false;
                } else if (this.x <= 0) {
                    Log.w(a, "invalid max entries");
                    return false;
                } else if (this.y <= 0) {
                    Log.w(a, "invalid max bytes");
                    return false;
                } else if (this.z != 0 && this.z != 1) {
                    Log.w(a, "invalid active region");
                    return false;
                } else if (this.A < 0 || this.A > this.x) {
                    Log.w(a, "invalid active entries");
                    return false;
                } else if (this.B < 4 || this.B > this.y) {
                    Log.w(a, "invalid active bytes");
                    return false;
                } else if (this.s.length() != ((long) (((this.x * 12) * 2) + 32))) {
                    Log.w(a, "invalid index file length");
                    return false;
                } else {
                    bArr = new byte[4];
                    if (this.t.read(bArr) != 4) {
                        Log.w(a, "cannot read data file magic");
                        return false;
                    } else if (a(bArr, 0) != c) {
                        Log.w(a, "invalid data file magic");
                        return false;
                    } else if (this.u.read(bArr) != 4) {
                        Log.w(a, "cannot read data file magic");
                        return false;
                    } else if (a(bArr, 0) != c) {
                        Log.w(a, "invalid data file magic");
                        return false;
                    } else {
                        this.v = this.s.getChannel();
                        this.w = this.v.map(MapMode.READ_WRITE, 0, this.s.length());
                        this.w.order(ByteOrder.LITTLE_ENDIAN);
                        g();
                        return true;
                    }
                }
            }
        } catch (Throwable e) {
            Log.e(a, "loadIndex failed.", e);
            return false;
        }
    }

    private void g() throws IOException {
        this.D = this.z == 0 ? this.t : this.u;
        this.E = this.z == 1 ? this.t : this.u;
        this.D.setLength((long) this.B);
        this.D.seek((long) this.B);
        this.F = 32;
        this.G = 32;
        if (this.z == 0) {
            this.G += this.x * 12;
        } else {
            this.F += this.x * 12;
        }
    }

    private void a(int i, int i2) throws IOException {
        this.s.setLength(0);
        this.s.setLength((long) (((i * 12) * 2) + 32));
        this.s.seek(0);
        byte[] bArr = this.H;
        b(bArr, 0, b);
        b(bArr, 4, i);
        b(bArr, 8, i2);
        b(bArr, 12, 0);
        b(bArr, 16, 0);
        b(bArr, 20, 4);
        b(bArr, 24, this.C);
        b(bArr, 28, a(bArr, 0, 28));
        this.s.write(bArr);
        this.t.setLength(0);
        this.u.setLength(0);
        this.t.seek(0);
        this.u.seek(0);
        b(bArr, 0, c);
        this.t.write(bArr, 0, 4);
        this.u.write(bArr, 0, 4);
    }

    private void h() throws IOException {
        this.z = 1 - this.z;
        this.A = 0;
        this.B = 4;
        b(this.H, 12, this.z);
        b(this.H, 16, this.A);
        b(this.H, 20, this.B);
        i();
        g();
        a(this.F);
        b();
    }

    private void i() {
        b(this.H, 28, a(this.H, 0, 28));
        this.w.position(0);
        this.w.put(this.H);
    }

    private void a(int i) {
        byte[] bArr = new byte[1024];
        this.w.position(i);
        int i2 = this.x * 12;
        while (i2 > 0) {
            int min = Math.min(i2, 1024);
            this.w.put(bArr, 0, min);
            i2 -= min;
        }
    }

    public void a(long j, byte[] bArr) throws IOException {
        if (bArr.length + 24 > this.y) {
            throw new RuntimeException("blob is too large!");
        }
        if ((this.B + 20) + bArr.length > this.y || this.A * 2 >= this.x) {
            h();
        }
        if (!a(j, this.F)) {
            this.A++;
            b(this.H, 16, this.A);
        }
        a(j, bArr, bArr.length);
        i();
    }

    private void a(long j, byte[] bArr, int i) throws IOException {
        byte[] bArr2 = this.I;
        int a = a(bArr);
        a(bArr2, 0, j);
        b(bArr2, 8, a);
        b(bArr2, 12, this.B);
        b(bArr2, 16, i);
        this.D.write(bArr2);
        this.D.write(bArr, 0, i);
        this.w.putLong(this.M, j);
        this.w.putInt(this.M + 8, this.B);
        this.B += i + 20;
        b(this.H, 20, this.B);
    }

    public byte[] a(long j) throws IOException {
        this.L.a = j;
        this.L.b = null;
        if (a(this.L)) {
            return this.L.b;
        }
        return null;
    }

    public boolean a(a aVar) throws IOException {
        if (a(aVar.a, this.F) && a(this.D, this.N, aVar)) {
            return true;
        }
        int i = this.M;
        if (!a(aVar.a, this.G) || !a(this.E, this.N, aVar)) {
            return false;
        }
        if ((this.B + 20) + aVar.c > this.y || this.A * 2 >= this.x) {
            return true;
        }
        this.M = i;
        try {
            a(aVar.a, aVar.b, aVar.c);
            this.A++;
            b(this.H, 16, this.A);
            i();
            return true;
        } catch (Throwable th) {
            Log.e(a, "cannot copy over");
            return true;
        }
    }

    private boolean a(RandomAccessFile randomAccessFile, int i, a aVar) throws IOException {
        byte[] bArr = this.I;
        long filePointer = randomAccessFile.getFilePointer();
        try {
            randomAccessFile.seek((long) i);
            if (randomAccessFile.read(bArr) != 20) {
                Log.w(a, "cannot read blob header");
                return false;
            }
            long b = b(bArr, 0);
            if (b != aVar.a) {
                Log.w(a, "blob key does not match: " + b);
                randomAccessFile.seek(filePointer);
                return false;
            }
            int a = a(bArr, 8);
            int a2 = a(bArr, 12);
            if (a2 != i) {
                Log.w(a, "blob offset does not match: " + a2);
                randomAccessFile.seek(filePointer);
                return false;
            }
            int a3 = a(bArr, 16);
            if (a3 < 0 || a3 > (this.y - i) - 20) {
                Log.w(a, "invalid blob length: " + a3);
                randomAccessFile.seek(filePointer);
                return false;
            }
            if (aVar.b == null || aVar.b.length < a3) {
                aVar.b = new byte[a3];
            }
            byte[] bArr2 = aVar.b;
            aVar.c = a3;
            if (randomAccessFile.read(bArr2, 0, a3) != a3) {
                Log.w(a, "cannot read blob data");
                randomAccessFile.seek(filePointer);
                return false;
            } else if (a(bArr2, 0, a3) != a) {
                Log.w(a, "blob checksum does not match: " + a);
                randomAccessFile.seek(filePointer);
                return false;
            } else {
                randomAccessFile.seek(filePointer);
                return true;
            }
        } catch (Throwable th) {
            Log.e(a, "getBlob failed.", th);
        } finally {
            randomAccessFile.seek(filePointer);
        }
    }

    private boolean a(long j, int i) {
        int i2 = (int) (j % ((long) this.x));
        if (i2 < 0) {
            i2 += this.x;
        }
        int i3 = i2;
        while (true) {
            int i4 = (i3 * 12) + i;
            long j2 = this.w.getLong(i4);
            int i5 = this.w.getInt(i4 + 8);
            if (i5 == 0) {
                this.M = i4;
                return false;
            } else if (j2 == j) {
                this.M = i4;
                this.N = i5;
                return true;
            } else {
                i3++;
                if (i3 >= this.x) {
                    i3 = 0;
                }
                if (i3 == i2) {
                    Log.w(a, "corrupted index: clear the slot.");
                    this.w.putInt(((i3 * 12) + i) + 8, 0);
                }
            }
        }
    }

    public void b() {
        try {
            this.w.force();
        } catch (Throwable th) {
            Log.w(a, "sync index failed", th);
        }
    }

    public void c() {
        b();
        try {
            this.t.getFD().sync();
        } catch (Throwable th) {
            Log.w(a, "sync data file 0 failed", th);
        }
        try {
            this.u.getFD().sync();
        } catch (Throwable th2) {
            Log.w(a, "sync data file 1 failed", th2);
        }
    }

    int d() {
        int i = 0;
        int i2 = 0;
        while (i < this.x) {
            if (this.w.getInt((this.F + (i * 12)) + 8) != 0) {
                i2++;
            }
            i++;
        }
        if (i2 == this.A) {
            return i2;
        }
        Log.e(a, "wrong active count: " + this.A + " vs " + i2);
        return -1;
    }

    int a(byte[] bArr) {
        this.J.reset();
        this.J.update(bArr);
        return (int) this.J.getValue();
    }

    int a(byte[] bArr, int i, int i2) {
        this.J.reset();
        this.J.update(bArr, i, i2);
        return (int) this.J.getValue();
    }

    static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
            }
        }
    }

    static int a(byte[] bArr, int i) {
        return (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 3] & 255) << 24);
    }

    static long b(byte[] bArr, int i) {
        long j = (long) (bArr[i + 7] & 255);
        for (int i2 = 6; i2 >= 0; i2--) {
            j = (j << 8) | ((long) (bArr[i + i2] & 255));
        }
        return j;
    }

    static void b(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            bArr[i + i3] = (byte) (i2 & 255);
            i2 >>= 8;
        }
    }

    static void a(byte[] bArr, int i, long j) {
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i + i2] = (byte) ((int) (255 & j));
            j >>= 8;
        }
    }
}
