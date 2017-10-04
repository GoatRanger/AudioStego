package dji.pilot.fpv.inner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.R;
import dji.pilot.fpv.model.f;
import dji.publics.DJIUI.DJITextView;

public class DJISnrView extends View implements OnGestureListener, OnTouchListener {
    private static final int d = 32;
    private static final int e = 32;
    private static final int f = 4;
    private static final int g = 20;
    private static final int h = 0;
    private static final int i = 80;
    private static final int j = 100;
    private static final int k = 100;
    private static final int l = 40;
    private static final int m = 100;
    private static int n;
    private static int o;
    private static int p = 0;
    private static int q = 0;
    private static int r = 0;
    private Paint A;
    private int B;
    private int C;
    private int D;
    private int E;
    int a;
    int b;
    int[] c;
    private int s;
    private int t;
    private float u;
    private int v;
    private int w;
    private GestureDetector x;
    private String y;
    private String z;

    public DJISnrView(Context context) {
        this(context, null);
    }

    public DJISnrView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJISnrView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new int[32];
        this.v = 0;
        this.w = 0;
        this.y = null;
        this.z = null;
        this.B = -12303292;
        if (!isInEditMode()) {
            this.x = new GestureDetector(context, this);
            setOnTouchListener(this);
            this.B = context.getResources().getColor(R.color.de);
            this.C = context.getResources().getColor(R.color.dh);
            this.D = context.getResources().getColor(R.color.df);
            this.E = context.getResources().getColor(R.color.dg);
            clean();
            this.A = new Paint();
            this.A.setAntiAlias(true);
            this.A.setTypeface(DJITextView.DEMI);
            this.y = context.getString(R.string.freq_snr_channel);
            this.z = context.getString(R.string.freq_snr_rssi);
            setWillNotDraw(false);
        }
    }

    public void clean() {
        for (int i = 0; i < this.c.length; i++) {
            this.c[i] = 0;
        }
    }

    public void addRecord(int[] iArr) {
        if (iArr != null && iArr.length >= 32) {
            for (int i = 0; i < 32; i++) {
                this.c[i] = iArr[i];
            }
            postInvalidate();
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.a == 0) {
            this.a = canvas.getWidth();
            this.b = canvas.getHeight();
            n = (this.b - 40) - 100;
            this.s = n - 40;
            this.t = this.a - 100;
            q = (this.s - (this.s % 4)) / 4;
            p = (this.t - (this.t % 33)) / 33;
            r = p / 3;
            this.u = ((float) this.s) / f.a;
            o = (this.t + 100) - 3;
        }
        this.A.setColor(this.B);
        this.A.setStrokeWidth(3.0f);
        canvas.drawLine(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, 40.0f, DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, (float) n, this.A);
        canvas.drawLine((float) o, 40.0f, (float) o, (float) n, this.A);
        canvas.drawLine(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, (float) n, (float) o, (float) n, this.A);
        c(canvas, this.A);
        a(canvas, this.A);
        b(canvas, this.A);
    }

    private void a(Canvas canvas, Paint paint) {
        paint.setColor(this.E);
        paint.setStyle(Style.FILL);
        int i = this.w * 32;
        for (int i2 = 1; i2 <= 32; i2++) {
            int i3 = i2 + i;
            paint.setColor(this.E);
            paint.setTextSize(20.0f);
            canvas.drawRect(new Rect(((p * i2) + 100) - (r / 2), (int) (((float) n) - (((float) (this.c[i3 - 1] + 0)) * this.u)), ((p * i2) + 100) + (r / 2), n), paint);
        }
    }

    private void b(Canvas canvas, Paint paint) {
        canvas.rotate(-90.0f, 98.0f, DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
        paint.setColor(-1);
        paint.setTextSize(30.0f);
        canvas.drawText(this.z, 60.0f, 40.0f, paint);
        canvas.rotate(90.0f, 98.0f, 60.0f);
        canvas.drawText(this.y, (float) ((this.t + 100) - 70), (float) (n + 40), paint);
        for (int i = 1; i <= 32; i++) {
            canvas.drawText(String.valueOf((this.v + i) + (this.w * 32)), (float) (((p * i) + 100) + 30), (float) (n + 10), paint);
            if (i <= 5) {
                canvas.drawText(String.valueOf(((i - 1) * 20) + 0), 92.0f, (float) ((n - (q * (i - 1))) - 35), paint);
            }
        }
    }

    private void a(Canvas canvas, Paint paint, int i) {
        for (int i2 = 0; i2 < 2; i2++) {
            if (i2 == i) {
                paint.setColor(-1);
            } else {
                paint.setColor(-7829368);
            }
            canvas.drawCircle((float) (((this.a / 2) + (i2 * 30)) + 10), (float) (n + 70), 10.0f, paint);
        }
    }

    private void c(Canvas canvas, Paint paint) {
        int i = 1;
        paint.setStrokeWidth(2.0f);
        for (int i2 = 1; i2 <= 4; i2++) {
            float f = (float) (n - (q * i2));
            paint.setColor(this.B);
            if (i2 == 4) {
                f -= 2.0f;
            }
            canvas.drawLine(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, f, (float) o, f, paint);
        }
        paint.setColor(this.B);
        while (i < 33) {
            float f2 = (float) ((p * i) + 100);
            canvas.drawLine(f2, (float) n, f2, 40.0f, paint);
            i++;
        }
    }

    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return true;
    }

    private void a() {
        if (this.w != 0) {
            if (this.w == 0) {
                this.w = 1;
            } else {
                this.w--;
            }
            postInvalidate();
        }
    }

    private void b() {
        if (this.w != 1) {
            if (this.w == 1) {
                this.w = 0;
            } else {
                this.w++;
            }
            postInvalidate();
        }
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return true;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.x.onTouchEvent(motionEvent);
    }
}
