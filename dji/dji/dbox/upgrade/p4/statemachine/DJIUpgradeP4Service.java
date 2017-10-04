package dji.dbox.upgrade.p4.statemachine;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.g;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCommonGetDeviceInfo;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataNotifyDisconnect;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.e.d;
import dji.midware.f.b;
import dji.thirdparty.a.c;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class DJIUpgradeP4Service extends Service {
    public static a a = a.Disconnect;
    protected static String b = "";
    protected static int c = 90;
    private static boolean j = false;
    private static f k = null;
    private static final int l = 0;
    private static final int m = 1;
    private static dji.dbox.upgrade.p4.c.a s;
    int d = 0;
    DataCommonGetVersion e = new DataCommonGetVersion();
    DataCommonGetVersion f = new DataCommonGetVersion();
    DataCommonGetDeviceInfo g = new DataCommonGetDeviceInfo();
    private String h = getClass().getSimpleName();
    private boolean i = false;
    private Handler n;
    private Timer o = null;
    private Timer p;
    private DeviceType q = DeviceType.OTHER;
    private int r = 0;

    public enum a {
        ConnectP4MC,
        ConnectP4RC,
        ConnectOther,
        Disconnect
    }

    public static String a() {
        return b;
    }

    public static String b() {
        String str = b;
        if (str.equals("wm330")) {
            return ProductType.Tomato._name();
        }
        if (str.equals("wm331")) {
            return ProductType.Pomato._name();
        }
        if (str.equals("wm620")) {
            return ProductType.Orange2._name();
        }
        if (str.equals("wm220")) {
            return ProductType.KumquatX._name();
        }
        if (str.equals("wm221")) {
            return ProductType.KumquatS._name();
        }
        if (str.equals("")) {
            return "Default";
        }
        return str;
    }

    public static boolean c() {
        return a == a.ConnectP4MC || a == a.ConnectP4RC;
    }

    public static boolean d() {
        return a == a.Disconnect || a == a.ConnectOther || a == a.ConnectP4RC;
    }

    public static boolean e() {
        return a == a.ConnectP4MC;
    }

    public static boolean f() {
        return a == a.ConnectP4MC && dji.midware.f.a.getInstance().d() != b.WIFI;
    }

    public static boolean g() {
        if (i.getInstance().e() && h() && d()) {
            return false;
        }
        return j;
    }

    public static boolean h() {
        return "wm330".equals(b) || "wm331".equals(b) || "wm620".equals(b);
    }

    private boolean a(DroneType droneType) {
        return droneType == DroneType.P4 || droneType == DroneType.Pomato || droneType == DroneType.Orange2;
    }

    public static f i() {
        return k;
    }

    public static synchronized void a(Context context) {
        synchronized (DJIUpgradeP4Service.class) {
            if (k == null) {
                k = new f(context.getApplicationContext());
                k.d();
            }
        }
    }

    public void onCreate() {
        super.onCreate();
        if (ServiceManager.getInstance().isConnected()) {
            o();
        } else {
            r();
        }
        HandlerThread handlerThread = new HandlerThread("upgrade");
        handlerThread.start();
        this.n = new Handler(handlerThread.getLooper(), new Callback(this) {
            final /* synthetic */ DJIUpgradeP4Service a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        dji.dbox.upgrade.p4.a.a.a(this.a.h, "camera connect 2");
                        this.a.p();
                        this.a.o();
                        break;
                    case 1:
                        dji.dbox.upgrade.p4.a.a.a(this.a.h, "camera disconnect 2");
                        this.a.r();
                        break;
                }
                return false;
            }
        });
        c.a().a((Object) this);
    }

    private void n() throws UnsupportedEncodingException {
        new dji.dbox.upgrade.p4.c.a(this, "wm331").b(new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ DJIUpgradeP4Service a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                str.toString();
            }

            public void a(Throwable th, int i, String str) {
                str.toString();
            }
        });
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    public void onEventBackgroundThread(p pVar) {
        switch (pVar) {
            case ConnectOK:
                dji.dbox.upgrade.p4.a.a.a(this.h, "connect 2 " + dji.dbox.upgrade.p4.a.a.n);
                p();
                if (!dji.dbox.upgrade.p4.a.a.n) {
                    o();
                    return;
                }
                return;
            case ConnectLose:
                dji.dbox.upgrade.p4.a.a.a(this.h, "disconnect 2");
                r();
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(o oVar) {
        if (!j || dji.dbox.upgrade.p4.a.a.n) {
            dji.dbox.upgrade.p4.a.a.a(this.h, "connect camera 2 " + dji.dbox.upgrade.p4.a.a.n);
            return;
        }
        switch (oVar) {
            case ConnectOK:
                this.n.removeMessages(1);
                this.n.removeMessages(0);
                this.n.sendEmptyMessageDelayed(0, 1000);
                return;
            case ConnectLose:
                return;
            default:
                return;
        }
    }

    private void o() {
        t();
        u();
        if (this.o == null) {
            this.o = new Timer();
            this.o.schedule(new TimerTask(this) {
                final /* synthetic */ DJIUpgradeP4Service a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.i && DJIUpgradeP4Service.s != null) {
                        boolean z = this.a.q == DeviceType.DM368 || this.a.q == DeviceType.DM368_G;
                        DJIUpgradeP4Service.j = z;
                        if (DJIUpgradeP4Service.j || i.getInstance().e()) {
                            dji.dbox.upgrade.p4.a.a.a(this.a.h, "isConnectP4Series=" + DJIUpgradeP4Service.j + " getDroneType=" + DataOsdGetPushCommon.getInstance().getDroneType());
                            if (!ServiceManager.getInstance().isRemoteOK() || i.getInstance().c().isFromWifi() || DJIUpgradeP4Service.j || DataOsdGetPushCommon.getInstance().getDroneType() != DroneType.Unknown) {
                                DroneType droneType = DataOsdGetPushCommon.getInstance().getDroneType();
                                if (!DJIUpgradeP4Service.j && this.a.a(droneType)) {
                                    DJIUpgradeP4Service.j = true;
                                }
                                if (this.a.q == DeviceType.OTHER) {
                                    DJIUpgradeP4Service.a = a.ConnectOther;
                                } else if (DJIUpgradeP4Service.b == null) {
                                    DJIUpgradeP4Service.a = a.ConnectOther;
                                    c.a().e(DJIUpgradeP4Service.a);
                                } else if (DJIUpgradeP4Service.j && this.a.q == DeviceType.DM368) {
                                    DJIUpgradeP4Service.a = a.ConnectP4MC;
                                    c.a().e(DJIUpgradeP4Service.a);
                                } else if (DJIUpgradeP4Service.j && this.a.q == DeviceType.DM368_G) {
                                    DJIUpgradeP4Service.a = a.ConnectP4RC;
                                    c.a().e(DJIUpgradeP4Service.a);
                                } else {
                                    DJIUpgradeP4Service.a = a.ConnectOther;
                                    c.a().e(DJIUpgradeP4Service.a);
                                }
                                dji.dbox.upgrade.p4.a.a.k = this.a.q;
                                this.a.o.cancel();
                                this.a.o = null;
                                return;
                            }
                            DJIUpgradeP4Service.a = a.ConnectOther;
                        }
                    }
                }
            }, 500, 1000);
        }
    }

    private void p() {
        this.d = 0;
        if (this.p != null) {
            this.p.cancel();
            this.p = null;
        }
    }

    private void q() {
        dji.dbox.upgrade.p4.a.a.n = false;
        dji.dbox.upgrade.p4.a.a.a(this.h, "disconnect");
        s();
        if (!ServiceManager.getInstance().isConnected()) {
            a = a.Disconnect;
            c.a().e(a);
        }
        p();
    }

    private void r() {
        if (dji.dbox.upgrade.p4.a.a.n) {
            dji.dbox.upgrade.p4.a.a.a(this.h, "升级中重启的特殊处理 延时60s结束升级状态");
            p();
            if (!DataNotifyDisconnect.getInstance().isGetted()) {
                dji.dbox.upgrade.p4.a.a.a(this.h, "1860重启的推送没获取到，按90秒超时计算");
                c.a().e(DataNotifyDisconnect.getInstance());
            }
            this.p = new Timer("disTimer");
            this.p.schedule(new TimerTask(this) {
                final /* synthetic */ DJIUpgradeP4Service a;

                {
                    this.a = r1;
                }

                public void run() {
                    dji.dbox.upgrade.p4.a.a.a(this.a.h, "disconnect second=" + this.a.d + " isUpProgressing=" + dji.dbox.upgrade.p4.a.a.n);
                    if (this.a.d == DJIUpgradeP4Service.c) {
                        DJIUpgradeP4Service.k.h();
                        this.a.q();
                    } else if (!dji.dbox.upgrade.p4.a.a.n) {
                        this.a.q();
                    }
                    DJIUpgradeP4Service dJIUpgradeP4Service = this.a;
                    dJIUpgradeP4Service.d++;
                }
            }, 0, 1000);
            return;
        }
        dji.dbox.upgrade.p4.a.a.a(this.h, "disconnect");
        s();
        g.getInstance().a(dji.midware.data.manager.P3.b.IDLE);
        a = a.Disconnect;
        c.a().e(a);
    }

    private void s() {
        this.i = false;
        j = false;
        if (this.o != null) {
            this.o.cancel();
            this.o = null;
        }
        dji.dbox.upgrade.p4.a.a.c();
    }

    private void t() {
        this.r++;
        this.e.setDeviceType(DeviceType.WHO);
        this.e.start(new d(this) {
            final /* synthetic */ DJIUpgradeP4Service a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                dji.dbox.upgrade.p4.a.a.a(this.a.h, "getVersion " + this.a.e.getWhoamI());
                this.a.q = this.a.e.getWhoamI();
                if (this.a.q == DeviceType.DM368) {
                    g.getInstance().a(dji.midware.data.manager.P3.b.MC);
                } else {
                    this.a.q = DeviceType.DM368_G;
                    g.getInstance().a(dji.midware.data.manager.P3.b.RC);
                }
                this.a.i = true;
                this.a.r = 0;
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                dji.dbox.upgrade.p4.a.a.a(this.a.h, "getVersion " + aVar);
                if (dji.midware.data.config.P3.a.TIMEOUT != aVar || this.a.r >= 4) {
                    this.a.r = 0;
                    if (!this.a.i) {
                        if (dji.midware.data.config.P3.a.NOCONNECT != aVar) {
                            this.a.q = DeviceType.DM368_G;
                        }
                        this.a.i = true;
                        return;
                    }
                    return;
                }
                this.a.t();
            }
        }, 500, 2);
    }

    public static dji.dbox.upgrade.p4.c.a j() {
        return s;
    }

    private void u() {
        b = "";
        this.f.setDeviceType(DeviceType.BROADCAST);
        this.f.setDeviceModel(0);
        this.f.start(new d(this) {
            final /* synthetic */ DJIUpgradeP4Service a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.f.getInfo();
                String toLowerCase = this.a.f.getHardwareVer().toLowerCase(Locale.ENGLISH);
                dji.dbox.upgrade.p4.a.a.a(this.a.h, "getDeviceInfo broadcast " + toLowerCase);
                if (toLowerCase.contains("wm330")) {
                    DJIUpgradeP4Service.b = "wm330";
                    i.getInstance().a(ProductType.Tomato);
                    i.getInstance().a(true);
                } else if (toLowerCase.contains("wm331")) {
                    DJIUpgradeP4Service.b = "wm331";
                    i.getInstance().a(ProductType.Pomato);
                    i.getInstance().a(true);
                } else if (toLowerCase.contains("wm620")) {
                    DJIUpgradeP4Service.b = "wm620";
                    i.getInstance().a(ProductType.Orange2);
                    i.getInstance().a(true);
                } else if (toLowerCase.contains("wm220")) {
                    DJIUpgradeP4Service.b = "wm220";
                    if (toLowerCase.contains("ac")) {
                        i.getInstance().a(ProductType.KumquatX);
                        i.getInstance().a(true);
                        this.a.q = DeviceType.DM368;
                    } else {
                        i.getInstance().a(ProductType.KumquatX);
                        this.a.q = DeviceType.DM368_G;
                    }
                } else if (toLowerCase.contains("wm221")) {
                    DJIUpgradeP4Service.b = "wm221";
                    if (toLowerCase.contains("ac")) {
                        i.getInstance().a(ProductType.KumquatS);
                        i.getInstance().a(true);
                        this.a.q = DeviceType.DM368;
                    } else {
                        i.getInstance().a(ProductType.KumquatS);
                        this.a.q = DeviceType.DM368_G;
                    }
                } else {
                    this.a.v();
                }
                if (!DJIUpgradeP4Service.b.equals("")) {
                    if (DJIUpgradeP4Service.s == null || !DJIUpgradeP4Service.s.a(DJIUpgradeP4Service.b)) {
                        DJIUpgradeP4Service.s = new dji.dbox.upgrade.p4.c.a(this.a.getApplicationContext(), DJIUpgradeP4Service.b);
                    }
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (aVar == dji.midware.data.config.P3.a.TIMEOUT || aVar == dji.midware.data.config.P3.a.UNDEFINED) {
                    this.a.v();
                }
            }
        }, 1000, 2);
    }

    private void v() {
        b = "";
        this.g.setReceiveType(DeviceType.DM368);
        this.g.setReceiveId(1);
        this.g.start(new d(this) {
            final /* synthetic */ DJIUpgradeP4Service a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                String toLowerCase = this.a.g.getInfo().toLowerCase(Locale.ENGLISH);
                dji.dbox.upgrade.p4.a.a.a(this.a.h, "getDeviceInfo broadcast " + toLowerCase);
                if (toLowerCase.contains("wm330")) {
                    DJIUpgradeP4Service.b = "wm330";
                    i.getInstance().a(ProductType.Tomato);
                    i.getInstance().a(true);
                } else if (toLowerCase.contains("wm331")) {
                    DJIUpgradeP4Service.b = "wm331";
                    i.getInstance().a(ProductType.Pomato);
                    i.getInstance().a(true);
                } else if (toLowerCase.contains("wm620")) {
                    DJIUpgradeP4Service.b = "wm620";
                    i.getInstance().a(ProductType.Orange2);
                    i.getInstance().a(true);
                } else if (toLowerCase.contains("wm220")) {
                    DJIUpgradeP4Service.b = "wm220";
                    if (toLowerCase.contains("ac")) {
                        i.getInstance().a(ProductType.KumquatX);
                        i.getInstance().a(true);
                    } else {
                        i.getInstance().a(ProductType.KumquatX);
                        this.a.q = DeviceType.DM368_G;
                    }
                } else if (toLowerCase.contains("wm221")) {
                    DJIUpgradeP4Service.b = "wm221";
                    if (toLowerCase.contains("ac")) {
                        i.getInstance().a(ProductType.KumquatS);
                        i.getInstance().a(true);
                    } else {
                        i.getInstance().a(ProductType.KumquatS);
                        this.a.q = DeviceType.DM368_G;
                    }
                } else {
                    DJIUpgradeP4Service.b = "";
                }
                if (!DJIUpgradeP4Service.b.equals("")) {
                    if (DJIUpgradeP4Service.s == null || !DJIUpgradeP4Service.s.a(DJIUpgradeP4Service.b)) {
                        DJIUpgradeP4Service.s = new dji.dbox.upgrade.p4.c.a(this.a.getApplicationContext(), DJIUpgradeP4Service.b);
                    }
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                dji.dbox.upgrade.p4.a.a.a(this.a.h, "getDeviceInfo broadcast " + aVar);
                if (aVar != dji.midware.data.config.P3.a.TIMEOUT) {
                }
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        c.a().d((Object) this);
        s();
        p();
        s = null;
        k = null;
    }

    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
