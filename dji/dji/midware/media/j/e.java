package dji.midware.media.j;

import android.util.Log;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.media.DJIVideoDataRecver;
import dji.midware.media.DJIVideoDecoder;
import dji.pilot2.main.a.a;
import dji.thirdparty.a.c;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class e extends c implements b, f {
    private static String g = "H264Recorder";
    private static e h = null;
    private BufferedOutputStream i = null;
    private OutputStream j = null;
    private OutputStream k = null;
    private BufferedOutputStream l = null;

    public static synchronized e getInstance() {
        e eVar;
        synchronized (e.class) {
            if (h == null) {
                h = new e();
                c.a().a(h);
            }
            eVar = h;
        }
        return eVar;
    }

    public static synchronized void m() {
        synchronized (e.class) {
            dji.midware.media.e.a("RecorderH264 is destroyed");
            if (h != null) {
                h.e();
                h = null;
            }
        }
    }

    private e() {
        Log.i(g, "An instance is created");
    }

    private void p() {
        try {
            this.j = new FileOutputStream(dji.midware.media.e.e.a() + this.e + ".h264");
            if (this.j != null) {
                this.i = new BufferedOutputStream(this.j);
                Log.i(g, "An H264 File has been opened");
            } else {
                Log.e(g, "error in creating H264 File");
            }
            this.k = new FileOutputStream(dji.midware.media.e.e.a() + this.e + ".index");
            if (this.k != null) {
                this.l = new BufferedOutputStream(this.k);
                Log.i(g, "An H264 Index File has been opened");
                return;
            }
            Log.e(g, "error in creating H264 INdex File");
        } catch (IOException e) {
            Log.e(g, "error in creating file " + this.e);
            e.printStackTrace();
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
            if (this.i != null) {
                this.i.close();
                this.i = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (this.j != null) {
                this.j.close();
                this.j = null;
            }
            Log.i(g, "H264 file has been closed");
        } catch (Exception e2) {
            Log.e(g, "error when closing H264 file");
            e2.printStackTrace();
        }
        try {
            if (this.l != null) {
                this.l.close();
                this.l = null;
            }
        } catch (Exception e22) {
            e22.printStackTrace();
        }
        try {
            if (this.k != null) {
                this.k.close();
                this.k = null;
            }
        } catch (Exception e222) {
            e222.printStackTrace();
        }
        if (this.f <= 150) {
            Log.i(g, "need to delete the related file because it has fewer frames than the threshold");
            if (new File(dji.midware.media.e.e.a() + this.e + ".h264").delete()) {
                Log.i(g, "has deleted h264 file");
            } else {
                Log.e(g, "failed to delete the short h264 file");
            }
            if (new File(dji.midware.media.e.e.a() + this.e + a.n).delete()) {
                Log.i(g, "has deleted the thumb file");
            } else {
                Log.e(g, "failed to delete the thumb file");
            }
            if (new File(dji.midware.media.e.e.a() + this.e + ".info").delete()) {
                Log.i(g, "has deleted the .info file");
            } else {
                Log.e(g, "failed to delete the .info file");
            }
            if (new File(dji.midware.media.e.e.a() + this.e + ".index").delete()) {
                Log.i(g, "has deleted the .index file");
            } else {
                Log.e(g, "failed to delete the .index file");
            }
        }
    }

    public void a(byte[] bArr, int i, long j, boolean z) {
        if (this.b == b.RECORDING) {
            try {
                if (this.i != null) {
                    j();
                    this.i.write(bArr, 0, i);
                    this.l.write(new String("size=" + i + " pts=" + j + " system_time=" + System.currentTimeMillis() + "\n").getBytes());
                    this.f++;
                    if (this.f % 15 == 0) {
                        this.i.flush();
                        this.l.flush();
                    }
                }
            } catch (Exception e) {
                Log.e(g, "error when writing H264 frames to File");
                e.printStackTrace();
            }
        }
    }

    private void r() throws IOException {
        ProductType c = i.getInstance().c();
        ServiceManager.getInstance().e();
        int i = DJIVideoDecoder.width;
        ServiceManager.getInstance().e();
        int iframeRawId = DJIVideoDecoder.getIframeRawId(c, i, DJIVideoDecoder.height);
        if (iframeRawId >= 0) {
            ServiceManager.getInstance();
            InputStream openRawResource = ServiceManager.getContext().getResources().openRawResource(iframeRawId);
            i = openRawResource.available();
            byte[] bArr = new byte[i];
            openRawResource.read(bArr);
            if (this.i != null) {
                this.i.write(bArr, 0, i);
            }
            openRawResource.close();
        }
    }

    protected void c() {
        o();
        g();
        q();
    }

    protected void b() {
        i();
        h();
        p();
        this.f = 0;
        f();
        try {
            r();
            this.f++;
        } catch (Exception e) {
            dji.midware.media.e.a(e);
        }
        n();
        c.a().e(new g.c(dji.midware.media.e.e.a() + this.e + a.n));
        Log.i(g, "have posted a bus event for saving a thumb");
    }

    protected String a() {
        return g;
    }
}
