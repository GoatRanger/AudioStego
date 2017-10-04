package com.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.internal.ah;
import com.facebook.internal.ai;
import com.sina.weibo.sdk.constant.WBConstants;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

final class b {
    static final String a = "AccessTokenManager";
    static final String b = "com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED";
    static final String c = "com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN";
    static final String d = "com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN";
    static final String e = "com.facebook.AccessTokenManager.SharedPreferences";
    private static final int f = 86400;
    private static final int g = 3600;
    private static final String h = "oauth/access_token";
    private static final String i = "me/permissions";
    private static volatile b j;
    private final LocalBroadcastManager k;
    private final a l;
    private AccessToken m;
    private AtomicBoolean n = new AtomicBoolean(false);
    private Date o = new Date(0);

    private static class a {
        public String a;
        public int b;

        private a() {
        }
    }

    b(LocalBroadcastManager localBroadcastManager, a aVar) {
        ai.a((Object) localBroadcastManager, "localBroadcastManager");
        ai.a((Object) aVar, "accessTokenCache");
        this.k = localBroadcastManager;
        this.l = aVar;
    }

    static b a() {
        if (j == null) {
            synchronized (b.class) {
                if (j == null) {
                    j = new b(LocalBroadcastManager.getInstance(o.h()), new a());
                }
            }
        }
        return j;
    }

    AccessToken b() {
        return this.m;
    }

    boolean c() {
        AccessToken a = this.l.a();
        if (a == null) {
            return false;
        }
        a(a, false);
        return true;
    }

    void a(AccessToken accessToken) {
        a(accessToken, true);
    }

    private void a(AccessToken accessToken, boolean z) {
        AccessToken accessToken2 = this.m;
        this.m = accessToken;
        this.n.set(false);
        this.o = new Date(0);
        if (z) {
            if (accessToken != null) {
                this.l.a(accessToken);
            } else {
                this.l.b();
                ah.b(o.h());
            }
        }
        if (!ah.a((Object) accessToken2, (Object) accessToken)) {
            a(accessToken2, accessToken);
        }
    }

    private void a(AccessToken accessToken, AccessToken accessToken2) {
        Intent intent = new Intent(b);
        intent.putExtra(c, accessToken);
        intent.putExtra(d, accessToken2);
        this.k.sendBroadcast(intent);
    }

    void d() {
        if (e()) {
            a(null);
        }
    }

    private boolean e() {
        if (this.m == null) {
            return false;
        }
        Long valueOf = Long.valueOf(new Date().getTime());
        if (!this.m.g().a() || valueOf.longValue() - this.o.getTime() <= 3600000 || valueOf.longValue() - this.m.h().getTime() <= 86400000) {
            return false;
        }
        return true;
    }

    private static GraphRequest a(AccessToken accessToken, com.facebook.GraphRequest.b bVar) {
        return new GraphRequest(accessToken, i, new Bundle(), w.a, bVar);
    }

    private static GraphRequest b(AccessToken accessToken, com.facebook.GraphRequest.b bVar) {
        Bundle bundle = new Bundle();
        bundle.putString(WBConstants.AUTH_PARAMS_GRANT_TYPE, "fb_extend_sso_token");
        return new GraphRequest(accessToken, h, bundle, w.a, bVar);
    }

    void a(final com.facebook.AccessToken.b bVar) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            b(bVar);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ b b;

                public void run() {
                    this.b.b(bVar);
                }
            });
        }
    }

    private void b(com.facebook.AccessToken.b bVar) {
        final AccessToken accessToken = this.m;
        if (accessToken == null) {
            if (bVar != null) {
                bVar.a(new k("No current access token to refresh"));
            }
        } else if (this.n.compareAndSet(false, true)) {
            this.o = new Date();
            final Set hashSet = new HashSet();
            final Set hashSet2 = new HashSet();
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            final a aVar = new a();
            u uVar = new u(a(accessToken, new com.facebook.GraphRequest.b(this) {
                final /* synthetic */ b d;

                public void onCompleted(v vVar) {
                    JSONObject b = vVar.b();
                    if (b != null) {
                        JSONArray optJSONArray = b.optJSONArray("data");
                        if (optJSONArray != null) {
                            atomicBoolean.set(true);
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                if (optJSONObject != null) {
                                    String optString = optJSONObject.optString("permission");
                                    String optString2 = optJSONObject.optString("status");
                                    if (!(ah.a(optString) || ah.a(optString2))) {
                                        optString2 = optString2.toLowerCase(Locale.US);
                                        if (optString2.equals("granted")) {
                                            hashSet.add(optString);
                                        } else if (optString2.equals("declined")) {
                                            hashSet2.add(optString);
                                        } else {
                                            Log.w(b.a, "Unexpected status: " + optString2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }), b(accessToken, new com.facebook.GraphRequest.b(this) {
                final /* synthetic */ b b;

                public void onCompleted(v vVar) {
                    JSONObject b = vVar.b();
                    if (b != null) {
                        aVar.a = b.optString("access_token");
                        aVar.b = b.optInt("expires_at");
                    }
                }
            }));
            final com.facebook.AccessToken.b bVar2 = bVar;
            uVar.a(new com.facebook.u.a(this) {
                final /* synthetic */ b g;

                public void a(u uVar) {
                    AccessToken accessToken;
                    Throwable th;
                    try {
                        if (b.a().b() == null || b.a().b().j() != accessToken.j()) {
                            if (bVar2 != null) {
                                bVar2.a(new k("No current access token to refresh"));
                            }
                            this.g.n.set(false);
                            if (bVar2 != null && null != null) {
                                bVar2.a(null);
                            }
                        } else if (!atomicBoolean.get() && aVar.a == null && aVar.b == 0) {
                            if (bVar2 != null) {
                                bVar2.a(new k("Failed to refresh access token"));
                            }
                            this.g.n.set(false);
                            if (bVar2 != null && null != null) {
                                bVar2.a(null);
                            }
                        } else {
                            String str;
                            Collection collection;
                            Collection collection2;
                            Date date;
                            if (aVar.a != null) {
                                str = aVar.a;
                            } else {
                                str = accessToken.c();
                            }
                            String i = accessToken.i();
                            String j = accessToken.j();
                            if (atomicBoolean.get()) {
                                collection = hashSet;
                            } else {
                                collection = accessToken.e();
                            }
                            if (atomicBoolean.get()) {
                                collection2 = hashSet2;
                            } else {
                                collection2 = accessToken.f();
                            }
                            c g = accessToken.g();
                            if (aVar.b != 0) {
                                date = new Date(((long) aVar.b) * 1000);
                            } else {
                                date = accessToken.d();
                            }
                            AccessToken accessToken2 = new AccessToken(str, i, j, collection, collection2, g, date, new Date());
                            try {
                                b.a().a(accessToken2);
                                this.g.n.set(false);
                                if (bVar2 != null && accessToken2 != null) {
                                    bVar2.a(accessToken2);
                                }
                            } catch (Throwable th2) {
                                Throwable th3 = th2;
                                accessToken = accessToken2;
                                th = th3;
                                this.g.n.set(false);
                                bVar2.a(accessToken);
                                throw th;
                            }
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        accessToken = null;
                        this.g.n.set(false);
                        if (!(bVar2 == null || accessToken == null)) {
                            bVar2.a(accessToken);
                        }
                        throw th;
                    }
                }
            });
            uVar.h();
        } else if (bVar != null) {
            bVar.a(new k("Refresh already in progress"));
        }
    }
}
