package dji.dbox.upgrade.p4.d;

import android.content.Context;
import dji.dbox.upgrade.p4.model.DJIUpCfgModel;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.model.P3.DataCommonGetCfgFile;
import dji.midware.data.model.P3.DataCommonGetCfgFile.DJIUpgradeFileType;
import dji.midware.e.d;
import dji.midware.natives.UpgradeVerify;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class b {
    DataCommonGetCfgFile a = new DataCommonGetCfgFile();
    byte[] b = new byte[1024];
    int c = 0;
    long d = -1;
    long e = 0;
    long f = 0;
    private final DeviceType g;
    private final a h;
    private String i = getClass().getSimpleName();
    private DJIUpCfgModel j;
    private String k = "";
    private File l = null;
    private FileOutputStream m = null;
    private int n = 0;
    private d o = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            this.a.n = 0;
            if (this.a.d == -1) {
                this.a.d = this.a.a.getRelLength();
            }
            this.a.c = this.a.a.getBuffer(this.a.b);
            b bVar = this.a;
            bVar.f += (long) this.a.c;
            this.a.e = this.a.a.getRemainLength();
            dji.dbox.upgrade.p4.a.a.b(this.a.i, "getCfgCallBack " + this.a.g + " totalLen=" + this.a.d + " bufferSize=" + this.a.c + " remainLen=" + this.a.e);
            try {
                this.a.d();
                if (this.a.e > 0) {
                    this.a.c();
                } else if (this.a.e == 0) {
                    String replace = this.a.k.replace("deviceCfg", "deviceCfg_verify");
                    dji.dbox.upgrade.p4.a.a.b(this.a.i, this.a.g + " t=" + replace);
                    boolean native_verifyCfg = UpgradeVerify.native_verifyCfg(this.a.k, replace);
                    if (native_verifyCfg) {
                        try {
                            this.a.j = dji.dbox.upgrade.p4.model.a.a.a(new File(replace));
                            this.a.h.a(this.a.j);
                            return;
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    new File(replace).renameTo(new File("/sdcard/DJI/LOG/deviceCfgError-" + this.a.g + ".xml"));
                    dji.dbox.upgrade.p4.a.a.b(this.a.i, "getCfgCallBack " + this.a.g + " native_verifyCfg=" + native_verifyCfg);
                    this.a.h.a();
                }
            } catch (IOException e2) {
                dji.dbox.upgrade.p4.a.a.b(this.a.i, "write error : " + e2);
                e2.printStackTrace();
            }
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            dji.dbox.upgrade.p4.a.a.b(this.a.i, "getCfgCallBack " + this.a.g + " ccode=" + aVar);
            if (aVar != dji.midware.data.config.P3.a.a || this.a.n == 3) {
                this.a.n = 0;
                this.a.j = DJIUpCfgModel.a();
                this.a.h.a(this.a.j);
                return;
            }
            dji.dbox.upgrade.p4.a.a.b(this.a.i, "getCfgCallBack " + this.a.g + " retryTime=" + this.a.n);
            this.a.n = this.a.n + 1;
            this.a.c();
        }
    };

    public interface a {
        void a();

        void a(DJIUpCfgModel dJIUpCfgModel);
    }

    public b(Context context, DeviceType deviceType, a aVar) {
        this.g = deviceType;
        this.h = aVar;
        String str = context.getFilesDir() + dji.dbox.upgrade.p4.a.a.i + DJIUpgradeP4Service.a();
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.k = str + "/deviceCfg-" + deviceType + ".xml";
        this.l = new File(this.k);
    }

    public void a() {
        this.f = 0;
        this.d = -1;
        this.e = 0;
        this.c = 0;
        this.j = null;
    }

    public void b() {
        if (this.m != null) {
            try {
                this.m.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.m = null;
        }
        c();
    }

    public void c() {
        this.a.setReceiveType(this.g).setReceiveId(1).setType(DJIUpgradeFileType.CFG).setLength(this.d).setOffset(this.f).start(this.o);
    }

    protected void d() throws IOException {
        if (this.e == 0) {
            if (this.m != null) {
                this.m.write(this.b, 0, this.c);
                this.m.flush();
                this.m.close();
                this.m = null;
            }
        } else if (this.e < 0) {
            dji.dbox.upgrade.p4.a.a.b(this.i, "getCfgCallBack " + this.g + " writeToLocal faild");
        } else {
            if (this.m == null) {
                if (this.l.exists()) {
                    this.l.delete();
                }
                this.l.createNewFile();
                this.m = new FileOutputStream(this.l);
            }
            this.m.write(this.b, 0, this.c);
            this.m.flush();
        }
    }
}
