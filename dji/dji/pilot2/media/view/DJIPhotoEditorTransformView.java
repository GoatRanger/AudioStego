package dji.pilot2.media.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.R;
import dji.pilot.visual.a.d;
import java.util.concurrent.Semaphore;

public class DJIPhotoEditorTransformView extends View {
    private static final int k = 6;
    private static final int l = 2;
    private static final int n = 0;
    private static final int o = 1;
    private static final int p = 2;
    private float A = 0.0f;
    private float B = 0.0f;
    private String C = null;
    private Context D = null;
    private Bitmap E = null;
    private final Semaphore F = new Semaphore(0);
    private String G = "";
    private String H = "";
    private String I = "";
    private boolean J = false;
    private boolean K = false;
    private boolean L = false;
    Paint a = null;
    float b = 0.0f;
    float c = this.h;
    float d = 0.0f;
    float e = this.i;
    private int f = 0;
    private int g = 0;
    private float h = DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition;
    private float i = DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity;
    private b j = b.CUT_ORIGINAL;
    private c m = null;
    private int q = 0;
    private float r = 1080.0f;
    private float s = 960.0f;
    private float t = 1080.0f;
    private float u = 960.0f;
    private PointF v = new PointF();
    private PointF w = new PointF();
    private float x = 1.0f;
    private a y = new a(this);
    private boolean z = false;

    public interface c {
        void a(float f, float f2);

        void a(float f, float f2, float f3);
    }

    public class a {
        public float a;
        public float b;
        public float c;
        public float d;
        final /* synthetic */ DJIPhotoEditorTransformView e;

        public a(DJIPhotoEditorTransformView dJIPhotoEditorTransformView) {
            this.e = dJIPhotoEditorTransformView;
        }
    }

    public enum b {
        CUT_ORIGINAL,
        CUT_1X1,
        CUT_3X4,
        CUT_4X3,
        CUT_16X9,
        CUT_9X16
    }

    public DJIPhotoEditorTransformView(Context context) {
        super(context);
        this.D = context;
        a();
    }

    public DJIPhotoEditorTransformView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.D = context;
        a();
    }

    private void a() {
        this.a = new Paint();
        this.a.setAntiAlias(true);
        this.f = Color.argb(150, 255, 255, 255);
        this.g = Color.argb(150, 0, 0, 0);
        this.y.a = 0.0f;
        this.y.c = 1.0f;
        this.y.b = 0.0f;
        this.y.d = 1.0f;
    }

    public void setCutType(b bVar) {
        this.j = bVar;
        getCutMargin();
        invalidate();
    }

    public b getCutType() {
        return this.j;
    }

    public void setPhotoSize(float f, float f2) {
        this.r = f;
        this.s = f2;
        this.y.b = d.c - (((DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity * f2) / f) / 3.0f);
        this.y.d = 1.0f - (this.y.b * 2.0f);
    }

    public void setOnTransformListener(c cVar) {
        this.m = cVar;
    }

    public a getCutRect() {
        return this.y;
    }

    public void enterCutPage(boolean z) {
        this.z = z;
        postInvalidate();
    }

    public float getmWaterMarksMarginLeft() {
        return this.A;
    }

    public float getmWaterMarksMarginBottom() {
        return this.B;
    }

    public float getOutPhotoWidth() {
        return this.t;
    }

    public float getOutPhotoHeight() {
        return this.u;
    }

    public void setNoLocationInfo(String str) {
        Handler handler = new Handler();
        Runnable anonymousClass1 = new Runnable(this) {
            final /* synthetic */ DJIPhotoEditorTransformView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.C = null;
                this.a.postInvalidate();
            }
        };
        this.C = str;
        postInvalidate();
        handler.postDelayed(anonymousClass1, 2000);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.h = (float) getMeasuredWidth();
        this.i = (float) getMeasuredHeight();
        getCutMargin();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.z) {
            switch (motionEvent.getAction() & 255) {
                case 0:
                    this.v.set(motionEvent.getX(), motionEvent.getY());
                    this.q = 1;
                    break;
                case 1:
                case 6:
                    this.q = 0;
                    break;
                case 2:
                    float a;
                    float f;
                    if (this.q != 1) {
                        if (this.q == 2) {
                            a = a(motionEvent);
                            if (a > 10.0f) {
                                f = a / this.x;
                                this.x = a;
                                if (this.m != null) {
                                    this.m.a(f, this.w.x, this.w.y);
                                    break;
                                }
                            }
                        }
                    }
                    a = motionEvent.getX();
                    f = motionEvent.getY();
                    if (this.m != null) {
                        this.m.a(a - this.v.x, f - this.v.y);
                    }
                    this.v.x = a;
                    this.v.y = f;
                    break;
                    break;
                case 5:
                    this.x = a(motionEvent);
                    if (this.x > 10.0f) {
                        this.w = b(motionEvent);
                        this.q = 2;
                        break;
                    }
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    private float a(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }

    private PointF b(MotionEvent motionEvent) {
        return new PointF((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
    }

    private void getCutMargin() {
        this.b = 0.0f;
        this.c = this.h;
        this.d = 0.0f;
        this.e = this.i;
        switch (this.j) {
            case CUT_ORIGINAL:
                if (this.h * this.s > this.i * this.r) {
                    this.b = (float) ((int) ((this.h / 2.0f) - (((this.i / 2.0f) * this.r) / this.s)));
                    this.c = (float) ((int) ((this.h / 2.0f) + (((this.i / 2.0f) * this.r) / this.s)));
                } else {
                    this.d = (float) ((int) ((this.i / 2.0f) - (((this.h / 2.0f) * this.s) / this.r)));
                    this.e = (float) ((int) ((this.i / 2.0f) + (((this.h / 2.0f) * this.s) / this.r)));
                }
                this.t = this.r;
                this.u = this.s;
                break;
            case CUT_1X1:
                if (this.h > this.i) {
                    this.b = (this.h - this.i) / 2.0f;
                    this.c = (this.h + this.i) / 2.0f;
                } else {
                    this.d = (this.i - this.h) / 2.0f;
                    this.e = (this.i + this.h) / 2.0f;
                }
                this.t = this.s;
                this.u = this.s;
                break;
            case CUT_3X4:
                if (this.h * DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity > this.i * 3.0f) {
                    this.b = (this.h / 2.0f) - ((this.i * 3.0f) / 8.0f);
                    this.c = (this.h / 2.0f) + ((this.i * 3.0f) / 8.0f);
                } else {
                    this.d = (this.i / 2.0f) - ((this.h * 2.0f) / 3.0f);
                    this.e = (this.i / 2.0f) + ((this.h * 2.0f) / 3.0f);
                }
                this.t = (this.s * 3.0f) / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity;
                this.u = this.s;
                break;
            case CUT_4X3:
                if (this.h * 3.0f > this.i * DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity) {
                    this.b = (this.h / 2.0f) - ((this.i * 2.0f) / 3.0f);
                    this.c = (this.h / 2.0f) + ((this.i * 2.0f) / 3.0f);
                } else {
                    this.d = (this.i / 2.0f) - ((this.h * 3.0f) / 8.0f);
                    this.e = (this.i / 2.0f) + ((this.h * 3.0f) / 8.0f);
                }
                this.t = this.r;
                this.u = (this.r * 3.0f) / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity;
                break;
            case CUT_16X9:
                if (this.h * 9.0f > this.i * dji.gs.e.b.a) {
                    this.b = (this.h / 2.0f) - ((this.i * 8.0f) / 9.0f);
                    this.c = (this.h / 2.0f) + ((this.i * 8.0f) / 9.0f);
                } else {
                    this.d = (this.i / 2.0f) - ((this.h * 9.0f) / 32.0f);
                    this.e = (this.i / 2.0f) + ((this.h * 9.0f) / 32.0f);
                }
                this.t = this.r;
                this.u = (this.r * 9.0f) / dji.gs.e.b.a;
                break;
            case CUT_9X16:
                if (this.h * dji.gs.e.b.a > this.i * 9.0f) {
                    this.b = (this.h / 2.0f) - ((this.i * 9.0f) / 32.0f);
                    this.c = (this.h / 2.0f) + ((this.i * 9.0f) / 32.0f);
                } else {
                    this.d = (this.i / 2.0f) - ((this.h * 8.0f) / 9.0f);
                    this.e = (this.i / 2.0f) + ((this.h * 8.0f) / 9.0f);
                }
                this.t = (this.s * 9.0f) / dji.gs.e.b.a;
                this.u = this.s;
                break;
        }
        this.y.a = this.b / this.h;
        this.y.c = 1.0f - (this.y.a * 2.0f);
        this.y.b = this.d / this.i;
        this.y.d = 1.0f - (this.y.b * 2.0f);
        this.A = this.b / this.h;
        this.B = this.d / this.i;
    }

    public void setConverBitmap(Bitmap bitmap) {
        this.E = bitmap;
        postInvalidate();
        try {
            this.F.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!(this.E == null || this.E.isRecycled())) {
            canvas.drawBitmap(this.E, 0.0f, 0.0f, null);
            this.E.recycle();
            this.E = null;
            this.F.release();
        }
        a(canvas);
        this.a.setColor(this.f);
        if (this.z) {
            float f = (this.c - this.b) / 3.0f;
            float f2 = (this.e - this.d) / 3.0f;
            this.a.setColor(-7829368);
            this.a.setStrokeWidth(2.0f);
            this.a.setStyle(Style.FILL);
            canvas.drawLine(this.b, this.d + f2, this.c, this.d + f2, this.a);
            Canvas canvas2 = canvas;
            canvas2.drawLine(this.b, (f2 * 2.0f) + this.d, this.c, (f2 * 2.0f) + this.d, this.a);
            canvas.drawLine(this.b + f, this.d, this.b + f, this.e, this.a);
            canvas2 = canvas;
            canvas2.drawLine((f * 2.0f) + this.b, this.d, (f * 2.0f) + this.b, this.e, this.a);
            this.a.setColor(-1);
            this.a.setStrokeWidth(6.0f);
            this.a.setStyle(Style.STROKE);
            canvas.drawRect(new RectF(this.b + 3.0f, this.d + 3.0f, this.c - 3.0f, this.e - 3.0f), this.a);
            this.a.setColor(this.g);
        } else {
            this.a.setColor(-16777216);
        }
        this.a.setStyle(Style.FILL);
        if (this.b > 0.0f) {
            canvas.drawRect(new RectF(0.0f, 0.0f, this.b, this.h + 6.0f), this.a);
            canvas.drawRect(new RectF(this.h - this.b, 0.0f, this.h, this.e + 6.0f), this.a);
        } else if (this.d > 0.0f) {
            canvas.drawRect(new RectF(0.0f, 0.0f, this.h + 6.0f, this.d), this.a);
            canvas.drawRect(new RectF(0.0f, this.i - this.d, this.h + 6.0f, this.i + 12.0f), this.a);
        }
        if (this.C != null) {
            Rect rect = new Rect();
            this.a.setTypeface(Typeface.DEFAULT_BOLD);
            this.a.setTextSize(this.D.getResources().getDimension(R.dimen.rl));
            this.a.getTextBounds(this.C, 0, this.C.length(), rect);
            this.a.setColor(Color.argb(85, 85, 85, 85));
            float dimension = this.D.getResources().getDimension(R.dimen.gw);
            float dimension2 = this.D.getResources().getDimension(R.dimen.fu);
            float dimension3 = this.D.getResources().getDimension(R.dimen.gl);
            if (dimension2 < ((float) (rect.width() + 40))) {
                dimension2 = (float) (rect.width() + 40);
            }
            canvas.drawRoundRect(new RectF((this.h - dimension2) / 2.0f, (this.i - dimension3) / 2.0f, (dimension2 + this.h) / 2.0f, (dimension3 + this.i) / 2.0f), dimension, dimension, this.a);
            this.a.setColor(Color.rgb(255, 255, 255));
            canvas.drawText(this.C, (this.h - ((float) rect.width())) / 2.0f, (((float) rect.height()) + this.i) / 2.0f, this.a);
        }
    }

    public void updateWaterMarks(boolean z, boolean z2, boolean z3) {
        this.J = z;
        this.K = z2;
        this.L = z3;
        postInvalidate();
    }

    public void setWaterMarks(String str, String str2, String str3) {
        this.G = str;
        this.H = str2;
        this.I = str3;
    }

    private void a(Canvas canvas) {
        float width;
        int i = (int) this.h;
        int i2 = (int) this.i;
        Paint paint = new Paint();
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setAntiAlias(true);
        paint.setStyle(Style.FILL);
        paint.setColor(Color.rgb(255, 255, 255));
        Rect rect = new Rect();
        float f = (float) i;
        float f2 = ((float) i2) - (((float) i2) * this.B);
        if (this.H.isEmpty()) {
            paint.setTextSize(50.0f * 1.0f);
            paint.getTextBounds(this.I, 0, this.I.length(), rect);
            width = 0.0f + ((float) rect.width());
        } else {
            paint.setTextSize(1.0f * 35.0f);
            paint.getTextBounds(this.H, 0, this.H.length(), rect);
            width = 0.0f + ((float) rect.width());
        }
        paint.setTextSize(1.0f * 35.0f);
        paint.getTextBounds(this.G, 0, this.G.length(), rect);
        width = (width + ((float) rect.width())) + ((1.0f * 37.0f) * 2.0f);
        if (width > f - ((this.A * ((float) i)) * 2.0f)) {
            width = ((f - ((this.A * ((float) i)) * 2.0f)) / width) * 0.8f;
        } else {
            width = 1.0f;
        }
        paint.setTextSize(width * 35.0f);
        paint.getTextBounds(this.H, 0, this.H.length(), rect);
        float f3 = width * 37.0f;
        float f4 = f2 - (width * 37.0f);
        if (this.K) {
            canvas.drawText(this.H, f3 + (this.A * ((float) i)), f4, paint);
        }
        paint.setTextSize(50.0f * width);
        paint.getTextBounds(this.I, 0, this.I.length(), rect);
        float f5 = width * 37.0f;
        f3 = f2 - (width * 37.0f);
        if (this.K) {
            f3 = (f4 - ((float) rect.height())) - (20.0f * width);
        }
        if (this.L) {
            canvas.drawText(this.I, f5 + (this.A * ((float) i)), f3, paint);
        }
        paint.setTextSize(width * 35.0f);
        paint.getTextBounds(this.G, 0, this.G.length(), rect);
        f3 = (f - ((float) rect.width())) - (width * 37.0f);
        width = f2 - (width * 37.0f);
        if (this.J) {
            canvas.drawText(this.G, f3 - (((float) i) * this.A), width, paint);
        }
    }
}
