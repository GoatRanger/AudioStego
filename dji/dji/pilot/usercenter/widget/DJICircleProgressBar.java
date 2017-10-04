package dji.pilot.usercenter.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.R$styleable;

public class DJICircleProgressBar extends View {
    private Drawable a = null;
    private boolean b = false;
    private int c = 0;
    private int d = 1;

    public DJICircleProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CirclePgb, 0, 0);
        this.b = obtainStyledAttributes.getBoolean(3, this.b);
        this.a = obtainStyledAttributes.getDrawable(2);
        this.c = obtainStyledAttributes.getInt(1, this.c);
        this.d = obtainStyledAttributes.getInt(0, this.d);
        obtainStyledAttributes.recycle();
        setWillNotDraw(false);
    }

    public void setMax(int i) {
        if (this.d != i) {
            this.d = i;
            a(getWidth(), getHeight());
            postInvalidate();
        }
    }

    public void setProgress(int i) {
        if (this.c != i) {
            this.c = i;
            a(getWidth(), getHeight());
            postInvalidate();
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a(i, i2);
    }

    private void a(int i, int i2) {
        if (this.a != null) {
            this.a.setBounds(0, 0, (int) ((this.d != 0 ? ((float) this.c) / ((float) this.d) : 1.0f) * ((float) i)), i2);
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.a != null) {
            this.a.draw(canvas);
        }
    }
}
