package dji.midware.media.d;

import android.media.MediaFormat;
import dji.midware.media.b;
import dji.midware.media.e;
import dji.midware.util.c;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class a implements d {
    private static boolean a = false;
    private static final String b = "AACDemuxer";
    private BufferedInputStream c = null;
    private byte[] d = new byte[b.a];
    private boolean e = false;
    private int f = 0;
    private int g = 0;

    public boolean a() {
        if (this.e) {
            return false;
        }
        try {
            this.c.read(this.d, 0, 7);
            e.c(a, b, "demuxer head=" + c.i(dji.thirdparty.afinal.c.c.a(this.d, 0, 7)));
            if ((this.d[0] & 255) == 255 && (this.d[1] & 240) == 240) {
                this.g = (((this.d[3] & 3) << 11) | ((this.d[4] & 255) << 3)) | ((this.d[5] & dji.thirdparty.g.b.a.a.fw_) >>> 5);
                e.c(a, b, "demuxer frameSize=" + this.g);
                int read = this.c.read(this.d, 7, this.g - 7);
                if (read == -1) {
                    this.e = true;
                    return false;
                } else if (read != this.g - 7) {
                    throw new RuntimeException("error. expected reading=" + (this.g - 7) + " actual reading=" + read);
                } else {
                    this.f++;
                    return true;
                }
            }
            throw new RuntimeException("error");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int b() {
        return 0;
    }

    public int c() {
        return 0;
    }

    public long d() {
        return 0;
    }

    public MediaFormat a(int i) {
        return null;
    }

    public void a(String str) throws IOException {
        this.c = new BufferedInputStream(new FileInputStream(str));
        if (this.c != null) {
            a();
        }
    }

    public int a(ByteBuffer byteBuffer, int i) {
        byteBuffer.clear();
        byteBuffer.position(i);
        byteBuffer.put(this.d, 0, this.g);
        return this.g;
    }

    public void a(long j, int i) {
    }

    public int e() {
        return 0;
    }

    public void b(int i) {
    }

    public void c(int i) {
    }

    public void f() {
        try {
            this.c.close();
        } catch (Exception e) {
        }
        this.c = null;
    }

    public int g() {
        return 0;
    }

    public int h() {
        return 0;
    }
}
