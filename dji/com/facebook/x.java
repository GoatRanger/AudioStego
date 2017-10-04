package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import com.facebook.internal.ah;
import com.facebook.internal.ai;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class x {
    private static final String A = "double";
    private static final String B = "double[]";
    private static final String C = "char";
    private static final String D = "char[]";
    private static final String E = "string";
    private static final String F = "stringList";
    private static final String G = "enum";
    public static final String a = "com.facebook.TokenCachingStrategy.Token";
    public static final String b = "com.facebook.TokenCachingStrategy.ExpirationDate";
    public static final String c = "com.facebook.TokenCachingStrategy.LastRefreshDate";
    public static final String d = "com.facebook.TokenCachingStrategy.AccessTokenSource";
    public static final String e = "com.facebook.TokenCachingStrategy.Permissions";
    public static final String f = "com.facebook.TokenCachingStrategy.DeclinedPermissions";
    public static final String g = "com.facebook.TokenCachingStrategy.ApplicationId";
    public static final String h = "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY";
    private static final long i = Long.MIN_VALUE;
    private static final String j = "com.facebook.TokenCachingStrategy.IsSSO";
    private static final String k = x.class.getSimpleName();
    private static final String l = "valueType";
    private static final String m = "value";
    private static final String n = "enumType";
    private static final String o = "bool";
    private static final String p = "bool[]";
    private static final String q = "byte";
    private static final String r = "byte[]";
    private static final String s = "short";
    private static final String t = "short[]";
    private static final String u = "int";
    private static final String v = "int[]";
    private static final String w = "long";
    private static final String x = "long[]";
    private static final String y = "float";
    private static final String z = "float[]";
    private String H;
    private SharedPreferences I;

    public x(Context context) {
        this(context, null);
    }

    public x(Context context, String str) {
        ai.a((Object) context, "context");
        if (ah.a(str)) {
            str = h;
        }
        this.H = str;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        this.I = context.getSharedPreferences(this.H, 0);
    }

    public Bundle a() {
        Bundle bundle = new Bundle();
        for (String str : this.I.getAll().keySet()) {
            try {
                a(str, bundle);
            } catch (JSONException e) {
                com.facebook.internal.x.a(y.CACHE, 5, k, "Error reading cached value for key: '" + str + "' -- " + e);
                return null;
            }
        }
        return bundle;
    }

    public void a(Bundle bundle) {
        ai.a((Object) bundle, "bundle");
        Editor edit = this.I.edit();
        for (String str : bundle.keySet()) {
            try {
                a(str, bundle, edit);
            } catch (JSONException e) {
                com.facebook.internal.x.a(y.CACHE, 5, k, "Error processing value for key: '" + str + "' -- " + e);
                return;
            }
        }
        edit.apply();
    }

    public void b() {
        this.I.edit().clear().apply();
    }

    public static boolean b(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        String string = bundle.getString(a);
        if (string == null || string.length() == 0 || bundle.getLong(b, 0) == 0) {
            return false;
        }
        return true;
    }

    public static String c(Bundle bundle) {
        ai.a((Object) bundle, "bundle");
        return bundle.getString(a);
    }

    public static void a(Bundle bundle, String str) {
        ai.a((Object) bundle, "bundle");
        ai.a((Object) str, m);
        bundle.putString(a, str);
    }

    public static Date d(Bundle bundle) {
        ai.a((Object) bundle, "bundle");
        return c(bundle, b);
    }

    public static void a(Bundle bundle, Date date) {
        ai.a((Object) bundle, "bundle");
        ai.a((Object) date, m);
        a(bundle, b, date);
    }

    public static long e(Bundle bundle) {
        ai.a((Object) bundle, "bundle");
        return bundle.getLong(b);
    }

    public static void a(Bundle bundle, long j) {
        ai.a((Object) bundle, "bundle");
        bundle.putLong(b, j);
    }

    public static Set<String> f(Bundle bundle) {
        ai.a((Object) bundle, "bundle");
        Collection stringArrayList = bundle.getStringArrayList(e);
        if (stringArrayList == null) {
            return null;
        }
        return new HashSet(stringArrayList);
    }

    public static void a(Bundle bundle, Collection<String> collection) {
        ai.a((Object) bundle, "bundle");
        ai.a((Object) collection, m);
        bundle.putStringArrayList(e, new ArrayList(collection));
    }

    public static void b(Bundle bundle, Collection<String> collection) {
        ai.a((Object) bundle, "bundle");
        ai.a((Object) collection, m);
        bundle.putStringArrayList(f, new ArrayList(collection));
    }

    public static c g(Bundle bundle) {
        ai.a((Object) bundle, "bundle");
        if (bundle.containsKey(d)) {
            return (c) bundle.getSerializable(d);
        }
        return bundle.getBoolean(j) ? c.FACEBOOK_APPLICATION_WEB : c.WEB_VIEW;
    }

    public static void a(Bundle bundle, c cVar) {
        ai.a((Object) bundle, "bundle");
        bundle.putSerializable(d, cVar);
    }

    public static Date h(Bundle bundle) {
        ai.a((Object) bundle, "bundle");
        return c(bundle, c);
    }

    public static void b(Bundle bundle, Date date) {
        ai.a((Object) bundle, "bundle");
        ai.a((Object) date, m);
        a(bundle, c, date);
    }

    public static long i(Bundle bundle) {
        ai.a((Object) bundle, "bundle");
        return bundle.getLong(c);
    }

    public static void b(Bundle bundle, long j) {
        ai.a((Object) bundle, "bundle");
        bundle.putLong(c, j);
    }

    public static String j(Bundle bundle) {
        ai.a((Object) bundle, "bundle");
        return bundle.getString(g);
    }

    public static void b(Bundle bundle, String str) {
        ai.a((Object) bundle, "bundle");
        bundle.putString(g, str);
    }

    static Date c(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        long j = bundle.getLong(str, Long.MIN_VALUE);
        if (j != Long.MIN_VALUE) {
            return new Date(j);
        }
        return null;
    }

    static void a(Bundle bundle, String str, Date date) {
        bundle.putLong(str, date.getTime());
    }

    private void a(String str, Bundle bundle, Editor editor) throws JSONException {
        Object obj = null;
        int i = 0;
        Object obj2 = bundle.get(str);
        if (obj2 != null) {
            JSONObject jSONObject = new JSONObject();
            String str2;
            if (obj2 instanceof Byte) {
                str2 = q;
                jSONObject.put(m, ((Byte) obj2).intValue());
                obj2 = null;
                obj = str2;
            } else if (obj2 instanceof Short) {
                str2 = s;
                jSONObject.put(m, ((Short) obj2).intValue());
                obj2 = null;
                r1 = str2;
            } else if (obj2 instanceof Integer) {
                str2 = u;
                jSONObject.put(m, ((Integer) obj2).intValue());
                obj2 = null;
                r1 = str2;
            } else if (obj2 instanceof Long) {
                str2 = w;
                jSONObject.put(m, ((Long) obj2).longValue());
                obj2 = null;
                r1 = str2;
            } else if (obj2 instanceof Float) {
                str2 = y;
                jSONObject.put(m, ((Float) obj2).doubleValue());
                obj2 = null;
                r1 = str2;
            } else if (obj2 instanceof Double) {
                str2 = A;
                jSONObject.put(m, ((Double) obj2).doubleValue());
                obj2 = null;
                r1 = str2;
            } else if (obj2 instanceof Boolean) {
                str2 = o;
                jSONObject.put(m, ((Boolean) obj2).booleanValue());
                obj2 = null;
                r1 = str2;
            } else if (obj2 instanceof Character) {
                str2 = C;
                jSONObject.put(m, obj2.toString());
                obj2 = null;
                r1 = str2;
            } else if (obj2 instanceof String) {
                str2 = E;
                jSONObject.put(m, (String) obj2);
                obj2 = null;
                r1 = str2;
            } else if (obj2 instanceof Enum) {
                str2 = G;
                jSONObject.put(m, obj2.toString());
                jSONObject.put(n, obj2.getClass().getName());
                obj2 = null;
                r1 = str2;
            } else {
                JSONArray jSONArray = new JSONArray();
                int length;
                JSONArray jSONArray2;
                if (obj2 instanceof byte[]) {
                    obj = r;
                    byte[] bArr = (byte[]) obj2;
                    length = bArr.length;
                    while (i < length) {
                        jSONArray.put(bArr[i]);
                        i++;
                    }
                    jSONArray2 = jSONArray;
                } else if (obj2 instanceof short[]) {
                    obj = t;
                    short[] sArr = (short[]) obj2;
                    length = sArr.length;
                    while (i < length) {
                        jSONArray.put(sArr[i]);
                        i++;
                    }
                    jSONArray2 = jSONArray;
                } else if (obj2 instanceof int[]) {
                    obj = v;
                    int[] iArr = (int[]) obj2;
                    length = iArr.length;
                    while (i < length) {
                        jSONArray.put(iArr[i]);
                        i++;
                    }
                    jSONArray2 = jSONArray;
                } else if (obj2 instanceof long[]) {
                    obj = x;
                    long[] jArr = (long[]) obj2;
                    length = jArr.length;
                    while (i < length) {
                        jSONArray.put(jArr[i]);
                        i++;
                    }
                    jSONArray2 = jSONArray;
                } else if (obj2 instanceof float[]) {
                    obj = z;
                    float[] fArr = (float[]) obj2;
                    length = fArr.length;
                    while (i < length) {
                        jSONArray.put((double) fArr[i]);
                        i++;
                    }
                    jSONArray2 = jSONArray;
                } else if (obj2 instanceof double[]) {
                    obj = B;
                    double[] dArr = (double[]) obj2;
                    length = dArr.length;
                    while (i < length) {
                        jSONArray.put(dArr[i]);
                        i++;
                    }
                    jSONArray2 = jSONArray;
                } else if (obj2 instanceof boolean[]) {
                    obj = p;
                    boolean[] zArr = (boolean[]) obj2;
                    length = zArr.length;
                    while (i < length) {
                        jSONArray.put(zArr[i]);
                        i++;
                    }
                    jSONArray2 = jSONArray;
                } else if (obj2 instanceof char[]) {
                    obj = D;
                    char[] cArr = (char[]) obj2;
                    length = cArr.length;
                    while (i < length) {
                        jSONArray.put(String.valueOf(cArr[i]));
                        i++;
                    }
                    jSONArray2 = jSONArray;
                } else if (obj2 instanceof List) {
                    obj = F;
                    for (Object obj22 : (List) obj22) {
                        if (obj22 == null) {
                            obj22 = JSONObject.NULL;
                        }
                        jSONArray.put(obj22);
                    }
                    jSONArray2 = jSONArray;
                } else {
                    obj22 = null;
                }
            }
            if (obj != null) {
                jSONObject.put(l, obj);
                if (obj22 != null) {
                    jSONObject.putOpt(m, obj22);
                }
                editor.putString(str, jSONObject.toString());
            }
        }
    }

    private void a(String str, Bundle bundle) throws JSONException {
        int i = 0;
        JSONObject jSONObject = new JSONObject(this.I.getString(str, "{}"));
        String string = jSONObject.getString(l);
        if (string.equals(o)) {
            bundle.putBoolean(str, jSONObject.getBoolean(m));
        } else if (string.equals(p)) {
            r1 = jSONObject.getJSONArray(m);
            boolean[] zArr = new boolean[r1.length()];
            while (i < zArr.length) {
                zArr[i] = r1.getBoolean(i);
                i++;
            }
            bundle.putBooleanArray(str, zArr);
        } else if (string.equals(q)) {
            bundle.putByte(str, (byte) jSONObject.getInt(m));
        } else if (string.equals(r)) {
            r1 = jSONObject.getJSONArray(m);
            byte[] bArr = new byte[r1.length()];
            while (i < bArr.length) {
                bArr[i] = (byte) r1.getInt(i);
                i++;
            }
            bundle.putByteArray(str, bArr);
        } else if (string.equals(s)) {
            bundle.putShort(str, (short) jSONObject.getInt(m));
        } else if (string.equals(t)) {
            r1 = jSONObject.getJSONArray(m);
            short[] sArr = new short[r1.length()];
            while (i < sArr.length) {
                sArr[i] = (short) r1.getInt(i);
                i++;
            }
            bundle.putShortArray(str, sArr);
        } else if (string.equals(u)) {
            bundle.putInt(str, jSONObject.getInt(m));
        } else if (string.equals(v)) {
            r1 = jSONObject.getJSONArray(m);
            int[] iArr = new int[r1.length()];
            while (i < iArr.length) {
                iArr[i] = r1.getInt(i);
                i++;
            }
            bundle.putIntArray(str, iArr);
        } else if (string.equals(w)) {
            bundle.putLong(str, jSONObject.getLong(m));
        } else if (string.equals(x)) {
            r1 = jSONObject.getJSONArray(m);
            long[] jArr = new long[r1.length()];
            while (i < jArr.length) {
                jArr[i] = r1.getLong(i);
                i++;
            }
            bundle.putLongArray(str, jArr);
        } else if (string.equals(y)) {
            bundle.putFloat(str, (float) jSONObject.getDouble(m));
        } else if (string.equals(z)) {
            r1 = jSONObject.getJSONArray(m);
            float[] fArr = new float[r1.length()];
            while (i < fArr.length) {
                fArr[i] = (float) r1.getDouble(i);
                i++;
            }
            bundle.putFloatArray(str, fArr);
        } else if (string.equals(A)) {
            bundle.putDouble(str, jSONObject.getDouble(m));
        } else if (string.equals(B)) {
            r1 = jSONObject.getJSONArray(m);
            double[] dArr = new double[r1.length()];
            while (i < dArr.length) {
                dArr[i] = r1.getDouble(i);
                i++;
            }
            bundle.putDoubleArray(str, dArr);
        } else if (string.equals(C)) {
            string = jSONObject.getString(m);
            if (string != null && string.length() == 1) {
                bundle.putChar(str, string.charAt(0));
            }
        } else if (string.equals(D)) {
            r2 = jSONObject.getJSONArray(m);
            char[] cArr = new char[r2.length()];
            for (r1 = 0; r1 < cArr.length; r1++) {
                String string2 = r2.getString(r1);
                if (string2 != null && string2.length() == 1) {
                    cArr[r1] = string2.charAt(0);
                }
            }
            bundle.putCharArray(str, cArr);
        } else if (string.equals(E)) {
            bundle.putString(str, jSONObject.getString(m));
        } else if (string.equals(F)) {
            r2 = jSONObject.getJSONArray(m);
            int length = r2.length();
            ArrayList arrayList = new ArrayList(length);
            for (r1 = 0; r1 < length; r1++) {
                Object obj = r2.get(r1);
                if (obj == JSONObject.NULL) {
                    obj = null;
                } else {
                    String str2 = (String) obj;
                }
                arrayList.add(r1, obj);
            }
            bundle.putStringArrayList(str, arrayList);
        } else if (string.equals(G)) {
            try {
                bundle.putSerializable(str, Enum.valueOf(Class.forName(jSONObject.getString(n)), jSONObject.getString(m)));
            } catch (ClassNotFoundException e) {
            } catch (IllegalArgumentException e2) {
            }
        }
    }
}
