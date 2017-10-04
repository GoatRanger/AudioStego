package dji.midware.media.g;

import android.annotation.TargetApi;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import java.io.IOException;
import java.nio.ByteBuffer;

@TargetApi(18)
public class a implements b {
    private MediaMuxer a;
    private int b = 0;

    public int a(MediaFormat mediaFormat) {
        this.b++;
        return this.a.addTrack(mediaFormat);
    }

    public int a() {
        return this.b;
    }

    public void b() {
        this.a.release();
    }

    public void c() {
        this.a.start();
    }

    public void d() {
        this.a.stop();
    }

    public void a(int i, ByteBuffer byteBuffer, BufferInfo bufferInfo, long j) {
        this.a.writeSampleData(i, byteBuffer, bufferInfo);
    }

    public void a(String str) throws IOException {
        this.a = new MediaMuxer(str, 0);
    }
}
