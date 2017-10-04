package dji.midware.media.j;

import android.annotation.TargetApi;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.util.Log;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataDm368SetParams;
import dji.midware.data.model.P3.DataDm368SetParams.DM368CmdId;
import dji.midware.media.DJIVideoDataRecver;
import dji.midware.media.e;
import dji.thirdparty.a.c;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

@TargetApi(18)
public class d extends c implements b, f {
    private static d h = null;
    private static String i = "RecorderGop";
    boolean g = true;
    private long j;
    private long k;
    private Object l = new Object();
    private boolean m;
    private MediaMuxer n = null;
    private BufferInfo o = new BufferInfo();
    private BufferedOutputStream p = null;
    private OutputStream q = null;
    private boolean r = false;
    private int s;

    public static synchronized d getInstance() {
        d dVar;
        synchronized (d.class) {
            if (h == null) {
                h = new d();
                c.a().a(h);
            }
            dVar = h;
        }
        return dVar;
    }

    public static synchronized void m() {
        synchronized (d.class) {
            e.a("RecorderGop is destroyed");
            if (h != null) {
                h.e();
                h = null;
            }
        }
    }

    private void p() {
        try {
            this.n = new MediaMuxer(dji.midware.media.e.e.a() + this.e + ".mp4", 0);
            e.a("successfully created muxer");
            if (this.r) {
                this.q = new FileOutputStream(dji.midware.media.e.e.a() + this.e + ".h264");
                if (this.q != null) {
                    this.p = new BufferedOutputStream(this.q);
                    Log.i(i, "An H264 File has been opened");
                    return;
                }
                Log.e(i, "error in creating H264 File");
            }
        } catch (Exception e) {
            e.a(e);
        }
    }

    private boolean a(byte[] bArr) {
        if (bArr.length < 11) {
            return false;
        }
        int i = 10;
        while ((bArr[i] & 31) == 9) {
            i += 6;
        }
        return (bArr[i] & 31) == 7;
    }

    public void a(byte[] bArr, int i, long j, boolean z) {
        try {
            if (this.b == b.RECORDING) {
                boolean a = a(bArr);
                if (this.g) {
                    if (a) {
                        this.c.o(this.s);
                        this.d.b();
                        this.g = false;
                        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(dji.midware.media.d.c[0], 1280, 720);
                        createVideoFormat.setInteger("frame-rate", 30);
                        createVideoFormat.setByteBuffer("csd-0", ByteBuffer.wrap(bArr, 6, 38));
                        createVideoFormat.setByteBuffer("csd-1", ByteBuffer.wrap(bArr, 44, 8));
                        this.n.addTrack(createVideoFormat);
                        this.n.start();
                    } else {
                        this.s++;
                        return;
                    }
                }
                try {
                    j();
                    if (this.r && this.p != null) {
                        this.p.write(bArr, 0, i);
                        if (this.f > 0 && this.f % 15 == 0) {
                            this.p.flush();
                        }
                    }
                    this.o.offset = 0;
                    this.o.size = i;
                    this.o.presentationTimeUs = (long) ((int) ((((double) (this.f + 1)) * dji.midware.media.d.d()) * 1000.0d));
                    this.o.flags = 0;
                    if (a) {
                        BufferInfo bufferInfo = this.o;
                        bufferInfo.flags |= 2;
                        bufferInfo = this.o;
                        bufferInfo.flags |= 1;
                    }
                    this.n.writeSampleData(0, ByteBuffer.wrap(bArr, 0, i), this.o);
                    this.f++;
                } catch (Exception e) {
                    Log.e(i, "error when writing H264 frames to File");
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e.a(e2);
        }
    }

    public void n() {
        DJIVideoDataRecver.getInstance().setH264FrameListener(true, this);
    }

    public void o() {
        DJIVideoDataRecver.getInstance().setH264FrameListener(true, null);
    }

    private void q() {
        try {
            if (this.n != null) {
                this.n.stop();
                this.n.release();
                this.n = null;
                a(dji.midware.media.e.e.a() + this.e + ".mp4");
            }
            Log.i(i, "muxer has been closed");
        } catch (Exception e) {
            Log.e(i, "error when closing muxer");
            e.printStackTrace();
        }
        if (this.r) {
            try {
                if (this.p != null) {
                    this.p.close();
                    this.p = null;
                }
                if (this.q != null) {
                    this.q.close();
                    this.q = null;
                }
                Log.i(i, "H264 file has been closed");
            } catch (Exception e2) {
                Log.e(i, "error when closing H264 file");
                e2.printStackTrace();
            }
        }
        if (this.f < 30) {
            Log.i(i, "need to delete the related file because it has fewer frames than the threshold");
            if (new File(dji.midware.media.e.e.a() + this.e + ".mp4").delete()) {
                Log.i(i, "has deleted mp4 file");
            } else {
                Log.e(i, "failed to delete the short mp4 file");
            }
            if (this.r) {
                if (new File(dji.midware.media.e.e.a() + this.e + ".h264").delete()) {
                    Log.i(i, "has deleted h264 file");
                } else {
                    Log.e(i, "failed to delete the short h264 file");
                }
            }
            if (new File(dji.midware.media.e.e.a() + this.e + ".info").delete()) {
                Log.i(i, "has deleted the .info file");
            } else {
                Log.e(i, "failed to delete the .info file");
            }
        }
    }

    protected String a() {
        return i;
    }

    protected void b() {
        try {
            r();
            i();
            h();
            p();
            this.f = 0;
            this.s = 0;
            f();
            n();
        } catch (Exception e) {
            e.a(e);
        }
    }

    private void r() {
        DataDm368SetParams dataDm368SetParams = new DataDm368SetParams();
        dataDm368SetParams.a(DM368CmdId.SetEncodeFormat, 1);
        this.k = System.currentTimeMillis();
        this.m = false;
        dataDm368SetParams.start(new dji.midware.e.d(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.m = true;
                this.a.j = System.currentTimeMillis() - this.a.k;
                e.a("Gop Activation success. Delay: " + this.a.j);
                synchronized (this.a.l) {
                    this.a.l.notify();
                }
            }

            public void onFailure(a aVar) {
                this.a.m = false;
                e.a(new Exception(aVar.toString()));
                synchronized (this.a.l) {
                    this.a.l.notify();
                }
            }
        });
        synchronized (this.l) {
            try {
                this.l.wait();
            } catch (InterruptedException e) {
                this.m = false;
                e.printStackTrace();
            }
        }
        this.j = System.currentTimeMillis() - this.k;
    }

    protected void c() {
        try {
            o();
            if (this.d != null) {
                this.d.a((int) (((double) this.f) * dji.midware.media.d.d()));
                this.d.b();
                this.d.a();
                this.d = null;
            }
            this.c = null;
            q();
        } catch (Exception e) {
            e.a(e);
        }
    }
}
