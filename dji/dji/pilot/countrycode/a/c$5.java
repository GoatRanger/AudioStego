package dji.pilot.countrycode.a;

import android.text.TextUtils;
import dji.common.error.DJIError;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.sdksharedlib.c.c;
import dji.sdksharedlib.d.a;

class c$5 implements c {
    final /* synthetic */ c a;

    c$5(c cVar) {
        this.a = cVar;
    }

    public void a(a aVar) {
        String str = "";
        if (aVar != null) {
            str = (String) aVar.e();
            if (!TextUtils.isEmpty(str)) {
                c.a(this.a, str);
            }
        }
        a.a("DJIGlobalService.flycSn=" + DJIGlobalService.f);
        c.b(this.a);
    }

    public void a(DJIError dJIError) {
        a.a("DJIGlobalService.flycSn=" + DJIGlobalService.f);
        c.b(this.a);
    }
}
