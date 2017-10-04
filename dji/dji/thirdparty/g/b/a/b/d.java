package dji.thirdparty.g.b.a.b;

import dji.thirdparty.g.b.a.a;
import dji.thirdparty.g.e;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class d extends g implements a {
    public final int K;
    public final int L;
    public final int M;
    public final int N;
    public final int O;
    public final int P;
    public final int Q;
    public final int R;

    public String a() {
        return "JFIF (" + b() + ")";
    }

    public d(int i, byte[] bArr) throws e, IOException {
        this(i, bArr.length, new ByteArrayInputStream(bArr));
    }

    public d(int i, int i2, InputStream inputStream) throws e, IOException {
        super(i, i2);
        byte[] a = a(inputStream, fp_.length);
        if (b(a, fp_) || b(a, fq_)) {
            this.K = a("JFIF_major_version", inputStream, "Not a Valid JPEG File");
            this.L = a("JFIF_minor_version", inputStream, "Not a Valid JPEG File");
            this.M = a("density_units", inputStream, "Not a Valid JPEG File");
            this.N = d("x_density", inputStream, "Not a Valid JPEG File");
            this.O = d("y_density", inputStream, "Not a Valid JPEG File");
            this.P = a("x_thumbnail", inputStream, "Not a Valid JPEG File");
            this.Q = a("y_thumbnail", inputStream, "Not a Valid JPEG File");
            this.R = this.P * this.Q;
            if (this.R > 0) {
                a(inputStream, this.R, "Not a Valid JPEG File: missing thumbnail");
            }
            if (f()) {
                System.out.println("");
                return;
            }
            return;
        }
        throw new e("Not a Valid JPEG File: missing JFIF string");
    }
}
