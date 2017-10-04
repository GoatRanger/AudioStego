package dji.thirdparty.g.a.b;

import java.io.IOException;
import java.io.InputStream;

public class a extends InputStream {
    private final b a;
    private final int b;

    public a(b bVar, int i) {
        this.a = bVar;
        this.b = i;
    }

    public int read() throws IOException {
        return a(8);
    }

    public int a(int i) throws IOException {
        int a = this.a.a(i);
        if (i < this.b) {
            return a << (this.b - i);
        }
        if (i > this.b) {
            return a >> (i - this.b);
        }
        return a;
    }

    public int[] a(int i, int i2) throws IOException {
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = a(i);
        }
        return iArr;
    }
}
