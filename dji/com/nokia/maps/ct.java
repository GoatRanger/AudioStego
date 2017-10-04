package com.nokia.maps;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.streetlevel.StreetLevelGesture;
import com.here.android.mpa.streetlevel.StreetLevelModel;

public class ct extends v implements db {
    private static final String b = ct.class.getSimpleName();
    private cq a = new cq(ct.class.getName());
    private Context c;
    private da d;

    public ct(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context;
        a();
    }

    private void a() {
        this.d = new da(this.c, this);
        getHolder().setFormat(-3);
        setRenderer(this.d.a());
        setRenderMode(0);
    }

    public db getProxy() {
        return this;
    }

    public void setPanorama(StreetLevelModel streetLevelModel) {
        this.d.a(streetLevelModel);
    }

    public StreetLevelGesture getStreetLevelGesture() {
        return this.d.b();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.d.a(motionEvent);
    }

    public void onPause() {
        super.onPause();
        this.d.d();
    }

    public void onResume() {
        super.onResume();
        this.d.e();
    }

    public void setBlankStreetLevelImageVisible(boolean z) {
        this.d.a(z);
    }

    public StreetLevelModel getPanorama() {
        return this.d.c();
    }

    public void a(OnScreenCaptureListener onScreenCaptureListener) {
        this.d.a(onScreenCaptureListener);
    }
}
