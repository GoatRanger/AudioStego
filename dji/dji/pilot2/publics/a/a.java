package dji.pilot2.publics.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.telephony.TelephonyManager;
import com.dji.frame.c.h;
import com.dji.frame.c.l;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.flyunlimit.b;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.afinal.c;
import java.util.ArrayList;
import java.util.List;

public class a {
    private static a a = null;
    private static final String d = "terms_setting_preferences";
    private static final String e = "need_upload";
    private static final String f = "terms_ver";
    private static final String g = "terms_date";
    private static final String h = "show_agreed";
    private static final String i = "users_agreed";
    private Context b = null;
    private SharedPreferences c;
    private boolean j = false;
    private Thread k = new 4(this);

    public enum a {
        show,
        exitApp
    }

    private a(Context context) {
        this.b = context;
        this.c = this.b.getSharedPreferences(d, 4);
        this.k.start();
    }

    public static a getInstance() {
        return a;
    }

    public static a a(Context context) {
        if (a == null) {
            a = new a(context);
        }
        return a;
    }

    public void a(String str, String str2) {
        Editor edit = this.c.edit();
        String n = f.getInstance().n();
        if (!l.a(n)) {
            boolean z;
            List a = h.a(this.c.getString(i, ""), new 1(this));
            List arrayList;
            if (a == null) {
                arrayList = new ArrayList();
            } else {
                arrayList = a;
            }
            if (r1.isEmpty()) {
                z = false;
            } else {
                z = false;
                for (String compareTo : r1) {
                    boolean z2;
                    if (compareTo.compareTo(n) == 0) {
                        z2 = true;
                    } else {
                        z2 = z;
                    }
                    z = z2;
                }
            }
            if (!(z || n == null)) {
                r1.add(n);
                edit.putString(i, h.a((List) r1));
            }
        }
        edit.putString(f, str);
        edit.putString(g, str2);
        edit.putBoolean(e, true);
        edit.putBoolean(h, false);
        edit.apply();
    }

    public boolean a() {
        return this.c.getBoolean(h, true);
    }

    public void b() {
        this.c.edit().putBoolean(h, true).apply();
        this.c.edit().putString(i, "").apply();
    }

    public void a(String str) {
        if (!b(str) && str != null) {
            List a = h.a(this.c.getString(i, ""), new 2(this));
            if (a == null) {
                a = new ArrayList();
            }
            a.add(str);
            this.c.edit().putString(i, h.a(a)).apply();
        }
    }

    public boolean b(String str) {
        List<String> a = h.a(this.c.getString(i, ""), new 3(this));
        if (a == null || a.isEmpty() || str == null) {
            return false;
        }
        for (String compareTo : a) {
            if (compareTo.compareTo(str) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean c() {
        return this.c.getString(i, "").isEmpty();
    }

    public void d() {
        this.c.edit().putBoolean(e, true).apply();
    }

    private void e() {
        String j = f.getInstance().j();
        String string = this.c.getString(f, "");
        String string2 = this.c.getString(g, "");
        String valueOf = String.valueOf(DataOsdGetPushCommon.getInstance().getLatitude());
        String valueOf2 = String.valueOf(DataOsdGetPushCommon.getInstance().getLongitude());
        String str = DJIGlobalService.f;
        String deviceId = ((TelephonyManager) this.b.getSystemService("phone")).getDeviceId();
        String str2 = "";
        String str3 = "";
        String valueOf3 = String.valueOf(System.currentTimeMillis());
        String n = f.getInstance().n();
        String str4 = "android";
        String string3 = this.b.getString(R.string.versionname);
        String str5 = "1.1";
        String c = dji.pilot.a.a.c(j + string + string2 + valueOf + valueOf2 + str + deviceId + str2 + str3 + valueOf3 + n + str4 + string3 + str5, b.a);
        c cVar = new c();
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        bVar.a("account", j);
        bVar.a("termsVersion", string);
        bVar.a("date", string2);
        bVar.a("latitude", valueOf);
        bVar.a("longitude", valueOf2);
        bVar.a(n.bc, str);
        bVar.a("uuid", deviceId);
        bVar.a("ext1", str2);
        bVar.a("ext2", str3);
        bVar.a(n.ax, valueOf3);
        bVar.a("token", n);
        bVar.a("os", str4);
        bVar.a("appVersion", string3);
        bVar.a("version", str5);
        bVar.a(d.L, c);
        cVar.b(dji.pilot.flyforbid.b.d(), bVar, new 5(this));
    }
}
