package dji.pilot.groundStation.stage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.pilot.R;
import dji.pilot.fpv.control.k;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.flightmode.c$d;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.c;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIMainStageView extends DJIRelativeLayout implements c$i, a {
    private static final int o = 0;
    private static final int p = 1;
    private static final int q = 2;
    private static final int r = 3;
    private static final int s = 4;
    private static final int t = 5;
    private static final int[] u = new int[]{R.id.akj, R.id.akm, R.id.akp, R.id.aks, R.id.akv, R.id.aky};
    private static final int[] v = new int[]{R.id.akk, R.id.akn, R.id.akq, R.id.akt, R.id.akw, R.id.akz};
    private DJILinearLayout A = null;
    private DJILinearLayout B = null;
    private DJILinearLayout C = null;
    private final DJIImageView[] D = new DJIImageView[u.length];
    private final DJITextView[] E = new DJITextView[v.length];
    private DJIImageView F = null;
    private DJITextView G = null;
    private OnClickListener H = new OnClickListener(this) {
        final /* synthetic */ DJIMainStageView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
            if (instance.d()) {
                if (view.getId() == R.id.al0) {
                    dji.pilot.groundStation.a.a.getInstance(null).a();
                    ((DJIStageView) this.a.getParent()).stop();
                    return;
                } else if (view.getId() == R.id.al1) {
                    if (this.a.n == null) {
                        this.a.n = (DJIStageView) this.a.getParent();
                    }
                    this.a.n.createStageViewWithAnim(R.layout.fpv_flightmode_view, 100, true, -1);
                    return;
                }
            } else if (view.getId() == R.id.al1 || view.getId() == R.id.al0) {
                instance.a();
                ((DJIStageView) this.a.getParent()).stop();
                return;
            }
            if (view.getId() != R.id.al0) {
                DataOsdGetPushCommon instance2 = DataOsdGetPushCommon.getInstance();
                b bVar;
                if (instance2.getFlycState() == FLYC_STATE.GoHome || instance2.getFlycState() == FLYC_STATE.AutoLanding) {
                    bVar = new b();
                    bVar.a = d.b;
                    bVar.f = c.a;
                    bVar.b = R.string.gs_function_not_available;
                    dji.thirdparty.a.c.a().e(bVar);
                    return;
                } else if (i.getInstance().c() == ProductType.A2 && !instance2.isMotorUp()) {
                    if (this.a.n == null) {
                        this.a.n = (DJIStageView) this.a.getParent();
                    }
                    this.a.n.createStageView(R.layout.gs_fly_not_take_off_warning_view, 25, false);
                    return;
                } else if (i.getInstance().c() != ProductType.A2 && instance2.groundOrSky() != 2) {
                    if (this.a.n == null) {
                        this.a.n = (DJIStageView) this.a.getParent();
                    }
                    this.a.n.createStageView(R.layout.gs_fly_not_take_off_warning_view, 25, false);
                    return;
                } else if (instance2.getVoltageWarning() == 2) {
                    bVar = new b();
                    bVar.a = d.b;
                    bVar.f = c.a;
                    bVar.b = R.string.battery_low_warning;
                    dji.thirdparty.a.c.a().e(bVar);
                    return;
                }
            }
            dji.pilot.groundStation.a.a.d z = instance.z();
            k i = instance.i();
            switch (view.getId()) {
                case R.id.akj:
                    if (this.a.n == null) {
                        this.a.n = (DJIStageView) this.a.getParent();
                    }
                    if (z == dji.pilot.groundStation.a.a.d.NONE) {
                        if (!(i == null || i.l() == null)) {
                            i.l().g();
                        }
                        this.a.a(R.layout.gs_point_of_insterest_set_hot_point_view, 15, c$d.POI_AUTO);
                        return;
                    } else if (z == dji.pilot.groundStation.a.a.d.POI_AUTO) {
                        this.a.n.createStageView(R.layout.gs_point_of_insterest_status_view, 22, false);
                        return;
                    } else {
                        this.a.J = 0;
                        this.a.b(R.layout.gs_point_of_insterest_set_hot_point_view, 15, c$d.POI_AUTO);
                        return;
                    }
                case R.id.akm:
                    b bVar2 = new b();
                    bVar2.a = d.b;
                    bVar2.f = c.a;
                    if (k.k() == null || instance.i().l().F() >= 10000.0f) {
                        bVar2.b = R.string.gs_follow_me_device_has_no_gps;
                        dji.thirdparty.a.c.a().e(bVar2);
                        return;
                    } else if (instance.i().l().F() > dji.gs.e.b.a) {
                        bVar2.b = R.string.gs_follow_me_can_not_get_user_location;
                        dji.thirdparty.a.c.a().e(bVar2);
                        return;
                    } else {
                        if (this.a.n == null) {
                            this.a.n = (DJIStageView) this.a.getParent();
                        }
                        if (z == dji.pilot.groundStation.a.a.d.NONE) {
                            if (!(i == null || i.l() == null)) {
                                i.l().g();
                                i.l().b(null, 0.0d);
                            }
                            this.a.a(R.layout.gs_follow_me_view, 3, c$d.FOLLOW_ME);
                        } else if (z == dji.pilot.groundStation.a.a.d.FOLLOW_ME) {
                            this.a.n.createStageView(R.layout.gs_follow_me_status_view, 14, false);
                        } else {
                            this.a.J = 0;
                            this.a.b(R.layout.gs_follow_me_view, 3, c$d.FOLLOW_ME);
                        }
                        e.c(c$i.d);
                        return;
                    }
                case R.id.akp:
                    if (this.a.n == null) {
                        this.a.n = (DJIStageView) this.a.getParent();
                    }
                    if (z == dji.pilot.groundStation.a.a.d.NONE) {
                        if (!(i == null || i.l() == null)) {
                            i.l().g();
                        }
                        this.a.a(R.layout.gs_way_point_view, 5, c$d.WP_AUTO);
                        return;
                    } else if (z == dji.pilot.groundStation.a.a.d.WP_AUTO) {
                        this.a.n.createStageView(R.layout.gs_way_point_status_view, 21, false);
                        return;
                    } else {
                        this.a.J = 0;
                        this.a.b(R.layout.gs_way_point_view, 5, c$d.WP_AUTO);
                        return;
                    }
                case R.id.aks:
                    if (this.a.n == null) {
                        this.a.n = (DJIStageView) this.a.getParent();
                    }
                    if (z == dji.pilot.groundStation.a.a.d.NONE) {
                        if (!(i == null || i.l() == null)) {
                            i.l().g();
                            i.l().b(null, 0.0d);
                        }
                        this.a.a(R.layout.gs_home_lock_view, 2, c$d.HOME_LOCK);
                        return;
                    } else if (z == dji.pilot.groundStation.a.a.d.HOME_LOCK) {
                        this.a.n.createStageView(R.layout.gs_home_lock_status_view, 13, false);
                        return;
                    } else {
                        this.a.J = 0;
                        this.a.b(R.layout.gs_home_lock_view, 2, c$d.HOME_LOCK);
                        return;
                    }
                case R.id.akv:
                    if (this.a.n == null) {
                        this.a.n = (DJIStageView) this.a.getParent();
                    }
                    if (z == dji.pilot.groundStation.a.a.d.NONE) {
                        if (!(i == null || i.l() == null)) {
                            i.l().g();
                            i.l().b(null, 0.0d);
                        }
                        this.a.a(R.layout.gs_course_lock_view, 1, c$d.COURSE_LOCK);
                        return;
                    } else if (z == dji.pilot.groundStation.a.a.d.COURSE_LOCK) {
                        this.a.n.createStageView(R.layout.gs_course_lock_status_view, 12, false);
                        return;
                    } else {
                        this.a.J = 0;
                        this.a.b(R.layout.gs_course_lock_view, 1, c$d.COURSE_LOCK);
                        return;
                    }
                case R.id.aky:
                    if (this.a.n == null) {
                        this.a.n = (DJIStageView) this.a.getParent();
                    }
                    this.a.a(R.layout.gs_way_point_view, 5, c$d.PANO);
                    return;
                default:
                    return;
            }
        }
    };
    private int I = Integer.MIN_VALUE;
    private volatile int J = 0;
    private DJIStageView n = null;
    private DJILinearLayout w = null;
    private DJILinearLayout x = null;
    private DJILinearLayout y = null;
    private DJILinearLayout z = null;

    public DJIMainStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    private void a(ViewGroup viewGroup) {
        int i = DJIBaseActivity.screenWidth / 5;
        int childCount = viewGroup.getChildCount();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            if (viewGroup.getChildAt(i3).getVisibility() != 8) {
                i2++;
                LayoutParams layoutParams = viewGroup.getChildAt(i3).getLayoutParams();
                layoutParams.width = i;
                layoutParams.height = -1;
                viewGroup.getChildAt(i3).setLayoutParams(layoutParams);
            }
        }
        if (i2 > 0) {
            i2 *= i;
            if (i2 < DJIBaseActivity.screenWidth) {
                i2 = (DJIBaseActivity.screenWidth - i2) / 2;
                viewGroup.setPadding(i2, 0, 0, 0);
            }
        }
        i2 = 0;
        viewGroup.setPadding(i2, 0, 0, 0);
    }

    public void dispatchOnResume() {
        if ((i.getInstance().c() != ProductType.A2 ? 1 : 0) != 0) {
            this.y.show();
            this.x.show();
            this.z.show();
            this.F.show();
        } else {
            this.y.go();
            this.x.go();
            this.z.go();
            this.F.go();
        }
        if (dji.pilot.groundStation.a.a.getInstance(null).d()) {
            this.G.setText("");
            this.G.setCompoundDrawablesWithIntrinsicBounds(R.drawable.back_icon, 0, 0, 0);
        } else {
            this.G.setText(R.string.gs_hide);
            this.G.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        a(this.w);
        a(dji.pilot.fpv.flightmode.c.getInstance().c());
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.w = (DJILinearLayout) findViewById(R.id.akh);
            this.x = (DJILinearLayout) findViewById(R.id.aki);
            this.y = (DJILinearLayout) findViewById(R.id.akl);
            this.z = (DJILinearLayout) findViewById(R.id.ako);
            this.A = (DJILinearLayout) findViewById(R.id.akr);
            this.B = (DJILinearLayout) findViewById(R.id.aku);
            this.C = (DJILinearLayout) findViewById(R.id.akx);
            this.F = (DJIImageView) findViewById(R.id.al0);
            this.G = (DJITextView) findViewById(R.id.al1);
            for (int i = 0; i < u.length; i++) {
                this.D[i] = (DJIImageView) findViewById(u[i]);
                this.E[i] = (DJITextView) findViewById(v[i]);
                this.D[i].setOnClickListener(this.H);
            }
            this.F.setOnClickListener(this.H);
            this.G.setOnClickListener(this.H);
        }
    }

    private void a(c$d dji_pilot_fpv_flightmode_c_d) {
        int i = this.I;
        this.I = Integer.MIN_VALUE;
        if (c$d.POI_AUTO == dji_pilot_fpv_flightmode_c_d) {
            this.I = 0;
        } else if (c$d.WP_AUTO == dji_pilot_fpv_flightmode_c_d) {
            this.I = 2;
        } else if (c$d.FOLLOW_ME == dji_pilot_fpv_flightmode_c_d) {
            this.I = 1;
        } else if (c$d.COURSE_LOCK == dji_pilot_fpv_flightmode_c_d) {
            this.I = 4;
        } else if (c$d.HOME_LOCK == dji_pilot_fpv_flightmode_c_d) {
            this.I = 3;
        } else if (c$d.PANO == dji_pilot_fpv_flightmode_c_d) {
            this.I = 5;
        }
        DJILogHelper.getInstance().LOGD("Test", "SmartMode[" + dji_pilot_fpv_flightmode_c_d + dji.pilot.usercenter.protocol.d.H, false, true);
        if (this.I != i) {
            if (i != Integer.MIN_VALUE) {
                this.D[i].setSelected(false);
                this.E[i].setSelected(false);
            }
            if (this.I != Integer.MIN_VALUE) {
                this.D[this.I].setSelected(true);
                this.E[this.I].setSelected(true);
            }
        }
    }

    private void a(int i, int i2, c$d dji_pilot_fpv_flightmode_c_d) {
        ((DJIStageView) getParent()).createStageView(i, i2, false);
        dji.pilot.fpv.flightmode.c.getInstance().a(dji_pilot_fpv_flightmode_c_d);
        a(dji_pilot_fpv_flightmode_c_d);
        dji.pilot.groundStation.a.a.getInstance(getContext()).n();
    }

    private void b(final int i, final int i2, final c$d dji_pilot_fpv_flightmode_c_d) {
        dji.pilot.groundStation.a.a.getInstance(null).a(false, new dji.midware.e.d(this) {
            final /* synthetic */ DJIMainStageView d;

            public void onSuccess(Object obj) {
                this.d.n.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        ((DJIStageView) this.a.d.getParent()).createStageView(i, i2, false);
                        dji.pilot.fpv.flightmode.c.getInstance().a(dji_pilot_fpv_flightmode_c_d);
                        this.a.d.a(dji_pilot_fpv_flightmode_c_d);
                        dji.pilot.groundStation.a.a.getInstance(this.a.d.getContext()).n();
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (this.d.J = this.d.J + 1 < 3) {
                    this.d.b(i, i2, dji_pilot_fpv_flightmode_c_d);
                }
            }
        });
    }
}
