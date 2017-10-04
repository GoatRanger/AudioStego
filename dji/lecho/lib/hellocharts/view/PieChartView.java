package lecho.lib.hellocharts.view;

import android.content.Context;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import lecho.lib.hellocharts.a;
import lecho.lib.hellocharts.a.j;
import lecho.lib.hellocharts.a.k;
import lecho.lib.hellocharts.e.h;
import lecho.lib.hellocharts.f.e;
import lecho.lib.hellocharts.g.i;
import lecho.lib.hellocharts.model.f;
import lecho.lib.hellocharts.model.l;
import lecho.lib.hellocharts.model.n;
import lecho.lib.hellocharts.model.o;

public class PieChartView extends AbstractChartView implements e {
    private static final String n = "PieChartView";
    protected l j;
    protected lecho.lib.hellocharts.e.l k;
    protected i l;
    protected lecho.lib.hellocharts.a.i m;

    public PieChartView(Context context) {
        this(context, null, 0);
    }

    public PieChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PieChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new h();
        this.l = new i(context, this, this);
        this.c = new lecho.lib.hellocharts.d.e(context, this);
        setChartRenderer(this.l);
        if (VERSION.SDK_INT < 14) {
            this.m = new k(this);
        } else {
            this.m = new j(this);
        }
        setPieChartData(l.k());
    }

    public l getPieChartData() {
        return this.j;
    }

    public void setPieChartData(l lVar) {
        if (a.a) {
            Log.d(n, "Setting data for ColumnChartView");
        }
        if (lVar == null) {
            this.j = l.k();
        } else {
            this.j = lVar;
        }
        super.a();
    }

    public f getChartData() {
        return this.j;
    }

    public void callTouchListener() {
        n h = this.d.h();
        if (h.b()) {
            this.k.a(h.c(), (o) this.j.m().get(h.c()));
            return;
        }
        this.k.a();
    }

    public lecho.lib.hellocharts.e.l getOnValueTouchListener() {
        return this.k;
    }

    public void setOnValueTouchListener(lecho.lib.hellocharts.e.l lVar) {
        if (lVar != null) {
            this.k = lVar;
        }
    }

    public RectF getCircleOval() {
        return this.l.k();
    }

    public void setCircleOval(RectF rectF) {
        this.l.a(rectF);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public int getChartRotation() {
        return this.l.l();
    }

    public void setChartRotation(int i, boolean z) {
        if (z) {
            this.m.a();
            this.m.a((float) this.l.l(), (float) i);
        } else {
            this.l.a(i);
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public boolean isChartRotationEnabled() {
        if (this.c instanceof lecho.lib.hellocharts.d.e) {
            return ((lecho.lib.hellocharts.d.e) this.c).h();
        }
        return false;
    }

    public void setChartRotationEnabled(boolean z) {
        if (this.c instanceof lecho.lib.hellocharts.d.e) {
            ((lecho.lib.hellocharts.d.e) this.c).e(z);
        }
    }

    public o getValueForAngle(int i, n nVar) {
        return this.l.a(i, nVar);
    }

    public float getCircleFillRatio() {
        return this.l.m();
    }

    public void setCircleFillRatio(float f) {
        this.l.a(f);
        ViewCompat.postInvalidateOnAnimation(this);
    }
}
