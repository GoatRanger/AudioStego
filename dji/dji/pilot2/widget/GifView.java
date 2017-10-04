package dji.pilot2.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import dji.pilot.R$styleable;

public class GifView extends View {
    private static final int a = 1000;
    private int b;
    private Movie c;
    private long d;
    private int e;
    private float f;
    private float g;
    private float h;
    private int i;
    private int j;
    private boolean k;
    private volatile boolean l;
    private long m;
    private int n;

    public GifView(Context context) {
        this(context, null);
    }

    public GifView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GifView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 0;
        this.k = true;
        this.l = false;
        a(context, attributeSet, i);
    }

    @SuppressLint({"NewApi"})
    private void a(Context context, AttributeSet attributeSet, int i) {
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.GifView, i, 0);
        this.b = obtainStyledAttributes.getResourceId(0, -1);
        this.l = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
        if (this.b != -1) {
            this.c = Movie.decodeStream(getResources().openRawResource(this.b));
        }
    }

    public void setMovieResource(int i) {
        this.b = i;
        this.c = Movie.decodeStream(getResources().openRawResource(this.b));
        requestLayout();
    }

    public void setMovie(Movie movie) {
        this.c = movie;
        requestLayout();
    }

    public Movie getMovie() {
        return this.c;
    }

    public void setMovieTime(int i) {
        this.e = i;
        invalidate();
    }

    public void setPaused(boolean z) {
        this.l = z;
        if (!z) {
            this.d = SystemClock.uptimeMillis() - ((long) this.e);
        }
        invalidate();
    }

    public boolean isPaused() {
        return this.l;
    }

    protected void onMeasure(int i, int i2) {
        if (this.c != null) {
            int width = this.c.width();
            int height = this.c.height();
            int size = MeasureSpec.getSize(i);
            this.h = 1.0f / (((float) width) / ((float) size));
            this.i = size;
            this.j = (int) (((float) height) * this.h);
            setMeasuredDimension(this.i, this.j);
            return;
        }
        setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f = ((float) (getWidth() - this.i)) / 2.0f;
        this.g = ((float) (getHeight() - this.j)) / 2.0f;
        this.k = getVisibility() == 0;
    }

    protected void onDraw(Canvas canvas) {
        if (this.c != null) {
            if (this.l) {
                a(canvas);
            } else {
                b();
                a(canvas);
                a();
            }
            if (this.m - this.d > ((long) (this.n - 50))) {
                setPaused(true);
            }
        }
    }

    @SuppressLint({"NewApi"})
    private void a() {
        if (!this.k) {
            return;
        }
        if (VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            invalidate();
        }
    }

    private void b() {
        this.m = SystemClock.uptimeMillis();
        if (this.d == 0) {
            this.d = this.m;
        }
        this.n = this.c.duration();
        if (this.n == 0) {
            this.n = 1000;
        }
        this.e = (int) ((this.m - this.d) % ((long) this.n));
    }

    private void a(Canvas canvas) {
        this.c.setTime(this.e);
        canvas.save(1);
        canvas.scale(this.h, this.h);
        this.c.draw(canvas, this.f / this.h, this.g / this.h);
        canvas.restore();
    }

    @SuppressLint({"NewApi"})
    public void onScreenStateChanged(int i) {
        boolean z = true;
        super.onScreenStateChanged(i);
        if (i != 1) {
            z = false;
        }
        this.k = z;
        a();
    }

    @SuppressLint({"NewApi"})
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.k = i == 0;
        a();
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.k = i == 0;
        a();
    }
}
