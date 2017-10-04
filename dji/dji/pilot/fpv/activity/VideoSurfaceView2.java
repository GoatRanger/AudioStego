package dji.pilot.fpv.activity;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import dji.midware.e.h;
import dji.midware.media.h.e;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.publics.c.f;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class VideoSurfaceView2 extends GLSurfaceView implements h {
    private a a = null;
    private dji.midware.media.h.b.b b;
    private b c;
    private boolean d = false;
    private boolean e = false;

    public class a implements Renderer {
        final /* synthetic */ VideoSurfaceView2 a;

        public a(VideoSurfaceView2 videoSurfaceView2) {
            this.a = videoSurfaceView2;
        }

        public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            Log.i("DJIGLRender", String.format("Thread %s calls onSurfaceCreated'", new Object[]{Thread.currentThread().getName()}));
        }

        public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        }

        public void onDrawFrame(GL10 gl10) {
            if (this.a.b == null) {
            }
        }
    }

    public interface b {
        f a(dji.midware.media.h.b.b bVar);

        void a();
    }

    public VideoSurfaceView2(Context context) {
        super(context);
        System.out.println("VideoSurfaceView 1");
    }

    public VideoSurfaceView2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        System.out.println("VideoSurfaceView lcd");
        setEGLContextClientVersion(2);
        setDebugFlags(3);
        this.b = e.a();
        this.b.a(DJIGenSettingDataManager.getInstance().F(), R.raw.overexposure);
    }

    public void setRenderer() {
        this.a = new a(this);
        setRenderer(this.a);
        if (this.d) {
            setRenderMode(0);
        } else {
            setRenderMode(1);
        }
    }

    public void setRenderListener(b bVar) {
        this.c = bVar;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.c != null) {
            this.c.a();
        }
        this.b.c();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        super.surfaceCreated(surfaceHolder);
        Log.i("DJIGLRender", String.format("Thread %s calls surfaceCreated'", new Object[]{Thread.currentThread().getName()}));
        getWidth();
        getHeight();
        if (!this.e) {
            this.e = true;
            this.b.a(getHolder(), 1920, dji.midware.media.h.b.a.e);
        }
        if (this.c != null) {
            f a = this.c.a(this.b);
            if (a != null) {
                a.a((h) this);
            }
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        super.surfaceDestroyed(surfaceHolder);
    }

    public void oneFrameComeIn() {
        if (this.d) {
            requestRender();
        }
    }

    public void resetVideoSurface(int i, int i2) {
    }
}
