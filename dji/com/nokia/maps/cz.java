package com.nokia.maps;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.streetlevel.StreetLevelGesture;
import com.here.android.mpa.streetlevel.StreetLevelModel;

public class cz extends y implements db {
    private static final String a = cz.class.getSimpleName();
    private Context b;
    private da c;
    private cq d = new cq(cz.class.getName());

    public cz(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = context;
        a();
    }

    private void a() {
        this.c = new da(this.b, this);
        setRenderer(this.c.a());
    }

    public db getProxy() {
        return this;
    }

    public void setPanorama(StreetLevelModel streetLevelModel) {
        this.c.a(streetLevelModel);
    }

    public StreetLevelGesture getStreetLevelGesture() {
        return this.c.b();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.c.a(motionEvent);
    }

    public void onPause() {
        this.c.d();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.c.e();
    }

    public void setBlankStreetLevelImageVisible(boolean z) {
        this.c.a(z);
    }

    public StreetLevelModel getPanorama() {
        return this.c.c();
    }

    public void a(OnScreenCaptureListener onScreenCaptureListener) {
        this.c.a(onScreenCaptureListener);
    }
}
