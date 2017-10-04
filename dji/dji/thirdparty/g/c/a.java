package dji.thirdparty.g.c;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class a extends InputStream {
    private final InputStream a;
    private final ByteArrayOutputStream b = new ByteArrayOutputStream();

    public a(InputStream inputStream) {
        this.a = inputStream;
    }

    public byte[] a() {
        return this.b.toByteArray();
    }

    public int read() throws IOException {
        int read = this.a.read();
        this.b.write(read);
        return read;
    }

    public int available() throws IOException {
        return this.a.available();
    }

    public void close() throws IOException {
        this.a.close();
    }
}
