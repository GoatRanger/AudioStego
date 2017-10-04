package dji.midware.media.k.a;

import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.util.Log;
import dji.midware.media.e;
import dji.midware.media.j.b;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class d implements b {
    private static String k = "OfflineDecoder";
    public MediaCodec a;
    int b;
    int c;
    int d;
    int e;
    int f;
    MediaFormat g;
    a h = null;
    boolean i = false;
    private a j;
    private boolean l = true;
    private BufferInfo m = new BufferInfo();
    private ByteBuffer n;
    private int o;

    protected class a extends Thread {
        final /* synthetic */ d a;

        protected a(d dVar) {
            this.a = dVar;
        }

        public void run() {
            try {
                Log.i(d.k, "thread DecoderOutputMonitor has started");
                while (this.a.l) {
                    this.a.e();
                }
            } catch (Exception e) {
                e.a(e);
            }
            e.a("", "Decoder monitor thread ends");
        }
    }

    public void a(a aVar) {
        this.j = aVar;
    }

    public void a(MediaCodec mediaCodec, int i, int i2, int i3) {
        Log.i(k, "Starting");
        this.a = mediaCodec;
        this.o = i;
        this.e = i2;
        this.f = i3;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.l = true;
        d();
        this.h = new a(this);
        this.h.start();
        Log.i(k, "Has started");
    }

    private void d() {
        this.g = MediaFormat.createVideoFormat(dji.midware.media.d.c[0], this.e, this.f);
        Log.i(k, "set color of decoder as " + this.o);
        e.a("set color of decoder as " + this.o);
        this.g.setInteger("color-format", this.o);
        if (j.b != null) {
            this.g.setByteBuffer("csd-0", ByteBuffer.wrap(j.b));
            Log.i(k, "set csd-0 for decoder as " + Arrays.toString(j.b));
        }
        if (j.c != null) {
            this.g.setByteBuffer("csd-1", ByteBuffer.wrap(j.c));
            Log.i(k, "set csd-1 for decoder as " + Arrays.toString(j.c));
        }
        this.a.configure(this.g, null, null, 0);
        this.a.start();
    }

    public boolean a() {
        this.l = false;
        try {
            this.h.join();
            return true;
        } catch (Exception e) {
            e.a(k, e);
            return false;
        }
    }

    public void b() {
        if (this.a != null) {
            synchronized (this.a) {
                this.a.stop();
                this.a.release();
            }
        }
    }

    private void a(byte[] bArr, int i) {
        int i2 = 0;
        synchronized (this.a) {
            try {
                if (dji.midware.media.d.g()) {
                    Log.i(k, "get a frame from upstream and will place it in the input");
                }
                this.b = this.d * (1000000 / dji.midware.media.d.c());
                if (this.d == 0) {
                    i2 = 1;
                }
                this.c = i2;
                this.d++;
                try {
                    int dequeueInputBuffer = this.a.dequeueInputBuffer(-1);
                    ByteBuffer byteBuffer = this.a.getInputBuffers()[dequeueInputBuffer];
                    byteBuffer.clear();
                    byteBuffer.put(bArr);
                    this.a.queueInputBuffer(dequeueInputBuffer, 0, i, (long) this.b, this.c);
                    if (dji.midware.media.d.g()) {
                        Log.i(k, "has place a frame into the input");
                    }
                } catch (Exception e) {
                    e.a(k, "Input thread reports:" + e.toString() + ". going to reset the decoder");
                    this.a.stop();
                    this.a.configure(this.g, null, null, 0);
                    this.a.start();
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace(new PrintWriter(new StringWriter()));
                Log.e(k, e2.toString());
            }
        }
    }

    private void e() throws Exception {
        try {
            int dequeueOutputBuffer = this.a.dequeueOutputBuffer(this.m, 50);
            if (!this.i && dequeueOutputBuffer == -2) {
                this.i = true;
                this.j.a(this.a.getOutputFormat());
            }
            if (dequeueOutputBuffer >= 0) {
                if (dji.midware.media.d.g()) {
                    Log.i(k, "DecoderOutputMonitor get a frame from the output buffer and will pass it to downstream");
                }
                this.n = this.a.getOutputBuffers()[dequeueOutputBuffer];
                this.j.a(this.n, this.m);
                this.a.releaseOutputBuffer(dequeueOutputBuffer, false);
            }
        } catch (Exception e) {
            Log.i(k, "output monitor exception when calling dequeue");
        }
    }

    public void a(byte[] bArr, int i, long j, boolean z) {
        a(bArr, i);
    }
}
