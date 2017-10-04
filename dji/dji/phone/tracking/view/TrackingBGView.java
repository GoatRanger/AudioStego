package dji.phone.tracking.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.fpv.R;
import lecho.lib.hellocharts.model.l;

public class TrackingBGView extends View {
    private static final String a = "TKBGView";
    private static final float c = 0.074f;
    private int b;
    private final int d = getResources().getDimensionPixelOffset(R.dimen.dp_2_in_sw320dp);
    private final int e = getResources().getDimensionPixelOffset(R.dimen.dp_4_in_sw320dp);
    private int f = Color.argb(25, 255, 255, 255);

    public TrackingBGView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setColor(int i) {
        this.b = i;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    protected void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        canvas.save();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(this.f);
        paint.setStrokeWidth(((float) this.e) * l.n);
        canvas.drawLine(0.0f, 0.0f, (float) getWidth(), 0.0f, paint);
        canvas.drawLine((float) getWidth(), 0.0f, (float) getWidth(), (float) getHeight(), paint);
        canvas.drawLine((float) getWidth(), (float) getHeight(), 0.0f, (float) getHeight(), paint);
        canvas.drawLine(0.0f, (float) getHeight(), 0.0f, 0.0f, paint);
        paint.setColor(this.b);
        int width = (int) (((float) getWidth()) * c);
        int height = (int) (((float) getHeight()) * c);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_7_in_sw320dp);
        width = Math.min(width, height);
        if (width < dimensionPixelSize) {
            i = dimensionPixelSize;
        } else {
            i = width;
        }
        paint.setStrokeWidth((float) this.e);
        paint.setStyle(Style.STROKE);
        canvas.drawLine(0.0f, 0.0f, (float) i, 0.0f, paint);
        canvas.drawLine(0.0f, 0.0f, 0.0f, (float) i, paint);
        canvas.drawLine((float) getWidth(), 0.0f, (float) (getWidth() - i), 0.0f, paint);
        canvas.drawLine((float) getWidth(), 0.0f, (float) getWidth(), (float) i, paint);
        canvas.drawLine(0.0f, (float) (getHeight() - i), 0.0f, (float) getHeight(), paint);
        canvas.drawLine(0.0f, (float) getHeight(), (float) i, (float) getHeight(), paint);
        canvas.drawLine((float) (getWidth() - i), (float) getHeight(), (float) getWidth(), (float) getHeight(), paint);
        canvas.drawLine((float) getWidth(), (float) (getHeight() - i), (float) getWidth(), (float) getHeight(), paint);
        canvas.restore();
    }
}
