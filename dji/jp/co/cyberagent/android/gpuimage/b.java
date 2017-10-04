package jp.co.cyberagent.android.gpuimage;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import com.tencent.android.tpush.common.Constants;

public class b {
    private final h a;
    private GLSurfaceView b;
    private e c;

    public interface a<T> {
        void a(T t);
    }

    public enum b {
        CENTER_INSIDE,
        CENTER_CROP
    }

    public b(Context context) {
        if (a(context)) {
            this.c = new e();
            this.a = new h(this.c);
            return;
        }
        throw new IllegalStateException("OpenGL ES 2.0 is not supported on this phone.");
    }

    public h a() {
        return this.a;
    }

    private boolean a(Context context) {
        return ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getDeviceConfigurationInfo().reqGlEsVersion >= 131072;
    }

    public void a(GLSurfaceView gLSurfaceView) {
        this.b = gLSurfaceView;
        this.b.setEGLContextClientVersion(2);
        this.b.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        this.b.getHolder().setFormat(1);
        this.b.setRenderer(this.a);
        this.b.setRenderMode(0);
        this.b.requestRender();
    }

    public void b() {
        if (this.b != null) {
            this.b.requestRender();
        }
    }

    public void a(e eVar) {
        this.c = eVar;
        this.a.a(this.c);
        b();
    }

    public void a(Bitmap bitmap) {
        this.a.a(bitmap, true);
        b();
    }

    public void a(z zVar) {
        this.a.a(zVar);
    }

    public void c() {
        this.a.a();
        b();
    }

    void a(Runnable runnable) {
        this.a.b(runnable);
    }
}
