package com.ortiz.touch;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.OverScroller;
import android.widget.Scroller;

public class TouchImageView extends ImageView {
    private static final String a = "DEBUG";
    private static final float b = 0.75f;
    private static final float c = 1.25f;
    private ScaleGestureDetector A;
    private GestureDetector B;
    private OnDoubleTapListener C = null;
    private OnTouchListener D = null;
    private e E = null;
    private float d;
    private Matrix e;
    private Matrix f;
    private h g;
    private float h;
    private float i;
    private float j;
    private float k;
    private float[] l;
    private Context m;
    private c n;
    private ScaleType o;
    private boolean p;
    private boolean q;
    private i r;
    private int s;
    private int t;
    private int u;
    private int v;
    private float w;
    private float x;
    private float y;
    private float z;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        static {
            try {
                a[ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    @TargetApi(9)
    private class a {
        Scroller a;
        OverScroller b;
        boolean c;
        final /* synthetic */ TouchImageView d;

        public a(TouchImageView touchImageView, Context context) {
            this.d = touchImageView;
            if (VERSION.SDK_INT < 9) {
                this.c = true;
                this.a = new Scroller(context);
                return;
            }
            this.c = false;
            this.b = new OverScroller(context);
        }

        public void a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (this.c) {
                this.a.fling(i, i2, i3, i4, i5, i6, i7, i8);
            } else {
                this.b.fling(i, i2, i3, i4, i5, i6, i7, i8);
            }
        }

        public void a(boolean z) {
            if (this.c) {
                this.a.forceFinished(z);
            } else {
                this.b.forceFinished(z);
            }
        }

        public boolean a() {
            if (this.c) {
                return this.a.isFinished();
            }
            return this.b.isFinished();
        }

        public boolean b() {
            if (this.c) {
                return this.a.computeScrollOffset();
            }
            this.b.computeScrollOffset();
            return this.b.computeScrollOffset();
        }

        public int c() {
            if (this.c) {
                return this.a.getCurrX();
            }
            return this.b.getCurrX();
        }

        public int d() {
            if (this.c) {
                return this.a.getCurrY();
            }
            return this.b.getCurrY();
        }
    }

    private class b implements Runnable {
        private static final float c = 500.0f;
        final /* synthetic */ TouchImageView a;
        private long b;
        private float d;
        private float e;
        private float f;
        private float g;
        private boolean h;
        private AccelerateDecelerateInterpolator i = new AccelerateDecelerateInterpolator();
        private PointF j;
        private PointF k;

        b(TouchImageView touchImageView, float f, float f2, float f3, boolean z) {
            this.a = touchImageView;
            touchImageView.setState(h.ANIMATE_ZOOM);
            this.b = System.currentTimeMillis();
            this.d = touchImageView.d;
            this.e = f;
            this.h = z;
            PointF a = touchImageView.a(f2, f3, false);
            this.f = a.x;
            this.g = a.y;
            this.j = touchImageView.a(this.f, this.g);
            this.k = new PointF((float) (touchImageView.s / 2), (float) (touchImageView.t / 2));
        }

        public void run() {
            float a = a();
            this.a.a(b(a), this.f, this.g, this.h);
            a(a);
            this.a.c();
            this.a.setImageMatrix(this.a.e);
            if (this.a.E != null) {
                this.a.E.a();
            }
            if (a < 1.0f) {
                this.a.a((Runnable) this);
            } else {
                this.a.setState(h.NONE);
            }
        }

        private void a(float f) {
            float f2 = this.j.x + ((this.k.x - this.j.x) * f);
            float f3 = this.j.y + ((this.k.y - this.j.y) * f);
            PointF a = this.a.a(this.f, this.g);
            this.a.e.postTranslate(f2 - a.x, f3 - a.y);
        }

        private float a() {
            return this.i.getInterpolation(Math.min(1.0f, ((float) (System.currentTimeMillis() - this.b)) / 500.0f));
        }

        private double b(float f) {
            return ((double) (this.d + ((this.e - this.d) * f))) / ((double) this.a.d);
        }
    }

    private class c implements Runnable {
        a a;
        int b;
        int c;
        final /* synthetic */ TouchImageView d;

        c(TouchImageView touchImageView, int i, int i2) {
            int i3;
            int i4;
            int k;
            int i5;
            this.d = touchImageView;
            touchImageView.setState(h.FLING);
            this.a = new a(touchImageView, touchImageView.m);
            touchImageView.e.getValues(touchImageView.l);
            int i6 = (int) touchImageView.l[2];
            int i7 = (int) touchImageView.l[5];
            if (touchImageView.getImageWidth() > ((float) touchImageView.s)) {
                i3 = touchImageView.s - ((int) touchImageView.getImageWidth());
                i4 = 0;
            } else {
                i4 = i6;
                i3 = i6;
            }
            if (touchImageView.getImageHeight() > ((float) touchImageView.t)) {
                k = touchImageView.t - ((int) touchImageView.getImageHeight());
                i5 = 0;
            } else {
                i5 = i7;
                k = i7;
            }
            this.a.a(i6, i7, i, i2, i3, i4, k, i5);
            this.b = i6;
            this.c = i7;
        }

        public void a() {
            if (this.a != null) {
                this.d.setState(h.NONE);
                this.a.a(true);
            }
        }

        public void run() {
            if (this.d.E != null) {
                this.d.E.a();
            }
            if (this.a.a()) {
                this.a = null;
            } else if (this.a.b()) {
                int c = this.a.c();
                int d = this.a.d();
                int i = c - this.b;
                int i2 = d - this.c;
                this.b = c;
                this.c = d;
                this.d.e.postTranslate((float) i, (float) i2);
                this.d.b();
                this.d.setImageMatrix(this.d.e);
                this.d.a((Runnable) this);
            }
        }
    }

    private class d extends SimpleOnGestureListener {
        final /* synthetic */ TouchImageView a;

        private d(TouchImageView touchImageView) {
            this.a = touchImageView;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (this.a.C != null) {
                return this.a.C.onSingleTapConfirmed(motionEvent);
            }
            return this.a.performClick();
        }

        public void onLongPress(MotionEvent motionEvent) {
            this.a.performLongClick();
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.a.n != null) {
                this.a.n.a();
            }
            this.a.n = new c(this.a, (int) f, (int) f2);
            this.a.a(this.a.n);
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            boolean onDoubleTap;
            if (this.a.C != null) {
                onDoubleTap = this.a.C.onDoubleTap(motionEvent);
            } else {
                onDoubleTap = false;
            }
            if (this.a.g != h.NONE) {
                return onDoubleTap;
            }
            this.a.a(new b(this.a, this.a.d == this.a.h ? this.a.i : this.a.h, motionEvent.getX(), motionEvent.getY(), false));
            return true;
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (this.a.C != null) {
                return this.a.C.onDoubleTapEvent(motionEvent);
            }
            return false;
        }
    }

    public interface e {
        void a();
    }

    private class f implements OnTouchListener {
        final /* synthetic */ TouchImageView a;
        private PointF b;

        private f(TouchImageView touchImageView) {
            this.a = touchImageView;
            this.b = new PointF();
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.a.A.onTouchEvent(motionEvent);
            this.a.B.onTouchEvent(motionEvent);
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
            if (this.a.g == h.NONE || this.a.g == h.DRAG || this.a.g == h.FLING) {
                switch (motionEvent.getAction()) {
                    case 0:
                        this.b.set(pointF);
                        if (this.a.n != null) {
                            this.a.n.a();
                        }
                        this.a.setState(h.DRAG);
                        break;
                    case 1:
                    case 6:
                        this.a.setState(h.NONE);
                        break;
                    case 2:
                        if (this.a.g == h.DRAG) {
                            float f = pointF.y - this.b.y;
                            this.a.e.postTranslate(this.a.b(pointF.x - this.b.x, (float) this.a.s, this.a.getImageWidth()), this.a.b(f, (float) this.a.t, this.a.getImageHeight()));
                            this.a.b();
                            this.b.set(pointF.x, pointF.y);
                            break;
                        }
                        break;
                }
            }
            this.a.setImageMatrix(this.a.e);
            if (this.a.D != null) {
                this.a.D.onTouch(view, motionEvent);
            }
            if (this.a.E != null) {
                this.a.E.a();
            }
            return true;
        }
    }

    private class g extends SimpleOnScaleGestureListener {
        final /* synthetic */ TouchImageView a;

        private g(TouchImageView touchImageView) {
            this.a = touchImageView;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            this.a.setState(h.ZOOM);
            return true;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            this.a.a((double) scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), true);
            if (this.a.E != null) {
                this.a.E.a();
            }
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            super.onScaleEnd(scaleGestureDetector);
            this.a.setState(h.NONE);
            boolean z = false;
            float d = this.a.d;
            if (this.a.d > this.a.i) {
                d = this.a.i;
                z = true;
            } else if (this.a.d < this.a.h) {
                d = this.a.h;
                z = true;
            }
            if (z) {
                this.a.a(new b(this.a, d, (float) (this.a.s / 2), (float) (this.a.t / 2), true));
            }
        }
    }

    private enum h {
        NONE,
        DRAG,
        ZOOM,
        FLING,
        ANIMATE_ZOOM
    }

    private class i {
        public float a;
        public float b;
        public float c;
        public ScaleType d;
        final /* synthetic */ TouchImageView e;

        public i(TouchImageView touchImageView, float f, float f2, float f3, ScaleType scaleType) {
            this.e = touchImageView;
            this.a = f;
            this.b = f2;
            this.c = f3;
            this.d = scaleType;
        }
    }

    public TouchImageView(Context context) {
        super(context);
        a(context);
    }

    public TouchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public TouchImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        super.setClickable(true);
        this.m = context;
        this.A = new ScaleGestureDetector(context, new g());
        this.B = new GestureDetector(context, new d());
        this.e = new Matrix();
        this.f = new Matrix();
        this.l = new float[9];
        this.d = 1.0f;
        if (this.o == null) {
            this.o = ScaleType.FIT_CENTER;
        }
        this.h = 1.0f;
        this.i = 3.0f;
        this.j = 0.75f * this.h;
        this.k = c * this.i;
        setImageMatrix(this.e);
        setScaleType(ScaleType.MATRIX);
        setState(h.NONE);
        this.q = false;
        super.setOnTouchListener(new f());
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.D = onTouchListener;
    }

    public void setOnTouchImageViewListener(e eVar) {
        this.E = eVar;
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        this.C = onDoubleTapListener;
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        a();
        d();
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        a();
        d();
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        a();
        d();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        a();
        d();
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType == ScaleType.FIT_START || scaleType == ScaleType.FIT_END) {
            throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
        } else if (scaleType == ScaleType.MATRIX) {
            super.setScaleType(ScaleType.MATRIX);
        } else {
            this.o = scaleType;
            if (this.q) {
                setZoom(this);
            }
        }
    }

    public ScaleType getScaleType() {
        return this.o;
    }

    public boolean isZoomed() {
        return this.d != 1.0f;
    }

    public RectF getZoomedRect() {
        if (this.o == ScaleType.FIT_XY) {
            throw new UnsupportedOperationException("getZoomedRect() not supported with FIT_XY");
        }
        PointF a = a(0.0f, 0.0f, true);
        PointF a2 = a((float) this.s, (float) this.t, true);
        float intrinsicWidth = (float) getDrawable().getIntrinsicWidth();
        float intrinsicHeight = (float) getDrawable().getIntrinsicHeight();
        return new RectF(a.x / intrinsicWidth, a.y / intrinsicHeight, a2.x / intrinsicWidth, a2.y / intrinsicHeight);
    }

    private void a() {
        if (this.e != null && this.t != 0 && this.s != 0) {
            this.e.getValues(this.l);
            this.f.setValues(this.l);
            this.z = this.x;
            this.y = this.w;
            this.v = this.t;
            this.u = this.s;
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putFloat("saveScale", this.d);
        bundle.putFloat("matchViewHeight", this.x);
        bundle.putFloat("matchViewWidth", this.w);
        bundle.putInt("viewWidth", this.s);
        bundle.putInt("viewHeight", this.t);
        this.e.getValues(this.l);
        bundle.putFloatArray("matrix", this.l);
        bundle.putBoolean("imageRendered", this.p);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.d = bundle.getFloat("saveScale");
            this.l = bundle.getFloatArray("matrix");
            this.f.setValues(this.l);
            this.z = bundle.getFloat("matchViewHeight");
            this.y = bundle.getFloat("matchViewWidth");
            this.v = bundle.getInt("viewHeight");
            this.u = bundle.getInt("viewWidth");
            this.p = bundle.getBoolean("imageRendered");
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void onDraw(Canvas canvas) {
        this.q = true;
        this.p = true;
        if (this.r != null) {
            setZoom(this.r.a, this.r.b, this.r.c, this.r.d);
            this.r = null;
        }
        super.onDraw(canvas);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
    }

    public float getMaxZoom() {
        return this.i;
    }

    public void setMaxZoom(float f) {
        this.i = f;
        this.k = c * this.i;
    }

    public float getMinZoom() {
        return this.h;
    }

    public float getCurrentZoom() {
        return this.d;
    }

    public void setMinZoom(float f) {
        this.h = f;
        this.j = 0.75f * this.h;
    }

    public void resetZoom() {
        this.d = 1.0f;
        d();
    }

    public void setZoom(float f) {
        setZoom(f, dji.pilot.visual.a.d.c, dji.pilot.visual.a.d.c);
    }

    public void setZoom(float f, float f2, float f3) {
        setZoom(f, f2, f3, this.o);
    }

    public void setZoom(float f, float f2, float f3, ScaleType scaleType) {
        if (this.q) {
            if (scaleType != this.o) {
                setScaleType(scaleType);
            }
            resetZoom();
            a((double) f, (float) (this.s / 2), (float) (this.t / 2), true);
            this.e.getValues(this.l);
            this.l[2] = -((getImageWidth() * f2) - (((float) this.s) * dji.pilot.visual.a.d.c));
            this.l[5] = -((getImageHeight() * f3) - (((float) this.t) * dji.pilot.visual.a.d.c));
            this.e.setValues(this.l);
            b();
            setImageMatrix(this.e);
            return;
        }
        this.r = new i(this, f, f2, f3, scaleType);
    }

    public void setZoom(TouchImageView touchImageView) {
        PointF scrollPosition = touchImageView.getScrollPosition();
        setZoom(touchImageView.getCurrentZoom(), scrollPosition.x, scrollPosition.y, touchImageView.getScaleType());
    }

    public PointF getScrollPosition() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        PointF a = a((float) (this.s / 2), (float) (this.t / 2), true);
        a.x /= (float) intrinsicWidth;
        a.y /= (float) intrinsicHeight;
        return a;
    }

    public void setScrollPosition(float f, float f2) {
        setZoom(this.d, f, f2);
    }

    private void b() {
        this.e.getValues(this.l);
        float f = this.l[2];
        float f2 = this.l[5];
        f = a(f, (float) this.s, getImageWidth());
        f2 = a(f2, (float) this.t, getImageHeight());
        if (f != 0.0f || f2 != 0.0f) {
            this.e.postTranslate(f, f2);
        }
    }

    private void c() {
        b();
        this.e.getValues(this.l);
        if (getImageWidth() < ((float) this.s)) {
            this.l[2] = (((float) this.s) - getImageWidth()) / 2.0f;
        }
        if (getImageHeight() < ((float) this.t)) {
            this.l[5] = (((float) this.t) - getImageHeight()) / 2.0f;
        }
        this.e.setValues(this.l);
    }

    private float a(float f, float f2, float f3) {
        float f4;
        float f5;
        if (f3 <= f2) {
            f4 = f2 - f3;
            f5 = 0.0f;
        } else {
            f5 = f2 - f3;
            f4 = 0.0f;
        }
        if (f < f5) {
            return (-f) + f5;
        }
        if (f > f4) {
            return (-f) + f4;
        }
        return 0.0f;
    }

    private float b(float f, float f2, float f3) {
        if (f3 <= f2) {
            return 0.0f;
        }
        return f;
    }

    private float getImageWidth() {
        return this.w * this.d;
    }

    private float getImageHeight() {
        return this.x * this.d;
    }

    protected void onMeasure(int i, int i2) {
        Drawable drawable = getDrawable();
        if (drawable == null || drawable.getIntrinsicWidth() == 0 || drawable.getIntrinsicHeight() == 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        this.s = a(mode, size, intrinsicWidth);
        this.t = a(mode2, size2, intrinsicHeight);
        setMeasuredDimension(this.s, this.t);
        d();
    }

    private void d() {
        Drawable drawable = getDrawable();
        if (drawable != null && drawable.getIntrinsicWidth() != 0 && drawable.getIntrinsicHeight() != 0 && this.e != null && this.f != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            float f = ((float) this.s) / ((float) intrinsicWidth);
            float f2 = ((float) this.t) / ((float) intrinsicHeight);
            switch (AnonymousClass1.a[this.o.ordinal()]) {
                case 1:
                    f2 = 1.0f;
                    f = 1.0f;
                    break;
                case 2:
                    f2 = Math.max(f, f2);
                    f = f2;
                    break;
                case 3:
                    f2 = Math.min(1.0f, Math.min(f, f2));
                    f = f2;
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    throw new UnsupportedOperationException("TouchImageView does not support FIT_START or FIT_END");
            }
            f2 = Math.min(f, f2);
            f = f2;
            float f3 = ((float) this.s) - (((float) intrinsicWidth) * f);
            float f4 = ((float) this.t) - (((float) intrinsicHeight) * f2);
            this.w = ((float) this.s) - f3;
            this.x = ((float) this.t) - f4;
            if (isZoomed() || this.p) {
                if (this.y == 0.0f || this.z == 0.0f) {
                    a();
                }
                this.f.getValues(this.l);
                this.l[0] = (this.w / ((float) intrinsicWidth)) * this.d;
                this.l[4] = (this.x / ((float) intrinsicHeight)) * this.d;
                f = this.l[2];
                float f5 = this.l[5];
                a(2, f, this.d * this.y, getImageWidth(), this.u, this.s, intrinsicWidth);
                a(5, f5, this.z * this.d, getImageHeight(), this.v, this.t, intrinsicHeight);
                this.e.setValues(this.l);
            } else {
                this.e.setScale(f, f2);
                this.e.postTranslate(f3 / 2.0f, f4 / 2.0f);
                this.d = 1.0f;
            }
            b();
            setImageMatrix(this.e);
        }
    }

    private int a(int i, int i2, int i3) {
        switch (i) {
            case Integer.MIN_VALUE:
                return Math.min(i3, i2);
            case 0:
                return i3;
            default:
                return i2;
        }
    }

    private void a(int i, float f, float f2, float f3, int i2, int i3, int i4) {
        if (f3 < ((float) i3)) {
            this.l[i] = (((float) i3) - (((float) i4) * this.l[0])) * dji.pilot.visual.a.d.c;
        } else if (f > 0.0f) {
            this.l[i] = -((f3 - ((float) i3)) * dji.pilot.visual.a.d.c);
        } else {
            this.l[i] = -((((Math.abs(f) + (((float) i2) * dji.pilot.visual.a.d.c)) / f2) * f3) - (((float) i3) * dji.pilot.visual.a.d.c));
        }
    }

    private void setState(h hVar) {
        this.g = hVar;
    }

    public boolean canScrollHorizontallyFroyo(int i) {
        return canScrollHorizontally(i);
    }

    public boolean canScrollHorizontally(int i) {
        this.e.getValues(this.l);
        float f = this.l[2];
        if (getImageWidth() < ((float) this.s)) {
            return false;
        }
        if (f >= -1.0f && i < 0) {
            return false;
        }
        if ((Math.abs(f) + ((float) this.s)) + 1.0f < getImageWidth() || i <= 0) {
            return true;
        }
        return false;
    }

    private void a(double d, float f, float f2, boolean z) {
        float f3;
        float f4;
        if (z) {
            f3 = this.j;
            f4 = this.k;
        } else {
            f3 = this.h;
            f4 = this.i;
        }
        float f5 = this.d;
        this.d = (float) (((double) this.d) * d);
        if (this.d > f4) {
            this.d = f4;
            d = (double) (f4 / f5);
        } else if (this.d < f3) {
            this.d = f3;
            d = (double) (f3 / f5);
        }
        this.e.postScale((float) d, (float) d, f, f2);
        c();
    }

    private PointF a(float f, float f2, boolean z) {
        this.e.getValues(this.l);
        float intrinsicWidth = (float) getDrawable().getIntrinsicWidth();
        float intrinsicHeight = (float) getDrawable().getIntrinsicHeight();
        float f3 = this.l[2];
        float imageWidth = ((f - f3) * intrinsicWidth) / getImageWidth();
        f3 = ((f2 - this.l[5]) * intrinsicHeight) / getImageHeight();
        if (z) {
            imageWidth = Math.min(Math.max(imageWidth, 0.0f), intrinsicWidth);
            f3 = Math.min(Math.max(f3, 0.0f), intrinsicHeight);
        }
        return new PointF(imageWidth, f3);
    }

    private PointF a(float f, float f2) {
        this.e.getValues(this.l);
        float intrinsicWidth = f / ((float) getDrawable().getIntrinsicWidth());
        float intrinsicHeight = f2 / ((float) getDrawable().getIntrinsicHeight());
        return new PointF((intrinsicWidth * getImageWidth()) + this.l[2], (intrinsicHeight * getImageHeight()) + this.l[5]);
    }

    @TargetApi(16)
    private void a(Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            postOnAnimation(runnable);
        } else {
            postDelayed(runnable, 16);
        }
    }

    private void e() {
        float[] fArr = new float[9];
        this.e.getValues(fArr);
        Log.d(a, "Scale: " + fArr[0] + " TransX: " + fArr[2] + " TransY: " + fArr[5]);
    }
}
