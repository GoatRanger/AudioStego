package dji.setting.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;

public class JoyStickDashedSquare extends View {
    private static final int r = Color.parseColor("#00d8ff");
    private static final int s = Color.parseColor("#727272");
    private static final int t = 40;
    private static final int u = 100;
    Context a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private RectF j;
    private Bitmap k;
    private Canvas l;
    private Paint m;
    private Paint n;
    private Paint o;
    private Paint p;
    private int q = -1;

    public JoyStickDashedSquare(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        this.j = new RectF();
        this.m = new Paint(4);
        this.n = new Paint();
        this.n.setAntiAlias(true);
        this.n.setDither(true);
        this.n.setColor(s);
        this.n.setStyle(Style.STROKE);
        this.n.setPathEffect(new DashPathEffect(new float[]{7.0f, 7.0f}, 0.0f));
        this.n.setStrokeWidth(8.0f);
        this.o = new Paint();
        this.o.setAntiAlias(true);
        this.o.setDither(true);
        this.o.setColor(r);
        this.o.setStyle(Style.STROKE);
        this.o.setStrokeWidth(10.0f);
        this.p = new Paint(1);
        this.p.setStrokeWidth(0.0f);
        this.p.setTextSize(40.0f);
        this.p.setColor(-1);
        this.p.setTextAlign(Align.CENTER);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.k = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
        this.l = new Canvas(this.k);
        this.b = i;
        this.c = i2;
        a();
        this.d = this.b / 2;
        this.e = this.c / 2;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.k, 0.0f, 0.0f, this.m);
        canvas.drawCircle((float) this.d, (float) this.e, 50.0f, this.o);
        a(canvas);
    }

    private void a(Canvas canvas) {
        int width = getWidth();
        a(canvas, this.h + "%", new Rect(0, 0, 100, width));
        a(canvas, this.i + "%", new Rect(width - 100, 0, width, width));
        a(canvas, this.f + "%", new Rect(0, 0, width, 100));
        a(canvas, this.g + "%", new Rect(0, width - 100, width, width));
    }

    private void a(Canvas canvas, String str, Rect rect) {
        FontMetricsInt fontMetricsInt = this.p.getFontMetricsInt();
        canvas.drawText(str, (float) rect.centerX(), (float) ((((rect.bottom + rect.top) - fontMetricsInt.bottom) - fontMetricsInt.top) / 2), this.p);
    }

    private void a(int i, int i2, int i3, int i4, int i5, Canvas canvas) {
        float f = (1.0f * ((float) (i - 200))) / ((float) i4);
        float f2 = (1.0f * ((float) (i2 - 200))) / ((float) i4);
        DJILogHelper.getInstance().LOGD("TESTING", "Ready to Draw", true, true);
        switch (i5) {
            case 0:
                b(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, i5, i3, f, f2, false);
                return;
            case 1:
                b((float) (i - 100), DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, i5, i3, f, f2, true);
                return;
            case 2:
                a((float) (i - 100), (float) (i2 - 100), i5, i3, f, f2, false);
                return;
            case 3:
                a(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, (float) (i2 - 100), i5, i3, f, f2, true);
                return;
            default:
                return;
        }
    }

    private void a(float f, float f2, int i, int i2, float f3, float f4, boolean z) {
        int i3;
        int i4;
        if (z) {
            i3 = 1;
            i4 = 0;
        } else {
            i3 = 0;
            i4 = 1;
        }
        for (int i5 = 0; i5 < this.q; i5++) {
            int pow = (int) Math.pow(2.0d, (double) i5);
            if ((i2 & pow) == pow) {
                this.l.drawLine(f - ((((float) i5) * f3) * ((float) i4)), f2 - ((((float) i5) * f4) * ((float) i3)), f - ((((float) (i5 + 1)) * f4) * ((float) i4)), f2 - ((((float) (i5 + 1)) * f4) * ((float) i3)), this.o);
            }
        }
        invalidate();
    }

    private void b(float f, float f2, int i, int i2, float f3, float f4, boolean z) {
        int i3;
        int i4;
        if (z) {
            i3 = 1;
            i4 = 0;
        } else {
            i3 = 0;
            i4 = 1;
        }
        for (int i5 = 0; i5 < this.q; i5++) {
            int pow = (int) Math.pow(2.0d, (double) i5);
            if ((i2 & pow) == pow) {
                this.l.drawLine(((((float) i5) * f3) * ((float) i4)) + f, ((((float) i5) * f4) * ((float) i3)) + f2, ((((float) (i5 + 1)) * f4) * ((float) i4)) + f, ((((float) (i5 + 1)) * f4) * ((float) i3)) + f2, this.o);
            }
        }
        invalidate();
    }

    public void drawCalibration(int i, int i2) {
        a(this.b, this.c, i, this.q, i2, this.l);
    }

    public void setCircleCenter(int i, int i2, int i3, int i4) {
        this.d = ((this.b / 2) - (((this.b - 200) * i) / 200)) + (((this.b - 200) * i3) / 200);
        this.e = ((this.c / 2) - (((this.c - 200) * i2) / 200)) + (((this.c - 200) * i4) / 200);
        this.h = i;
        this.f = i2;
        this.i = i3;
        this.g = i4;
        invalidate();
    }

    public void reset() {
        this.l.drawRect(this.j, this.n);
        this.l.drawColor(0, Mode.CLEAR);
        a();
        invalidate();
    }

    public boolean hasSegNumSet() {
        return this.q != -1;
    }

    public void setSegmentNum(int i) {
        this.q = i;
    }

    private void a() {
        int[] iArr = new int[]{100, 100};
        int[] iArr2 = new int[]{this.b - 100, 100};
        int[] iArr3 = new int[]{this.b / 2, 100};
        int[] iArr4 = new int[]{100, this.c / 2};
        int[] iArr5 = new int[]{this.b - 100, this.c / 2};
        int[] iArr6 = new int[]{100, this.c - 100};
        int[] iArr7 = new int[]{this.b - 100, this.c - 100};
        int[] iArr8 = new int[]{this.b / 2, this.c - 100};
        this.l.drawLine((float) iArr[0], (float) iArr[1], (float) iArr7[0], (float) iArr7[1], this.n);
        this.l.drawLine((float) iArr3[0], (float) iArr3[1], (float) iArr8[0], (float) iArr8[1], this.n);
        this.l.drawLine((float) iArr2[0], (float) iArr2[1], (float) iArr6[0], (float) iArr6[1], this.n);
        this.l.drawLine((float) iArr4[0], (float) iArr4[1], (float) iArr5[0], (float) iArr5[1], this.n);
        this.j.set(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, (float) (this.b - 100), (float) (this.c - 100));
        this.l.drawRect(this.j, this.n);
    }
}
