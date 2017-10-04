package antistatic.spinnerwheel;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public abstract class g {
    public static final int a = 1;
    private static final int c = 400;
    protected Scroller b;
    private a d;
    private Context e;
    private GestureDetector f;
    private int g;
    private float h;
    private boolean i;
    private final int j = 0;
    private final int k = 1;
    private Handler l = new Handler(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.b.computeScrollOffset();
            int a = this.a.a();
            int a2 = this.a.g - a;
            this.a.g = a;
            if (a2 != 0) {
                this.a.d.a(a2);
            }
            if (Math.abs(a - this.a.b()) < 1) {
                this.a.b.forceFinished(true);
            }
            if (!this.a.b.isFinished()) {
                this.a.l.sendEmptyMessage(message.what);
            } else if (message.what == 0) {
                this.a.f();
            } else {
                this.a.d();
            }
        }
    };

    public interface a {
        void a();

        void a(int i);

        void b();

        void c();

        void d();

        void e();
    }

    protected abstract float a(MotionEvent motionEvent);

    protected abstract int a();

    protected abstract void a(int i, int i2);

    protected abstract void a(int i, int i2, int i3);

    protected abstract int b();

    public g(Context context, a aVar) {
        this.f = new GestureDetector(context, new SimpleOnGestureListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return true;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                this.a.g = 0;
                this.a.a(this.a.g, (int) f, (int) f2);
                this.a.a(0);
                return true;
            }
        });
        this.f.setIsLongpressEnabled(false);
        this.b = new Scroller(context);
        this.d = aVar;
        this.e = context;
    }

    public void a(Interpolator interpolator) {
        this.b.forceFinished(true);
        this.b = new Scroller(this.e, interpolator);
    }

    public void b(int i, int i2) {
        this.b.forceFinished(true);
        this.g = 0;
        if (i2 == 0) {
            i2 = 400;
        }
        a(i, i2);
        a(0);
        g();
    }

    public void c() {
        this.b.forceFinished(true);
    }

    public boolean b(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.h = a(motionEvent);
                this.b.forceFinished(true);
                e();
                this.d.b();
                break;
            case 1:
                if (this.b.isFinished()) {
                    this.d.c();
                    break;
                }
                break;
            case 2:
                int a = (int) (a(motionEvent) - this.h);
                if (a != 0) {
                    g();
                    this.d.a(a);
                    this.h = a(motionEvent);
                    break;
                }
                break;
        }
        if (!this.f.onTouchEvent(motionEvent) && motionEvent.getAction() == 1) {
            f();
        }
        return true;
    }

    private void a(int i) {
        e();
        this.l.sendEmptyMessage(i);
    }

    private void e() {
        this.l.removeMessages(0);
        this.l.removeMessages(1);
    }

    private void f() {
        this.d.e();
        a(1);
    }

    private void g() {
        if (!this.i) {
            this.i = true;
            this.d.a();
        }
    }

    protected void d() {
        if (this.i) {
            this.d.d();
            this.i = false;
        }
    }
}
