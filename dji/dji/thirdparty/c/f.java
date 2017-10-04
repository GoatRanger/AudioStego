package dji.thirdparty.c;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class f implements Serializable, Comparable<f> {
    static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final f b = a(new byte[0]);
    private static final long f = 1;
    final byte[] c;
    transient int d;
    transient String e;

    public /* synthetic */ int compareTo(Object obj) {
        return a((f) obj);
    }

    f(byte[] bArr) {
        this.c = bArr;
    }

    public static f a(byte... bArr) {
        if (bArr != null) {
            return new f((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public static f a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("data == null");
        }
        y.a((long) bArr.length, (long) i, (long) i2);
        Object obj = new byte[i2];
        System.arraycopy(bArr, i, obj, 0, i2);
        return new f(obj);
    }

    public static f a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        f fVar = new f(str.getBytes(y.a));
        fVar.e = str;
        return fVar;
    }

    public String a() {
        String str = this.e;
        if (str != null) {
            return str;
        }
        str = new String(this.c, y.a);
        this.e = str;
        return str;
    }

    public String b() {
        return b.a(this.c);
    }

    public f c() {
        return d("MD5");
    }

    public f d() {
        return d("SHA-1");
    }

    public f e() {
        return d("SHA-256");
    }

    private f d(String str) {
        try {
            return a(MessageDigest.getInstance(str).digest(this.c));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public String f() {
        return b.b(this.c);
    }

    public static f b(String str) {
        if (str == null) {
            throw new IllegalArgumentException("base64 == null");
        }
        byte[] a = b.a(str);
        return a != null ? new f(a) : null;
    }

    public String g() {
        int i = 0;
        char[] cArr = new char[(this.c.length * 2)];
        byte[] bArr = this.c;
        int length = bArr.length;
        int i2 = 0;
        while (i < length) {
            byte b = bArr[i];
            int i3 = i2 + 1;
            cArr[i2] = a[(b >> 4) & 15];
            i2 = i3 + 1;
            cArr[i3] = a[b & 15];
            i++;
        }
        return new String(cArr);
    }

    public static f c(String str) {
        if (str == null) {
            throw new IllegalArgumentException("hex == null");
        } else if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + str);
        } else {
            byte[] bArr = new byte[(str.length() / 2)];
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) ((a(str.charAt(i * 2)) << 4) + a(str.charAt((i * 2) + 1)));
            }
            return a(bArr);
        }
    }

    private static int a(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 97) + 10;
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 65) + 10;
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c);
    }

    public static f a(InputStream inputStream, int i) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        } else if (i < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + i);
        } else {
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int read = inputStream.read(bArr, i2, i - i2);
                if (read == -1) {
                    throw new EOFException();
                }
                i2 += read;
            }
            return new f(bArr);
        }
    }

    public f h() {
        int i = 0;
        while (i < this.c.length) {
            byte b = this.c[i];
            if (b < (byte) 65 || b > (byte) 90) {
                i++;
            } else {
                byte[] bArr = (byte[]) this.c.clone();
                int i2 = i + 1;
                bArr[i] = (byte) (b + 32);
                for (i = i2; i < bArr.length; i++) {
                    byte b2 = bArr[i];
                    if (b2 >= (byte) 65 && b2 <= (byte) 90) {
                        bArr[i] = (byte) (b2 + 32);
                    }
                }
                return new f(bArr);
            }
        }
        return this;
    }

    public f i() {
        int i = 0;
        while (i < this.c.length) {
            byte b = this.c[i];
            if (b < (byte) 97 || b > (byte) 122) {
                i++;
            } else {
                byte[] bArr = (byte[]) this.c.clone();
                int i2 = i + 1;
                bArr[i] = (byte) (b - 32);
                for (i = i2; i < bArr.length; i++) {
                    byte b2 = bArr[i];
                    if (b2 >= (byte) 97 && b2 <= (byte) 122) {
                        bArr[i] = (byte) (b2 - 32);
                    }
                }
                return new f(bArr);
            }
        }
        return this;
    }

    public f a(int i) {
        return a(i, this.c.length);
    }

    public f a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        } else if (i2 > this.c.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.c.length + ")");
        } else {
            int i3 = i2 - i;
            if (i3 < 0) {
                throw new IllegalArgumentException("endIndex < beginIndex");
            } else if (i == 0 && i2 == this.c.length) {
                return this;
            } else {
                Object obj = new byte[i3];
                System.arraycopy(this.c, i, obj, 0, i3);
                this(obj);
                return this;
            }
        }
    }

    public byte b(int i) {
        return this.c[i];
    }

    public int j() {
        return this.c.length;
    }

    public byte[] k() {
        return (byte[]) this.c.clone();
    }

    byte[] l() {
        return this.c;
    }

    public ByteBuffer m() {
        return ByteBuffer.wrap(this.c).asReadOnlyBuffer();
    }

    public void a(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        outputStream.write(this.c);
    }

    void a(c cVar) {
        cVar.b(this.c, 0, this.c.length);
    }

    public boolean a(int i, f fVar, int i2, int i3) {
        return fVar.a(i2, this.c, i, i3);
    }

    public boolean a(int i, byte[] bArr, int i2, int i3) {
        return i <= this.c.length - i3 && i2 <= bArr.length - i3 && y.a(this.c, i, bArr, i2, i3);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        boolean z = (obj instanceof f) && ((f) obj).j() == this.c.length && ((f) obj).a(0, this.c, 0, this.c.length);
        return z;
    }

    public int hashCode() {
        int i = this.d;
        if (i != 0) {
            return i;
        }
        i = Arrays.hashCode(this.c);
        this.d = i;
        return i;
    }

    public int a(f fVar) {
        int j = j();
        int j2 = fVar.j();
        int min = Math.min(j, j2);
        int i = 0;
        while (i < min) {
            int b = b(i) & 255;
            int b2 = fVar.b(i) & 255;
            if (b == b2) {
                i++;
            } else if (b < b2) {
                return -1;
            } else {
                return 1;
            }
        }
        if (j == j2) {
            return 0;
        }
        if (j >= j2) {
            return 1;
        }
        return -1;
    }

    public String toString() {
        if (this.c.length == 0) {
            return "ByteString[size=0]";
        }
        if (this.c.length <= 16) {
            return String.format("ByteString[size=%s data=%s]", new Object[]{Integer.valueOf(this.c.length), g()});
        }
        return String.format("ByteString[size=%s md5=%s]", new Object[]{Integer.valueOf(this.c.length), c().g()});
    }

    private void a(ObjectInputStream objectInputStream) throws IOException {
        f a = a((InputStream) objectInputStream, objectInputStream.readInt());
        try {
            Field declaredField = f.class.getDeclaredField("c");
            declaredField.setAccessible(true);
            declaredField.set(this, a.c);
        } catch (NoSuchFieldException e) {
            throw new AssertionError();
        } catch (IllegalAccessException e2) {
            throw new AssertionError();
        }
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.c.length);
        objectOutputStream.write(this.c);
    }
}
