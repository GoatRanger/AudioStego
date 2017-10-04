package dji.internal.version.a;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import dji.common.error.DJIError;
import dji.internal.version.DJIModelUpgradePackList;
import dji.internal.version.DJIModelUpgradePackList.DJIUpgradePack;
import dji.internal.version.DJIVersionBaseComponent;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetCfgFile;
import dji.midware.data.model.P3.DataCommonGetCfgFile.DJIUpgradeFileType;
import dji.midware.e.d;
import dji.midware.natives.UpgradeVerify;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

public class r extends DJIVersionBaseComponent {
    private static final String h = "DJIVersionP4Component";
    byte[] a = new byte[1024];
    int b = 0;
    long c = -1;
    long d = 0;
    long e = 0;
    File f = null;
    DataCommonGetCfgFile g = DataCommonGetCfgFile.getInstance();
    private String i = null;
    private FileOutputStream j = null;
    private d k = new d(this) {
        final /* synthetic */ r a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            if (this.a.c == -1) {
                this.a.c = this.a.g.getRelLength();
            }
            this.a.b = this.a.g.getBuffer(this.a.a);
            r rVar = this.a;
            rVar.e += (long) this.a.b;
            this.a.d = this.a.g.getRemainLength();
            try {
                this.a.i();
                if (this.a.d > 0) {
                    this.a.j();
                } else if (this.a.d == 0) {
                    String replace = this.a.i.replace(".cfg.sig", "_verify.xml");
                    boolean native_verifyCfg = UpgradeVerify.native_verifyCfg(this.a.i, replace);
                    if (native_verifyCfg) {
                        try {
                            this.a.a(new File(replace));
                            Log.d(r.h, "Success" + this.a.l);
                            c.a().e(this.a);
                            return;
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    Log.d(r.h, "getCfgCallBack native_verifyCfg=" + native_verifyCfg);
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        public void onFailure(a aVar) {
            Log.d(r.h, "Fail" + DJIError.getDJIError(aVar).getDescription());
        }
    };
    private String l = "";
    private boolean m = false;

    protected String[] a() {
        return null;
    }

    protected ArrayList<DJIUpgradePack> a(DJIModelUpgradePackList dJIModelUpgradePackList) {
        return null;
    }

    public void a(Context context) {
        this.f = new File(Environment.getExternalStorageDirectory().getPath() + "/DJI/" + context.getApplicationContext().getPackageName() + "/deviceCfg.xml");
        this.i = Environment.getExternalStorageDirectory().getPath() + "/DJI/" + context.getApplicationContext().getPackageName() + "/deviceCfg.xml";
        Log.d(h, this.i);
        j();
    }

    public void c() {
    }

    public String d() {
        return this.l;
    }

    protected String a(DJIUpgradePack dJIUpgradePack) {
        return this.l;
    }

    protected String b() {
        return r.class.getSimpleName();
    }

    protected void i() throws IOException {
        if (this.d == 0) {
            if (this.j != null) {
                this.j.write(this.a, 0, this.b);
                this.j.flush();
                this.j.close();
                this.j = null;
            }
        } else if (this.d >= 0) {
            if (this.j == null) {
                if (this.f.exists()) {
                    this.f.delete();
                }
                this.f.createNewFile();
                this.j = new FileOutputStream(this.f);
            }
            this.j.write(this.a, 0, this.b);
            this.j.flush();
        }
    }

    private void j() {
        DataCommonGetCfgFile.getInstance().setReceiveType(DeviceType.DM368).setReceiveId(1).setType(DJIUpgradeFileType.CFG).setLength(this.c).setOffset(this.e).start(this.k);
    }

    private void a(File file) throws FileNotFoundException {
        InputStream fileInputStream = new FileInputStream(file);
        XmlPullParser newPullParser = Xml.newPullParser();
        try {
            newPullParser.setInput(fileInputStream, "UTF-8");
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                switch (eventType) {
                    case 0:
                        Log.d(h, "P4 Get version");
                        break;
                    case 2:
                        String name = newPullParser.getName();
                        if (!name.equalsIgnoreCase("device")) {
                            if (this.m) {
                                if (!name.equalsIgnoreCase("firmware")) {
                                    if (!name.equalsIgnoreCase("release")) {
                                        break;
                                    }
                                    this.l = newPullParser.getAttributeValue(null, "version");
                                    break;
                                }
                                Log.d(h, "Get firmware");
                                break;
                            }
                            break;
                        }
                        Log.d(h, "Get device type: " + newPullParser.getAttributeValue(null, "id"));
                        this.m = true;
                        break;
                    default:
                        break;
                }
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
