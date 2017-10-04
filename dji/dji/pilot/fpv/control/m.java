package dji.pilot.fpv.control;

import android.view.MotionEvent;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataGimbalSpeedControl;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import java.util.Timer;
import java.util.TimerTask;
import lecho.lib.hellocharts.model.l;

public class m {
    private static final boolean e = true;
    private static final int t = 40;
    protected float a = (DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity / ((float) DJIBaseActivity.screenWidth));
    protected float b = (DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity / ((float) DJIBaseActivity.screenHeight));
    public boolean c = false;
    private String d = "DJIGimbalSpeedController";
    private DJIImageView f;
    private DJIImageView g;
    private DJIImageView h = null;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m = 0;
    private int n = 0;
    private Timer o;
    private float p;
    private float q;
    private float r = 0.0f;
    private float s = 0.0f;

    public m(DJIRelativeLayout dJIRelativeLayout) {
        this.f = (DJIImageView) dJIRelativeLayout.findViewById(R.id.a1i);
        this.g = (DJIImageView) dJIRelativeLayout.findViewById(R.id.a1k);
        this.h = (DJIImageView) dJIRelativeLayout.findViewById(R.id.a1j);
    }

    private void b() {
        if (this.o != null) {
            this.o.cancel();
        }
        this.o = new Timer();
        this.o.schedule(new TimerTask(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.d();
            }
        }, 0, 40);
    }

    private void c() {
        if (this.o != null) {
            this.o.cancel();
            this.o = null;
        }
        this.p = 0.0f;
        this.q = 0.0f;
    }

    public void a() {
        this.c = false;
        c();
        e();
    }

    private void d() {
        int i = (int) (this.a * this.p);
        int i2 = (int) ((-this.a) * this.q);
        a(i2 * Math.abs(i2), i * Math.abs(i));
    }

    public void a(float f, float f2) {
        if (b.o(i.getInstance().c())) {
            this.p = f;
        }
        this.q = f2;
    }

    private void e() {
        f();
        this.f.animGo();
        this.g.animGo();
        this.h.animGo();
    }

    private void a(int i, int i2) {
        DataGimbalSpeedControl.getInstance().setPermission(true).setPitch(i).setRoll(0).setYaw(i2).start();
    }

    private void f() {
        DataGimbalSpeedControl.getInstance().setPermission(false).setPitch(0).setRoll(0).setYaw(0).start(new d(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD(this.a.d, "DataGimbalSpeedControl onSuccess");
            }

            public void onFailure(a aVar) {
                DJILogHelper.getInstance().LOGD(this.a.d, "DataGimbalSpeedControl fail " + aVar);
            }
        });
    }

    public void a(MotionEvent motionEvent) {
        if (motionEvent != null) {
            this.c = true;
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (this.i == 0) {
                this.i = this.f.getWidth();
                this.j = this.f.getHeight();
                this.k = this.g.getWidth();
                this.l = this.g.getHeight();
                this.m = this.h.getWidth();
                this.n = this.h.getHeight();
            }
            this.r = x;
            this.s = y;
            this.h.performHapticFeedback(0, 2);
            this.f.setX(x - ((float) (this.i / 2)));
            this.f.setY(y - ((float) (this.j / 2)));
            this.g.setX(x - ((float) (this.k / 2)));
            this.g.setY(y - ((float) (this.l / 2)));
            this.f.animShow();
            this.g.animShow();
            b();
        }
    }

    public void b(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (b.o(i.getInstance().c())) {
            this.g.setX(x - ((float) (this.k / 2)));
        }
        this.g.setY(y - ((float) (this.l / 2)));
        b(x, y);
    }

    private void b(float f, float f2) {
        float f3 = f - this.r;
        if (!b.o(i.getInstance().c())) {
            f3 = 0.0f;
        }
        float f4 = f2 - this.s;
        float sqrt = (float) Math.sqrt((double) ((f3 * f3) + (f4 * f4)));
        float pow = (float) Math.pow((double) sqrt, 0.7d);
        if (pow < ((float) this.m) * l.n || sqrt < 1.0f) {
            this.h.go();
            return;
        }
        double d;
        this.h.show();
        sqrt = pow / sqrt;
        f3 *= sqrt;
        f4 *= sqrt;
        this.h.setX((this.r + f3) - ((float) (this.m / 2)));
        this.h.setY((this.s + f4) - ((float) (this.n / 2)));
        this.h.setAlpha(Math.min(0.7f, (pow / ((float) this.m)) / 3.0f));
        double asin = Math.asin((double) (f4 / pow));
        if (f3 < 0.0f) {
            d = 3.141592653589793d - asin;
        } else {
            d = asin;
        }
        this.h.setRotation((float) Math.toDegrees(d));
        f3 = (float) (1.0d + Math.min(0.5d, ((double) (pow / ((float) this.m))) / 10.0d));
        this.h.setScaleX(f3);
        this.h.setScaleY(f3);
    }
}
