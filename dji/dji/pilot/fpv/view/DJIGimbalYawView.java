package dji.pilot.fpv.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import com.dji.frame.c.e;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.R;

public class DJIGimbalYawView extends View {
    private static final int a = 30;
    private static final int b = 270;
    private static final int c = 190;
    private static final int d = 90;
    private static final long e = 200;
    private Paint f = null;
    private final RectF g = new RectF();
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private boolean m = false;
    private float n = 0.0f;
    private float o = 0.0f;
    private float p = 0.0f;
    private float q = 0.0f;
    private float r = 0.0f;
    private float s = 0.0f;
    private Handler t = new Handler(this) {
        final /* synthetic */ DJIGimbalYawView a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    this.a.postInvalidate();
                    this.a.t.sendEmptyMessageDelayed(1, 50);
                    return;
                default:
                    return;
            }
        }
    };

    public DJIGimbalYawView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        Context context;
        if (isInEditMode()) {
            context = getContext();
            this.h = e.b(context, 1.0f);
            this.f = new Paint();
            this.f.setStrokeWidth((float) this.h);
            this.f.setStyle(Style.STROKE);
            this.f.setAntiAlias(true);
            this.j = context.getResources().getColor(R.color.d9);
            this.k = context.getResources().getColor(R.color.gj);
            this.l = context.getResources().getColor(R.color.gl);
        } else {
            context = getContext();
            this.h = e.b(context, 1.0f);
            this.f = new Paint();
            this.f.setStrokeWidth((float) this.h);
            this.f.setStyle(Style.STROKE);
            this.f.setAntiAlias(true);
            this.j = context.getResources().getColor(R.color.d9);
            this.k = context.getResources().getColor(R.color.gj);
            this.l = context.getResources().getColor(R.color.gl);
        }
    }

    public void setYaw(float f) {
        if (this.n != f) {
            this.n = f;
            if (f < 0.0f) {
                f = 0.0f - f;
            }
            this.o = f;
            if (this.o >= 190.0f) {
                this.m = true;
            } else if (this.o < 90.0f) {
                this.m = false;
            }
            this.p = 0.0f;
            this.q = 0.0f;
            this.r = 0.0f;
            this.s = 30.0f;
            if (this.n < 0.0f) {
                this.p = this.n;
                this.q = 0.0f - this.n;
            } else {
                this.r = DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMinAngle;
                this.q = this.n;
            }
            postInvalidate();
        }
    }

    protected void onDraw(Canvas canvas) {
        if (!isInEditMode()) {
            float width = (float) getWidth();
            float f = ((float) this.h) / 2.0f;
            this.g.set(f, f, width - f, width - f);
            width /= 2.0f;
            canvas.save();
            canvas.translate(width, width);
            canvas.rotate(-90.0f);
            canvas.translate(-width, -width);
            if (this.o >= 270.0f) {
                this.i = this.i == this.k ? this.l : this.k;
                this.f.setColor(this.i);
                canvas.drawArc(this.g, this.r, this.s, false, this.f);
                postInvalidateDelayed(200);
            } else if (this.m) {
                this.i = this.k;
                this.f.setColor(this.k);
                canvas.drawArc(this.g, this.r, this.s, false, this.f);
            }
            this.f.setColor(this.j);
            canvas.drawArc(this.g, this.p, this.q, false, this.f);
            canvas.restore();
        }
    }
}
