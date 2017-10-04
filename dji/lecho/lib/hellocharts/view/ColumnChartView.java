package lecho.lib.hellocharts.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import lecho.lib.hellocharts.a;
import lecho.lib.hellocharts.e.e;
import lecho.lib.hellocharts.f.b;
import lecho.lib.hellocharts.model.g;
import lecho.lib.hellocharts.model.h;
import lecho.lib.hellocharts.model.n;
import lecho.lib.hellocharts.model.p;

public class ColumnChartView extends AbstractChartView implements b {
    private static final String j = "ColumnChartView";
    private h k;
    private lecho.lib.hellocharts.e.b l;

    public ColumnChartView(Context context) {
        this(context, null, 0);
    }

    public ColumnChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ColumnChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = new e();
        setChartRenderer(new lecho.lib.hellocharts.g.e(context, this, this));
        setColumnChartData(h.k());
    }

    public h getColumnChartData() {
        return this.k;
    }

    public void setColumnChartData(h hVar) {
        if (a.a) {
            Log.d(j, "Setting data for ColumnChartView");
        }
        if (hVar == null) {
            this.k = h.k();
        } else {
            this.k = hVar;
        }
        super.a();
    }

    public h getChartData() {
        return this.k;
    }

    public void callTouchListener() {
        n h = this.d.h();
        if (h.b()) {
            this.l.a(h.c(), h.d(), (p) ((g) this.k.m().get(h.c())).b().get(h.d()));
            return;
        }
        this.l.a();
    }

    public lecho.lib.hellocharts.e.b getOnValueTouchListener() {
        return this.l;
    }

    public void setOnValueTouchListener(lecho.lib.hellocharts.e.b bVar) {
        if (bVar != null) {
            this.l = bVar;
        }
    }
}
