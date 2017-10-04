package dji.thirdparty.g.a;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class o extends b {
    public final byte[] a(byte[] bArr) throws IOException {
        return b(new InflaterInputStream(new ByteArrayInputStream(bArr)));
    }

    public final byte[] b(byte[] bArr) throws IOException {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream);
        deflaterOutputStream.write(bArr);
        deflaterOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
