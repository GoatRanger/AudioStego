package dji.device.camera.view.focus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import dji.device.activity.DJIPreviewActivityLongan;
import dji.device.camera.view.focus.LonganAfInfiniteSwitcher.a;
import dji.device.widget.LonganPopWarnView;
import dji.logic.f.b;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo$ShotFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetFocusArea;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.publics.layout.DJIDragLayout;
import dji.thirdparty.a.c;

public class DJIFocusAreaViewCompat extends DJIDragLayout {
    private DJIRelativeLayout a = null;
    private DJIImageView b = null;
    private DJIImageView c = null;
    private DJIImageView d = null;
    private DJITextView e = null;
    private FuselageFocusMode f = FuselageFocusMode.OTHER;
    private CameraType g = CameraType.OTHER;
    private int h = -1;
    private float i = 0.0f;
    private float j = 0.0f;
    private int[] q = null;
    private int[] r = null;
    private float s = -1.0f;
    private float t = -1.0f;
    private Animation u = null;

    public DJIFocusAreaViewCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void showView() {
        if (getVisibility() != 0) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            a(DataCameraGetPushStateInfo.getInstance(), false);
            onEventMainThread(DataCameraGetPushShotInfo.getInstance());
            if (LonganAfInfiniteSwitcher.a == a.AF) {
                setVisibility(0);
            }
        }
    }

    public void reFocus() {
        if (this.s != -1.0f) {
            b(this.s, this.t);
        } else {
            b((float) (DJIPreviewActivityLongan.mScreenWidth / 2), (float) (DJIPreviewActivityLongan.mScreenHeight / 2));
        }
    }

    public void hideView() {
        if (getVisibility() != 8) {
            this.f = FuselageFocusMode.OTHER;
            this.g = CameraType.OTHER;
            if (c.a().c(this)) {
                c.a().d(this);
            }
            setVisibility(8);
        }
    }

    public void setPosition(float f, float f2) {
        a(f, f2);
    }

    public void setHVLimits(int[] iArr, int[] iArr2) {
        this.q = iArr;
        this.r = iArr2;
        a((float) b(this.i, this.q), (float) b(this.j, this.r));
    }

    public void handleMotion(MotionEvent motionEvent) {
        if (DataCameraGetPushShotInfo.getInstance().getFocusStatus() != 1) {
            if ((this.f == FuselageFocusMode.OneAuto || this.f == FuselageFocusMode.ContinuousAuto) && DataCameraGetPushShotInfo.getInstance().getShotFocusMode() == DataCameraGetPushShotInfo$ShotFocusMode.Manual) {
                LonganPopWarnView.getInstance(getContext()).pop(R.drawable.longan_notice, R.string.fpv_shottype_mf_tip, LonganPopWarnView.a.LENGTH_SHORT);
                return;
            }
            a(motionEvent);
            DataCameraSetFocusArea.getInstance().a(a(motionEvent.getX(), this.q)).b(a(motionEvent.getY(), this.r)).start(null);
            this.s = motionEvent.getX();
            this.t = motionEvent.getY();
        }
    }

    private void b(float f, float f2) {
        a(f, f2);
        DataCameraSetFocusArea.getInstance().a(a(f, this.q)).b(a(f2, this.r)).start(null);
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        a(dataCameraGetPushStateInfo, true);
    }

    private void a(DataCameraGetPushStateInfo dataCameraGetPushStateInfo, boolean z) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (this.g != cameraType) {
            this.g = cameraType;
            if (!b.n(cameraType)) {
                hideView();
            } else if (z) {
                onEventMainThread(DataCameraGetPushShotInfo.getInstance());
            }
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        FuselageFocusMode fuselageFocusMode = dataCameraGetPushShotInfo.getFuselageFocusMode();
        if (fuselageFocusMode != this.f) {
            this.f = fuselageFocusMode;
            MarginLayoutParams marginLayoutParams;
            if (fuselageFocusMode == FuselageFocusMode.Manual || fuselageFocusMode == FuselageFocusMode.ManualFine) {
                this.d.show();
                this.a.go();
                this.e.setText(R.string.camera_area_manual_desc);
                marginLayoutParams = (MarginLayoutParams) this.e.getLayoutParams();
                marginLayoutParams.topMargin = 0;
                this.e.setLayoutParams(marginLayoutParams);
                this.e.setTextColor(getResources().getColor(R.color.white));
            } else {
                this.a.show();
                this.d.go();
                this.e.setText(R.string.camera_area_auto_desc);
                marginLayoutParams = (MarginLayoutParams) this.e.getLayoutParams();
                marginLayoutParams.topMargin = 0 - getResources().getDimensionPixelSize(R.dimen.txt_ten);
                this.e.setLayoutParams(marginLayoutParams);
                this.e.setTextColor(getResources().getColor(R.color.camera_area_auto));
            }
        }
        if (fuselageFocusMode == FuselageFocusMode.OneAuto || fuselageFocusMode == FuselageFocusMode.ContinuousAuto) {
            int focusStatus = dataCameraGetPushShotInfo.getFocusStatus();
            if (this.h != focusStatus) {
                this.h = focusStatus;
                if (focusStatus == 1) {
                    this.b.startAnimation(getAutoFocuaAnimation());
                } else if (focusStatus == 2) {
                    this.b.clearAnimation();
                } else {
                    this.b.clearAnimation();
                }
            }
        } else {
            this.h = 0;
        }
        float spotAFAxisX = dataCameraGetPushShotInfo.getSpotAFAxisX();
        float spotAFAxisY = dataCameraGetPushShotInfo.getSpotAFAxisY();
        if (this.i != spotAFAxisX || this.j != spotAFAxisY) {
            this.i = spotAFAxisX;
            this.j = spotAFAxisY;
            a((float) b(spotAFAxisX, this.q), (float) b(spotAFAxisY, this.r));
        }
    }

    private Animation getAutoFocuaAnimation() {
        if (this.u == null) {
            this.u = AnimationUtils.loadAnimation(getContext(), R.anim.camera_autofocus);
        }
        return this.u;
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
            this.q = new int[]{0, DJIPreviewActivityLongan.mScreenWidth};
            this.r = new int[]{0, DJIPreviewActivityLongan.mScreenHeight};
            this.a = (DJIRelativeLayout) findViewById(R.id.camera_focus_area_auto_ly);
            this.b = (DJIImageView) findViewById(R.id.camera_focus_area_auto_bg);
            this.c = (DJIImageView) findViewById(R.id.camera_focus_area_auto_cross);
            this.d = (DJIImageView) findViewById(R.id.camera_focus_area_manual_img);
            this.e = (DJITextView) findViewById(R.id.camera_focus_area_tv);
        }
    }

    public void onEventMainThread(a aVar) {
        if (aVar == a.AF) {
            showView();
        } else {
            hide();
        }
    }
}
