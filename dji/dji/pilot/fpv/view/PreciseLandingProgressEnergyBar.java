package dji.pilot.fpv.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import dji.gs.e.b;
import dji.pilot.R;

public class PreciseLandingProgressEnergyBar extends View {
    private static final int a = Color.parseColor("#AEffffff");
    private static final int b = Color.parseColor("#FF0AEE8B");
    private static final int c = Color.parseColor("#FFFFFFFF");
    private int d;
    private int e;
    private int f;
    private int g;
    private Paint h = new Paint();
    private Paint i;
    private Paint j;
    private Bitmap k;

    public PreciseLandingProgressEnergyBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h.setAntiAlias(true);
        this.h.setDither(true);
        this.h.setStrokeWidth(b.a);
        this.h.setStyle(Style.STROKE);
        this.h.setColor(a);
        this.i = new Paint();
        this.i.setAntiAlias(true);
        this.i.setDither(true);
        this.i.setStrokeWidth(b.a);
        this.i.setStyle(Style.STROKE);
        this.i.setColor(b);
        this.j = new Paint();
        this.k = BitmapFactory.decodeResource(getResources(), R.drawable.fpv_precise_landing_bar_progress);
        this.e = this.k.getWidth() / 2;
        this.f = this.k.getHeight() / 2;
        this.g = 0;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0.0f, (float) this.f, (float) this.d, (float) this.f, this.h);
        canvas.drawLine(0.0f, (float) this.f, (float) getProgress(), (float) this.f, this.i);
    }

    public void setProgress(int i) {
        this.g = i;
        invalidate();
    }

    private int getProgress() {
        return (int) (((1.0d * ((double) this.g)) / 100.0d) * ((double) this.d));
    }

    private int getThumbPosition() {
        return (((int) (((1.0d * ((double) this.g)) / 100.0d) * ((double) this.d))) - this.e) - 120;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.d = i;
    }
}
