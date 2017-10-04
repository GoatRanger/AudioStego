package dji.pilot.fpv.sport;

import android.content.Context;
import android.util.AttributeSet;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.model.P3.DataFlycGetPushPowerParam;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIClipView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import it.sauronsoftware.ftp4j.FTPCodes;

public class MergeSportAttitudeView extends DJIRelativeLayout {
    private static final int[] a = new int[]{5, 26, 78, 100};
    private static final int[] b = new int[]{120, 165, 270, FTPCodes.PENDING_FURTHER_INFORMATION};
    private DJITextView c = null;
    private float d = 32767.0f;
    private float e = 32767.0f;
    private DJIClipView f;

    public MergeSportAttitudeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            onEventMainThread(DataFlycGetPushPowerParam.getInstance());
        }
        a();
    }

    public void go() {
        if (getVisibility() != 8) {
            if (c.a().c(this)) {
                c.a().d(this);
            }
            setVisibility(8);
        }
    }

    public void onEventMainThread(DataFlycGetPushPowerParam dataFlycGetPushPowerParam) {
        float f = 9800.0f;
        float escAverageSpeed = dataFlycGetPushPowerParam.getEscAverageSpeed();
        if (escAverageSpeed <= 9800.0f) {
            f = escAverageSpeed;
        }
        if (Math.abs(f - this.d) >= 1.0f) {
            this.d = f;
            this.c.setText(String.format("%04d", new Object[]{Integer.valueOf((int) f)}));
        }
        f = dataFlycGetPushPowerParam.getLift();
        if (f < 0.0f || Float.compare(f, Float.NaN) == 0) {
            f = 0.0f;
        } else if (f > DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) {
            f = DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity;
        }
        if (Math.abs(f - this.e) >= 0.1f) {
            this.e = f;
            if (this.e == 0.0f) {
                this.e = 0.001f;
            }
            this.f.updateScale(this.e / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
        }
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.f = (DJIClipView) findViewById(R.id.a8z);
            this.c = (DJITextView) findViewById(R.id.a90);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            a();
        }
    }

    private void a() {
        this.f.updateScale(0.001f);
        this.c.setText(String.format("%04d", new Object[]{Integer.valueOf(0)}));
    }

    protected void onDetachedFromWindow() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
