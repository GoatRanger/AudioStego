package dji.dbox.upgrade.p4.statemachine;

import android.os.Environment;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonRequestReceiveData;
import dji.midware.data.model.P3.DataCommonRequestUpgrade;
import dji.midware.data.model.P3.DataCommonRequestUpgrade.DJIUpgradeFileMethod;
import dji.midware.data.model.P3.DataCommonRequestUpgrade.DJIUpgradeTranMethod;
import dji.midware.data.model.P3.DataCommonTranslateComplete;
import dji.midware.data.model.P3.DataCommonTranslateData;
import dji.midware.e.d;
import dji.publics.DJIObject.c;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

class e extends c {
    private final String a = getClass().getSimpleName();
    private final String b = (Environment.getExternalStorageDirectory().getPath() + "/DJI/p4_08.bin");
    private boolean c = true;
    private int d = 0;
    private int e = 0;
    private long f = 0;
    private long g = 0;
    private RandomAccessFile h;
    private DataCommonRequestUpgrade i = DataCommonRequestUpgrade.getInstance().setReceiveType(DeviceType.DM368).setReceiveId(1);
    private DataCommonRequestReceiveData j = DataCommonRequestReceiveData.getInstance().setReceiveType(DeviceType.DM368).setReceiveId(1);
    private DataCommonTranslateData k = DataCommonTranslateData.getInstance().setReceiveType(DeviceType.DM368).setReceiveId(1);
    private DataCommonTranslateComplete l = DataCommonTranslateComplete.getInstance().setReceiveType(DeviceType.DM368).setReceiveId(1);
    private g m;
    private DJIUpgradeFileMethod n;
    private DJIUpgradeTranMethod o;
    private byte[] p = new byte[2048];
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private byte[] u;

    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] a = new int[a.values().length];

        static {
            try {
                a[a.s.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.a.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public e(g gVar) {
        this.m = gVar;
    }

    public void a(String str, boolean z) {
        this.i.setReceiveType(dji.dbox.upgrade.p4.a.a.k);
        this.j.setReceiveType(dji.dbox.upgrade.p4.a.a.k);
        this.k.setReceiveType(dji.dbox.upgrade.p4.a.a.k);
        this.l.setReceiveType(dji.dbox.upgrade.p4.a.a.k);
        if (z) {
            f();
        }
        if (this.c && str == null) {
            str = this.b;
        }
        g();
        if (new File(str).exists()) {
            this.u = com.dji.frame.c.a.a(new File(str));
            a("md5=" + dji.midware.util.c.i(this.u));
            try {
                this.h = new RandomAccessFile(str, "r");
                this.f = this.h.length();
                return;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                this.m.n();
                return;
            } catch (IOException e2) {
                e2.printStackTrace();
                this.m.n();
                return;
            }
        }
        dji.dbox.upgrade.p4.a.a.a(this.a, "tar 文件不存在");
        this.m.n();
    }

    private void f() {
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = false;
    }

    private void g() {
        this.e = 0;
        this.f = 0;
        this.g = 0;
        if (this.h != null) {
            try {
                this.h.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void a(String str) {
        if (this.c) {
            DJILogHelper.getInstance().LOGD(this.a, str, false, false);
        }
    }

    public void c() {
        h();
    }

    private void h() {
        if (this.q) {
            i();
        } else {
            this.i.start(new d(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    DJIUpgradeTranMethod tranMethodEntry = this.a.i.getTranMethodEntry();
                    DJIUpgradeFileMethod tranFileEntry = this.a.i.getTranFileEntry();
                    if (!tranMethodEntry.isSupportV1) {
                        dji.dbox.upgrade.p4.a.a.a(this.a.a, "isSupportV1 false");
                        this.a.m.o();
                    } else if (tranFileEntry.isSupportBigPackage) {
                        this.a.n = (DJIUpgradeFileMethod) tranFileEntry.clone();
                        this.a.o = (DJIUpgradeTranMethod) tranMethodEntry.clone();
                        this.a.i();
                    } else {
                        dji.dbox.upgrade.p4.a.a.a(this.a.a, "isSupportBigPackage false");
                        this.a.m.o();
                    }
                }

                public void onFailure(a aVar) {
                    dji.dbox.upgrade.p4.a.a.a(this.a.a, "uploadFailEnter onFailure " + aVar + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + aVar.b());
                    this.a.m.o();
                }
            });
        }
    }

    private void i() {
        if (this.r) {
            d();
            return;
        }
        this.n.reset();
        this.n.isSupportBigPackage = true;
        this.o.reset();
        this.o.isSupportV1 = true;
        this.j.setDataLength(this.f);
        this.j.setTranMethod(this.o);
        this.j.setFileMethod(this.n).start(new d(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.d = this.a.j.getReceiveDataLength();
                dji.dbox.upgrade.p4.a.a.a(this.a.a, "packUnitLength = " + this.a.d);
                this.a.d();
            }

            public void onFailure(a aVar) {
                dji.dbox.upgrade.p4.a.a.a(this.a.a, "uploadFailPreTran onFailure " + aVar);
                this.a.m.p();
            }
        });
    }

    public void d() {
        if (this.s) {
            k();
            return;
        }
        try {
            long j = (long) (this.e * this.d);
            this.h.seek(j);
            int read = this.h.read(this.p, 0, this.d);
            this.k.setData(this.p, read);
            this.k.setSequence(this.e);
            this.e++;
            this.g = j + ((long) read);
            if (j() || (this.e > 0 && this.e % 4000 == 0)) {
                a("uploadNextPack start packIndex=" + this.e);
                this.k.start(new d(this) {
                    final /* synthetic */ e a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.a("uploadonSuccess packIndex=" + this.a.e);
                        if (this.a.j()) {
                            this.a.k();
                        } else {
                            this.a.m.r();
                        }
                    }

                    public void onFailure(a aVar) {
                        switch (AnonymousClass5.a[aVar.ordinal()]) {
                            case 1:
                                this.a.e = this.a.k.getSequence() + 1;
                                this.a.m.r();
                                break;
                            case 2:
                                this.a.e = this.a.e - 1;
                                this.a.m.r();
                                break;
                            default:
                                this.a.m.q();
                                break;
                        }
                        this.a.a("uploadFailTraning onFailure " + aVar + " packIndex=" + (this.a.e + 1) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + aVar + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + aVar.b());
                    }
                });
                return;
            }
            this.k.start();
            this.m.r();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean j() {
        return this.g >= this.f;
    }

    private void k() {
        if (this.t) {
            this.m.s();
            return;
        }
        g();
        this.l.setMD5(this.u).setTimeOut(10000).start(new d(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.m.s();
            }

            public void onFailure(a aVar) {
                dji.dbox.upgrade.p4.a.a.a(this.a.a, "uploadFailQuit onFailure " + aVar);
                if (aVar == a.z) {
                    this.a.m.s();
                } else {
                    this.a.m.a(aVar);
                }
            }
        });
    }

    protected void a() {
    }

    public void b() {
    }

    public int e() {
        return (int) ((((float) this.g) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) / ((float) this.f));
    }
}
