package dji.pilot.publics.control.rc;

import dji.thirdparty.afinal.f.c;

class b$7 implements b$c {
    final /* synthetic */ b a;

    b$7(b bVar) {
        this.a = bVar;
    }

    public void a(DJIDLPackageInfo dJIDLPackageInfo, long j, long j2) {
        if (b.a(this.a, dJIDLPackageInfo.mFileName) != null) {
            b.e(this.a).c(dJIDLPackageInfo, "mAbsPath='" + dJIDLPackageInfo.mAbsPath + "'");
            if (b.f(this.a) == dJIDLPackageInfo && b.g(this.a) != null) {
                b.g(this.a).a(dJIDLPackageInfo, (long) (j2 != 0 ? (int) ((j * 200) / j2) : 0), 200);
            }
        }
    }

    public void a(DJIDLPackageInfo dJIDLPackageInfo, int i) {
        if (b.a(this.a, dJIDLPackageInfo.mFileName) != null) {
            b.e(this.a).c(dJIDLPackageInfo, "mAbsPath='" + dJIDLPackageInfo.mAbsPath + "'");
            b.h(this.a).remove(dJIDLPackageInfo);
            if (b.f(this.a) == dJIDLPackageInfo) {
                b.c(this.a, 261);
                if (b.g(this.a) != null) {
                    b.g(this.a).a(dJIDLPackageInfo, i);
                }
            }
        }
    }

    public void a(DJIDLPackageInfo dJIDLPackageInfo, boolean z) {
        if (b.a(this.a, dJIDLPackageInfo.mFileName) != null) {
            b.e(this.a).c(dJIDLPackageInfo, "mAbsPath='" + dJIDLPackageInfo.mAbsPath + "'");
            if (b.f(this.a) == dJIDLPackageInfo && b.g(this.a) != null) {
                b.g(this.a).a(dJIDLPackageInfo, z);
            }
        }
    }

    public void a(DJIDLPackageInfo dJIDLPackageInfo, int i, String str) {
        if (b.a(this.a, dJIDLPackageInfo.mFileName) != null) {
            b.e(this.a).c(dJIDLPackageInfo, "mAbsPath='" + dJIDLPackageInfo.mAbsPath + "'");
            c cVar = (c) b.h(this.a).remove(dJIDLPackageInfo);
            if (b.f(this.a) == dJIDLPackageInfo && cVar != null && !cVar.g()) {
                b.c(this.a, 260);
                if (b.g(this.a) != null) {
                    b.g(this.a).a(dJIDLPackageInfo, i, str);
                }
            }
        }
    }
}
