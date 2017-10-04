package com.facebook.internal;

import android.os.Bundle;
import com.facebook.o;
import com.facebook.y;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;

public final class af {
    public static final String A = "version";
    public static final String B = "touch";
    public static final String C = "v2.5";
    public static final Collection<String> D = ah.a("service_disabled", "AndroidAuthKillSwitchException");
    public static final Collection<String> E = ah.a("access_denied", "OAuthAccessDeniedException");
    public static final String F = "CONNECTION_FAILURE";
    private static final String G = af.class.getName();
    private static final String H = "m.%s";
    private static final String I = "https://graph-video.%s";
    private static final String J = "https://graph.%s";
    public static final String a = "dialog/";
    public static final String b = "access_token";
    public static final String c = "app_id";
    public static final String d = "auth_type";
    public static final String e = "client_id";
    public static final String f = "display";
    public static final String g = "touch";
    public static final String h = "e2e";
    public static final String i = "legacy_override";
    public static final String j = "redirect_uri";
    public static final String k = "response_type";
    public static final String l = "return_scopes";
    public static final String m = "scope";
    public static final String n = "default_audience";
    public static final String o = "sdk";
    public static final String p = "state";
    public static final String q = "rerequest";
    public static final String r = "token,signed_request";
    public static final String s = "true";
    public static final String t = "fbconnect://success";
    public static final String u = "fbconnect://cancel";
    public static final String v = "app_id";
    public static final String w = "bridge_args";
    public static final String x = "android_key_hash";
    public static final String y = "method_args";
    public static final String z = "method_results";

    public static final String a() {
        return String.format(H, new Object[]{o.g()});
    }

    public static final String b() {
        return String.format(J, new Object[]{o.g()});
    }

    public static final String c() {
        return String.format(I, new Object[]{o.g()});
    }

    public static final String d() {
        return C;
    }

    public static Bundle a(String str, int i, Bundle bundle) {
        String d = o.d(o.h());
        if (ah.a(d)) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString(x, d);
        bundle2.putString("app_id", o.k());
        bundle2.putInt("version", i);
        bundle2.putString("display", "touch");
        Bundle bundle3 = new Bundle();
        bundle3.putString("action_id", str);
        if (bundle == null) {
            bundle = new Bundle();
        }
        try {
            JSONObject a = e.a(bundle3);
            JSONObject a2 = e.a(bundle);
            if (a == null || a2 == null) {
                return null;
            }
            bundle2.putString(w, a.toString());
            bundle2.putString(y, a2.toString());
            return bundle2;
        } catch (JSONException e) {
            x.a(y.DEVELOPER_ERRORS, 6, G, "Error creating Url -- " + e);
            bundle2 = null;
        }
    }
}
