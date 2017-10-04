package dji.thirdparty.g.a;

import android.support.v4.media.TransportMediator;
import java.io.IOException;
import java.io.InputStream;

public class f extends InputStream implements a {
    private final InputStream j;
    private int k;
    private int l = 0;
    private long m = 0;

    public f(InputStream inputStream) {
        this.j = inputStream;
    }

    public int read() throws IOException {
        if (this.l <= 0) {
            return this.j.read();
        }
        throw new IOException("BitInputStream: incomplete bit read");
    }

    public final int a(int i) throws IOException {
        if (i < 8) {
            if (this.l == 0) {
                this.k = this.j.read();
                this.l = 8;
                this.m++;
            }
            if (i > this.l) {
                throw new IOException("BitInputStream: can't read bit fields across bytes");
            }
            this.l -= i;
            int i2 = this.k >> this.l;
            switch (i) {
                case 1:
                    return i2 & 1;
                case 2:
                    return i2 & 3;
                case 3:
                    return i2 & 7;
                case 4:
                    return i2 & 15;
                case 5:
                    return i2 & 31;
                case 6:
                    return i2 & 63;
                case 7:
                    return i2 & TransportMediator.KEYCODE_MEDIA_PAUSE;
            }
        }
        if (this.l > 0) {
            throw new IOException("BitInputStream: incomplete bit read");
        } else if (i == 8) {
            this.m++;
            return this.j.read();
        } else if (i == 16) {
            this.m += 2;
            return (this.j.read() << 8) | (this.j.read() << 0);
        } else if (i == 24) {
            this.m += 3;
            return ((this.j.read() << 16) | (this.j.read() << 8)) | (this.j.read() << 0);
        } else if (i == 32) {
            this.m += 4;
            return (((this.j.read() << 24) | (this.j.read() << 16)) | (this.j.read() << 8)) | (this.j.read() << 0);
        } else {
            throw new IOException("BitInputStream: unknown error");
        }
    }

    public void a() {
        this.l = 0;
    }

    public long b() {
        return this.m;
    }
}
