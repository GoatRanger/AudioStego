package dji.pilot.upgrade;

import android.content.Context;
import android.os.Handler;
import com.dji.frame.c.f;
import com.dji.frame.c.h;
import com.here.odnp.config.OdnpConfigStatic;
import com.here.odnp.debug.DebugFile;
import dji.midware.util.b;
import dji.pilot.publics.control.a;
import dji.pilot.publics.model.DJIUpgradePackListModel;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class UpgradeBaseComponent {
    private static final String b = "UpgradeBaseComponent";
    private static final boolean c = true;
    protected boolean a = false;
    private String[] d = null;
    private String e = null;
    private ArrayList<FirmwareVersion> f = null;
    private String g = null;
    private Handler h = null;
    private Runnable i = null;
    private boolean j = false;

    public static class FirmwareVersionList {
        public ArrayList<FirmwareVersion> list;
    }

    protected abstract String a(DJIUpgradePack dJIUpgradePack);

    protected abstract ArrayList<DJIUpgradePack> a(DJIUpgradePackListModel dJIUpgradePackListModel);

    protected abstract String[] a();

    protected abstract boolean b();

    protected abstract String c();

    public void a(Context context) {
        d.a(b, "~~~~~" + c() + " init " + getClass().getSimpleName(), true);
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir == null) {
            externalCacheDir = context.getCacheDir();
        }
        this.e = externalCacheDir.getAbsolutePath() + "/upgrade_component_" + c() + ".json";
        this.d = a();
        c.a().a(this);
        j();
        this.h = new Handler(b.b());
        this.i = new Runnable(this) {
            final /* synthetic */ UpgradeBaseComponent a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.i();
            }
        };
        h();
    }

    public void d() {
        this.h.removeCallbacks(this.i);
        this.h = null;
        this.i = null;
        c.a().d(this);
    }

    public String e() {
        return this.g;
    }

    public String f() {
        ArrayList k = k();
        if (k == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("------------------------------\n");
        Iterator it = k.iterator();
        while (it.hasNext()) {
            stringBuilder.append(((FirmwareVersion) it.next()).toString() + "\n");
        }
        stringBuilder.append("------------------------------\n");
        return stringBuilder.toString();
    }

    public void g() {
        i();
    }

    protected void h() {
        this.h.removeCallbacks(this.i);
        this.h.postDelayed(this.i, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
    }

    protected void i() {
        d.a(b, "~~~~~" + c() + " startGetVersion 1", true);
        if (!this.j) {
            d.a("startGetVersion 2");
            if (b()) {
                d.a(b, "~~~~~" + c() + " startGetVersion 3", true);
                this.j = true;
                c cVar = new c(this.d, new c.b(this) {
                    final /* synthetic */ UpgradeBaseComponent a;

                    {
                        this.a = r1;
                    }

                    public void onResultCallBack(boolean z, ArrayList<FirmwareVersion> arrayList) {
                        d.a(UpgradeBaseComponent.b, "~~~~~" + this.a.c() + " onResultCallBack:" + z, true);
                        this.a.j = false;
                        this.a.f = arrayList;
                        this.a.a(this.a.f);
                        d.a(UpgradeBaseComponent.b, this.a.f(), true);
                        this.a.j();
                    }
                });
            }
        }
    }

    public void onEventBackgroundThread(UpgradeConfigInfo upgradeConfigInfo) {
        j();
    }

    private void j() {
        String str = null;
        ArrayList k = k();
        DJIUpgradePackListModel a = UpgradeConfigInfo.getInstance().a();
        DJIUpgradePackListModel b = UpgradeConfigInfo.getInstance().b();
        d.a(b, c() + ": curList = " + k, true);
        d.a(b, c() + ": releaseModel = " + a, true);
        d.a(b, c() + ": updateComponentVersion brModel = " + a, true);
        if (k != null && a != null && b != null) {
            String str2;
            ArrayList a2 = a(a);
            ArrayList a3 = a(b);
            d.a(b, c() + ": updateComponentVersion 2", true);
            if (a2 == null || a2.size() <= 0) {
                str2 = null;
            } else {
                int size = a2.size() - 1;
                str2 = null;
                while (size >= 0) {
                    String str3;
                    String a4 = a((DJIUpgradePack) a2.get(size));
                    if (a4 != null) {
                        if (((DJIUpgradePack) a2.get(size)).android_ignore != 1) {
                            d.a(b, c() + ": releaseList version:" + a4, true);
                            int a5 = a((DJIUpgradePack) a2.get(size), k);
                            d.a(b, c() + ": updateComponentVersion 2 index : " + size + "; ret = " + a5, true);
                            if (a5 != 0) {
                                if (a5 != -1) {
                                    if (a5 == 1) {
                                        break;
                                    }
                                    str3 = str2;
                                } else {
                                    str3 = a4;
                                }
                            } else {
                                str = a4;
                                break;
                            }
                        }
                        str3 = str2;
                    } else {
                        str3 = str2;
                    }
                    size--;
                    str2 = str3;
                }
            }
            d.a(b, "~~~~~" + c() + " updateComponentVersion 3", true);
            if (str == null && a3 != null && a3.size() > 0) {
                for (int size2 = a3.size() - 1; size2 >= 0; size2--) {
                    if (a((DJIUpgradePack) a3.get(size2), k) == 0) {
                        str = a((DJIUpgradePack) a3.get(size2));
                        break;
                    }
                }
            }
            d.a(b, "~~~~~" + c() + " updateComponentVersion 4" + str + "," + str2, true);
            this.g = str;
            if (this.g == null) {
                if (str2 == null) {
                    this.g = "N/A";
                } else {
                    this.g = str2 + "+";
                }
            }
            c.a().e(this);
            if (this.a && a2 != null) {
                CharSequence a6 = a((DJIUpgradePack) a2.get(0));
                if (a6 != null && this.g != null && !this.g.contains(a6)) {
                    a.getInstance().n();
                }
            }
        }
    }

    private int a(DJIUpgradePack dJIUpgradePack, ArrayList<FirmwareVersion> arrayList) {
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            FirmwareVersion firmwareVersion = (FirmwareVersion) it.next();
            if (!(firmwareVersion.version == Long.MIN_VALUE || firmwareVersion.version == 0)) {
                if (firmwareVersion.versionStr == null || !firmwareVersion.versionStr.equals("255.255.255.254")) {
                    try {
                        String str = (String) DJIUpgradePack.class.getField("m" + firmwareVersion.firmware).get(dJIUpgradePack);
                        if (str != null) {
                            FirmwareVersion firmwareVersion2 = new FirmwareVersion(firmwareVersion.firmware, str.split(com.alipay.sdk.h.a.b)[0]);
                            if (firmwareVersion2.version != firmwareVersion.version) {
                                d.a(b, String.format("version:%s, firmware:%s, f %s, tmp %s", new Object[]{"" + a(dJIUpgradePack), firmwareVersion.firmware, "" + firmwareVersion.versionStr, "" + str}), true);
                            }
                            if (firmwareVersion2.version > firmwareVersion.version) {
                                d.a(b, "===== keynote " + String.format("version:%s, firmware:%s, f %s, tmp %s", new Object[]{"" + a(dJIUpgradePack), firmwareVersion.firmware, "" + firmwareVersion.versionStr, "" + str}) + "(tmp" + firmwareVersion2.version + "), (f" + firmwareVersion.version + ") ", true);
                                return 1;
                            }
                            int i2;
                            if (firmwareVersion2.version < firmwareVersion.version) {
                                i2 = -1;
                            } else {
                                i2 = i;
                            }
                            i = i2;
                        } else if (firmwareVersion.firmware.equals("1101")) {
                            return -1;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        d.a(b, "===== keynote crash" + a(e), true);
                        return -1;
                    }
                }
            }
        }
        return i;
    }

    public static String a(Exception exception) {
        try {
            Writer stringWriter = new StringWriter();
            exception.printStackTrace(new PrintWriter(stringWriter));
            return DebugFile.EOL + stringWriter.toString() + DebugFile.EOL;
        } catch (Exception e) {
            return "bad getErrorInfoFromException";
        }
    }

    private ArrayList<FirmwareVersion> k() {
        FirmwareVersionList firmwareVersionList;
        String a = f.a(new File(this.e));
        if (a != "") {
            firmwareVersionList = (FirmwareVersionList) h.b(a, FirmwareVersionList.class);
        } else {
            firmwareVersionList = null;
        }
        if (firmwareVersionList != null) {
            return firmwareVersionList.list;
        }
        return null;
    }

    private void a(ArrayList<FirmwareVersion> arrayList) {
        FirmwareVersionList firmwareVersionList = new FirmwareVersionList();
        firmwareVersionList.list = arrayList;
        String a = h.a(firmwareVersionList);
        File file = new File(this.e);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        f.a(file, a);
    }
}
