package dji.setting.ui.rc;

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
import dji.apppublic.reflect.AppPublicReflect;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.model.P3.DataOsdGetPushSweepFrequency;
import dji.pilot.fpv.model.f;
import dji.pilot.setting.ui.R;
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
    private int A = 0;
    private int B;
    private int C = 0;
    private boolean D = true;
    private GestureDetector E;
    private String F = null;
    private String G = null;
    private Paint H;
    private Paint I;
    private int J = -12303292;
    private int K;
    private int L;
    private int M;
    private int N;
    private boolean O;
    private int P;
    private int Q = 0;
    private int R = 0;
    private int q;
    private int r = -1;
    private float s;
    private int t;
    private int u;
    private int v = 32;
    private int w = 20;
    private SparseIntArray[] x = new SparseIntArray[this.v];
    private int[] y = new int[this.v];
    private int[] z = new int[this.v];

    public FreqSnrView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            if (AppPublicReflect.isOpenAllChanel()) {
                a = false;
                b = 0;
                this.A = b;
            }
            this.w = (int) getResources().getDimension(R.dimen.setting_ui_txt_smaller);
            this.E = new GestureDetector(context, this);
            setOnTouchListener(this);
            this.J = context.getResources().getColor(R.color.setting_ui_hd_fq_gray);
            this.K = context.getResources().getColor(R.color.setting_ui_hd_fq_white);
            this.L = context.getResources().getColor(R.color.setting_ui_hd_fq_green);
            this.M = context.getResources().getColor(R.color.setting_ui_hd_fq_red);
            this.N = context.getResources().getColor(R.color.setting_ui_hd_fq_blue);
            this.P = context.getResources().getColor(R.color.setting_ui_hd_fq_white_alpha);
            clean();
            this.H = new Paint();
            this.H.setAntiAlias(true);
            this.H.setTypeface(DJITextView.DEMI);
            this.I = new Paint();
            this.I.setAntiAlias(true);
            this.I.setColor(this.P);
            b();
            this.F = context.getString(R.string.setting_ui_hd_freq_snr_channel);
            this.G = context.getString(R.string.setting_ui_hd_freq_snr_rssi);
            a();
        }
    }

    private void a() {
        if (a) {
            this.O = true;
            g = 8;
            this.A = 12;
            return;
        }
        this.O = false;
        g = 16;
        this.A = 0;
    }

    public void configOfdmFreqMode(int i) {
        if (i == 0) {
            this.Q = 0;
            if (AppPublicReflect.isOpenAllChanel()) {
                a = false;
                b = 0;
                this.A = b;
            } else {
                a = true;
                b = 12;
                this.A = b;
            }
            a();
        } else if (i == 2) {
            this.Q = 2;
            a = false;
            b = 0;
            this.A = b;
            this.O = false;
            g = 16;
        }
        if (this.r != -1) {
            n = (this.r - (this.r % (g + 1))) / (g + 1);
        }
        this.C = 0;
    }

    private void b() {
        if (!c.a().c(this)) {
            c.a().a(this);
        }
    }

    private void c() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            b();
        } else {
            c();
        }
    }

    public void onDestroy() {
        c();
    }

    public void onEventBackgroundThread(DataOsdGetPushSweepFrequency dataOsdGetPushSweepFrequency) {
        a(dataOsdGetPushSweepFrequency.getSignalList());
        postInvalidate();
    }

    public void setIsAuto(boolean z) {
        if (!a) {
            this.O = z;
            d();
        }
    }

    private void d() {
        this.C = 0;
        this.t = 0;
        if (this.O) {
            g = 8;
            this.A = 12;
        } else {
            g = 16;
            this.A = 0;
        }
        postInvalidate();
    }

    public void setWorkFreqIndex(int i) {
        this.B = i;
    }

    public void clean() {
        for (int i = 0; i < this.y.length; i++) {
            this.y[i] = -100;
            this.z[i] = -100;
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.H != null) {
            if (this.t == 0) {
                this.t = canvas.getWidth();
                this.u = canvas.getHeight();
                l = (this.u - 40) - 100;
                this.q = l - 40;
                this.r = this.t - 100;
                o = (this.q - (this.q % 4)) / 4;
                n = (this.r - (this.r % (g + 1))) / (g + 1);
                p = (int) getResources().getDimension(R.dimen.dp_2);
                this.s = ((float) this.q) / 40.0f;
                m = (this.r + 100) - 3;
            }
            this.H.setColor(this.J);
            this.H.setStrokeWidth(3.0f);
            if (!this.D) {
                this.H.setColor(this.M);
                this.H.setTextSize(48.0f);
                canvas.drawText(getContext().getString(R.string.setting_ui_hd_freq_snr_none), (float) (this.r / 2), (float) ((this.q / 2) + 40), this.H);
            } else if (this.O) {
                b(canvas, this.H);
                e(canvas, this.H);
            } else {
                a(canvas, this.H, this.C);
                a(canvas, this.H);
                e(canvas, this.H);
            }
        }
    }

    private void a(Canvas canvas, Paint paint) {
        paint.setColor(this.M);
        paint.setStyle(Style.FILL);
        int i = this.C * g;
        for (int i2 = 1; i2 <= g; i2++) {
            int i3 = i2 + i;
            if (this.B + 1 == i3) {
                paint.setColor(this.N);
            } else if (this.z[i3 - 1] < -90) {
                paint.setColor(this.L);
            } else {
                paint.setColor(this.M);
            }
            paint.setTextSize((float) this.w);
            if (this.Q == 2 && i3 > 29) {
                this.z[i3 - 1] = -100;
            }
            canvas.drawRect(new Rect(((n * i2) + 100) - (p / 2), (int) (((float) l) - (((float) (this.z[i3 - 1] + 100)) * this.s)), ((n * i2) + 100) + (p / 2), l), paint);
        }
    }

    private void b(Canvas canvas, Paint paint) {
        paint.setColor(this.M);
        paint.setStyle(Style.FILL);
        for (int i = 1; i <= g; i++) {
            if ((this.B + 1) - this.A == i) {
                paint.setColor(this.N);
            } else if (this.z[(i - 1) + this.A] < -90) {
                paint.setColor(this.L);
            } else {
                paint.setColor(this.M);
            }
            paint.setTextSize((float) this.w);
            int i2 = (i - 1) + this.A;
            if (this.Q == 2 && i2 >= 29) {
                this.z[i2] = -100;
            }
            canvas.drawRect(new Rect(((n * i) + 100) - (p / 2), (int) (((float) l) - (((float) (this.z[i2] + 100)) * this.s)), ((n * i) + 100) + (p / 2), l), paint);
        }
    }

    private void c(Canvas canvas, Paint paint) {
        int i = g * this.C;
        for (int i2 = 1; i2 <= g; i2++) {
            int i3 = i2 + i;
            paint.setColor(this.K);
            canvas.drawCircle((float) ((n * i2) + 100), ((float) l) - (((float) (this.y[i3 - 1] + 100)) * this.s), 3.0f, paint);
            paint.setTextSize(20.0f);
            if (i2 < g) {
                paint.setStrokeWidth(3.0f);
                canvas.drawLine((float) ((n * i2) + 100), ((float) l) - (((float) (this.y[i3 - 1] + 100)) * this.s), (float) (((i2 + 1) * n) + 100), ((float) l) - (((float) (this.y[i3] + 100)) * this.s), paint);
            }
        }
    }

    private void d(Canvas canvas, Paint paint) {
        for (int i = 1; i <= g; i++) {
            paint.setColor(this.K);
            canvas.drawCircle((float) ((n * i) + 100), ((float) l) - (((float) (this.y[(i - 1) + this.A] + 100)) * this.s), 3.0f, paint);
            paint.setTextSize(20.0f);
            if (i < g) {
                paint.setStrokeWidth(3.0f);
                canvas.drawLine((float) ((n * i) + 100), ((float) l) - (((float) (this.y[(i - 1) + this.A] + 100)) * this.s), (float) (((i + 1) * n) + 100), ((float) l) - (((float) (this.y[this.A + i] + 100)) * this.s), paint);
            }
        }
    }

    private void e(Canvas canvas, Paint paint) {
        canvas.rotate(-90.0f, 98.0f, DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
        paint.setColor(-1);
        paint.setTextSize((float) this.w);
        canvas.drawText(this.G, 60.0f, 40.0f, paint);
        canvas.rotate(90.0f, 98.0f, 60.0f);
        canvas.drawText(this.F, (float) ((this.r + 100) - 70), (float) (l + 40), paint);
        int i = 1;
        while (i <= g) {
            int i2 = (this.A + i) + (g * this.C);
            if (i2 <= 29 || this.Q != 2) {
                canvas.drawText(String.valueOf(i2), (float) (((n * i) + 100) + 30), (float) (l + 10), paint);
                if (i <= 5) {
                    canvas.drawText(String.valueOf(((i - 1) * 10) - 100), 92.0f, (float) ((l - (o * (i - 1))) - 35), paint);
                }
                i++;
            } else {
                return;
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
            paint.setColor(this.J);
            if (i2 == 4) {
                f -= 2.0f;
            }
            canvas.drawLine(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, f, (float) m, f, paint);
        }
        paint.setColor(this.J);
        while (i < g + 1) {
            float f2 = (float) ((n * i) + 100);
            canvas.drawLine(f2, (float) l, f2, 40.0f, paint);
            i++;
        }
    }

    private void g(Canvas canvas, Paint paint) {
        int i;
        int i2;
        if (this.C == 0) {
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
            this.D = true;
            for (int i = 0; i < iArr.length; i++) {
                SparseIntArray sparseIntArray = this.x[i];
                if (sparseIntArray == null) {
                    sparseIntArray = new SparseIntArray(0);
                    this.x[i] = sparseIntArray;
                }
                if (sparseIntArray.size() >= 20) {
                    this.R = 0;
                }
                sparseIntArray.put(this.R, iArr[i]);
                this.z[i] = iArr[i];
            }
            this.R++;
            e();
            postInvalidate();
        }
    }

    private void e() {
        for (int i = 0; i < this.y.length; i++) {
            SparseIntArray sparseIntArray = this.x[i];
            int size = sparseIntArray.size();
            if (size != 0) {
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    i2 += sparseIntArray.get(i3);
                }
                this.y[i] = (int) ((((float) i2) * 1.0f) / ((float) size));
                if (this.y[i] < -100) {
                    this.y[i] = -100;
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
        if (!this.O) {
            float x = motionEvent.getX();
            float x2 = motionEvent2.getX();
            if (x - x2 > f.a) {
                Log.d(getClass().getName(), "To LEFT(" + x + "," + x2 + ")");
                g();
            } else if (x - x2 < -80.0f) {
                Log.d(getClass().getName(), "To Right(" + x + "," + x2 + ")");
                f();
            }
        }
        return true;
    }

    private void f() {
        if (this.C != 0) {
            if (this.C == 0) {
                this.C = 1;
            } else {
                this.C--;
            }
            postInvalidate();
        }
    }

    private void g() {
        if (this.C != 1) {
            if (this.C == 1) {
                this.C = 0;
            } else {
                this.C++;
            }
            postInvalidate();
        }
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (!this.O) {
            float x = motionEvent.getX();
            float x2 = motionEvent2.getX();
            Log.d(getClass().getName(), "distanceX=" + f + " d2=" + (x2 - x));
            if (x2 - x < -80.0f) {
                Log.d(getClass().getName(), "To LEFT");
                g();
            } else if (x2 - x > f.a) {
                Log.d(getClass().getName(), "To Right");
                f();
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
        return this.E.onTouchEvent(motionEvent);
    }
}
