package lecho.lib.hellocharts.model;

import android.graphics.PathEffect;
import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.c.d;
import lecho.lib.hellocharts.c.i;
import lecho.lib.hellocharts.h.b;

public class j {
    public static final int a = 0;
    private static final int b = 3;
    private static final int c = 6;
    private static final int d = 64;
    private int e = b.a;
    private int f = 0;
    private int g = b.b;
    private int h = 64;
    private int i = 3;
    private int j = 6;
    private boolean k = true;
    private boolean l = true;
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private q r = q.CIRCLE;
    private PathEffect s;
    private d t = new i();
    private List<m> u = new ArrayList();

    public j(List<m> list) {
        a((List) list);
    }

    public j(j jVar) {
        this.e = jVar.e;
        this.f = jVar.f;
        this.g = jVar.g;
        this.h = jVar.h;
        this.i = jVar.i;
        this.j = jVar.j;
        this.k = jVar.k;
        this.l = jVar.l;
        this.m = jVar.m;
        this.n = jVar.n;
        this.p = jVar.p;
        this.o = jVar.o;
        this.q = jVar.q;
        this.r = jVar.r;
        this.s = jVar.s;
        this.t = jVar.t;
        for (m mVar : jVar.u) {
            this.u.add(new m(mVar));
        }
    }

    public void a(float f) {
        for (m a : this.u) {
            a.a(f);
        }
    }

    public void a() {
        for (m a : this.u) {
            a.a();
        }
    }

    public List<m> b() {
        return this.u;
    }

    public void a(List<m> list) {
        if (list == null) {
            this.u = new ArrayList();
        } else {
            this.u = list;
        }
    }

    public int c() {
        return this.e;
    }

    public j a(int i) {
        this.e = i;
        if (this.f == 0) {
            this.g = b.a(i);
        }
        return this;
    }

    public int d() {
        if (this.f == 0) {
            return this.e;
        }
        return this.f;
    }

    public j b(int i) {
        this.f = i;
        if (i == 0) {
            this.g = b.a(this.e);
        } else {
            this.g = b.a(i);
        }
        return this;
    }

    public int e() {
        return this.g;
    }

    public int f() {
        return this.h;
    }

    public j c(int i) {
        this.h = i;
        return this;
    }

    public int g() {
        return this.i;
    }

    public j d(int i) {
        this.i = i;
        return this;
    }

    public boolean h() {
        return this.k;
    }

    public j a(boolean z) {
        this.k = z;
        return this;
    }

    public boolean i() {
        return this.l;
    }

    public j b(boolean z) {
        this.l = z;
        return this;
    }

    public boolean j() {
        return this.m;
    }

    public j c(boolean z) {
        this.m = z;
        if (z) {
            this.n = false;
        }
        return this;
    }

    public boolean k() {
        return this.n;
    }

    public j d(boolean z) {
        this.n = z;
        if (z) {
            this.m = false;
        }
        return this;
    }

    public int l() {
        return this.j;
    }

    public j e(int i) {
        this.j = i;
        return this;
    }

    public boolean m() {
        return this.o;
    }

    public j e(boolean z) {
        this.o = z;
        if (this.p) {
            f(false);
        }
        return this;
    }

    public boolean n() {
        return this.p;
    }

    public j f(boolean z) {
        this.p = z;
        if (this.o) {
            e(false);
        }
        return this;
    }

    public boolean o() {
        return this.q;
    }

    public j g(boolean z) {
        this.q = z;
        return this;
    }

    public q p() {
        return this.r;
    }

    public j a(q qVar) {
        this.r = qVar;
        return this;
    }

    public PathEffect q() {
        return this.s;
    }

    public void a(PathEffect pathEffect) {
        this.s = pathEffect;
    }

    public d r() {
        return this.t;
    }

    public j a(d dVar) {
        if (dVar != null) {
            this.t = dVar;
        }
        return this;
    }
}
