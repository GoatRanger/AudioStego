package dji.device.camera.view.record;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.device.camera.a.a;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.b;
import dji.device.common.view.LonganBLN;
import dji.device.timelapse.LonganTimelapseSetLayout;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetAudio;
import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushTimelapseParms;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataOSDGetMicControl;
import dji.midware.data.model.P3.DataOsdGetPushMicInfo;
import dji.midware.data.model.P3.DataOsdGetPushMicInfo.MIC_TYPE;
import dji.midware.e.d;
import dji.midware.media.DJIVideoDecoder;
import dji.pilot.fpv.R;
import dji.pilot.set.g;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJIBottomBarLongan extends DJIRelativeLayout {
    private static final String s = "DJIBottomBarLongan";
    private static int[] t = new int[]{R.drawable.longan_record_volume0, R.drawable.longan_record_volume1, R.drawable.longan_record_volume2, R.drawable.longan_record_volume3, R.drawable.longan_record_volume4, R.drawable.longan_record_volume5, R.drawable.longan_record_volume6, R.drawable.longan_record_volume7, R.drawable.longan_record_volume8, R.drawable.longan_record_volume9};
    private static final int u = 1;
    private static final int v = 2;
    private static final int w = 3;
    private static final int x = 4;
    int a;
    int b;
    Animation c;
    boolean d;
    DJITextView e;
    DJITextView f;
    DJITextView g;
    DJITextView h;
    ImageView i;
    DJILinearLayout j;
    DJILinearLayout k;
    DataOSDGetMicControl l = null;
    ImageView m;
    LonganBLN n = null;
    SDCardState o = SDCardState.OTHER;
    boolean p = false;
    boolean q = true;
    float r = 5.87f;
    private RatioType y = RatioType.R_16_9;
    private Handler z = new Handler(new Callback(this) {
        final /* synthetic */ DJIBottomBarLongan a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            boolean z = true;
            switch (message.what) {
                case 2:
                    DJIBottomBarLongan dJIBottomBarLongan = this.a;
                    if (message.arg1 != 1) {
                        z = false;
                    }
                    dJIBottomBarLongan.show(z);
                    break;
                case 3:
                    this.a.e();
                    break;
                case 4:
                    if (!this.a.a(DataOsdGetPushMicInfo.getInstance())) {
                        this.a.m.setImageResource(R.drawable.longan_record_recorder_no_voice);
                        break;
                    }
                    this.a.m.setImageResource(R.drawable.longan_record_recorder);
                    break;
            }
            return false;
        }
    });

    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[o.values().length];

        static {
            b = new int[m.values().length];
            try {
                b[m.HIDE_ALL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[m.SHOW_ALL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[m.HIDE_INFO_BAR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[m.SHOW_INFO_BAR.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                b[m.SHOW_SHOTCUTS_CAMERA_LY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                b[m.HIDE_SHOTCUTS_CAMERA_LY.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[o.a.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public DJIBottomBarLongan(Context context) {
        super(context);
    }

    public DJIBottomBarLongan(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJIBottomBarLongan(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        resetView();
    }

    public void resetView() {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        if (getResources().getConfiguration().orientation == 2) {
            getScreenSize();
            layoutParams.addRule(12);
            layoutParams.bottomMargin = 20;
            if (getVisibility() == 0) {
                startAnimation(this.c);
            }
            if (this.d) {
                setTranslationX(DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngularVelocity);
                return;
            }
            return;
        }
        getScreenSize();
        layoutParams.addRule(12, 0);
        layoutParams.topMargin = ((this.b + getVideoViewHeight()) / 2) + 20;
        if (getVisibility() == 0) {
            startAnimation(this.c);
        }
        if (this.d) {
            setTranslationX(0.0f);
        }
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

    private void a() {
        c.a().a(this);
        this.e = (DJITextView) findViewById(R.id.longan_bottombar_time_tv);
        this.f = (DJITextView) findViewById(R.id.longan_bottombar_generate_time_tv);
        this.g = (DJITextView) findViewById(R.id.longan_bottombar_remain_time_tv);
        this.c = AnimationUtils.loadAnimation(getContext(), R.anim.longan_fade_in);
        this.k = (DJILinearLayout) findViewById(R.id.longan_bottombar_record_info_ly);
        this.m = (ImageView) findViewById(R.id.longan_record_recorder_iv);
        this.j = (DJILinearLayout) findViewById(R.id.longan_bottom_timelapse_ly);
        this.h = (DJITextView) findViewById(R.id.longan_record_ext);
        this.i = (ImageView) findViewById(R.id.longan_record_volume_iv);
        this.n = (LonganBLN) findViewById(R.id.longan_sd_bln);
        resetView();
        onEventMainThread(a.getInstance());
        onEventBackgroundThread(DataCameraGetPushStateInfo.getInstance());
        getVideoVoiceEnable();
        if (b.getInstance().c(4) >= b.k) {
            b();
        }
    }

    private void b() {
        this.l = new DataOSDGetMicControl();
        this.l.start(new d(this) {
            final /* synthetic */ DJIBottomBarLongan a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                boolean z = true;
                DJIBottomBarLongan dJIBottomBarLongan = this.a;
                if (this.a.l.getInnerEnable() != 1) {
                    z = false;
                }
                dJIBottomBarLongan.q = z;
                this.a.z.obtainMessage(4).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.q = true;
                this.a.z.obtainMessage(4).sendToTarget();
            }
        });
    }

    private void c() {
        this.k.setVisibility(8);
        this.j.setVisibility(0);
        this.e.setVisibility(8);
    }

    private void d() {
        this.k.setVisibility(0);
        this.j.setVisibility(8);
        this.e.setVisibility(0);
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (!dataCameraGetPushStateInfo.getIsStoring()) {
            this.z.sendEmptyMessage(1);
        }
        this.z.sendEmptyMessage(3);
    }

    public void onEventBackgroundThread(o oVar) {
        switch (AnonymousClass4.a[oVar.ordinal()]) {
            case 1:
                this.z.sendEmptyMessage(1);
                return;
            default:
                return;
        }
    }

    private int getVideoViewHeight() {
        int i = DJIVideoDecoder.width;
        int i2 = DJIVideoDecoder.height;
        float radio = getRadio();
        if (Math.abs(radio - dji.midware.util.a.b.a) < Math.abs(radio - dji.midware.util.a.b.b)) {
            this.y = RatioType.R_16_9;
        } else {
            this.y = RatioType.R_4_3;
        }
        if (this.y == RatioType.R_16_9) {
            return (int) (((float) this.a) / dji.midware.util.a.b.a);
        }
        return (int) (((((float) i2) * 1.0f) / ((float) i)) * ((float) this.a));
    }

    @SuppressLint({"NewApi"})
    private float getRadio() {
        int i;
        int i2;
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        int i3;
        if (VERSION.SDK_INT < 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
            if (i >= i2) {
                i3 = i2;
                i2 = i;
                i = i3;
            }
        } else {
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            i3 = point.x > point.y ? point.y : point.x;
            i2 = point.x > point.y ? point.x : point.y;
            i = i3;
        }
        return (((float) i2) * 1.0f) / ((float) i);
    }

    private void e() {
        DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
        this.e.setText(dji.device.common.a.a.a(instance.getVideoRecordTime(), getContext()) + " | " + dji.device.common.a.a.a(instance.getRemainedTime(), getContext()));
    }

    public void onEventMainThread(a aVar) {
        if (aVar.d() == a.a.RECORD && !this.e.isShown()) {
            d();
            this.z.sendMessage(this.z.obtainMessage(2, 1, 0));
        } else if (aVar.d() == a.a.TAKEPHOTO && this.e.isShown()) {
            this.z.sendMessage(this.z.obtainMessage(2, 0, 0));
        }
    }

    public void onEventMainThread(dji.device.camera.a.b.c cVar) {
        if (cVar == dji.device.camera.a.b.c.NOT_TIMING) {
            this.z.sendMessage(this.z.obtainMessage(2, 0, 0));
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (dji.device.camera.a.b.getInstance().d() == dji.device.camera.a.b.c.TIMING && dji.device.camera.a.c.getInstance().c() == dji.device.camera.a.c.b.TIMELAPSE) {
            int timelapseDuration;
            int timelapseRecordedFrame = dataCameraGetPushShotParams.getTimelapseRecordedFrame();
            if (dji.device.timelapse.b.getInstance().c() == dji.device.timelapse.b.b.STATIONARY) {
                timelapseDuration = dataCameraGetPushShotParams.getTimelapseDuration();
            } else {
                timelapseDuration = DataCameraGetPushTimelapseParms.getInstance().getDuration(1) - 1;
            }
            long videoRecordIntervalTime = (long) (timelapseDuration - ((timelapseRecordedFrame * DataCameraGetPushShotParams.getInstance().getVideoRecordIntervalTime()) / 10));
            if (videoRecordIntervalTime < 0) {
                videoRecordIntervalTime = -videoRecordIntervalTime;
            }
            this.g.setText(LonganTimelapseSetLayout.convertSecondToTime(videoRecordIntervalTime));
            if (!this.j.isShown()) {
                c();
                this.z.sendMessage(this.z.obtainMessage(2, 1, 0));
            }
            this.f.setText(dji.device.timelapse.b.h);
        }
    }

    public void show(boolean z) {
        if (z) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }

    public void onEventMainThread(m mVar) {
        if (a.getInstance().d() != a.a.TAKEPHOTO) {
            switch (mVar) {
                case HIDE_ALL:
                    this.d = false;
                    if (getResources().getConfiguration().orientation == 2) {
                        setTranslationX(0.0f);
                        return;
                    }
                    return;
                case SHOW_ALL:
                    show();
                    return;
                case HIDE_INFO_BAR:
                case SHOW_INFO_BAR:
                    return;
                case SHOW_SHOTCUTS_CAMERA_LY:
                    this.d = true;
                    if (getResources().getConfiguration().orientation == 2) {
                        setTranslationX(DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngularVelocity);
                        return;
                    }
                    return;
                case HIDE_SHOTCUTS_CAMERA_LY:
                    this.d = false;
                    if (getResources().getConfiguration().orientation == 2) {
                        setTranslationX(0.0f);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar != o.a && oVar == o.b && a.getInstance().d() == a.a.RECORD) {
            show();
        }
    }

    public void onEventMainThread(DataOsdGetPushMicInfo dataOsdGetPushMicInfo) {
        if (isShown() && a.getInstance().d() == a.a.RECORD) {
            if (dataOsdGetPushMicInfo.getMicType() == MIC_TYPE.IN) {
                this.h.setVisibility(8);
            } else {
                this.h.setVisibility(0);
            }
            float micVolume = (float) dataOsdGetPushMicInfo.getMicVolume();
            if (!a(dataOsdGetPushMicInfo)) {
                this.m.setImageResource(R.drawable.longan_record_recorder_no_voice);
                this.i.setImageResource(t[0]);
            } else if (micVolume == 0.0f) {
                this.i.setImageResource(t[0]);
            } else {
                int i = (int) (micVolume / this.r);
                if (i < 8) {
                    this.i.setImageResource(t[i + 1]);
                } else {
                    this.i.setImageResource(t[9]);
                }
                this.m.setImageResource(R.drawable.longan_record_recorder);
            }
        }
    }

    private boolean a(DataOsdGetPushMicInfo dataOsdGetPushMicInfo) {
        if (dataOsdGetPushMicInfo.getMicType() == MIC_TYPE.IN) {
            return this.q && this.p;
        } else {
            return this.p;
        }
    }

    public void onEventMainThread(dji.pilot.set.a.a aVar) {
        if (g.j == aVar.a) {
            getVideoVoiceEnable();
        } else if (g.m == aVar.a) {
            b();
        }
    }

    private void getVideoVoiceEnable() {
        final DataCameraGetAudio dataCameraGetAudio = new DataCameraGetAudio();
        dataCameraGetAudio.start(new d(this) {
            final /* synthetic */ DJIBottomBarLongan b;

            public void onSuccess(Object obj) {
                this.b.p = dataCameraGetAudio.isEnable();
                this.b.z.obtainMessage(4).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    public void onEventMainThread(dji.device.camera.a.d.a aVar) {
        if (aVar == dji.device.camera.a.d.a.RECORDING) {
            this.n.startAnim();
        } else {
            this.n.stopAnim();
        }
    }
}
