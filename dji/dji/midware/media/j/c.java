package dji.midware.media.j;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Log;
import com.tencent.android.tpush.common.MessageKey;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.media.DJIVideoDecoder;
import dji.midware.media.d;
import dji.midware.media.e;
import dji.midware.media.e.f;
import dji.midware.media.e.g;
import java.io.File;
import java.util.Date;

public abstract class c implements f {
    protected Object a = new Object();
    protected b b = b.STANDBY;
    protected f c = null;
    protected g d = null;
    protected String e = "";
    protected int f;
    private long g;
    private int h;
    private long i;
    private int j;

    public static class a {
        private final String a;

        public a(String str) {
            this.a = str;
        }

        public String a() {
            return this.a;
        }
    }

    protected enum b {
        STANDBY,
        RECORDING
    }

    private class c extends Thread {
        final /* synthetic */ c a;
        private dji.midware.media.j.g.b b = null;

        public c(c cVar, dji.midware.media.j.g.b bVar) {
            this.a = cVar;
            this.b = bVar;
        }

        public void run() {
            e.c(this.a.a(), this.b.toString());
            Log.i(this.a.a(), "an event is received from the bus: " + this.b.toString());
            synchronized (this.a.a) {
                e.a("Status=" + this.a.b + " event=" + this.b);
                switch (this.a.b) {
                    case STANDBY:
                        if (this.b == dji.midware.media.j.g.b.START_RECORD) {
                            if (!g.c()) {
                                dji.thirdparty.a.c.a().e(g.e.NO_SPACE);
                                break;
                            }
                            this.a.b();
                            this.a.a(b.RECORDING);
                            new Thread(this) {
                                final /* synthetic */ c a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    try {
                                        Thread.sleep((long) g.c);
                                        while (g.f() != null) {
                                            if (g.c()) {
                                                try {
                                                    Thread.sleep((long) g.c);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                    return;
                                                }
                                            }
                                            e.a("Stop recording due to limit of storage space");
                                            new c(this.a.a, dji.midware.media.j.g.b.END_RECORD).start();
                                            dji.thirdparty.a.c.a().e(g.e.NO_SPACE);
                                            return;
                                        }
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }.start();
                            break;
                        }
                        e.c(this.a.a(), "error in state transition: state=" + this.a.b + " action=" + this.b);
                        break;
                    case RECORDING:
                        if (this.b != dji.midware.media.j.g.b.END_RECORD) {
                            if (this.b != dji.midware.media.j.g.b.START_RECORD) {
                                e.c(this.a.a(), "error in state transition: state=" + this.a.b + " action=" + this.b);
                                break;
                            }
                            this.a.c();
                            this.a.b();
                            this.a.a(b.RECORDING);
                            break;
                        }
                        this.a.c();
                        this.a.a(b.STANDBY);
                        break;
                    default:
                        e.c(this.a.a(), "error in state transition: state=" + this.a.b + " action=" + this.b);
                        break;
                }
            }
            e.c(this.a.a(), "NEW state=" + this.a.d());
        }
    }

    protected abstract String a();

    protected abstract void b();

    protected abstract void c();

    public synchronized void onEventBackgroundThread(dji.midware.media.j.g.b bVar) {
        Log.d("recode", MessageKey.MSG_ACCEPT_TIME_START);
        new c(this, bVar).start();
    }

    protected b d() {
        return this.b;
    }

    protected void a(b bVar) {
        this.b = bVar;
    }

    protected void e() {
        new Thread(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                synchronized (this.a.a) {
                    e.a("Status=" + this.a.b + " event=ON_DESTROY");
                    if (this.a.b == b.RECORDING) {
                        try {
                            this.a.c();
                        } catch (Exception e) {
                            e.a(e);
                        }
                        this.a.a(b.STANDBY);
                    }
                }
            }
        }.start();
        dji.thirdparty.a.c.a().d(this);
    }

    protected void f() {
        e.d(a(), "going to create VideoRecordInfoSetter");
        this.c = new f();
        f fVar = this.c;
        Date date = new Date();
        ServiceManager.getInstance().e();
        int i = DJIVideoDecoder.width;
        ServiceManager.getInstance().e();
        this.d = new g(fVar, date, i, DJIVideoDecoder.height, this.e, dji.midware.media.e.e.a() + this.e + ".mp4");
        e.d(a(), "video record info setter is created");
    }

    protected void g() {
        e.d(a(), "video record info setter is closed");
        if (this.d != null) {
            this.d.a((int) (((double) this.f) * d.d()));
            this.d.b();
            this.d.a();
            this.d = null;
        }
        this.c = null;
    }

    protected void h() {
        this.e = d.b();
    }

    protected void i() {
        long currentTimeMillis = System.currentTimeMillis();
        this.i = currentTimeMillis;
        this.g = currentTimeMillis;
        this.j = DataCameraGetPushStateInfo.getInstance().getVideoRecordTime() * 1000;
        this.h = 0;
    }

    protected void j() {
        long currentTimeMillis = System.currentTimeMillis();
        if (((double) (currentTimeMillis - this.g)) > d.d() * ((double) ((this.f - this.h) + d.c()))) {
            this.d.a((int) (((double) this.f) * d.d()), ((int) (currentTimeMillis - this.i)) + this.j);
            this.g = currentTimeMillis;
            this.h = this.f;
        }
    }

    protected String k() {
        return this.e;
    }

    protected void a(String str) {
        try {
            Uri fromFile = Uri.fromFile(new File(str));
            ServiceManager.getInstance();
            Context context = ServiceManager.getContext();
            if (VERSION.SDK_INT >= 19) {
                Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                intent.setData(fromFile);
                context.sendBroadcast(intent);
            } else {
                context.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", fromFile));
            }
            dji.thirdparty.a.c.a().e(new a(str));
        } catch (Exception e) {
            e.a(a(), e);
        }
    }

    public String l() {
        String str;
        synchronized (this.a) {
            if (b.RECORDING == this.b) {
                str = this.e;
            } else {
                str = null;
            }
        }
        return str;
    }
}
