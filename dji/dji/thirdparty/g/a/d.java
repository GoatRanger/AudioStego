package dji.thirdparty.g.a;

import dji.thirdparty.g.e;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class d extends InputStream implements a {
    protected boolean j = false;
    private final InputStream k;
    private int l = 77;

    public final void a(boolean z) {
        this.j = z;
    }

    public final boolean a() {
        return this.j;
    }

    public d(byte[] bArr, int i) {
        this.l = i;
        this.k = new ByteArrayInputStream(bArr);
    }

    public d(InputStream inputStream, int i) {
        this.l = i;
        this.k = inputStream;
    }

    public d(InputStream inputStream) {
        this.k = inputStream;
    }

    protected void a(int i, int i2) throws e, IOException {
        if (i != i2) {
            throw new e("Byte Order bytes don't match (" + i + ", " + i2 + ").");
        } else if (i == 77) {
            this.l = i;
        } else if (i == 73) {
            this.l = i;
        } else {
            throw new e("Unknown Byte Order hint: " + i);
        }
    }

    protected void a(int i) {
        this.l = i;
    }

    protected int b() {
        return this.l;
    }

    public int read() throws IOException {
        return this.k.read();
    }

    protected final int a(String str, byte[] bArr) {
        return b(str, bArr, this.l);
    }

    public final int b(String str, byte[] bArr) {
        return c(str, bArr, this.l);
    }

    public final int a(String str, int i, byte[] bArr) {
        return a(str, i, bArr, this.l);
    }

    public final int a(String str, String str2) throws e, IOException {
        return a(str, str2, this.l);
    }

    public final int b(String str, String str2) throws e, IOException {
        return b(str, str2, this.l);
    }

    public final int c(String str, String str2) throws e, IOException {
        return c(str, str2, this.l);
    }

    protected final void c() throws e, IOException {
        for (int i = 0; i < 100; i++) {
            d("" + i, "Random Data");
        }
    }

    public final void a(String str, int i) {
        a(str, i, 1);
    }

    public final void a(String str, int i, int i2) {
        System.out.print(str + ": " + i + " (");
        int i3 = i;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 > 0) {
                System.out.print(",");
            }
            int i5 = i3 & 255;
            System.out.print(((char) i5) + " [" + i5 + dji.pilot.usercenter.protocol.d.H);
            i3 >>= 8;
        }
        System.out.println(") [0x" + Integer.toHexString(i) + ", " + Integer.toBinaryString(i) + dji.pilot.usercenter.protocol.d.H);
    }

    public final void a(byte[] bArr, String str) throws e, IOException {
        int i = 0;
        while (i < bArr.length) {
            int read = this.k.read();
            int i2 = (byte) (read & 255);
            if (read < 0 || i2 != bArr[i]) {
                System.out.println("i: " + i);
                c("expected", bArr);
                a("data[" + i + dji.pilot.usercenter.protocol.d.H, i2);
                throw new e(str);
            }
            i++;
        }
    }

    protected final void a(String str, byte[] bArr, String str2) throws e, IOException {
        byte[] a = a(str, bArr.length, str2);
        for (int i = 0; i < bArr.length; i++) {
            if (a[i] != bArr[i]) {
                System.out.println("i: " + i);
                a("bytes[" + i + dji.pilot.usercenter.protocol.d.H, a[i]);
                a("expected[" + i + dji.pilot.usercenter.protocol.d.H, bArr[i]);
                throw new e(str2);
            }
        }
    }

    public final void a(int i, String str) throws IOException {
        long j = 0;
        while (((long) i) != j) {
            long skip = this.k.skip(((long) i) - j);
            if (skip < 1) {
                throw new IOException(str + " (" + skip + ")");
            }
            j += skip;
        }
    }

    protected final void a(byte b) throws IOException {
        int i = 0;
        int i2 = 0;
        while (i < 3) {
            int read = this.k.read();
            if (read >= 0) {
                if ((read & 255) == b) {
                    System.out.println("\t" + i2 + ": match.");
                    i++;
                }
                i2++;
            } else {
                return;
            }
        }
    }

    public final byte d(String str, String str2) throws IOException {
        int read = this.k.read();
        if (read < 0) {
            System.out.println(str + ": " + read);
            throw new IOException(str2);
        }
        if (this.j) {
            a(str, read);
        }
        return (byte) (read & 255);
    }

    protected final m[] a(String str, byte[] bArr, int i, int i2, int i3) {
        int i4 = (i2 * 8) + i;
        if (bArr.length < i4) {
            System.out.println(str + ": expected length: " + i4 + ", actual length: " + bArr.length);
            return null;
        }
        m[] mVarArr = new m[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            mVarArr[i5] = a(str, bArr, (i5 * 8) + i, i3);
        }
        return mVarArr;
    }

    protected final m a(String str, byte[] bArr, int i) {
        return a(str, bArr, 0, i);
    }

    protected final m a(String str, byte[] bArr, int i, int i2) {
        return new m(b(str, bArr, i + 0, 4, i2), b(str, bArr, i + 4, 4, i2));
    }

    protected final int b(String str, byte[] bArr, int i) {
        return b(str, bArr, 0, 4, i);
    }

    protected final int b(String str, byte[] bArr, int i, int i2, int i3) {
        byte b = bArr[i + 0];
        byte b2 = bArr[i + 1];
        byte b3 = bArr[i + 2];
        int i4 = 0;
        if (i2 == 4) {
            i4 = bArr[i + 3];
        }
        if (i3 == 77) {
            i4 = ((i4 & 255) << 0) + ((((b & 255) << 24) + ((b2 & 255) << 16)) + ((b3 & 255) << 8));
        } else {
            i4 = ((((i4 & 255) << 24) + ((b3 & 255) << 16)) + ((b2 & 255) << 8)) + ((b & 255) << 0);
        }
        if (this.j) {
            a(str, i4, 4);
        }
        return i4;
    }

    protected final int[] c(String str, byte[] bArr, int i, int i2, int i3) {
        int i4 = (i2 * 4) + i;
        if (bArr.length < i4) {
            System.out.println(str + ": expected length: " + i4 + ", actual length: " + bArr.length);
            return null;
        }
        int[] iArr = new int[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            iArr[i5] = b(str, bArr, i + (i5 * 4), 4, i3);
        }
        return iArr;
    }

    protected final int c(String str, byte[] bArr, int i) {
        return a(str, 0, bArr, i);
    }

    protected final int a(String str, int i, byte[] bArr, int i2) {
        int i3;
        byte b = bArr[i + 0];
        byte b2 = bArr[i + 1];
        if (i2 == 77) {
            i3 = ((b & 255) << 8) + ((b2 & 255) << 0);
        } else {
            i3 = ((b & 255) << 0) + ((b2 & 255) << 8);
        }
        if (this.j) {
            a(str, i3, 2);
        }
        return i3;
    }

    protected final int[] d(String str, byte[] bArr, int i, int i2, int i3) {
        int i4 = (i2 * 2) + i;
        if (bArr.length < i4) {
            System.out.println(str + ": expected length: " + i4 + ", actual length: " + bArr.length);
            return null;
        }
        int[] iArr = new int[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            iArr[i5] = a(str, (i5 * 2) + i, bArr, i3);
        }
        return iArr;
    }

    public final byte[] a(String str, int i, String str2) throws e, IOException {
        int i2 = 0;
        byte[] bArr = new byte[i];
        int i3 = 0;
        while (i3 < i) {
            int read = this.k.read(bArr, i3, i - i3);
            if (read < 1) {
                throw new IOException(str2);
            }
            i3 += read;
        }
        if (this.j) {
            while (i2 < i && i2 < 150) {
                a(str + " (" + i2 + ")", bArr[i2] & 255);
                i2++;
            }
        }
        return bArr;
    }

    protected final void c(String str, byte[] bArr) {
        System.out.println(str + ": " + bArr.length);
        int i = 0;
        while (i < bArr.length && i < 50) {
            a(str + " (" + i + ")", bArr[i]);
            i++;
        }
    }

    protected final void a(String str, int[] iArr, int i) {
        System.out.println(str + ": " + iArr.length);
        int i2 = 0;
        while (i2 < iArr.length && i2 < 50) {
            a(str + " (" + i2 + ")", iArr[i2], i);
            i2++;
        }
    }

    public final byte[] b(String str, byte[] bArr, int i, int i2) {
        if (bArr.length < i + i2) {
            return null;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        if (!this.j) {
            return bArr2;
        }
        c(str, bArr2);
        return bArr2;
    }

    public final byte[] b(int i, String str) throws e, IOException {
        return a(i, str, false, true);
    }

    public final byte[] a(int i, String str, boolean z, boolean z2) throws e, IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (true) {
            int read = read(bArr, i2, i - i2);
            if (read <= 0) {
                break;
            }
            i2 += read;
        }
        if (i2 >= i) {
            return bArr;
        }
        if (z2) {
            throw new e(str);
        }
        if (z) {
            System.out.println(str);
        }
        return null;
    }

    protected final byte[] d(String str, byte[] bArr, int i) {
        return b(str, bArr, i, bArr.length - i);
    }

    protected final byte[] e(String str, byte[] bArr, int i) {
        return b(str, bArr, 0, bArr.length - i);
    }

    public final boolean a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr.length < i + i3 || bArr2.length < i2 + i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i + i4] != bArr2[i2 + i4]) {
                a("a[" + (i + i4) + dji.pilot.usercenter.protocol.d.H, bArr[i + i4]);
                a("b[" + (i2 + i4) + dji.pilot.usercenter.protocol.d.H, bArr2[i4 + i2]);
                return false;
            }
        }
        return true;
    }

    protected final int a(String str, String str2, int i) throws e, IOException {
        byte[] bArr = new byte[4];
        int i2 = 0;
        while (i2 < 4) {
            int read = this.k.read(bArr, i2, 4 - i2);
            if (read < 1) {
                throw new IOException(str2);
            }
            i2 += read;
        }
        return b(str, bArr, i);
    }

    protected final int b(String str, String str2, int i) throws e, IOException {
        byte[] bArr = new byte[3];
        int i2 = 0;
        while (i2 < 3) {
            int read = this.k.read(bArr, i2, 3 - i2);
            if (read < 1) {
                throw new IOException(str2);
            }
            i2 += read;
        }
        return b(str, bArr, 0, 3, i);
    }

    protected final int c(String str, String str2, int i) throws e, IOException {
        byte[] bArr = new byte[2];
        int i2 = 0;
        while (i2 < 2) {
            int read = this.k.read(bArr, i2, 2 - i2);
            if (read < 1) {
                throw new IOException(str2);
            }
            i2 += read;
        }
        return c(str, bArr, i);
    }

    public final int a(String str) throws e, IOException {
        int read = this.k.read();
        if (read >= 0) {
            return read & 255;
        }
        throw new e(str);
    }

    public final int b(String str) throws e, IOException {
        int read = this.k.read();
        int read2 = this.k.read();
        if (read < 0 || read2 < 0) {
            throw new e(str);
        } else if (this.l == 77) {
            return ((read & 255) << 8) + ((read2 & 255) << 0);
        } else {
            return ((read & 255) << 0) + ((read2 & 255) << 8);
        }
    }

    public final int c(String str) throws e, IOException {
        int read = this.k.read();
        int read2 = this.k.read();
        int read3 = this.k.read();
        int read4 = this.k.read();
        if (read < 0 || read2 < 0 || read3 < 0 || read4 < 0) {
            throw new e(str);
        } else if (this.l == 77) {
            return ((((read & 255) << 24) + ((read2 & 255) << 16)) + ((read3 & 255) << 8)) + ((read4 & 255) << 0);
        } else {
            return ((read & 255) << 0) + (((read2 & 255) << 8) + (((read3 & 255) << 16) + ((read4 & 255) << 24)));
        }
    }

    protected final void b(String str, int i) {
        System.out.println(str + ": '" + ((char) ((i >> 24) & 255)) + ((char) ((i >> 16) & 255)) + ((char) ((i >> 8) & 255)) + ((char) ((i >> 0) & 255)) + "'");
    }

    protected final void a(String str, byte b) {
        System.out.println(str + ": '" + Integer.toBinaryString(b & 255));
    }

    protected static final int a(char c, char c2, char c3, char c4) {
        return ((((c & 255) << 24) | ((c2 & 255) << 16)) | ((c3 & 255) << 8)) | ((c4 & 255) << 0);
    }

    public final int a(byte[] bArr) {
        return a(bArr, 0);
    }

    public final int a(byte[] bArr, int i) {
        while (i < bArr.length) {
            if (bArr[i] == (byte) 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    protected final byte[] a(RandomAccessFile randomAccessFile, long j, int i, String str) throws IOException {
        byte[] bArr = new byte[i];
        if (this.j) {
            System.out.println("getRAFBytes pos: " + j);
            System.out.println("getRAFBytes length: " + i);
        }
        randomAccessFile.seek(j);
        int i2 = 0;
        while (i2 < i) {
            int read = randomAccessFile.read(bArr, i2, i - i2);
            if (read < 1) {
                throw new IOException(str);
            }
            i2 += read;
        }
        return bArr;
    }

    protected void b(int i) throws IOException {
        a(i, "Couldn't skip bytes");
    }
}
