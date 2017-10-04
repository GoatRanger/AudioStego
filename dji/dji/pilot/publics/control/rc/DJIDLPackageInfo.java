package dji.pilot.publics.control.rc;

import dji.pilot.usercenter.protocol.d;

public class DJIDLPackageInfo {
    public static final int STATE_FAILED = 4;
    public static final int STATE_FINISHED = 3;
    public static final int STATE_INIT = 0;
    public static final int STATE_PAUSE = 1;
    public static final int STATE_RUNNING = 2;
    public static final int TYPE_RC = 0;
    public int _id = 0;
    public String mAbsPath = null;
    public long mDLSize = 0;
    public int mDLStatus = 0;
    public String mDLUrl = null;
    public String mFileName = null;
    public long mPackageSize = 0;
    public int mProductId = 0;
    public int mRealProductId = 0;
    public int mType = 0;
    public String mVersion = null;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r4) {
        /*
        r3 = this;
        r0 = super.equals(r4);
        if (r0 != 0) goto L_0x0021;
    L_0x0006:
        r0 = r4 instanceof dji.pilot.publics.control.rc.DJIDLPackageInfo;
        if (r0 == 0) goto L_0x0021;
    L_0x000a:
        r0 = r4;
        r0 = (dji.pilot.publics.control.rc.DJIDLPackageInfo) r0;
        r1 = r3.mAbsPath;
        r2 = r0.mAbsPath;
        if (r1 == r2) goto L_0x0021;
    L_0x0013:
        r1 = r3.mAbsPath;
        if (r1 == 0) goto L_0x0021;
    L_0x0017:
        r1 = r3.mAbsPath;
        r0 = r0.mAbsPath;
        r0 = r1.equals(r0);
        if (r0 == 0) goto L_0x0021;
    L_0x0021:
        r0 = super.equals(r4);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.publics.control.rc.DJIDLPackageInfo.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        if (this.mAbsPath != null) {
            return this.mAbsPath.hashCode() + 527;
        }
        return 17;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append("path[").append(this.mAbsPath).append(d.H);
        stringBuilder.append("size[").append(String.valueOf(this.mPackageSize)).append(d.H);
        stringBuilder.append("status[").append(String.valueOf(this.mDLStatus)).append(d.H);
        return stringBuilder.toString();
    }
}
