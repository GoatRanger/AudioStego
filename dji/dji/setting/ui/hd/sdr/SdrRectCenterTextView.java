package dji.setting.ui.hd.sdr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.setting.ui.R;

public class SdrRectCenterTextView extends View {
    private String a;
    private float b;
    private Paint c;
    private float d;
    private float e;

    public SdrRectCenterTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = "-70dBm";
        this.b = 0.0f;
        this.d = 30.0f;
        this.e = 0.0f;
        this.d = getResources().getDimension(R.dimen.setting_ui_txt_small);
        this.c = new Paint();
        this.c.setColor(getResources().getColor(17170443));
        this.c.setTextSize(this.d);
        this.e = getResources().getDimension(R.dimen.setting_ui_hd_sdr_chart_left_axis_width);
    }

    public void setCenterAverageText(String str) {
        this.a = str;
        postInvalidate();
    }

    public void setCenterPos(float f, boolean z) {
        this.b = f;
        if (z && getVisibility() != 4) {
            setVisibility(4);
        } else if (!(z || getVisibility() == 0)) {
            setVisibility(0);
        }
        postInvalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(this.a, (this.e + this.b) - ((this.d / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity) * ((float) this.a.length())), this.d, this.c);
    }
}
