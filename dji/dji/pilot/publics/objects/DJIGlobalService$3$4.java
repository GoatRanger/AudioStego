package dji.pilot.publics.objects;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycGetPlaneName;
import dji.midware.e.d;
import dji.pilot.publics.objects.DJIGlobalService.3;

class DJIGlobalService$3$4 implements d {
    final /* synthetic */ 3 a;

    DJIGlobalService$3$4(3 3) {
        this.a = 3;
    }

    public void onSuccess(Object obj) {
        DJIGlobalService.e = DataFlycGetPlaneName.getInstance().getName();
        DJILogHelper.getInstance().LOGD(DJIGlobalService.b(this.a.a), "DataFlycGetPlaneName success " + DJIGlobalService.e, false, true);
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD(DJIGlobalService.b(this.a.a), "DataFlycGetPlaneName=" + aVar, false, true);
    }
}
