package antistatic.spinnerwheel;

import android.content.Context;
import android.view.MotionEvent;
import antistatic.spinnerwheel.g.a;

public class h extends g {
    public h(Context context, a aVar) {
        super(context, aVar);
    }

    protected int a() {
        return this.b.getCurrY();
    }

    protected int b() {
        return this.b.getFinalY();
    }

    protected float a(MotionEvent motionEvent) {
        return motionEvent.getY();
    }

    protected void a(int i, int i2) {
        this.b.startScroll(0, 0, 0, i, i2);
    }

    protected void a(int i, int i2, int i3) {
        this.b.fling(0, i, 0, -i3, 0, 0, -2147483647, Integer.MAX_VALUE);
    }
}
