package dji.pilot.fpv.b;

import android.view.MotionEvent;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;

public interface b {
    public static final int h = 4096;
    public static final int i = 150;
    public static final int j = 200;
    public static final int k = 100;
    public static final int l = 50;

    void a();

    void a(DataCameraGetPushShotParams dataCameraGetPushShotParams);

    boolean a(MotionEvent motionEvent);

    float b();
}
