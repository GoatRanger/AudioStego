package dji.pilot.view;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera.Area;
import android.os.Handler;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnTouchListener;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.phonecamera.e.g;
import java.util.ArrayList;
import java.util.List;

public class CameraPreview extends TextureView {
    public static final String a = "CameraView";
    dji.pilot.phonecamera.e.a b = new dji.pilot.phonecamera.e.a(this) {
        final /* synthetic */ CameraPreview a;

        {
            this.a = r1;
        }

        public void a(boolean z, g gVar) {
            if (z) {
                gVar.g();
            }
        }
    };
    private dji.pilot.phonecamera.g c;
    private Context d;
    private DrawingView e;

    private final class a implements OnTouchListener {
        private static final int b = 0;
        private static final int c = 1;
        final /* synthetic */ CameraPreview a;
        private int d;
        private float e;

        private a(CameraPreview cameraPreview) {
            this.a = cameraPreview;
            this.d = 0;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int i = 0;
            switch (motionEvent.getAction() & 255) {
                case 0:
                    this.d = 0;
                    if (this.a.c.a(dji.pilot.phonecamera.c.a.AUTOFUCUS)) {
                        this.a.onTouchEvent(motionEvent);
                        break;
                    }
                    break;
                case 1:
                    if (this.d != 1) {
                        this.a.c.a(new Handler(this.a.d.getMainLooper()), this.a.b);
                        break;
                    }
                    break;
                case 2:
                    if (this.d == 1 && motionEvent.getPointerCount() >= 2) {
                        float a = a(motionEvent);
                        int i2 = (int) ((a - this.e) / 10.0f);
                        if (i2 >= 1 || i2 <= -1) {
                            i2 += this.a.c.m().getZoom();
                            if (i2 > this.a.c.m().getMaxZoom()) {
                                i2 = this.a.c.m().getMaxZoom();
                            }
                            if (i2 >= 0) {
                                i = i2;
                            }
                            this.a.c.c(i);
                            this.e = a;
                            break;
                        }
                    }
                case 5:
                    this.d = 1;
                    this.e = a(motionEvent);
                    break;
            }
            return true;
        }

        private float a(MotionEvent motionEvent) {
            float x = motionEvent.getX(1) - motionEvent.getX(0);
            float y = motionEvent.getY(1) - motionEvent.getY(0);
            return (float) Math.sqrt((double) ((x * x) + (y * y)));
        }
    }

    public CameraPreview(Context context, DrawingView drawingView) {
        super(context);
        this.d = context;
        Log.e(a, "CameraPreview: ");
        this.e = drawingView;
        setOnTouchListener(new a());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.d(a, "onTouchEvent: " + motionEvent);
        if (motionEvent.getAction() == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            Rect rect = new Rect((int) (x - DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity), (int) (y - DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity), (int) (x + DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity), (int) (y + DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity));
            doTouchFocus(new Rect(((rect.left * 2000) / getWidth()) + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, ((rect.top * 2000) / getHeight()) + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, ((rect.right * 2000) / getWidth()) + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, ((rect.bottom * 2000) / getHeight()) + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED));
            this.e.setHaveTouch(true, rect);
            this.e.invalidate();
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ CameraPreview a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.e.setHaveTouch(false, new Rect(0, 0, 0, 0));
                    this.a.e.invalidate();
                }
            }, 1000);
        }
        return false;
    }

    public void setCameraModule(dji.pilot.phonecamera.g gVar) {
        this.c = gVar;
        this.c.a(getSurfaceTexture());
    }

    public void doTouchFocus(Rect rect) {
        Log.d(a, "TouchFocus");
        try {
            List arrayList = new ArrayList();
            arrayList.add(new Area(rect, 1000));
            this.c.a(arrayList);
            this.c.b(arrayList);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(a, "Unable to autofocus");
        }
    }
}
