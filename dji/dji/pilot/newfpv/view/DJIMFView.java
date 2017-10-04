package dji.pilot.newfpv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.newfpv.d;
import dji.pilot.newfpv.f.e;
import dji.pilot.newfpv.g;
import dji.pilot.newfpv.h;
import dji.pilot.newfpv.view.b.a;
import dji.thirdparty.a.c;

public class DJIMFView extends View implements h<e> {
    private g a = null;
    private int b = 0;
    private d c = null;

    public DJIMFView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        bindInfo(a.ViewId_MF, e.FPV_FM_SHOW, e.FPV_FM_HIDE);
    }

    public a getViewId() {
        return this.c.b;
    }

    public void bind(g gVar, int i) {
        this.a = gVar;
        this.b = i;
    }

    public void bindInfo(a aVar, e eVar, e eVar2) {
        this.c = new d(this, aVar, eVar, eVar2);
    }

    public void onEventMainThread(e eVar) {
        a.a(e.FPV_FM_SHOW == eVar, this.a, this);
    }

    public d getViewInfo() {
        return this.c;
    }

    public boolean needShow() {
        return 1 == System.currentTimeMillis() % 2;
    }

    public View getSelf() {
        return this;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!c.a().c(this)) {
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
