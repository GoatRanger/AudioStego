package dji.device.camera.view.ref;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.camera.datamanager.DJICameraDataManagerCompat.a;
import dji.device.common.view.set.view.a.b;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.pilot.fpv.R;
import dji.pilot.visual.a.d;
import dji.thirdparty.a.c;

public class DJILCameraLineRefView extends View implements b {
    private final Paint m = new Paint();
    private DJICameraDataManagerCompat.b n = DJICameraDataManagerCompat.b.WHITE;
    private int o = 1;
    private final RectF p = new RectF();

    public DJILCameraLineRefView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        this.m.setAntiAlias(true);
        this.m.setStrokeWidth(getResources().getDimension(R.dimen.dp_2_in_sw320dp));
        this.m.setStyle(Style.STROKE);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.p.isEmpty()) {
            canvas.drawRect(this.p, this.m);
        }
    }

    public void onEventMainThread(a aVar) {
        if (aVar == a.LINEREF_CHANGED) {
            a(true);
        } else if (aVar == a.LINEREF_CUSTOMATTR_CHANGED) {
            a(true);
        } else if (aVar == a.LINEREF_COLOR_CHANGED) {
            a(true);
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (dji.logic.f.b.l(dataCameraGetPushStateInfo.getCameraType())) {
            DJICameraDataManagerCompat.getInstance().updateLineRef(0);
        } else {
            DJICameraDataManagerCompat.getInstance().updateLineRef(0);
        }
    }

    private void a(boolean z) {
        DJICameraDataManagerCompat instance = DJICameraDataManagerCompat.getInstance();
        this.n = instance.getLineRefColor();
        this.o = instance.getLineRef();
        this.m.setColor(getResources().getColor(this.n.a()));
        if (this.o == 0) {
            this.p.setEmpty();
        } else {
            float f = l[this.o][0];
            float f2 = l[this.o][1];
            int width = getWidth();
            int height = getHeight();
            float min = Math.min(((float) width) / f, ((float) height) / f2);
            f = (float) Math.round((((float) width) - (f * min)) * d.c);
            f2 = (float) Math.round((((float) height) - (f2 * min)) * d.c);
            this.p.set(f, f2, ((float) width) - f, ((float) height) - f2);
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
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
