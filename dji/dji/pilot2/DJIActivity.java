package dji.pilot2;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import com.dji.frame.c.c;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraControlUpgrade;
import dji.midware.data.model.P3.DataCameraControlUpgrade.ControlCmd;
import dji.midware.data.model.P3.DataCameraGetPushUpgradeStatus;
import dji.midware.data.model.P3.DataCameraGetPushUpgradeStatus.UpgradeStep;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataRcAckGimbalCtrPermission;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.publics.control.a;
import dji.pilot.publics.control.a$b;
import dji.pilot.publics.control.a.e;
import dji.pilot.publics.widget.b;
import dji.pilot.publics.widget.g;
import dji.pilot.publics.widget.h;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.thirdparty.afinal.FinalActivity;

public class DJIActivity extends FinalActivity {
    private static final int a = 300;
    private static long b = 0;
    public static int bj_;
    public static int bk_;
    public static float bl_;
    private boolean A = false;
    private Handler B = new Handler(new Callback(this) {
        final /* synthetic */ DJIActivity a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            boolean z = true;
            switch (message.what) {
                case 1:
                    this.a.a(message.arg1, message.arg2, ((Integer) message.obj).intValue());
                    break;
                case 3:
                    this.a.a(this.a.bh_);
                    break;
                case 100:
                    this.a.onEventMainThread(DataCameraGetPushUpgradeStatus.getInstance());
                    this.a.onEventMainThread(a.getInstance(this.a).h);
                    break;
                case 200:
                    this.a.z = this.a.z - 1;
                    if (this.a.z != 0) {
                        this.a.w.a(this.a.getString(R.string.app_upgrade_success, new Object[]{Integer.valueOf(this.a.z)}));
                        this.a.B.sendEmptyMessageDelayed(200, 1000);
                        break;
                    }
                    this.a.z = 5;
                    DataCameraControlUpgrade.getInstance().setControlCmd(ControlCmd.Restart).start(new d(this) {
                        final /* synthetic */ AnonymousClass8 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            this.a.a.B.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.a.w.dismiss();
                                }
                            });
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            DJILogHelper.getInstance().LOGD(this.a.a.TAG, "restart failed", false, true);
                        }
                    });
                    break;
                case 210:
                    this.a.w.dismiss();
                    break;
                case 300:
                    this.a.e();
                    break;
                case 400:
                    DJIActivity dJIActivity = this.a;
                    if (this.a.A) {
                        z = false;
                    }
                    dJIActivity.A = z;
                    this.a.e();
                    this.a.B.sendEmptyMessageDelayed(400, 2000);
                    break;
            }
            return false;
        }
    });
    protected Window bh_;
    protected b bi_;
    protected ViewGroup bm_ = null;
    protected int[] bn_ = null;
    protected int bo_ = 0;
    protected OnClickListener bp_ = null;
    protected View bq_ = null;
    protected DJIImageView br_ = null;
    protected Options bs_ = null;
    protected boolean bt_ = false;
    protected boolean bu_;
    protected dji.pilot2.publics.a.b bv_ = dji.pilot2.publics.a.b.getInstance();
    private Context c;
    private b d;
    private b t;
    private b u;
    private dji.pilot.publics.widget.a v;
    private h w;
    private g x;
    private UpgradeStep y;
    private int z = 5;

    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] b = new int[e.values().length];
        static final /* synthetic */ int[] c = new int[c.a.values().length];
        static final /* synthetic */ int[] d = new int[o.values().length];
        static final /* synthetic */ int[] e = new int[p.values().length];
        static final /* synthetic */ int[] f = new int[UpgradeStep.values().length];

        static {
            try {
                f[UpgradeStep.Check.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f[UpgradeStep.Ack.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f[UpgradeStep.End.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                e[p.b.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                e[p.a.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                d[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                d[o.a.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                c[c.a.a.ordinal()] = 1;
            } catch (NoSuchFieldError e8) {
            }
            try {
                c[c.a.b.ordinal()] = 2;
            } catch (NoSuchFieldError e9) {
            }
            try {
                b[e.c.ordinal()] = 1;
            } catch (NoSuchFieldError e10) {
            }
            try {
                b[e.a.ordinal()] = 2;
            } catch (NoSuchFieldError e11) {
            }
            try {
                b[e.b.ordinal()] = 3;
            } catch (NoSuchFieldError e12) {
            }
            a = new int[a$b.values().length];
            try {
                a[a$b.NEW.ordinal()] = 1;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[a$b.OLD.ordinal()] = 2;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[a$b.OLD_FORCE.ordinal()] = 3;
            } catch (NoSuchFieldError e15) {
            }
        }
    }

    protected void c() {
        int i;
        if (VERSION.SDK_INT >= 19) {
            i = 2050;
        } else {
            i = 2;
        }
        getWindow().getDecorView().setSystemUiVisibility(i);
    }

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 19) {
            getWindow().addFlags(67108864);
            if (!DJIOriLayout.getDeviceType().equals(DJIDeviceType.Pad)) {
                getWindow().addFlags(134217728);
            }
        }
        getWindow().addFlags(128);
    }

    protected void a(Window window) {
        c.a(window);
    }

    @SuppressLint({"NewApi"})
    public void setContentView(int i) {
        super.setContentView(i);
        DJIOriLayout.setOrientationByDevice(this);
        this.bh_ = getWindow();
        a(this.bh_);
        this.c = this;
        dji.pilot.fpv.model.b.a(getBaseContext());
        if (bj_ == 0) {
            if (VERSION.SDK_INT < 17) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                bj_ = displayMetrics.widthPixels;
                bk_ = displayMetrics.heightPixels;
                if (bj_ < bk_) {
                    int i2 = bj_;
                    bj_ = bk_;
                    bk_ = i2;
                }
            } else {
                Display defaultDisplay = getWindowManager().getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getRealSize(point);
                bj_ = point.x > point.y ? point.x : point.y;
                bk_ = point.x > point.y ? point.y : point.x;
            }
            bl_ = (((float) bj_) * 1.0f) / ((float) bk_);
        }
        this.B.sendEmptyMessageDelayed(100, 1000);
    }

    protected void onResume() {
        super.onResume();
        a(this.bh_);
        this.bu_ = true;
        this.bv_.c(this);
    }

    private void a() {
        if (this.bv_.a()) {
            this.bv_.a(false);
            this.bv_.b(this);
        }
    }

    protected void onPause() {
        super.onPause();
        this.bu_ = false;
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
        DataCameraGetPushUpgradeStatus.getInstance().clear();
    }

    public void d() {
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            a(this.bh_);
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - b < 300) {
                DJILogHelper.getInstance().autoHandle();
                Log.d("", "click double");
                b = 0;
            } else {
                b = currentTimeMillis;
                Log.d("", "click single");
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onEventMainThread(a$b dji_pilot_publics_control_a_b) {
        if (this.bu_) {
            switch (dji_pilot_publics_control_a_b) {
                case NEW:
                    return;
                case OLD:
                    final long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis >= dji.pilot.publics.objects.g.b(getBaseContext(), a.d, currentTimeMillis)) {
                        if (this.t == null) {
                            this.t = new b((Context) this, false);
                            this.t.f();
                            this.t.a((int) R.string.app_tip);
                            this.t.b((int) R.string.check_app_upgrade_tip);
                            this.t.d((int) R.string.check_app_upgrade_not_show);
                            this.t.a(new DialogInterface.OnClickListener(this) {
                                final /* synthetic */ DJIActivity b;

                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dji.pilot.publics.objects.g.a(this.b.getBaseContext(), a.d, currentTimeMillis + 86400000);
                                    dialogInterface.dismiss();
                                }
                            });
                            this.t.e(R.string.show_netupgrade_dialog_right);
                            this.t.b(new DialogInterface.OnClickListener(this) {
                                final /* synthetic */ DJIActivity a;

                                {
                                    this.a = r1;
                                }

                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent("android.intent.action.VIEW");
                                    intent.setData(Uri.parse("http://m.dji.net/djipilot"));
                                    this.a.startActivity(intent);
                                }
                            });
                        }
                        this.t.show();
                        return;
                    }
                    return;
                case OLD_FORCE:
                    if (this.d == null) {
                        this.d = new b((Context) this, false);
                        this.d.f();
                        this.d.a((int) R.string.app_tip);
                        this.d.b((int) R.string.check_app_must_upgrade);
                        this.d.d((int) R.string.check_app_must_upgrade_now);
                        this.d.a(new DialogInterface.OnClickListener(this) {
                            final /* synthetic */ DJIActivity a;

                            {
                                this.a = r1;
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent("android.intent.action.VIEW");
                                intent.setData(Uri.parse("market://details?id=" + this.a.getPackageName()));
                                this.a.startActivity(intent);
                            }
                        });
                        this.d.e(R.string.check_app_must_upgrade_now_web);
                        this.d.b(new DialogInterface.OnClickListener(this) {
                            final /* synthetic */ DJIActivity a;

                            {
                                this.a = r1;
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent("android.intent.action.VIEW");
                                intent.setData(Uri.parse("http://m.dji.net/djipilot"));
                                this.a.startActivity(intent);
                            }
                        });
                    }
                    this.d.show();
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventMainThread(e eVar) {
        if (this.bu_) {
            switch (AnonymousClass9.b[eVar.ordinal()]) {
                case 1:
                    if (this.u == null) {
                        this.u = new b((Context) this, false);
                        this.u.f();
                        this.u.a((int) R.string.app_tip);
                        this.u.b(getString(R.string.check_upgrade_remain_time_tips, new Object[]{Integer.valueOf(a.getInstance().k())}));
                        this.u.d((int) R.string.app_enter);
                        this.u.a(new DialogInterface.OnClickListener(this) {
                            final /* synthetic */ DJIActivity a;

                            {
                                this.a = r1;
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                                this.a.u.dismiss();
                            }
                        });
                    }
                    this.u.show();
                    return;
                case 2:
                case 3:
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventMainThread(c.a aVar) {
        if (this.bu_) {
            switch (AnonymousClass9.c[aVar.ordinal()]) {
                case 1:
                    a(this.bh_);
                    return;
                case 2:
                    this.B.sendEmptyMessageDelayed(3, 2000);
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (this.A != dataOsdGetPushCommon.isMotorUp()) {
            this.A = dataOsdGetPushCommon.isMotorUp();
            this.B.sendEmptyMessage(300);
        }
        a(dataOsdGetPushCommon);
    }

    protected void a(DataOsdGetPushCommon dataOsdGetPushCommon) {
    }

    public void onEventMainThread(o oVar) {
        if (this.bu_) {
            switch (AnonymousClass9.d[oVar.ordinal()]) {
                case 1:
                    return;
                case 2:
                    this.y = null;
                    if (this.w != null) {
                        this.w.dismiss();
                    }
                    if (this.x == null) {
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventMainThread(p pVar) {
        if (this.bu_) {
            switch (AnonymousClass9.e[pVar.ordinal()]) {
                case 1:
                    return;
                case 2:
                    if (this.x != null) {
                        this.x.dismiss();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventBackgroundThread(final DataRcAckGimbalCtrPermission dataRcAckGimbalCtrPermission) {
        if (this.bu_ && i.getInstance().c() != ProductType.litchiC) {
            Log.d("", "DataRcAckGimbalCtrPermission");
            this.B.post(new Runnable(this) {
                final /* synthetic */ DJIActivity b;

                public void run() {
                    if (this.b.v == null) {
                        this.b.v = dji.pilot.publics.widget.a.a(this.b, this.b.getString(R.string.fpv_rc_control_title), this.b.getString(R.string.fpv_rc_control_name, new Object[]{dataRcAckGimbalCtrPermission.getName()}), this.b.getString(R.string.base_refuse), new DialogInterface.OnClickListener(this) {
                            final /* synthetic */ AnonymousClass6 a;

                            {
                                this.a = r1;
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                                dataRcAckGimbalCtrPermission.setIsAgree(false).start();
                                dialogInterface.dismiss();
                            }
                        }, this.b.getString(R.string.base_agree), new DialogInterface.OnClickListener(this) {
                            final /* synthetic */ AnonymousClass6 a;

                            {
                                this.a = r1;
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                                dataRcAckGimbalCtrPermission.setIsAgree(true).start();
                                dialogInterface.dismiss();
                            }
                        });
                        this.b.v.a(true);
                        this.b.v.b(false);
                    }
                    this.b.v.show();
                    DJILogHelper.getInstance().LOGD(this.b.TAG, "DataRcAckGimbalCtrPermission dialog.show", false, true);
                }
            });
        }
    }

    private void b() {
        DataCameraControlUpgrade.getInstance().setControlCmd(ControlCmd.Start).start(new d(this) {
            final /* synthetic */ DJIActivity a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("", "ControlCmd start success", false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("", "ControlCmd start " + aVar, false, true);
            }
        });
    }

    public void onEventMainThread(DataCameraGetPushUpgradeStatus dataCameraGetPushUpgradeStatus) {
        if (this.bu_) {
            UpgradeStep step = dataCameraGetPushUpgradeStatus.getStep();
            if (step != this.y) {
                this.y = step;
                DJILogHelper.getInstance().LOGD(this.TAG, "upgrade step " + this.y, false, true);
                switch (AnonymousClass9.f[step.ordinal()]) {
                    case 1:
                    case 3:
                        return;
                    case 2:
                        b();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void a(int i, int i2, int i3) {
        if (this.x == null) {
            this.x = new g(this);
            this.x.a(getString(R.string.app_upgrade_progress));
            this.x.a(i3);
        } else {
            this.x.a(i3);
        }
        if (this.x != null && !this.x.isShowing()) {
            this.x.show();
        }
    }

    private void e() {
        if (this.w == null || !this.w.isShowing() || !this.w.f() || this.w.b()) {
            return;
        }
        if (this.A) {
            this.w.a(false);
            this.w.d((int) R.string.upgrade_prohibited_message);
            return;
        }
        this.w.a(true);
        this.w.d((int) R.string.app_upgrade_start_title);
    }
}
