package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycNavigationSwitch;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$11 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ d b;

    d$11(d dVar, e eVar) {
        this.b = dVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        if (this.a == null) {
            return;
        }
        if (DataFlycNavigationSwitch.getInstance().getResult() == 0) {
            CallbackUtils.onSuccess(this.a, null);
        } else {
            CallbackUtils.onFailure(this.a, d.a(DataFlycNavigationSwitch.getInstance().getResult()));
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            CallbackUtils.onFailure(this.a, DJIError.getDJIError(aVar));
        }
    }
}
