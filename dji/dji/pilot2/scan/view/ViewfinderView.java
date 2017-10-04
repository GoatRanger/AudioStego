package dji.pilot2.scan.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.google.a.t;
import dji.midware.util.a.b;
import dji.pilot.R;
import dji.pilot2.scan.a.d;
import java.util.ArrayList;
import java.util.List;

public final class ViewfinderView extends View {
    private static final int[] a = new int[]{0, 64, 128, 192, 255, 192, 128, 64};
    private static final long b = 80;
    private static final int c = 160;
    private static final int d = 20;
    private static final int e = 6;
    private d f;
    private final Paint g = new Paint(1);
    private Bitmap h;
    private final int i;
    private final int j;
    private final int k;
    private final int l;
    private final int m;
    private int n;
    private List<t> o;
    private List<t> p;
    private int q;
    private final int r = 5;
    private boolean s = false;

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = getResources();
        this.i = resources.getColor(R.color.o4);
        this.j = resources.getColor(R.color.gu);
        this.k = resources.getColor(R.color.o3);
        this.l = resources.getColor(R.color.g_);
        this.m = resources.getColor(R.color.ij);
        this.n = 0;
        this.o = new ArrayList(5);
        this.p = null;
    }

    public void setCameraManager(d dVar) {
        this.f = dVar;
    }

    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        if (this.f != null) {
            Rect e = this.f.e();
            Rect f = this.f.f();
            if (e != null && f != null) {
                b(canvas, e);
                c(canvas, e);
                a(canvas, e);
                if (this.h != null) {
                    this.g.setAlpha(c);
                    canvas.drawBitmap(this.h, null, e, this.g);
                    return;
                }
                postInvalidateDelayed(b, e.left - 6, e.top - 6, e.right + 6, e.bottom + 6);
            }
        }
    }

    public void refresh() {
        this.s = true;
        invalidate();
    }

    private void a(Canvas canvas, Rect rect) {
        int width = canvas.getWidth();
        canvas.getHeight();
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.scan_view_rq_location)).getBitmap();
        Paint paint = new Paint();
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(45.0f);
        textPaint.setAntiAlias(true);
        textPaint.setColor(-1);
        textPaint.setTextAlign(Align.CENTER);
        StaticLayout staticLayout = new StaticLayout(getResources().getText(R.string.v2_phantom_fragment_scan_wifi_location_description), textPaint, bitmap.getWidth(), Alignment.ALIGN_NORMAL, b.c, 0.0f, false);
        if (!this.s) {
            canvas.drawBitmap(bitmap, (float) ((width / 2) - (bitmap.getWidth() / 2)), (float) (rect.bottom + 320), paint);
            canvas.translate((float) (width / 2), (float) (rect.bottom + 100));
            staticLayout.draw(canvas);
            canvas.translate(0.0f, 0.0f);
        }
    }

    private void b(Canvas canvas, Rect rect) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        this.g.setColor(this.h != null ? this.j : this.i);
        canvas.drawRect(0.0f, 0.0f, (float) width, (float) rect.top, this.g);
        canvas.drawRect(0.0f, (float) rect.top, (float) rect.left, (float) (rect.bottom + 1), this.g);
        canvas.drawRect((float) (rect.right + 1), (float) rect.top, (float) width, (float) (rect.bottom + 1), this.g);
        canvas.drawRect(0.0f, (float) (rect.bottom + 1), (float) width, (float) height, this.g);
    }

    private void c(Canvas canvas, Rect rect) {
        this.g.setColor(-1);
        this.g.setStrokeWidth(2.0f);
        this.g.setStyle(Style.STROKE);
        canvas.drawRect(rect, this.g);
        this.g.setColor(getResources().getColor(R.color.do));
        this.g.setStyle(Style.FILL);
        canvas.drawRect((float) (rect.left - 15), (float) rect.top, (float) rect.left, (float) (rect.top + 45), this.g);
        canvas.drawRect((float) (rect.left - 15), (float) (rect.top - 15), (float) (rect.left + 45), (float) rect.top, this.g);
        canvas.drawRect((float) rect.right, (float) rect.top, (float) (rect.right + 15), (float) (rect.top + 45), this.g);
        canvas.drawRect((float) (rect.right - 45), (float) (rect.top - 15), (float) (rect.right + 15), (float) rect.top, this.g);
        canvas.drawRect((float) (rect.left - 15), (float) (rect.bottom - 45), (float) rect.left, (float) rect.bottom, this.g);
        canvas.drawRect((float) (rect.left - 15), (float) rect.bottom, (float) (rect.left + 45), (float) (rect.bottom + 15), this.g);
        canvas.drawRect((float) rect.right, (float) (rect.bottom - 45), (float) (rect.right + 15), (float) rect.bottom, this.g);
        canvas.drawRect((float) (rect.right - 45), (float) rect.bottom, (float) (rect.right + 15), (float) (rect.bottom + 15), this.g);
    }

    private void a(Canvas canvas, Rect rect, int i) {
        String string = getResources().getString(R.string.viewfinderview_status_text1);
        String string2 = getResources().getString(R.string.viewfinderview_status_text2);
        this.g.setColor(this.m);
        this.g.setTextSize((float) 45);
        canvas.drawText(string, (float) ((i - ((int) this.g.measureText(string))) / 2), (float) (rect.top - 180), this.g);
        canvas.drawText(string2, (float) ((i - ((int) this.g.measureText(string2))) / 2), (float) ((rect.top - 180) + 60), this.g);
    }

    private void d(Canvas canvas, Rect rect) {
        if (this.q == 0) {
            this.q = rect.top;
        }
        if (this.q >= rect.bottom) {
            this.q = rect.top;
        } else {
            this.q += 5;
        }
        Rect rect2 = new Rect(rect.left, this.q, rect.right, this.q + 30);
    }

    public void drawViewfinder() {
        Bitmap bitmap = this.h;
        this.h = null;
        if (bitmap != null) {
            bitmap.recycle();
        }
        invalidate();
    }

    public void drawResultBitmap(Bitmap bitmap) {
        this.h = bitmap;
        invalidate();
    }

    public void addPossibleResultPoint(t tVar) {
        List list = this.o;
        synchronized (list) {
            list.add(tVar);
            int size = list.size();
            if (size > 20) {
                list.subList(0, size - 10).clear();
            }
        }
    }
}
