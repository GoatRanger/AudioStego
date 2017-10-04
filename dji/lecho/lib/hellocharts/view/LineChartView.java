package lecho.lib.hellocharts.view;

import android.content.Context;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import lecho.lib.hellocharts.a;
import lecho.lib.hellocharts.e.g;
import lecho.lib.hellocharts.e.j;
import lecho.lib.hellocharts.f.d;
import lecho.lib.hellocharts.g.h;
import lecho.lib.hellocharts.model.f;
import lecho.lib.hellocharts.model.k;
import lecho.lib.hellocharts.model.m;
import lecho.lib.hellocharts.model.n;

public class LineChartView extends AbstractChartView implements d {
    private static final String l = "LineChartView";
    protected k j;
    protected j k;

    public LineChartView(Context context) {
        this(context, null, 0);
    }

    public LineChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LineChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new g();
        setChartRenderer(new h(context, this, this));
        setLineChartData(k.k());
    }

    public k getLineChartData() {
        return this.j;
    }

    public void setLineChartData(k kVar) {
        if (a.a) {
            Log.d(l, "Setting data for LineChartView");
        }
        if (kVar == null) {
            this.j = k.k();
        } else {
            this.j = kVar;
        }
        super.a();
    }

    public f getChartData() {
        return this.j;
    }

    public void callTouchListener() {
        n h = this.d.h();
        if (h.b()) {
            this.k.a(h.c(), h.d(), (m) ((lecho.lib.hellocharts.model.j) this.j.m().get(h.c())).b().get(h.d()));
            return;
        }
        this.k.a();
    }

    public j getOnValueTouchListener() {
        return this.k;
    }

    public void setOnValueTouchListener(j jVar) {
        if (jVar != null) {
            this.k = jVar;
        }
    }

    public void setLineShader(Shader shader) {
        if (this.d instanceof h) {
            ((h) this.d).a(shader);
        }
    }
}
