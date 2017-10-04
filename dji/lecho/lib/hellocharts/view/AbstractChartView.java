package lecho.lib.hellocharts.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import lecho.lib.hellocharts.a.c;
import lecho.lib.hellocharts.a.e;
import lecho.lib.hellocharts.a.f;
import lecho.lib.hellocharts.a.g;
import lecho.lib.hellocharts.b.a;
import lecho.lib.hellocharts.e.m;
import lecho.lib.hellocharts.g.b;
import lecho.lib.hellocharts.g.d;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.model.n;

public abstract class AbstractChartView extends View implements a {
    protected a a;
    protected b b;
    protected lecho.lib.hellocharts.d.b c;
    protected d d;
    protected lecho.lib.hellocharts.a.b e;
    protected e f;
    protected boolean g;
    protected boolean h;
    protected lecho.lib.hellocharts.d.d i;

    public AbstractChartView(Context context) {
        this(context, null, 0);
    }

    public AbstractChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AbstractChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = true;
        this.h = false;
        this.a = new a();
        this.c = new lecho.lib.hellocharts.d.b(context, this);
        this.b = new b(context, this);
        if (VERSION.SDK_INT < 14) {
            this.e = new lecho.lib.hellocharts.a.d(this);
            this.f = new g(this);
            return;
        }
        this.f = new f(this);
        this.e = new c(this);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.a.a(getWidth(), getHeight(), getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        this.d.i();
        this.b.a();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isEnabled()) {
            this.b.a(canvas);
            int save = canvas.save();
            canvas.clipRect(this.a.b());
            this.d.a(canvas);
            canvas.restoreToCount(save);
            this.d.b(canvas);
            this.b.b(canvas);
            return;
        }
        canvas.drawColor(lecho.lib.hellocharts.h.b.a);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        if (!this.g) {
            return false;
        }
        boolean a;
        if (this.h) {
            a = this.c.a(motionEvent, getParent(), this.i);
        } else {
            a = this.c.a(motionEvent);
        }
        if (a) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        return true;
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.g && this.c.b()) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void startDataAnimation() {
        this.e.a(Long.MIN_VALUE);
    }

    public void startDataAnimation(long j) {
        this.e.a(j);
    }

    public void cancelDataAnimation() {
        this.e.a();
    }

    public void animationDataUpdate(float f) {
        getChartData().a(f);
        this.d.j();
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public void animationDataFinished() {
        getChartData().l();
        this.d.j();
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public void setDataAnimationListener(lecho.lib.hellocharts.a.a aVar) {
        this.e.a(aVar);
    }

    public void setViewportAnimationListener(lecho.lib.hellocharts.a.a aVar) {
        this.f.a(aVar);
    }

    public void setViewportChangeListener(m mVar) {
        this.a.a(mVar);
    }

    public d getChartRenderer() {
        return this.d;
    }

    public void setChartRenderer(d dVar) {
        this.d = dVar;
        b();
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public b getAxesRenderer() {
        return this.b;
    }

    public a getChartComputator() {
        return this.a;
    }

    public lecho.lib.hellocharts.d.b getTouchHandler() {
        return this.c;
    }

    public boolean isInteractive() {
        return this.g;
    }

    public void setInteractive(boolean z) {
        this.g = z;
    }

    public boolean isZoomEnabled() {
        return this.c.c();
    }

    public void setZoomEnabled(boolean z) {
        this.c.a(z);
    }

    public boolean isScrollEnabled() {
        return this.c.d();
    }

    public void setScrollEnabled(boolean z) {
        this.c.b(z);
    }

    public void moveTo(float f, float f2) {
        setCurrentViewport(a(f, f2));
    }

    public void moveToWithAnimation(float f, float f2) {
        setCurrentViewportWithAnimation(a(f, f2));
    }

    private Viewport a(float f, float f2) {
        Viewport maximumViewport = getMaximumViewport();
        Viewport currentViewport = getCurrentViewport();
        Viewport viewport = new Viewport(currentViewport);
        if (maximumViewport.d(f, f2)) {
            float c = currentViewport.c();
            float d = currentViewport.d();
            float f3 = (d / 2.0f) + f2;
            float max = Math.max(maximumViewport.a, Math.min(f - (c / 2.0f), maximumViewport.c - c));
            float max2 = Math.max(maximumViewport.d + d, Math.min(f3, maximumViewport.b));
            viewport.a(max, max2, c + max, max2 - d);
        }
        return viewport;
    }

    public boolean isValueTouchEnabled() {
        return this.c.f();
    }

    public void setValueTouchEnabled(boolean z) {
        this.c.c(z);
    }

    public lecho.lib.hellocharts.d.g getZoomType() {
        return this.c.e();
    }

    public void setZoomType(lecho.lib.hellocharts.d.g gVar) {
        this.c.a(gVar);
    }

    public float getMaxZoom() {
        return this.a.k();
    }

    public void setMaxZoom(float f) {
        this.a.e(f);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public float getZoomLevel() {
        Viewport maximumViewport = getMaximumViewport();
        Viewport currentViewport = getCurrentViewport();
        return Math.max(maximumViewport.c() / currentViewport.c(), maximumViewport.d() / currentViewport.d());
    }

    public void setZoomLevel(float f, float f2, float f3) {
        setCurrentViewport(a(f, f2, f3));
    }

    public void setZoomLevelWithAnimation(float f, float f2, float f3) {
        setCurrentViewportWithAnimation(a(f, f2, f3));
    }

    private Viewport a(float f, float f2, float f3) {
        Viewport maximumViewport = getMaximumViewport();
        Viewport viewport = new Viewport(getMaximumViewport());
        if (maximumViewport.d(f, f2)) {
            if (f3 < 1.0f) {
                f3 = 1.0f;
            } else if (f3 > getMaxZoom()) {
                f3 = getMaxZoom();
            }
            float c = viewport.c() / f3;
            float d = viewport.d() / f3;
            float f4 = c / 2.0f;
            float f5 = d / 2.0f;
            float f6 = f - f4;
            f4 += f;
            float f7 = f2 + f5;
            f5 = f2 - f5;
            if (f6 < maximumViewport.a) {
                f6 = maximumViewport.a;
                f4 = f6 + c;
            } else if (f4 > maximumViewport.c) {
                f4 = maximumViewport.c;
                f6 = f4 - c;
            }
            if (f7 > maximumViewport.b) {
                f7 = maximumViewport.b;
                f5 = f7 - d;
            } else if (f5 < maximumViewport.d) {
                f5 = maximumViewport.d;
                f7 = f5 + d;
            }
            lecho.lib.hellocharts.d.g zoomType = getZoomType();
            if (lecho.lib.hellocharts.d.g.HORIZONTAL_AND_VERTICAL == zoomType) {
                viewport.a(f6, f7, f4, f5);
            } else if (lecho.lib.hellocharts.d.g.HORIZONTAL == zoomType) {
                viewport.a = f6;
                viewport.c = f4;
            } else if (lecho.lib.hellocharts.d.g.VERTICAL == zoomType) {
                viewport.b = f7;
                viewport.d = f5;
            }
        }
        return viewport;
    }

    public Viewport getMaximumViewport() {
        return this.d.e();
    }

    public void setMaximumViewport(Viewport viewport) {
        this.d.a(viewport);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public void setCurrentViewportWithAnimation(Viewport viewport) {
        if (viewport != null) {
            this.f.a();
            this.f.a(getCurrentViewport(), viewport);
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public void setCurrentViewportWithAnimation(Viewport viewport, long j) {
        if (viewport != null) {
            this.f.a();
            this.f.a(getCurrentViewport(), viewport, j);
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public Viewport getCurrentViewport() {
        return getChartRenderer().f();
    }

    public void setCurrentViewport(Viewport viewport) {
        if (viewport != null) {
            this.d.b(viewport);
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public void resetViewports() {
        this.d.a(null);
        this.d.b(null);
    }

    public boolean isViewportCalculationEnabled() {
        return this.d.g();
    }

    public void setViewportCalculationEnabled(boolean z) {
        this.d.a(z);
    }

    public boolean isValueSelectionEnabled() {
        return this.c.g();
    }

    public void setValueSelectionEnabled(boolean z) {
        this.c.d(z);
    }

    public void selectValue(n nVar) {
        this.d.a(nVar);
        callTouchListener();
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public n getSelectedValue() {
        return this.d.h();
    }

    public boolean isContainerScrollEnabled() {
        return this.h;
    }

    public void setContainerScrollEnabled(boolean z, lecho.lib.hellocharts.d.d dVar) {
        this.h = z;
        this.i = dVar;
    }

    protected void a() {
        this.a.a();
        this.d.b();
        this.b.b();
        ViewCompat.postInvalidateOnAnimation(this);
    }

    protected void b() {
        this.d.a();
        this.b.c();
        this.c.a();
    }

    public boolean canScrollHorizontally(int i) {
        if (((double) getZoomLevel()) <= 1.0d) {
            return false;
        }
        Viewport currentViewport = getCurrentViewport();
        Viewport maximumViewport = getMaximumViewport();
        if (i < 0) {
            if (currentViewport.a <= maximumViewport.a) {
                return false;
            }
            return true;
        } else if (currentViewport.c >= maximumViewport.c) {
            return false;
        } else {
            return true;
        }
    }
}
