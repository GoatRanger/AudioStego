package dji.device.common.view.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;
import dji.pilot.fpv.R;

public class BaseCustomProgressBar extends ProgressBar {
    protected static final int k = 0;
    private static final int l = 10;
    private static final int m = -261935;
    private static final int n = -2894118;
    private static final int o = 20;
    private static final int p = 2;
    private static final int q = 10;
    protected Paint a;
    protected int b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    protected int h;
    protected int i;
    protected boolean j;

    public BaseCustomProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseCustomProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Paint();
        this.b = m;
        this.c = b(10);
        this.d = a(10);
        this.e = a(20);
        this.f = m;
        this.g = n;
        this.h = a(2);
        this.j = true;
        a(attributeSet);
        this.a.setTextSize((float) this.c);
        this.a.setColor(this.b);
    }

    protected synchronized void onMeasure(int i, int i2) {
    }

    private void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.BaseCustomProgressBar);
        this.b = obtainStyledAttributes.getColor(R.styleable.BaseCustomProgressBar_progress_text_color, m);
        this.c = (int) obtainStyledAttributes.getDimension(R.styleable.BaseCustomProgressBar_progress_text_size, (float) this.c);
        this.f = obtainStyledAttributes.getColor(R.styleable.BaseCustomProgressBar_progress_reached_color, this.b);
        this.g = obtainStyledAttributes.getColor(R.styleable.BaseCustomProgressBar_progress_unreached_color, n);
        this.e = (int) obtainStyledAttributes.getDimension(R.styleable.BaseCustomProgressBar_progress_reached_bar_height, (float) this.e);
        this.h = (int) obtainStyledAttributes.getDimension(R.styleable.BaseCustomProgressBar_progress_unreached_bar_height, (float) this.h);
        this.d = (int) obtainStyledAttributes.getDimension(R.styleable.BaseCustomProgressBar_progress_text_offset, (float) this.d);
        if (obtainStyledAttributes.getInt(R.styleable.BaseCustomProgressBar_progress_text_visibility, 0) != 0) {
            this.j = false;
        }
        obtainStyledAttributes.recycle();
    }

    protected synchronized void onDraw(Canvas canvas) {
    }

    protected int a(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
    }

    protected int b(int i) {
        return (int) TypedValue.applyDimension(2, (float) i, getResources().getDisplayMetrics());
    }
}
