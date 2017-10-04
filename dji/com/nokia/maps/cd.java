package com.nokia.maps;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;

public class cd extends v implements fa {
    private final cb a = new cb(this);
    private bx b;

    public cd(Context context) {
        super(context);
        i();
    }

    public cd(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        i();
    }

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

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        setPadding(0, 0, 0, 0);
        super.onLayout(z, i, i2, i3, i4);
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

    public void i() {
        if (this.b == null) {
            getHolder().setFormat(-3);
            this.b = this.a.i();
            setRenderer(this.b);
            setRenderMode(0);
            requestRender();
        }
    }

    public void j() {
        setOnTouchListener(null);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.a.a(motionEvent);
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    public bw getProxy() {
        return this.a;
    }

    public void requestRender() {
        if (this.b != null) {
            super.requestRender();
        }
    }
}
