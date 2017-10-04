package dji.pilot.visual.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.dji.frame.c.e;
import dji.pilot.R;
import dji.publics.d.c;

public class VisualDrawOnScreen extends View implements c {
    private static final boolean a = true;
    private static final int b = 48;
    private Paint c = null;
    private final Path d = new Path();
    private final RectF e = new RectF();
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private boolean j = false;
    private boolean k = false;
    private float l = 0.0f;
    private float m = 0.0f;
    private float n = 0.0f;
    private float o = 0.0f;
    private Drawable p = null;

    public VisualDrawOnScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a(context);
        }
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

    public float getMinX() {
        return this.n > this.l ? this.l : this.n;
    }

    public float getMinY() {
        return this.o > this.m ? this.m : this.o;
    }

    public float getMaxX() {
        return this.n > this.l ? this.n : this.l;
    }

    public float getMaxY() {
        return this.o > this.m ? this.o : this.m;
    }

    public boolean needDraw() {
        return this.j;
    }

    public VisualDrawOnScreen startMotionXY(float f, float f2) {
        this.j = false;
        this.k = false;
        this.d.reset();
        this.l = f;
        this.n = f;
        this.m = f2;
        this.o = f2;
        return this;
    }

    public VisualDrawOnScreen stopMotionXY() {
        float abs = Math.abs(this.n - this.l);
        float abs2 = Math.abs(this.o - this.m);
        float f = (this.n + this.l) / 2.0f;
        float f2 = (this.o + this.m) / 2.0f;
        float f3 = ((float) this.i) / 2.0f;
        if (abs < ((float) this.i)) {
            this.l = f - f3;
            this.n = f + f3;
            if (this.l < 0.0f) {
                this.l = 0.0f;
                this.n = (float) this.i;
            } else if (this.n > ((float) this.g)) {
                this.n = (float) this.g;
                this.l = (float) (this.g - this.i);
            }
        }
        if (abs2 < ((float) this.i)) {
            this.m = f2 - f3;
            this.o = f2 + f3;
            if (this.m < 0.0f) {
                this.m = 0.0f;
                this.o = (float) this.i;
            } else if (this.o > ((float) this.h)) {
                this.o = (float) this.h;
                this.m = (float) (this.h - this.i);
            }
        }
        this.e.set(getMinX(), getMinY(), getMaxX(), getMaxY());
        this.p.setBounds((int) this.e.left, (int) this.e.top, (int) this.e.right, (int) this.e.bottom);
        return this;
    }

    public boolean updateMotionXY(float f, float f2) {
        boolean z = false;
        if (this.d.isEmpty()) {
            this.d.moveTo(this.l, this.m);
        }
        this.d.lineTo(f, f2);
        this.n = f;
        this.o = f2;
        if (this.n > this.l) {
            this.e.left = this.l;
            this.e.right = this.n;
        } else {
            this.e.left = this.n;
            this.e.right = this.l;
        }
        if (this.o > this.m) {
            this.e.top = this.m;
            this.e.bottom = this.o;
        } else {
            this.e.top = this.o;
            this.e.bottom = this.m;
        }
        this.p.setBounds((int) this.e.left, (int) this.e.top, (int) this.e.right, (int) this.e.bottom);
        if (!this.j) {
            if (Math.abs(this.n - this.l) >= 48.0f || Math.abs(this.o - this.m) >= 48.0f) {
                z = true;
            }
            this.j = z;
            z = this.j;
        }
        if (this.j) {
            postInvalidate();
        }
        return z;
    }

    public void resetPath() {
        this.j = false;
        this.d.reset();
        postInvalidate();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.e.contains(motionEvent.getX(), motionEvent.getY())) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    private void a(Context context) {
        Resources resources = context.getResources();
        this.c = new Paint();
        this.c.setStyle(Style.STROKE);
        this.c.setAntiAlias(true);
        this.c.setColor(resources.getColor(R.color.om));
        this.c.setStrokeWidth((float) e.b(context, 2.0f));
        this.f = resources.getDimensionPixelSize(R.dimen.nu);
        this.p = resources.getDrawable(R.drawable.visual_track_target_bg);
        this.i = resources.getDrawable(R.drawable.visual_enter).getIntrinsicWidth() + (this.f * 2);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.g = i;
        this.h = i2;
    }

    protected void onDraw(Canvas canvas) {
        if (this.j) {
            this.p.draw(canvas);
        }
    }
}
