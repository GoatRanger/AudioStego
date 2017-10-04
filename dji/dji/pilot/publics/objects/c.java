package dji.pilot.publics.objects;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager.LayoutParams;
import com.dji.frame.c.c.a;
import dji.pilot.R;
import dji.pilot.fpv.model.b;

public class c extends Dialog {
    protected static final float M = 0.4f;
    protected Context N;
    public int O;
    public int P;
    private Handler a;
    private boolean b;
    private float c;

    public c(Context context) {
        this(context, (int) R.style.c6);
    }

    public c(Context context, boolean z) {
        this(context, (int) R.style.c6);
        this.b = z;
    }

    public c(Context context, int i) {
        super(context, i);
        this.N = null;
        this.a = new Handler();
        this.b = true;
        this.c = 0.0f;
        this.N = context;
    }

    public c(Context context, int i, boolean z) {
        super(context, i);
        this.N = null;
        this.a = new Handler();
        this.b = true;
        this.c = 0.0f;
        this.N = context;
        this.b = z;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m();
    }

    protected void onStart() {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.flags |= 8;
        getWindow().setAttributes(attributes);
        super.onStart();
        this.a.postDelayed(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                if (!((this.a.getContext() instanceof Activity) && ((Activity) this.a.getContext()).isFinishing()) && this.a.isShowing()) {
                    LayoutParams attributes = this.a.getWindow().getAttributes();
                    attributes.flags &= -9;
                    this.a.getWindow().setAttributes(attributes);
                }
            }
        }, 50);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        if (this.b) {
            com.dji.frame.c.c.a(getWindow());
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.b) {
            dji.thirdparty.a.c.a().e(a.HIDE);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (isShowing() && this.b) {
            com.dji.frame.c.c.a(getWindow());
        }
    }

    public void onEventMainThread(a aVar) {
        if (isShowing() && this.b) {
            switch (aVar) {
                case HIDE:
                    com.dji.frame.c.c.a(getWindow());
                    return;
                case HIDE_DELAY:
                    return;
                default:
                    return;
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.b) {
            com.dji.frame.c.c.a(getWindow());
        }
        if (motionEvent.getAction() == 0 && a(motionEvent) && a()) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    protected void m() {
        a(b.a(this.N, (int) R.dimen.l0), DJIBaseActivity.screenHeight - b.a(this.N, (int) R.dimen.kz), 0, 17, true, true);
    }

    public void a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        LayoutParams attributes = getWindow().getAttributes();
        this.O = i;
        this.P = i2;
        attributes.width = i;
        attributes.height = i2;
        attributes.y = i3;
        attributes.dimAmount = this.c;
        attributes.flags &= -3;
        attributes.gravity = i4;
        getWindow().setAttributes(attributes);
        getWindow().setWindowAnimations(R.style.e5);
        setCancelable(z);
        setCanceledOnTouchOutside(z2);
    }

    public void n() {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.flags &= -33;
        attributes.flags &= -9;
        getWindow().setAttributes(attributes);
    }

    public void o() {
        this.a.postDelayed(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                if (!((this.a.getContext() instanceof Activity) && ((Activity) this.a.getContext()).isFinishing()) && this.a.isShowing()) {
                    LayoutParams attributes = this.a.getWindow().getAttributes();
                    attributes.flags |= 32;
                    this.a.getWindow().setAttributes(attributes);
                }
            }
        }, 500);
    }

    public void a(float f) {
        this.c = f;
        LayoutParams attributes = getWindow().getAttributes();
        attributes.dimAmount = f;
        attributes.flags |= 2;
        getWindow().setAttributes(attributes);
    }

    protected boolean a() {
        return false;
    }

    protected boolean a(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int scaledWindowTouchSlop = ViewConfiguration.get(this.N).getScaledWindowTouchSlop();
        View decorView = getWindow().getDecorView();
        return x < (-scaledWindowTouchSlop) || y < (-scaledWindowTouchSlop) || x > decorView.getWidth() + scaledWindowTouchSlop || y > decorView.getHeight() + scaledWindowTouchSlop;
    }
}
