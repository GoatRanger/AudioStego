package dji.pilot2.upgrade;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.dji.frame.c.f;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCommonGetPushUpgradeStatus;
import dji.midware.data.model.P3.DataWifiGetSSID;
import dji.pilot.publics.control.a$d;
import dji.pilot.publics.control.p3cupgrade.b.j;
import dji.pilot.publics.control.p3cupgrade.g;
import dji.pilot.publics.model.DJIProductListModel.DJIProductModel;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot2.upgrade.rollback.DJIRollBackActivity$a;
import java.io.File;
import java.io.IOException;

public class b {
    public static final String a = "UpgradeUIStateMachine";
    private static final boolean b = false;
    private a c;
    private d d;
    private dji.pilot.publics.control.p3cupgrade.b e;
    private ProductType f;
    private DJIProductModel g;
    private DJIRollBackActivity$a h;
    private long i;
    private int j;

    private class a extends Handler {
        final /* synthetic */ b a;

        public a(b bVar, Looper looper) {
            this.a = bVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (b.values()[message.what]) {
                case StartInit:
                    this.a.d = d.Initing;
                    this.a.a();
                    break;
                case InitFails:
                    if (this.a.d == d.Initing) {
                        this.a.d = d.None;
                        break;
                    }
                    break;
                case InitSuccessToNeedUpgrade:
                    if (this.a.d == d.Initing) {
                        this.a.d = d.NeedUpgrade;
                        break;
                    }
                    break;
                case InitSuccessToNotNeedUpgrade:
                    if (this.a.d == d.Initing) {
                        this.a.d = d.NotNeedUpgrade;
                        break;
                    }
                    break;
                case RecieveNeedUpgrade:
                    if (this.a.d == d.NotNeedUpgrade) {
                        this.a.d = d.NeedUpgrade;
                        break;
                    }
                    break;
                case RecieveNotNeedUpgrade:
                    if (this.a.d == d.NeedUpgrade) {
                        this.a.d = d.NotNeedUpgrade;
                        break;
                    }
                    break;
                case StartUpgrade:
                    if (this.a.d == d.NeedUpgrade) {
                        this.a.d = d.Upgrading;
                        this.a.a(false);
                        break;
                    }
                    break;
                case StartUpgradeRecovery:
                    if (this.a.d == d.NotNeedUpgrade || this.a.d == d.NeedUpgrade) {
                        this.a.d = d.Upgrading;
                        this.a.a(true);
                        break;
                    }
                case UpgradeFinish:
                    if (this.a.d == d.Upgrading) {
                        this.a.d = d.UpgradeFinish;
                        break;
                    }
                    break;
                case UserRequestNoShow:
                    if (this.a.d == d.Upgrading) {
                        this.a.d = d.NotNeedUpgrade;
                        this.a.a(this.a.m());
                        break;
                    }
                    break;
                case FinishToNeedUpgrade:
                    if (this.a.d == d.UpgradeFinish) {
                        this.a.d = d.NeedUpgrade;
                        break;
                    }
                    break;
                case FinishToNoNeedUpgrade:
                    if (this.a.d == d.UpgradeFinish) {
                        this.a.d = d.NotNeedUpgrade;
                        this.a.e = null;
                        break;
                    }
                    break;
            }
            dji.thirdparty.a.c.a().e(this.a.d);
            DJILogHelper.getInstance().LOGI(b.a, String.format("%s status = %s, msgID = %s", new Object[]{b.a, "" + this.a.d, "" + r0}));
        }
    }

    public enum b {
        StartInit,
        InitFails,
        InitSuccessToNeedUpgrade,
        InitSuccessToNotNeedUpgrade,
        RecieveNeedUpgrade,
        RecieveNotNeedUpgrade,
        StartUpgrade,
        StartUpgradeRecovery,
        UpgradeFinish,
        FinishToNeedUpgrade,
        FinishToNoNeedUpgrade,
        UserRequestNoShow
    }

    private static final class c {
        private static final b a = new b();

        private c() {
        }
    }

    public enum d {
        None,
        Initing,
        NotNeedUpgrade,
        NeedUpgrade,
        Upgrading,
        UpgradeFinish
    }

    public static b getInstance() {
        return c.a;
    }

    private b() {
        this.h = DJIRollBackActivity$a.NONE;
        this.i = 0;
        this.j = 0;
        this.d = d.None;
        dji.thirdparty.a.c.a().a(this);
        j();
        o();
        if (this.f != null) {
            a(b.StartInit);
        }
        p();
    }

    private void j() {
        if (this.c != null) {
            k();
        }
        this.c = new a(this, g.getInstance().a());
    }

    private void k() {
        if (this.c != null) {
            this.c = null;
        }
    }

    public void a() {
        if (this.f == null || !dji.pilot.publics.control.a.getInstance().o()) {
            a(b.InitSuccessToNotNeedUpgrade);
        } else {
            a(b.InitSuccessToNeedUpgrade);
        }
    }

    private void a(boolean z) {
        if (this.d == d.Upgrading) {
            DJIUpgradePack a = dji.pilot.publics.control.a.getInstance().a(this.f);
            if (a == null) {
                if (z) {
                    if (a == null) {
                        a = dji.pilot.publics.control.a.getInstance().b(this.f);
                    }
                    if (a == null && this.e == null) {
                        this.e = new dji.pilot.publics.control.p3cupgrade.b();
                        this.e.a(true);
                        this.e.a(a, this.f);
                        return;
                    }
                    DJILogHelper.getInstance().LOGD(a, "is go here, the upgrade has error to fix, pack = " + a + ", mgr = " + this.e);
                    a(b.RecieveNotNeedUpgrade);
                } else if (a == null) {
                    if (this.e != null) {
                        DJILogHelper.getInstance().LOGD(a, "is go here, the upgrade has error to fix, mgr = " + this.e);
                    }
                    this.e = new dji.pilot.publics.control.p3cupgrade.b();
                    this.e.a(a, this.f);
                } else {
                    a(b.RecieveNotNeedUpgrade);
                }
            } else if (z) {
                if (a == null) {
                    a = dji.pilot.publics.control.a.getInstance().b(this.f);
                }
                if (a == null) {
                }
                DJILogHelper.getInstance().LOGD(a, "is go here, the upgrade has error to fix, pack = " + a + ", mgr = " + this.e);
                a(b.RecieveNotNeedUpgrade);
            } else if (a == null) {
                a(b.RecieveNotNeedUpgrade);
            } else {
                if (this.e != null) {
                    DJILogHelper.getInstance().LOGD(a, "is go here, the upgrade has error to fix, mgr = " + this.e);
                }
                this.e = new dji.pilot.publics.control.p3cupgrade.b();
                this.e.a(a, this.f);
            }
        }
    }

    private DJIUpgradePack l() {
        DJIUpgradePack dJIUpgradePack = new DJIUpgradePack();
        dJIUpgradePack.date = 1444555552;
        dJIUpgradePack.version = "99.02.00.10";
        dJIUpgradePack.m0700 = "01.00.09.12&0";
        dJIUpgradePack.m0800 = "02.13.07.17&0";
        dJIUpgradePack.m0100 = "02.08.36.25&0";
        dJIUpgradePack.m0101 = "02.08.36.25&0";
        dJIUpgradePack.m0400 = "01.26.00.25&0";
        dJIUpgradePack.m0900 = "00.00.00.22&0";
        dJIUpgradePack.packurl = "http://download.dji-innovations.com/downloads/phantom_3/P3C_FW_V00.00.0005.bin";
        dJIUpgradePack.priority = 0;
        return dJIUpgradePack;
    }

    private void a(b bVar) {
        if (bVar != null || this.c != null) {
            this.c.sendEmptyMessage(bVar.ordinal());
        }
    }

    private void a(String str) {
    }

    private void b(String str) {
    }

    private long c(String str) {
        return 0;
    }

    private String m() {
        return null;
    }

    public void b() {
        if (this.d == d.NeedUpgrade) {
            a(b.StartUpgrade);
        }
    }

    public d c() {
        return this.d;
    }

    public String d() {
        DJIUpgradePack a = dji.pilot.publics.control.a.getInstance().a(this.f);
        if (a != null) {
            return a.version;
        }
        return "";
    }

    public String e() {
        return this.g.collegeName;
    }

    public void onEventBackgroundThread(ProductType productType) {
        o();
        this.c.postDelayed(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.n();
            }
        }, 1000);
        if (this.d == d.None && this.f != null) {
            a(b.StartInit);
        }
    }

    private void n() {
        if (!i.getInstance().c().isFromWifi()) {
            this.d = d.NotNeedUpgrade;
            dji.thirdparty.a.c.a().e(this.d);
        }
    }

    public void onEventBackgroundThread(dji.pilot2.upgrade.P3cUpgradeActivity.a aVar) {
        if (this.h != DJIRollBackActivity$a.SHOWING) {
            if (this.d == d.UpgradeFinish) {
                switch (this.e.h()) {
                    case UPGRADE_FAILS:
                        a(b.FinishToNeedUpgrade);
                        break;
                    case INIT_FAILS:
                    case NOT_NEED_UPGRADE:
                    case UPGRADE_SUCCESS:
                        a(b.FinishToNoNeedUpgrade);
                        break;
                }
            }
            if (this.e != null && this.e.h() == j.UPGRADE_SUCCESS) {
                dji.pilot.publics.control.a.getInstance().s();
            }
        }
    }

    public void onEventBackgroundThread(a$d dji_pilot_publics_control_a_d) {
        ProductType c = i.getInstance().c();
        if (this.h != DJIRollBackActivity$a.SHOWING && c != ProductType.BigBanana && c != ProductType.OrangeRAW) {
            DJILogHelper.getInstance().LOGD(a, "DJIUpgradeStatus = " + dji_pilot_publics_control_a_d);
            switch (this.d) {
                case NotNeedUpgrade:
                    if (dji.pilot.publics.control.a.getInstance().o() && this.f != null) {
                        if (ServiceManager.getInstance().isConnected()) {
                            c = i.getInstance().c();
                            if (c.isFromWifi() || c == ProductType.LonganMobile) {
                                a(b.RecieveNeedUpgrade);
                                return;
                            }
                            return;
                        }
                        a(b.RecieveNeedUpgrade);
                        return;
                    }
                    return;
                case NeedUpgrade:
                    if (this.i == 0) {
                        this.i = System.currentTimeMillis();
                        this.c.postDelayed(new Runnable(this) {
                            final /* synthetic */ b a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (!dji.pilot.publics.control.a.getInstance().o()) {
                                    this.a.a(b.RecieveNotNeedUpgrade);
                                }
                            }
                        }, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
                        return;
                    } else if (System.currentTimeMillis() - this.i >= OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL && !dji.pilot.publics.control.a.getInstance().o()) {
                        a(b.RecieveNotNeedUpgrade);
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public void onEventBackgroundThread(j jVar) {
        if (this.h != DJIRollBackActivity$a.SHOWING && this.d == d.Upgrading) {
            switch (jVar) {
                case UPGRADE_FAILS:
                case INIT_FAILS:
                case NOT_NEED_UPGRADE:
                    a(b.UpgradeFinish);
                    return;
                case UPGRADE_SUCCESS:
                    a(b.UpgradeFinish);
                    return;
                case STOP_NO_SHOW:
                    a(b.UserRequestNoShow);
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventBackgroundThread(DataCommonGetPushUpgradeStatus dataCommonGetPushUpgradeStatus) {
        if (this.h == DJIRollBackActivity$a.SHOWING || dataCommonGetPushUpgradeStatus.getDescList().mUpgradeState == 4 || !i.getInstance().c().isFromWifi() || this.j != 0) {
            return;
        }
        if (this.d == d.NotNeedUpgrade || this.d == d.NeedUpgrade) {
            a(b.StartUpgradeRecovery);
            this.j++;
        } else if (this.d == d.Upgrading && this.e.o()) {
            this.e.n();
            this.j++;
        }
    }

    public void onEventBackgroundThread(DJIRollBackActivity$a dJIRollBackActivity$a) {
        this.h = dJIRollBackActivity$a;
    }

    public dji.pilot.publics.control.p3cupgrade.b.c f() {
        if (this.e != null) {
            return this.e.m();
        }
        return null;
    }

    private void o() {
        this.f = null;
        if (ServiceManager.getInstance().isConnected() || i.getInstance().d() != ProductType.Longan) {
            this.f = i.getInstance().c();
        } else {
            this.f = ProductType.Longan;
        }
        if (this.f != null) {
            this.g = dji.pilot.publics.c.d.getInstance().a(this.f);
            return;
        }
        this.f = null;
        this.g = null;
    }

    public ProductType g() {
        return this.f;
    }

    public dji.pilot.publics.control.p3cupgrade.b h() {
        return this.e;
    }

    public void onEventBackgroundThread(o oVar) {
        if (oVar == o.b) {
            this.c.postDelayed(new Runnable(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void run() {
                    b.p();
                    DJILogHelper.getInstance().LOGD(b.a, "saveWifiSSidToFile start");
                }
            }, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
        }
    }

    private static void p() {
        DataWifiGetSSID instance = DataWifiGetSSID.getInstance();
        if (dji.logic.f.d.a(i.getInstance().c())) {
            instance.setFromLongan(true);
        }
        instance.start(new dji.midware.e.d() {
            public void onSuccess(Object obj) {
                String ssid = DataWifiGetSSID.getInstance().getSSID();
                if (!dji.pilot.publics.e.d.a(ssid)) {
                    File externalCacheDir = DJIApplication.a().getExternalCacheDir();
                    if (externalCacheDir == null) {
                        externalCacheDir = DJIApplication.a().getCacheDir();
                    }
                    File file = new File(externalCacheDir.getAbsolutePath() + "/wifi_ssid.json");
                    if (!file.exists()) {
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    f.a(file, ssid);
                    DJILogHelper.getInstance().LOGD(b.a, "saveWifiSSidToFile success wifi : " + ssid);
                }
                DataWifiGetSSID.getInstance().setFromLongan(false);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(b.a, "saveWifiSSidToFile success fails : " + aVar);
                DataWifiGetSSID.getInstance().setFromLongan(false);
            }
        });
    }
}
