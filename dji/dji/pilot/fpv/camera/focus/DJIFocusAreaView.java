package dji.pilot.fpv.camera.focus;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.ShotFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetFocusArea;
import dji.midware.util.i;
import dji.pilot.R;
import dji.pilot.fpv.camera.a.a;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.publics.layout.DJIDragLayout;
import dji.setting.ui.general.FpvLongPressSelectView;
import dji.thirdparty.a.c;

public class DJIFocusAreaView extends DJIDragLayout {
    private static final int u = 2500;
    public boolean a = false;
    private DJIRelativeLayout b = null;
    private DJIImageView c = null;
    private DJIImageView d = null;
    private DJIImageView e = null;
    private DJITextView f = null;
    private FuselageFocusMode g = FuselageFocusMode.OTHER;
    private CameraType h = CameraType.OTHER;
    private int i = 0;
    private float j = 0.0f;
    private float q = 0.0f;
    private int[] r = null;
    private int[] s = null;
    private Animation t = null;
    private Handler v = new Handler(Looper.getMainLooper());

    public DJIFocusAreaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setPosition(float f, float f2) {
        a(f, f2);
    }

    public void showView() {
        DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
        boolean z = instance.isGetted() && b.c(instance.getCameraType());
        if (getVisibility() != 0 && z) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            a(DataCameraGetPushStateInfo.getInstance(), false);
            onEventMainThread(DataCameraGetPushShotInfo.getInstance());
            setVisibility(0);
        }
    }

    public void hideView() {
        if (getVisibility() != 8) {
            this.g = FuselageFocusMode.OTHER;
            this.h = CameraType.OTHER;
            this.i = 0;
            this.d.setBackgroundResource(R.drawable.camera_focus_auto_cross);
            this.c.setBackgroundResource(R.drawable.camera_focus_auto_bg);
            if (c.a().c(this)) {
                c.a().d(this);
            }
            setVisibility(8);
        }
    }

    public void setHVLimits(int[] iArr, int[] iArr2) {
        this.r = iArr;
        this.s = iArr2;
    }

    public void handleMotion(MotionEvent motionEvent) {
        DataCameraGetPushShotInfo instance = DataCameraGetPushShotInfo.getInstance();
        if (instance.getFocusStatus() != 1 && instance.getMFFocusStatus() != 1) {
            if (this.g == FuselageFocusMode.OneAuto || this.g == FuselageFocusMode.ContinuousAuto) {
                if (instance.getShotFocusMode() == ShotFocusMode.Manual) {
                    DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                    bVar.b = R.string.fpv_shottype_mf_tip;
                    bVar.a = d.b;
                    bVar.f = DJIErrorPopView.c.a;
                    c.a().e(bVar);
                    return;
                }
                this.f.setTextColor(getResources().getColor(R.color.b2));
                this.d.setBackgroundResource(R.drawable.camera_focus_auto_cross);
                this.c.setBackgroundResource(R.drawable.camera_focus_auto_bg);
            }
            a(motionEvent);
            this.a = true;
            DataCameraSetFocusArea.getInstance().a(a(motionEvent.getX(), this.r)).b(a(motionEvent.getY(), this.s)).start(null);
        }
    }

    public void handleMotion4LongPress(MotionEvent motionEvent) {
        handleMotion(motionEvent);
        if (getVisibility() != 0) {
            showView();
            this.v.postDelayed(new Runnable(this) {
                final /* synthetic */ DJIFocusAreaView a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.hideView();
                }
            }, 2500);
        }
    }

    public static boolean canLongPressFocus(Context context) {
        CameraType cameraType = CameraType.OTHER;
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        return i.b(context, FpvLongPressSelectView.a, 1) == 1 && b.c(cameraType);
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        a(dataCameraGetPushStateInfo, true);
    }

    private void a(DataCameraGetPushStateInfo dataCameraGetPushStateInfo, boolean z) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (this.h != cameraType) {
            this.h = cameraType;
            if (!b.c(cameraType)) {
                hideView();
            } else if (z) {
                onEventMainThread(DataCameraGetPushShotInfo.getInstance());
            }
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        float f = 2.0f;
        FuselageFocusMode fuselageFocusMode = dataCameraGetPushShotInfo.getFuselageFocusMode();
        if (fuselageFocusMode != this.g) {
            this.g = fuselageFocusMode;
            MarginLayoutParams marginLayoutParams;
            if (fuselageFocusMode == FuselageFocusMode.Manual || fuselageFocusMode == FuselageFocusMode.ManualFine) {
                this.e.show();
                this.b.go();
                this.f.setText(R.string.camera_area_manual_desc);
                marginLayoutParams = (MarginLayoutParams) this.f.getLayoutParams();
                marginLayoutParams.topMargin = 0;
                this.f.setLayoutParams(marginLayoutParams);
                this.f.setTextColor(getResources().getColor(R.color.om));
            } else {
                this.b.show();
                this.e.go();
                this.f.setText(R.string.camera_area_auto_desc);
                marginLayoutParams = (MarginLayoutParams) this.f.getLayoutParams();
                marginLayoutParams.topMargin = 0 - getResources().getDimensionPixelSize(R.dimen.rp);
                this.f.setLayoutParams(marginLayoutParams);
                this.f.setTextColor(getResources().getColor(R.color.b2));
            }
        }
        int i = Integer.MAX_VALUE;
        if (fuselageFocusMode == FuselageFocusMode.OneAuto || fuselageFocusMode == FuselageFocusMode.ContinuousAuto) {
            i = dataCameraGetPushShotInfo.getFocusStatus();
            a.a(null, "focus status-" + this.i + com.alipay.sdk.j.i.b + i);
            if (this.i != i) {
                this.i = i;
                if (i == 1) {
                    this.c.startAnimation(getAutoFocuaAnimation());
                } else {
                    this.c.clearAnimation();
                    if (i != 2 && i == 3) {
                        this.c.setBackgroundResource(R.drawable.camera_focus_fail_bg);
                        this.d.setBackgroundResource(R.drawable.camera_focus_fail_cross);
                        this.f.setTextColor(getResources().getColor(R.color.gj));
                    }
                }
            }
        }
        float spotAFAxisX = dataCameraGetPushShotInfo.getSpotAFAxisX();
        float spotAFAxisY = dataCameraGetPushShotInfo.getSpotAFAxisY();
        if (i == 0 || (spotAFAxisX <= 0.01f && spotAFAxisY <= 0.01f)) {
            spotAFAxisY = 2.0f;
        } else {
            f = spotAFAxisX;
        }
        a.a(null, "focus status-" + i + com.alipay.sdk.j.i.b + f + com.alipay.sdk.j.i.b + spotAFAxisY);
        if (this.j != f || this.q != spotAFAxisY) {
            this.j = f;
            this.q = spotAFAxisY;
            a((float) b(f, this.r), (float) b(spotAFAxisY, this.s));
        }
    }

    private Animation getAutoFocuaAnimation() {
        if (this.t == null) {
            this.t = AnimationUtils.loadAnimation(getContext(), R.anim.h);
        }
        return this.t;
    }

    private float a(float f, int[] iArr) {
        if (iArr == null || iArr.length < 2) {
            return 0.0f;
        }
        return (f - ((float) iArr[0])) / ((float) (iArr[1] - iArr[0]));
    }

    private int b(float f, int[] iArr) {
        if (iArr == null || iArr.length < 2) {
            return 0;
        }
        return (int) (((float) iArr[0]) + (((float) (iArr[1] - iArr[0])) * f));
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.r = new int[]{0, DJIBaseActivity.screenWidth};
            this.s = new int[]{0, DJIBaseActivity.screenHeight};
            this.b = (DJIRelativeLayout) findViewById(R.id.ir);
            this.c = (DJIImageView) findViewById(R.id.is);
            this.d = (DJIImageView) findViewById(R.id.it);
            this.e = (DJIImageView) findViewById(R.id.iu);
            this.f = (DJITextView) findViewById(R.id.iv);
        }
    }
}
