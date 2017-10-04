package dji.pilot.publics.control.upgrade;

import dji.thirdparty.afinal.b;
import dji.thirdparty.afinal.f.a;
import java.io.File;

final class b$a extends a<File> {
    private DLPackageInfo a;
    private b$b b;
    private b c;
    private long d;

    private b$a(DLPackageInfo dLPackageInfo, b bVar, b$b dji_pilot_publics_control_upgrade_b_b) {
        this.a = null;
        this.b = null;
        this.a = dLPackageInfo;
        this.b = dji_pilot_publics_control_upgrade_b_b;
        this.c = bVar;
    }

    public void a(boolean z) {
        this.a.mDLStatus = 2;
        b.a(this.c, this.a);
        this.b.a(this.a);
        this.d = System.currentTimeMillis();
    }

    public void a(long j, long j2) {
        if (!(this.a.mPackageSize == j || j == 0)) {
            this.a.mPackageSize = j;
        }
        this.a.mDLSize = j2;
        if (this.a.mDLSize > j && j != 0) {
            this.a.mDLSize = j;
        }
        if (System.currentTimeMillis() - this.d > 800) {
            b.a(this.c, this.a);
            this.b.a(this.a, j2, j);
            this.d = System.currentTimeMillis();
        }
    }

    public void a(File file) {
        this.a.mDLStatus = 3;
        b.a(this.c, this.a);
        this.b.a(this.a, 0);
    }

    public void a(Throwable th, int i, String str) {
        if (str == null || !(str.contains("ETIMEDOUT") || str.contains("timed out"))) {
            this.a.mDLStatus = 4;
        } else {
            this.a.mDLStatus = 1;
        }
        b.a(this.c, this.a);
        this.b.a(this.a, i, str);
    }
}
