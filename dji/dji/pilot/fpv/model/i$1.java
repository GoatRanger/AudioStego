package dji.pilot.fpv.model;

import dji.pilot.fpv.model.f.a;
import java.util.Comparator;

class i$1 implements Comparator<f> {
    final /* synthetic */ a a;
    final /* synthetic */ boolean b;
    final /* synthetic */ boolean c;

    i$1(a aVar, boolean z, boolean z2) {
        this.a = aVar;
        this.b = z;
        this.c = z2;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((f) obj, (f) obj2);
    }

    public int a(f fVar, f fVar2) {
        int i = 0;
        switch (i$2.a[this.a.ordinal()]) {
            case 1:
                if (this.b) {
                    return fVar2.x - fVar.x;
                }
                return fVar.x - fVar2.x;
            case 2:
                if (this.c) {
                    i = fVar.x - fVar2.x;
                }
                if (i != 0) {
                    return i;
                }
                if (this.b) {
                    return fVar.A - fVar2.A;
                }
                return fVar2.A - fVar.A;
            case 3:
                if (this.c) {
                    i = fVar.x - fVar2.x;
                }
                if (i != 0) {
                    return i;
                }
                if (this.b) {
                    return i.a(fVar2.C, fVar.C);
                }
                return i.a(fVar.C, fVar2.C);
            case 4:
                if (this.c) {
                    i = fVar.x - fVar2.x;
                }
                if (i != 0) {
                    return i;
                }
                if (this.b) {
                    return i.a(fVar2.F, fVar.F);
                }
                return i.a(fVar.F, fVar2.F);
            case 5:
                if (this.c) {
                    i = fVar.x - fVar2.x;
                }
                if (i != 0) {
                    return i;
                }
                if (this.b) {
                    return i.a((long) fVar2.G, (long) fVar.G);
                }
                return i.a((long) fVar.G, (long) fVar2.G);
            case 6:
                if (this.c) {
                    i = fVar.x - fVar2.x;
                }
                if (i != 0) {
                    return i;
                }
                if (this.b) {
                    return i.a(fVar2.H, fVar.H);
                }
                return i.a(fVar.H, fVar2.H);
            case 7:
                if (this.c) {
                    i = fVar.x - fVar2.x;
                }
                if (i != 0) {
                    return i;
                }
                if (this.b) {
                    return fVar2.K - fVar.K;
                }
                return fVar.K - fVar2.K;
            case 8:
                if (this.c) {
                    i = fVar.x - fVar2.x;
                }
                if (i != 0) {
                    return i;
                }
                if (this.b) {
                    return (int) (fVar2.L - fVar.L);
                }
                return (int) (fVar.L - fVar2.L);
            default:
                return 0;
        }
    }
}
