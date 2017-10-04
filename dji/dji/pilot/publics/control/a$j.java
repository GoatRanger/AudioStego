package dji.pilot.publics.control;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.thirdparty.a.c;

class a$j extends a$i {
    final /* synthetic */ a c;

    private a$j(a aVar) {
        this.c = aVar;
        super(aVar);
    }

    public void run() {
        this.a.startForce(new d(this) {
            final /* synthetic */ a$j a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "getVertions " + this.a.a.getDeviceType() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + a.b(this.a.c, this.a.a) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.a.a.getFirmVer("."), true, true);
                a.c(this.a.c, this.a.a);
                a.l(this.a.c);
                c.a().e(this.a.a);
            }

            public void onFailure(a aVar) {
                DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "getVertions " + this.a.a.getDeviceType() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + a.b(this.a.c, this.a.a) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + aVar, true, true);
                a.l(this.a.c);
            }
        }, 500, 1, false);
        this.a.join();
    }
}
