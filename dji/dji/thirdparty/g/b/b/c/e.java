package dji.thirdparty.g.b.b.c;

import dji.thirdparty.g.b.b.a;
import dji.thirdparty.g.b.b.a.d;
import dji.thirdparty.g.b.b.a.f;
import dji.thirdparty.g.b.b.c;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class e extends g implements f {
    public final int j;
    private final ArrayList l = new ArrayList();
    private e m = null;
    private a n = null;

    public void a(e eVar) {
        this.m = eVar;
    }

    public e(int i) {
        this.j = i;
    }

    public void a(f fVar) {
        this.l.add(fVar);
    }

    public ArrayList a() {
        return new ArrayList(this.l);
    }

    public void a(dji.thirdparty.g.b.b.a.e eVar) {
        a(eVar.k);
    }

    public void a(int i) {
        Collection arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            f fVar = (f) this.l.get(i2);
            if (fVar.j == i) {
                arrayList.add(fVar);
            }
        }
        this.l.removeAll(arrayList);
    }

    public f b(dji.thirdparty.g.b.b.a.e eVar) {
        return b(eVar.k);
    }

    public f b(int i) {
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            f fVar = (f) this.l.get(i2);
            if (fVar.j == i) {
                return fVar;
            }
        }
        return null;
    }

    public void b() {
        Collections.sort(this.l, new Comparator(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public int compare(Object obj, Object obj2) {
                f fVar = (f) obj;
                f fVar2 = (f) obj2;
                if (fVar.j != fVar2.j) {
                    return fVar.j - fVar2.j;
                }
                return fVar.c() - fVar2.c();
            }
        });
    }

    public String c() {
        return c.a(this.j);
    }

    public void a(dji.thirdparty.g.a.e eVar) throws IOException, dji.thirdparty.g.f {
        int g;
        eVar.d(this.l.size());
        for (int i = 0; i < this.l.size(); i++) {
            ((f) this.l.get(i)).a(eVar);
        }
        if (this.m != null) {
            g = this.m.g();
        } else {
            g = 0;
        }
        if (g == -1) {
            eVar.b(0);
        } else {
            eVar.b(g);
        }
    }

    public void a(a aVar) {
        this.n = aVar;
    }

    public a d() {
        return this.n;
    }

    public int e() {
        return ((this.l.size() * 12) + 2) + 4;
    }

    public String f() {
        return "Directory: " + d.a(this.j).b + " (" + this.j + ")";
    }

    private void c(dji.thirdparty.g.b.b.a.e eVar) {
        f b = b(eVar);
        if (b != null) {
            this.l.remove(b);
        }
    }

    protected List a(i iVar) throws dji.thirdparty.g.f {
        f fVar;
        int i = 0;
        c(hs_);
        c(ht_);
        if (this.n != null) {
            f fVar2 = new f(hs_, gc_, 1, dji.thirdparty.g.b.b.b.a.a());
            a(fVar2);
            a(new f(ht_, gc_, 1, gc_.a(new int[]{this.n.gw_}, iVar.j)));
            fVar = fVar2;
        } else {
            fVar = null;
        }
        c(fM_);
        c(gC_);
        c(hf_);
        c(hg_);
        List arrayList = new ArrayList();
        arrayList.add(this);
        b();
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            fVar2 = (f) this.l.get(i2);
            if (!fVar2.b()) {
                arrayList.add(fVar2.a());
            }
        }
        if (null != null) {
            while (i < null.c.length) {
                arrayList.add(null.c[i]);
                i++;
            }
            iVar.a(null);
        }
        if (this.n != null) {
            g aVar = new g.a("JPEG image data", this.n.a);
            arrayList.add(aVar);
            iVar.a(aVar, fVar);
        }
        return arrayList;
    }
}
