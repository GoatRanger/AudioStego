package dji.pilot.longan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.RelativeLayout.LayoutParams;
import dji.log.DJILog;
import dji.midware.e.h;
import dji.midware.media.h.b.b;
import dji.midware.media.h.e;

public class LonganVideoSurfaceView extends SurfaceView implements Callback, h {
    private static final String a = "VideoSurfaceView";
    private b b;
    private a c;
    private int d;
    private int e;

    public interface a {
        a onRenderCreated(b bVar);

        void onRenderCreatedPrepared();

        void onRenderDestroy();
    }

    public LonganVideoSurfaceView(Context context) {
        super(context);
        a();
    }

    public LonganVideoSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        if (!isInEditMode()) {
            System.out.println("VideoSurfaceView lcd");
            getHolder().setFormat(4);
            getHolder().setKeepScreenOn(true);
            getHolder().addCallback(this);
            DJILog.d(a, "addCallback");
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        DJILog.d(a, "surfaceCreated");
        this.c.onRenderCreatedPrepared();
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        this.b = e.a();
        this.b.a(surfaceHolder, layoutParams.width, layoutParams.height, 0, this.d);
        this.c.onRenderCreated(this.b);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        DJILog.d(a, "surfaceChanged");
        if (this.b != null) {
            this.b.a(i2, i3, 0, this.d);
            this.b.onFrameAvailable(null);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        DJILog.d(a, "surfaceChanged");
        this.b.c();
        this.c.onRenderDestroy();
    }

    public void setRotateDegree(int i) {
        this.d = i;
    }

    public void setViewX(int i) {
        this.e = i;
    }

    public Bitmap getBitmap() {
        if (this.b != null) {
            return this.b.b(getWidth(), getHeight());
        }
        return null;
    }

    public void oneFrameComeIn() {
    }

    public void resetVideoSurface(int i, int i2) {
    }

    protected void onMeasure(int i, int i2) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        setMeasuredDimension(layoutParams.width, layoutParams.height);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public void setRenderListener(a aVar) {
        this.c = aVar;
    }

    public void setRenderer() {
    }
}
