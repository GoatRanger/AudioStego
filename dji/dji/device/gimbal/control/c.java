package dji.device.gimbal.control;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import dji.device.common.DJIUIEventManagerLongan.j;
import dji.midware.data.model.P3.DataGimbalSpeedControl;

public class c implements SensorEventListener {
    float a = -100000.0f;
    float b = -100000.0f;
    int c = 0;
    boolean d = false;
    Activity e;
    private SensorManager f = null;
    private Sensor g = null;

    public c(Context context, Activity activity) {
        this.e = activity;
        this.f = (SensorManager) context.getSystemService("sensor");
        this.g = this.f.getDefaultSensor(4);
        if (this.d) {
            this.f.registerListener(this, this.g, 0);
        }
        dji.thirdparty.a.c.a().a(this);
    }

    public void a(boolean z) {
        if (z) {
            this.f.registerListener(this, this.g, 0);
            this.d = true;
            return;
        }
        this.f.unregisterListener(this);
        this.d = false;
    }

    public void a() {
        if (this.d) {
            this.f.unregisterListener(this);
        }
        dji.thirdparty.a.c.a().d(this);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (this.a == -10000.0f) {
            this.a = (float) Math.toDegrees((double) sensorEvent.values[0]);
            this.b = (float) Math.toDegrees((double) sensorEvent.values[1]);
        }
        float toDegrees = (this.a * 0.618f) + (((float) Math.toDegrees((double) sensorEvent.values[0])) * 0.372f);
        float toDegrees2 = (this.b * 0.618f) + (((float) Math.toDegrees((double) sensorEvent.values[1])) * 0.372f);
        if (toDegrees > 120.0f) {
            toDegrees = 120.0f;
        }
        if (toDegrees < (-1123024896)) {
            toDegrees = -1123024896;
        }
        if (toDegrees2 > 120.0f) {
            toDegrees2 = 120.0f;
        }
        if (toDegrees2 < (-1123024896)) {
            toDegrees2 = -1123024896;
        }
        this.a = toDegrees;
        this.b = toDegrees2;
        this.c++;
        if (this.c == 5) {
            int b = b();
            if (b == 0) {
                DataGimbalSpeedControl.getInstance().setPermission(true).setYaw((int) ((-toDegrees2) * 10.0f)).setPitch(((int) toDegrees) * 10).start();
            } else if (b == 90) {
                DataGimbalSpeedControl.getInstance().setPermission(true).setPitch((int) ((-toDegrees2) * 10.0f)).setYaw(((int) (-toDegrees)) * 10).start();
            } else if (b == 270) {
                DataGimbalSpeedControl.getInstance().setPermission(true).setPitch((int) (toDegrees2 * 10.0f)).setYaw(((int) toDegrees) * 10).start();
            }
            this.c = 0;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onEventMainThread(j jVar) {
        if (jVar == j.START_SENSOR_CONTROL) {
            a(true);
        } else if (jVar == j.STOP_SENSOR_CONTROL) {
            a(false);
        }
    }

    public int b() {
        switch (this.e.getWindowManager().getDefaultDisplay().getRotation()) {
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                return 0;
        }
    }
}
