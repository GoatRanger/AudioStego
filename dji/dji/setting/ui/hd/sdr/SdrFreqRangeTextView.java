package dji.setting.ui.hd.sdr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.util.a.b;
import dji.pilot.setting.ui.R;

public class SdrFreqRangeTextView extends View {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private Paint f;
    private float g;
    private int h;

    public SdrFreqRangeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0.0f;
        this.b = 0.0f;
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0.0f;
        this.g = 30.0f;
        this.h = 6;
        this.g = getResources().getDimension(R.dimen.dp_12);
        this.f = new Paint();
        this.f.setColor(getResources().getColor(17170443));
        this.f.setTextSize(this.g);
        this.e = getResources().getDimension(R.dimen.setting_ui_hd_sdr_chart_left_axis_width);
    }

    public void setMinMaxValue(float f, float f2, float f3, float f4) {
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        postInvalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = (this.g / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity) * ((float) this.h);
        canvas.drawText(String.format("%.1f", new Object[]{Float.valueOf(this.a)}), ((this.e + this.c) - f) - (this.g / 2.0f), this.g * b.c, this.f);
        canvas.drawText(String.format("%.1f", new Object[]{Float.valueOf(this.b)}), ((this.e + this.d) - f) + (this.g / 2.0f), this.g * b.c, this.f);
    }
}
