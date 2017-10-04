package dji.pilot.upgrade;

import android.os.Handler;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.f.a;
import dji.thirdparty.a.c;

public class b {
    private static final String a = "ComponentMgr";
    private static final boolean b = false;
    private static b c = null;
    private b d = b.a;
    private b e = b.a;
    private c f = c.a;
    private c g = c.a;
    private a h = a.a;
    private a i = a.a;
    private Handler j = null;

    public static synchronized b getInstance() {
        b bVar;
        synchronized (b.class) {
            if (c == null) {
                c = new b();
            }
            bVar = c;
        }
        return bVar;
    }

    private b() {
    }

    public void a() {
        this.j = new Handler();
        h();
        c.a().a((Object) this);
    }

    public b b() {
        return this.d;
    }

    public b c() {
        return this.e;
    }

    public c d() {
        return this.f;
    }

    public c e() {
        return this.g;
    }

    public a f() {
        return this.h;
    }

    public a g() {
        return this.i;
    }

    public static ProductType a(b bVar) {
        ProductType productType = ProductType.None;
        switch (2.a[bVar.ordinal()]) {
            case 1:
                return ProductType.litchiC;
            case 2:
                return ProductType.litchiS;
            case 3:
                return ProductType.litchiX;
            case 4:
                return ProductType.P34K;
            case 5:
                return ProductType.Longan;
            case 6:
                return ProductType.LonganPro;
            case 7:
                return ProductType.LonganRaw;
            case 8:
                return ProductType.LonganZoom;
            case 9:
                return ProductType.LonganMobile;
            case 10:
                return ProductType.N1;
            case 11:
                return ProductType.Orange;
            case 12:
                return ProductType.Grape2;
            case 13:
                return ProductType.Tomato;
            case 14:
                return ProductType.A3;
            case 15:
                return ProductType.N3;
            case 16:
                return ProductType.OTHER;
            default:
                return ProductType.None;
        }
    }

    public static ProductType a(c cVar) {
        ProductType productType = ProductType.None;
        switch (2.b[cVar.ordinal()]) {
            case 1:
                return ProductType.litchiS;
            case 2:
                return ProductType.litchiX;
            case 3:
                return ProductType.N1;
            case 4:
                return ProductType.Orange;
            case 5:
                return ProductType.Tomato;
            case 6:
                return ProductType.Pomato;
            case 7:
                return ProductType.KumquatX;
            case 8:
                return ProductType.OTHER;
            default:
                return ProductType.None;
        }
    }

    public static b a(ProductType productType) {
        b bVar = b.a;
        switch (2.c[productType.ordinal()]) {
            case 1:
                return b.d;
            case 2:
                return b.c;
            case 3:
                return b.b;
            case 4:
                return b.e;
            case 5:
                return b.j;
            case 6:
                return b.k;
            case 7:
                return b.l;
            case 8:
                return b.m;
            case 9:
                return b.n;
            case 10:
                return b.g;
            case 11:
                return b.h;
            case 12:
                return b.i;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                return b.p;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
                return b.f;
            case 23:
                return b.s;
            default:
                return b.a;
        }
    }

    public static c b(ProductType productType) {
        c cVar = c.a;
        switch (2.c[productType.ordinal()]) {
            case 2:
                return c.c;
            case 3:
                return c.b;
            case 10:
                return c.e;
            case 13:
                return c.g;
            case 14:
                return c.h;
            case 15:
            case 16:
                return c.i;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
                return c.d;
            case 23:
                return c.j;
            default:
                return c.a;
        }
    }

    private void h() {
        boolean i = i();
        boolean j = j();
        boolean k = k();
        if (i || j || k) {
            d.a(a, "Petyr ====================", true);
            d.a(a, "Petyr mMainComponentType : " + this.d, true);
            d.a(a, "Petyr mLastMainComponentType : " + this.e, true);
            d.a(a, "Petyr mRcComponentType : " + this.f, true);
            d.a(a, "Petyr mLastRcComponentType : " + this.g, true);
            d.a(a, "Petyr mCameraComponentType : " + this.h, true);
            d.a(a, "Petyr mLastCameraComponentType : " + this.i, true);
            d.a(a, "Petyr ====================", true);
        }
    }

    private boolean i() {
        b bVar;
        ProductType productType = ProductType.None;
        if (ServiceManager.getInstance().isRemoteOK() && ServiceManager.getInstance().isOK()) {
            productType = i.getInstance().c();
        }
        b bVar2 = b.a;
        switch (2.c[productType.ordinal()]) {
            case 1:
                bVar = b.d;
                break;
            case 2:
                bVar = b.c;
                break;
            case 3:
                bVar = b.b;
                break;
            case 4:
                bVar = b.e;
                break;
            case 5:
                bVar = b.j;
                break;
            case 6:
                bVar = b.k;
                break;
            case 7:
                bVar = b.l;
                break;
            case 8:
                bVar = b.m;
                break;
            case 9:
                bVar = b.n;
                break;
            case 10:
                bVar = b.g;
                break;
            case 11:
                bVar = b.h;
                break;
            case 12:
                bVar = b.i;
                break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                bVar = b.p;
                break;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
                bVar = b.f;
                break;
            case 23:
                bVar = b.s;
                break;
            case 24:
            case 25:
                bVar = b.o;
                break;
            case 26:
                bVar = b.q;
                break;
            case 27:
                bVar = b.r;
                break;
            default:
                bVar = b.a;
                break;
        }
        if (this.d == bVar) {
            return false;
        }
        this.e = this.d;
        this.d = bVar;
        c.a().e(this.d);
        return true;
    }

    private boolean j() {
        c cVar;
        d.a(a, "============mRcComponentType updateRcComponentType: ", false);
        ProductType productType = ProductType.None;
        if (ServiceManager.getInstance().isOK()) {
            productType = i.getInstance().a();
            if (ServiceManager.getInstance().isRemoteOK()) {
                productType = i.getInstance().c();
            }
            d.a(a, "============mRcComponentType productType: " + productType, false);
        }
        c cVar2 = c.a;
        switch (2.c[productType.ordinal()]) {
            case 2:
                cVar = c.c;
                break;
            case 3:
                cVar = c.b;
                break;
            case 10:
                cVar = c.e;
                break;
            case 11:
            case 12:
            case 24:
            case 25:
                cVar = c.f;
                break;
            case 13:
                cVar = c.g;
                break;
            case 14:
                cVar = c.h;
                break;
            case 15:
            case 16:
                cVar = c.i;
                break;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
                cVar = c.d;
                break;
            case 23:
                cVar = c.j;
                break;
            default:
                cVar = c.a;
                break;
        }
        d.a(a, "============mRcComponentType: " + cVar, false);
        if (this.f == cVar) {
            return false;
        }
        this.g = this.f;
        this.f = cVar;
        c.a().e(this.f);
        return true;
    }

    private boolean k() {
        d.a(a, "============mCameraComponentType updateRcComponentType: ", false);
        a aVar = a.a;
        if (ServiceManager.getInstance().isRemoteOK() && ServiceManager.getInstance().isOK()) {
            DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
            if (!(instance == null || !instance.isGetted() || instance.getCameraType() == null)) {
                switch (2.e[instance.getCameraType().ordinal()]) {
                    case 1:
                        switch (2.d[a.getInstance().d().ordinal()]) {
                            case 1:
                            case 2:
                                aVar = a.b;
                                break;
                            default:
                                break;
                        }
                    case 2:
                        aVar = a.c;
                        break;
                    case 3:
                        aVar = a.d;
                        break;
                    case 4:
                    case 5:
                        aVar = a.e;
                        break;
                    case 6:
                        aVar = a.f;
                        break;
                    case 7:
                        aVar = a.g;
                        break;
                }
            }
        }
        if (this.h == aVar) {
            return false;
        }
        this.i = this.h;
        this.h = aVar;
        c.a().e(this.h);
        return true;
    }

    public void onEventBackgroundThread(ProductType productType) {
        d.a(a, "============productType: " + productType, false);
        l();
    }

    public void onEventBackgroundThread(i.a aVar) {
        d.a(a, "============DJIProductRcEvent: " + aVar, false);
        l();
    }

    public void onEventBackgroundThread(dji.midware.f.b bVar) {
        l();
    }

    public void onEventBackgroundThread(p pVar) {
        l();
    }

    public void onEventBackgroundThread(o oVar) {
        l();
    }

    public void onEventBackgroundThread(DJIUpgradeP4Service.a aVar) {
        if (aVar != DJIUpgradeP4Service.a.Disconnect && aVar != DJIUpgradeP4Service.a.ConnectP4MC) {
            l();
        }
    }

    private void l() {
        if (!DJIUpgradeP4Service.e()) {
            this.j.removeCallbacksAndMessages(null);
            this.j.postDelayed(new 1(this), 500);
        }
    }
}
