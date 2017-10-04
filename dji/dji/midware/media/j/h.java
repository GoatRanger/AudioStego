package dji.midware.media.j;

import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.os.Build.VERSION;
import android.util.Log;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCameraGetAudio;
import dji.midware.media.d;
import dji.midware.media.e;
import dji.midware.media.g.b;
import dji.midware.media.k.b.e.a;
import dji.thirdparty.a.c;
import java.io.File;
import java.nio.ByteBuffer;

public class h extends c implements f, a {
    public static String g = "RecorderMp4";
    private static h h = null;
    private static final boolean i = false;
    private static MediaRecorder m;
    private boolean j = false;
    private b k = null;
    private long l = -1;
    private ServiceManager n;

    public static synchronized h getInstance() {
        h hVar;
        synchronized (h.class) {
            if (h == null) {
                h = new h();
                c.a().a(h);
            }
            hVar = h;
        }
        return hVar;
    }

    public static synchronized void m() {
        synchronized (h.class) {
            e.a("RecorderMp4 will be destroyed asynchronously");
            if (h != null) {
                h.e();
                h = null;
            }
        }
    }

    private h() {
        Log.i(g, "An instance is created");
    }

    private void n() {
        try {
            e.a(g, "Android Version is: " + VERSION.SDK_INT);
            this.k = dji.midware.media.g.e.a(dji.midware.media.g.e.a.FFMPEG);
            this.k.a(dji.midware.media.e.e.a() + this.e + ".mp4");
            e.a(g, "successfully created muxer");
        } catch (Exception e) {
            e.a(e);
        }
    }

    private void o() {
        try {
            if (this.k != null) {
                BufferInfo bufferInfo = new BufferInfo();
                bufferInfo.set(0, 0, 0, 4);
                this.k.a(0, ByteBuffer.allocate(10), bufferInfo, 1);
                this.k.d();
                this.k.b();
                this.k = null;
            }
            e.a(g, "muxer has been closed");
        } catch (Exception e) {
            e.a(g, "error when closing muxer. possibly because the beginning frames are filtered");
        }
        if (this.f >= 30) {
            String str = dji.midware.media.e.e.a() + this.e + ".mp4";
            if (new File(str).exists()) {
                a(str);
                return;
            }
            return;
        }
        Log.i(dji.midware.media.k.b.e.c, "need to delete the related file because it has fewer frames than the threshold");
        File file = new File(dji.midware.media.e.e.a() + this.e + ".mp4");
        if (file.exists()) {
            if (file.delete()) {
                Log.i(dji.midware.media.k.b.e.c, "has deleted mp4 file");
            } else {
                Log.e(dji.midware.media.k.b.e.c, "failed to delete the short mp4 file");
            }
        }
        file = new File(dji.midware.media.e.e.a() + this.e + ".h264");
        if (file.exists()) {
            if (file.delete()) {
                Log.i(dji.midware.media.k.b.e.c, "has deleted h264 file");
            } else {
                Log.e(dji.midware.media.k.b.e.c, "failed to delete the short h264 file");
            }
        }
        file = new File(dji.midware.media.e.e.a() + this.e + ".info");
        if (!file.exists()) {
            return;
        }
        if (file.delete()) {
            Log.i(dji.midware.media.k.b.e.c, "has deleted the .info file");
        } else {
            Log.e(dji.midware.media.k.b.e.c, "failed to delete the .info file");
        }
    }

    protected String a() {
        return g;
    }

    protected void b() {
        this.n = ServiceManager.getInstance();
        i();
        this.j = false;
        this.f = 0;
        h();
        n();
        f();
        dji.midware.media.k.b.e.getInstance().a((a) this);
        if (DataCameraGetAudio.getInstance().isEnable()) {
            e.a("OSMO: start to record audio locally");
            p();
            return;
        }
        e.a("No local audio recording.");
    }

    private void p() {
        try {
            m = new MediaRecorder();
            if (m != null) {
                m.setOnErrorListener(new OnErrorListener(this) {
                    final /* synthetic */ h a;

                    {
                        this.a = r1;
                    }

                    public void onError(MediaRecorder mediaRecorder, int i, int i2) {
                        e.b(h.g, "MeidaRecorder error: what=" + i + " extra=" + i2);
                        h.m = null;
                    }
                });
                m.setAudioSource(1);
                m.setOutputFormat(2);
                m.setAudioEncoder(3);
                m.setAudioChannels(2);
                m.setAudioSamplingRate(dji.g.b.b.i);
                m.setOutputFile(dji.midware.media.e.e.a() + this.e + ".m4a");
                m.prepare();
                m.start();
            }
        } catch (Exception e) {
            e.a(g, e);
            m = null;
        }
    }

    protected void c() {
        if (m != null) {
            q();
        }
        dji.midware.media.k.b.e.getInstance().b((a) this);
        g();
        o();
        Log.i(g, "onEndRecord() completion");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void q() {
        /*
        r3 = this;
        r2 = 0;
        r0 = m;	 Catch:{ Exception -> 0x0016 }
        if (r0 == 0) goto L_0x000a;
    L_0x0005:
        r0 = m;	 Catch:{ Exception -> 0x0016 }
        r0.stop();	 Catch:{ Exception -> 0x0016 }
    L_0x000a:
        r0 = m;	 Catch:{ Exception -> 0x001d }
        if (r0 == 0) goto L_0x0013;
    L_0x000e:
        r0 = m;	 Catch:{ Exception -> 0x001d }
        r0.release();	 Catch:{ Exception -> 0x001d }
    L_0x0013:
        m = r2;
    L_0x0015:
        return;
    L_0x0016:
        r0 = move-exception;
        r1 = g;
        dji.midware.media.e.a(r1, r0);
        goto L_0x000a;
    L_0x001d:
        r0 = move-exception;
        r1 = g;	 Catch:{ all -> 0x0026 }
        dji.midware.media.e.a(r1, r0);	 Catch:{ all -> 0x0026 }
        m = r2;
        goto L_0x0015;
    L_0x0026:
        r0 = move-exception;
        m = r2;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.j.h.q():void");
    }

    public synchronized void onFrameInput(ByteBuffer byteBuffer, BufferInfo bufferInfo, int i, int i2, int i3) {
        dji.midware.util.a.a.getInstance("RecorderMp4.onFrameInput").a(dji.midware.util.a.a.a, Integer.valueOf(bufferInfo.size));
        dji.midware.util.a.a.getInstance("RecorderMp4.onFrameInput").a("width", Integer.valueOf(i2));
        dji.midware.util.a.a.getInstance("RecorderMp4.onFrameInput").a("height", Integer.valueOf(i3));
        if (!this.j) {
            if (this.n.e() == null) {
                Log.e(g, "failed to init muxer. decoder is null. can't get sps pps");
            } else {
                byte[] bArr = this.n.e().sps_header;
                byte[] bArr2 = this.n.e().pps_header;
                int i4 = this.n.e().outputWidth;
                int i5 = this.n.e().outputHeight;
                if (bArr == null || bArr2 == null || i4 == 0 || i5 == 0) {
                    Log.e(g, "failed to init muxer. sps or pps is null. width or height is 0");
                } else {
                    if (this.c != null) {
                        this.c.o(i);
                        this.d.b();
                    }
                    MediaFormat createVideoFormat = MediaFormat.createVideoFormat(d.c[0], i4, i5);
                    createVideoFormat.setInteger("frame-rate", 30);
                    createVideoFormat.setByteBuffer("csd-0", ByteBuffer.wrap(bArr));
                    createVideoFormat.setByteBuffer("csd-1", ByteBuffer.wrap(bArr2));
                    this.k.a(createVideoFormat);
                    this.k.c();
                    Log.i(g, "muxer has added a track");
                    this.l = bufferInfo.presentationTimeUs;
                    this.j = true;
                }
            }
        }
        bufferInfo.presentationTimeUs -= this.l;
        j();
        this.k.a(0, byteBuffer, bufferInfo, 1);
        this.f++;
        e.c(false, g, String.format("muxer write a frame. num=%d, size=%d, pts=%d, flags=%s (KEY=1 END=4)", new Object[]{Integer.valueOf(this.f), Integer.valueOf(bufferInfo.size), Long.valueOf(bufferInfo.presentationTimeUs), Integer.valueOf(bufferInfo.flags)}));
    }
}
