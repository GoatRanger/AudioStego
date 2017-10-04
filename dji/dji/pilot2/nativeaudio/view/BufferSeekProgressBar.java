package dji.pilot2.nativeaudio.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import dji.log.DJILogHelper;

@SuppressLint({"NewApi"})
public class BufferSeekProgressBar extends View {
    private static final int a = 5;
    private static final int b = -9342607;
    private static final int c = -5855578;
    private static final int d = -1;
    private static final int e = 100;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private Paint n;
    private a o;
    private RectF p;
    private RectF q;
    private RectF r;
    private RectF s;

    public interface a {
        void a(float f);
    }

    public BufferSeekProgressBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a();
    }

    public BufferSeekProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public BufferSeekProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public BufferSeekProgressBar(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.j = 5;
        this.f = 0;
        this.g = 0;
        this.h = 100;
        this.i = 100;
        this.k = b;
        this.l = c;
        this.m = -1;
        this.n = new Paint(1);
        this.n.setStrokeWidth(0.0f);
        this.p = new RectF();
        this.q = new RectF();
        this.r = new RectF();
        this.s = new RectF();
    }

    public void setStrokeWidth(int i) {
        this.j = this.j;
        invalidate();
    }

    public void setMaxProgress(int i) {
        this.h = i;
        invalidate();
    }

    public void setMaxBufferProgress(int i) {
        this.i = i;
        invalidate();
    }

    public void setProgress(int i) {
        this.f = i;
        invalidate();
    }

    public void setBufferProgress(int i) {
        this.g = i;
        invalidate();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = this.j / 2;
        int width = getWidth();
        int height = getHeight();
        float f = (float) ((height / 2) - i);
        this.p.set(0.0f, f, (float) width, ((float) this.j) + f);
        this.q.set(0.0f, f, (float) (((double) width) * ((1.0d * ((double) this.g)) / ((double) this.i))), ((float) this.j) + f);
        this.r.set(0.0f, f, (float) (((double) width) * ((1.0d * ((double) this.f)) / ((double) this.h))), ((float) this.j) + f);
        float f2 = (float) (((double) (width - this.j)) * ((1.0d * ((double) this.f)) / ((double) this.h)));
        this.s.set(f2, 0.0f, ((float) this.j) + f2, (float) height);
        this.n.setColor(this.k);
        canvas.drawRoundRect(this.p, (float) i, (float) i, this.n);
        this.n.setColor(this.l);
        canvas.drawRoundRect(this.q, (float) i, (float) i, this.n);
        this.n.setColor(this.m);
        canvas.drawRoundRect(this.r, (float) i, (float) i, this.n);
        canvas.drawRoundRect(this.s, (float) i, (float) i, this.n);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float f = 0.0f;
        switch (motionEvent.getAction()) {
            case 0:
            case 1:
            case 2:
                float x = motionEvent.getX();
                DJILogHelper.getInstance().LOGI("Lyric", "event.getX(): " + x);
                if (this.o != null) {
                    if (x >= 0.0f) {
                        f = x > ((float) getWidth()) ? (float) getWidth() : x;
                    }
                    this.o.a(f / ((float) getWidth()));
                }
                return true;
            default:
                return super.onTouchEvent(motionEvent);
        }
    }

    public void setOnSeekProgressCallBack(a aVar) {
        this.o = aVar;
    }
}
