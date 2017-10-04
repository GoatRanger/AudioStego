package com.facebook.share.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.internal.ab;
import com.facebook.internal.af;
import com.facebook.internal.ah;
import com.facebook.internal.ak;
import com.facebook.internal.x;
import com.facebook.u;
import com.facebook.v;
import com.facebook.w;
import com.facebook.y;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class d {
    private static final String A = "unlike_token";
    private static final int B = 3501;
    private static com.facebook.internal.n C = null;
    private static final ConcurrentHashMap<String, d> D = new ConcurrentHashMap();
    private static ak E = new ak(1);
    private static ak F = new ak(1);
    private static Handler G = null;
    private static String H = null;
    private static boolean I = false;
    private static volatile int J = 0;
    private static com.facebook.d K = null;
    public static final String a = "com.facebook.sdk.LikeActionController.UPDATED";
    public static final String b = "com.facebook.sdk.LikeActionController.DID_ERROR";
    public static final String c = "com.facebook.sdk.LikeActionController.DID_RESET";
    public static final String d = "com.facebook.sdk.LikeActionController.OBJECT_ID";
    public static final String e = "Invalid Object Id";
    public static final String f = "Unable to publish the like/unlike action";
    private static final String g = d.class.getSimpleName();
    private static final int h = 3;
    private static final int i = 128;
    private static final int j = 1000;
    private static final String k = "com.facebook.LikeActionController.CONTROLLER_STORE_KEY";
    private static final String l = "PENDING_CONTROLLER_KEY";
    private static final String m = "OBJECT_SUFFIX";
    private static final String n = "com.facebook.share.internal.LikeActionController.version";
    private static final String o = "object_id";
    private static final String p = "object_type";
    private static final String q = "like_count_string_with_like";
    private static final String r = "like_count_string_without_like";
    private static final String s = "social_sentence_with_like";
    private static final String t = "social_sentence_without_like";
    private static final String u = "is_object_liked";
    private static final String v = "unlike_token";
    private static final String w = "facebook_dialog_analytics_bundle";
    private static final String x = "object_is_liked";
    private static final String y = "like_count_string";
    private static final String z = "social_sentence";
    private String L;
    private com.facebook.share.widget.LikeView.e M;
    private boolean N;
    private String O;
    private String P;
    private String Q;
    private String R;
    private String S;
    private String T;
    private boolean U;
    private boolean V;
    private boolean W;
    private Bundle X;
    private com.facebook.a.b Y;

    private interface m {
        void a();
    }

    public interface c {
        void a(d dVar, com.facebook.k kVar);
    }

    private interface n {
        com.facebook.n a();

        void a(u uVar);
    }

    private abstract class a implements n {
        protected String a;
        protected com.facebook.share.widget.LikeView.e b;
        protected com.facebook.n c;
        final /* synthetic */ d d;
        private GraphRequest e;

        protected abstract void a(v vVar);

        protected a(d dVar, String str, com.facebook.share.widget.LikeView.e eVar) {
            this.d = dVar;
            this.a = str;
            this.b = eVar;
        }

        public void a(u uVar) {
            uVar.a(this.e);
        }

        public com.facebook.n a() {
            return this.c;
        }

        protected void a(GraphRequest graphRequest) {
            this.e = graphRequest;
            graphRequest.b(af.C);
            graphRequest.a(new com.facebook.GraphRequest.b(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onCompleted(v vVar) {
                    this.a.c = vVar.a();
                    if (this.a.c != null) {
                        this.a.a(this.a.c);
                    } else {
                        this.a.a(vVar);
                    }
                }
            });
        }

        protected void a(com.facebook.n nVar) {
            x.a(y.REQUESTS, d.g, "Error running request for object '%s' with type '%s' : %s", this.a, this.b, nVar);
        }
    }

    private static class b implements Runnable {
        private String a;
        private com.facebook.share.widget.LikeView.e b;
        private c c;

        b(String str, com.facebook.share.widget.LikeView.e eVar, c cVar) {
            this.a = str;
            this.b = eVar;
            this.c = cVar;
        }

        public void run() {
            d.c(this.a, this.b, this.c);
        }
    }

    private class d extends a {
        String e = this.i.O;
        String f = this.i.P;
        String g = this.i.Q;
        String h = this.i.R;
        final /* synthetic */ d i;

        d(d dVar, String str, com.facebook.share.widget.LikeView.e eVar) {
            this.i = dVar;
            super(dVar, str, eVar);
            Bundle bundle = new Bundle();
            bundle.putString(GraphRequest.c, "engagement.fields(count_string_with_like,count_string_without_like,social_sentence_with_like,social_sentence_without_like)");
            bundle.putString(dji.pilot2.publics.b.a.k, Locale.getDefault().toString());
            a(new GraphRequest(AccessToken.a(), str, bundle, w.a));
        }

        protected void a(v vVar) {
            JSONObject b = ah.b(vVar.b(), "engagement");
            if (b != null) {
                this.e = b.optString("count_string_with_like", this.e);
                this.f = b.optString("count_string_without_like", this.f);
                this.g = b.optString(d.s, this.g);
                this.h = b.optString(d.t, this.h);
            }
        }

        protected void a(com.facebook.n nVar) {
            x.a(y.REQUESTS, d.g, "Error fetching engagement for object '%s' with type '%s' : %s", this.a, this.b, nVar);
            this.i.a("get_engagement", nVar);
        }
    }

    private class e extends a {
        String e;
        final /* synthetic */ d f;

        e(d dVar, String str, com.facebook.share.widget.LikeView.e eVar) {
            this.f = dVar;
            super(dVar, str, eVar);
            Bundle bundle = new Bundle();
            bundle.putString(GraphRequest.c, "og_object.fields(id)");
            bundle.putString("ids", str);
            a(new GraphRequest(AccessToken.a(), "", bundle, w.a));
        }

        protected void a(com.facebook.n nVar) {
            if (nVar.f().contains("og_object")) {
                this.c = null;
                return;
            }
            x.a(y.REQUESTS, d.g, "Error getting the FB id for object '%s' with type '%s' : %s", this.a, this.b, nVar);
        }

        protected void a(v vVar) {
            JSONObject b = ah.b(vVar.b(), this.a);
            if (b != null) {
                b = b.optJSONObject("og_object");
                if (b != null) {
                    this.e = b.optString("id");
                }
            }
        }
    }

    private interface i extends n {
        boolean b();

        String c();
    }

    private class f extends a implements i {
        final /* synthetic */ d e;
        private boolean f = this.e.N;
        private String g;
        private final String h;
        private final com.facebook.share.widget.LikeView.e i;

        f(d dVar, String str, com.facebook.share.widget.LikeView.e eVar) {
            this.e = dVar;
            super(dVar, str, eVar);
            this.h = str;
            this.i = eVar;
            Bundle bundle = new Bundle();
            bundle.putString(GraphRequest.c, "id,application");
            bundle.putString("object", this.h);
            a(new GraphRequest(AccessToken.a(), "me/og.likes", bundle, w.a));
        }

        protected void a(v vVar) {
            JSONArray c = ah.c(vVar.b(), "data");
            if (c != null) {
                for (int i = 0; i < c.length(); i++) {
                    JSONObject optJSONObject = c.optJSONObject(i);
                    if (optJSONObject != null) {
                        this.f = true;
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("application");
                        AccessToken a = AccessToken.a();
                        if (!(optJSONObject2 == null || a == null || !ah.a(a.i(), optJSONObject2.optString("id")))) {
                            this.g = optJSONObject.optString("id");
                        }
                    }
                }
            }
        }

        protected void a(com.facebook.n nVar) {
            x.a(y.REQUESTS, d.g, "Error fetching like status for object '%s' with type '%s' : %s", this.h, this.i, nVar);
            this.e.a("get_og_object_like", nVar);
        }

        public boolean b() {
            return this.f;
        }

        public String c() {
            return this.g;
        }
    }

    private class g extends a {
        String e;
        boolean f;
        final /* synthetic */ d g;

        g(d dVar, String str, com.facebook.share.widget.LikeView.e eVar) {
            this.g = dVar;
            super(dVar, str, eVar);
            Bundle bundle = new Bundle();
            bundle.putString(GraphRequest.c, "id");
            bundle.putString("ids", str);
            a(new GraphRequest(AccessToken.a(), "", bundle, w.a));
        }

        protected void a(v vVar) {
            JSONObject b = ah.b(vVar.b(), this.a);
            if (b != null) {
                this.e = b.optString("id");
                this.f = !ah.a(this.e);
            }
        }

        protected void a(com.facebook.n nVar) {
            x.a(y.REQUESTS, d.g, "Error getting the FB id for object '%s' with type '%s' : %s", this.a, this.b, nVar);
        }
    }

    private class h extends a implements i {
        final /* synthetic */ d e;
        private boolean f = this.e.N;
        private String g;

        h(d dVar, String str) {
            this.e = dVar;
            super(dVar, str, com.facebook.share.widget.LikeView.e.PAGE);
            this.g = str;
            Bundle bundle = new Bundle();
            bundle.putString(GraphRequest.c, "id");
            a(new GraphRequest(AccessToken.a(), "me/likes/" + str, bundle, w.a));
        }

        protected void a(v vVar) {
            JSONArray c = ah.c(vVar.b(), "data");
            if (c != null && c.length() > 0) {
                this.f = true;
            }
        }

        protected void a(com.facebook.n nVar) {
            x.a(y.REQUESTS, d.g, "Error fetching like status for page id '%s': %s", this.g, nVar);
            this.e.a("get_page_like", nVar);
        }

        public boolean b() {
            return this.f;
        }

        public String c() {
            return null;
        }
    }

    private static class j implements Runnable {
        private static ArrayList<String> a = new ArrayList();
        private String b;
        private boolean c;

        j(String str, boolean z) {
            this.b = str;
            this.c = z;
        }

        public void run() {
            if (this.b != null) {
                a.remove(this.b);
                a.add(0, this.b);
            }
            if (this.c && a.size() >= 128) {
                while (64 < a.size()) {
                    d.D.remove((String) a.remove(a.size() - 1));
                }
            }
        }
    }

    private class k extends a {
        String e;
        final /* synthetic */ d f;

        k(d dVar, String str, com.facebook.share.widget.LikeView.e eVar) {
            this.f = dVar;
            super(dVar, str, eVar);
            Bundle bundle = new Bundle();
            bundle.putString("object", str);
            a(new GraphRequest(AccessToken.a(), "me/og.likes", bundle, w.b));
        }

        protected void a(v vVar) {
            this.e = ah.a(vVar.b(), "id");
        }

        protected void a(com.facebook.n nVar) {
            if (nVar.c() == d.B) {
                this.c = null;
                return;
            }
            x.a(y.REQUESTS, d.g, "Error liking object '%s' with type '%s' : %s", this.a, this.b, nVar);
            this.f.a("publish_like", nVar);
        }
    }

    private class l extends a {
        final /* synthetic */ d e;
        private String f;

        l(d dVar, String str) {
            this.e = dVar;
            super(dVar, null, null);
            this.f = str;
            a(new GraphRequest(AccessToken.a(), str, null, w.c));
        }

        protected void a(v vVar) {
        }

        protected void a(com.facebook.n nVar) {
            x.a(y.REQUESTS, d.g, "Error unliking object with unlike token '%s' : %s", this.f, nVar);
            this.e.a("publish_unlike", nVar);
        }
    }

    private static class o implements Runnable {
        private String a;
        private String b;

        o(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public void run() {
            d.b(this.a, this.b);
        }
    }

    private static void b(java.lang.String r4, java.lang.String r5) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(Unknown Source)
	at java.util.HashMap$KeyIterator.next(Unknown Source)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:80)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r1 = 0;
        r0 = C;	 Catch:{ IOException -> 0x0014, all -> 0x0022 }
        r1 = r0.b(r4);	 Catch:{ IOException -> 0x0014, all -> 0x0022 }
        r0 = r5.getBytes();	 Catch:{ IOException -> 0x0014, all -> 0x0022 }
        r1.write(r0);	 Catch:{ IOException -> 0x0014, all -> 0x0022 }
        if (r1 == 0) goto L_0x0013;
    L_0x0010:
        com.facebook.internal.ah.a(r1);
    L_0x0013:
        return;
    L_0x0014:
        r0 = move-exception;
        r2 = g;	 Catch:{ IOException -> 0x0014, all -> 0x0022 }
        r3 = "Unable to serialize controller to disk";	 Catch:{ IOException -> 0x0014, all -> 0x0022 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ IOException -> 0x0014, all -> 0x0022 }
        if (r1 == 0) goto L_0x0013;
    L_0x001e:
        com.facebook.internal.ah.a(r1);
        goto L_0x0013;
    L_0x0022:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0028;
    L_0x0025:
        com.facebook.internal.ah.a(r1);
    L_0x0028:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.share.internal.d.b(java.lang.String, java.lang.String):void");
    }

    public static boolean a(final int i, final int i2, final Intent intent) {
        if (ah.a(H)) {
            H = com.facebook.o.h().getSharedPreferences(k, 0).getString(l, null);
        }
        if (ah.a(H)) {
            return false;
        }
        a(H, com.facebook.share.widget.LikeView.e.UNKNOWN, new c() {
            public void a(d dVar, com.facebook.k kVar) {
                if (kVar == null) {
                    dVar.b(i, i2, intent);
                } else {
                    ah.a(d.g, (Exception) kVar);
                }
            }
        });
        return true;
    }

    public static void a(String str, com.facebook.share.widget.LikeView.e eVar, c cVar) {
        if (!I) {
            j();
        }
        d a = a(str);
        if (a != null) {
            a(a, eVar, cVar);
        } else {
            F.a(new b(str, eVar, cVar));
        }
    }

    private static void a(d dVar, com.facebook.share.widget.LikeView.e eVar, c cVar) {
        com.facebook.k kVar;
        d dVar2 = null;
        com.facebook.share.widget.LikeView.e a = q.a(eVar, dVar.M);
        if (a == null) {
            kVar = new com.facebook.k("Object with id:\"%s\" is already marked as type:\"%s\". Cannot change the type to:\"%s\"", dVar.L, dVar.M.toString(), eVar.toString());
        } else {
            dVar.M = a;
            kVar = null;
            dVar2 = dVar;
        }
        a(cVar, dVar2, kVar);
    }

    private static void c(String str, com.facebook.share.widget.LikeView.e eVar, c cVar) {
        d a = a(str);
        if (a != null) {
            a(a, eVar, cVar);
            return;
        }
        a = b(str);
        if (a == null) {
            a = new d(str, eVar);
            l(a);
        }
        a(str, a);
        G.post(new Runnable() {
            public void run() {
                a.o();
            }
        });
        a(cVar, a, null);
    }

    private static synchronized void j() {
        synchronized (d.class) {
            if (!I) {
                G = new Handler(Looper.getMainLooper());
                J = com.facebook.o.h().getSharedPreferences(k, 0).getInt(m, 1);
                C = new com.facebook.internal.n(g, new com.facebook.internal.n.d());
                k();
                com.facebook.internal.f.a(com.facebook.internal.f.b.Like.a(), new com.facebook.internal.f.a() {
                    public boolean a(int i, Intent intent) {
                        return d.a(com.facebook.internal.f.b.Like.a(), i, intent);
                    }
                });
                I = true;
            }
        }
    }

    private static void a(final c cVar, final d dVar, final com.facebook.k kVar) {
        if (cVar != null) {
            G.post(new Runnable() {
                public void run() {
                    cVar.a(dVar, kVar);
                }
            });
        }
    }

    private static void k() {
        K = new com.facebook.d() {
            protected void a(AccessToken accessToken, AccessToken accessToken2) {
                Context h = com.facebook.o.h();
                if (accessToken2 == null) {
                    d.J = (d.J + 1) % 1000;
                    h.getSharedPreferences(d.k, 0).edit().putInt(d.m, d.J).apply();
                    d.D.clear();
                    d.C.b();
                }
                d.d(null, d.c);
            }
        };
    }

    private static void a(String str, d dVar) {
        String d = d(str);
        E.a(new j(d, true));
        D.put(d, dVar);
    }

    private static d a(String str) {
        String d = d(str);
        d dVar = (d) D.get(d);
        if (dVar != null) {
            E.a(new j(d, false));
        }
        return dVar;
    }

    private static void l(d dVar) {
        String m = m(dVar);
        String d = d(dVar.L);
        if (!ah.a(m) && !ah.a(d)) {
            F.a(new o(d, m));
        }
    }

    private static d b(String str) {
        Throwable e;
        Throwable th;
        d dVar = null;
        Closeable a;
        try {
            a = C.a(d(str));
            if (a != null) {
                try {
                    String a2 = ah.a((InputStream) a);
                    if (!ah.a(a2)) {
                        dVar = c(a2);
                    }
                } catch (IOException e2) {
                    e = e2;
                    try {
                        Log.e(g, "Unable to deserialize controller from disk", e);
                        if (a != null) {
                            ah.a(a);
                        }
                        return dVar;
                    } catch (Throwable th2) {
                        th = th2;
                        if (a != null) {
                            ah.a(a);
                        }
                        throw th;
                    }
                }
            }
            if (a != null) {
                ah.a(a);
            }
        } catch (IOException e3) {
            e = e3;
            a = null;
            Log.e(g, "Unable to deserialize controller from disk", e);
            if (a != null) {
                ah.a(a);
            }
            return dVar;
        } catch (Throwable e4) {
            a = null;
            th = e4;
            if (a != null) {
                ah.a(a);
            }
            throw th;
        }
        return dVar;
    }

    private static d c(String str) {
        d dVar;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(n, -1) != 3) {
                return null;
            }
            dVar = new d(jSONObject.getString("object_id"), com.facebook.share.widget.LikeView.e.fromInt(jSONObject.optInt("object_type", com.facebook.share.widget.LikeView.e.UNKNOWN.a())));
            dVar.O = jSONObject.optString(q, null);
            dVar.P = jSONObject.optString(r, null);
            dVar.Q = jSONObject.optString(s, null);
            dVar.R = jSONObject.optString(t, null);
            dVar.N = jSONObject.optBoolean(u);
            dVar.S = jSONObject.optString("unlike_token", null);
            jSONObject = jSONObject.optJSONObject(w);
            if (jSONObject != null) {
                dVar.X = com.facebook.internal.e.a(jSONObject);
            }
            return dVar;
        } catch (Throwable e) {
            Log.e(g, "Unable to deserialize controller from JSON", e);
            dVar = null;
        }
    }

    private static String m(d dVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(n, 3);
            jSONObject.put("object_id", dVar.L);
            jSONObject.put("object_type", dVar.M.a());
            jSONObject.put(q, dVar.O);
            jSONObject.put(r, dVar.P);
            jSONObject.put(s, dVar.Q);
            jSONObject.put(t, dVar.R);
            jSONObject.put(u, dVar.N);
            jSONObject.put("unlike_token", dVar.S);
            if (dVar.X != null) {
                JSONObject a = com.facebook.internal.e.a(dVar.X);
                if (a != null) {
                    jSONObject.put(w, a);
                }
            }
            return jSONObject.toString();
        } catch (Throwable e) {
            Log.e(g, "Unable to serialize controller to JSON", e);
            return null;
        }
    }

    private static String d(String str) {
        String str2 = null;
        AccessToken a = AccessToken.a();
        if (a != null) {
            str2 = a.c();
        }
        if (str2 != null) {
            str2 = ah.b(str2);
        }
        return String.format(Locale.ROOT, "%s|%s|com.fb.sdk.like|%d", new Object[]{str, ah.a(str2, ""), Integer.valueOf(J)});
    }

    private static void d(d dVar, String str) {
        c(dVar, str, null);
    }

    private static void c(d dVar, String str, Bundle bundle) {
        Intent intent = new Intent(str);
        if (dVar != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString(d, dVar.a());
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        LocalBroadcastManager.getInstance(com.facebook.o.h()).sendBroadcast(intent);
    }

    private d(String str, com.facebook.share.widget.LikeView.e eVar) {
        this.L = str;
        this.M = eVar;
    }

    public String a() {
        return this.L;
    }

    public String b() {
        return this.N ? this.O : this.P;
    }

    public String c() {
        return this.N ? this.Q : this.R;
    }

    public boolean d() {
        return this.N;
    }

    public boolean e() {
        if (e.e() || e.f()) {
            return true;
        }
        if (this.U || this.M == com.facebook.share.widget.LikeView.e.PAGE) {
            return false;
        }
        AccessToken a = AccessToken.a();
        if (a == null || a.e() == null || !a.e().contains("publish_actions")) {
            return false;
        }
        return true;
    }

    public void a(Activity activity, com.facebook.internal.o oVar, Bundle bundle) {
        boolean z = true;
        boolean z2 = !this.N;
        if (n()) {
            b(z2);
            if (this.W) {
                l().a(com.facebook.internal.a.I, null, bundle);
                return;
            } else if (!a(z2, bundle)) {
                if (z2) {
                    z = false;
                }
                b(z);
                b(activity, oVar, bundle);
                return;
            } else {
                return;
            }
        }
        b(activity, oVar, bundle);
    }

    private com.facebook.a.b l() {
        if (this.Y == null) {
            this.Y = com.facebook.a.b.c(com.facebook.o.h());
        }
        return this.Y;
    }

    private boolean a(boolean z, Bundle bundle) {
        if (n()) {
            if (z) {
                c(bundle);
                return true;
            } else if (!ah.a(this.S)) {
                d(bundle);
                return true;
            }
        }
        return false;
    }

    private void a(boolean z) {
        b(z);
        Bundle bundle = new Bundle();
        bundle.putString(ab.ah, f);
        c(this, b, bundle);
    }

    private void b(boolean z) {
        a(z, this.O, this.P, this.Q, this.R, this.S);
    }

    private void a(boolean z, String str, String str2, String str3, String str4, String str5) {
        Object obj;
        Object a = ah.a(str, null);
        Object a2 = ah.a(str2, null);
        Object a3 = ah.a(str3, null);
        Object a4 = ah.a(str4, null);
        Object a5 = ah.a(str5, null);
        if (z == this.N && ah.a(a, this.O) && ah.a(a2, this.P) && ah.a(a3, this.Q) && ah.a(a4, this.R) && ah.a(a5, this.S)) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj != null) {
            this.N = z;
            this.O = a;
            this.P = a2;
            this.Q = a3;
            this.R = a4;
            this.S = a5;
            l(this);
            d(this, a);
        }
    }

    private void b(Activity activity, com.facebook.internal.o oVar, Bundle bundle) {
        String str;
        if (e.e()) {
            str = com.facebook.internal.a.F;
        } else if (e.f()) {
            str = com.facebook.internal.a.G;
        } else {
            a("present_dialog", bundle);
            ah.c(g, "Cannot show the Like Dialog on this device.");
            d(null, a);
            str = null;
        }
        if (str != null) {
            if (this.M != null) {
                str = this.M.toString();
            } else {
                str = com.facebook.share.widget.LikeView.e.UNKNOWN.toString();
            }
            LikeContent b = new com.facebook.share.internal.LikeContent.a().a(this.L).b(str).b();
            if (oVar != null) {
                new e(oVar).b(b);
            } else {
                new e(activity).b(b);
            }
            b(bundle);
            l().a(com.facebook.internal.a.F, null, bundle);
        }
    }

    private void b(int i, int i2, Intent intent) {
        q.a(i, i2, intent, a(this.X));
        m();
    }

    private m a(final Bundle bundle) {
        return new m(this, null) {
            final /* synthetic */ d b;

            public void a(com.facebook.internal.b bVar, Bundle bundle) {
                if (bundle != null && bundle.containsKey(d.x)) {
                    boolean z = bundle.getBoolean(d.x);
                    String b = this.b.O;
                    String c = this.b.P;
                    if (bundle.containsKey(d.y)) {
                        c = bundle.getString(d.y);
                        b = c;
                    }
                    String d = this.b.Q;
                    String e = this.b.R;
                    if (bundle.containsKey(d.z)) {
                        e = bundle.getString(d.z);
                        d = e;
                    }
                    String string = bundle.containsKey(d.x) ? bundle.getString("unlike_token") : this.b.S;
                    Bundle bundle2 = bundle == null ? new Bundle() : bundle;
                    bundle2.putString(com.facebook.internal.a.m, bVar.c().toString());
                    this.b.l().a(com.facebook.internal.a.J, null, bundle2);
                    this.b.a(z, b, c, d, e, string);
                }
            }

            public void a(com.facebook.internal.b bVar, com.facebook.k kVar) {
                x.a(y.REQUESTS, d.g, "Like Dialog failed with error : %s", kVar);
                Bundle bundle = bundle == null ? new Bundle() : bundle;
                bundle.putString(com.facebook.internal.a.m, bVar.c().toString());
                this.b.a("present_dialog", bundle);
                d.c(this.b, d.b, ab.a(kVar));
            }

            public void a(com.facebook.internal.b bVar) {
                a(bVar, new com.facebook.m());
            }
        };
    }

    private void b(Bundle bundle) {
        e(this.L);
        this.X = bundle;
        l(this);
    }

    private void m() {
        this.X = null;
        e(null);
    }

    private static void e(String str) {
        H = str;
        com.facebook.o.h().getSharedPreferences(k, 0).edit().putString(l, H).apply();
    }

    private boolean n() {
        AccessToken a = AccessToken.a();
        return (this.U || this.T == null || a == null || a.e() == null || !a.e().contains("publish_actions")) ? false : true;
    }

    private void c(final Bundle bundle) {
        this.W = true;
        a(new m(this) {
            final /* synthetic */ d b;

            public void a() {
                if (ah.a(this.b.T)) {
                    Bundle bundle = new Bundle();
                    bundle.putString(ab.ah, d.e);
                    d.c(this.b, d.b, bundle);
                    return;
                }
                u uVar = new u();
                final k kVar = new k(this.b, this.b.T, this.b.M);
                kVar.a(uVar);
                uVar.a(new com.facebook.u.a(this) {
                    final /* synthetic */ AnonymousClass10 b;

                    public void a(u uVar) {
                        this.b.b.W = false;
                        if (kVar.a() != null) {
                            this.b.b.a(false);
                            return;
                        }
                        this.b.b.S = ah.a(kVar.e, null);
                        this.b.b.V = true;
                        this.b.b.l().a(com.facebook.internal.a.E, null, bundle);
                        this.b.b.e(bundle);
                    }
                });
                uVar.h();
            }
        });
    }

    private void d(final Bundle bundle) {
        this.W = true;
        u uVar = new u();
        final l lVar = new l(this, this.S);
        lVar.a(uVar);
        uVar.a(new com.facebook.u.a(this) {
            final /* synthetic */ d c;

            public void a(u uVar) {
                this.c.W = false;
                if (lVar.a() != null) {
                    this.c.a(true);
                    return;
                }
                this.c.S = null;
                this.c.V = false;
                this.c.l().a(com.facebook.internal.a.H, null, bundle);
                this.c.e(bundle);
            }
        });
        uVar.h();
    }

    private void o() {
        if (AccessToken.a() == null) {
            p();
        } else {
            a(new m(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void a() {
                    i hVar;
                    switch (this.a.M) {
                        case PAGE:
                            hVar = new h(this.a, this.a.T);
                            break;
                        default:
                            hVar = new f(this.a, this.a.T, this.a.M);
                            break;
                    }
                    final d dVar = new d(this.a, this.a.T, this.a.M);
                    u uVar = new u();
                    hVar.a(uVar);
                    dVar.a(uVar);
                    uVar.a(new com.facebook.u.a(this) {
                        final /* synthetic */ AnonymousClass12 c;

                        public void a(u uVar) {
                            if (hVar.a() == null && dVar.a() == null) {
                                this.c.a.a(hVar.b(), dVar.e, dVar.f, dVar.g, dVar.h, hVar.c());
                                return;
                            }
                            x.a(y.REQUESTS, d.g, "Unable to refresh like state for id: '%s'", this.c.a.L);
                        }
                    });
                    uVar.h();
                }
            });
        }
    }

    private void p() {
        g gVar = new g(com.facebook.o.h(), com.facebook.o.k(), this.L);
        if (gVar.b()) {
            gVar.a(new com.facebook.internal.ad.a(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void a(Bundle bundle) {
                    if (bundle != null && bundle.containsKey(n.X)) {
                        this.a.a(bundle.getBoolean(n.X), bundle.containsKey(n.Y) ? bundle.getString(n.Y) : this.a.O, bundle.containsKey(n.Z) ? bundle.getString(n.Z) : this.a.P, bundle.containsKey(n.aa) ? bundle.getString(n.aa) : this.a.Q, bundle.containsKey(n.ab) ? bundle.getString(n.ab) : this.a.R, bundle.containsKey(n.ac) ? bundle.getString(n.ac) : this.a.S);
                    }
                }
            });
        }
    }

    private void e(Bundle bundle) {
        if (this.N != this.V && !a(this.N, bundle)) {
            a(!this.N);
        }
    }

    private void a(final m mVar) {
        if (ah.a(this.T)) {
            final e eVar = new e(this, this.L, this.M);
            final g gVar = new g(this, this.L, this.M);
            u uVar = new u();
            eVar.a(uVar);
            gVar.a(uVar);
            uVar.a(new com.facebook.u.a(this) {
                final /* synthetic */ d d;

                public void a(u uVar) {
                    this.d.T = eVar.e;
                    if (ah.a(this.d.T)) {
                        this.d.T = gVar.e;
                        this.d.U = gVar.f;
                    }
                    if (ah.a(this.d.T)) {
                        x.a(y.DEVELOPER_ERRORS, d.g, "Unable to verify the FB id for '%s'. Verify that it is a valid FB object or page", this.d.L);
                        this.d.a("get_verified_id", gVar.a() != null ? gVar.a() : eVar.a());
                    }
                    if (mVar != null) {
                        mVar.a();
                    }
                }
            });
            uVar.h();
        } else if (mVar != null) {
            mVar.a();
        }
    }

    private void a(String str, Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        bundle2.putString("object_id", this.L);
        bundle2.putString("object_type", this.M.toString());
        bundle2.putString(com.facebook.internal.a.Q, str);
        l().a(com.facebook.internal.a.K, null, bundle2);
    }

    private void a(String str, com.facebook.n nVar) {
        Bundle bundle = new Bundle();
        if (nVar != null) {
            JSONObject k = nVar.k();
            if (k != null) {
                bundle.putString("error", k.toString());
            }
        }
        a(str, bundle);
    }
}
