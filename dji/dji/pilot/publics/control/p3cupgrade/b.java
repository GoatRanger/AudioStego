package dji.pilot.publics.control.p3cupgrade;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataOsdGetPushPowerStatus;
import dji.pilot.R;
import dji.pilot.publics.control.upgrade.DJIProductUpgradeListModel.DJIUpgradeGroupModel;
import dji.pilot.publics.control.upgrade.DJIProductUpgradeListModel.DJIUpgradeModel;
import dji.pilot.publics.control.upgrade.DLPackageInfo;
import dji.pilot.publics.control.upgrade.b$b;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.pilot.upgrade.FirmwareVersion;
import java.io.File;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class b implements dji.pilot.fpv.d.c.g, b$b {
    private static String X = null;
    public static boolean a = false;
    public static boolean b = false;
    public static boolean c = false;
    public static boolean d = false;
    public static boolean e = false;
    public static boolean f = false;
    public static boolean g = false;
    public static boolean h = false;
    public static boolean i = false;
    public static boolean j = false;
    public static final boolean k = true;
    public static final String n = "Upgrade";
    private static String o = "DJIP3cUpgradeManager";
    private ArrayList<b> N;
    private DJIUpgradeModel O;
    private ArrayList<g> P;
    private int Q;
    private i R;
    private f S = null;
    private boolean T = false;
    private d U;
    private c V;
    private e W;
    private boolean Y = false;
    private boolean Z = false;
    private dji.pilot.publics.control.p3cupgrade.d.a aa = new dji.pilot.publics.control.p3cupgrade.d.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void a() {
        }

        public void a(int i) {
            if (this.a.p == j.UPGRADING) {
                this.a.a(i);
            }
        }

        public void b() {
            if (this.a.p == j.UPGRADING) {
                this.a.a("更新hg300成功");
                this.a.y();
            }
        }

        public void a(dji.midware.data.config.P3.a aVar) {
            if (this.a.p == j.UPGRADING) {
                this.a.a("更新hg300失败");
                this.a.a(h.MSG_ID_UPGRADE_FAILS);
            }
        }
    };
    private dji.pilot.publics.control.p3cupgrade.a.b ab = new dji.pilot.publics.control.p3cupgrade.a.b(this) {
        final /* synthetic */ b a;
        private int b;

        {
            this.a = r1;
        }

        public void a(a aVar, int i) {
            this.b = 0;
        }

        public void a(a aVar, int i, int i2) {
            this.b += i;
            int i3 = (this.b * 100) / i2;
            if (this.a.p == j.UPGRADING && this.a.Q - 1 < this.a.P.size()) {
                g gVar = (g) this.a.P.get(this.a.Q - 1);
                this.a.a(((gVar.d * i3) / 100) + gVar.c);
            }
        }

        public void b(a aVar, int i) {
            if (this.a.p == j.UPGRADING) {
                this.a.a("更新rc成功");
                this.a.y();
            }
        }

        public void a(a aVar, dji.midware.data.config.P3.a aVar2, int i, int i2) {
            if (this.a.p == j.UPGRADING) {
                this.a.a("更新rc失败");
                this.a.a(h.MSG_ID_UPGRADE_FAILS);
            }
        }
    };
    public DLPackageInfo l;
    public boolean m = d;
    private j p;
    private DJIUpgradePack q;
    private ProductType r;
    private a s;
    private boolean t;
    private String u;

    public static class a {
        public int a;
        public String b = "0";
        public long c;
        public long d;
    }

    private class b {
        DeviceType a;
        int b;
        String c;
        String d;
        ProductType e;
        public String f;
        final /* synthetic */ b g;

        private b(b bVar) {
            this.g = bVar;
        }

        public boolean a() {
            if (this.g.m) {
                return true;
            }
            if (b.e) {
                if (this.a == DeviceType.WIFI || this.a == DeviceType.DM368) {
                    return true;
                }
                return false;
            } else if (b.f) {
                if (this.a != DeviceType.WIFI) {
                    return false;
                }
                return true;
            } else if (b.g && this.a == DeviceType.WIFI) {
                return true;
            } else {
                if (b.h && this.a == DeviceType.WIFI_G) {
                    return true;
                }
                if (b.i && this.a == DeviceType.OSD) {
                    return true;
                }
                ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
                if ((c == ProductType.Longan || c == ProductType.LonganZoom) && this.a == DeviceType.DM368) {
                    return true;
                }
                if (this.d == null) {
                    return false;
                }
                if (this.c == null) {
                    return false;
                }
                String[] split = this.c.split("\\.");
                String[] split2 = this.d.split("\\.");
                for (int i = 0; i < 4; i++) {
                    if (Integer.valueOf(split2[i]).intValue() > Integer.valueOf(split[i]).intValue()) {
                        return true;
                    }
                    if (Integer.valueOf(split2[i]).intValue() < Integer.valueOf(split[i]).intValue()) {
                        return false;
                    }
                    if (i == 3) {
                        return false;
                    }
                }
                return false;
            }
        }

        public String toString() {
            return String.format("DeviceType = %d, moduleId = %d, curVersion = %s, lastestVersion = %s", new Object[]{Integer.valueOf(this.a.value()), Integer.valueOf(this.b), this.c, this.d});
        }
    }

    public static class c {
        public int a;
        public String b;
        public h c;

        public c(h hVar, int i, String str) {
            this.a = i;
            this.b = str;
            this.c = hVar;
        }
    }

    private class d extends Handler {
        final /* synthetic */ b a;

        public d(b bVar, Looper looper) {
            this.a = bVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (h.values()[message.what]) {
                case MSG_ID_INIT:
                    this.a.p = j.INITING;
                    this.a.r();
                    break;
                case MSG_ID_TO_RECOVERY:
                    if (this.a.o() || this.a.p == j.INITING) {
                        this.a.p = j.UPGRADING;
                        this.a.B();
                        break;
                    }
                case MSG_ID_INIT_FAILS:
                    if (this.a.p == j.INITING) {
                        this.a.p = j.INIT_FAILS;
                        break;
                    }
                    break;
                case MSG_ID_START_FROM_DOWNLOAD:
                    if (this.a.p == j.INITING) {
                        this.a.p = j.START_WAIT_DOWNLOAD;
                        break;
                    }
                    break;
                case MSG_ID_START_FROM_UPGRADE:
                    if (this.a.p == j.INITING) {
                        this.a.p = j.START_WAIT_UPGRADE;
                        break;
                    }
                    break;
                case MSG_ID_USER_REQUEST_NOSHOW:
                    if (this.a.p == j.START_WAIT_DOWNLOAD) {
                        this.a.p = j.STOP_NO_SHOW;
                        break;
                    }
                    break;
                case MSG_ID_DOWNLOAD_START:
                    if (this.a.p == j.START_WAIT_DOWNLOAD) {
                        this.a.p = j.CHECKING_DOWNLOAD_NETWORK;
                        this.a.t();
                        break;
                    }
                    break;
                case MSG_ID_CONTINUE_DOWN:
                    if (this.a.p == j.DOWNLOAD_PAUSE) {
                        this.a.p = j.CHECKING_DOWNLOAD_NETWORK;
                        this.a.t();
                        break;
                    }
                    break;
                case MSG_ID_CHECK_DOWNLOAD_FAILS:
                    if (this.a.p == j.CHECKING_DOWNLOAD_NETWORK) {
                        this.a.p = j.DOWNLOAD_PAUSE;
                        break;
                    }
                    break;
                case MSG_ID_CHECK_DOWNLOAD_SUCCESS:
                    if (this.a.p == j.CHECKING_DOWNLOAD_NETWORK) {
                        this.a.p = j.DOWNLOADING;
                        this.a.s();
                        break;
                    }
                    break;
                case MSG_ID_DOWNLOAD_FAILS:
                    if (this.a.p == j.DOWNLOADING) {
                        this.a.p = j.DOWNLOAD_PAUSE;
                        break;
                    }
                    break;
                case MSG_ID_DOWNLOAD_SUCCESS:
                    if (this.a.p == j.DOWNLOADING) {
                        this.a.p = j.WAITINGT_TO_UPGRADE;
                        break;
                    }
                    break;
                case MSG_ID_UPGRADE_START:
                    if (this.a.p == j.WAITINGT_TO_UPGRADE || this.a.p == j.START_WAIT_UPGRADE || this.a.p == j.WAIT_AUTO_CONNECTG_RESULT) {
                        this.a.p = j.CHECKING_UPGRADE_BASE;
                        this.a.v();
                        break;
                    }
                case MSG_ID_CONTINUE_UPGRADE:
                    if (this.a.p == j.UPGRADE_PAUSE) {
                        this.a.p = j.CHECKING_UPGRADE_BASE;
                        this.a.v();
                        break;
                    }
                    break;
                case MSG_ID_CHECK_UPGRADE_FAILS:
                    if (this.a.p == j.CHECKING_UPGRADE_BASE) {
                        this.a.p = j.UPGRADE_PAUSE;
                        break;
                    }
                    break;
                case MSG_ID_CHECK_UPGRADE_FILE_FAILS:
                    if (this.a.p == j.CHECKING_UPGRADE_BASE) {
                        this.a.p = j.DOWNLOAD_PAUSE;
                        break;
                    }
                    break;
                case MSG_ID_CHECK_UPGRADE_NOT_NEED:
                    if (this.a.p == j.CHECKING_UPGRADE_BASE) {
                        this.a.p = j.NOT_NEED_UPGRADE;
                        break;
                    }
                    break;
                case MSG_ID_CHECK_UPGRADE_SUCCESS:
                    if (this.a.p == j.CHECKING_UPGRADE_BASE) {
                        this.a.p = j.UPGRADING;
                        this.a.x();
                        break;
                    }
                    break;
                case MSG_ID_UPGRADE_FAILS:
                    if (this.a.p == j.UPGRADING) {
                        this.a.p = j.UPGRADE_FAILS;
                        dji.pilot.fpv.d.e.c(dji.pilot.fpv.d.c.g.L);
                        break;
                    }
                    break;
                case MSG_ID_UPGRADE_SUCCESS:
                    if (this.a.p == j.UPGRADING) {
                        this.a.a(100);
                        this.a.p = j.UPGRADE_SUCCESS;
                        dji.pilot.fpv.d.e.c(dji.pilot.fpv.d.c.g.K);
                        break;
                    }
                    break;
                default:
                    super.handleMessage(message);
                    break;
            }
            dji.thirdparty.a.c.a().e(this.a.p);
            dji.thirdparty.a.c.a().e(this.a);
            e.a(b.o, String.format("%s status = %s", new Object[]{b.o, "" + this.a.p}));
            if (this.a.p == j.INIT_FAILS || this.a.p == j.NOT_NEED_UPGRADE || this.a.p == j.UPGRADE_SUCCESS || this.a.p == j.UPGRADE_FAILS) {
                this.a.a();
            }
        }
    }

    public static class e {
        public int a;
        public String b;
        public h c;

        public e(h hVar, int i, String str) {
            this.a = i;
            this.b = str;
            this.c = hVar;
        }
    }

    public static class f {
        public String a;

        public f(String str) {
            this.a = str;
        }
    }

    public static class g {
        public DJIUpgradeGroupModel a;
        public ArrayList<String> b;
        public int c;
        public int d;

        public g(DJIUpgradeGroupModel dJIUpgradeGroupModel) {
            this.a = dJIUpgradeGroupModel;
        }

        public g(DJIUpgradeGroupModel dJIUpgradeGroupModel, ArrayList<String> arrayList) {
            this.a = dJIUpgradeGroupModel;
            this.b = arrayList;
        }
    }

    public enum h {
        MSG_ID_INIT,
        MSG_ID_INIT_FAILS,
        MSG_ID_START_FROM_DOWNLOAD,
        MSG_ID_START_FROM_UPGRADE,
        MSG_ID_USER_REQUEST_NOSHOW,
        MSG_ID_DOWNLOAD_START,
        MSG_ID_CONTINUE_DOWN,
        MSG_ID_CHECK_DOWNLOAD_FAILS,
        MSG_ID_CHECK_DOWNLOAD_SUCCESS,
        MSG_ID_DOWNLOAD_FAILS,
        MSG_ID_DOWNLOAD_SUCCESS,
        MSG_ID_TO_RECOVERY,
        MSG_ID_UPGRADE_START,
        MSG_ID_CONTINUE_UPGRADE,
        MSG_ID_CHECK_UPGRADE_FAILS,
        MSG_ID_CHECK_UPGRADE_FILE_FAILS,
        MSG_ID_CHECK_UPGRADE_NOT_NEED,
        MSG_ID_CHECK_UPGRADE_SUCCESS,
        MSG_ID_UPGRADE_FAILS,
        MSG_ID_UPGRADE_SUCCESS
    }

    public static class i {
        public int a;
    }

    public enum j {
        INITING,
        INIT_FAILS,
        NOT_NEED_UPGRADE,
        STOP_NO_SHOW,
        START_WAIT_DOWNLOAD,
        START_WAIT_UPGRADE,
        CHECKING_DOWNLOAD_NETWORK,
        DOWNLOAD_PAUSE,
        DOWNLOADING,
        WAITINGT_TO_UPGRADE,
        AUTO_UPGRADE,
        WAIT_AUTO_CONNECTG_RESULT,
        CHECKING_UPGRADE_BASE,
        UPGRADE_PAUSE,
        UPGRADING,
        UPGRADE_FAILS,
        UPGRADE_SUCCESS
    }

    public void a(DJIUpgradePack dJIUpgradePack, ProductType productType) {
        dji.pilot2.library.d.getInstance().g(true);
        this.Z = true;
        dji.thirdparty.a.c.a().a(this);
        z();
        this.u = com.dji.frame.c.d.a(DJIApplication.a(), "Upgrade/UpgradeTmp");
        this.r = productType;
        if (ServiceManager.getInstance().isConnected() || ServiceManager.getInstance().isRemoteOK()) {
            this.t = false;
        } else if (dji.pilot.publics.control.upgrade.b.getInstance().b(dJIUpgradePack, this.r) == null) {
            this.t = true;
        } else {
            this.t = false;
        }
        if (dJIUpgradePack == null) {
            a(h.MSG_ID_INIT_FAILS);
            a(new c(h.MSG_ID_INIT_FAILS, R.string.v2_upgrade_init_fails, "初始化失败"));
            return;
        }
        this.q = dJIUpgradePack;
        a(h.MSG_ID_INIT);
    }

    public void a() {
        dji.pilot2.library.d.getInstance().g(false);
        this.Z = false;
        dji.thirdparty.a.c.a().d(this);
        A();
    }

    public boolean b() {
        return this.t;
    }

    private void a(c cVar) {
        if (cVar != null && this.Z) {
            this.V = cVar;
            dji.thirdparty.a.c.a().e(cVar);
            DJILogHelper.getInstance().LOGD(o, "error:" + cVar.b);
        }
    }

    private void a(e eVar) {
        if (eVar != null && this.Z) {
            this.W = eVar;
            dji.thirdparty.a.c.a().e(eVar);
            DJILogHelper.getInstance().LOGD(o, "notify:" + eVar.b);
        }
    }

    public void a(boolean z) {
        this.Y = z;
    }

    public void b(boolean z) {
        this.m = z;
    }

    public void c() {
        if (this.p == j.START_WAIT_DOWNLOAD) {
            a(h.MSG_ID_DOWNLOAD_START);
        } else if (this.p == j.DOWNLOAD_PAUSE) {
            a(h.MSG_ID_CONTINUE_DOWN);
        }
    }

    public void d() {
        if (this.p == j.START_WAIT_UPGRADE) {
            a(h.MSG_ID_UPGRADE_START);
        }
        if (this.p == j.WAITINGT_TO_UPGRADE) {
            a(h.MSG_ID_UPGRADE_START);
        } else if (this.p == j.UPGRADE_PAUSE) {
            a(h.MSG_ID_CONTINUE_UPGRADE);
        }
    }

    public void e() {
        if (this.p == j.START_WAIT_DOWNLOAD) {
            a(h.MSG_ID_USER_REQUEST_NOSHOW);
        }
    }

    public a f() {
        return this.s;
    }

    private void a(int i, long j, long j2) {
        if (this.Z) {
            this.s.a = i;
            this.s.c = j;
            this.s.d = j2;
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            this.s.b = decimalFormat.format((double) (((float) (100 * j2)) / ((float) j)));
            dji.thirdparty.a.c.a().e(this.s);
        }
    }

    public i g() {
        return this.R;
    }

    private void a(int i) {
        if (this.Z) {
            this.R.a = i;
            dji.thirdparty.a.c.a().e(this.R);
        }
    }

    private void r() {
        this.s = new a();
        this.R = new i();
        this.O = dji.pilot.publics.control.upgrade.a.getInstance().a(this.r);
        this.N = new ArrayList();
        Iterator it = this.O.groups.iterator();
        while (it.hasNext()) {
            DJIUpgradeGroupModel dJIUpgradeGroupModel = (DJIUpgradeGroupModel) it.next();
            for (int i = 0; i < dJIUpgradeGroupModel.devices.size(); i++) {
                b bVar = new b();
                bVar.a = dJIUpgradeGroupModel.getDeviceType(i);
                bVar.b = dJIUpgradeGroupModel.getDeviceModule(i);
                bVar.e = this.r;
                bVar.f = (String) dJIUpgradeGroupModel.devices.get(i);
                this.N.add(bVar);
            }
        }
        if (b) {
            this.l = new DLPackageInfo();
            this.l.mAbsPath = "/sdcard/HG300.bin";
            this.l.mDLStatus = 3;
            a(h.MSG_ID_START_FROM_UPGRADE);
            return;
        }
        DLPackageInfo b = dji.pilot.publics.control.upgrade.b.getInstance().b(this.q, this.r);
        if (this.Y) {
            this.l = b;
            a(h.MSG_ID_TO_RECOVERY);
        } else if (b != null) {
            this.l = b;
            switch (b.mDLStatus) {
                case 2:
                    a(h.MSG_ID_INIT_FAILS);
                    return;
                case 3:
                    if (new File(b.mAbsPath).exists()) {
                        a(h.MSG_ID_START_FROM_UPGRADE);
                        return;
                    }
                    dji.pilot.publics.control.upgrade.b.getInstance().b(b);
                    a(h.MSG_ID_START_FROM_DOWNLOAD);
                    return;
                default:
                    a(h.MSG_ID_START_FROM_DOWNLOAD);
                    return;
            }
        } else {
            a(h.MSG_ID_START_FROM_DOWNLOAD);
        }
    }

    public j h() {
        return this.p;
    }

    private void s() {
        dji.pilot.publics.control.upgrade.b.getInstance().a(this.q, dji.midware.data.manager.P3.i.getInstance().c(), this);
    }

    private void t() {
        if (ServiceManager.getInstance().isConnected() && dji.midware.data.manager.P3.i.getInstance().c() != ProductType.LonganMobile) {
            a(new e(h.MSG_ID_CHECK_DOWNLOAD_FAILS, R.string.v2_upgrade_not_network, "无法连接网络"));
            a(h.MSG_ID_CHECK_DOWNLOAD_FAILS);
            dji.publics.b.b.a.getInstance().f("createtime", System.currentTimeMillis() + "", false).f(dji.publics.b.a.b.x, "11", false).f("device_ver", dji.pilot.upgrade.e.getInstance().b(), false).f("device_type", dji.midware.data.manager.P3.i.getInstance().c()._name(), true);
        } else if (DJINetWorkReceiver.a(DJIApplication.a())) {
            a(h.MSG_ID_CHECK_DOWNLOAD_SUCCESS);
        } else {
            a(new e(h.MSG_ID_CHECK_DOWNLOAD_FAILS, R.string.v2_upgrade_not_network, "无法连接网络"));
            a(h.MSG_ID_CHECK_DOWNLOAD_FAILS);
            dji.publics.b.b.a.getInstance().f("createtime", System.currentTimeMillis() + "", false).f(dji.publics.b.a.b.x, "11", false).f("device_ver", dji.pilot.upgrade.e.getInstance().b(), false).f("device_type", dji.midware.data.manager.P3.i.getInstance().c()._name(), true);
        }
    }

    private void u() {
        dji.pilot2.utils.e eVar = new dji.pilot2.utils.e(DJIApplication.a());
        com.dji.frame.c.f.a(new File(DJIApplication.a().getExternalCacheDir().getAbsolutePath() + "/wifi_ssid.json"));
        com.dji.frame.c.f.a(new File(DJIApplication.a().getExternalCacheDir().getAbsolutePath() + "/wifi_password.json"));
        eVar.b(new dji.pilot2.utils.e.a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a(h.MSG_ID_UPGRADE_START);
            }

            public void b() {
                this.a.p = j.WAITINGT_TO_UPGRADE;
                dji.thirdparty.a.c.a().e(this.a.p);
            }

            public void c() {
            }
        });
    }

    private boolean v() {
        boolean z = false;
        if (this.l != null && this.l.mDLStatus == 3 && !new File(this.l.mAbsPath).exists()) {
            a("包解析有误，请重新下载");
            dji.pilot.publics.control.upgrade.b.getInstance().b(this.l);
            a(new e(h.MSG_ID_CHECK_UPGRADE_FILE_FAILS, R.string.v2_upgrade_file_error, "包解析有误，请重新下载"));
            a(h.MSG_ID_CHECK_UPGRADE_FILE_FAILS);
            return false;
        } else if (!ServiceManager.getInstance().isConnected() || !ServiceManager.getInstance().isRemoteOK()) {
            a(new c(h.MSG_ID_CHECK_UPGRADE_FAILS, R.string.v2_upgrade_device_not_connect, "设备没有连接，请检查"));
            a(h.MSG_ID_CHECK_UPGRADE_FAILS);
            dji.publics.b.b.a.getInstance().f("createtime", System.currentTimeMillis() + "", false).f(dji.publics.b.a.b.x, "12", false).f("device_ver", dji.pilot.upgrade.e.getInstance().b(), false).f("device_type", dji.midware.data.manager.P3.i.getInstance().c()._name(), true);
            return false;
        } else if (dji.logic.f.d.a(this.r) && DataOsdGetPushPowerStatus.getInstance().isGetted() && DataOsdGetPushPowerStatus.getInstance().getPowerStatus() == 1 && this.r != ProductType.LonganMobile) {
            a(new c(h.MSG_ID_CHECK_UPGRADE_FAILS, R.string.v2_upgrade_device_device_sleep, "休眠中"));
            a(h.MSG_ID_CHECK_UPGRADE_FAILS);
            return false;
        } else {
            DataCenterGetPushBatteryCommon instance = DataCenterGetPushBatteryCommon.getInstance();
            int currentCapacity = (int) ((((float) instance.getCurrentCapacity()) / ((float) instance.getFullCapacity())) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
            if (currentCapacity == 0) {
                this.m = true;
                a(h.MSG_ID_CHECK_UPGRADE_SUCCESS);
                return true;
            } else if (currentCapacity >= dji.logic.f.d.i(this.r) || currentCapacity == 0) {
                if (this.r != ProductType.LonganMobile) {
                    currentCapacity = DataCameraGetPushStateInfo.getInstance().getSDCardFreeSize();
                    boolean sDCardInsertState = DataCameraGetPushStateInfo.getInstance().getSDCardInsertState();
                    SDCardState sDCardState = DataCameraGetPushStateInfo.getInstance().getSDCardState();
                    if (!sDCardInsertState) {
                        DJIUpgradeGroupModel cameraModel = this.O.getCameraModel();
                        if (cameraModel != null && dji.pilot.publics.control.upgrade.c.a(cameraModel.ftpUrl, cameraModel.ftpUsername, cameraModel.ftpPwd)) {
                            this.T = true;
                        }
                        if (sDCardState == SDCardState.None || !this.T) {
                            a("未插入sd卡，无法升级");
                            a(new c(h.MSG_ID_CHECK_UPGRADE_FAILS, R.string.v2_activity_upgrade_camera_no_sd, "未插入sd卡，无法升级"));
                            a(h.MSG_ID_CHECK_UPGRADE_FAILS);
                            return false;
                        }
                    }
                    if (currentCapacity < 150 && !this.T) {
                        a("相机Sd Card空间不足，此次升级需要65MB空间");
                        a(new c(h.MSG_ID_CHECK_UPGRADE_FAILS, R.string.v2_activity_upgrade_camera_sd_noenough, "相机Sd Card空间不足，此次升级需要65MB空间"));
                        a(h.MSG_ID_CHECK_UPGRADE_FAILS);
                        return false;
                    }
                }
                if ((this.l != null && this.l.mDLStatus == 3) || b) {
                    boolean z2;
                    if (c) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    dji.pilot.publics.control.upgrade.e.c a = dji.pilot.publics.control.upgrade.e.a(this.l.mAbsPath, this.r, z2);
                    if (a != null && a.a) {
                        Iterator it = this.N.iterator();
                        while (it.hasNext()) {
                            dji.pilot.publics.control.upgrade.e.a a2;
                            b bVar = (b) it.next();
                            if (a(bVar.a)) {
                                a2 = a.a(bVar.a, 1);
                            } else {
                                a2 = a.a(bVar.a, bVar.b);
                            }
                            if (a2 != null) {
                                bVar.d = a2.l;
                            }
                        }
                        w();
                        z = true;
                    }
                }
                if (z) {
                    return z;
                }
                a("包解析有误，请重新下载");
                dji.pilot.publics.control.upgrade.b.getInstance().b(this.l);
                a(new c(h.MSG_ID_CHECK_UPGRADE_FILE_FAILS, R.string.v2_upgrade_file_error, "包解析有误，请重新下载"));
                a(h.MSG_ID_CHECK_UPGRADE_FILE_FAILS);
                return z;
            } else {
                a(new c(h.MSG_ID_CHECK_UPGRADE_FAILS, R.string.v2_upgrade_device_power_noenough, "电量不足，禁止升级"));
                a(h.MSG_ID_CHECK_UPGRADE_FAILS);
                return false;
            }
        }
    }

    private boolean a(DeviceType deviceType) {
        if (this.r != ProductType.LonganMobile || deviceType.value() != 9) {
            return false;
        }
        DataCommonGetVersion dataCommonGetVersion = new DataCommonGetVersion();
        dataCommonGetVersion.setDeviceType(DeviceType.find(9));
        if (dataCommonGetVersion.getLoaderByte(2) != 1) {
            return false;
        }
        return true;
    }

    private void w() {
        String[] strArr = new String[this.N.size()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = ((b) this.N.get(i)).f;
        }
        dji.pilot.upgrade.c cVar = new dji.pilot.upgrade.c(strArr, new dji.pilot.upgrade.c.b(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onResultCallBack(boolean z, ArrayList<FirmwareVersion> arrayList) {
                Object obj = null;
                for (int i = 0; i < arrayList.size(); i++) {
                    ((b) this.a.N.get(i)).c = ((FirmwareVersion) arrayList.get(i)).versionStr;
                }
                Iterator it = this.a.N.iterator();
                while (it.hasNext()) {
                    b bVar = (b) it.next();
                    e.a(b.o, bVar.toString());
                    if (bVar.a()) {
                        obj = 1;
                        break;
                    }
                }
                if (obj != null) {
                    this.a.a(h.MSG_ID_CHECK_UPGRADE_SUCCESS);
                    return;
                }
                this.a.a("已是最新版本，无需升级");
                this.a.a(new c(h.MSG_ID_CHECK_UPGRADE_NOT_NEED, R.string.v2_upgrade_not_need, "已是最新版本，无需升级"));
                this.a.a(h.MSG_ID_CHECK_UPGRADE_NOT_NEED);
            }
        });
    }

    public void a(DLPackageInfo dLPackageInfo) {
        if (this.p == j.DOWNLOADING) {
            a("开始下载升级包");
        }
    }

    public void a(DLPackageInfo dLPackageInfo, long j, long j2) {
        if (this.p == j.DOWNLOADING) {
            a((int) ((100 * j) / j2), dLPackageInfo.mPackageSize, dLPackageInfo.mDLSize);
        }
    }

    public void a(DLPackageInfo dLPackageInfo, int i) {
        if (this.p == j.DOWNLOADING) {
            this.l = dLPackageInfo;
            a(h.MSG_ID_DOWNLOAD_SUCCESS);
            a("开始下载升级包");
        }
    }

    public void a(DLPackageInfo dLPackageInfo, int i, String str) {
        if (this.p == j.DOWNLOADING) {
            a(new e(h.MSG_ID_DOWNLOAD_FAILS, R.string.v2_upgrade_down_fails, "下载升级包失败"));
            a(h.MSG_ID_DOWNLOAD_FAILS);
            a("下载升级包失败");
        }
    }

    public boolean a(g gVar) {
        if (this.p != j.UPGRADING) {
            return false;
        }
        new d(gVar, this.l.mAbsPath, this.aa).c();
        return true;
    }

    public boolean b(g gVar) {
        Exception exception;
        if (this.p != j.UPGRADING) {
            return false;
        }
        boolean z;
        String str = this.l.mAbsPath;
        dji.pilot.publics.control.upgrade.e.c a = dji.pilot.publics.control.upgrade.e.a(str, this.r, false);
        if (a != null && a.a) {
            dji.pilot.publics.control.upgrade.e.a a2 = a.a((String) gVar.a.devices.get(0));
            if (a2 != null) {
                dji.pilot.publics.control.rc.a.a aVar = new dji.pilot.publics.control.rc.a.a();
                aVar.a = a2.a;
                aVar.b = a2.b;
                aVar.c = a2.c;
                aVar.d = a2.d;
                aVar.e = a2.e;
                aVar.g = a2.g;
                aVar.h = a2.h;
                aVar.i = a2.i;
                aVar.j = a2.j;
                aVar.k = a2.k;
                try {
                    a aVar2 = new a(DeviceType.find(aVar.a), aVar, new RandomAccessFile(new File(str), "r"), this.ab);
                    aVar2.a(true);
                    aVar2.b();
                    try {
                        DJILogHelper.getInstance().LOGD(o, "upgradeRC start", true, true);
                        z = true;
                    } catch (Exception e) {
                        Exception exception2 = e;
                        z = true;
                        exception = exception2;
                        exception.printStackTrace();
                        return z;
                    }
                } catch (Exception e2) {
                    exception = e2;
                    z = false;
                    exception.printStackTrace();
                    return z;
                }
                return z;
            }
        }
        z = false;
        return z;
    }

    private void x() {
        int i;
        this.Q = 0;
        this.P = new ArrayList();
        Iterator it = this.O.groups.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            int i3;
            DJIUpgradeGroupModel dJIUpgradeGroupModel = (DJIUpgradeGroupModel) it.next();
            if (dJIUpgradeGroupModel.isSingleFile) {
                b a = a(dJIUpgradeGroupModel.getDeviceType(0), dJIUpgradeGroupModel.getDeviceModule(0));
                if (a != null && a.a()) {
                    this.P.add(new g(dJIUpgradeGroupModel));
                    i2 += dJIUpgradeGroupModel.weight;
                }
                i3 = i2;
            } else {
                if (dJIUpgradeGroupModel.devices != null && dJIUpgradeGroupModel.devices.size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (i = 0; i < dJIUpgradeGroupModel.devices.size(); i++) {
                        b a2 = a(dJIUpgradeGroupModel.getDeviceType(i), dJIUpgradeGroupModel.getDeviceModule(i));
                        if (a2 != null && a2.a()) {
                            arrayList.add(dJIUpgradeGroupModel.devices.get(i));
                        }
                    }
                    if (arrayList.size() > 0) {
                        this.P.add(new g(dJIUpgradeGroupModel, arrayList));
                        i3 = i2 + dJIUpgradeGroupModel.weight;
                    }
                }
                i3 = i2;
            }
            i2 = i3;
        }
        it = this.P.iterator();
        i = 0;
        while (it.hasNext()) {
            g gVar = (g) it.next();
            gVar.d = (gVar.a.weight * 100) / i2;
            gVar.c = i;
            i += gVar.d;
            DJILogHelper.getInstance().LOGD(o, String.format("group name = %s, realWeight = %d, startPos = %d, weight = %d", new Object[]{gVar.a.groupName, Integer.valueOf(gVar.d), Integer.valueOf(gVar.c), Integer.valueOf(gVar.a.weight)}), true, true);
        }
        y();
    }

    private b a(DeviceType deviceType, int i) {
        Iterator it = this.N.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (deviceType == bVar.a && i == bVar.b) {
                return bVar;
            }
        }
        return null;
    }

    private void y() {
        if (this.p != j.UPGRADING) {
            return;
        }
        if (this.Q < this.P.size()) {
            g gVar = (g) this.P.get(this.Q);
            if (gVar.a.upgradeMode == 0) {
                if (this.S != null) {
                    e.a(o, "if run here, error need to fix.petyr.zhan mFtpTask != null");
                }
                this.S = new f();
                String str = this.l.mAbsPath;
                if (this.m) {
                    this.S.b(true);
                }
                if (this.r == ProductType.Longan && dji.midware.data.manager.P3.i.getInstance().b() == CameraType.DJICameraTypeFC550 && gVar.a.groupName.equals(dji.sdksharedlib.b.b.a)) {
                    gVar.a.ftpDstFileName = "OSMO_FC550_FW_V99.99.99.99.bin";
                    gVar.a.extraStartFile = "OSMO_FC550_APP_START";
                }
                this.S.a(this.r, gVar, str, this.u, this.T);
                this.Q++;
                return;
            } else if (gVar.a.upgradeMode == 1) {
                if (b(gVar)) {
                    this.Q++;
                    return;
                } else {
                    a(h.MSG_ID_UPGRADE_FAILS);
                    return;
                }
            } else if (gVar.a.upgradeMode != 2) {
                return;
            } else {
                if (a(gVar)) {
                    this.Q++;
                    return;
                } else {
                    a(h.MSG_ID_UPGRADE_FAILS);
                    return;
                }
            }
        }
        a(h.MSG_ID_UPGRADE_SUCCESS);
    }

    private void z() {
        if (this.U != null) {
            A();
        }
        this.U = new d(this, g.getInstance().a());
    }

    private void A() {
        if (this.U != null) {
            this.U = null;
        }
    }

    private void a(String str) {
        if (this.Z) {
            X = str;
            DJILogHelper.getInstance().LOGD("DJIP3cUpgradeManager", str, false, false);
            dji.thirdparty.a.c.a().e(new f(str));
        }
    }

    public String i() {
        return X;
    }

    public void onEventBackgroundThread(dji.pilot.publics.control.p3cupgrade.f.g gVar) {
        switch (gVar) {
            case FAILS:
                this.S = null;
                a(h.MSG_ID_UPGRADE_FAILS);
                break;
            case SUCCESS:
                this.S = null;
                y();
                break;
        }
        a(f.g());
    }

    public void onEventBackgroundThread(dji.pilot.publics.control.p3cupgrade.f.e eVar) {
        if (this.p == j.UPGRADING && this.Q - 1 < this.P.size()) {
            g gVar = (g) this.P.get(this.Q - 1);
            a(((gVar.d * eVar.a) / 100) + gVar.c);
        }
    }

    public void onEventBackgroundThread(dji.pilot.publics.control.p3cupgrade.f.a aVar) {
        Log.d(o, "onEventBackgroundThread 有错误发生咯~~" + aVar.b + hashCode());
        a(new c(h.MSG_ID_CHECK_UPGRADE_FAILS, aVar.a, aVar.b));
    }

    public static String j() {
        return X;
    }

    public ProductType k() {
        return this.r;
    }

    public String l() {
        return this.q.version;
    }

    public c m() {
        return this.V;
    }

    private void a(h hVar) {
        if (hVar != null && this.U != null) {
            this.U.sendEmptyMessage(hVar.ordinal());
        }
    }

    public void n() {
        if (o()) {
            this.Y = true;
            a(h.MSG_ID_TO_RECOVERY);
        }
    }

    private void B() {
        this.P = new ArrayList();
        if (this.O != null && this.O.groups != null) {
            Iterator it = this.O.groups.iterator();
            while (it.hasNext()) {
                DJIUpgradeGroupModel dJIUpgradeGroupModel = (DJIUpgradeGroupModel) it.next();
                if (dJIUpgradeGroupModel.isCameraGroup) {
                    this.P.add(new g(dJIUpgradeGroupModel, dJIUpgradeGroupModel.devices));
                    break;
                }
            }
            ((g) this.P.get(0)).d = 100;
            ((g) this.P.get(0)).c = 0;
            if (this.S != null) {
                e.a(o, "if run here, error need to fix.petyr.zhan mFtpTask != null");
            }
            this.Q = 1;
            this.S = new f();
            this.S.a(true);
            if (this.l == null) {
                this.S.a(this.r, (g) this.P.get(0), null, this.u, this.T);
            } else {
                this.S.a(this.r, (g) this.P.get(0), this.l.mAbsPath, this.u, this.T);
            }
        }
    }

    public boolean o() {
        return this.p == j.START_WAIT_UPGRADE || this.p == j.WAITINGT_TO_UPGRADE || this.p == j.UPGRADE_PAUSE;
    }

    public String p() {
        if (this.q == null || this.q.release_note == null) {
            return "";
        }
        String language = Locale.getDefault().getLanguage();
        if (language == null || !language.equals("zh")) {
            return this.q.release_note.en;
        }
        return this.q.release_note.cn;
    }
}
