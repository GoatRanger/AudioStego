package dji.thirdparty.g.c;

import java.io.IOException;
import java.io.InputStream;

public class d extends InputStream {
    private final InputStream a;
    private long b = 0;

    public d(InputStream inputStream) {
        this.a = inputStream;
    }

    public int read() throws IOException {
        int read = this.a.read();
        this.b++;
        return read;
    }

    public long skip(long j) throws IOException {
        long skip = this.a.skip(j);
        this.b += j;
        return skip;
    }

    public int available() throws IOException {
        return this.a.available();
    }

    public void close() throws IOException {
        this.a.close();
    }

    public long a() {
        return this.b;
    }
}
