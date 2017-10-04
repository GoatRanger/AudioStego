package dji.dbox.upgrade.p4.d;

import android.text.TextUtils;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCommonGetCfgFile;
import dji.midware.data.model.P3.DataCommonGetCfgFile.DJIUpgradeFileType;
import dji.midware.e.d;
import dji.pilot.usercenter.mode.n;
import dji.thirdparty.afinal.c;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class a {
    private static String a = "DJIP4UpgradeLogUtils";
    private static a v = null;
    @Deprecated
    private boolean b = false;
    private int c = 0;
    private int d = 100;
    private Queue<DataCommonGetCfgFile> e = new LinkedList();
    private Queue<Boolean> f = new LinkedList();
    private Queue<File> g = new LinkedList();
    private Queue<Boolean> h = new LinkedList();
    private final String i = dji.dbox.upgrade.p4.a.a.e;
    private FileOutputStream j = null;
    private long k = -1;
    private long l = this.k;
    private long m = 0;
    private byte[] n = new byte[1024];
    private int o = 0;
    private long p = 0;
    private long q = 0;
    private a r;
    @Deprecated
    private b s;
    private dji.thirdparty.afinal.f.b t = new dji.thirdparty.afinal.f.b();
    private c u = com.dji.frame.c.c.a();
    private d w = new 1(this);
    private dji.thirdparty.afinal.f.a x = new 2(this);

    public interface a {
        void a();

        @Deprecated
        void a(int i);

        void b();
    }

    @Deprecated
    public interface b {
        void a();

        void a(String str);
    }

    public static a getInstance() {
        if (v == null) {
            v = new a();
        }
        return v;
    }

    private a() {
        File file = new File(this.i);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @Deprecated
    public void a(String str, String str2, String str3) {
        if (this.b) {
            String str4;
            String str5 = "";
            str5 = "";
            if (dji.dbox.upgrade.p4.a.a.a.a) {
                this.g = dji.dbox.upgrade.p4.a.a.a.b;
                str4 = dji.dbox.upgrade.p4.a.a.a.a.a;
                str5 = dji.dbox.upgrade.p4.a.a.a.a.b;
                str = dji.dbox.upgrade.p4.a.a.a.a.c;
            } else {
                str4 = i.getInstance().c().name();
                if (dji.dbox.upgrade.p4.a.a.v != null) {
                    str5 = dji.dbox.upgrade.p4.a.a.v.product_version;
                } else {
                    dji.dbox.upgrade.p4.a.a.a(" DJIUpConstants.latestElement == null ");
                }
            }
            dji.dbox.upgrade.p4.a.a.a("start 总入口，auto invoke downDeviceLog->upDeviceLog，no listers，推荐该方式");
            this.t.a("user_id", str3);
            this.t.a("product", str4);
            this.t.a("firmware_version", str5);
            this.t.a(n.bc, str);
            this.u.a("Token", str2);
            dji.dbox.upgrade.p4.a.a.a("start():token=" + str2);
            a(null);
            return;
        }
        dji.dbox.upgrade.p4.a.a.a("start, cannt auto invoke downDeviceLog->upDeviceLog , mIsAuto = " + this.b);
    }

    public void a(a aVar) {
        this.r = aVar;
        this.e.clear();
        this.f.clear();
        this.g.clear();
        this.h.clear();
        DataCommonGetCfgFile dataCommonGetCfgFile;
        if (i.getInstance().h()) {
            dataCommonGetCfgFile = new DataCommonGetCfgFile();
            dataCommonGetCfgFile.setReceiveId(1).setType(DJIUpgradeFileType.LOG).setLength(this.l).setOffset(this.m).setReceiveType(DeviceType.DM368);
            this.e.offer(dataCommonGetCfgFile);
            dataCommonGetCfgFile = new DataCommonGetCfgFile();
            dataCommonGetCfgFile.setReceiveId(1).setType(DJIUpgradeFileType.LOG).setLength(this.l).setOffset(this.m).setReceiveType(DeviceType.DM368_G);
            this.e.offer(dataCommonGetCfgFile);
        } else {
            dataCommonGetCfgFile = new DataCommonGetCfgFile();
            dataCommonGetCfgFile.setReceiveId(1).setType(DJIUpgradeFileType.LOG).setLength(this.l).setOffset(this.m).setReceiveType(DeviceType.DM368);
            this.e.offer(dataCommonGetCfgFile);
        }
        dji.dbox.upgrade.p4.a.a.a("downDeviceLog Main Entry,ProductName=" + i.getInstance().c()._name() + ",app will download [" + this.e.size() + "] LOG");
        this.d = this.e.size() * 100;
        a();
        b();
        dji.dbox.upgrade.p4.a.a.a("starDownLog 1st LOG");
    }

    public void a(String str, String str2, String str3, b bVar) {
        Object obj;
        this.s = bVar;
        String str4 = "";
        str4 = "";
        if (dji.dbox.upgrade.p4.a.a.a.a) {
            this.g = dji.dbox.upgrade.p4.a.a.a.b;
            obj = dji.dbox.upgrade.p4.a.a.a.a.a;
            str4 = dji.dbox.upgrade.p4.a.a.a.a.b;
            str = dji.dbox.upgrade.p4.a.a.a.a.c;
        } else {
            obj = i.getInstance().c()._name();
            str4 = dji.dbox.upgrade.p4.a.a.v.product_version;
        }
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || TextUtils.isEmpty(obj)) {
            dji.dbox.upgrade.p4.a.a.a("token/sn/product is required!!");
            bVar.a("");
            return;
        }
        this.t.a("user_id", str3);
        this.t.a("product", obj);
        this.t.a("firmware_version", str4);
        this.t.a(n.bc, str);
        this.u.a("Token", str2);
        dji.dbox.upgrade.p4.a.a.a("startUpLog:token=" + str2);
        f();
    }

    private void a() {
        this.q = 0;
        this.m = 0;
        this.l = this.k;
        this.o = 0;
    }

    private void b() {
        DataCommonGetCfgFile dataCommonGetCfgFile = (DataCommonGetCfgFile) this.e.peek();
        dataCommonGetCfgFile.setLength(this.l);
        dataCommonGetCfgFile.setOffset(this.m);
        dataCommonGetCfgFile.start(this.w);
    }

    private void c() {
        boolean z = true;
        this.e.poll();
        if (this.e.isEmpty()) {
            boolean z2 = false;
            int i = 0;
            while (!this.f.isEmpty()) {
                boolean z3;
                if (((Boolean) this.f.poll()).booleanValue()) {
                    dji.dbox.upgrade.p4.a.a.a("Down index=" + i + ",Success");
                    z3 = true;
                } else {
                    dji.dbox.upgrade.p4.a.a.a("Down index=" + i + ",onFailed,but may be down part!!!");
                    z3 = z2;
                }
                i++;
                z2 = z3;
            }
            File file = new File(dji.dbox.upgrade.p4.a.a.b());
            if (file.exists()) {
                dji.dbox.upgrade.p4.a.a.a("starDownNext finish, today log of app " + file + " exists!, add  mUpQueue!!");
                this.g.add(file);
            } else {
                dji.dbox.upgrade.p4.a.a.a("starDownNext finish, but today log of app " + file + "is not exists!,not nessssray add  mUpQueue!!");
                z = false;
            }
            dji.dbox.upgrade.p4.a.a.a("starDownNext->isExistAppLog=" + z + ",isExistRcOr1860log=" + z2);
            if (z || z2) {
                dji.dbox.upgrade.p4.a.a.a("starDownNext->onSuccess， get all LOG Complete！！");
                if (this.b) {
                    f();
                    return;
                } else if (this.r != null) {
                    this.r.a();
                    return;
                } else {
                    return;
                }
            }
            dji.dbox.upgrade.p4.a.a.a("starDownNext->onFailed， no find any LOG ！！");
            if (this.r != null) {
                this.r.b();
                return;
            }
            return;
        }
        dji.dbox.upgrade.p4.a.a.a("starDownNext，continue download the next");
        a();
        b();
    }

    private void d() throws IOException {
        if (this.q == 0) {
            if (this.j != null) {
                this.j.write(this.n, 0, this.o);
                this.j.flush();
                this.j.close();
                this.j = null;
            }
        } else if (this.q < 0) {
            dji.dbox.upgrade.p4.a.a.a("GetCfgCallBack writeToLocal error");
        } else {
            if (this.j == null) {
                this.g.add(e());
                this.j = new FileOutputStream((File) this.g.peek());
            }
            this.j.write(this.n, 0, this.o);
            this.j.flush();
        }
    }

    private File e() {
        return new File(this.i + System.currentTimeMillis() + ".txt");
    }

    private void f() {
        File file = (File) this.g.peek();
        if (file == null) {
            dji.dbox.upgrade.p4.a.a.a("error,file=" + file);
            return;
        }
        dji.dbox.upgrade.p4.a.a.a("startUpLog:" + dji.dbox.upgrade.p4.a.a.f + ":param=[" + this.t.toString() + dji.pilot.usercenter.protocol.d.H);
        dji.dbox.upgrade.p4.a.a.a("file=" + file);
        try {
            this.t.a(n.K, dji.midware.util.c.h(com.dji.frame.c.a.a(file)));
            this.t.a("log", file);
            this.u.b(dji.dbox.upgrade.p4.a.a.f, this.t, this.x);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            f();
        }
    }

    private void g() {
        this.g.poll();
        if (this.g.isEmpty()) {
            Object obj = null;
            int i = 0;
            while (!this.h.isEmpty()) {
                Object obj2;
                if (((Boolean) this.h.poll()).booleanValue()) {
                    dji.dbox.upgrade.p4.a.a.a("upload index=" + i + ",Success");
                    obj2 = 1;
                } else {
                    dji.dbox.upgrade.p4.a.a.a("upload index=" + i + ",onFailed");
                    obj2 = obj;
                }
                i++;
                obj = obj2;
            }
            if (obj != null) {
                dji.dbox.upgrade.p4.a.a.a("starUpNext->onSuccess， all LOG UpUpUpUpUpUp Complete！！");
                if (this.s != null) {
                    this.s.a();
                    return;
                }
                return;
            }
            dji.dbox.upgrade.p4.a.a.a("starUpNext->onFailure");
            if (this.s != null) {
                this.s.a("");
                return;
            }
            return;
        }
        dji.dbox.upgrade.p4.a.a.a("starUpNext，continue upload the next");
        f();
    }
}
