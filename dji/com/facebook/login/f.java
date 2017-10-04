package com.facebook.login;

import android.app.Activity;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.FacebookActivity;
import com.facebook.Profile;
import com.facebook.g;
import com.facebook.h;
import com.facebook.internal.ai;
import com.facebook.internal.o;
import com.facebook.k;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import com.facebook.share.internal.n;
import com.facebook.v;
import com.tencent.android.tpush.common.Constants;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class f {
    private static final String a = "publish";
    private static final String b = "manage";
    private static final Set<String> c = d();
    private static volatile f d;
    private c e = c.NATIVE_WITH_FALLBACK;
    private a f = a.FRIENDS;

    private static class a implements h {
        private final Activity a;

        a(Activity activity) {
            ai.a((Object) activity, Constants.FLAG_ACTIVITY_NAME);
            this.a = activity;
        }

        public void a(Intent intent, int i) {
            this.a.startActivityForResult(intent, i);
        }

        public Activity a() {
            return this.a;
        }
    }

    private static class b implements h {
        private final o a;

        b(o oVar) {
            ai.a((Object) oVar, "fragment");
            this.a = oVar;
        }

        public void a(Intent intent, int i) {
            this.a.a(intent, i);
        }

        public Activity a() {
            return this.a.c();
        }
    }

    private static class c {
        private static volatile e a;

        private c() {
        }

        private static synchronized e b(Context context) {
            e eVar;
            synchronized (c.class) {
                if (context == null) {
                    context = com.facebook.o.h();
                }
                if (context == null) {
                    eVar = null;
                } else {
                    if (a == null) {
                        a = new e(context, com.facebook.o.k());
                    }
                    eVar = a;
                }
            }
            return eVar;
        }
    }

    f() {
        ai.b();
    }

    public static f getInstance() {
        if (d == null) {
            synchronized (f.class) {
                if (d == null) {
                    d = new f();
                }
            }
        }
        return d;
    }

    public void a(Activity activity, v vVar) {
        a(new a(activity), a(vVar));
    }

    public void a(Fragment fragment, v vVar) {
        a(new o(fragment), vVar);
    }

    private void a(o oVar, v vVar) {
        a(new b(oVar), a(vVar));
    }

    private Request a(v vVar) {
        ai.a((Object) vVar, "response");
        AccessToken f = vVar.e().f();
        return c(f != null ? f.e() : null);
    }

    public void a(com.facebook.f fVar, final h<g> hVar) {
        if (fVar instanceof com.facebook.internal.f) {
            ((com.facebook.internal.f) fVar).b(com.facebook.internal.f.b.Login.a(), new com.facebook.internal.f.a(this) {
                final /* synthetic */ f b;

                public boolean a(int i, Intent intent) {
                    return this.b.a(i, intent, hVar);
                }
            });
            return;
        }
        throw new k("Unexpected CallbackManager, please use the provided Factory.");
    }

    boolean a(int i, Intent intent) {
        return a(i, intent, null);
    }

    boolean a(int i, Intent intent, h<g> hVar) {
        boolean z;
        Request request;
        AccessToken accessToken;
        Exception exception;
        a aVar;
        Map map;
        Exception exception2 = null;
        AccessToken accessToken2 = null;
        a aVar2 = a.ERROR;
        boolean z2 = false;
        if (intent != null) {
            AccessToken accessToken3;
            Map map2;
            Request request2;
            Exception exception3;
            a aVar3;
            Result result = (Result) intent.getParcelableExtra("com.facebook.LoginFragment:Result");
            if (result != null) {
                Request request3 = result.e;
                a aVar4 = result.a;
                if (i == -1) {
                    if (result.a == a.SUCCESS) {
                        accessToken2 = result.b;
                    } else {
                        exception2 = new g(result.c);
                    }
                } else if (i == 0) {
                    z2 = true;
                }
                Request request4 = request3;
                accessToken3 = accessToken2;
                map2 = result.f;
                request2 = request4;
                a aVar5 = aVar4;
                exception3 = exception2;
                aVar3 = aVar5;
            } else {
                request2 = null;
                accessToken3 = null;
                map2 = null;
                exception3 = null;
                aVar3 = aVar2;
            }
            z = z2;
            request = request2;
            accessToken = accessToken3;
            exception = exception3;
            Map map3 = map2;
            aVar = aVar3;
            map = map3;
        } else if (i == 0) {
            z = true;
            accessToken = null;
            aVar = a.CANCEL;
            request = null;
            exception = null;
            map = null;
        } else {
            z = false;
            accessToken = null;
            aVar = aVar2;
            request = null;
            exception = null;
            map = null;
        }
        if (exception == null && accessToken == null && !z) {
            exception = new k("Unexpected call to LoginManager.onActivityResult");
        }
        a(null, aVar, map, exception, true, request);
        a(accessToken, request, exception, z, hVar);
        return true;
    }

    public c a() {
        return this.e;
    }

    public f a(c cVar) {
        this.e = cVar;
        return this;
    }

    public a b() {
        return this.f;
    }

    public f a(a aVar) {
        this.f = aVar;
        return this;
    }

    public void c() {
        AccessToken.a(null);
        Profile.a(null);
    }

    public void a(Fragment fragment, Collection<String> collection) {
        a(new o(fragment), (Collection) collection);
    }

    private void a(o oVar, Collection<String> collection) {
        a((Collection) collection);
        a(new b(oVar), c(collection));
    }

    public void a(Activity activity, Collection<String> collection) {
        a((Collection) collection);
        a(new a(activity), c(collection));
    }

    public void b(Fragment fragment, Collection<String> collection) {
        b(new o(fragment), (Collection) collection);
    }

    private void b(o oVar, Collection<String> collection) {
        b(collection);
        a(new b(oVar), c(collection));
    }

    public void b(Activity activity, Collection<String> collection) {
        b(collection);
        a(new a(activity), c(collection));
    }

    private void a(Collection<String> collection) {
        if (collection != null) {
            for (String a : collection) {
                if (a(a)) {
                    throw new k(String.format("Cannot pass a publish or manage permission (%s) to a request for read authorization", new Object[]{(String) r1.next()}));
                }
            }
        }
    }

    private void b(Collection<String> collection) {
        if (collection != null) {
            for (String a : collection) {
                if (!a(a)) {
                    throw new k(String.format("Cannot pass a read permission (%s) to a request for publish authorization", new Object[]{(String) r1.next()}));
                }
            }
        }
    }

    static boolean a(String str) {
        return str != null && (str.startsWith(a) || str.startsWith(b) || c.contains(str));
    }

    private static Set<String> d() {
        return Collections.unmodifiableSet(new HashSet<String>() {
            {
                add("ads_management");
                add("create_event");
                add("rsvp_event");
            }
        });
    }

    private Request c(Collection<String> collection) {
        Request request = new Request(this.e, Collections.unmodifiableSet(collection != null ? new HashSet(collection) : new HashSet()), this.f, com.facebook.o.k(), UUID.randomUUID().toString());
        request.a(AccessToken.a() != null);
        return request;
    }

    private void a(h hVar, Request request) throws k {
        a(hVar.a(), request);
        com.facebook.internal.f.a(com.facebook.internal.f.b.Login.a(), new com.facebook.internal.f.a(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public boolean a(int i, Intent intent) {
                return this.a.a(i, intent);
            }
        });
        if (!b(hVar, request)) {
            Exception kVar = new k("Log in attempt failed: FacebookActivity could not be started. Please make sure you added FacebookActivity to the AndroidManifest.");
            a(hVar.a(), a.ERROR, null, kVar, false, request);
            throw kVar;
        }
    }

    private void a(Context context, Request request) {
        e a = c.b(context);
        if (a != null && request != null) {
            a.a(request);
        }
    }

    private void a(Context context, a aVar, Map<String, String> map, Exception exception, boolean z, Request request) {
        e a = c.b(context);
        if (a != null) {
            if (request == null) {
                a.b("fb_mobile_login_complete", "Unexpected call to logCompleteLogin with null pendingAuthorizationRequest.");
                return;
            }
            Map hashMap = new HashMap();
            hashMap.put("try_login_activity", z ? "1" : "0");
            a.a(request.e(), hashMap, aVar, map, exception);
        }
    }

    private boolean b(h hVar, Request request) {
        Intent a = a(request);
        if (!a(a)) {
            return false;
        }
        try {
            hVar.a(a, LoginClient.d());
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }

    private boolean a(Intent intent) {
        if (com.facebook.o.h().getPackageManager().resolveActivity(intent, 0) == null) {
            return false;
        }
        return true;
    }

    private Intent a(Request request) {
        Intent intent = new Intent();
        intent.setClass(com.facebook.o.h(), FacebookActivity.class);
        intent.setAction(request.b().toString());
        Bundle bundle = new Bundle();
        bundle.putParcelable(n.r, request);
        intent.putExtras(bundle);
        return intent;
    }

    static g a(Request request, AccessToken accessToken) {
        Collection a = request.a();
        Object hashSet = new HashSet(accessToken.e());
        if (request.f()) {
            hashSet.retainAll(a);
        }
        Set hashSet2 = new HashSet(a);
        hashSet2.removeAll(hashSet);
        return new g(accessToken, hashSet, hashSet2);
    }

    private void a(AccessToken accessToken, Request request, k kVar, boolean z, h<g> hVar) {
        if (accessToken != null) {
            AccessToken.a(accessToken);
            Profile.b();
        }
        if (hVar != null) {
            g a = accessToken != null ? a(request, accessToken) : null;
            if (z || (a != null && a.b().size() == 0)) {
                hVar.onCancel();
            } else if (kVar != null) {
                hVar.onError(kVar);
            } else if (accessToken != null) {
                hVar.onSuccess(a);
            }
        }
    }
}
