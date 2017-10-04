package antistatic.spinnerwheel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import dji.frame.widget.R;
import dji.pilot.visual.a.d;

public abstract class AbstractWheelView extends AbstractWheel {
    protected static final String C = "selectorPaintCoeff";
    protected static final String D = "separatorsPaintAlpha";
    private static int G = -1;
    protected static final int m = 50;
    protected static final int n = 70;
    protected static final int o = 70;
    protected static final int p = 10;
    protected static final int q = 10;
    protected static final int r = 2;
    protected Animator A;
    protected Animator B;
    protected Bitmap E;
    protected Bitmap F;
    private final String H;
    protected int s;
    protected int t;
    protected int u;
    protected int v;
    protected int w;
    protected Drawable x;
    protected Paint y;
    protected Paint z;

    protected abstract void a(Canvas canvas);

    protected abstract void j();

    public abstract void setSelectorPaintCoeff(float f);

    public AbstractWheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        StringBuilder append = new StringBuilder().append(AbstractWheelView.class.getName()).append(" #");
        int i2 = G + 1;
        G = i2;
        this.H = append.append(i2).toString();
    }

    protected void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.AbstractWheelView, i, 0);
        this.s = obtainStyledAttributes.getInt(R.styleable.AbstractWheelView_itemsDimmedAlpha, 50);
        this.t = obtainStyledAttributes.getInt(R.styleable.AbstractWheelView_selectionDividerActiveAlpha, 70);
        this.u = obtainStyledAttributes.getInt(R.styleable.AbstractWheelView_selectionDividerDimmedAlpha, 70);
        this.v = obtainStyledAttributes.getInt(R.styleable.AbstractWheelView_itemOffsetPercent, 10);
        this.w = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AbstractWheelView_itemsPadding, 10);
        this.x = obtainStyledAttributes.getDrawable(R.styleable.AbstractWheelView_selectionDivider);
        obtainStyledAttributes.recycle();
    }

    protected void a(Context context) {
        super.a(context);
        this.A = ObjectAnimator.ofFloat(this, C, new float[]{1.0f, d.c});
        this.B = ObjectAnimator.ofInt(this, D, new int[]{this.t, this.u});
        this.z = new Paint();
        this.z.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        this.z.setAlpha(this.u);
        this.y = new Paint();
        this.y.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
    }

    protected void a(int i, int i2) {
        this.E = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        this.F = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        setSelectorPaintCoeff(0.4f);
    }

    public void setSeparatorsPaintAlpha(int i) {
        this.z.setAlpha(i);
        invalidate();
    }

    public void setSelectionDivider(Drawable drawable) {
        this.x = drawable;
    }

    protected void b() {
        this.A.cancel();
        this.B.cancel();
        setSelectorPaintCoeff(1.0f);
        setSeparatorsPaintAlpha(this.t);
    }

    protected void c() {
        super.c();
        a(750);
        b(750);
    }

    protected void d() {
        a(500);
        b(500);
    }

    private void a(long j) {
        this.A.setDuration(j);
        this.A.start();
    }

    private void b(long j) {
        this.B.setDuration(j);
        this.B.start();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.j != null && this.j.h() > 0) {
            if (i()) {
                j();
            }
            f();
            a(canvas);
        }
    }
}
