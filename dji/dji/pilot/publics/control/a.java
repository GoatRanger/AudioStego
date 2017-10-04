package dji.pilot.publics.control;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.dji.frame.c.f;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.pilot.R;
import dji.pilot.publics.c.d;
import dji.pilot.publics.model.DJIProductListModel.DJIProductModel;
import dji.pilot.publics.model.DJIProductVerModel.DJIVerModel;
import dji.pilot.publics.model.DJIUpgradeDateModel;
import dji.pilot.publics.model.DJIUpgradePackListModel;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.pilot.publics.model.DJIVersionDbModel;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.pilot.publics.objects.g;
import dji.thirdparty.afinal.b;
import dji.thirdparty.afinal.c;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class a {
    private static final String K = "mc@camera@battery@rc";
    public static final String a = "keyForNetUpdateDialog";
    public static final String b = "keyForLockDialog";
    public static final String c = "keyForNotice";
    public static final String d = "keyForAppUpdate";
    public static final int e = 0;
    public static long f = 0;
    public static long g = 0;
    public static int n = 3;
    private static String p = "";
    private static final String q = "keyForVersionList";
    private static final String r = "keyForNetUpdate";
    private static a s = null;
    private static String t;
    private static String u;
    private final int A = 10;
    private final int B = 11;
    private Handler C = new Handler(new 1(this));
    private DJIUpgradePackListModel D;
    private DJIUpgradePackListModel E;
    private c F;
    private boolean G;
    private b H;
    private String I = "";
    private String J = "";
    private String L = K;
    private int M = 0;
    private int N = n;
    private ArrayList<DataCommonGetVersion> O = new ArrayList();
    private boolean P = false;
    private int Q = 0;
    private int R = 0;
    private int S = 0;
    private boolean T = false;
    private k U = new k(this, null);
    private j V = new j(this, null);
    private boolean W = false;
    private List<DJIVersionDbModel> X;
    public b h = b.a;
    public f i = f.g;
    public boolean j = false;
    protected DJIUpgradeDateModel k;
    protected DJIUpgradeDateModel l;
    protected boolean m;
    private final String o = "DJIUpgradeControl";
    private Context v;
    private d w = d.b;
    private d x = d.b;
    private final int y = 0;
    private final int z = 1;

    public enum e {
        YES,
        NO,
        DIALOG,
        LOCK
    }

    public enum h {
        MC("mc"),
        CAMERA(dji.publics.b.a.b.v),
        BATTERY("battery"),
        RC("rc");
        
        private g e;
        private String f;

        private h(String str) {
            this.e = new g();
            this.f = str;
        }

        public String a() {
            return this.f;
        }

        public static ArrayList<DJIVerModel> getModelList(h hVar, ProductType productType) {
            DJIProductModel a = d.getInstance().a(productType);
            switch (8.a[hVar.ordinal()]) {
                case 1:
                    return a.verModel.mc;
                case 2:
                    return a.verModel.rc;
                case 3:
                    return a.verModel.battery;
                case 4:
                    return a.verModel.camera;
                default:
                    return null;
            }
        }

        public static ArrayList<DJIVerModel> getModelListAll(ProductType productType) {
            return d.getInstance().a(productType).verModel.getAll();
        }

        public static void reset(ProductType productType) {
            for (h b : values()) {
                b.b().a();
            }
            d.getInstance().a(productType).verModel.reset();
        }

        public g b() {
            return this.e;
        }
    }

    public static synchronized a getInstance(Context context) {
        a aVar;
        synchronized (a.class) {
            if (s == null) {
                s = new a(context);
            }
            aVar = s;
        }
        return aVar;
    }

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            aVar = s;
        }
        return aVar;
    }

    public a(Context context) {
        this.H = com.dji.frame.c.c.c(context);
        p = dji.pilot.c.b.g[dji.pilot.c.a.j];
        this.v = context.getApplicationContext();
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir == null) {
            externalCacheDir = context.getCacheDir();
        }
        u = externalCacheDir.getAbsolutePath() + "/date.json";
        t = externalCacheDir.getAbsolutePath() + "/list.json";
        this.F = com.dji.frame.c.c.b(context);
        F();
        this.C.sendEmptyMessageDelayed(11, 2000);
        dji.pilot.publics.c.b.getInstance(context);
        dji.thirdparty.a.c.a().a((Object) this);
        g = g.b(context, r, 0);
        if (g == 0) {
            g = System.currentTimeMillis();
            g.a(context, r, g);
        }
        f = g.b(context, a, 0);
    }

    public void a() {
    }

    private void v() {
        if (DJINetWorkReceiver.a(this.v) && b()) {
            d();
        } else {
            c();
        }
    }

    public static boolean b() {
        boolean e = i.getInstance().e();
        return !e || (e && !i.getInstance().c().isFromWifi());
    }

    public void c() {
        String a = f.a(new File(t));
        if (!a.equals("")) {
            this.D = (DJIUpgradePackListModel) com.dji.frame.c.h.b(a, DJIUpgradePackListModel.class);
            DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "packListModel =" + this.D, false, false);
            A();
        }
    }

    private void g(String str) {
        this.D = (DJIUpgradePackListModel) com.dji.frame.c.h.b(str, DJIUpgradePackListModel.class);
    }

    public void d() {
        if (new File(t).exists()) {
            DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "list数据已存在 将获取date数据来对比", false, true);
            x();
            return;
        }
        DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "list数据不存在 直接获取一次", false, true);
        z();
    }

    private DJIUpgradeDateModel w() {
        String a = f.a(new File(u));
        if (a != "") {
            return (DJIUpgradeDateModel) com.dji.frame.c.h.b(a, DJIUpgradeDateModel.class);
        }
        return null;
    }

    private void x() {
        this.G = true;
        this.F.a(dji.pilot.c.b.i, new 2(this));
    }

    private boolean y() {
        if (this.D != null && this.k != null && dji.pilot.c.a.j == 0 && this.k.data >= this.l.data && g.b(this.v, q, false)) {
            return false;
        }
        return true;
    }

    protected void e() {
        c();
        if (y()) {
            DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "date数据已更新 将重新获取list数据", false, true);
            z();
            return;
        }
        g.a(this.v, r, System.currentTimeMillis());
        DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "date数据未更新 将使用list数据", false, true);
        G();
    }

    private void z() {
        this.G = true;
        g.a(this.v, q, false);
        this.F.a(p, new 3(this));
    }

    private void A() {
        if (this.D != null && this.D.application != null) {
            String str = this.D.application.android;
            String string = this.v.getResources().getString(R.string.versionname);
            if (str != null && !str.equals("")) {
                if (b(str, string)) {
                    if (this.D.application.significant1 == 1) {
                        this.h = b.c;
                    } else {
                        this.h = b.b;
                    }
                    dji.thirdparty.a.c.a().e(this.h);
                    return;
                }
                this.h = b.a;
            }
        }
    }

    private boolean b(String str, String str2) {
        if (str2.equals(str)) {
            return false;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int length = split.length < split2.length ? split.length : split2.length;
        for (int i = 0; i < length; i++) {
            if (Integer.parseInt(split[i]) > Integer.parseInt(split2[i])) {
                return true;
            }
            if (Integer.parseInt(split[i]) < Integer.parseInt(split2[i])) {
                return false;
            }
        }
        return false;
    }

    public DJIUpgradePackListModel f() {
        return this.D;
    }

    public boolean g() {
        return this.G;
    }

    public boolean a(String str) {
        if (str.equals("0305")) {
            str = "0300";
        } else if (str.equals("0306") && !this.T) {
            str = "0300";
        }
        if (this.W) {
            return j(str);
        }
        DataCommonGetVersion f = f(str);
        if (f == null) {
            return false;
        }
        return f.isGetted();
    }

    public int a(String str, int i) {
        if (this.W) {
            return Integer.parseInt(k(str).loadver.substring(1, 2));
        }
        DataCommonGetVersion f = f(str);
        if (f == null || !f.isGetted()) {
            return 0;
        }
        return f.getLoaderByte(i);
    }

    public int b(String str, int i) {
        if (this.W) {
            return Integer.parseInt(k(str).firmver.substring(1, 2));
        }
        DataCommonGetVersion f = f(str);
        if (f == null || !f.isGetted()) {
            return 0;
        }
        return f.getFirmByte(i);
    }

    public String b(String str) {
        if (str.equals("0305")) {
            str = "0300";
        } else if (str.equals("0306") && !this.T) {
            str = "0300";
        }
        return c(str);
    }

    public String c(String str) {
        if (this.W) {
            DJIVersionDbModel k = k(str);
            if (k != null) {
                return k.loadver;
            }
            return null;
        }
        DataCommonGetVersion f = f(str);
        if (f == null || !f.isGetted()) {
            return null;
        }
        return f.getLoader(".");
    }

    public String d(String str) {
        if (str.equals("0305")) {
            str = "0300";
        } else if (str.equals("0306") && !this.T) {
            str = "0300";
        }
        return e(str);
    }

    public String e(String str) {
        if (this.W) {
            DJIVersionDbModel k = k(str);
            if (k != null) {
                return k.firmver;
            }
            return null;
        }
        DataCommonGetVersion f = f(str);
        if (f == null || !f.isGetted()) {
            return null;
        }
        return f.getFirmVer(".");
    }

    public long a(String str, String str2) {
        long c = dji.pilot.publics.e.d.c(DJIUpgradePack.getVersion(str));
        if (c == 0) {
            return 1;
        }
        String str3;
        Object obj = null;
        if (str2.equals("0305")) {
            str2 = "0300";
            obj = 1;
        } else if (str2.equals("0306") && !this.T) {
            str2 = "0300";
        }
        String str4 = "";
        str4 = "";
        if (this.W) {
            DJIVersionDbModel k = k(str2);
            str3 = k.loadver;
            str4 = k.firmver;
        } else {
            DataCommonGetVersion f = f(str2);
            str3 = f.getLoader(".");
            str4 = f.getFirmVer(".");
        }
        if (str4.equals("01.00.08.220")) {
            str4 = "01.00.22.68";
        } else if (str4.equals("01.00.08.217")) {
            str4 = "01.00.22.67";
        }
        if (obj != null) {
            return dji.pilot.publics.e.d.c(str3) - c;
        }
        long c2 = dji.pilot.publics.e.d.c(str4);
        if (str2 == "1100" && c2 < 3000011) {
            return 0;
        }
        if (str2 != "1200" || dji.pilot.publics.e.d.c(str3) >= 9) {
            return c2 - c;
        }
        return 0;
    }

    public String h() {
        return this.L;
    }

    private boolean B() {
        return this.L.contains(h.RC.f) && this.L.contains(h.MC.f) && this.L.contains(h.CAMERA.f) && this.L.contains(h.BATTERY.f);
    }

    public String i() {
        return this.I;
    }

    public String j() {
        return this.J;
    }

    public DJIUpgradePack a(ProductType productType) {
        if (this.D == null) {
            return null;
        }
        List versionList = this.D.getVersionList(productType);
        if (versionList == null || versionList.isEmpty()) {
            return null;
        }
        return (DJIUpgradePack) versionList.get(0);
    }

    public DJIUpgradePack b(ProductType productType) {
        c();
        DJIUpgradePack dJIUpgradePack = null;
        if (this.D != null) {
            List versionList = this.D.getVersionList(productType);
            if (!(versionList == null || versionList.isEmpty())) {
                DJIUpgradePack dJIUpgradePack2 = (DJIUpgradePack) versionList.get(0);
                int size = versionList.size();
                int i = 1;
                dJIUpgradePack = dJIUpgradePack2;
                while (i < size) {
                    dJIUpgradePack2 = (DJIUpgradePack) versionList.get(i);
                    if (dJIUpgradePack2.date <= dJIUpgradePack.date) {
                        dJIUpgradePack2 = dJIUpgradePack;
                    }
                    i++;
                    dJIUpgradePack = dJIUpgradePack2;
                }
            }
        }
        return dJIUpgradePack;
    }

    private void C() {
        ArrayList arrayList = null;
        this.j = false;
        if (!this.G) {
            ArrayList versionList;
            ProductType c = i.getInstance().c();
            ProductType a = i.getInstance().a();
            DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "pType=" + c + " rcType=" + a + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.G, true, true);
            if (!this.W) {
                dji.pilot.publics.c.b.getInstance(this.v).a();
            }
            if (this.D != null) {
                versionList = this.D.getVersionList(c);
                arrayList = this.D.getVersionList(a);
            } else {
                versionList = null;
            }
            if (this.D == null || versionList == null || versionList.size() == 0) {
                DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "packListModel=" + this.D + " versionList=" + versionList, true, true);
                return;
            }
            this.M = 0;
            this.I = "";
            this.J = "";
            this.w = d.b;
            this.L = "";
            boolean z = versionList == arrayList;
            if (z) {
                h.reset(c);
                a(versionList, c, h.RC);
            } else {
                h.reset(c);
                h.reset(a);
                a(arrayList, a, h.RC);
            }
            a(versionList, c, h.MC);
            a(versionList, c);
            a(versionList, c, h.BATTERY);
            a(z);
            DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "准备锁定电机 flag=" + this.M + " isSameProduct =" + z, false, true);
            DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "bigVersion=" + this.I + " rcVersion=" + this.J, true, true);
            a(this.M);
            if (DJIUpgradeP4Service.d() && dji.dbox.upgrade.p4.a.a.s) {
                if (this.L.contains(h.RC.f)) {
                    this.L = "";
                    this.L += h.RC.f + "@";
                } else {
                    this.L = "";
                }
            }
            this.w = B() ? d.b : d.a;
            if (this.x == d.b) {
                dji.thirdparty.a.c.a().e(this.w);
                DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "remoteStatus " + this.w, false, true);
            }
            D();
            this.j = true;
        }
    }

    private void a(ArrayList<DJIUpgradePack> arrayList, ProductType productType) {
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "getCameraType =" + cameraType, false, true);
        if (CameraType.DJICameraTypeFC550 == cameraType) {
            if (productType == ProductType.Longan) {
                arrayList = this.D.versionlisthgX5;
            } else {
                arrayList = this.D.versionlistx5;
            }
        } else if (dji.pilot.fpv.d.b.j(cameraType)) {
            arrayList = this.D.versionlistxt;
        } else if (CameraType.DJICameraTypeFC550Raw == cameraType) {
            arrayList = this.D.versionlistx5r;
        } else if (CameraType.DJICameraTypeCV600 == cameraType && productType == ProductType.OrangeCV600) {
            arrayList = this.D.versionlistz3;
        } else if (CameraType.DJICameraTypeGD600 == cameraType) {
            arrayList = this.D.versionlistz30;
        }
        if (dji.pilot.publics.e.a.g()) {
            arrayList = null;
        }
        if (arrayList != null) {
            a(arrayList, productType, h.CAMERA);
        } else {
            this.L += h.CAMERA.a() + "@";
        }
    }

    public void onEventBackgroundThread(dji.dbox.upgrade.p4.a.b bVar) {
        switch (8.b[bVar.ordinal()]) {
            case 1:
                if (DJIUpgradeP4Service.h()) {
                    if (dji.dbox.upgrade.p4.a.a.s) {
                        if (this.L.contains(h.RC.f)) {
                            this.L = "";
                            this.L += h.RC.f + "@";
                        } else {
                            this.L = "";
                        }
                    } else if (this.L.contains(h.RC.f)) {
                        this.L = K;
                    } else {
                        this.L = K.replace("rc", "");
                    }
                } else if (dji.dbox.upgrade.p4.a.a.s) {
                    this.L = "";
                } else {
                    this.L = K;
                }
                this.w = B() ? d.b : d.a;
                dji.thirdparty.a.c.a().e(this.w);
                this.i = dji.dbox.upgrade.p4.a.a.r ? f.a : f.g;
                D();
                return;
            default:
                return;
        }
    }

    private void a(ArrayList<DJIUpgradePack> arrayList, ProductType productType, h hVar) {
        ArrayList modelList = h.getModelList(hVar, productType);
        g b = hVar.b();
        if (modelList == null) {
            this.L += hVar.a() + "@";
            return;
        }
        Iterator it = modelList.iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            DJIVerModel dJIVerModel = (DJIVerModel) it.next();
            if (a(dJIVerModel.code)) {
                String d = d(dJIVerModel.code);
                if (d == null) {
                    z = true;
                } else {
                    boolean z3;
                    try {
                        if (0 == Long.parseLong(d.replace(".", ""))) {
                            if (!dJIVerModel.code.equals("1500")) {
                                z2 = true;
                            }
                            DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "+++checkAlgo isUpgrade=" + z2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + dJIVerModel.code, true, true);
                            z = true;
                        } else {
                            z3 = z2;
                            int i = 0;
                            while (i < arrayList.size()) {
                                DJIUpgradePack dJIUpgradePack = (DJIUpgradePack) arrayList.get(i);
                                Class cls = dJIUpgradePack.getClass();
                                try {
                                    String i2 = i(dJIVerModel.code);
                                    if (h(i2)) {
                                        d = (String) cls.getField("m0901").get(dJIUpgradePack);
                                    } else {
                                        d = (String) cls.getField("m" + i2).get(dJIUpgradePack);
                                    }
                                    if (d == null && i == 0) {
                                        dJIVerModel.isSeted = true;
                                        dJIVerModel.position = i;
                                        dJIVerModel.version = dJIUpgradePack.version;
                                        if (i >= b.d) {
                                            b.d = i;
                                            if (h.RC == hVar) {
                                                b.a = dJIUpgradePack.rcversion;
                                                if (dji.pilot.publics.e.d.a(b.a)) {
                                                    b.a = dJIUpgradePack.version;
                                                }
                                            } else {
                                                b.a = dJIUpgradePack.version;
                                            }
                                        }
                                        if (dJIVerModel.version.equals("")) {
                                            dJIVerModel.isSeted = true;
                                            dJIVerModel.position = arrayList.size() - 1;
                                            dJIVerModel.version = ((DJIUpgradePack) arrayList.get(dJIVerModel.position)).version;
                                        }
                                        z2 = z3;
                                        z = true;
                                    } else {
                                        DJILogHelper.getInstance().LOGD("", "version=" + d + " key=" + i2, true, false);
                                        long a = a(d, dJIVerModel.code);
                                        if (a < 0) {
                                            int i3;
                                            int flag = DJIUpgradePack.getFlag(d);
                                            if (b.b > flag) {
                                                i3 = b.b;
                                            } else {
                                                i3 = flag;
                                            }
                                            b.b = i3;
                                            dJIVerModel.setFlag(flag);
                                            if (i == 0) {
                                                i3 = 1;
                                            } else {
                                                i3 = i;
                                            }
                                            b.d = i3;
                                        } else if (a == 0 || i == 0) {
                                            dJIVerModel.isSeted = true;
                                            dJIVerModel.position = i;
                                            dJIVerModel.version = dJIUpgradePack.version;
                                            if (i >= b.d) {
                                                b.d = i;
                                                if (h.RC == hVar) {
                                                    b.a = dJIUpgradePack.rcversion;
                                                    if (dji.pilot.publics.e.d.a(b.a)) {
                                                        b.a = dJIUpgradePack.version;
                                                    }
                                                } else {
                                                    b.a = dJIUpgradePack.version;
                                                }
                                            }
                                            if (dJIVerModel.version.equals("")) {
                                                dJIVerModel.isSeted = true;
                                                dJIVerModel.position = arrayList.size() - 1;
                                                dJIVerModel.version = ((DJIUpgradePack) arrayList.get(dJIVerModel.position)).version;
                                            }
                                            z2 = z3;
                                            z = true;
                                        }
                                        i++;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "error=" + e.getMessage(), false, true);
                                }
                            }
                            if (dJIVerModel.version.equals("")) {
                                dJIVerModel.isSeted = true;
                                dJIVerModel.position = arrayList.size() - 1;
                                dJIVerModel.version = ((DJIUpgradePack) arrayList.get(dJIVerModel.position)).version;
                            }
                            z2 = z3;
                            z = true;
                        }
                    } catch (Exception e2) {
                        z3 = z2;
                    }
                }
            }
        }
        if (!z2 && (b.d == 0 || !z)) {
            this.L += hVar.a() + "@";
        }
        DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "devicesStatus=" + this.L, true, true);
        DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "isUpgrade=" + z2 + " statusModel.position=" + b.d, true, true);
        DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "device=" + hVar + " isNeedCheck=" + z, true, true);
        this.M = this.M > b.b ? this.M : b.b;
    }

    private boolean h(String str) {
        if (i.getInstance().c() != ProductType.LonganMobile || !str.equals("0900")) {
            return false;
        }
        DataCommonGetVersion dataCommonGetVersion = new DataCommonGetVersion();
        dataCommonGetVersion.setDeviceType(DeviceType.find(9));
        if (dataCommonGetVersion.getLoaderByte(2) != 1) {
            return false;
        }
        return true;
    }

    private void a(boolean z) {
        int i = 0;
        h[] values;
        int length;
        h hVar;
        g b;
        if (z) {
            values = h.values();
            length = values.length;
            while (i < length) {
                hVar = values[i];
                if (!hVar.a().equals("rc")) {
                    b = hVar.b();
                    if (this.I.equals("")) {
                        this.I = b.a;
                    } else if (!b.a.equals("") && c(this.I, b.a)) {
                        this.I = b.a;
                    }
                }
                i++;
            }
            this.J = h.RC.b().a;
            if (dji.pilot.publics.e.d.a(this.J)) {
                this.J = this.I;
                return;
            }
            return;
        }
        values = h.values();
        length = values.length;
        while (i < length) {
            hVar = values[i];
            if (!hVar.a().equals("rc")) {
                b = hVar.b();
                if (this.I.equals("")) {
                    this.I = b.a;
                } else if (!b.a.equals("") && c(this.I, b.a)) {
                    this.I = b.a;
                }
            }
            i++;
        }
        this.J = h.RC.b().a;
        if (dji.pilot.publics.e.d.a(this.J)) {
            this.J = this.I;
        }
    }

    private String i(String str) {
        int i = 1;
        if ("1600".equals(str)) {
            return "160" + String.valueOf(a("1600", 2));
        } else if ("2000".equals(str)) {
            return "200" + String.valueOf(a("2000", 2));
        } else if ("1400".equals(str)) {
            return "140" + String.valueOf(a("1400", 2));
        } else if ("1300".equals(str)) {
            return "130" + String.valueOf(a("1300", 2));
        } else if (!"1100".equals(str)) {
            return str;
        } else {
            int b = b("1100", 1);
            StringBuilder append = new StringBuilder().append("110");
            if (b == 1) {
                i = 0;
            }
            return append.append(i).toString();
        }
    }

    private boolean c(String str, String str2) {
        try {
            boolean z;
            String[] split = str.split("\\.");
            String[] split2 = str2.split("\\.");
            int min = Math.min(split.length, split2.length);
            int i = 0;
            while (i < min) {
                int parseInt = Integer.parseInt(split[i]);
                int parseInt2 = Integer.parseInt(split2[i]);
                if (parseInt < parseInt2) {
                    z = true;
                    break;
                } else if (parseInt > parseInt2) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            z = false;
            if (z) {
                if (z > false) {
                    return false;
                }
                return true;
            } else if (split.length == split2.length) {
                return false;
            } else {
                if (split.length - split2.length > 0) {
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public int k() {
        return this.N;
    }

    private void D() {
        if (this.i == f.g) {
            g.a(this.v, b, 0);
        } else if (this.i == f.a) {
            dji.thirdparty.a.c.a().e(e.LOCK);
        } else {
            long b = g.b(this.v, b, 0);
            long currentTimeMillis = System.currentTimeMillis();
            DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "pretime=" + b + " remain=" + (currentTimeMillis - b), false, true);
            switch (8.c[this.i.ordinal()]) {
                case 1:
                    n = 3;
                    break;
                case 2:
                    n = 5;
                    break;
                case 3:
                    n = 7;
                    break;
                case 4:
                    n = 9;
                    break;
                default:
                    n = 1;
                    break;
            }
            if (b == 0 || currentTimeMillis - b < ((long) (((n * 24) * 3600) * 1000))) {
                if (b == 0) {
                    g.a(this.v, b, currentTimeMillis);
                    this.N = n;
                } else {
                    this.N = n - Math.round((float) ((currentTimeMillis - b) / 86400000));
                }
                dji.thirdparty.a.c.a().e(e.DIALOG);
            }
        }
    }

    private void a(int i) {
        switch (i) {
            case 0:
                this.i = f.g;
                return;
            case 1:
                this.i = f.b;
                return;
            case 2:
                this.i = f.c;
                return;
            case 3:
                this.i = f.d;
                return;
            case 4:
                this.i = f.e;
                return;
            case 100:
                this.i = f.a;
                return;
            default:
                this.i = f.f;
                return;
        }
    }

    public boolean l() {
        return o() && this.i != f.g;
    }

    public boolean m() {
        String e = e("1400");
        if (e == null) {
            return false;
        }
        long j = 0;
        try {
            j = dji.pilot.publics.e.d.c(e);
        } catch (Exception e2) {
        }
        if (j >= 67764224) {
            return true;
        }
        return false;
    }

    public void n() {
        this.L += "lb2@";
        this.x = d.a;
        dji.thirdparty.a.c.a().e(d.a);
    }

    public boolean o() {
        return (this.w == d.b && this.x == d.b) ? false : true;
    }

    public boolean p() {
        DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "needShowNotice =" + this.m);
        if (!this.m) {
            return false;
        }
        this.m = false;
        return true;
    }

    public String q() {
        String str = "";
        String toLowerCase = Locale.getDefault().toString().toLowerCase();
        DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "getLanguage=" + toLowerCase);
        if (this.D == null || this.D.announcement == null) {
            return str;
        }
        if (this.D.announcementAndroid != null) {
            if (toLowerCase.contains("ja")) {
                return this.D.announcementAndroid.jp;
            }
            if (toLowerCase.contains("zh")) {
                return this.D.announcementAndroid.zh;
            }
            return this.D.announcementAndroid.en;
        } else if (toLowerCase.contains("ja")) {
            return this.D.announcement.jp;
        } else {
            if (toLowerCase.contains("zh")) {
                return this.D.announcement.zh;
            }
            return this.D.announcement.en;
        }
    }

    public void onEventBackgroundThread(ProductType productType) {
        if (productType.isFromWifi()) {
            DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "date productType " + b());
            this.C.removeMessages(11);
        }
    }

    public void onEventBackgroundThread(p pVar) {
        switch (8.d[pVar.ordinal()]) {
            case 1:
                this.C.removeMessages(0);
                this.C.sendEmptyMessageDelayed(0, 1000);
                return;
            case 2:
                this.j = false;
                this.I = "";
                this.J = "";
                this.L = "";
                this.P = false;
                this.C.removeMessages(0);
                E();
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (8.e[oVar.ordinal()]) {
            case 1:
                this.C.removeMessages(0);
                this.C.sendEmptyMessageDelayed(0, 1000);
                return;
            case 2:
                this.j = false;
                this.P = false;
                this.C.removeMessages(0);
                return;
            default:
                return;
        }
    }

    private void E() {
        this.w = d.b;
        this.x = d.b;
        this.L = K;
    }

    private String a(DataCommonGetVersion dataCommonGetVersion) {
        int value = dataCommonGetVersion.getDeviceType().value();
        int modelId = dataCommonGetVersion.getModelId();
        return String.format(Locale.ENGLISH, "%02d%02d", new Object[]{Integer.valueOf(value), Integer.valueOf(modelId)});
    }

    public DataCommonGetVersion f(String str) {
        Iterator it = this.O.iterator();
        while (it.hasNext()) {
            DataCommonGetVersion dataCommonGetVersion = (DataCommonGetVersion) it.next();
            if (str.equals(a(dataCommonGetVersion))) {
                return dataCommonGetVersion;
            }
        }
        return null;
    }

    private void F() {
        DataCommonGetVersion deviceType = new DataCommonGetVersion().setDeviceType(DeviceType.CAMERA);
        DataCommonGetVersion deviceModel = new DataCommonGetVersion().setDeviceType(DeviceType.CAMERA).setDeviceModel(1);
        DataCommonGetVersion deviceModel2 = new DataCommonGetVersion().setDeviceType(DeviceType.CAMERA).setDeviceModel(4);
        DataCommonGetVersion deviceType2 = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL);
        DataCommonGetVersion deviceModel3 = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL).setDeviceModel(1);
        DataCommonGetVersion deviceModel4 = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL).setDeviceModel(2);
        DataCommonGetVersion deviceModel5 = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL).setDeviceModel(3);
        DataCommonGetVersion deviceType3 = new DataCommonGetVersion().setDeviceType(DeviceType.BATTERY);
        DataCommonGetVersion deviceType4 = new DataCommonGetVersion().setDeviceType(DeviceType.FLYC);
        DataCommonGetVersion deviceType5 = new DataCommonGetVersion().setDeviceType(DeviceType.CENTER);
        DataCommonGetVersion deviceType6 = new DataCommonGetVersion().setDeviceType(DeviceType.DM368);
        DataCommonGetVersion deviceModel6 = new DataCommonGetVersion().setDeviceType(DeviceType.DM368).setDeviceModel(1);
        DataCommonGetVersion deviceModel7 = new DataCommonGetVersion().setDeviceType(DeviceType.OFDM).setDeviceModel(0);
        DataCommonGetVersion deviceModel8 = new DataCommonGetVersion().setDeviceType(DeviceType.OFDM).setDeviceModel(1);
        DataCommonGetVersion deviceModel9 = new DataCommonGetVersion().setDeviceType(DeviceType.DIGITAL).setDeviceModel(0);
        DataCommonGetVersion deviceModel10 = new DataCommonGetVersion().setDeviceType(DeviceType.DIGITAL).setDeviceModel(1);
        DataCommonGetVersion deviceModel11 = new DataCommonGetVersion().setDeviceType(DeviceType.DIGITAL).setDeviceModel(2);
        DataCommonGetVersion deviceModel12 = new DataCommonGetVersion().setDeviceType(DeviceType.DIGITAL).setDeviceModel(3);
        DataCommonGetVersion deviceType7 = new DataCommonGetVersion().setDeviceType(DeviceType.TRANSFORM);
        DataCommonGetVersion deviceModel13 = new DataCommonGetVersion().setDeviceType(DeviceType.TRANSFORM).setDeviceModel(1);
        DataCommonGetVersion deviceType8 = new DataCommonGetVersion().setDeviceType(DeviceType.FPGA);
        DataCommonGetVersion deviceModel14 = new DataCommonGetVersion().setDeviceType(DeviceType.SINGLE).setDeviceModel(0);
        DataCommonGetVersion deviceModel15 = new DataCommonGetVersion().setDeviceType(DeviceType.SINGLE).setDeviceModel(1);
        DataCommonGetVersion deviceModel16 = new DataCommonGetVersion().setDeviceType(DeviceType.WIFI).setDeviceModel(0);
        DataCommonGetVersion deviceModel17 = new DataCommonGetVersion().setDeviceType(DeviceType.DM368).setDeviceModel(7);
        DataCommonGetVersion deviceType9 = new DataCommonGetVersion().setDeviceType(DeviceType.DM368_G);
        DataCommonGetVersion deviceType10 = new DataCommonGetVersion().setDeviceType(DeviceType.OSD);
        DataCommonGetVersion deviceType11 = new DataCommonGetVersion().setDeviceType(DeviceType.TRANSFORM_G);
        DataCommonGetVersion deviceType12 = new DataCommonGetVersion().setDeviceType(DeviceType.FPGA_G);
        DataCommonGetVersion deviceModel18 = new DataCommonGetVersion().setDeviceType(DeviceType.WIFI_G).setDeviceModel(0);
        DataCommonGetVersion deviceModel19 = new DataCommonGetVersion().setDeviceType(DeviceType.RC).setDeviceModel(5);
        this.O.add(deviceType9);
        this.O.add(deviceType10);
        this.O.add(deviceType11);
        this.O.add(deviceType12);
        this.O.add(deviceModel18);
        this.O.add(deviceModel14);
        this.O.add(deviceModel15);
        this.O.add(deviceType);
        this.O.add(deviceModel);
        this.O.add(deviceModel2);
        this.O.add(deviceType4);
        this.O.add(deviceType3);
        this.O.add(deviceType2);
        this.O.add(deviceModel3);
        this.O.add(deviceModel4);
        this.O.add(deviceModel5);
        this.O.add(deviceType5);
        this.O.add(deviceModel6);
        this.O.add(deviceType6);
        this.O.add(deviceModel7);
        this.O.add(deviceModel8);
        this.O.add(deviceModel9);
        this.O.add(deviceModel10);
        this.O.add(deviceModel11);
        this.O.add(deviceModel12);
        this.O.add(deviceType7);
        this.O.add(deviceModel13);
        this.O.add(deviceType8);
        this.O.add(deviceModel16);
        this.O.add(deviceModel17);
        this.O.add(deviceModel19);
    }

    private boolean b(DataCommonGetVersion dataCommonGetVersion) {
        ArrayList modelListAll = h.getModelListAll(i.getInstance().c());
        String format = String.format(Locale.ENGLISH, "%02d", new Object[]{Integer.valueOf(dataCommonGetVersion.getDeviceType().value())});
        Iterator it = modelListAll.iterator();
        while (it.hasNext()) {
            DJIVerModel dJIVerModel = (DJIVerModel) it.next();
            if (dJIVerModel.code.startsWith("08") || dJIVerModel.code.startsWith("15")) {
                if (dJIVerModel.code.equals(a(dataCommonGetVersion))) {
                    return true;
                }
            } else if (dJIVerModel.code.startsWith(format)) {
                return true;
            }
        }
        return false;
    }

    private void G() {
        if (this.P) {
            DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "版本获取中...重复操作将跳过", false, true);
            return;
        }
        this.i = f.g;
        this.P = true;
        if (ServiceManager.getInstance().isConnected()) {
            this.W = false;
            new DataCommonGetVersion().setDeviceType(DeviceType.OSD).start(new 4(this), 1000, 1);
            return;
        }
        P();
        this.P = false;
    }

    private void H() {
        new DataCommonGetVersion().setDeviceType(DeviceType.DM368_G).start(new 5(this), 1000, 2);
    }

    private void I() {
        DataCommonGetVersion deviceType = new DataCommonGetVersion().setDeviceType(DeviceType.TRANSFORM);
        deviceType.start(new 6(this, deviceType), 1000, 2);
    }

    private void J() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DataCommonGetVersion deviceType = new DataCommonGetVersion().setDeviceType(DeviceType.TRANSFORM);
        deviceType.startForce(new 7(this, deviceType), 1000, 2, true);
    }

    public void r() {
        l.a(new l(this, null), this.U, false).start();
    }

    private boolean K() {
        this.Q++;
        if (this.Q != this.O.size()) {
            return true;
        }
        if (this.R <= 0 || this.S != 0) {
            L();
            this.S = 0;
            this.C.sendEmptyMessage(10);
            return false;
        }
        this.S++;
        this.C.sendEmptyMessage(1);
        return false;
    }

    private void L() {
        ProductType c = i.getInstance().c();
        if (c == ProductType.P34K || c == ProductType.litchiC) {
            DataCommonGetVersion f = f("0300");
            Log.d("flyccc", "checkoutIsFlycFails " + f);
            if (f != null && f.getRecData() == null) {
                Log.d("flyccc", "checkoutIsFlycFails 2");
                f.setDeviceModel(6);
                this.T = true;
                return;
            }
        }
        if (f("0306") == null) {
            this.T = false;
        }
    }

    private void M() {
        O();
        l.a(new l(this, null), this.V, true).start();
    }

    private boolean N() {
        this.Q++;
        if (this.Q != this.O.size()) {
            return true;
        }
        this.P = false;
        C();
        return false;
    }

    private void c(DataCommonGetVersion dataCommonGetVersion) {
        Object dJIVersionDbModel = new DJIVersionDbModel();
        dJIVersionDbModel.device = dataCommonGetVersion.getDeviceType().value();
        dJIVersionDbModel.model = dataCommonGetVersion.getModelId();
        dJIVersionDbModel.firmver = dataCommonGetVersion.getFirmVer(".");
        dJIVersionDbModel.loadver = dataCommonGetVersion.getLoader(".");
        String str = "device=" + dJIVersionDbModel.device + " AND model=" + dJIVersionDbModel.model;
        if (this.H.c(DJIVersionDbModel.class, str).size() > 0) {
            this.H.c(dJIVersionDbModel, str);
        } else {
            this.H.a(dJIVersionDbModel);
        }
    }

    private void O() {
        this.H.a(DJIVersionDbModel.class);
    }

    public void s() {
        this.X = null;
        this.H.a(DJIVersionDbModel.class);
    }

    private void P() {
        if (i.getInstance().d().isFromWifi()) {
            this.X = this.H.c(DJIVersionDbModel.class);
            DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "进入本地版本匹配流程 " + this.X.size(), false, true);
            if (this.X.size() > 0) {
                for (DJIVersionDbModel dJIVersionDbModel : this.X) {
                    DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "local model=" + ((dJIVersionDbModel.device * 100) + dJIVersionDbModel.model) + " firm=" + dJIVersionDbModel.firmver, false, true);
                }
                this.W = true;
                C();
            }
        }
    }

    private boolean j(String str) {
        int parseInt = Integer.parseInt(str);
        for (DJIVersionDbModel dJIVersionDbModel : this.X) {
            if (parseInt == dJIVersionDbModel.model + (dJIVersionDbModel.device * 100)) {
                return true;
            }
        }
        return false;
    }

    private DJIVersionDbModel k(String str) {
        int parseInt = Integer.parseInt(str);
        for (DJIVersionDbModel dJIVersionDbModel : this.X) {
            if (parseInt == (dJIVersionDbModel.device * 100) + dJIVersionDbModel.model) {
                return dJIVersionDbModel;
            }
        }
        return null;
    }
}
