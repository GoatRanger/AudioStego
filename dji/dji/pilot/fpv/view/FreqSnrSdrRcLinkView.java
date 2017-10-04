package dji.pilot.fpv.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.model.P3.DataOsdGetPushSdrUpwardSweepFrequency;
import dji.pilot.R;
import dji.pilot.fpv.model.f;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import it.sauronsoftware.ftp4j.FTPCodes;

public class FreqSnrSdrRcLinkView extends DJIImageView implements OnGestureListener, OnTouchListener {
    private static float[] O = new float[t];
    private static final int a = 4;
    private static final int b = 10;
    private static final int c = -110;
    private static final int d = -70;
    private static int e = 83;
    private static final int f = 100;
    private static final int g = 100;
    private static final int h = 40;
    private static final int i = 100;
    private static int j;
    private static int k;
    private static float l = 0.0f;
    private static float m = 0.0f;
    private static float n = 0.0f;
    private static int t = 83;
    private GestureDetector A;
    private String B = null;
    private String C = null;
    private Paint D;
    private Paint E;
    private int F = -12303292;
    private int G;
    private int H;
    private int I;
    private int J;
    private boolean K;
    private int L;
    private int M;
    private float N;
    private int P = 0;
    private int o;
    private int p;
    private float q;
    private int r;
    private int s;
    private SparseIntArray[] u = new SparseIntArray[t];
    private int[] v = new int[t];
    private int[] w = new int[t];
    private int x;
    private int y = 0;
    private boolean z = true;

    public FreqSnrSdrRcLinkView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.A = new GestureDetector(context, this);
            setOnTouchListener(this);
            this.F = context.getResources().getColor(R.color.de);
            this.G = context.getResources().getColor(R.color.dh);
            this.H = context.getResources().getColor(R.color.df);
            this.I = context.getResources().getColor(R.color.dg);
            this.J = context.getResources().getColor(R.color.dd);
            this.L = context.getResources().getColor(R.color.di);
            clean();
            this.D = new Paint();
            this.D.setAntiAlias(true);
            this.D.setTypeface(DJITextView.DEMI);
            this.E = new Paint();
            this.E.setAntiAlias(true);
            this.E.setColor(this.L);
            c.a().a(this);
            this.B = context.getString(R.string.freq_snr_channel);
            this.C = context.getString(R.string.freq_snr_rssi);
        }
    }

    public void onDestroy() {
        c.a().d(this);
    }

    public void onEventBackgroundThread(DataOsdGetPushSdrUpwardSweepFrequency dataOsdGetPushSdrUpwardSweepFrequency) {
        a(dataOsdGetPushSdrUpwardSweepFrequency.getSignalList());
        postInvalidate();
    }

    public void setWorkFreqIndex(int i) {
        this.x = i;
    }

    public void clean() {
        for (int i = 0; i < this.v.length; i++) {
            this.v[i] = -110;
            this.w[i] = -110;
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.D != null) {
            if (this.r == 0) {
                this.r = canvas.getWidth();
                this.s = canvas.getHeight();
                j = (this.s - 40) - 100;
                this.o = j - 40;
                this.p = this.r - 100;
                m = (((float) (this.o - (this.o % 4))) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity;
                l = (((float) this.p) * 1.0f) / ((float) (e + 1));
                n = l;
                this.q = ((float) this.o) / 40.0f;
                k = this.p + 100;
            }
            this.D.setColor(this.F);
            this.D.setStrokeWidth(1.0f);
            canvas.drawLine(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, 40.0f, DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, (float) j, this.D);
            canvas.drawLine((float) k, 40.0f, (float) k, (float) j, this.D);
            canvas.drawLine(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, (float) j, (float) k, (float) j, this.D);
            if (this.z) {
                Log.e(getClass().getName(), "curPageIndex = " + this.y);
                d(canvas, this.D);
                a(canvas, this.D);
                b(canvas, this.D);
                c(canvas, this.D);
                return;
            }
            this.D.setColor(this.I);
            this.D.setTextSize(48.0f);
            canvas.drawText(getContext().getString(R.string.freq_snr_none), (float) (this.p / 2), (float) ((this.o / 2) + 40), this.D);
        }
    }

    private void a(Canvas canvas, Paint paint) {
        try {
            paint.setColor(this.J);
            paint.setStyle(Style.FILL);
            float f = this.N * n;
            for (int i = 1; i <= this.M; i++) {
                paint.setTextSize(20.0f);
                canvas.drawRect(((O[i - 1] * l) + DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) - (f / 2.0f), ((float) j) - (40.0f * this.q), (f / 2.0f) + ((O[i - 1] * l) + DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity), (float) j, paint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b(Canvas canvas, Paint paint) {
        int i = e * this.y;
        for (int i2 = 1; i2 <= e; i2++) {
            int i3 = i2 + i;
            paint.setColor(this.G);
            canvas.drawCircle((((float) i2) * l) + DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, ((float) j) - (((float) (this.v[i3 - 1] + FTPCodes.RESTART_MARKER)) * this.q), 2.0f, paint);
            paint.setTextSize(2.0f);
            if (i2 < e) {
                paint.setStrokeWidth(2.0f);
                canvas.drawLine((((float) i2) * l) + DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, ((float) j) - (((float) (this.v[i3 - 1] + FTPCodes.RESTART_MARKER)) * this.q), (((float) (i2 + 1)) * l) + DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, ((float) j) - (((float) (this.v[i3] + FTPCodes.RESTART_MARKER)) * this.q), paint);
            }
        }
    }

    private void c(Canvas canvas, Paint paint) {
        canvas.rotate(-90.0f, 98.0f, DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
        paint.setColor(-1);
        paint.setTextSize(25.0f);
        canvas.drawText(this.C, 40.0f, 40.0f, paint);
        canvas.rotate(90.0f, 98.0f, 60.0f);
        canvas.drawText(this.B, (float) ((this.p + 100) + d), (float) (j + 40), paint);
        int i = 1;
        while (i <= e) {
            if (i == 1) {
                canvas.drawText(String.valueOf(i), ((((float) i) * l) + DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) + 30.0f, (float) (j + 10), paint);
            } else if (i != e && i % 10 == 0) {
                canvas.drawText(String.valueOf(i), ((((float) i) * l) + DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) + 30.0f, (float) (j + 10), paint);
            }
            if (i <= 5) {
                canvas.drawText(String.valueOf(((i - 1) * 10) - 110), f.a, (((float) j) - (m * ((float) (i - 1)))) - 35.0f, paint);
            }
            i++;
        }
    }

    private void a(Canvas canvas, Paint paint, int i) {
        for (int i2 = 0; i2 < 2; i2++) {
            if (i2 == i) {
                paint.setColor(-1);
            } else {
                paint.setColor(-7829368);
            }
            canvas.drawCircle((float) (((this.r / 2) + (i2 * 30)) + 10), (float) (j + 70), 10.0f, paint);
        }
    }

    private void d(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(1.0f);
        for (int i = 1; i <= 4; i++) {
            float f = ((float) j) - (m * ((float) i));
            paint.setColor(this.F);
            if (i == 4) {
                f -= 2.0f;
            }
            canvas.drawLine(DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, f, (float) k, f, paint);
        }
        paint.setColor(this.F);
        for (int i2 = 1; i2 < e + 2; i2++) {
            float f2 = DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity + (((float) i2) * l);
            canvas.drawLine(f2, (float) j, f2, 40.0f, paint);
        }
    }

    private void a(int[] iArr) {
        if (iArr.length >= t) {
            this.z = true;
            for (int i = 0; i < iArr.length; i++) {
                SparseIntArray sparseIntArray = this.u[i];
                if (sparseIntArray == null) {
                    sparseIntArray = new SparseIntArray(0);
                    this.u[i] = sparseIntArray;
                }
                if (sparseIntArray.size() >= 20) {
                    this.P = 0;
                }
                sparseIntArray.put(this.P, iArr[i]);
                this.w[i] = iArr[i];
            }
            this.P++;
            a();
            postInvalidate();
        }
    }

    private void a() {
        for (int i = 0; i < this.v.length; i++) {
            SparseIntArray sparseIntArray = this.u[i];
            int size = sparseIntArray.size();
            if (size != 0) {
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    i2 += sparseIntArray.get(i3);
                }
                this.v[i] = (int) ((((float) i2) * 1.0f) / ((float) size));
                if (this.v[i] < -110) {
                    this.v[i] = -110;
                } else if (this.v[i] > d) {
                    this.v[i] = d;
                }
            }
        }
    }

    public void setChannelInfo(float f, int i, float[] fArr) {
        this.N = f;
        this.M = i;
        for (int i2 = 0; i2 < i; i2++) {
            O[i2] = fArr[i2];
        }
        postInvalidate();
    }

    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        float x = motionEvent.getX();
        float x2 = motionEvent2.getX();
        if (x - x2 > f.a) {
            Log.d(getClass().getName(), "To LEFT(" + x + "," + x2 + ")");
        } else if (x - x2 < -80.0f) {
            Log.d(getClass().getName(), "To Right(" + x + "," + x2 + ")");
        }
        return true;
    }

    private void b() {
        if (this.y != 0) {
            if (this.y == 0) {
                this.y = 1;
            } else {
                this.y--;
            }
            postInvalidate();
        }
    }

    private void c() {
        if (this.y != 1) {
            if (this.y == 1) {
                this.y = 0;
            } else {
                this.y++;
            }
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
        } else if (x2 - x > f.a) {
            Log.d(getClass().getName(), "To Right");
        }
        return true;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.A.onTouchEvent(motionEvent);
    }
}
