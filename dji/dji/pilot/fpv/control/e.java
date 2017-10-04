package dji.pilot.fpv.control;

import android.content.Context;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.MotionEvent;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetFocusParam;
import dji.midware.e.d;
import dji.pilot.fpv.b.b;
import dji.pilot.publics.objects.k;
import dji.pilot.publics.objects.k.a;
import dji.publics.widget.FpvPopWarnView;

public class e implements Callback, b, a {
    protected final Context a;
    protected float b = 0.1f;
    protected float c = 0.0f;
    protected int d = 100;
    protected int e = 100;
    protected long f = 0;
    protected k g = null;

    public e(Context context, int i) {
        this.a = context;
        this.b = 150.0f / ((float) i);
        this.g = new k(this, this);
    }

    public void a() {
        this.c = 0.0f;
    }

    public boolean a(MotionEvent motionEvent) {
        int i = 200;
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        x = (float) Math.sqrt((double) ((x * x) + (y * y)));
        if (0.0f == this.c) {
            this.c = x;
            this.d = this.e;
        }
        this.d = (int) (((float) this.d) + ((x - this.c) * this.b));
        if (this.d <= 200) {
            i = this.d;
        }
        this.d = i;
        this.d = this.d < 100 ? 100 : this.d;
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f >= 50) {
            this.f = currentTimeMillis;
            a(this.d);
            this.c = x;
        }
        return false;
    }

    private void a(final int i) {
        if (dji.pilot.fpv.d.b.r() && i >= 100 && i <= 200) {
            DataCameraSetFocusParam.getInstance().d(true).d(((float) i) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity).start(new d(this) {
                final /* synthetic */ e b;

                public void onSuccess(Object obj) {
                    DJILogHelper.getInstance().LOGD("Digital", "Digital scale[" + i + "]success", false, true);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD("Digital", "Digital scale[" + i + "]fail", false, true);
                }
            });
        }
    }

    private void c() {
        if (DataCameraGetPushStateInfo.getInstance().getVerstion() >= 4) {
            FpvPopWarnView.getInstance(this.a).pop(0, String.format("X%.1f", new Object[]{Float.valueOf(((float) this.e) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity)}), FpvPopWarnView.a.LENGTH_SHORT);
        }
    }

    public void a(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (dji.pilot.fpv.d.b.r()) {
            int digitalZoomScale = dataCameraGetPushShotParams.getDigitalZoomScale();
            if (this.e != digitalZoomScale) {
                this.e = digitalZoomScale;
                this.g.obtainMessage(4096).sendToTarget();
            }
        }
    }

    public float b() {
        return ((float) this.e) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 4096:
                c();
                break;
        }
        return false;
    }

    public boolean isFinished() {
        return false;
    }
}
