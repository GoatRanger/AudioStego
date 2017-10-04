package dji.pilot.visual.a;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataGimbalSpeedControl;
import dji.midware.data.model.P3.DataSingleSetFlyYaw;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import java.util.Timer;
import java.util.TimerTask;
import lecho.lib.hellocharts.model.l;

public class b {
    protected static final String a = b.class.getSimpleName();
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    private static final boolean h = true;
    protected float e;
    protected float f;
    public boolean g = false;
    private DJIImageView i;
    private DJIImageView j;
    private DJIImageView k = null;
    private float l = 0.0f;
    private float m = 0.0f;
    private int n = 0;
    private volatile int o = 0;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t = 0;
    private int u = 0;
    private Timer v;
    private float w;
    private float x;

    public b(DJIRelativeLayout dJIRelativeLayout, Activity activity) {
        this.i = (DJIImageView) dJIRelativeLayout.findViewById(R.id.d9f);
        this.j = (DJIImageView) dJIRelativeLayout.findViewById(R.id.d9h);
        this.k = (DJIImageView) dJIRelativeLayout.findViewById(R.id.d9g);
        a(activity);
    }

    @SuppressLint({"NewApi"})
    private void a(Activity activity) {
        int i;
        int i2;
        int i3;
        if (VERSION.SDK_INT < 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
            if (i >= i2) {
                i3 = i2;
                i2 = i;
                i = i3;
            }
        } else {
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            i3 = point.x > point.y ? point.y : point.x;
            i2 = point.x > point.y ? point.x : point.y;
            i = i3;
        }
        this.e = DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity / ((float) i2);
        this.f = DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity / ((float) i);
    }

    private void d() {
        if (this.v != null) {
            this.v.cancel();
        }
        this.v = new Timer();
        this.v.schedule(new TimerTask(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.f();
            }
        }, 0, 50);
    }

    private void e() {
        if (this.v != null) {
            this.v.cancel();
            this.v = null;
        }
    }

    public boolean a() {
        return this.n == 2;
    }

    public void b() {
        if (this.g) {
            e();
            g();
            this.w = 0.0f;
            this.x = 0.0f;
        }
        this.n = 0;
        this.g = false;
    }

    private void f() {
        int i = 50;
        int i2 = (int) (this.e * this.w);
        int i3 = (int) ((-this.e) * this.x);
        if (this.n == 1) {
            DataGimbalSpeedControl.getInstance().setPermission(true).setPitch(i3 * Math.abs(i3)).setRoll(0).setYaw(0).start();
            if (Math.abs(this.w) > 1.0f / this.e) {
                if (i2 <= 50) {
                    if (i2 < -50) {
                        i = -50;
                    } else {
                        i = i2;
                    }
                }
                a(i * 200);
            }
        } else if (this.n != 2) {
            DataGimbalSpeedControl.getInstance().setPermission(true).setPitch(Math.abs(i3) * i3).setRoll(0).setYaw(Math.abs(i2) * i2).start();
        }
    }

    public void a(float f, float f2) {
        if (dji.pilot.fpv.d.b.o(i.getInstance().c()) || 1 == this.n) {
            this.w = f;
        }
        this.x = f2;
    }

    private void g() {
        if (this.n == 1) {
            for (int i = 0; i < 3; i++) {
                a(0);
            }
        }
        if (this.n == 1 || this.n == 0) {
            DataGimbalSpeedControl.getInstance().setPermission(false).setPitch(0).setRoll(0).setYaw(0).start(null);
        }
        this.i.animGo();
        this.k.animGo();
        this.j.animGo();
    }

    private int b(int i) {
        if (i >= 512 || i <= 0) {
            return 1;
        }
        return i + 1;
    }

    public void a(float f, float f2, int i) {
        this.g = true;
        if (this.p == 0) {
            this.p = this.i.getWidth();
            this.q = this.i.getHeight();
            this.r = this.j.getWidth();
            this.s = this.j.getHeight();
            this.t = this.k.getWidth();
            this.u = this.k.getHeight();
        }
        this.n = i;
        this.l = f;
        this.m = f2;
        this.o = b(this.o);
        this.k.setSoundEffectsEnabled(true);
        this.k.playSoundEffect(4);
        this.k.performHapticFeedback(0, 2);
        this.i.setX(f - ((float) (this.p / 2)));
        this.i.setY(f2 - ((float) (this.q / 2)));
        this.j.setX(f - ((float) (this.r / 2)));
        this.j.setY(f2 - ((float) (this.s / 2)));
        this.i.animShow();
        this.j.animShow();
        if (this.n != 2) {
            d();
        }
    }

    public void a(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (this.n == 2 || this.n == 1 || dji.pilot.fpv.d.b.o(i.getInstance().c())) {
            this.j.setX(x - ((float) (this.r / 2)));
        }
        this.j.setY(y - ((float) (this.s / 2)));
        b(x, y);
        if (this.n != 2) {
        }
    }

    private void b(float f, float f2) {
        float f3;
        if (this.n == 1) {
            f3 = f - this.l;
        } else {
            f3 = 0.0f;
        }
        float f4 = f2 - this.m;
        float sqrt = (float) Math.sqrt((double) ((f3 * f3) + (f4 * f4)));
        float pow = (float) Math.pow((double) sqrt, 0.7d);
        if (pow < ((float) this.t) * l.n || sqrt < 1.0f) {
            this.k.go();
            return;
        }
        double d;
        this.k.show();
        sqrt = pow / sqrt;
        f3 *= sqrt;
        f4 *= sqrt;
        this.k.setX((this.l + f3) - ((float) (this.t / 2)));
        this.k.setY((this.m + f4) - ((float) (this.u / 2)));
        this.k.setAlpha(Math.min(0.7f, (pow / ((float) this.t)) / 3.0f));
        double asin = Math.asin((double) (f4 / pow));
        if (f3 < 0.0f) {
            d = 3.141592653589793d - asin;
        } else {
            d = asin;
        }
        this.k.setRotation((float) Math.toDegrees(d));
        f3 = (float) (1.0d + Math.min(0.5d, ((double) (pow / ((float) this.t))) / 10.0d));
        this.k.setScaleX(f3);
        this.k.setScaleY(f3);
    }

    public void c() {
        this.n = 0;
    }

    public void a(int i) {
        new DataSingleSetFlyYaw().a((short) this.o).b((short) i).a();
    }
}
