package dji.thirdparty.g.b.b.c;

import dji.thirdparty.g.b.b.a.f;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class i implements f {
    public final int j;
    public final e k;
    public final Map l;
    private List m = new ArrayList();
    private List n = new ArrayList();

    private static class a {
        public final g a;
        public final f b;

        public a(g gVar, f fVar) {
            this.b = fVar;
            this.a = gVar;
        }
    }

    public i(int i, e eVar, Map map) {
        this.j = i;
        this.k = eVar;
        this.l = map;
    }

    public void a(g gVar, f fVar) {
        this.m.add(new a(gVar, fVar));
    }

    public void a(int i) throws dji.thirdparty.g.f {
        int i2;
        for (i2 = 0; i2 < this.m.size(); i2++) {
            a aVar = (a) this.m.get(i2);
            aVar.b.a(gc_.a(new int[]{aVar.a.g()}, i));
        }
        for (i2 = 0; i2 < this.n.size(); i2++) {
            a aVar2 = (a) this.n.get(i2);
            for (int i3 = 0; i3 < aVar2.c.length; i3++) {
                aVar2.a[i3] = aVar2.c[i3].g();
            }
            aVar2.b.a(gc_.a(aVar2.a, i));
        }
    }

    public void a(a aVar) {
        this.n.add(aVar);
    }
}
