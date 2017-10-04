package dji.pilot.publics.control.p3cupgrade;

import android.support.v4.view.MotionEventCompat;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.p;
import dji.midware.data.model.P3.DataBaseSetting;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataOsdSetLED;
import dji.midware.e.e;
import dji.midware.util.b;
import dji.midware.util.m;
import dji.pilot.publics.control.p3cupgrade.b.g;
import dji.pilot.publics.control.p3cupgrade.c.c;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Timer;
import java.util.TimerTask;

public class d implements c {
    public final String a = "DJIHG300UpgradeTask";
    long b;
    private final String c = "0900";
    private final String d = "0901";
    private final int e = 1;
    private final int f = 2;
    private final int g = 3;
    private boolean h = false;
    private String i;
    private g j;
    private int k;
    private a l;
    private int m;
    private int n;
    private Timer o;
    private boolean p = false;
    private DataOsdSetLED q;
    private String r;

    public interface a {
        void a();

        void a(int i);

        void a(dji.midware.data.config.P3.a aVar);

        void b();
    }

    public d(g gVar, String str, a aVar) {
        this.i = str;
        this.j = gVar;
        this.k = this.j.b.size();
        this.l = aVar;
    }

    public void c() {
        this.m = 0;
        if (this.h) {
            e();
        } else {
            a(false);
        }
        f();
        dji.midware.b.c.getInstance().a(true);
    }

    public void d() {
        this.l = null;
    }

    private void a(final boolean z) {
        e.a("DJIHG300UpgradeTask", "checkMcu confirmed?" + dji.logic.a.a.getInstance().b + "is 303?" + dji.logic.a.a.getInstance().a);
        if (dji.logic.a.a.getInstance().b) {
            this.p = true;
            b(dji.logic.a.a.getInstance().a);
            e();
            return;
        }
        final e dataCommonGetVersion = new DataCommonGetVersion();
        dataCommonGetVersion.setDeviceType(DeviceType.find(9));
        new m(dataCommonGetVersion, 20, 1000, new dji.midware.e.d(this) {
            final /* synthetic */ d c;

            public void onSuccess(Object obj) {
                int loaderByte = dataCommonGetVersion.getLoaderByte(2);
                if (loaderByte == 1) {
                    this.c.b(true);
                    dji.logic.a.a.getInstance().a(true);
                }
                this.c.p = true;
                this.c.e();
                e.a("DJIHG300UpgradeTask", "request mcu succeed, loader value:" + loaderByte + "isFinal?" + z);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (z) {
                    this.c.l.a(aVar);
                    return;
                }
                this.c.p = false;
                this.c.e();
                e.a("DJIHG300UpgradeTask", "request mcu failed for" + aVar + "isFinal?" + z);
            }
        }).a();
    }

    private void b(boolean z) {
        if (z) {
            int indexOf = this.j.a.devices.indexOf("0900");
            if (indexOf >= 0 && indexOf < this.j.a.devices.size()) {
                this.j.a.devices.set(indexOf, "0901");
            }
            indexOf = this.j.b.indexOf("0900");
            if (indexOf >= 0 && indexOf < this.j.b.size()) {
                this.j.b.set(indexOf, "0901");
            }
            e.a("DJIHG300UpgradeTask", "configMcuDevice is303?" + z);
        }
    }

    private void e() {
        if (this.m < this.k) {
            String str = (String) this.j.b.get(this.m);
            this.r = str;
            if (this.h) {
                a(str);
                this.n = (this.m * 100) / this.k;
                e.a("DJIHG300UpgradeTask", "update device : " + str);
                this.l.a(this.n);
                this.m++;
                return;
            } else if (!str.equals("0900") || this.p) {
                a(str);
                this.n = (this.m * 100) / this.k;
                e.a("DJIHG300UpgradeTask", "update device : " + str);
                this.l.a(this.n);
                this.m++;
                return;
            } else {
                a(true);
                return;
            }
        }
        this.l.a(100);
        this.l.b();
        this.o.cancel();
        this.o = null;
        dji.midware.b.c.getInstance().a(false);
        if (!dji.logic.a.a.getInstance().a) {
            b(3);
        }
    }

    private void a(String str) {
        dji.pilot.publics.control.upgrade.e.c a = dji.pilot.publics.control.upgrade.e.a(this.i, ProductType.LonganMobile, false);
        if (a != null && a.a) {
            dji.pilot.publics.control.upgrade.e.a a2 = a.a(str);
            if (a2 != null) {
                if (a2.a == 9 && a2.b == 1) {
                    a2.b = 0;
                }
                try {
                    new c(DeviceType.find(a2.a), a2, new RandomAccessFile(new File(this.i), "r"), this).a();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void a() {
    }

    public void a(int i) {
        int i2 = (((this.m - 1) * 100) / this.k) + (((100 / this.k) * i) / 100);
        this.l.a(i2);
        this.n = i2;
        e.a("DJIHG300UpgradeTask", "progress : " + this.n);
        if (!dji.logic.a.a.getInstance().a && System.currentTimeMillis() - this.b > 2000) {
            this.b = System.currentTimeMillis();
            b(1);
        }
    }

    public void b() {
        b.a(new Runnable(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.e();
            }
        }, 2000);
    }

    public void a(dji.midware.data.config.P3.a aVar) {
        this.l.a(aVar);
        DJILogHelper.getInstance().LOGD("DJIHG300UpgradeTask", "fails : " + aVar);
        this.o.cancel();
        this.o = null;
        dji.midware.b.c.getInstance().a(false);
        if (!dji.logic.a.a.getInstance().a) {
            b(2);
        }
    }

    private void f() {
        final byte[] bArr = new byte[]{(byte) -75, (byte) 42};
        final DataBaseSetting dataBaseSetting = new DataBaseSetting();
        if (this.o == null) {
            this.o = new Timer("hg300 gimbal upgrade control");
            this.o.schedule(new TimerTask(this) {
                final /* synthetic */ d c;

                public void run() {
                    dataBaseSetting.a(p.e).a(13).a(DeviceType.GIMBAL).a(bArr).start(null);
                }
            }, 1000, 1000);
        }
    }

    private void b(int i) {
        if (this.p && !dji.logic.a.a.getInstance().a) {
            if (this.r.equals("0900")) {
                if (i == 3) {
                    if (this.q == null) {
                        this.q = new DataOsdSetLED();
                    }
                    this.q.a().c(1, 65535, 16, 255);
                    new m(this.q, 20, null).a();
                } else if (i == 2) {
                    if (this.q == null) {
                        this.q = new DataOsdSetLED();
                    }
                    this.q.a().a(1, 65535, 16, 255);
                    new m(this.q, 20, null).a();
                }
            } else if (i == 1) {
                if (this.q == null) {
                    this.q = new DataOsdSetLED();
                }
                this.q.a().a(1, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 16, 255).c(1, 255, 16, 255).start(null);
            }
        }
    }
}
