package dji.pilot.fpv.control;

import android.content.Context;
import android.view.OrientationEventListener;
import dji.common.camera.DJICameraSettingsDef.CameraOrientation;
import dji.sdksharedlib.b.b;

public class h {
    private Context a = null;
    private OrientationEventListener b = null;
    private CameraOrientation c = null;
    private a d;

    public interface a {
        void a();

        void b();
    }

    public h(Context context, a aVar) {
        this.a = context;
        this.c = (CameraOrientation) dji.sdksharedlib.a.a.b(b.bW);
        this.d = aVar;
        this.b = new OrientationEventListener(this, context) {
            final /* synthetic */ h a;

            public void onOrientationChanged(int i) {
                if (this.a.a(i)) {
                    this.a.d.a();
                } else if (this.a.b(i)) {
                    this.a.d.b();
                }
            }
        };
        this.b.enable();
    }

    private boolean a(int i) {
        return (i >= 0 && i < 30) || i > 330 || (i > 150 && i < 210);
    }

    private boolean b(int i) {
        return (i > 60 && i < 120) || (i > 240 && i < 300);
    }

    public void a() {
        this.a = null;
        if (this.b != null) {
            this.b.disable();
            this.b = null;
        }
    }
}
