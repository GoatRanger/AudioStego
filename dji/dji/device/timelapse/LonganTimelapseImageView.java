package dji.device.timelapse;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.widget.ImageView;
import dji.device.common.DJIUIEventManagerLongan.k;
import dji.pilot.dji_groundstation.controller.e;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class LonganTimelapseImageView extends ImageView {
    int a;
    int b;
    int c;
    String d = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
    String e;
    dji.device.timelapse.LonganTimelapseMotionPhotoView.a f = null;
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

    public LonganTimelapseImageView(Context context, dji.device.timelapse.LonganTimelapseMotionPhotoView.a aVar, int i) {
        super(context);
        this.f = aVar;
        this.i = i;
        this.e = this.d.substring(i - 1, i);
        this.a = getResources().getDimensionPixelOffset(R.dimen.timelapse_motion_photo_point_radius_big);
        this.b = getResources().getDimensionPixelOffset(R.dimen.timelapse_motion_photo_point_radius_small);
        this.c = getResources().getDimensionPixelOffset(R.dimen.timelapse_motion_photo_point_interval);
    }

    public void setLayoutPosition(dji.device.timelapse.LonganTimelapseMotionPhotoView.a aVar) {
        this.f = aVar;
        invalidate();
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

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        int left = getLeft() + (getWidth() / 2);
        int top = getTop() + (getHeight() / 2);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.white));
        canvas.drawCircle((float) left, (float) top, (float) this.a, paint);
        paint.setColor(getResources().getColor(R.color.blue));
        paint.setTextSize(60.0f);
        canvas.drawText(this.e, ((float) left) - (paint.measureText(this.e) / 2.0f), ((float) top) - ((paint.descent() + paint.ascent()) / 2.0f), paint);
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
        int i3 = i2 - ((this.a + this.c) + this.b);
        while (i3 > getTop()) {
            canvas.drawCircle((float) i, (float) i3, (float) this.b, paint);
            i3 -= this.c + (this.b * 2);
        }
    }

    private void b(int i, int i2, Canvas canvas, Paint paint) {
        int i3 = ((this.a + this.c) + this.b) + i2;
        while (i3 < getBottom()) {
            canvas.drawCircle((float) i, (float) i3, (float) this.b, paint);
            i3 += this.c + (this.b * 2);
        }
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
            if (this.f == dji.device.timelapse.LonganTimelapseMotionPhotoView.a.LAST && kVar.g > this.i) {
                this.f = dji.device.timelapse.LonganTimelapseMotionPhotoView.a.MIDDLE;
            }
            invalidate();
        }
    }
}
