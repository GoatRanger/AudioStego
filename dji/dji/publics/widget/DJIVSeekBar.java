package dji.publics.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.frame.widget.R;

public class DJIVSeekBar extends View {
    private static final int i = 10000;
    protected Drawable a = null;
    protected int b = 0;
    protected Drawable c = null;
    protected int d = 1;
    protected int e = 0;
    protected int f = 0;
    protected Drawable g = null;
    protected int h = 80;
    private int j = 0;
    private float k = 0.0f;
    private boolean l = false;
    private float m = 0.0f;
    private a n = null;

    public interface a {
        void a(DJIVSeekBar dJIVSeekBar);

        void a(DJIVSeekBar dJIVSeekBar, int i, boolean z);

        void b(DJIVSeekBar dJIVSeekBar);
    }

    public DJIVSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.VerticalSB, 0, 0);
        this.b = obtainStyledAttributes.getDimensionPixelSize(R.styleable.VerticalSB_progressHeight, this.b);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.VerticalSB_progressDrawable);
        if (drawable != null) {
            setProgressDrawable(drawable);
        }
        setMax(obtainStyledAttributes.getInt(R.styleable.VerticalSB_max, this.d));
        setProgress(obtainStyledAttributes.getInt(R.styleable.VerticalSB_progress, this.e));
        setSecondaryProgress(obtainStyledAttributes.getInt(R.styleable.VerticalSB_secondaryProgress, this.f));
        drawable = obtainStyledAttributes.getDrawable(R.styleable.VerticalSB_thumb);
        if (drawable != null) {
            setThumb(drawable);
        }
        drawable = obtainStyledAttributes.getDrawable(R.styleable.VerticalSB_secondaryThumb);
        if (drawable != null) {
            setSecondaryThumb(drawable);
        }
        obtainStyledAttributes.recycle();
    }

    public void setOnChangeListener(a aVar) {
        this.n = aVar;
    }

    public void setProgressDrawable(Drawable drawable) {
        boolean z;
        Drawable a = a(drawable, false, 0);
        if (this.a == null || a == this.a) {
            z = false;
        } else {
            this.a.setCallback(null);
            z = true;
        }
        if (a != null) {
            a.setCallback(this);
        }
        this.a = a;
        postInvalidate();
        if (z) {
            a(getWidth(), getHeight());
            a(16908301, this.e, false, false);
            a(16908303, this.f, false, false);
        }
    }

    public void setThumb(Drawable drawable) {
        Object obj;
        if (this.c == null || drawable == this.c) {
            obj = null;
        } else {
            obj = 1;
        }
        if (!(drawable == null || obj == null || (drawable.getIntrinsicWidth() == this.c.getIntrinsicWidth() && drawable.getIntrinsicHeight() == this.c.getIntrinsicHeight()))) {
            requestLayout();
        }
        this.c = drawable;
        invalidate();
        if (obj != null) {
            a(getWidth(), getHeight());
        }
    }

    public void setSecondaryThumb(Drawable drawable) {
        this.g = drawable;
        if (drawable != null) {
            a(getWidth(), getHeight(), drawable, this.d == 0 ? 0.0f : ((float) this.f) / ((float) this.d));
        }
        invalidate();
    }

    public int getMax() {
        return this.d;
    }

    public void setMax(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i != this.d) {
            this.d = i;
            if (this.e > i) {
                this.e = i;
            }
            if (this.c != null) {
                a(getWidth(), getHeight(), this.c, this.d == 0 ? 0.0f : ((float) this.e) / ((float) this.d));
            }
            postInvalidate();
            a(16908301, this.e, false, false);
            a(16908303, this.f, false, false);
        }
    }

    public int getProgress() {
        return this.e;
    }

    public void setProgress(int i) {
        a(i, false);
    }

    private void a(int i, boolean z) {
        if (i < 0) {
            i = 0;
        }
        if (i > this.d) {
            i = this.d;
        }
        if (i != this.e) {
            this.e = i;
            a(16908301, this.e, z, true);
        }
    }

    public int getSecondaryProgress() {
        return this.f;
    }

    public void setSecondaryProgress(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i > this.d) {
            i = this.d;
        }
        if (i != this.f) {
            this.f = i;
            a(16908303, this.f, false, false);
        }
    }

    protected void a(int i, int i2) {
        Drawable drawable = this.a;
        Drawable drawable2 = this.c;
        if (drawable2 != null) {
            drawable2.getIntrinsicHeight();
        }
        int intrinsicWidth = drawable2 == null ? 0 : drawable2.getIntrinsicWidth();
        int i3 = this.b;
        int i4 = this.d;
        float f = i4 > 0 ? ((float) this.e) / ((float) i4) : 0.0f;
        if (drawable2 != null) {
            a(i, i2, drawable2, f);
        }
        intrinsicWidth = (intrinsicWidth - i3) / 2;
        i4 = (i2 - getPaddingTop()) - getPaddingBottom();
        if (drawable != null) {
            drawable.setBounds(intrinsicWidth, 0, intrinsicWidth + i3, i4);
        }
    }

    protected void a(int i, int i2, Drawable drawable, float f) {
        int paddingTop = (i2 - getPaddingTop()) - getPaddingBottom();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        paddingTop -= intrinsicHeight;
        int i3 = (i - intrinsicWidth) / 2;
        paddingTop = (getPaddingTop() + paddingTop) - ((int) (((float) paddingTop) * f));
        drawable.setBounds(i3, paddingTop, intrinsicWidth + i3, intrinsicHeight + paddingTop);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (!c()) {
                    setPressed(true);
                    if (this.c != null) {
                        invalidate(this.c.getBounds());
                    }
                    a();
                    a(motionEvent);
                    d();
                    break;
                }
                this.k = motionEvent.getY();
                break;
            case 1:
                if (this.l) {
                    a(motionEvent);
                    b();
                    setPressed(false);
                } else {
                    a();
                    a(motionEvent);
                    b();
                }
                invalidate();
                break;
            case 2:
                if (!this.l) {
                    if (Math.abs(motionEvent.getY() - this.k) > ((float) this.j)) {
                        setPressed(true);
                        if (this.c != null) {
                            invalidate(this.c.getBounds());
                        }
                        a();
                        a(motionEvent);
                        d();
                        break;
                    }
                }
                a(motionEvent);
                break;
                break;
            case 3:
                if (this.l) {
                    b();
                    setPressed(false);
                }
                invalidate();
                break;
        }
        return true;
    }

    private void a(MotionEvent motionEvent) {
        float f;
        float f2 = 0.0f;
        int height = getHeight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i = (height - paddingTop) - paddingBottom;
        int y = (int) motionEvent.getY();
        if (y > height - paddingBottom) {
            f = 0.0f;
        } else if (y < paddingTop) {
            f = 1.0f;
        } else {
            f = ((float) ((i - y) + paddingTop)) / ((float) i);
            f2 = this.m;
        }
        a((int) (f2 + (f * ((float) getMax()))), true);
    }

    private void d() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    void a() {
        this.l = true;
        if (this.n != null) {
            this.n.b(this);
        }
    }

    void b() {
        this.l = false;
        if (this.n != null) {
            this.n.a(this);
        }
    }

    protected boolean c() {
        ViewParent parent = getParent();
        while (parent != null && (parent instanceof ViewGroup)) {
            if (((ViewGroup) parent).shouldDelayChildPressedState()) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public Drawable getThumb() {
        return this.c;
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == this.a || drawable == this.c || drawable == this.g || super.verifyDrawable(drawable);
    }

    public void invalidateDrawable(Drawable drawable) {
        if (verifyDrawable(drawable)) {
            Rect bounds = drawable.getBounds();
            int scrollX = getScrollX() + getPaddingLeft();
            int scrollY = getScrollY() + getPaddingRight();
            invalidate(bounds.left + scrollX, bounds.top + scrollY, scrollX + bounds.right, bounds.bottom + scrollY);
            return;
        }
        super.invalidateDrawable(drawable);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        a(i, i2);
        if (this.g != null) {
            a(i, i2, this.g, this.d == 0 ? 0.0f : ((float) this.f) / ((float) this.d));
        }
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(Math.max(this.c == null ? 0 : this.c.getIntrinsicWidth(), this.b) + (getPaddingLeft() + getPaddingRight()), (getPaddingTop() + getPaddingBottom()) + MeasureSpec.getSize(i2));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = this.a;
        if (drawable != null) {
            canvas.save();
            canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            drawable.draw(canvas);
            canvas.restore();
        }
        if (this.g != null) {
            canvas.save();
            canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            this.g.draw(canvas);
            canvas.restore();
        }
        if (this.c != null) {
            canvas.save();
            canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            this.c.draw(canvas);
            canvas.restore();
        }
    }

    private synchronized void a(int i, int i2, boolean z, boolean z2) {
        float f;
        Drawable drawable;
        if (this.d > 0) {
            f = ((float) i2) / ((float) this.d);
        } else {
            f = 0.0f;
        }
        Drawable drawable2 = this.a;
        if (drawable2 != null) {
            drawable = null;
            if (drawable2 instanceof LayerDrawable) {
                drawable = ((LayerDrawable) drawable2).findDrawableByLayerId(i);
            }
            int i3 = (int) (10000.0f * f);
            if (drawable != null) {
                drawable2 = drawable;
            }
            drawable2.setLevel(i3);
        } else {
            invalidate();
        }
        if (i == 16908301) {
            drawable = this.c;
            if (drawable != null) {
                a(getWidth(), getHeight(), drawable, f);
                invalidate();
            }
            if (z2 && this.n != null) {
                this.n.a(this, i2, z);
            }
        } else if (i == 16908303) {
            drawable = this.g;
            if (drawable != null) {
                a(getWidth(), getHeight(), drawable, f);
                invalidate();
            }
        }
    }

    protected void a(Context context) {
        this.b = dji.publics.e.a.b(context, DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity);
        this.d = 100;
        this.e = 0;
        this.f = 0;
        this.j = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private Shape getDrawableShape() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    private Drawable a(Drawable drawable, boolean z, int i) {
        int i2 = 0;
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            Drawable[] drawableArr = new Drawable[numberOfLayers];
            for (int i3 = 0; i3 < numberOfLayers; i3++) {
                boolean z2;
                int id = layerDrawable.getId(i3);
                Drawable drawable2 = layerDrawable.getDrawable(i3);
                if (id == 16908301 || id == 16908303) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                drawableArr[i3] = a(drawable2, z2, id);
            }
            Drawable layerDrawable2 = new LayerDrawable(drawableArr);
            while (i2 < numberOfLayers) {
                layerDrawable2.setId(i2, layerDrawable.getId(i2));
                i2++;
            }
            return layerDrawable2;
        } else if (!(drawable instanceof BitmapDrawable)) {
            return drawable;
        } else {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Drawable shapeDrawable = new ShapeDrawable(getDrawableShape());
            shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, TileMode.REPEAT, TileMode.CLAMP));
            if (16908288 == i) {
                this.b = bitmap.getWidth();
            }
            if (z) {
                return new ClipDrawable(shapeDrawable, 80, 2);
            }
            return shapeDrawable;
        }
    }
}
