package dji.gs.views;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.gs.c.e;

public class EventView extends RelativeLayout {
    public static boolean b = false;
    int a;
    private e c;
    private ImageView d;
    private int e;
    private int f;
    private int g;
    private Context h;
    private c i = c.NORMAL;
    private long j;
    private boolean k = false;
    private boolean l = false;
    private long m = 0;
    private long n = 0;
    private Point o = null;
    private boolean p = false;

    public enum a {
        CLICKBG,
        CLEARPOINT
    }

    public enum b {
        HAS,
        NONE
    }

    public enum c {
        PAINTING,
        MULTIPLE,
        MOVE_START,
        MOVE_ING,
        MOVE_SHORT,
        NORMAL,
        OVER
    }

    public EventView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = context;
        if (!isInEditMode()) {
            dji.thirdparty.a.c.a().a(this);
        }
    }

    public void destroy() {
        dji.thirdparty.a.c.a().d(this);
        this.c = null;
        this.h = null;
    }

    public void onEventMainThread(c cVar) {
        this.i = cVar;
    }

    public void setHand(ImageView imageView) {
        this.d = imageView;
        int b = com.dji.frame.c.e.b(getContext(), DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
        this.a = b / 2;
        this.d.setLayoutParams(new LayoutParams(b, b));
    }

    public c getMode() {
        return this.i;
    }

    public Point getPoint() {
        return this.o;
    }

    public boolean isPaintUp() {
        return this.l;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.k) {
            return false;
        }
        switch (motionEvent.getActionMasked()) {
        }
        if (this.i != c.PAINTING && this.i != c.MULTIPLE) {
            return super.dispatchTouchEvent(motionEvent);
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.l = false;
                this.n = System.currentTimeMillis();
                break;
            case 1:
            case 3:
                this.l = true;
                this.o = null;
                dji.thirdparty.a.c.a().e(this);
                this.i = c.PAINTING;
                break;
            case 2:
                if (!this.p) {
                    if (System.currentTimeMillis() - this.n > 10) {
                        this.o = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
                        dji.thirdparty.a.c.a().e(this);
                    }
                    return true;
                }
                break;
            case 5:
                this.p = true;
                this.i = c.MULTIPLE;
                break;
            case 6:
                this.p = false;
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void isDisPatchTouchEvent(boolean z) {
        this.k = z;
    }
}
