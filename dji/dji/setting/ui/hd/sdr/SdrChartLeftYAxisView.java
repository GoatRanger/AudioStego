package dji.setting.ui.hd.sdr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.setting.ui.R;

public class SdrChartLeftYAxisView extends View {
    private int[] a = new int[]{-70, -90, SdrFreqView.b};
    private Paint b = new Paint();
    private float c = getResources().getDimension(R.dimen.dp_12);
    private float d;
    private float e = 0.0f;
    private float f = 0.0f;

    public SdrChartLeftYAxisView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b.setColor(getResources().getColor(17170443));
        this.b.setTextSize(this.c);
        this.d = getResources().getDimension(R.dimen.setting_ui_hd_sdr_chart_height);
        this.f = this.c / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity;
        this.e = (float) ((int) getResources().getDimension(R.dimen.dp_3));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(String.valueOf(this.a[0]), this.e, (this.d / 5.0f) + this.f, this.b);
        canvas.drawText(String.valueOf(this.a[1]), this.e, ((this.d / 5.0f) * 3.0f) + this.f, this.b);
        canvas.drawText(String.valueOf(this.a[2]), this.e, this.d + this.f, this.b);
    }
}
