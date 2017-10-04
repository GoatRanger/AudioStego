package dji.pilot.publics.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class DJITitleScrollView extends ScrollView {
    private a a = null;
    private int b = -1;
    private int[] c = null;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = 0;

    public interface a {
        void a(int i, boolean z, int i2);
    }

    public DJITitleScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }

    public void setTitleIndexTops(int[] iArr, int i) {
        this.c = iArr;
        this.d = i;
    }

    public void setOnTitleIndexChangeListener(a aVar) {
        this.a = aVar;
    }
}
