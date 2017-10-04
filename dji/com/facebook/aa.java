package com.facebook;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.internal.ah;
import com.facebook.internal.ai;

final class aa {
    static final String a = "com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED";
    static final String b = "com.facebook.sdk.EXTRA_OLD_PROFILE";
    static final String c = "com.facebook.sdk.EXTRA_NEW_PROFILE";
    private static volatile aa d;
    private final LocalBroadcastManager e;
    private final z f;
    private Profile g;

    aa(LocalBroadcastManager localBroadcastManager, z zVar) {
        ai.a((Object) localBroadcastManager, "localBroadcastManager");
        ai.a((Object) zVar, "profileCache");
        this.e = localBroadcastManager;
        this.f = zVar;
    }

    static aa a() {
        if (d == null) {
            synchronized (aa.class) {
                if (d == null) {
                    d = new aa(LocalBroadcastManager.getInstance(o.h()), new z());
                }
            }
        }
        return d;
    }

    Profile b() {
        return this.g;
    }

    boolean c() {
        Profile a = this.f.a();
        if (a == null) {
            return false;
        }
        a(a, false);
        return true;
    }

    void a(Profile profile) {
        a(profile, true);
    }

    private void a(Profile profile, boolean z) {
        Profile profile2 = this.g;
        this.g = profile;
        if (z) {
            if (profile != null) {
                this.f.a(profile);
            } else {
                this.f.b();
            }
        }
        if (!ah.a((Object) profile2, (Object) profile)) {
            a(profile2, profile);
        }
    }

    private void a(Profile profile, Profile profile2) {
        Intent intent = new Intent(a);
        intent.putExtra(b, profile);
        intent.putExtra(c, profile2);
        this.e.sendBroadcast(intent);
    }
}
