package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.common.util.MultiModeEnabledUtil;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycNavigationSwitch;
import dji.midware.data.model.P3.DataFlycNavigationSwitch.GS_COMMAND;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$13 implements e {
    final /* synthetic */ e a;
    final /* synthetic */ d b;

    d$13(d dVar, e eVar) {
        this.b = dVar;
        this.a = eVar;
    }

    public void a(Object obj) {
        if (!MultiModeEnabledUtil.verifyRCMode(this.a)) {
            DataFlycNavigationSwitch.getInstance().setCommand(GS_COMMAND.OPEN_GROUND_STATION).start(new d(this) {
                final /* synthetic */ d$13 a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    if (this.a.a == null) {
                        return;
                    }
                    if (DataFlycNavigationSwitch.getInstance().getResult() == 0) {
                        CallbackUtils.onSuccess(this.a.a, null);
                    } else {
                        CallbackUtils.onFailure(this.a.a, d.a(DataFlycNavigationSwitch.getInstance().getResult()));
                    }
                }

                public void onFailure(a aVar) {
                    if (this.a.a != null) {
                        CallbackUtils.onFailure(this.a.a, DJIError.getDJIError(aVar));
                    }
                }
            });
        }
    }

    public void a(DJIError dJIError) {
        CallbackUtils.onFailure(this.a, dJIError);
    }
}
