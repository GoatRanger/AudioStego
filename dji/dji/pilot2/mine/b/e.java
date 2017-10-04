package dji.pilot2.mine.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.usercenter.b.f;
import dji.pilot2.b;

public class e {
    public static final String a = "setting_preferences";
    public static final String b = "setting_use_cellular";
    public static final String c = "setting_update";
    public static final String d = "settings_upload_type";
    public static final String e = "settings_upload_user";
    public static final String f = "setting_video_trailer_date";
    public static final String g = "setting_video_trailer_director";
    public static final String h = "setting_video_trailer_location";
    public static final String i = "last_login_account";
    public static final String j = "last_level_info_string";
    public static final String k = "draft_notification";
    public static final String l = "last_artwork_account";
    public static final String m = "last_video_artwork";
    public static final String n = "last_photo_artwork";
    public static final String o = "none";
    public static final String p = "youtube";
    public static final String q = "youku";
    public static final String r = "none";
    private static final String s = "unknown";
    private static final String t = "what's_new_version";
    private static e u;
    private SharedPreferences v = this.w.getSharedPreferences(a, 4);
    private Context w;

    public static synchronized e getInstance() {
        e eVar;
        synchronized (e.class) {
            if (u == null) {
                u = new e(b.a.a());
            }
            eVar = u;
        }
        return eVar;
    }

    private e(Context context) {
        this.w = context;
    }

    public String a() {
        String str = "unknown";
        try {
            PackageInfo packageInfo = this.w.getPackageManager().getPackageInfo(this.w.getPackageName(), 0);
            String string = this.w.getResources().getString(R.string.buildname);
            str = String.format(this.w.getResources().getString(R.string.mine_settings_version), new Object[]{packageInfo.versionName, string});
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return str;
    }

    public boolean b() {
        String string = this.v.getString(t, "unknown");
        String a = a();
        DJILogHelper.getInstance().LOGI("Lyric", "what's new version: " + string);
        DJILogHelper.getInstance().LOGI("Lyric", "current version: " + a);
        this.v.edit().putString(t, a).apply();
        if (string.equals(a)) {
        }
        return false;
    }

    public String c() {
        return this.v.getString(d, "none");
    }

    public void a(String str) {
        this.v.edit().putString(d, str).apply();
    }

    public String d() {
        return this.v.getString(e, "none");
    }

    public Boolean e() {
        return Boolean.valueOf(this.v.getBoolean(f, true));
    }

    public void a(Boolean bool) {
        this.v.edit().putBoolean(f, bool.booleanValue()).apply();
    }

    public Boolean f() {
        return Boolean.valueOf(this.v.getBoolean(g, true));
    }

    public void b(Boolean bool) {
        this.v.edit().putBoolean(g, bool.booleanValue()).apply();
    }

    public Boolean g() {
        return Boolean.valueOf(this.v.getBoolean(h, true));
    }

    public void c(Boolean bool) {
        this.v.edit().putBoolean(h, bool.booleanValue()).apply();
    }

    public void b(String str) {
        this.v.edit().putString(e, str).apply();
    }

    public boolean h() {
        return this.v.getBoolean(b, true);
    }

    public void a(boolean z) {
        this.v.edit().putBoolean(b, z).apply();
    }

    public boolean i() {
        return this.v.getBoolean(c, false);
    }

    public void b(boolean z) {
        this.v.edit().putBoolean(c, z);
    }

    public a j() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) b.a.a().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return a.d;
        }
        if (activeNetworkInfo.getType() == 0) {
            return a.b;
        }
        if (activeNetworkInfo.getType() == 1) {
            return a.a;
        }
        return a.c;
    }

    public String c(String str) {
        if (k().equals(str)) {
            return this.v.getString(j, "");
        }
        return "";
    }

    public void a(String str, String str2) {
        if (str != null && str2 != null && !str2.equals("")) {
            Editor edit = this.v.edit();
            edit.putString(i, str);
            edit.putString(j, str2);
            edit.apply();
        }
    }

    public void d(String str) {
        this.v.edit().putString(i, str).apply();
    }

    public String k() {
        return this.v.getString(i, "");
    }

    public void a(int i) {
        this.v.edit().putInt(k, i).apply();
    }

    public int l() {
        return this.v.getInt(k, 0);
    }

    public void a(String str, String str2, String str3) {
        Editor edit = this.v.edit();
        edit.putString(l, str);
        edit.putString(m, str2);
        edit.putString(n, str3);
        edit.apply();
    }

    public String e(String str) {
        String string = this.v.getString(l, "");
        String l = f.getInstance().l();
        if (string.equals(str) || string.equals(l)) {
            return this.v.getString(m, "");
        }
        return "";
    }

    public String f(String str) {
        String string = this.v.getString(l, "");
        String l = f.getInstance().l();
        if (string.equals(str) || string.equals(l)) {
            return this.v.getString(n, "");
        }
        return "";
    }
}
