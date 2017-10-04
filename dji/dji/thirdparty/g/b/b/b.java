package dji.thirdparty.g.b.b;

import dji.thirdparty.g.c.c;
import dji.thirdparty.g.e;
import java.util.ArrayList;
import java.util.Collections;

public class b {
    public final f a;
    public final ArrayList b;

    public b(f fVar, ArrayList arrayList) {
        this.a = fVar;
        this.b = arrayList;
    }

    public ArrayList a() throws e {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.a);
        for (int i = 0; i < this.b.size(); i++) {
            c cVar = (c) this.b.get(i);
            arrayList.add(cVar);
            ArrayList arrayList2 = cVar.k;
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                d c = ((e) arrayList2.get(i2)).c();
                if (c != null) {
                    arrayList.add(c);
                }
            }
            if (cVar.e()) {
                arrayList.addAll(cVar.f());
            }
            if (cVar.d()) {
                arrayList.add(cVar.h());
            }
        }
        return arrayList;
    }

    public e a(dji.thirdparty.g.b.b.a.e eVar) throws e {
        for (int i = 0; i < this.b.size(); i++) {
            e a = ((c) this.b.get(i)).a(eVar);
            if (a != null) {
                return a;
            }
        }
        return null;
    }

    public void a(boolean z) throws e {
        ArrayList a = a();
        Collections.sort(a, d.gx_);
        int i = 0;
        for (int i2 = 0; i2 < a.size(); i2++) {
            d dVar = (d) a.get(i2);
            if (dVar.gv_ > i) {
                c.a("\tgap: " + (dVar.gv_ - i));
            }
            if (dVar.gv_ < i) {
                c.a("\toverlap");
            }
            c.a("element, start: " + dVar.gv_ + ", length: " + dVar.gw_ + ", end: " + (dVar.gv_ + dVar.gw_) + ": " + dVar.a(false));
            if (z) {
                String a2 = dVar.a(true);
                if (a2 != null) {
                    c.a(a2);
                }
            }
            i = dVar.gv_ + dVar.gw_;
        }
        c.a("end: " + i);
        c.a();
    }
}
