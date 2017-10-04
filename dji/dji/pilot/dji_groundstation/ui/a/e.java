package dji.pilot.dji_groundstation.ui.a;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager.LayoutParams;
import com.dji.frame.c.c;
import com.dji.frame.c.c.a;
import dji.pilot.dji_groundstation.R;

public class e extends Dialog {
    private static final String a = "GSBaseDialog";
    protected Context b = null;
    public int c;
    public int d;
    protected boolean e = true;
    protected Handler f = new Handler(Looper.getMainLooper());

    public e(Context context) {
        super(context, R.style.GSDialog);
        this.b = context;
    }

    protected void a(Runnable runnable) {
        if (runnable != null && this.f != null) {
            this.f.post(runnable);
        }
    }

    protected void a(Runnable runnable, int i) {
        if (runnable != null && this.f != null) {
            this.f.postDelayed(runnable, (long) i);
        }
    }

    public void setContentView(int i) {
        super.setContentView(i);
        if (this.e) {
            c.a(getWindow());
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.e) {
            dji.thirdparty.a.c.a().e(a.a);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (isShowing() && this.e) {
            c.a(getWindow());
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && a(motionEvent)) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void d() {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.flags |= 32;
        attributes.flags |= 2;
        getWindow().setAttributes(attributes);
    }

    public void e() {
        if (!((getContext() instanceof Activity) && ((Activity) getContext()).isFinishing()) && isShowing()) {
            LayoutParams attributes = getWindow().getAttributes();
            attributes.flags &= -33;
            attributes.flags &= -9;
            attributes.flags &= -3;
            getWindow().setAttributes(attributes);
        }
    }

    protected boolean a(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int scaledWindowTouchSlop = ViewConfiguration.get(this.b).getScaledWindowTouchSlop();
        View decorView = getWindow().getDecorView();
        return x < (-scaledWindowTouchSlop) || y < (-scaledWindowTouchSlop) || x > decorView.getWidth() + scaledWindowTouchSlop || y > decorView.getHeight() + scaledWindowTouchSlop;
    }

    protected void f() {
        c.a(getWindow());
    }

    public void a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = i;
        attributes.height = i2;
        attributes.y = i3;
        attributes.dimAmount = 0.0f;
        attributes.gravity = i4;
        getWindow().setAttributes(attributes);
        setCancelable(z);
        setCanceledOnTouchOutside(z2);
    }
}
