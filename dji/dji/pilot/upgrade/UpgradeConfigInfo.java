package dji.pilot.upgrade;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.dji.frame.c.f;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.c.b;
import dji.pilot.publics.model.DJIUpgradeDateModel;
import dji.pilot.publics.model.DJIUpgradePackListModel;
import dji.thirdparty.afinal.c;
import java.io.File;
import java.io.IOException;

public class UpgradeConfigInfo extends BroadcastReceiver {
    private static final String a = "UpgradeConfigInfo";
    private static final boolean b = (dji.pilot.c.a.j != 0);
    private String c;
    private String d;
    private DJIUpgradePackListModel e;
    private DJIUpgradePackListModel f;
    private String g;
    private String h;
    private String i;
    private c j;
    private boolean k;
    private String l;
    private String m;
    private String n;

    private static final class a {
        private static final UpgradeConfigInfo a = new UpgradeConfigInfo();

        private a() {
        }
    }

    public static UpgradeConfigInfo getInstance() {
        return a.a;
    }

    private UpgradeConfigInfo() {
        this.e = null;
        this.f = null;
        this.k = false;
    }

    public void a(Context context) {
        d.a("UpgradeConfigInfo init");
        this.c = b.g[dji.pilot.c.a.j];
        this.d = b.h[dji.pilot.c.a.j];
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir == null) {
            externalCacheDir = context.getCacheDir();
        }
        this.g = externalCacheDir.getAbsolutePath() + "/component_upgrade_date.json";
        this.h = externalCacheDir.getAbsolutePath() + "/component_upgrade_list.json";
        this.i = externalCacheDir.getAbsolutePath() + "/component_upgrade_list_br.json";
        this.e = h();
        this.f = i();
        this.j = com.dji.frame.c.c.b(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this, intentFilter);
        f();
    }

    public void b(Context context) {
        context.unregisterReceiver(this);
    }

    public DJIUpgradePackListModel a() {
        return this.e;
    }

    public DJIUpgradePackListModel b() {
        return this.f;
    }

    public void onReceive(Context context, Intent intent) {
        d.a("UpgradeConfigInfo onReceive");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
        if (((networkInfo != null && networkInfo.isConnected()) || (networkInfo2 != null && networkInfo2.isConnected())) && dji.pilot.publics.control.a.b()) {
            f();
        }
    }

    public void onEventBackgroundThread(ProductType productType) {
        if (dji.pilot.publics.control.a.b()) {
            f();
        }
    }

    private void f() {
        d.a("UpgradeConfigInfo getDateFile");
        if (!this.k) {
            this.k = true;
            this.j.a(b.i, new dji.thirdparty.afinal.f.a<String>(this) {
                final /* synthetic */ UpgradeConfigInfo a;

                {
                    this.a = r1;
                }

                public void a(String str) {
                    DJIUpgradeDateModel a = this.a.g();
                    DJIUpgradeDateModel dJIUpgradeDateModel = (DJIUpgradeDateModel) h.b(str, DJIUpgradeDateModel.class);
                    if (UpgradeConfigInfo.b || a == null || dJIUpgradeDateModel == null || dJIUpgradeDateModel.data > a.data) {
                        this.a.l = str;
                        this.a.c();
                        DJILogHelper.getInstance().LOGD("", "拉取date文件成功0", false, true);
                        return;
                    }
                    this.a.k = false;
                    DJILogHelper.getInstance().LOGD("", "拉取date文件成功1", false, true);
                }

                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(Throwable th, int i, String str) {
                    DJILogHelper.getInstance().LOGE("", "拉取date失败，错误原因：" + str, false, true);
                    this.a.k = false;
                }
            });
        }
    }

    protected void c() {
        this.j.a(this.c, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ UpgradeConfigInfo a;

            {
                this.a = r1;
            }

            public void a(String str) {
                this.a.m = str;
                this.a.d();
                DJILogHelper.getInstance().LOGD("", "拉取升级列表文件成功", false, true);
            }

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(Throwable th, int i, String str) {
                DJILogHelper.getInstance().LOGE("", "拉取升级列表失败：" + str, false, true);
                this.a.k = false;
            }
        });
    }

    protected void d() {
        this.j.a(this.d, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ UpgradeConfigInfo a;

            {
                this.a = r1;
            }

            public void a(String str) {
                DJIUpgradePackListModel dJIUpgradePackListModel;
                this.a.n = str;
                DJIUpgradePackListModel dJIUpgradePackListModel2;
                try {
                    dJIUpgradePackListModel = (DJIUpgradePackListModel) h.b(this.a.m, DJIUpgradePackListModel.class);
                    try {
                        dJIUpgradePackListModel2 = (DJIUpgradePackListModel) h.b(this.a.n, DJIUpgradePackListModel.class);
                    } catch (Exception e) {
                        dJIUpgradePackListModel2 = null;
                        if (dJIUpgradePackListModel != null) {
                        }
                        DJILogHelper.getInstance().LOGE("", "解析失败,有可能是联网但跳转到联网界面了", false, true);
                        this.a.k = false;
                        return;
                    }
                } catch (Exception e2) {
                    dJIUpgradePackListModel = null;
                    dJIUpgradePackListModel2 = null;
                    if (dJIUpgradePackListModel != null) {
                    }
                    DJILogHelper.getInstance().LOGE("", "解析失败,有可能是联网但跳转到联网界面了", false, true);
                    this.a.k = false;
                    return;
                }
                if (dJIUpgradePackListModel != null || r1 == null) {
                    DJILogHelper.getInstance().LOGE("", "解析失败,有可能是联网但跳转到联网界面了", false, true);
                    this.a.k = false;
                    return;
                }
                this.a.a(this.a.g, this.a.l);
                this.a.a(this.a.h, this.a.m);
                this.a.a(this.a.i, this.a.n);
                this.a.e = (DJIUpgradePackListModel) h.b(this.a.m, DJIUpgradePackListModel.class);
                this.a.f = (DJIUpgradePackListModel) h.b(this.a.n, DJIUpgradePackListModel.class);
                dji.thirdparty.a.c.a().e(this.a);
                d.a("UpgradeConfigInfo getFile for net success");
                DJILogHelper.getInstance().LOGD("", "拉取降级列表文件成功", false, true);
            }

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(Throwable th, int i, String str) {
                DJILogHelper.getInstance().LOGE("", "拉取date失败，错误原因：" + str, false, true);
                this.a.k = false;
            }
        });
    }

    private DJIUpgradeDateModel g() {
        String a = f.a(new File(this.g));
        if (a != "") {
            return (DJIUpgradeDateModel) h.b(a, DJIUpgradeDateModel.class);
        }
        return null;
    }

    private DJIUpgradePackListModel h() {
        String a = f.a(new File(this.h));
        if (a != "") {
            return (DJIUpgradePackListModel) h.b(a, DJIUpgradePackListModel.class);
        }
        return null;
    }

    private DJIUpgradePackListModel i() {
        String a = f.a(new File(this.i));
        if (a != "") {
            return (DJIUpgradePackListModel) h.b(a, DJIUpgradePackListModel.class);
        }
        return null;
    }

    private void a(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        f.a(file, str2);
    }
}
