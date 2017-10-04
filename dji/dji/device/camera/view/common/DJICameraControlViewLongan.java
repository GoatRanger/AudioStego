package dji.device.camera.view.common;

import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import dji.device.activity.DJIPreviewActivityLongan;
import dji.device.camera.a.c;
import dji.device.camera.a.d;
import dji.device.camera.a.e;
import dji.device.common.DJIUIEventManagerLongan;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.DJICameraAnimViewCompat;
import dji.device.common.view.DJICameraSwitchView;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo$RecordType;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.data.model.P3.DataCameraSetPhotoMode;
import dji.midware.data.model.P3.DataCameraSwitchUSB;
import dji.midware.data.model.P3.DataOsdGetPushPowerStatus;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.midware.media.DJIVideoDecoder;
import dji.midware.media.j.g;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJILinearLayout;

public class DJICameraControlViewLongan extends DJILinearLayout implements OnClickListener {
    private static final int A = 1;
    private static final int B = 2;
    private static final int C = 3;
    private static final int D = 4;
    private static final int E = 5;
    private static final int F = 6;
    private static final int G = 7;
    private static final int[] L = new int[]{R.drawable.longan_selector_handle_mode_single, R.drawable.longan_selector_handle_mode_multiple, R.drawable.longan_selector_handle_mode_pano, R.drawable.longan_selector_handle_mode_interval, R.drawable.longan_selector_handle_mode_video_auto, R.drawable.longan_selector_handle_mode_video_timelapse, R.drawable.longan_selector_handle_mode_slow_720p, R.drawable.longan_selector_handle_mode_video_slo, R.drawable.longan_selector_handle_mode_video_timelapse};
    private static final int[] M = new int[]{R.drawable.longan_selector_parms, R.drawable.longan_selector_parms_s, R.drawable.longan_selector_parms_m, R.drawable.longan_selector_parms_a};
    public static final int c = 1765;
    public static final int d = 1465;
    public static boolean j = false;
    private boolean H = true;
    private boolean I = false;
    private boolean J = false;
    private SDCardState K = SDCardState.OTHER;
    b a;
    DJICameraAnimViewCompat b;
    dji.device.camera.a.a e = dji.device.camera.a.a.getInstance();
    c f = c.getInstance();
    e g = e.getInstance();
    dji.device.camera.a.b h = dji.device.camera.a.b.getInstance();
    d i = d.getInstance();
    protected Handler k = new Handler(new Callback(this) {
        final /* synthetic */ DJICameraControlViewLongan a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            boolean z = true;
            View view;
            switch (message.what) {
                case 1:
                    view = (View) message.obj;
                    if (view != this.a.q) {
                        if (message.arg1 != 1) {
                            z = false;
                        }
                        view.setEnabled(z);
                        break;
                    }
                    this.a.onEventMainThread(DataCameraGetPushStateInfo.getInstance());
                    break;
                case 2:
                    this.a.a((dji.device.camera.a.a.a) message.obj);
                    break;
                case 3:
                    dji.thirdparty.a.c.a().e(a.CENTER);
                    break;
                case 4:
                    if (message.arg1 != 1) {
                        this.a.f();
                        break;
                    }
                    this.a.g();
                    break;
                case 5:
                    DJIVideoDecoder e = ServiceManager.getInstance().e();
                    if (e != null) {
                        e.setConnectLosedelay(2000);
                        break;
                    }
                    break;
                case 7:
                    view = (View) message.obj;
                    if (message.arg1 != 1) {
                        z = false;
                    }
                    view.setSelected(z);
                    break;
            }
            return false;
        }
    });
    boolean l = false;
    int m = 0;
    private final String n = "DJICameraControlViewLongan";
    private LayoutParams o;
    private DJICameraSwitchView p;
    private DJIStateImageViewCompat q;
    private LonganShutterButton r;
    private DJIStateImageViewCompat s;
    private DJIStateImageViewCompat t;
    private TextView u;
    private AudioManager v;
    private Animation w;
    private Animation x;
    private TYPE y = TYPE.SINGLE;
    private DataCameraGetPushStateInfo z = DataCameraGetPushStateInfo.getInstance();

    public interface b {
        void b(boolean z);
    }

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] e = new int[o.values().length];

        static {
            g = new int[m.values().length];
            try {
                g[m.HIDE_ALL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                g[m.SHOW_ALL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                g[m.HIDE_MENU.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                g[m.SHOW_MENU.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                g[m.HIDE_PASM.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                g[m.SHOW_PASM.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            f = new int[dji.device.camera.a.d.a.values().length];
            try {
                f[dji.device.camera.a.d.a.NO.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f[dji.device.camera.a.d.a.START.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f[dji.device.camera.a.d.a.RECORDING.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f[dji.device.camera.a.d.a.STOP.ordinal()] = 4;
            } catch (NoSuchFieldError e10) {
            }
            try {
                e[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e11) {
            }
            try {
                e[o.a.ordinal()] = 2;
            } catch (NoSuchFieldError e12) {
            }
            d = new int[SDCardState.values().length];
            try {
                d[SDCardState.Full.ordinal()] = 1;
            } catch (NoSuchFieldError e13) {
            }
            try {
                d[SDCardState.Normal.ordinal()] = 2;
            } catch (NoSuchFieldError e14) {
            }
            try {
                d[SDCardState.ToFormat.ordinal()] = 3;
            } catch (NoSuchFieldError e15) {
            }
            try {
                d[SDCardState.Slow.ordinal()] = 4;
            } catch (NoSuchFieldError e16) {
            }
            c = new int[dji.device.camera.a.e.a.values().length];
            try {
                c[dji.device.camera.a.e.a.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError e17) {
            }
            try {
                c[dji.device.camera.a.e.a.TIMELAPSE.ordinal()] = 2;
            } catch (NoSuchFieldError e18) {
            }
            try {
                c[dji.device.camera.a.e.a.SLOWMOTION_720.ordinal()] = 3;
            } catch (NoSuchFieldError e19) {
            }
            try {
                c[dji.device.camera.a.e.a.SLOWMOTION_1080.ordinal()] = 4;
            } catch (NoSuchFieldError e20) {
            }
            b = new int[dji.device.camera.a.c.a.values().length];
            try {
                b[dji.device.camera.a.c.a.SINGLE_0s.ordinal()] = 1;
            } catch (NoSuchFieldError e21) {
            }
            try {
                b[dji.device.camera.a.c.a.SINGLE_5s.ordinal()] = 2;
            } catch (NoSuchFieldError e22) {
            }
            try {
                b[dji.device.camera.a.c.a.SINGLE_10s.ordinal()] = 3;
            } catch (NoSuchFieldError e23) {
            }
            try {
                b[dji.device.camera.a.c.a.SINGLE_HDR.ordinal()] = 4;
            } catch (NoSuchFieldError e24) {
            }
            try {
                b[dji.device.camera.a.c.a.BURST_3.ordinal()] = 5;
            } catch (NoSuchFieldError e25) {
            }
            try {
                b[dji.device.camera.a.c.a.BURST_5.ordinal()] = 6;
            } catch (NoSuchFieldError e26) {
            }
            try {
                b[dji.device.camera.a.c.a.BURST_7.ordinal()] = 7;
            } catch (NoSuchFieldError e27) {
            }
            try {
                b[dji.device.camera.a.c.a.AEB_3.ordinal()] = 8;
            } catch (NoSuchFieldError e28) {
            }
            try {
                b[dji.device.camera.a.c.a.AEB_5.ordinal()] = 9;
            } catch (NoSuchFieldError e29) {
            }
            try {
                b[dji.device.camera.a.c.a.PANO_AUTO.ordinal()] = 10;
            } catch (NoSuchFieldError e30) {
            }
            try {
                b[dji.device.camera.a.c.a.PANO_AUTO180.ordinal()] = 11;
            } catch (NoSuchFieldError e31) {
            }
            try {
                b[dji.device.camera.a.c.a.PANO_MANU.ordinal()] = 12;
            } catch (NoSuchFieldError e32) {
            }
            try {
                b[dji.device.camera.a.c.a.PANO_BALL.ordinal()] = 13;
            } catch (NoSuchFieldError e33) {
            }
            try {
                b[dji.device.camera.a.c.a.PANO_SELFIE.ordinal()] = 14;
            } catch (NoSuchFieldError e34) {
            }
            try {
                b[dji.device.camera.a.c.a.PANO_SECTORIAL.ordinal()] = 15;
            } catch (NoSuchFieldError e35) {
            }
            try {
                b[dji.device.camera.a.c.a.INTERVAL_3s.ordinal()] = 16;
            } catch (NoSuchFieldError e36) {
            }
            try {
                b[dji.device.camera.a.c.a.INTERVAL_5s.ordinal()] = 17;
            } catch (NoSuchFieldError e37) {
            }
            try {
                b[dji.device.camera.a.c.a.INTERVAL_10s.ordinal()] = 18;
            } catch (NoSuchFieldError e38) {
            }
            try {
                b[dji.device.camera.a.c.a.INTERVAL_30s.ordinal()] = 19;
            } catch (NoSuchFieldError e39) {
            }
            try {
                b[dji.device.camera.a.c.a.TIMELAPSE.ordinal()] = 20;
            } catch (NoSuchFieldError e40) {
            }
            a = new int[dji.device.camera.a.a.a.values().length];
            try {
                a[dji.device.camera.a.a.a.TAKEPHOTO.ordinal()] = 1;
            } catch (NoSuchFieldError e41) {
            }
            try {
                a[dji.device.camera.a.a.a.RECORD.ordinal()] = 2;
            } catch (NoSuchFieldError e42) {
            }
        }
    }

    public enum a {
        POINT,
        NO_POINT,
        CENTER
    }

    public DJICameraControlViewLongan(Context context) {
        super(context);
    }

    public DJICameraControlViewLongan(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJICameraControlViewLongan(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DJICameraControlViewLongan addAnimaView(DJICameraAnimViewCompat dJICameraAnimViewCompat) {
        this.b = dJICameraAnimViewCompat;
        return this;
    }

    public DJICameraControlViewLongan addListener(b bVar) {
        this.a = bVar;
        return this;
    }

    public void init() {
        this.o = (LayoutParams) getLayoutParams();
        h();
        a();
        resetView();
        dji.thirdparty.a.c.a().a(this);
        if (this.z.isGetted()) {
            onEventBackgroundThread(this.z);
        }
        if (DataCameraGetPushShotParams.getInstance().isGetted()) {
            onEventMainThread(DataCameraGetPushShotParams.getInstance());
            onEventBackgroundThread(DataCameraGetPushStateInfo.getInstance());
            onEventBackgroundThread(this.e);
            onEventMainThread(this.f);
            onEventMainThread(this.g);
            onEventBackgroundThread(this.i.c());
            onEventMainThread(dji.device.camera.a.b.getInstance().d());
        }
        this.v = (AudioManager) getContext().getSystemService("audio");
    }

    private void a() {
        this.p = (DJICameraSwitchView) findViewById(R.id.longan_camera_switch);
        this.p.setOnClickListener(this);
        this.q = (DJIStateImageViewCompat) findViewById(R.id.longan_camera_mode_iv);
        this.q.setOnClickListener(this);
        this.r = (LonganShutterButton) findViewById(R.id.longan_camera_control_shutter_view);
        this.r.addSoundPlayer(this.b);
        this.u = (TextView) findViewById(R.id.force_stop_remain_time_tv);
        this.s = (DJIStateImageViewCompat) findViewById(R.id.longan_pasm_iv);
        this.s.setOnClickListener(this);
        this.t = (DJIStateImageViewCompat) findViewById(R.id.longan_preview_iv);
        this.t.setOnClickListener(this);
        if (!this.I) {
            this.t.setEnabled(false);
            a(Boolean.valueOf(ServiceManager.getInstance().isOK()));
        }
        this.p.setOnModeChengeCallback(new DJICameraSwitchView.c(this) {
            final /* synthetic */ DJICameraControlViewLongan a;

            {
                this.a = r1;
            }

            public void a(MODE mode) {
                this.a.a(mode);
            }
        });
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            init();
        }
    }

    protected void onDetachedFromWindow() {
        dji.thirdparty.a.c.a().d(this);
        DataSpecialControl.getInstance().stop();
        ServiceManager.getInstance().setDataMode(false);
        g.b();
        super.onDetachedFromWindow();
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        resetView();
    }

    public void resetView() {
        if (getResources().getConfiguration().orientation == 2) {
            if (this.a != null) {
                this.a.b(true);
            }
            setOrientation(1);
            setHorizontalGravity(17);
            this.o.addRule(9);
            this.o.width = -2;
            this.o.height = -1;
            if (getVisibility() == 0) {
                startAnimation(this.w);
            }
            if (j) {
                dji.thirdparty.a.c.a().e(m.HIDE_ERROR_NOTICE);
                return;
            }
            return;
        }
        if (this.a != null) {
            this.a.b(false);
        }
        setOrientation(0);
        setVerticalGravity(17);
        this.o.addRule(12);
        this.o.width = -1;
        this.o.height = -2;
        if (getVisibility() == 0) {
            startAnimation(this.x);
        }
        if (j) {
            dji.thirdparty.a.c.a().e(m.SHOW_ERROR_NOTICE);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.longan_camera_switch) {
            DJILogHelper.getInstance().LOGD("DJICameraControlViewLongan", "longan_camera_switch clicked");
        } else if (id == R.id.longan_camera_mode_iv) {
            m();
        } else if (id == R.id.longan_pasm_iv) {
            l();
        } else if (id == R.id.longan_preview_iv) {
            dji.thirdparty.a.c.a().e(new dji.device.activity.DJIPreviewActivityLongan.a());
        }
    }

    private void b() {
        if (this.m == 0) {
            DataCameraSwitchUSB.getInstance().a(1).start(new dji.midware.e.d(this) {
                final /* synthetic */ DJICameraControlViewLongan a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.m = 1;
                    Log.d("pano", "switch usb succeed : " + this.a.m);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.m = 0;
                    Log.d("pano", "switch usb failed :" + this.a.m);
                }
            });
        } else {
            DataCameraSwitchUSB.getInstance().a(0).start(new dji.midware.e.d(this) {
                final /* synthetic */ DJICameraControlViewLongan a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.m = 0;
                    Log.d("pano", "switch usb succeed" + this.a.m);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.m = 1;
                    Log.d("pano", "switch usb failed" + this.a.m);
                }
            });
        }
    }

    private void a(dji.device.camera.a.a.a aVar) {
        if (aVar == dji.device.camera.a.a.a.RECORD) {
            if (this.p.getState() != dji.device.common.view.DJICameraSwitchView.a.RECORDING) {
                closeSettingView();
                this.p.switchToRecord(new dji.device.common.view.DJICameraSwitchView.b(this) {
                    final /* synthetic */ DJICameraControlViewLongan a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.p.setState(dji.device.common.view.DJICameraSwitchView.a.RECORDING);
                    }
                });
            }
        } else if (aVar == dji.device.camera.a.a.a.TAKEPHOTO && this.p.getState() != dji.device.common.view.DJICameraSwitchView.a.PHOTOING) {
            closeSettingView();
            this.p.switchToPhoto(new dji.device.common.view.DJICameraSwitchView.b(this) {
                final /* synthetic */ DJICameraControlViewLongan a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.p.setState(dji.device.common.view.DJICameraSwitchView.a.PHOTOING);
                }
            });
        }
    }

    private void c() {
        MODE mode = this.z.getMode() == MODE.TAKEPHOTO ? MODE.RECORD : MODE.TAKEPHOTO;
        DJILogHelper.getInstance().LOGD("DJICameraControlViewLongan", "switch camera mode to:" + mode);
        dji.device.camera.settings.a.getInstance().a(mode);
    }

    private void a(MODE mode) {
        DJILogHelper.getInstance().LOGD("DJICameraControlViewLongan", "switch camera mode to:" + mode);
        dji.device.camera.settings.a.getInstance().a(mode);
    }

    public void closeSettingView() {
        d();
        e();
        j = false;
    }

    private void d() {
        if (this.q.isSelected()) {
            this.q.setSelected(false);
            dji.thirdparty.a.c.a().e(m.HIDE_MENU);
        }
    }

    private void e() {
        if (this.s.isSelected()) {
            this.s.setSelected(false);
            dji.thirdparty.a.c.a().e(m.HIDE_PASM);
        }
    }

    private void f() {
        this.J = true;
        this.z.clear();
        DataCameraGetPushStateInfo.getInstance().clear();
        this.K = SDCardState.OTHER;
        disableAll();
    }

    private void g() {
    }

    private void a(boolean z) {
        if (getResources().getConfiguration().orientation != 2) {
            return;
        }
        if (z) {
            dji.thirdparty.a.c.a().e(m.HIDE_ERROR_NOTICE);
        } else {
            dji.thirdparty.a.c.a().e(m.SHOW_ERROR_NOTICE);
        }
    }

    public static boolean getIsSettingParms() {
        return j;
    }

    private void h() {
        this.w = AnimationUtils.loadAnimation(getContext(), R.anim.main_contain_slide_left_in);
        this.x = AnimationUtils.loadAnimation(getContext(), R.anim.main_contain_slide_bottom_in);
    }

    private void i() {
        if (this.g.b() != dji.device.camera.a.e.a.SLOWMOTION_720 && this.g.b() != dji.device.camera.a.e.a.SLOWMOTION_1080) {
            boolean b = dji.pilot.set.a.b(getContext());
            Log.d("DJICameraControlViewLongan", "is open buffer:" + b);
            if (DJIPreviewActivityLongan.openRecord && b) {
                g.a(dji.midware.media.j.g.a.GDR_ONLINE);
                g.a(dji.pilot.set.a.a(getContext()));
                dji.thirdparty.a.c.a().e(dji.midware.media.j.g.b.START_RECORD);
            }
        }
    }

    private void j() {
        if (this.g.b() != dji.device.camera.a.e.a.SLOWMOTION_720 && this.g.b() != dji.device.camera.a.e.a.SLOWMOTION_1080) {
            boolean b = dji.pilot.set.a.b(getContext());
            if (DJIPreviewActivityLongan.openRecord && b) {
                dji.thirdparty.a.c.a().e(dji.midware.media.j.g.b.END_RECORD);
            }
        }
    }

    public void setPhotoMode() {
        DataCameraSetPhotoMode.getInstance().a(this.y).a(1).c(255).b(0).d(1).start(new dji.midware.e.d(this) {
            final /* synthetic */ DJICameraControlViewLongan a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("", "DataCameraSetPhotoMode success value=1", false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("", "DataCameraSetPhotoMode fail " + aVar, false, true);
            }
        });
    }

    private void k() {
        if (i.getInstance().c() != ProductType.litchiS) {
            return;
        }
        if (this.y == TYPE.HDR || this.y == TYPE.AEB || this.y == TYPE.BURST || this.y == TYPE.SINGLE) {
            DJIVideoDecoder e = ServiceManager.getInstance().e();
            if (e != null) {
                e.setConnectLosedelay(20000);
                this.k.sendEmptyMessageDelayed(5, 18000);
            }
        }
    }

    private void a(Boolean bool) {
        int i;
        if (bool.booleanValue()) {
            i = 1;
        } else {
            i = 0;
        }
        this.k.sendMessage(this.k.obtainMessage(1, i, 0, this.p));
        this.k.sendMessage(this.k.obtainMessage(1, i, 0, this.q));
        this.k.sendMessage(this.k.obtainMessage(1, i, 0, this.s));
    }

    public void onEventBackgroundThread(dji.device.camera.a.a aVar) {
        switch (aVar.d()) {
            case TAKEPHOTO:
                if (this.p.getState() != dji.device.common.view.DJICameraSwitchView.a.PHOTOING) {
                    this.k.sendMessage(this.k.obtainMessage(2, dji.device.camera.a.a.a.TAKEPHOTO));
                    return;
                }
                return;
            case RECORD:
                if (this.p.getState() != dji.device.common.view.DJICameraSwitchView.a.RECORDING) {
                    this.k.sendMessage(this.k.obtainMessage(2, dji.device.camera.a.a.a.RECORD));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(c cVar) {
        if (this.e.d() != dji.device.camera.a.a.a.RECORD) {
            int i;
            switch (cVar.d()) {
                case SINGLE_0s:
                    i = R.drawable.longan_selector_handle_mode_singlephoto_0s;
                    break;
                case SINGLE_5s:
                    i = R.drawable.longan_selector_handle_mode_singlephoto_5s;
                    break;
                case SINGLE_10s:
                    i = R.drawable.longan_selector_handle_mode_singlephoto_10s;
                    break;
                case SINGLE_HDR:
                    i = R.drawable.longan_selector_handle_mode_hdr;
                    break;
                case BURST_3:
                    i = R.drawable.longan_selector_handle_mode_burst_3;
                    break;
                case BURST_5:
                    i = R.drawable.longan_selector_handle_mode_burst_5;
                    break;
                case BURST_7:
                    i = R.drawable.longan_selector_handle_mode_burst_7;
                    break;
                case AEB_3:
                    i = R.drawable.longan_selector_handle_mode_aeb_3;
                    break;
                case AEB_5:
                    i = R.drawable.longan_selector_handle_mode_aeb_5;
                    break;
                case PANO_AUTO:
                    i = R.drawable.longan_selector_pano_auto;
                    break;
                case PANO_AUTO180:
                    i = R.drawable.longan_selector_pano_auto_180;
                    break;
                case PANO_MANU:
                    i = R.drawable.longan_selector_pano_manu;
                    break;
                case PANO_BALL:
                    i = R.drawable.longan_selector_pano_ball;
                    break;
                case PANO_SELFIE:
                    i = R.drawable.longan_selector_pano_selfie;
                    break;
                case PANO_SECTORIAL:
                    i = R.drawable.longan_selector_pano_sectorial;
                    break;
                case INTERVAL_3s:
                    i = R.drawable.longan_selector_handle_mode_interval_3;
                    break;
                case INTERVAL_5s:
                    i = R.drawable.longan_selector_handle_mode_interval_5;
                    break;
                case INTERVAL_10s:
                    i = R.drawable.longan_selector_handle_mode_interval_10;
                    break;
                case INTERVAL_30s:
                    i = R.drawable.longan_selector_handle_mode_interval_30;
                    break;
                case TIMELAPSE:
                    i = R.drawable.longan_selector_handle_mode_video_timelapse;
                    break;
                default:
                    i = R.drawable.longan_selector_handle_mode_singlephoto_0s;
                    break;
            }
            this.q.setImageDrawable(getResources().getDrawable(i));
        }
    }

    public void onEventMainThread(e eVar) {
        int i = 4;
        Log.e("mode issue", "control received" + eVar.b() + "camera mode:" + this.e.d());
        if (this.e.d() != dji.device.camera.a.a.a.TAKEPHOTO) {
            switch (eVar.b()) {
                case TIMELAPSE:
                    i = 5;
                    break;
                case SLOWMOTION_720:
                    i = 6;
                    break;
                case SLOWMOTION_1080:
                    i = 7;
                    break;
            }
            this.q.setImageDrawable(getResources().getDrawable(L[i]));
        }
    }

    public void onEventBackgroundThread(final DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (dataCameraGetPushStateInfo.isGetted()) {
            SDCardState sDCardState = dataCameraGetPushStateInfo.getSDCardState();
            if (!(this.K._equals(sDCardState.value()) || this.J)) {
                DJILogHelper.getInstance().LOGE("DJICameraControlViewLongan", "curSdcardState=" + sDCardState, false, true);
                this.K = sDCardState;
                switch (this.K) {
                    case Full:
                        this.k.sendMessage(this.k.obtainMessage(1, 1, 0, this.t));
                        break;
                    case Normal:
                    case ToFormat:
                    case Slow:
                        this.k.sendMessage(this.k.obtainMessage(1, 1, 0, this.t));
                        break;
                    default:
                        this.k.sendMessage(this.k.obtainMessage(1, 0, 0, this.t));
                        break;
                }
            }
            if (dataCameraGetPushStateInfo.getVideoRecordTime() < d || dataCameraGetPushStateInfo.getRecordState() != DataCameraGetPushStateInfo$RecordType.STARTING) {
                if (isShown()) {
                    post(new Runnable(this) {
                        final /* synthetic */ DJICameraControlViewLongan a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.u.setVisibility(8);
                        }
                    });
                }
            } else if (dataCameraGetPushStateInfo.getVideoRecordTime() < 1765) {
                post(new Runnable(this) {
                    final /* synthetic */ DJICameraControlViewLongan b;

                    public void run() {
                        this.b.u.setVisibility(0);
                        this.b.u.setText(dji.device.common.a.a.a(1765 - dataCameraGetPushStateInfo.getVideoRecordTime(), this.b.getContext()));
                    }
                });
            } else if (e.getInstance().b() == dji.device.camera.a.e.a.AUTO) {
                DataSpecialControl.getInstance().setRecordType(false, 0, 0).start(20);
            } else {
                DataSpecialControl.getInstance().setRecordType(false, 2, 0).start(20);
            }
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (AnonymousClass2.e[oVar.ordinal()]) {
            case 1:
                Log.d("DJICameraControlViewLongan", "camera connect ok");
                this.J = false;
                this.k.sendMessage(this.k.obtainMessage(1, 1, 0, this.p));
                this.k.sendMessage(this.k.obtainMessage(1, 1, 0, this.q));
                this.k.sendMessage(this.k.obtainMessage(1, 1, 0, this.s));
                this.k.sendMessage(this.k.obtainMessage(4, 1, 0));
                return;
            case 2:
                Log.d("DJICameraControlViewLongan", "camera connect lost");
                this.k.sendMessage(this.k.obtainMessage(4, 0, 0));
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        ExposureMode exposureMode = dataCameraGetPushShotParams.getExposureMode();
        if (exposureMode == ExposureMode.b || exposureMode == ExposureMode.a) {
            this.s.setImageResource(M[0]);
        } else if (exposureMode == ExposureMode.c) {
            this.s.setImageResource(M[1]);
        } else if (exposureMode == ExposureMode.e) {
            this.s.setImageResource(M[2]);
        } else if (exposureMode == ExposureMode.d) {
            this.s.setImageResource(M[3]);
        }
    }

    public void onEventBackgroundThread(dji.device.camera.a.d.a aVar) {
        if (this.e.d() != dji.device.camera.a.a.a.TAKEPHOTO) {
            switch (aVar) {
                case NO:
                    DJILogHelper.getInstance().LOGD("DJICameraControlViewLongan", "receive state no");
                    if (DataCameraGetPushStateInfo.getInstance().getSDCardState() != SDCardState.None) {
                        this.k.sendMessage(this.k.obtainMessage(1, 1, 0, this.t));
                        this.k.sendMessage(this.k.obtainMessage(1, 1, 0, this.p));
                        this.H = true;
                        j();
                    } else {
                        this.k.sendMessage(this.k.obtainMessage(1, 1, 0, this.t));
                        this.k.sendMessage(this.k.obtainMessage(1, 1, 0, this.p));
                        this.H = true;
                        j();
                    }
                    return;
                case START:
                case STOP:
                    return;
                case RECORDING:
                    DJILogHelper.getInstance().LOGD("DJICameraControlViewLongan", "receive state RECORDING");
                    this.k.sendMessage(this.k.obtainMessage(1, 0, 0, this.t));
                    this.k.sendMessage(this.k.obtainMessage(1, 0, 0, this.p));
                    this.H = false;
                    i();
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventBackgroundThread(DJIUIEventManagerLongan.c cVar) {
        if (cVar == DJIUIEventManagerLongan.c.DISABLE_ALL) {
            disableAll();
        } else if (cVar == DJIUIEventManagerLongan.c.ENABLE_ALL) {
            enableBtns();
        }
    }

    private void l() {
        if (this.s.isSelected()) {
            dji.thirdparty.a.c.a().e(m.HIDE_PASM);
            dji.thirdparty.a.c.a().e(m.SHOW_INFO_BAR);
            return;
        }
        dji.thirdparty.a.c.a().e(m.SHOW_PASM);
        dji.thirdparty.a.c.a().e(m.HIDE_INFO_BAR);
    }

    private void m() {
        if (this.q.isSelected()) {
            dji.thirdparty.a.c.a().e(m.HIDE_MENU);
        } else {
            dji.thirdparty.a.c.a().e(m.SHOW_MENU);
        }
    }

    public void onEventMainThread(DJIUIEventManagerLongan.e eVar) {
        if (eVar == DJIUIEventManagerLongan.e.ENTER_SLEEP_MODE) {
            this.k.sendMessage(this.k.obtainMessage(4, 0, 0));
        }
    }

    public void onEventMainThread(m mVar) {
        switch (mVar) {
            case HIDE_ALL:
                setVisibility(4);
                closeSettingView();
                break;
            case SHOW_ALL:
                setVisibility(0);
                break;
            case HIDE_MENU:
                this.q.setSelected(false);
                break;
            case SHOW_MENU:
                e();
                this.q.setSelected(true);
                break;
            case HIDE_PASM:
                this.s.setSelected(false);
                break;
            case SHOW_PASM:
                d();
                this.s.setSelected(true);
                break;
            default:
                return;
        }
        if (this.s.isSelected() || this.q.isSelected()) {
            j = true;
            a(true);
            return;
        }
        j = false;
        dji.thirdparty.a.c.a().e(m.EXIT_SETTING);
        a(false);
    }

    public void onEventMainThread(dji.device.camera.a.b.b bVar) {
        if (bVar == dji.device.camera.a.b.b.SAVING) {
            disableAll();
        } else if (bVar == dji.device.camera.a.b.b.SAVING_NOT) {
            enableBtns();
        }
    }

    public void onEventMainThread(dji.device.camera.a.b.a aVar) {
        if (aVar != dji.device.camera.a.b.a.PHOTO_ING) {
        }
    }

    public void onEventMainThread(DJIUIEventManagerLongan.g gVar) {
        if (gVar == DJIUIEventManagerLongan.g.PANO_START) {
            disableAll();
        } else if (gVar == DJIUIEventManagerLongan.g.PANO_FINISH) {
            enableBtns();
        }
    }

    public void onEventMainThread(dji.device.camera.a.d.a aVar) {
        if (aVar == dji.device.camera.a.d.a.RECORDING) {
            closeSettingView();
            disableAll();
            this.k.sendMessageDelayed(this.k.obtainMessage(1, 1, 0, this.s), 50);
        } else if (aVar == dji.device.camera.a.d.a.NO) {
            enableBtns();
        }
    }

    public void onEventMainThread(dji.device.camera.a.b.c cVar) {
        if (cVar == dji.device.camera.a.b.c.TIMING) {
            disableAll();
            this.k.sendMessageDelayed(this.k.obtainMessage(1, 1, 0, this.s), 50);
            closeSettingView();
            if (this.q.isSelected()) {
                this.q.setSelected(false);
            }
        } else if (cVar == dji.device.camera.a.b.c.NOT_TIMING) {
            enableBtns();
        }
    }

    public void disableAll() {
        this.k.sendMessageDelayed(this.k.obtainMessage(1, 0, 0, this.t), 50);
        this.k.sendMessageDelayed(this.k.obtainMessage(1, 0, 0, this.p), 50);
        this.k.sendMessageDelayed(this.k.obtainMessage(1, 0, 0, this.q), 50);
        this.k.sendMessageDelayed(this.k.obtainMessage(1, 0, 0, this.s), 50);
    }

    public void enableBtns() {
        if (ServiceManager.getInstance().isConnected()) {
            this.k.sendMessageDelayed(this.k.obtainMessage(1, 1, 0, this.t), 50);
            this.k.sendMessageDelayed(this.k.obtainMessage(1, 1, 0, this.p), 50);
            this.k.sendMessageDelayed(this.k.obtainMessage(1, 1, 0, this.q), 50);
            this.k.sendMessageDelayed(this.k.obtainMessage(1, 1, 0, this.s), 50);
            if (d.getInstance().c() == dji.device.camera.a.d.a.RECORDING || dji.device.camera.a.b.getInstance().d() == dji.device.camera.a.b.c.TIMING) {
                this.k.sendMessageDelayed(this.k.obtainMessage(1, 0, 0, this.p), 50);
                this.k.sendMessageDelayed(this.k.obtainMessage(1, 0, 0, this.t), 50);
                this.k.sendMessageDelayed(this.k.obtainMessage(1, 0, 0, this.q), 50);
            }
            if (dji.device.camera.a.b.getInstance().e() == dji.device.camera.a.b.b.SAVING) {
                this.k.sendMessageDelayed(this.k.obtainMessage(1, 0, 0, this.s), 50);
                this.k.sendMessageDelayed(this.k.obtainMessage(1, 0, 0, this.p), 50);
                this.k.sendMessageDelayed(this.k.obtainMessage(1, 0, 0, this.t), 50);
                this.k.sendMessageDelayed(this.k.obtainMessage(1, 0, 0, this.q), 50);
            }
            onEventBackgroundThread(DataCameraGetPushStateInfo.getInstance());
        }
    }

    public void onEventMainThread(DataOsdGetPushPowerStatus dataOsdGetPushPowerStatus) {
        if (dataOsdGetPushPowerStatus.getIsPowerOff()) {
            closeSettingView();
        }
    }

    public void onEventMainThread(dji.device.camera.a.a aVar) {
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if ((dji.logic.f.b.h(null) && dji.device.camera.a.a.getInstance().d() == dji.device.camera.a.a.a.RECORD) || d.getInstance().c() == dji.device.camera.a.d.a.RECORDING || dji.device.camera.a.b.getInstance().d() == dji.device.camera.a.b.c.TIMING || dji.device.camera.a.b.getInstance().e() == dji.device.camera.a.b.b.SAVING) {
            this.q.setEnabled(false);
        } else {
            this.q.setEnabled(true);
        }
    }
}
