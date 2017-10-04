package dji.thirdparty.g.c;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class b extends OutputStream {
    private final OutputStream a;
    private final ByteArrayOutputStream b = new ByteArrayOutputStream();

    public b(OutputStream outputStream) {
        this.a = outputStream;
    }

    public void write(int i) throws IOException {
        this.a.write(i);
        this.b.write(i);
    }

    public byte[] a() {
        return this.b.toByteArray();
    }

    public void close() throws IOException {
        this.a.close();
    }

    public void flush() throws IOException {
        this.a.flush();
    }
}
