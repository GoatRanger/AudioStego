package dji.pilot2.upgrade.rollback;

import android.content.Context;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.pilot.upgrade.b$b;
import dji.pilot.upgrade.b$c;
import dji.pilot.upgrade.d;
import dji.pilot.upgrade.e;
import dji.pilot.upgrade.f;
import dji.thirdparty.a.c;
import java.util.ArrayList;

public class a {
    private static final String a = "DJIRollBackController";
    private static final boolean b = true;
    private static a i = null;
    private ArrayList<String> c = null;
    private ArrayList<DJIUpgradePack> d = null;
    private int e = -1;
    private b$b f = b$b.None;
    private b g = b.NONE;
    private String h;

    public static class a {
        public b$b a;
        public DJIUpgradePack b;
    }

    public enum b {
        NONE,
        READY
    }

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (i == null) {
                i = new a();
            }
            aVar = i;
        }
        return aVar;
    }

    a() {
    }

    public void a(Context context) {
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        h();
    }

    public void a() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
        i = null;
    }

    private void h() {
        b$b b = dji.pilot.upgrade.b.getInstance().b();
        b$c d = dji.pilot.upgrade.b.getInstance().d();
        d.a(a, "DJIRollBackController mainType: " + b, true);
        d.a(a, "DJIRollBackController rcType: " + d, true);
        if (b != b$b.None || d != b$c.None || (dji.pilot.upgrade.b.getInstance().c() != b$b.P3c && dji.pilot.upgrade.b.getInstance().c() != b$b.OSMO && dji.pilot.upgrade.b.getInstance().c() != b$b.P34k)) {
            switch (d) {
                case P3s:
                    b = b$b.P3s;
                    break;
                case P3x:
                    b = b$b.P3x;
                    break;
                case M100:
                    b = b$b.M100;
                    break;
                case Inspire:
                    b = b$b.Inspire;
                    break;
                case LB2:
                    b = b$b.LB2;
                    break;
                case P4:
                    b = b$b.P4;
                    break;
                default:
                    break;
            }
        }
        b = dji.pilot.upgrade.b.getInstance().c();
        d.a(a, "DJIRollBackController mainType 1: " + b, true);
        if (b != b$b.None && this.f != b) {
            this.f = b;
            d.a(a, "DJIRollBackController 3 ", true);
            if (this.f == b$b.None) {
                this.g = b.NONE;
                d.a(a, "DJIRollBackController 4 ", true);
            } else {
                if (this.f == b$b.P3c || this.f == b$b.OSMO || this.f == b$b.P34k) {
                    this.h = e.getInstance().b();
                } else {
                    this.h = f.getInstance().b();
                }
                d.a(a, "DJIRollBackController mVersion : " + this.h, true);
                i();
                this.g = b.READY;
            }
            if (this.c != null && this.c.size() > 0) {
                a(0);
                d.a(a, "DJIRollBackController 5 ", true);
            }
            c.a().e(this.g);
            c.a().e(this);
            d.a(a, "Petyr DJIRollBackController status : " + this.g, true);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void i() {
        /*
        r5 = this;
        r1 = 0;
        r0 = dji.pilot.upgrade.UpgradeConfigInfo.getInstance();
        r0 = r0.b();
        if (r0 == 0) goto L_0x000f;
    L_0x000b:
        r2 = r5.f;
        if (r2 != 0) goto L_0x0012;
    L_0x000f:
        r5.c = r1;
    L_0x0011:
        return;
    L_0x0012:
        r2 = dji.pilot2.upgrade.rollback.a.AnonymousClass1.b;
        r3 = r5.f;
        r3 = r3.ordinal();
        r2 = r2[r3];
        switch(r2) {
            case 1: goto L_0x002d;
            case 2: goto L_0x0030;
            case 3: goto L_0x0033;
            case 4: goto L_0x0036;
            case 5: goto L_0x0054;
            case 6: goto L_0x0057;
            case 7: goto L_0x005a;
            case 8: goto L_0x005d;
            case 9: goto L_0x0060;
            default: goto L_0x001f;
        };
    L_0x001f:
        r0 = r1;
    L_0x0020:
        if (r0 == 0) goto L_0x0028;
    L_0x0022:
        r2 = r0.size();
        if (r2 != 0) goto L_0x0063;
    L_0x0028:
        r5.c = r1;
        r5.d = r1;
        goto L_0x0011;
    L_0x002d:
        r0 = r0.versionlistc;
        goto L_0x0020;
    L_0x0030:
        r0 = r0.versionlists;
        goto L_0x0020;
    L_0x0033:
        r0 = r0.versionlistx;
        goto L_0x0020;
    L_0x0036:
        r2 = dji.midware.data.model.P3.DataCameraGetPushStateInfo.getInstance();
        r2 = r2.getCameraType();
        r3 = dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType.DJICameraTypeFC550;
        if (r2 != r3) goto L_0x0045;
    L_0x0042:
        r0 = r0.versionlisthgX5;
        goto L_0x0020;
    L_0x0045:
        r2 = dji.midware.data.model.P3.DataCameraGetPushStateInfo.getInstance();
        r2 = r2.getCameraType();
        r3 = dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType.DJICameraTypeFC350;
        if (r2 != r3) goto L_0x001f;
    L_0x0051:
        r0 = r0.versionlisthg;
        goto L_0x0020;
    L_0x0054:
        r0 = r0.versionlistm100;
        goto L_0x0020;
    L_0x0057:
        r0 = r0.versionlist;
        goto L_0x0020;
    L_0x005a:
        r0 = r0.versionlistxw;
        goto L_0x0020;
    L_0x005d:
        r0 = r0.versionlistlb2;
        goto L_0x0020;
    L_0x0060:
        r0 = r0.versionlistx;
        goto L_0x0020;
    L_0x0063:
        r1 = new java.util.ArrayList;
        r1.<init>();
        r5.c = r1;
        r1 = new java.util.ArrayList;
        r1.<init>();
        r5.d = r1;
        r1 = r0.iterator();
    L_0x0075:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0011;
    L_0x007b:
        r0 = r1.next();
        r0 = (dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack) r0;
        r2 = r5.f;
        r3 = dji.pilot.upgrade.b$b.P3c;
        if (r2 == r3) goto L_0x0093;
    L_0x0087:
        r2 = r5.f;
        r3 = dji.pilot.upgrade.b$b.OSMO;
        if (r2 == r3) goto L_0x0093;
    L_0x008d:
        r2 = r5.f;
        r3 = dji.pilot.upgrade.b$b.P34k;
        if (r2 != r3) goto L_0x00a8;
    L_0x0093:
        r2 = r0.packurl;
        r2 = com.dji.frame.c.l.a(r2);
        if (r2 != 0) goto L_0x0075;
    L_0x009b:
        r2 = r5.c;
        r3 = r0.version;
        r2.add(r3);
        r2 = r5.d;
        r2.add(r0);
        goto L_0x0075;
    L_0x00a8:
        r2 = "DJIRollBackController";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "pack.rcurl:";
        r3 = r3.append(r4);
        r4 = r0.rcurl;
        r3 = r3.append(r4);
        r4 = " pack.rc1url:";
        r3 = r3.append(r4);
        r4 = r0.rc1url;
        r3 = r3.append(r4);
        r4 = " pack.rcversion:";
        r3 = r3.append(r4);
        r4 = r0.rcversion;
        r3 = r3.append(r4);
        r3 = r3.toString();
        r4 = 1;
        dji.pilot.upgrade.d.a(r2, r3, r4);
        r2 = r0.rcurl;
        r2 = com.dji.frame.c.l.a(r2);
        if (r2 == 0) goto L_0x00eb;
    L_0x00e3:
        r2 = r0.rc1url;
        r2 = com.dji.frame.c.l.a(r2);
        if (r2 != 0) goto L_0x0075;
    L_0x00eb:
        r2 = r0.rcversion;
        r2 = com.dji.frame.c.l.a(r2);
        if (r2 == 0) goto L_0x0101;
    L_0x00f3:
        r2 = r5.c;
        r3 = r0.version;
        r2.add(r3);
    L_0x00fa:
        r2 = r5.d;
        r2.add(r0);
        goto L_0x0075;
    L_0x0101:
        r2 = r5.c;
        r3 = r0.rcversion;
        r2.add(r3);
        goto L_0x00fa;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot2.upgrade.rollback.a.i():void");
    }

    public b$b b() {
        return this.f;
    }

    public String c() {
        if (this.f == b$b.None) {
            return null;
        }
        d.a(a, "getProductTypeName Type: " + this.f, true);
        String j = dji.pilot.publics.c.d.getInstance().j(dji.pilot.upgrade.b.a(this.f));
        d.a(a, "getProductTypeName productName: " + j, true);
        return j;
    }

    public String d() {
        return this.h;
    }

    public ArrayList<String> e() {
        return this.c;
    }

    public void onEventBackgroundThread(b$b dji_pilot_upgrade_b_b) {
        d.a(a, "DJIRollBackController MainComponentType", true);
        h();
    }

    public void onEventBackgroundThread(b$c dji_pilot_upgrade_b_c) {
        d.a(a, "DJIRollBackController RcComponentType", true);
        h();
    }

    public void onEventBackgroundThread(e eVar) {
        d.a(a, "DJIRollBackController ProductShowVersionController", true);
        h();
    }

    public void onEventBackgroundThread(f fVar) {
        d.a(a, "DJIRollBackController RcShowVersionController", true);
        h();
    }

    public b f() {
        return this.g;
    }

    public void a(int i) {
        if (this.e != i) {
            this.e = i;
            a aVar = new a();
            aVar.b = (DJIUpgradePack) this.d.get(this.e);
            aVar.a = this.f;
            c.a().e(aVar);
        }
    }

    public DJIUpgradePack g() {
        if (this.d == null || this.e < 0) {
            return null;
        }
        return (DJIUpgradePack) this.d.get(this.e);
    }
}
