package dji.pilot.newfpv;

import dji.pilot.newfpv.f.d;
import dji.thirdparty.a.c;
import java.util.HashMap;
import java.util.Map;

public abstract class a implements g {
    protected e a = null;
    protected int b = 0;
    protected int c = 0;
    protected final HashMap<dji.pilot.newfpv.view.b.a, d> d = new HashMap();

    public void a(e eVar, int i) {
        this.a = eVar;
        this.b = i;
    }

    public void f() {
        if (!c.a().c(this)) {
            c.a().a(this);
        }
    }

    public void g() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    public void h() {
    }

    public void i() {
    }

    public void onEventMainThread(d dVar) {
        if (d.GS_SWITCH_LARGE == dVar) {
            a(new dji.pilot.newfpv.view.b.a[]{dji.pilot.newfpv.view.b.a.ViewId_MF}, new boolean[]{false});
        } else if (d.GS_SWITCH_SMALL == dVar) {
            a(new dji.pilot.newfpv.view.b.a[]{dji.pilot.newfpv.view.b.a.ViewId_MF}, new boolean[]{true});
        }
    }

    protected void a(dji.pilot.newfpv.view.b.a[] aVarArr, boolean[] zArr) {
        int length = aVarArr.length;
        for (int i = 0; i < length; i++) {
            d dVar = (d) this.d.get(aVarArr[i]);
            c.a().e(zArr[i] ? dVar.c : dVar.d);
        }
    }

    public void a(Map<dji.pilot.newfpv.view.b.a, d> map) {
        if (map == null || map.isEmpty()) {
            this.d.clear();
            return;
        }
        for (dji.pilot.newfpv.view.b.a aVar : map.keySet()) {
            ((d) map.get(aVar)).a.bind(this, 0);
        }
        this.d.putAll(map);
    }

    public int a() {
        int i = this.c;
        this.c = i + 1;
        return i;
    }

    public boolean a(d dVar, int i) {
        return this.a.a(dVar, i);
    }
}
