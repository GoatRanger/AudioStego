package lecho.lib.hellocharts.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import lecho.lib.hellocharts.a;
import lecho.lib.hellocharts.b.b;
import lecho.lib.hellocharts.d.f;
import lecho.lib.hellocharts.g.j;
import lecho.lib.hellocharts.model.h;

public class PreviewColumnChartView extends ColumnChartView {
    private static final String k = "ColumnChartView";
    protected j j;

    public PreviewColumnChartView(Context context) {
        this(context, null, 0);
    }

    public PreviewColumnChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PreviewColumnChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new b();
        this.j = new j(context, this, this);
        this.c = new f(context, this);
        setChartRenderer(this.j);
        setColumnChartData(h.k());
    }

    public int getPreviewColor() {
        return this.j.k();
    }

    public void setPreviewColor(int i) {
        if (a.a) {
            Log.d(k, "Changing preview area color");
        }
        this.j.a(i);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public boolean canScrollHorizontally(int i) {
        int computeHorizontalScrollOffset = computeHorizontalScrollOffset();
        int computeHorizontalScrollRange = computeHorizontalScrollRange() - computeHorizontalScrollExtent();
        if (computeHorizontalScrollRange == 0) {
            return false;
        }
        if (i < 0) {
            if (computeHorizontalScrollOffset <= 0) {
                return false;
            }
            return true;
        } else if (computeHorizontalScrollOffset >= computeHorizontalScrollRange - 1) {
            return false;
        } else {
            return true;
        }
    }
}
