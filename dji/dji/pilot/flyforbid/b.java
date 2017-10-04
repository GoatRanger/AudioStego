package dji.pilot.flyforbid;

import dji.pilot.c.a;
import java.io.File;

public class b {
    private static final String a = "https://flysafe-api.dji.com/api/v1/geo_fence/noflyzone_params/?";
    private static final String b = "http://121.43.186.94/api/v1/geo_fence/noflyzone_params/?";
    private static final String c = "https://flysafe-api.dji.com/api/beta/geo_fence/noflyzone_params/?";
    private static final String d = "http://121.43.186.94/api/beta/geo_fence/noflyzone_params/?";
    private static final String e = "https://flysafe-api.dji.com/api/release_limitarea.json/?updated_at=";
    private static final String f = "http://121.43.186.94/api/release_limitarea.json/?updated_at=";
    private static final String g = "https://flysafe-api.dji.com/index.php?r=userarea/terms";
    private static final String h = "http://121.43.186.94:443/index.php?r=userarea/terms";
    private static final String i = "https://flysafe-api.dji.com/api/unlimit_license_list";
    private static final String j = "http://121.43.186.94/api/unlimit_license_list";
    private static final String k = "https://flysafe-api.dji.com/api/unlimit_license";
    private static final String l = "http://121.43.186.94/api/unlimit_license";
    private static final String m = "https://flysafe-api.dji.com/api/unlimit_user_verify/?";
    private static final String n = "http://121.43.186.94/api/unlimit_user_verify/?";
    private static final String o = "https://flysafe-api.dji.com/api/airmap_verify/?version=1.0";
    private static final String p = "http://121.43.186.94/api/airmap_verify/?version=1.0";
    private static final String q = "https://flysafe-api.dji.com/api/mobile_unlock_areas/?";
    private static final String r = "http://121.43.186.94/api/mobile_unlock_areas/?";
    private static final String s = "https://flysafe-api.dji.com/index.php?r=userarea/errorreport";
    private static final String t = "http://121.43.186.94:443/index.php?r=userarea/errorreport";
    private static final String u = "https://flysafe-api.dji.com/api/v1/geo_fence/list_unlimited_areas";
    private static final String v = "http://121.43.186.94/api/v1/geo_fence/list_unlimited_areas";
    private static final String w = "https://flysafe-api.dji.com/api/v1/geo_fence/fetch_corrections?updated_at=";
    private static final String x = "http://121.43.186.94/api/v1/geo_fence/fetch_corrections?updated_at=";

    public static void a() {
        File file = new File("/sdcard/flyforbidtest");
        File file2 = new File("/sdcard/Flyforbidtest");
        File file3 = new File("/sdcard/flyforbidbeta");
        File file4 = new File("/sdcard/Flyforbidbeta");
        if (file.exists() || file2.exists()) {
            a.C = true;
            a.D = false;
        }
        if (file3.exists() || file4.exists()) {
            a.C = false;
            a.D = true;
        }
    }

    public static String b() {
        if (a.C && !a.D) {
            return b;
        }
        if (a.C && a.D) {
            return d;
        }
        if (a.D) {
            return c;
        }
        return a;
    }

    public static String c() {
        if (a.C) {
            return f;
        }
        return e;
    }

    public static String d() {
        if (a.C) {
            return h;
        }
        return g;
    }

    public static String e() {
        if (a.C) {
            return j;
        }
        return i;
    }

    public static String f() {
        if (a.C) {
            return l;
        }
        return k;
    }

    public static String g() {
        if (a.C) {
            return n;
        }
        return m;
    }

    public static String h() {
        if (a.C) {
            return p;
        }
        return o;
    }

    public static String i() {
        if (a.C) {
            return r;
        }
        return q;
    }

    public static String j() {
        if (a.C) {
            return t;
        }
        return s;
    }

    public static String k() {
        if (a.C) {
            return v;
        }
        return u;
    }

    public static String l() {
        if (a.C) {
            return x;
        }
        return w;
    }
}
