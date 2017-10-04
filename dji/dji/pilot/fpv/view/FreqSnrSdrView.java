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
import dji.log.WM220LogUtil;
import dji.logic.c.b;
import dji.midware.data.model.P3.DataOsdGetPushSdrSweepFrequency;
import dji.midware.data.model.P3.DataWifiGetPushSweepFrequency;
import dji.pilot.R;
import dji.pilot.fpv.model.f;
import dji.pilot.publics.c.a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class FreqSnrSdrView extends DJIImageView implements OnGestureListener, OnTouchListener {
    public static boolean a = false;
    public static int b = 12;
    private static final int c = 4;
    private static final int d = 10;
    private static int e = -100;
    private static final int f = (e + 40);
    private static int g = -90;
    private static int h = 32;
    private static final int i = 100;
    private static final int j = 100;
    private static final int k = 40;
    private static final int l = 100;
    private static int m;
    private static int n;
    private static int o = 0;
    private static int p = 0;
    private static int q = 0;
    private int A = 0;
    private int B;
    private float C;
    private int D = 0;
    private boolean E = true;
    private GestureDetector F;
    private String G = null;
    private String H = null;
    private Paint I;
    private Paint J;
    private int K = -12303292;
    private int L;
    private int M;
    private int N;
    private int O;
    private boolean P;
    private int Q;
    private int R = 32;
    private int S = 2;
    private int T = 16;
    private String[] U;
    private int V = 0;
    private int r;
    private int s;
    private float t;
    private int u;
    private int v;
    private int w = 64;
    private SparseIntArray[] x = new SparseIntArray[this.w];
    private int[] y = new int[this.w];
    private int[] z = new int[this.w];

    public FreqSnrSdrView(Context context, AttributeSet attributeSet) {
        int i = 0;
        super(context, attributeSet);
        if (!isInEditMode()) {
            if (a.a) {
                a = false;
                b = 0;
                this.A = b;
            }
            this.F = new GestureDetector(context, this);
            setOnTouchListener(this);
            this.K = context.getResources().getColor(R.color.de);
            this.L = context.getResources().getColor(R.color.dh);
            this.M = context.getResources().getColor(R.color.df);
            this.N = context.getResources().getColor(R.color.dg);
            this.O = context.getResources().getColor(R.color.dd);
            this.Q = context.getResources().getColor(R.color.di);
            clean();
            this.I = new Paint();
            this.I.setAntiAlias(true);
            this.I.setTypeface(DJITextView.DEMI);
            this.J = new Paint();
            this.J.setAntiAlias(true);
            this.J.setColor(this.Q);
            c.a().a(this);
            this.G = context.getString(R.string.freq_snr_channel);
            this.H = context.getString(R.string.freq_snr_rssi);
            this.U = new String[(this.S * this.T)];
            while (i != this.S * this.T) {
                this.U[i] = String.valueOf(i + 1);
                i++;
            }
            if (a) {
                this.P = true;
                h = 8;
                this.A = 12;
            }
        }
    }

    public void onDestroy() {
        c.a().d(this);
    }

    public void onEventBackgroundThread(DataOsdGetPushSdrSweepFrequency dataOsdGetPushSdrSweepFrequency) {
        a(dataOsdGetPushSdrSweepFrequency.getSignalList());
        postInvalidate();
    }

    public void onEventBackgroundThread(DataWifiGetPushSweepFrequency dataWifiGetPushSweepFrequency) {
        int[] iArr = dataWifiGetPushSweepFrequency.get24GRssiList();
        int[] iArr2 = dataWifiGetPushSweepFrequency.get5GRssiList();
        int length = iArr.length + iArr2.length;
        if (length != iArr.length + iArr2.length) {
            WM220LogUtil.LOGI("wifi rssi list lengths are wrong!");
            return;
        }
        int[] iArr3 = new int[length];
        length = 0;
        int i = 0;
        while (length < iArr.length) {
            iArr3[i] = iArr[length] == 0 ? true : iArr[length];
            if (iArr3[i] > 40) {
                iArr3[i] = 40;
            }
            length++;
            i++;
        }
        length = 0;
        while (length < iArr2.length) {
            iArr3[i] = iArr2[length] == 0 ? true : iArr2[length];
            if (iArr3[i] > 40) {
                iArr3[i] = 40;
            }
            length++;
            i++;
        }
        e = 0;
        g = 16;
        this.P = true;
        WM220LogUtil.LOGI("**into get wifi rssi list!");
        a(iArr3);
    }

    public void setIs32Channel(boolean z) {
        if (!a) {
            this.P = z;
            a();
            if (this.P) {
                this.R = 32;
            } else {
                this.R = 64;
            }
        }
    }

    private void a() {
        this.D = 0;
        this.u = 0;
        h = 32;
        this.A = 0;
        clean();
        postInvalidate();
    }

    public void setWorkFreqIndex(int i) {
        this.B = i;
        if (i > (this.R / 2) - 1) {
            d();
        } else {
            c();
        }
    }

    public void setWorkFreqIndex(float f) {
        this.C = f;
    }

    public void clean() {
        for (int i = 0; i < this.y.length; i++) {
            this.y[i] = e;
            this.z[i] = e;
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.I != null) {
            if (this.u == 0) {
                this.u = canvas.getWidth();
                this.v = canvas.getHeight();
                m = (this.v - 40) - 100;
                this.r = m - 40;
                this.s = this.u - 100;
                p = (this.r - (this.r % 4)) / 4;
                o = (this.s - (this.s % (h + 1))) / (h + 1);
                q = o / 2;
                this.t = ((float) this.r) / 40.0f;
                n = (this.s + 100) - 6;
            }
            this.I.setColor(this.K);
            this.I.setStrokeWidth(3.0f);
            canvas.drawLine(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, 40.0f, DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, (float) m, this.I);
            canvas.drawLine((float) n, 40.0f, (float) n, (float) m, this.I);
            canvas.drawLine(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, (float) m, (float) n, (float) m, this.I);
            if (!this.E) {
                this.I.setColor(this.N);
                this.I.setTextSize(48.0f);
                canvas.drawText(getContext().getString(R.string.freq_snr_none), (float) (this.s / 2), (float) ((this.r / 2) + 40), this.I);
            } else if (this.P) {
                a(canvas, this.I, this.D);
                f(canvas, this.I);
                b(canvas, this.I);
                if (!b.getInstance().b()) {
                    d(canvas, this.I);
                }
                e(canvas, this.I);
            } else {
                Log.e(getClass().getName(), "curPageIndex = " + this.D);
                a(canvas, this.I, this.D);
                f(canvas, this.I);
                a(canvas, this.I);
                c(canvas, this.I);
                e(canvas, this.I);
            }
        }
    }

    private void a(Canvas canvas, Paint paint) {
        paint.setColor(this.N);
        paint.setStyle(Style.FILL);
        int i = this.D * h;
        for (int i2 = 1; i2 <= h; i2++) {
            int i3 = i2 + i;
            if (this.R == 32) {
                if ((this.B * 2) + 1 == i3) {
                    paint.setColor(this.O);
                } else if (this.z[i3 - 1] < g) {
                    paint.setColor(this.M);
                } else {
                    paint.setColor(this.N);
                }
            } else if (this.B + 1 == i3) {
                paint.setColor(this.O);
            } else if (this.z[i3 - 1] < g) {
                paint.setColor(this.M);
            } else {
                paint.setColor(this.N);
            }
            paint.setTextSize(3.0f);
            canvas.drawRect(new Rect(((o * i2) + 100) - (q / 2), (int) (((float) m) - (((float) (this.z[i3 - 1] - e)) * this.t)), ((o * i2) + 100) + (q / 2), m), paint);
        }
    }

    private void b(Canvas canvas, Paint paint) {
        paint.setColor(this.N);
        paint.setStyle(Style.FILL);
        int i = (this.D * this.T) + 1;
        for (int i2 = 1; i2 <= this.T && i <= this.z.length; i2++) {
            if (this.B + 1 == i) {
                paint.setColor(this.O);
            } else if (this.z[i - 1] < g) {
                paint.setColor(this.M);
            } else {
                paint.setColor(this.N);
            }
            paint.setTextSize(3.0f);
            canvas.drawRect(new Rect(((((i2 * 2) - 1) * o) + 100) - (q / 2), (int) (((float) m) - (((float) (this.z[i - 1] - e)) * this.t)), ((((i2 * 2) - 1) * o) + 100) + (q / 2), m), paint);
            i++;
        }
    }

    private void c(Canvas canvas, Paint paint) {
        int i = h * this.D;
        for (int i2 = 1; i2 <= h; i2++) {
            int i3 = i2 + i;
            paint.setColor(this.L);
            canvas.drawCircle((float) ((o * i2) + 100), ((float) m) - (((float) (this.y[i3 - 1] - e)) * this.t), 3.0f, paint);
            paint.setTextSize(3.0f);
            if (i2 < h) {
                paint.setStrokeWidth(2.0f);
                canvas.drawLine((float) ((o * i2) + 100), ((float) m) - (((float) (this.y[i3 - 1] - e)) * this.t), (float) (((i2 + 1) * o) + 100), ((float) m) - (((float) (this.y[i3] - e)) * this.t), paint);
            }
        }
    }

    private void d(Canvas canvas, Paint paint) {
        int i = 1;
        int i2 = (this.D * this.T) + 1;
        while (i <= this.T && i2 <= this.y.length) {
            paint.setColor(this.L);
            canvas.drawCircle((float) ((((i * 2) - 1) * o) + 100), ((float) m) - (((float) (this.y[i2 - 1] - e)) * this.t), 3.0f, paint);
            paint.setTextSize(3.0f);
            paint.setStrokeWidth(2.0f);
            if (i < 16) {
                canvas.drawLine((float) ((((i * 2) - 1) * o) + 100), ((float) m) - (((float) (this.y[i2 - 1] - e)) * this.t), (float) ((((i * 2) + 1) * o) + 100), ((float) m) - (((float) (this.y[i2] - e)) * this.t), paint);
            }
            i++;
            i2++;
        }
    }

    public void setXAxisTextValues(String[] strArr) {
        this.U = new String[strArr.length];
        for (int i = 0; i != this.U.length; i++) {
            this.U[i] = strArr[i].substring(0, strArr[i].indexOf(40));
        }
        this.S = (int) Math.ceil(((double) this.U.length) / ((double) this.T));
        postInvalidate();
    }

    private void e(Canvas canvas, Paint paint) {
        canvas.rotate(-90.0f, 98.0f, DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
        paint.setColor(-1);
        paint.setTextSize(30.0f);
        canvas.drawText(this.H, 60.0f, 40.0f, paint);
        canvas.rotate(90.0f, 98.0f, 60.0f);
        canvas.drawText(this.G, (float) ((this.s + 100) - 70), (float) (m + 40), paint);
        Log.e(getClass().getName(), "startIndex = " + this.A);
        int i = (this.D * this.T) + 1;
        for (int i2 = 1; i2 <= this.R && i <= this.U.length; i2++) {
            if (this.R == 32) {
                if (i2 % 2 == 1) {
                    canvas.drawText(this.U[i - 1], (float) (((o * i2) + 100) + 30), (float) (m + 10), paint);
                    if (i2 <= 5) {
                        canvas.drawText(String.valueOf(e + ((i2 - 1) * 10)), 92.0f, (float) ((m - (p * (i2 - 1))) - 35), paint);
                    }
                    i++;
                }
            } else if (i2 % 2 == 1) {
                if (!((i == 17 && this.D == 0) || i == 33)) {
                    canvas.drawText(String.valueOf(i), (float) (((o * i2) + 100) + 30), (float) (m + 10), paint);
                    if (i2 <= 5) {
                        canvas.drawText(String.valueOf(e + ((i2 - 1) * 10)), 92.0f, (float) ((m - (p * (i2 - 1))) - 35), paint);
                    }
                }
                i++;
            }
        }
    }

    public void setMaxPageCount(int i) {
        this.S = i;
    }

    private void a(Canvas canvas, Paint paint, int i) {
        for (int i2 = 0; i2 < this.S; i2++) {
            if (i2 == i) {
                paint.setColor(-1);
            } else {
                paint.setColor(-7829368);
            }
            canvas.drawCircle((float) (((this.u / 2) + (i2 * 30)) + 10), (float) (m + 70), 10.0f, paint);
        }
    }

    private void f(Canvas canvas, Paint paint) {
        int i = 1;
        paint.setStrokeWidth(2.0f);
        for (int i2 = 1; i2 <= 4; i2++) {
            float f = (float) (m - (p * i2));
            paint.setColor(this.K);
            if (i2 == 4) {
                f -= 2.0f;
            }
            canvas.drawLine(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, f, (float) n, f, paint);
        }
        paint.setColor(this.K);
        while (i < h + 2) {
            float f2 = (float) ((o * i) + 100);
            canvas.drawLine(f2, (float) m, f2, 40.0f, paint);
            i++;
        }
    }

    private void a(int[] iArr) {
        if (iArr.length >= this.w || b.getInstance().b()) {
            this.E = true;
            for (int i = 0; i < iArr.length; i++) {
                SparseIntArray sparseIntArray = this.x[i];
                if (sparseIntArray == null) {
                    sparseIntArray = new SparseIntArray(0);
                    this.x[i] = sparseIntArray;
                }
                if (sparseIntArray.size() >= 20) {
                    this.V = 0;
                }
                sparseIntArray.put(this.V, iArr[i]);
                this.z[i] = iArr[i];
            }
            this.V++;
            if (!b.getInstance().b()) {
                b();
            }
            postInvalidate();
        }
    }

    private void b() {
        for (int i = 0; i < this.y.length; i++) {
            SparseIntArray sparseIntArray = this.x[i];
            int size = sparseIntArray.size();
            if (size != 0) {
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    i2 += sparseIntArray.get(i3);
                }
                this.y[i] = (int) ((((float) i2) * 1.0f) / ((float) size));
                if (this.y[i] < e) {
                    this.y[i] = e;
                } else if (this.y[i] > f) {
                    this.y[i] = f;
                }
            }
        }
    }

    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float x = motionEvent.getX();
        float x2 = motionEvent2.getX();
        if (x - x2 > f.a) {
            Log.d(getClass().getName(), "To LEFT(" + x + "," + x2 + ")");
            d();
        } else if (x - x2 < -80.0f) {
            Log.d(getClass().getName(), "To Right(" + x + "," + x2 + ")");
            c();
        }
        return true;
    }

    private void c() {
        if (this.D != 0) {
            this.D--;
            postInvalidate();
        }
    }

    private void d() {
        if (this.D != this.S - 1) {
            this.D++;
            postInvalidate();
        }
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
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
        return true;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.F.onTouchEvent(motionEvent);
    }
}
