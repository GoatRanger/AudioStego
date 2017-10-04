package dji.pilot.publics.objects;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.publics.objects.DJIGlobalService.3;

class DJIGlobalService$3$2 implements d {
    final /* synthetic */ 3 a;

    DJIGlobalService$3$2(3 3) {
        this.a = 3;
    }

    public void onSuccess(Object obj) {
        DJIGlobalService.a(this.a.a).sendEmptyMessage(8192);
    }

    public void onFailure(a aVar) {
    }
}
