package lecho.lib.hellocharts.model;

import android.graphics.Typeface;
import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.c.e;
import lecho.lib.hellocharts.c.j;

public class l extends a {
    public static final int l = 42;
    public static final int m = 16;
    public static final float n = 0.6f;
    private static final int o = 2;
    private Typeface A;
    private String B;
    private int C = -16777216;
    private Typeface D;
    private String E;
    private List<o> F = new ArrayList();
    private int p = 42;
    private int q = 16;
    private float r = n;
    private int s = 2;
    private e t = new j();
    private boolean u = false;
    private boolean v = false;
    private boolean w = false;
    private boolean x = false;
    private int y = 0;
    private int z = -16777216;

    public l() {
        a(null);
        b(null);
    }

    public l(List<o> list) {
        a((List) list);
        a(null);
        b(null);
    }

    public l(l lVar) {
        super(lVar);
        this.t = lVar.t;
        this.u = lVar.u;
        this.v = lVar.v;
        this.w = lVar.w;
        this.x = lVar.x;
        this.y = lVar.y;
        this.r = lVar.r;
        this.z = lVar.z;
        this.p = lVar.p;
        this.A = lVar.A;
        this.B = lVar.B;
        this.C = lVar.C;
        this.q = lVar.q;
        this.D = lVar.D;
        this.E = lVar.E;
        for (o oVar : lVar.F) {
            this.F.add(new o(oVar));
        }
    }

    public static l k() {
        l lVar = new l();
        List arrayList = new ArrayList(4);
        arrayList.add(new o(40.0f));
        arrayList.add(new o(20.0f));
        arrayList.add(new o(30.0f));
        arrayList.add(new o(50.0f));
        lVar.a(arrayList);
        return lVar;
    }

    public void a(float f) {
        for (o a : this.F) {
            a.a(f);
        }
    }

    public void l() {
        for (o a : this.F) {
            a.a();
        }
    }

    public void a(b bVar) {
        super.a(null);
    }

    public void b(b bVar) {
        super.b(null);
    }

    public List<o> m() {
        return this.F;
    }

    public l a(List<o> list) {
        if (list == null) {
            this.F = new ArrayList();
        } else {
            this.F = list;
        }
        return this;
    }

    public boolean n() {
        return this.u;
    }

    public l c(boolean z) {
        this.u = z;
        if (z) {
            this.v = false;
        }
        return this;
    }

    public boolean o() {
        return this.v;
    }

    public l d(boolean z) {
        this.v = z;
        if (z) {
            this.u = false;
        }
        return this;
    }

    public boolean p() {
        return this.w;
    }

    public l e(boolean z) {
        this.w = z;
        return this;
    }

    public boolean q() {
        return this.x;
    }

    public l f(boolean z) {
        this.x = z;
        return this;
    }

    public int r() {
        return this.y;
    }

    public l d(int i) {
        this.y = i;
        return this;
    }

    public float s() {
        return this.r;
    }

    public l b(float f) {
        this.r = f;
        return this;
    }

    public int t() {
        return this.z;
    }

    public l e(int i) {
        this.z = i;
        return this;
    }

    public int u() {
        return this.p;
    }

    public l f(int i) {
        this.p = i;
        return this;
    }

    public Typeface v() {
        return this.A;
    }

    public l b(Typeface typeface) {
        this.A = typeface;
        return this;
    }

    public String w() {
        return this.B;
    }

    public l a(String str) {
        this.B = str;
        return this;
    }

    public String x() {
        return this.E;
    }

    public l b(String str) {
        this.E = str;
        return this;
    }

    public int y() {
        return this.C;
    }

    public l g(int i) {
        this.C = i;
        return this;
    }

    public int z() {
        return this.q;
    }

    public l h(int i) {
        this.q = i;
        return this;
    }

    public Typeface A() {
        return this.D;
    }

    public l c(Typeface typeface) {
        this.D = typeface;
        return this;
    }

    public int B() {
        return this.s;
    }

    public l i(int i) {
        this.s = i;
        return this;
    }

    public e C() {
        return this.t;
    }

    public l a(e eVar) {
        if (eVar != null) {
            this.t = eVar;
        }
        return this;
    }
}
