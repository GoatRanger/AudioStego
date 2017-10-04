package dji.dbox.upgrade.p4.statemachine;

import android.content.Context;
import com.dji.frame.c.h;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.dbox.upgrade.p4.c.a;
import dji.dbox.upgrade.p4.c.a$a;
import dji.dbox.upgrade.p4.model.DJIUpCfgModel;
import dji.dbox.upgrade.p4.model.DJIUpDownPathModel;
import dji.midware.natives.UpgradeVerify;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

class c {
    a a;
    ArrayList<dji.thirdparty.afinal.f.c<File>> b;
    ArrayList<String> c;
    private String d = getClass().getSimpleName();
    private g e;
    private int f = 0;
    private int g = 0;
    private int h = 0;

    public c(g gVar, Context context) {
        this.e = gVar;
        this.a = DJIUpgradeP4Service.j();
    }

    public void a(a$a dji_dbox_upgrade_p4_c_a_a) {
        d();
        this.a.a(dji.dbox.upgrade.p4.a.a.u.cfgModel.b, this.c, dji_dbox_upgrade_p4_c_a_a);
    }

    private void d() {
        dji.dbox.upgrade.p4.a.a.d();
        this.c = new ArrayList();
        Iterator it = dji.dbox.upgrade.p4.a.a.u.cfgModel.h.iterator();
        while (it.hasNext()) {
            DJIUpCfgModel.a aVar = (DJIUpCfgModel.a) it.next();
            if (dji.dbox.upgrade.p4.a.a.x.contains(aVar.i)) {
                this.c.add(aVar.c);
            }
        }
    }

    private void a(ArrayList<DJIUpCfgModel.a> arrayList) {
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            int i2;
            DJIUpCfgModel.a aVar = (DJIUpCfgModel.a) arrayList.get(i);
            if (dji.dbox.upgrade.p4.a.a.x.contains(aVar.i)) {
                i2 = i;
                i = size;
            } else {
                arrayList.remove(aVar);
                i2 = i - 1;
                i = size - 1;
            }
            size = i;
            i = i2 + 1;
        }
    }

    protected void a(final DJIUpCfgModel dJIUpCfgModel) {
        e();
        if (dJIUpCfgModel.h.size() > 0) {
            int size = dJIUpCfgModel.h.size();
            this.f = size;
            this.g = size;
            Iterator it = dJIUpCfgModel.h.iterator();
            while (it.hasNext()) {
                final DJIUpCfgModel.a aVar = (DJIUpCfgModel.a) it.next();
                try {
                    this.a.a(new dji.thirdparty.afinal.f.a<String>(this) {
                        final /* synthetic */ c c;

                        public void a(boolean z) {
                        }

                        public void a(long j, long j2) {
                        }

                        public void a(String str) {
                            DJIUpDownPathModel dJIUpDownPathModel = (DJIUpDownPathModel) h.b(str, DJIUpDownPathModel.class);
                            if (dJIUpDownPathModel == null) {
                                dji.dbox.upgrade.p4.a.a.b(this.c.d, "getDownPath toBean Failed " + str);
                                this.c.e.b("");
                                this.c.a();
                                return;
                            }
                            if (dJIUpDownPathModel.code == 0) {
                                this.c.g = this.c.g - 1;
                                aVar.d = dJIUpDownPathModel.url;
                            } else {
                                dji.dbox.upgrade.p4.a.a.b(this.c.d, "getDownPath code " + dJIUpDownPathModel.code);
                                this.c.e.b("");
                                this.c.a();
                            }
                            if (this.c.g == 0) {
                                dji.dbox.upgrade.p4.a.a.b(this.c.d, "getDownPath over");
                                this.c.b(dJIUpCfgModel);
                            }
                        }

                        public void a(Throwable th, int i, String str) {
                            dji.dbox.upgrade.p4.a.a.b(this.c.d, "getDownPath onFailure " + str);
                            this.c.a();
                            this.c.e.b("");
                        }
                    }, dJIUpCfgModel.b, aVar.b, aVar.a);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    this.e.b("");
                    return;
                }
            }
            return;
        }
        this.e.b("");
        dji.dbox.upgrade.p4.a.a.b(this.d, "DJIUpCfgModel modules size 0");
    }

    private void b(final DJIUpCfgModel dJIUpCfgModel) {
        this.g = this.f;
        this.b = new ArrayList(this.f);
        Iterator it = dJIUpCfgModel.h.iterator();
        while (it.hasNext()) {
            final DJIUpCfgModel.a aVar = (DJIUpCfgModel.a) it.next();
            dji.thirdparty.afinal.f.a anonymousClass2 = new dji.thirdparty.afinal.f.a<File>(this) {
                final /* synthetic */ c c;
                private long d = 0;

                public void a(boolean z) {
                    this.d = 0;
                }

                public void a(long j, long j2) {
                    this.c.h = (int) (((long) this.c.h) + (j2 - this.d));
                    this.c.e.a((int) ((((float) this.c.h) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) / ((float) dJIUpCfgModel.g)));
                    this.d = j2;
                }

                public void a(File file) {
                    if (UpgradeVerify.native_verifyFile(aVar.a, file.getAbsolutePath())) {
                        this.c.g = this.c.g - 1;
                    } else {
                        dji.dbox.upgrade.p4.a.a.b(this.c.d, "verify image " + aVar.a + " 失败 " + file.getName());
                        this.c.e.b("");
                        file.delete();
                        this.c.a();
                    }
                    if (this.c.g == 0) {
                        this.c.e.k();
                    }
                }

                public void a(Throwable th, int i, String str) {
                    dji.dbox.upgrade.p4.a.a.b(this.c.d, "downloadImage onFailure " + str);
                    this.c.a();
                    this.c.e.b("");
                }
            };
            try {
                if (aVar.d == null) {
                    this.b.add(this.a.a(aVar.c, anonymousClass2, dJIUpCfgModel.b, aVar.b, aVar.a, (long) aVar.h));
                } else {
                    this.b.add(this.a.a(aVar.d, aVar.c, dJIUpCfgModel.b, (long) aVar.h, anonymousClass2));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                a();
                this.e.b("");
                return;
            }
        }
    }

    protected void a() {
        if (this.b != null) {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                dji.thirdparty.afinal.f.c cVar = (dji.thirdparty.afinal.f.c) it.next();
                if (!(cVar == null || cVar.g())) {
                    cVar.h();
                }
            }
        }
        e();
    }

    private void e() {
        this.f = 0;
        this.g = 0;
        this.h = 0;
        if (this.b != null) {
            this.b.clear();
            this.b = null;
        }
    }

    public void b() {
        dji.dbox.upgrade.p4.a.a.b(this.d, "choiceElement code=" + dji.dbox.upgrade.p4.a.a.u.product_version);
        f();
        a(dji.dbox.upgrade.p4.a.a.u.cfgModel);
    }

    private void f() {
    }

    public String c() {
        return this.a.a();
    }
}
