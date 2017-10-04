package dji.midware.data.model.b;

import android.util.Log;
import com.alipay.sdk.j.i;
import dji.log.DJILogHelper;
import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataBatteryActiveStatus;
import dji.midware.data.model.P3.DataCameraActiveStatus;
import dji.midware.data.model.P3.DataFlycActiveStatus;
import dji.midware.data.model.P3.DataGimbalActiveStatus;
import dji.midware.data.model.P3.DataOsdActiveStatus;
import dji.midware.data.model.b.a.a;
import dji.midware.e.e;
import dji.pilot.usercenter.protocol.d;

public class b extends n implements e {
    private DeviceType a;
    private a b;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[DeviceType.values().length];

        static {
            try {
                a[DeviceType.CAMERA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[DeviceType.FLYC.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[DeviceType.BATTERY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[DeviceType.OSD.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[DeviceType.GIMBAL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public b a(DeviceType deviceType) {
        this.a = deviceType;
        return this;
    }

    public a a() {
        return this.b;
    }

    public void setRecData(byte[] bArr) {
        super.setRecData(bArr);
        this.b = a.find(((Integer) get(0, 1, Integer.class)).intValue());
        DJILogHelper.getInstance().LOGD("", "active get version[" + this.b + i.b + this.a + d.H, false, true);
        switch (AnonymousClass1.a[this.a.ordinal()]) {
            case 1:
                DataCameraActiveStatus.getInstance().setVersion(this.b);
                return;
            case 2:
                DataFlycActiveStatus.getInstance().setVersion(this.b);
                return;
            case 3:
                DataBatteryActiveStatus.getInstance().setVersion(this.b);
                return;
            case 4:
                DataOsdActiveStatus.getInstance().setVersion(this.b);
                return;
            case 5:
                DataGimbalActiveStatus.getInstance().setVersion(this.b);
                return;
            default:
                return;
        }
    }

    protected void doPack() {
        this._sendData = new byte[1];
        this._sendData[0] = (byte) dji.midware.data.model.b.a.b.a.a();
    }

    protected void LogPack(String str) {
    }

    public void start(dji.midware.e.d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
        if ((c == ProductType.PM820 || c == ProductType.PM820PRO) && this.a == DeviceType.BATTERY) {
            cVar.g = 0;
            Log.e("pm820", "**into active battery getversion id 0");
        }
        cVar.h = this.a.value();
        cVar.j = q.a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = dji.midware.data.config.P3.q.b.a.a();
        cVar.m = p.a.a();
        cVar.n = dji.midware.data.config.P3.d.a.s.a();
        cVar.v = 1000;
        cVar.w = 2;
        start(cVar, dVar);
    }
}
