package dji.device.camera.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import dji.device.camera.a.a;
import dji.device.camera.a.c;
import dji.device.camera.a.e;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.DJIRadioGroup;
import dji.device.common.view.DJIRadioGroup.b;
import dji.device.timelapse.LonganNewTimelapseMainLayout;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJILinearLayout;

public class DJILevel1MenuViewLongan extends DJILinearLayout implements b {
    private static final int y = 1;
    private Handler A = new Handler(new Callback(this) {
        final /* synthetic */ DJILevel1MenuViewLongan a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.startAnimation(this.a.w);
                    break;
            }
            return false;
        }
    });
    private View B = null;
    private View C = null;
    LayoutParams a;
    TextView b;
    TextView c;
    TextView d;
    TextView e;
    TextView f;
    TextView g;
    TextView h;
    TextView i;
    DJIRadioGroup j;
    DJIRadioGroup k;
    DJIRadioGroup l;
    Button m;
    RelativeLayout n;
    RelativeLayout o;
    RelativeLayout p;
    c q = c.getInstance();
    e r = e.getInstance();
    boolean s = false;
    int t = 0;
    boolean u = false;
    private final String v = "DJILevel1MenuControllerLongan";
    private Animation w;
    private a x = a.getInstance();
    private boolean z = false;

    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] d = new int[o.values().length];

        static {
            e = new int[m.values().length];
            try {
                e[m.HIDE_ALL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                e[m.SHOW_MENU.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                e[m.HIDE_MENU.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                d[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                d[o.a.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            c = new int[e.a.values().length];
            try {
                c[e.a.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                c[e.a.SLOWMOTION_720.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                c[e.a.SLOWMOTION_1080.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            b = new int[c.b.values().length];
            try {
                b[c.b.SINGLE.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                b[c.b.BURST.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            try {
                b[c.b.AEB.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                b[c.b.PANO.ordinal()] = 4;
            } catch (NoSuchFieldError e12) {
            }
            try {
                b[c.b.INTERVAL.ordinal()] = 5;
            } catch (NoSuchFieldError e13) {
            }
            try {
                b[c.b.TIMELAPSE.ordinal()] = 6;
            } catch (NoSuchFieldError e14) {
            }
            a = new int[a.a.values().length];
            try {
                a[a.a.TAKEPHOTO.ordinal()] = 1;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[a.a.RECORD.ordinal()] = 2;
            } catch (NoSuchFieldError e16) {
            }
        }
    }

    public DJILevel1MenuViewLongan(Context context) {
        super(context);
    }

    public DJILevel1MenuViewLongan(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJILevel1MenuViewLongan(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        init();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.thirdparty.a.c.a().d(this);
    }

    public void init() {
        if (!isInEditMode()) {
            DJILogHelper.getInstance().LOGD("init log", "L1 init");
            dji.thirdparty.a.c.a().a(this);
            this.b = (TextView) findViewById(R.id.longan_modeset_single_tv);
            this.c = (TextView) findViewById(R.id.longan_modeset_multiple_tv);
            this.d = (TextView) findViewById(R.id.longan_modeset_interval_tv);
            this.e = (TextView) findViewById(R.id.longan_modeset_pano_tv);
            this.f = (TextView) findViewById(R.id.longan_modeset_timelapse_tv);
            this.a = (LayoutParams) getLayoutParams();
            this.j = (DJIRadioGroup) findViewById(R.id.longan_radiogroup_photomodes);
            this.j.setOnCheckedChangeListener(this);
            this.w = AnimationUtils.loadAnimation(getContext(), R.anim.longan_menu_fade_in);
            this.w.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ DJILevel1MenuViewLongan a;

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
            this.m = (Button) findViewById(R.id.longan_timelapse_fuck_btn);
            this.m.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ DJILevel1MenuViewLongan a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 0) {
                        if (LonganNewTimelapseMainLayout.e) {
                            dji.thirdparty.a.c.a().e(m.HIDE_TIMELAPSE_LY);
                        } else {
                            dji.thirdparty.a.c.a().e(m.SHOW_TIMELAPSE_LY);
                        }
                    }
                    return false;
                }
            });
            resetView();
            if (DataCameraGetPushShotParams.getInstance().isGetted()) {
                onEventMainThread(this.x);
                onEventMainThread(this.q.c());
                onEventMainThread(this.r);
            }
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
            this.a.addRule(1, R.id.longan_preview_cameracontrol);
            this.a.width = -2;
            this.a.height = -1;
            this.a.setMargins((int) getContext().getResources().getDimension(R.dimen.longan_level1_menu_margin_left_orbottom), 0, 0, 0);
        } else {
            setOrientation(0);
            this.a.addRule(1, 0);
            this.a.addRule(2, R.id.longan_preview_cameracontrol);
            this.a.height = -2;
            this.a.width = -1;
            this.a.setMargins(0, 0, 0, (int) getContext().getResources().getDimension(R.dimen.longan_level1_menu_margin_left_orbottom));
        }
        if (getVisibility() == 0) {
            setAnimationVisibility(0);
        }
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).getVisibility() == 0) {
                ((DJIRadioGroup) getChildAt(i)).setOrientation(getOrientation());
            }
        }
    }

    public void setAnimationVisibility(int i) {
        if (i == 0) {
            setVisibility(0);
            this.A.sendEmptyMessageDelayed(1, (long) (getResources().getInteger(R.integer.longan_translate_duration) + 30));
            return;
        }
        setVisibility(4);
    }

    public void handleViewChange() {
        if (getVisibility() == 0) {
            setVisibility(4);
        } else {
            setVisibility(0);
        }
    }

    public void show(boolean z) {
        if (z) {
            setAnimationVisibility(0);
        } else {
            setAnimationVisibility(4);
        }
    }

    public void onCheckedChanged(DJIRadioGroup dJIRadioGroup, int i) {
        RadioButton radioButtonByCheckedId = dJIRadioGroup.getRadioButtonByCheckedId(i);
        if (radioButtonByCheckedId == null || !radioButtonByCheckedId.isChecked()) {
            return;
        }
        if (i == R.id.longan_modeset_single) {
            c.getInstance().a(c.b.SINGLE);
            a(this.b);
        } else if (i == R.id.longan_modeset_multiple) {
            c.getInstance().a(c.b.BURST);
            a(this.c);
        } else if (i == R.id.longan_modeset_pano) {
            c.getInstance().a(c.b.PANO);
            a(this.e);
        } else if (i == R.id.longan_modeset_interval) {
            c.getInstance().a(c.b.INTERVAL);
            a(this.d);
        } else if (i == R.id.longan_modeset_timelapse) {
            c.getInstance().a(c.b.TIMELAPSE).a(255, dji.device.timelapse.b.getInstance().a);
            a(this.f);
            dji.device.timelapse.b.getInstance().f();
            try {
                Class cls = Class.forName("dji.pilot.reflect.FpvReflect");
                cls.getMethod("flurryOsmoTimePlace", new Class[0]).invoke(cls, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (i == R.id.longan_handle_mode_video_auto && this.z) {
            this.r.a(e.a.AUTO).c(0).b(0);
            a(this.g);
        } else if (i == R.id.longan_handle_mode_video_slo && this.z) {
            this.r.a(e.a.SLOWMOTION_1080);
            a(this.h);
        }
    }

    private void a(TextView textView) {
        if (this.i == null || this.i.getId() != textView.getId()) {
            textView.setSelected(true);
            if (this.i != null) {
                this.i.setSelected(false);
            }
            this.i = textView;
        }
    }

    public void onEventMainThread(a aVar) {
        DJIRadioGroup dJIRadioGroup = null;
        switch (aVar.d()) {
            case TAKEPHOTO:
                a();
                this.j.setVisibility(0);
                dJIRadioGroup = this.j;
                onEventMainThread(this.q.c());
                break;
            case RECORD:
                a();
                this.k = a(this.k, R.id.longan_radiogroup_videomodes_vs);
                this.g = (TextView) this.k.findViewById(R.id.longan_modeset_auto_tv);
                this.h = (TextView) this.k.findViewById(R.id.longan_modeset_slowmotion_tv);
                dJIRadioGroup = this.k;
                break;
        }
        if (dJIRadioGroup != null) {
            dJIRadioGroup.setOrientation(getOrientation());
            dJIRadioGroup.setOnCheckedChangeListener(this);
        }
    }

    public void onEventMainThread(c cVar) {
        onEventMainThread(cVar.c());
    }

    public void onEventMainThread(c.b bVar) {
        if (this.x.d() == a.a.TAKEPHOTO) {
            switch (bVar) {
                case SINGLE:
                    this.t = R.id.longan_modeset_single;
                    break;
                case BURST:
                    this.t = R.id.longan_modeset_multiple;
                    break;
                case AEB:
                    this.t = R.id.longan_modeset_multiple;
                    break;
                case PANO:
                    this.t = R.id.longan_modeset_pano;
                    break;
                case INTERVAL:
                    this.t = R.id.longan_modeset_interval;
                    break;
                case TIMELAPSE:
                    this.t = R.id.longan_modeset_timelapse;
                    break;
            }
            if (this.j != null) {
                this.j.check(this.t);
            }
        }
    }

    public void onEventMainThread(e eVar) {
        Log.e("mode issue", "level1 received" + eVar.b());
        this.s = false;
        if (this.x.d() == a.a.RECORD) {
            switch (eVar.b()) {
                case AUTO:
                    this.t = R.id.longan_handle_mode_video_auto;
                    if (this.g != null) {
                        a(this.g);
                        break;
                    }
                    break;
                case SLOWMOTION_720:
                    this.t = R.id.longan_handle_mode_video_slo;
                    if (this.g != null) {
                        a(this.h);
                        break;
                    }
                    break;
                case SLOWMOTION_1080:
                    this.t = R.id.longan_handle_mode_video_slo;
                    if (this.g != null) {
                        a(this.h);
                        break;
                    }
                    break;
            }
            if (this.k != null) {
                this.z = false;
                this.k.check(this.t);
            }
            this.z = true;
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        ExposureMode exposureMode = dataCameraGetPushShotParams.getExposureMode();
        if (exposureMode == ExposureMode.b) {
            if (this.n != null) {
                this.n.setVisibility(8);
                this.o.setVisibility(0);
                this.p.setVisibility(0);
            }
        } else if (exposureMode == ExposureMode.c) {
            if (this.o != null) {
                this.o.setVisibility(8);
                this.n.setVisibility(0);
                this.p.setVisibility(0);
            }
        } else if (exposureMode == ExposureMode.e && this.p != null) {
            this.p.setVisibility(8);
            this.o.setVisibility(0);
            this.n.setVisibility(0);
        }
        TYPE photoType = dataCameraGetPushShotParams.getPhotoType();
        if (!(photoType == TYPE.SINGLE || photoType == TYPE.BURST || photoType == TYPE.FULLVIEW || photoType != TYPE.TIME)) {
        }
        int videoRecordMode = dataCameraGetPushShotParams.getVideoRecordMode();
        if (videoRecordMode != 0 && videoRecordMode != 1 && videoRecordMode == 2) {
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

    public void onEventMainThread(o oVar) {
        switch (AnonymousClass4.d[oVar.ordinal()]) {
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
                onEventMainThread(DataCameraGetPushStateInfo.getInstance());
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

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (this.B == null) {
            this.B = findViewById(R.id.longan_modeset_pano);
            this.C = findViewById(R.id.longan_modeset_timelapse);
        }
        if (dji.logic.f.b.h(null)) {
            this.e.setVisibility(8);
            this.f.setVisibility(8);
            this.B.setVisibility(8);
            this.B.setVisibility(8);
            this.C.setVisibility(8);
            this.C.setVisibility(8);
            this.m.setVisibility(8);
            return;
        }
        this.e.setVisibility(0);
        this.f.setVisibility(0);
        this.B.setVisibility(0);
        this.B.setVisibility(0);
        this.C.setVisibility(0);
        this.C.setVisibility(0);
        this.m.setVisibility(0);
    }
}
