package com.facebook.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.alipay.sdk.j.i;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.k;
import com.facebook.o;
import com.facebook.v;
import com.facebook.w;
import com.here.odnp.config.OdnpConfigStatic;
import com.here.odnp.posclient.pos.IPositioningSession;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class ah {
    private static long A = -1;
    private static long B = -1;
    private static long C = -1;
    private static String D = "";
    private static String E = v;
    static final String a = "FacebookSDK";
    public static final int b = 8192;
    private static final String c = "MD5";
    private static final String d = "SHA-1";
    private static final String e = "https";
    private static final String f = "com.facebook.internal.preferences.APP_SETTINGS";
    private static final String g = "com.facebook.internal.APP_SETTINGS.%s";
    private static final String h = "supports_implicit_sdk_logging";
    private static final String i = "gdpv4_nux_content";
    private static final String j = "gdpv4_nux_enabled";
    private static final String k = "android_dialog_configs";
    private static final String l = "android_sdk_error_categories";
    private static final String m = "a2";
    private static final String n = "\\|";
    private static final String o = "name";
    private static final String p = "versions";
    private static final String q = "url";
    private static final String r = "UTF-8";
    private static final String[] s = new String[]{h, i, j, k, l};
    private static final String t = "fields";
    private static final int u = 1800000;
    private static final String v = "NoCarrier";
    private static final int w = 10;
    private static Map<String, b> x = new ConcurrentHashMap();
    private static AtomicBoolean y = new AtomicBoolean(false);
    private static int z = 0;

    public interface c {
        void onFailure(k kVar);

        void onSuccess(JSONObject jSONObject);
    }

    public static class a {
        private String a;
        private String b;
        private Uri c;
        private int[] d;

        private static a b(JSONObject jSONObject) {
            Uri uri = null;
            String optString = jSONObject.optString("name");
            if (ah.a(optString)) {
                return null;
            }
            String[] split = optString.split(ah.n);
            if (split.length != 2) {
                return null;
            }
            String str = split[0];
            String str2 = split[1];
            if (ah.a(str) || ah.a(str2)) {
                return null;
            }
            optString = jSONObject.optString("url");
            if (!ah.a(optString)) {
                uri = Uri.parse(optString);
            }
            return new a(str, str2, uri, a(jSONObject.optJSONArray(ah.p)));
        }

        private static int[] a(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            int length = jSONArray.length();
            int[] iArr = new int[length];
            for (int i = 0; i < length; i++) {
                int optInt = jSONArray.optInt(i, -1);
                if (optInt == -1) {
                    String optString = jSONArray.optString(i);
                    if (!ah.a(optString)) {
                        try {
                            optInt = Integer.parseInt(optString);
                        } catch (Exception e) {
                            ah.a(ah.a, e);
                            optInt = -1;
                        }
                    }
                }
                iArr[i] = optInt;
            }
            return iArr;
        }

        private a(String str, String str2, Uri uri, int[] iArr) {
            this.a = str;
            this.b = str2;
            this.c = uri;
            this.d = iArr;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public Uri c() {
            return this.c;
        }

        public int[] d() {
            return this.d;
        }
    }

    public static class b {
        private boolean a;
        private String b;
        private boolean c;
        private Map<String, Map<String, a>> d;
        private l e;

        private b(boolean z, String str, boolean z2, Map<String, Map<String, a>> map, l lVar) {
            this.a = z;
            this.b = str;
            this.c = z2;
            this.d = map;
            this.e = lVar;
        }

        public boolean a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public boolean c() {
            return this.c;
        }

        public Map<String, Map<String, a>> d() {
            return this.d;
        }

        public l e() {
            return this.e;
        }
    }

    public interface d<T, K> {
        K a(T t);
    }

    public static class e {
        List<String> a;
        List<String> b;

        public e(List<String> list, List<String> list2) {
            this.a = list;
            this.b = list2;
        }

        public List<String> a() {
            return this.a;
        }

        public List<String> b() {
            return this.b;
        }
    }

    public interface f<T> {
        boolean a(T t);
    }

    public static int[] a(int[] iArr, int[] iArr2) {
        int i = 0;
        if (iArr == null) {
            return iArr2;
        }
        if (iArr2 == null) {
            return iArr;
        }
        int[] iArr3 = new int[(iArr.length + iArr2.length)];
        int i2 = 0;
        int i3 = 0;
        while (i2 < iArr.length && i < iArr2.length) {
            int i4;
            int i5;
            int i6 = iArr[i2];
            int i7 = iArr2[i];
            if (i2 < iArr.length - 1) {
                i4 = iArr[i2 + 1];
            } else {
                i4 = Integer.MAX_VALUE;
            }
            if (i < iArr2.length - 1) {
                i5 = iArr2[i + 1];
            } else {
                i5 = Integer.MAX_VALUE;
            }
            if (i6 < i7) {
                if (i4 <= i7) {
                    i2 += 2;
                    i5 = Integer.MAX_VALUE;
                    i7 = Integer.MIN_VALUE;
                } else if (i4 > i5) {
                    i += 2;
                } else {
                    i2 += 2;
                    i5 = i4;
                }
            } else if (i5 <= i6) {
                i += 2;
                i5 = Integer.MAX_VALUE;
                i7 = Integer.MIN_VALUE;
            } else if (i5 > i4) {
                i2 += 2;
                i5 = i4;
                i7 = i6;
            } else {
                i += 2;
                i7 = i6;
            }
            if (i7 != Integer.MIN_VALUE) {
                i4 = i3 + 1;
                iArr3[i3] = i7;
                if (i5 == Integer.MAX_VALUE) {
                    i3 = i4;
                    break;
                }
                i3 = i4 + 1;
                iArr3[i4] = i5;
            }
        }
        return Arrays.copyOf(iArr3, i3);
    }

    public static <T> boolean a(Collection<T> collection, Collection<T> collection2) {
        if (collection2 != null && collection2.size() != 0) {
            HashSet hashSet = new HashSet(collection2);
            for (T contains : collection) {
                if (!hashSet.contains(contains)) {
                    return false;
                }
            }
            return true;
        } else if (collection == null || collection.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static <T> boolean a(Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public static String a(String str, String str2) {
        return a(str) ? str2 : str;
    }

    public static <T> Collection<T> a(T... tArr) {
        return Collections.unmodifiableCollection(Arrays.asList(tArr));
    }

    public static <T> ArrayList<T> b(T... tArr) {
        ArrayList<T> arrayList = new ArrayList(tArr.length);
        for (Object add : tArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static <T> HashSet<T> c(T... tArr) {
        HashSet<T> hashSet = new HashSet(tArr.length);
        for (Object add : tArr) {
            hashSet.add(add);
        }
        return hashSet;
    }

    public static String b(String str) {
        return d(c, str);
    }

    public static String c(String str) {
        return d(d, str);
    }

    public static String a(byte[] bArr) {
        return a(d, bArr);
    }

    private static String d(String str, String str2) {
        return a(str, str2.getBytes());
    }

    private static String a(String str, byte[] bArr) {
        try {
            return a(MessageDigest.getInstance(str), bArr);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static String a(MessageDigest messageDigest, byte[] bArr) {
        messageDigest.update(bArr);
        byte[] digest = messageDigest.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : digest) {
            stringBuilder.append(Integer.toHexString((b >> 4) & 15));
            stringBuilder.append(Integer.toHexString((b >> 0) & 15));
        }
        return stringBuilder.toString();
    }

    public static Uri a(String str, String str2, Bundle bundle) {
        Builder builder = new Builder();
        builder.scheme("https");
        builder.authority(str);
        builder.path(str2);
        if (bundle != null) {
            for (String str3 : bundle.keySet()) {
                Object obj = bundle.get(str3);
                if (obj instanceof String) {
                    builder.appendQueryParameter(str3, (String) obj);
                }
            }
        }
        return builder.build();
    }

    public static Bundle d(String str) {
        Bundle bundle = new Bundle();
        if (!a(str)) {
            for (String split : str.split(com.alipay.sdk.h.a.b)) {
                String[] split2 = split.split("=");
                try {
                    if (split2.length == 2) {
                        bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), URLDecoder.decode(split2[1], "UTF-8"));
                    } else if (split2.length == 1) {
                        bundle.putString(URLDecoder.decode(split2[0], "UTF-8"), "");
                    }
                } catch (Exception e) {
                    a(a, e);
                }
            }
        }
        return bundle;
    }

    public static void a(Bundle bundle, String str, String str2) {
        if (!a(str2)) {
            bundle.putString(str, str2);
        }
    }

    public static void a(Bundle bundle, String str, List<String> list) {
        if (list != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String append : list) {
                stringBuilder.append(append);
                stringBuilder.append(",");
            }
            String append2 = "";
            if (stringBuilder.length() > 0) {
                append2 = stringBuilder.substring(0, stringBuilder.length() - 1);
            }
            bundle.putString(str, append2);
        }
    }

    public static void a(Bundle bundle, String str, Uri uri) {
        if (uri != null) {
            a(bundle, str, uri.toString());
        }
    }

    public static boolean a(Bundle bundle, String str, Object obj) {
        if (obj == null) {
            bundle.remove(str);
        } else if (obj instanceof Boolean) {
            bundle.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof boolean[]) {
            bundle.putBooleanArray(str, (boolean[]) obj);
        } else if (obj instanceof Double) {
            bundle.putDouble(str, ((Double) obj).doubleValue());
        } else if (obj instanceof double[]) {
            bundle.putDoubleArray(str, (double[]) obj);
        } else if (obj instanceof Integer) {
            bundle.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof int[]) {
            bundle.putIntArray(str, (int[]) obj);
        } else if (obj instanceof Long) {
            bundle.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof long[]) {
            bundle.putLongArray(str, (long[]) obj);
        } else if (obj instanceof String) {
            bundle.putString(str, (String) obj);
        } else if (obj instanceof JSONArray) {
            bundle.putString(str, ((JSONArray) obj).toString());
        } else if (!(obj instanceof JSONObject)) {
            return false;
        } else {
            bundle.putString(str, ((JSONObject) obj).toString());
        }
        return true;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static void a(URLConnection uRLConnection) {
        if (uRLConnection != null && (uRLConnection instanceof HttpURLConnection)) {
            ((HttpURLConnection) uRLConnection).disconnect();
        }
    }

    public static String a(Context context) {
        ai.a((Object) context, "context");
        o.a(context);
        return o.k();
    }

    static Map<String, Object> a(JSONObject jSONObject) {
        Map hashMap = new HashMap();
        JSONArray names = jSONObject.names();
        for (int i = 0; i < names.length(); i++) {
            try {
                String string = names.getString(i);
                Object obj = jSONObject.get(string);
                if (obj instanceof JSONObject) {
                    obj = a((JSONObject) obj);
                }
                hashMap.put(string, obj);
            } catch (JSONException e) {
            }
        }
        return hashMap;
    }

    public static Object a(JSONObject jSONObject, String str, String str2) throws JSONException {
        Object obj;
        Object opt = jSONObject.opt(str);
        if (opt == null || !(opt instanceof String)) {
            obj = opt;
        } else {
            obj = new JSONTokener((String) opt).nextValue();
        }
        if (obj == null || (obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            return obj;
        }
        if (str2 != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt(str2, obj);
            return jSONObject2;
        }
        throw new k("Got an unexpected non-JSON object.");
    }

    public static String a(InputStream inputStream) throws IOException {
        Throwable th;
        Closeable closeable = null;
        Closeable inputStreamReader;
        try {
            Closeable bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                inputStreamReader = new InputStreamReader(bufferedInputStream);
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    char[] cArr = new char[2048];
                    while (true) {
                        int read = inputStreamReader.read(cArr);
                        if (read != -1) {
                            stringBuilder.append(cArr, 0, read);
                        } else {
                            String stringBuilder2 = stringBuilder.toString();
                            a(bufferedInputStream);
                            a(inputStreamReader);
                            return stringBuilder2;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeable = bufferedInputStream;
                    a(closeable);
                    a(inputStreamReader);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStreamReader = null;
                closeable = bufferedInputStream;
                a(closeable);
                a(inputStreamReader);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            inputStreamReader = null;
            a(closeable);
            a(inputStreamReader);
            throw th;
        }
    }

    public static int a(InputStream inputStream, OutputStream outputStream) throws IOException {
        Throwable th;
        int i = 0;
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    i += read;
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                return i;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public static boolean b(String str, String str2) {
        boolean isEmpty = TextUtils.isEmpty(str);
        boolean isEmpty2 = TextUtils.isEmpty(str2);
        if (isEmpty && isEmpty2) {
            return true;
        }
        if (isEmpty || isEmpty2) {
            return false;
        }
        return str.equals(str2);
    }

    private static void b(Context context, String str) {
        CookieSyncManager.createInstance(context).sync();
        CookieManager instance = CookieManager.getInstance();
        String cookie = instance.getCookie(str);
        if (cookie != null) {
            for (String split : cookie.split(i.b)) {
                String[] split2 = split.split("=");
                if (split2.length > 0) {
                    instance.setCookie(str, split2[0].trim() + "=;expires=Sat, 1 Jan 2000 00:00:01 UTC;");
                }
            }
            instance.removeExpiredCookie();
        }
    }

    public static void b(Context context) {
        b(context, "facebook.com");
        b(context, ".facebook.com");
        b(context, "https://facebook.com");
        b(context, "https://.facebook.com");
    }

    public static void a(String str, Exception exception) {
        if (o.d() && str != null && exception != null) {
            Log.d(str, exception.getClass().getSimpleName() + ": " + exception.getMessage());
        }
    }

    public static void c(String str, String str2) {
        if (o.d() && str != null && str2 != null) {
            Log.d(str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (o.d() && !a(str)) {
            Log.d(str, str2, th);
        }
    }

    public static <T> boolean a(T t, T t2) {
        if (t == null) {
            return t2 == null;
        } else {
            return t.equals(t2);
        }
    }

    public static boolean a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null || !jSONObject.has("id") || !jSONObject2.has("id")) {
            return false;
        }
        if (jSONObject.equals(jSONObject2)) {
            return true;
        }
        String optString = jSONObject.optString("id");
        String optString2 = jSONObject2.optString("id");
        if (optString == null || optString2 == null) {
            return false;
        }
        return optString.equals(optString2);
    }

    public static void a(final Context context, final String str) {
        boolean compareAndSet = y.compareAndSet(false, true);
        if (!a(str) && !x.containsKey(str) && compareAndSet) {
            final String format = String.format(g, new Object[]{str});
            o.f().execute(new Runnable() {
                public void run() {
                    JSONObject jSONObject;
                    SharedPreferences sharedPreferences = context.getSharedPreferences(ah.f, 0);
                    String string = sharedPreferences.getString(format, null);
                    if (!ah.a(string)) {
                        try {
                            jSONObject = new JSONObject(string);
                        } catch (Exception e) {
                            ah.a(ah.a, e);
                            jSONObject = null;
                        }
                        if (jSONObject != null) {
                            ah.b(str, jSONObject);
                        }
                    }
                    jSONObject = ah.h(str);
                    if (jSONObject != null) {
                        ah.b(str, jSONObject);
                        sharedPreferences.edit().putString(format, jSONObject.toString()).apply();
                    }
                    ah.y.set(false);
                }
            });
        }
    }

    public static b e(String str) {
        return str != null ? (b) x.get(str) : null;
    }

    public static b a(String str, boolean z) {
        if (!z && x.containsKey(str)) {
            return (b) x.get(str);
        }
        JSONObject h = h(str);
        if (h == null) {
            return null;
        }
        return b(str, h);
    }

    private static b b(String str, JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray(l);
        b bVar = new b(jSONObject.optBoolean(h, false), jSONObject.optString(i, ""), jSONObject.optBoolean(j, false), c(jSONObject.optJSONObject(k)), optJSONArray == null ? l.d() : l.a(optJSONArray));
        x.put(str, bVar);
        return bVar;
    }

    private static JSONObject h(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", TextUtils.join(",", s));
        GraphRequest b = GraphRequest.b(null, str, null);
        b.a(true);
        b.a(bundle);
        return b.m().b();
    }

    public static a a(String str, String str2, String str3) {
        if (a(str2) || a(str3)) {
            return null;
        }
        b bVar = (b) x.get(str);
        if (bVar != null) {
            Map map = (Map) bVar.d().get(str2);
            if (map != null) {
                return (a) map.get(str3);
            }
        }
        return null;
    }

    private static Map<String, Map<String, a>> c(JSONObject jSONObject) {
        Map hashMap = new HashMap();
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    a a = a.b(optJSONArray.optJSONObject(i));
                    if (a != null) {
                        String a2 = a.a();
                        Map map = (Map) hashMap.get(a2);
                        if (map == null) {
                            map = new HashMap();
                            hashMap.put(a2, map);
                        }
                        map.put(a.b(), a);
                    }
                }
            }
        }
        return hashMap;
    }

    public static String a(JSONObject jSONObject, String str) {
        return jSONObject != null ? jSONObject.optString(str, "") : "";
    }

    public static JSONObject b(JSONObject jSONObject, String str) {
        return jSONObject != null ? jSONObject.optJSONObject(str) : null;
    }

    public static JSONArray c(JSONObject jSONObject, String str) {
        return jSONObject != null ? jSONObject.optJSONArray(str) : null;
    }

    public static void c(Context context) {
        q.a(context);
    }

    public static void a(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File a : listFiles) {
                        a(a);
                    }
                }
            }
            file.delete();
        }
    }

    public static <T> List<T> d(T... tArr) {
        List arrayList = new ArrayList();
        for (Object obj : tArr) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static List<String> a(JSONArray jSONArray) throws JSONException {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    public static Set<String> b(JSONArray jSONArray) throws JSONException {
        Set<String> hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(jSONArray.getString(i));
        }
        return hashSet;
    }

    public static void a(JSONObject jSONObject, d dVar, String str, boolean z) throws JSONException {
        boolean z2 = true;
        if (!(dVar == null || dVar.a() == null)) {
            jSONObject.put("attribution", dVar.a());
        }
        if (!(dVar == null || dVar.b() == null)) {
            jSONObject.put("advertiser_id", dVar.b());
            jSONObject.put("advertiser_tracking_enabled", !dVar.d());
        }
        if (!(dVar == null || dVar.c() == null)) {
            jSONObject.put("installer_package", dVar.c());
        }
        jSONObject.put("anon_id", str);
        String str2 = "application_tracking_enabled";
        if (z) {
            z2 = false;
        }
        jSONObject.put(str2, z2);
    }

    public static void a(JSONObject jSONObject, Context context) throws JSONException {
        Locale locale;
        double d;
        int i;
        int i2;
        int i3;
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(m);
        e(context);
        String packageName = context.getPackageName();
        int i4 = -1;
        Object obj = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            i4 = packageInfo.versionCode;
            obj = packageInfo.versionName;
        } catch (NameNotFoundException e) {
        }
        jSONArray.put(packageName);
        jSONArray.put(i4);
        jSONArray.put(obj);
        jSONArray.put(VERSION.RELEASE);
        jSONArray.put(Build.MODEL);
        try {
            locale = context.getResources().getConfiguration().locale;
        } catch (Exception e2) {
            locale = Locale.getDefault();
        }
        jSONArray.put(locale.getLanguage() + "_" + locale.getCountry());
        jSONArray.put(D);
        jSONArray.put(E);
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager != null) {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                defaultDisplay.getMetrics(displayMetrics);
                int i5 = displayMetrics.widthPixels;
                try {
                    int i6 = displayMetrics.heightPixels;
                    try {
                        d = (double) displayMetrics.density;
                        i = i6;
                        i2 = i5;
                    } catch (Exception e3) {
                        i3 = i6;
                        i4 = i5;
                        i = i3;
                        i2 = i4;
                        d = 0.0d;
                        jSONArray.put(i2);
                        jSONArray.put(i);
                        jSONArray.put(String.format("%.2f", new Object[]{Double.valueOf(d)}));
                        jSONArray.put(b());
                        jSONArray.put(B);
                        jSONArray.put(C);
                        jSONObject.put("extinfo", jSONArray.toString());
                    }
                } catch (Exception e4) {
                    i3 = 0;
                    i4 = i5;
                    i = i3;
                    i2 = i4;
                    d = 0.0d;
                    jSONArray.put(i2);
                    jSONArray.put(i);
                    jSONArray.put(String.format("%.2f", new Object[]{Double.valueOf(d)}));
                    jSONArray.put(b());
                    jSONArray.put(B);
                    jSONArray.put(C);
                    jSONObject.put("extinfo", jSONArray.toString());
                }
            }
            d = 0.0d;
            i = 0;
            i2 = 0;
        } catch (Exception e5) {
            i3 = 0;
            i4 = 0;
            i = i3;
            i2 = i4;
            d = 0.0d;
            jSONArray.put(i2);
            jSONArray.put(i);
            jSONArray.put(String.format("%.2f", new Object[]{Double.valueOf(d)}));
            jSONArray.put(b());
            jSONArray.put(B);
            jSONArray.put(C);
            jSONObject.put("extinfo", jSONArray.toString());
        }
        jSONArray.put(i2);
        jSONArray.put(i);
        jSONArray.put(String.format("%.2f", new Object[]{Double.valueOf(d)}));
        jSONArray.put(b());
        jSONArray.put(B);
        jSONArray.put(C);
        jSONObject.put("extinfo", jSONArray.toString());
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method a(String str, String str2, Class<?>... clsArr) {
        try {
            return a(Class.forName(str), str2, (Class[]) clsArr);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Object a(Object obj, Method method, Object... objArr) {
        Object obj2 = null;
        try {
            obj2 = method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e2) {
        }
        return obj2;
    }

    public static String d(Context context) {
        if (context == null) {
            return "null";
        }
        if (context == context.getApplicationContext()) {
            return "unknown";
        }
        return context.getClass().getSimpleName();
    }

    public static <T> List<T> a(List<T> list, f<T> fVar) {
        if (list == null) {
            return null;
        }
        List<T> arrayList = new ArrayList();
        for (Object next : list) {
            if (fVar.a(next)) {
                arrayList.add(next);
            }
        }
        if (arrayList.size() != 0) {
            return arrayList;
        }
        return null;
    }

    public static <T, K> List<K> a(List<T> list, d<T, K> dVar) {
        if (list == null) {
            return null;
        }
        List<K> arrayList = new ArrayList();
        for (T a : list) {
            Object a2 = dVar.a(a);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        if (arrayList.size() != 0) {
            return arrayList;
        }
        return null;
    }

    public static String a(Uri uri) {
        return uri == null ? null : uri.toString();
    }

    public static boolean b(Uri uri) {
        return uri != null && ("http".equalsIgnoreCase(uri.getScheme()) || "https".equalsIgnoreCase(uri.getScheme()));
    }

    public static boolean c(Uri uri) {
        return uri != null && "content".equalsIgnoreCase(uri.getScheme());
    }

    public static boolean d(Uri uri) {
        return uri != null && dji.pilot.usercenter.protocol.d.A.equalsIgnoreCase(uri.getScheme());
    }

    public static long e(Uri uri) {
        Throwable th;
        Cursor query;
        try {
            query = o.h().getContentResolver().query(uri, null, null, null, null);
            try {
                int columnIndex = query.getColumnIndex("_size");
                query.moveToFirst();
                long j = query.getLong(columnIndex);
                if (query != null) {
                    query.close();
                }
                return j;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public static Date a(Bundle bundle, String str, Date date) {
        if (bundle == null) {
            return null;
        }
        long longValue;
        Object obj = bundle.get(str);
        if (obj instanceof Long) {
            longValue = ((Long) obj).longValue();
        } else if (!(obj instanceof String)) {
            return null;
        } else {
            try {
                longValue = Long.parseLong((String) obj);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        if (longValue == 0) {
            return new Date(IPositioningSession.NotSet);
        }
        return new Date((longValue * 1000) + date.getTime());
    }

    public static void a(Parcel parcel, Map<String, String> map) {
        if (map == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(map.size());
        for (Entry entry : map.entrySet()) {
            parcel.writeString((String) entry.getKey());
            parcel.writeString((String) entry.getValue());
        }
    }

    public static Map<String, String> a(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        for (int i = 0; i < readInt; i++) {
            hashMap.put(parcel.readString(), parcel.readString());
        }
        return hashMap;
    }

    public static boolean a(AccessToken accessToken) {
        return accessToken != null ? accessToken.equals(AccessToken.a()) : false;
    }

    public static void a(final String str, final c cVar) {
        JSONObject a = ae.a(str);
        if (a != null) {
            cVar.onSuccess(a);
            return;
        }
        com.facebook.GraphRequest.b anonymousClass2 = new com.facebook.GraphRequest.b() {
            public void onCompleted(v vVar) {
                if (vVar.a() != null) {
                    cVar.onFailure(vVar.a().n());
                    return;
                }
                ae.a(str, vVar.b());
                cVar.onSuccess(vVar.b());
            }
        };
        GraphRequest i = i(str);
        i.a(anonymousClass2);
        i.n();
    }

    public static JSONObject f(String str) {
        JSONObject a = ae.a(str);
        if (a != null) {
            return a;
        }
        v m = i(str).m();
        if (m.a() != null) {
            return null;
        }
        return m.b();
    }

    private static GraphRequest i(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,name,first_name,middle_name,last_name,link");
        bundle.putString("access_token", str);
        return new GraphRequest(null, "me", bundle, w.a, null);
    }

    private static int b() {
        if (z > 0) {
            return z;
        }
        try {
            File[] listFiles = new File("/sys/devices/system/cpu/").listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return Pattern.matches("cpu[0-9]+", str);
                }
            });
            if (listFiles != null) {
                z = listFiles.length;
            }
        } catch (Exception e) {
        }
        if (z <= 0) {
            z = Math.max(Runtime.getRuntime().availableProcessors(), 1);
        }
        return z;
    }

    private static void e(Context context) {
        if (A == -1 || System.currentTimeMillis() - A >= OdnpConfigStatic.OEM_MAX_MEDIUM_POWER_INTERVAL) {
            A = System.currentTimeMillis();
            c();
            f(context);
            f();
            e();
        }
    }

    private static void c() {
        try {
            TimeZone timeZone = TimeZone.getDefault();
            D = timeZone.getDisplayName(timeZone.inDaylightTime(new Date()), 0);
        } catch (Exception e) {
        }
    }

    private static void f(Context context) {
        if (E.equals(v)) {
            try {
                E = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
            } catch (Exception e) {
            }
        }
    }

    private static boolean d() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    private static void e() {
        try {
            if (d()) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                C = ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
            }
            C = a((double) C);
        } catch (Exception e) {
        }
    }

    private static void f() {
        try {
            if (d()) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                B = ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
            }
            B = a((double) B);
        } catch (Exception e) {
        }
    }

    private static long a(double d) {
        return Math.round(d / 1.073741824E9d);
    }

    public static e b(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONObject(ab.ac).getJSONArray("data");
        List arrayList = new ArrayList(jSONArray.length());
        List arrayList2 = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            String optString = optJSONObject.optString("permission");
            if (!(optString == null || optString.equals("installed"))) {
                String optString2 = optJSONObject.optString("status");
                if (optString2 != null) {
                    if (optString2.equals("granted")) {
                        arrayList.add(optString);
                    } else if (optString2.equals("declined")) {
                        arrayList2.add(optString);
                    }
                }
            }
        }
        return new e(arrayList, arrayList2);
    }
}
