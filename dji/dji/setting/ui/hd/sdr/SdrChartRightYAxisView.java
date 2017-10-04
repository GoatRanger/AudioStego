package dji.setting.ui.hd.sdr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.setting.ui.R;
import it.sauronsoftware.ftp4j.FTPCodes;

public class SdrChartRightYAxisView extends View {
    private String[] a = new String[]{"≈1km", "≈4km", "MHz"};
    private int b = -91;
    private int c = -103;
    private float d = 0.0f;
    private float e = 0.0f;
    private Paint f = new Paint();
    private float g = getResources().getDimension(R.dimen.dp_12);
    private float h = getResources().getDimension(R.dimen.dp_14);
    private float i;
    private float j = 0.0f;
    private float k = 0.0f;

    public SdrChartRightYAxisView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f.setColor(getResources().getColor(17170443));
        this.f.setTextSize(this.g);
        this.i = getResources().getDimension(R.dimen.setting_ui_hd_sdr_chart_height);
        this.k = this.g / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity;
        this.j = (float) ((int) getResources().getDimension(R.dimen.dp_3));
        this.d = (this.i * ((float) (50 - (this.b + FTPCodes.RESTART_MARKER)))) / 50.0f;
        this.e = (this.i * ((float) (50 - (this.c + FTPCodes.RESTART_MARKER)))) / 50.0f;
    }

    public void set1KmNfValue(int i) {
        this.b = i;
        this.c = this.b - 12;
        this.d = (this.i * ((float) (50 - (this.b + FTPCodes.RESTART_MARKER)))) / 50.0f;
        this.e = (this.i * ((float) (50 - (this.c + FTPCodes.RESTART_MARKER)))) / 50.0f;
        postInvalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f.setColor(getResources().getColor(17170443));
        this.f.setTextSize(this.g);
        canvas.drawText(String.valueOf(this.a[0]), this.j, this.d + this.k, this.f);
        canvas.drawText(String.valueOf(this.a[1]), this.j, this.e + this.k, this.f);
        this.f.setColor(getResources().getColor(17170432));
        this.f.setTextSize(this.h);
        canvas.drawText(String.valueOf(this.a[2]), this.j * 2.0f, this.i + this.k, this.f);
    }
}
