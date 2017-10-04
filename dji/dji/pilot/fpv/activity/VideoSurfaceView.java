package dji.pilot.fpv.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.RelativeLayout.LayoutParams;
import dji.midware.e.h;
import dji.midware.media.h.b.b;
import dji.midware.media.h.e;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;

public class VideoSurfaceView extends SurfaceView implements h {
    private b a;
    private a b;
    private int c;
    private int d;

    public VideoSurfaceView(Context context) {
        super(context);
        System.out.println("VideoSurfaceView 1");
    }

    public VideoSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            System.out.println("VideoSurfaceView lcd");
            getHolder().setFormat(4);
            getHolder().setKeepScreenOn(true);
            getHolder().addCallback(new Callback(this) {
                final /* synthetic */ VideoSurfaceView a;

                {
                    this.a = r1;
                }

                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    this.a.b.x();
                    LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
                    this.a.a = e.a();
                    this.a.a.a(DJIGenSettingDataManager.getInstance().F(), R.raw.overexposure);
                    this.a.a.a(this.a.getHolder(), layoutParams.width, layoutParams.height, 0, this.a.c);
                    this.a.b.a(this.a.a);
                }

                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                    if (this.a.a != null) {
                        this.a.a.a(i2, i3, 0, this.a.c);
                        this.a.a.onFrameAvailable(null);
                    }
                }

                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    this.a.a.c();
                    this.a.b.y();
                }
            });
        }
    }

    public void setRotateDegree(int i) {
        this.c = i;
    }

    public void setViewX(int i) {
        this.d = i;
    }

    public Bitmap getBitmap() {
        if (this.a != null) {
            return this.a.b(getWidth(), getHeight());
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
        this.b = aVar;
    }

    public void setRenderer() {
    }

    public void onResume() {
    }

    public void onPause() {
    }
}
