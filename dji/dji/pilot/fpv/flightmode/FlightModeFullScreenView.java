package dji.pilot.fpv.flightmode;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.control.k;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.model.n;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.c;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.groundStation.b.f;
import dji.pilot.publics.objects.g;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.visual.a.g$d;
import dji.pilot.visual.a.g$e;
import dji.pilot.visual.a.g$f;
import dji.publics.DJIUI.DJIRelativeLayout;

public class FlightModeFullScreenView extends DJIRelativeLayout implements c$i, a {
    private static final String n = "g_config.novice_cfg.novice_func_enabled_0";
    private static final String o = "show_terrain_tracking_info";
    private ViewPager p;
    private dji.pilot.fpv.flightmode.a.a q;
    private LinearLayout r;
    private Switch s;
    private DJIStateImageView t;
    private c u;
    private volatile int v = 0;
    private dji.pilot.visual.util.a w = new dji.pilot.visual.util.a(getContext());
    private OnItemClickListener x = new OnItemClickListener(this) {
        final /* synthetic */ FlightModeFullScreenView a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.a((dji.pilot.fpv.flightmode.a.a.a.a) view.getTag(), view);
        }
    };

    public FlightModeFullScreenView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        this.p = (ViewPager) findViewById(R.id.a13);
        ViewPager viewPager = this.p;
        PagerAdapter aVar = new dji.pilot.fpv.flightmode.a.a(getContext(), this.x);
        this.q = aVar;
        viewPager.setAdapter(aVar);
        this.r = (LinearLayout) findViewById(R.id.a18);
        for (int i = 0; i != this.q.getCount(); i++) {
            View imageView = new ImageView(getContext());
            imageView.setLayoutParams(new LayoutParams(-2, -2));
            imageView.setPadding(10, 5, 10, 5);
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.flightmode_index_circle));
            this.r.addView(imageView);
        }
        a();
        this.r.getChildAt(0).setAlpha(1.0f);
        this.p.addOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ FlightModeFullScreenView a;

            {
                this.a = r1;
            }

            public void onPageSelected(int i) {
                this.a.a();
                this.a.r.getChildAt(i).setAlpha(1.0f);
                this.a.q.a();
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
        this.s = (Switch) findViewById(R.id.a17);
        this.s.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FlightModeFullScreenView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                boolean isChecked = ((Switch) view).isChecked();
                if (isChecked && DataOsdGetPushCommon.getInstance().isMotorUp()) {
                    dji.setting.ui.widget.a.a(this.a.getContext(), (int) R.string.fpv_gensetting_beginner_mode_note);
                    this.a.b();
                    return;
                }
                new DataFlycSetParams().a("g_config.novice_cfg.novice_func_enabled_0", Integer.valueOf(isChecked ? 1 : 0)).start(new d(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        dji.setting.ui.flyc.a.b().a("g_config.novice_cfg.novice_func_enabled_0");
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        DJILogHelper.getInstance().LOGD("BeginerView", "fails code = " + aVar);
                        this.a.a.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.a.b();
                            }
                        });
                    }
                });
            }
        });
        this.t = (DJIStateImageView) findViewById(R.id.a12);
        this.t.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FlightModeFullScreenView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.getStageView().stop();
            }
        });
        super.onFinishInflate();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.u = c.getInstance();
        }
    }

    private void a() {
        for (int i = 0; i != this.q.getCount(); i++) {
            this.r.getChildAt(i).setAlpha(dji.pilot.visual.a.d.c);
        }
    }

    private void b() {
        boolean z = true;
        if (dji.midware.data.manager.P3.d.read("g_config.novice_cfg.novice_func_enabled_0").value.intValue() != 1) {
            z = false;
        }
        this.s.setChecked(z);
    }

    private DJIStageView getStageView() {
        return (DJIStageView) getParent();
    }

    private void a(dji.pilot.fpv.flightmode.a.a.a.a aVar, View view) {
        c$a dji_pilot_fpv_flightmode_c_a = aVar.c;
        c$b a = this.u.a();
        this.v = 0;
        if (!dji_pilot_fpv_flightmode_c_a.equals(a)) {
            if (this.u.b() && !b.a(getContext(), false)) {
                return;
            }
            if (dji_pilot_fpv_flightmode_c_a.equals(c$b.NORMAL)) {
                g(view);
            } else if (dji_pilot_fpv_flightmode_c_a.equals(c$b.POINT)) {
                if (b.a()) {
                    f(view);
                }
            } else if (dji_pilot_fpv_flightmode_c_a.equals(c$b.TRACK)) {
                if (b.b(getContext())) {
                    d(view);
                }
            } else if (dji_pilot_fpv_flightmode_c_a.equals(c$b.TRACK_SELFIE)) {
                if (b.a(getContext())) {
                    e(view);
                }
            } else if (b.b()) {
                DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
                if (i.getInstance().c() == ProductType.A2 && !instance.isMotorUp()) {
                    getStageView().createStageView(R.layout.gs_fly_not_take_off_warning_view, 25, false);
                } else if (i.getInstance().c() == ProductType.A2 || instance.groundOrSky() == 2) {
                    dji.pilot.groundStation.a.a.d z = dji.pilot.groundStation.a.a.getInstance(null).z();
                    k i = dji.pilot.groundStation.a.a.getInstance(null).i();
                    dji.pilot.groundStation.a.a instance2 = dji.pilot.groundStation.a.a.getInstance(null);
                    if (dji_pilot_fpv_flightmode_c_a.equals(c$d.POI_AUTO)) {
                        if (z == dji.pilot.groundStation.a.a.d.NONE) {
                            if (!(i == null || i.l() == null)) {
                                i.l().g();
                            }
                            a(R.layout.gs_point_of_insterest_set_hot_point_view, 15, c$d.POI_AUTO, view);
                        } else if (z == dji.pilot.groundStation.a.a.d.POI_AUTO) {
                            getStageView().createStageView(R.layout.gs_point_of_insterest_status_view, 22, false);
                        } else {
                            this.v = 0;
                            b(R.layout.gs_point_of_insterest_set_hot_point_view, 15, c$d.POI_AUTO, view);
                        }
                    } else if (dji_pilot_fpv_flightmode_c_a.equals(c$d.FOLLOW_ME)) {
                        b bVar = new b();
                        bVar.a = DJIErrorPopView.d.b;
                        bVar.f = c.a;
                        if (k.k() == null || instance2.i().l().F() >= 10000.0f) {
                            bVar.b = R.string.gs_follow_me_device_has_no_gps;
                            dji.thirdparty.a.c.a().e(bVar);
                        } else if (instance2.i().l().F() > dji.gs.e.b.a) {
                            bVar.b = R.string.gs_follow_me_can_not_get_user_location;
                            dji.thirdparty.a.c.a().e(bVar);
                        } else {
                            if (z == dji.pilot.groundStation.a.a.d.NONE) {
                                if (!(i == null || i.l() == null)) {
                                    i.l().g();
                                    i.l().b(null, 0.0d);
                                }
                                a(R.layout.gs_follow_me_view, 3, c$d.FOLLOW_ME, view);
                            } else if (z == dji.pilot.groundStation.a.a.d.FOLLOW_ME) {
                                getStageView().createStageView(R.layout.gs_follow_me_status_view, 14, false);
                            } else {
                                this.v = 0;
                                b(R.layout.gs_follow_me_view, 3, c$d.FOLLOW_ME, view);
                            }
                            e.c(c$i.d);
                        }
                    } else if (dji_pilot_fpv_flightmode_c_a.equals(c$d.WP_AUTO)) {
                        if (z == dji.pilot.groundStation.a.a.d.NONE) {
                            if (!(i == null || i.l() == null)) {
                                i.l().g();
                            }
                            a(R.layout.gs_way_point_view, 5, c$d.WP_AUTO, view);
                        } else if (z == dji.pilot.groundStation.a.a.d.WP_AUTO) {
                            getStageView().createStageView(R.layout.gs_way_point_status_view, 21, false);
                        } else {
                            this.v = 0;
                            b(R.layout.gs_way_point_view, 5, c$d.WP_AUTO, view);
                        }
                    } else if (dji_pilot_fpv_flightmode_c_a.equals(c$d.HOME_LOCK)) {
                        if (z == dji.pilot.groundStation.a.a.d.NONE) {
                            if (!(i == null || i.l() == null)) {
                                i.l().g();
                                i.l().b(null, 0.0d);
                            }
                            a(R.layout.gs_home_lock_view, 2, c$d.HOME_LOCK, view);
                        } else if (z == dji.pilot.groundStation.a.a.d.HOME_LOCK) {
                            getStageView().createStageView(R.layout.gs_home_lock_status_view, 13, false);
                        } else {
                            this.v = 0;
                            b(R.layout.gs_home_lock_view, 2, c$d.HOME_LOCK, view);
                        }
                    } else if (dji_pilot_fpv_flightmode_c_a.equals(c$d.COURSE_LOCK)) {
                        if (z == dji.pilot.groundStation.a.a.d.NONE) {
                            if (!(i == null || i.l() == null)) {
                                i.l().g();
                                i.l().b(null, 0.0d);
                            }
                            a(R.layout.gs_course_lock_view, 1, c$d.COURSE_LOCK, view);
                        } else if (z == dji.pilot.groundStation.a.a.d.COURSE_LOCK) {
                            getStageView().createStageView(R.layout.gs_course_lock_status_view, 12, false);
                        } else {
                            this.v = 0;
                            b(R.layout.gs_course_lock_view, 1, c$d.COURSE_LOCK, view);
                        }
                    } else if (dji_pilot_fpv_flightmode_c_a.equals(c$d.TERRAIN_TRACKING)) {
                        if (z == dji.pilot.groundStation.a.a.d.NONE) {
                            if (!(i == null || i.l() == null)) {
                                i.l().g();
                                i.l().b(null, 0.0d);
                            }
                            a(view);
                        } else if (z == dji.pilot.groundStation.a.a.d.TERRAIN_TRACKING) {
                            getStageView().createStageView(R.layout.gs_terrain_tracking_status_view, 32, false);
                        } else {
                            this.v = 0;
                            a(view);
                        }
                    } else if (dji_pilot_fpv_flightmode_c_a.equals(c$d.PANO)) {
                        a(R.layout.gs_way_point_view, 5, c$d.PANO, view);
                    }
                } else {
                    getStageView().createStageView(R.layout.gs_fly_not_take_off_warning_view, 25, false);
                }
            }
        }
    }

    private void a(final View view) {
        if (g.b(getContext(), o, true)) {
            f fVar = new f(getContext());
            fVar.a((int) R.drawable.gs_terrain_tracking_help_image);
            fVar.a(getResources().getString(R.string.gs_terrain_tracking_help_title));
            fVar.b(getResources().getString(R.string.gs_terrain_tracking_help_desc));
            fVar.c((int) R.string.gs_terrain_tracking_help_i_know);
            fVar.b(new DialogInterface.OnClickListener(this) {
                final /* synthetic */ FlightModeFullScreenView b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    this.b.a(R.layout.gs_terrain_tracking_view, 31, c$d.TERRAIN_TRACKING, view);
                }
            });
            fVar.b((int) R.string.gs_terrain_tracking_help_dont_show_again);
            fVar.a(new DialogInterface.OnClickListener(this) {
                final /* synthetic */ FlightModeFullScreenView b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    g.a(this.b.getContext(), FlightModeFullScreenView.o, false);
                    this.b.a(R.layout.gs_terrain_tracking_view, 31, c$d.TERRAIN_TRACKING, view);
                }
            });
            fVar.show();
            return;
        }
        a(R.layout.gs_terrain_tracking_view, 31, c$d.TERRAIN_TRACKING, view);
    }

    private void a(int i, int i2, c$d dji_pilot_fpv_flightmode_c_d, View view) {
        view.setSelected(true);
        ((DJIStageView) getParent()).createStageView(i, i2, false);
        c.getInstance().a(c$b.SMART);
        dji.pilot.visual.a.c.getInstance().g();
        dji.thirdparty.a.c.a().e(g$d.EXIT_VISUAL);
        c.getInstance().a(dji_pilot_fpv_flightmode_c_d);
        dji.pilot.groundStation.a.a.getInstance(getContext()).n();
    }

    private void b(int i, int i2, c$d dji_pilot_fpv_flightmode_c_d, View view) {
        b(view);
        final View view2 = view;
        final int i3 = i;
        final int i4 = i2;
        final c$d dji_pilot_fpv_flightmode_c_d2 = dji_pilot_fpv_flightmode_c_d;
        dji.pilot.groundStation.a.a.getInstance(null).a(false, new d(this) {
            final /* synthetic */ FlightModeFullScreenView e;

            public void onSuccess(Object obj) {
                this.e.getStageView().post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass10 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.e.c(view2);
                        this.a.e.a(i3, i4, dji_pilot_fpv_flightmode_c_d2, view2);
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (this.e.v = this.e.v + 1 < 3) {
                    this.e.b(i3, i4, dji_pilot_fpv_flightmode_c_d2, view2);
                }
            }
        });
    }

    private void b(View view) {
        dji.thirdparty.a.c.a().e(n.a.b);
        view.setEnabled(false);
        view.setAlpha(0.3f);
        this.t.setEnabled(false);
        view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.g));
    }

    private void c(View view) {
        this.v = 0;
        dji.thirdparty.a.c.a().e(n.a.c);
        view.setEnabled(true);
        view.setAlpha(1.0f);
        this.t.setEnabled(true);
        view.clearAnimation();
    }

    private void d(View view) {
        a(view, false, new Runnable(this) {
            final /* synthetic */ FlightModeFullScreenView a;

            {
                this.a = r1;
            }

            public void run() {
                dji.pilot.groundStation.a.a.getInstance(null);
                this.a.getStageView().stop();
                this.a.u.a(c$b.TRACK);
                dji.pilot.visual.a.c.getInstance().a(g$f.WORKING);
                dji.pilot.visual.a.c.getInstance().a(g$e.TRACK_MODE);
                dji.thirdparty.a.c.a().e(g$d.ENTER_VISUAL);
            }
        }, 50, (int) R.string.fpv_flight_mode_switch_failed);
    }

    private void e(View view) {
        a(view, false, new Runnable(this) {
            final /* synthetic */ FlightModeFullScreenView a;

            {
                this.a = r1;
            }

            public void run() {
                dji.pilot.groundStation.a.a.getInstance(null);
                this.a.getStageView().stop();
                b.a(this.a.getContext(), new Runnable(this) {
                    final /* synthetic */ AnonymousClass12 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.c();
                    }
                });
            }
        }, 50, (int) R.string.fpv_flight_mode_switch_failed);
    }

    private void c() {
        this.u.a(c$b.TRACK_SELFIE);
        dji.pilot.visual.a.c.getInstance().a(g$f.WORKING);
        dji.pilot.visual.a.c.getInstance().a(g$e.TRACK_MODE);
    }

    private void f(View view) {
        a(view, false, new Runnable(this) {
            final /* synthetic */ FlightModeFullScreenView a;

            {
                this.a = r1;
            }

            public void run() {
                dji.pilot.groundStation.a.a.getInstance(null);
                this.a.getStageView().stop();
                this.a.u.a(c$b.POINT);
                dji.pilot.visual.a.c.getInstance().a(g$f.WORKING);
                dji.pilot.visual.a.c.getInstance().a(g$e.POINT_MODE);
                dji.thirdparty.a.c.a().e(g$d.ENTER_VISUAL);
            }
        }, 50, (int) R.string.fpv_flight_mode_switch_failed);
    }

    private void g(View view) {
        a(view, false, new Runnable(this) {
            final /* synthetic */ FlightModeFullScreenView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.u.a(c$b.NORMAL);
                dji.pilot.visual.a.c.getInstance().g();
                dji.thirdparty.a.c.a().e(g$d.EXIT_VISUAL);
                this.a.getStageView().stop();
            }
        }, 50, (int) R.string.fpv_flight_mode_switch_failed);
    }

    private void a(View view, boolean z, Runnable runnable, long j, int i) {
        b(view);
        final View view2 = view;
        final Runnable runnable2 = runnable;
        final long j2 = j;
        final boolean z2 = z;
        final int i2 = i;
        dji.pilot.groundStation.a.a.getInstance(null).a(z, 2, new d(this) {
            final /* synthetic */ FlightModeFullScreenView f;

            public void onSuccess(Object obj) {
                this.f.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.f.c(view2);
                        view2.setSelected(true);
                        runnable2.run();
                    }
                }, j2);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (aVar == dji.midware.data.config.P3.a.a || this.f.v = this.f.v + 1 >= 3) {
                    this.f.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.f.c(view2);
                            b bVar = new b();
                            bVar.a = DJIErrorPopView.d.b;
                            bVar.f = c.a;
                            bVar.b = i2;
                            dji.thirdparty.a.c.a().e(bVar);
                        }
                    });
                } else {
                    this.f.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.f.a(view2, z2, runnable2, j2, i2);
                        }
                    });
                }
            }
        });
    }

    public void dispatchOnStart() {
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
        b();
        dji.setting.ui.flyc.a.b().a("g_config.novice_cfg.novice_func_enabled_0");
    }

    public void dispatchOnStop() {
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
    }

    public void dispatchOnResume() {
        this.q.a();
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }

    public void onEventMainThread(dji.setting.ui.flyc.a.a aVar) {
        if (aVar.a.equals("g_config.novice_cfg.novice_func_enabled_0")) {
            b();
        }
    }

    public void onEventMainThread(c$b dji_pilot_fpv_flightmode_c_b) {
        this.q.a();
    }
}
