package dji.pilot2;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraControlUpgrade;
import dji.midware.data.model.P3.DataCameraControlUpgrade.ControlCmd;
import dji.midware.data.model.P3.DataCameraGetPushUpgradeStatus;
import dji.midware.data.model.P3.DataCameraGetPushUpgradeStatus.UpgradeStep;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataRcAckGimbalCtrPermission;
import dji.pilot.R;
import dji.pilot.publics.control.a.e;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.publics.widget.a;
import dji.pilot.publics.widget.b;
import dji.pilot.publics.widget.g;
import dji.pilot.publics.widget.h;
import dji.pilot2.coupon.c;
import dji.pilot2.utils.f;
import dji.pilot2.utils.k;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import java.util.Locale;

public class DJIFragmentActivityNoFullScreen extends Activity {
    private static String a = "DJIFragmentActivityNoFullScreen";
    public static int cK_ = 0;
    public static float cL_ = 0.0f;
    public static int ev_ = 0;
    private static final int r = 300;
    private static long s = 0;
    private UpgradeStep A;
    private int B = 5;
    private boolean C = false;
    private Handler D = new Handler(new 2(this));
    protected Window cI_;
    protected b cJ_;
    protected ViewGroup cM_ = null;
    protected int[] cN_ = null;
    protected int cO_ = 0;
    protected OnClickListener cP_ = null;
    protected View cQ_ = null;
    protected DJIImageView cR_ = null;
    protected Options cS_ = null;
    protected boolean cT_ = false;
    f cU_ = null;
    protected boolean ew_;
    protected dji.pilot2.publics.a.b ex_ = dji.pilot2.publics.a.b.getInstance();
    private Context t;
    private b u;
    private b v;
    private b w;
    private a x;
    private h y;
    private g z;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 19) {
            getWindow().addFlags(67108864);
            if (!DJIOriLayout.getDeviceType().equals(DJIDeviceType.Pad)) {
                getWindow().addFlags(134217728);
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
    }

    protected void b() {
        int i;
        if (VERSION.SDK_INT >= 19) {
            i = 2050;
        } else {
            i = 2;
        }
        getWindow().getDecorView().setSystemUiVisibility(i);
    }

    protected void a(Window window) {
    }

    @SuppressLint({"NewApi"})
    public void setContentView(int i) {
        super.setContentView(i);
        DJIOriLayout.setOrientationByDevice(this);
        this.cI_ = getWindow();
        this.t = this;
        dji.pilot.fpv.model.b.a(getBaseContext());
        if (cK_ == 0) {
            if (VERSION.SDK_INT < 17) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                cK_ = displayMetrics.widthPixels;
                ev_ = displayMetrics.heightPixels;
                if (cK_ < ev_) {
                    int i2 = cK_;
                    cK_ = ev_;
                    ev_ = i2;
                }
            } else {
                Display defaultDisplay = getWindowManager().getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getRealSize(point);
                cK_ = point.x > point.y ? point.x : point.y;
                ev_ = point.x > point.y ? point.y : point.x;
            }
            cL_ = (((float) cK_) * 1.0f) / ((float) ev_);
        }
        this.D.sendEmptyMessageDelayed(100, 1000);
    }

    protected void onResume() {
        super.onResume();
        this.ew_ = true;
        this.ex_.c(this);
        if (dji.logic.c.b.getInstance().a()) {
            dji.logic.c.b.getInstance().a(this, getString(R.string.wm220_switch_to_rc_title), getString(R.string.wm220_switch_to_rc_tip), getString(R.string.wm220_flying_not_switch_to_rc));
        }
    }

    private void a() {
        if (this.ex_.a()) {
            this.ex_.a(false);
            this.ex_.b(this);
        }
    }

    protected void onPause() {
        super.onPause();
        this.ew_ = false;
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
        DataCameraGetPushUpgradeStatus.getInstance().clear();
    }

    public void c() {
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - s < 300) {
                DJILogHelper.getInstance().autoHandle();
                Log.d("", "click double");
                s = 0;
            } else {
                s = currentTimeMillis;
                Log.d("", "click single");
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onEventMainThread(dji.pilot.publics.control.a.b bVar) {
        if (this.ew_) {
            switch (3.a[bVar.ordinal()]) {
                case 1:
                    return;
                case 2:
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis >= dji.pilot.publics.objects.g.b(getBaseContext(), dji.pilot.publics.control.a.d, currentTimeMillis)) {
                        if (this.v == null) {
                            this.v = new b(this, false);
                            this.v.f();
                            this.v.a(R.string.app_tip);
                            this.v.b(R.string.check_app_upgrade_tip);
                            this.v.d(R.string.check_app_upgrade_not_show);
                            this.v.a(new 1(this, currentTimeMillis));
                            this.v.e(R.string.show_netupgrade_dialog_right);
                            this.v.b(new 4(this));
                        }
                        this.v.show();
                        return;
                    }
                    return;
                case 3:
                    if (this.u == null) {
                        this.u = new b(this, false);
                        this.u.f();
                        this.u.a(R.string.app_tip);
                        this.u.b(R.string.check_app_must_upgrade);
                        this.u.d(R.string.check_app_must_upgrade_now);
                        this.u.a(new 5(this));
                        this.u.e(R.string.check_app_must_upgrade_now_web);
                        this.u.b(new 6(this));
                    }
                    this.u.show();
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventMainThread(e eVar) {
        if (this.ew_) {
            switch (3.b[eVar.ordinal()]) {
                case 1:
                    if (this.w == null) {
                        this.w = new b(this, false);
                        this.w.f();
                        this.w.a(R.string.app_tip);
                        this.w.b(getString(R.string.check_upgrade_remain_time_tips, new Object[]{Integer.valueOf(dji.pilot.publics.control.a.getInstance().k())}));
                        this.w.d(R.string.app_enter);
                        this.w.a(new 7(this));
                    }
                    this.w.show();
                    return;
                case 2:
                case 3:
                    return;
                default:
                    return;
            }
        }
    }

    private void a(boolean z) {
        if (z) {
            c.a(this, "");
        } else if (dji.pilot.usercenter.b.f.getInstance().c()) {
            com.dji.frame.c.c.b(getApplicationContext()).a(k.m(dji.pilot.usercenter.b.f.getInstance().n()), new 8(this));
        }
    }

    public void onEventMainThread(dji.pilot.publics.control.a.c cVar) {
        Log.i("zyx", "show notice !!!!>>>>");
        dji.pilot.publics.control.a.getInstance().p();
        switch (3.c[cVar.ordinal()]) {
            case 1:
                if (this.cU_ == null) {
                    this.cU_ = new f(this.t);
                }
                String language = Locale.getDefault().getLanguage();
                String str = "";
                if (language.equals("zh") || language.equals("cn")) {
                    language = k.a() + "lang=cn";
                } else {
                    language = k.a() + "lang=en";
                }
                a(false);
                com.dji.frame.c.c.b(this).a(language, new 9(this));
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(com.dji.frame.c.c.a aVar) {
        if (this.ew_) {
            switch (3.d[aVar.ordinal()]) {
                case 1:
                case 2:
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (this.C != dataOsdGetPushCommon.isMotorUp()) {
            this.C = dataOsdGetPushCommon.isMotorUp();
            this.D.sendEmptyMessage(300);
        }
        a(dataOsdGetPushCommon);
    }

    protected void a(DataOsdGetPushCommon dataOsdGetPushCommon) {
    }

    public void onEventMainThread(o oVar) {
        if (this.ew_) {
            switch (3.e[oVar.ordinal()]) {
                case 1:
                    return;
                case 2:
                    this.A = null;
                    if (this.y != null) {
                        this.y.dismiss();
                    }
                    if (this.z == null) {
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventMainThread(p pVar) {
        if (this.ew_) {
            switch (3.f[pVar.ordinal()]) {
                case 1:
                    return;
                case 2:
                    if (this.z != null) {
                        this.z.dismiss();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventBackgroundThread(DataRcAckGimbalCtrPermission dataRcAckGimbalCtrPermission) {
        if (this.ew_) {
            Log.d("", "DataRcAckGimbalCtrPermission");
            this.D.post(new 10(this, dataRcAckGimbalCtrPermission));
        }
    }

    private void f() {
        DataCameraControlUpgrade.getInstance().setControlCmd(ControlCmd.Start).start(new 11(this));
    }

    public void onEventMainThread(DataCameraGetPushUpgradeStatus dataCameraGetPushUpgradeStatus) {
        if (this.ew_) {
            UpgradeStep step = dataCameraGetPushUpgradeStatus.getStep();
            if (step != this.A) {
                this.A = step;
                DJILogHelper.getInstance().LOGD(a, "upgrade step " + this.A, false, true);
                switch (3.g[step.ordinal()]) {
                    case 1:
                    case 3:
                        return;
                    case 2:
                        f();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void a(int i, int i2, int i3) {
        if (this.z == null) {
            this.z = new g(this);
            this.z.a(getString(R.string.app_upgrade_progress));
            this.z.a(i3);
        } else {
            this.z.a(i3);
        }
        if (this.z != null && !this.z.isShowing()) {
            this.z.show();
        }
    }

    private void g() {
        if (this.y == null || !this.y.isShowing() || !this.y.f() || this.y.b()) {
            return;
        }
        if (this.C) {
            this.y.a(false);
            this.y.d(R.string.upgrade_prohibited_message);
            return;
        }
        this.y.a(true);
        this.y.d(R.string.app_upgrade_start_title);
    }

    public void onEventMainThread(dji.logic.c.b.a aVar) {
        if (aVar == dji.logic.c.b.a.SHOW_SWITCH_DLG) {
            dji.logic.c.b.getInstance().a(true);
        } else if (aVar == dji.logic.c.b.a.SHOW_NOT_CONNECT_DLG) {
            dji.logic.c.b.getInstance().a((Context) this, getString(R.string.wm220_not_connect_rc));
        }
    }

    public void onEventMainThread(DJIGlobalService.b bVar) {
    }

    public int d() {
        int i = 0;
        try {
            Class cls = Class.forName("com.android.internal.R$dimen");
            i = getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}
