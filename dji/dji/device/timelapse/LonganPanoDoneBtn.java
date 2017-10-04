package dji.device.timelapse;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.activity.DJIPreviewActivityLongan;
import dji.device.camera.a.b;
import dji.device.camera.a.c.a;
import dji.device.common.DJIUIEventManagerLongan.e;
import dji.midware.data.model.P3.DataCameraGetPushCurPanoFileName;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class LonganPanoDoneBtn extends Button implements OnClickListener {
    LayoutParams a;

    public LonganPanoDoneBtn(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnClickListener(this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a = (LayoutParams) getLayoutParams();
        a();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
    }

    private void a() {
        this.a.addRule(12);
        this.a.addRule(11);
        if (getResources().getConfiguration().orientation == 2) {
            this.a.bottomMargin = getResources().getDimensionPixelOffset(R.dimen.dp_10_in_sw320dp);
            this.a.rightMargin = getResources().getDimensionPixelOffset(R.dimen.dp_40_in_sw320dp);
            return;
        }
        int i = (DJIPreviewActivityLongan.mScreenHeight / 2) - (DJIPreviewActivityLongan.mVideoHeight / 2);
        this.a.bottomMargin = i + getResources().getDimensionPixelOffset(R.dimen.dp_2_in_sw320dp);
        this.a.rightMargin = getResources().getDimensionPixelOffset(R.dimen.dp_5_in_sw320dp);
    }

    public void onEventMainThread(DataCameraGetPushCurPanoFileName dataCameraGetPushCurPanoFileName) {
        if (dji.device.camera.a.c.getInstance().d() == a.PANO_MANU && dataCameraGetPushCurPanoFileName.getCurSavedNumber() >= 2) {
            setVisibility(0);
        }
    }

    public void onEventMainThread(b.a aVar) {
        if (aVar == b.a.PHOTO_NOT) {
            setVisibility(8);
        }
        Log.d("", "status:" + aVar);
    }

    public void onClick(View view) {
        DataSpecialControl.getInstance().setPhotoType(TYPE.STOP).start(20);
        c.a().e(e.FORCE_START_PANO);
    }
}
