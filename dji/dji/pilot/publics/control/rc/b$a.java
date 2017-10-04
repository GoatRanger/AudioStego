package dji.pilot.publics.control.rc;

import dji.thirdparty.afinal.f.a;
import java.io.File;

final class b$a extends a<File> {
    private static final int a = 1000;
    private DJIDLPackageInfo b;
    private b$c c;
    private int d;
    private long e;
    private long f;

    private b$a(DJIDLPackageInfo dJIDLPackageInfo, b$c dji_pilot_publics_control_rc_b_c) {
        this.b = null;
        this.c = null;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.b = dJIDLPackageInfo;
        this.c = dji_pilot_publics_control_rc_b_c;
    }

    public void a(boolean z) {
        this.b.mDLStatus = 2;
        this.c.a(this.b, z);
        this.e = 0;
    }

    public void a(long j, long j2) {
        if (!(this.b.mPackageSize == j || j == 0)) {
            this.b.mPackageSize = j;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.e >= 1000) {
            int i = (int) (j2 - this.f);
            if (i >= 0) {
                this.d = (int) (((((long) i) / (currentTimeMillis - this.e)) * 10000) / 1024);
            } else {
                this.d = 0;
            }
            this.e = currentTimeMillis;
            this.f = j2;
        }
        this.b.mDLSize = j2;
        if (this.b.mDLSize > j && j != 0) {
            this.b.mDLSize = j;
        }
        this.c.a(this.b, j2, j);
    }

    public void a(File file) {
        this.d = 0;
        this.b.mDLStatus = 3;
        this.c.a(this.b, 0);
    }

    public void a(Throwable th, int i, String str) {
        this.d = 0;
        this.b.mDLStatus = 4;
        this.c.a(this.b, i, str);
    }
}
