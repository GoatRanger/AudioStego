package com.amap.api.mapcore.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public abstract class bf extends bc {
    protected float h;
    protected float i;
    protected float j;
    protected float k;
    private final float l;
    private float m;
    private float n;
    private float o;
    private float p;

    public bf(Context context) {
        super(context);
        this.l = (float) ViewConfiguration.get(context).getScaledEdgeSlop();
    }

    protected void b(MotionEvent motionEvent) {
        super.b(motionEvent);
        if (this.c != null) {
            MotionEvent motionEvent2 = this.c;
            int pointerCount = this.c.getPointerCount();
            int pointerCount2 = motionEvent.getPointerCount();
            if (pointerCount2 == 2 && pointerCount2 == pointerCount) {
                this.o = -1.0f;
                this.p = -1.0f;
                float x = motionEvent2.getX(0);
                float y = motionEvent2.getY(0);
                float x2 = motionEvent2.getX(1);
                float y2 = motionEvent2.getY(1) - y;
                this.h = x2 - x;
                this.i = y2;
                y2 = motionEvent.getX(0);
                x = motionEvent.getY(0);
                x = motionEvent.getY(1) - x;
                this.j = motionEvent.getX(1) - y2;
                this.k = x;
            }
        }
    }

    public float c() {
        if (this.o == -1.0f) {
            float f = this.j;
            float f2 = this.k;
            this.o = (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
        }
        return this.o;
    }

    protected static float a(MotionEvent motionEvent, int i) {
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        if (i < motionEvent.getPointerCount()) {
            return rawX + motionEvent.getX(i);
        }
        return 0.0f;
    }

    protected static float b(MotionEvent motionEvent, int i) {
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        if (i < motionEvent.getPointerCount()) {
            return rawY + motionEvent.getY(i);
        }
        return 0.0f;
    }

    protected boolean d(MotionEvent motionEvent) {
        DisplayMetrics displayMetrics = this.a.getResources().getDisplayMetrics();
        this.m = ((float) displayMetrics.widthPixels) - this.l;
        this.n = ((float) displayMetrics.heightPixels) - this.l;
        float f = this.l;
        float f2 = this.m;
        float f3 = this.n;
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        float a = a(motionEvent, 1);
        float b = b(motionEvent, 1);
        boolean z = rawX < f || rawY < f || rawX > f2 || rawY > f3;
        boolean z2;
        if (a < f || b < f || a > f2 || b > f3) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || r2) {
            return true;
        }
        return false;
    }
}
