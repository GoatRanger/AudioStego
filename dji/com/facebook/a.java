package com.facebook;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.internal.ai;
import org.json.JSONException;
import org.json.JSONObject;

class a {
    static final String a = "com.facebook.AccessTokenManager.CachedAccessToken";
    private final SharedPreferences b;
    private final a c;
    private x d;

    static class a {
        a() {
        }

        public x a() {
            return new x(o.h());
        }
    }

    a(SharedPreferences sharedPreferences, a aVar) {
        this.b = sharedPreferences;
        this.c = aVar;
    }

    public a() {
        this(o.h().getSharedPreferences("com.facebook.AccessTokenManager.SharedPreferences", 0), new a());
    }

    public AccessToken a() {
        if (c()) {
            return d();
        }
        if (!e()) {
            return null;
        }
        AccessToken f = f();
        if (f == null) {
            return f;
        }
        a(f);
        g().b();
        return f;
    }

    public void a(AccessToken accessToken) {
        ai.a((Object) accessToken, "accessToken");
        try {
            this.b.edit().putString(a, accessToken.l().toString()).apply();
        } catch (JSONException e) {
        }
    }

    public void b() {
        this.b.edit().remove(a).apply();
        if (e()) {
            g().b();
        }
    }

    private boolean c() {
        return this.b.contains(a);
    }

    private AccessToken d() {
        AccessToken accessToken = null;
        String string = this.b.getString(a, accessToken);
        if (string != null) {
            try {
                accessToken = AccessToken.a(new JSONObject(string));
            } catch (JSONException e) {
            }
        }
        return accessToken;
    }

    private boolean e() {
        return o.e();
    }

    private AccessToken f() {
        Bundle a = g().a();
        if (a == null || !x.b(a)) {
            return null;
        }
        return AccessToken.a(a);
    }

    private x g() {
        if (this.d == null) {
            synchronized (this) {
                if (this.d == null) {
                    this.d = this.c.a();
                }
            }
        }
        return this.d;
    }
}
