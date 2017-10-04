package dji.pilot.publics.control.rc;

import android.content.Context;
import android.widget.Toast;
import com.dji.frame.c.d;
import com.dji.frame.c.l;
import com.here.odnp.debug.DebugFile;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCommonGetDeviceStatus;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataCommonRestartDevice;
import dji.midware.data.model.P3.DataOsdSetUpgradeTip;
import dji.midware.data.model.P3.DataOsdSetUpgradeTip.UPGRADETIP;
import dji.pilot.R;
import dji.pilot.publics.control.rc.a.c;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import java.io.File;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class b {
    private static final int A = 8192;
    private static final int B = 5000;
    private static final int C = 200;
    private static final int D = 3;
    private static final int E = 50;
    private static final int F = 20480;
    private static final int G = 24576;
    private static final int H = 39321;
    private static final int I = 10000;
    private static final int J = 100;
    private static final boolean K = true;
    private static final String L = "WM610_FW_RC_V";
    private static final String M = "P3XS_FW_RC_V";
    private static final String N = "P3XS_FW_RC_V";
    private static final String O = "RC_FW_V";
    private static final String P = ".bin";
    private static final String Q = "Package/";
    private static final String R = "result.txt";
    private static final String S = "RC_FW_DEBUG";
    private static final String T = "";
    private static final DeviceType[] V = new DeviceType[]{DeviceType.DM368_G, DeviceType.TRANSFORM_G, DeviceType.FPGA_G, DeviceType.OSD, DeviceType.RC};
    private static final DeviceType[] W = new DeviceType[]{DeviceType.TRANSFORM_G, DeviceType.FPGA_G, DeviceType.OSD, DeviceType.RC};
    public static final boolean a = false;
    public static final boolean b = true;
    public static final boolean c = true;
    public static final int d = 256;
    public static final int e = 257;
    public static final int f = 258;
    public static final int g = 259;
    public static final int h = 260;
    public static final int i = 261;
    public static final int j = 262;
    public static final int k = 263;
    public static final int l = 264;
    public static final int m = 512;
    public static final int n = 513;
    public static final int o = 514;
    public static final int p = 5;
    public static final String q = "1.0";
    public static final int r = 200;
    private static final String s = b.class.getSimpleName();
    private static final int t = 4096;
    private static final int u = 4097;
    private static final long v = 2600;
    private static final int w = 4098;
    private static final int x = 4352;
    private static final long y = 200;
    private static final int z = 30;
    private DeviceType[] U;
    private Context X;
    private volatile boolean Y;
    private String Z;
    private g aA;
    private final DataOsdSetUpgradeTip aB;
    private boolean aC;
    private int aD;
    private DJIUpgradePack aE;
    private int aa;
    private int ab;
    private String ac;
    private String ad;
    private DJIUpgradePack ae;
    private final ArrayList<b> af;
    private c ag;
    private dji.pilot.publics.control.rc.c.b ah;
    private long ai;
    private long aj;
    private int ak;
    private long al;
    private boolean am;
    private long an;
    private RandomAccessFile ao;
    private c ap;
    private c aq;
    private c ar;
    private e as;
    private d at;
    private dji.thirdparty.afinal.b au;
    private final ArrayList<DJIDLPackageInfo> av;
    private final ArrayList<DJIDLPackageInfo> aw;
    private final HashMap<DJIDLPackageInfo, dji.thirdparty.afinal.f.c<File>> ax;
    private DJIDLPackageInfo ay;
    private dji.thirdparty.afinal.c az;

    public static b getInstance() {
        return f.a();
    }

    public synchronized void a(Context context) {
        if (!this.Y) {
            this.Y = true;
            this.ab = 256;
            this.X = context.getApplicationContext();
            dji.thirdparty.a.c.a().a((Object) this, 100);
            this.Z = d.a(this.X, Q);
            dji.pilot.usercenter.f.c.f(this.Z);
            this.au = com.dji.frame.c.c.c(this.X);
            try {
                List c = this.au.c(DJIDLPackageInfo.class, "mType=" + String.valueOf(0));
                if (!(c == null || c.isEmpty())) {
                    int size = c.size();
                    for (int i = 0; i < size; i++) {
                        DJIDLPackageInfo dJIDLPackageInfo = (DJIDLPackageInfo) c.get(i);
                        a(dJIDLPackageInfo, true);
                        this.av.add(dJIDLPackageInfo);
                    }
                }
            } catch (Exception e) {
            }
            this.az = com.dji.frame.c.c.b(this.X);
        }
    }

    private void a(DJIDLPackageInfo dJIDLPackageInfo, boolean z) {
        if (z) {
            dJIDLPackageInfo.mDLStatus = 1;
        }
        File file = new File(dJIDLPackageInfo.mAbsPath);
        if (0 == dJIDLPackageInfo.mPackageSize) {
            dJIDLPackageInfo.mDLSize = 0;
        } else if (dji.pilot.usercenter.f.c.a(file)) {
            dJIDLPackageInfo.mDLSize = file.length();
            if (file.length() >= dJIDLPackageInfo.mPackageSize) {
                dJIDLPackageInfo.mDLStatus = 3;
            }
        } else {
            dJIDLPackageInfo.mDLSize = 0;
        }
    }

    public synchronized void a() {
        if (this.Y) {
            this.ay = null;
            this.av.clear();
            this.aw.clear();
            t();
            dji.thirdparty.a.c.a().d((Object) this);
            this.Y = false;
        }
    }

    private void t() {
        if (!this.ax.isEmpty()) {
            for (Object obj : this.ax.keySet()) {
                dji.thirdparty.afinal.f.c cVar = (dji.thirdparty.afinal.f.c) this.ax.get(obj);
                if (cVar != null) {
                    cVar.h();
                }
            }
            this.ax.clear();
        }
    }

    public void onEventMainThread(p pVar) {
        DJILogHelper.getInstance().LOGD(s, "Data " + pVar, false, true);
        if (pVar == p.ConnectLose) {
            if (this.ab != j || this.ap == null) {
                this.aa = 512;
                this.ak = 0;
                this.aj = 0;
                this.ai = 0;
                this.af.clear();
                if (this.ap != null) {
                    this.ap.c();
                    this.ap = null;
                }
                C();
                if (this.ab == j) {
                    this.ab = l;
                    if (this.as != null) {
                        this.as.a(this, a.NOCONNECT, 101, 0);
                        return;
                    }
                    return;
                }
                return;
            }
            this.ap.a(true);
        } else if (pVar != p.ConnectOK) {
        } else {
            if (this.ab == j && this.ap != null) {
                this.ap.a(false);
            } else if (this.aa == 512) {
                this.aa = 513;
            }
        }
    }

    public void b() {
        if (this.ap != null) {
            this.ap.a(false);
            this.ap.d();
        }
        if (this.aA != null) {
            this.aA.removeMessages(8192);
        }
    }

    public void onEventMainThread(i.a aVar) {
        if (!g()) {
            int i = this.ab;
            this.ab = 256;
            if (i != this.ab && this.at != null) {
                this.at.a(this.ab);
            }
        }
    }

    private String a(boolean z) {
        if (z) {
            if (this.aE != null) {
                this.ae = this.aE;
            } else {
                this.ae = dji.pilot.publics.control.a.getInstance(this.X).a(K());
            }
        }
        if (this.ae != null) {
            return this.ae.rc1url;
        }
        return null;
    }

    private String b(boolean z) {
        String str = "";
        if (this.aE != null) {
            this.ae = this.aE;
        } else if (z) {
            this.ae = dji.pilot.publics.control.a.getInstance(this.X).a(K());
        }
        if (this.ae != null) {
            if (dji.pilot.publics.e.d.a(this.ae.rcversion)) {
                str = this.ae.version;
            } else {
                str = this.ae.rcversion;
            }
        }
        if (dji.pilot.publics.e.d.a(str)) {
            return "";
        }
        return str;
    }

    public boolean a(DJIUpgradePack dJIUpgradePack) {
        if (dJIUpgradePack != null && j != this.ab && this.aC) {
            this.aE = dJIUpgradePack;
            int i = this.ab;
            this.ae = dJIUpgradePack;
            this.ab = 258;
            this.ad = b(false);
            DJIDLPackageInfo a = a(d(this.ad));
            if (a != null) {
                this.ay = a;
                a(a, false);
                if (a.mDLStatus == 3) {
                    if (this.ah == null) {
                        this.ab = 261;
                    } else if (this.ab != l) {
                        this.ab = 261;
                    }
                } else if (this.ah == null) {
                    this.ab = 259;
                } else if (this.ab != 260) {
                    this.ab = 259;
                }
            } else {
                this.ay = null;
                this.ab = 258;
            }
            w();
            if (!(i == this.ab || this.at == null)) {
                this.at.a(this.ab);
            }
        } else if (j != this.ab) {
            this.aE = null;
            u();
        }
        return true;
    }

    private void u() {
        int i = this.ab;
        if (dji.pilot.publics.control.a.getInstance(this.X).a(b(DeviceType.DM368_G.value(), 0))) {
            this.U = V;
            this.aC = dji.pilot.publics.control.a.getInstance(this.X).a(b(DeviceType.DM368_G.value(), 0), 1) > 1;
        } else {
            this.U = W;
            this.aC = true;
        }
        if (!this.aC) {
            this.ay = null;
            this.ab = 257;
        } else if (this.aE == null) {
            if (dji.pilot.publics.control.a.getInstance(this.X).h().contains("rc")) {
                this.ay = null;
                this.ab = 256;
            } else {
                this.ab = 258;
                this.ac = dji.pilot.publics.control.a.getInstance(this.X).j();
                if (dji.pilot.publics.e.d.a(this.ac)) {
                    this.ac = "";
                }
                this.ad = b(true);
                DJIDLPackageInfo a = a(d(this.ad));
                if (a != null) {
                    this.ay = a;
                    a(a, false);
                    if (a.mDLStatus == 3) {
                        if (this.ah == null) {
                            this.ab = 261;
                        } else if (this.ab != l) {
                            this.ab = 261;
                        }
                    } else if (this.ah == null) {
                        this.ab = 259;
                    } else if (this.ab != 260) {
                        this.ab = 259;
                    }
                } else {
                    this.ay = null;
                    this.ab = 258;
                }
                w();
            }
        }
        DJILogHelper.getInstance().LOGD(s, "RcStatus[" + this.ab + dji.pilot.usercenter.protocol.d.H, false, true);
        if (i != this.ab && this.at != null) {
            this.at.a(this.ab);
        }
    }

    public void onEventMainThread(dji.pilot.publics.control.a.d dVar) {
        DJILogHelper.getInstance().LOGD(s, "upgrade status[" + dVar + "]type[" + i.getInstance().a() + dji.pilot.usercenter.protocol.d.H, false, true);
        if (g()) {
            this.aa = 514;
            if (this.ab == j) {
                return;
            }
            if (dji.pilot.publics.control.a.d.b == dVar) {
                this.ab = 256;
                return;
            } else {
                u();
                return;
            }
        }
        this.ab = 256;
    }

    public void a(c cVar) {
        this.ar = cVar;
    }

    public void c() {
        this.ar = null;
    }

    public void a(e eVar) {
        this.as = eVar;
    }

    public void d() {
        this.as = null;
    }

    public void a(d dVar) {
        this.at = dVar;
    }

    public void e() {
        this.at = null;
    }

    public boolean f() {
        if (this.aa == 512) {
            if (this.ab == 256 || this.ab == 258 || this.ab == l || this.ab == k) {
                return false;
            }
            return true;
        } else if (this.ab == 256) {
            return false;
        } else {
            return true;
        }
    }

    public boolean g() {
        return a(K());
    }

    public static boolean a(ProductType productType) {
        return productType == ProductType.Orange || productType == ProductType.litchiS || productType == ProductType.litchiX || productType == ProductType.N1 || productType == ProductType.Grape2 || productType == ProductType.A2 || productType == ProductType.Tomato || productType == ProductType.Pomato || productType == ProductType.BigBanana || productType == ProductType.OrangeRAW || productType == ProductType.Olives || productType == ProductType.OrangeCV600;
    }

    public boolean h() {
        return this.ab == 260 || this.ab == 259 || this.ab == 261;
    }

    public int i() {
        return this.ab;
    }

    public boolean j() {
        return this.ab == j;
    }

    public String k() {
        return this.ac;
    }

    public String l() {
        return this.ad;
    }

    public List<DJIDLPackageInfo> m() {
        return this.aw;
    }

    public DJIDLPackageInfo n() {
        return this.ay;
    }

    public int o() {
        return this.ak;
    }

    public int a(DJIDLPackageInfo dJIDLPackageInfo) {
        long j = 0;
        if (dJIDLPackageInfo == null) {
            return 0;
        }
        if (dJIDLPackageInfo.mPackageSize != 0) {
            j = (dJIDLPackageInfo.mDLSize * 200) / dJIDLPackageInfo.mPackageSize;
        }
        return (int) j;
    }

    public void p() {
        String a = a(false);
        if (dji.pilot.publics.e.d.a(a)) {
            dji.pilot.publics.control.a.getInstance(this.X).d();
            Toast.makeText(this.X, this.X.getString(R.string.rcupgrade_wait_version_dling), 0).show();
            return;
        }
        DJIDLPackageInfo dJIDLPackageInfo;
        this.ad = b(false);
        String d = d(this.ad);
        int i = this.ab;
        DJIDLPackageInfo a2 = a(d);
        if (a2 == null) {
            a2 = a(true, d);
            this.ay = a2;
            this.ab = 259;
            dJIDLPackageInfo = a2;
        } else {
            w();
            a2.mDLUrl = a;
            a2.mDLSize = 0;
            this.ay = a2;
            this.ab = 259;
            dJIDLPackageInfo = a2;
        }
        this.ax.put(dJIDLPackageInfo, this.az.a(dJIDLPackageInfo.mDLUrl, dJIDLPackageInfo.mAbsPath, true, true, new a(dJIDLPackageInfo, this.aq, null)));
        if (i != this.ab && this.at != null) {
            this.at.a(this.ab);
        }
    }

    public void b(DJIDLPackageInfo dJIDLPackageInfo) {
        dji.thirdparty.afinal.f.c cVar = (dji.thirdparty.afinal.f.c) this.ax.get(dJIDLPackageInfo);
        int i = this.ab;
        if (cVar != null) {
            cVar.h();
        }
        if (this.ay == dJIDLPackageInfo) {
            this.ab = 259;
        }
        dJIDLPackageInfo.mDLStatus = 1;
        if (i != this.ab && this.at != null) {
            this.at.a(this.ab);
        }
    }

    public void q() {
        if (this.ay != null) {
            int i = this.ab;
            dji.thirdparty.afinal.f.c cVar = (dji.thirdparty.afinal.f.c) this.ax.remove(this.ay);
            if (cVar != null) {
                cVar.h();
            }
            dji.pilot.usercenter.f.c.e(this.ay.mAbsPath);
            this.au.f(this.ay);
            b(this.ay.mFileName);
            this.ay = null;
            this.ab = 258;
            w();
            if (i != this.ab && this.at != null) {
                this.at.a(this.ab);
            }
        }
    }

    public void r() {
        x();
        y();
    }

    private void v() {
        if (this.ab != j) {
            return;
        }
        if (!J()) {
            dji.pilot.usercenter.f.c.e(e(d(this.ad)));
            this.ab = 258;
            if (this.at != null) {
                this.at.a(this.ab, 0);
            }
        } else if (this.af.isEmpty()) {
            a(null, a.UNDEFINED, 101, 0);
        } else {
            B();
            H();
        }
    }

    public void s() {
        DJILogHelper.getInstance().LOGD(s, "upgradeRc[" + this.aa + dji.pilot.usercenter.protocol.d.H, false, true);
        if (514 == this.aa) {
            this.am = true;
            String e = e(d(this.ad));
            DJILogHelper.getInstance().LOGD(s, "upgradeRc[" + e + dji.pilot.usercenter.protocol.d.H, false, true);
            if (dji.pilot.usercenter.f.c.b(e)) {
                D();
                A();
                this.aD = 0;
                c(0);
                return;
            }
            this.ay = a(d(this.ad));
            if (this.ay != null) {
                this.ab = 259;
                this.ay.mDLSize = 0;
                this.ay.mDLStatus = 4;
                if (this.ar != null) {
                    this.ar.a(this.ay, 0, "");
                    return;
                }
                return;
            }
            this.ay = null;
            this.ab = 258;
            if (this.at != null) {
                this.at.a(this.ab);
            }
        }
    }

    private void w() {
        this.aw.clear();
        int size = this.av.size();
        for (int i = 0; i < size; i++) {
            DJIDLPackageInfo dJIDLPackageInfo = (DJIDLPackageInfo) this.av.get(i);
            if (dJIDLPackageInfo.mProductId == ProductType.None.value()) {
                this.aw.add(dJIDLPackageInfo);
            }
        }
    }

    private void x() {
        while (this.av.size() > 0) {
            DJIDLPackageInfo dJIDLPackageInfo = (DJIDLPackageInfo) this.av.get(0);
            this.av.remove(0);
            this.au.a(DJIDLPackageInfo.class, "mAbsPath='" + dJIDLPackageInfo.mAbsPath + "'");
            dji.pilot.usercenter.f.c.e(dJIDLPackageInfo.mAbsPath);
        }
    }

    private void y() {
        File[] listFiles = new File(this.Z).listFiles(new 1(this, L()));
        if (listFiles != null && listFiles.length > 0) {
            for (File d : listFiles) {
                dji.pilot.usercenter.f.c.d(d);
            }
        }
    }

    private void z() {
        int i = 0;
        while (i < this.av.size()) {
            DJIDLPackageInfo dJIDLPackageInfo = (DJIDLPackageInfo) this.av.get(i);
            if (dJIDLPackageInfo.mProductId == ProductType.None.value() && (dJIDLPackageInfo.mVersion == null || !dJIDLPackageInfo.mVersion.equals(this.ad))) {
                this.av.remove(i);
                this.au.f(dJIDLPackageInfo);
                dji.pilot.usercenter.f.c.e(dJIDLPackageInfo.mAbsPath);
                i--;
            }
            i++;
        }
    }

    private DJIDLPackageInfo a(String str) {
        int size = this.av.size();
        for (int i = 0; i < size; i++) {
            DJIDLPackageInfo dJIDLPackageInfo = (DJIDLPackageInfo) this.av.get(i);
            if (str.equals(dJIDLPackageInfo.mFileName)) {
                return dJIDLPackageInfo;
            }
        }
        return null;
    }

    private DJIDLPackageInfo b(String str) {
        int size = this.av.size();
        for (int i = 0; i < size; i++) {
            DJIDLPackageInfo dJIDLPackageInfo = (DJIDLPackageInfo) this.av.get(i);
            if (str.equals(dJIDLPackageInfo.mFileName)) {
                this.av.remove(i);
                this.au.f(dJIDLPackageInfo);
                dji.pilot.usercenter.f.c.e(this.ay.mAbsPath);
                return dJIDLPackageInfo;
            }
        }
        return null;
    }

    private DJIDLPackageInfo a(boolean z, String str) {
        Object dJIDLPackageInfo = new DJIDLPackageInfo();
        dJIDLPackageInfo.mAbsPath = e(str);
        dJIDLPackageInfo.mFileName = str;
        dJIDLPackageInfo.mDLUrl = a(false);
        dJIDLPackageInfo.mDLSize = 0;
        dJIDLPackageInfo.mDLStatus = 0;
        dJIDLPackageInfo.mPackageSize = 0;
        dJIDLPackageInfo.mProductId = ProductType.None.value();
        dJIDLPackageInfo.mRealProductId = K().value();
        dJIDLPackageInfo.mVersion = this.ad;
        dJIDLPackageInfo.mType = 0;
        this.av.add(dJIDLPackageInfo);
        this.au.a(dJIDLPackageInfo);
        return dJIDLPackageInfo;
    }

    private void A() {
        this.ab = j;
        dji.thirdparty.a.c.a().e(dji.pilot2.upgrade.rollback.DJIRollBackActivity.b.a);
        if (this.as != null) {
            this.as.b(this, 0);
        }
    }

    private void a(int i, int i2) {
        if (this.as != null) {
            this.as.a(this, i, i2);
        }
    }

    private void a(c cVar, a aVar, int i, int i2) {
        dji.thirdparty.a.c.a().e(dji.pilot2.upgrade.rollback.DJIRollBackActivity.b.a);
        this.ap = null;
        C();
        if (this.ab == j) {
            String deviceType = cVar != null ? cVar.a().toString() : "null";
            String str = "";
            if (i2 == 0) {
                deviceType = "device[" + deviceType + "]err[" + aVar + dji.pilot.usercenter.protocol.d.H;
            } else {
                deviceType = "device[" + deviceType + "]err[" + aVar + "]reason[" + this.X.getString(i2) + dji.pilot.usercenter.protocol.d.H;
            }
            c("upgrade failed " + l.b() + "reason " + deviceType + DebugFile.EOL);
            for (DeviceType a : this.U) {
                a(a, true);
            }
            a(UPGRADETIP.c, 3, 50, false);
            this.ab = l;
            if (this.as != null) {
                this.as.a(this, aVar, i, i2);
            }
            dji.thirdparty.a.c.a().e(dji.pilot2.upgrade.rollback.DJIRollBackActivity.b.a);
        }
    }

    private void a(int i) {
        dji.thirdparty.a.c.a().e(dji.pilot2.upgrade.rollback.DJIRollBackActivity.b.a);
        this.ap = null;
        C();
        if (this.ab == j) {
            c("upgrade success in time[" + l.b() + "]\r\n");
            a(UPGRADETIP.b, 3, 50, false);
            this.ab = k;
            if (this.as != null) {
                this.as.a(this, i);
            }
            dji.thirdparty.a.c.a().e(dji.pilot2.upgrade.rollback.DJIRollBackActivity.b.a);
        }
    }

    private void c(String str) {
        dji.pilot.usercenter.f.c.a(e(R), str, true);
    }

    private void B() {
        C();
        try {
            this.ao = new RandomAccessFile(new File(e(d(this.ad))), "r");
            if (this.ao == null) {
                try {
                    this.ao = new RandomAccessFile(new File(e(this.ad)), "r");
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            if (this.ao == null) {
                try {
                    this.ao = new RandomAccessFile(new File(e(this.ad)), "r");
                } catch (Exception e3) {
                }
            }
        } catch (Throwable th) {
            if (this.ao == null) {
                try {
                    this.ao = new RandomAccessFile(new File(e(this.ad)), "r");
                } catch (Exception e4) {
                }
            }
        }
    }

    private void C() {
        if (this.ao != null) {
            try {
                this.ao.close();
            } catch (Exception e) {
            }
            this.ao = null;
        }
    }

    private void D() {
        this.ai = 0;
        this.aj = 0;
        this.ak = 0;
    }

    private boolean E() {
        int size = this.af.size();
        int i = 0;
        boolean z = false;
        while (i < size) {
            boolean z2;
            if (((b) this.af.get(i)).a == DeviceType.FPGA_G) {
                z2 = true;
            } else {
                z2 = z;
            }
            i++;
            z = z2;
        }
        return z;
    }

    private void b(int i) {
        if (!this.af.isEmpty()) {
            this.af.remove(0);
        }
        if (i == 2 && !E()) {
            a(UPGRADETIP.a, -1, 200, false);
            this.aA.sendEmptyMessageDelayed(H, 10000);
        } else if (!this.af.isEmpty()) {
            a(UPGRADETIP.a, -1, 200, false);
            this.aA.sendEmptyMessageDelayed(4097, v);
        } else if (this.am) {
            a(0);
        } else {
            a(null, a.FIRM_MATCH_WRONG, 106, (int) R.string.rcupgrade_fail_notdetect);
        }
    }

    private boolean a(DeviceType deviceType, String str) {
        boolean z = true;
        if (DeviceType.FPGA_G != deviceType) {
            return dji.pilot.publics.control.a.getInstance(this.X).a(str);
        }
        if (!dji.pilot.publics.control.a.getInstance(this.X).a(str)) {
            return false;
        }
        String b = dji.pilot.publics.control.a.getInstance(this.X).b(str);
        DJILogHelper.getInstance().LOGD(s, "==== FPGA Loader[" + b + dji.pilot.usercenter.protocol.d.H, false, true);
        if (dji.pilot.publics.e.d.a(b)) {
            return false;
        }
        try {
            if (Long.parseLong(b.replace(".", "")) == 0) {
                z = false;
            }
            return z;
        } catch (Exception e) {
            return false;
        }
    }

    private void F() {
        Object obj = 1;
        if (!(this.ag == null || this.ag.c.isEmpty())) {
            String b = b(DeviceType.FPGA_G.value(), 0);
            String e = dji.pilot.publics.control.a.getInstance(this.X).e(b);
            if (dji.pilot.publics.e.d.a(e) || !a(DeviceType.FPGA_G, b)) {
                this.am = false;
            } else {
                List a = a(DeviceType.FPGA_G, this.ag.c);
                if (a == null || a.isEmpty()) {
                    obj = null;
                } else {
                    int size = a.size();
                    int i = 0;
                    while (i < size) {
                        Object obj2;
                        a.a aVar = (a.a) a.get(i);
                        String b2 = b(aVar.a, aVar.b);
                        long j = aVar.f;
                        long c = dji.pilot.publics.e.d.c(e);
                        DJILogHelper.getInstance().LOGD(s, "====FPGA[" + c + dji.pilot.usercenter.protocol.d.H + j + dji.pilot.usercenter.protocol.d.H, false, true);
                        if (this.aE != null || c < j) {
                            b bVar = new b(null);
                            bVar.f = String.valueOf(aVar.f);
                            bVar.a = DeviceType.FPGA_G;
                            bVar.c = b2;
                            bVar.b = 0;
                            bVar.d = true;
                            bVar.g = aVar;
                            this.af.add(bVar);
                            obj2 = obj;
                        } else {
                            obj2 = null;
                        }
                        i++;
                        obj = obj2;
                    }
                }
            }
        }
        if (obj != null) {
            H();
            return;
        }
        this.an = this.aj;
        G();
    }

    private void G() {
        this.aj = ((long) ((int) (this.al / 30))) + this.aj;
        if (this.aj > this.an + this.al) {
            this.aj = this.an + this.al;
        }
        int i = (int) ((this.aj * 200) / this.ai);
        if (this.ak != i) {
            this.ak = i;
            a(i, 200);
        }
        if (this.aj >= this.an + this.al) {
            H();
        } else {
            this.aA.sendEmptyMessageDelayed(x, 200);
        }
    }

    private void a(DeviceType deviceType, boolean z) {
        int i;
        int value = deviceType.value();
        if (DeviceType.RC == deviceType) {
            i = 5;
        } else {
            i = 0;
        }
        a(0, 0, dji.pilot.publics.control.a.getInstance(this.X).f(b(value, i)), z);
    }

    private void a(int i, int i2, DataCommonGetVersion dataCommonGetVersion, boolean z) {
        if (dataCommonGetVersion == null || i >= 5) {
            return;
        }
        if (z) {
            dataCommonGetVersion.startForce(new 2(this, dataCommonGetVersion, i));
        } else {
            dataCommonGetVersion.start(new 3(this, dataCommonGetVersion, i));
        }
    }

    private void H() {
        if (!this.af.isEmpty()) {
            b bVar = (b) this.af.get(0);
            a(UPGRADETIP.a, -1, 200, false);
            this.ap = new c(bVar.a, bVar.b, bVar.g, this.ao, this.ah);
            this.ap.b(bVar.i);
            this.ap.b();
            DJILogHelper.getInstance().LOGD(s, "upgreadeNext device[" + this.ap.a() + dji.pilot.usercenter.protocol.d.H, true, true);
        } else if (this.am) {
            a(0);
        } else {
            a(null, a.FIRM_MATCH_WRONG, 106, (int) R.string.rcupgrade_fail_notdetect);
        }
    }

    private List<a.a> a(DeviceType deviceType, List<a.a> list) {
        int i = 0;
        List<a.a> arrayList = new ArrayList();
        int a = deviceType == DeviceType.TRANSFORM_G ? dji.pilot.publics.control.a.getInstance().a(b(deviceType.value(), 0), 2) : deviceType == DeviceType.FPGA_G ? dji.pilot.publics.control.a.getInstance().a(b(deviceType.value(), 0), 2) : deviceType == DeviceType.OSD ? dji.pilot.publics.control.a.getInstance().a(b(deviceType.value(), 0), 2) : deviceType == DeviceType.DM368_G ? dji.pilot.publics.control.a.getInstance().a(b(deviceType.value(), 0), 2) : deviceType == DeviceType.RC ? 5 : -1;
        int size = list.size();
        while (i < size) {
            a.a aVar = (a.a) list.get(i);
            if (aVar.a == deviceType.value() && (r1 == -1 || r1 == aVar.b)) {
                arrayList.add(aVar);
                break;
            }
            i++;
        }
        return arrayList;
    }

    private String b(int i, int i2) {
        return String.format(Locale.US, "%02d%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
    }

    private String I() {
        return "C1";
    }

    private static boolean a(String str, a.a aVar, DeviceType deviceType) {
        Throwable th;
        boolean z = false;
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(new File(str), "r");
            try {
                if (randomAccessFile.length() >= ((long) (aVar.g + aVar.h))) {
                    if (deviceType == DeviceType.FPGA_G) {
                        z = true;
                    } else {
                        byte[] bArr = new byte[4096];
                        randomAccessFile.seek((long) aVar.g);
                        int i = aVar.h > 4096 ? 4096 : aVar.h;
                        MessageDigest instance = MessageDigest.getInstance("MD5");
                        int i2 = i;
                        i = 0;
                        while (true) {
                            int read = randomAccessFile.read(bArr, 0, i2);
                            if (read == -1) {
                                break;
                            }
                            instance.update(bArr, 0, read);
                            i += read;
                            if (i >= aVar.h) {
                                break;
                            } else if (aVar.h - i < 4096) {
                                i2 = aVar.h - i;
                            }
                        }
                        z = dji.thirdparty.afinal.c.c.a(aVar.j, instance.digest());
                    }
                }
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (Exception e3) {
                    }
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            randomAccessFile = null;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            return z;
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile = null;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
        return z;
    }

    private boolean J() {
        boolean z = false;
        this.af.clear();
        String e = e(d(this.ad));
        this.ag = a.a(e, I());
        if (!(this.ag == null || this.ag.c.isEmpty())) {
            z = true;
            int length = this.U.length;
            int i = 0;
            while (i < length && r8) {
                List a = a(this.U[i], this.ag.c);
                if (!(a == null || a.isEmpty())) {
                    int size = a.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        Object obj;
                        a.a aVar = (a.a) a.get(i2);
                        int i3 = 0;
                        String str = "";
                        if (DeviceType.TRANSFORM_G == this.U[i]) {
                            str = b(aVar.a, 0);
                        } else if (DeviceType.FPGA_G == this.U[i]) {
                            str = b(aVar.a, 0);
                            this.al = (long) aVar.h;
                        } else if (DeviceType.RC == this.U[i]) {
                            str = b(aVar.a, 5);
                            i3 = 5;
                        } else {
                            str = b(aVar.a, 0);
                        }
                        String b = b(aVar.a, aVar.b);
                        String e2 = dji.pilot.publics.control.a.getInstance(this.X).e(str);
                        boolean a2 = a(this.U[i], str);
                        if (dji.pilot.publics.e.d.a(e2) || !a2) {
                            obj = 1;
                            if (!(this.aD == 1 && DeviceType.FPGA_G == this.U[i])) {
                                this.am = false;
                            }
                        } else {
                            boolean z2;
                            long j = aVar.f;
                            long c = dji.pilot.publics.e.d.c(e2);
                            DJILogHelper instance = DJILogHelper.getInstance();
                            instance.LOGD(s, "Firm[" + j + "]cur[" + c + "]-" + str, false, true);
                            if (this.aE != null || c < j) {
                                z = a(e, aVar, this.U[i]);
                                if (z) {
                                    b bVar = new b(null);
                                    bVar.f = String.valueOf(aVar.f);
                                    bVar.a = this.U[i];
                                    bVar.c = b;
                                    bVar.b = i3;
                                    bVar.d = true;
                                    bVar.g = aVar;
                                    if (DeviceType.OSD == this.U[i] && this.aD == 1) {
                                        bVar.i = true;
                                        this.af.add(0, bVar);
                                    } else {
                                        this.af.add(bVar);
                                    }
                                    obj = 1;
                                    z2 = z;
                                } else {
                                    obj = null;
                                    z2 = z;
                                }
                            } else {
                                obj = null;
                                z2 = z;
                            }
                            z = z2;
                        }
                        if (obj != null) {
                            this.ai = ((long) aVar.h) + this.ai;
                        }
                    }
                }
                i++;
            }
        }
        String str2 = "Type[" + this.ag.b.f + dji.pilot.usercenter.protocol.d.H;
        int size2 = this.af.size();
        str2 = (str2 + "size[" + size2 + dji.pilot.usercenter.protocol.d.H) + "model[";
        int i4 = 0;
        while (i4 < size2) {
            String str3 = str2 + ((b) this.af.get(i4)).a + com.alipay.sdk.j.i.b + ((b) this.af.get(i4)).b + ";;";
            i4++;
            str2 = str3;
        }
        DJILogHelper.getInstance().LOGD(s, "===" + (str2 + dji.pilot.usercenter.protocol.d.H) + dji.pilot.usercenter.protocol.d.H, true, true);
        return z;
    }

    private ProductType K() {
        return i.getInstance().a();
    }

    private String L() {
        return O;
    }

    private String d(String str) {
        return L() + str + P;
    }

    private String e(String str) {
        return this.Z + str;
    }

    private void c(int i, int i2) {
        if (i < 100) {
            new DataCommonRestartDevice().setReceiveType(DeviceType.RC).setRestartType(0).setDelay(i2 * 1000).start(new 4(this, i, i2));
        }
    }

    private void c(int i) {
        if (i < 5) {
            DataCommonGetDeviceStatus dataCommonGetDeviceStatus = new DataCommonGetDeviceStatus();
            dataCommonGetDeviceStatus.setReceiveType(DeviceType.OSD).setReceiveId(0).setVersioin(0, 0).start(new 5(this, dataCommonGetDeviceStatus, i));
            return;
        }
        this.aD = 0;
        v();
    }

    private void a(int i, int i2, a aVar) {
        DJILogHelper.getInstance().LOGD(s, "Osd1765 Result[" + i + "]Status[" + i2 + dji.pilot.usercenter.protocol.d.H, true, true);
        if (i == 0) {
            this.aD = i2;
            v();
            return;
        }
        c(i2 + 1);
    }

    private void a(UPGRADETIP upgradetip, int i, int i2, boolean z) {
        this.aA.removeMessages(8192);
        if (upgradetip == UPGRADETIP.a && z) {
            i2 = 5000;
        }
        this.aB.a(upgradetip).start(null);
        int i3 = i == -1 ? -1 : i - 1;
        if (i3 == -1 || i3 > 0) {
            this.aA.sendMessageDelayed(this.aA.obtainMessage(8192, i3, i2, upgradetip), (long) i2);
        }
    }

    private b() {
        this.U = V;
        this.X = null;
        this.Y = false;
        this.Z = null;
        this.aa = 512;
        this.ab = 256;
        this.ac = "";
        this.ad = "";
        this.ae = null;
        this.af = new ArrayList();
        this.ag = null;
        this.ah = null;
        this.ai = 0;
        this.aj = 0;
        this.ak = 0;
        this.al = 0;
        this.am = true;
        this.an = 0;
        this.ao = null;
        this.ap = null;
        this.aq = null;
        this.ar = null;
        this.as = null;
        this.at = null;
        this.au = null;
        this.av = new ArrayList();
        this.aw = new ArrayList();
        this.ax = new HashMap();
        this.ay = null;
        this.az = null;
        this.aA = null;
        this.aB = new DataOsdSetUpgradeTip();
        this.aC = true;
        this.aD = 0;
        this.aE = null;
        this.aA = new g(this);
        this.ah = new 6(this);
        this.aq = new 7(this);
    }
}
