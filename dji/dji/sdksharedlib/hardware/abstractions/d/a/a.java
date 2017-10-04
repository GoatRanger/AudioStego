package dji.sdksharedlib.hardware.abstractions.d.a;

import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.a.h;
import java.util.List;

public class a extends h {

    private class a implements d {
        final /* synthetic */ a a;
        private b[] b;

        public a(a aVar, b[] bVarArr) {
            this.a = aVar;
            this.b = bVarArr;
        }

        public void onSuccess(Object obj) {
            for (b bVar : this.b) {
                bVar.b.a(dji.midware.data.manager.P3.d.read(bVar.a));
            }
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            for (b bVar : this.b) {
                bVar.b.a(aVar);
            }
        }
    }

    public static class b {
        public String a;
        public c b;

        public b(String str, c cVar) {
            this.a = str;
            this.b = cVar;
        }
    }

    public void a(String str, c cVar) {
        a((Object) new b(str, cVar));
    }

    protected void a(List<Object> list) {
        int size = list.size();
        String[] strArr = new String[size];
        b[] bVarArr = new b[size];
        for (int i = 0; i < size; i++) {
            bVarArr[i] = (b) list.get(i);
            strArr[i] = bVarArr[i].a;
        }
        DataFlycGetParams.getInstance().setInfos(strArr).start(new a(this, bVarArr));
    }
}
