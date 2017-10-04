package dji.pilot.active;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;

class DJIActiveController$3 implements d {
    final /* synthetic */ DataCommonGetVersion a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ DJIActiveController d;

    DJIActiveController$3(DJIActiveController dJIActiveController, DataCommonGetVersion dataCommonGetVersion, String str, String str2) {
        this.d = dJIActiveController;
        this.a = dataCommonGetVersion;
        this.b = str;
        this.c = str2;
    }

    public void onSuccess(Object obj) {
        DJILogHelper.getInstance().LOGI("DJIActiveController", "DJIMethod : onSuccess (1215)postActiveTermsRecord:version success", "tremLog");
        try {
            DJIActiveController.a(this.d, this.a.getFirmVer("."), this.b, this.c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGI("DJIActiveController", "DJIMethod : onFailure (1215)postActiveTermsRecord:version onFailure", "tremLog");
        try {
            DJIActiveController.a(this.d, "0.0.0.0", this.b, this.c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
