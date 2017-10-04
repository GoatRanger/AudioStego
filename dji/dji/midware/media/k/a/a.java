package dji.midware.media.k.a;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.util.Log;
import dji.midware.media.d;
import dji.midware.media.e;
import dji.midware.media.g.b;
import java.io.IOException;
import java.nio.ByteBuffer;

public class a {
    a a = null;
    int b = 0;
    int c = 0;
    int d;
    int e;
    int f;
    BufferInfo g = new BufferInfo();
    int h = 0;
    byte[] i;
    byte[] j;
    private MediaCodec k = null;
    private b l = null;
    private String m = "EncoderMuxer";
    private boolean n = true;
    private String o;
    private int p;
    private int q;
    private int r;
    private dji.midware.media.b.a s = null;

    protected class a extends Thread {
        final /* synthetic */ a a;

        protected a(a aVar) {
            this.a = aVar;
        }

        public void run() {
            while (this.a.n) {
                try {
                    this.a.d();
                } catch (Exception e) {
                    e.a(e);
                    return;
                }
            }
            e.a("", "Encoder monitor thread ends");
        }
    }

    public void a(ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        int i = 0;
        if (this.c == 0) {
            try {
                int dequeueInputBuffer = this.k.dequeueInputBuffer(-1);
                ByteBuffer byteBuffer2 = this.k.getInputBuffers()[dequeueInputBuffer];
                byteBuffer2.clear();
                this.d = this.f * (1000000 / d.c());
                if (this.f % 15 == 0) {
                    i = 1;
                }
                this.e = i;
                this.f++;
                if (this.s != null) {
                    if (this.i == null) {
                        this.i = new byte[bufferInfo.size];
                    }
                    if (this.j == null) {
                        this.j = new byte[(byteBuffer2.limit() - byteBuffer2.position())];
                    }
                    byteBuffer.get(this.i, 0, this.i.length);
                    this.s.a(this.i, this.j, this.p, this.q);
                    byteBuffer2.put(ByteBuffer.wrap(this.j, 0, this.j.length));
                    this.k.queueInputBuffer(dequeueInputBuffer, 0, this.j.length, (long) this.d, this.e);
                    return;
                }
                byteBuffer.position(bufferInfo.offset);
                byteBuffer.limit(bufferInfo.offset + bufferInfo.size);
                if (bufferInfo.size > byteBuffer2.limit() - byteBuffer2.position()) {
                    Log.i(this.m, "when moving from decoder to encoder: buffer overflow");
                    if (this.i == null) {
                        this.i = new byte[(byteBuffer2.limit() - byteBuffer2.position())];
                    }
                    byteBuffer.get(this.i, 0, this.i.length);
                    byteBuffer2.put(this.i);
                } else {
                    byteBuffer2.put(byteBuffer);
                }
                this.k.queueInputBuffer(dequeueInputBuffer, 0, bufferInfo.size, (long) this.d, this.e);
                return;
            } catch (Exception e) {
                e.a(e);
                return;
            }
        }
        this.c--;
        Log.i(this.m, "encoder get a frame for the upstream, but will jump (remaining to be jumped=" + this.c + ")");
    }

    public void a(MediaFormat mediaFormat) throws Exception {
        Log.i(this.m, "decoder's output color format is: " + mediaFormat.getInteger("color-format"));
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(d.c[0], mediaFormat.getInteger("width"), mediaFormat.getInteger("height"));
        createVideoFormat.setInteger("bitrate", d.n);
        createVideoFormat.setInteger("frame-rate", d.c());
        createVideoFormat.setInteger("i-frame-interval", 1);
        createVideoFormat.setInteger("color-format", this.r);
        Log.i(this.m, "set color of encoder as " + this.r);
        e.a("set color of encoder as " + this.r);
        Log.i(this.m, "decoder's output format: width=" + mediaFormat.getInteger("width") + " height=" + mediaFormat.getInteger("height"));
        try {
            this.k.configure(createVideoFormat, null, null, 1);
            this.k.start();
            this.a = new a(this);
            this.a.start();
        } catch (Exception e) {
            e.a(e);
            this.k.release();
            this.k = null;
            throw e;
        }
    }

    private void c() throws IOException {
        try {
            this.l = dji.midware.media.g.e.a(dji.midware.media.g.e.a.FFMPEG);
            this.l.a(this.o);
            e.a("successfully created muxer");
        } catch (Exception e) {
            e.a(e);
            throw e;
        }
    }

    private void d() throws IOException {
        int dequeueOutputBuffer = this.k.dequeueOutputBuffer(this.g, 50);
        if (dequeueOutputBuffer < 0 && dequeueOutputBuffer != -1) {
            e.a("encoder outputs bufferIndex=" + dequeueOutputBuffer + ", -2 means INFO_OUTPUT_FORMAT_CHANGED");
        }
        if (dequeueOutputBuffer == -2 && this.b == 0) {
            c();
            this.b++;
            this.l.a(this.k.getOutputFormat());
            this.l.c();
        }
        if (dequeueOutputBuffer >= 0) {
            this.h++;
            if (d.g() && (this.h == 1 || this.h % 30 == 0)) {
                e.a("Muxer has received " + this.h + " frames");
            }
            if ((this.g.flags & 4) == 4) {
                this.n = false;
                e.a("muxer received flag of END_OF_STREAM");
            }
            this.l.a(0, this.k.getOutputBuffers()[dequeueOutputBuffer], this.g, d.e());
            this.k.releaseOutputBuffer(dequeueOutputBuffer, false);
        }
    }

    public void a(String str) {
        this.o = str;
    }

    public void a(MediaCodec mediaCodec, int i, int i2, int i3) {
        this.k = mediaCodec;
        this.r = i;
        this.p = i2;
        this.q = i3;
        this.f = 0;
        Log.i(this.m, "complete execution of start()");
    }

    public void a(int i) {
        this.c = i;
    }

    public boolean a() {
        this.n = false;
        try {
            this.a.join();
            return true;
        } catch (Exception e) {
            e.a(e);
            return false;
        }
    }

    public void b() {
        if (this.k != null) {
            this.k.stop();
            this.k.release();
        }
        if (this.l != null) {
            this.l.d();
            this.l.b();
        }
    }

    public void a(dji.midware.media.b.a aVar) {
        this.s = aVar;
    }
}
