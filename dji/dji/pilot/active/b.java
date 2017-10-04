package dji.pilot.active;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import com.google.android.gms.common.api.CommonStatusCodes;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.log.DJILogHelper;
import dji.logic.f.d;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataBatteryActiveStatus;
import dji.midware.data.model.P3.DataCameraActiveStatus;
import dji.midware.data.model.P3.DataFlycActiveStatus;
import dji.midware.data.model.P3.DataGimbalActiveStatus;
import dji.midware.data.model.P3.DataGlassActiveStatus;
import dji.midware.data.model.P3.DataOsdActiveStatus;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.pilot.welcome.activity.DJIActiveActivity;
import dji.pilot2.usercenter.activate.a;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.Iterator;

public class b {
    private static b e;
    ArrayList<DeviceType> a = new ArrayList(4);
    private String b = "DJIActiveLauncher";
    private Context c;
    private c d = c.getInstance();
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private int l = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
    private int m = 0;
    private boolean n = false;
    private Handler o = new Handler(new 1(this));

    public void a(Context context) {
        this.c = context.getApplicationContext();
        c.a().a((Object) this);
        if (dji.pilot.fpv.d.b.h(null)) {
            if (DataCameraActiveStatus.getInstance().isGetted()) {
                onEventBackgroundThread(DataCameraActiveStatus.getInstance());
            }
            if (DataOsdActiveStatus.getInstance().isGetted()) {
                onEventBackgroundThread(DataOsdActiveStatus.getInstance());
            }
        }
    }

    public static b getInstance() {
        if (e == null) {
            e = new b();
        }
        return e;
    }

    private b() {
    }

    public void onEventBackgroundThread(DataFlycActiveStatus dataFlycActiveStatus) {
        if (!this.f && !this.n && !a.getInstance().h() && DJIUpgradeP4Service.d() && !dji.dbox.upgrade.p4.a.a.n && i.getInstance().e() && dataFlycActiveStatus.isPushSnAvailable()) {
            this.f = true;
            DJIGlobalService.b = true;
            this.a.add(DeviceType.FLYC);
            b();
            c();
            dji.pilot2.usercenter.activate.c.a("Start active from FlyC status.");
        }
    }

    public void onEventBackgroundThread(DataCameraActiveStatus dataCameraActiveStatus) {
        if (dataCameraActiveStatus.isPushSnAvailable() && !this.g && !this.n && DJIUpgradeP4Service.d() && i.getInstance().e()) {
            this.g = true;
            this.a.add(DeviceType.CAMERA);
            dji.pilot2.usercenter.activate.c.a("add camera to device list.");
            b();
            dji.pilot2.usercenter.activate.c.a("Get camera active status.");
        }
    }

    public void onEventBackgroundThread(DataGlassActiveStatus dataGlassActiveStatus) {
        if (dataGlassActiveStatus.isPushSnAvailable() && !this.h && !this.n && DJIUpgradeP4Service.d() && i.getInstance().e()) {
            this.h = true;
            this.a.add(DeviceType.GLASS);
            b();
            dji.pilot2.usercenter.activate.c.a("Get Glass active status.");
        }
    }

    public void onEventBackgroundThread(DataBatteryActiveStatus dataBatteryActiveStatus) {
        if (dji.pilot.publics.e.a.d(null)) {
            int senderIndex = dataBatteryActiveStatus.getSenderIndex();
            DJILogHelper.getInstance().LOGD("pm820", "**into DataBatteryActiveStatus index: " + senderIndex, false, true);
            if (!this.d.a(senderIndex) && !this.d.a() && DJIUpgradeP4Service.d() && i.getInstance().e()) {
                Log.e("pm820", "battery into ****record");
                this.d.a(senderIndex, true);
                this.d.a(dataBatteryActiveStatus.getPushSN());
                this.a.add(DeviceType.BATTERY);
                b();
            }
        } else if (!this.i && !this.n && DJIUpgradeP4Service.d() && i.getInstance().e()) {
            this.i = true;
            this.a.add(DeviceType.BATTERY);
            b();
            dji.pilot2.usercenter.activate.c.a("Get Battery active status.");
        }
    }

    public void onEventBackgroundThread(DataGimbalActiveStatus dataGimbalActiveStatus) {
        if (!this.k) {
            this.k = true;
            this.a.add(DeviceType.GIMBAL);
            if (d.c(null)) {
                a.getInstance().d(true);
                b();
                c();
            } else {
                b();
            }
            dji.pilot2.usercenter.activate.c.a("Get Gimbal active status.");
        }
    }

    public void onEventBackgroundThread(DataOsdActiveStatus dataOsdActiveStatus) {
        if (!this.j && !this.n && !a.getInstance().h()) {
            if ((DJIUpgradeP4Service.d() || i.getInstance().c().isFromWifi()) && i.getInstance().e()) {
                this.j = true;
                ProductType c = i.getInstance().c();
                if (d.a(c)) {
                    this.a.add(DeviceType.OFDM);
                } else {
                    this.a.add(DeviceType.OSD);
                }
                b();
                if (d.a(null)) {
                    if (d.b(null)) {
                        a.getInstance().d(true);
                    } else {
                        a.getInstance().b(true);
                    }
                    DJILogHelper.getInstance().LOGD(this.b, "DataOsdActiveStatus startActive " + c, "active");
                    c();
                }
                dji.pilot2.usercenter.activate.c.a("Get OSD active status.");
            }
        }
    }

    private void b() {
        this.m++;
        if (d.a(null)) {
            int i;
            if (d.b(null)) {
                i = 2;
            } else {
                i = 1;
            }
            if (this.m == i) {
                this.o.removeMessages(0);
                d();
                return;
            }
        }
        if (this.m == 2 && dji.pilot.fpv.d.b.h(null)) {
            this.o.removeMessages(0);
            d();
            return;
        }
        if (this.m == 4) {
            this.o.removeMessages(0);
            d();
        }
        if (this.m == 1) {
            this.o.sendEmptyMessageDelayed(0, (long) this.l);
        }
    }

    private void c() {
        dji.pilot2.usercenter.activate.c.a("Start Active page.");
        Intent intent = new Intent(this.c, DJIActiveActivity.class);
        intent.setFlags(268435456);
        this.c.startActivity(intent);
    }

    private void d() {
        dji.pilot2.usercenter.activate.c.a("Start offline active. devices list=" + this.a.size());
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            dji.pilot2.usercenter.activate.c.a("Start offline active. devices =" + ((DeviceType) it.next()));
        }
        DJILogHelper.getInstance().LOGD(this.b, "active status set device " + this.a.size(), false, true);
        DJIActiveController.a(this.a);
        if (!this.f && !a.getInstance().h() && !a.getInstance().i().booleanValue()) {
            DJIActiveController dJIActiveController = new DJIActiveController(this.c, null);
            if (DJINetWorkReceiver.a(this.c)) {
                DJILogHelper.getInstance().LOGD(this.b, "CT active status online", false, true);
                try {
                    dJIActiveController.a();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            DJILogHelper.getInstance().LOGD(this.b, "active status offline", false, true);
            dJIActiveController.d();
        }
    }

    public void onEventBackgroundThread(p pVar) {
        switch (2.a[pVar.ordinal()]) {
            case 1:
                DJILogHelper.getInstance().LOGD(this.b, "disconnect", true, true);
                e();
                return;
            case 2:
                DJILogHelper.getInstance().LOGD(this.b, "connect", true, true);
                if (DataFlycActiveStatus.getInstance().isPushSnAvailable() && DataFlycActiveStatus.getInstance().isActive()) {
                    dji.pilot2.usercenter.activate.c.a("Start deal active from connect.");
                    onEventBackgroundThread(DataFlycActiveStatus.getInstance());
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (2.b[oVar.ordinal()]) {
            case 1:
                e();
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(c.a aVar) {
        this.a.clear();
        this.m = 0;
        this.d.a(false);
    }

    private void e() {
        this.n = false;
        this.m = 0;
        this.a.clear();
        this.f = false;
        this.g = false;
        this.i = false;
        this.j = false;
        this.k = false;
        this.d.d();
    }

    public void a() {
        c.a().d((Object) this);
    }
}
