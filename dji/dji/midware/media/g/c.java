package dji.midware.media.g;

import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.util.Log;
import dji.midware.media.e;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class c implements b {
    private static final String a = "EmptyMuxer";
    private static final int b = 15;
    private BufferedOutputStream c = null;
    private OutputStream d = null;
    private int e;

    public void a(String str) throws IOException {
        this.e = 0;
        this.d = new FileOutputStream(str);
        if (this.d != null) {
            this.c = new BufferedOutputStream(this.d);
            Log.i(a, "An H264 File has been opened");
            return;
        }
        Log.e(a, "error in creating H264 File");
    }

    public int a() {
        return 0;
    }

    public int a(MediaFormat mediaFormat) {
        return 0;
    }

    public void b() {
    }

    public void c() {
    }

    public void d() {
        try {
            if (this.c != null) {
                this.c.flush();
                this.c.close();
                this.c = null;
            }
            if (this.d != null) {
                this.d.flush();
                this.d.close();
                this.d = null;
            }
            Log.i(a, "H264 file has been closed");
        } catch (Exception e) {
            Log.e(a, "error when closing H264 file");
            e.printStackTrace();
        }
    }

    public void a(int i, ByteBuffer byteBuffer, BufferInfo bufferInfo, long j) {
        try {
            this.c.write(byteBuffer.array(), bufferInfo.offset, bufferInfo.size);
            this.e++;
            if (this.e % 15 == 0) {
                this.c.flush();
            }
        } catch (Exception e) {
            e.a(a, e);
        }
    }
}
