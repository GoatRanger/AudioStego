package lecho.lib.hellocharts.model;

import dji.common.flightcontroller.DJIFlightControllerDataType;
import java.util.ArrayList;
import java.util.List;

public class k extends a {
    public static final float l = 0.0f;
    private List<j> m = new ArrayList();
    private float n = 0.0f;

    public k(List<j> list) {
        a((List) list);
    }

    public k(k kVar) {
        super(kVar);
        this.n = kVar.n;
        for (j jVar : kVar.m) {
            this.m.add(new j(jVar));
        }
    }

    public static k k() {
        k kVar = new k();
        List arrayList = new ArrayList(4);
        arrayList.add(new m(0.0f, 2.0f));
        arrayList.add(new m(1.0f, DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity));
        arrayList.add(new m(2.0f, 3.0f));
        arrayList.add(new m(3.0f, DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity));
        j jVar = new j(arrayList);
        arrayList = new ArrayList(1);
        arrayList.add(jVar);
        kVar.a(arrayList);
        return kVar;
    }

    public void a(float f) {
        for (j a : this.m) {
            a.a(f);
        }
    }

    public void l() {
        for (j a : this.m) {
            a.a();
        }
    }

    public List<j> m() {
        return this.m;
    }

    public k a(List<j> list) {
        if (list == null) {
            this.m = new ArrayList();
        } else {
            this.m = list;
        }
        return this;
    }

    public float n() {
        return this.n;
    }

    public k b(float f) {
        this.n = f;
        return this;
    }
}
