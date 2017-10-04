package dji.common.util;

import dji.common.error.DJIFlightControllerError;
import dji.midware.c.a.c;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.params.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

public class MultiModeEnabledUtil {
    public static void setMultiModeEnabled(final e eVar) {
        String[] strArr = new String[]{a.B, "g_config.control.multi_control_mode_enable_0"};
        Number[] numberArr = new Number[]{Integer.valueOf(0), Integer.valueOf(1)};
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a(strArr);
        dataFlycSetParams.a(numberArr);
        dataFlycSetParams.start(new d() {
            public void onSuccess(Object obj) {
                CallbackUtils.onSuccess(eVar, null);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                CallbackUtils.onFailure(eVar, aVar);
            }
        });
    }

    public static boolean verifyRCMode(e eVar) {
        if (dji.midware.c.a.getInstance().a() != null && dji.midware.c.a.getInstance().a().equals(c.OSMO)) {
            return false;
        }
        if (dji.midware.c.a.getInstance().a().equals(c.P4)) {
            if (DataRcGetPushParams.getInstance().getMode() == 1) {
                return false;
            }
            if (eVar != null) {
                CallbackUtils.onFailure(eVar, DJIFlightControllerError.MISSION_RESULT_RC_MODE_ERROR);
            }
            return true;
        } else if (DataRcGetPushParams.getInstance().getMode() == 2) {
            return false;
        } else {
            if (eVar != null) {
                CallbackUtils.onFailure(eVar, DJIFlightControllerError.MISSION_RESULT_RC_MODE_ERROR);
            }
            return true;
        }
    }
}
