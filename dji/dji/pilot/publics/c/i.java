package dji.pilot.publics.c;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import dji.thirdparty.a.c;

public class i implements SensorEventListener {
    private SensorManager a = null;
    private Context b;
    private Sensor c;
    private float[] d = new float[3];
    private float[] e = new float[9];

    public i(Context context) {
        this.b = context.getApplicationContext();
        this.a = (SensorManager) this.b.getSystemService("sensor");
        this.c = this.a.getDefaultSensor(11);
    }

    public void a() {
        this.a.unregisterListener(this, this.c);
    }

    public void b() {
        this.a.registerListener(this, this.c, 2);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 11) {
            a(this.e, sensorEvent.values);
            SensorManager.getOrientation(this.e, this.d);
            c.a().e(this.d);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public static void a(float[] fArr, float[] fArr2) {
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

    public static int a(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int i = activity.getResources().getConfiguration().orientation;
        if (i == 1) {
            if (rotation == 0 || rotation == 3) {
                return 1;
            }
            return 9;
        } else if (i != 2) {
            return -1;
        } else {
            if (rotation == 0 || rotation == 1) {
                return 0;
            }
            return 8;
        }
    }
}
