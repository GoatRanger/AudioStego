package lecho.lib.hellocharts.model;

import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.c.c;
import lecho.lib.hellocharts.c.h;

public class g {
    private boolean a = false;
    private boolean b = false;
    private c c = new h();
    private List<p> d = new ArrayList();

    public g(List<p> list) {
        a((List) list);
    }

    public g(g gVar) {
        this.a = gVar.a;
        this.b = gVar.b;
        this.c = gVar.c;
        for (p pVar : gVar.d) {
            this.d.add(new p(pVar));
        }
    }

    public void a(float f) {
        for (p a : this.d) {
            a.a(f);
        }
    }

    public void a() {
        for (p a : this.d) {
            a.a();
        }
    }

    public List<p> b() {
        return this.d;
    }

    public g a(List<p> list) {
        if (list == null) {
            this.d = new ArrayList();
        } else {
            this.d = list;
        }
        return this;
    }

    public boolean c() {
        return this.a;
    }

    public g a(boolean z) {
        this.a = z;
        if (z) {
            this.b = false;
        }
        return this;
    }

    public boolean d() {
        return this.b;
    }

    public g b(boolean z) {
        this.b = z;
        if (z) {
            this.a = false;
        }
        return this;
    }

    public c e() {
        return this.c;
    }

    public g a(c cVar) {
        if (cVar != null) {
            this.c = cVar;
        }
        return this;
    }
}
