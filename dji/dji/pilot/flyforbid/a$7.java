package dji.pilot.flyforbid;

import dji.midware.data.config.P3.a;
import dji.midware.data.forbid.NFZLogUtil;
import dji.midware.data.model.P3.DataFlycActiveStatus;
import dji.midware.e.d;
import dji.pilot.publics.objects.DJIGlobalService;

class a$7 implements d {
    final /* synthetic */ DataFlycActiveStatus a;
    final /* synthetic */ a b;

    a$7(a aVar, DataFlycActiveStatus dataFlycActiveStatus) {
        this.b = aVar;
        this.a = dataFlycActiveStatus;
    }

    public void onSuccess(Object obj) {
        DJIGlobalService.f = this.a.getSN();
    }

    public void onFailure(a aVar) {
        a.l(this.b);
        if (a.m(this.b) == 3) {
            a.c(this.b, 0);
            return;
        }
        NFZLogUtil.LOGD("**getFlycSnFromRemote onFailure");
        a.j(this.b).sendEmptyMessageDelayed(2, 100);
    }
}
