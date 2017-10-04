package dji.pilot.dji_groundstation.ui.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import dji.gs.e.b;
import dji.gs.utils.a;
import dji.midware.data.forbid.FlyForbidProtocol;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.controller.DataMgr.c;
import it.sauronsoftware.ftp4j.FTPCodes;

public class FollowMeCircleView extends CalPhoneAzimuthView {
    private static final String g = "FollowMeCircleView";
    private static int k = 20;
    private Bitmap h = null;
    private Bitmap i = null;
    private Bitmap j = null;
    private float l = 0.0f;
    private float m = 0.0f;
    private float n = 0.0f;

    public FollowMeCircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        k = (int) f.a(20.0d, getContext());
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.h = BitmapFactory.decodeResource(getResources(), R.drawable.gs_follow_me_image);
        this.i = BitmapFactory.decodeResource(getResources(), R.drawable.gs_follow_me_aircraft);
        this.j = BitmapFactory.decodeResource(getResources(), R.drawable.gs_follow_me_triangle);
        this.a = (SensorManager) getContext().getSystemService("sensor");
        this.b = this.a.getDefaultSensor(11);
        this.a.registerListener(this, this.b, 2);
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
        if (!(this.j == null || this.j.isRecycled())) {
            this.j.recycle();
            this.j = null;
        }
        if (this.a != null) {
            this.a.unregisterListener(this, this.b);
        }
    }

    public void updateRotate(float f, float f2, float f3) {
        this.l = f;
        this.m = f2;
        this.n = f3;
        postInvalidate();
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(this.h.getWidth(), this.h.getHeight() + (k * 2));
    }

    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(this.h, 0.0f, (float) k, null);
        float width = (float) ((this.h.getWidth() * 171) / FTPCodes.CONNECTION_CLOSED);
        float sin = (float) (((double) width) * Math.sin((((double) this.l) * 3.141592653589793d) / 180.0d));
        width = (float) (((double) width) * Math.cos((((double) this.l) * 3.141592653589793d) / 180.0d));
        Matrix matrix = new Matrix();
        matrix.postRotate(this.n, (float) (this.j.getWidth() / 2), (float) this.j.getHeight());
        matrix.postTranslate(((float) ((this.h.getWidth() / 2) - (this.j.getWidth() / 2))) + sin, ((float) (((this.h.getHeight() / 2) + k) - this.j.getHeight())) + width);
        canvas.drawBitmap(this.j, matrix, null);
        matrix = new Matrix();
        matrix.postRotate(this.m, (float) (this.i.getWidth() / 2), (float) (this.i.getHeight() / 2));
        matrix.postTranslate(sin + ((float) ((this.h.getWidth() / 2) - (this.i.getWidth() / 2))), width + ((float) (((this.h.getHeight() / 2) + k) - (this.i.getHeight() / 2))));
        canvas.drawBitmap(this.i, matrix, null);
    }

    private void b() {
        float f = 0.0f;
        b l = c.getInstance().l();
        if (l != null) {
            float atan2;
            double latitude = DataOsdGetPushCommon.getInstance().getLatitude();
            double longitude = DataOsdGetPushCommon.getInstance().getLongitude();
            if (!(latitude == 0.0d && longitude == 0.0d)) {
                b bVar = new b(latitude, longitude);
                atan2 = (float) ((Math.atan2((bVar.c - l.c) * 1000.0d, (-(bVar.b - l.b)) * FlyForbidProtocol.UNLOCK_RADIUS) * 180.0d) / 3.141592653589793d);
                if (atan2 < 0.0f) {
                    f = atan2 + 360.0f;
                } else {
                    f = atan2;
                }
                a.a(bVar.b, bVar.c, l.b, l.c);
            }
            atan2 = ((float) DataOsdGetPushCommon.getInstance().getYaw()) / 10.0f;
            updateRotate(((float) this.e) + f, atan2 - ((float) this.e), (atan2 + (((float) DataGimbalGetPushParams.getInstance().getYawAngle()) / 10.0f)) - ((float) this.e));
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        super.onSensorChanged(sensorEvent);
        if (sensorEvent.sensor.getType() == 11) {
            b();
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
