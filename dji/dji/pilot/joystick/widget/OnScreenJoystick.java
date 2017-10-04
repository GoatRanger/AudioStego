package dji.pilot.joystick.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.visual.a.d;
import lecho.lib.hellocharts.model.l;

public class OnScreenJoystick extends SurfaceView implements Callback, OnTouchListener {
    private Bitmap a;
    private SurfaceHolder b;
    private Rect c;
    private a d;
    private int e;
    private int f;
    private int g;
    private int h;
    private float i;
    private a j;
    private boolean k = true;

    private class a extends Thread {
        final /* synthetic */ OnScreenJoystick a;
        private boolean b;

        private a(OnScreenJoystick onScreenJoystick) {
            this.a = onScreenJoystick;
            this.b = false;
        }

        public synchronized void start() {
            this.b = true;
            super.start();
        }

        public void a(boolean z) {
            this.b = z;
        }

        public void run() {
            Canvas lockCanvas;
            Canvas canvas;
            Throwable th;
            while (this.b) {
                try {
                    lockCanvas = this.a.b.lockCanvas(null);
                    try {
                        synchronized (this.a.b) {
                            lockCanvas.drawColor(0, Mode.CLEAR);
                            this.a.doDraw(lockCanvas);
                        }
                        if (lockCanvas != null) {
                            this.a.b.unlockCanvasAndPost(lockCanvas);
                        }
                    } catch (Exception e) {
                        canvas = lockCanvas;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e2) {
                    canvas = null;
                    if (canvas != null) {
                        this.a.b.unlockCanvasAndPost(canvas);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    lockCanvas = null;
                }
            }
            return;
            if (lockCanvas != null) {
                this.a.b.unlockCanvasAndPost(lockCanvas);
            }
            throw th;
        }
    }

    public OnScreenJoystick(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    private void a(AttributeSet attributeSet) {
        Resources resources = getContext().getResources();
        Options options = new Options();
        options.inSampleSize = 4;
        this.a = BitmapFactory.decodeResource(resources, R.drawable.joystick, options);
    }

    private void a(Canvas canvas) {
        this.h = canvas.getHeight();
        this.g = Math.round(((float) this.h) * l.n);
        this.c = new Rect();
        this.i = ((float) this.h) * d.c;
        this.e = Math.round(((float) (this.h - this.g)) * d.c);
        this.f = Math.round(((float) (this.h - this.g)) * d.c);
    }

    private void a() {
        this.b = getHolder();
        this.b.addCallback(this);
        setZOrderOnTop(true);
        this.b.setFormat(-2);
        setOnTouchListener(this);
        setEnabled(true);
        setAutoCentering(true);
    }

    public void setAutoCentering(boolean z) {
        this.k = z;
    }

    public boolean isAutoCentering() {
        return this.k;
    }

    public void setJoystickListener(a aVar) {
        this.j = aVar;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.d = new a();
        this.d.start();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean z = true;
        DJILogHelper.getInstance().LOGD("", "joystick 线程关闭", false, true);
        this.d.a(false);
        while (z) {
            try {
                this.d.join();
                z = false;
            } catch (InterruptedException e) {
            }
        }
    }

    public void doDraw(Canvas canvas) {
        if (this.c == null) {
            a(canvas);
        }
        this.c.set(this.e, this.f, this.e + this.g, this.f + this.g);
        canvas.drawBitmap(this.a, null, this.c, null);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 1:
                if (isAutoCentering()) {
                    this.e = Math.round(((float) (this.h - this.g)) * d.c);
                    this.f = Math.round(((float) (this.h - this.g)) * d.c);
                    break;
                }
                break;
            default:
                if (!a(x, y)) {
                    double atan2 = Math.atan2((double) (y - this.i), (double) (x - this.i));
                    this.e = (int) (((float) Math.round(((double) this.i) + (((double) (this.i - (((float) this.g) * d.c))) * Math.cos(atan2)))) - (((float) this.g) * d.c));
                    this.f = (int) (((float) Math.round((Math.sin(atan2) * ((double) (this.i - (((float) this.g) * d.c)))) + ((double) this.i))) - (((float) this.g) * d.c));
                    break;
                }
                this.e = Math.round(x - (((float) this.g) * d.c));
                this.f = Math.round(y - (((float) this.g) * d.c));
                break;
        }
        if (this.j != null) {
            this.j.a((d.c - (((float) this.e) / ((this.i * 2.0f) - ((float) this.g)))) * -2.0f, (d.c - (((float) this.f) / ((this.i * 2.0f) - ((float) this.g)))) * 2.0f);
        }
        return true;
    }

    private boolean a(float f, float f2) {
        return Math.pow((double) (this.i - f), 2.0d) + Math.pow((double) (this.i - f2), 2.0d) <= Math.pow((double) (this.i - (((float) this.g) * d.c)), 2.0d);
    }

    private void b() {
        if (this.j != null) {
            this.j.a((d.c - (((float) this.e) / ((this.i * 2.0f) - ((float) this.g)))) * -2.0f, (d.c - (((float) this.f) / ((this.i * 2.0f) - ((float) this.g)))) * 2.0f);
        }
    }
}
