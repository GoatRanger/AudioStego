package dji.device.timelapse;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.camera.a.b;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class LonganTimeView extends DJITextView {
    LayoutParams a;

    public LonganTimeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a = (LayoutParams) getLayoutParams();
        a();
        onEventMainThread(b.getInstance().d());
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
        if (getResources().getConfiguration().orientation == 2) {
            this.a.addRule(11, 0);
            this.a.addRule(12, 0);
            this.a.addRule(12);
            this.a.addRule(9);
            this.a.width = getResources().getDimensionPixelOffset(R.dimen.longan_main_button_size);
            this.a.height = getResources().getDimensionPixelOffset(R.dimen.longan_time_size);
            return;
        }
        this.a.addRule(12, 0);
        this.a.addRule(9, 0);
        this.a.addRule(11);
        this.a.addRule(12);
        this.a.height = getResources().getDimensionPixelOffset(R.dimen.longan_main_button_size);
        this.a.width = getResources().getDimensionPixelOffset(R.dimen.longan_time_size);
    }

    public void onEventMainThread(b.c cVar) {
        if (cVar == b.c.NOT_TIMING) {
            setVisibility(4);
        } else {
            setVisibility(0);
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (!isShown()) {
            return;
        }
        if (dji.device.camera.a.c.getInstance().c() == dji.device.camera.a.c.b.INTERVAL || (dji.device.camera.a.c.getInstance().c() == dji.device.camera.a.c.b.SINGLE && dji.device.camera.a.c.getInstance().f() != 0)) {
            setText(dataCameraGetPushShotParams.getTimeCountdown() + "");
        } else if (dji.device.camera.a.c.getInstance().c() == dji.device.camera.a.c.b.TIMELAPSE) {
            setText(dataCameraGetPushShotParams.getTimelapseTimeCountDown() + "");
        }
    }
}
