package dji.pilot.fpv.control;

import android.content.Context;
import android.os.Handler;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.WM220LogUtil;
import dji.logic.c.b;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetJoyStickParams;
import dji.midware.data.model.P3.DataFlycSetJoyStickParams.FlycMode;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.publics.objects.g;
import dji.thirdparty.a.c;
import java.util.Timer;

public class v {
    private static final int A = 1001;
    private static final int B = 5000;
    public static final String a = "key_wifi_rc_stick_mode";
    public static int b = 587;
    public static final int c = 1024;
    public static final int d = 50;
    public static final int e = 80;
    private static v f = null;
    private static final float j = 0.89f;
    private static final int k = 660;
    private static final String x = "g_config.flying_limit.max_radius_0";
    private static final String y = "g_config.flying_limit.max_height_0";
    private static final String z = "g_config.novice_cfg.novice_func_enabled_0";
    private Handler C = new Handler(new 3(this));
    private Context g;
    private b h = b.a;
    private DataFlycSetJoyStickParams i = new DataFlycSetJoyStickParams();
    private int l = 1024;
    private int m = 1024;
    private int n = 1024;
    private int o = 1024;
    private FlycMode p = FlycMode.b;
    private final int q = 100;
    private int r = 100;
    private boolean s = false;
    private Timer t = null;
    private int u = 0;
    private int v = 0;
    private boolean w = false;

    public static synchronized v getInstance() {
        v vVar;
        synchronized (v.class) {
            if (f == null) {
                f = new v();
            }
            vVar = f;
        }
        return vVar;
    }

    public void a(Context context) {
        this.g = context.getApplicationContext();
        this.h = b.values()[g.b(context, a, 0)];
    }

    private v() {
        c.a().a((Object) this);
    }

    public void a(int i) {
        this.r = i;
        d();
        c();
    }

    public void a() {
        this.r = 100;
        d();
        c();
    }

    public int b() {
        return b;
    }

    public void b(int i) {
        b = i;
    }

    public void a(int i, int i2) {
        if (this.h == b.a) {
            c(i);
            d(i2);
        } else if (this.h == b.c) {
            e(i);
            f(i2);
        } else if (this.h == b.b) {
            e(i);
            d(i2);
        }
    }

    private void c(int i) {
        this.o = i;
        if (this.o > b + 1024) {
            this.o = b + 1024;
        } else if (this.o <= (1024 - b) + 5) {
            if (this.s) {
                int swaveHeight;
                DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
                if (instance.isSwaveWork()) {
                    swaveHeight = instance.getSwaveHeight();
                } else {
                    swaveHeight = instance.getHeight();
                }
                if (swaveHeight < 5) {
                    this.o = 364;
                }
            } else {
                this.o = 1024 - b;
            }
            if (!this.C.hasMessages(1001)) {
                this.C.sendEmptyMessageDelayed(1001, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
            }
        } else {
            this.C.removeMessages(1001);
            this.s = false;
        }
    }

    private void d(int i) {
        this.l = i;
        if (this.l > b + 1024) {
            this.l = b + 1024;
        } else if (this.l < 1024 - b) {
            this.l = 1024 - b;
        }
    }

    public void b(int i, int i2) {
        if (this.h == b.a) {
            e(i);
            f(i2);
        } else if (this.h == b.c) {
            c(i);
            d(i2);
        } else if (this.h == b.b) {
            c(i);
            f(i2);
        }
    }

    private void e(int i) {
        this.n = i;
        if (this.n > b + 1024) {
            this.n = b + 1024;
        } else if (this.n < 1024 - b) {
            this.n = 1024 - b;
        }
    }

    private void f(int i) {
        this.m = i;
        if (this.m > b + 1024) {
            this.m = b + 1024;
        } else if (this.m < 1024 - b) {
            this.m = 1024 - b;
        }
    }

    public void a(FlycMode flycMode) {
        this.p = flycMode;
    }

    private void c() {
        if (b.getInstance().a(null)) {
            this.t = new Timer("joystickTimer");
            this.t.schedule(new 1(this), 10, (long) this.r);
        }
    }

    private void d() {
        if (this.t != null) {
            this.t.cancel();
            this.t = null;
        }
    }

    public void a(a aVar) {
        DataFlycGetParams.getInstance().setInfos(new String[]{"g_config.novice_cfg.novice_func_enabled_0", y, x}).start(new 2(this, aVar));
    }

    private void e() {
        new DataFlycSetParams().a(y, Integer.valueOf(this.u)).start(null);
    }

    private void f() {
        new DataFlycSetParams().a(x, Integer.valueOf(this.v)).start(null);
    }

    public void onEventBackgroundThread(c cVar) {
        this.h = b.values()[g.b(this.g, a, b.a.ordinal())];
    }

    public void onEventBackgroundThread(o oVar) {
        WM220LogUtil.LOGD("into joystickController DataCameraEvent");
        if (b.getInstance().a(null)) {
            if (oVar == o.ConnectOK) {
                a(this.p);
                c();
            } else if (oVar == o.ConnectLose) {
                d();
            }
        } else if (!b.getInstance().b()) {
            d();
        }
    }
}
