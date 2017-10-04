package com.f.a.a;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import org.xeustechnologies.jtar.TarHeader;

public final class h {
    private static final int a = 37;
    private static final int b = 17;
    private static final byte[] c;
    private static final byte[] d;

    public static boolean a(boolean z, boolean z2) {
        return z == z2;
    }

    public static boolean a(byte b, byte b2) {
        return b == b2;
    }

    public static boolean a(char c, char c2) {
        return c == c2;
    }

    public static boolean a(short s, short s2) {
        return s == s2;
    }

    public static boolean a(int i, int i2) {
        return i == i2;
    }

    public static boolean a(long j, long j2) {
        return j == j2;
    }

    public static boolean a(float f, float f2) {
        return f == f2;
    }

    public static boolean a(double d, double d2) {
        return d == d2;
    }

    public static boolean a(Object obj, Object obj2) {
        return obj.equals(obj2);
    }

    public static int b(boolean z, boolean z2) {
        int i = 1;
        int i2 = z ? 1 : 0;
        if (!z2) {
            i = 0;
        }
        return i2 - i;
    }

    public static int b(byte b, byte b2) {
        if (b < b2) {
            return -1;
        }
        return b > b2 ? 1 : 0;
    }

    public static int b(char c, char c2) {
        if (c < c2) {
            return -1;
        }
        return c > c2 ? 1 : 0;
    }

    public static int b(short s, short s2) {
        if (s < s2) {
            return -1;
        }
        return s > s2 ? 1 : 0;
    }

    public static int b(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i > i2 ? 1 : 0;
    }

    public static int b(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j > j2 ? 1 : 0;
    }

    public static int b(float f, float f2) {
        if (f < f2) {
            return -1;
        }
        return f > f2 ? 1 : 0;
    }

    public static int b(double d, double d2) {
        if (d < d2) {
            return -1;
        }
        return d > d2 ? 1 : 0;
    }

    public static <T extends Comparable<T>> int a(T t, T t2) {
        return t.compareTo(t2);
    }

    public static <T extends Comparable<T>> int a(List<T> list, List<T> list2) {
        Iterator it = list.iterator();
        Iterator it2 = list2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            int compareTo = ((Comparable) it.next()).compareTo(it2.next());
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return b(it.hasNext(), it2.hasNext());
    }

    public static <T extends Comparable<T>> int a(T[] tArr, T[] tArr2) {
        int i = 0;
        int i2 = 0;
        while (i2 < tArr.length && i < tArr2.length) {
            int compareTo = tArr[i2].compareTo(tArr2[i]);
            if (compareTo != 0) {
                return compareTo;
            }
            i2++;
            i++;
        }
        return b(tArr.length, tArr2.length);
    }

    public static int a(boolean[] zArr, boolean[] zArr2) {
        int i = 0;
        int i2 = 0;
        while (i2 < zArr.length && i < zArr2.length) {
            int b = b(zArr[i2], zArr2[i]);
            if (b != 0) {
                return b;
            }
            i2++;
            i++;
        }
        return b(zArr.length, zArr2.length);
    }

    public static int a(byte[] bArr, byte[] bArr2) {
        int i = 0;
        int i2 = 0;
        while (i2 < bArr.length && i < bArr2.length) {
            int b = b(bArr[i2], bArr2[i]);
            if (b != 0) {
                return b;
            }
            i2++;
            i++;
        }
        return b(bArr.length, bArr2.length);
    }

    public static int a(char[] cArr, char[] cArr2) {
        int i = 0;
        int i2 = 0;
        while (i2 < cArr.length && i < cArr2.length) {
            int b = b(cArr[i2], cArr2[i]);
            if (b != 0) {
                return b;
            }
            i2++;
            i++;
        }
        return b(cArr.length, cArr2.length);
    }

    public static int a(short[] sArr, short[] sArr2) {
        int i = 0;
        int i2 = 0;
        while (i2 < sArr.length && i < sArr2.length) {
            int b = b(sArr[i2], sArr2[i]);
            if (b != 0) {
                return b;
            }
            i2++;
            i++;
        }
        return b(sArr.length, sArr2.length);
    }

    public static int a(int[] iArr, int[] iArr2) {
        int i = 0;
        int i2 = 0;
        while (i2 < iArr.length && i < iArr2.length) {
            int b = b(iArr[i2], iArr2[i]);
            if (b != 0) {
                return b;
            }
            i2++;
            i++;
        }
        return b(iArr.length, iArr2.length);
    }

    public static int a(long[] jArr, long[] jArr2) {
        int i = 0;
        int i2 = 0;
        while (i2 < jArr.length && i < jArr2.length) {
            int b = b(jArr[i2], jArr2[i]);
            if (b != 0) {
                return b;
            }
            i2++;
            i++;
        }
        return b(jArr.length, jArr2.length);
    }

    public static int a(float[] fArr, float[] fArr2) {
        int i = 0;
        int i2 = 0;
        while (i2 < fArr.length && i < fArr2.length) {
            int b = b(fArr[i2], fArr2[i]);
            if (b != 0) {
                return b;
            }
            i2++;
            i++;
        }
        return b(fArr.length, fArr2.length);
    }

    public static int a(double[] dArr, double[] dArr2) {
        int i = 0;
        int i2 = 0;
        while (i2 < dArr.length && i < dArr2.length) {
            int b = b(dArr[i2], dArr2[i]);
            if (b != 0) {
                return b;
            }
            i2++;
            i++;
        }
        return b(dArr.length, dArr2.length);
    }

    public static int a(boolean z) {
        return (z ? 0 : 1) + 629;
    }

    public static int a(boolean[] zArr) {
        if (zArr == null) {
            return 629;
        }
        int i = 17;
        for (boolean z : zArr) {
            i = (z ? 0 : 1) + (i * 37);
        }
        return i;
    }

    public static int a(byte b) {
        return b + 629;
    }

    public static int a(byte[] bArr) {
        if (bArr == null) {
            return 629;
        }
        int i = 17;
        for (byte b : bArr) {
            i = (i * 37) + b;
        }
        return i;
    }

    public static int a(char c) {
        return c + 629;
    }

    public static int a(char[] cArr) {
        if (cArr == null) {
            return 629;
        }
        int i = 17;
        for (char c : cArr) {
            i = (i * 37) + c;
        }
        return i;
    }

    public static int a(double d) {
        return a(Double.doubleToLongBits(d));
    }

    public static int a(double[] dArr) {
        if (dArr == null) {
            return 629;
        }
        int i = 17;
        for (int i2 = 0; i2 < dArr.length; i2++) {
            i = (i * 37) + ((int) (Double.doubleToLongBits(dArr[i2]) ^ (Double.doubleToLongBits(dArr[i2]) >> 32)));
        }
        return i;
    }

    public static int a(float f) {
        return Float.floatToIntBits(f) + 629;
    }

    public static int a(float[] fArr) {
        if (fArr == null) {
            return 629;
        }
        int i = 17;
        for (float floatToIntBits : fArr) {
            i = (i * 37) + Float.floatToIntBits(floatToIntBits);
        }
        return i;
    }

    public static int a(short s) {
        return s + 629;
    }

    public static int a(short[] sArr) {
        if (sArr == null) {
            return 629;
        }
        int i = 17;
        for (short s : sArr) {
            i = (i * 37) + s;
        }
        return i;
    }

    public static int a(int i) {
        return i + 629;
    }

    public static int a(int[] iArr) {
        if (iArr == null) {
            return 629;
        }
        int i = 17;
        for (int i2 : iArr) {
            i = (i * 37) + i2;
        }
        return i;
    }

    public static int a(long j) {
        return ((int) ((j >> 32) ^ j)) + 629;
    }

    public static int a(long[] jArr) {
        if (jArr == null) {
            return 629;
        }
        int i = 17;
        for (int i2 = 0; i2 < jArr.length; i2++) {
            i = (i * 37) + ((int) (jArr[i2] ^ (jArr[i2] >> 32)));
        }
        return i;
    }

    public static int a(g[] gVarArr) {
        if (gVarArr == null) {
            return 629;
        }
        int i = 17;
        for (Object hashCode : gVarArr) {
            i = (i * 37) + hashCode.hashCode();
        }
        return i;
    }

    public static int a(Object obj) {
        if (obj == null) {
            return 629;
        }
        if (obj.getClass().isArray()) {
            if (obj instanceof long[]) {
                return a((long[]) obj);
            }
            if (obj instanceof int[]) {
                return a((int[]) obj);
            }
            if (obj instanceof short[]) {
                return a((short[]) obj);
            }
            if (obj instanceof char[]) {
                return a((char[]) obj);
            }
            if (obj instanceof byte[]) {
                return a((byte[]) obj);
            }
            if (obj instanceof double[]) {
                return a((double[]) obj);
            }
            if (obj instanceof float[]) {
                return a((float[]) obj);
            }
            if (obj instanceof boolean[]) {
                return a((boolean[]) obj);
            }
            if (obj instanceof g[]) {
                return a((g[]) obj);
            }
            return a((Object) (Object[]) obj);
        } else if (obj instanceof g) {
            return obj.hashCode();
        } else {
            return obj.hashCode() + 629;
        }
    }

    public static byte[] a(ByteBuffer byteBuffer) {
        Object obj = new byte[byteBuffer.position()];
        System.arraycopy(byteBuffer.array(), 0, obj, 0, obj.length);
        return obj;
    }

    static {
        byte[] bArr = new byte[]{TarHeader.LF_NORMAL, TarHeader.LF_LINK, TarHeader.LF_SYMLINK, TarHeader.LF_CHR, TarHeader.LF_BLK, TarHeader.LF_DIR, TarHeader.LF_FIFO, TarHeader.LF_CONTIG, (byte) 56, (byte) 57, (byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70};
        byte[] bArr2 = new byte[256];
        byte[] bArr3 = new byte[256];
        for (int i = 0; i < 256; i++) {
            bArr2[i] = bArr[i >>> 4];
            bArr3[i] = bArr[i & 15];
        }
        c = bArr2;
        d = bArr3;
    }

    public static String b(byte[] bArr) {
        return b(ByteBuffer.wrap(bArr));
    }

    public static String b(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        if (remaining == 0) {
            return "empty";
        }
        StringBuffer stringBuffer = new StringBuffer((byteBuffer.remaining() * 3) - 1);
        int position = byteBuffer.position();
        int i = byteBuffer.get() & 255;
        stringBuffer.append((char) c[i]);
        stringBuffer.append((char) d[i]);
        for (remaining--; remaining > 0; remaining--) {
            stringBuffer.append(' ');
            i = byteBuffer.get() & 255;
            stringBuffer.append((char) c[i]);
            stringBuffer.append((char) d[i]);
        }
        byteBuffer.position(position);
        return stringBuffer.toString();
    }
}
