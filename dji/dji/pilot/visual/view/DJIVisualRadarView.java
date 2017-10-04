package dji.pilot.visual.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import dji.gs.c.e;
import dji.logic.g.a;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance.SensorType;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.DJIGenSettingDataManager$b;
import dji.pilot.fpv.d.b;
import dji.pilot.visual.a.c;
import dji.pilot.visual.a.d;
import dji.pilot.visual.a.g;
import dji.pilot.visual.a.g$b;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIVisualRadarView extends DJIRelativeLayout implements g {
    private static final int a = 4;
    private static final long b = 2000;
    private static final int[] c = new int[]{200, 300, 400, 600, e.g, 1000};
    private static final int[] d = new int[]{R.drawable.vision_radar_dir_0_level_6, R.drawable.vision_radar_dir_0_level_5, R.drawable.vision_radar_dir_0_level_4, R.drawable.vision_radar_dir_0_level_3, R.drawable.vision_radar_dir_0_level_2, R.drawable.vision_radar_dir_0_level_1, R.drawable.vision_radar_dir_0_level_0};
    private static final int[] e = new int[]{R.drawable.vision_radar_dir_1_level_6, R.drawable.vision_radar_dir_1_level_5, R.drawable.vision_radar_dir_1_level_4, R.drawable.vision_radar_dir_1_level_3, R.drawable.vision_radar_dir_1_level_2, R.drawable.vision_radar_dir_1_level_1, R.drawable.vision_radar_dir_1_level_0};
    private static final int[] f = new int[]{R.drawable.vision_radar_dir_2_level_6, R.drawable.vision_radar_dir_2_level_5, R.drawable.vision_radar_dir_2_level_4, R.drawable.vision_radar_dir_2_level_3, R.drawable.vision_radar_dir_2_level_2, R.drawable.vision_radar_dir_2_level_1, R.drawable.vision_radar_dir_2_level_0};
    private static final int[] g = new int[]{R.drawable.vision_radar_dir_3_level_6, R.drawable.vision_radar_dir_3_level_5, R.drawable.vision_radar_dir_3_level_4, R.drawable.vision_radar_dir_3_level_3, R.drawable.vision_radar_dir_3_level_2, R.drawable.vision_radar_dir_3_level_1, R.drawable.vision_radar_dir_3_level_0};
    private static final int[][] h = new int[][]{d, e, f, g};
    private final Runnable A = new Runnable(this) {
        final /* synthetic */ DJIVisualRadarView a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.y.n()) {
                this.a.s.go();
            }
        }
    };
    private boolean B = true;
    private DJIRelativeLayout i = null;
    private final DJIImageView[] j = new DJIImageView[4];
    private DJITextView k = null;
    private DJIRelativeLayout s = null;
    private DJITextView t = null;
    private final int[] u = new int[4];
    private AnimationListener v = null;
    private Animation w = null;
    private int x = Integer.MAX_VALUE;
    private final c y = c.getInstance();
    private ProductType z = ProductType.OTHER;

    public DJIVisualRadarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void hideByOuter() {
        this.B = false;
        go();
    }

    public void showByOuter() {
        this.B = true;
        if (c()) {
            show();
        }
    }

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
        }
    }

    public void go() {
        if (getVisibility() != 8) {
            setVisibility(8);
        }
    }

    public void onEventMainThread(DJIGenSettingDataManager$b dJIGenSettingDataManager$b) {
        if (dJIGenSettingDataManager$b == DJIGenSettingDataManager$b.PARAMETER_UNIT_CHANGED && this.k.getVisibility() == 0 && this.x <= c[c.length - 1]) {
            a();
        }
    }

    private void a() {
        float f = ((float) (this.x / 50)) * d.c;
        if (DJIGenSettingDataManager.getInstance().v() == 0) {
            int b = (int) DJIGenSettingDataManager.getInstance().b(f);
            this.k.setText(String.format("%d FT", new Object[]{Integer.valueOf(b)}));
            return;
        }
        this.k.setText(getResources().getString(R.string.fpv_metric, new Object[]{Float.valueOf(f)}));
    }

    public void updateRadarValues(int[] iArr) {
        boolean[] a = a(iArr);
        if (!a[0]) {
            int i = Integer.MAX_VALUE;
            int i2 = 0;
            while (i2 < iArr.length && i2 < 4) {
                this.j[i2].setBackgroundResource(a(i2, iArr[i2]));
                if (iArr[i2] < i) {
                    i = iArr[i2];
                }
                i2++;
            }
            if (iArr.length < 4) {
                for (i2 = iArr.length; i2 < 4; i2++) {
                    this.j[i2].setBackgroundResource(0);
                }
            }
            if (a[1]) {
                this.k.go();
                this.i.startAnimation(this.w);
                return;
            }
            this.i.clearAnimation();
            this.x = i;
            this.k.show();
            a();
            this.i.show();
        }
    }

    public void onEventMainThread(p pVar) {
        a(i.getInstance().c(), pVar == p.b);
    }

    public void onEventMainThread(o oVar) {
        a(i.getInstance().c(), oVar == o.b);
    }

    public void onEventMainThread(ProductType productType) {
        a(productType, ServiceManager.getInstance().isRemoteOK());
    }

    private void a(ProductType productType, boolean z) {
        if (!z) {
            this.z = ProductType.OTHER;
            this.x = Integer.MAX_VALUE;
            go();
        } else if (this.z != productType) {
            this.z = productType;
            if (c()) {
                show();
                return;
            }
            this.x = Integer.MAX_VALUE;
            go();
        }
    }

    private boolean b() {
        MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
        return (!b.n(this.z) || mode == MODE.PLAYBACK || mode == MODE.NEW_PLAYBACK || mode == MODE.DOWNLOAD) ? false : true;
    }

    private boolean c() {
        g.c m = this.y.m();
        return b() && ((m == g.c.e || m == g.c.c) && this.B);
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        MODE mode = dataCameraGetPushStateInfo.getMode();
        if (mode == MODE.PLAYBACK || mode == MODE.NEW_PLAYBACK || mode == MODE.DOWNLOAD) {
            go();
        } else if (c()) {
            show();
        }
    }

    public void onEventMainThread(a.b bVar) {
        if (a.b.a == bVar) {
            g.c m = this.y.m();
            if (b() && m == g.c.e && this.B) {
                updateRadarValues(a.getInstance().a(SensorType.Front).getObserveValues());
            }
        }
    }

    public void onEventMainThread(DataEyeGetPushFrontAvoidance dataEyeGetPushFrontAvoidance) {
        g.c m = this.y.m();
        if (b() && m == g.c.e && this.B) {
            updateRadarValues(dataEyeGetPushFrontAvoidance.getObserveValues());
        }
    }

    public void onEventMainThread(g$b dji_pilot_visual_a_g_b) {
        removeCallbacks(this.A);
        if (dji_pilot_visual_a_g_b.c == g.c.e) {
            if (c()) {
                show();
            }
            this.s.go();
            this.i.clearAnimation();
            this.i.show();
            onEventMainThread(a.getInstance().a(SensorType.Front));
        } else if (dji_pilot_visual_a_g_b.c == g.c.c) {
            if (c()) {
                show();
            }
            dji.thirdparty.afinal.c.c.b(this.u, c[c.length - 1]);
            for (int i = 0; i < 4; i++) {
                this.j[i].setBackgroundResource(0);
            }
            this.i.clearAnimation();
            this.i.go();
            this.s.show();
            this.t.setText(this.y.r());
            postDelayed(this.A, 2000);
        } else {
            this.i.clearAnimation();
            this.i.go();
            this.s.go();
        }
    }

    private int a(int i, int i2) {
        int i3 = 0;
        while (i3 < c.length && i2 > c[i3]) {
            i3++;
        }
        return h[i][i3];
    }

    private boolean[] a(int[] iArr) {
        boolean[] zArr = new boolean[]{false, true};
        zArr[0] = dji.thirdparty.afinal.c.c.a(this.u, iArr);
        if (!zArr[0]) {
            System.arraycopy(iArr, 0, this.u, 0, 4);
            for (int i : iArr) {
                if (i <= c[c.length - 1]) {
                    zArr[1] = false;
                }
            }
        }
        return zArr;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            int[] iArr = new int[]{R.id.d__, R.id.d_a, R.id.d_b, R.id.d_c};
            this.i = (DJIRelativeLayout) findViewById(R.id.d_9);
            for (int i = 0; i < 4; i++) {
                this.j[i] = (DJIImageView) findViewById(iArr[i]);
            }
            this.k = (DJITextView) findViewById(R.id.d_d);
            this.s = (DJIRelativeLayout) findViewById(R.id.d_e);
            this.t = (DJITextView) findViewById(R.id.d_h);
            this.v = new AnimationListener(this) {
                final /* synthetic */ DJIVisualRadarView a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    this.a.i.go();
                }
            };
            this.w = d();
            this.w.setAnimationListener(this.v);
        }
    }

    private Animation d() {
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setInterpolator(new AccelerateInterpolator(2.0f));
        return alphaAnimation;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!dji.thirdparty.a.c.a().c(this)) {
                dji.thirdparty.a.c.a().a(this);
            }
            onEventMainThread(i.getInstance().c());
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
