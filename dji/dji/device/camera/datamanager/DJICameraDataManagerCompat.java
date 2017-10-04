package dji.device.camera.datamanager;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetIso.TYPE;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetShotInfo;
import dji.midware.data.model.P3.DataCameraSetAperture;
import dji.midware.data.model.P3.DataCameraSetExposureMode;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetFocusWindow;
import dji.midware.data.model.P3.DataCameraSetIso;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.e.d;
import dji.midware.util.i;
import dji.pilot.fpv.R;
import java.util.Locale;

public class DJICameraDataManagerCompat implements dji.device.common.view.set.view.a, dji.device.common.view.set.view.a.a, dji.device.common.view.set.view.a.b {
    private static final int DEFAULT_DEMARCATE_VALUE = 0;
    private static final long DELAY_DEMARCATE_AMODE = 300;
    private static final long DELAY_GET_CAMERAINFO = 100;
    private static final int FPS_120 = 7;
    private static final int FPS_15 = 0;
    private static final int FPS_24 = 1;
    private static final int FPS_240 = 8;
    private static final int FPS_25 = 2;
    private static final int FPS_30 = 3;
    private static final int FPS_48 = 4;
    private static final int FPS_480 = 9;
    private static final int FPS_50 = 5;
    private static final int FPS_60 = 6;
    private static final String KEY_DEMARCATE_VALUE = "_value";
    private static final String KEY_SHOWINFO = "key_camera_showinfo";
    private static final int MAX_RETRY_TIMES = 8;
    public static final int MODE_AUTO = 1;
    public static final int MODE_MANUAL = 4;
    public static final int MODE_SHUTTER = 2;
    private static final int MSG_ID_CAMERAINFO_GETTED = 4097;
    private static final int MSG_ID_DEMARCATE_17APERTURE = 8194;
    private static final int MSG_ID_DEMARCATE_AMODE = 8193;
    private static final int MSG_ID_DEMARCATE_ISOAUTO = 8195;
    private static final int MSG_ID_DEMARCATE_REC = 8192;
    private static final int MSG_ID_GET_CAMERAINFO = 4096;
    private static final int RSL_1280x720 = 4;
    private static final int RSL_1920x1080 = 10;
    private static final int RSL_2704x1520 = 24;
    private static final int RSL_3840x2160 = 16;
    private static final int RSL_4096x2160 = 22;
    public static final int TYPE_VIDEORESOLUTION_1080P = 3;
    public static final int TYPE_VIDEORESOLUTION_2704P = 2;
    public static final int TYPE_VIDEORESOLUTION_4KFULL = 0;
    public static final int TYPE_VIDEORESOLUTION_4KNOTFULL = 1;
    public static final int TYPE_VIDEORESOLUTION_720P = 4;
    public static final int TYPE_VIDEORESOLUTION_COUNT = 5;
    private static final int _Title = -1;
    private static final int[] mLonganNtscFpsValues = new int[]{-1, 1, 3, 1, -1, 3, 1, -1, 6, 4, 3, 1, -1, 6, 4, 3, 1, -1, 7};
    private static final int[] mLonganNtscRatioValues = new int[]{-1, 22, 16, 16, -1, 24, 24, -1, 10, 10, 10, 10, -1, 4, 4, 4, 4, -1, 10};
    private static final int[] mLonganPalFpsValues = new int[]{-1, 2, 1, 2, 1, -1, 2, 1, -1, 5, 4, 2, 1, -1, 5, 4, 2, 1, -1, 7};
    private static final int[] mLonganPalRatioValues = new int[]{-1, 22, 22, 16, 16, -1, 24, 24, -1, 10, 10, 10, 10, -1, 4, 4, 4, 4, -1, 10};
    public static final int[] mPictureFormatImgRes = new int[]{R.drawable.advanced_more_photoformat_raw, R.drawable.advanced_more_photoformat_jpeg, R.drawable.advanced_more_photoformat_jpeg_raw};
    public static final byte[][] mPictureStyleValue = new byte[][]{new byte[]{(byte) 0, (byte) 0, (byte) 0}, new byte[]{(byte) 1, (byte) 1, (byte) 0}, new byte[]{(byte) -1, (byte) 0, (byte) 0}, new byte[]{(byte) 0, (byte) 1, (byte) 0}};
    public static final int[] mShowGridImgRes = new int[]{R.drawable.advanced_more_none, R.drawable.advanced_more_grid_sudoku, R.drawable.advanced_more_grid_diagonal, R.drawable.advanced_more_grid_center};
    public static final int[] mStyleNameStr = new int[]{R.string.shotcuts_style_standard_txt, R.string.shotcuts_style_scenery_txt, R.string.shotcuts_style_soft_txt, R.string.shotcuts_style_custom_txt};
    public static final int[] mVideoFpsCmdIds = new int[]{1, 2, 3, 4, 5, 6, 7};
    private static final int[][] mVideoFpsImgResIds;
    public static final int[] mWhiteBalanceImgRes = new int[]{R.drawable.longan_camera_setting_wb_value_auto_off, R.drawable.longan_camera_setting_wb_value_outdoor_off, R.drawable.longan_camera_setting_wb_value_indoor_off, R.drawable.longan_camera_setting_wb_value_tungsten_off, R.drawable.longan_camera_setting_wb_value_neon_off, R.drawable.advanced_more_whitebalance_custom};
    public static final int[] mWhiteBalanceInterval = new int[]{20, 100};
    private String SLOWMOTIONStr = null;
    private String[] mApertureAr = null;
    private int[] mApertureValueAr = null;
    private CameraType mCameraType = CameraType.OTHER;
    private b mCenterPointColor = b.WHITE;
    private int mCenterPoints = 0;
    Context mContext;
    private String[] mCustomWBNameArray = null;
    private int[] mCustomWBValueArray = null;
    private volatile int mDemarcateValue = 0;
    private String[] mDigitalFilterAr = null;
    private String[] mDigitalFilterArAll = null;
    private int[] mDigitalFilterCmdIdAr = null;
    private int[] mDigitalFilterCmdIdArAll = null;
    private String[] mEvAr = null;
    private int mEvCenterSoundId = 0;
    private int[] mEvCmdIdAr = null;
    private int mFocusSoundId = 0;
    private volatile boolean mHadDemarcate = true;
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ DJICameraDataManagerCompat a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            boolean z = false;
            switch (message.what) {
                case 4096:
                    this.a.getCameraInfo(message.arg1);
                    return;
                case 4097:
                    String format = String.format(Locale.US, "%d%d%d", new Object[]{Integer.valueOf(DataCameraGetShotInfo.getInstance().getMemberId()), Integer.valueOf(DataCameraGetShotInfo.getInstance().getModelId()), Integer.valueOf(DataCameraGetShotInfo.getInstance().getHardVersion())});
                    this.a.mNeedDemarcate = dji.pilot.set.a.b(this.a.mContext, format, true);
                    DJICameraDataManagerCompat dJICameraDataManagerCompat = this.a;
                    if (!this.a.mNeedDemarcate) {
                        z = true;
                    }
                    dJICameraDataManagerCompat.mHadDemarcate = z;
                    if (this.a.needDemarcate()) {
                        FuselageFocusMode fuselageFocusMode = DataCameraGetPushShotInfo.getInstance().getFuselageFocusMode();
                        if (fuselageFocusMode == FuselageFocusMode.Manual || fuselageFocusMode == FuselageFocusMode.ManualFine) {
                            this.a.mNeedPostDemarcateTip = true;
                            dji.thirdparty.a.c.a().e(a.MF_DEMARCATE);
                        }
                    } else {
                        this.a.mDemarcateValue = dji.pilot.set.a.b(this.a.mContext, format + DJICameraDataManagerCompat.KEY_DEMARCATE_VALUE, this.a.mDemarcateValue);
                    }
                    dji.thirdparty.a.c.a().e(a.CAMERAINFO_GETTED);
                    return;
                case 8192:
                    if (message.arg1 == 1) {
                        this.a.prepareToAMode();
                        return;
                    }
                    return;
                case 8193:
                    if (1 == message.arg1) {
                        this.a.prepareTo17Aperture();
                        return;
                    }
                    return;
                case 8194:
                    if (1 == message.arg1) {
                        this.a.prepareToIsoAuto();
                        return;
                    }
                    return;
                case DJICameraDataManagerCompat.MSG_ID_DEMARCATE_ISOAUTO /*8195*/:
                    if (1 != message.arg1) {
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private String[] mISOAr = null;
    private int[] mISOCmdIdAr = null;
    private int mLineRef = 0;
    private b mLineRefColor = b.WHITE;
    private volatile boolean mNeedDemarcate = false;
    private volatile boolean mNeedPostDemarcateTip = false;
    private String[] mPictureFormatAr = null;
    private int[] mPictureFormatCmdIdAr = null;
    private String[] mShowGridAr = null;
    private int[] mShowGridCmdIdAr = null;
    private boolean mShowInfo = true;
    private String[] mShutterAr = null;
    private String[] mShutterValueAr = null;
    private int mSimpleSoundId = 0;
    private SoundPool mSoundPlayer = null;
    private String[] mWhiteBalanceAr = null;
    private int[] mWhiteBalanceCmdIdAr = null;

    public enum a {
        SHOW_CAMERAINFO,
        HIDE_CAMERAINFO,
        PHOTOTYPE_CHANGED,
        PHOTOTYPE_CHANGED_AUTO,
        CAMERAINFO_GETTED,
        MF_DEMARCATE,
        CENTER_POINT_CHANGED,
        CENTER_POINT_COLOR_CHANGED,
        LINEREF_CHANGED,
        LINEREF_COLOR_CHANGED,
        LINEREF_CUSTOMATTR_CHANGED
    }

    public enum b {
        WHITE(0, R.color.white),
        YELLOW(1, R.color.ref_yellow),
        RED(2, R.color.ref_red),
        BLUE(3, R.color.ref_blue),
        GREEN(4, R.color.ref_green),
        BLACK(5, R.color.black);
        
        private int g;
        private int h;

        private b(int i, int i2) {
            this.g = 0;
            this.h = R.color.white;
            this.g = i;
            this.h = i2;
        }

        public int a() {
            return this.h;
        }

        public int b() {
            return this.g;
        }

        private boolean a(int i) {
            return this.g == i;
        }

        public static b find(int i) {
            for (b bVar : values()) {
                if (bVar.a(i)) {
                    return bVar;
                }
            }
            return WHITE;
        }
    }

    private static final class c {
        private static final DJICameraDataManagerCompat a = new DJICameraDataManagerCompat();

        private c() {
        }
    }

    static {
        r0 = new int[5][];
        r0[0] = new int[]{R.drawable.advanced_more_videoformat_4096x2160_24p, R.drawable.advanced_more_videoformat_4096x2160_25p, R.drawable.advanced_more_videoformat_4096x2160_30p};
        r0[1] = new int[]{R.drawable.advanced_more_videoformat_3840x2160_24p, R.drawable.advanced_more_videoformat_3840x2160_25p, R.drawable.advanced_more_videoformat_3840x2160_30p};
        r0[2] = new int[]{R.drawable.advanced_more_videoformat_2704x1520_24p, R.drawable.advanced_more_videoformat_2704x1520_25p, R.drawable.advanced_more_videoformat_2704x1520_30p};
        r0[3] = new int[]{R.drawable.advanced_more_videoformat_1920x1080_24p, R.drawable.advanced_more_videoformat_1920x1080_25p, R.drawable.advanced_more_videoformat_1920x1080_30p, R.drawable.advanced_more_videoformat_1920x1080_48p, R.drawable.advanced_more_videoformat_1920x1080_50p, R.drawable.advanced_more_videoformat_1920x1080_60p, R.drawable.advanced_more_videoformat_1920x1080_slo};
        r0[4] = new int[]{R.drawable.advanced_more_videoformat_1280x720_24p, R.drawable.advanced_more_videoformat_1280x720_25p, R.drawable.advanced_more_videoformat_1280x720_30p, R.drawable.advanced_more_videoformat_1280x720_48p, R.drawable.advanced_more_videoformat_1280x720_50p, R.drawable.advanced_more_videoformat_1280x720_60p};
        mVideoFpsImgResIds = r0;
    }

    public static DJICameraDataManagerCompat getInstance() {
        return c.a;
    }

    public static void initEventBus(Context context) {
        dji.thirdparty.a.c.a().a(getInstance());
        getInstance().mContext = context;
    }

    public void loadData(Context context) {
        getInstance().loadSound(context);
        getAllArs(this.mContext);
    }

    public void unInif() {
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
    }

    public void loadSound(Context context) {
        if (this.mSoundPlayer == null) {
            this.mSoundPlayer = new SoundPool(2, 3, 0);
            this.mSimpleSoundId = this.mSoundPlayer.load(context, R.raw.camera_simple_click, 1);
            this.mEvCenterSoundId = this.mSoundPlayer.load(context, R.raw.camera_ev_center, 1);
            this.mFocusSoundId = this.mSoundPlayer.load(context, R.raw.camera_focus_beep, 1);
        }
    }

    public void releaseSound() {
        if (this.mSoundPlayer != null) {
            this.mSoundPlayer.unload(this.mSimpleSoundId);
            this.mSoundPlayer.unload(this.mEvCenterSoundId);
            this.mSoundPlayer.release();
            this.mSoundPlayer = null;
        }
        this.mSimpleSoundId = 0;
    }

    public void playSimpleSound(Context context) {
        playSound(this.mSimpleSoundId, context);
    }

    public void playEvCenterSound(Context context) {
        playSound(this.mEvCenterSoundId, context);
    }

    public void playFocusSound(Context context) {
        playSound(this.mFocusSoundId, context);
    }

    private void playSound(int i, Context context) {
        float f = 0.3f;
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        float streamVolume = ((float) audioManager.getStreamVolume(3)) / ((float) audioManager.getStreamMaxVolume(3));
        if (streamVolume >= 0.3f) {
            f = streamVolume;
        }
        this.mSoundPlayer.play(i, f, f, 100, 0, 1.0f);
    }

    private void getAllArs(Context context) {
        Resources resources = context.getResources();
        this.SLOWMOTIONStr = resources.getString(R.string.set_camera_video_resolution_module_title_slow_motion);
        this.mPictureFormatAr = resources.getStringArray(R.array.camera_pictureformat_array);
        this.mPictureFormatCmdIdAr = resources.getIntArray(R.array.camera_pictureformat_value_array);
        this.mWhiteBalanceAr = resources.getStringArray(R.array.longan_camera_whitebalance_array);
        this.mWhiteBalanceCmdIdAr = resources.getIntArray(R.array.longan_camera_whitebalance_value_array);
        this.mISOAr = resources.getStringArray(R.array.longan_camera_iso_array);
        this.mISOCmdIdAr = resources.getIntArray(R.array.longan_camera_iso_value_array);
        this.mShutterAr = resources.getStringArray(R.array.longan_camera_shutter_array);
        this.mShutterValueAr = resources.getStringArray(R.array.longan_camera_shutter_value_array);
        this.mApertureAr = resources.getStringArray(R.array.longan_camera_aperture_array);
        this.mApertureValueAr = resources.getIntArray(R.array.longan_camera_aperture_value_array);
        this.mEvAr = resources.getStringArray(R.array.camera_ev_array);
        this.mEvCmdIdAr = resources.getIntArray(R.array.camera_ev_value_array);
        this.mDigitalFilterAr = resources.getStringArray(R.array.camera_digitalfilter_array);
        this.mDigitalFilterCmdIdAr = resources.getIntArray(R.array.camera_digitalfilter_value_array);
        this.mDigitalFilterArAll = resources.getStringArray(R.array.camera_digitalfilter_array_osmo);
        this.mDigitalFilterCmdIdArAll = resources.getIntArray(R.array.camera_digitalfilter_value_array_osmo);
        this.mShowGridAr = resources.getStringArray(R.array.camera_grid_str_array);
        this.mShowGridCmdIdAr = resources.getIntArray(R.array.camera_grid_value_array);
        this.mCustomWBValueArray = resources.getIntArray(R.array.longan_camera_wb_value_array);
        this.mCustomWBNameArray = resources.getStringArray(R.array.longan_camera_wb_name_array);
        this.mCenterPoints = i.b(context, "key_camera_centerpoints", 0);
        this.mCenterPointColor = b.find(i.b(context, "key_camera_cp_color", b.WHITE.b()));
        this.mLineRef = i.b(context, "key_camera_lineref", 0);
        this.mLineRefColor = b.find(i.b(context, "key_camera_lineref_color", b.WHITE.b()));
        l[4][0] = i.b(context, "key_camera_lineref_cwidth", l[4][0]);
        l[4][1] = i.b(context, "key_camera_lineref_cheight", l[4][1]);
    }

    public String getISODesc(int i) {
        return this.mISOAr[i];
    }

    public String[] getISOAr() {
        return this.mISOAr;
    }

    public int getISOValuePos(int i) {
        int i2 = 0;
        int length = this.mISOCmdIdAr.length;
        while (i2 < length - 1 && i > this.mISOCmdIdAr[i2]) {
            i2++;
        }
        return i2;
    }

    public int getISOCmdValue(int i) {
        return this.mISOCmdIdAr[i];
    }

    public int[] getISOCmdValueAr() {
        return this.mISOCmdIdAr;
    }

    public String[] getShutterAr() {
        return this.mShutterAr;
    }

    public String getShutterValue(int i) {
        return this.mShutterValueAr[i];
    }

    public int getShutterVauePos(String str) {
        int length = this.mShutterValueAr.length;
        for (int i = 0; i < length; i++) {
            if (this.mShutterValueAr[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public String[] getApertureAr() {
        return this.mApertureAr;
    }

    public int[] getApertureValueAr() {
        return this.mApertureValueAr;
    }

    public int getAperturePos(String str) {
        int length = this.mApertureAr.length;
        for (int i = 0; i < length; i++) {
            if (this.mApertureAr[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public int getApertureValuePos(int i) {
        return findPosByValue(this.mApertureValueAr, i, -1);
    }

    public int getApertureValue(int i) {
        return this.mApertureValueAr[i];
    }

    public String[] getEvAr() {
        return this.mEvAr;
    }

    public int getEvCmdValue(int i) {
        return this.mEvCmdIdAr[i];
    }

    public int getEvValuePos(int i) {
        int length = this.mEvCmdIdAr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i == this.mEvCmdIdAr[i2]) {
                return i2;
            }
        }
        return 0;
    }

    public int[] getEvCmdValueAr() {
        return this.mEvCmdIdAr;
    }

    public String[] getWhiteBalanceAr() {
        return this.mWhiteBalanceAr;
    }

    public int getWhiteBalancePos(int i) {
        int length = this.mWhiteBalanceCmdIdAr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i == this.mWhiteBalanceCmdIdAr[i2]) {
                return i2;
            }
        }
        return 0;
    }

    public int[] getWhiteBalanceCmdIdAr() {
        return this.mWhiteBalanceCmdIdAr;
    }

    public void getCameraInfo(final int i) {
        if (i < 8 && !this.mHandler.hasMessages(4096)) {
            DataCameraGetShotInfo.getInstance().start(new d(this) {
                final /* synthetic */ DJICameraDataManagerCompat b;

                public void onSuccess(Object obj) {
                    this.b.mHandler.sendEmptyMessageDelayed(4097, DJICameraDataManagerCompat.DELAY_GET_CAMERAINFO);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    if (aVar != dji.midware.data.config.P3.a.E && !this.b.mHandler.hasMessages(4096)) {
                        this.b.mHandler.sendMessageDelayed(this.b.mHandler.obtainMessage(4096, i + 1, 0), DJICameraDataManagerCompat.DELAY_GET_CAMERAINFO);
                    }
                }
            });
        }
    }

    public String[] getPictureFormatAr() {
        return this.mPictureFormatAr;
    }

    public int getPictureFormatPos(int i) {
        return findPosByValue(this.mPictureFormatCmdIdAr, i, 0);
    }

    public int[] getPictureFormatCmdIdAr() {
        return this.mPictureFormatCmdIdAr;
    }

    public String[] getDigitalFilterAr() {
        return this.mDigitalFilterAr;
    }

    public String[] getDigitalFilterArAll() {
        return this.mDigitalFilterArAll;
    }

    public int getDigitalFilterPos(int i) {
        return findPosByValue(this.mDigitalFilterCmdIdArAll, i, 0);
    }

    public int[] getDigitalFilterCmdIdAr() {
        return this.mDigitalFilterCmdIdAr;
    }

    public String[] getShowGridAr() {
        return this.mShowGridAr;
    }

    public int[] getShowGridCmdIdAr() {
        return this.mShowGridCmdIdAr;
    }

    public int getShowGridPos(int i) {
        return i;
    }

    public static int findPosByValue(int[] iArr, int i, int i2) {
        int length = iArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (i == iArr[i3]) {
                return i3;
            }
        }
        return i2;
    }

    public String[] getVideoTitles(CameraType cameraType, int i) {
        if (cameraType == CameraType.DJICameraTypeFC350) {
            if (i == 0) {
                return new String[]{"4K", "4096x2160 25fps", "4096x2160 24fps", "3840x2160 25fps", "3840x2160 24fps", "2.7K", "2704x1520 25fps", "2704x1520 24fps", "1080P", "1920x1080 50fps", "1920x1080 48fps", "1920x1080 25fps", "1920x1080 24fps", dji.pilot.phonecamera.a.c.A, "1280x720  50fps", "1280x720  48fps", "1280x720  25fps", "1280x720  24fps", this.SLOWMOTIONStr, "1920x1080 SLO"};
            }
            return new String[]{"4K", "4096x2160 24fps", "3840x2160 30fps", "3840x2160 24fps", "2.7K", "2704x1520 30fps", "2704x1520 24fps", "1080P", "1920x1080 60fps", "1920x1080 48fps", "1920x1080 30fps", "1920x1080 24fps", dji.pilot.phonecamera.a.c.A, "1280x720  60fps", "1280x720  48fps", "1280x720  30fps", "1280x720  24fps", this.SLOWMOTIONStr, "1920x1080 SLO"};
        } else if (cameraType == CameraType.DJICameraTypeFC550) {
            if (i == 0) {
                return new String[]{"4K", "4096x2160 25fps", "4096x2160 24fps", "3840x2160 25fps", "3840x2160 24fps", "2.7K", "2704x1520 25fps", "2704x1520 24fps", "1080P", "1920x1080 50fps", "1920x1080 48fps", "1920x1080 25fps", "1920x1080 24fps"};
            }
            return new String[]{"4K", "4096x2160 24fps", "3840x2160 30fps", "3840x2160 24fps", "2.7K", "2704x1520 30fps", "2704x1520 24fps", "1080P", "1920x1080 60fps", "1920x1080 48fps", "1920x1080 30fps", "1920x1080 24fps"};
        } else if (cameraType == CameraType.DJICameraTypeFC550Raw) {
            if (i == 0) {
                return new String[]{"4K", "4096x2160 25fps", "4096x2160 24fps", "3840x2160 25fps", "3840x2160 24fps", "2.7K", "2704x1520 25fps", "2704x1520 24fps", "1080P", "1920x1080 50fps", "1920x1080 48fps", "1920x1080 25fps", "1920x1080 24fps"};
            }
            return new String[]{"4K", "4096x2160 24fps", "3840x2160 30fps", "3840x2160 24fps", "2.7K", "2704x1520 30fps", "2704x1520 24fps", "1080P", "1920x1080 60fps", "1920x1080 48fps", "1920x1080 30fps", "1920x1080 24fps"};
        } else if (cameraType != CameraType.DJICameraTypeCV600) {
            return null;
        } else {
            if (i == 0) {
                return new String[]{"4K", "4096x2160 25fps", "4096x2160 24fps", "3840x2160 25fps", "3840x2160 24fps", "2.7K", "2704x1520 25fps", "2704x1520 24fps", "1080P", "1920x1080 50fps", "1920x1080 48fps", "1920x1080 25fps", "1920x1080 24fps", this.SLOWMOTIONStr, "1920x1080 SLO"};
            }
            return new String[]{"4K", "4096x2160 24fps", "3840x2160 30fps", "3840x2160 24fps", "2.7K", "2704x1520 30fps", "2704x1520 24fps", "1080P", "1920x1080 60fps", "1920x1080 48fps", "1920x1080 30fps", "1920x1080 24fps", this.SLOWMOTIONStr, "1920x1080 SLO"};
        }
    }

    public int[] getVideoResolutionValues(CameraType cameraType, int i) {
        if (cameraType == CameraType.DJICameraTypeFC350) {
            if (i == 0) {
                return new int[]{-1, 22, 22, 16, 16, -1, 24, 24, -1, 10, 10, 10, 10, -1, 4, 4, 4, 4, -1, 10};
            }
            return new int[]{-1, 22, 16, 16, -1, 24, 24, -1, 10, 10, 10, 10, -1, 4, 4, 4, 4, -1, 10};
        } else if (cameraType == CameraType.DJICameraTypeFC550) {
            if (i == 0) {
                return new int[]{-1, 22, 22, 16, 16, -1, 24, 24, -1, 10, 10, 10, 10};
            }
            return new int[]{-1, 22, 16, 16, -1, 24, 24, -1, 10, 10, 10, 10};
        } else if (cameraType == CameraType.DJICameraTypeFC550Raw) {
            if (i == 0) {
                return new int[]{-1, 22, 22, 16, 16, -1, 24, 24, -1, 10, 10, 10, 10};
            }
            return new int[]{-1, 22, 16, 16, -1, 24, 24, -1, 10, 10, 10, 10};
        } else if (cameraType != CameraType.DJICameraTypeCV600) {
            return null;
        } else {
            if (i == 0) {
                return new int[]{-1, 22, 22, 16, 16, -1, 24, 24, -1, 10, 10, 10, 10, -1, 10};
            }
            return new int[]{-1, 22, 16, 16, -1, 24, 24, -1, 10, 10, 10, 10, -1, 10};
        }
    }

    public int[] getVideoFpsValues(CameraType cameraType, int i) {
        if (cameraType == CameraType.DJICameraTypeFC350) {
            if (i == 0) {
                return new int[]{-1, 2, 1, 2, 1, -1, 2, 1, -1, 5, 4, 2, 1, -1, 5, 4, 2, 1, -1, 7};
            }
            return new int[]{-1, 1, 3, 1, -1, 3, 1, -1, 6, 4, 3, 1, -1, 6, 4, 3, 1, -1, 7};
        } else if (cameraType == CameraType.DJICameraTypeFC550) {
            if (i == 0) {
                return new int[]{-1, 2, 1, 2, 1, -1, 2, 1, -1, 5, 4, 2, 1};
            }
            return new int[]{-1, 1, 3, 1, -1, 3, 1, -1, 6, 4, 3, 1};
        } else if (cameraType == CameraType.DJICameraTypeFC550Raw) {
            if (i == 0) {
                return new int[]{-1, 2, 1, 2, 1, -1, 2, 1, -1, 5, 4, 2, 1};
            }
            return new int[]{-1, 1, 3, 1, -1, 3, 1, -1, 6, 4, 3, 1};
        } else if (cameraType != CameraType.DJICameraTypeCV600) {
            return null;
        } else {
            if (i == 0) {
                return new int[]{-1, 2, 1, 2, 1, -1, 2, 1, -1, 5, 4, 2, 1, -1, 7};
            }
            return new int[]{-1, 1, 3, 1, -1, 3, 1, -1, 6, 4, 3, 1, -1, 7};
        }
    }

    public int[] getCutsomWBValues() {
        return this.mCustomWBValueArray;
    }

    public int getCustomWBPos(int i) {
        int length = this.mCustomWBValueArray.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i == this.mCustomWBValueArray[i2]) {
                return i2;
            }
        }
        return 0;
    }

    public String[] getCutsomNameValues() {
        return this.mCustomWBNameArray;
    }

    public int getVideoFpsImgRes(int i, int i2) {
        int videoResolutionType = getVideoResolutionType(i);
        return mVideoFpsImgResIds[videoResolutionType][findPosByValue(mVideoFpsCmdIds, i2, 0)];
    }

    public int getVideoResolutionType(int i) {
        if (i == 24) {
            return 2;
        }
        if (i == 16) {
            return 1;
        }
        if (i == 10) {
            return 3;
        }
        if (i != 4) {
            return 0;
        }
        return 4;
    }

    public boolean isCur4kVideo() {
        int videoFormat = DataCameraGetPushShotParams.getInstance().getVideoFormat();
        if (((videoFormat >= 14 && videoFormat <= 23) || videoFormat == 28 || videoFormat == 29) && DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD) {
            return true;
        }
        return false;
    }

    public void prepareDemarcate() {
        DJILogHelper.getInstance().LOGD("", "****had[" + this.mHadDemarcate + "," + this.mNeedDemarcate + dji.pilot.usercenter.protocol.d.H, false, true);
        if (!this.mHadDemarcate) {
            this.mHadDemarcate = true;
            prepareRecMode();
        }
    }

    private void prepareRecMode() {
        if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD) {
            prepareToAMode();
        } else {
            DataCameraSetMode.getInstance().a(MODE.RECORD).start(new d(this) {
                final /* synthetic */ DJICameraDataManagerCompat a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.mHandler.obtainMessage(8192, 1, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.mHandler.obtainMessage(8192, 0, 0).sendToTarget();
                }
            });
        }
    }

    private void prepareToAMode() {
        if (DataCameraGetPushShotParams.getInstance().getExposureMode() == ExposureMode.d) {
            prepareTo17Aperture();
        } else {
            new DataCameraSetExposureMode().a(ExposureMode.d.a()).start(new d(this) {
                final /* synthetic */ DJICameraDataManagerCompat a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.mHandler.sendMessageDelayed(this.a.mHandler.obtainMessage(8193, 1, 0), 300);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.mHandler.obtainMessage(8193, 0, 0).sendToTarget();
                }
            });
        }
    }

    private void prepareTo17Aperture() {
        if (170 == DataCameraGetPushShotParams.getInstance().getRealApertureSize()) {
            prepareToIsoAuto();
        } else {
            new DataCameraSetAperture().a((short) 170).start(new d(this) {
                final /* synthetic */ DJICameraDataManagerCompat a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.mHandler.obtainMessage(8194, 1, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.mHandler.obtainMessage(8194, 0, 0).sendToTarget();
                }
            });
        }
    }

    private void prepareToIsoAuto() {
        int iso = DataCameraGetPushShotParams.getInstance().getISO();
        if (iso != 0 && iso != 1) {
            new DataCameraSetIso().a(true).a(TYPE.AUTO).start(new d(this) {
                final /* synthetic */ DJICameraDataManagerCompat a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.mHandler.obtainMessage(DJICameraDataManagerCompat.MSG_ID_DEMARCATE_ISOAUTO, 1, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.mHandler.obtainMessage(DJICameraDataManagerCompat.MSG_ID_DEMARCATE_ISOAUTO, 0, 0).sendToTarget();
                }
            });
        }
    }

    public void handleAFToMF() {
        if (needDemarcate()) {
            this.mNeedPostDemarcateTip = true;
            dji.thirdparty.a.c.a().e(a.MF_DEMARCATE);
        }
    }

    public void cancelDemarcateCamera() {
        DataCameraSetFocusWindow.getInstance().a(1).b(1).c(0).start(null);
        this.mNeedDemarcate = false;
        this.mNeedPostDemarcateTip = false;
        this.mDemarcateValue = 0;
        new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.A).a(FuselageFocusMode.Manual.value()).start(null);
    }

    public void demarcateCamera() {
        DataCameraSetFocusWindow.getInstance().a(1).b(1).c(0).start(null);
        String format = String.format(Locale.US, "%d%d%d", new Object[]{Integer.valueOf(DataCameraGetShotInfo.getInstance().getMemberId()), Integer.valueOf(DataCameraGetShotInfo.getInstance().getModelId()), Integer.valueOf(DataCameraGetShotInfo.getInstance().getHardVersion())});
        this.mNeedDemarcate = false;
        this.mNeedPostDemarcateTip = false;
        this.mDemarcateValue = DataCameraGetPushShotInfo.getInstance().getShotFocusCurStroke();
        dji.pilot.set.a.a(this.mContext, format, false);
        dji.pilot.set.a.a(this.mContext, format + KEY_DEMARCATE_VALUE, this.mDemarcateValue);
        new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.A).a(FuselageFocusMode.Manual.value()).start(null);
    }

    public void redemarcateCamera() {
        if (dji.logic.f.b.a(null)) {
            String format = String.format(Locale.US, "%d%d%d", new Object[]{Integer.valueOf(DataCameraGetShotInfo.getInstance().getMemberId()), Integer.valueOf(DataCameraGetShotInfo.getInstance().getModelId()), Integer.valueOf(DataCameraGetShotInfo.getInstance().getHardVersion())});
            this.mNeedDemarcate = true;
            this.mHadDemarcate = false;
            this.mNeedPostDemarcateTip = true;
            this.mDemarcateValue = 0;
            dji.pilot.set.a.a(this.mContext, format, true);
            dji.pilot.set.a.a(this.mContext, format + KEY_DEMARCATE_VALUE, 0);
            new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.A).a(FuselageFocusMode.OneAuto.value()).start(null);
            dji.thirdparty.a.c.a().e(a.MF_DEMARCATE);
        }
    }

    public boolean needPostDemarcateTip() {
        return this.mNeedPostDemarcateTip;
    }

    public boolean needDemarcate() {
        return this.mNeedDemarcate && dji.logic.f.b.a(null);
    }

    public int getDemarcateValue() {
        if (needDemarcate()) {
            return 0;
        }
        return this.mDemarcateValue;
    }

    public int getPortraitModeCmdId() {
        return this.mDigitalFilterCmdIdArAll[10];
    }

    public void onEventMainThread(dji.device.camera.view.focus.DJIMFDemarcateViewLongan.a aVar) {
        if (aVar == dji.device.camera.view.focus.DJIMFDemarcateViewLongan.a.SHOW && needDemarcate()) {
            prepareDemarcate();
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        FuselageFocusMode fuselageFocusMode = DataCameraGetPushShotInfo.getInstance().getFuselageFocusMode();
        if (!needDemarcate()) {
            return;
        }
        if (fuselageFocusMode == FuselageFocusMode.Manual || fuselageFocusMode == FuselageFocusMode.ManualFine) {
            this.mNeedPostDemarcateTip = true;
            dji.thirdparty.a.c.a().e(a.MF_DEMARCATE);
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.b) {
            this.mNeedDemarcate = false;
        } else if (oVar == o.a) {
            this.mCameraType = CameraType.OTHER;
            this.mNeedDemarcate = false;
            this.mHadDemarcate = true;
            this.mNeedPostDemarcateTip = false;
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (this.mCameraType != cameraType) {
            this.mCameraType = cameraType;
            if (dji.logic.f.b.n(cameraType)) {
                getCameraInfo(0);
            }
        }
    }

    public int getCenterPoint() {
        return this.mCenterPoints;
    }

    public void updateCenterPoint(int i) {
        if (i != this.mCenterPoints) {
            this.mCenterPoints = i;
            i.a(this.mContext, "key_camera_centerpoints", i);
            dji.thirdparty.a.c.a().e(a.CENTER_POINT_CHANGED);
        }
    }

    public b getCPColor() {
        return this.mCenterPointColor;
    }

    public void updateCPColor(b bVar) {
        if (bVar != this.mCenterPointColor) {
            this.mCenterPointColor = bVar;
            i.a(this.mContext, "key_camera_cp_color", bVar.b());
            dji.thirdparty.a.c.a().e(a.CENTER_POINT_COLOR_CHANGED);
        }
    }

    public int getLineRef() {
        return this.mLineRef;
    }

    public void updateLineRef(int i) {
        if (i != this.mLineRef) {
            this.mLineRef = i;
            i.a(this.mContext, "key_camera_lineref", i);
            dji.thirdparty.a.c.a().e(a.LINEREF_CHANGED);
        }
    }

    public b getLineRefColor() {
        return this.mLineRefColor;
    }

    public void updateLineRefColor(b bVar) {
        if (bVar != this.mLineRefColor) {
            this.mLineRefColor = bVar;
            i.a(this.mContext, "key_camera_lineref_color", bVar.b());
            dji.thirdparty.a.c.a().e(a.LINEREF_COLOR_CHANGED);
        }
    }

    public float getLineRefCW() {
        return l[4][0];
    }

    public void updateLineRefCW(float f) {
        if (Math.abs(f - l[4][0]) >= 0.005f) {
            l[4][0] = f;
            i.a(this.mContext, "key_camera_lineref_cwidth", f);
            dji.thirdparty.a.c.a().e(a.LINEREF_CUSTOMATTR_CHANGED);
        }
    }

    public float getLineRefCH() {
        return l[4][1];
    }

    public void updateLineRefCH(float f) {
        if (Math.abs(f - l[4][1]) >= 0.005f) {
            l[4][1] = f;
            i.a(this.mContext, "key_camera_lineref_cheight", f);
            dji.thirdparty.a.c.a().e(a.LINEREF_CUSTOMATTR_CHANGED);
        }
    }
}
