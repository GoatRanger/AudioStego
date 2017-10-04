package lecho.lib.hellocharts.model;

import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.c.b;
import lecho.lib.hellocharts.c.g;

public class d extends a {
    public static final int l = 6;
    public static final float m = 1.0f;
    private b n = new g();
    private boolean o = false;
    private boolean p = false;
    private int q = 6;
    private float r = 1.0f;
    private List<e> s = new ArrayList();

    public d(List<e> list) {
        a((List) list);
    }

    public d(d dVar) {
        super(dVar);
        this.n = dVar.n;
        this.o = dVar.o;
        this.p = dVar.p;
        this.q = dVar.q;
        this.r = dVar.r;
        for (e eVar : dVar.m()) {
            this.s.add(new e(eVar));
        }
    }

    public static d k() {
        d dVar = new d();
        List arrayList = new ArrayList(4);
        arrayList.add(new e(0.0f, 20.0f, 15000.0f));
        arrayList.add(new e(3.0f, 22.0f, 20000.0f));
        arrayList.add(new e(5.0f, 25.0f, 5000.0f));
        arrayList.add(new e(7.0f, 30.0f, 30000.0f));
        arrayList.add(new e(11.0f, 22.0f, 10.0f));
        dVar.a(arrayList);
        return dVar;
    }

    public void a(float f) {
        for (e a : this.s) {
            a.a(f);
        }
    }

    public void l() {
        for (e a : this.s) {
            a.a();
        }
    }

    public List<e> m() {
        return this.s;
    }

    public d a(List<e> list) {
        if (list == null) {
            this.s = new ArrayList();
        } else {
            this.s = list;
        }
        return this;
    }

    public boolean n() {
        return this.o;
    }

    public d c(boolean z) {
        this.o = z;
        if (z) {
            this.p = false;
        }
        return this;
    }

    public boolean o() {
        return this.p;
    }

    public d d(boolean z) {
        this.p = z;
        if (z) {
            this.o = false;
        }
        return this;
    }

    public int p() {
        return this.q;
    }

    public void d(int i) {
        this.q = i;
    }

    public float q() {
        return this.r;
    }

    public void b(float f) {
        this.r = f;
    }

    public b r() {
        return this.n;
    }

    public d a(b bVar) {
        if (bVar != null) {
            this.n = bVar;
        }
        return this;
    }
}
