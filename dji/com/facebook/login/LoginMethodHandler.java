package com.facebook.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.a.b;
import com.facebook.c;
import com.facebook.internal.a;
import com.facebook.internal.ab;
import com.facebook.internal.ah;
import com.facebook.k;
import com.facebook.login.LoginClient.Request;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

abstract class LoginMethodHandler implements Parcelable {
    Map<String, String> a;
    protected LoginClient b;

    abstract boolean a(Request request);

    abstract String c();

    LoginMethodHandler(LoginClient loginClient) {
        this.b = loginClient;
    }

    LoginMethodHandler(Parcel parcel) {
        this.a = ah.a(parcel);
    }

    void a(LoginClient loginClient) {
        if (this.b != null) {
            throw new k("Can't set LoginClient if it is already set.");
        }
        this.b = loginClient;
    }

    boolean a(int i, int i2, Intent intent) {
        return false;
    }

    boolean d() {
        return false;
    }

    void a() {
    }

    protected String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("0_auth_logger_id", str);
            jSONObject.put("3_method", c());
        } catch (JSONException e) {
            Log.w("LoginMethodHandler", "Error creating client state json: " + e.getMessage());
        }
        return jSONObject.toString();
    }

    protected void a(String str, Object obj) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, obj == null ? null : obj.toString());
    }

    protected void b(String str) {
        String d = this.b.c().d();
        b c = b.c(this.b.b(), d);
        Bundle bundle = new Bundle();
        bundle.putString(a.j, str);
        bundle.putLong(a.k, System.currentTimeMillis());
        bundle.putString("app_id", d);
        c.a(a.c, null, bundle);
    }

    static AccessToken a(Bundle bundle, c cVar, String str) {
        Date a = ah.a(bundle, ab.Z, new Date(0));
        Collection stringArrayList = bundle.getStringArrayList(ab.U);
        String string = bundle.getString(ab.Y);
        if (ah.a(string)) {
            return null;
        }
        return new AccessToken(string, str, bundle.getString(ab.X), stringArrayList, null, cVar, a, new Date());
    }

    public static AccessToken a(Collection<String> collection, Bundle bundle, c cVar, String str) throws k {
        Collection collection2;
        Date a = ah.a(bundle, "expires_in", new Date());
        String string = bundle.getString("access_token");
        String string2 = bundle.getString("granted_scopes");
        if (ah.a(string2)) {
            Collection<String> collection3 = collection;
        } else {
            Collection arrayList = new ArrayList(Arrays.asList(string2.split(",")));
        }
        string2 = bundle.getString("denied_scopes");
        if (ah.a(string2)) {
            collection2 = null;
        } else {
            collection2 = new ArrayList(Arrays.asList(string2.split(",")));
        }
        if (ah.a(string)) {
            return null;
        }
        return new AccessToken(string, str, c(bundle.getString("signed_request")), arrayList, collection2, cVar, a, new Date());
    }

    private static String c(String str) throws k {
        if (str == null || str.isEmpty()) {
            throw new k("Authorization response does not contain the signed_request");
        }
        try {
            String[] split = str.split("\\.");
            if (split.length == 2) {
                return new JSONObject(new String(Base64.decode(split[1], 0), "UTF-8")).getString("user_id");
            }
        } catch (UnsupportedEncodingException e) {
        } catch (JSONException e2) {
        }
        throw new k("Failed to retrieve user_id from signed_request");
    }

    public void writeToParcel(Parcel parcel, int i) {
        ah.a(parcel, this.a);
    }
}
