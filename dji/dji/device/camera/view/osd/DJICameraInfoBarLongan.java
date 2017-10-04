package dji.device.camera.view.osd;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.alipay.e.a.a.b.b.c;
import com.here.odnp.posclient.pos.IPositioningSession;
import dji.device.activity.DJIPreviewActivityLongan;
import dji.device.camera.a.c.b;
import dji.device.camera.view.focus.DJIMFDemarcateViewLongan;
import dji.device.camera.view.focus.a.b.a;
import dji.device.common.DJIUIEventManagerLongan.e;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetAELock;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataRcGetPushFollowFocus2;
import dji.midware.data.model.P3.DataRcGetPushFollowFocus2.CtrlType;
import dji.midware.data.model.P3.DataWifiGetPushSignal;
import dji.pilot.fpv.R;
import dji.pilot.visual.a.d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class DJICameraInfoBarLongan extends DJILinearLayout {
    private static final int ag = 1;
    private static final int ah = 2;
    private static final int ai = 3;
    private static final int aj = 4;
    private static final int ak = 5;
    private static final int al = 6;
    private static final int am = 7;
    private static final int an = 8;
    public static float e = 0.0f;
    private static final String i = "DJICameraInfoBarLongan";
    private DJITextView A;
    private ImageView B;
    private TextView C;
    private View D;
    private DJILinearLayout E;
    private LinearLayout F;
    private DJITextView G;
    private boolean H;
    private boolean I;
    private int[] J;
    private int[] K;
    private String[] L;
    private int[] M;
    private String[] N;
    private String[] O;
    private int[] P;
    private CtrlType Q;
    private ExposureMode R;
    private int S;
    private String T;
    private int U;
    private int V;
    private long W;
    int a;
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private int ae;
    private MODE af;
    private DataCameraGetPushShotParams ao;
    private Handler ap;
    int b;
    int c;
    int d;
    CameraType f;
    Animation g;
    boolean h;
    private DJILinearLayout j;
    private DJITextView k;
    private DJITextView l;
    private DJITextView m;
    private DJITextView n;
    private DJITextView o;
    private DJITextView p;
    private DJITextView q;
    private DJITextView r;
    private DJITextView s;
    private DJITextView t;
    private DJITextView u;
    private DJITextView v;
    private DJITextView w;
    private DJITextView x;
    private DJIImageView y;
    private DJIImageView z;

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] c = new int[o.values().length];

        static {
            d = new int[m.values().length];
            try {
                d[m.HIDE_ALL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                d[m.SHOW_ALL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                d[m.HIDE_INFO_BAR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                d[m.SHOW_INFO_BAR.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                d[m.HIDE_TIMELAPSE_LY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                d[m.SHOW_TIMELAPSE_LY.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                d[m.HIDE_MENU.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                d[m.SHOW_MENU.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                d[m.HIDE_SHOTCUTS_GIMBAL_LY.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                d[m.SHOW_SHOTCUTS_GIMBAL_LY.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                c[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e11) {
            }
            try {
                c[o.a.ordinal()] = 2;
            } catch (NoSuchFieldError e12) {
            }
            b = new int[a.values().length];
            try {
                b[a.showUI.ordinal()] = 1;
            } catch (NoSuchFieldError e13) {
            }
            try {
                b[a.hideUI.ordinal()] = 2;
            } catch (NoSuchFieldError e14) {
            }
            a = new int[CtrlType.values().length];
            try {
                a[CtrlType.APERTURE.ordinal()] = 1;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[CtrlType.FOCUS_POSITION.ordinal()] = 2;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[CtrlType.FOCUS_LENGTH.ordinal()] = 3;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    public DJICameraInfoBarLongan(Context context) {
        super(context);
        this.j = null;
        this.H = false;
        this.I = false;
        this.J = new int[]{R.drawable.longan_statebar_wifi_0, R.drawable.longan_statebar_wifi_1, R.drawable.longan_statebar_wifi_2, R.drawable.longan_statebar_wifi_3, R.drawable.longan_statebar_wifi_4};
        this.K = new int[]{R.drawable.longan_battery0, R.drawable.longan_battery2, R.drawable.longan_battery2, R.drawable.longan_battery3, R.drawable.longan_battery4, R.drawable.longan_battery5, R.drawable.longan_battery6, R.drawable.longan_battery7, R.drawable.longan_battery8, R.drawable.longan_battery9, R.drawable.longan_battery10, R.drawable.longan_battery_error};
        this.N = new String[]{c.c, dji.thirdparty.g.c.k, "J+R"};
        this.R = null;
        this.S = Integer.MAX_VALUE;
        this.T = null;
        this.U = Integer.MAX_VALUE;
        this.V = Integer.MAX_VALUE;
        this.W = IPositioningSession.NotSet;
        this.aa = Integer.MAX_VALUE;
        this.ab = Integer.MAX_VALUE;
        this.ac = Integer.MAX_VALUE;
        this.ad = Integer.MAX_VALUE;
        this.ae = Integer.MAX_VALUE;
        this.af = null;
        this.f = CameraType.OTHER;
        this.h = false;
        this.ap = new Handler(new Callback(this) {
            final /* synthetic */ DJICameraInfoBarLongan a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        this.a.d();
                        break;
                    case 2:
                        this.a.parmsViewShow();
                        break;
                    case 3:
                        this.a.parmsViewGo();
                        break;
                    case 4:
                        this.a.a(message.arg1);
                        break;
                    case 6:
                        if (this.a.af == MODE.TAKEPHOTO) {
                            this.a.f();
                        } else if (this.a.af == MODE.RECORD) {
                            this.a.e();
                        }
                        this.a.invalidate();
                        break;
                    case 7:
                        this.a.g();
                        break;
                    case 8:
                        this.a.show();
                        break;
                }
                return false;
            }
        });
    }

    public DJICameraInfoBarLongan(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = null;
        this.H = false;
        this.I = false;
        this.J = new int[]{R.drawable.longan_statebar_wifi_0, R.drawable.longan_statebar_wifi_1, R.drawable.longan_statebar_wifi_2, R.drawable.longan_statebar_wifi_3, R.drawable.longan_statebar_wifi_4};
        this.K = new int[]{R.drawable.longan_battery0, R.drawable.longan_battery2, R.drawable.longan_battery2, R.drawable.longan_battery3, R.drawable.longan_battery4, R.drawable.longan_battery5, R.drawable.longan_battery6, R.drawable.longan_battery7, R.drawable.longan_battery8, R.drawable.longan_battery9, R.drawable.longan_battery10, R.drawable.longan_battery_error};
        this.N = new String[]{c.c, dji.thirdparty.g.c.k, "J+R"};
        this.R = null;
        this.S = Integer.MAX_VALUE;
        this.T = null;
        this.U = Integer.MAX_VALUE;
        this.V = Integer.MAX_VALUE;
        this.W = IPositioningSession.NotSet;
        this.aa = Integer.MAX_VALUE;
        this.ab = Integer.MAX_VALUE;
        this.ac = Integer.MAX_VALUE;
        this.ad = Integer.MAX_VALUE;
        this.ae = Integer.MAX_VALUE;
        this.af = null;
        this.f = CameraType.OTHER;
        this.h = false;
        this.ap = new Handler(/* anonymous class already generated */);
    }

    public DJICameraInfoBarLongan(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = null;
        this.H = false;
        this.I = false;
        this.J = new int[]{R.drawable.longan_statebar_wifi_0, R.drawable.longan_statebar_wifi_1, R.drawable.longan_statebar_wifi_2, R.drawable.longan_statebar_wifi_3, R.drawable.longan_statebar_wifi_4};
        this.K = new int[]{R.drawable.longan_battery0, R.drawable.longan_battery2, R.drawable.longan_battery2, R.drawable.longan_battery3, R.drawable.longan_battery4, R.drawable.longan_battery5, R.drawable.longan_battery6, R.drawable.longan_battery7, R.drawable.longan_battery8, R.drawable.longan_battery9, R.drawable.longan_battery10, R.drawable.longan_battery_error};
        this.N = new String[]{c.c, dji.thirdparty.g.c.k, "J+R"};
        this.R = null;
        this.S = Integer.MAX_VALUE;
        this.T = null;
        this.U = Integer.MAX_VALUE;
        this.V = Integer.MAX_VALUE;
        this.W = IPositioningSession.NotSet;
        this.aa = Integer.MAX_VALUE;
        this.ab = Integer.MAX_VALUE;
        this.ac = Integer.MAX_VALUE;
        this.ad = Integer.MAX_VALUE;
        this.ae = Integer.MAX_VALUE;
        this.af = null;
        this.f = CameraType.OTHER;
        this.h = false;
        this.ap = new Handler(/* anonymous class already generated */);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.thirdparty.a.c.a().d(this);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getScreenSize();
        resetView();
    }

    public void resetView() {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        if (getResources().getConfiguration().orientation == 2) {
            e = (float) this.c;
            setY(e);
            setOrientation(0);
            layoutParams.width = -2;
            layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.dp_35_in_sw320dp);
            return;
        }
        e = (((float) (this.b - dji.device.common.a.a.a(getContext()))) / 2.0f) - ((float) this.d);
        setY(e);
        setOrientation(1);
        layoutParams.width = -1;
        layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.dp_80_in_sw320dp);
    }

    @SuppressLint({"NewApi"})
    private void getScreenSize() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        Point point = new Point();
        if (VERSION.SDK_INT < 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            this.a = displayMetrics.widthPixels;
            this.b = displayMetrics.heightPixels;
            return;
        }
        windowManager.getDefaultDisplay().getRealSize(point);
        this.a = point.x;
        this.b = point.y;
    }

    public static int dip2px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + d.c);
    }

    private void a() {
        dji.thirdparty.a.c.a().a(this);
        this.j = (DJILinearLayout) findViewById(R.id.longan_amf_ly);
        this.l = (DJITextView) findViewById(R.id.topbar_shutter_label);
        this.k = (DJITextView) findViewById(R.id.topbar_iso_tv);
        this.m = (DJITextView) findViewById(R.id.topbar_fnumbe_tv);
        this.n = (DJITextView) findViewById(R.id.topbar_ev_tv);
        this.o = (DJITextView) findViewById(R.id.topbar_PSM_tv);
        this.p = (DJITextView) findViewById(R.id.topbar_iso_value);
        this.q = (DJITextView) findViewById(R.id.topbar_shutter_value);
        this.r = (DJITextView) findViewById(R.id.topbar_fnumbe_value);
        this.s = (DJITextView) findViewById(R.id.topbar_ev_value);
        this.t = (DJITextView) findViewById(R.id.topbar_photoformat_value);
        this.u = (DJITextView) findViewById(R.id.topbar_timelapseformat_value);
        this.v = (DJITextView) findViewById(R.id.topbar_remain_number);
        this.w = (DJITextView) findViewById(R.id.topbar_videoformat_value);
        this.x = (DJITextView) findViewById(R.id.topbar_videofps_value);
        this.y = (DJIImageView) findViewById(R.id.topbar_wifi_iv);
        this.z = (DJIImageView) findViewById(R.id.topbar_battery_iv);
        this.A = (DJITextView) findViewById(R.id.topbar_battery_value);
        this.D = findViewById(R.id.longan_info_ae_line);
        this.E = (DJILinearLayout) findViewById(R.id.longan_camera_infobar_aelock_ly);
        this.E.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJICameraInfoBarLongan a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.B.isSelected()) {
                    DataCameraSetAELock.getInstance().a(false).start(null);
                } else {
                    DataCameraSetAELock.getInstance().a(true).start(null);
                }
            }
        });
        this.C = (TextView) findViewById(R.id.longan_ae_tv);
        this.B = (ImageView) findViewById(R.id.longan_ae_lock_iv);
        this.G = (DJITextView) findViewById(R.id.topbar_follow_focus_text);
        this.F = (LinearLayout) findViewById(R.id.topbar_follow_focus_iv);
        this.ao = DataCameraGetPushShotParams.getInstance();
        Resources resources = getContext().getResources();
        this.L = resources.getStringArray(R.array.camera_ev_array);
        this.M = resources.getIntArray(R.array.camera_ev_value_array);
        this.P = resources.getIntArray(R.array.fpv_camera_video_fps_array);
        this.O = resources.getStringArray(R.array.fpv_camera_video_resolution_array);
        this.g = AnimationUtils.loadAnimation(getContext(), R.anim.longan_fade_in);
        getScreenSize();
        this.c = getResources().getDimensionPixelOffset(R.dimen.longan_camerainfobar_y_land);
        this.d = getResources().getDimensionPixelOffset(R.dimen.dp_80_in_sw320dp);
        resetView();
        if (ServiceManager.getInstance().isOK()) {
            this.I = true;
            parmsViewShow();
            b();
        }
    }

    private void b() {
        onEventBackgroundThread(DataCameraGetPushStateInfo.getInstance());
        onEventMainThread(DataCameraGetPushShotParams.getInstance());
        onEventBackgroundThread(DataWifiGetPushSignal.getInstance());
        onEventMainThread(DataCenterGetPushBatteryCommon.getInstance());
    }

    private void c() {
        this.R = null;
        this.S = Integer.MAX_VALUE;
        this.T = null;
        this.U = Integer.MAX_VALUE;
        this.V = Integer.MAX_VALUE;
        this.W = IPositioningSession.NotSet;
        this.aa = Integer.MAX_VALUE;
        this.ac = Integer.MAX_VALUE;
        this.ad = Integer.MAX_VALUE;
        this.ae = Integer.MAX_VALUE;
    }

    public void parmsViewGo() {
        c();
        this.o.setText("N/A");
        this.p.setText("N/A");
        this.q.setText("N/A");
        this.r.setText("N/A");
        this.s.setText("N/A");
        this.t.setText("N/A");
        this.v.setText("N/A");
        this.w.setText("N/A");
        this.x.setText("N/A");
        this.y.setImageResource(this.J[0]);
        this.z.setImageResource(this.K[0]);
        this.A.setText("N/A");
    }

    public void parmsViewShow() {
        this.k.setVisibility(0);
        this.l.setVisibility(0);
        this.m.setVisibility(0);
        this.n.setVisibility(0);
        this.o.setVisibility(0);
        this.p.setVisibility(0);
        this.q.setVisibility(0);
        this.r.setVisibility(0);
        this.s.setVisibility(0);
        if (this.H) {
            this.t.setVisibility(0);
            this.v.setVisibility(0);
        } else {
            this.w.setVisibility(0);
            this.x.setVisibility(0);
        }
        this.y.setVisibility(0);
        this.z.setVisibility(0);
        this.A.setVisibility(0);
        this.D.setVisibility(0);
        this.B.setVisibility(0);
        this.C.setVisibility(0);
    }

    private void d() {
        int relExposureCompensation;
        ExposureMode exposureMode = this.ao.getExposureMode();
        if (!(this.R == exposureMode || exposureMode == ExposureMode.a)) {
            this.R = exposureMode;
            if (exposureMode == ExposureMode.b) {
                this.o.setText("");
            } else {
                this.o.setText(exposureMode + "");
            }
        }
        if (exposureMode == ExposureMode.e) {
            this.E.setEnabled(false);
            this.B.setImageAlpha(150);
            this.C.setAlpha(d.c);
            this.B.setSelected(false);
            this.C.setSelected(false);
        } else {
            this.E.setEnabled(true);
            this.B.setImageAlpha(255);
            this.C.setAlpha(1.0f);
            this.B.setSelected(this.ao.isAELock());
            this.C.setSelected(this.ao.isAELock());
        }
        int relISO = this.ao.getRelISO();
        if (relISO != this.S) {
            this.S = relISO;
            this.p.setText(relISO + "");
        }
        CharSequence relShutterString = this.ao.getRelShutterString();
        if (!relShutterString.equals(this.T)) {
            this.T = relShutterString;
            this.q.setText(relShutterString);
        }
        relISO = this.ao.getApertureSize();
        if (relISO != this.V) {
            this.V = relISO;
            this.r.setText((((double) relISO) / 100.0d) + "");
        }
        if (exposureMode == ExposureMode.e) {
            relExposureCompensation = this.ao.getRelExposureCompensation();
        } else {
            relExposureCompensation = this.ao.getExposureCompensation();
        }
        if (relExposureCompensation != this.U) {
            this.U = relExposureCompensation;
            this.s.setText(this.L[a(relExposureCompensation, this.M)]);
        }
        relExposureCompensation = DataCameraGetPushShotParams.getInstance().getTimelapseSaveType();
        if (this.ab != relExposureCompensation) {
            if (relExposureCompensation == 0) {
                this.u.setText("Video");
            } else if (relExposureCompensation == 1) {
                this.u.setText("Picture");
            } else if (relExposureCompensation == 2) {
                this.u.setText("J+V");
            }
        }
        long remainedShots = DataCameraGetPushStateInfo.getInstance().getRemainedShots();
        if (remainedShots != this.W && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO) {
            this.W = remainedShots;
            this.v.setText(remainedShots + "");
        }
        relExposureCompensation = this.ao.getImageFormat();
        if (this.aa != relExposureCompensation && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO) {
            this.aa = relExposureCompensation;
            if (relExposureCompensation > this.N.length) {
                this.t.setText("N/A");
            } else {
                this.t.setText(this.N[relExposureCompensation]);
            }
        }
        relExposureCompensation = this.ao.getVideoFormat();
        if (this.ac != relExposureCompensation && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD) {
            this.ac = relExposureCompensation;
            if (relExposureCompensation <= 5) {
                this.w.setText("720p");
            } else if (relExposureCompensation <= 13) {
                this.w.setText("1080p");
            } else if (relExposureCompensation <= 22) {
                this.w.setText("4k");
            } else if (relExposureCompensation == 24) {
                this.w.setText("2.7k");
            }
        }
        relExposureCompensation = this.ao.getVideoFps();
        if (this.ad != relExposureCompensation && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD) {
            this.ad = relExposureCompensation;
            if (relExposureCompensation > this.P.length) {
                this.x.setText("N/A");
            } else if (relExposureCompensation == 7) {
                this.x.setText("SLO");
            } else {
                this.x.setText(this.P[relExposureCompensation] + "");
            }
        }
    }

    private int a(int i, int[] iArr) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (iArr[i2] == i) {
                return i2;
            }
        }
        return 0;
    }

    private void e() {
        this.u.setVisibility(8);
        this.t.setVisibility(8);
        this.v.setVisibility(8);
        this.w.setVisibility(0);
        this.x.setVisibility(0);
        this.H = false;
    }

    private void f() {
        this.u.setVisibility(8);
        this.t.setVisibility(0);
        this.v.setVisibility(0);
        this.w.setVisibility(8);
        this.x.setVisibility(8);
        this.H = true;
    }

    private void g() {
        this.u.setVisibility(0);
        this.t.setVisibility(8);
        this.v.setVisibility(8);
        this.w.setVisibility(8);
        this.x.setVisibility(8);
        this.H = false;
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (this.I) {
            if (this.k.getVisibility() == 8) {
                parmsViewShow();
            }
            d();
        }
    }

    public void onEventMainThread(DataRcGetPushFollowFocus2 dataRcGetPushFollowFocus2) {
        if (this.Q == null || this.Q != dataRcGetPushFollowFocus2.getCtrlType()) {
            this.Q = dataRcGetPushFollowFocus2.getCtrlType();
            switch (this.Q) {
                case APERTURE:
                    this.G.setText(R.string.longan_top_bar_follow_focus_Aperture);
                    return;
                case FOCUS_POSITION:
                    this.G.setText(R.string.longan_top_bar_follow_focus_Focus);
                    return;
                case FOCUS_LENGTH:
                    this.G.setText(R.string.longan_top_bar_follow_focus_Zoom);
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventMainThread(a aVar) {
        Log.d(i, "onEventMainThread: " + aVar.name());
        switch (aVar) {
            case showUI:
                this.F.setVisibility(0);
                return;
            case hideUI:
                this.F.setVisibility(8);
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (this.I) {
            this.ap.sendEmptyMessage(1);
            if (dataCameraGetPushStateInfo.getMode() != this.af && this.I) {
                this.af = dataCameraGetPushStateInfo.getMode();
                this.ap.sendEmptyMessage(6);
            }
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (AnonymousClass3.c[oVar.ordinal()]) {
        }
    }

    public void onEventMainThread(o oVar) {
        switch (AnonymousClass3.c[oVar.ordinal()]) {
            case 1:
                this.I = true;
                b();
                this.ap.sendEmptyMessage(2);
                return;
            case 2:
                this.I = false;
                this.ap.sendEmptyMessage(3);
                return;
            default:
                return;
        }
    }

    private void a(int i) {
        if (i <= 0) {
            this.y.setImageResource(this.J[0]);
        } else if (i <= 25) {
            this.y.setImageResource(this.J[1]);
        } else if (i <= 50) {
            this.y.setImageResource(this.J[2]);
        } else if (i <= 75) {
            this.y.setImageResource(this.J[3]);
        } else if (i <= 100) {
            this.y.setImageResource(this.J[4]);
        }
    }

    private void b(int i) {
        int i2 = 100;
        int i3 = (int) (((double) (((float) ((i - 3) * 100)) / 97.0f)) + 0.5d);
        if (i3 <= 100) {
            i2 = i3;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        i3 = i2 / 10;
        if (i2 == -1 || i3 > 11) {
            this.z.setImageResource(this.K[11]);
            this.A.setText("Err");
            return;
        }
        this.A.setText(i2 + "%");
        this.z.setImageResource(this.K[i3]);
        if (i3 == 0) {
            this.A.setTextColor(getResources().getColor(R.color.red));
        } else {
            this.A.setTextColor(getResources().getColor(R.color.white));
        }
    }

    public void onEventBackgroundThread(DataWifiGetPushSignal dataWifiGetPushSignal) {
        this.ap.sendMessage(this.ap.obtainMessage(4, dataWifiGetPushSignal.getSignal(), 0));
    }

    public void onEventMainThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        int relativeCapacity = dataCenterGetPushBatteryCommon.getRelativeCapacity();
        if (this.ae != relativeCapacity) {
            this.ae = relativeCapacity;
            b(relativeCapacity);
        }
    }

    public void onEventMainThread(m mVar) {
        switch (mVar) {
            case HIDE_ALL:
                hide();
                return;
            case SHOW_ALL:
                this.ap.sendEmptyMessageDelayed(8, 200);
                return;
            case HIDE_INFO_BAR:
                hide();
                return;
            case SHOW_INFO_BAR:
                this.ap.sendEmptyMessageDelayed(8, 200);
                return;
            case HIDE_TIMELAPSE_LY:
                this.ap.sendEmptyMessageDelayed(8, 200);
                return;
            case SHOW_TIMELAPSE_LY:
                hide();
                return;
            case HIDE_MENU:
                this.ap.sendEmptyMessageDelayed(8, 200);
                return;
            case SHOW_MENU:
                hide();
                return;
            case HIDE_SHOTCUTS_GIMBAL_LY:
                this.ap.sendEmptyMessageDelayed(8, 200);
                return;
            case SHOW_SHOTCUTS_GIMBAL_LY:
                hide();
                return;
            default:
                return;
        }
    }

    public void show() {
        if (!DJIPreviewActivityLongan.isPopViewShown() && !DJIPreviewActivityLongan.isHideAll) {
            super.show();
            dji.thirdparty.a.c.a().e(m.INFO_BAR_SHOWEN);
        }
    }

    public void hide() {
        super.hide();
        dji.thirdparty.a.c.a().e(m.INFO_BAR_HIDDEN);
    }

    public void onEventMainThread(e eVar) {
        if (eVar == e.ENTER_SLEEP_MODE) {
            this.I = false;
            this.ap.sendEmptyMessage(3);
        }
    }

    public void onEventMainThread(dji.device.camera.a.c cVar) {
        if (cVar.c() == b.TIMELAPSE) {
            this.ap.sendEmptyMessage(7);
        } else {
            this.ap.sendEmptyMessage(6);
        }
    }

    public void onEventMainThread(dji.device.camera.a.b.c cVar) {
        if (cVar == dji.device.camera.a.b.c.TIMING || cVar != dji.device.camera.a.b.c.NOT_TIMING) {
        }
    }

    public void onEventMainThread(DJIMFDemarcateViewLongan.a aVar) {
        if (aVar == DJIMFDemarcateViewLongan.a.HIDE) {
            show();
        }
    }
}
