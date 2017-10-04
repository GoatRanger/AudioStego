package dji.phone.timelapse;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.widget.ImageView;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.device.common.DJIUIEventManagerLongan.k;
import dji.pilot.dji_groundstation.controller.e;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class LpTimelapseImageView extends ImageView {
    private static final int j = 5;
    private static final int k = 10;
    int a;
    int b;
    int c;
    String d = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String e;
    dji.phone.timelapse.LpTlpPhotoView.a f = null;
    a g;
    int h = 2;
    int i;

    public enum a {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        UP_DOWN,
        LEFT_RIGHT
    }

    public LpTimelapseImageView(Context context, dji.phone.timelapse.LpTlpPhotoView.a aVar, int i) {
        super(context);
        this.f = aVar;
        this.i = i;
        updateIdFromIndex();
        this.a = getResources().getDimensionPixelOffset(R.dimen.timelapse_motion_photo_point_radius_big);
        this.b = getResources().getDimensionPixelOffset(R.dimen.timelapse_motion_photo_point_radius_small);
        this.c = getResources().getDimensionPixelOffset(R.dimen.timelapse_motion_photo_point_interval);
        setPadding(0, 10, 0, 10);
    }

    public void setLayoutPosition(dji.phone.timelapse.LpTlpPhotoView.a aVar) {
        this.f = aVar;
        invalidate();
    }

    public void setIndex(int i) {
        this.i = i;
        updateIdFromIndex();
        invalidate();
    }

    public dji.phone.timelapse.LpTlpPhotoView.a getLayoutPosition() {
        return this.f;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void setId(String str) {
        this.e = str;
    }

    public void updateIdFromIndex() {
        this.e = this.d.substring(this.i, this.i + 1);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        int left = getLeft() + (getWidth() / 2);
        int top = getTop() + (getHeight() / 2);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.black_20P_longan));
        canvas.drawCircle((float) left, (float) top, (float) this.a, paint);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth((float) getResources().getDimensionPixelOffset(R.dimen.dp_2_in_sw320dp));
        paint.setColor(getResources().getColor(R.color.white_20P));
        canvas.drawCircle((float) left, (float) top, (float) this.a, paint);
        paint.setStyle(Style.FILL);
        paint.setColor(getResources().getColor(R.color.white));
        paint.setTextSize(60.0f);
        canvas.drawText(this.e, ((float) left) - (paint.measureText(this.e) / 2.0f), ((float) top) - ((paint.descent() + paint.ascent()) / 2.0f), paint);
        paint.setAntiAlias(true);
        paint.setStyle(Style.STROKE);
        paint.setPathEffect(new DashPathEffect(new float[]{10.0f, 10.0f}, 0.0f));
        paint.setStrokeWidth(DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity);
        paint.setColor(getResources().getColor(R.color.white));
        if (this.f != null) {
            int i = getResources().getConfiguration().orientation;
            switch (this.f) {
                case FIRST:
                    if (i != 2) {
                        d(left, top, canvas, paint);
                        break;
                    } else {
                        b(left, top, canvas, paint);
                        break;
                    }
                case MIDDLE:
                    if (i != 2) {
                        c(left, top, canvas, paint);
                        d(left, top, canvas, paint);
                        break;
                    }
                    a(left, top, canvas, paint);
                    b(left, top, canvas, paint);
                    break;
                case LAST:
                    if (i != 2) {
                        c(left, top, canvas, paint);
                        break;
                    } else {
                        a(left, top, canvas, paint);
                        break;
                    }
            }
            canvas.restore();
        }
    }

    private void a(int i, int i2, Canvas canvas, Paint paint) {
        Canvas canvas2 = canvas;
        canvas2.drawLine((float) i, 10.0f, (float) i, (float) (i2 - ((this.a + this.c) + this.b)), paint);
    }

    private void b(int i, int i2, Canvas canvas, Paint paint) {
        canvas.drawLine((float) i, (float) (((this.a + this.c) + this.b) + i2), (float) i, (float) getBottom(), paint);
    }

    private void c(int i, int i2, Canvas canvas, Paint paint) {
        int i3 = i - ((this.a + this.c) + this.b);
        while (i3 > getLeft()) {
            canvas.drawCircle((float) i3, (float) i2, (float) this.b, paint);
            i3 -= this.c + (this.b * 2);
        }
    }

    private void d(int i, int i2, Canvas canvas, Paint paint) {
        int i3 = ((this.a + this.c) + this.b) + i;
        while (i3 < getRight()) {
            canvas.drawCircle((float) i3, (float) i2, (float) this.b, paint);
            i3 += this.c + (this.b * 2);
        }
    }

    public void onEventMainThread(k kVar) {
        Log.d(e.c, "cutINdex:" + this.i + "total:" + kVar.g);
        if (kVar == k.ADD_ONE_POINT) {
            if (this.f == dji.phone.timelapse.LpTlpPhotoView.a.LAST && kVar.g > this.i) {
                this.f = dji.phone.timelapse.LpTlpPhotoView.a.MIDDLE;
            }
            invalidate();
        }
    }
}
