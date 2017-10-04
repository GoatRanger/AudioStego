package dji.thirdparty.g.b.a.b;

import dji.thirdparty.g.b.a.c;
import dji.thirdparty.g.e;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class b extends a implements Comparable {
    public final byte[] k;
    public final int l;
    public final int m;

    public b(int i, byte[] bArr) throws e, IOException {
        this(i, bArr.length, new ByteArrayInputStream(bArr));
    }

    public b(int i, int i2, InputStream inputStream) throws e, IOException {
        super(i, i2, inputStream);
        if (a(this.n, c.fx_)) {
            InputStream byteArrayInputStream = new ByteArrayInputStream(this.n);
            a(byteArrayInputStream, c.fx_, "Not a Valid App2 Segment: missing ICC Profile label");
            this.l = a("cur_marker", byteArrayInputStream, "Not a valid App2 Marker");
            this.m = a("num_markers", byteArrayInputStream, "Not a valid App2 Marker");
            this.k = a("App2 Data", (i2 - c.fx_.length) - 2, byteArrayInputStream, "Invalid App2 Segment: insufficient data");
            return;
        }
        this.l = -1;
        this.m = -1;
        this.k = null;
    }

    public int compareTo(Object obj) {
        return this.l - ((b) obj).l;
    }
}
