package dji.internal.version;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.dji.frame.c.b;
import com.dji.frame.c.h;
import dji.log.DJILog;
import dji.log.DJILogHelper;
import dji.midware.natives.SDKRelativeJNI;
import dji.midware.util.i;
import dji.thirdparty.afinal.c;
import java.lang.reflect.InvocationTargetException;

public class DJIRemoteVersionInfo extends BroadcastReceiver {
    public static final String a = SDKRelativeJNI.native_getUrlForDate();
    public static final String[] b = new String[]{SDKRelativeJNI.native_getUpgradeUrls0(), SDKRelativeJNI.native_getUpgradeUrls1(), SDKRelativeJNI.native_getUpgradeUrls2()};
    private static final String c = "DJIRemoteVersionInfo";
    private static final String d = SDKRelativeJNI.native_getUrlForBr();
    private static final String e = SDKRelativeJNI.native_getUrlForBrTest();
    private static final String f = "data";
    private static final String g = "list";
    private static final String h = "br_list";
    private static boolean i = false;
    private String j;
    private String k;
    private DJIModelUpgradePackList l;
    private DJIModelUpgradePackList m;
    private c n;
    private boolean o;
    private String p;
    private String q;
    private String r;

    private static final class a {
        private static final DJIRemoteVersionInfo a = new DJIRemoteVersionInfo();

        private a() {
        }
    }

    public static DJIRemoteVersionInfo getInstance() {
        return a.a;
    }

    private DJIRemoteVersionInfo() {
        this.l = null;
        this.m = null;
        this.o = false;
    }

    public void a(Context context) {
        a("UpgradeConfigInfo init");
        i = b.c(context);
        this.j = b[0];
        if (i) {
            this.k = e;
        } else {
            this.k = d;
        }
        if (context.getExternalCacheDir() == null) {
            context.getCacheDir();
        }
        this.l = h();
        this.m = i();
        this.n = com.dji.frame.c.c.b(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this, intentFilter);
        f();
    }

    public void b(Context context) {
        try {
            context.unregisterReceiver(this);
        } catch (Exception e) {
            DJILog.e(c, "Receiver not existed!");
        }
    }

    public DJIModelUpgradePackList a() {
        return this.l;
    }

    public DJIModelUpgradePackList b() {
        return this.m;
    }

    public void onReceive(Context context, Intent intent) {
        a("UpgradeConfigInfo onReceive");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
        if ((networkInfo != null && networkInfo.isConnected()) || (networkInfo2 != null && networkInfo2.isConnected())) {
            f();
        }
    }

    private void f() {
        a("UpgradeConfigInfo getDateFile");
        if (!this.o) {
            this.o = true;
            this.n.a(a, new dji.thirdparty.afinal.f.a<String>(this) {
                final /* synthetic */ DJIRemoteVersionInfo a;

                {
                    this.a = r1;
                }

                public void a(String str) {
                    DJIModelUpgradeDate a = this.a.g();
                    DJIModelUpgradeDate dJIModelUpgradeDate = (DJIModelUpgradeDate) h.b(str, DJIModelUpgradeDate.class);
                    if (DJIRemoteVersionInfo.i || a == null || dJIModelUpgradeDate == null || dJIModelUpgradeDate.data > a.data) {
                        this.a.p = str;
                        this.a.c();
                        return;
                    }
                    this.a.o = false;
                }

                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(Throwable th, int i, String str) {
                    DJILogHelper.getInstance().LOGE("", "拉取date失败，错误原因：" + str);
                    this.a.o = false;
                }
            });
        }
    }

    protected void c() {
        this.n.a(this.j, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ DJIRemoteVersionInfo a;

            {
                this.a = r1;
            }

            public void a(String str) {
                this.a.q = str;
                this.a.d();
            }

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(Throwable th, int i, String str) {
                DJILogHelper.getInstance().LOGE("", "拉取升级列表失败：" + str);
                this.a.o = false;
            }
        });
    }

    protected void d() {
        this.n.a(this.k, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ DJIRemoteVersionInfo a;

            {
                this.a = r1;
            }

            public void a(String str) {
                this.a.r = str;
                this.a.a("data", this.a.p);
                this.a.a(DJIRemoteVersionInfo.g, this.a.q);
                this.a.a(DJIRemoteVersionInfo.h, this.a.r);
                this.a.l = (DJIModelUpgradePackList) h.b(this.a.q, DJIModelUpgradePackList.class);
                this.a.m = (DJIModelUpgradePackList) h.b(this.a.r, DJIModelUpgradePackList.class);
                dji.thirdparty.a.c.a().e(this.a);
                this.a.a("UpgradeConfigInfo getFile for net success");
            }

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(Throwable th, int i, String str) {
                DJILogHelper.getInstance().LOGE("", "拉取date失败，错误原因：" + str);
                this.a.o = false;
            }
        });
    }

    private DJIModelUpgradeDate g() {
        String b = b("data");
        if (b != "") {
            return (DJIModelUpgradeDate) h.b(b, DJIModelUpgradeDate.class);
        }
        return null;
    }

    private DJIModelUpgradePackList h() {
        String b = b(g);
        if (b != "") {
            return (DJIModelUpgradePackList) h.b(b, DJIModelUpgradePackList.class);
        }
        return null;
    }

    private DJIModelUpgradePackList i() {
        String b = b(h);
        if (b != "") {
            return (DJIModelUpgradePackList) h.b(b, DJIModelUpgradePackList.class);
        }
        return null;
    }

    private void a(String str) {
        DJILogHelper.getInstance().LOGD(c, str);
    }

    private void a(String str, String str2) {
        try {
            i.a(dji.sdksharedlib.e.c.a().getApplicationContext(), "DJIRemoteVersionInfo_" + str, str2);
        } catch (ClassNotFoundException e) {
            DJILog.d(c, "saveToLocalDJIDeviceVersionList error", true, true);
        } catch (NoSuchMethodException e2) {
            DJILog.d(c, "saveToLocalDJIDeviceVersionList error", true, true);
        } catch (InvocationTargetException e3) {
            DJILog.d(c, "saveToLocalDJIDeviceVersionList error", true, true);
        } catch (IllegalAccessException e4) {
            DJILog.d(c, "saveToLocalDJIDeviceVersionList error", true, true);
        }
    }

    private String b(String str) {
        String str2 = null;
        try {
            str2 = i.b(dji.sdksharedlib.e.c.a().getApplicationContext(), "DJIRemoteVersionInfo_" + str, null);
        } catch (ClassNotFoundException e) {
            DJILog.d(c, "saveToLocalDJIDeviceVersionList error", true, true);
        } catch (NoSuchMethodException e2) {
            DJILog.d(c, "saveToLocalDJIDeviceVersionList error", true, true);
        } catch (InvocationTargetException e3) {
            DJILog.d(c, "saveToLocalDJIDeviceVersionList error", true, true);
        } catch (IllegalAccessException e4) {
            DJILog.d(c, "saveToLocalDJIDeviceVersionList error", true, true);
        }
        return str2;
    }
}
