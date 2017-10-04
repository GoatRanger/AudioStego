package dji.pilot2.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import dji.pilot2.utils.h;

public class InhaleRelativeLayout extends RelativeLayout {
    private static final int p = 20;
    private float a;
    private float b;
    private h c;
    private Bitmap d;
    private Path e;
    private Path f;
    private PathMeasure g;
    private PathMeasure h;
    private Animation i;
    private float[] j;
    private boolean k;
    private Paint l;
    private PointF[] m;
    private PointF[] n;
    private a o;

    @SuppressLint({"NewApi"})
    public InhaleRelativeLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
    }

    public InhaleRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public InhaleRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public InhaleRelativeLayout(Context context) {
        super(context);
        a();
    }

    private void a() {
        int i = 0;
        this.b = 0.0f;
        this.a = 0.0f;
        this.d = null;
        this.c = new h();
        this.j = new float[2];
        this.l = new Paint();
        this.l.setColor(SupportMenu.CATEGORY_MASK);
        this.k = false;
        this.m = new PointF[21];
        this.n = new PointF[21];
        while (i < this.m.length) {
            this.m[i] = new PointF();
            this.n[i] = new PointF();
            i++;
        }
    }

    public void setEndPosition(float f, float f2) {
        this.a = f;
        this.b = f2;
        this.e = new Path();
        this.f = new Path();
        View childAt = getChildAt(0);
        this.e.moveTo((float) childAt.getLeft(), (float) childAt.getTop());
        this.e.lineTo((float) childAt.getLeft(), (float) childAt.getBottom());
        this.e.cubicTo((float) childAt.getLeft(), ((float) childAt.getBottom()) + ((this.b - ((float) childAt.getBottom())) / 2.0f), this.a, ((float) childAt.getBottom()) + ((this.b - ((float) childAt.getBottom())) / 2.0f), this.a, this.b);
        this.f.moveTo((float) childAt.getRight(), (float) childAt.getTop());
        this.f.lineTo((float) childAt.getRight(), (float) childAt.getBottom());
        this.f.cubicTo((float) childAt.getRight(), ((float) childAt.getBottom()) + ((this.b - ((float) childAt.getBottom())) / 2.0f), this.a, ((float) childAt.getBottom()) + ((this.b - ((float) childAt.getBottom())) / 2.0f), this.a, this.b);
        this.g = new PathMeasure(this.e, false);
        this.h = new PathMeasure(this.f, false);
        this.i = new 1(this);
        this.i.setInterpolator(getContext(), 17432581);
        this.i.setDuration(500);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.k && this.d != null) {
            canvas.drawBitmapMesh(this.d, 20, 20, this.c.a(), 0, null, 0, this.l);
        }
    }

    public void inhale() {
        this.k = true;
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.setDrawingCacheEnabled(true);
            childAt.invalidate();
            this.d = childAt.getDrawingCache();
        }
        if (this.d != null) {
            childAt.setVisibility(4);
            startAnimation(this.i);
        }
    }

    public void setOnInhaleFinishListener(a aVar) {
        this.o = aVar;
    }
}
