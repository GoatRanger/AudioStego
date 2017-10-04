package dji.pilot.fpv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import dji.logic.c.b;
import dji.midware.data.model.P3.DataEyeGetPushPreciseLandingEnergy;
import dji.pilot.R;
import dji.pilot.newfpv.d;
import dji.pilot.newfpv.f.f;
import dji.pilot.newfpv.g;
import dji.pilot.newfpv.h;
import dji.pilot.newfpv.view.b.a;
import dji.thirdparty.a.c;

public class DJIPreciseLandingView extends LinearLayout implements h<f> {
    private PreciseLandingProgressEnergyBar a;
    private boolean b = false;
    private g c = null;
    private int d = 0;
    private d e = null;

    public DJIPreciseLandingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        bindInfo(a.ViewID_PreciseLanding, f.SIM_ATTI_SHOW, f.SIM_ATTI_HIDE);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        if (!isInEditMode()) {
            this.a = (PreciseLandingProgressEnergyBar) findViewById(R.id.a9x);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        if (DataEyeGetPushPreciseLandingEnergy.getInstance().isGetted()) {
            onEventMainThread(DataEyeGetPushPreciseLandingEnergy.getInstance());
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!c.a().c(this)) {
            c.a().d(this);
        }
    }

    public void onEventMainThread(DataEyeGetPushPreciseLandingEnergy dataEyeGetPushPreciseLandingEnergy) {
        int i = 100;
        Log.d("CJComment", "onReceived " + dataEyeGetPushPreciseLandingEnergy.getEnery());
        if (dataEyeGetPushPreciseLandingEnergy.getEnery() > 0) {
            setVisibility(0);
            this.b = true;
            PreciseLandingProgressEnergyBar preciseLandingProgressEnergyBar = this.a;
            if (dataEyeGetPushPreciseLandingEnergy.getEnery() * 10 < 100) {
                i = dataEyeGetPushPreciseLandingEnergy.getEnery() * 10;
            }
            preciseLandingProgressEnergyBar.setProgress(i);
            return;
        }
        setVisibility(4);
        this.b = false;
        if (this.a != null) {
            this.a.setProgress(0);
        }
    }

    public void bind(g gVar, int i) {
        this.c = gVar;
        this.d = i;
    }

    public void bindInfo(a aVar, f fVar, f fVar2) {
        this.e = new d(this, aVar, fVar, fVar2);
    }

    public a getViewId() {
        return this.e.b;
    }

    public d getViewInfo() {
        return this.e;
    }

    public boolean needShow() {
        if (b.getInstance().a(null) && this.b) {
            return true;
        }
        return false;
    }

    public View getSelf() {
        return this;
    }
}
