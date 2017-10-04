package dji.pilot.groundStation.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.R;
import dji.pilot.fpv.control.k.c;
import dji.pilot.groundStation.a.a;

public class DJIGSCourseLockView extends View {
    private static int d = 20;
    private Bitmap a = null;
    private Bitmap b = null;
    private Bitmap c = null;
    private float e = 0.0f;
    private float f = 0.0f;
    private boolean g = false;

    public DJIGSCourseLockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d = (int) getResources().getDimension(R.dimen.g1);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a = BitmapFactory.decodeResource(getResources(), R.drawable.gs_course_lock_image);
        this.b = BitmapFactory.decodeResource(getResources(), R.drawable.gs_course_lock_with_dot_image);
        this.c = BitmapFactory.decodeResource(getResources(), R.drawable.gs_course_lock_aircraft);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!(this.a == null || this.a.isRecycled())) {
            this.a.recycle();
            this.a = null;
        }
        if (!(this.b == null || this.b.isRecycled())) {
            this.b.recycle();
            this.b = null;
        }
        if (this.c != null && !this.c.isRecycled()) {
            this.c.recycle();
            this.c = null;
        }
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(this.a.getWidth(), this.a.getHeight() + (d * 2));
    }

    public void updateRotate(float f) {
        this.e = f;
        postInvalidate();
    }

    public void lock(boolean z, float f) {
        this.g = z;
        this.f = f;
    }

    public float getPhoneAzimuth() {
        float w = (float) a.getInstance(null).w();
        if (a.getInstance(null).i().m() != c.LOCK || a.getInstance(null).v()) {
            return w;
        }
        return 0.0f;
    }

    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        Matrix matrix;
        Paint paint = new Paint();
        paint.setAlpha(100);
        canvas.drawBitmap(this.a, 0.0f, (float) d, paint);
        if (this.g) {
            matrix = new Matrix();
            matrix.postRotate(this.f - getPhoneAzimuth(), (float) (this.b.getWidth() / 2), (float) (this.b.getHeight() / 2));
            matrix.postTranslate(0.0f, (float) d);
            canvas.drawBitmap(this.b, matrix, null);
        } else {
            matrix = new Matrix();
            matrix.postRotate(this.e, (float) (this.b.getWidth() / 2), (float) (this.b.getHeight() / 2));
            matrix.postTranslate(0.0f, (float) d);
            canvas.drawBitmap(this.b, matrix, null);
        }
        matrix = new Matrix();
        matrix.postRotate(this.e, (float) (this.c.getWidth() / 2), (float) (this.c.getHeight() / 2));
        matrix.postTranslate((float) ((this.a.getWidth() / 2) - (this.c.getWidth() / 2)), (float) (((this.a.getHeight() / 2) + d) - (this.c.getHeight() / 2)));
        canvas.drawBitmap(this.c, matrix, null);
    }
}
