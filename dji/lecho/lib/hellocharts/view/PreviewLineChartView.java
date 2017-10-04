package lecho.lib.hellocharts.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import lecho.lib.hellocharts.a;
import lecho.lib.hellocharts.b.b;
import lecho.lib.hellocharts.d.f;
import lecho.lib.hellocharts.g.k;

public class PreviewLineChartView extends LineChartView {
    private static final String m = "PreviewLineChartView";
    protected k l;

    public PreviewLineChartView(Context context) {
        this(context, null, 0);
    }

    public PreviewLineChartView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PreviewLineChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new b();
        this.l = new k(context, this, this);
        this.c = new f(context, this);
        setChartRenderer(this.l);
        setLineChartData(lecho.lib.hellocharts.model.k.k());
    }

    public int getPreviewColor() {
        return this.l.k();
    }

    public void setPreviewColor(int i) {
        if (a.a) {
            Log.d(m, "Changing preview area color");
        }
        this.l.a(i);
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
