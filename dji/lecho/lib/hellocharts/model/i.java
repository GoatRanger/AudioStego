package lecho.lib.hellocharts.model;

public class i extends a {
    private h l;
    private k m;

    public i() {
        this.l = new h();
        this.m = new k();
    }

    public i(h hVar, k kVar) {
        a(hVar);
        a(kVar);
    }

    public i(i iVar) {
        super(iVar);
        a(new h(iVar.m()));
        a(new k(iVar.n()));
    }

    public static i k() {
        i iVar = new i();
        iVar.a(h.k());
        iVar.a(k.k());
        return iVar;
    }

    public void a(float f) {
        this.l.a(f);
        this.m.a(f);
    }

    public void l() {
        this.l.l();
        this.m.l();
    }

    public h m() {
        return this.l;
    }

    public void a(h hVar) {
        if (hVar == null) {
            this.l = new h();
        } else {
            this.l = hVar;
        }
    }

    public k n() {
        return this.m;
    }

    public void a(k kVar) {
        if (kVar == null) {
            this.m = new k();
        } else {
            this.m = kVar;
        }
    }
}
