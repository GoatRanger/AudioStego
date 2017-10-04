package dji.thirdparty.g.a;

import dji.thirdparty.g.a.b.d;
import dji.thirdparty.g.a.b.e;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class h {
    public byte[] a(byte[] bArr, int i, int i2, int i3) throws IOException {
        return new e(i, i3).a(new ByteArrayInputStream(bArr), i2);
    }

    public byte[] a(byte[] bArr, int i, int i2) throws dji.thirdparty.g.e, IOException {
        return new l().a(bArr, i);
    }

    public byte[] a(byte[] bArr, int i, int i2, boolean z) throws IOException {
        return new d(i, i2, z).a(bArr);
    }
}
