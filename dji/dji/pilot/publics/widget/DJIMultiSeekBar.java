package dji.pilot.publics.widget;

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
import android.view.View;
import android.view.View.MeasureSpec;
import com.dji.frame.c.e;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.R;
import dji.pilot.R$styleable;

public class DJIMultiSeekBar extends View {
    private static final int h = 10000;
    protected Drawable a;
    protected int b;
    protected Drawable c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;

    public DJIMultiSeekBar(Context context) {
        this(context, null);
    }

    public DJIMultiSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJIMultiSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = null;
        this.b = 0;
        this.c = null;
        this.d = 1;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MultiSeekBar, i, 0);
        this.b = obtainStyledAttributes.getDimensionPixelSize(3, this.b);
        Drawable drawable = obtainStyledAttributes.getDrawable(2);
        if (drawable != null) {
            setProgressDrawable(a(drawable, false));
        }
        setMax(obtainStyledAttributes.getInt(0, this.d));
        setProgress(obtainStyledAttributes.getInt(1, this.e));
        setSecondaryProgress(obtainStyledAttributes.getInt(4, this.f));
        setThirdProgress(obtainStyledAttributes.getInt(6, this.g));
        drawable = obtainStyledAttributes.getDrawable(5);
        if (drawable != null) {
            setThumb(drawable);
        }
        obtainStyledAttributes.recycle();
    }

    public void setProgressDrawable(Drawable drawable) {
        Object obj;
        if (this.a == null || drawable == this.a) {
            obj = null;
        } else {
            this.a.setCallback(null);
            obj = 1;
        }
        if (drawable != null) {
            drawable.setCallback(this);
        }
        this.a = drawable;
        postInvalidate();
        if (obj != null) {
            a(getWidth(), getHeight());
            b(R.id.k, this.e);
            b(R.id.l, this.f);
            b(R.id.m, this.g);
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
                a(getWidth(), this.c, this.d == 0 ? 0.0f : ((float) this.e) / ((float) this.d));
            }
            postInvalidate();
            b(R.id.k, this.e);
            b(R.id.l, this.f);
            b(R.id.m, this.f);
        }
    }

    public int getProgress() {
        return this.e;
    }

    public void setProgress(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i > this.d) {
            i = this.d;
        }
        if (i != this.e) {
            this.e = i;
            if (this.c != null) {
                a(getWidth(), this.c, this.d == 0 ? 0.0f : ((float) i) / ((float) this.d));
                postInvalidate();
            }
            b(R.id.k, this.e);
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
            b(R.id.l, this.f);
        }
    }

    public int getThirdProgress() {
        return this.g;
    }

    public void setThirdProgress(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i > this.d) {
            i = this.d;
        }
        if (i != this.g) {
            this.g = i;
            b(R.id.m, this.g);
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return drawable == this.a || super.verifyDrawable(drawable);
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
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(i) + (getPaddingLeft() + getPaddingRight()), Math.max(this.c == null ? 0 : this.c.getIntrinsicHeight(), MeasureSpec.getSize(i2)) + (getPaddingTop() + getPaddingBottom()));
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
        if (this.c != null) {
            canvas.save();
            canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            this.c.draw(canvas);
            canvas.restore();
        }
    }

    private synchronized void b(int i, int i2) {
        float f;
        if (this.d > 0) {
            f = ((float) i2) / ((float) this.d);
        } else {
            f = 0.0f;
        }
        Drawable drawable = this.a;
        if (drawable != null) {
            Drawable drawable2 = null;
            if (drawable instanceof LayerDrawable) {
                drawable2 = ((LayerDrawable) drawable).findDrawableByLayerId(i);
            }
            int i3 = (int) (f * 10000.0f);
            if (drawable2 != null) {
                drawable = drawable2;
            }
            drawable.setLevel(i3);
        } else {
            invalidate();
        }
    }

    protected void a() {
        this.b = e.b(getContext(), DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity);
        this.d = 100;
        this.e = 0;
        this.f = 0;
        this.g = 0;
    }

    protected void a(int i, int i2) {
        Drawable drawable = this.a;
        Drawable drawable2 = this.c;
        int intrinsicHeight = drawable2 == null ? 0 : drawable2.getIntrinsicHeight();
        if (drawable2 != null) {
            drawable2.getIntrinsicWidth();
        }
        int i3 = this.b;
        int i4 = this.d;
        float f = i4 > 0 ? ((float) this.e) / ((float) i4) : 0.0f;
        if (drawable2 != null) {
            a(i, drawable2, f);
        }
        i4 = (intrinsicHeight - i3) / 2;
        intrinsicHeight = (i - getPaddingRight()) - getPaddingLeft();
        if (drawable != null) {
            drawable.setBounds(0, i4, intrinsicHeight, i4 + i3);
        }
    }

    protected void a(int i, Drawable drawable, float f) {
        int paddingLeft = (i - getPaddingLeft()) - getPaddingRight();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        paddingLeft = (int) (((float) (paddingLeft - intrinsicWidth)) * f);
        drawable.setBounds(paddingLeft, 0, intrinsicWidth + paddingLeft, drawable.getIntrinsicHeight());
    }

    private Shape getDrawableShape() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    private Drawable a(Drawable drawable, boolean z) {
        int i = 0;
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            Drawable[] drawableArr = new Drawable[numberOfLayers];
            for (int i2 = 0; i2 < numberOfLayers; i2++) {
                boolean z2;
                int id = layerDrawable.getId(i2);
                Drawable drawable2 = layerDrawable.getDrawable(i2);
                if (id == R.id.k || id == R.id.l || id == R.id.m) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                drawableArr[i2] = a(drawable2, z2);
            }
            Drawable layerDrawable2 = new LayerDrawable(drawableArr);
            while (i < numberOfLayers) {
                layerDrawable2.setId(i, layerDrawable.getId(i));
                i++;
            }
            return layerDrawable2;
        } else if (!(drawable instanceof BitmapDrawable)) {
            return drawable;
        } else {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Drawable shapeDrawable = new ShapeDrawable(getDrawableShape());
            shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, TileMode.REPEAT, TileMode.CLAMP));
            if (z) {
                return new ClipDrawable(shapeDrawable, 3, 1);
            }
            return shapeDrawable;
        }
    }
}
