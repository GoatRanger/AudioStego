package com.facebook;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.ab;
import com.facebook.internal.ah;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ag {
    static final /* synthetic */ boolean a = (!ag.class.desiredAssertionStatus());
    private static final String b = "TestUserManager";
    private String c;
    private String d;
    private Map<String, JSONObject> e;

    private enum a {
        PRIVATE,
        SHARED
    }

    public ag(String str, String str2) {
        if (ah.a(str2) || ah.a(str)) {
            throw new k("Must provide app ID and secret");
        }
        this.c = str;
        this.d = str2;
    }

    public AccessToken a(List<String> list) {
        return a(list, a.PRIVATE, null);
    }

    public AccessToken b(List<String> list) {
        return a((List) list, null);
    }

    public AccessToken a(List<String> list, String str) {
        return a(list, a.SHARED, str);
    }

    public synchronized String a() {
        return this.d;
    }

    public synchronized String b() {
        return this.c;
    }

    private AccessToken a(List<String> list, a aVar, String str) {
        Object asList;
        JSONObject c;
        d();
        if (ah.a((Collection) list)) {
            asList = Arrays.asList(new String[]{"email", "publish_actions"});
        } else {
            List<String> list2 = list;
        }
        if (aVar == a.PRIVATE) {
            c = c(asList, aVar, str);
        } else {
            c = b(asList, aVar, str);
        }
        return new AccessToken(c.optString("access_token"), this.d, c.optString("id"), asList, null, c.TEST_USER, null, null);
    }

    private synchronized void d() {
        if (this.e == null) {
            this.e = new HashMap();
            GraphRequest.e(this.d);
            Bundle bundle = new Bundle();
            bundle.putString("access_token", c());
            GraphRequest graphRequest = new GraphRequest(null, "app/accounts/test-users", bundle, null);
            graphRequest.c("testUsers");
            graphRequest.b(false);
            bundle = new Bundle();
            bundle.putString("access_token", c());
            bundle.putString("ids", "{result=testUsers:$.data.*.id}");
            bundle.putString(GraphRequest.c, "name");
            new GraphRequest(null, "", bundle, null).d("testUsers");
            List b = GraphRequest.b(graphRequest, r2);
            if (b == null || b.size() != 2) {
                throw new k("Unexpected number of results from TestUsers batch query");
            }
            a(((v) b.get(0)).b().optJSONArray("data"), ((v) b.get(1)).b());
        }
    }

    private synchronized void a(JSONArray jSONArray, JSONObject jSONObject) {
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            try {
                optJSONObject.put("name", jSONObject.optJSONObject(optJSONObject.optString("id")).optString("name"));
            } catch (Throwable e) {
                Log.e(b, "Could not set name", e);
            }
            a(optJSONObject);
        }
    }

    private synchronized void a(JSONObject jSONObject) {
        this.e.put(jSONObject.optString("id"), jSONObject);
    }

    private synchronized JSONObject a(String str) {
        JSONObject jSONObject;
        for (JSONObject jSONObject2 : this.e.values()) {
            if (jSONObject2.optString("name").contains(str)) {
                break;
            }
        }
        jSONObject2 = null;
        return jSONObject2;
    }

    final String c() {
        return this.d + "|" + this.c;
    }

    private JSONObject b(List<String> list, a aVar, String str) {
        JSONObject a = a(b(list, str));
        return a != null ? a : c(list, aVar, str);
    }

    private String b(List<String> list, String str) {
        return a((str != null ? ((long) str.hashCode()) & 4294967295L : 0) ^ (((long) c(list).hashCode()) & 4294967295L));
    }

    private String a(long j) {
        String l = Long.toString(j);
        StringBuilder stringBuilder = new StringBuilder("Perm");
        char[] toCharArray = l.toCharArray();
        int length = toCharArray.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = toCharArray[i];
            if (i3 == i2) {
                i3 = (char) (i3 + 10);
            }
            stringBuilder.append((char) ((i3 + 97) - 48));
            i++;
            i2 = i3;
        }
        return stringBuilder.toString();
    }

    private JSONObject c(List<String> list, a aVar, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("installed", "true");
        bundle.putString(ab.ac, c(list));
        bundle.putString("access_token", c());
        if (aVar == a.SHARED) {
            bundle.putString("name", String.format("Shared %s Testuser", new Object[]{b(list, str)}));
        }
        v m = new GraphRequest(null, String.format("%s/accounts/test-users", new Object[]{this.d}), bundle, w.b).m();
        n a = m.a();
        JSONObject b = m.b();
        if (a != null) {
            return null;
        }
        if (a || b != null) {
            if (aVar == a.SHARED) {
                try {
                    b.put("name", bundle.getString("name"));
                } catch (Throwable e) {
                    Log.e(b, "Could not set name", e);
                }
                a(b);
            }
            return b;
        }
        throw new AssertionError();
    }

    private String c(List<String> list) {
        return TextUtils.join(",", list);
    }
}
