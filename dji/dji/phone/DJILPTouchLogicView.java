package dji.phone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.phone.a.g;
import dji.phone.e.a.d;
import dji.phone.e.a.e;
import dji.phone.e.b;
import dji.phone.widget.DJILPUISwitcher;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class DJILPTouchLogicView extends View {
    public static final String a = "DJILPTouchLogicView";
    public static MotionEvent b = null;
    private static final int h = 5;
    private static final int i = 30;
    private Paint c = new Paint();
    private Rect d;
    private boolean e = false;
    private float f;
    private float g;
    private int j = 0;
    private GestureDetector k;
    private OnGestureListener l = new OnGestureListener(this) {
        final /* synthetic */ DJILPTouchLogicView a;

        {
            this.a = r1;
        }

        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        public void onShowPress(MotionEvent motionEvent) {
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            c.a().e(new b(e.VIEW_PREVIEW, dji.phone.e.a.c.b));
            return false;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            Log.d(DJILPTouchLogicView.a, "onScroll: ");
            if (DJILPUISwitcher.a == dji.phone.g.b.METERING) {
                switch (dji.phone.preview.a.d) {
                    case ROTATION_0:
                        break;
                    case ROTATION_90:
                        f2 = -f;
                        break;
                    case ROTATION_180:
                        f2 = -f2;
                        break;
                    case ROTATION_270:
                        f2 = f;
                        break;
                    default:
                        f2 = 0.0f;
                        break;
                }
                g.a(DJILPTouchLogicView.a, "event:" + dji.phone.preview.a.d + "mDistance:" + f2);
                new d().b(dji.phone.e.a.a.a, Float.valueOf(f2));
            }
            return false;
        }

        public void onLongPress(MotionEvent motionEvent) {
            Log.d(DJILPTouchLogicView.a, "onLongPress: ");
            if (this.a.j != 1) {
                new d().a(dji.phone.e.a.a.b, motionEvent);
            }
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }
    };

    private final class a implements OnTouchListener {
        private static final int b = 0;
        private static final int c = 1;
        final /* synthetic */ DJILPTouchLogicView a;
        private float d;

        private a(DJILPTouchLogicView dJILPTouchLogicView) {
            this.a = dJILPTouchLogicView;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int i = 0;
            switch (motionEvent.getAction() & 255) {
                case 0:
                    this.a.j = 0;
                    this.a.onTouchEvent(motionEvent);
                    break;
                case 1:
                    if (this.a.j == 1) {
                        if (DJILPUISwitcher.a == dji.phone.g.b.TRACKING) {
                            this.a.onTouchEvent(motionEvent);
                            break;
                        }
                    }
                    this.a.onTouchEvent(motionEvent);
                    break;
                    break;
                case 2:
                    if (this.a.j == 1) {
                        if (DJILPUISwitcher.a != dji.phone.g.b.TRACKING) {
                            if (motionEvent.getPointerCount() >= 2) {
                                float a = a(motionEvent);
                                Log.d(DJILPTouchLogicView.a, "onTouch: " + (a - this.d));
                                int i2 = (int) ((a - this.d) / 10.0f);
                                Log.d(DJILPTouchLogicView.a, "onTouch: " + i2);
                                if (i2 >= 1 || i2 <= -1) {
                                    try {
                                        i2 += dji.phone.c.a.c().h();
                                        if (i2 > dji.phone.c.a.c().i()) {
                                            i2 = dji.phone.c.a.c().i();
                                        }
                                        if (i2 >= 0) {
                                            i = i2;
                                        }
                                        dji.phone.d.d.getInstance().c(i, true);
                                        this.d = a;
                                        break;
                                    } catch (IllegalStateException e) {
                                        Log.d(DJILPTouchLogicView.a, "" + e);
                                        break;
                                    }
                                }
                            }
                        }
                        this.a.onTouchEvent(motionEvent);
                        break;
                    }
                    this.a.onTouchEvent(motionEvent);
                    break;
                    break;
                case 5:
                    if (DJILPUISwitcher.a != dji.phone.g.b.TRACKING) {
                        this.a.j = 1;
                        this.d = a(motionEvent);
                        break;
                    }
                    break;
            }
            return true;
        }

        private float a(MotionEvent motionEvent) {
            float x = motionEvent.getX(1) - motionEvent.getX(0);
            float y = motionEvent.getY(1) - motionEvent.getY(0);
            return (float) Math.sqrt((double) ((x * x) + (y * y)));
        }
    }

    public DJILPTouchLogicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c.setColor(getResources().getColor(R.color.white));
        this.c.setAntiAlias(true);
        this.c.setStyle(Style.STROKE);
        this.c.setShadowLayer(5.0f, 0.0f, 0.0f, getResources().getColor(R.color.black_40P_longan));
        this.c.setStrokeWidth(DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity);
        setLayerType(1, this.c);
        this.d = new Rect();
        this.k = new GestureDetector(getContext(), this.l);
        this.k.setIsLongpressEnabled(true);
        setOnTouchListener(new a());
    }

    public void setHaveTouch(boolean z, Rect rect) {
        this.e = z;
        this.d = rect;
        invalidate();
    }

    public void setHaveTouch(boolean z) {
        this.e = z;
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        if (this.e) {
            canvas.drawRect((float) this.d.left, (float) this.d.top, (float) this.d.right, (float) this.d.bottom, this.c);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        b = motionEvent;
        if (motionEvent.getAction() == 0) {
            if (DJILPUISwitcher.a == dji.phone.g.b.TRACKING) {
                this.f = motionEvent.getX();
                this.g = motionEvent.getY();
                this.d.top = (int) this.g;
                this.d.left = (int) this.f;
                this.d.right = (int) this.f;
                this.d.bottom = (int) this.g;
            } else {
                c.a().e(motionEvent);
            }
        } else if (motionEvent.getAction() == 2 && DJILPUISwitcher.a == dji.phone.g.b.TRACKING) {
            this.d.top = (int) (this.g < motionEvent.getY() ? this.g : motionEvent.getY());
            this.d.left = (int) (this.f < motionEvent.getX() ? this.f : motionEvent.getX());
            this.d.right = (int) (this.f > motionEvent.getX() ? this.f : motionEvent.getX());
            this.d.bottom = (int) (this.g > motionEvent.getY() ? this.g : motionEvent.getY());
            setHaveTouch(true, this.d);
        } else if (motionEvent.getAction() == 1 && DJILPUISwitcher.a == dji.phone.g.b.TRACKING) {
            boolean z;
            setHaveTouch(false);
            dji.phone.tracking.d dVar = new dji.phone.tracking.d();
            if (this.d.width() < 30 || this.d.height() < 30) {
                z = true;
            } else {
                z = false;
            }
            dVar.a = z;
            dVar.b = this.d;
            c.a().e(dVar);
        }
        if (this.j == 1) {
            return false;
        }
        return this.k.onTouchEvent(motionEvent);
    }
}
