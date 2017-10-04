package dji.phone.d;

import android.util.Log;
import com.alipay.sdk.j.i;

public class c {
    public a a;
    private final String b = c.class.getSimpleName();
    private b c;
    private a d;
    private a e;

    public enum a {
        CAMERASTATE_TAKEPICTURE_PREVIEW,
        CAMERASTATE_TAKEPICTURE,
        CAMERASTATE_TAKEPICTURE_PANO_PREVIEW,
        CAMERASTATE_RECORD_PREVIEW,
        CAMERASTATE_RECORDING
    }

    public enum b {
        CAMERA_FRONT,
        CAMERA_BACK,
        CAMERA_ERROR
    }

    public synchronized b a() {
        return this.c;
    }

    public synchronized a b() {
        return this.d;
    }

    public synchronized void a(b bVar, boolean z) {
        Log.d(this.b, "setCameraType: " + bVar);
        this.c = bVar;
        switch (bVar) {
            case CAMERA_BACK:
                this.a.b();
                break;
            case CAMERA_FRONT:
                this.a.a();
                break;
        }
        this.a.b(dji.pilot.phonecamera.a.c.a().g(), false);
        this.a.a(dji.pilot.phonecamera.a.c.a().f(), false);
        this.a.d(dji.pilot.phonecamera.a.c.a().l(), false);
        if (z) {
            a(this.c);
        }
    }

    public synchronized void a(a aVar, boolean z) {
        if (this.d != aVar) {
            Log.d(this.b, "setCameraState: " + aVar.name());
            this.e = this.d;
            this.d = aVar;
            switch (this.d) {
                case CAMERASTATE_TAKEPICTURE_PREVIEW:
                    if (this.e == null) {
                        this.a.a(dji.phone.d.a.c.AUTO, false);
                        this.a.a(dji.phone.d.a.b.SINGLE, false);
                        this.a.a(dji.phone.d.a.a.SINGLE_0s, false);
                        break;
                    }
                    break;
                case CAMERASTATE_RECORD_PREVIEW:
                    if (this.e == null) {
                        this.a.a(dji.phone.d.a.b.SINGLE, false);
                        this.a.a(dji.phone.d.a.a.SINGLE_0s, false);
                        this.a.a(dji.phone.d.a.c.AUTO, false);
                        break;
                    }
                    break;
            }
            if (z) {
                a(this.d);
            }
        }
    }

    private void a(b bVar) {
        dji.thirdparty.a.c.a().e(bVar);
    }

    private void a(a aVar) {
        dji.thirdparty.a.c.a().e(aVar);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Camera Id {").append(this.c).append(",camera state = ").append(this.d).append(", mCamera status info = ").append(this.a.toString());
        stringBuilder.append(i.d);
        return stringBuilder.toString();
    }
}
