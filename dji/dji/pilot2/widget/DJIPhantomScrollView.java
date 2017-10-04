package dji.pilot2.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.OverScroller;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import java.lang.reflect.Field;
import java.util.ArrayList;

@SuppressLint({"ClickableViewAccessibility"})
public class DJIPhantomScrollView extends HorizontalScrollView {
    private OverScroller a;
    private int b = 0;
    private ViewGroup c = null;
    private int d = 0;
    private int e = 0;
    private ArrayList<Integer> f = new ArrayList();
    private Boolean g = Boolean.valueOf(false);
    private a h = null;
    private int i = 0;
    private int j = 4;

    public interface a {
        void a();
    }

    public DJIPhantomScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public DJIPhantomScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public DJIPhantomScrollView(Context context) {
        super(context);
        a();
    }

    private void a() {
        setHorizontalScrollBarEnabled(false);
        try {
            Class cls = getClass();
            do {
                cls = cls.getSuperclass();
            } while (!cls.getName().equals("android.widget.HorizontalScrollView"));
            Field declaredField = cls.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            this.a = (OverScroller) declaredField.get(this);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        receiveChildInfo();
    }

    public void receiveChildInfo() {
        this.c = (ViewGroup) getChildAt(0);
        if (this.c != null) {
            this.b = this.c.getChildCount();
        }
    }

    public int getChileCount() {
        return this.b;
    }

    public int getCurrentPager() {
        return this.e;
    }

    public void setOnScrollViewListener(a aVar) {
        this.h = aVar;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.d = (int) motionEvent.getX();
                requestDisallowInterceptTouchEvent(false);
                break;
            case 1:
            case 3:
                if (Math.abs(motionEvent.getX() - ((float) this.d)) < DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMaxVelocity) {
                    return true;
                }
                if (Math.abs(motionEvent.getX() - ((float) this.d)) <= ((float) (getWidth() / this.j))) {
                    b();
                } else if (motionEvent.getX() - ((float) this.d) <= 0.0f) {
                    c();
                } else if (this.e == 0) {
                    customSmoothScrollTo(((Integer) this.f.get(this.e)).intValue(), 0);
                    return true;
                } else {
                    d();
                }
                requestDisallowInterceptTouchEvent(false);
                return true;
            case 2:
                requestDisallowInterceptTouchEvent(false);
                break;
        }
        requestDisallowInterceptTouchEvent(false);
        return super.onTouchEvent(motionEvent);
    }

    private void b() {
        customSmoothScrollTo(((Integer) this.f.get(this.e)).intValue(), 0);
        this.h.a();
    }

    private void c() {
        if (this.e < this.b - 1) {
            this.e++;
            customSmoothScrollTo(((Integer) this.f.get(this.e)).intValue(), 0);
        }
        this.h.a();
    }

    private void d() {
        if (this.e > 0) {
            this.e--;
            customSmoothScrollTo(((Integer) this.f.get(this.e)).intValue(), 0);
        }
        this.h.a();
    }

    public void nextPage() {
        c();
    }

    public void prePage() {
        d();
        this.h.a();
    }

    public boolean gotoPage(int i) {
        scrollTo(((Integer) this.f.get(i)).intValue(), 0);
        this.e = i;
        this.h.a();
        return false;
    }

    public void fling(int i) {
        super.fling(0);
    }

    public boolean pageScroll(int i) {
        return super.pageScroll(i);
    }

    public void setIsPad(Boolean bool, int i) {
        if (bool.booleanValue()) {
            this.g = Boolean.valueOf(true);
            this.j = 8;
        } else {
            this.g = Boolean.valueOf(false);
            this.j = 4;
        }
        this.i = i;
    }

    public void setList(int i) {
        for (int i2 = 0; i2 < this.i; i2++) {
            this.f.add(Integer.valueOf(i * i2));
        }
    }

    public void setStart(int i) {
        if (i >= 0) {
            try {
                gotoPage(i);
            } catch (Exception e) {
            }
        } else if (this.g.booleanValue()) {
            scrollTo(0, 0);
        } else {
            scrollTo(((Integer) this.f.get(0)).intValue(), 0);
        }
    }

    @SuppressLint({"NewApi"})
    public void customSmoothScrollBy(int i, int i2) {
        if (this.a == null) {
            smoothScrollBy(i, i2);
        } else if (getChildCount() != 0) {
            int max = Math.max(0, getChildAt(0).getWidth() - ((getWidth() - getPaddingRight()) - getPaddingLeft()));
            int scrollX = getScrollX();
            int max2 = Math.max(0, getChildAt(0).getBottom() - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
            int scrollY = getScrollY();
            this.a.startScroll(scrollX, scrollY, Math.max(0, Math.min(scrollX + i, max)) - scrollX, Math.max(0, Math.min(scrollY + i2, max2)) - scrollY, 600);
            invalidate();
        }
    }

    public void customSmoothScrollTo(int i, int i2) {
        customSmoothScrollBy(i - getScrollX(), i2 - getScrollY());
    }
}
