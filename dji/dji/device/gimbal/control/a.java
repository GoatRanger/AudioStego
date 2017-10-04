package dji.device.gimbal.control;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.logic.f.d;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataGimbalSpeedControl;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import java.util.Timer;
import java.util.TimerTask;

public class a {
    protected float a;
    protected float b;
    Activity c;
    public boolean d = false;
    private String e = "DJIGimbalSpeedController";
    private DJIImageView f;
    private DJIImageView g;
    private int h;
    private int i;
    private int j;
    private int k;
    private Timer l;
    private float m;
    private float n;

    public a(DJIRelativeLayout dJIRelativeLayout, Activity activity) {
        this.f = (DJIImageView) dJIRelativeLayout.findViewById(R.id.fpv_gimbal_control_center);
        this.g = (DJIImageView) dJIRelativeLayout.findViewById(R.id.fpv_gimbal_control_move);
        this.c = activity;
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
        this.a = DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity / ((float) i2);
        this.b = DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity / ((float) i);
    }

    private void b() {
        if (this.l != null) {
            this.l.cancel();
        }
        this.l = new Timer();
        this.l.schedule(new TimerTask(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.d();
            }
        }, 0, 40);
    }

    private void c() {
        if (this.l != null) {
            this.l.cancel();
            this.l = null;
        }
    }

    public void a() {
        this.d = false;
        this.m = 0.0f;
        this.n = 0.0f;
        c();
        e();
    }

    private void d() {
        int i = (int) (this.a * this.m);
        int i2 = (int) ((-this.a) * this.n);
        DataGimbalSpeedControl.getInstance().setPermission(true).setPitch(i2 * Math.abs(i2)).setRoll(0).setYaw(i * Math.abs(i)).start();
    }

    public void a(float f, float f2) {
        if (i.getInstance().c() == ProductType.Orange || i.getInstance().c() == ProductType.N1 || d.d(null)) {
            this.m = f;
        }
        this.n = f2;
    }

    private void e() {
        DataGimbalSpeedControl.getInstance().setPermission(false).setPitch(0).setRoll(0).setYaw(0).start(new dji.midware.e.d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD(this.a.e, "DataGimbalSpeedControl onSuccess");
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(this.a.e, "DataGimbalSpeedControl fail " + aVar);
            }
        });
        this.f.animGo();
        this.g.animGo();
    }

    public void a(MotionEvent motionEvent) {
        if (motionEvent != null) {
            this.d = true;
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (this.h == 0) {
                this.h = this.f.getWidth();
                this.i = this.f.getHeight();
                this.j = this.g.getWidth();
                this.k = this.g.getHeight();
            }
            this.f.setX(x - ((float) (this.h / 2)));
            this.f.setY(y - ((float) (this.i / 2)));
            this.g.setX(x - ((float) (this.j / 2)));
            this.g.setY(y - ((float) (this.k / 2)));
            this.f.animShow();
            this.g.animShow();
            b();
            ((Vibrator) this.c.getSystemService("vibrator")).vibrate(new long[]{0, 100}, -1);
        }
    }

    public void b(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (i.getInstance().c() == ProductType.Orange || i.getInstance().c() == ProductType.N1 || d.d(null)) {
            this.g.setX(x - ((float) (this.j / 2)));
        }
        this.g.setY(y - ((float) (this.k / 2)));
    }
}
