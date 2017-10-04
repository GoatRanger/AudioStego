package com.facebook.a;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import bolts.AppLinks;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.internal.ah;
import com.facebook.internal.ai;
import com.facebook.internal.x;
import com.facebook.k;
import com.facebook.n;
import com.facebook.o;
import com.facebook.v;
import com.facebook.y;
import dji.midware.data.forbid.FlyForbidProtocol;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    public static final String a = "com.facebook.sdk.appEventPreferences";
    public static final String b = "com.facebook.sdk.APP_EVENTS_FLUSHED";
    public static final String c = "com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED";
    public static final String d = "com.facebook.sdk.APP_EVENTS_FLUSH_RESULT";
    private static final String e = b.class.getCanonicalName();
    private static final int f = 100;
    private static final int g = 15;
    private static final int h = 86400;
    private static final int i = 30;
    private static final String j = "_fbSourceApplicationHasBeenSet";
    private static Map<a, i> m = new ConcurrentHashMap();
    private static ScheduledThreadPoolExecutor n;
    private static c o = c.AUTO;
    private static boolean p;
    private static Context q;
    private static Object r = new Object();
    private static String s;
    private static String t;
    private static boolean u;
    private static boolean v;
    private final String k;
    private final a l;

    private static class a implements Serializable {
        private static final long a = 1;
        private final String b;
        private final String c;

        private static class a implements Serializable {
            private static final long a = -2488473066578201069L;
            private final String b;
            private final String c;

            private a(String str, String str2) {
                this.b = str;
                this.c = str2;
            }

            private Object a() {
                return new a(this.b, this.c);
            }
        }

        a(AccessToken accessToken) {
            this(accessToken.c(), o.k());
        }

        a(String str, String str2) {
            if (ah.a(str)) {
                str = null;
            }
            this.b = str;
            this.c = str2;
        }

        String a() {
            return this.b;
        }

        String b() {
            return this.c;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.b == null ? 0 : this.b.hashCode();
            if (this.c != null) {
                i = this.c.hashCode();
            }
            return hashCode ^ i;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (ah.a(aVar.b, this.b) && ah.a(aVar.c, this.c)) {
                return true;
            }
            return false;
        }

        private Object c() {
            return new a(this.b, this.c);
        }
    }

    static class b implements Serializable {
        private static final long a = 1;
        private static final HashSet<String> d = new HashSet();
        private JSONObject b;
        private boolean c;
        private String e;

        private static class a implements Serializable {
            private static final long a = -2488473066578201069L;
            private final String b;
            private final boolean c;

            private a(String str, boolean z) {
                this.b = str;
                this.c = z;
            }

            private Object a() throws JSONException {
                return new b(this.b, this.c);
            }
        }

        public b(String str, String str2, Double d, Bundle bundle, boolean z) {
            try {
                a(str2);
                this.e = str2;
                this.c = z;
                this.b = new JSONObject();
                this.b.put("_eventName", str2);
                this.b.put("_logTime", System.currentTimeMillis() / 1000);
                this.b.put("_ui", str);
                if (d != null) {
                    this.b.put("_valueToSum", d.doubleValue());
                }
                if (this.c) {
                    this.b.put("_implicitlyLogged", "1");
                }
                if (bundle != null) {
                    for (String str3 : bundle.keySet()) {
                        a(str3);
                        Object obj = bundle.get(str3);
                        if ((obj instanceof String) || (obj instanceof Number)) {
                            this.b.put(str3, obj.toString());
                        } else {
                            throw new k(String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", new Object[]{obj, str3}));
                        }
                    }
                }
                if (!this.c) {
                    x.a(y.APP_EVENTS, "AppEvents", "Created app event '%s'", this.b.toString());
                }
            } catch (JSONException e) {
                x.a(y.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", e.toString());
                this.b = null;
            } catch (k e2) {
                x.a(y.APP_EVENTS, "AppEvents", "Invalid app event name or parameter:", e2.toString());
                this.b = null;
            }
        }

        public String a() {
            return this.e;
        }

        private b(String str, boolean z) throws JSONException {
            this.b = new JSONObject(str);
            this.c = z;
        }

        public boolean b() {
            return this.c;
        }

        public JSONObject c() {
            return this.b;
        }

        private void a(String str) throws k {
            String str2 = "^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$";
            if (str == null || str.length() == 0 || str.length() > 40) {
                if (str == null) {
                    str = "<None Provided>";
                }
                throw new k(String.format(Locale.ROOT, "Identifier '%s' must be less than %d characters", new Object[]{str, Integer.valueOf(40)}));
            }
            synchronized (d) {
                boolean contains = d.contains(str);
            }
            if (!contains) {
                if (str.matches("^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$")) {
                    synchronized (d) {
                        d.add(str);
                    }
                    return;
                }
                throw new k(String.format("Skipping event named '%s' due to illegal name - must be under 40 chars and alphanumeric, _, - or space, and not start with a space or hyphen.", new Object[]{str}));
            }
        }

        private Object d() {
            return new a(this.b.toString(), this.c);
        }

        public String toString() {
            return String.format("\"%s\", implicit: %b, json: %s", new Object[]{this.b.optString("_eventName"), Boolean.valueOf(this.c), this.b.toString()});
        }
    }

    public enum c {
        AUTO,
        EXPLICIT_ONLY
    }

    private enum d {
        EXPLICIT,
        TIMER,
        SESSION_CHANGE,
        PERSISTED_EVENTS,
        EVENT_THRESHOLD,
        EAGER_FLUSHING_EVENT
    }

    private enum e {
        SUCCESS,
        SERVER_ERROR,
        NO_CONNECTIVITY,
        UNKNOWN_ERROR
    }

    private static class f {
        public int a;
        public e b;

        private f() {
            this.a = 0;
            this.b = e.SUCCESS;
        }
    }

    static class g {
        private static final String a = "AppEventsLogger.persistedsessioninfo";
        private static final Object b = new Object();
        private static boolean c = false;
        private static boolean d = false;
        private static Map<a, c> e;
        private static final Runnable f = new Runnable() {
            public void run() {
                g.a(b.q);
            }
        };

        g() {
        }

        private static void b(Context context) {
            Exception e;
            Closeable closeable = null;
            synchronized (b) {
                if (!d) {
                    Closeable objectInputStream;
                    try {
                        objectInputStream = new ObjectInputStream(context.openFileInput(a));
                        try {
                            e = (HashMap) objectInputStream.readObject();
                            x.a(y.APP_EVENTS, "AppEvents", "App session info loaded");
                            ah.a(objectInputStream);
                            context.deleteFile(a);
                            if (e == null) {
                                e = new HashMap();
                            }
                            d = true;
                            c = false;
                        } catch (FileNotFoundException e2) {
                            closeable = objectInputStream;
                            ah.a(closeable);
                            context.deleteFile(a);
                            if (e == null) {
                                e = new HashMap();
                            }
                            d = true;
                            c = false;
                        } catch (Exception e3) {
                            e = e3;
                            try {
                                Log.d(b.e, "Got unexpected exception: " + e.toString());
                                ah.a(objectInputStream);
                                context.deleteFile(a);
                                if (e == null) {
                                    e = new HashMap();
                                }
                                d = true;
                                c = false;
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                ah.a(objectInputStream);
                                context.deleteFile(a);
                                if (e == null) {
                                    e = new HashMap();
                                }
                                d = true;
                                c = false;
                                throw th2;
                            }
                        }
                    } catch (FileNotFoundException e4) {
                        ah.a(closeable);
                        context.deleteFile(a);
                        if (e == null) {
                            e = new HashMap();
                        }
                        d = true;
                        c = false;
                    } catch (Exception e5) {
                        Exception exception = e5;
                        objectInputStream = null;
                        e = exception;
                        Log.d(b.e, "Got unexpected exception: " + e.toString());
                        ah.a(objectInputStream);
                        context.deleteFile(a);
                        if (e == null) {
                            e = new HashMap();
                        }
                        d = true;
                        c = false;
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        objectInputStream = null;
                        th2 = th4;
                        ah.a(objectInputStream);
                        context.deleteFile(a);
                        if (e == null) {
                            e = new HashMap();
                        }
                        d = true;
                        c = false;
                        throw th2;
                    }
                }
            }
        }

        static void a(Context context) {
            Closeable objectOutputStream;
            Exception e;
            Throwable th;
            synchronized (b) {
                if (c) {
                    try {
                        objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(context.openFileOutput(a, 0)));
                        try {
                            objectOutputStream.writeObject(e);
                            c = false;
                            x.a(y.APP_EVENTS, "AppEvents", "App session info saved");
                            ah.a(objectOutputStream);
                        } catch (Exception e2) {
                            e = e2;
                            try {
                                Log.d(b.e, "Got unexpected exception: " + e.toString());
                                ah.a(objectOutputStream);
                            } catch (Throwable th2) {
                                th = th2;
                                ah.a(objectOutputStream);
                                throw th;
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        objectOutputStream = null;
                        Log.d(b.e, "Got unexpected exception: " + e.toString());
                        ah.a(objectOutputStream);
                    } catch (Throwable th3) {
                        th = th3;
                        objectOutputStream = null;
                        ah.a(objectOutputStream);
                        throw th;
                    }
                }
            }
        }

        static void a(Context context, a aVar, b bVar, long j, String str) {
            synchronized (b) {
                a(context, aVar).a(bVar, j, str);
                a();
            }
        }

        static void a(Context context, a aVar, b bVar, long j) {
            synchronized (b) {
                a(context, aVar).a(bVar, j);
                a();
            }
        }

        private static c a(Context context, a aVar) {
            b(context);
            c cVar = (c) e.get(aVar);
            if (cVar != null) {
                return cVar;
            }
            cVar = new c();
            e.put(aVar, cVar);
            return cVar;
        }

        private static void a() {
            if (!c) {
                c = true;
                b.n.schedule(f, 30, TimeUnit.SECONDS);
            }
        }
    }

    static class h {
        static final String a = "AppEventsLogger.persistedevents";
        private Context b;
        private HashMap<a, List<b>> c = new HashMap();

        private h(Context context) {
            this.b = context;
        }

        public static h a(Context context) {
            h hVar;
            synchronized (b.r) {
                hVar = new h(context);
                hVar.c();
            }
            return hVar;
        }

        public static void a(Context context, a aVar, i iVar) {
            Map hashMap = new HashMap();
            hashMap.put(aVar, iVar);
            a(context, hashMap);
        }

        public static void a(Context context, Map<a, i> map) {
            synchronized (b.r) {
                h a = a(context);
                for (Entry entry : map.entrySet()) {
                    List b = ((i) entry.getValue()).b();
                    if (b.size() != 0) {
                        a.a((a) entry.getKey(), b);
                    }
                }
                a.b();
            }
        }

        public Set<a> a() {
            return this.c.keySet();
        }

        public List<b> a(a aVar) {
            return (List) this.c.get(aVar);
        }

        private void b() {
            Closeable objectOutputStream;
            Exception e;
            Throwable th;
            try {
                objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(this.b.openFileOutput(a, 0)));
                try {
                    objectOutputStream.writeObject(this.c);
                    ah.a(objectOutputStream);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        Log.d(b.e, "Got unexpected exception: " + e.toString());
                        ah.a(objectOutputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        ah.a(objectOutputStream);
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                objectOutputStream = null;
                Log.d(b.e, "Got unexpected exception: " + e.toString());
                ah.a(objectOutputStream);
            } catch (Throwable th3) {
                th = th3;
                objectOutputStream = null;
                ah.a(objectOutputStream);
                throw th;
            }
        }

        private void c() {
            Closeable objectInputStream;
            Exception e;
            Throwable th;
            Closeable closeable = null;
            try {
                objectInputStream = new ObjectInputStream(new BufferedInputStream(this.b.openFileInput(a)));
                try {
                    HashMap hashMap = (HashMap) objectInputStream.readObject();
                    this.b.getFileStreamPath(a).delete();
                    this.c = hashMap;
                    ah.a(objectInputStream);
                } catch (FileNotFoundException e2) {
                    closeable = objectInputStream;
                    ah.a(closeable);
                } catch (Exception e3) {
                    e = e3;
                    try {
                        Log.d(b.e, "Got unexpected exception: " + e.toString());
                        ah.a(objectInputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        ah.a(objectInputStream);
                        throw th;
                    }
                }
            } catch (FileNotFoundException e4) {
                ah.a(closeable);
            } catch (Exception e5) {
                Exception exception = e5;
                objectInputStream = null;
                e = exception;
                Log.d(b.e, "Got unexpected exception: " + e.toString());
                ah.a(objectInputStream);
            } catch (Throwable th3) {
                Throwable th4 = th3;
                objectInputStream = null;
                th = th4;
                ah.a(objectInputStream);
                throw th;
            }
        }

        public void a(a aVar, List<b> list) {
            if (!this.c.containsKey(aVar)) {
                this.c.put(aVar, new ArrayList());
            }
            ((List) this.c.get(aVar)).addAll(list);
        }
    }

    private static class i {
        public static final String a = "event_count";
        public static final String b = "encoded_events";
        public static final String c = "num_skipped";
        private List<b> d = new ArrayList();
        private List<b> e = new ArrayList();
        private int f;
        private com.facebook.internal.d g;
        private String h;
        private String i;
        private final int j = 1000;

        public i(com.facebook.internal.d dVar, String str, String str2) {
            this.g = dVar;
            this.h = str;
            this.i = str2;
        }

        public synchronized void a(b bVar) {
            if (this.d.size() + this.e.size() >= 1000) {
                this.f++;
            } else {
                this.d.add(bVar);
            }
        }

        public synchronized int a() {
            return this.d.size();
        }

        public synchronized void a(boolean z) {
            if (z) {
                this.d.addAll(this.e);
            }
            this.e.clear();
            this.f = 0;
        }

        public int a(GraphRequest graphRequest, boolean z, boolean z2) {
            synchronized (this) {
                int i = this.f;
                this.e.addAll(this.d);
                this.d.clear();
                JSONArray jSONArray = new JSONArray();
                for (b bVar : this.e) {
                    if (z || !bVar.b()) {
                        jSONArray.put(bVar.c());
                    }
                }
                if (jSONArray.length() == 0) {
                    return 0;
                }
                a(graphRequest, i, jSONArray, z2);
                return jSONArray.length();
            }
        }

        public synchronized List<b> b() {
            List<b> list;
            list = this.d;
            this.d = new ArrayList();
            return list;
        }

        public synchronized void a(List<b> list) {
            this.d.addAll(list);
        }

        private void a(GraphRequest graphRequest, int i, JSONArray jSONArray, boolean z) {
            JSONObject a;
            try {
                a = com.facebook.internal.c.a(com.facebook.internal.c.a.CUSTOM_APP_EVENTS, this.g, this.i, z, b.q);
                if (this.f > 0) {
                    a.put("num_skipped_events", i);
                }
            } catch (JSONException e) {
                a = new JSONObject();
            }
            graphRequest.a(a);
            Bundle e2 = graphRequest.e();
            if (e2 == null) {
                e2 = new Bundle();
            }
            Object jSONArray2 = jSONArray.toString();
            if (jSONArray2 != null) {
                e2.putByteArray("custom_events_file", a((String) jSONArray2));
                graphRequest.a(jSONArray2);
            }
            graphRequest.a(e2);
        }

        private byte[] a(String str) {
            byte[] bArr = null;
            try {
                bArr = str.getBytes("UTF-8");
            } catch (Exception e) {
                ah.a("Encoding exception: ", e);
            }
            return bArr;
        }
    }

    public static void a(Context context) {
        o.a(context);
        a(context, ah.a(context));
    }

    public static void a(Context context, String str) {
        if (context == null || str == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        }
        if (context instanceof Activity) {
            a((Activity) context);
        } else {
            g();
            Log.d(b.class.getName(), "To set source application the context of activateApp must be an instance of Activity");
        }
        o.a(context, str);
        final b bVar = new b(context, str, null);
        final long currentTimeMillis = System.currentTimeMillis();
        final String f = f();
        n.execute(new Runnable() {
            public void run() {
                bVar.a(currentTimeMillis, f);
            }
        });
    }

    public static void b(Context context) {
        b(context, ah.a(context));
    }

    public static void b(Context context, String str) {
        if (context == null || str == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        }
        g();
        final b bVar = new b(context, str, null);
        final long currentTimeMillis = System.currentTimeMillis();
        n.execute(new Runnable() {
            public void run() {
                bVar.a(currentTimeMillis);
            }
        });
    }

    private void a(long j, String str) {
        g.a(q, this.l, this, j, str);
    }

    private void a(long j) {
        g.a(q, this.l, this, j);
    }

    public static b c(Context context) {
        return new b(context, null, null);
    }

    public static b a(Context context, AccessToken accessToken) {
        return new b(context, null, accessToken);
    }

    public static b a(Context context, String str, AccessToken accessToken) {
        return new b(context, str, accessToken);
    }

    public static b c(Context context, String str) {
        return new b(context, str, null);
    }

    public static c a() {
        c cVar;
        synchronized (r) {
            cVar = o;
        }
        return cVar;
    }

    public static void a(c cVar) {
        synchronized (r) {
            o = cVar;
        }
    }

    public void a(String str) {
        a(str, null);
    }

    public void a(String str, double d) {
        a(str, d, null);
    }

    public void a(String str, Bundle bundle) {
        a(str, null, bundle, false);
    }

    public void a(String str, double d, Bundle bundle) {
        a(str, Double.valueOf(d), bundle, false);
    }

    public void a(BigDecimal bigDecimal, Currency currency) {
        a(bigDecimal, currency, null);
    }

    public void a(BigDecimal bigDecimal, Currency currency, Bundle bundle) {
        if (bigDecimal == null) {
            b("purchaseAmount cannot be null");
        } else if (currency == null) {
            b("currency cannot be null");
        } else {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString(a.r, currency.getCurrencyCode());
            a(a.n, bigDecimal.doubleValue(), bundle);
            e();
        }
    }

    public void b() {
        b(d.EXPLICIT);
    }

    public static void c() {
        h.a(q, m);
    }

    public boolean a(AccessToken accessToken) {
        return this.l.equals(new a(accessToken));
    }

    public void a(String str, Double d, Bundle bundle) {
        a(str, d, bundle, true);
    }

    public String d() {
        return this.l.b();
    }

    private b(Context context, String str, AccessToken accessToken) {
        ai.a((Object) context, "context");
        this.k = ah.d(context);
        if (accessToken == null) {
            accessToken = AccessToken.a();
        }
        if (accessToken == null || !(str == null || str.equals(accessToken.i()))) {
            if (str == null) {
                str = ah.a(context);
            }
            this.l = new a(null, str);
        } else {
            this.l = new a(accessToken);
        }
        synchronized (r) {
            if (q == null) {
                q = context.getApplicationContext();
            }
        }
        n();
    }

    private static void n() {
        synchronized (r) {
            if (n != null) {
                return;
            }
            n = new ScheduledThreadPoolExecutor(1);
            n.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    if (b.a() != c.EXPLICIT_ONLY) {
                        b.c(d.TIMER);
                    }
                }
            }, 0, 15, TimeUnit.SECONDS);
            n.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    Set<String> hashSet = new HashSet();
                    synchronized (b.r) {
                        for (a b : b.m.keySet()) {
                            hashSet.add(b.b());
                        }
                    }
                    for (String a : hashSet) {
                        ah.a(a, true);
                    }
                }
            }, 0, FlyForbidProtocol.UNLIMIT_AREA_EXPIRED_TIME, TimeUnit.SECONDS);
        }
    }

    private void a(String str, Double d, Bundle bundle, boolean z) {
        a(q, new b(this.k, str, d, bundle, z), this.l);
    }

    private static void a(final Context context, final b bVar, final a aVar) {
        o.f().execute(new Runnable() {
            public void run() {
                b.b(context, aVar).a(bVar);
                b.o();
            }
        });
        if (!bVar.c && !v) {
            if (bVar.a() == a.a) {
                v = true;
            } else {
                x.a(y.APP_EVENTS, "AppEvents", "Warning: Please call AppEventsLogger.activateApp(...)from the long-lived activity's onResume() methodbefore logging other app events.");
            }
        }
    }

    static void e() {
        if (a() != c.EXPLICIT_ONLY) {
            b(d.EAGER_FLUSHING_EVENT);
        }
    }

    private static void o() {
        synchronized (r) {
            if (a() != c.EXPLICIT_ONLY && p() > 100) {
                b(d.EVENT_THRESHOLD);
            }
        }
    }

    private static int p() {
        int i;
        synchronized (r) {
            i = 0;
            for (i a : m.values()) {
                i = a.a() + i;
            }
        }
        return i;
    }

    private static i b(Context context, a aVar) {
        i iVar;
        com.facebook.internal.d dVar = null;
        if (((i) m.get(aVar)) == null) {
            dVar = com.facebook.internal.d.a(context);
        }
        synchronized (r) {
            iVar = (i) m.get(aVar);
            if (iVar == null) {
                iVar = new i(dVar, context.getPackageName(), d(context));
                m.put(aVar, iVar);
            }
        }
        return iVar;
    }

    private static i a(a aVar) {
        i iVar;
        synchronized (r) {
            iVar = (i) m.get(aVar);
        }
        return iVar;
    }

    private static void b(final d dVar) {
        o.f().execute(new Runnable() {
            public void run() {
                b.c(dVar);
            }
        });
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void c(com.facebook.a.b.d r4) {
        /*
        r1 = r;
        monitor-enter(r1);
        r0 = p;	 Catch:{ all -> 0x0048 }
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);	 Catch:{ all -> 0x0048 }
    L_0x0008:
        return;
    L_0x0009:
        r0 = 1;
        p = r0;	 Catch:{ all -> 0x0048 }
        r2 = new java.util.HashSet;	 Catch:{ all -> 0x0048 }
        r0 = m;	 Catch:{ all -> 0x0048 }
        r0 = r0.keySet();	 Catch:{ all -> 0x0048 }
        r2.<init>(r0);	 Catch:{ all -> 0x0048 }
        monitor-exit(r1);	 Catch:{ all -> 0x0048 }
        q();
        r0 = 0;
        r0 = a(r4, r2);	 Catch:{ Exception -> 0x004b }
    L_0x0020:
        r1 = r;
        monitor-enter(r1);
        r2 = 0;
        p = r2;	 Catch:{ all -> 0x0054 }
        monitor-exit(r1);	 Catch:{ all -> 0x0054 }
        if (r0 == 0) goto L_0x0008;
    L_0x0029:
        r1 = new android.content.Intent;
        r2 = "com.facebook.sdk.APP_EVENTS_FLUSHED";
        r1.<init>(r2);
        r2 = "com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED";
        r3 = r0.a;
        r1.putExtra(r2, r3);
        r2 = "com.facebook.sdk.APP_EVENTS_FLUSH_RESULT";
        r0 = r0.b;
        r1.putExtra(r2, r0);
        r0 = q;
        r0 = android.support.v4.content.LocalBroadcastManager.getInstance(r0);
        r0.sendBroadcast(r1);
        goto L_0x0008;
    L_0x0048:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0048 }
        throw r0;
    L_0x004b:
        r1 = move-exception;
        r2 = e;
        r3 = "Caught unexpected exception while flushing: ";
        com.facebook.internal.ah.a(r2, r3, r1);
        goto L_0x0020;
    L_0x0054:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0054 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.a.b.c(com.facebook.a.b$d):void");
    }

    private static f a(d dVar, Set<a> set) {
        GraphRequest a;
        f fVar = new f();
        boolean b = o.b(q);
        List<GraphRequest> arrayList = new ArrayList();
        for (a aVar : set) {
            i a2 = a(aVar);
            if (a2 != null) {
                a = a(aVar, a2, b, fVar);
                if (a != null) {
                    arrayList.add(a);
                }
            }
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        x.a(y.APP_EVENTS, e, "Flushing %d events due to %s.", Integer.valueOf(fVar.a), dVar.toString());
        for (GraphRequest a3 : arrayList) {
            a3.m();
        }
        return fVar;
    }

    private static GraphRequest a(final a aVar, final i iVar, boolean z, final f fVar) {
        com.facebook.internal.ah.b a = ah.a(aVar.b(), false);
        final GraphRequest a2 = GraphRequest.a(null, String.format("%s/activities", new Object[]{r0}), null, null);
        Bundle e = a2.e();
        if (e == null) {
            e = new Bundle();
        }
        e.putString("access_token", aVar.a());
        a2.a(e);
        if (a == null) {
            return null;
        }
        int a3 = iVar.a(a2, a.a(), z);
        if (a3 == 0) {
            return null;
        }
        fVar.a = a3 + fVar.a;
        a2.a(new com.facebook.GraphRequest.b() {
            public void onCompleted(v vVar) {
                b.b(aVar, a2, vVar, iVar, fVar);
            }
        });
        return a2;
    }

    private static void b(a aVar, GraphRequest graphRequest, v vVar, i iVar, f fVar) {
        e eVar;
        n a = vVar.a();
        String str = "Success";
        e eVar2 = e.SUCCESS;
        String str2;
        if (a == null) {
            str2 = str;
            eVar = eVar2;
        } else if (a.c() == -1) {
            Object obj = "Failed: No Connectivity";
            eVar = e.NO_CONNECTIVITY;
        } else {
            str2 = String.format("Failed:\n  Response: %s\n  Error %s", new Object[]{vVar.toString(), a.toString()});
            eVar = e.SERVER_ERROR;
        }
        if (o.c(y.APP_EVENTS)) {
            String jSONArray;
            try {
                jSONArray = new JSONArray((String) graphRequest.l()).toString(2);
            } catch (JSONException e) {
                jSONArray = "<Can't encode events for debug logging>";
            }
            x.a(y.APP_EVENTS, e, "Flush completed\nParams: %s\n  Result: %s\n  Events JSON: %s", graphRequest.a().toString(), obj, jSONArray);
        }
        iVar.a(a != null);
        if (eVar == e.NO_CONNECTIVITY) {
            h.a(q, aVar, iVar);
        }
        if (eVar != e.SUCCESS && fVar.b != e.NO_CONNECTIVITY) {
            fVar.b = eVar;
        }
    }

    private static int q() {
        h a = h.a(q);
        int i = 0;
        for (a aVar : a.a()) {
            i b = b(q, aVar);
            List a2 = a.a(aVar);
            b.a(a2);
            i = a2.size() + i;
        }
        return i;
    }

    private static void b(String str) {
        x.a(y.DEVELOPER_ERRORS, "AppEvents", str);
    }

    private static void a(Activity activity) {
        ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity != null) {
            String packageName = callingActivity.getPackageName();
            if (packageName.equals(activity.getPackageName())) {
                g();
                return;
            }
            t = packageName;
        }
        Intent intent = activity.getIntent();
        if (intent == null || intent.getBooleanExtra(j, false)) {
            g();
            return;
        }
        Bundle appLinkData = AppLinks.getAppLinkData(intent);
        if (appLinkData == null) {
            g();
            return;
        }
        u = true;
        appLinkData = appLinkData.getBundle("referer_app_link");
        if (appLinkData == null) {
            t = null;
            return;
        }
        t = appLinkData.getString("package");
        intent.putExtra(j, true);
    }

    static void a(String str, boolean z) {
        t = str;
        u = z;
    }

    static String f() {
        String str = "Unclassified";
        if (u) {
            str = "Applink";
        }
        if (t != null) {
            return str + "(" + t + ")";
        }
        return str;
    }

    static void g() {
        t = null;
        u = false;
    }

    public static String d(Context context) {
        if (s == null) {
            synchronized (r) {
                if (s == null) {
                    s = context.getSharedPreferences(a, 0).getString("anonymousAppDeviceGUID", null);
                    if (s == null) {
                        s = "XZ" + UUID.randomUUID().toString();
                        context.getSharedPreferences(a, 0).edit().putString("anonymousAppDeviceGUID", s).apply();
                    }
                }
            }
        }
        return s;
    }
}
