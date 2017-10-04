package lecho.lib.hellocharts.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import lecho.lib.hellocharts.f.a;
import lecho.lib.hellocharts.g.c;
import lecho.lib.hellocharts.model.d;
import lecho.lib.hellocharts.model.e;
import lecho.lib.hellocharts.model.f;
import lecho.lib.hellocharts.model.n;

public class BubbleChartView extends AbstractChartView implements a {
    private static final String m = "BubbleChartView";
    protected d j;
    protected lecho.lib.hellocharts.e.a k;
    protected c l;

    public BubbleChartView(Context context) {
        this(context, null, 0);
    }

    public BubbleChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BubbleChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new lecho.lib.hellocharts.e.d();
        this.l = new c(context, this, this);
        setChartRenderer(this.l);
        setBubbleChartData(d.k());
    }

    public d getBubbleChartData() {
        return this.j;
    }

    public void setBubbleChartData(d dVar) {
        if (lecho.lib.hellocharts.a.a) {
            Log.d(m, "Setting data for BubbleChartView");
        }
        if (dVar == null) {
            this.j = d.k();
        } else {
            this.j = dVar;
        }
        super.a();
    }

    public f getChartData() {
        return this.j;
    }

    public void callTouchListener() {
        n h = this.d.h();
        if (h.b()) {
            this.k.a(h.c(), (e) this.j.m().get(h.c()));
            return;
        }
        this.k.a();
    }

    public lecho.lib.hellocharts.e.a getOnValueTouchListener() {
        return this.k;
    }

    public void setOnValueTouchListener(lecho.lib.hellocharts.e.a aVar) {
        if (aVar != null) {
            this.k = aVar;
        }
    }

    public void removeMargins() {
        this.l.k();
        ViewCompat.postInvalidateOnAnimation(this);
    }
}
