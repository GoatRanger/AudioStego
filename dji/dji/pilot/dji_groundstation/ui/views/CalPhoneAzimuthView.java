package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class CalPhoneAzimuthView extends View implements SensorEventListener {
    private static final String g = "CalPhoneAzimuthView";
    protected SensorManager a = null;
    protected Sensor b;
    protected float[] c = new float[3];
    protected float[] d = new float[9];
    protected int e = 0;
    protected int f = 0;
    private Display h = null;

    public CalPhoneAzimuthView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void a() {
        int i = 0;
        int toDegrees = (int) Math.toDegrees((double) this.c[0]);
        if (toDegrees - this.f > 2 || this.f - toDegrees > 2) {
            i = 1;
        }
        if (i != 0) {
            this.f = toDegrees;
            if (getDisplayRotation() == 3) {
                toDegrees += 180;
            }
            this.e = toDegrees + 90;
        }
    }

    private int getDisplayRotation() {
        if (this.h == null) {
            this.h = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        }
        return this.h.getRotation();
    }

    protected void a(float[] fArr, float[] fArr2) {
        float f;
        float f2 = fArr2[0];
        float f3 = fArr2[1];
        float f4 = fArr2[2];
        if (fArr2.length == 4) {
            f = fArr2[3];
        } else {
            f = ((1.0f - (f2 * f2)) - (f3 * f3)) - (f4 * f4);
            f = f > 0.0f ? (float) Math.sqrt((double) f) : 0.0f;
        }
        float f5 = (2.0f * f2) * f2;
        float f6 = (2.0f * f3) * f3;
        float f7 = (2.0f * f4) * f4;
        float f8 = (2.0f * f2) * f3;
        float f9 = (2.0f * f4) * f;
        float f10 = (2.0f * f2) * f4;
        float f11 = (2.0f * f3) * f;
        f3 = (f3 * 2.0f) * f4;
        f *= f2 * 2.0f;
        if (fArr.length == 9) {
            fArr[0] = (1.0f - f6) - f7;
            fArr[1] = f8 - f9;
            fArr[2] = f10 + f11;
            fArr[3] = f8 + f9;
            fArr[4] = (1.0f - f5) - f7;
            fArr[5] = f3 - f;
            fArr[6] = f10 - f11;
            fArr[7] = f + f3;
            fArr[8] = (1.0f - f5) - f6;
        } else if (fArr.length == 16) {
            fArr[0] = (1.0f - f6) - f7;
            fArr[1] = f8 - f9;
            fArr[2] = f10 + f11;
            fArr[3] = 0.0f;
            fArr[4] = f8 + f9;
            fArr[5] = (1.0f - f5) - f7;
            fArr[6] = f3 - f;
            fArr[7] = 0.0f;
            fArr[8] = f10 - f11;
            fArr[9] = f + f3;
            fArr[10] = (1.0f - f5) - f6;
            fArr[11] = 0.0f;
            fArr[14] = 0.0f;
            fArr[13] = 0.0f;
            fArr[12] = 0.0f;
            fArr[15] = 1.0f;
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 11) {
            a(this.d, sensorEvent.values);
            SensorManager.getOrientation(this.d, this.c);
            a();
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
