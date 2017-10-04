package dji.midware.media.k.a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Log;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.media.e;
import java.io.File;

public abstract class f implements h {
    protected static boolean c = true;
    protected String d = "";
    protected String e = "";
    protected dji.midware.media.e.f f = null;
    protected Object g = new Object();
    protected i h = null;
    protected a i = a.STANDBY;
    protected Object j = new Object();
    protected int k;

    protected enum a {
        STANDBY,
        TRANSCODING
    }

    protected abstract void f();

    protected abstract void g();

    abstract String h();

    public int d() {
        return this.k;
    }

    public String c() {
        return this.d;
    }

    protected void i() {
        this.f.o(120);
        this.f.f(this.d.replace(".h264", ".info"));
    }

    protected void j() {
        try {
            Uri fromFile = Uri.fromFile(new File(this.e));
            ServiceManager.getInstance();
            Context context = ServiceManager.getContext();
            if (VERSION.SDK_INT >= 19) {
                Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                intent.setData(fromFile);
                context.sendBroadcast(intent);
            } else {
                context.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", fromFile));
            }
        } catch (Exception e) {
            e.a(h(), e);
        }
        e.a("have add " + this.e + " into the library");
    }

    protected void a(String str, String str2) {
        this.d = str;
        this.e = str2;
        this.f = new dji.midware.media.e.f();
        try {
            this.f.g(str.replaceAll(".h264", ".info"));
        } catch (Exception e) {
            e.a(e);
            synchronized (this.g) {
                if (this.h != null) {
                    this.h.a(e);
                }
            }
        }
    }

    protected void k() {
        this.f = null;
    }

    protected void l() {
        if (!c) {
            return;
        }
        if (Boolean.valueOf(new File(this.d).delete()).booleanValue()) {
            Log.i(h(), "H264 File has been deleted");
        } else {
            Log.e(h(), "H264 File not deleted");
        }
    }

    public void a(String str, String str2, i iVar) {
        synchronized (this.j) {
            if (this.i == a.STANDBY) {
                e.a(h(), "status=" + this.i + " event=START");
                this.i = a.TRANSCODING;
                a(str, str2);
                synchronized (this.g) {
                    this.h = iVar;
                }
                g();
            }
        }
    }

    public void a() {
        synchronized (this.j) {
            if (this.i == a.TRANSCODING) {
                e.a(h(), "status=" + this.i + " event=STOP");
                f();
                this.i = a.STANDBY;
            }
        }
        synchronized (this.g) {
            if (this.h == null) {
                e.a("Transcoder is stopped when UI is inactive");
            } else {
                e.a("Transcoder is stopped when UI is active");
            }
        }
    }

    public void b() {
        e.a(h(), "event=DESTROY");
        a();
    }

    public void a(i iVar) {
        synchronized (this.g) {
            this.h = iVar;
        }
    }

    public boolean e() {
        return this.i == a.TRANSCODING;
    }

    protected void m() {
        if (this.d != null) {
            new File(this.d.replace(".h264", dji.pilot2.main.a.a.n)).delete();
        }
    }
}
