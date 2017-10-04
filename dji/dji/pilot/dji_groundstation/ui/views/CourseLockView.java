package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.controller.DataMgr.b;
import dji.pilot.dji_groundstation.controller.a;

public class CourseLockView extends CalPhoneAzimuthView {
    private static final String g = "CourseLockView";
    private static int k = 20;
    private Bitmap h = null;
    private Bitmap i = null;
    private Bitmap j = null;
    private float l = 0.0f;
    private float m = 0.0f;
    private boolean n = false;

    public CourseLockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        k = (int) f.a(20.0d, getContext());
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.h = BitmapFactory.decodeResource(getResources(), R.drawable.gs_course_lock_image);
        this.i = BitmapFactory.decodeResource(getResources(), R.drawable.gs_course_lock_with_dot_image);
        this.j = BitmapFactory.decodeResource(getResources(), R.drawable.gs_course_lock_aircraft);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!(this.h == null || this.h.isRecycled())) {
            this.h.recycle();
            this.h = null;
        }
        if (!(this.i == null || this.i.isRecycled())) {
            this.i.recycle();
            this.i = null;
        }
        if (this.j != null && !this.j.isRecycled()) {
            this.j.recycle();
            this.j = null;
        }
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(this.h.getWidth(), this.h.getHeight() + (k * 2));
    }

    public void updateRotate(float f) {
        this.l = f;
    }

    public void lock(boolean z) {
        updateRotate();
        if (this.n != z) {
            this.n = z;
            if (this.n) {
                this.m = this.l;
                b.getInstance().a(this.m);
            }
        }
        invalidate();
    }

    public void forceLock() {
        updateRotate();
        b.getInstance().a(this.l);
        invalidate();
    }

    public float getDegree() {
        return this.l;
    }

    public float getPhoneAzimuth() {
        float f = (float) this.e;
        if (a.getInstance(getContext()).c()) {
            return f;
        }
        return 0.0f;
    }

    protected void onDraw(Canvas canvas) {
        Matrix matrix;
        Paint paint = new Paint();
        paint.setAlpha(100);
        canvas.drawBitmap(this.h, 0.0f, (float) k, paint);
        if (b.getInstance().i()) {
            matrix = new Matrix();
            getPhoneAzimuth();
            matrix.postRotate(b.getInstance().k(), (float) (this.i.getWidth() / 2), (float) (this.i.getHeight() / 2));
            matrix.postTranslate(0.0f, (float) k);
            canvas.drawBitmap(this.i, matrix, null);
        } else {
            matrix = new Matrix();
            matrix.postRotate(this.l, (float) (this.i.getWidth() / 2), (float) (this.i.getHeight() / 2));
            matrix.postTranslate(0.0f, (float) k);
            canvas.drawBitmap(this.i, matrix, null);
        }
        matrix = new Matrix();
        matrix.postRotate(this.l, (float) (this.j.getWidth() / 2), (float) (this.j.getHeight() / 2));
        matrix.postTranslate((float) ((this.h.getWidth() / 2) - (this.j.getWidth() / 2)), (float) (((this.h.getHeight() / 2) + k) - (this.j.getHeight() / 2)));
        canvas.drawBitmap(this.j, matrix, null);
    }

    public void updateRotate() {
        float yaw = ((float) (DataOsdGetPushCommon.getInstance().getYaw() / 10)) - getPhoneAzimuth();
        if (yaw < 0.0f) {
            yaw += 360.0f;
        } else if (yaw >= 360.0f) {
            yaw -= 360.0f;
        }
        updateRotate(yaw);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        super.onSensorChanged(sensorEvent);
        updateRotate();
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
        super.onAccuracyChanged(sensor, i);
    }
}
