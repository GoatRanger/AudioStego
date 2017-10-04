package dji.sdksharedlib.hardware.abstractions.h;

import dji.common.error.DJIRemoteControllerError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcGetPassword;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;
import java.text.NumberFormat;

class a$4 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$4(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        int i = 9999;
        int pw = DataRcGetPassword.getInstance().getPw();
        if (pw <= 9999) {
            i = pw;
        }
        if (i < 0) {
            i = 0;
        }
        NumberFormat instance = NumberFormat.getInstance();
        instance.setGroupingUsed(false);
        instance.setMaximumIntegerDigits(4);
        instance.setMinimumIntegerDigits(4);
        String format = instance.format((long) i);
        if (this.a != null) {
            this.a.a(format);
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJIRemoteControllerError.getDJIError(aVar));
        }
    }
}
