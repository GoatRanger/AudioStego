package dji.pilot.publics.objects;

import dji.log.WM220LogUtil;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.publics.objects.DJIGlobalService.25;

class DJIGlobalService$25$5 implements d {
    final /* synthetic */ 25 a;

    DJIGlobalService$25$5(25 25) {
        this.a = 25;
    }

    public void onSuccess(Object obj) {
        WM220LogUtil.LOGD("**into DataRcSetRcUnitNLang onSuccess");
    }

    public void onFailure(a aVar) {
        WM220LogUtil.LOGD("**into DataRcSetRcUnitNLang onFailure");
    }
}
