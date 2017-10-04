package dji.device.camera.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.camera.a.a;
import dji.device.camera.a.c;
import dji.device.camera.a.e;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.DJIRadioGroup;
import dji.device.common.view.DJIRadioGroup.b;
import dji.device.widget.LonganPopWarnView;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJILinearLayout;

public class DJILevel2MenuViewLongan extends DJILinearLayout implements b {
    private static final int p = 16384;
    LayoutParams a;
    DJIRadioGroup b;
    DJIRadioGroup c;
    DJIRadioGroup d;
    DJIRadioGroup e;
    DJIRadioGroup f;
    DJIRadioGroup g = null;
    int h = R.id.longan_handle_mode_level2_single_0s;
    int i = R.id.longan_handle_mode_level2_interval_3;
    a j = a.getInstance();
    c k = c.getInstance();
    e l = e.getInstance();
    boolean m = false;
    private final String n = "DJILevel2MenuViewLongan";
    private Animation o;
    private View q = null;
    private int r = 1;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] c = new int[o.values().length];

        static {
            d = new int[m.values().length];
            try {
                d[m.HIDE_ALL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                d[m.SHOW_MENU.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                d[m.HIDE_MENU.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                c[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                c[o.a.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            b = new int[c.a.values().length];
            try {
                b[c.a.SINGLE_0s.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                b[c.a.SINGLE_5s.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                b[c.a.SINGLE_10s.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                b[c.a.SINGLE_HDR.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
            try {
                b[c.a.BURST_3.ordinal()] = 5;
            } catch (NoSuchFieldError e10) {
            }
            try {
                b[c.a.BURST_5.ordinal()] = 6;
            } catch (NoSuchFieldError e11) {
            }
            try {
                b[c.a.BURST_7.ordinal()] = 7;
            } catch (NoSuchFieldError e12) {
            }
            try {
                b[c.a.AEB_3.ordinal()] = 8;
            } catch (NoSuchFieldError e13) {
            }
            try {
                b[c.a.AEB_5.ordinal()] = 9;
            } catch (NoSuchFieldError e14) {
            }
            try {
                b[c.a.PANO_AUTO.ordinal()] = 10;
            } catch (NoSuchFieldError e15) {
            }
            try {
                b[c.a.PANO_SELFIE.ordinal()] = 11;
            } catch (NoSuchFieldError e16) {
            }
            try {
                b[c.a.PANO_AUTO180.ordinal()] = 12;
            } catch (NoSuchFieldError e17) {
            }
            try {
                b[c.a.PANO_MANU.ordinal()] = 13;
            } catch (NoSuchFieldError e18) {
            }
            try {
                b[c.a.PANO_SECTORIAL.ordinal()] = 14;
            } catch (NoSuchFieldError e19) {
            }
            try {
                b[c.a.INTERVAL_3s.ordinal()] = 15;
            } catch (NoSuchFieldError e20) {
            }
            try {
                b[c.a.INTERVAL_5s.ordinal()] = 16;
            } catch (NoSuchFieldError e21) {
            }
            try {
                b[c.a.INTERVAL_10s.ordinal()] = 17;
            } catch (NoSuchFieldError e22) {
            }
            try {
                b[c.a.INTERVAL_30s.ordinal()] = 18;
            } catch (NoSuchFieldError e23) {
            }
            try {
                b[c.a.TIMELAPSE.ordinal()] = 19;
            } catch (NoSuchFieldError e24) {
            }
            a = new int[c.b.values().length];
            try {
                a[c.b.SINGLE.ordinal()] = 1;
            } catch (NoSuchFieldError e25) {
            }
            try {
                a[c.b.BURST.ordinal()] = 2;
            } catch (NoSuchFieldError e26) {
            }
            try {
                a[c.b.AEB.ordinal()] = 3;
            } catch (NoSuchFieldError e27) {
            }
            try {
                a[c.b.PANO.ordinal()] = 4;
            } catch (NoSuchFieldError e28) {
            }
            try {
                a[c.b.INTERVAL.ordinal()] = 5;
            } catch (NoSuchFieldError e29) {
            }
            try {
                a[c.b.TIMELAPSE.ordinal()] = 6;
            } catch (NoSuchFieldError e30) {
            }
            try {
                a[c.b.NOT_PHOTOING.ordinal()] = 7;
            } catch (NoSuchFieldError e31) {
            }
        }
    }

    public DJILevel2MenuViewLongan(Context context) {
        super(context);
    }

    public DJILevel2MenuViewLongan(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJILevel2MenuViewLongan(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            init();
            DJILogHelper.getInstance().LOGD("init log", "L2 init");
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.thirdparty.a.c.a().d(this);
    }

    public void init() {
        this.a = (LayoutParams) getLayoutParams();
        dji.thirdparty.a.c.a().a(this);
        this.b = (DJIRadioGroup) findViewById(R.id.longan_radiogroup_single);
        this.b.setOnCheckedChangeListener(this);
        this.o = AnimationUtils.loadAnimation(getContext(), R.anim.longan_menu_fade_in);
        this.o.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ DJILevel2MenuViewLongan a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.setVisibility(0);
            }
        });
        resetView();
        if (DataCameraGetPushShotParams.getInstance().isGetted()) {
            onEventMainThread(this.k.c());
            onEventMainThread(this.k);
            onEventMainThread(this.l);
            onEventMainThread(DataCameraGetPushShotParams.getInstance());
        }
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        resetView();
    }

    public void resetView() {
        if (getResources().getConfiguration().orientation == 2) {
            setOrientation(1);
            this.a.addRule(2, 0);
            this.a.addRule(1, R.id.longan_level1_menu_layout);
            this.a.width = -2;
            this.a.height = -1;
            setHorizontalGravity(17);
            this.a.setMargins(2, 0, 0, 0);
        } else {
            setOrientation(0);
            this.a.addRule(1, 0);
            this.a.addRule(2, R.id.longan_level1_menu_layout);
            this.a.height = -2;
            this.a.width = -1;
            setVerticalGravity(17);
            this.a.setMargins(0, 0, 0, 2);
        }
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).getVisibility() == 0) {
                ((DJIRadioGroup) getChildAt(i)).setOrientation(getOrientation());
            }
        }
    }

    public void handleViewChange() {
        if (getVisibility() == 0) {
            setVisibility(4);
        } else {
            setVisibility(0);
        }
    }

    public void setAnimationVisibility(int i) {
        if (i == 0) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
    }

    public void show(boolean z) {
        if (z) {
            setAnimationVisibility(0);
        } else {
            setAnimationVisibility(4);
        }
    }

    public void onEventMainThread(c.b bVar) {
        if (this.j.d() != a.a.RECORD) {
            if (this.k.c() != c.b.BURST || this.c == null || !this.c.isShown()) {
                a();
                a(bVar, true);
            }
        }
    }

    private void a(c.b bVar, boolean z) {
        int i;
        switch (bVar) {
            case SINGLE:
                this.b.setVisibility(0);
                this.g = this.b;
                a(DataCameraGetPushShotParams.getInstance().getImageFormat());
                i = R.id.longan_handle_mode_level2_single_0s;
                this.b.clearCheck();
                break;
            case BURST:
                this.c = a(this.c, R.id.longan_radiogroup_multiple_vs);
                i = R.id.longan_handle_mode_level2_burst_3;
                this.g = this.c;
                break;
            case AEB:
                this.c = a(this.c, R.id.longan_radiogroup_multiple_vs);
                this.g = this.c;
                i = 0;
                break;
            case PANO:
                this.d = a(this.d, R.id.longan_radiogroup_pano_vs);
                int i2 = R.id.longan_handle_mode_level2_pano_auto;
                this.g = this.d;
                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.longan_pano_auto180_ly);
                RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.longan_pano_m_ly);
                RelativeLayout relativeLayout3 = (RelativeLayout) findViewById(R.id.longan_pano_wide_ly);
                CameraType b = i.getInstance().b();
                if (!dji.logic.f.b.j(b)) {
                    i = i2;
                    break;
                }
                if (b != CameraType.DJICameraTypeFC350 || dji.device.common.b.getInstance().c(0) >= dji.device.common.b.a) {
                    relativeLayout.setVisibility(0);
                } else {
                    relativeLayout.setVisibility(8);
                }
                if (dji.device.common.b.getInstance().c(0) <= dji.device.common.b.b) {
                    relativeLayout2.setVisibility(8);
                } else {
                    relativeLayout2.setVisibility(0);
                }
                if (dji.device.common.b.getInstance().c(0) > dji.device.common.b.c) {
                    relativeLayout3.setVisibility(0);
                    i = i2;
                    break;
                }
                relativeLayout3.setVisibility(8);
                i = i2;
                break;
                break;
            case INTERVAL:
                this.e = a(this.e, R.id.longan_radiogroup_interval_vs);
                this.g = this.e;
                a(DataCameraGetPushShotParams.getInstance().getImageFormat());
                i = this.i;
                break;
            case TIMELAPSE:
                if (this.g == null) {
                    i = 0;
                    break;
                }
                this.g.clearCheck();
                i = 0;
                break;
            case NOT_PHOTOING:
                a();
                i = 0;
                break;
            default:
                this.b.setVisibility(0);
                i = R.id.longan_handle_mode_level2_single_0s;
                this.g = this.b;
                break;
        }
        if (this.g != null) {
            this.g.setOnCheckedChangeListener(this);
            if (i != 0 && z) {
                this.g.check(i);
            }
            this.g.setOrientation(getOrientation());
        }
    }

    public void onEventMainThread(c cVar) {
        a();
        a(cVar.c(), false);
        if (this.j.d() != a.a.RECORD) {
            switch (cVar.d()) {
                case SINGLE_0s:
                    this.b.check(R.id.longan_handle_mode_level2_single_0s);
                    return;
                case SINGLE_5s:
                    this.b.check(R.id.longan_handle_mode_level2_single_5s);
                    return;
                case SINGLE_10s:
                    this.b.check(R.id.longan_handle_mode_level2_single_10s);
                    return;
                case SINGLE_HDR:
                    this.b.check(R.id.longan_handle_mode_level2_single_hdr);
                    return;
                case BURST_3:
                    this.c.check(R.id.longan_handle_mode_level2_burst_3);
                    return;
                case BURST_5:
                    this.c.check(R.id.longan_handle_mode_level2_burst_5);
                    return;
                case BURST_7:
                    this.c.check(R.id.longan_handle_mode_level2_burst_7);
                    return;
                case AEB_3:
                    this.c.check(R.id.longan_handle_mode_level2_aeb_3);
                    return;
                case AEB_5:
                    this.c.check(R.id.longan_handle_mode_level2_aeb_5);
                    return;
                case PANO_AUTO:
                    this.d.check(R.id.longan_handle_mode_level2_pano_auto);
                    return;
                case PANO_SELFIE:
                    this.d.check(R.id.longan_handle_mode_level2_pano_selfie);
                    return;
                case PANO_AUTO180:
                    this.d.check(R.id.longan_handle_mode_level2_pano_auto_180);
                    return;
                case PANO_MANU:
                    this.d.check(R.id.longan_handle_mode_level2_pano_m);
                    return;
                case PANO_SECTORIAL:
                    this.d.check(R.id.longan_handle_mode_level2_pano_sectorial);
                    return;
                case INTERVAL_3s:
                    this.e.check(R.id.longan_handle_mode_level2_interval_3);
                    return;
                case INTERVAL_5s:
                    this.e.check(R.id.longan_handle_mode_level2_interval_5);
                    return;
                case INTERVAL_10s:
                    this.e.check(R.id.longan_handle_mode_level2_interval_10);
                    return;
                case INTERVAL_30s:
                    this.e.check(R.id.longan_handle_mode_level2_interval_30);
                    return;
                case TIMELAPSE:
                    return;
                default:
                    this.b.check(R.id.longan_handle_mode_level2_single_0s);
                    return;
            }
        }
    }

    public void onEventMainThread(e eVar) {
        if (this.j.d() != a.a.TAKEPHOTO) {
            a();
        }
    }

    private DJIRadioGroup a(DJIRadioGroup dJIRadioGroup, int i) {
        if (findViewById(i) != null) {
            return (DJIRadioGroup) ((ViewStub) findViewById(i)).inflate();
        }
        dJIRadioGroup.setVisibility(0);
        return dJIRadioGroup;
    }

    private void a() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(8);
        }
    }

    public void onCheckedChanged(DJIRadioGroup dJIRadioGroup, int i) {
        dJIRadioGroup.clearCheckedId();
        if (i != this.h) {
            RadioButton radioButton = (RadioButton) findViewById(this.h);
            if (radioButton != null) {
                radioButton.setChecked(false);
            }
            this.h = i;
            c instance = c.getInstance();
            if (i == R.id.longan_handle_mode_level2_single_0s) {
                if (this.k.d() != c.a.SINGLE_0s) {
                    instance.a(1, 0);
                }
            } else if (i == R.id.longan_handle_mode_level2_single_5s) {
                if (this.k.d() != c.a.SINGLE_5s) {
                    instance.a(1, 5);
                }
            } else if (i == R.id.longan_handle_mode_level2_single_10s) {
                if (this.k.d() != c.a.SINGLE_10s) {
                    instance.a(1, 10);
                }
            } else if (i == R.id.longan_handle_mode_level2_single_hdr) {
                if (this.k.d() != c.a.SINGLE_HDR) {
                    instance.a(1, -1);
                }
            } else if (i == R.id.longan_handle_mode_level2_burst_3) {
                if (this.k.d() != c.a.BURST_3) {
                    instance.a(c.b.BURST);
                    instance.a(3, 1);
                }
            } else if (i == R.id.longan_handle_mode_level2_burst_5) {
                if (this.k.d() != c.a.BURST_5) {
                    instance.a(c.b.BURST);
                    instance.a(5, 1);
                }
            } else if (i == R.id.longan_handle_mode_level2_burst_7) {
                if (this.k.d() != c.a.BURST_7) {
                    instance.a(c.b.BURST);
                    instance.a(7, 1);
                }
            } else if (i == R.id.longan_handle_mode_level2_aeb_3) {
                if (this.k.d() != c.a.AEB_3) {
                    LonganPopWarnView.getInstance(getContext()).pop(R.drawable.longan_camera_capture_setting_aeb_icon, R.string.longan_aeb_tips_3, LonganPopWarnView.a.LENGTH_SHORT);
                    instance.a(c.b.AEB);
                    instance.a(3, 1);
                }
            } else if (i == R.id.longan_handle_mode_level2_aeb_5) {
                if (this.k.d() != c.a.AEB_5) {
                    LonganPopWarnView.getInstance(getContext()).pop(R.drawable.longan_camera_capture_setting_aeb_icon, R.string.longan_aeb_tips_5, LonganPopWarnView.a.LENGTH_SHORT);
                    instance.a(c.b.AEB);
                    instance.a(5, 1);
                }
            } else if (i == R.id.longan_handle_mode_level2_pano_auto) {
                if (this.k.d() != c.a.PANO_AUTO) {
                    instance.a(-1, 0);
                    try {
                        r0 = Class.forName("dji.pilot.reflect.FpvReflect");
                        r0.getMethod("flurryOsmoPano", new Class[0]).invoke(r0, new Object[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (i == R.id.longan_handle_mode_level2_pano_selfie) {
                if (this.k.d() != c.a.PANO_SELFIE) {
                    instance.a(-3, 0);
                    try {
                        r0 = Class.forName("dji.pilot.reflect.FpvReflect");
                        r0.getMethod("flurryOsmoSafiPano", new Class[0]).invoke(r0, new Object[0]);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } else if (i == R.id.longan_handle_mode_level2_pano_auto_180) {
                if (this.k.d() != c.a.PANO_AUTO180) {
                    instance.a(-5, 0);
                }
            } else if (i == R.id.longan_handle_mode_level2_pano_m) {
                if (this.k.d() != c.a.PANO_MANU) {
                    instance.a(-4, 0);
                }
            } else if (i == R.id.longan_handle_mode_level2_pano_sectorial) {
                if (this.k.d() != c.a.PANO_SECTORIAL) {
                    instance.a(-7, 0);
                }
            } else if (i == R.id.longan_handle_mode_level2_interval_3) {
                if (this.k.d() != c.a.INTERVAL_3s) {
                    instance.a(255, 3);
                }
            } else if (i == R.id.longan_handle_mode_level2_interval_5) {
                if (this.k.d() != c.a.INTERVAL_5s) {
                    instance.a(255, 5);
                }
            } else if (i == R.id.longan_handle_mode_level2_interval_10) {
                if (this.k.d() != c.a.INTERVAL_10s) {
                    instance.a(255, 10);
                }
            } else if (i == R.id.longan_handle_mode_level2_interval_30) {
                if (this.k.d() != c.a.INTERVAL_30s) {
                    instance.a(255, 30);
                }
            } else if (i == R.id.longan_handle_mode_slow_720p) {
                if (this.l.b() != e.a.SLOWMOTION_720) {
                    this.l.a(e.a.SLOWMOTION_720);
                }
            } else if (i != R.id.longan_handle_mode_slow_1080p) {
                this.h = 0;
            } else if (this.l.b() != e.a.SLOWMOTION_1080) {
                this.l.a(e.a.SLOWMOTION_1080);
            }
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int imageFormat = dataCameraGetPushShotParams.getImageFormat();
        if (imageFormat != this.r) {
            this.r = imageFormat;
            a(imageFormat);
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        int childCount;
        int i;
        View childAt;
        if (dataCameraGetPushStateInfo.getIsGimbalBusy()) {
            childCount = getChildCount();
            for (i = 0; i < childCount; i++) {
                childAt = getChildAt(i);
                if (childAt.isShown()) {
                    childAt.setEnabled(false);
                    childAt.setAlpha(0.4f);
                    this.m = true;
                }
            }
        } else if (this.m) {
            childCount = getChildCount();
            for (i = 0; i < childCount; i++) {
                childAt = getChildAt(i);
                if (childAt.isShown()) {
                    childAt.setEnabled(true);
                    childAt.setAlpha(1.0f);
                }
            }
            this.m = false;
        }
        if (this.q == null) {
            this.q = findViewById(R.id.longan_handle_mode_level2_single_hdr);
        }
        if (dji.logic.f.b.h(null)) {
            if (this.q.getVisibility() == 0) {
                this.q.setVisibility(8);
            }
        } else if (this.q.getVisibility() != 0) {
            this.q.setVisibility(0);
        }
    }

    private void a(int i) {
        if (i != 1) {
            if (this.g == this.b) {
                findViewById(R.id.longan_handle_mode_level2_single_5s_ly).setVisibility(8);
            } else if (this.g == this.e && this.g != null) {
                this.e.findViewById(R.id.longan_handle_mode_level2_interval_3_ly).setVisibility(8);
                this.e.findViewById(R.id.longan_handle_mode_level2_interval_5_ly).setVisibility(8);
                this.i = R.id.longan_handle_mode_level2_interval_10;
            }
        } else if (this.g == this.b) {
            findViewById(R.id.longan_handle_mode_level2_single_5s_ly).setVisibility(0);
        } else if (this.g == this.e) {
            findViewById(R.id.longan_handle_mode_level2_interval_3_ly).setVisibility(0);
            findViewById(R.id.longan_handle_mode_level2_interval_5_ly).setVisibility(0);
            this.i = R.id.longan_handle_mode_level2_interval_3;
        }
    }

    public void onEventMainThread(o oVar) {
        switch (AnonymousClass2.c[oVar.ordinal()]) {
            case 1:
                setEnabled(true);
                return;
            case 2:
                setEnabled(false);
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(m mVar) {
        switch (mVar) {
            case HIDE_ALL:
                hide();
                return;
            case SHOW_MENU:
                show();
                return;
            case HIDE_MENU:
                hide();
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(dji.device.camera.a.b.c cVar) {
        if (cVar == dji.device.camera.a.b.c.TIMING) {
            hide();
        }
    }
}
