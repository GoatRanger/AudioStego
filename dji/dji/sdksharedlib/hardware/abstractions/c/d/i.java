package dji.sdksharedlib.hardware.abstractions.c.d;

import dji.common.error.DJICameraError;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode.OpticsZommMode;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode.ZoomSpeed;
import dji.midware.e.d;
import dji.sdksharedlib.b.b;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.c.a;
import java.nio.ByteBuffer;

public class i extends f {
    private static String I = "X5RHandHeldCamera";

    protected void a() {
        a(b.class, getClass());
    }

    protected boolean E() {
        return true;
    }

    protected boolean F() {
        return true;
    }

    protected boolean y() {
        return true;
    }

    protected boolean s() {
        return true;
    }

    protected boolean B() {
        return true;
    }

    protected boolean L() {
        return true;
    }

    protected boolean K() {
        return true;
    }

    protected String M() {
        return a.i;
    }

    protected void h(int i, final e eVar) {
        byte[] array = ByteBuffer.allocate(4).putInt(i).array();
        new DataCameraSetOpticsZoomMode().a(OpticsZommMode.b, ZoomSpeed.d, new Byte(array[3]).intValue(), new Byte(array[2]).intValue()).start(new d(this) {
            final /* synthetic */ i b;

            public void onSuccess(Object obj) {
                if (eVar != null) {
                    eVar.a(null);
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (eVar != null) {
                    eVar.a(DJICameraError.getDJIError(aVar));
                }
            }
        });
    }
}
