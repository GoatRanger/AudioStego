package com.amap.api.mapcore.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.RemoteException;
import android.view.WindowManager;
import com.amap.api.maps.model.Marker;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import dji.common.flightcontroller.DJIFlightControllerDataType;

public class as implements SensorEventListener {
    private SensorManager a;
    private Sensor b;
    private long c = 0;
    private final int d = 100;
    private float e;
    private Context f;
    private IAMapDelegate g;
    private Marker h;

    public as(Context context, IAMapDelegate iAMapDelegate) {
        this.f = context;
        this.g = iAMapDelegate;
        this.a = (SensorManager) context.getSystemService("sensor");
        this.b = this.a.getDefaultSensor(3);
    }

    public void a() {
        this.a.registerListener(this, this.b, 3);
    }

    public void b() {
        this.a.unregisterListener(this, this.b);
    }

    public void a(Marker marker) {
        this.h = marker;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (System.currentTimeMillis() - this.c >= 100 && this.g.getCameraAnimator().isFinished()) {
            switch (sensorEvent.sensor.getType()) {
                case 3:
                    float a = (sensorEvent.values[0] + ((float) a(this.f))) % 360.0f;
                    if (a > 180.0f) {
                        a -= 360.0f;
                    } else if (a < DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngle) {
                        a += 360.0f;
                    }
                    if (Math.abs(this.e - a) >= 3.0f) {
                        if (Float.isNaN(a)) {
                            a = 0.0f;
                        }
                        this.e = a;
                        if (this.h != null) {
                            try {
                                this.g.moveCamera(CameraUpdateFactoryDelegate.changeBearing(this.e));
                                this.h.setRotateAngle(-this.e);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                        this.c = System.currentTimeMillis();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public static int a(Context context) {
        switch (((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 0:
                return 0;
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return -90;
            default:
                return 0;
        }
    }
}
