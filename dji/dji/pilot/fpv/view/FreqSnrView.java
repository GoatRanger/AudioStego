package dji.pilot.fpv.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.model.P3.DataOsdGetPushSweepFrequency;
import dji.pilot.R;
import dji.pilot.fpv.model.f;
import dji.pilot.publics.c.a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class FreqSnrView extends DJIImageView implements OnGestureListener, OnTouchListener {
    public static boolean a = true;
    public static int b = 12;
    private static final int c = 4;
    private static final int d = 10;
    private static final int e = -100;
    private static final int f = -60;
    private static int g = 16;
    private static final int h = 100;
    private static final int i = 100;
    private static final int j = 40;
    private static final int k = 100;
    private static int l;
    private static int m;
    private static int n = 0;
    private static int o = 0;
    private static int p = 0;
    private int A;
    private int B = 0;
    private boolean C = true;
    private GestureDetector D;
    private String E = null;
    private String F = null;
    private Paint G;
    private Paint H;
    private int I = -12303292;
    private int J;
    private int K;
    private int L;
    private int M;
    private boolean N;
    private int O;
    private int P = 0;
    private int q;
    private int r;
    private float s;
    private int t;
    private int u;
    private int v = 32;
    private SparseIntArray[] w = new SparseIntArray[this.v];
    private int[] x = new int[this.v];
    private int[] y = new int[this.v];
    private int z = 0;

    public FreqSnrView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            if (a.a) {
                a = false;
                b = 0;
                this.z = b;
            }
            this.D = new GestureDetector(context, this);
            setOnTouchListener(this);
            this.I = context.getResources().getColor(R.color.de);
            this.J = context.getResources().getColor(R.color.dh);
            this.K = context.getResources().getColor(R.color.df);
            this.L = context.getResources().getColor(R.color.dg);
            this.M = context.getResources().getColor(R.color.dd);
            this.O = context.getResources().getColor(R.color.di);
            clean();
            this.G = new Paint();
            this.G.setAntiAlias(true);
            this.G.setTypeface(DJITextView.DEMI);
            this.H = new Paint();
            this.H.setAntiAlias(true);
            this.H.setColor(this.O);
            c.a().a(this);
            this.E = context.getString(R.string.freq_snr_channel);
            this.F = context.getString(R.string.freq_snr_rssi);
            if (a) {
                this.N = true;
                g = 8;
                this.z = 12;
            }
        }
    }

    public void onDestroy() {
        c.a().d(this);
    }

    public void onEventBackgroundThread(DataOsdGetPushSweepFrequency dataOsdGetPushSweepFrequency) {
        a(dataOsdGetPushSweepFrequency.getSignalList());
        postInvalidate();
    }

    public void setIsAuto(boolean z) {
        if (!a) {
            this.N = z;
            a();
        }
    }

    private void a() {
        this.B = 0;
        this.t = 0;
        if (this.N) {
            g = 8;
            this.z = 12;
        } else {
            g = 16;
            this.z = 0;
        }
        postInvalidate();
    }

    public void setWorkFreqIndex(int i) {
        this.A = i;
    }

    public void clean() {
        for (int i = 0; i < this.x.length; i++) {
            this.x[i] = -100;
            this.y[i] = -100;
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.G != null) {
            if (this.t == 0) {
                int i;
                this.t = canvas.getWidth();
                this.u = canvas.getHeight();
                l = (this.u - 40) - 100;
                this.q = l - 40;
                this.r = this.t - 100;
                o = (this.q - (this.q % 4)) / 4;
                n = (this.r - (this.r % (g + 1))) / (g + 1);
                if (this.N) {
                    i = n / 6;
                } else {
                    i = n / 3;
                }
                p = i;
                this.s = ((float) this.q) / 40.0f;
                m = (this.r + 100) - 3;
            }
            this.G.setColor(this.I);
            this.G.setStrokeWidth(3.0f);
            canvas.drawLine(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, 40.0f, DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, (float) l, this.G);
            canvas.drawLine((float) m, 40.0f, (float) m, (float) l, this.G);
            canvas.drawLine(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, (float) l, (float) m, (float) l, this.G);
            if (!this.C) {
                this.G.setColor(this.L);
                this.G.setTextSize(48.0f);
                canvas.drawText(getContext().getString(R.string.freq_snr_none), (float) (this.r / 2), (float) ((this.q / 2) + 40), this.G);
            } else if (this.N) {
                f(canvas, this.G);
                b(canvas, this.G);
                d(canvas, this.G);
                e(canvas, this.G);
            } else {
                a(canvas, this.G, this.B);
                f(canvas, this.G);
                a(canvas, this.G);
                c(canvas, this.G);
                e(canvas, this.G);
                g(canvas, this.H);
            }
        }
    }

    private void a(Canvas canvas, Paint paint) {
        paint.setColor(this.L);
        paint.setStyle(Style.FILL);
        int i = this.B * g;
        for (int i2 = 1; i2 <= g; i2++) {
            int i3 = i2 + i;
            if (this.A + 1 == i3) {
                paint.setColor(this.M);
            } else if (this.y[i3 - 1] < -90) {
                paint.setColor(this.K);
            } else {
                paint.setColor(this.L);
            }
            paint.setTextSize(20.0f);
            canvas.drawRect(new Rect(((n * i2) + 100) - (p / 2), (int) (((float) l) - (((float) (this.y[i3 - 1] + 100)) * this.s)), ((n * i2) + 100) + (p / 2), l), paint);
        }
    }

    private void b(Canvas canvas, Paint paint) {
        paint.setColor(this.L);
        paint.setStyle(Style.FILL);
        for (int i = 1; i <= g; i++) {
            if ((this.A + 1) - this.z == i) {
                paint.setColor(this.M);
            } else if (this.y[(i - 1) + this.z] < -90) {
                paint.setColor(this.K);
            } else {
                paint.setColor(this.L);
            }
            paint.setTextSize(20.0f);
            canvas.drawRect(new Rect(((n * i) + 100) - (p / 2), (int) (((float) l) - (((float) (this.y[(i - 1) + this.z] + 100)) * this.s)), ((n * i) + 100) + (p / 2), l), paint);
        }
    }

    private void c(Canvas canvas, Paint paint) {
        int i = g * this.B;
        for (int i2 = 1; i2 <= g; i2++) {
            int i3 = i2 + i;
            paint.setColor(this.J);
            canvas.drawCircle((float) ((n * i2) + 100), ((float) l) - (((float) (this.x[i3 - 1] + 100)) * this.s), 5.0f, paint);
            paint.setTextSize(20.0f);
            if (i2 < g) {
                paint.setStrokeWidth(5.0f);
                canvas.drawLine((float) ((n * i2) + 100), ((float) l) - (((float) (this.x[i3 - 1] + 100)) * this.s), (float) (((i2 + 1) * n) + 100), ((float) l) - (((float) (this.x[i3] + 100)) * this.s), paint);
            }
        }
    }

    private void d(Canvas canvas, Paint paint) {
        for (int i = 1; i <= g; i++) {
            paint.setColor(this.J);
            canvas.drawCircle((float) ((n * i) + 100), ((float) l) - (((float) (this.x[(i - 1) + this.z] + 100)) * this.s), 5.0f, paint);
            paint.setTextSize(20.0f);
            if (i < g) {
                paint.setStrokeWidth(5.0f);
                canvas.drawLine((float) ((n * i) + 100), ((float) l) - (((float) (this.x[(i - 1) + this.z] + 100)) * this.s), (float) (((i + 1) * n) + 100), ((float) l) - (((float) (this.x[this.z + i] + 100)) * this.s), paint);
            }
        }
    }

    private void e(Canvas canvas, Paint paint) {
        canvas.rotate(-90.0f, 98.0f, DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
        paint.setColor(-1);
        paint.setTextSize(30.0f);
        canvas.drawText(this.F, 60.0f, 40.0f, paint);
        canvas.rotate(90.0f, 98.0f, 60.0f);
        canvas.drawText(this.E, (float) ((this.r + 100) - 70), (float) (l + 40), paint);
        for (int i = 1; i <= g; i++) {
            canvas.drawText(String.valueOf((this.z + i) + (g * this.B)), (float) (((n * i) + 100) + 30), (float) (l + 10), paint);
            if (i <= 5) {
                canvas.drawText(String.valueOf(((i - 1) * 10) - 100), 92.0f, (float) ((l - (o * (i - 1))) - 35), paint);
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
            canvas.drawCircle((float) (((this.t / 2) + (i2 * 30)) + 10), (float) (l + 70), 10.0f, paint);
        }
    }

    private void f(Canvas canvas, Paint paint) {
        int i = 1;
        paint.setStrokeWidth(2.0f);
        for (int i2 = 1; i2 <= 4; i2++) {
            float f = (float) (l - (o * i2));
            paint.setColor(this.I);
            if (i2 == 4) {
                f -= 2.0f;
            }
            canvas.drawLine(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, f, (float) m, f, paint);
        }
        paint.setColor(this.I);
        while (i < g + 1) {
            float f2 = (float) ((n * i) + 100);
            canvas.drawLine(f2, (float) l, f2, 40.0f, paint);
            i++;
        }
    }

    private void g(Canvas canvas, Paint paint) {
        int i;
        int i2;
        if (this.B == 0) {
            i = 13;
            i2 = 17;
        } else {
            i2 = 4;
            i = 0;
        }
        canvas.drawRect(new Rect(((i + 2) * n) - 4, 0, ((i2 + 2) * n) - 4, l - 40), paint);
    }

    private void a(int[] iArr) {
        if (iArr.length >= 32) {
            this.C = true;
            for (int i = 0; i < iArr.length; i++) {
                SparseIntArray sparseIntArray = this.w[i];
                if (sparseIntArray == null) {
                    sparseIntArray = new SparseIntArray(0);
                    this.w[i] = sparseIntArray;
                }
                if (sparseIntArray.size() >= 20) {
                    this.P = 0;
                }
                sparseIntArray.put(this.P, iArr[i]);
                this.y[i] = iArr[i];
            }
            this.P++;
            b();
            postInvalidate();
        }
    }

    private void b() {
        for (int i = 0; i < this.x.length; i++) {
            SparseIntArray sparseIntArray = this.w[i];
            int size = sparseIntArray.size();
            if (size != 0) {
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    i2 += sparseIntArray.get(i3);
                }
                this.x[i] = (int) ((((float) i2) * 1.0f) / ((float) size));
                if (this.x[i] < -100) {
                    this.x[i] = -100;
                } else if (this.x[i] > f) {
                    this.x[i] = f;
                }
            }
        }
    }

    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!this.N) {
            float x = motionEvent.getX();
            float x2 = motionEvent2.getX();
            if (x - x2 > f.a) {
                Log.d(getClass().getName(), "To LEFT(" + x + "," + x2 + ")");
                d();
            } else if (x - x2 < -80.0f) {
                Log.d(getClass().getName(), "To Right(" + x + "," + x2 + ")");
                c();
            }
        }
        return true;
    }

    private void c() {
        if (this.B != 0) {
            if (this.B == 0) {
                this.B = 1;
            } else {
                this.B--;
            }
            postInvalidate();
        }
    }

    private void d() {
        if (this.B != 1) {
            if (this.B == 1) {
                this.B = 0;
            } else {
                this.B++;
            }
            postInvalidate();
        }
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!this.N) {
            float x = motionEvent.getX();
            float x2 = motionEvent2.getX();
            Log.d(getClass().getName(), "distanceX=" + f + " d2=" + (x2 - x));
            if (x2 - x < -80.0f) {
                Log.d(getClass().getName(), "To LEFT");
                d();
            } else if (x2 - x > f.a) {
                Log.d(getClass().getName(), "To Right");
                c();
            }
        }
        return true;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.D.onTouchEvent(motionEvent);
    }
}
