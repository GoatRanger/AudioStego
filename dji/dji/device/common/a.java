package dji.device.common;

import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataGimbalActiveStatus;
import dji.midware.data.model.b.a.b;
import dji.midware.e.d;

public class a {
    private static final String a = "DeviceInfo";
    private static a b = new a();

    public a a() {
        return b;
    }

    public void b() {
        DataGimbalActiveStatus.getInstance().getSN();
        DataGimbalActiveStatus.getInstance().setVersion(dji.midware.data.model.b.a.a.b).setType(b.b).start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DataGimbalActiveStatus.getInstance().getSN();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(a.a, "DJIMethod : onFailure (1003)fuck", false, true);
            }
        });
    }
}
