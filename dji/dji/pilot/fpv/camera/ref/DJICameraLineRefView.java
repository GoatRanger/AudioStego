package dji.pilot.fpv.camera.ref;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.alipay.sdk.j.i;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.a;
import dji.pilot.fpv.camera.more.a$a;
import dji.pilot.fpv.camera.more.d$a;
import dji.pilot.fpv.camera.more.d.e;
import dji.pilot.visual.a.d;
import dji.thirdparty.a.c;

public class DJICameraLineRefView extends View implements e {
    private final Paint a = new Paint();
    private d$a b = d$a.WHITE;
    private int o = 1;
    private final RectF p = new RectF();

    public DJICameraLineRefView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        this.a.setAntiAlias(true);
        this.a.setStrokeWidth(getResources().getDimension(R.dimen.ga));
        this.a.setStyle(Style.STROKE);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.p.isEmpty()) {
            canvas.drawRect(this.p, this.a);
        }
    }

    public void onEventMainThread(a$a dji_pilot_fpv_camera_more_a_a) {
        if (dji_pilot_fpv_camera_more_a_a == a$a.LINEREF_CHANGED) {
            a(true);
        } else if (dji_pilot_fpv_camera_more_a_a == a$a.LINEREF_CUSTOMATTR_CHANGED) {
            a(true);
        } else if (dji_pilot_fpv_camera_more_a_a == a$a.LINEREF_COLOR_CHANGED) {
            a(true);
        }
    }

    private void a(boolean z) {
        a instance = a.getInstance();
        this.b = instance.v();
        this.o = instance.u();
        this.a.setColor(getResources().getColor(this.b.a()));
        if (this.o == 0) {
            this.p.setEmpty();
        } else {
            float f = aq_[this.o][0];
            float f2 = aq_[this.o][1];
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            float min = Math.min(((float) measuredWidth) / f, ((float) measuredHeight) / f2);
            f = (float) Math.round((((float) measuredWidth) - (f * min)) * d.c);
            f2 = (float) Math.round((((float) measuredHeight) - (f2 * min)) * d.c);
            this.p.set(f, f2, ((float) measuredWidth) - f, ((float) measuredHeight) - f2);
            a.a("LineRef[" + this.p + i.b + measuredWidth + i.b + measuredHeight + dji.pilot.usercenter.protocol.d.H);
        }
        if (z) {
            postInvalidate();
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        a(true);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!(isInEditMode() || c.a().c(this))) {
            c.a().a(this);
        }
        a(false);
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
