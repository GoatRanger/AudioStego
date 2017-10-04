package dji.pilot.fpv.control.b;

import android.content.Context;
import android.os.Handler;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataFlycActiveStatus;
import dji.midware.data.model.b.a.b;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.pilot.publics.objects.g;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.thirdparty.a.c;
import java.util.HashMap;

public class a {
    private static a a = new a();
    private final String b = "djiaDJIHereMapReportManager";
    private final String c = "here_report_sn";
    private DataFlycActiveStatus d = new DataFlycActiveStatus();
    private Context e;
    private final int f = 2;
    private final int g = 3;
    private final int h = 4;
    private int i = 0;
    private Handler j = new 2(this);
    private final String k = "here_map_sn_report";
    private final String l = n.bc;
    private final String m = "uid";
    private final String n = n.ax;
    private final String o = "map_type";
    private final String p = "build_num";
    private final String q = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;

    public static a getInstance() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    private a() {
    }

    public void a(Context context) {
        DJILogHelper.getInstance().LOGD("djiaDJIHereMapReportManager", "DJIHereMapReportManager init.", true, false);
        this.e = context;
        if (!c.a().c((Object) this)) {
            c.a().a((Object) this);
        }
    }

    public void onEventBackgroundThread(dji.gs.c.e.a aVar) {
        DJILogHelper.getInstance().LOGD("djiaDJIHereMapReportManager", "OnHere init:" + aVar, true, false);
        if (aVar == dji.gs.c.e.a.b) {
            a();
        }
    }

    private void a() {
        if (ServiceManager.getInstance().isRemoteOK()) {
            b();
        } else {
            com.dji.a.a.a("here_map_sn_report", a("sn_null_disconnect"), true);
        }
    }

    private void b() {
        this.d.setVersion(DataFlycActiveStatus.getInstance().getVersion()).setType(b.b).start(new 1(this));
    }

    private HashMap<String, String> a(String str) {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put(n.bc, str);
        hashMap.put("uid", f.getInstance().o());
        hashMap.put(n.ax, System.currentTimeMillis() + "");
        hashMap.put("map_type", DJINetWorkReceiver.a(this.e) ? "online" : "offline");
        Object b = dji.pilot.c.b.b(this.e);
        if (b == null || b.isEmpty()) {
            b = "0";
        }
        hashMap.put("build_num", b);
        return hashMap;
    }

    private boolean b(String str) {
        String trim = g.b(this.e, "here_report_sn", "").trim();
        DJILogHelper.getInstance().LOGI("djiaDJIHereMapReportManager", "Start Report: sentSNStr=" + trim + " sn=" + str, true, false);
        String[] split = trim.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        if (split.length > 0) {
            for (Object equals : split) {
                if (str.equals(equals)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void c(String str) {
        g.a(this.e, "here_report_sn", g.b(this.e, "here_report_sn", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).trim() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str);
    }
}
