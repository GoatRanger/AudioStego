package dji.device.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.alipay.sdk.j.f;
import com.facebook.login.widget.ToolTipPopup;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.device.camera.a.e;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.camera.view.common.DJICameraControlViewLongan;
import dji.device.camera.view.focus.DJIFocusAreaViewCompat;
import dji.device.camera.view.focus.DJIMFDemarcateViewLongan;
import dji.device.camera.view.focus.LonganAfInfiniteSwitcher;
import dji.device.camera.view.focus.LonganFocusExposureSwitchView;
import dji.device.camera.view.menu.DJILevel1MenuViewLongan;
import dji.device.camera.view.menu.DJILevel2MenuViewLongan;
import dji.device.camera.view.menu.DJIMenuSeptalLineLongan;
import dji.device.common.DJIUIEventManagerLongan;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.DJICameraAnimViewCompat;
import dji.device.common.view.DJICameraChartViewCompat;
import dji.device.common.view.DJIGimbalRollFineTuneViewCompat;
import dji.device.common.view.DJIGridLineCompat;
import dji.device.common.view.set.view.LonganCameraShotcutsView;
import dji.device.common.view.set.view.LonganGimbalNewShotcutsView;
import dji.device.pano.DJIFpvPanoDisplayer;
import dji.device.timelapse.LonganNewTimelapseMainLayout;
import dji.device.widget.LonganPopWarnView;
import dji.device.widget.popview.DJIErrorPopViewCompat;
import dji.log.DJILogHelper;
import dji.logic.album.model.DJIAlbumFile;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.g;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetMeteringArea;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushPrepareOpenFan;
import dji.midware.data.model.P3.DataCameraGetPushRecordingName;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo$RecordType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetRecordFan;
import dji.midware.data.model.P3.DataCameraRequestForIFrame;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetMeteringArea;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataCameraSetTrackingParms;
import dji.midware.data.model.P3.DataCameraSetZoomParams;
import dji.midware.data.model.P3.DataCameraSwitchUSB;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushPowerStatus;
import dji.midware.e.d;
import dji.midware.e.h;
import dji.midware.media.DJIVideoDataRecver;
import dji.midware.media.DJIVideoDecoder;
import dji.pilot.fpv.R;
import dji.pilot.longan.LonganCameraLiveView;
import dji.pilot2.multimoment.view.HorizonalSegmentView;
import dji.publics.DJIObject.DJIBaseActivityForVirtualKey;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.lang.ref.WeakReference;

public class DJIPreviewActivityLongan extends DJIBaseActivityForVirtualKey implements OnClickListener, h {
    public static final int GUESTURE_MIN_TIME = 120;
    private static final String KEY_CAMERA_FAN_CHANGED = "camera_fan_changed";
    private static final String KEY_CAMERA_FAN_OFF_T = "camera_fan_off_t";
    private static final String KEY_CAMERA_FAN_ON_T = "camera_fan_on_t";
    private static final String KEY_DEVICE_SN = "device_sn";
    private static final String KEY_FIRMWARE_RECORDED = "firmware_version_recorded";
    private static final int MSG_ID_DISCONNECT = 16384;
    private static final int MSG_ID_FOCUS_ANIM = 20480;
    private static final int MSG_ID_GIMBAL_CONTROL = 36864;
    private static final int MSG_ID_GIMBAL_CONTROL_RESET = 36865;
    private static final int MSG_ID_LOSE_VIDEO = 36866;
    private static final int MSG_ID_LOSE_VIDEO_SECOND = 36867;
    private static final int MSG_ID_METERING_CENTER = 28672;
    private static final int MSG_ID_METERING_SHOW = 32768;
    private static final int MSG_ID_RESET_VIDEO = 12288;
    private static final int MSG_ID_SHOW_REAL_FOCUS_AREA = 36872;
    private static final int MSG_ID_SHOW_TIMELAPSE_FPV = 36870;
    private static final int MSG_ID_SHOW_ZOOM_SCALE = 36871;
    private static final int MSG_ID_START_TRACKING_ANIM = 20481;
    private static final int MSG_ID_START_TUTORIAL = 36869;
    private static final int MSG_ID_TRACKING_RESET = 20483;
    private static final int MSG_ID_TRACKING_SHOW = 20482;
    private static final int MSG_ID_TURN_GIMBAL = 36868;
    private static final int REQUEST_START_PLAYBACK = 1126;
    public static boolean isHideAll = false;
    public static int mScreenHeight;
    public static int mScreenWidth;
    public static int mVideoHeight;
    public static int mVideoWidth;
    public static boolean openRecord = true;
    protected final int SCALE_MAX = 200;
    protected final int SCALE_MIN = 100;
    protected final int SCALE_SEND_INTERVAL = 50;
    protected float SCALE_UNIT = 0.0f;
    public final String TAG = "DJIPreviewActivityLongan";
    private float baseValue = 0.0f;
    private MODE cameraMode;
    private Runnable connectLoseRunnable = new Runnable(this) {
        final /* synthetic */ DJIPreviewActivityLongan a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.mGray.isShown()) {
                try {
                    Drawable bitmapDrawable = new BitmapDrawable(this.a.getResources(), this.a.getBitmap());
                    ColorMatrix colorMatrix = new ColorMatrix();
                    colorMatrix.setSaturation(0.0f);
                    bitmapDrawable.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
                    this.a.mGray.setBackground(bitmapDrawable);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.a.mGray.animate().setDuration(300).alpha(1.0f).withStartAction(new Runnable(this) {
                    final /* synthetic */ AnonymousClass19 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.mGray.show();
                    }
                }).start();
            }
        }
    };
    private Runnable connectOkRunnable = new Runnable(this) {
        final /* synthetic */ DJIPreviewActivityLongan a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.mGray.isShown()) {
                this.a.mGray.animate().setDuration(500).alpha(0.0f).withEndAction(new Runnable(this) {
                    final /* synthetic */ AnonymousClass18 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.mGray.hide();
                    }
                }).start();
            }
        }
    };
    int curMetering = -1;
    private DJIImageView fpvCameraFocusCancelView;
    private DJIImageView fpvCameraFocusView;
    private DJIRelativeLayout fpvVideoLayout;
    private GestureDetector gestureDetector;
    private OnGestureListener gestureListener = new OnGestureListener(this) {
        final /* synthetic */ DJIPreviewActivityLongan a;

        {
            this.a = r1;
        }

        public boolean onSingleTapUp(final MotionEvent motionEvent) {
            this.a.testmode = !this.a.testmode;
            if (DJIPreviewActivityLongan.isPopViewShown()) {
                c.a().e(m.HIDE_MENU);
                c.a().e(m.HIDE_PASM);
                c.a().e(m.HIDE_TIMELAPSE_LY);
                c.a().e(m.HIDE_SHOTCUTS_CAMERA_LY);
                c.a().e(m.HIDE_SHOTCUTS_GIMBAL_LY);
                c.a().e(m.SHOW_INFO_BAR);
            } else if (DataCameraGetPushShotParams.getInstance().getExposureMode() == ExposureMode.e && LonganFocusExposureSwitchView.a == dji.device.camera.view.focus.LonganFocusExposureSwitchView.a.METER) {
                Toast.makeText(this.a, R.string.camera_focus_nowork_in_m_mode, 0).show();
            } else if (motionEvent.getX() < ((float) this.a.widthLimit[0]) || motionEvent.getX() > ((float) this.a.widthLimit[1]) || motionEvent.getY() < ((float) this.a.heightLimit[0]) || motionEvent.getY() > ((float) this.a.heightLimit[1])) {
                this.a.startLimitAreaAnim(0.4f, 1.0f, 3);
            } else if (this.a.mMfFocusStatus == 1) {
                LonganPopWarnView.getInstance(this.a.getApplicationContext()).pop(R.drawable.longan_notice, R.string.fpv_cant_mffocusing_tap, dji.device.widget.LonganPopWarnView.a.LENGTH_SHORT);
            } else if (this.a.isTracking) {
                this.a.setTrackingArea(motionEvent);
                if (!DataCameraGetPushShotParams.getInstance().isAELock()) {
                    this.a.setTrackingMetering(motionEvent);
                }
            } else {
                if (LonganFocusExposureSwitchView.a == dji.device.camera.view.focus.LonganFocusExposureSwitchView.a.METER) {
                    if (DataCameraGetPushShotParams.getInstance().isAELock()) {
                        LonganPopWarnView.getInstance(this.a.getApplicationContext()).pop(R.drawable.longan_notice, R.string.fpv_cant_metering_ae, dji.device.widget.LonganPopWarnView.a.LENGTH_LONG);
                    }
                } else if (LonganFocusExposureSwitchView.a == dji.device.camera.view.focus.LonganFocusExposureSwitchView.a.FOCUS && LonganAfInfiniteSwitcher.a == dji.device.camera.view.focus.LonganAfInfiniteSwitcher.a.INFINITE) {
                    LonganPopWarnView.getInstance(this.a.getApplicationContext()).pop(R.drawable.longan_notice, R.string.fpv_cant_metering_infinite_locked, dji.device.widget.LonganPopWarnView.a.LENGTH_SHORT);
                }
                int metering = DataCameraGetPushShotParams.getInstance().getMetering();
                if (ServiceManager.getInstance().isOK()) {
                    if (LonganFocusExposureSwitchView.a == dji.device.camera.view.focus.LonganFocusExposureSwitchView.a.METER) {
                        if (metering == 0) {
                            this.a.meterSetter.a(dji.midware.data.config.P3.b.a.y).a(2).start(new d(this) {
                                final /* synthetic */ AnonymousClass9 b;

                                public void onSuccess(Object obj) {
                                    this.b.a.setMeteringArea(motionEvent);
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                }
                            });
                            try {
                                Class cls = Class.forName("dji.pilot.reflect.FpvReflect");
                                cls.getMethod("flurryCameraExposure", new Class[0]).invoke(cls, new Object[0]);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (metering == 2) {
                            this.a.setMeteringArea(motionEvent);
                        }
                    } else if (LonganFocusExposureSwitchView.a == dji.device.camera.view.focus.LonganFocusExposureSwitchView.a.FOCUS) {
                        this.a.mFocusAreaView.handleMotion(motionEvent);
                    }
                }
            }
            return false;
        }

        public void onShowPress(MotionEvent motionEvent) {
            DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "onShowPress", false, true);
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.a.gimbalSpeedController.d) {
                this.a.gimbalSpeedController.b(motionEvent2);
                this.a.gimbalSpeedController.a(motionEvent2.getX() - motionEvent.getX(), motionEvent2.getY() - motionEvent.getY());
            } else {
                long eventTime = motionEvent2.getEventTime() - motionEvent.getEventTime();
                if ((motionEvent.getY() - motionEvent2.getY() > ((float) this.a.mGustureMinDisClear) && Math.abs(motionEvent2.getX() - motionEvent.getX()) < ((float) this.a.mGustureMinDis) && !DJIPreviewActivityLongan.isHideAll && eventTime < 120) || (motionEvent.getY() - motionEvent2.getY() > ((float) (this.a.mGustureMinDis * 4)) && Math.abs(motionEvent2.getX() - motionEvent.getX()) < ((float) this.a.mGustureMinDis))) {
                    DJIPreviewActivityLongan.isHideAll = true;
                    c.a().e(m.HIDE_ALL);
                } else if (motionEvent2.getY() - motionEvent.getY() > ((float) (this.a.mGustureMinDis / 10)) && DJIPreviewActivityLongan.isHideAll) {
                    DJIPreviewActivityLongan.isHideAll = false;
                    c.a().e(m.SHOW_ALL);
                }
                if ((motionEvent2.getX() - motionEvent.getX() > ((float) this.a.mGustureMinDis) && Math.abs(motionEvent2.getY() - motionEvent.getY()) < ((float) this.a.mGustureMinDis) && eventTime < 120) || (motionEvent2.getX() - motionEvent.getX() > ((float) (this.a.mGustureMinDis * 4)) && Math.abs(motionEvent2.getY() - motionEvent.getY()) < ((float) this.a.mGustureMinDis))) {
                    c.a().e(m.SHOW_POWER_VIEW);
                }
            }
            return false;
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (!this.a.gimbalSpeedController.d) {
                this.a.gimbalCenterEvent = motionEvent;
                this.a.showGimbalControl();
            }
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }
    };
    private MotionEvent gimbalCenterEvent = null;
    private dji.device.gimbal.control.a gimbalSpeedController;
    private int[] heightLimit = new int[]{0, 0};
    protected boolean isDownloading = false;
    boolean isGimbalMoveStarted = false;
    boolean isTracking;
    boolean isTrackingEnable = false;
    boolean isTutorialWaitAwake = false;
    private long lastTwoFingersDownForInnerTools = 0;
    private DJIImageView longanTrackingView;
    private volatile int mAeLock = -1;
    dji.logic.album.a.d.a<DJIAlbumFile> mAlbumPullListener;
    private ImageView mCameraBg;
    private DJICameraChartViewCompat mChartViewCompat;
    protected long mCurScaleTime = 0;
    protected String mCurShutterString = null;
    protected int mCurrentScale = 100;
    private String mDeviceSN;
    private DJIErrorPopViewCompat mErrorPopView;
    private volatile ExposureMode mExposureMode = ExposureMode.i;
    private DJIFocusAreaViewCompat mFocusAreaView;
    protected int mFocusNumX = 0;
    protected int mFocusNumY = 0;
    protected int mFocusPositionX = 0;
    protected int mFocusPositionY = 0;
    private int mFocusStatus = 0;
    dji.device.gimbal.control.c mGSC;
    DJIGimbalRollFineTuneViewCompat mGimbalRollTuneView;
    private DJIImageView mGray;
    private DJIGridLineCompat mGridLine;
    private int mGustureMinDis = 0;
    private int mGustureMinDisClear = 0;
    private DJIImageView mImgLimitArea;
    boolean mIsInPlaybackActivity = false;
    private DJILevel1MenuViewLongan mLevel1MenuView;
    private DJILevel2MenuViewLongan mLevel2MenuView;
    private Animation mLimitAreaAnim = null;
    private DJICameraControlViewLongan mLonganCameraController;
    private DJIMenuSeptalLineLongan mLonganMenuLine;
    private ImageView mMenuBg;
    private float mMeterXRatio;
    private float mMeterYRatio;
    private DJIMFDemarcateViewLongan mMfDemarcateview;
    private volatile int mMfFocusStatus = -1;
    private int mOffT = 0;
    private int mOnT = 0;
    private int mPadding = 0;
    dji.device.camera.a.c.b mPhotoType = dji.device.camera.a.c.b.SINGLE;
    private ImageView mQuickSetBg;
    private DJIRelativeLayout mRootView;
    protected int mShowScale = 100;
    protected int mTimelapseFpvIndex = 0;
    private DJIImageView mTimelapseFpvIv;
    private MODE mTmpMode = null;
    View mTutorialView;
    private DJITextView mTvFocusDesc;
    private LonganCameraLiveView mVideoSurface;
    private b mViewHandler = null;
    ProgressDialog mWaitProgressDlg;
    protected int mZoomUnit = 0;
    private boolean mbShotConnected = true;
    private int meterHnum = 12;
    private DataBaseCameraSetting meterSetter = new DataBaseCameraSetting();
    private int meterVnum = 8;
    private d meteringAreaCallBack = new d(this) {
        final /* synthetic */ DJIPreviewActivityLongan a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            DataCameraGetMeteringArea dataCameraGetMeteringArea = (DataCameraGetMeteringArea) obj;
            this.a.meterHnum = dataCameraGetMeteringArea.getHnum();
            this.a.meterVnum = dataCameraGetMeteringArea.getVnum();
            this.a.unitH = DJIPreviewActivityLongan.mScreenWidth / this.a.meterHnum;
            this.a.unitV = DJIPreviewActivityLongan.mScreenHeight / this.a.meterVnum;
            DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "测光区域获取成功 " + this.a.meterHnum + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.a.meterVnum);
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            this.a.unitH = 0;
            DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "测光区域获取 " + aVar);
        }
    };
    private DisplayMetrics metrics = new DisplayMetrics();
    dji.logic.album.a.b.c nailLoader;
    private Point outSize = new Point();
    private RatioType screenRatioType = RatioType.R_16_9;
    private d setCameraTempCallBack = new d(this) {
        final /* synthetic */ DJIPreviewActivityLongan a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            DataCameraGetRecordFan.getInstance().start(new d(this) {
                final /* synthetic */ AnonymousClass16 a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.a.mOffT = DataCameraGetRecordFan.getInstance().getForceFanOffT();
                    this.a.a.mOnT = DataCameraGetRecordFan.getInstance().getForceFanOnT();
                    DJILogHelper.getInstance().LOGD("get temp ", "success offT:" + this.a.a.mOffT + "on:" + this.a.a.mOnT, false, true);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD("get temp ", f.b + aVar, false, true);
                }
            });
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            DJILogHelper.getInstance().LOGD("set temp ", f.b + aVar, false, true);
        }
    };
    private boolean testmode = false;
    private RatioType tmpRatioType = RatioType.R_16_9;
    private int unitH;
    private int unitV;
    private int[] widthLimit = new int[]{0, 0};

    static /* synthetic */ class AnonymousClass11 {
        static final /* synthetic */ int[] b = new int[o.values().length];
        static final /* synthetic */ int[] d = new int[ProductType.values().length];

        static {
            try {
                d[ProductType.Orange.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                d[ProductType.N1.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                d[ProductType.litchiS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                d[ProductType.litchiX.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                d[ProductType.Longan.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            c = new int[dji.device.camera.view.common.DJICameraControlViewLongan.a.values().length];
            try {
                c[dji.device.camera.view.common.DJICameraControlViewLongan.a.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                c[dji.device.camera.view.common.DJICameraControlViewLongan.a.CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                b[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e8) {
            }
            try {
                b[o.a.ordinal()] = 2;
            } catch (NoSuchFieldError e9) {
            }
            a = new int[dji.midware.data.manager.P3.m.values().length];
            try {
                a[dji.midware.data.manager.P3.m.ConnectOK.ordinal()] = 1;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[dji.midware.data.manager.P3.m.ConnectLose.ordinal()] = 2;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    public static class a {
        String a;
    }

    private static final class b extends Handler {
        private final WeakReference<DJIPreviewActivityLongan> a;

        public b(DJIPreviewActivityLongan dJIPreviewActivityLongan) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIPreviewActivityLongan);
        }

        public void handleMessage(Message message) {
            int i = 0;
            DJIPreviewActivityLongan dJIPreviewActivityLongan = (DJIPreviewActivityLongan) this.a.get();
            if (dJIPreviewActivityLongan != null && !dJIPreviewActivityLongan.isFinishing()) {
                switch (message.what) {
                    case 12288:
                        if (message.arg1 == 0) {
                            dJIPreviewActivityLongan.resetVideo();
                            return;
                        } else {
                            dJIPreviewActivityLongan.resetVideo(message.arg1, message.arg2);
                            return;
                        }
                    case 16384:
                        dJIPreviewActivityLongan.disconnnect();
                        return;
                    case 20480:
                        MotionEvent motionEvent = (MotionEvent) message.obj;
                        dJIPreviewActivityLongan.fpvCameraFocusView.setBackgroundResource(R.drawable.longan_fpv_spot_metering);
                        Drawable background = dJIPreviewActivityLongan.fpvCameraFocusView.getBackground();
                        float intrinsicWidth = (((float) background.getIntrinsicWidth()) * 1.0f) / 2.0f;
                        float intrinsicHeight = (((float) background.getIntrinsicHeight()) * 1.0f) / 2.0f;
                        dJIPreviewActivityLongan.fpvCameraFocusView.setX(motionEvent.getX() - intrinsicWidth);
                        dJIPreviewActivityLongan.fpvCameraFocusView.setY(motionEvent.getY() - intrinsicHeight);
                        dJIPreviewActivityLongan.fpvCameraFocusCancelView.setX((intrinsicWidth + motionEvent.getX()) - ((float) dJIPreviewActivityLongan.fpvCameraFocusCancelView.getWidth()));
                        dJIPreviewActivityLongan.fpvCameraFocusCancelView.setY(motionEvent.getY() - intrinsicHeight);
                        dJIPreviewActivityLongan.mTvFocusDesc.setX(motionEvent.getX() - ((float) (dJIPreviewActivityLongan.mTvFocusDesc.getWidth() / 2)));
                        dJIPreviewActivityLongan.mTvFocusDesc.setY((motionEvent.getY() + intrinsicHeight) + 5.0f);
                        if (dJIPreviewActivityLongan.mExposureMode == ExposureMode.i && DataCameraGetPushShotParams.getInstance().isGetted()) {
                            dJIPreviewActivityLongan.mExposureMode = DataCameraGetPushShotParams.getInstance().getExposureMode();
                            dJIPreviewActivityLongan.mAeLock = DataCameraGetPushShotParams.getInstance().isAELock() ? 1 : 0;
                        }
                        if (LonganFocusExposureSwitchView.a == dji.device.camera.view.focus.LonganFocusExposureSwitchView.a.METER && dJIPreviewActivityLongan.mExposureMode != ExposureMode.i && ExposureMode.e != dJIPreviewActivityLongan.mExposureMode && dJIPreviewActivityLongan.mAeLock == 0) {
                            dJIPreviewActivityLongan.fpvCameraFocusView.animShow();
                            dJIPreviewActivityLongan.mTvFocusDesc.animShow();
                            dJIPreviewActivityLongan.fpvCameraFocusCancelView.animShow();
                            return;
                        }
                        return;
                    case 20481:
                    case DJIPreviewActivityLongan.MSG_ID_GIMBAL_CONTROL /*36864*/:
                    case DJIPreviewActivityLongan.MSG_ID_TURN_GIMBAL /*36868*/:
                    case DJIPreviewActivityLongan.MSG_ID_SHOW_REAL_FOCUS_AREA /*36872*/:
                        return;
                    case 20482:
                        if (message.arg1 == 0) {
                            dJIPreviewActivityLongan.longanTrackingView.hide();
                            dJIPreviewActivityLongan.isTracking = false;
                            return;
                        }
                        dJIPreviewActivityLongan.longanTrackingView.show();
                        return;
                    case 20483:
                        dJIPreviewActivityLongan.isTracking = false;
                        return;
                    case DJIPreviewActivityLongan.MSG_ID_METERING_CENTER /*28672*/:
                        dJIPreviewActivityLongan.fpvCameraFocusCancelView.hide();
                        dJIPreviewActivityLongan.fpvCameraFocusView.setBackgroundResource(R.drawable.fpv_center_metering);
                        Drawable background2 = dJIPreviewActivityLongan.fpvCameraFocusView.getBackground();
                        int intrinsicWidth2 = background2.getIntrinsicWidth();
                        int intrinsicHeight2 = background2.getIntrinsicHeight();
                        dJIPreviewActivityLongan.fpvCameraFocusView.setX((float) ((DJIPreviewActivityLongan.mScreenWidth - intrinsicWidth2) / 2));
                        dJIPreviewActivityLongan.fpvCameraFocusView.setY((float) ((DJIPreviewActivityLongan.mScreenHeight - intrinsicHeight2) / 2));
                        dJIPreviewActivityLongan.mTvFocusDesc.setX((float) ((DJIPreviewActivityLongan.mScreenWidth - dJIPreviewActivityLongan.mTvFocusDesc.getWidth()) / 2));
                        dJIPreviewActivityLongan.mTvFocusDesc.setY((float) (((intrinsicHeight2 + DJIPreviewActivityLongan.mScreenHeight) / 2) + 5));
                        return;
                    case 32768:
                        if (message.arg1 == 0) {
                            dJIPreviewActivityLongan.fpvCameraFocusCancelView.hide();
                            dJIPreviewActivityLongan.mTvFocusDesc.hide();
                            dJIPreviewActivityLongan.fpvCameraFocusView.hide();
                            return;
                        } else if (LonganFocusExposureSwitchView.a == dji.device.camera.view.focus.LonganFocusExposureSwitchView.a.METER) {
                            if (dJIPreviewActivityLongan.mExposureMode == ExposureMode.i && DataCameraGetPushShotParams.getInstance().isGetted()) {
                                dJIPreviewActivityLongan.mExposureMode = DataCameraGetPushShotParams.getInstance().getExposureMode();
                                if (DataCameraGetPushShotParams.getInstance().isAELock()) {
                                    i = 1;
                                }
                                dJIPreviewActivityLongan.mAeLock = i;
                            }
                            if (dJIPreviewActivityLongan.mExposureMode != ExposureMode.i && ExposureMode.e != dJIPreviewActivityLongan.mExposureMode && dJIPreviewActivityLongan.mAeLock == 0) {
                                if (DataCameraGetPushShotParams.getInstance().getMetering() != 2 || message.arg2 == 1) {
                                    dJIPreviewActivityLongan.fpvCameraFocusCancelView.hide();
                                }
                                dJIPreviewActivityLongan.fpvCameraFocusView.show();
                                dJIPreviewActivityLongan.mTvFocusDesc.show();
                                return;
                            }
                            return;
                        } else {
                            return;
                        }
                    case DJIPreviewActivityLongan.MSG_ID_GIMBAL_CONTROL_RESET /*36865*/:
                        dJIPreviewActivityLongan.gimbalSpeedController.a();
                        return;
                    case DJIPreviewActivityLongan.MSG_ID_START_TUTORIAL /*36869*/:
                        ViewStub viewStub = (ViewStub) dJIPreviewActivityLongan.findViewById(R.id.longan_tutorial_view_vs);
                        if (viewStub != null) {
                            dJIPreviewActivityLongan.mTutorialView = viewStub.inflate();
                            return;
                        }
                        return;
                    case DJIPreviewActivityLongan.MSG_ID_SHOW_TIMELAPSE_FPV /*36870*/:
                        dJIPreviewActivityLongan.mTimelapseFpvIv.setBackground(new BitmapDrawable(dJIPreviewActivityLongan.getResources(), (Bitmap) message.obj));
                        return;
                    case DJIPreviewActivityLongan.MSG_ID_SHOW_ZOOM_SCALE /*36871*/:
                        dJIPreviewActivityLongan.popScale();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        DJILogHelper.getInstance().LOGD("init log", "activity init");
        super.onCreate(bundle);
        Log.d("DJIPreviewActivityLongan", "onCreate");
        DJICameraDataManagerCompat.getInstance().loadData(this);
        setContentView(R.layout.fpv_longan);
        getScreenSize();
        initViews();
        getWindow().addFlags(128);
        this.gimbalSpeedController = new dji.device.gimbal.control.a(this.mRootView, this);
        initController();
        initOthers();
        findWidgets();
        registerEventBus();
        initRemoteData();
        this.unitH = mScreenWidth / this.meterHnum;
        this.unitV = mScreenHeight / this.meterVnum;
        this.SCALE_UNIT = 150.0f / ((float) (mScreenHeight > mScreenWidth ? mScreenWidth : mScreenHeight));
        resetPadding(getResources().getConfiguration().orientation);
        if (DataCameraGetPushShotParams.getInstance().isGetted()) {
            onEventBackgroundThread(DataCameraGetPushShotParams.getInstance());
        }
        resetVideo();
        DJIVideoDataRecver.getInstance().setDecoderType(dji.midware.media.DJIVideoDataRecver.a.Hardware);
        g.getInstance().a(HorizonalSegmentView.N);
        DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
        if (instance.isGetted()) {
            MODE mode = instance.getMode();
            if (!(mode == MODE.TAKEPHOTO || mode == MODE.RECORD)) {
                DataCameraSetMode dataCameraSetMode = new DataCameraSetMode();
                dataCameraSetMode.a(MODE.TAKEPHOTO);
                dataCameraSetMode.start(new d(this) {
                    final /* synthetic */ DJIPreviewActivityLongan a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        Log.d("DJIPreviewActivityLongan", "on create switch mode succeed:");
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        Log.d("DJIPreviewActivityLongan", "on create switch mode failed:");
                    }
                });
            }
            saveCameraTempratureInfo();
        }
        this.mGSC = new dji.device.gimbal.control.c(getApplicationContext(), this);
        handleMFDemarcateFirst();
    }

    private void initController() {
        e.getInstance().a((Context) this);
        dji.device.camera.a.c.getInstance().a();
        dji.device.camera.a.a.getInstance().a();
        dji.device.camera.a.b.getInstance().a();
        dji.device.camera.a.d.getInstance().a();
        dji.device.common.b.getInstance().a();
        dji.device.camera.view.focus.a.c.getInstance().a((Context) this);
    }

    private void unitController() {
        e.getInstance().a();
        dji.device.camera.a.c.getInstance().b();
        dji.device.camera.a.a.getInstance().b();
        dji.device.camera.a.b.getInstance().b();
        dji.device.camera.a.d.getInstance().b();
        dji.device.camera.view.focus.a.c.getInstance().a();
    }

    @SuppressLint({"NewApi"})
    private void getScreenSize() {
        if (VERSION.SDK_INT < 17) {
            getWindowManager().getDefaultDisplay().getMetrics(this.metrics);
            mScreenWidth = this.metrics.widthPixels;
            mScreenHeight = this.metrics.heightPixels;
            return;
        }
        getWindowManager().getDefaultDisplay().getRealSize(this.outSize);
        mScreenWidth = this.outSize.x;
        mScreenHeight = this.outSize.y;
    }

    private void initRemoteData() {
        if (ServiceManager.getInstance().isConnected()) {
            int metering = DataCameraGetPushShotParams.getInstance().getMetering();
            DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "测光=" + metering);
            if (metering == 2) {
                setCenterMetering();
            } else if (metering == 0) {
                this.mViewHandler.sendEmptyMessage(MSG_ID_METERING_CENTER);
                showCenterMeter();
            }
        }
    }

    private void initOthers() {
        this.mViewHandler = new b(this);
        if (this.nailLoader == null) {
            this.nailLoader = new dji.logic.album.a.b.c();
        }
        this.mAlbumPullListener = new dji.logic.album.a.d.a<DJIAlbumFile>(this) {
            final /* synthetic */ DJIPreviewActivityLongan a;

            {
                this.a = r1;
            }

            public void a(DJIAlbumFile dJIAlbumFile) {
                this.a.isDownloading = false;
                this.a.mViewHandler.obtainMessage(DJIPreviewActivityLongan.MSG_ID_SHOW_TIMELAPSE_FPV, dJIAlbumFile.e).sendToTarget();
                DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "on Success", false, true);
            }

            public void a() {
                this.a.isDownloading = true;
            }

            public void a(long j, long j2, long j3) {
            }

            public void a(long j, long j2) {
            }

            public void a(DJIAlbumPullErrorType dJIAlbumPullErrorType) {
                this.a.isDownloading = false;
            }
        };
        this.mZoomUnit = getResources().getDimensionPixelOffset(R.dimen.dp_20_in_sw320dp);
        this.mGustureMinDis = getResources().getDimensionPixelOffset(R.dimen.dp_70_in_sw320dp);
        this.mGustureMinDisClear = getResources().getDimensionPixelOffset(R.dimen.dp_80_in_sw320dp);
    }

    public void onEventMainThread(dji.pilot.set.a.a aVar) {
        if (dji.pilot.set.g.a == aVar.a) {
            handleShowGrid();
        }
    }

    private void initViews() {
        this.mRootView = (DJIRelativeLayout) findViewById(R.id.fpv_content_view);
        this.mVideoSurface = (LonganCameraLiveView) findViewById(R.id.video_previewer_surface);
        this.mTimelapseFpvIv = (DJIImageView) findViewById(R.id.longan_timelapse_fpv_iv);
        this.mGray = (DJIImageView) findViewById(R.id.video_previewer_gray);
        this.mGridLine = (DJIGridLineCompat) findViewById(R.id.fpv_grid_line);
        this.mErrorPopView = (DJIErrorPopViewCompat) findViewById(R.id.fpv_error_popview);
        this.fpvVideoLayout = (DJIRelativeLayout) findViewById(R.id.fpv_video_layout);
        this.fpvCameraFocusView = (DJIImageView) findViewById(R.id.longan_fpv_camera_focus);
        this.mTvFocusDesc = (DJITextView) findViewById(R.id.longan_fpv_camera_focus_tv);
        this.fpvCameraFocusCancelView = (DJIImageView) findViewById(R.id.fpv_camera_focus_cancel);
        this.longanTrackingView = (DJIImageView) findViewById(R.id.longan_tracking_iv);
        this.mQuickSetBg = (ImageView) findViewById(R.id.quickset_shade_bg);
        this.mCameraBg = (ImageView) findViewById(R.id.camera_shade_bg);
        this.mMenuBg = (ImageView) findViewById(R.id.menu_shade_bg);
        this.mChartViewCompat = (DJICameraChartViewCompat) findViewById(R.id.fpv_camera_chart_ly_longan);
        this.mImgLimitArea = (DJIImageView) findViewById(R.id.longan_video_preview_limit_ly);
        this.mWaitProgressDlg = new ProgressDialog(this);
        this.mWaitProgressDlg.show();
        this.mWaitProgressDlg.setContentView(R.layout.longan_progress_dialog);
        Window window = this.mWaitProgressDlg.getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.alpha = 0.8f;
        attributes.width = getResources().getDimensionPixelOffset(R.dimen.longan_progress_width);
        window.setAttributes(attributes);
        this.mWaitProgressDlg.setCanceledOnTouchOutside(false);
        this.mWaitProgressDlg.dismiss();
        this.mFocusAreaView = (DJIFocusAreaViewCompat) findViewById(R.id.longan_fpv_camera_focus_area_ly);
        this.mMfDemarcateview = (DJIMFDemarcateViewLongan) findViewById(R.id.fpv_camera_demarcate_ly);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void findWidgets() {
        this.mErrorPopView.dispatchOnCreate();
        handleShowGrid();
        this.gestureDetector = new GestureDetector(this, this.gestureListener);
        this.gestureDetector.setIsLongpressEnabled(true);
        this.fpvVideoLayout.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ DJIPreviewActivityLongan a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.a.baseValue = 0.0f;
                } else if (motionEvent.getAction() == 2) {
                    this.a.handleTouchMove(motionEvent);
                } else if (motionEvent.getAction() == 1) {
                    this.a.baseValue = 0.0f;
                    this.a.onTouchUp();
                }
                return this.a.gestureDetector.onTouchEvent(motionEvent);
            }
        });
        this.mChartViewCompat.dispatchOnCreate();
        this.fpvCameraFocusCancelView.setOnClickListener(this);
        DJICameraAnimViewCompat dJICameraAnimViewCompat = (DJICameraAnimViewCompat) findViewById(R.id.cameraanimview);
        this.mLonganCameraController = (DJICameraControlViewLongan) findViewById(R.id.longan_preview_cameracontrol);
        this.mLonganMenuLine = (DJIMenuSeptalLineLongan) findViewById(R.id.longan_menu_line);
        this.mLevel1MenuView = (DJILevel1MenuViewLongan) findViewById(R.id.longan_level1_menu_layout);
        this.mLevel2MenuView = (DJILevel2MenuViewLongan) findViewById(R.id.longan_level2_menu_layout);
        this.mLonganCameraController.addAnimaView(dJICameraAnimViewCompat).addListener(new dji.device.camera.view.common.DJICameraControlViewLongan.b(this) {
            final /* synthetic */ DJIPreviewActivityLongan a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                int i;
                int i2 = 4;
                this.a.mLevel1MenuView.show(z);
                this.a.mLevel2MenuView.show(z);
                this.a.mLonganMenuLine.show(z);
                ImageView access$1100 = this.a.mMenuBg;
                if (z) {
                    i = 0;
                } else {
                    i = 4;
                }
                access$1100.setVisibility(i);
                ImageView access$1200 = this.a.mCameraBg;
                if (!z) {
                    i2 = 0;
                }
                access$1200.setVisibility(i2);
            }

            public void b(boolean z) {
                int i = z ? 0 : 4;
                this.a.mCameraBg.setVisibility(i);
                this.a.mQuickSetBg.setVisibility(i);
            }
        });
        if (!dji.pilot.set.a.d(getApplicationContext())) {
            if (ServiceManager.getInstance().isConnected() && DataOsdGetPushPowerStatus.getInstance().getPowerStatus() == 0) {
                this.mViewHandler.sendEmptyMessage(MSG_ID_START_TUTORIAL);
            } else {
                this.isTutorialWaitAwake = true;
            }
        }
    }

    protected void handleTouchMove(MotionEvent motionEvent) {
        int i = 200;
        if (motionEvent.getPointerCount() == 2) {
            if (dji.device.common.a.a.b()) {
                float x = motionEvent.getX(0) - motionEvent.getX(1);
                float y = motionEvent.getY(0) - motionEvent.getY(1);
                x = (float) Math.sqrt((double) ((x * x) + (y * y)));
                if (this.baseValue == 0.0f) {
                    this.baseValue = x;
                    this.mCurrentScale = this.mShowScale;
                }
                this.mCurrentScale = (int) (((float) this.mCurrentScale) + ((x - this.baseValue) * this.SCALE_UNIT));
                if (this.mCurrentScale <= 200) {
                    i = this.mCurrentScale;
                }
                this.mCurrentScale = i;
                this.mCurrentScale = this.mCurrentScale < 100 ? 100 : this.mCurrentScale;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.mCurScaleTime >= 50) {
                    this.mCurScaleTime = currentTimeMillis;
                    sendZoom(this.mCurrentScale);
                    this.baseValue = x;
                    return;
                }
                return;
            }
            popDZoomWarning(getApplicationContext());
        } else if (motionEvent.getPointerCount() == 1 && this.gimbalSpeedController.d) {
            this.gimbalSpeedController.b(motionEvent);
            this.gimbalSpeedController.a(motionEvent.getX() - this.gimbalCenterEvent.getX(), motionEvent.getY() - this.gimbalCenterEvent.getY());
        }
    }

    public static void popDZoomWarning(Context context) {
        if (DataCameraGetPushStateInfo.getInstance().getCameraType() == CameraType.DJICameraTypeFC350 && DataCameraGetPushStateInfo.getInstance().getVerstion() >= 4) {
            if (DJICameraDataManagerCompat.getInstance().isCur4kVideo()) {
                LonganPopWarnView.getInstance(context).pop(R.drawable.longan_notice, R.string.longan_unsupport_dzoom_4k, dji.device.widget.LonganPopWarnView.a.LENGTH_SHORT);
            } else if (dji.device.camera.a.c.getInstance().c() == dji.device.camera.a.c.b.PANO) {
                LonganPopWarnView.getInstance(context).pop(R.drawable.longan_notice, R.string.longan_unsupport_dzoom_pano, dji.device.widget.LonganPopWarnView.a.LENGTH_SHORT);
            } else if (e.getInstance().b() == dji.device.camera.a.e.a.SLOWMOTION_1080) {
                LonganPopWarnView.getInstance(context).pop(R.drawable.longan_notice, R.string.longan_unsupport_dzoom_slowmotion, dji.device.widget.LonganPopWarnView.a.LENGTH_SHORT);
            }
        }
    }

    private void sendZoom(int i) {
        if (dji.device.common.a.a.b()) {
            DataCameraSetZoomParams.getInstance().c(i).start(null);
        }
    }

    private void popScale() {
        if (DataCameraGetPushStateInfo.getInstance().getVerstion() >= 4) {
            LonganPopWarnView.getInstance(getApplicationContext()).pop(0, String.format("X%.1f", new Object[]{Float.valueOf(((float) this.mShowScale) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity)}), dji.device.widget.LonganPopWarnView.a.LENGTH_SHORT);
        }
    }

    private void handleMenuVisibleChanged() {
        this.mLevel1MenuView.handleViewChange();
        this.mLevel2MenuView.handleViewChange();
        this.mLonganMenuLine.handleViewChange();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.fpv_camera_focus_cancel) {
            cancelSpotMetering();
        }
    }

    private void cancelSpotMetering() {
        this.mViewHandler.sendMessage(this.mViewHandler.obtainMessage(32768, 0, 0));
        setCenterMetering();
    }

    public void onClickBackground(View view) {
    }

    public void disconnnect() {
        this.mMfDemarcateview.hideView();
        resetData();
    }

    private void handleShowGrid() {
        int f = dji.pilot.set.a.f(this);
        if (f == 0) {
            this.mGridLine.go();
            return;
        }
        if (f == 1) {
            this.mGridLine.setType(1);
        } else if (f == 2) {
            this.mGridLine.setType(2);
        } else if (f == 3) {
            this.mGridLine.setType(4);
        }
        this.mGridLine.show();
    }

    private void showFocusRingView() {
    }

    public void finishThis() {
        this.mViewHandler.removeMessages(16384);
        overridePendingTransition(0, 0);
        sendBroadcast(new Intent("dji.device.activity.DJIPreviewActivityLongan.FINISH"));
    }

    public void finish() {
        super.finish();
        finishThis();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void onLowMemory() {
        super.onLowMemory();
    }

    protected void onDestroy() {
        unitController();
        unregisterEventBus();
        this.mErrorPopView.dispatchOnDestroy();
        Log.d("DJIPreviewActivityLongan", "onDestroy");
        this.mChartViewCompat.dispatchOnDestroy();
        this.mGSC.a();
        super.onDestroy();
    }

    protected void onStart() {
        super.onStart();
        Log.d("DJIPreviewActivityLongan", "onStart" + System.currentTimeMillis());
        ServiceManager.getInstance().pauseService(false);
    }

    protected void onStop() {
        Log.d("DJIPreviewActivityLongan", "onStop");
        super.onStop();
    }

    public void onBackPressed() {
        if (dji.device.camera.a.c.getInstance().c() != dji.device.camera.a.c.b.PANO) {
            finish();
        } else if (!DJIFpvPanoDisplayer.p) {
        } else {
            if (DJIFpvPanoDisplayer.q) {
                c.a().e(DJIUIEventManagerLongan.g.CLOSE_PANO_DISPLAYER);
            } else {
                finish();
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        Log.d("onConfigurationChanged", "changed");
        getScreenSize();
        resetPadding(configuration.orientation);
        resetVideo();
        resetMeterPosition();
        resetBackgroundShadow();
        this.mChartViewCompat.getWidthAndHeight();
        this.SCALE_UNIT = 150.0f / ((float) (mScreenHeight > mScreenWidth ? mScreenWidth : mScreenHeight));
        super.onConfigurationChanged(configuration);
    }

    public void oneFrameComeIn() {
    }

    private void resetPadding(int i) {
        if (i == 2) {
            this.mPadding = getResources().getDimensionPixelSize(R.dimen.longan_litchi_fpvtop_signal);
        } else {
            this.mPadding = 0;
        }
    }

    public void resetVideoSurface(int i, int i2) {
        this.mViewHandler.sendMessage(this.mViewHandler.obtainMessage(12288, i, i2));
    }

    private void resetVideo(int i, int i2) {
        DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "videoWidth=" + i + "  videoHeight=" + i2, false, true);
        resetVideo();
    }

    private void resetVideo() {
        int i = DJIVideoDecoder.width;
        int i2 = DJIVideoDecoder.height;
        float radio = getRadio();
        if (Math.abs(radio - dji.midware.util.a.b.a) < Math.abs(radio - dji.midware.util.a.b.b)) {
            this.screenRatioType = RatioType.R_16_9;
        } else {
            this.screenRatioType = RatioType.R_4_3;
        }
        DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "ratioType=" + this.tmpRatioType, false, true);
        DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "screenRatioType=" + this.screenRatioType, false, true);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoSurface.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = this.mGridLine.getLayoutParams();
        if (this.screenRatioType == RatioType.R_16_9) {
            layoutParams.width = mScreenWidth;
            layoutParams.height = (int) (((float) mScreenWidth) / dji.midware.util.a.b.a);
        } else {
            layoutParams.width = mScreenWidth;
            layoutParams.height = (int) (((1.0f * ((float) i2)) / ((float) i)) * ((float) mScreenWidth));
        }
        DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "videoHeight" + i2 + "videoWidth:" + i, false, true);
        if (this.tmpRatioType == RatioType.R_16_9 || dji.device.camera.a.a.getInstance().d() == dji.device.camera.a.a.a.RECORD) {
            layoutParams2.height = layoutParams.height;
            layoutParams2.width = (int) (((float) layoutParams.height) * dji.midware.util.a.b.a);
            this.mGridLine.setLayoutParams(layoutParams2);
        } else {
            layoutParams2.height = layoutParams.height;
            layoutParams2.width = (int) (((float) layoutParams.height) * dji.midware.util.a.b.b);
            this.mGridLine.setLayoutParams(layoutParams2);
        }
        Log.d("DJIPreviewActivityLongan", "ratio:" + this.screenRatioType);
        if (DataCameraGetPushStateInfo.getInstance().getVerstion() < 4 || dji.logic.f.b.h(null)) {
            this.mVideoSurface.setLayoutParams(layoutParams);
            mVideoHeight = layoutParams.height;
            mVideoWidth = layoutParams.width;
        } else {
            mVideoHeight = layoutParams2.height;
            mVideoWidth = layoutParams2.width;
            this.mVideoSurface.setLayoutParams(layoutParams2);
        }
        this.mTimelapseFpvIv.setLayoutParams(layoutParams);
        this.mGray.setLayoutParams(layoutParams);
        this.mFocusAreaView.setHVLimits(new int[]{(mScreenWidth / 2) - (layoutParams.width / 2), r1[0] + layoutParams.width}, new int[]{(mScreenHeight - layoutParams.height) / 2, (mScreenHeight + layoutParams.height) / 2});
        if (this.tmpRatioType == RatioType.R_16_9) {
            layoutParams = new RelativeLayout.LayoutParams(0, 0);
            layoutParams.addRule(13, -1);
            layoutParams.width = mScreenWidth;
            layoutParams.height = (int) (((float) mScreenWidth) / dji.midware.util.a.b.a);
            this.widthLimit[0] = (this.mPadding * 2) + 0;
            this.widthLimit[1] = layoutParams.width - (this.mPadding * 2);
            int i3 = (mScreenHeight - layoutParams.height) / 2;
            this.heightLimit[0] = this.mPadding + i3;
            this.heightLimit[1] = (mScreenHeight - i3) - this.mPadding;
            ViewGroup.LayoutParams layoutParams3 = this.mImgLimitArea.getLayoutParams();
            layoutParams3.width = this.widthLimit[1] - this.widthLimit[0];
            layoutParams3.height = this.heightLimit[1] - this.heightLimit[0];
            this.mImgLimitArea.setLayoutParams(layoutParams3);
            resetMiteringArea();
            return;
        }
        this.widthLimit[0] = (mScreenWidth / 2) - (layoutParams2.width / 2);
        this.widthLimit[1] = (mScreenWidth / 2) + (layoutParams2.width / 2);
        i3 = (mScreenHeight - layoutParams.height) / 2;
        this.heightLimit[0] = this.mPadding + i3;
        this.heightLimit[1] = (mScreenHeight - i3) - this.mPadding;
        ViewGroup.LayoutParams layoutParams4 = this.mImgLimitArea.getLayoutParams();
        layoutParams4.width = layoutParams2.width;
        layoutParams4.height = mScreenHeight - (i3 * 2);
        this.mImgLimitArea.setLayoutParams(layoutParams4);
        resetMiteringArea();
    }

    private void resetBackgroundShadow() {
        if (getResources().getConfiguration().orientation == 2) {
            this.mCameraBg.setVisibility(0);
            this.mQuickSetBg.setVisibility(0);
            if (DJICameraControlViewLongan.j) {
                this.mMenuBg.setVisibility(0);
                return;
            }
            return;
        }
        this.mCameraBg.setVisibility(4);
        this.mQuickSetBg.setVisibility(4);
        this.mMenuBg.setVisibility(4);
    }

    @SuppressLint({"NewApi"})
    private float getRadio() {
        int i;
        int i2;
        int i3;
        if (VERSION.SDK_INT < 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
            if (i >= i2) {
                i3 = i2;
                i2 = i;
                i = i3;
            }
        } else {
            Display defaultDisplay = getWindowManager().getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            i3 = point.x > point.y ? point.y : point.x;
            i2 = point.x > point.y ? point.x : point.y;
            i = i3;
        }
        return (((float) i2) * 1.0f) / ((float) i);
    }

    private void resetMiteringArea() {
        this.unitH = (this.widthLimit[1] - this.widthLimit[0]) / this.meterHnum;
        this.unitV = (this.heightLimit[1] - this.heightLimit[0]) / this.meterVnum;
    }

    private void requestIFrame() {
        this.mViewHandler.postDelayed(new Runnable(this) {
            final /* synthetic */ DJIPreviewActivityLongan a;

            {
                this.a = r1;
            }

            public void run() {
                DataCameraRequestForIFrame.getInstance().start(null);
            }
        }, 1000);
    }

    private void registerEventBus() {
        c.a().a(this);
    }

    private void unregisterEventBus() {
        c.a().d(this);
    }

    private void handleSwitchModeChanged(dji.device.camera.view.focus.LonganFocusExposureSwitchView.a aVar) {
        if (dji.device.camera.view.focus.LonganFocusExposureSwitchView.a.METER == aVar) {
            this.mFocusAreaView.hideView();
            if (this.mExposureMode == ExposureMode.i && DataCameraGetPushShotParams.getInstance().isGetted()) {
                this.mExposureMode = DataCameraGetPushShotParams.getInstance().getExposureMode();
                this.mAeLock = DataCameraGetPushShotParams.getInstance().isAELock() ? 1 : 0;
            }
            if (this.mExposureMode != ExposureMode.i && ExposureMode.e != this.mExposureMode && this.mAeLock == 0) {
                int metering = DataCameraGetPushShotParams.getInstance().getMetering();
                this.fpvCameraFocusView.show();
                this.mTvFocusDesc.show();
                if (metering != 2) {
                    this.fpvCameraFocusCancelView.hide();
                }
            }
        } else if (dji.device.camera.view.focus.LonganFocusExposureSwitchView.a.FOCUS == aVar) {
            this.fpvCameraFocusView.hide();
            this.mTvFocusDesc.hide();
            this.fpvCameraFocusCancelView.hide();
            this.mFocusAreaView.showView();
        }
    }

    public void onEventMainThread(dji.device.camera.view.focus.LonganFocusExposureSwitchView.a aVar) {
        handleSwitchModeChanged(aVar);
        if (aVar == dji.device.camera.view.focus.LonganFocusExposureSwitchView.a.FOCUS) {
            this.mFocusAreaView.reFocus();
        }
    }

    public void onEventMainThread(dji.device.camera.view.focus.DJIMFFocusRingViewCompat.a aVar) {
        if (aVar == dji.device.camera.view.focus.DJIMFFocusRingViewCompat.a.SHOW) {
            handleSwitchModeChanged(LonganFocusExposureSwitchView.a);
            if (LonganFocusExposureSwitchView.a == dji.device.camera.view.focus.LonganFocusExposureSwitchView.a.FOCUS) {
                this.mFocusAreaView.reFocus();
            }
        } else if (aVar == dji.device.camera.view.focus.DJIMFFocusRingViewCompat.a.NEEDSHOW) {
            showFocusRingView();
        }
    }

    public void onEventBackgroundThread(dji.midware.data.manager.P3.m mVar) {
        switch (mVar) {
            case ConnectOK:
                Log.d("DJIPreviewActivityLongan", "video ok");
                this.mViewHandler.post(this.connectOkRunnable);
                this.mViewHandler.removeMessages(MSG_ID_LOSE_VIDEO);
                this.mViewHandler.removeMessages(MSG_ID_LOSE_VIDEO_SECOND);
                return;
            case ConnectLose:
                Log.d("DJIPreviewActivityLongan", "video lost");
                handleVideoLost();
                return;
            default:
                return;
        }
    }

    private void handleVideoLost() {
        if (!this.mIsInPlaybackActivity) {
            this.mViewHandler.post(this.connectLoseRunnable);
            if (ServiceManager.getInstance().isRemoteOK() && DataOsdGetPushCommon.getInstance().groundOrSky() == 2 && DataCameraGetPushStateInfo.getInstance().getMode() != MODE.NEW_PLAYBACK) {
                this.mViewHandler.sendEmptyMessageDelayed(MSG_ID_LOSE_VIDEO, ToolTipPopup.a);
            }
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (AnonymousClass11.b[oVar.ordinal()]) {
            case 1:
                Log.d("DJIPreviewActivityLongan", "camera ok");
                initRemoteData();
                this.mViewHandler.removeMessages(16384);
                if (this.isTutorialWaitAwake) {
                    this.mViewHandler.sendEmptyMessage(MSG_ID_START_TUTORIAL);
                    return;
                }
                return;
            case 2:
                Log.d("DJIPreviewActivityLongan", "camera lost");
                this.cameraMode = null;
                return;
            default:
                return;
        }
    }

    protected void handleOnEventOver(DataOsdGetPushCommon dataOsdGetPushCommon) {
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        MODE mode = dataCameraGetPushStateInfo.getMode();
        if (mode != MODE.NEW_PLAYBACK && this.mWaitProgressDlg.isShowing()) {
            this.mWaitProgressDlg.dismiss();
        }
        if (mode != this.cameraMode) {
            this.cameraMode = mode;
            this.mViewHandler.sendEmptyMessage(12288);
            if (this.cameraMode == MODE.NEW_PLAYBACK) {
                this.mViewHandler.sendMessage(this.mViewHandler.obtainMessage(32768, 0, 0));
            }
            DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "cameramode=" + this.cameraMode, false, true);
        }
        if (!(mode == MODE.TAKEPHOTO || mode == MODE.RECORD || this.mIsInPlaybackActivity)) {
            DataCameraSetMode dataCameraSetMode = new DataCameraSetMode();
            Log.d("DJIPreviewActivityLongan", "onEventBackgroundThread *****mTmpMode:" + this.mTmpMode + "is inplayback" + this.mIsInPlaybackActivity);
            if (this.mTmpMode == null) {
                this.mTmpMode = MODE.TAKEPHOTO;
            }
            dataCameraSetMode.a(this.mTmpMode);
            dataCameraSetMode.start(new d(this) {
                final /* synthetic */ DJIPreviewActivityLongan a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    Log.d("DJIPreviewActivityLongan", "resume mode failed!" + this.a.mTmpMode + aVar);
                }
            });
        }
        if (dji.device.camera.a.c.getInstance().c() != dji.device.camera.a.c.b.PANO && dataCameraGetPushStateInfo.getUsbState()) {
            DataCameraSwitchUSB.getInstance().a(0).start(new d(this) {
                final /* synthetic */ DJIPreviewActivityLongan a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    Log.d("DJIPreviewActivityLongan", "preview switch usb succeed");
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    Log.d("DJIPreviewActivityLongan", "preview switch usb failed");
                }
            });
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int i = dataCameraGetPushShotParams.isAELock() ? 1 : 0;
        ExposureMode exposureMode = dataCameraGetPushShotParams.getExposureMode();
        if (!(this.mAeLock == i && exposureMode == this.mExposureMode)) {
            this.mAeLock = i;
            this.mExposureMode = exposureMode;
            if (i == 1 || exposureMode == ExposureMode.e) {
                this.mViewHandler.sendMessage(this.mViewHandler.obtainMessage(32768, 0, 0));
            } else {
                this.mViewHandler.sendMessage(this.mViewHandler.obtainMessage(32768, 1, 0));
            }
        }
        RatioType imageRatio = DataCameraGetPushShotParams.getInstance().getImageRatio();
        if (imageRatio != this.tmpRatioType) {
            this.tmpRatioType = imageRatio;
            this.mViewHandler.sendEmptyMessage(12288);
        }
        if (dataCameraGetPushShotParams.getExposureMode() == ExposureMode.c && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO) {
            adjustExposure(dataCameraGetPushShotParams);
        } else if (this.mVideoSurface != null) {
            this.mVideoSurface.setYUVScale(1.0f);
        }
        i = dataCameraGetPushShotParams.getDigitalZoomScale();
        if (this.mShowScale != i) {
            this.mShowScale = i;
            this.mViewHandler.obtainMessage(MSG_ID_SHOW_ZOOM_SCALE).sendToTarget();
        }
    }

    private void adjustExposure(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        float f = 0.0f;
        String str = dataCameraGetPushShotParams.getRelShutterString() + dataCameraGetPushShotParams.getCapMinShutterStr() + dataCameraGetPushShotParams.getCapMinShutterStr();
        if (str != null && !str.equals(this.mCurShutterString)) {
            this.mCurShutterString = str;
            float relShutter = (float) dataCameraGetPushShotParams.getRelShutter();
            int relShutterSpeedDecimal = dataCameraGetPushShotParams.getRelShutterSpeedDecimal();
            if (!dataCameraGetPushShotParams.isRelReciprocal()) {
                f = relShutterSpeedDecimal == 0 ? relShutter : (((float) relShutterSpeedDecimal) / (((float) (relShutterSpeedDecimal + "").length()) * 10.0f)) + relShutter;
            } else if (relShutter != 0.0f) {
                f = 1.0f / relShutter;
            }
            relShutter = (float) dataCameraGetPushShotParams.getCapMinShutter();
            relShutterSpeedDecimal = dataCameraGetPushShotParams.getCapMinShutterDecimal();
            if (dataCameraGetPushShotParams.isCapMinShutterReciprocal()) {
                relShutter = 1.0f / relShutter;
            } else {
                relShutter += ((float) relShutterSpeedDecimal) / (((float) (relShutterSpeedDecimal + "").length()) * 10.0f);
            }
            float capMaxShutter = (float) dataCameraGetPushShotParams.getCapMaxShutter();
            float capMaxShutterDecimal = (float) dataCameraGetPushShotParams.getCapMaxShutterDecimal();
            if (dataCameraGetPushShotParams.isCapMinShutterReciprocal()) {
                capMaxShutter = 1.0f / capMaxShutter;
            } else {
                capMaxShutter += capMaxShutterDecimal / (((float) (capMaxShutterDecimal + "").length()) * 10.0f);
            }
            autoAdjustLuminanceScale(f, relShutter, capMaxShutter);
        }
    }

    private void autoAdjustLuminanceScale(float f, float f2, float f3) {
        float f4 = 1.0f;
        if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.TAKEPHOTO && DataCameraGetPushShotParams.getInstance().getExposureMode() == ExposureMode.c && f2 != 0.0f && f3 != 0.0f && f != 0.0f && f2 <= f3) {
            if (f > f3) {
                f4 = (float) Math.sqrt(((double) f) / ((double) f3));
            } else if (f < f2) {
                f4 = (float) Math.sqrt(((double) f) / ((double) f2));
            }
        }
        this.mVideoSurface.setYUVScale(f4);
    }

    private void resetData() {
        this.mbShotConnected = true;
        this.mAeLock = -1;
        this.mExposureMode = ExposureMode.i;
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        if (dji.logic.f.b.n(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            boolean isShotConnected = dataCameraGetPushShotInfo.isShotConnected();
            if (isShotConnected != this.mbShotConnected) {
                this.mbShotConnected = isShotConnected;
                if (isShotConnected) {
                    postShotConnectTip(false);
                } else {
                    postShotConnectTip(true);
                }
            }
            int mFFocusStatus = dataCameraGetPushShotInfo.getMFFocusStatus();
            if (mFFocusStatus != this.mMfFocusStatus) {
                this.mMfFocusStatus = mFFocusStatus;
                if (mFFocusStatus == 1) {
                    this.mFocusAreaView.hideView();
                    this.fpvCameraFocusView.hide();
                    this.mTvFocusDesc.hide();
                    this.fpvCameraFocusCancelView.hide();
                } else {
                    handleSwitchModeChanged(LonganFocusExposureSwitchView.a);
                }
            }
            FuselageFocusMode fuselageFocusMode = dataCameraGetPushShotInfo.getFuselageFocusMode();
            if (fuselageFocusMode == FuselageFocusMode.OneAuto || fuselageFocusMode == FuselageFocusMode.ContinuousAuto) {
                mFFocusStatus = dataCameraGetPushShotInfo.getFocusStatus();
                if (this.mFocusStatus != mFFocusStatus) {
                    this.mFocusStatus = mFFocusStatus;
                    if (mFFocusStatus == 2 && LonganFocusExposureSwitchView.a == dji.device.camera.view.focus.LonganFocusExposureSwitchView.a.FOCUS && DataCameraGetPushStateInfo.getInstance().getRecordState() != DataCameraGetPushStateInfo$RecordType.STARTING) {
                        DJICameraDataManagerCompat.getInstance().playFocusSound(getApplicationContext());
                        return;
                    }
                    return;
                }
                return;
            }
            this.mFocusStatus = 0;
        }
    }

    private void postShotConnectTip(boolean z) {
        LonganPopWarnView.getInstance(getApplicationContext()).pop(R.drawable.longan_notice, R.string.fpv_shot_nonconnect_tip, dji.device.widget.LonganPopWarnView.a.LENGTH_SHORT);
    }

    public void onEventBackgroundThread(dji.device.camera.view.common.DJICameraControlViewLongan.a aVar) {
        switch (aVar) {
            case POINT:
                if (this.meterHnum == 0) {
                    DataCameraGetMeteringArea.getInstance().start(this.meteringAreaCallBack);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(dji.device.camera.view.focus.DJIMFDemarcateViewLongan.a aVar) {
        if (aVar == dji.device.camera.view.focus.DJIMFDemarcateViewLongan.a.NEEDSHOW && DataCameraGetPushStateInfo.getInstance().isGetted() && dji.logic.f.b.a(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            this.mMfDemarcateview.showView();
        }
    }

    public void onEventMainThread(dji.device.camera.datamanager.DJICameraDataManagerCompat.a aVar) {
        if (aVar == dji.device.camera.datamanager.DJICameraDataManagerCompat.a.MF_DEMARCATE) {
            onEventMainThread(dji.device.camera.view.focus.DJIMFDemarcateViewLongan.a.NEEDSHOW);
        }
    }

    public void onEventMainThread(dji.midware.media.j.g.c cVar) {
        if (openRecord) {
            Log.i("DJIPreviewActivity", "received a bus event for bitmap");
            dji.midware.media.j.g.a(getBitmap(), cVar.a());
        }
    }

    public void onEventBackgroundThread(ProductType productType) {
        DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "lasType=" + i.getInstance().d() + " typenow=" + i.getInstance().c(), false, true);
        switch (AnonymousClass11.d[i.getInstance().c().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                finish();
                return;
            default:
                return;
        }
    }

    private void setCenterMetering() {
        if (ServiceManager.getInstance().isConnected()) {
            this.mViewHandler.sendEmptyMessage(MSG_ID_METERING_CENTER);
            this.meterSetter.a(dji.midware.data.config.P3.b.a.y).a(0).start(new d(this) {
                final /* synthetic */ DJIPreviewActivityLongan a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.showCenterMeter();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        }
    }

    private void showCenterMeter() {
        this.mViewHandler.sendMessage(this.mViewHandler.obtainMessage(32768, 1, 0));
        this.mViewHandler.sendMessageDelayed(this.mViewHandler.obtainMessage(32768, 0, 0), 2000);
    }

    private void setMeteringArea(MotionEvent motionEvent) {
        if (this.unitH != 0) {
            int y = (((int) ((motionEvent.getY() - ((float) this.heightLimit[0])) / ((float) this.unitV))) * this.meterHnum) + ((int) ((motionEvent.getX() - ((float) this.widthLimit[0])) / ((float) this.unitH)));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoSurface.getLayoutParams();
            this.mMeterXRatio = (motionEvent.getX() - ((float) ((mScreenWidth - layoutParams.width) / 2))) / ((float) layoutParams.width);
            this.mMeterYRatio = (motionEvent.getY() - ((float) ((mScreenHeight - layoutParams.height) / 2))) / ((float) layoutParams.height);
            this.mViewHandler.sendMessage(this.mViewHandler.obtainMessage(20480, motionEvent));
            DataCameraSetMeteringArea.getInstance().a(y).start(new d(this) {
                final /* synthetic */ DJIPreviewActivityLongan a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        }
    }

    private void resetMeterPosition() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoSurface.getLayoutParams();
        float f = (this.mMeterXRatio * ((float) layoutParams.width)) + ((float) ((mScreenWidth - layoutParams.width) / 2));
        float f2 = ((this.mMeterYRatio * ((float) layoutParams.height)) + ((float) (mScreenHeight / 2))) - ((float) (layoutParams.height / 2));
        Drawable background = this.fpvCameraFocusView.getBackground();
        float intrinsicWidth = (((float) background.getIntrinsicWidth()) * 1.0f) / 2.0f;
        float intrinsicHeight = (((float) background.getIntrinsicHeight()) * 1.0f) / 2.0f;
        this.fpvCameraFocusView.setX(f - intrinsicWidth);
        this.fpvCameraFocusView.setY(f2 - intrinsicHeight);
        this.fpvCameraFocusCancelView.setX((intrinsicWidth + f) - ((float) this.fpvCameraFocusCancelView.getWidth()));
        this.fpvCameraFocusCancelView.setY(f2 - intrinsicHeight);
        this.mTvFocusDesc.setX(f - ((float) (this.mTvFocusDesc.getWidth() / 2)));
        this.mTvFocusDesc.setY((f2 + intrinsicHeight) + 5.0f);
    }

    private void setTrackingMetering(MotionEvent motionEvent) {
        if (this.unitH != 0) {
            DataCameraSetMeteringArea.getInstance().a(((int) ((motionEvent.getX() - ((float) this.widthLimit[0])) / ((float) this.unitH))) + (((int) ((motionEvent.getY() - ((float) this.heightLimit[0])) / ((float) this.unitV))) * this.meterHnum)).start(new d(this) {
                final /* synthetic */ DJIPreviewActivityLongan a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        }
    }

    private void setTrackingArea(MotionEvent motionEvent) {
        this.mViewHandler.sendMessage(this.mViewHandler.obtainMessage(20481, motionEvent));
        int y = (int) ((motionEvent.getY() / ((float) mScreenHeight)) * 65535.0f);
        DataCameraSetTrackingParms.getInstance().a(true).a((int) ((motionEvent.getX() / ((float) mScreenWidth)) * 65535.0f)).b(y).start(new d(this) {
            final /* synthetic */ DJIPreviewActivityLongan a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void startLimitAreaAnim(float f, float f2, int i) {
        if (this.mLimitAreaAnim == null) {
            this.mLimitAreaAnim = new AlphaAnimation(f, f2);
            this.mLimitAreaAnim.setDuration(100);
            this.mLimitAreaAnim.setRepeatCount(i);
            this.mLimitAreaAnim.setRepeatMode(2);
            this.mLimitAreaAnim.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ DJIPreviewActivityLongan a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    this.a.mImgLimitArea.go();
                }
            });
        }
        this.mImgLimitArea.show();
        this.mImgLimitArea.startAnimation(this.mLimitAreaAnim);
    }

    private void onTouchUp() {
        DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "onTouchUp", false, true);
        if (this.gimbalSpeedController.d) {
            resetControlMode();
        }
    }

    public static boolean isPopViewShown() {
        return DJICameraControlViewLongan.getIsSettingParms() || LonganNewTimelapseMainLayout.e || LonganCameraShotcutsView.a || LonganGimbalNewShotcutsView.a;
    }

    private void resetControlMode() {
        this.mViewHandler.removeMessages(MSG_ID_GIMBAL_CONTROL);
        if (this.gimbalSpeedController != null) {
            this.gimbalSpeedController.a();
        }
    }

    public void showGimbalControl() {
        try {
            Class cls = Class.forName("dji.pilot.reflect.FpvReflect");
            cls.getMethod("flurryGimbalDrag", new Class[0]).invoke(cls, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.gimbalSpeedController.a(this.gimbalCenterEvent);
    }

    public Bitmap getBitmap() {
        return this.mVideoSurface.getBitmap();
    }

    public Bitmap getBitmap(int i) {
        return this.mVideoSurface.getBitmap();
    }

    public void onEventMainThread(a aVar) {
        startPlaybackActivity();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == FTPCodes.RESTART_MARKER) {
            DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "requestCode == 110", false, true);
            if (this.mVideoSurface != null) {
                DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "mVideoDecoder resetToManager", false, true);
                this.mVideoSurface.resetToManager();
            }
        } else if (REQUEST_START_PLAYBACK == i) {
            DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "end play back activity", false, true);
            endPlaybackActivity();
        }
        this.mIsInPlaybackActivity = false;
    }

    private void startPlaybackActivity() {
        this.mTmpMode = DataCameraGetPushStateInfo.getInstance().getMode();
        Log.d("DJIPreviewActivityLongan", "cur mode:" + this.mTmpMode);
        try {
            Intent intent = new Intent(this, Class.forName("dji.pilot.playback.litchi.DJIPlayBackActivity"));
            intent.setFlags(131072);
            intent.putExtra("isSensor", true);
            startActivityForResult(intent, REQUEST_START_PLAYBACK);
            this.mIsInPlaybackActivity = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void endPlaybackActivity() {
        if (!this.mWaitProgressDlg.isShowing()) {
            this.mWaitProgressDlg.show();
        }
        if (this.mVideoSurface != null) {
            DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "mVideoDecoder resetToManager", false, true);
            this.mVideoSurface.resetToManager();
        }
        onEventBackgroundThread(DataCameraGetPushStateInfo.getInstance());
        Log.d("", "test playback:   " + System.currentTimeMillis());
    }

    public void onEventMainThread(DJIUIEventManagerLongan.i iVar) {
        if (iVar == DJIUIEventManagerLongan.i.PLEASE_TAKE_SCREENSHORT) {
            c.a().e(DJIUIEventManagerLongan.i.GOT_SCREENSHORT.a(getBitmap(iVar.d)));
        }
    }

    public void onEventMainThread(m mVar) {
        if (mVar == m.HIDE_TRACKING) {
            this.mViewHandler.sendMessage(this.mViewHandler.obtainMessage(20482, 0, 0));
        } else if (mVar == m.SHOW_GIMBAL_ROLL_TUNE_VIEW) {
            if (this.mGimbalRollTuneView == null) {
                ViewStub viewStub = (ViewStub) findViewById(R.id.longan_gimbal_roll_adjust_vs);
                if (viewStub != null) {
                    this.mGimbalRollTuneView = (DJIGimbalRollFineTuneViewCompat) viewStub.inflate();
                    return;
                }
                return;
            }
            this.mGimbalRollTuneView.show();
        } else if (mVar == m.SHOW_MENU) {
            if (getResources().getConfiguration().orientation == 2) {
                this.mMenuBg.setVisibility(0);
            }
        } else if (mVar == m.HIDE_MENU) {
            this.mMenuBg.setVisibility(8);
        } else if (mVar == m.HIDE_ALL) {
            this.mCameraBg.setVisibility(4);
            this.mQuickSetBg.setVisibility(4);
        } else if (mVar == m.SHOW_ALL) {
            this.mCameraBg.setVisibility(0);
            this.mQuickSetBg.setVisibility(0);
        }
    }

    public void onEventMainThread(DJIUIEventManagerLongan.e eVar) {
        if (eVar == DJIUIEventManagerLongan.e.TUTORIAL_FINISHED) {
            ((ViewGroup) this.mTutorialView.getParent()).removeView(this.mTutorialView);
        } else if (eVar == DJIUIEventManagerLongan.e.ENTER_SLEEP_MODE) {
            handleVideoLost();
        }
    }

    public static void reflectPostEvent(String str) {
        c.a().e(Enum.valueOf(m.class, str));
    }

    public void onEventBackgroundThread(DataCameraGetPushRecordingName dataCameraGetPushRecordingName) {
        if (dji.device.camera.a.c.getInstance().c() == dji.device.camera.a.c.b.TIMELAPSE && DataCameraGetPushStateInfo.getInstance().getVerstion() >= 4) {
            DJIAlbumFileInfo dJIAlbumFileInfo = new DJIAlbumFileInfo();
            dJIAlbumFileInfo.d = dataCameraGetPushRecordingName.getIndex();
            dJIAlbumFileInfo.c = System.currentTimeMillis();
            dJIAlbumFileInfo.e = 0;
            if (dataCameraGetPushRecordingName.getIndex() != this.mTimelapseFpvIndex) {
                this.mTimelapseFpvIndex = dataCameraGetPushRecordingName.getIndex();
                this.nailLoader.a(dJIAlbumFileInfo, this.mAlbumPullListener);
                this.nailLoader.a(dji.midware.data.config.a.a.c.TIMELAPSE);
                if (!this.isDownloading) {
                    this.nailLoader.b();
                }
            }
        }
    }

    public void onEventMainThread(dji.device.camera.a.b.c cVar) {
        if (DataCameraGetPushStateInfo.getInstance().getVerstion() < 4) {
            return;
        }
        if (cVar != dji.device.camera.a.b.c.TIMING || dji.device.camera.a.c.getInstance().c() != dji.device.camera.a.c.b.TIMELAPSE) {
            this.mTimelapseFpvIv.setVisibility(8);
            if (this.mViewHandler.hasMessages(MSG_ID_SHOW_TIMELAPSE_FPV)) {
                this.mViewHandler.removeMessages(MSG_ID_SHOW_TIMELAPSE_FPV);
            }
            this.mViewHandler.sendEmptyMessage(12288);
        } else if (!this.mTimelapseFpvIv.isShown()) {
            this.mTimelapseFpvIv.setVisibility(0);
            this.mViewHandler.obtainMessage(MSG_ID_SHOW_TIMELAPSE_FPV, getBitmap(1)).sendToTarget();
            this.mViewHandler.sendEmptyMessage(12288);
        }
    }

    public void handleMFDemarcateFirst() {
        if (DataCameraGetPushStateInfo.getInstance().isGetted() && dji.logic.f.b.a(DataCameraGetPushStateInfo.getInstance().getCameraType()) && DJICameraDataManagerCompat.getInstance().needPostDemarcateTip()) {
            onEventMainThread(dji.device.camera.view.focus.DJIMFDemarcateViewLongan.a.NEEDSHOW);
        }
    }

    public void onEventMainThread(DataCameraSetZoomParams dataCameraSetZoomParams) {
        DJILogHelper.getInstance().LOGD("DJIPreviewActivityLongan", "########DataCameraSetZoomParams:", false, true);
    }

    public void onEventMainThread(dji.device.camera.a.c cVar) {
        if (cVar.c() != this.mPhotoType) {
            this.mViewHandler.sendEmptyMessage(12288);
            this.mPhotoType = cVar.c();
        }
    }

    private void saveCameraTempratureInfo() {
        final DataCameraGetRecordFan instance = DataCameraGetRecordFan.getInstance();
        instance.start(new d(this) {
            final /* synthetic */ DJIPreviewActivityLongan b;

            public void onSuccess(Object obj) {
                int b = dji.pilot.set.h.b(this.b.getApplicationContext(), DJIPreviewActivityLongan.KEY_CAMERA_FAN_ON_T, 0);
                int b2 = dji.pilot.set.h.b(this.b.getApplicationContext(), DJIPreviewActivityLongan.KEY_CAMERA_FAN_OFF_T, 0);
                this.b.mOnT = instance.getForceFanOnT();
                this.b.mOffT = instance.getForceFanOffT();
                if (b != this.b.mOnT || b2 != this.b.mOffT) {
                    dji.pilot.set.h.a(this.b.getApplicationContext(), DJIPreviewActivityLongan.KEY_CAMERA_FAN_ON_T, this.b.mOnT);
                    dji.pilot.set.h.a(this.b.getApplicationContext(), DJIPreviewActivityLongan.KEY_CAMERA_FAN_OFF_T, this.b.mOffT);
                    dji.pilot.set.h.a(this.b.getApplicationContext(), DJIPreviewActivityLongan.KEY_CAMERA_FAN_CHANGED, true);
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    public void onEventMainThread(DataCameraGetPushPrepareOpenFan dataCameraGetPushPrepareOpenFan) {
        int leftSeconds = dataCameraGetPushPrepareOpenFan.getLeftSeconds();
        if (leftSeconds > 0 && leftSeconds != 255) {
            LonganPopWarnView.getInstance(getApplicationContext()).pop(0, String.format(getString(R.string.longan_camera_open_fan_warning), new Object[]{Integer.valueOf(leftSeconds)}), dji.device.widget.LonganPopWarnView.a.LENGTH_LONG);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 2 && motionEvent.getAction() == 261) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.lastTwoFingersDownForInnerTools < 800) {
                try {
                    Class.forName("com.dji.tools.base.InnerToolsDialog").getMethod("showInnerTools", new Class[]{Context.class}).invoke(null, new Object[]{this});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.lastTwoFingersDownForInnerTools = currentTimeMillis;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
