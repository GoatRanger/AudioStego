package dji.device.common.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.camera.a.b.b;
import dji.device.common.DJIUIEventManagerLongan;
import dji.device.common.DJIUIEventManagerLongan.e;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushRawParams;
import dji.midware.data.model.P3.DataCameraGetPushRawParams.DiskStatus;
import dji.midware.data.model.P3.DataGimbalControl.MODE;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.thirdparty.a.c;

public class DJICameraQuickSettingViewLongan extends DJIRelativeLayout implements OnClickListener {
    public static boolean d = true;
    public static boolean e = false;
    public static boolean f = false;
    private static final String i = "DJICameraQuickSettingViewLongan";
    private static final int j = 100;
    private static final int q = 17;
    private static final int r = 18;
    private static final int s = 19;
    private static final int t = 20;
    private static final int v = 1;
    private static final int w = 2;
    private static final int[] x = new int[]{R.drawable.longan_quicksettingbar_gimbal, R.drawable.longan_selector_quicksetting_gimbal_recenter, R.drawable.longan_selector_quicksetting_gimbal_tracking, R.drawable.longan_selector_quicksetting_gimbal_pantiltlock, R.drawable.longan_selector_quicksetting_gimbal_sensorcontrol};
    LayoutParams a;
    LinearLayout b;
    boolean c = false;
    TypedArray g;
    protected Handler h = new Handler(new Callback(this) {
        final /* synthetic */ DJICameraQuickSettingViewLongan a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.u = 19;
                    this.a.p.setSelected(false);
                    this.a.p.setImageResource(DJICameraQuickSettingViewLongan.x[0]);
                    c.a().e(m.HIDE_TRACKING);
                    break;
                case 2:
                    this.a.u = 17;
                    this.a.p.setSelected(true);
                    this.a.p.setImageResource(DJICameraQuickSettingViewLongan.x[2]);
                    break;
            }
            return false;
        }
    });
    private Animation k;
    private Animation l;
    private DJIStateImageViewCompat m;
    private DJIStateImageViewCompat n;
    private DJIStateImageViewCompat o;
    private DJIStateImageViewCompat p;
    private int u = 19;
    private DiskStatus y = DiskStatus.OTHER;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] b = new int[o.values().length];

        static {
            try {
                b[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[o.a.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            a = new int[m.values().length];
            try {
                a[m.HIDE_ALL.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[m.SHOW_QUICKSETTING_BAR.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[m.RETURN_QUICKSETTING_BAR.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[m.SHOW_ALL.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[m.HIDE_SHOTCUTS_GIMBAL_LY.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[m.HIDE_SHOTCUTS_CAMERA_LY.ordinal()] = 6;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[m.SHOW_TIMELAPSE_LY.ordinal()] = 7;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[m.SHOW_CONFLICT_RIGHT_VIEW.ordinal()] = 8;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    public DJICameraQuickSettingViewLongan(Context context) {
        super(context);
    }

    public DJICameraQuickSettingViewLongan(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJICameraQuickSettingViewLongan(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void init() {
        if (!isInEditMode()) {
            this.g = getResources().obtainTypedArray(R.array.camera_whitebalance_res_array);
            this.b = (LinearLayout) findViewById(R.id.longan_quicksetting_ori_ly);
            this.a = (LayoutParams) getLayoutParams();
            this.m = (DJIStateImageViewCompat) findViewById(R.id.longan_fpv_home);
            this.n = (DJIStateImageViewCompat) findViewById(R.id.longan_fpv_camera);
            this.o = (DJIStateImageViewCompat) findViewById(R.id.longan_fpv_setting);
            this.p = (DJIStateImageViewCompat) findViewById(R.id.longan_fpv_gimbal);
            this.m.setOnClickListener(this);
            this.n.setOnClickListener(this);
            this.o.setOnClickListener(this);
            this.p.setOnClickListener(this);
            setBtnEnableExceptHome(false);
            b();
            resetView();
            c.a().a(this);
            if (this.c) {
                this.p.setEnabled(true);
            }
        }
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        resetView();
    }

    public void resetView() {
        if (getResources().getConfiguration().orientation == 2) {
            this.b.setOrientation(1);
            this.b.setHorizontalGravity(17);
            this.b.getLayoutParams().width = -2;
            this.b.getLayoutParams().height = -1;
            this.a.addRule(10, 0);
            this.a.addRule(11);
            this.a.width = -2;
            this.a.height = -1;
            startAnimation(this.k);
            return;
        }
        this.b.setOrientation(0);
        this.b.setVerticalGravity(17);
        this.b.getLayoutParams().height = -2;
        this.b.getLayoutParams().width = -1;
        this.a.addRule(11, 0);
        this.a.addRule(10);
        this.a.width = -1;
        this.a.height = -2;
        startAnimation(this.l);
    }

    private void b() {
        this.k = AnimationUtils.loadAnimation(getContext(), R.anim.main_contain_slide_right_in);
        this.l = AnimationUtils.loadAnimation(getContext(), R.anim.main_contain_slide_top_in);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.longan_fpv_home) {
            ((Activity) getContext()).finish();
        } else if (id == R.id.longan_fpv_camera) {
            c();
        } else if (id == R.id.longan_fpv_setting) {
            try {
                r0 = Class.forName("dji.pilot.set.SetProxy");
                r0.getMethod("showSetActivity", new Class[]{Context.class}).invoke(r0, new Object[]{getContext()});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.longan_fpv_gimbal) {
            d();
            try {
                r0 = Class.forName("dji.pilot.reflect.FpvReflect");
                r0.getMethod("flurryOsmoGimbalMode", new Class[0]).invoke(r0, new Object[0]);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void c() {
        if (this.n.isSelected()) {
            this.n.setSelected(false);
            c.a().e(m.HIDE_SHOTCUTS_CAMERA_LY);
            e = false;
            return;
        }
        if (this.p.isSelected()) {
            d();
        }
        this.n.setSelected(true);
        c.a().e(m.SHOW_SHOTCUTS_CAMERA_LY);
        e = true;
    }

    private void d() {
        if (this.p.isSelected()) {
            this.p.setSelected(false);
            c.a().e(m.HIDE_SHOTCUTS_GIMBAL_LY);
            f = false;
            return;
        }
        if (this.n.isSelected()) {
            c();
        }
        this.p.setSelected(true);
        c.a().e(m.SHOW_SHOTCUTS_GIMBAL_LY);
        f = true;
    }

    public void onEventMainThread(m mVar) {
        switch (mVar) {
            case HIDE_ALL:
                hide();
                if (this.p.isSelected()) {
                    this.p.setSelected(false);
                }
                if (this.n.isSelected()) {
                    this.n.setSelected(false);
                    return;
                }
                return;
            case SHOW_QUICKSETTING_BAR:
                show();
                return;
            case RETURN_QUICKSETTING_BAR:
                showOriginalLy();
                return;
            case SHOW_ALL:
                show();
                return;
            case HIDE_SHOTCUTS_GIMBAL_LY:
                if (this.p.isSelected()) {
                    this.p.setSelected(false);
                    return;
                }
                return;
            case HIDE_SHOTCUTS_CAMERA_LY:
                if (this.n.isSelected()) {
                    this.n.setSelected(false);
                    return;
                }
                return;
            case SHOW_TIMELAPSE_LY:
                if (this.n.isSelected()) {
                    this.n.setSelected(false);
                }
                if (this.p.isSelected()) {
                    this.p.setSelected(false);
                    return;
                }
                return;
            case SHOW_CONFLICT_RIGHT_VIEW:
                if (this.n.isSelected()) {
                    this.n.setSelected(false);
                }
                if (this.p.isSelected()) {
                    this.p.setSelected(false);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(o oVar) {
        switch (AnonymousClass2.b[oVar.ordinal()]) {
            case 1:
                setBtnEnableExceptHome(true);
                this.m.setEnabled(true);
                return;
            case 2:
                e();
                return;
            default:
                return;
        }
    }

    private void e() {
        showOriginalLy();
        setBtnEnableExceptHome(false);
    }

    public void onEventMainThread(e eVar) {
        if (eVar == e.ENTER_SLEEP_MODE) {
            e();
        }
    }

    public void showOriginalLy() {
        if (!this.b.isShown()) {
            this.b.setVisibility(0);
        }
        d = true;
    }

    private void setBtnEnableExceptHome(boolean z) {
        this.n.setEnabled(z);
        this.o.setEnabled(z);
        this.p.setEnabled(z);
    }

    public void onEventMainThread(DJIUIEventManagerLongan.c cVar) {
        if (cVar == DJIUIEventManagerLongan.c.DISABLE_ALL) {
            setBtnEnableExceptHome(false);
        } else if (cVar == DJIUIEventManagerLongan.c.ENABLE_ALL) {
            setBtnEnableExceptHome(true);
        }
    }

    private void setEnableCamera(boolean z) {
        if (z) {
            this.n.setEnabled(true);
            this.n.setAlpha(1.0f);
            return;
        }
        this.n.setEnabled(false);
        this.n.setAlpha(0.4f);
    }

    public void onEventMainThread(b bVar) {
        if (bVar == b.SAVING) {
            setEnableCamera(false);
        } else if (bVar == b.SAVING_NOT) {
            setEnableCamera(true);
        }
        invalidate();
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        if (dataGimbalGetPushParams.getMode() == MODE.YawNoFollow) {
            this.p.setImageResource(R.drawable.longan_gimbal_pantiltlock_on);
        } else if (dataGimbalGetPushParams.getMode() == MODE.YawFollow) {
            this.p.setImageResource(R.drawable.longan_selector_gimbal);
        }
    }

    public void onEventMainThread(DataCameraGetPushRawParams dataCameraGetPushRawParams) {
        if (dataCameraGetPushRawParams.isGetted()) {
            DiskStatus diskStatus = dataCameraGetPushRawParams.getDiskStatus();
            if (this.y == diskStatus) {
                return;
            }
            if (diskStatus == DiskStatus.INITIALIZING) {
                setEnableCamera(false);
            } else {
                setEnableCamera(true);
            }
        }
    }
}
