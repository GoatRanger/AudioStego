package com.ut.mini.core.f;

import android.content.Context;
import com.ut.mini.base.IUTMCBuildInfo;
import com.ut.mini.base.UTLogFieldsScheme;
import com.ut.mini.base.a;
import com.ut.mini.base.c;
import com.ut.mini.core.e;
import com.ut.mini.core.sign.IUTRequestAuthentication;
import com.ut.mini.core.sign.UTSecuritySDKRequestAuthentication;
import com.ut.mini.d.f;
import com.ut.mini.d.g;
import com.ut.mini.d.n;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

public class b {
    public static String a(String str, Map<String, Object> map, Map<String, Object> map2) throws Exception {
        String str2;
        String str3 = "";
        if (map2 != null && map2.size() > 0) {
            Set keySet = map2.keySet();
            String[] strArr = new String[keySet.size()];
            keySet.toArray(strArr);
            str2 = str3;
            for (String str4 : f.a().a(strArr, true)) {
                str2 = str2 + str4 + g.b((byte[]) map2.get(str4));
            }
            str3 = str2;
        }
        try {
            str3 = a(str, null, null, str3);
        } catch (Exception e) {
            str3 = a(a.a(), null, null, str3);
        }
        str2 = e.a().e();
        if (str2 != null) {
            return str3 + "&dk=" + URLEncoder.encode(str2, "UTF-8");
        }
        return str3;
    }

    public static String b(String str, Map<String, Object> map, Map<String, Object> map2) throws Exception {
        String str2;
        String.format("dd=%s&nsgs=1", new Object[]{URLEncoder.encode(a(), "UTF-8")});
        String str3 = "dd=" + URLEncoder.encode(a(), "UTF-8") + "&nsgs=1";
        String a = a();
        String str4 = "";
        if (map2 != null && map2.size() > 0) {
            str2 = (String) map2.get("cf");
            if (str2 != null) {
                str2 = g.b(str2.getBytes());
                return a(str, str3, a, str2);
            }
        }
        str2 = str4;
        return a(str, str3, a, str2);
    }

    public static String c(String str, Map<String, Object> map, Map<String, Object> map2) throws Exception {
        String str2 = "";
        String str3 = "";
        String str4 = "";
        if (map != null && map.size() > 0) {
            str3 = (String) map.get("logid");
            if (n.a(str3)) {
                com.ut.mini.b.a.b(1, "getSignedABTestUrl", "logid is null,return abtest!");
                return null;
            }
            str2 = "logid=" + str3;
        }
        return a(str, str2, str3, str4);
    }

    private static String a() {
        String f = c.a().f();
        if (f != null) {
            try {
                byte[] c = g.c(f.getBytes("UTF-8"));
                if (c != null && c.length > 0) {
                    return com.ut.mini.d.c.b(c, 2);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private static String a(String str, String str2, String str3, String str4) throws Exception {
        String str5;
        String shortSDKVersion;
        Context k = c.a().k();
        String l = c.a().l();
        String h = c.a().h();
        if (h == null) {
            str5 = "";
        } else {
            str5 = h;
        }
        h = (String) com.ut.mini.core.a.a(k).get(UTLogFieldsScheme.APPVERSION.toString());
        String str6 = (String) com.ut.mini.core.a.a(k).get(UTLogFieldsScheme.OS.toString());
        String str7 = "4.1.0";
        IUTMCBuildInfo c = c.a().c();
        if (c != null) {
            shortSDKVersion = c.getShortSDKVersion();
            if (!n.a(shortSDKVersion)) {
                str7 = shortSDKVersion;
            }
        }
        shortSDKVersion = (String) com.ut.mini.core.a.a(k).get(UTLogFieldsScheme.UTDID.toString());
        String str8 = "3.0";
        String valueOf = String.valueOf(System.currentTimeMillis());
        IUTRequestAuthentication d = c.a().d();
        String str9 = "0";
        if (d instanceof UTSecuritySDKRequestAuthentication) {
            str9 = "1";
        }
        StringBuilder append = new StringBuilder().append(l).append(str5).append(h).append(str6).append(str7).append(shortSDKVersion).append(valueOf).append(str8).append(str9);
        if (str3 == null) {
            str3 = "";
        }
        append = append.append(str3);
        if (str4 == null) {
            str4 = "";
        }
        String sign = d.getSign(g.b(append.append(str4).toString().getBytes()));
        String str10 = "";
        if (!n.a(str2)) {
            str10 = str2 + com.alipay.sdk.h.a.b;
        }
        return String.format("%s?%sak=%s&av=%s&c=%s&v=%s&s=%s&d=%s&sv=%s&p=%s&t=%s&u=%s&is=%s", new Object[]{str, str10, a(l), a(h), a(str5), a(str8), a(sign), a(shortSDKVersion), str7, str6, valueOf, "", str9});
    }

    private static String a(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }
}
