package antistatic.spinnerwheel;

import android.content.Context;
import android.view.MotionEvent;
import antistatic.spinnerwheel.g.a;

public class e extends g {
    public e(Context context, a aVar) {
        super(context, aVar);
    }

    protected int a() {
        return this.b.getCurrX();
    }

    protected int b() {
        return this.b.getFinalX();
    }

    protected float a(MotionEvent motionEvent) {
        return motionEvent.getX();
    }

    protected void a(int i, int i2) {
        this.b.startScroll(0, 0, i, 0, i2);
    }

    protected void a(int i, int i2, int i3) {
        this.b.fling(i, 0, -i2, 0, -2147483647, Integer.MAX_VALUE, 0, 0);
    }
}
