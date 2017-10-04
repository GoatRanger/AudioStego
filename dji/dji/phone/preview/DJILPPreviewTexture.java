package dji.phone.preview;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera.Area;
import android.support.v4.app.NotificationManagerCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View.MeasureSpec;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.f.a;
import dji.phone.DJILPTouchLogicView;
import dji.phone.e.b;
import dji.phone.j.c;
import dji.phone.j.d;
import dji.phone.widget.DJILPUISwitcher;
import dji.pilot.phonecamera.e;
import dji.pilot.phonecamera.e.g;
import java.util.ArrayList;
import java.util.List;

public class DJILPPreviewTexture extends TextureView implements SurfaceTextureListener {
    private static final String a = "DJILPPreview";
    private c b;
    private c c;

    public DJILPPreviewTexture(Context context) {
        super(context);
        setSurfaceTextureListener(this);
    }

    public DJILPPreviewTexture(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setSurfaceTextureListener(this);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(i), MeasureSpec.getSize(i2));
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a.a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onDestory();
    }

    public void doTouchFocus(Rect rect) {
        List arrayList = new ArrayList();
        arrayList.add(new Area(rect, 1000));
        dji.phone.c.a.c().a(arrayList);
        dji.phone.c.a.c().b(arrayList);
        dji.phone.c.a.c().a(getHandler(), new e.a(this) {
            final /* synthetic */ DJILPPreviewTexture a;

            {
                this.a = r1;
            }

            public void a(boolean z, g gVar) {
                dji.phone.c.a.c().n();
            }
        });
    }

    private void a(int i, int i2) {
    }

    private void a() {
        if (this.c != null) {
            this.c.interrupt();
            this.c = null;
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.b = d.getInstance().a(getContext(), surfaceTexture, i, i2);
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        this.b.a(i, i2);
        if (this.c != null) {
            this.c.a(i, i2);
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        if (this.b != null) {
            this.b.interrupt();
            this.b = null;
        }
        a();
        return true;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onDestory() {
        if (this.b != null) {
            this.b.interrupt();
            this.b = null;
        }
        a();
        a.b(this);
    }

    public void onEventMainThread(b bVar) {
        if (bVar.b == dji.phone.e.a.a.f) {
            doTouchFocus(a(DJILPTouchLogicView.b));
        }
    }

    private Rect a(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        Rect rect = new Rect((int) (x - DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity), (int) (y - DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity), (int) (x + DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity), (int) (y + DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity));
        return new Rect(((rect.left * 2000) / getWidth()) + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, ((rect.top * 2000) / getHeight()) + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, ((rect.right * 2000) / getWidth()) + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, ((rect.bottom * 2000) / getHeight()) + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
    }

    public void onEventMainThread(dji.phone.e.a.d dVar) {
        if (dVar.a == dji.phone.e.a.a.b && DJILPUISwitcher.a != dji.phone.g.b.TRACKING) {
            doTouchFocus(a((MotionEvent) dVar.b));
        }
    }
}
