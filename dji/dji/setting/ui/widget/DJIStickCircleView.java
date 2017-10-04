package dji.setting.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.setting.ui.R;

public class DJIStickCircleView extends View {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private Paint i;
    private Paint j;
    private Paint k;
    private Paint l;
    private int m;
    private int n;
    private int o;
    private int p;

    public DJIStickCircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 50;
        this.b = 60;
        this.c = 10;
        this.d = 3;
        this.e = 12;
        this.f = 50;
        this.g = Color.parseColor("#727272");
        this.h = Color.parseColor("#00d8ff");
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.a = (int) getResources().getDimension(R.dimen.dp_5);
        this.b = (int) getResources().getDimension(R.dimen.dp_9);
        this.c = (int) getResources().getDimension(R.dimen.dp_7);
        this.d = (int) getResources().getDimension(R.dimen.dp_2);
        this.f = (int) getResources().getDimension(R.dimen.dp_30);
        this.e = (int) getResources().getDimension(R.dimen.setting_ui_txt_small);
        this.i = new Paint();
        this.i.setStyle(Style.FILL);
        this.i.setAntiAlias(true);
        this.i.setColor(this.h);
        this.i.setStrokeWidth(0.0f);
        this.j = new Paint();
        this.j.setStyle(Style.STROKE);
        this.j.setAntiAlias(true);
        this.j.setColor(this.h);
        this.j.setStrokeWidth(1.0f);
        this.k = new Paint();
        this.k.setStyle(Style.FILL);
        this.k.setAntiAlias(true);
        this.k.setColor(this.g);
        this.k.setStrokeWidth(0.0f);
        this.l = new Paint(1);
        this.l.setStrokeWidth(0.0f);
        this.l.setTextSize((float) this.e);
        this.l.setColor(-1);
        this.l.setTextAlign(Align.CENTER);
    }

    public void setValue(int i, int i2, int i3, int i4) {
        this.m = i;
        this.n = i2;
        this.o = i3;
        this.p = i4;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        a(canvas);
        canvas.save();
        canvas.translate((float) this.f, (float) this.f);
        b(canvas);
        canvas.restore();
        c(canvas);
    }

    private void a(Canvas canvas) {
        int width = getWidth();
        canvas.drawCircle((float) (width / 2), (float) (width / 2), (float) this.a, this.i);
        canvas.drawCircle((float) (width / 2), (float) (width / 2), (float) this.b, this.j);
    }

    private void b(Canvas canvas) {
        int width = getWidth() - (this.f * 2);
        int i = ((width / 2) - this.b) - (this.c * 2);
        a(canvas, width, i, 100, this.k);
        b(canvas, width, i, 100, this.k);
        c(canvas, width, i, 100, this.k);
        d(canvas, width, i, 100, this.k);
        a(canvas, width, i, this.m, this.i);
        b(canvas, width, i, this.o, this.i);
        c(canvas, width, i, this.n, this.i);
        d(canvas, width, i, this.p, this.i);
    }

    private void a(Canvas canvas, int i, int i2, int i3, Paint paint) {
        canvas.drawRect(new Rect((((100 - i3) * i2) / 100) + this.c, (i - this.d) / 2, this.c + i2, ((i - this.d) / 2) + this.d), paint);
    }

    private void b(Canvas canvas, int i, int i2, int i3, Paint paint) {
        canvas.drawRect(new Rect((i - this.c) - i2, (i - this.d) / 2, (i - this.c) - (((100 - i3) * i2) / 100), ((i - this.d) / 2) + this.d), paint);
    }

    private void c(Canvas canvas, int i, int i2, int i3, Paint paint) {
        canvas.drawRect(new Rect((i - this.d) / 2, (((100 - i3) * i2) / 100) + this.c, ((i - this.d) / 2) + this.d, this.c + i2), paint);
    }

    private void d(Canvas canvas, int i, int i2, int i3, Paint paint) {
        canvas.drawRect(new Rect((i - this.d) / 2, (i - this.c) - i2, ((i - this.d) / 2) + this.d, (i - this.c) - (((100 - i3) * i2) / 100)), paint);
    }

    private void c(Canvas canvas) {
        int width = getWidth();
        a(canvas, this.m + "%", new Rect(0, 0, this.f, width));
        a(canvas, this.o + "%", new Rect(width - this.f, 0, width, width));
        a(canvas, this.n + "%", new Rect(0, 0, width, this.f));
        a(canvas, this.p + "%", new Rect(0, width - this.f, width, width));
    }

    private void a(Canvas canvas, String str, Rect rect) {
        FontMetricsInt fontMetricsInt = this.l.getFontMetricsInt();
        canvas.drawText(str, (float) rect.centerX(), (float) ((((rect.bottom + rect.top) - fontMetricsInt.bottom) - fontMetricsInt.top) / 2), this.l);
    }
}
