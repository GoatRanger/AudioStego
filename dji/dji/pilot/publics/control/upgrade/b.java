package dji.pilot.publics.control.upgrade;

import android.content.Context;
import com.dji.frame.c.a;
import com.dji.frame.c.d;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.thirdparty.afinal.c;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class b {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    private Context f = null;
    private dji.thirdparty.afinal.b g = null;
    private c h = null;
    private final HashMap<DLPackageInfo, dji.thirdparty.afinal.f.c<File>> i = new HashMap();
    private volatile boolean j = false;
    private String k = null;

    public static b getInstance() {
        return c.a();
    }

    public synchronized void a(Context context) {
        if (!this.j) {
            this.j = true;
            this.f = context;
            this.k = d.a(this.f, "Upgrade/DLPackage");
            dji.pilot.usercenter.f.c.f(this.k);
            this.g = com.dji.frame.c.c.c(this.f);
            this.h = com.dji.frame.c.c.b(this.f);
            b();
        }
    }

    private void b() {
        try {
            List c = this.g.c(DLPackageInfo.class, "mDLStatus = 2");
            if (c != null && !c.isEmpty()) {
                int size = c.size();
                for (int i = 0; i < size; i++) {
                    DLPackageInfo dLPackageInfo = (DLPackageInfo) c.get(i);
                    if (dLPackageInfo.mDLStatus == 2) {
                        dLPackageInfo.mDLStatus = 1;
                        c(dLPackageInfo);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a(DJIUpgradePack dJIUpgradePack, ProductType productType) {
        DLPackageInfo a = a(dJIUpgradePack.version, productType);
        if (a == null || a.mDLStatus != 3) {
            return false;
        }
        File file = new File(a.mAbsPath);
        if (!file.exists() || file.length() < a.mPackageSize) {
            return false;
        }
        return true;
    }

    public void a(DJIUpgradePack dJIUpgradePack, ProductType productType, b bVar) {
        if (dJIUpgradePack != null && bVar != null) {
            DLPackageInfo a = a(dJIUpgradePack.version, productType);
            if (a == null) {
                a(productType);
            }
            if (a == null) {
                a = c(dJIUpgradePack, productType);
            }
            if (a.mDLStatus == 2) {
                bVar.a(a, 1126, "出现这种情况，是UI逻辑层调用下载相同包两次，前一次下载还没下载完，正在进行中,这里直接返回，并返回失败，UI逻辑层收到该错误，应该检查自己逻辑，为什么会同时调用两次");
                return;
            }
            DLPackageInfo c;
            if (a.mDLStatus == 3) {
                File file = new File(a.mAbsPath);
                if (file.exists()) {
                    if (file.length() >= a.mPackageSize) {
                        bVar.a(a);
                        return;
                    } else {
                        b(a);
                        a = c(dJIUpgradePack, productType);
                    }
                }
            }
            if (a.mDLStatus == 4) {
                b(a);
                c = c(dJIUpgradePack, productType);
            } else {
                c = a;
            }
            a(c);
            this.i.put(c, this.h.a(c.mDLUrl, c.mAbsPath, true, true, new a(c, this.g, bVar, null)));
        }
    }

    public void a(DLPackageInfo dLPackageInfo) {
        this.g.a((Object) dLPackageInfo);
    }

    private void c(DLPackageInfo dLPackageInfo) {
        this.g.c((Object) dLPackageInfo, String.format("mProductId = '%s' AND mVersion = '%s'", new Object[]{Integer.valueOf(dLPackageInfo.mProductId), dLPackageInfo.mVersion}));
    }

    private static void b(dji.thirdparty.afinal.b bVar, DLPackageInfo dLPackageInfo) {
        bVar.c((Object) dLPackageInfo, String.format("mProductId = '%s' AND mVersion = '%s'", new Object[]{Integer.valueOf(dLPackageInfo.mProductId), dLPackageInfo.mVersion}));
    }

    private DLPackageInfo a(String str, ProductType productType) {
        List c = this.g.c(DLPackageInfo.class, String.format("mProductId = '%s' AND mVersion = '%s'", new Object[]{Integer.valueOf(productType.value()), str}));
        if (c == null || c.size() <= 0) {
            return null;
        }
        return (DLPackageInfo) c.get(0);
    }

    public void b(DLPackageInfo dLPackageInfo) {
        if (dLPackageInfo != null) {
            this.g.a(DLPackageInfo.class, String.format("mProductId = '%s' AND mVersion = '%s'", new Object[]{Integer.valueOf(dLPackageInfo.mProductId), dLPackageInfo.mVersion}));
            dji.pilot.usercenter.f.c.e(dLPackageInfo.mAbsPath);
        }
    }

    private DLPackageInfo c(DJIUpgradePack dJIUpgradePack, ProductType productType) {
        DLPackageInfo dLPackageInfo = new DLPackageInfo();
        String b = b(dJIUpgradePack);
        dLPackageInfo.mAbsPath = a(b);
        dLPackageInfo.mFileName = b;
        if (!dji.pilot.publics.e.d.a(dJIUpgradePack.rcurl)) {
            dLPackageInfo.mDLUrl = dJIUpgradePack.rcurl;
        } else if (!dji.pilot.publics.e.d.a(dJIUpgradePack.packurl)) {
            dLPackageInfo.mDLUrl = dJIUpgradePack.packurl;
        }
        dLPackageInfo.mDLSize = 0;
        dLPackageInfo.mDLStatus = 0;
        dLPackageInfo.mPackageSize = 0;
        dLPackageInfo.mProductId = productType.value();
        dLPackageInfo.mVersion = dJIUpgradePack.version;
        return dLPackageInfo;
    }

    private void a(ProductType productType) {
        List c = this.g.c(DLPackageInfo.class, "mProductId = " + productType.value());
        for (int i = 0; i < c.size(); i++) {
            b((DLPackageInfo) c.get(i));
        }
    }

    private String b(DJIUpgradePack dJIUpgradePack) {
        String str = null;
        String a = a(dJIUpgradePack);
        if (a == null) {
            return null;
        }
        if (a.endsWith(".bin")) {
            String[] split = a.split(dji.pilot.usercenter.protocol.d.t);
            for (int length = split.length - 1; length > 0; length--) {
                if (split[length].endsWith(".bin")) {
                    str = split[length];
                    break;
                }
            }
        }
        if (str == null) {
            return a.a(a);
        }
        return str;
    }

    private String a(String str) {
        return this.k + dji.pilot.usercenter.protocol.d.t + str;
    }

    public synchronized void a() {
        if (this.j) {
            c();
            dji.thirdparty.a.c.a().d((Object) this);
            this.j = false;
        }
    }

    private void c() {
        if (!this.i.isEmpty()) {
            for (Object obj : this.i.keySet()) {
                dji.thirdparty.afinal.f.c cVar = (dji.thirdparty.afinal.f.c) this.i.get(obj);
                if (cVar != null) {
                    cVar.h();
                }
            }
            this.i.clear();
        }
    }

    public DLPackageInfo b(DJIUpgradePack dJIUpgradePack, ProductType productType) {
        return a(dJIUpgradePack.version, productType);
    }

    public String a(DJIUpgradePack dJIUpgradePack) {
        if (dJIUpgradePack == null) {
            return null;
        }
        if (!dji.pilot.publics.e.d.a(dJIUpgradePack.rcurl)) {
            return dJIUpgradePack.rcurl;
        }
        if (dji.pilot.publics.e.d.a(dJIUpgradePack.packurl)) {
            return null;
        }
        return dJIUpgradePack.packurl;
    }
}
