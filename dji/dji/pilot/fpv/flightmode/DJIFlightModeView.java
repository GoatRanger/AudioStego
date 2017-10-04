package dji.pilot.fpv.flightmode;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.model.n;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.visual.a.g$d;
import dji.pilot.visual.a.g$e;
import dji.pilot.visual.a.g$f;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJIFlightModeView extends DJIRelativeLayout implements OnClickListener, a {
    public static final long a = 50;
    public static boolean b = false;
    public static final int c = 3;
    private static final float d = 0.3f;
    private static final long e = 100;
    private static final int f = 0;
    private static final int g = 1;
    private static final int h = 2;
    private static final int i = 3;
    private static final int[] j = new int[]{R.id.a0n, R.id.a0q, R.id.a0t, R.id.a0w};
    private static final int[] k = new int[]{R.id.a0o, R.id.a0r, R.id.a0u, R.id.a0x};
    private static final int[] l = new int[]{R.id.a0p, R.id.a0s, R.id.a0v, R.id.a0y};
    private static final boolean[] m = new boolean[]{true, true, true, true};
    private static final c$b[] n = new c$b[]{c$b.POINT, c$b.TRACK, c$b.NORMAL, c$b.SMART};
    private int A = Integer.MIN_VALUE;
    private volatile int o = 0;
    private DJITextView p = null;
    private DJIImageView q = null;
    private DJILinearLayout r = null;
    private DJILinearLayout s;
    private final DJILinearLayout[] t = new DJILinearLayout[j.length];
    private final DJIImageView[] u = new DJIImageView[j.length];
    private final DJITextView[] v = new DJITextView[j.length];
    private Animation[] w = null;
    private DJIStageView x = null;
    private ProductType y = ProductType.OTHER;
    private c z = null;

    public DJIFlightModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.z = c.getInstance();
            this.p = (DJITextView) findViewById(R.id.a0z);
            this.q = (DJIImageView) findViewById(R.id.a10);
            this.r = (DJILinearLayout) findViewById(R.id.a0m);
            this.q.setOnClickListener(this);
            int i = DJIBaseActivity.screenWidth / 5;
            for (int i2 = 0; i2 < j.length; i2++) {
                this.t[i2] = (DJILinearLayout) findViewById(j[i2]);
                this.u[i2] = (DJIImageView) findViewById(k[i2]);
                this.v[i2] = (DJITextView) findViewById(l[i2]);
                this.u[i2].setOnClickListener(this);
                LayoutParams layoutParams = this.t[i2].getLayoutParams();
                layoutParams.width = i;
                this.t[i2].setLayoutParams(layoutParams);
            }
        }
    }

    private DJIStageView getStageView() {
        if (this.x == null) {
            this.x = (DJIStageView) getParent();
        }
        return this.x;
    }

    private c$b a(int i) {
        c$b dji_pilot_fpv_flightmode_c_b = c$b.NORMAL;
        int i2 = 0;
        while (i2 < k.length) {
            if (k[i2] == i) {
                break;
            }
            i2++;
        }
        i2 = -1;
        if (i2 != -1) {
            return n[i2];
        }
        return dji_pilot_fpv_flightmode_c_b;
    }

    private int a(c$b dji_pilot_fpv_flightmode_c_b) {
        for (int i = 0; i < n.length; i++) {
            if (n[i] == dji_pilot_fpv_flightmode_c_b) {
                return i;
            }
        }
        return Integer.MIN_VALUE;
    }

    public void onEventMainThread(c$b dji_pilot_fpv_flightmode_c_b) {
        int i = this.A;
        this.A = a(dji_pilot_fpv_flightmode_c_b);
        if (this.A != i) {
            if (Integer.MIN_VALUE != this.A) {
                this.u[this.A].setSelected(true);
                this.v[this.A].setSelected(true);
            }
            if (Integer.MIN_VALUE != i) {
                this.u[i].setSelected(false);
                this.v[i].setSelected(false);
            }
        }
    }

    private void b(int i) {
        this.o = 0;
        c.a().e(n.a.b);
        for (int i2 = 0; i2 < k.length; i2++) {
            this.u[i2].setEnabled(false);
            if (i != i2) {
                this.u[i2].setAlpha(d);
            }
        }
        this.q.setEnabled(false);
        this.u[i].startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.g));
    }

    private void c(int i) {
        int i2 = 0;
        this.o = 0;
        c.a().e(n.a.c);
        while (i2 < k.length) {
            this.u[i2].setEnabled(true);
            if (i != i2) {
                this.u[i2].setAlpha(1.0f);
            }
            i2++;
        }
        this.q.setEnabled(true);
        this.u[i].clearAnimation();
    }

    private void a(int i, boolean z, Runnable runnable, long j, int i2) {
        b(i);
        final int i3 = i;
        final Runnable runnable2 = runnable;
        final long j2 = j;
        final boolean z2 = z;
        final int i4 = i2;
        dji.pilot.groundStation.a.a.getInstance(null).a(z, 2, new d(this) {
            final /* synthetic */ DJIFlightModeView f;

            public void onSuccess(Object obj) {
                this.f.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.f.c(i3);
                        runnable2.run();
                    }
                }, j2);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (aVar == dji.midware.data.config.P3.a.a || this.f.o = this.f.o + 1 >= 3) {
                    this.f.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.f.c(i3);
                            b bVar = new b();
                            bVar.a = DJIErrorPopView.d.b;
                            bVar.f = DJIErrorPopView.c.a;
                            bVar.b = i4;
                            c.a().e(bVar);
                        }
                    });
                } else {
                    this.f.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.f.a(i3, z2, runnable2, j2, i4);
                        }
                    });
                }
            }
        });
    }

    private void a() {
        a(1, false, new Runnable(this) {
            final /* synthetic */ DJIFlightModeView a;

            {
                this.a = r1;
            }

            public void run() {
                dji.pilot.groundStation.a.a.getInstance(null);
                this.a.getStageView().stop();
                this.a.z.a(c$b.TRACK);
                dji.pilot.visual.a.c.getInstance().a(g$f.WORKING);
                dji.pilot.visual.a.c.getInstance().a(g$e.TRACK_MODE);
                c.a().e(g$d.ENTER_VISUAL);
            }
        }, 50, R.string.fpv_flight_mode_switch_failed);
    }

    private void b() {
        a(0, false, new Runnable(this) {
            final /* synthetic */ DJIFlightModeView a;

            {
                this.a = r1;
            }

            public void run() {
                dji.pilot.groundStation.a.a.getInstance(null);
                this.a.getStageView().stop();
                this.a.z.a(c$b.POINT);
                dji.pilot.visual.a.c.getInstance().a(g$f.WORKING);
                dji.pilot.visual.a.c.getInstance().a(g$e.POINT_MODE);
                c.a().e(g$d.ENTER_VISUAL);
            }
        }, 50, R.string.fpv_flight_mode_switch_failed);
    }

    private void c() {
        a(3, true, new Runnable(this) {
            final /* synthetic */ DJIFlightModeView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.z.a(c$b.SMART);
                dji.pilot.visual.a.c.getInstance().g();
                c.a().e(g$d.EXIT_VISUAL);
                this.a.getStageView().createStageView(R.layout.gs_main_view, 0, true);
            }
        }, 50, R.string.fpv_flight_mode_switch_failed);
    }

    private void d() {
        a(2, false, new Runnable(this) {
            final /* synthetic */ DJIFlightModeView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.z.a(c$b.NORMAL);
                dji.pilot.visual.a.c.getInstance().g();
                c.a().e(g$d.EXIT_VISUAL);
                this.a.getStageView().stop();
            }
        }, 50, R.string.fpv_flight_mode_switch_failed);
    }

    private void b(c$b dji_pilot_fpv_flightmode_c_b) {
        if (dji_pilot_fpv_flightmode_c_b == c$b.NORMAL) {
            d();
        } else if (dji_pilot_fpv_flightmode_c_b == c$b.POINT) {
            b();
        } else if (dji_pilot_fpv_flightmode_c_b == c$b.TRACK) {
            a();
        } else if (dji_pilot_fpv_flightmode_c_b == c$b.SMART) {
            c();
        }
    }

    private void c(c$b dji_pilot_fpv_flightmode_c_b) {
        b(dji_pilot_fpv_flightmode_c_b);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.a10) {
            getStageView().stop();
            return;
        }
        c$b a = a(id);
        if (this.z.a() == a && id != R.id.a0x) {
            getStageView().stop();
        } else if (a == c$b.NORMAL) {
            c(c$b.NORMAL);
        } else if (c$b.TRACK == a) {
            if (b.b(getContext())) {
                c(c$b.TRACK);
            }
        } else if (c$b.POINT == a) {
            if (b.a()) {
                c(c$b.POINT);
            }
        } else if (c$b.SMART == a && b.b()) {
            c(c$b.SMART);
        }
    }

    private void a(ViewGroup viewGroup) {
        int i = DJIBaseActivity.screenWidth / 5;
        int childCount = viewGroup.getChildCount();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            if (viewGroup.getChildAt(i3).getVisibility() != 8) {
                i2++;
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

    public void onEventMainThread(ProductType productType) {
        if (this.y != productType) {
            this.y = productType;
            if (dji.pilot.fpv.d.b.n(productType)) {
                this.t[0].show();
                this.t[1].show();
                m[0] = true;
                m[1] = true;
            } else {
                this.t[0].go();
                this.t[1].go();
                m[0] = false;
                m[1] = false;
            }
            a(this.r);
        }
    }

    public void dispatchOnStart() {
        onEventMainThread(this.z.a());
        onEventMainThread(i.getInstance().c());
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        if (b) {
            int childCount = this.r.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (m[i]) {
                    this.t[i].go();
                }
            }
            postDelayed(new Runnable(this) {
                final /* synthetic */ DJIFlightModeView a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.e();
                }
            }, 150);
            b = false;
        }
    }

    private void e() {
        int i = 0;
        int childCount = this.r.getChildCount();
        if (this.w == null) {
            this.w = new Animation[childCount];
            for (int i2 = 0; i2 < childCount; i2++) {
                this.w[i2] = AnimationUtils.loadAnimation(getContext(), R.anim.ad);
            }
        }
        while (i < childCount) {
            if (m[i]) {
                postDelayed(new Runnable(this) {
                    final /* synthetic */ DJIFlightModeView b;

                    public void run() {
                        this.b.r.getChildAt(i).setVisibility(0);
                        this.b.r.getChildAt(i).startAnimation(this.b.w[i]);
                    }
                }, ((long) i) * e);
            }
            i++;
        }
    }

    public void dispatchOnStop() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
