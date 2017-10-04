package dji.setting.ui.hd.sdr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.setting.ui.R;
import it.sauronsoftware.ftp4j.FTPCodes;

public class SdrDistanceLineView extends View {
    private int a = -91;
    private int b = -103;
    private float c = 0.0f;
    private float d = 0.0f;
    private Paint e = new Paint();
    private float f;

    public SdrDistanceLineView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e.setColor(getResources().getColor(17170432));
        this.e.setStrokeWidth(1.0f);
        this.e.setAlpha(FTPCodes.DATA_CONNECTION_ALREADY_OPEN);
        this.f = getResources().getDimension(R.dimen.setting_ui_hd_sdr_chart_height);
        this.c = (this.f * ((float) (50 - (this.a + FTPCodes.RESTART_MARKER)))) / 50.0f;
        this.d = (this.f * ((float) (50 - (this.b + FTPCodes.RESTART_MARKER)))) / 50.0f;
    }

    public void set1KmNfValue(int i) {
        this.a = i;
        this.b = this.a - 12;
        this.c = (this.f * ((float) (50 - (this.a + FTPCodes.RESTART_MARKER)))) / 50.0f;
        this.d = (this.f * ((float) (50 - (this.b + FTPCodes.RESTART_MARKER)))) / 50.0f;
        postInvalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0.0f, this.c, (float) canvas.getWidth(), this.c, this.e);
        canvas.drawLine(0.0f, this.d, (float) canvas.getWidth(), this.d, this.e);
    }
}
