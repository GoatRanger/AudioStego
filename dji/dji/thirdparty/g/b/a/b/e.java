package dji.thirdparty.g.b.a.b;

import dji.thirdparty.g.b.a.a;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class e extends g {
    public final int k;
    public final int l;
    public final int m;
    public final int n;

    public e(int i, byte[] bArr) throws dji.thirdparty.g.e, IOException {
        this(i, bArr.length, new ByteArrayInputStream(bArr));
    }

    public e(int i, int i2, InputStream inputStream) throws dji.thirdparty.g.e, IOException {
        super(i, i2);
        if (f()) {
            System.out.println("SOF0Segment marker_length: " + i2);
        }
        this.n = a("Data_precision", inputStream, "Not a Valid JPEG File");
        this.l = d("Image_height", inputStream, "Not a Valid JPEG File");
        this.k = d("Image_Width", inputStream, "Not a Valid JPEG File");
        this.m = a("Number_of_components", inputStream, "Not a Valid JPEG File");
        a(inputStream, i2 - 6, "Not a Valid JPEG File: SOF0 Segment");
        if (f()) {
            System.out.println("");
        }
    }

    public String a() {
        return "SOFN (SOF" + (this.gt_ - a.q) + ") (" + b() + ")";
    }
}
