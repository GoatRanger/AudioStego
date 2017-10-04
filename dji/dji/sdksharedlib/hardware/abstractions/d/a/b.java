package dji.sdksharedlib.hardware.abstractions.d.a;

import dji.common.error.DJIError;
import dji.sdksharedlib.hardware.a.f;
import dji.sdksharedlib.hardware.a.h;
import dji.sdksharedlib.hardware.abstractions.b.e;
import java.util.ArrayList;
import java.util.List;

public class b extends h {

    private class a implements e {
        final /* synthetic */ b a;
        private f[] b;

        public a(b bVar, f[] fVarArr) {
            this.a = bVar;
            this.b = fVarArr;
        }

        public void a(Object obj) {
            if (obj != null) {
                for (f fVar : this.b) {
                    fVar.b.a(dji.sdksharedlib.e.a.a.getInstance().a(fVar.a).value);
                }
            }
        }

        public void a(DJIError dJIError) {
        }
    }

    public void a(String str, dji.sdksharedlib.hardware.a.e eVar) {
        a((Object) new f(str, eVar));
    }

    protected void a(List<Object> list) {
        f[] fVarArr = (f[]) list.toArray(new f[list.size()]);
        ArrayList arrayList = new ArrayList();
        for (f fVar : fVarArr) {
            arrayList.add(fVar.a);
        }
        dji.sdksharedlib.e.a.a.getInstance().a(arrayList, new a(this, fVarArr));
    }
}
