package dji.phone.controview;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataGimbalCameraIdNotify;
import dji.midware.data.model.P3.DataGimbalSetTimelapseParams;
import dji.midware.data.model.P3.DataGimbalSpeedControl;
import dji.midware.data.model.P3.DataOsdSetLED;
import dji.midware.e.d;
import dji.phone.e.a.e;
import dji.phone.menu.DJILPCameraLevel1MenuView;
import dji.phone.menu.DJILPCameraLevel2MenuView;
import dji.phone.preview.DJILPPreviewActivity;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.HashMap;

public class a implements dji.phone.controview.DJILPCameraModuleSwitcher.a, dji.phone.pano.c {
    private int A = Integer.MAX_VALUE;
    private int[] B = new int[]{R.drawable.lp_ic_battery_lv1, R.drawable.lp_ic_battery_lv2, R.drawable.lp_ic_battery_lv3, R.drawable.lp_ic_battery_lv4, R.drawable.lp_ic_battery_lv5, R.drawable.lp_ic_battery_lv6, R.drawable.lp_ic_battery_lv7, R.drawable.lp_ic_battery_lv8, R.drawable.lp_ic_battery_lv9};
    private long C;
    private ObjectAnimator D = null;
    private final Handler E = new c();
    private final int F = 1;
    private final int G = 2;
    private final int H = 3;
    private final int I = 4;
    private final int J = 5;
    private final int K = 6;
    private final int L = 7;
    private final int M = 8;
    private int N = 0;
    private boolean O = false;
    private boolean P = false;
    private boolean Q = false;
    private boolean R = false;
    private boolean S = false;
    private boolean T = false;
    private boolean U = false;
    private boolean V = false;
    private boolean W = false;
    private boolean X = true;
    private boolean Y = false;
    private boolean Z = false;
    public DJILPCameraControView a;
    private int aa = 0;
    private Animation ab;
    private SoundPool ac;
    private int ad;
    private int ae;
    private int af;
    private a ag;
    private HandlerThread ah;
    DJILPCameraRecordBottomBar b;
    public HashMap<String, String> c = new HashMap();
    DataGimbalSetTimelapseParams d;
    MediaRecorder e;
    View f;
    Activity g;
    final Object h = new Object();
    Runnable i = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.n();
        }
    };
    d j = new d(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            Log.d("CameraControPresenter", "onSuccess: " + dji.midware.util.c.i(((DataGimbalCameraIdNotify) obj).a().r));
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            Log.d("CameraControPresenter", "onFailure: " + aVar.name());
            DJILogHelper.getInstance().LOGD("CameraControPresenter", "onFailure: " + aVar.name(), false, true);
            this.a.E.post(new Runnable(this) {
                final /* synthetic */ AnonymousClass8 a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.j();
                }
            });
        }
    };
    private final String k = "CameraControPresenter";
    private LinearLayout l;
    private LinearLayout m;
    private DJIImageView n;
    private DJIImageView o;
    private DJITextView p;
    private ImageView q;
    private DJILPCameraLevel1MenuView r;
    private DJILPCameraLevel2MenuView s;
    private DJIStateImageViewCompat t;
    private DJILPCameraZoomSeekBar u;
    private DJILPCameraModuleSwitcher v;
    private boolean w = false;
    private View x;
    private TextView y;
    private boolean z = true;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[dji.pilot.phonecamera.a.a.values().length];

        static {
            f = new int[dji.phone.d.a.c.values().length];
            try {
                f[dji.phone.d.a.c.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f[dji.phone.d.a.c.TIMELAPSE_MOTION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f[dji.phone.d.a.c.TIMELAPSE_STATIONARY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            e = new int[dji.phone.d.a.a.values().length];
            try {
                e[dji.phone.d.a.a.SINGLE_0s.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                e[dji.phone.d.a.a.SINGLE_2s.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                e[dji.phone.d.a.a.SINGLE_5s.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                e[dji.phone.d.a.a.SINGLE_10s.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                e[dji.phone.d.a.a.LONGEXPOSURE_4s.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                e[dji.phone.d.a.a.LONGEXPOSURE_8s.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            try {
                e[dji.phone.d.a.a.LONGEXPOSURE_16s.ordinal()] = 7;
            } catch (NoSuchFieldError e10) {
            }
            try {
                e[dji.phone.d.a.a.LONGEXPOSURE_32s.ordinal()] = 8;
            } catch (NoSuchFieldError e11) {
            }
            try {
                e[dji.phone.d.a.a.LONGEXPOSURE_INFINITY.ordinal()] = 9;
            } catch (NoSuchFieldError e12) {
            }
            d = new int[dji.phone.d.c.b.values().length];
            try {
                d[dji.phone.d.c.b.CAMERA_BACK.ordinal()] = 1;
            } catch (NoSuchFieldError e13) {
            }
            try {
                d[dji.phone.d.c.b.CAMERA_FRONT.ordinal()] = 2;
            } catch (NoSuchFieldError e14) {
            }
            c = new int[b.values().length];
            try {
                c[b.SWITCH_TO_PHOTO.ordinal()] = 1;
            } catch (NoSuchFieldError e15) {
            }
            try {
                c[b.SWITCH_TO_VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError e16) {
            }
            try {
                c[b.SWITCH_CAMERA_TO_BACK.ordinal()] = 3;
            } catch (NoSuchFieldError e17) {
            }
            try {
                c[b.SWITCH_CAMERA_TO_FRONT.ordinal()] = 4;
            } catch (NoSuchFieldError e18) {
            }
            try {
                c[b.TAKEPHOTO.ordinal()] = 5;
            } catch (NoSuchFieldError e19) {
            }
            try {
                c[b.START_RECORD.ordinal()] = 6;
            } catch (NoSuchFieldError e20) {
            }
            try {
                c[b.STOP_RECORD.ordinal()] = 7;
            } catch (NoSuchFieldError e21) {
            }
            b = new int[dji.phone.b.a.values().length];
            try {
                b[dji.phone.b.a.CMD_SWITCH_ID.ordinal()] = 1;
            } catch (NoSuchFieldError e22) {
            }
            try {
                b[dji.phone.b.a.CMD_TAKEPICTURE.ordinal()] = 2;
            } catch (NoSuchFieldError e23) {
            }
            try {
                b[dji.phone.b.a.CMD_START_RECORD.ordinal()] = 3;
            } catch (NoSuchFieldError e24) {
            }
            try {
                b[dji.phone.b.a.CMD_STOP_RECORD.ordinal()] = 4;
            } catch (NoSuchFieldError e25) {
            }
            try {
                b[dji.phone.b.a.CMD_RECORD.ordinal()] = 5;
            } catch (NoSuchFieldError e26) {
            }
            try {
                b[dji.phone.b.a.CMD_LOCK_AEAF.ordinal()] = 6;
            } catch (NoSuchFieldError e27) {
            }
            try {
                b[dji.phone.b.a.CMD_UNLOCK_AEAF.ordinal()] = 7;
            } catch (NoSuchFieldError e28) {
            }
            try {
                a[dji.pilot.phonecamera.a.a.b.ordinal()] = 1;
            } catch (NoSuchFieldError e29) {
            }
            try {
                a[dji.pilot.phonecamera.a.a.c.ordinal()] = 2;
            } catch (NoSuchFieldError e30) {
            }
            try {
                a[dji.pilot.phonecamera.a.a.g.ordinal()] = 3;
            } catch (NoSuchFieldError e31) {
            }
        }
    }

    private class a extends Handler {
        final /* synthetic */ a a;

        a(a aVar, Looper looper) {
            this.a = aVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch ((b) message.obj) {
                case SWITCH_TO_PHOTO:
                    Log.d("CameraControPresenter", "handleMessage: SWITCH_TO_PHOTO");
                    dji.phone.c.a.a(0);
                    dji.phone.d.d.getInstance().a(dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE_PREVIEW, true);
                    this.a.P = false;
                    return;
                case SWITCH_TO_VIDEO:
                    Log.d("CameraControPresenter", "handleMessage: SWITCH_TO_VIDEO");
                    dji.phone.c.a.a(1);
                    dji.phone.d.d.getInstance().a(dji.phone.d.c.a.CAMERASTATE_RECORD_PREVIEW, true);
                    this.a.P = false;
                    return;
                case TAKEPHOTO:
                    this.a.Z = true;
                    dji.phone.c.a.c().d();
                    this.a.E.post(new Runnable(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.d();
                        }
                    });
                    this.a.t();
                    return;
                case START_RECORD:
                    Log.d("CameraControPresenter", "handleMessage: START_RECORD");
                    int e = dji.phone.c.a.c().e();
                    if (e == 0) {
                        this.a.E.sendEmptyMessage(6);
                    } else {
                        Message obtainMessage = this.a.E.obtainMessage();
                        obtainMessage.what = 5;
                        obtainMessage.arg1 = e;
                        this.a.E.sendMessage(obtainMessage);
                    }
                    this.a.u();
                    return;
                case STOP_RECORD:
                    Log.d("CameraControPresenter", "handleMessage: STOP_RECORD");
                    synchronized (this.a.h) {
                        if (!this.a.V) {
                            try {
                                this.a.h.wait();
                            } catch (InterruptedException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    if (dji.phone.c.a.b()) {
                        dji.phone.c.a.c().f();
                        this.a.E.sendEmptyMessage(7);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private enum b {
        SWITCH_TO_PHOTO,
        SWITCH_TO_VIDEO,
        SWITCH_CAMERA_TO_BACK,
        SWITCH_CAMERA_TO_FRONT,
        SWITCH_CAMERA,
        TAKEPHOTO,
        RECORD,
        START_RECORD,
        STOP_RECORD
    }

    private class c extends Handler {
        final /* synthetic */ a a;

        private c(a aVar) {
            this.a = aVar;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.r();
                    return;
                case 2:
                    this.a.c();
                    return;
                case 3:
                    this.a.q();
                    return;
                case 5:
                    this.a.c(message.arg1);
                    return;
                case 6:
                    this.a.c(0);
                    return;
                case 7:
                    this.a.d(true);
                    return;
                case 8:
                    Log.d("CameraControPresenter", "handleMessage: TAKE_PICTURE isSwitchingModule = " + this.a.P);
                    if (this.a.P) {
                        this.a.E.sendEmptyMessageDelayed(8, 500);
                        return;
                    } else {
                        this.a.m();
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public a(View view, Activity activity) {
        this.f = view;
        this.g = activity;
        l();
    }

    private void l() {
        this.ah = new HandlerThread("CameraHandler Thread");
        this.ah.start();
        this.ag = new a(this, this.ah.getLooper());
        this.ab = AnimationUtils.loadAnimation(this.g, R.anim.count_down_exit);
        this.ac = new SoundPool(1, 5, 0);
        this.ae = this.ac.load(this.g, R.raw.beep_once, 1);
        this.ad = this.ac.load(this.g, R.raw.beep_quarter, 1);
        this.af = this.ac.load(this.g, R.raw.beep_ten, 1);
        this.a = (DJILPCameraControView) this.f.findViewById(R.id.lp_preview_cameracontrol);
        this.b = (DJILPCameraRecordBottomBar) this.f.findViewById(R.id.longan_bottom_bar_layout);
        this.r = (DJILPCameraLevel1MenuView) this.f.findViewById(R.id.lp_level1_menu_layout);
        this.s = (DJILPCameraLevel2MenuView) this.f.findViewById(R.id.lp_level2_menu_layout);
        this.y = (TextView) this.f.findViewById(R.id.lp_countdown_view);
        this.a.setCameraPresenter(this);
        this.r.setCameraPresenter(this);
        this.s.setCameraPresenter(this);
        this.t = (DJIStateImageViewCompat) this.a.findViewById(R.id.longan_camera_mode_iv);
        this.l = (LinearLayout) this.f.findViewById(R.id.lp_battery_flash_view);
        this.m = (LinearLayout) this.f.findViewById(R.id.lp_battery_view);
        this.n = (DJIImageView) this.f.findViewById(R.id.topbar_battery_iv);
        this.o = (DJIImageView) this.f.findViewById(R.id.topbar_battery_onchange);
        this.p = (DJITextView) this.f.findViewById(R.id.topbar_battery_value);
        this.q = (ImageView) this.f.findViewById(R.id.topbar_flash_iv);
        this.x = this.f.findViewById(R.id.flash_overlay);
        this.u = (DJILPCameraZoomSeekBar) this.f.findViewById(R.id.lp_zoom_info_top_bar_layout);
        this.v = (DJILPCameraModuleSwitcher) this.f.findViewById(R.id.longan_camera_switch);
        this.W = dji.phone.bluetooth.c.getInstance().b();
        dji.thirdparty.a.c.a().a(this);
    }

    public void onEventMainThread(dji.pilot.d.a aVar) {
        if (aVar.b(0) == 2) {
            this.q.setImageResource(aVar.b(1));
        }
    }

    public void onEventMainThread(dji.phone.h.b bVar) {
        if (bVar.a(dji.phone.h.b.ROTATION_90.b()) || bVar.a(dji.phone.h.b.ROTATION_270.b())) {
            this.l.setVisibility(4);
        } else {
            this.l.setVisibility(0);
        }
    }

    public void a(int i) {
        Log.d("CameraControPresenter", "switchModule: mCurrentModuleId = " + i);
        this.P = true;
        if (this.T || this.w || this.Z) {
            Log.d("CameraControPresenter", "switchModule: isTimerTakingPicture = " + this.T + " isLongExposure = " + this.w + " isTakePicture = " + this.Z);
            this.P = false;
            this.v.setEnabled(true);
        } else if (this.O) {
            Log.d("CameraControPresenter", "switchModule: mMediaRecorderRecording");
            this.P = false;
            this.v.setEnabled(true);
        } else {
            Message obtainMessage;
            if (((DJILPPreviewActivity) this.g).mLongExposurePresenter.d()) {
                ((DJILPPreviewActivity) this.g).mLongExposurePresenter.e();
                a(false);
            }
            if (i == 0) {
                obtainMessage = this.ag.obtainMessage();
                obtainMessage.obj = b.SWITCH_TO_PHOTO;
                this.ag.sendMessage(obtainMessage);
                this.a.switchToPhotoUI();
                if (this.t.isSelected()) {
                    this.r.switchModuleToPhoto();
                }
            }
            if (i == 1) {
                obtainMessage = this.ag.obtainMessage();
                obtainMessage.obj = b.SWITCH_TO_VIDEO;
                this.ag.sendMessage(obtainMessage);
                this.a.switchToVideoUI();
                if (this.t.isSelected()) {
                    this.r.switchModuleToVideo();
                }
            }
        }
    }

    public void b(int i) {
        if (((DJILPPreviewActivity) this.g).mLongExposurePresenter.d()) {
            ((DJILPPreviewActivity) this.g).mLongExposurePresenter.e();
            a(false);
        }
        try {
            dji.phone.c.a.c().b(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dji.phone.bluetooth.c.getInstance().b()) {
            j();
        }
    }

    public void a() {
        dji.thirdparty.a.c.a().e(new dji.phone.e.b(e.BTN_CAMERA_ID_SWITCHER, dji.phone.e.a.c.c));
        dji.phone.preview.a.getInstance().d();
        b(-1);
    }

    public void a(boolean z) {
        if (!z) {
            DJILogHelper.getInstance().LOGD("CameraControPresenter", "notifyGimbalLongExposure: stop", false, true);
            this.d.b(1).a(1).c(1).start(new d(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    DJILogHelper.getInstance().LOGD("CameraControPresenter", "onSuccess: ", false, true);
                    this.a.S = false;
                    this.a.N = 0;
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD("CameraControPresenter", "onFailure: " + aVar.name(), false, true);
                    if (this.a.N > 5) {
                        this.a.N = 0;
                        return;
                    }
                    this.a.N = this.a.N + 1;
                    this.a.a(false);
                }
            });
        } else if (!this.S) {
            DJILogHelper.getInstance().LOGD("CameraControPresenter", "notifyGimbalLongExposure: start", false, true);
            this.d = new DataGimbalSetTimelapseParams();
            this.d.b(1).a(1).c(0).start(new d(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    DJILogHelper.getInstance().LOGD("CameraControPresenter", "onSuccess: ", false, true);
                    this.a.S = true;
                    this.a.N = 0;
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD("CameraControPresenter", "onFailure: " + aVar.name(), false, true);
                    if (this.a.N > 5) {
                        this.a.N = 0;
                        return;
                    }
                    this.a.N = this.a.N + 1;
                    this.a.a(true);
                }
            });
        }
    }

    public synchronized void b(boolean z) {
        this.w = z;
        this.Z = false;
        t();
    }

    public void b() {
        if (this.X && !this.O) {
            this.X = false;
            this.c.put(dji.publics.b.a.b.i, "1");
        }
        m();
    }

    private void m() {
        Log.d("CameraControPresenter", "takePicture: ");
        if (this.T) {
            this.c.clear();
            this.X = true;
            this.U = true;
        } else if (this.O) {
            dji.phone.c.a.c().d();
            d();
        } else if (!dji.pilot.phonecamera.a.c.a().t().equals(dji.pilot.phonecamera.a.c.y)) {
            if (dji.pilot.phonecamera.a.c.a().t().equals(dji.pilot.phonecamera.a.c.x)) {
                this.aa = dji.pilot.phonecamera.a.c.a().q();
                if (this.aa > 0) {
                    this.T = true;
                    dji.phone.d.d.getInstance().a(dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE, true);
                    this.y.setVisibility(0);
                    c();
                    return;
                }
                this.C = SystemClock.uptimeMillis();
                Message obtainMessage = this.ag.obtainMessage();
                obtainMessage.obj = b.TAKEPHOTO;
                this.ag.sendMessage(obtainMessage);
            }
            if (dji.pilot.phonecamera.a.c.a().t().equals("pano")) {
                dji.phone.d.a.a j = dji.phone.d.d.getInstance().j();
                boolean z = dji.pilot.phonecamera.d.a().k() == dji.pilot.phonecamera.d.a().j();
                dji.phone.pano.d dVar = dji.phone.pano.d.P_180;
                if (j == dji.phone.d.a.a.PANO_180) {
                    dVar = dji.phone.pano.d.P_180;
                } else if (j == dji.phone.d.a.a.PANO_330) {
                    dVar = dji.phone.pano.d.P_330;
                } else if (j == dji.phone.d.a.a.PANO_WIDE) {
                    dVar = dji.phone.pano.d.P_WIDE;
                }
                dji.phone.preview.a.getInstance().a(dVar, z);
            }
        } else if (((DJILPPreviewActivity) this.g).mLongExposurePresenter.d()) {
            ((DJILPPreviewActivity) this.g).mLongExposurePresenter.e();
            this.a.switchToTakePhotoUI(false);
            dji.phone.d.d.getInstance().a(dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE_PREVIEW, true);
            this.w = false;
            this.Z = false;
            a(false);
        } else {
            if (!this.S) {
                a(true);
            }
            dji.phone.d.d.getInstance().a(dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE, true);
            this.w = true;
            this.Z = true;
            ((DJILPPreviewActivity) this.g).mLongExposurePresenter.b();
            this.a.switchToTakePhotoUI(true);
        }
    }

    public void c() {
        if (this.U) {
            this.E.removeMessages(2);
            this.y.setVisibility(8);
            this.a.switchToTakePhotoUI(false);
            this.T = false;
            this.U = false;
            dji.phone.d.d.getInstance().a(dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE_PREVIEW, true);
            return;
        }
        new DataOsdSetLED().a().c(1, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 16, 1).start(null);
        this.a.switchToTakePhotoUI(true);
        this.y.setText(Integer.toString(this.aa));
        this.ab.reset();
        this.y.clearAnimation();
        this.y.startAnimation(this.ab);
        if (this.z) {
            if (this.aa == 1) {
                this.ac.play(this.af, 1.0f, 1.0f, 0, 0, 1.0f);
            } else if (this.aa == 2) {
                this.ac.play(this.ad, 1.0f, 1.0f, 0, 0, 1.0f);
            } else if (this.aa > 2) {
                this.ac.play(this.ae, 1.0f, 1.0f, 0, 0, 1.0f);
            }
        }
        if (this.aa > 0) {
            this.aa--;
            this.E.sendEmptyMessageDelayed(2, 1000);
            return;
        }
        this.T = false;
        this.y.setVisibility(8);
        Message obtainMessage = this.ag.obtainMessage();
        obtainMessage.obj = b.TAKEPHOTO;
        this.ag.sendMessage(obtainMessage);
        this.a.switchToTakePhotoUI(false);
    }

    public void c(boolean z) {
        this.Z = false;
        if (dji.phone.d.d.getInstance().h() == dji.phone.d.a.b.SINGLE) {
            dji.phone.d.d.getInstance().a(dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE_PREVIEW, true);
        }
    }

    public void onEventMainThread(dji.pilot.phonecamera.a.a aVar) {
        switch (AnonymousClass2.a[aVar.ordinal()]) {
            case 1:
                c(true);
                return;
            case 2:
                b(-1);
                return;
            default:
                return;
        }
    }

    public void d() {
        if (this.D != null && this.D.isRunning()) {
            this.D.cancel();
        }
        this.D = ObjectAnimator.ofFloat(this.x, "alpha", new float[]{0.3f, 0.0f});
        this.D.setDuration(500);
        this.D.addListener(new AnimatorListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animator animator) {
                this.a.x.setVisibility(0);
            }

            public void onAnimationEnd(Animator animator) {
                this.a.x.setAlpha(0.0f);
                this.a.x.setVisibility(8);
                this.a.D.removeAllListeners();
                this.a.D = null;
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        this.D.start();
    }

    public void e() {
        if (this.X && !this.O) {
            this.X = false;
            this.c.put(dji.publics.b.a.b.i, "1");
        }
        n();
    }

    private void n() {
        Log.d("CameraControPresenter", "startVideoRecording: ");
        if (this.w || this.T) {
            this.c.clear();
            this.X = true;
        } else if (this.P) {
            Log.d("CameraControPresenter", "startVideoRecording: postDelayed");
            this.E.removeCallbacks(this.i);
            this.E.postDelayed(this.i, 500);
        } else if (this.Q) {
            this.c.clear();
            this.X = true;
        } else if (this.R) {
            this.c.clear();
            this.X = true;
        } else {
            this.O = true;
            this.Q = true;
            Message obtainMessage = this.ag.obtainMessage();
            obtainMessage.obj = b.START_RECORD;
            this.ag.sendMessage(obtainMessage);
        }
    }

    public void c(int i) {
        boolean z = true;
        if (i == 0) {
            dji.thirdparty.a.c.a().e(dji.phone.set.gimbalplan.a.a.WEAKEN);
            this.e = dji.phone.c.a.f();
            this.a.switchToRecordingUI(true);
            this.b.show(true);
            this.C = SystemClock.uptimeMillis();
            if (this.u.isShown()) {
                this.u.setVisibility(4);
            }
            r();
            q();
            dji.phone.d.d.getInstance().a(dji.phone.d.c.a.CAMERASTATE_RECORDING, true);
        } else {
            this.O = false;
            if (i == -3 || i == -5) {
                this.E.post(new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.o();
                    }
                });
            }
        }
        this.Q = false;
        Log.d("CameraControPresenter", "startVideoForResult: " + i);
        DJILPCameraShutterButton dJILPCameraShutterButton = this.a.b;
        if (i != 0) {
            z = false;
        }
        dJILPCameraShutterButton.updateVideoRecording(z);
    }

    private void o() {
        Log.d("CameraControPresenter", "showStartRecordingFailureDialog: ");
        final dji.phone.a.c cVar = new dji.phone.a.c(this.g, R.style.LpBaseDialog);
        cVar.a(this.g.getString(R.string.lp_recording_audio_permission_denied));
        cVar.a(false);
        cVar.a(0, false);
        cVar.a(1, new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings"));
                intent.setAction("android.intent.action.VIEW");
                cVar.dismiss();
                this.b.g.finish();
                this.b.g.startActivity(intent);
            }
        });
        cVar.show();
    }

    private void p() {
        if (this.E.hasMessages(3)) {
            this.E.removeMessages(3);
        }
    }

    private void q() {
        Log.d("CameraControPresenter", "sendGimbalArgularSpeedIfSupported: pitch = " + dji.pilot.phonecamera.a.c.a().i() + " yaw = " + dji.pilot.phonecamera.a.c.a().j());
        if (dji.pilot.phonecamera.a.c.a().h()) {
            DataGimbalSpeedControl.getInstance().setPermission(true);
            DataGimbalSpeedControl.getInstance().setPitch(dji.pilot.phonecamera.a.c.a().i() * 10);
            DataGimbalSpeedControl.getInstance().setYaw(dji.pilot.phonecamera.a.c.a().j() * 10);
            DataGimbalSpeedControl.getInstance().start();
            this.E.sendEmptyMessageDelayed(3, 100);
        }
    }

    public void f() {
        Log.d("CameraControPresenter", "stopVideoRecording: ");
        if (!this.R && !this.Q) {
            this.O = false;
            this.R = true;
            Message obtainMessage = this.ag.obtainMessage();
            obtainMessage.obj = b.STOP_RECORD;
            this.ag.sendMessage(obtainMessage);
        }
    }

    public void d(boolean z) {
        Log.d("CameraControPresenter", "stopVideoForResult: ");
        dji.thirdparty.a.c.a().e(dji.phone.set.gimbalplan.a.a.STRENGTHEN);
        dji.pilot.phonecamera.a.c.a().a(0.0f);
        dji.pilot.phonecamera.a.c.a().n(0);
        this.a.switchToRecordingUI(false);
        this.b.show(false);
        p();
        this.a.b.updateVideoRecording(false);
        dji.phone.d.d.getInstance().a(dji.phone.d.c.a.CAMERASTATE_RECORD_PREVIEW, true);
        this.e = null;
        this.R = false;
    }

    private void r() {
        Log.d("CameraControPresenter", "updateRecordingTimeAndVoice: ");
        if (this.O) {
            long uptimeMillis = ((SystemClock.uptimeMillis() - this.C) - 500) / 1000;
            long v = ((long) dji.pilot.phonecamera.a.c.a().v()) - uptimeMillis;
            this.b.updateTimeTv(uptimeMillis, v);
            if (dji.phone.d.d.getInstance().i() == dji.phone.d.a.c.TIMELAPSE_MOTION || dji.phone.d.d.getInstance().i() == dji.phone.d.a.c.TIMELAPSE_STATIONARY) {
                this.b.hideVoiceView();
            } else if (this.e != null) {
                Log.d("CameraControPresenter", "mMediaRecorder != null updateRecordingTimeAndVoice: ");
                this.b.updateAudioVolume((long) this.e.getMaxAmplitude());
            }
            if (v <= 0) {
                f();
                this.E.sendEmptyMessageDelayed(1, 300);
                return;
            }
            this.E.sendEmptyMessageDelayed(1, 300);
            return;
        }
        synchronized (this.h) {
            this.V = true;
            this.h.notifyAll();
        }
    }

    public void d(int i) {
        a(i);
    }

    public void g() {
        this.a.switchToTakePhotoUI(true);
        s();
    }

    private void s() {
        dji.phone.c.a.c().a(true);
    }

    public void a(int i, int i2, int i3) {
        if (i == 1) {
            dji.phone.k.b.showLong(this.a.getContext().getString(R.string.lp_pano_taking_txt) + ":" + i2 + dji.pilot.usercenter.protocol.d.t + i3);
        } else {
            this.a.switchToTakePhotoUI(false);
        }
    }

    public void a(int i, String str) {
        this.a.switchToTakePhotoUI(false);
        dji.phone.c.a.c().a(false);
    }

    public void onEventBackgroundThread(dji.phone.b.a aVar) {
        switch (aVar) {
            case CMD_SWITCH_ID:
                b(-1);
                return;
            case CMD_TAKEPICTURE:
                Log.d("CameraControPresenter", "onEventBackgroundThread: CMD_TAKEPICTURE");
                if (this.X && !this.O) {
                    this.X = false;
                    this.c.put(dji.publics.b.a.b.i, "2");
                }
                this.E.sendEmptyMessage(8);
                return;
            case CMD_START_RECORD:
                if (this.X && !this.O) {
                    this.X = false;
                    this.c.put(dji.publics.b.a.b.i, "1");
                }
                if (!this.O) {
                    n();
                    return;
                }
                return;
            case CMD_STOP_RECORD:
                if (this.O) {
                    f();
                    return;
                }
                return;
            case CMD_RECORD:
                if (this.X && !this.O) {
                    this.X = false;
                    this.c.put(dji.publics.b.a.b.i, "2");
                }
                if (this.O) {
                    f();
                    return;
                } else {
                    n();
                    return;
                }
            case CMD_LOCK_AEAF:
                dji.phone.c.a.c().a(true);
                return;
            case CMD_UNLOCK_AEAF:
                dji.phone.c.a.c().a(false);
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(dji.pilot.phonecamera.a.a aVar) {
        switch (AnonymousClass2.a[aVar.ordinal()]) {
            case 3:
                f();
                return;
            default:
                return;
        }
    }

    private synchronized void t() {
        switch (dji.phone.d.d.getInstance().b()) {
            case CAMERA_BACK:
                dji.publics.b.b.a.getInstance().b(dji.publics.b.a.b.v, "1", false);
                break;
            case CAMERA_FRONT:
                dji.publics.b.b.a.getInstance().b(dji.publics.b.a.b.v, "2", false);
                break;
        }
        switch (dji.phone.d.d.getInstance().j()) {
            case SINGLE_0s:
                dji.publics.b.b.a.getInstance().b("action", "1", false);
                break;
            case SINGLE_2s:
                dji.publics.b.b.a.getInstance().b("action", "2", false);
                break;
            case SINGLE_5s:
                dji.publics.b.b.a.getInstance().b("action", "3", false);
                break;
            case SINGLE_10s:
                dji.publics.b.b.a.getInstance().b("action", "4", false);
                break;
            case LONGEXPOSURE_4s:
                dji.publics.b.b.a.getInstance().b("action", "5", false);
                break;
            case LONGEXPOSURE_8s:
                dji.publics.b.b.a.getInstance().b("action", "6", false);
                break;
            case LONGEXPOSURE_16s:
                dji.publics.b.b.a.getInstance().b("action", "7", false);
                break;
            case LONGEXPOSURE_32s:
                dji.publics.b.b.a.getInstance().b("action", "8", false);
                break;
            case LONGEXPOSURE_INFINITY:
                dji.publics.b.b.a.getInstance().b("action", "9", false);
                break;
        }
        dji.publics.b.b.a.getInstance().b("createtime", System.currentTimeMillis() + "", false).b(dji.publics.b.a.b.i, (String) this.c.get(dji.publics.b.a.b.i), false).b("device_type", i.getInstance().c()._name(), false).b("pro_id", dji.publics.b.b.a.a, false).b("device_ver", "0.0.0.0", true);
        this.c.clear();
        this.X = true;
    }

    private synchronized void u() {
        switch (dji.phone.d.d.getInstance().b()) {
            case CAMERA_BACK:
                dji.publics.b.b.a.getInstance().c(dji.publics.b.a.b.v, "1", false);
                break;
            case CAMERA_FRONT:
                dji.publics.b.b.a.getInstance().c(dji.publics.b.a.b.v, "2", false);
                break;
        }
        switch (dji.phone.d.d.getInstance().i()) {
            case AUTO:
                dji.publics.b.b.a.getInstance().c("action", "1", false);
                break;
            case TIMELAPSE_MOTION:
                dji.publics.b.b.a.getInstance().c("action", "5", false);
                break;
            case TIMELAPSE_STATIONARY:
                dji.publics.b.b.a.getInstance().c("action", "4", false);
                break;
        }
        dji.publics.b.b.a.getInstance().c("createtime", System.currentTimeMillis() + "", false).c(dji.publics.b.a.b.i, (String) this.c.get(dji.publics.b.a.b.i), false).c("device_type", i.getInstance().c()._name(), false).c("pro_id", dji.publics.b.b.a.a, false).c("device_ver", "0.0.0.0", true);
        this.c.clear();
        this.X = true;
    }

    public void h() {
        if (this.O) {
            if (this.E.hasMessages(1)) {
                this.E.removeMessages(1);
            }
            d(true);
            this.O = false;
        }
        if (this.w) {
            ((DJILPPreviewActivity) this.g).mLongExposurePresenter.e();
            this.a.switchToTakePhotoUI(false);
            dji.phone.d.d.getInstance().a(dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE_PREVIEW, true);
            this.w = false;
            a(false);
        }
        if (this.T) {
            this.U = true;
        }
    }

    public void i() {
        this.W = false;
        this.g = null;
        this.e = null;
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        this.ah.getLooper().quit();
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.b) {
            this.W = true;
            j();
            return;
        }
        this.W = false;
        this.m.setVisibility(8);
    }

    public void j() {
        if (this.W) {
            int s = dji.pilot.phonecamera.a.c.a().s();
            Log.d("CameraControPresenter", "notifyCameraId: " + s);
            if (s < 0) {
                DataGimbalCameraIdNotify.getInstance().a(0).a(this.j);
            } else if (s == dji.pilot.phonecamera.d.a().i()) {
                DataGimbalCameraIdNotify.getInstance().a(0).a(this.j);
            } else if (s == dji.pilot.phonecamera.d.a().j()) {
                DataGimbalCameraIdNotify.getInstance().a(1).a(this.j);
            }
        }
    }

    public void onEventMainThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        int relativeCapacity = dataCenterGetPushBatteryCommon.getRelativeCapacity();
        if (!this.m.isShown()) {
            this.m.setVisibility(0);
        }
        if (this.A != relativeCapacity || this.Y != dataCenterGetPushBatteryCommon.isBatteryOnCharge()) {
            this.A = relativeCapacity;
            this.Y = dataCenterGetPushBatteryCommon.isBatteryOnCharge();
            e(relativeCapacity);
        }
    }

    public void k() {
        Log.d("CameraControPresenter", "onResume: ");
        this.E.postDelayed(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.v();
            }
        }, 200);
    }

    private void v() {
        if (this.a == null || !this.a.isOnAttch()) {
            this.E.postDelayed(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.v();
                }
            }, 100);
            return;
        }
        switch (dji.phone.c.a.g()) {
            case 0:
                this.a.switchToPhotoUI();
                if (this.t.isSelected()) {
                    this.r.switchModuleToPhoto();
                    return;
                }
                return;
            case 1:
                this.a.switchToVideoUI();
                if (this.t.isSelected()) {
                    this.r.switchModuleToVideo();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void e(int i) {
        int i2 = 100;
        Log.d("CameraControPresenter", "updateBatteryStatus: old percent " + i);
        if (i <= 100) {
            i2 = i;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        int i3 = i2 / 11;
        DJILogHelper.getInstance().LOGD("CameraControPresenter", "updateBatteryStatus: percent = " + i2, false, true);
        Log.d("CameraControPresenter", "updateBatteryStatus: index = " + i3);
        Log.d("CameraControPresenter", "updateBatteryStatus: isGimbalOnChange = " + this.Y);
        if (this.Y) {
            this.o.setVisibility(0);
        } else {
            this.o.setVisibility(8);
        }
        if (i2 == -1 || i3 > 9) {
            this.n.setImageResource(this.B[0]);
            this.p.setText("Err");
            return;
        }
        int i4;
        if (i3 == 9) {
            i4 = 8;
        } else {
            i4 = i3;
        }
        this.p.setText(i2 + "%");
        this.n.setImageResource(this.B[i4]);
        if (i4 == 0) {
            this.p.setTextColor(this.f.getResources().getColor(R.color.red));
        } else {
            this.p.setTextColor(this.f.getResources().getColor(R.color.white));
        }
    }
}
