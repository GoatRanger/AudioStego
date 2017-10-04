package dji.thirdparty.g.a;

import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.g.e;
import dji.thirdparty.g.f;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class b implements a {
    protected boolean fn_ = false;

    public final void a(boolean z) {
        this.fn_ = z;
    }

    public final boolean f() {
        return this.fn_;
    }

    protected final void a(InputStream inputStream) throws e, IOException {
        for (int i = 0; i < 100; i++) {
            a("" + i, inputStream, "Random Data");
        }
    }

    public final void a(String str, int i) {
        a(str, i, 1);
    }

    public final void a(String str, int i, int i2) {
        PrintWriter printWriter = new PrintWriter(System.out);
        a(printWriter, str, i, i2);
        printWriter.flush();
    }

    public final void a(PrintWriter printWriter, String str, int i) {
        a(printWriter, str, i, 1);
    }

    public final void a(PrintWriter printWriter, String str, int i, int i2) {
        printWriter.print(str + ": " + i + " (");
        int i3 = i;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 > 0) {
                printWriter.print(",");
            }
            int i5 = i3 & 255;
            printWriter.print(((char) i5) + " [" + i5 + d.H);
            i3 >>= 8;
        }
        printWriter.println(") [0x" + Integer.toHexString(i) + ", " + Integer.toBinaryString(i) + d.H);
        printWriter.flush();
    }

    public final boolean a(byte[] bArr, byte[] bArr2) {
        if (bArr2 == null || bArr == null || bArr2.length > bArr.length) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr2[i] != bArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final byte[] a(InputStream inputStream, int i) throws e, IOException {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) inputStream.read();
        }
        return bArr;
    }

    public final void a(InputStream inputStream, byte[] bArr, String str) throws e, IOException {
        int i = 0;
        while (i < bArr.length) {
            int read = inputStream.read();
            byte b = (byte) (read & 255);
            if (read < 0) {
                throw new e("Unexpected EOF.");
            } else if (b != bArr[i]) {
                throw new e(str);
            } else {
                i++;
            }
        }
    }

    protected final void a(String str, InputStream inputStream, byte[] bArr, String str2) throws e, IOException {
        byte[] a = a(str, bArr.length, inputStream, str2);
        for (int i = 0; i < bArr.length; i++) {
            if (a[i] != bArr[i]) {
                throw new e(str2);
            }
        }
    }

    public final void a(InputStream inputStream, int i, String str) throws IOException {
        long j = 0;
        while (((long) i) != j) {
            long skip = inputStream.skip(((long) i) - j);
            if (skip < 1) {
                throw new IOException(str + " (" + skip + ")");
            }
            j += skip;
        }
    }

    protected final void a(InputStream inputStream, byte b) throws IOException {
        int i = 0;
        int i2 = 0;
        while (i < 3) {
            int read = inputStream.read();
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

    public final byte a(String str, InputStream inputStream, String str2) throws e, IOException {
        int read = inputStream.read();
        if (read < 0) {
            System.out.println(str + ": " + read);
            throw new IOException(str2);
        }
        if (this.fn_) {
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
        return new m(b(str, bArr, i + 0, i2), b(str, bArr, i + 4, i2));
    }

    protected final int b(String str, byte[] bArr, int i) {
        return b(str, bArr, 0, i);
    }

    protected final int b(String str, byte[] bArr, int i, int i2) {
        int i3;
        byte b = bArr[i + 0];
        byte b2 = bArr[i + 1];
        byte b3 = bArr[i + 2];
        byte b4 = bArr[i + 3];
        if (i2 == 77) {
            i3 = ((((b & 255) << 24) | ((b2 & 255) << 16)) | ((b3 & 255) << 8)) | ((b4 & 255) << 0);
        } else {
            int i4 = (b2 & 255) << 8;
            i3 = (b & 255) << 0;
            i3 |= i4 | (((b3 & 255) << 16) | ((b4 & 255) << 24));
        }
        if (this.fn_) {
            a(str, i3, 4);
        }
        return i3;
    }

    protected final int[] b(String str, byte[] bArr, int i, int i2, int i3) {
        int i4 = (i2 * 4) + i;
        if (bArr.length < i4) {
            System.out.println(str + ": expected length: " + i4 + ", actual length: " + bArr.length);
            return null;
        }
        int[] iArr = new int[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            iArr[i5] = b(str, bArr, (i5 * 4) + i, i3);
        }
        return iArr;
    }

    protected final void a(int i, byte[] bArr, int i2, int i3) {
        if (i3 == 77) {
            bArr[i2 + 0] = (byte) (i >> 24);
            bArr[i2 + 1] = (byte) (i >> 16);
            bArr[i2 + 2] = (byte) (i >> 8);
            bArr[i2 + 3] = (byte) (i >> 0);
            return;
        }
        bArr[i2 + 3] = (byte) (i >> 24);
        bArr[i2 + 2] = (byte) (i >> 16);
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2 + 0] = (byte) (i >> 0);
    }

    protected static final byte[] a(int i, int i2) {
        if (i2 == 77) {
            return new byte[]{(byte) (i >> 8), (byte) (i >> 0)};
        }
        return new byte[]{(byte) (i >> 0), (byte) (i >> 8)};
    }

    protected final byte[] a(int[] iArr, int i) {
        byte[] bArr = new byte[(iArr.length * 4)];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            a(iArr[i2], bArr, i2 * 4, i);
        }
        return bArr;
    }

    protected final byte[] b(int[] iArr, int i) {
        byte[] bArr = new byte[(iArr.length * 2)];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            if (i == 77) {
                bArr[(i2 * 2) + 0] = (byte) (i3 >> 8);
                bArr[(i2 * 2) + 1] = (byte) (i3 >> 0);
            } else {
                bArr[(i2 * 2) + 1] = (byte) (i3 >> 8);
                bArr[(i2 * 2) + 0] = (byte) (i3 >> 0);
            }
        }
        return bArr;
    }

    protected final byte[] b(int i, int i2) {
        byte[] bArr = new byte[2];
        if (i2 == 77) {
            bArr[0] = (byte) (i >> 8);
            bArr[1] = (byte) (i >> 0);
        } else {
            bArr[1] = (byte) (i >> 8);
            bArr[0] = (byte) (i >> 0);
        }
        return bArr;
    }

    protected final byte[] a(int[] iArr, int[] iArr2, int i) throws f {
        if (iArr.length != iArr2.length) {
            throw new f("numerators.length (" + iArr.length + " != denominators.length (" + iArr2.length + ")");
        }
        byte[] bArr = new byte[(iArr.length * 8)];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            a(iArr[i2], bArr, i2 * 8, i);
            a(iArr2[i2], bArr, (i2 * 8) + 4, i);
        }
        return bArr;
    }

    protected final byte[] a(m[] mVarArr, int i) throws f {
        byte[] bArr = new byte[(mVarArr.length * 8)];
        for (int i2 = 0; i2 < mVarArr.length; i2++) {
            a(mVarArr[i2].a, bArr, i2 * 8, i);
            a(mVarArr[i2].b, bArr, (i2 * 8) + 4, i);
        }
        return bArr;
    }

    protected final byte[] a(m mVar, int i) throws f {
        byte[] bArr = new byte[8];
        a(mVar.a, bArr, 0, i);
        a(mVar.b, bArr, 4, i);
        return bArr;
    }

    protected final int c(String str, byte[] bArr, int i) throws e {
        return a(str, 0, bArr, i);
    }

    protected final int a(String str, int i, byte[] bArr, int i2) throws e {
        if (i + 1 >= bArr.length) {
            throw new e("Index out of bounds. Array size: " + bArr.length + ", index: " + i);
        }
        int i3 = bArr[i + 0] & 255;
        int i4 = bArr[i + 1] & 255;
        if (i2 == 77) {
            i3 = (i3 << 8) | i4;
        } else {
            i3 |= i4 << 8;
        }
        if (this.fn_) {
            a(str, i3, 2);
        }
        return i3;
    }

    protected final int[] c(String str, byte[] bArr, int i, int i2, int i3) throws e {
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

    public final byte[] a(String str, int i, InputStream inputStream) throws IOException {
        return a(str, i, inputStream, str + " could not be read.");
    }

    public final byte[] a(String str, int i, InputStream inputStream, String str2) throws IOException {
        int i2 = 0;
        byte[] bArr = new byte[i];
        int i3 = 0;
        while (i3 < i) {
            int read = inputStream.read(bArr, i3, i - i3);
            if (read < 1) {
                throw new IOException(str2);
            }
            i3 += read;
        }
        if (this.fn_) {
            while (i2 < i && i2 < 50) {
                a(str + " (" + i2 + ")", bArr[i2] & 255);
                i2++;
            }
        }
        return bArr;
    }

    public final void a(String str, byte[] bArr) {
        System.out.println(str + ": " + bArr.length);
        int i = 0;
        while (i < bArr.length && i < 50) {
            a("\t (" + i + ")", bArr[i] & 255);
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

    public final byte[] c(String str, byte[] bArr, int i, int i2) throws e {
        if (bArr.length < i + i2) {
            throw new e("Invalid read. bytes.length: " + bArr.length + ", start: " + i + ", count: " + i2);
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        if (this.fn_) {
            a(str, bArr2);
        }
        return bArr2;
    }

    protected final byte[] d(String str, byte[] bArr, int i) throws e {
        return c(str, bArr, i, bArr.length - i);
    }

    protected final byte[] e(String str, byte[] bArr, int i) throws e {
        return c(str, bArr, 0, bArr.length - i);
    }

    public static final byte[] a(byte[] bArr, int i, int i2) {
        if (bArr.length < i + i2) {
            return null;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static final byte[] a(byte[] bArr, int i) {
        if (i > bArr.length) {
            i = bArr.length;
        }
        return a(bArr, bArr.length - i, i);
    }

    public static final byte[] b(byte[] bArr, int i) {
        if (i > bArr.length) {
            i = bArr.length;
        }
        return a(bArr, 0, i);
    }

    public final boolean b(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        return a(bArr, 0, bArr2, 0, bArr.length);
    }

    public final boolean a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr.length < i + i3 || bArr2.length < i2 + i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i + i4] != bArr2[i2 + i4]) {
                return false;
            }
        }
        return true;
    }

    public static final boolean c(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        return b(bArr, 0, bArr2, 0, bArr.length);
    }

    public static final boolean b(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr.length < i + i3 || bArr2.length < i2 + i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i + i4] != bArr2[i2 + i4]) {
                return false;
            }
        }
        return true;
    }

    protected final int a(String str, InputStream inputStream, String str2, int i) throws e, IOException {
        byte[] bArr = new byte[4];
        int i2 = 0;
        while (i2 < 4) {
            int read = inputStream.read(bArr, i2, 4 - i2);
            if (read < 1) {
                throw new IOException(str2);
            }
            i2 += read;
        }
        return b(str, bArr, i);
    }

    protected final int b(String str, InputStream inputStream, String str2, int i) throws e, IOException {
        int i2;
        byte read = (byte) inputStream.read();
        byte read2 = (byte) inputStream.read();
        byte read3 = (byte) inputStream.read();
        if (i == 77) {
            i2 = (((read & 255) << 16) | ((read2 & 255) << 8)) | ((read3 & 255) << 0);
        } else {
            i2 = (read & 255) << 0;
            i2 |= ((read2 & 255) << 8) | ((read3 & 255) << 16);
        }
        if (this.fn_) {
            a(str, i2, 3);
        }
        return i2;
    }

    protected final int c(String str, InputStream inputStream, String str2, int i) throws e, IOException {
        byte[] bArr = new byte[2];
        int i2 = 0;
        while (i2 < 2) {
            int read = inputStream.read(bArr, i2, 2 - i2);
            if (read < 1) {
                throw new IOException(str2);
            }
            i2 += read;
        }
        return c(str, bArr, i);
    }

    protected final void b(String str, int i) {
        System.out.println(str + ": '" + ((char) ((i >> 24) & 255)) + ((char) ((i >> 16) & 255)) + ((char) ((i >> 8) & 255)) + ((char) ((i >> 0) & 255)) + "'");
    }

    protected final void b(PrintWriter printWriter, String str, int i) {
        printWriter.println(str + ": '" + ((char) ((i >> 24) & 255)) + ((char) ((i >> 16) & 255)) + ((char) ((i >> 8) & 255)) + ((char) ((i >> 0) & 255)) + "'");
    }

    protected final void a(String str, byte b) {
        System.out.println(str + ": '" + Integer.toBinaryString(b & 255));
    }

    public static final int a(char c, char c2, char c3, char c4) {
        return ((((c & 255) << 24) | ((c2 & 255) << 16)) | ((c3 & 255) << 8)) | ((c4 & 255) << 0);
    }

    public final int e(byte[] bArr) {
        return c(bArr, 0);
    }

    public final int c(byte[] bArr, int i) {
        while (i < bArr.length) {
            if (bArr[i] == (byte) 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    protected final byte[] a(RandomAccessFile randomAccessFile, long j, int i, String str) throws IOException {
        if (this.fn_) {
            System.out.println("getRAFBytes pos: " + j);
            System.out.println("getRAFBytes length: " + i);
        }
        byte[] bArr = new byte[i];
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

    protected final float f(String str, byte[] bArr, int i) {
        return d(str, bArr, 0, i);
    }

    protected final float d(String str, byte[] bArr, int i, int i2) {
        int i3;
        byte b = bArr[i + 0];
        byte b2 = bArr[i + 1];
        byte b3 = bArr[i + 2];
        byte b4 = bArr[i + 3];
        if (i2 == 77) {
            i3 = ((((b & 255) << 24) | ((b2 & 255) << 16)) | ((b3 & 255) << 8)) | ((b4 & 255) << 0);
        } else {
            int i4 = (b2 & 255) << 8;
            i3 = (b & 255) << 0;
            i3 |= i4 | (((b3 & 255) << 16) | ((b4 & 255) << 24));
        }
        return Float.intBitsToFloat(i3);
    }

    protected final float[] d(String str, byte[] bArr, int i, int i2, int i3) {
        int i4 = (i2 * 4) + i;
        if (bArr.length < i4) {
            System.out.println(str + ": expected length: " + i4 + ", actual length: " + bArr.length);
            return null;
        }
        float[] fArr = new float[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            fArr[i5] = d(str, bArr, (i5 * 4) + i, i3);
        }
        return fArr;
    }

    protected final byte[] a(float f, int i) {
        byte[] bArr = new byte[4];
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        if (i == 77) {
            bArr[0] = (byte) ((floatToRawIntBits >> 0) & 255);
            bArr[1] = (byte) ((floatToRawIntBits >> 8) & 255);
            bArr[2] = (byte) ((floatToRawIntBits >> 16) & 255);
            bArr[3] = (byte) ((floatToRawIntBits >> 24) & 255);
        } else {
            bArr[3] = (byte) ((floatToRawIntBits >> 0) & 255);
            bArr[2] = (byte) ((floatToRawIntBits >> 8) & 255);
            bArr[1] = (byte) ((floatToRawIntBits >> 16) & 255);
            bArr[0] = (byte) ((floatToRawIntBits >> 24) & 255);
        }
        return bArr;
    }

    protected final byte[] a(float[] fArr, int i) {
        byte[] bArr = new byte[(fArr.length * 4)];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            int floatToRawIntBits = Float.floatToRawIntBits(fArr[i2]);
            int i3 = i2 * 4;
            if (i == 77) {
                bArr[i3 + 0] = (byte) ((floatToRawIntBits >> 0) & 255);
                bArr[i3 + 1] = (byte) ((floatToRawIntBits >> 8) & 255);
                bArr[i3 + 2] = (byte) ((floatToRawIntBits >> 16) & 255);
                bArr[i3 + 3] = (byte) ((floatToRawIntBits >> 24) & 255);
            } else {
                bArr[i3 + 3] = (byte) ((floatToRawIntBits >> 0) & 255);
                bArr[i3 + 2] = (byte) ((floatToRawIntBits >> 8) & 255);
                bArr[i3 + 1] = (byte) ((floatToRawIntBits >> 16) & 255);
                bArr[i3 + 0] = (byte) ((floatToRawIntBits >> 24) & 255);
            }
        }
        return bArr;
    }

    protected final byte[] a(double d, int i) {
        byte[] bArr = new byte[8];
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        if (i == 77) {
            bArr[0] = (byte) ((int) ((doubleToRawLongBits >> 0) & 255));
            bArr[1] = (byte) ((int) ((doubleToRawLongBits >> 8) & 255));
            bArr[2] = (byte) ((int) ((doubleToRawLongBits >> 16) & 255));
            bArr[3] = (byte) ((int) ((doubleToRawLongBits >> 24) & 255));
            bArr[4] = (byte) ((int) ((doubleToRawLongBits >> 32) & 255));
            bArr[5] = (byte) ((int) ((doubleToRawLongBits >> 40) & 255));
            bArr[6] = (byte) ((int) ((doubleToRawLongBits >> 48) & 255));
            bArr[7] = (byte) ((int) ((doubleToRawLongBits >> 56) & 255));
        } else {
            bArr[7] = (byte) ((int) ((doubleToRawLongBits >> 0) & 255));
            bArr[6] = (byte) ((int) ((doubleToRawLongBits >> 8) & 255));
            bArr[5] = (byte) ((int) ((doubleToRawLongBits >> 16) & 255));
            bArr[4] = (byte) ((int) ((doubleToRawLongBits >> 24) & 255));
            bArr[3] = (byte) ((int) ((doubleToRawLongBits >> 32) & 255));
            bArr[2] = (byte) ((int) ((doubleToRawLongBits >> 40) & 255));
            bArr[1] = (byte) ((int) ((doubleToRawLongBits >> 48) & 255));
            bArr[0] = (byte) ((int) ((doubleToRawLongBits >> 56) & 255));
        }
        return bArr;
    }

    protected final byte[] a(double[] dArr, int i) {
        byte[] bArr = new byte[(dArr.length * 8)];
        for (int i2 = 0; i2 < dArr.length; i2++) {
            long doubleToRawLongBits = Double.doubleToRawLongBits(dArr[i2]);
            int i3 = i2 * 8;
            if (i == 77) {
                bArr[i3 + 0] = (byte) ((int) (255 & (doubleToRawLongBits >> null)));
                bArr[i3 + 1] = (byte) ((int) (255 & (doubleToRawLongBits >> 8)));
                bArr[i3 + 2] = (byte) ((int) (255 & (doubleToRawLongBits >> 16)));
                bArr[i3 + 3] = (byte) ((int) (255 & (doubleToRawLongBits >> 24)));
                bArr[i3 + 4] = (byte) ((int) (255 & (doubleToRawLongBits >> 32)));
                bArr[i3 + 5] = (byte) ((int) (255 & (doubleToRawLongBits >> 40)));
                bArr[i3 + 6] = (byte) ((int) (255 & (doubleToRawLongBits >> 48)));
                bArr[i3 + 7] = (byte) ((int) ((doubleToRawLongBits >> 56) & 255));
            } else {
                bArr[i3 + 7] = (byte) ((int) (255 & (doubleToRawLongBits >> null)));
                bArr[i3 + 6] = (byte) ((int) (255 & (doubleToRawLongBits >> 8)));
                bArr[i3 + 5] = (byte) ((int) (255 & (doubleToRawLongBits >> 16)));
                bArr[i3 + 4] = (byte) ((int) (255 & (doubleToRawLongBits >> 24)));
                bArr[i3 + 3] = (byte) ((int) (255 & (doubleToRawLongBits >> 32)));
                bArr[i3 + 2] = (byte) ((int) (255 & (doubleToRawLongBits >> 40)));
                bArr[i3 + 1] = (byte) ((int) (255 & (doubleToRawLongBits >> 48)));
                bArr[i3 + 0] = (byte) ((int) ((doubleToRawLongBits >> 56) & 255));
            }
        }
        return bArr;
    }

    protected final double g(String str, byte[] bArr, int i) {
        return e(str, bArr, 0, i);
    }

    protected final double e(String str, byte[] bArr, int i, int i2) {
        long j;
        byte b = bArr[i + 0];
        byte b2 = bArr[i + 1];
        byte b3 = bArr[i + 2];
        byte b4 = bArr[i + 3];
        byte b5 = bArr[i + 4];
        byte b6 = bArr[i + 5];
        byte b7 = bArr[i + 6];
        byte b8 = bArr[i + 7];
        if (i2 == 77) {
            j = (long) (((((((((b & 255) << 56) | ((b2 & 255) << 48)) | ((b3 & 255) << 40)) | ((b4 & 255) << 32)) | ((b5 & 255) << 24)) | ((b6 & 255) << 16)) | ((b7 & 255) << 8)) | ((b8 & 255) << 0));
        } else {
            int i3 = (b6 & 255) << 40;
            int i4 = (b5 & 255) << 32;
            int i5 = (b4 & 255) << 24;
            int i6 = (b3 & 255) << 16;
            int i7 = (b2 & 255) << 8;
            int i8 = (b & 255) << 0;
            j = (long) (i8 | (i7 | (i6 | (i5 | (i4 | (i3 | (((b7 & 255) << 48) | ((b8 & 255) << 56))))))));
        }
        return Double.longBitsToDouble(j);
    }

    protected final double[] e(String str, byte[] bArr, int i, int i2, int i3) {
        int i4 = (i2 * 8) + i;
        if (bArr.length < i4) {
            System.out.println(str + ": expected length: " + i4 + ", actual length: " + bArr.length);
            return null;
        }
        double[] dArr = new double[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            dArr[i5] = e(str, bArr, (i5 * 8) + i, i3);
        }
        return dArr;
    }

    protected void b(InputStream inputStream, int i) throws IOException {
        a(inputStream, i, "Couldn't skip bytes");
    }

    public final void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public final byte[] b(InputStream inputStream) throws IOException {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
