package dji.thirdparty.g.c;

import java.io.IOException;
import java.io.OutputStream;

public class e extends OutputStream {
    private final OutputStream a;
    private long b = 0;

    public e(OutputStream outputStream) {
        this.a = outputStream;
    }

    public void write(int i) throws IOException {
        this.a.write(i);
        this.b++;
    }

    public void write(byte[] bArr) throws IOException {
        this.a.write(bArr);
        this.b += (long) bArr.length;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.a.write(bArr, i, i2);
        this.b += (long) i2;
    }

    public void flush() throws IOException {
        this.a.flush();
    }

    public void close() throws IOException {
        this.a.close();
    }

    public long a() {
        return this.b;
    }
}
