package com.facebook;

import android.content.SharedPreferences;
import com.facebook.internal.ai;
import com.google.android.gms.common.Scopes;
import org.json.JSONException;
import org.json.JSONObject;

final class z {
    static final String a = "com.facebook.ProfileManager.CachedProfile";
    static final String b = "com.facebook.AccessTokenManager.SharedPreferences";
    private final SharedPreferences c = o.h().getSharedPreferences(b, 0);

    z() {
    }

    Profile a() {
        String string = this.c.getString(a, null);
        if (string != null) {
            try {
                return new Profile(new JSONObject(string));
            } catch (JSONException e) {
            }
        }
        return null;
    }

    void a(Profile profile) {
        ai.a((Object) profile, Scopes.PROFILE);
        JSONObject i = profile.i();
        if (i != null) {
            this.c.edit().putString(a, i.toString()).apply();
        }
    }

    void b() {
        this.c.edit().remove(a).apply();
    }
}
