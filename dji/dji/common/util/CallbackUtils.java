package dji.common.util;

import dji.common.error.DJIError;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

public class CallbackUtils {
    public static void onSuccess(e eVar, Object obj) {
        if (eVar != null) {
            eVar.a(obj);
        }
    }

    public static void onFailure(e eVar, a aVar) {
        if (eVar != null) {
            eVar.a(DJIError.getDJIError(aVar));
        }
    }

    public static void onFailure(e eVar) {
        if (eVar != null) {
            eVar.a(null);
        }
    }

    public static void onFailure(e eVar, DJIError dJIError) {
        if (eVar != null) {
            eVar.a(dJIError);
        }
    }

    public static d getSetterDJIDataCallback(final e eVar) {
        return new d() {
            public void onSuccess(Object obj) {
                CallbackUtils.onSuccess(eVar, null);
            }

            public void onFailure(a aVar) {
                CallbackUtils.onFailure(eVar, aVar);
            }
        };
    }
}
