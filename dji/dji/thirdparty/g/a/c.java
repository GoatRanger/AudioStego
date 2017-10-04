package dji.thirdparty.g.a;

import dji.thirdparty.g.e;
import java.io.IOException;
import java.io.InputStream;

public class c extends b {
    private int k = 77;

    public c(int i) {
        this.k = i;
    }

    protected void c(int i, int i2) throws e, IOException {
        if (i != i2) {
            throw new e("Byte Order bytes don't match (" + i + ", " + i2 + ").");
        } else if (i == 77) {
            this.k = i;
        } else if (i == 73) {
            this.k = i;
        } else {
            throw new e("Unknown Byte Order hint: " + i);
        }
    }

    protected void a(int i) {
        this.k = i;
    }

    protected int g() {
        return this.k;
    }

    protected final int a(String str, int i, byte[] bArr) {
        return b(str, bArr, i, this.k);
    }

    protected final int b(String str, byte[] bArr) {
        return b(str, bArr, this.k);
    }

    public final int c(String str, byte[] bArr) throws e {
        return c(str, bArr, this.k);
    }

    public final int b(String str, int i, byte[] bArr) throws e {
        return a(str, i, bArr, this.k);
    }

    public final int b(String str, InputStream inputStream, String str2) throws e, IOException {
        return a(str, inputStream, str2, this.k);
    }

    public final int c(String str, InputStream inputStream, String str2) throws e, IOException {
        return b(str, inputStream, str2, this.k);
    }

    public final int d(String str, InputStream inputStream, String str2) throws e, IOException {
        return c(str, inputStream, str2, this.k);
    }

    public static boolean d(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length < bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    protected final byte[] b(int i) {
        return b.a(i, this.k);
    }
}
