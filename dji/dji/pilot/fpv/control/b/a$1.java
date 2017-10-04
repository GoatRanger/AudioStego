package dji.pilot.fpv.control.b;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class a$1 implements d {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void onSuccess(Object obj) {
        a.a(this.a).sendEmptyMessage(2);
        DJILogHelper.getInstance().LOGD("djiaDJIHereMapReportManager", "Get Sn Success:" + a.b(this.a).getSN(), true, false);
    }

    public void onFailure(a aVar) {
        a.a(this.a).sendEmptyMessage(3);
        DJILogHelper.getInstance().LOGD("djiaDJIHereMapReportManager", "Get Sn Fail ccode:" + aVar.name(), true, false);
    }
}
