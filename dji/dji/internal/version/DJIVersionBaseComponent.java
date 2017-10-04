package dji.internal.version;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.alipay.sdk.h.a;
import com.dji.frame.c.h;
import com.here.odnp.config.OdnpConfigStatic;
import com.here.odnp.debug.DebugFile;
import dji.internal.version.DJIModelUpgradePackList.DJIUpgradePack;
import dji.internal.version.a.b;
import dji.log.DJILog;
import dji.log.DJILogHelper;
import dji.midware.util.i;
import dji.thirdparty.a.c;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class DJIVersionBaseComponent {
    private static final String a = "DJIVersionBaseComponent";
    private static final boolean b = false;
    private String c = null;
    private ArrayList<DJIDeviceVersion> d = null;
    private String e = null;
    private Handler f = null;
    private Runnable g = null;
    private boolean h = false;

    public static class DJIDeviceVersionList {
        public ArrayList<DJIDeviceVersion> list;
    }

    protected abstract String a(DJIUpgradePack dJIUpgradePack);

    protected abstract ArrayList<DJIUpgradePack> a(DJIModelUpgradePackList dJIModelUpgradePackList);

    protected abstract String[] a();

    protected abstract String b();

    public void a(Context context) {
        getClass().getSimpleName();
        a(b() + " init", false);
        this.c = "upgrade_component_" + b();
        c.a().a(this);
        i();
        this.f = new Handler(Looper.getMainLooper());
        this.g = new Runnable(this) {
            final /* synthetic */ DJIVersionBaseComponent a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.h();
            }
        };
        g();
    }

    public void c() {
        this.f.removeCallbacks(this.g);
        this.f = null;
        this.g = null;
        c.a().d(this);
    }

    public String d() {
        return this.e;
    }

    public String e() {
        ArrayList j = j();
        if (j == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("------------------------------\n");
        Iterator it = j.iterator();
        while (it.hasNext()) {
            stringBuilder.append(((DJIDeviceVersion) it.next()).toString() + "\n");
        }
        stringBuilder.append("------------------------------\n");
        return stringBuilder.toString();
    }

    public void f() {
        h();
    }

    protected void g() {
        this.f.removeCallbacks(this.g);
        this.f.postDelayed(this.g, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
    }

    protected void h() {
        a("~~~~~" + b() + " startGetVersion 1", false);
        if (!this.h) {
            a("startGetVersion 2");
            a("~~~~~" + b() + " startGetVersion 3", false);
            this.h = true;
            a aVar = new a(a(), new b(this) {
                final /* synthetic */ DJIVersionBaseComponent a;

                {
                    this.a = r1;
                }

                public void a(boolean z, ArrayList<DJIDeviceVersion> arrayList) {
                    this.a.a("~~~~~" + this.a.b() + " startGetVersion 4", false);
                    this.a.h = false;
                    this.a.d = arrayList;
                    this.a.a(this.a.d);
                    this.a.a(this.a.e(), false);
                    this.a.i();
                }
            });
        }
    }

    public void onEventBackgroundThread(DJIRemoteVersionInfo dJIRemoteVersionInfo) {
        i();
    }

    private void i() {
        String str = null;
        ArrayList j = j();
        DJIModelUpgradePackList a = DJIRemoteVersionInfo.getInstance().a();
        DJIModelUpgradePackList b = DJIRemoteVersionInfo.getInstance().b();
        a("~~~~~" + b() + " updateComponentVersion releaseModel : " + a, false);
        a("~~~~~" + b() + " updateComponentVersion brModel : " + b, false);
        if (j != null && a != null && b != null) {
            String str2;
            ArrayList a2 = a(a);
            ArrayList a3 = a(b);
            a(b() + " updateComponentVersion 2", false);
            if (a2 != null) {
                a(b() + " updateComponentVersion 2 size : " + a2.size(), false);
            }
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
                            a("~~~~~" + b() + " releaseList version:" + a4, false);
                            int a5 = a((DJIUpgradePack) a2.get(size), j);
                            a("~~~~~" + b() + " updateComponentVersion 2 index : " + size + "; rst = " + a5, false);
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
            a(b() + " updateComponentVersion 3, componentVersion: " + str, false);
            if (str == null && a3 != null && a3.size() > 0) {
                for (int size2 = a3.size() - 1; size2 >= 0; size2--) {
                    if (a((DJIUpgradePack) a3.get(size2), j) == 0) {
                        str = a((DJIUpgradePack) a3.get(size2));
                        break;
                    }
                }
            }
            a(b() + " updateComponentVersion 4" + str + "," + str2, false);
            this.e = str;
            if (this.e == null) {
                if (str2 == null) {
                    this.e = "N/A";
                } else {
                    this.e = str2 + "+";
                }
            }
            c.a().e(this);
        }
    }

    private int a(DJIUpgradePack dJIUpgradePack, ArrayList<DJIDeviceVersion> arrayList) {
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            DJIDeviceVersion dJIDeviceVersion = (DJIDeviceVersion) it.next();
            if (!(dJIDeviceVersion.version == Long.MIN_VALUE || dJIDeviceVersion.version == 0)) {
                try {
                    String str = (String) DJIUpgradePack.class.getField("m" + dJIDeviceVersion.firmware).get(dJIUpgradePack);
                    if (str != null) {
                        DJIDeviceVersion dJIDeviceVersion2 = new DJIDeviceVersion(dJIDeviceVersion.firmware, str.split(a.b)[0]);
                        if (dJIDeviceVersion2.version != dJIDeviceVersion.version) {
                            a(String.format("version:%s, firmware:%s, f %s, tmp %s", new Object[]{"" + a(dJIUpgradePack), dJIDeviceVersion.firmware, "" + dJIDeviceVersion.versionStr, "" + str}), false);
                        }
                        if (dJIDeviceVersion2.version > dJIDeviceVersion.version) {
                            a("===== keynote " + String.format("version:%s, firmware:%s, f %s, tmp %s", new Object[]{"" + a(dJIUpgradePack), dJIDeviceVersion.firmware, "" + dJIDeviceVersion.versionStr, "" + str}), false);
                            return 1;
                        }
                        int i2;
                        if (dJIDeviceVersion2.version < dJIDeviceVersion.version) {
                            i2 = -1;
                        } else {
                            i2 = i;
                        }
                        i = i2;
                    } else {
                        continue;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    a("===== keynote crash" + a(e), false);
                    return 1;
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

    private ArrayList<DJIDeviceVersion> j() {
        String b;
        DJIDeviceVersionList dJIDeviceVersionList;
        try {
            b = b(this.c);
        } catch (ClassNotFoundException e) {
            DJILog.d(a, "saveToLocalDJIDeviceVersionList error", true, true);
            e.printStackTrace();
            b = null;
        } catch (NoSuchMethodException e2) {
            DJILog.d(a, "saveToLocalDJIDeviceVersionList error", true, true);
            e2.printStackTrace();
            b = null;
        } catch (IllegalAccessException e3) {
            DJILog.d(a, "saveToLocalDJIDeviceVersionList error", true, true);
            e3.printStackTrace();
            b = null;
        } catch (InvocationTargetException e4) {
            DJILog.d(a, "saveToLocalDJIDeviceVersionList error", true, true);
            e4.printStackTrace();
            b = null;
        }
        if (b != "") {
            dJIDeviceVersionList = (DJIDeviceVersionList) h.b(b, DJIDeviceVersionList.class);
        } else {
            dJIDeviceVersionList = null;
        }
        if (dJIDeviceVersionList != null) {
            return dJIDeviceVersionList.list;
        }
        return null;
    }

    private void a(ArrayList<DJIDeviceVersion> arrayList) {
        DJIDeviceVersionList dJIDeviceVersionList = new DJIDeviceVersionList();
        dJIDeviceVersionList.list = arrayList;
        try {
            a(this.c, h.a(dJIDeviceVersionList));
        } catch (ClassNotFoundException e) {
            DJILog.d(a, "saveToLocalDJIDeviceVersionList error", true, true);
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            DJILog.d(a, "saveToLocalDJIDeviceVersionList error", true, true);
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            DJILog.d(a, "saveToLocalDJIDeviceVersionList error", true, true);
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            DJILog.d(a, "saveToLocalDJIDeviceVersionList error", true, true);
            e4.printStackTrace();
        }
    }

    private void a(String str) {
        DJILogHelper.getInstance().LOGD(a, str);
    }

    private void a(String str, boolean z) {
        DJILogHelper.getInstance().LOGD(a, str, true, z);
    }

    private void a(String str, String str2) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        i.a(dji.sdksharedlib.e.c.a().getApplicationContext(), "DJIVersionBaseComponent_" + str, str2);
    }

    private String b(String str) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return i.b(dji.sdksharedlib.e.c.a().getApplicationContext(), "DJIVersionBaseComponent_" + str, null);
    }
}
