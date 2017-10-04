package dji.pilot.flyunlimit;

import android.content.Context;
import android.os.Handler;
import com.dji.frame.c.l;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import dji.gs.utils.a;
import dji.log.DJILogHelper;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.forbid.FlyForbidElement;
import dji.midware.data.forbid.NFZLogUtil;
import dji.midware.data.model.P3.DataFlycEnableUnlimitAreas;
import dji.midware.data.model.P3.DataFlycGetPushUnlimitState;
import dji.midware.data.model.P3.DataFlycGetPushUnlimitState.UnlimitArea;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.flyunlimit.b.c;
import dji.pilot.flyunlimit.b.e;
import dji.pilot.flyunlimit.jsonbean.DJIFlyUnlimitArea;
import dji.pilot.fpv.control.k;
import dji.pilot.fpv.d.c$p;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.publics.objects.g;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import java.util.List;
import java.util.Locale;

public class b {
    public static final String a = "tcbRrxEPyPTzbZqPnH2kCfthvfj3Rpfz";
    public static final String b = "United States";
    public static final String c = "https://cdn.airmap.io/airmap.js/1.0.3/verify.html";
    public static final String d = "https://cdn.airmap.io";
    private static b e = null;
    private static final String o = "https://api.airmap.io/data/v1/error-report";
    private static String r = "JqY9CmQLfE4xnXGfGKHDwLehoEugbKGV";
    private List<DJIFlyUnlimitArea> f = null;
    private Context g = null;
    private k h = null;
    private Handler i = new Handler();
    private int j = 0;
    private String k;
    private String l;
    private String m;
    private String n = "";
    private c p = null;
    private boolean q;

    private b(Context context) {
        this.g = context.getApplicationContext();
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
    }

    public static b getInstance(Context context) {
        synchronized (b.class) {
            if (e == null) {
                e = new b(context);
            }
        }
        return e;
    }

    public void a(k kVar) {
        this.h = kVar;
    }

    public List<DJIFlyUnlimitArea> a() {
        return this.f;
    }

    public void a(c cVar) {
        if (!l.a(f.getInstance().n())) {
            com.dji.frame.c.c.b(this.g).a(String.format("%s?token=%s", new Object[]{dji.pilot.flyforbid.b.e(), r0}), new 1(this, cVar));
        } else if (cVar != null) {
            cVar.a(false);
        }
    }

    public void a(int i, c cVar) {
        String n = f.getInstance().n();
        if (this.f != null && i < this.f.size() && !l.a(n)) {
            String str = ((DJIFlyUnlimitArea) this.f.get(i)).key;
            if (!l.a(str)) {
                a(str, n, i, cVar);
            } else if (cVar != null) {
                cVar.a(false);
            }
        } else if (cVar != null) {
            cVar.a(false);
        }
    }

    private void a(String str, String str2, int i, c cVar) {
        com.dji.frame.c.c.b(this.g).a(String.format("%s?token=%s&license_key=%s", new Object[]{dji.pilot.flyforbid.b.f(), str2, str}), new 2(this, str, i, cVar));
    }

    public int b() {
        return this.j;
    }

    public boolean c() {
        DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
        return a.a(instance.getLatitude(), instance.getLongitude());
    }

    public void a(e eVar, Context context) {
        DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
        instance.getLatitude();
        instance.getLongitude();
        a(g.b(this.g, dji.pilot.flyforbid.a.a, "US"), "", eVar, context);
    }

    public String d() {
        return this.n;
    }

    private void a(String str, String str2, e eVar, Context context) {
        dji.thirdparty.afinal.c b = com.dji.frame.c.c.b(this.g);
        String j = f.getInstance().j();
        String format = String.format(Locale.getDefault(), "%d", new Object[]{Long.valueOf(System.currentTimeMillis())});
        String n = f.getInstance().n();
        String string = this.g.getString(R.string.versionname);
        double latitude = DataOsdGetPushCommon.getInstance().getLatitude();
        String c = dji.pilot.a.a.c(j + str + format + n + "android" + string + latitude + DataOsdGetPushCommon.getInstance().getLongitude(), a);
        j = String.format(Locale.getDefault(), "%s&account=%s&country=%s&time=%s&token=%s&os=android&appVersion=%s&signature=%s&lat=%s&lng=%s", new Object[]{dji.pilot.flyforbid.b.g(), j, str, format, n, string, c, Double.valueOf(latitude), Double.valueOf(r12)}).replace(' ', '+');
        DJILogHelper.getInstance().LOGD("", j, true, true);
        b.a(j, new 3(this, eVar, str, str2));
    }

    public void a(e eVar) {
        DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
        double latitude = instance.getLatitude();
        double longitude = instance.getLongitude();
        List removeItemFromCheckResult = DJIFlyForbidController.getInstance().removeItemFromCheckResult(latitude, longitude);
        if (removeItemFromCheckResult.isEmpty()) {
            dji.pilot.fpv.d.e.c(c$p.b);
            this.j = 17;
            if (eVar != null) {
                eVar.a("unlimit area fail!");
                return;
            }
            return;
        }
        a(removeItemFromCheckResult, latitude, longitude, eVar);
    }

    private void a(List<FlyForbidElement> list, double d, double d2, e eVar) {
        String j = f.getInstance().j();
        String format = String.format(Locale.getDefault(), "%d", new Object[]{Long.valueOf(System.currentTimeMillis())});
        String n = f.getInstance().n();
        String string = this.g.getString(R.string.versionname);
        String str = "unknown";
        String str2 = "unknown";
        String str3 = "";
        for (FlyForbidElement flyForbidElement : list) {
            str3 = str3 + flyForbidElement.area_id + ",";
        }
        String substring = str3.substring(0, str3.length() - 1);
        str3 = DJIFlyForbidController.getInstance().getDataSource();
        String c = dji.pilot.a.a.c(j + d + d2 + str + str2 + str3 + substring + format + n + "android" + string + DJIGlobalService.f, a);
        substring = String.format("%s&account=%s&lat=%s&lng=%s&country=%s&city=%s&areas_type=%s&areas_id=%s&time=%s&token=%s&os=%s&appVersion=%s&sn=%s&signature=%s", new Object[]{dji.pilot.flyforbid.b.i(), j, Double.valueOf(d), Double.valueOf(d2), str, str2, str3, substring, format, n, "android", string, r12, c});
        dji.thirdparty.afinal.c b = com.dji.frame.c.c.b(this.g);
        NFZLogUtil.LOGD(substring);
        b.a(substring, new 4(this, eVar, list));
    }

    public void a(double d, double d2, String str, int i, dji.pilot.flyunlimit.b.g gVar) {
        String str2 = "";
        String str3 = "unknown";
        String str4 = "";
        if (f.getInstance().c()) {
            str2 = f.getInstance().j();
            str3 = f.getInstance().m();
            str4 = f.getInstance().n();
        }
        String dataSource = DJIFlyForbidController.getInstance().getDataSource();
        String str5 = "";
        String str6 = "android";
        String string = this.g.getString(R.string.versionname);
        String str7 = "1.1";
        String c = dji.pilot.a.a.c(str2 + str3 + str2 + str + dataSource + i + str5 + d + d2 + str4 + str6 + string + str7, a);
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        bVar.a("account", str2);
        bVar.a("name", str3);
        bVar.a("email", str2);
        bVar.a("message", str);
        bVar.a("type", dataSource);
        bVar.a("area_id", "" + i);
        bVar.a("area_name", str5);
        bVar.a("latitude", "" + d);
        bVar.a("longitude", "" + d2);
        bVar.a("token", str4);
        bVar.a("os", str6);
        bVar.a("appVersion", string);
        bVar.a("version", str7);
        bVar.a(d.L, c);
        com.dji.frame.c.c.b(this.g).b(dji.pilot.flyforbid.b.j(), bVar, new 5(this, gVar));
        dji.thirdparty.afinal.c cVar = new dji.thirdparty.afinal.c();
        cVar.a("X-API-Key", r);
        dji.thirdparty.afinal.f.b bVar2 = new dji.thirdparty.afinal.f.b();
        bVar2.a("message", str);
        bVar2.a("email", str2);
        bVar2.a("name", str3);
        bVar2.a("object_id", "" + i);
        bVar2.a("latitude", "" + d);
        bVar2.a("longitude", "" + d2);
        cVar.b(o, bVar2, new 6(this));
    }

    public void a(dji.pilot.flyunlimit.b.f fVar) {
        if (f.getInstance().c()) {
            String j = f.getInstance().j();
            long currentTimeMillis = System.currentTimeMillis();
            String n = f.getInstance().n();
            String str = "android";
            String string = this.g.getString(R.string.versionname);
            String str2 = "1.1";
            String c = dji.pilot.a.a.c(j + 1 + 30 + currentTimeMillis + n + str + string + str2, a);
            dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
            bVar.a("account", j);
            bVar.a(ParamKey.PAGE, "" + 1);
            bVar.a(dji.pilot.college.b.b.l, "" + 30);
            bVar.a(n.ax, "" + currentTimeMillis);
            bVar.a("token", n);
            bVar.a("os", str);
            bVar.a("appVersion", string);
            bVar.a("version", str2);
            bVar.a(d.L, c);
            com.dji.frame.c.c.b(this.g).b(dji.pilot.flyforbid.b.k(), bVar, new 7(this, fVar));
            return;
        }
        NFZLogUtil.LOGD("getLicenseUnlockList app not log");
    }

    public void b(c cVar) {
        this.p = cVar;
    }

    public void onEventMainThread(DataFlycGetPushUnlimitState dataFlycGetPushUnlimitState) {
        int unlimitAreasEnabled = dataFlycGetPushUnlimitState.getUnlimitAreasEnabled();
        int unlimitAreasSize = dataFlycGetPushUnlimitState.getUnlimitAreasSize();
        if (this.p != null) {
            this.p.a(unlimitAreasEnabled == 1);
        }
        if (this.h == null) {
            return;
        }
        if (unlimitAreasSize <= 0 || unlimitAreasEnabled != 1) {
            this.h.l().G();
            this.h.l().I();
            return;
        }
        for (UnlimitArea unlimitArea : dataFlycGetPushUnlimitState.getUnlimitAreasList()) {
            this.h.l().c(a.a(new dji.gs.e.b(unlimitArea.lat, unlimitArea.lng)), unlimitArea.radius);
        }
        this.h.l().H();
    }

    public void a(String str, dji.midware.e.d dVar) {
        new 8(this, str, dVar).start();
    }

    public void a(boolean z, dji.midware.e.d dVar) {
        DataFlycEnableUnlimitAreas.getInstance().a(z).start(dVar);
    }

    public static String e() {
        return r;
    }

    public static void a(String str) {
        r = str;
    }
}
