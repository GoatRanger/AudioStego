package dji.pilot2.scan.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import dji.pilot.phonecamera.h;
import java.lang.reflect.Method;

final class b {
    private static final String a = "CameraConfiguration";
    private final Context b;
    private Point c;
    private Point d;

    b(Context context) {
        this.b = context;
    }

    @SuppressLint({"NewApi"})
    void a(Camera camera) {
        Parameters parameters = camera.getParameters();
        Display defaultDisplay = ((WindowManager) this.b.getSystemService("window")).getDefaultDisplay();
        this.c = new Point(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        Log.i(a, "Screen resolution: " + this.c);
        Point point = new Point();
        point.x = this.c.x;
        point.y = this.c.y;
        if (this.c.x < this.c.y) {
            point.x = this.c.y;
            point.y = this.c.x;
        }
        this.d = c.a(parameters, point);
        Log.i(a, "Camera resolution: " + this.d);
    }

    void a(Camera camera, boolean z) {
        Parameters parameters = camera.getParameters();
        if (parameters == null) {
            Log.w(a, "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        Log.i(a, "Initial camera parameters: " + parameters.flatten());
        if (z) {
            Log.w(a, "In camera config safe mode -- most settings will not be honored");
        }
        PreferenceManager.getDefaultSharedPreferences(this.b);
        parameters.setFocusMode(h.a);
        parameters.setPreviewSize(this.d.x, this.d.y);
        a(camera, 90);
        Log.i(a, "Final camera parameters: " + parameters.flatten());
        camera.setParameters(parameters);
        camera.cancelAutoFocus();
        Size previewSize = camera.getParameters().getPreviewSize();
        if (previewSize == null) {
            return;
        }
        if (this.d.x != previewSize.width || this.d.y != previewSize.height) {
            Log.w(a, "Camera said it supported preview size " + this.d.x + 'x' + this.d.y + ", but after setting it, preview size is " + previewSize.width + 'x' + previewSize.height);
            this.d.x = previewSize.width;
            this.d.y = previewSize.height;
        }
    }

    void a(Camera camera, int i) {
        try {
            Method method = camera.getClass().getMethod("setDisplayOrientation", new Class[]{Integer.TYPE});
            if (method != null) {
                method.invoke(camera, new Object[]{Integer.valueOf(i)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Point a() {
        return this.d;
    }

    Point b() {
        return this.c;
    }
}
