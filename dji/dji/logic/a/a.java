package dji.logic.a;

import android.content.Context;
import android.os.Handler;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataCommonPushHeart;
import dji.midware.data.model.P3.DataOsdSetLED;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.b;
import dji.midware.util.i;
import dji.midware.util.m;
import dji.thirdparty.a.c;
import java.util.Timer;
import java.util.TimerTask;

public class a {
    public static String c = "mcu_confirmed";
    public static String d = "is_mcu_303";
    private static final String e = "DJIHandheldHelper";
    private static Runnable h = new Runnable() {
        public void run() {
            new DataOsdSetLED().a().b(1, -1, 32, 255).start(new d(this) {
                final /* synthetic */ AnonymousClass2 a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGE(a.e, "set led failed" + aVar, true, true);
                }
            });
        }
    };
    public boolean a;
    public boolean b;
    private Context f;
    private Timer g;

    private static final class a {
        private static final a a = new a();

        private a() {
        }
    }

    public static a getInstance() {
        return a.a;
    }

    private a() {
        this.a = true;
        this.b = false;
    }

    public void a(Context context) {
        this.f = context;
        this.b = i.b(this.f, c, false);
        if (this.b) {
            this.a = i.b(this.f, d, true);
        }
        if (!c.a().c(this)) {
            c.a().a(this);
        }
    }

    public void a() {
        if (this.g == null) {
            this.g = new Timer();
        }
        final DataCommonPushHeart a = new DataCommonPushHeart().a(DeviceType.OFDM);
        this.g.schedule(new TimerTask(this) {
            final /* synthetic */ a b;

            public void run() {
                a.start(null);
            }
        }, 0, 1000);
    }

    public void b() {
        if (this.g != null) {
            this.g.cancel();
            this.g = null;
        }
    }

    public void c() {
        new Handler(b.b()).postDelayed(h, 2000);
    }

    public void onEventBackgroundThread(p pVar) {
        if (pVar == p.b && dji.midware.b.c.getInstance().isConnected()) {
            d();
        }
    }

    private void d() {
        final e dataCommonGetVersion = new DataCommonGetVersion();
        dataCommonGetVersion.setDeviceType(DeviceType.find(9));
        new m(dataCommonGetVersion, 10, new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                if (dataCommonGetVersion.getLoaderByte(2) == 1) {
                    this.b.a = true;
                } else {
                    this.b.a = false;
                }
                this.b.b = true;
                DJILogHelper.getInstance().LOGD(getClass().getSimpleName(), "get mcu type:" + this.b.a);
                i.a(this.b.f, a.c, this.b.b);
                i.a(this.b.f, a.d, this.b.a);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(getClass().getSimpleName(), "get mcu type failed");
            }
        }).a();
    }

    public void a(boolean z) {
        this.a = z;
    }
}
