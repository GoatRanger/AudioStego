package com.facebook.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.k;
import com.facebook.m;
import com.facebook.o;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ab {
    public static final String A = "error";
    public static final String B = "com.facebook.platform.extra.DID_COMPLETE";
    public static final String C = "com.facebook.platform.extra.COMPLETION_GESTURE";
    public static final String D = "didComplete";
    public static final String E = "completionGesture";
    public static final int F = 65536;
    public static final int G = 65537;
    static final int H = 65538;
    static final int I = 65539;
    public static final int J = 65540;
    public static final int K = 65541;
    public static final int L = 65542;
    public static final int M = 65543;
    static final String N = "com.facebook.platform.extra.PROTOCOL_VERSIONS";
    public static final String O = "com.facebook.platform.action.request.FEED_DIALOG";
    public static final String P = "com.facebook.platform.action.request.MESSAGE_DIALOG";
    public static final String Q = "com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG";
    public static final String R = "com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG";
    public static final String S = "com.facebook.platform.action.request.LIKE_DIALOG";
    public static final String T = "com.facebook.platform.action.request.APPINVITES_DIALOG";
    public static final String U = "com.facebook.platform.extra.PERMISSIONS";
    public static final String V = "com.facebook.platform.extra.APPLICATION_ID";
    public static final String W = "com.facebook.platform.extra.APPLICATION_NAME";
    public static final String X = "com.facebook.platform.extra.USER_ID";
    public static final String Y = "com.facebook.platform.extra.ACCESS_TOKEN";
    public static final String Z = "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH";
    public static final int a = -1;
    public static final String aA = "is_fallback";
    public static final String aB = "only_me";
    public static final String aC = "friends";
    public static final String aD = "everyone";
    private static final String aE = "com.facebook.katana.ProxyAuth";
    private static final String aF = "com.facebook.katana.platform.TokenRefreshService";
    private static final String aG = "content://";
    private static final String aH = ".provider.PlatformProvider";
    private static final String aI = ".provider.PlatformProvider/versions";
    private static final String aJ = "version";
    private static final c aK = new a();
    private static List<c> aL = e();
    private static Map<String, List<c>> aM = f();
    private static AtomicBoolean aN = new AtomicBoolean(false);
    private static final List<Integer> aO = Arrays.asList(new Integer[]{Integer.valueOf(q), Integer.valueOf(p), Integer.valueOf(o), Integer.valueOf(n), Integer.valueOf(m), Integer.valueOf(l), Integer.valueOf(k), Integer.valueOf(j), Integer.valueOf(i), Integer.valueOf(h), Integer.valueOf(g)});
    public static final String aa = "access_token";
    public static final String ab = "expires_seconds_since_epoch";
    public static final String ac = "permissions";
    public static final String ad = "fbsdk:create_object";
    public static final String ae = "user_generated";
    public static final String af = "url";
    public static final String ag = "com.facebook.platform.status.ERROR_TYPE";
    public static final String ah = "com.facebook.platform.status.ERROR_DESCRIPTION";
    public static final String ai = "com.facebook.platform.status.ERROR_CODE";
    public static final String aj = "com.facebook.platform.status.ERROR_SUBCODE";
    public static final String ak = "com.facebook.platform.status.ERROR_JSON";
    public static final String al = "error_type";
    public static final String am = "error_description";
    public static final String an = "error_code";
    public static final String ao = "error_subcode";
    public static final String ap = "error_json";
    public static final String aq = "UnknownError";
    public static final String ar = "ProtocolError";
    public static final String as = "UserCanceled";
    public static final String at = "ApplicationError";
    public static final String au = "NetworkError";
    public static final String av = "PermissionDenied";
    public static final String aw = "ServiceDisabled";
    public static final String ax = "url";
    public static final String ay = "action";
    public static final String az = "params";
    public static final String b = "scope";
    public static final String c = "client_id";
    public static final String d = "e2e";
    static final String e = "com.facebook.platform.PLATFORM_ACTIVITY";
    static final String f = "com.facebook.platform.PLATFORM_SERVICE";
    public static final int g = 20121101;
    public static final int h = 20130502;
    public static final int i = 20130618;
    public static final int j = 20131107;
    public static final int k = 20140204;
    public static final int l = 20140324;
    public static final int m = 20140701;
    public static final int n = 20141001;
    public static final int o = 20141028;
    public static final int p = 20141107;
    public static final int q = 20141218;
    public static final String r = "com.facebook.platform.protocol.PROTOCOL_VERSION";
    public static final String s = "com.facebook.platform.protocol.PROTOCOL_ACTION";
    public static final String t = "com.facebook.platform.protocol.CALL_ID";
    public static final String u = "com.facebook.platform.extra.INSTALLDATA_PACKAGE";
    public static final String v = "com.facebook.platform.protocol.BRIDGE_ARGS";
    public static final String w = "com.facebook.platform.protocol.METHOD_ARGS";
    public static final String x = "com.facebook.platform.protocol.RESULT_ARGS";
    public static final String y = "app_name";
    public static final String z = "action_id";

    private static abstract class c {
        private static final String a = "a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc";
        private static final String b = "5e8f16062ea3cd2c4a0d547876baa6f38cabf625";
        private static final String c = "8a3c4b262d721acd49a4bf97d5213199c86fa2b9";
        private static final HashSet<String> d = c();
        private TreeSet<Integer> e;

        protected abstract String a();

        private c() {
        }

        private static HashSet<String> c() {
            HashSet<String> hashSet = new HashSet();
            hashSet.add(c);
            hashSet.add(a);
            hashSet.add(b);
            return hashSet;
        }

        public boolean a(Context context, String str) {
            String str2 = Build.BRAND;
            int i = context.getApplicationInfo().flags;
            if (str2.startsWith("generic") && (i & 2) != 0) {
                return true;
            }
            try {
                for (Signature toByteArray : context.getPackageManager().getPackageInfo(str, 64).signatures) {
                    if (d.contains(ah.a(toByteArray.toByteArray()))) {
                        return true;
                    }
                }
                return false;
            } catch (NameNotFoundException e) {
                return false;
            }
        }

        public TreeSet<Integer> b() {
            if (this.e == null) {
                a(false);
            }
            return this.e;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private synchronized void a(boolean r2) {
            /*
            r1 = this;
            monitor-enter(r1);
            if (r2 != 0) goto L_0x0007;
        L_0x0003:
            r0 = r1.e;	 Catch:{ all -> 0x000f }
            if (r0 != 0) goto L_0x000d;
        L_0x0007:
            r0 = com.facebook.internal.ab.b(r1);	 Catch:{ all -> 0x000f }
            r1.e = r0;	 Catch:{ all -> 0x000f }
        L_0x000d:
            monitor-exit(r1);
            return;
        L_0x000f:
            r0 = move-exception;
            monitor-exit(r1);
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.ab.c.a(boolean):void");
        }
    }

    private static class a extends c {
        static final String a = "com.facebook.katana";

        private a() {
            super();
        }

        protected String a() {
            return a;
        }
    }

    private static class b extends c {
        static final String a = "com.facebook.orca";

        private b() {
            super();
        }

        protected String a() {
            return "com.facebook.orca";
        }
    }

    private static class d extends c {
        static final String a = "com.facebook.wakizashi";

        private d() {
            super();
        }

        protected String a() {
            return a;
        }
    }

    private static List<c> e() {
        List<c> arrayList = new ArrayList();
        arrayList.add(aK);
        arrayList.add(new d());
        return arrayList;
    }

    private static Map<String, List<c>> f() {
        Map<String, List<c>> hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new b());
        hashMap.put(Q, aL);
        hashMap.put(O, aL);
        hashMap.put(S, aL);
        hashMap.put(T, aL);
        hashMap.put(P, arrayList);
        hashMap.put(R, arrayList);
        return hashMap;
    }

    static Intent a(Context context, Intent intent, c cVar) {
        if (intent == null) {
            return null;
        }
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            return null;
        }
        if (cVar.a(context, resolveActivity.activityInfo.packageName)) {
            return intent;
        }
        return null;
    }

    static Intent b(Context context, Intent intent, c cVar) {
        if (intent == null) {
            return null;
        }
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService == null) {
            return null;
        }
        if (cVar.a(context, resolveService.serviceInfo.packageName)) {
            return intent;
        }
        return null;
    }

    public static Intent a(Context context, String str, Collection<String> collection, String str2, boolean z, boolean z2, com.facebook.login.a aVar, String str3) {
        for (c cVar : aL) {
            Intent putExtra = new Intent().setClassName(cVar.a(), aE).putExtra("client_id", str);
            if (!ah.a((Collection) collection)) {
                putExtra.putExtra("scope", TextUtils.join(",", collection));
            }
            if (!ah.a(str2)) {
                putExtra.putExtra("e2e", str2);
            }
            putExtra.putExtra("state", str3);
            putExtra.putExtra("response_type", af.r);
            putExtra.putExtra(af.l, "true");
            if (z2) {
                putExtra.putExtra(af.n, aVar.a());
            }
            putExtra.putExtra(af.i, af.C);
            if (z) {
                putExtra.putExtra(af.d, af.q);
            }
            Intent a = a(context, putExtra, cVar);
            if (a != null) {
                return a;
            }
        }
        return null;
    }

    public static Intent a(Context context) {
        for (c cVar : aL) {
            Intent b = b(context, new Intent().setClassName(cVar.a(), aF), cVar);
            if (b != null) {
                return b;
            }
        }
        return null;
    }

    public static final int a() {
        return ((Integer) aO.get(0)).intValue();
    }

    private static Intent a(Context context, String str, String str2) {
        List<c> list = (List) aM.get(str2);
        if (list == null) {
            return null;
        }
        Intent intent = null;
        for (c cVar : list) {
            intent = a(context, new Intent().setAction(str).setPackage(cVar.a()).addCategory("android.intent.category.DEFAULT"), cVar);
            if (intent != null) {
                return intent;
            }
        }
        return intent;
    }

    public static boolean a(int i) {
        return aO.contains(Integer.valueOf(i)) && i >= m;
    }

    public static Intent a(Context context, String str, String str2, int i, Bundle bundle) {
        Intent a = a(context, e, str2);
        if (a == null) {
            return null;
        }
        a(a, str, str2, i, bundle);
        return a;
    }

    public static void a(Intent intent, String str, String str2, int i, Bundle bundle) {
        String k = o.k();
        String l = o.l();
        intent.putExtra(r, i).putExtra(s, str2).putExtra(V, k);
        if (a(i)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("action_id", str);
            ah.a(bundle2, y, l);
            intent.putExtra(v, bundle2);
            if (bundle == null) {
                bundle = new Bundle();
            }
            intent.putExtra(w, bundle);
            return;
        }
        intent.putExtra(t, str);
        if (!ah.a(l)) {
            intent.putExtra(W, l);
        }
        intent.putExtras(bundle);
    }

    public static Intent a(Intent intent, Bundle bundle, k kVar) {
        UUID b = b(intent);
        if (b == null) {
            return null;
        }
        Intent intent2 = new Intent();
        intent2.putExtra(r, a(intent));
        Bundle bundle2 = new Bundle();
        bundle2.putString("action_id", b.toString());
        if (kVar != null) {
            bundle2.putBundle("error", a(kVar));
        }
        intent2.putExtra(v, bundle2);
        if (bundle == null) {
            return intent2;
        }
        intent2.putExtra(x, bundle);
        return intent2;
    }

    public static Intent b(Context context) {
        for (c cVar : aL) {
            Intent b = b(context, new Intent(f).setPackage(cVar.a()).addCategory("android.intent.category.DEFAULT"), cVar);
            if (b != null) {
                return b;
            }
        }
        return null;
    }

    public static int a(Intent intent) {
        return intent.getIntExtra(r, 0);
    }

    public static UUID b(Intent intent) {
        UUID uuid = null;
        if (intent != null) {
            String string;
            if (a(a(intent))) {
                Bundle bundleExtra = intent.getBundleExtra(v);
                if (bundleExtra != null) {
                    string = bundleExtra.getString("action_id");
                } else {
                    Object obj = uuid;
                }
            } else {
                string = intent.getStringExtra(t);
            }
            if (string != null) {
                try {
                    uuid = UUID.fromString(string);
                } catch (IllegalArgumentException e) {
                }
            }
        }
        return uuid;
    }

    public static Bundle c(Intent intent) {
        if (a(a(intent))) {
            return intent.getBundleExtra(v);
        }
        return null;
    }

    public static Bundle d(Intent intent) {
        if (a(a(intent))) {
            return intent.getBundleExtra(w);
        }
        return intent.getExtras();
    }

    public static Bundle e(Intent intent) {
        int a = a(intent);
        Bundle extras = intent.getExtras();
        return (!a(a) || extras == null) ? extras : extras.getBundle(x);
    }

    public static boolean f(Intent intent) {
        Bundle c = c(intent);
        if (c != null) {
            return c.containsKey("error");
        }
        return intent.hasExtra(ag);
    }

    public static Bundle g(Intent intent) {
        if (!f(intent)) {
            return null;
        }
        Bundle c = c(intent);
        if (c != null) {
            return c.getBundle("error");
        }
        return intent.getExtras();
    }

    public static k a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString(al);
        if (string == null) {
            string = bundle.getString(ag);
        }
        String string2 = bundle.getString(am);
        if (string2 == null) {
            string2 = bundle.getString(ah);
        }
        if (string == null || !string.equalsIgnoreCase(as)) {
            return new k(string2);
        }
        return new m(string2);
    }

    public static Bundle a(k kVar) {
        if (kVar == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString(am, kVar.toString());
        if (!(kVar instanceof m)) {
            return bundle;
        }
        bundle.putString(al, as);
        return bundle;
    }

    public static int b(int i) {
        return a(aL, new int[]{i});
    }

    public static int a(String str, int[] iArr) {
        return a((List) aM.get(str), iArr);
    }

    private static int a(List<c> list, int[] iArr) {
        b();
        if (list == null) {
            return -1;
        }
        for (c b : list) {
            int a = a(b.b(), a(), iArr);
            if (a != -1) {
                return a;
            }
        }
        return -1;
    }

    public static void b() {
        if (aN.compareAndSet(false, true)) {
            o.f().execute(new Runnable() {
                public void run() {
                    try {
                        for (c a : ab.aL) {
                            a.a(true);
                        }
                    } finally {
                        ab.aN.set(false);
                    }
                }
            });
        }
    }

    private static TreeSet<Integer> b(c cVar) {
        Throwable th;
        Cursor cursor = null;
        TreeSet<Integer> treeSet = new TreeSet();
        ContentResolver contentResolver = o.h().getContentResolver();
        String[] strArr = new String[]{"version"};
        Uri c = c(cVar);
        try {
            Cursor query;
            if (o.h().getPackageManager().resolveContentProvider(cVar.a() + aH, 0) != null) {
                query = contentResolver.query(c, strArr, null, null, null);
                if (query != null) {
                    while (query.moveToNext()) {
                        try {
                            treeSet.add(Integer.valueOf(query.getInt(query.getColumnIndex("version"))));
                        } catch (Throwable th2) {
                            cursor = query;
                            th = th2;
                        }
                    }
                }
            } else {
                query = null;
            }
            if (query != null) {
                query.close();
            }
            return treeSet;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static int a(TreeSet<Integer> treeSet, int i, int[] iArr) {
        int length = iArr.length - 1;
        Iterator descendingIterator = treeSet.descendingIterator();
        int i2 = -1;
        int i3 = length;
        while (descendingIterator.hasNext()) {
            int intValue = ((Integer) descendingIterator.next()).intValue();
            length = Math.max(i2, intValue);
            i2 = i3;
            while (i2 >= 0 && iArr[i2] > intValue) {
                i2--;
            }
            if (i2 < 0) {
                return -1;
            }
            if (iArr[i2] != intValue) {
                i3 = i2;
                i2 = length;
            } else if (i2 % 2 == 0) {
                return Math.min(length, i);
            } else {
                return -1;
            }
        }
        return -1;
    }

    private static Uri c(c cVar) {
        return Uri.parse(aG + cVar.a() + aI);
    }
}
