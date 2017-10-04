package dji.pilot.publics.objects;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.publics.objects.DJIGlobalService.3;

class DJIGlobalService$3$10 implements d {
    final /* synthetic */ 3 a;

    DJIGlobalService$3$10(3 3) {
        this.a = 3;
    }

    public void onSuccess(Object obj) {
        DJIGlobalService.g = DJIGlobalService.f(this.a.a).getSN();
        g.a(this.a.a.getApplicationContext(), "device_sn", DJIGlobalService.g);
        DJILogHelper.getInstance().LOGD(DJIGlobalService.b(this.a.a), "cameraActiveStatus success " + DJIGlobalService.g, false, true);
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD(DJIGlobalService.b(this.a.a), "cameraActiveStatus=" + aVar, false, true);
    }
}
