package lecho.lib.hellocharts.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import lecho.lib.hellocharts.e.f;
import lecho.lib.hellocharts.f.c;
import lecho.lib.hellocharts.f.d;
import lecho.lib.hellocharts.g.e;
import lecho.lib.hellocharts.g.g;
import lecho.lib.hellocharts.model.h;
import lecho.lib.hellocharts.model.i;
import lecho.lib.hellocharts.model.j;
import lecho.lib.hellocharts.model.k;
import lecho.lib.hellocharts.model.m;
import lecho.lib.hellocharts.model.n;
import lecho.lib.hellocharts.model.p;

public class ComboLineColumnChartView extends AbstractChartView implements c {
    private static final String n = "ComboLineColumnChartView";
    protected i j;
    protected lecho.lib.hellocharts.f.b k;
    protected d l;
    protected lecho.lib.hellocharts.e.c m;

    private class a implements lecho.lib.hellocharts.f.b {
        final /* synthetic */ ComboLineColumnChartView a;

        private a(ComboLineColumnChartView comboLineColumnChartView) {
            this.a = comboLineColumnChartView;
        }

        public h getColumnChartData() {
            return this.a.j.m();
        }

        public void setColumnChartData(h hVar) {
            this.a.j.a(hVar);
        }
    }

    private class b implements d {
        final /* synthetic */ ComboLineColumnChartView a;

        private b(ComboLineColumnChartView comboLineColumnChartView) {
            this.a = comboLineColumnChartView;
        }

        public k getLineChartData() {
            return this.a.j.n();
        }

        public void setLineChartData(k kVar) {
            this.a.j.a(kVar);
        }
    }

    public ComboLineColumnChartView(Context context) {
        this(context, null, 0);
    }

    public ComboLineColumnChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ComboLineColumnChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new a();
        this.l = new b();
        this.m = new f();
        setChartRenderer(new g(context, (a) this, this.k, this.l));
        setComboLineColumnChartData(i.k());
    }

    public i getComboLineColumnChartData() {
        return this.j;
    }

    public void setComboLineColumnChartData(i iVar) {
        if (lecho.lib.hellocharts.a.a) {
            Log.d(n, "Setting data for ComboLineColumnChartView");
        }
        if (iVar == null) {
            this.j = null;
        } else {
            this.j = iVar;
        }
        super.a();
    }

    public lecho.lib.hellocharts.model.f getChartData() {
        return this.j;
    }

    public void callTouchListener() {
        n h = this.d.h();
        if (!h.b()) {
            this.m.a();
        } else if (lecho.lib.hellocharts.model.n.a.COLUMN.equals(h.e())) {
            this.m.a(h.c(), h.d(), (p) ((lecho.lib.hellocharts.model.g) this.j.m().m().get(h.c())).b().get(h.d()));
        } else if (lecho.lib.hellocharts.model.n.a.LINE.equals(h.e())) {
            this.m.a(h.c(), h.d(), (m) ((j) this.j.n().m().get(h.c())).b().get(h.d()));
        } else {
            throw new IllegalArgumentException("Invalid selected value type " + h.e().name());
        }
    }

    public lecho.lib.hellocharts.e.c getOnValueTouchListener() {
        return this.m;
    }

    public void setOnValueTouchListener(lecho.lib.hellocharts.e.c cVar) {
        if (cVar != null) {
            this.m = cVar;
        }
    }

    public void setColumnChartRenderer(Context context, e eVar) {
        setChartRenderer(new g(context, (a) this, eVar, this.l));
    }

    public void setLineChartRenderer(Context context, lecho.lib.hellocharts.g.h hVar) {
        setChartRenderer(new g(context, (a) this, this.k, hVar));
    }
}
