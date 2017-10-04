package lecho.lib.hellocharts.model;

import java.util.ArrayList;
import java.util.List;

public class h extends a {
    public static final float l = 0.75f;
    public static final float m = 0.0f;
    private float n = l;
    private float o = 0.0f;
    private List<g> p = new ArrayList();
    private boolean q = false;

    public h(List<g> list) {
        a((List) list);
    }

    public h(h hVar) {
        super(hVar);
        this.q = hVar.q;
        this.n = hVar.n;
        for (g gVar : hVar.p) {
            this.p.add(new g(gVar));
        }
    }

    public static h k() {
        h hVar = new h();
        List arrayList = new ArrayList(4);
        for (int i = 1; i <= 4; i++) {
            List arrayList2 = new ArrayList(4);
            arrayList2.add(new p((float) i));
            arrayList.add(new g(arrayList2));
        }
        hVar.a(arrayList);
        return hVar;
    }

    public void a(float f) {
        for (g a : this.p) {
            a.a(f);
        }
    }

    public void l() {
        for (g a : this.p) {
            a.a();
        }
    }

    public List<g> m() {
        return this.p;
    }

    public h a(List<g> list) {
        if (list == null) {
            this.p = new ArrayList();
        } else {
            this.p = list;
        }
        return this;
    }

    public boolean n() {
        return this.q;
    }

    public h c(boolean z) {
        this.q = z;
        return this;
    }

    public float o() {
        return this.n;
    }

    public h b(float f) {
        float f2 = 1.0f;
        float f3 = 0.0f;
        if (f >= 0.0f) {
            f3 = f;
        }
        if (f3 <= 1.0f) {
            f2 = f3;
        }
        this.n = f2;
        return this;
    }

    public float p() {
        return this.o;
    }

    public h c(float f) {
        this.o = f;
        return this;
    }
}
