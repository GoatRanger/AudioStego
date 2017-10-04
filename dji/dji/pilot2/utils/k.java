package dji.pilot2.utils;

import android.content.Context;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.alipay.sdk.j.i;
import dji.log.DJILogHelper;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.model.b;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.publics.b.a;
import dji.pilot2.publics.b.a$a;
import dji.pilot2.publics.b.a$b;
import dji.pilot2.publics.b.a$c;
import dji.pilot2.publics.b.a$d;
import dji.pilot2.publics.b.a$e;
import dji.pilot2.publics.b.a$f;
import dji.pilot2.publics.b.a$g;
import dji.pilot2.publics.b.a$h;
import dji.pilot2.publics.b.a$i;
import dji.pilot2.publics.b.a$j;
import dji.pilot2.publics.b.a$l;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class k implements a {
    public static final boolean a = false;
    public static boolean b = false;
    public static boolean c = false;
    private static boolean d = false;

    public static String a() {
        return "https://www.skypixel.com/api/mobile/explore/alert?";
    }

    public static String a(String str, String str2, String str3) {
        return "http://djistatic.com/flightlog?token=" + str + com.alipay.sdk.h.a.b + "email=" + str2 + a$j.p + str3 + "#slideFly";
    }

    public static String a(String str, String str2) {
        return "https://www.skypixel.com/api/" + str + d.t + str2 + a$i.an;
    }

    public static String b(String str, String str2) {
        return "https://www.skypixel.com/api/" + str + d.t + str2 + a$i.ao;
    }

    public static String c(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            return a$i.aq + str;
        }
        return a$i.aq + str + "?token=" + str2;
    }

    public static String a(String str) {
        return a$i.aq + str + a$i.ah;
    }

    public static String b(String str) {
        return a$i.aq + str + a$i.ar;
    }

    public static String c(String str) {
        return a$i.aq + str + a$i.as;
    }

    public static String d(String str) {
        return a$l.e + str;
    }

    public static String b() {
        if (Locale.getDefault().getLanguage().equals("zh")) {
            return "https://www.skypixel.com/api/mobile/explore/notices?lang=cn";
        }
        return "https://www.skypixel.com/api/mobile/explore/notices?lang=en";
    }

    public static String a(String str, int i) {
        String str2;
        if (Locale.getDefault().getLanguage().equals("zh")) {
            str2 = "https://www.skypixel.com/api/msg/list?token=" + str + "&lang=cn" + "&page=" + i + "&version=2.6.0";
        } else {
            str2 = "https://www.skypixel.com/api/msg/list?token=" + str + "&lang=en" + "&page=" + i + "&version=2.6.0";
        }
        Log.i("zh", "url:" + str2);
        return str2;
    }

    public static String e(String str) {
        return a$e.m + str;
    }

    public static String f(String str) {
        return a$e.n + str;
    }

    public static String c() {
        if (dji.pilot.c.a.u) {
            return a.u;
        }
        return a.v;
    }

    public static String d() {
        if (dji.pilot.c.a.u) {
            return "http://m.dbeta.me/api/mobile/store/timestamps?from=dji-pilot-app";
        }
        return "http://m.dji.com/api/mobile/store/timestamps?from=dji-pilot-app";
    }

    public static String e() {
        if (dji.pilot.c.a.u) {
            return a$h.d;
        }
        return a$h.bX_;
    }

    public static String g(String str) {
        return "http://click.dji.com/api/publishers/account_last_update?email=" + str;
    }

    public static String h(String str) {
        String language = Locale.getDefault().getLanguage();
        if (dji.pilot.c.a.u) {
            return "http://bbs.dbeta.me/point.php?token=" + str;
        }
        if ("zh".equals(language)) {
            language = a$h.l;
        } else {
            language = a$h.m;
        }
        return language + "token" + "=" + str;
    }

    public static String i(String str) {
        String str2 = "";
        if (d) {
            str2 = "https://www.skypixel.com/api/giftcards/has_new_giftcard?token=" + str;
        } else {
            str2 = "https://www.skypixel.com/api/giftcards/has_new_giftcard?token=" + str;
        }
        DJILogHelper.getInstance().LOGI("bob", "getCouponUpdateUrl = " + str2);
        return str2;
    }

    public static String j(String str) {
        String str2 = "";
        str2 = g();
        if (d) {
            if (str2 != null) {
                str2 = a$c.d + str + "?lang=" + str2;
            } else {
                str2 = a$c.d + str;
            }
        } else if (str2 != null) {
            str2 = a$c.d + str + "?lang=" + str2;
        } else {
            str2 = a$c.d + str;
        }
        DJILogHelper.getInstance().LOGI("bob", "getCouponGiftShareUrl = " + str2);
        return str2;
    }

    public static String f() {
        String language = Locale.getDefault().getLanguage();
        String str = a$c.k;
        if (!language.equalsIgnoreCase("zh")) {
            str = a$c.l;
        }
        DJILogHelper.getInstance().LOGI("bob", "getCouponUrl user rule " + str);
        return str;
    }

    public static String g() {
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        if (!language.equalsIgnoreCase("zh")) {
            return language;
        }
        if (country.equalsIgnoreCase("CN")) {
            return a.p;
        }
        return "zh-TW";
    }

    public static String a(String str, Context context) {
        String str2 = "";
        str2 = g();
        if (d) {
            str2 = a$c.a + str + "&lang=" + str2;
        } else {
            str2 = a$c.a + str + "&lang=" + str2;
        }
        DJILogHelper.getInstance().LOGI("bob", "getCouponGiftCards = " + str2);
        return str2;
    }

    public static String k(String str) {
        String str2 = "";
        str2 = g();
        if (d) {
            str2 = a$c.b + str + "?lang=" + str2;
        } else {
            str2 = a$c.b + str + "?lang=" + str2;
        }
        DJILogHelper.getInstance().LOGI("bob", "getCouponGiftCardsDetail = " + str2);
        return str2;
    }

    public static String a(String str, boolean z) {
        String str2 = "";
        if (d) {
            str2 = "http://www.skypixel.com/api/giftcards/notifications?token=" + str + com.alipay.sdk.h.a.b + "contain_read=" + z;
        } else {
            str2 = "http://www.skypixel.com/api/giftcards/notifications?token=" + str + com.alipay.sdk.h.a.b + "contain_read=" + z;
        }
        DJILogHelper.getInstance().LOGI("bob", "getCouponGiftCardsMsg = " + str2);
        return str2;
    }

    public static String l(String str) {
        String str2 = "";
        if (d) {
            str2 = "https://www.skypixel.com/api/giftcards/clear_notifications?token=" + str;
        } else {
            str2 = "https://www.skypixel.com/api/giftcards/clear_notifications?token=" + str;
        }
        DJILogHelper.getInstance().LOGI("bob", "getCouponGiftCardsClearMsg = " + str2);
        return str2;
    }

    public static String m(String str) {
        String str2 = "";
        str2 = g();
        if (d) {
            str2 = "https://www.skypixel.com/api/giftcards/popup?token=" + str + "&lang=" + str2;
        } else {
            str2 = "https://www.skypixel.com/api/giftcards/popup?token=" + str + "&lang=" + str2;
        }
        DJILogHelper.getInstance().LOGI("bob", "getCouponGiftCardsPopupMsg = " + str2);
        return str2;
    }

    public static String a(String str, DJIDeviceType dJIDeviceType) {
        String str2 = "";
        str2 = g();
        if (d) {
            if (dJIDeviceType.equals(DJIDeviceType.Phone)) {
                str2 = a$c.f + str + "&locale=" + str2;
            } else {
                str2 = a$c.h + str + "&locale=" + str2;
            }
        } else if (dJIDeviceType.equals(DJIDeviceType.Phone)) {
            str2 = a$c.e + str + "&locale=" + str2;
        } else {
            str2 = "http://store.dji.com/coupons/&locale=" + str2;
        }
        DJILogHelper.getInstance().LOGI("bob", "getCouponImmediateUseUrl = " + str2);
        return str2;
    }

    public static String h() {
        if (dji.pilot.c.a.u) {
            return a$h.c;
        }
        return "https://www.skypixel.com/";
    }

    public static String i() {
        if (dji.pilot.c.a.u) {
            return a.s;
        }
        return a.t;
    }

    public static String j() {
        return a$h.x;
    }

    public static String a(Context context, int i) {
        if (i >= a$d.b.length) {
            i = 0;
        }
        if ("zh".equals(context.getResources().getConfiguration().locale.getLanguage())) {
            return a$d.b[i];
        }
        return a$d.c[i];
    }

    public static String a(int i) {
        String str;
        if (i >= a$d.e.length) {
            i = 0;
        }
        if (dji.pilot.c.a.u) {
            str = a$d.e[i];
        } else {
            str = a$d.d[i];
        }
        String str2 = "";
        if (s() == a.p) {
            str2 = "cn/";
        }
        String str3 = "m";
        if (r().equals(a.q)) {
            str3 = "store";
        }
        return String.format(Locale.US, str, new Object[]{str3, str2});
    }

    private static boolean x(String str) {
        boolean z = false;
        if (str == null || str.length() <= 0) {
            return true;
        }
        int i = -1;
        int i2 = 0;
        while (true) {
            i = str.indexOf(37, i + 1);
            if (i < 0) {
                break;
            }
            i2++;
        }
        if (i2 >= 2) {
            z = true;
        }
        return z;
    }

    public static String n(String str) {
        String str2 = "";
        if (s() == a.p) {
            str2 = "cn/";
        }
        if (x(str)) {
            String str3 = "m";
            if (r().equals(a.q)) {
                str3 = "store";
            }
            return String.format(Locale.US, str, new Object[]{str3, str2});
        }
        return String.format(Locale.US, str, new Object[]{str2});
    }

    public static String o(String str) {
        String str2;
        if (s() != a.p) {
            str2 = a$h.h;
        } else if (dji.pilot.c.a.u) {
            str2 = a$h.f;
        } else {
            str2 = a$h.g;
        }
        return str2 + "token" + "=" + str + "&app=2&mobile=YES";
    }

    public static String p(String str) {
        return o(str) + "&from=djigo";
    }

    public static String q(String str) {
        return o(str) + "&from=djigo_share";
    }

    public static String k() {
        return a$a.a;
    }

    public static String l() {
        return a$h.a;
    }

    public static String d(String str, String str2) {
        return b(str, str2, null);
    }

    public static String b(String str, String str2, String str3) {
        if (dji.pilot.c.a.u) {
            if (str.equals("photo")) {
                return "https://www.aasky.net/pilot/photos/" + str2;
            }
            if (str.equals("video")) {
                return "https://www.aasky.net/pilot/videos/" + str2;
            }
            return "";
        } else if (str.equals("photo")) {
            return "https://www.skypixel.com/pilot/photos/share/" + str2;
        } else {
            if (!str.equals("video")) {
                return "";
            }
            if (str3 == null || str3.equals("")) {
                return "https://www.skypixel.com/pilot/videos/share/" + str2;
            }
            return a$l.b + str3 + d.t + str2;
        }
    }

    public static String m() {
        if (dji.pilot.c.a.u) {
            return a$h.r;
        }
        return a$h.q;
    }

    public static String n() {
        String str = "";
        if (dji.pilot.c.a.u) {
            str = a$h.bZ_;
        } else {
            str = a$h.bY_;
        }
        String str2 = "";
        if (s() == a.p) {
            str2 = "/cn";
        }
        String str3 = "m";
        if (r().equals(a.q)) {
            str3 = "store";
        }
        return String.format(Locale.US, str, new Object[]{str3, str2});
    }

    public static String r(String str) {
        String n = f.getInstance().n();
        String r = r();
        String s = s();
        return String.format(Locale.US, a$h.n, new Object[]{n, r, s, str});
    }

    public static String o() {
        String str;
        if (dji.pilot.c.a.u) {
            str = a$h.u;
        } else {
            str = a$h.t;
        }
        if (s() == a.p) {
            return String.format(Locale.US, str, new Object[]{"cn"});
        }
        return String.format(Locale.US, str, new Object[]{"en"});
    }

    public static String p() {
        String str;
        if (dji.pilot.c.a.u) {
            str = a$h.w;
        } else {
            str = a$h.v;
        }
        if (s() == a.p) {
            return String.format(Locale.US, str, new Object[]{"cn"});
        }
        return String.format(Locale.US, str, new Object[]{"en"});
    }

    public static String q() {
        String str = "en";
        if (Locale.getDefault().getCountry().equals("CN")) {
            str = "cn";
        } else if (Locale.getDefault().getCountry().equals("JP")) {
            str = a$j.e;
        }
        DJILogHelper.getInstance().LOGI("Lyric", "lang=" + str);
        return a$h.C + str;
    }

    public static String e(String str, String str2) {
        String toLowerCase = Locale.getDefault().getCountry().toLowerCase();
        DJILogHelper.getInstance().LOGI("bob", "lang = " + toLowerCase);
        return "http://www.djiexplore.com/feedback?platform=android&language" + toLowerCase + "&content=" + str + "&contact=" + str2;
    }

    public static String s(String str) {
        String n = f.getInstance().n();
        String r = r();
        String s = s();
        return String.format(Locale.US, a$h.s, new Object[]{n, r, s, str});
    }

    public static String r() {
        String a = b.a();
        String str = a.r;
        if ("large".equals(a) || "xlarge".equals(a)) {
            return a.q;
        }
        return str;
    }

    public static String s() {
        String str = "en";
        if (Locale.SIMPLIFIED_CHINESE.getLanguage().equals(Locale.getDefault().getLanguage()) && Locale.SIMPLIFIED_CHINESE.getCountry().equals(Locale.getDefault().getCountry())) {
            return a.p;
        }
        return str;
    }

    public static void a(Context context) {
        if (context == null) {
            context = dji.pilot2.b.a.a();
        }
        CookieSyncManager.createInstance(context);
        CookieManager.getInstance().removeSessionCookie();
        CookieSyncManager.getInstance().sync();
    }

    public static boolean t() {
        return c$i.b.equals(f(c(), "_logged"));
    }

    private static String f(String str, String str2) {
        String str3 = null;
        CookieManager instance = CookieManager.getInstance();
        String cookie = instance.getCookie(str);
        instance.setCookie(str, cookie);
        if (cookie != null) {
            for (String str4 : cookie.split(i.b)) {
                if (str4.contains(str2)) {
                    str3 = str4.split("=")[1];
                }
            }
        }
        return str3;
    }

    public static void a(Context context, String str) {
        if (context == null) {
            context = dji.pilot2.b.a.a();
        }
        CookieSyncManager createInstance = CookieSyncManager.createInstance(context);
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        instance.setCookie(c(), "wn2grgIkKubUV044=" + str + "; domain=" + c() + "; HttpOnly; secure(https)");
        instance.setCookie(a.w, "wn2grgIkKubUV044=" + str + "; domain=" + a.w + "; HttpOnly; secure(https)");
        instance.setCookie(c(), "_logged=yes; domain=" + c() + "; HttpOnly; secure(https)");
        instance.setCookie(a.w, "_logged=yes; domain=.skypixel.com; HttpOnly; secure(https)");
        createInstance.sync();
    }

    public static String u() {
        if (dji.pilot.c.a.u) {
            return a$l.c;
        }
        return a$l.a;
    }

    public static String v() {
        if (dji.pilot.c.a.u) {
            return a$l.d;
        }
        return a$l.b;
    }

    public static String t(String str) {
        if (dji.pilot.c.a.u) {
            return a$h.y + str;
        }
        String str2 = a$h.z + str;
        DJILogHelper.getInstance().LOGI("bob", "lelString = " + str2);
        return str2;
    }

    public static String w() {
        return a$g.c;
    }

    public static String x() {
        return a$g.d;
    }

    public static String y() {
        String str = "";
        if (b) {
            str = a$f.a;
        } else {
            str = a$f.b;
        }
        return str + a$f.c;
    }

    public static String z() {
        String str = "";
        if (b) {
            str = a$f.a;
        } else {
            str = a$f.b;
        }
        return str + a$f.d;
    }

    public static String A() {
        String str = "";
        if (b) {
            str = a$f.a;
        } else {
            str = a$f.b;
        }
        return str + a$f.e;
    }

    public static String a(boolean z, double d, double d2) {
        if (!z) {
            return a$f.g;
        }
        return String.format(a$f.f, new Object[]{Double.valueOf(d), Double.valueOf(d2)});
    }

    public static String B() {
        if (Locale.getDefault().getCountry().equalsIgnoreCase("CN")) {
            return a$f.j;
        }
        return a$f.k;
    }

    public static String b(boolean z, double d, double d2) {
        if (!z) {
            return a$f.i;
        }
        return String.format(a$f.h, new Object[]{Double.valueOf(d), Double.valueOf(d2)});
    }

    public static String b(Context context) {
        String country = context.getResources().getConfiguration().locale.getCountry();
        if (country.equalsIgnoreCase("CN")) {
            return a$h.W;
        }
        if (country.equalsIgnoreCase("JP")) {
            return a$h.cc_;
        }
        if (country.equalsIgnoreCase("TW")) {
            return a$h.cd_;
        }
        return a$h.X;
    }

    public static String u(String str) {
        return "http://djistatic.com/repair/" + str;
    }

    public static String C() {
        return "http://aftersale.djiexplore.com/addon/";
    }

    public static String c(Context context) {
        return a$h.V + context.getResources().getConfiguration().locale.getCountry().toLowerCase();
    }

    public static String d(Context context) {
        String country = context.getResources().getConfiguration().locale.getCountry();
        DJILogHelper.getInstance().LOGI("bob", "getAcademyLanguString country = " + country + " Locale.SIMPLIFIED_CHINESE.getLanguage() = " + Locale.SIMPLIFIED_CHINESE.getLanguage() + " Locale.TRADITIONAL_CHINESE.getLanguage()" + Locale.TRADITIONAL_CHINESE.getLanguage());
        if (country.equalsIgnoreCase("CN")) {
            DJILogHelper.getInstance().LOGI("bob", "getAcademyLanguString Language = " + context.getResources().getConfiguration().locale.getVariant());
            return "cn";
        } else if (country.equalsIgnoreCase("TW")) {
            return a$j.g;
        } else {
            if (country.equalsIgnoreCase("JP")) {
                return a$j.e;
            }
            if (country.equalsIgnoreCase(a$j.f)) {
                return a$j.f;
            }
            return "en";
        }
    }

    public static String b(Context context, String str) {
        if (c) {
            return a$j.h + d(context) + d.t + str;
        }
        return a$j.i + d(context) + d.t + str;
    }

    public static String c(Context context, String str) {
        if (c) {
            return a$j.j + d(context) + d.t + str;
        }
        return a$j.k + d(context) + d.t + str;
    }

    public static String d(Context context, String str) {
        if (c) {
            return "http://10.11.0.50:8812/academy/faq/" + d(context) + d.t + "3" + d.t + str;
        }
        return "http://www.djiexplore.com/academy/faq/" + d(context) + d.t + "3" + d.t + str;
    }

    public static String a(int i, Context context) {
        if (c) {
            return "http://10.11.0.50:8864/academy/faq/?id=" + i + a$j.p + d(context);
        }
        return "http://djistatic.com/academy/faq/?id=" + i + a$j.p + d(context);
    }

    public static String v(String str) {
        return "https://www.skypixel.com/api/users/" + str + a$i.al;
    }

    public static String D() {
        return a$b.a;
    }

    public static String E() {
        return a$b.b;
    }

    public static String w(String str) {
        StringBuffer stringBuffer = new StringBuffer(1024);
        Matcher matcher = Pattern.compile("\\{.*?\\}").matcher(str);
        while (matcher.find()) {
            String substring = str.substring(matcher.start(), matcher.end());
            if (substring.equals("{dji_token}")) {
                if (f.getInstance().c()) {
                    matcher.appendReplacement(stringBuffer, f.getInstance().n());
                } else {
                    matcher.appendReplacement(stringBuffer, "");
                }
            } else if (substring.equals("{dji_email}")) {
                if (f.getInstance().c()) {
                    matcher.appendReplacement(stringBuffer, f.getInstance().j());
                } else {
                    matcher.appendReplacement(stringBuffer, "");
                }
            } else if (!substring.equals("{dji_lang}")) {
                matcher.appendReplacement(stringBuffer, "");
            } else if ("CN".equals(Locale.getDefault().getCountry())) {
                matcher.appendReplacement(stringBuffer, "cn");
            } else if ("JP".equals(Locale.getDefault().getCountry())) {
                matcher.appendReplacement(stringBuffer, a$j.e);
            } else if ("MO,TW,HK".contains(Locale.getDefault().getCountry())) {
                matcher.appendReplacement(stringBuffer, a$j.g);
            } else {
                matcher.appendReplacement(stringBuffer, "en");
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
