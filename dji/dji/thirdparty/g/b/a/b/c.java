package dji.thirdparty.g.b.a.b;

import dji.thirdparty.g.e;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public abstract class c extends g {
    public final byte[] n;

    public c(int i, int i2, InputStream inputStream) throws e, IOException {
        super(i, i2);
        this.n = a("Segment Data", i2, inputStream, "Invalid Segment: insufficient data");
    }

    public c(int i, byte[] bArr) throws e, IOException {
        super(i, bArr.length);
        this.n = bArr;
    }

    public void a(PrintWriter printWriter) {
        a(printWriter, 0);
    }

    public void a(PrintWriter printWriter, int i) {
        int i2 = 0;
        while (i2 < 50 && i2 + i < this.n.length) {
            a(printWriter, "\t" + (i2 + i), (int) this.n[i2 + i]);
            i2++;
        }
    }
}
