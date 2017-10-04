package dji.pilot.usercenter.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class DJICircleProgressBar2 extends View {
    RectF a = new RectF();
    Paint b = new Paint();
    private int c = 100;
    private int d = 0;

    public DJICircleProgressBar2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onDraw(Canvas canvas) {
        int i;
        int i2;
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (width != height) {
            height = Math.min(width, height);
            i = height;
            i2 = height;
        } else {
            i = height;
            i2 = width;
        }
        this.b.setAntiAlias(true);
        this.b.setColor(-1);
        canvas.drawColor(0);
        this.b.setStrokeWidth((float) 4);
        this.b.setStyle(Style.STROKE);
        this.a.left = (float) 2;
        this.a.top = (float) 2;
        this.a.right = (float) (i2 - 2);
        this.a.bottom = (float) (i - 2);
        canvas.drawArc(this.a, -90.0f, 360.0f, false, this.b);
        this.b.setColor(-16777216);
        canvas.drawArc(this.a, -90.0f, 360.0f * (((float) this.d) / ((float) this.c)), false, this.b);
        this.b.setStrokeWidth(1.0f);
        String str = this.d + "%";
        width = i / 4;
        this.b.setTextSize((float) width);
        int measureText = (int) this.b.measureText(str, 0, str.length());
        this.b.setStyle(Style.FILL);
        canvas.drawText(str, (float) ((i2 / 2) - (measureText / 2)), (float) ((width / 2) + (i / 2)), this.b);
    }

    public int getMaxProgress() {
        return this.c;
    }

    public void setMaxProgress(int i) {
        this.c = i;
    }

    public void setProgress(int i) {
        this.d = i;
        invalidate();
    }

    public void setProgressNotInUiThread(int i) {
        this.d = i;
        postInvalidate();
    }
}
