package dji.device.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.Scroller;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.pilot.fpv.R;
import dji.pilot.usercenter.protocol.d;

public class DJIRulerView extends View implements dji.publics.d.c {
    protected static final int a = 10;
    protected static final int b = 13;
    private static final String w = DJIRulerView.class.getSimpleName();
    protected Context c = null;
    protected int d = 0;
    protected int e = 0;
    protected Paint f = null;
    protected Drawable g = null;
    protected int h = 0;
    protected int i = 0;
    protected float j = 0.0f;
    protected int k = 0;
    protected int l = 0;
    protected Scroller m = null;
    protected VelocityTracker n = null;
    protected int o = 0;
    protected int p = 0;
    protected final RectF q = new RectF();
    protected int r = 2000;
    protected int s = 0;
    protected int t = 10;
    protected b u = null;
    protected a v = null;

    public interface a {
        void a(DJIRulerView dJIRulerView, int i, int i2, boolean z);
    }

    public interface b {
        void a(DJIRulerView dJIRulerView);

        void b(DJIRulerView dJIRulerView);
    }

    private final class c implements Runnable {
        final /* synthetic */ DJIRulerView a;
        private int b;
        private int c;
        private int d;
        private boolean e;

        private c(DJIRulerView dJIRulerView, int i, int i2) {
            this(dJIRulerView, i, i2, 2);
        }

        private c(DJIRulerView dJIRulerView, int i, int i2, int i3) {
            this.a = dJIRulerView;
            this.b = 0;
            this.c = 0;
            this.d = 2;
            this.e = false;
            this.b = i;
            this.c = i2;
            this.d = i3;
            if (i < i2) {
                this.e = true;
            }
        }

        public void run() {
            int i;
            DJIRulerView dJIRulerView;
            if (this.e) {
                if (this.c <= (this.b + this.d) + 1) {
                    i = this.a.s;
                    this.a.s = this.c;
                    if (this.a.v != null) {
                        this.a.v.a(this.a, this.c, i, true);
                    }
                    this.a.o = (int) (((float) this.c) * this.a.j);
                    this.a.postInvalidate();
                    return;
                }
                dJIRulerView = this.a;
                dJIRulerView.s += this.d;
                if (this.a.s >= this.c) {
                    i = this.a.s;
                    this.a.s = this.c;
                    if (this.a.v != null) {
                        this.a.v.a(this.a, this.c, i, true);
                    }
                    this.a.o = (int) (((float) this.c) * this.a.j);
                    this.a.postInvalidate();
                    return;
                }
                this.a.o = (int) (((float) this.a.s) * this.a.j);
                this.a.invalidate();
                this.a.postDelayed(this, 10);
            } else if ((this.c + this.d) + 1 >= this.b) {
                i = this.a.s;
                this.a.s = this.c;
                if (this.a.v != null) {
                    this.a.v.a(this.a, this.c, i, true);
                }
                this.a.o = (int) (((float) this.c) * this.a.j);
                this.a.postInvalidate();
            } else {
                dJIRulerView = this.a;
                dJIRulerView.s -= this.d;
                if (this.a.s <= this.c) {
                    i = this.a.s;
                    this.a.s = this.c;
                    if (this.a.v != null) {
                        this.a.v.a(this.a, this.c, i, true);
                    }
                    this.a.o = (int) (((float) this.c) * this.a.j);
                    this.a.postInvalidate();
                    return;
                }
                this.a.o = (int) (((float) this.a.s) * this.a.j);
                this.a.invalidate();
                this.a.postDelayed(this, 10);
            }
        }
    }

    public DJIRulerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context;
        a(context);
        if (!isInEditMode()) {
            a();
            a(attributeSet, 0);
        }
    }

    public void setOnScrollListener(b bVar) {
        this.u = bVar;
    }

    public void setOnChangeListener(a aVar) {
        this.v = aVar;
    }

    public void setMaxSize(int i) {
        if (i != this.r) {
            this.r = i;
            if (this.s > i) {
                int i2 = this.s;
                this.s = i;
                if (this.v != null) {
                    this.v.a(this, i, i2, false);
                }
                this.o = (int) (((float) (i + 1)) * this.j);
            }
            postInvalidate();
        }
    }

    public int getMaxSize() {
        return this.r;
    }

    public boolean isInMin() {
        return this.s == 0;
    }

    public boolean isInMax() {
        return this.s == this.r;
    }

    public void setCurSizeNow(int i) {
        int i2 = this.s;
        this.s = i;
        if (this.v != null) {
            this.v.a(this, i, i2, true);
        }
        this.o = (int) (((float) i) * this.j);
        postInvalidate();
    }

    public void setCurSize(int i) {
        if (i != this.s) {
            int i2 = i > this.r ? this.r : i < 0 ? 0 : i;
            post(new c(this.s, i2, (int) (((((float) Math.abs(this.s - i2)) * 1.0f) / 8.0f) + 1.0f)));
        }
    }

    public int getCurSize() {
        return this.s;
    }

    public void stepUp(int i) {
        if (this.s < this.r) {
            int i2 = this.s + i;
            if (i2 > this.r) {
                i2 = this.r;
            }
            post(new c(this.s, i2));
        }
    }

    public void stepDown(int i) {
        if (this.s > 0) {
            int i2 = this.s - i;
            if (i2 < 0) {
                i2 = 0;
            }
            post(new c(this.s, i2));
        }
    }

    public void stepNext() {
        if (this.s < this.r) {
            int i = this.s + this.t;
            if (i > this.r) {
                i = this.r;
            }
            post(new c(this.s, i));
        }
    }

    public void stepPrev() {
        if (this.s > 0) {
            int i = this.s - this.t;
            if (i < 0) {
                i = 0;
            }
            post(new c(this.s, i));
        }
    }

    protected void a(AttributeSet attributeSet, int i) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = this.c.obtainStyledAttributes(attributeSet, R.styleable.RulerViewLongan, i, 0);
            this.h = obtainStyledAttributes.getColor(R.styleable.RulerViewLongan_scaleColorLongan, this.h);
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.RulerViewLongan_selectDrawableLongan);
            if (drawable != null) {
                this.g = drawable;
            }
            this.i = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RulerViewLongan_scalePaddingLongan, this.i);
            obtainStyledAttributes.recycle();
            this.f.setColor(this.h);
        }
    }

    protected void a() {
        Resources resources = this.c.getResources();
        this.g = resources.getDrawable(R.drawable.longan_focus_mid_indicator);
        this.h = resources.getColor(R.color.white);
        this.i = resources.getDimensionPixelSize(R.dimen.gen_corner_radius);
    }

    protected void a(Context context) {
        this.m = new Scroller(context);
        this.f = new Paint();
        this.f.setAntiAlias(true);
        this.f.setStyle(Style.FILL);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.k = viewConfiguration.getScaledMinimumFlingVelocity();
        this.l = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    protected void onMeasure(int i, int i2) {
        float f = DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity;
        int size = MeasureSpec.getSize(i2);
        setMeasuredDimension(MeasureSpec.getSize(i), size);
        float f2 = this.c.getResources().getDisplayMetrics().density * 2.0f;
        if (f2 >= DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity) {
            f = f2;
        }
        int i3 = 12;
        float f3 = ((float) size) * 1.0f;
        this.j = f3 / ((float) ((this.t * 12) + 1));
        while (this.j > f) {
            i3 += 2;
            this.j = f3 / ((float) ((this.t * i3) + 1));
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.d = i;
        this.e = i2;
        int intrinsicHeight = this.g.getIntrinsicHeight();
        int intrinsicWidth = this.g.getIntrinsicWidth();
        this.g.setBounds((i - intrinsicWidth) / 2, (i2 - intrinsicHeight) / 2, (intrinsicWidth + i) / 2, (intrinsicHeight + i2) / 2);
    }

    private void b() {
        if (this.n == null) {
            this.n = VelocityTracker.obtain();
        }
    }

    private void c() {
        if (this.n != null) {
            this.n.clear();
            this.n.recycle();
            this.n = null;
        }
    }

    private void d() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    private void a(int i) {
        int i2 = (int) (((float) i) / this.j);
        if (i2 != this.s) {
            int i3 = this.s;
            this.s = i2;
            if (this.v != null) {
                this.v.a(this, i2, i3, false);
            }
        }
        DJILogHelper.getInstance().LOGD(w, "=== Ruler Size[" + i2 + d.H, false, false);
    }

    private void b(int i) {
        int i2 = (int) (((float) (this.r + 1)) * this.j);
        this.o += i;
        if (this.o < 0) {
            this.o = 0;
        } else if (this.o > i2) {
            this.o = i2;
        }
        a(this.o);
        postInvalidate();
    }

    public void computeScroll() {
        if (this.m.computeScrollOffset()) {
            this.o = this.m.getCurrY();
            a(this.o);
            if (this.m.isFinished() && this.u != null) {
                this.u.b(this);
            }
            postInvalidateOnAnimation();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        b();
        this.n.addMovement(motionEvent);
        switch (motionEvent.getAction()) {
            case 0:
                d();
                if (!this.m.isFinished()) {
                    this.m.abortAnimation();
                }
                this.p = (int) motionEvent.getY();
                if (this.u != null) {
                    this.u.a(this);
                    break;
                }
                break;
            case 1:
            case 3:
                this.n.computeCurrentVelocity(1000, (float) this.l);
                int yVelocity = (int) this.n.getYVelocity();
                if (Math.abs(yVelocity) > this.k) {
                    int i = -yVelocity;
                    this.m.fling(0, this.o, 0, i, 0, 0, 0, (int) (((float) (this.r + 1)) * this.j));
                } else if (this.u != null) {
                    this.u.b(this);
                }
                c();
                break;
            case 2:
                int y = (int) motionEvent.getY();
                int i2 = this.p - y;
                this.p = y;
                b(i2);
                break;
        }
        return true;
    }

    protected void onDraw(Canvas canvas) {
        this.g.draw(canvas);
        float f = (float) this.o;
        float f2 = this.j * ((float) (this.r + 1));
        float f3 = (((float) this.e) * 1.0f) / 2.0f;
        int i = (int) ((f / this.j) % ((float) this.t));
        if (i != 0) {
            i = this.t - i;
        }
        float f4 = (float) this.i;
        float f5 = (float) (this.d - this.i);
        float f6 = this.j / 2.0f;
        float f7 = ((float) i) * this.j;
        float f8 = this.j / 2.0f;
        while (f7 < ((float) this.e)) {
            if (f3 <= (f7 + f) + this.j && (f7 + f) + f8 <= f2 + f3) {
                this.q.set(f4, f7, f5, this.j + f7);
                this.f.setAlpha(a(f7, f3));
                canvas.drawRoundRect(this.q, f6, f6, this.f);
            }
            f7 += ((float) this.t) * this.j;
        }
    }

    private int a(float f, float f2) {
        float abs = (Math.abs((((this.j * 1.0f) / 2.0f) + f) - f2) * 1.0f) / f2;
        return (int) (((((1.0f - abs) * (1.0f - abs)) * 0.95f) + 0.05f) * 255.0f);
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
