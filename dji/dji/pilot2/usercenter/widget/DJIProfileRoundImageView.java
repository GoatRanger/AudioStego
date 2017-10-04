package dji.pilot2.usercenter.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import dji.pilot.R$styleable;
import dji.pilot.visual.a.d;

public class DJIProfileRoundImageView extends ImageView {
    private static final ScaleType a = ScaleType.CENTER_CROP;
    private static final Config b = Config.ARGB_8888;
    private static final int c = 2;
    private static final int d = 0;
    private static final int e = -16777216;
    private static final boolean f = false;
    private final RectF g;
    private final RectF h;
    private final Matrix i;
    private final Paint j;
    private final Paint k;
    private int l;
    private int m;
    private Bitmap n;
    private BitmapShader o;
    private int p;
    private int q;
    private float r;
    private float s;
    private ColorFilter t;
    private boolean u;
    private boolean v;
    private boolean w;

    public DJIProfileRoundImageView(Context context) {
        super(context);
        this.g = new RectF();
        this.h = new RectF();
        this.i = new Matrix();
        this.j = new Paint();
        this.k = new Paint();
        this.l = -16777216;
        this.m = 0;
        a();
    }

    public DJIProfileRoundImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJIProfileRoundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = new RectF();
        this.h = new RectF();
        this.i = new Matrix();
        this.j = new Paint();
        this.k = new Paint();
        this.l = -16777216;
        this.m = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CircleImageView, i, 0);
        this.m = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.l = obtainStyledAttributes.getColor(1, -16777216);
        this.w = obtainStyledAttributes.getBoolean(2, false);
        obtainStyledAttributes.recycle();
        a();
    }

    private void a() {
        super.setScaleType(a);
        this.u = true;
        if (this.v) {
            b();
            this.v = false;
        }
    }

    public ScaleType getScaleType() {
        return a;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType != a) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", new Object[]{scaleType}));
        }
    }

    public void setAdjustViewBounds(boolean z) {
        if (z) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    protected void onDraw(Canvas canvas) {
        if (getDrawable() != null) {
            canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.r, this.j);
            if (this.m != 0) {
                canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), this.s, this.k);
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        b();
    }

    public int getBorderColor() {
        return this.l;
    }

    public void setBorderColor(int i) {
        if (i != this.l) {
            this.l = i;
            this.k.setColor(this.l);
            invalidate();
        }
    }

    public void setBorderColorResource(int i) {
        setBorderColor(getContext().getResources().getColor(i));
    }

    public int getBorderWidth() {
        return this.m;
    }

    public void setBorderWidth(int i) {
        if (i != this.m) {
            this.m = i;
            b();
        }
    }

    public boolean isBorderOverlay() {
        return this.w;
    }

    public void setBorderOverlay(boolean z) {
        if (z != this.w) {
            this.w = z;
            b();
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.n = bitmap;
        b();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.n = a(drawable);
        b();
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        this.n = a(getDrawable());
        b();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        this.n = a(getDrawable());
        b();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter != this.t) {
            this.t = colorFilter;
            this.j.setColorFilter(this.t);
            invalidate();
        }
    }

    private Bitmap a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap;
            if (drawable instanceof ColorDrawable) {
                createBitmap = Bitmap.createBitmap(2, 2, b);
            } else {
                createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), b);
            }
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    private void b() {
        if (!this.u) {
            this.v = true;
        } else if (this.n != null) {
            this.o = new BitmapShader(this.n, TileMode.CLAMP, TileMode.CLAMP);
            this.j.setAntiAlias(true);
            this.j.setShader(this.o);
            this.k.setStyle(Style.STROKE);
            this.k.setAntiAlias(true);
            this.k.setColor(this.l);
            this.k.setStrokeWidth((float) this.m);
            if (VERSION.SDK_INT > 19) {
                setLayerType(2, this.j);
                setLayerType(2, this.k);
            }
            this.q = this.n.getHeight();
            this.p = this.n.getWidth();
            this.h.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.s = Math.min((this.h.height() - ((float) this.m)) / 2.0f, (this.h.width() - ((float) this.m)) / 2.0f);
            this.g.set(this.h);
            if (!this.w) {
                this.g.inset((float) this.m, (float) this.m);
            }
            this.r = Math.min(this.g.height() / 2.0f, this.g.width() / 2.0f);
            c();
            invalidate();
        }
    }

    private void c() {
        float height;
        float width;
        float f = 0.0f;
        this.i.set(null);
        if (((float) this.p) * this.g.height() > this.g.width() * ((float) this.q)) {
            height = this.g.height() / ((float) this.q);
            width = (this.g.width() - (((float) this.p) * height)) * d.c;
        } else {
            height = this.g.width() / ((float) this.p);
            width = 0.0f;
            f = (this.g.height() - (((float) this.q) * height)) * d.c;
        }
        this.i.setScale(height, height);
        this.i.postTranslate(((float) ((int) (width + d.c))) + this.g.left, ((float) ((int) (f + d.c))) + this.g.top);
        this.o.setLocalMatrix(this.i);
    }
}
