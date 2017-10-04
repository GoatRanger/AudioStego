package dji.pilot.fpv.control;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.os.Handler.Callback;
import android.os.Message;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataEyeGetPushFlatCheck;
import dji.midware.data.model.P3.DataEyeGetPushFlatCheck.FlatStatus;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataFlycGetPushAvoidParam;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.util.i;
import dji.pilot.R;
import dji.pilot.fpv.leftmenu.b;
import dji.pilot.fpv.model.n$b;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.c;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIErrorPopView.f;
import dji.pilot.publics.c.e;
import dji.pilot.publics.objects.k;
import dji.pilot.publics.objects.k.a;

public class r implements a {
    private static final int a = 4096;
    private static final int b = 4097;
    private static final long c = 5000;
    private static final long d = 1000;
    private static final String e = "key_show_armaction";
    private Context f = null;
    private k g = null;
    private Callback h = null;
    private boolean i = false;
    private RcModeChannel j = RcModeChannel.CHANNEL_UNKNOWN;
    private dji.pilot.fpv.flightmode.a k = null;
    private boolean l = true;
    private dji.pilot.fpv.a.a m = null;
    private boolean n = false;
    private b o = null;
    private FlatStatus p = FlatStatus.None;
    private b q = null;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;

    public r(Context context) {
        this.f = context;
        this.l = i.b(context, e, this.l);
        j();
    }

    private void i() {
        if (DataOsdGetPushCommon.getInstance().isGetted()) {
            d();
        }
        if (DataOsdGetPushHome.getInstance().isGetted()) {
            e();
        }
        if (ServiceManager.getInstance().isRemoteOK()) {
            m();
        }
        if (DataFlycGetPushAvoidParam.getInstance().isGetted()) {
            b();
        }
        if (DataEyeGetPushFlatCheck.getInstance().isGetted()) {
            a();
        }
    }

    private void j() {
        this.h = new Callback(this) {
            final /* synthetic */ r a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 4096:
                        this.a.i();
                        break;
                    case 4097:
                        if (ServiceManager.getInstance().isRemoteOK() && this.a.t) {
                            DJIErrorPopView.b.b(d.b, R.string.fpv_gs_safe_avoid_obstacle_state, 0, c.a, f.a);
                            break;
                        }
                }
                return false;
            }
        };
        this.g = new k(this, this.h);
        if (ServiceManager.getInstance().isRemoteOK()) {
            this.g.sendEmptyMessageDelayed(4096, 5000);
        }
    }

    public void a() {
        if (!this.g.hasMessages(4096)) {
            FlatStatus flatStatus = DataEyeGetPushFlatCheck.getInstance().getFlatStatus();
            if (flatStatus != this.p) {
                a(flatStatus);
                this.p = flatStatus;
            }
        }
    }

    private void a(FlatStatus flatStatus) {
        if (FlatStatus.SafeForLanding == flatStatus) {
            DJIErrorPopView.b.b(d.a, R.string.landing_check_safe_land, 0, c.a, f.a);
            n();
        } else if (FlatStatus.UnsafeToHover == flatStatus) {
            a(this.f.getString(R.string.landing_check_not_smooth));
        } else if (FlatStatus.WaterSurfaceToHover == flatStatus) {
            a(this.f.getString(R.string.landing_check_water_surface));
        } else if (FlatStatus.EnterCheckArea == flatStatus) {
            DJIErrorPopView.b.b(d.a, R.string.landing_check_enter_area, 0, c.a, f.a);
            n();
        } else if (FlatStatus.DriftMuchWhenLanding == flatStatus) {
            DJIErrorPopView.b.b(d.b, R.string.landing_check_drift_much, 0, c.a, f.a);
            n();
        } else if (b(flatStatus) && !b(this.p)) {
            n();
            DJIErrorPopView.b.b(d.b, R.string.landing_check_badresult, 0, c.a, f.a);
        }
    }

    private boolean b(FlatStatus flatStatus) {
        return FlatStatus.UnderExposure == flatStatus || FlatStatus.MoveStickWhenCalculating == flatStatus || FlatStatus.TooLow == flatStatus || FlatStatus.TooHigh == flatStatus || FlatStatus.BadResult == flatStatus;
    }

    private void c(FlatStatus flatStatus) {
        if (FlatStatus.SafeForLanding == flatStatus) {
            DJIErrorPopView.b.b(d.a, R.string.landing_check_safe_land, 0, c.a, f.a);
            n();
        } else if (FlatStatus.UnsafeToHover == flatStatus) {
            a(this.f.getString(R.string.landing_check_not_smooth));
        } else if (FlatStatus.WaterSurfaceToHover == flatStatus) {
            a(this.f.getString(R.string.landing_check_water_surface));
        } else if (FlatStatus.EnterCheckArea == flatStatus) {
            DJIErrorPopView.b.b(d.a, R.string.landing_check_enter_area, 0, c.a, f.a);
            n();
        } else if (FlatStatus.UnderExposure == flatStatus) {
            DJIErrorPopView.b.b(d.b, R.string.landing_check_dark, 0, c.a, f.a);
            n();
        } else if (FlatStatus.DriftMuchWhenLanding == flatStatus) {
            DJIErrorPopView.b.b(d.b, R.string.landing_check_drift_much, 0, c.a, f.a);
            n();
        } else if (FlatStatus.MoveStickWhenCalculating == flatStatus) {
            n();
            DJIErrorPopView.b.b(d.b, R.string.landing_check_move_calculating, 0, c.a, f.a);
        } else if (FlatStatus.TooLow == flatStatus) {
            n();
            DJIErrorPopView.b.b(d.b, R.string.landing_check_toolow, 0, c.a, f.a);
        } else if (FlatStatus.TooHigh == flatStatus) {
            n();
            DJIErrorPopView.b.b(d.b, R.string.landing_check_toohigh, 0, c.a, f.a);
        } else if (FlatStatus.BadResult == flatStatus) {
            n();
            DJIErrorPopView.b.b(d.b, R.string.landing_check_badresult, 0, c.a, f.a);
        }
    }

    public void b() {
        if (!this.g.hasMessages(4096) && DataOsdGetPushCommon.getInstance().getFlycVersion() >= 13) {
            boolean z = DataFlycGetPushAvoidParam.getInstance().avoidGroundForceLanding() && dji.pilot.fpv.d.f.a(DataOsdGetPushCommon.getInstance().getFlycState());
            if (this.n != z) {
                this.n = z;
                if (z) {
                    o();
                } else {
                    p();
                }
            }
        }
    }

    public void c() {
        if (!this.g.hasMessages(4096) && DataOsdGetPushCommon.getInstance().getFlycVersion() >= 16 && dji.pilot.dji_groundstation.controller.d.getInstance().b() != dji.pilot.dji_groundstation.a.d.c.a) {
            DJIErrorPopView.b bVar;
            boolean isRadiusLimitWorking = DataFlycGetPushAvoidParam.getInstance().isRadiusLimitWorking();
            if (this.r != isRadiusLimitWorking) {
                this.r = isRadiusLimitWorking;
                if (this.r) {
                    bVar = new DJIErrorPopView.b();
                    bVar.b = R.string.fpv_gs_safe_radius_limte_state;
                    dji.thirdparty.a.c.a().e(bVar);
                }
            }
            isRadiusLimitWorking = DataFlycGetPushAvoidParam.getInstance().isAirportLimitWorking();
            if (this.s != isRadiusLimitWorking) {
                this.s = isRadiusLimitWorking;
                if (this.s) {
                    bVar = new DJIErrorPopView.b();
                    bVar.b = R.string.fpv_gs_safe_airport_limte_state;
                    dji.thirdparty.a.c.a().e(bVar);
                }
            }
            isRadiusLimitWorking = DataFlycGetPushAvoidParam.getInstance().isAvoidObstacleWorking();
            if (this.t != isRadiusLimitWorking) {
                this.t = isRadiusLimitWorking;
                if (this.t) {
                    this.g.sendEmptyMessageDelayed(4097, 1000);
                } else {
                    this.g.removeMessages(4097);
                }
            }
        }
    }

    public void d() {
        if (!this.g.hasMessages(4096)) {
            RcModeChannel modeChannel = DataOsdGetPushCommon.getInstance().getModeChannel();
            if (this.j != modeChannel) {
                this.j = modeChannel;
                a(modeChannel, this.i);
            }
        }
    }

    public void e() {
        if (!this.g.hasMessages(4096)) {
            DataOsdGetPushHome instance = DataOsdGetPushHome.getInstance();
            boolean a = dji.pilot.fpv.d.b.a(instance.isBeginnerMode(), instance.isMultipleModeOpen());
            if (this.i != a) {
                this.i = a;
                a(this.j, a);
            }
        }
    }

    public void f() {
        this.g.removeMessages(0, null);
        l();
        a(false);
        p();
        this.i = false;
        this.j = RcModeChannel.CHANNEL_UNKNOWN;
        this.n = false;
        this.p = FlatStatus.None;
    }

    private void a(RcModeChannel rcModeChannel, boolean z) {
        if (!z || rcModeChannel != RcModeChannel.CHANNEL_S || dji.pilot.publics.e.a.d(null)) {
            l();
        } else if (dji.pilot.fpv.flightmode.c.getInstance().d()) {
            k();
        }
    }

    private void k() {
        if (this.k == null) {
            this.k = new dji.pilot.fpv.flightmode.a(this.f);
            this.k.a(this.f.getResources().getString(R.string.fpv_sport_disclaimer_title));
            this.k.b(this.f.getResources().getString(R.string.fpv_sport_disclaimer_tip)).a(new OnClickListener(this) {
                final /* synthetic */ r a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.l();
                }
            }).b(new OnClickListener(this) {
                final /* synthetic */ r a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.l();
                    dji.pilot.fpv.flightmode.c.getInstance().e();
                }
            }).b().c(this.f.getString(R.string.app_enter));
            this.k.setOnShowListener(new OnShowListener(this) {
                final /* synthetic */ r a;

                {
                    this.a = r1;
                }

                public void onShow(DialogInterface dialogInterface) {
                    this.a.k.getWindow().getDecorView().postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            dji.thirdparty.a.c.a().e(n$b.HIDE_VIEWS_EXCEPT_TOP);
                        }
                    }, 50);
                }
            });
        }
        if (!this.k.isShowing()) {
            this.k.show();
        }
    }

    private void l() {
        if (this.k != null && this.k.isShowing()) {
            this.k.dismiss();
            this.k = null;
            dji.thirdparty.a.c.a().e(n$b.SHOW_VIEWS_EXCEPT_TOP);
        }
    }

    public void a(DataCommonGetVersion dataCommonGetVersion) {
        if (!this.g.hasMessages(4096) && dataCommonGetVersion != null && dataCommonGetVersion.isGetted() && dataCommonGetVersion.getDeviceType() != null && dataCommonGetVersion.getDeviceType() == DeviceType.OSD) {
            m();
        }
    }

    private void m() {
        if (dji.pilot.c.d.f == 1 && this.l) {
            String e = dji.pilot.publics.control.a.getInstance().e("1400");
            if (e != null) {
                try {
                    if (dji.pilot.publics.e.d.c(e) >= 67699493) {
                        dji.thirdparty.a.c.a().e(n$b.SHOW_NEW_ARMACTION_DLG);
                    }
                } catch (Exception e2) {
                }
            }
        }
    }

    public void g() {
        if (!this.g.hasMessages(4096)) {
            m();
        }
    }

    public void h() {
        if (this.l) {
            ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
            if (c != ProductType.Pomato && !dji.pilot.publics.e.a.c(c)) {
                if (this.m == null) {
                    this.m = new dji.pilot.fpv.a.a(this.f);
                    this.m.a(new OnClickListener(this) {
                        final /* synthetic */ r a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.a(true);
                        }
                    });
                    this.m.setOnShowListener(new OnShowListener(this) {
                        final /* synthetic */ r a;

                        {
                            this.a = r1;
                        }

                        public void onShow(DialogInterface dialogInterface) {
                            this.a.m.getWindow().getDecorView().postDelayed(new Runnable(this) {
                                final /* synthetic */ AnonymousClass7 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    dji.thirdparty.a.c.a().e(n$b.HIDE_VIEWS);
                                }
                            }, 50);
                        }
                    });
                }
                if (!this.m.isShowing()) {
                    this.m.show();
                }
            }
        }
    }

    private void a(boolean z) {
        if (this.m != null && this.m.isShowing()) {
            if (z) {
                this.l = false;
                i.a(this.f, e, false);
            }
            this.m.dismiss();
            this.m = null;
            dji.thirdparty.a.c.a().e(n$b.SHOW_VIEWS);
        }
    }

    private void a(String str) {
        if (this.q == null) {
            this.q = new b(this.f);
            this.q.a(new b.a(this) {
                final /* synthetic */ r a;

                {
                    this.a = r1;
                }

                public void a(DialogInterface dialogInterface, int i) {
                    this.a.n();
                }

                public void b(DialogInterface dialogInterface, int i) {
                }

                public void a(DialogInterface dialogInterface, boolean z, int i) {
                    if (z) {
                        this.a.n();
                        FLYC_COMMAND flyc_command = FLYC_COMMAND.ForceLanding2;
                        if (FlatStatus.UnsafeToHover == DataEyeGetPushFlatCheck.getInstance().getFlatStatus()) {
                            flyc_command = FLYC_COMMAND.ForceLanding2;
                        }
                        DataFlycFunctionControl.getInstance().setCommand(flyc_command).start(new dji.midware.e.d(this) {
                            final /* synthetic */ AnonymousClass8 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                DJIErrorPopView.b.a(d.b, this.a.a.f.getString(R.string.landing_check_cmd_fail), null, c.a, f.a);
                            }
                        });
                    }
                }
            });
            this.q.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ r a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                }
            });
            this.q.a(this.f.getString(R.string.landing_check_title));
            this.q.a(1);
            this.q.b((int) R.drawable.leftmenu_dlg_landing);
            this.q.a(8, 0);
            this.q.e(8);
            this.q.e(this.f.getString(R.string.landing_check_switch));
            this.q.d(8);
            this.q.c(this.f.getString(R.string.app_enter));
            this.q.setCancelable(false);
            this.q.setCanceledOnTouchOutside(false);
        }
        this.q.b(str);
        if (ProductType.Pomato == dji.midware.data.manager.P3.i.getInstance().c()) {
            e.getInstance().a(new long[]{100, 300, 500, 300}, -1);
        }
        if (!this.q.isShowing()) {
            this.q.show();
        }
    }

    private void n() {
        if (this.q != null && this.q.isShowing()) {
            this.q.dismiss();
            this.q = null;
        }
    }

    private void o() {
        if (this.o == null) {
            this.o = new b(this.f);
            this.o.a(new b.a(this) {
                final /* synthetic */ r a;

                {
                    this.a = r1;
                }

                public void a(DialogInterface dialogInterface, int i) {
                    this.a.p();
                    DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.DropLanding).start(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass10 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                        }
                    });
                }

                public void b(DialogInterface dialogInterface, int i) {
                }

                public void a(DialogInterface dialogInterface, boolean z, int i) {
                    if (z) {
                        this.a.p();
                        DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.ForceLanding).start(new dji.midware.e.d(this) {
                            final /* synthetic */ AnonymousClass10 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                DJIErrorPopView.b.a(d.b, this.a.a.f.getString(R.string.fpv_confirm_touchdown_fail), null, c.a, f.a);
                            }
                        });
                    }
                }
            });
            this.o.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ r a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                }
            });
            this.o.a(this.f.getString(R.string.fpv_confirm_touchdown_title));
            this.o.a(1);
            this.o.b((int) R.drawable.leftmenu_dlg_landing);
            this.o.a(8, 0);
            this.o.e(0);
            this.o.e(this.f.getString(R.string.fpv_confirm_touchdown_switch));
            this.o.d(8);
            this.o.setCancelable(false);
            this.o.setCanceledOnTouchOutside(false);
        }
        if (!this.o.isShowing()) {
            int forceLandingHeight = DataOsdGetPushHome.getInstance().getForceLandingHeight();
            if (forceLandingHeight != Integer.MIN_VALUE) {
                float f = ((float) forceLandingHeight) * 0.1f;
                this.o.b(this.f.getString(R.string.fpv_confirm_touchdown_custom_height_msg, new Object[]{Float.valueOf(f), Float.valueOf(dji.pilot.publics.e.e.f(f))}));
            } else {
                this.o.b(this.f.getString(R.string.fpv_confirm_touchdown_msg));
            }
            this.o.show();
        }
    }

    private void p() {
        if (this.o != null && this.o.isShowing()) {
            this.o.dismiss();
            this.o = null;
        }
    }

    public boolean isFinished() {
        return false;
    }
}
