package com.nokia.maps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import com.here.android.mpa.ar.ARController;
import com.here.android.mpa.ar.ARController.OnCameraEnteredListener;
import com.here.android.mpa.ar.ARController.OnCameraExitedListener;
import com.here.android.mpa.common.OnEngineInitListener.Error;
import com.here.android.mpa.mapping.MapGesture.OnGestureListener.OnGestureListenerAdapter;
import com.nokia.maps.ar.a;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressLint({"NewApi"})
public class ai extends u implements fa {
    public a i;
    private cb j;
    private bx k;
    private ah l;
    private boolean m;
    private ARController n;
    private dx o;
    private AtomicBoolean p;
    private AtomicBoolean q;
    private OnGestureListenerAdapter r;

    public ai(Context context) {
        super(context);
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = null;
        this.o = null;
        this.p = new AtomicBoolean(false);
        this.q = new AtomicBoolean(false);
        this.r = new OnGestureListenerAdapter(this) {
            final /* synthetic */ ai a;

            {
                this.a = r1;
            }

            public void onPanStart() {
                this.a.a.b.a(null, null);
            }

            public void onPanEnd() {
                this.a.a.c.a(null, null);
            }

            public void onMultiFingerManipulationStart() {
                this.a.a.b.a(null, null);
            }

            public void onMultiFingerManipulationEnd() {
                this.a.a.c.a(null, null);
            }

            public boolean onTapEvent(PointF pointF) {
                this.a.a.c.a(null, null);
                return false;
            }

            public boolean onDoubleTapEvent(PointF pointF) {
                this.a.a.c.a(null, null);
                return false;
            }

            public boolean onTwoFingerTapEvent(PointF pointF) {
                this.a.a.c.a(null, null);
                return false;
            }

            public boolean onLongPressEvent(PointF pointF) {
                this.a.a.b.a(null, null);
                return false;
            }

            public void onLongPressRelease() {
                this.a.a.c.a(null, null);
            }
        };
        this.i = new a(this) {
            final /* synthetic */ ai a;

            {
                this.a = r1;
            }

            public boolean a(Object obj, Object obj2) {
                this.a.n = null;
                this.a.j = null;
                this.a.l = null;
                this.a.k = null;
                this.a.o = null;
                return false;
            }
        };
        this.j = new cb(this);
        Log.d(h.a, "---- CREATE CompositeTextureView");
    }

    public ai(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = null;
        this.o = null;
        this.p = new AtomicBoolean(false);
        this.q = new AtomicBoolean(false);
        this.r = /* anonymous class already generated */;
        this.i = /* anonymous class already generated */;
        this.j = new cb(this);
        Log.d(h.a, "---- CREATE CompositeTextureView");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean a;
        if (getARGesture() != null) {
            a = getARGesture().a(motionEvent);
        } else {
            a = false;
        }
        if (!this.m || h.k) {
            return this.j != null ? this.j.a(motionEvent) : false;
        } else {
            return a;
        }
    }

    protected void finalize() {
        Log.d(h.a, "---- DELETED CompositeTextureView");
    }

    @SuppressLint({"NewApi"})
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(a(i), b(i2));
    }

    private int a(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        return 400;
    }

    private int b(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        return 400;
    }

    @SuppressLint({"NewApi"})
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        setPadding(0, 0, 0, 0);
        super.onLayout(z, i, i2, i3, i4);
    }

    public bw getProxy() {
        return this.j;
    }

    public boolean g() {
        return true;
    }

    public void i() {
        if (this.j.b() != null) {
            this.n.setMap(this.j.b());
            this.j.c().addOnGestureListener(this.r);
        }
        if (this.k == null && this.b == null) {
            this.k = this.j.i();
            this.b = new i(this.c, b.a(this.n).R());
            this.l = new ah();
            l();
            this.l.c(this.k);
            this.l.c(this.b);
            setRenderer(this.l);
        }
    }

    public void j() {
        setOnTouchListener(null);
    }

    public Bundle k() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        return bundle;
    }

    public void a(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            super.onRestoreInstanceState(((Bundle) parcelable).getParcelable("instanceState"));
        } else {
            super.onRestoreInstanceState(parcelable);
        }
    }

    public ARController a(dx dxVar) {
        if (this.n != null) {
            return this.n;
        }
        this.o = dxVar;
        this.o.c.a(this.i);
        this.n = b.a(new b(this, dxVar));
        this.n.addOnCameraEnteredListener(new OnCameraEnteredListener(this) {
            final /* synthetic */ ai a;

            {
                this.a = r1;
            }

            public void onCameraEntered() {
                this.a.m = true;
            }
        });
        this.n.addOnCameraExitedListener(new OnCameraExitedListener(this) {
            final /* synthetic */ ai a;

            {
                this.a = r1;
            }

            public void onCameraExited() {
                this.a.m = false;
            }
        });
        return this.n;
    }

    public ARController getARController() {
        return this.n;
    }

    void l() {
        if (!this.p.get()) {
            this.p.set(true);
            if (this.l.a(this.k)) {
                Log.d(h.a, "+++ ADD MAP renderer");
            }
            this.j.a(true);
        }
    }

    void m() {
        if (this.p.get()) {
            this.p.set(false);
            if (this.l.b(this.k)) {
                Log.d(h.a, "--- REMOVE MAP renderer");
            }
            this.j.a(false);
        }
    }

    void n() {
        if (!this.q.get()) {
            this.q.set(true);
            if (this.l.a(this.b)) {
                Log.d(h.a, "+++ ADD AR renderer");
            }
        }
    }

    void o() {
        if (this.q.get()) {
            this.q.set(false);
            if (this.l.b(this.b)) {
                Log.d(h.a, "--- REMOVE AR renderer");
            }
        }
    }

    public void p() {
        if (this.b != null) {
            this.b.a(null);
            this.b = null;
        }
    }

    public final i getARRenderer() {
        return this.b;
    }

    public void a(Error error) {
        if (error == Error.NONE) {
            b.a(this.n).R().applicationIsReady();
        }
    }
}
