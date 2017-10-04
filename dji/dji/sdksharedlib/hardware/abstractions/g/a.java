package dji.sdksharedlib.hardware.abstractions.g;

import dji.common.error.DJIError;
import dji.common.product.Model;
import dji.log.DJILog;
import dji.midware.c.a.c;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraActiveStatus;
import dji.midware.data.model.P3.DataFlycActiveStatus;
import dji.midware.data.model.P3.DataOsdActiveStatus;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.sdksharedlib.b.d;
import dji.sdksharedlib.b.h;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.b.f;
import dji.sdksharedlib.hardware.abstractions.e;

public class a extends b {
    private static final String a = "DJISDKCacheProductAbstraction";
    private DroneType b;
    private c c;

    protected void a() {
        a(h.class, getClass());
        a((Object) Boolean.valueOf(true), dji.sdksharedlib.a.b.a(h.f));
        a((Object) Boolean.valueOf(true), dji.sdksharedlib.a.b.a(h.g));
        a((Object) Boolean.valueOf(true), dji.sdksharedlib.a.b.a(h.h));
    }

    public void onEventBackgroundThread(DataFlycActiveStatus dataFlycActiveStatus) {
        a((Object) Boolean.valueOf(false), dji.sdksharedlib.a.b.a(h.f));
    }

    public void onEventBackgroundThread(DataCameraActiveStatus dataCameraActiveStatus) {
        a((Object) Boolean.valueOf(false), dji.sdksharedlib.a.b.a(h.g));
    }

    public void onEventBackgroundThread(DataOsdActiveStatus dataOsdActiveStatus) {
        a((Object) Boolean.valueOf(false), dji.sdksharedlib.a.b.a(h.h));
    }

    protected dji.sdksharedlib.b.c a(String str) {
        return new dji.sdksharedlib.b.c.a().b(h.a).d(str).a();
    }

    protected void b() {
        boolean z = true;
        DroneType droneType = DataOsdGetPushCommon.getInstance().getDroneType();
        c a = dji.midware.c.a.getInstance().a();
        if (a == null) {
            a = dji.midware.c.a.getInstance().b();
            DJILog.d(a, "last platformType : " + a);
        }
        if (!(this.c != null && a == this.c && droneType == this.b)) {
            DJILog.d(a, "model : " + c());
            if (c() != null) {
                a((Object) Boolean.valueOf(true), a(d.ck));
                a((Object) c(), a(h.c));
                if (a != c.h) {
                    z = false;
                }
                a((Object) Boolean.valueOf(z), a(h.d));
            } else {
                a((Object) Boolean.valueOf(false), a(d.ck));
                a(null, a(h.c));
                a(null, a(h.d));
            }
        }
        this.c = a;
        this.b = droneType;
    }

    private Model c() {
        c a = dji.midware.c.a.getInstance().a();
        dji.midware.c.a.a e = dji.midware.c.a.getInstance().e();
        switch (1.a[a.ordinal()]) {
            case 1:
                return Model.Osmo_Mobile;
            case 2:
                if (e == dji.midware.c.a.a.g || e == dji.midware.c.a.a.j || e == dji.midware.c.a.a.k) {
                    return Model.Inspire_1;
                }
                if (e == dji.midware.c.a.a.h) {
                    return Model.Inspire_1_Pro;
                }
                if (e == dji.midware.c.a.a.i) {
                    return Model.Inspire_1_Raw;
                }
                if (e == dji.midware.c.a.a.n) {
                    return Model.ZenmuseZ3;
                }
                return Model.Inspire_1;
            case 3:
                return Model.Matrice_100;
            case 4:
                return Model.Phantom_3_Standard;
            case 5:
                return Model.Phantom_3_Advanced;
            case 6:
                return Model.Phantom_3_Professional;
            case 7:
                return Model.Phantom_3_4K;
            case 8:
                if (e == dji.midware.c.a.a.h) {
                    return Model.Osmo_Pro;
                }
                if (e == dji.midware.c.a.a.i) {
                    return Model.Osmo_Raw;
                }
                if (e == dji.midware.c.a.a.n) {
                    return Model.OsmoPlus;
                }
                return Model.Osmo;
            case 9:
                if (DataOsdGetPushCommon.getInstance().getDroneType() == DroneType.A3) {
                    return Model.A3;
                }
                return Model.UnknownAircraft;
            case 10:
                return Model.Phantom_4;
            case 11:
                return Model.M600;
            case 12:
                return Model.Inspire_2;
            case 13:
                return Model.MavicPro;
            case 14:
                return Model.Phantom4_Pro;
            default:
                return null;
        }
    }

    @e(a = "FirmwarePackageVersion")
    public void a(b.e eVar) {
        Object d = dji.internal.version.c.getInstance().d();
        if (d == null || d.equals("")) {
            eVar.a(DJIError.COMMON_UNKNOWN);
        } else {
            eVar.a(d);
        }
    }

    public void onEventBackgroundThread(dji.midware.c.a.d dVar) {
        b();
    }

    public void onEventBackgroundThread(dji.midware.c.a.a aVar) {
        b();
    }

    public void onEventBackgroundThread(c cVar) {
        b();
    }

    public void onEventBackgroundThread(p pVar) {
        dji.midware.c.a.getInstance().c();
        a((Object) Boolean.valueOf(pVar.equals(p.ConnectOK)), dji.sdksharedlib.a.b.a(h.e));
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (dataOsdGetPushCommon.getDroneType() != this.b) {
            b();
        }
    }

    public void a(String str, int i, dji.sdksharedlib.d.c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
        b();
    }

    public void e() {
        if (dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().d((Object) this);
        }
        super.e();
    }
}
