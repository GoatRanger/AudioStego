package dji.pilot.publics.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import dji.publics.DJIUI.DJIListView;

public class DJISwipeListView extends DJIListView {
    private View a;
    private View b;
    private float c;
    private float d;
    private int e = 300;
    private Boolean f;
    private final int g = 100;
    private final int h = 10;
    private boolean i;
    private boolean j = false;
    private boolean k = false;

    private class a implements Runnable {
        final /* synthetic */ DJISwipeListView a;
        private int b;
        private int c;
        private int d;
        private View e;
        private boolean f;

        private a(DJISwipeListView dJISwipeListView, View view, int i, int i2) {
            this.a = dJISwipeListView;
            this.b = 0;
            this.f = false;
            this.e = view;
            this.c = i;
            this.d = i2;
        }

        private void a() {
            this.f = false;
            this.b = 0;
        }

        public void run() {
            boolean z = true;
            if (this.b == 0) {
                if (!this.f) {
                    this.f = true;
                    this.b = (int) ((((double) ((this.d - this.c) * 10)) * 1.0d) / 100.0d);
                    if (this.b < 0 && this.b > -1) {
                        this.b = -1;
                    } else if (this.b > 0 && this.b < 1) {
                        this.b = 1;
                    }
                    if (Math.abs(this.d - this.c) < 10) {
                        this.e.scrollTo(this.d, 0);
                        this.a.invalidate();
                        a();
                        return;
                    }
                }
                return;
            }
            this.c += this.b;
            if ((this.b <= 0 || this.c <= this.d) && (this.b >= 0 || this.c >= this.d)) {
                z = false;
            }
            if (z) {
                this.c = this.d;
            }
            this.e.scrollTo(this.c, 0);
            this.a.invalidate();
            if (z) {
                a();
            } else {
                this.a.postDelayed(this, 10);
            }
        }
    }

    public DJISwipeListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getRightViewWidth() {
        return this.e;
    }

    public void setRightViewWidth(int i) {
        this.e = i;
    }

    public void setHandleAllEvent(boolean z) {
        this.j = z;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.j) {
            return true;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                this.f = null;
                this.c = x;
                this.d = y;
                int pointToPosition = pointToPosition((int) this.c, (int) this.d);
                this.a = this.b;
                if (pointToPosition < 0) {
                    this.b = null;
                    break;
                }
                this.b = getChildAt(pointToPosition - getFirstVisiblePosition());
                break;
            case 1:
            case 3:
                if (this.i && this.a != null && (this.a != this.b || a(x))) {
                    b(this.a);
                    this.a = null;
                    break;
                }
            case 2:
                y -= this.d;
                if (Math.abs(x - this.c) >= 20.0f && Math.abs(y) >= 20.0f) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    private boolean a(float f) {
        return f < ((float) (getWidth() - this.e));
    }

    private boolean a(float f, float f2) {
        if (Math.abs(f) > 30.0f && Math.abs(f) > Math.abs(f2) * 2.0f) {
            this.f = Boolean.valueOf(true);
            return true;
        } else if (Math.abs(f2) <= 30.0f || Math.abs(f2) <= Math.abs(f) * 2.0f) {
            return false;
        } else {
            this.f = Boolean.valueOf(false);
            return true;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.j) {
            return true;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 1:
            case 3:
                a();
                if (this.i && this.a != null) {
                    b(this.a);
                    this.a = null;
                }
                if (this.f != null && this.f.booleanValue()) {
                    if (this.c - x > ((float) (this.e / 2))) {
                        a(this.b);
                        return true;
                    } else if (this.b == null) {
                        return true;
                    } else {
                        b(this.b);
                        this.b = null;
                        return true;
                    }
                }
            case 2:
                x -= this.c;
                y -= this.d;
                if (this.f != null || this.b == null || a(x, y)) {
                    if (this.f == null || !this.f.booleanValue()) {
                        if (this.i && this.a != null) {
                            b(this.a);
                            this.a = null;
                            break;
                        }
                    }
                    if (!(!this.i || this.a == null || this.a == this.b)) {
                        b(this.a);
                        this.a = null;
                    }
                    if (this.i && this.a == this.b) {
                        x -= (float) this.e;
                    }
                    if (x >= 0.0f || x <= ((float) (-this.e)) || this.b == null) {
                        return true;
                    }
                    this.b.scrollTo((int) (-x), 0);
                    return true;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void a() {
        if (this.b != null) {
            this.b.setPressed(false);
        }
        if (this.a != null) {
            this.a.setPressed(false);
        }
        setPressed(false);
        refreshDrawableState();
    }

    private void a(View view) {
        post(new a(view, view.getScrollX(), this.e));
        this.i = true;
        this.k = true;
    }

    public void hiddenRight() {
        if (this.b != null) {
            b(this.b);
            this.b = null;
        }
    }

    private void b(View view) {
        post(new a(view, view.getScrollX(), 0));
        this.i = false;
        view.postDelayed(new Runnable(this) {
            final /* synthetic */ DJISwipeListView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.k = false;
            }
        }, 10);
    }

    public boolean isRightShow() {
        return this.k;
    }
}
