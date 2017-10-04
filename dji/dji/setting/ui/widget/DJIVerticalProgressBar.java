package dji.setting.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import dji.publics.d.c;

public class DJIVerticalProgressBar extends ProgressBar implements c {
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;

    public DJIVerticalProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i2, i, i4, i3);
        this.a = i2;
        this.b = i;
        this.c = i4;
        this.d = i3;
    }

    public void setProgressDrawable(Drawable drawable) {
        super.setProgressDrawable(drawable);
        onSizeChanged(this.b, this.a, this.d, this.c);
    }

    public synchronized void setProgress(int i) {
        super.setProgress(i);
        onSizeChanged(this.b, this.a, this.d, this.c);
    }

    protected synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    protected void onDraw(Canvas canvas) {
        canvas.rotate(-90.0f);
        canvas.translate((float) (-getHeight()), 0.0f);
        super.onDraw(canvas);
    }

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
        }
    }

    public void hide() {
        if (getVisibility() != 4) {
            setVisibility(4);
        }
    }

    public void go() {
        if (getVisibility() != 8) {
            setVisibility(8);
        }
    }
}
