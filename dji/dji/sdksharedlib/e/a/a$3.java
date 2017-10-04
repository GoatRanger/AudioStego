package dji.sdksharedlib.e.a;

import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;
import java.util.ArrayList;

class a$3 implements d {
    final /* synthetic */ String[] a;
    final /* synthetic */ e b;
    final /* synthetic */ a c;

    a$3(a aVar, String[] strArr, e eVar) {
        this.c = aVar;
        this.a = strArr;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (String str : this.a) {
            dji.midware.data.manager.P3.d.getInstance();
            ParamInfo read = dji.midware.data.manager.P3.d.read(str);
            if (read != null) {
                arrayList.add(read);
            }
        }
        this.b.a(arrayList);
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.b, aVar);
    }
}
