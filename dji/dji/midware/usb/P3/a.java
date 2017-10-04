package dji.midware.usb.P3;

import dji.log.DJILog;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataDm368GetParams;
import dji.midware.data.model.P3.DataDm368GetPushStatus;
import dji.midware.data.model.P3.DataDm368SetParams.DM368CmdId;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.e.d;
import java.util.Timer;
import java.util.TimerTask;

public class a {
    private static a b = null;
    private static final String c = "LB2VideoController";
    DataDm368GetParams a = new DataDm368GetParams();
    private b d;
    private c e = c.a;
    private a f = a.HDMI;
    private int g = -1;
    private int h = -1;

    public enum a {
        HDMI,
        AV
    }

    public enum b {
        DEFAULT,
        SINGLE,
        DUAL,
        NONE
    }

    public enum c {
        a,
        EXT
    }

    public static a getInstance() {
        if (b == null) {
            b = new a();
        }
        return b;
    }

    private a() {
        g();
    }

    private void g() {
        dji.thirdparty.a.c.a().a(this);
        h();
        new Timer().schedule(new TimerTask(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a();
            }
        }, 1500, 1500);
    }

    private void h() {
        b bVar = b.DEFAULT;
        if (i()) {
            bVar = b.SINGLE;
        }
        if (j()) {
            bVar = b.DUAL;
        }
        if (bVar != this.d || this.g != e() || this.h != f()) {
            this.d = bVar;
            dji.thirdparty.a.c.a().e(this.d);
            DJILog.d(c, "encodeMode : " + this.d);
            if (this.d == b.SINGLE) {
                k();
            } else {
                l();
            }
            this.g = e();
            this.h = f();
        }
    }

    private boolean i() {
        ProductType c = i.getInstance().c();
        if ((c == ProductType.A2 || c == ProductType.PM820 || c == ProductType.PM820PRO || c == ProductType.Grape2 || c == ProductType.N3 || c == ProductType.A3) && DataDm368GetPushStatus.getInstance().isGetted() && DataDm368GetPushStatus.getInstance().getEncodeMode() == 0) {
            return true;
        }
        return false;
    }

    private boolean j() {
        boolean z = true;
        ProductType c = i.getInstance().c();
        if (c == ProductType.A2 || c == ProductType.PM820 || c == ProductType.PM820PRO || c == ProductType.Grape2 || c == ProductType.N3 || c == ProductType.A3) {
            if (!DataDm368GetPushStatus.getInstance().isGetted()) {
                return false;
            }
            if (DataDm368GetPushStatus.getInstance().getEncodeMode() != 1) {
                z = false;
            }
            return z;
        } else if (c == ProductType.Tomato || c == ProductType.Pomato || c == ProductType.Orange2) {
            return true;
        } else {
            return false;
        }
    }

    private void k() {
        c cVar = null;
        if (this.d == b.SINGLE) {
            cVar = this.e;
            int e = e();
            if (e == 0) {
                cVar = c.EXT;
            } else if (e == 10) {
                cVar = c.a;
            }
        }
        if (cVar != this.e) {
            this.e = cVar;
            if (this.e != null) {
                dji.thirdparty.a.c.a().e(this.e);
            }
            DJILog.d(c, "curSingleType : " + this.e);
        }
    }

    private void l() {
        a aVar = null;
        if (this.d == b.DUAL) {
            aVar = this.f;
            ProductType c = i.getInstance().c();
            if (c == ProductType.Tomato || c == ProductType.Pomato) {
                aVar = a.HDMI;
            } else {
                int f = f();
                DJILog.d(c, "dual bandwidth : " + f);
                if (f == 0) {
                    aVar = a.AV;
                } else if (f == 10) {
                    aVar = a.HDMI;
                }
            }
        }
        if (aVar != this.f) {
            this.f = aVar;
            if (this.f != null) {
                dji.thirdparty.a.c.a().e(this.f);
            }
            DJILog.d(c, "curDualType : " + this.f);
        }
    }

    public void a(c cVar) {
        DJILog.d(c, "setCurSingleType : " + cVar);
        if (this.d == b.SINGLE && this.e != cVar) {
            this.e = cVar;
            if (this.e != null) {
                dji.thirdparty.a.c.a().e(this.e);
            }
        }
    }

    public void a(a aVar) {
        if (this.d == b.DUAL && this.f != aVar) {
            this.f = aVar;
            if (this.f != null) {
                dji.thirdparty.a.c.a().e(this.f);
            }
        }
    }

    public void a() {
        this.a.set(DM368CmdId.BandwidthPercentage).start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                a.a("get BandwidthPercentage from drone success");
                this.a.h();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                a.a("get BandwidthPercentage from drone fail");
            }
        });
    }

    public c b() {
        return this.e;
    }

    public a c() {
        return this.f;
    }

    public b d() {
        return this.d;
    }

    public void onEventBackgroundThread(DataDm368GetPushStatus dataDm368GetPushStatus) {
        h();
    }

    public void onEventBackgroundThread(dji.midware.data.model.c.a aVar) {
        h();
    }

    public void onEventBackgroundThread(p pVar) {
        h();
    }

    public void onEventBackgroundThread(ProductType productType) {
        h();
        a();
    }

    public void onEventBackgroundThread(o oVar) {
        h();
    }

    public int e() {
        return dji.midware.data.model.c.a.get().b().getBandWidthPercent();
    }

    public int f() {
        int bandWidth;
        if (ProductType.Orange2 == i.getInstance().c()) {
            if (DataRcGetPushParams.getInstance().isGetted()) {
                bandWidth = DataRcGetPushParams.getInstance().getBandWidth();
            }
            bandWidth = -1;
        } else {
            if (this.a.isGetted()) {
                bandWidth = this.a.getBandWidthPercentInstant();
            }
            bandWidth = -1;
        }
        if (bandWidth == -1) {
            return 10;
        }
        return bandWidth;
    }

    public static void a(String str) {
    }
}
