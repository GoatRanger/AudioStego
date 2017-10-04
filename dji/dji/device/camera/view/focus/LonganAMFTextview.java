package dji.device.camera.view.focus;

import android.content.Context;
import android.content.res.Configuration;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import dji.device.activity.DJIPreviewActivityLongan;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.logic.f.b;
import dji.midware.data.config.P3.b.a;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class LonganAMFTextview extends DJITextView implements OnClickListener {
    private static final String e = "LonganAMFTextview";
    protected FuselageFocusMode a;
    protected CameraType b;
    protected DataCameraGetPushShotInfo c;
    protected String d;

    public LonganAMFTextview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = FuselageFocusMode.OTHER;
        this.b = CameraType.OTHER;
        this.c = null;
        this.d = null;
        this.c = DataCameraGetPushShotInfo.getInstance();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.d = getContext().getString(R.string.longan_fpv_camera_amf);
        setOnClickListener(this);
        c();
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        onEventMainThread(DataCameraGetPushShotInfo.getInstance());
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        c();
    }

    private void c() {
    }

    public void show() {
        if (!DJIPreviewActivityLongan.isPopViewShown() && b.a(this.b) && !DJIPreviewActivityLongan.isHideAll) {
            super.show();
        }
    }

    public void onClick(View view) {
        a();
    }

    protected void a() {
        if ((this.a == FuselageFocusMode.OneAuto || this.a == FuselageFocusMode.ContinuousAuto) && DJICameraDataManagerCompat.getInstance().needDemarcate()) {
            DJICameraDataManagerCompat.getInstance().handleAFToMF();
        } else if (this.a != FuselageFocusMode.Manual && FuselageFocusMode.ManualFine != this.a) {
            new DataBaseCameraSetting().a(a.A).a(FuselageFocusMode.Manual.value()).start(null);
        } else if (dji.device.camera.view.focus.a.c.getInstance().b() == 1) {
            dji.device.camera.view.focus.a.c.getInstance().a(1);
        } else {
            new DataBaseCameraSetting().a(a.A).a(FuselageFocusMode.OneAuto.value()).start(null);
        }
    }

    protected void b() {
        if (this.c.isGetted()) {
            FuselageFocusMode fuselageFocusMode = this.c.getFuselageFocusMode();
            if (this.a != fuselageFocusMode) {
                this.a = fuselageFocusMode;
                if (fuselageFocusMode == FuselageFocusMode.OneAuto || fuselageFocusMode == FuselageFocusMode.ContinuousAuto) {
                    setText(a(this.d, 0, 2));
                } else if (fuselageFocusMode == FuselageFocusMode.Manual || fuselageFocusMode == FuselageFocusMode.ManualFine) {
                    setText(a(getContext().getString(R.string.longan_fpv_camera_amf), this.d.length() - 2, this.d.length()));
                } else {
                    setText(getContext().getString(R.string.longan_fpv_camera_amf));
                }
            }
        }
    }

    protected SpannableString a(String str, int i, int i2) {
        SpannableString spannableString = new SpannableString(str);
        if (i2 == str.length()) {
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.view_bg_highlight)), i, i2, 17);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white_half)), 0, i, 17);
        } else {
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.view_bg_highlight)), i, i2, 17);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white_half)), i2, str.length(), 17);
        }
        return spannableString;
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        if (isShown()) {
            b();
        } else if (dji.device.camera.view.focus.a.c.getInstance().b() == 1) {
            b();
        }
    }

    public void onEventMainThread(dji.device.camera.view.focus.a.b.b bVar) {
        if (bVar != dji.device.camera.view.focus.a.b.b.FOCUS) {
            return;
        }
        if (this.a == FuselageFocusMode.OneAuto || this.a == FuselageFocusMode.ContinuousAuto) {
            Log.d(e, "onEventMainThread: " + this.a.name());
            a();
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
            if (cameraType != this.b) {
                this.b = cameraType;
                if (b.a(cameraType)) {
                    show();
                } else {
                    go();
                }
            }
        }
    }

    public void hide() {
        if (isShown()) {
            super.hide();
        }
    }

    public void onEventMainThread(m mVar) {
        switch (mVar) {
            case HIDE_ALL:
                hide();
                return;
            case SHOW_ALL:
                show();
                return;
            case HIDE_INFO_BAR:
                hide();
                return;
            case SHOW_INFO_BAR:
                show();
                return;
            case HIDE_TIMELAPSE_LY:
                show();
                return;
            case SHOW_TIMELAPSE_LY:
                hide();
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(DJIMFDemarcateViewLongan.a aVar) {
        if (aVar == DJIMFDemarcateViewLongan.a.HIDE) {
            show();
        }
    }
}
