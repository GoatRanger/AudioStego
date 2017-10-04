package dji.midware.data.manager.P3;

import android.content.Context;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.data.model.P3.DataOsdGetPushPowerStatus;
import dji.midware.f.a;
import dji.midware.f.b;
import dji.midware.usbhost.P3.NativeRcController;
import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.a.c;

public class i {
    private static final String a = "DJIProductManager";
    private static final String b = "Set Type:";
    private static i c = null;
    private final String d = "PRODUCT_TYPE_KEY";
    private final String e = "PRODUCT_RC_KEY";
    private final String f = "PRODUCT_CAMERA_KEY";
    private Context g;
    private volatile boolean h = false;
    private volatile boolean i = false;
    private ProductType j = null;
    private CameraType k = null;
    private ProductType l = ProductType.Tomato;
    private ProductType m = ProductType.Tomato;
    private CameraType n;
    private DroneType o;

    public static synchronized i build(Context context) {
        i iVar;
        synchronized (i.class) {
            if (c == null) {
                c = new i(context);
            }
            iVar = c;
        }
        return iVar;
    }

    public static i getInstance() {
        return c;
    }

    private i(Context context) {
        this.g = context.getApplicationContext();
        int b = dji.midware.util.i.b(this.g, "PRODUCT_TYPE_KEY", ProductType.Orange.value());
        int b2 = dji.midware.util.i.b(this.g, "PRODUCT_RC_KEY", b);
        int b3 = dji.midware.util.i.b(this.g, "PRODUCT_CAMERA_KEY", 0);
        this.l = ProductType.find(b);
        this.j = ProductType.find(b2);
        this.k = CameraType.find(b3);
        this.m = this.l;
        DJILogHelper.getInstance().LOGD(a, "lType=" + this.l + " lrcType=" + this.j, false, true);
        c.a().a((Object) this);
        DJILogHelper.getInstance().LOGD(a, "DJIProductManager register");
    }

    public ProductType a() {
        return this.j;
    }

    public CameraType b() {
        return this.k;
    }

    private void a(CameraType cameraType) {
        this.k = cameraType;
        dji.midware.util.i.a(this.g, "PRODUCT_CAMERA_KEY", this.k.value());
    }

    public ProductType c() {
        return this.l;
    }

    public ProductType d() {
        return this.m;
    }

    public boolean e() {
        return this.h;
    }

    public synchronized void a(boolean z) {
        this.h = z;
        if (z) {
            c.a().e(b.a);
        }
    }

    public synchronized void a(ProductType productType) {
        if (this.l != productType) {
            DJILogHelper.getInstance().LOGD(a, "type=" + productType + " last=" + this.l, false, true);
            this.m = this.l;
            this.l = productType;
            c.a().e(this.l);
            i();
            dji.midware.util.i.a(this.g, "PRODUCT_TYPE_KEY", this.l.value());
        }
        int i = 3;
        if (this.l == ProductType.Tomato || this.l == ProductType.Pomato) {
            i = 4;
        }
        NativeRcController.a(i);
    }

    public synchronized void b(ProductType productType) {
        if (productType == ProductType.litchiX && this.l == ProductType.litchiS) {
            productType = ProductType.litchiS;
        }
        if (this.j != productType) {
            this.j = productType;
            c.a().e(a.a);
            dji.midware.util.i.a(this.g, "PRODUCT_RC_KEY", this.j.value());
        }
        DJILogHelper.getInstance().LOGD(a, "===rcType===[" + productType + d.H, false, true);
    }

    private void i() {
        if (this.l == ProductType.litchiS && this.j == ProductType.litchiX) {
            b(ProductType.litchiS);
        }
        if (this.l == ProductType.Longan && this.j == ProductType.litchiC) {
            b(ProductType.Longan);
        }
        if (this.l == ProductType.A2 || this.l == ProductType.A3 || this.l == ProductType.PM820 || this.l == ProductType.PM820PRO || this.l == ProductType.N3) {
            b(ProductType.Grape2);
        }
        if (this.j == null) {
            b(this.l);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(dji.midware.f.b r6) {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = 0;
        r1 = dji.midware.data.manager.P3.i.1.b;	 Catch:{ all -> 0x0083 }
        r2 = r6.ordinal();	 Catch:{ all -> 0x0083 }
        r1 = r1[r2];	 Catch:{ all -> 0x0083 }
        switch(r1) {
            case 1: goto L_0x0048;
            case 2: goto L_0x0048;
            case 3: goto L_0x0048;
            case 4: goto L_0x004b;
            case 5: goto L_0x00be;
            default: goto L_0x000d;
        };	 Catch:{ all -> 0x0083 }
    L_0x000d:
        r1 = 0;
        r5.b(r1);	 Catch:{ all -> 0x0083 }
    L_0x0011:
        if (r0 == 0) goto L_0x0022;
    L_0x0013:
        r1 = r5.j;	 Catch:{ all -> 0x0083 }
        if (r1 == r0) goto L_0x0022;
    L_0x0017:
        r5.j = r0;	 Catch:{ all -> 0x0083 }
        r1 = dji.thirdparty.a.c.a();	 Catch:{ all -> 0x0083 }
        r2 = dji.midware.data.manager.P3.i.a.a;	 Catch:{ all -> 0x0083 }
        r1.e(r2);	 Catch:{ all -> 0x0083 }
    L_0x0022:
        r1 = dji.log.DJILogHelper.getInstance();	 Catch:{ all -> 0x0083 }
        r2 = "";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0083 }
        r3.<init>();	 Catch:{ all -> 0x0083 }
        r4 = "===rcType bylink===[";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0083 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0083 }
        r3 = "]";
        r0 = r0.append(r3);	 Catch:{ all -> 0x0083 }
        r0 = r0.toString();	 Catch:{ all -> 0x0083 }
        r3 = 0;
        r4 = 1;
        r1.LOGD(r2, r0, r3, r4);	 Catch:{ all -> 0x0083 }
        monitor-exit(r5);
        return;
    L_0x0048:
        r0 = dji.midware.data.config.P3.ProductType.litchiX;	 Catch:{ all -> 0x0083 }
        goto L_0x0011;
    L_0x004b:
        r1 = dji.midware.data.model.P3.DataCameraGetPushStateInfo.getInstance();	 Catch:{ all -> 0x0083 }
        r1 = r1.isGetted();	 Catch:{ all -> 0x0083 }
        if (r1 == 0) goto L_0x00b4;
    L_0x0055:
        r1 = dji.midware.data.model.P3.DataCameraGetPushStateInfo.getInstance();	 Catch:{ all -> 0x0083 }
        r1 = r1.getCameraType();	 Catch:{ all -> 0x0083 }
        r5.n = r1;	 Catch:{ all -> 0x0083 }
        r1 = r5.n;	 Catch:{ all -> 0x0083 }
        r5.a(r1);	 Catch:{ all -> 0x0083 }
        r1 = dji.midware.data.manager.P3.i.1.a;	 Catch:{ all -> 0x0083 }
        r2 = r5.n;	 Catch:{ all -> 0x0083 }
        r2 = r2.ordinal();	 Catch:{ all -> 0x0083 }
        r1 = r1[r2];	 Catch:{ all -> 0x0083 }
        switch(r1) {
            case 1: goto L_0x0086;
            case 2: goto L_0x0089;
            case 3: goto L_0x008c;
            case 4: goto L_0x008c;
            default: goto L_0x0071;
        };	 Catch:{ all -> 0x0083 }
    L_0x0071:
        r1 = 0;
        r5.b(r1);	 Catch:{ all -> 0x0083 }
    L_0x0075:
        r1 = r5.e();	 Catch:{ all -> 0x0083 }
        if (r1 != 0) goto L_0x0011;
    L_0x007b:
        r5.a(r0);	 Catch:{ all -> 0x0083 }
        r1 = 1;
        r5.b(r1);	 Catch:{ all -> 0x0083 }
        goto L_0x0011;
    L_0x0083:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x0086:
        r0 = dji.midware.data.config.P3.ProductType.Longan;	 Catch:{ all -> 0x0083 }
        goto L_0x0075;
    L_0x0089:
        r0 = dji.midware.data.config.P3.ProductType.LonganZoom;	 Catch:{ all -> 0x0083 }
        goto L_0x0075;
    L_0x008c:
        r0 = dji.midware.data.config.P3.ProductType.KumquatX;	 Catch:{ all -> 0x0083 }
        r1 = r5.e();	 Catch:{ all -> 0x0083 }
        if (r1 != 0) goto L_0x0071;
    L_0x0094:
        r5.a(r0);	 Catch:{ all -> 0x0083 }
        r1 = "Set Type:";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0083 }
        r2.<init>();	 Catch:{ all -> 0x0083 }
        r3 = "updateRcTypeByLinkType:";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0083 }
        r2 = r2.append(r0);	 Catch:{ all -> 0x0083 }
        r2 = r2.toString();	 Catch:{ all -> 0x0083 }
        android.util.Log.d(r1, r2);	 Catch:{ all -> 0x0083 }
        r1 = 1;
        r5.b(r1);	 Catch:{ all -> 0x0083 }
        goto L_0x0071;
    L_0x00b4:
        r0 = dji.midware.data.config.P3.ProductType.litchiC;	 Catch:{ all -> 0x0083 }
        r1 = r5.e();	 Catch:{ all -> 0x0083 }
        if (r1 != 0) goto L_0x0011;
    L_0x00bc:
        goto L_0x0011;
    L_0x00be:
        r1 = 1;
        r5.b(r1);	 Catch:{ all -> 0x0083 }
        r1 = dji.midware.data.config.P3.ProductType.LonganMobile;	 Catch:{ all -> 0x0083 }
        r5.a(r1);	 Catch:{ all -> 0x0083 }
        r1 = 1;
        r5.a(r1);	 Catch:{ all -> 0x0083 }
        goto L_0x000d;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.midware.data.manager.P3.i.a(dji.midware.f.b):void");
    }

    public void onEventBackgroundThread(b bVar) {
        DJILogHelper.getInstance().LOGD("", "===DJIProductManager===[" + bVar + d.H, false, true);
        a(bVar);
    }

    public void onEventBackgroundThread(DataOsdGetPushPowerStatus dataOsdGetPushPowerStatus) {
        DJILogHelper.getInstance().LOGD("", "===DataOsdGetPushPowerStatus===[" + dataOsdGetPushPowerStatus.getPowerStatus() + d.H, false, true);
        ProductType productType = null;
        if (this.n != null) {
            switch (1.a[this.n.ordinal()]) {
                case 1:
                    productType = ProductType.Longan;
                    break;
                case 2:
                    productType = ProductType.LonganZoom;
                    break;
                case 5:
                    productType = ProductType.LonganPro;
                    break;
                case 6:
                    productType = ProductType.LonganRaw;
                    break;
            }
        }
        if (productType != null) {
            a(productType);
            a(true);
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        this.n = dataCameraGetPushStateInfo.getCameraType();
        if (this.k != this.n || ServiceManager.getInstance().isRemoteOK()) {
            a(this.n);
            DJILogHelper.getInstance().LOGD(b, "cameraType=" + this.n, false, true);
        }
        if ((!e() || this.l == ProductType.Grape2) && !dataCameraGetPushStateInfo.isPushLosed()) {
            ProductType productType = ProductType.Orange;
            switch (1.a[this.n.ordinal()]) {
                case 1:
                    switch (1.b[a.getInstance().d().ordinal()]) {
                        case 1:
                        case 2:
                            productType = ProductType.Orange;
                            break;
                        case 4:
                            productType = ProductType.Longan;
                            break;
                        default:
                            productType = ProductType.Longan;
                            break;
                    }
                case 2:
                    switch (1.b[a.getInstance().d().ordinal()]) {
                        case 1:
                        case 2:
                            productType = ProductType.OrangeCV600;
                            break;
                        case 4:
                            productType = ProductType.LonganZoom;
                            break;
                        default:
                            break;
                    }
                case 3:
                    productType = ProductType.KumquatX;
                    break;
                case 4:
                    productType = ProductType.KumquatS;
                    break;
                case 5:
                    switch (1.b[a.getInstance().d().ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                            productType = ProductType.BigBanana;
                            break;
                        case 4:
                            productType = ProductType.LonganRaw;
                            break;
                        default:
                            productType = ProductType.LonganRaw;
                            break;
                    }
                case 6:
                    switch (1.b[a.getInstance().d().ordinal()]) {
                        case 1:
                        case 2:
                            productType = ProductType.OrangeRAW;
                            break;
                        case 4:
                            productType = ProductType.LonganRaw;
                            break;
                        default:
                            break;
                    }
                case 7:
                    productType = ProductType.litchiS;
                    break;
                case 8:
                    productType = ProductType.litchiX;
                    break;
                case 9:
                    productType = ProductType.litchiC;
                    break;
                case 10:
                    productType = ProductType.Tomato;
                    break;
                case 11:
                    productType = ProductType.P34K;
                    break;
                case 12:
                    productType = ProductType.Pomato;
                    break;
                case 13:
                case 14:
                    if (!DataOsdGetPushCommon.getInstance().isGetted() || DataOsdGetPushCommon.getInstance().getDroneType() != DroneType.OpenFrame) {
                        productType = ProductType.Olives;
                        break;
                    } else {
                        productType = ProductType.N1;
                        break;
                    }
                    break;
            }
            if (productType != null) {
                a(productType);
                DJILogHelper.getInstance().LOGD(b, "DataCameraGetPushStateInfo:" + productType, false, true);
                a(true);
            }
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (!dataOsdGetPushCommon.isPushLosed() && this.o == null) {
            this.o = dataOsdGetPushCommon.getDroneType();
            switch (1.c[this.o.ordinal()]) {
                case 1:
                    DJILogHelper.getInstance().LOGD("", "droneType=" + this.o, false, true);
                    a(ProductType.N1);
                    a(true);
                    return;
                case 2:
                    DJILogHelper.getInstance().LOGD("", "droneType=" + this.o, false, true);
                    a(ProductType.A3);
                    a(true);
                    return;
                case 3:
                    DJILogHelper.getInstance().LOGD("", "droneType=" + this.o, false, true);
                    a(ProductType.PM820);
                    a(true);
                    return;
                case 4:
                    DJILogHelper.getInstance().LOGD("", "droneType=" + this.o, false, true);
                    a(ProductType.PM820PRO);
                    a(true);
                    return;
                case 5:
                    DJILogHelper.getInstance().LOGD("", "droneType=" + this.o, false, true);
                    a(ProductType.A2);
                    a(true);
                    return;
                case 6:
                    DJILogHelper.getInstance().LOGD("", "droneType=" + this.o, false, true);
                    a(ProductType.Orange2);
                    a(true);
                    return;
                case 7:
                    DJILogHelper.getInstance().LOGD("", "droneType=" + this.o, false, true);
                    a(ProductType.N3);
                    a(true);
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (1.d[oVar.ordinal()]) {
            case 1:
                this.o = null;
                this.n = null;
                a(false);
                ServiceManager.getInstance().onDisconnect();
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(p pVar) {
        switch (1.e[pVar.ordinal()]) {
            case 1:
                b(false);
                a(false);
                return;
            default:
                return;
        }
    }

    public boolean f() {
        return this.i;
    }

    public synchronized void b(boolean z) {
        this.i = z;
    }

    public boolean g() {
        return this.l.equals(ProductType.KumquatX) || this.l.equals(ProductType.KumquatS);
    }

    public boolean h() {
        return this.l.equals(ProductType.KumquatX) || this.l.equals(ProductType.KumquatS);
    }
}
