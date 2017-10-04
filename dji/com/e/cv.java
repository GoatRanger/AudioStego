package com.e;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.android.tpush.common.Constants;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONException;
import org.json.JSONObject;

public class cv {
    public static int a = -1;
    public static String b = "";

    public static class a {
        public String a;
        public int b = -1;
        public JSONObject c;
        public JSONObject d;
        public JSONObject e;
        public JSONObject f;
        public JSONObject g;
        public JSONObject h;
        public JSONObject i;
        public JSONObject j;
        public JSONObject k;
        public a l;
        public c m;
        public b n;

        public static class a {
            public boolean a;
            public boolean b;
        }

        public static class b {
            public String a;
            public String b;
        }

        public static class c {
            public String a;
            public String b;
            public String c;
        }
    }

    static class b extends ak {
        private String f;
        private Map<String, String> g;

        b(Context context, dc dcVar, String str, Map<String, String> map) {
            super(context, dcVar);
            this.f = str;
            this.g = map;
        }

        private Map<String, String> k() {
            Object q = cx.q(this.a);
            if (!TextUtils.isEmpty(q)) {
                q = cz.b(new StringBuilder(q).reverse().toString());
            }
            Map<String, String> hashMap = new HashMap();
            hashMap.put("authkey", this.f);
            hashMap.put("plattype", "android");
            hashMap.put("product", this.b.a());
            hashMap.put("version", this.b.b());
            hashMap.put("output", "json");
            hashMap.put("androidversion", VERSION.SDK_INT + "");
            hashMap.put(Constants.FLAG_DEVICE_ID, q);
            if (!(this.g == null || this.g.isEmpty())) {
                hashMap.putAll(this.g);
            }
            if (VERSION.SDK_INT >= 21) {
                try {
                    ApplicationInfo applicationInfo = this.a.getApplicationInfo();
                    Field declaredField = Class.forName(ApplicationInfo.class.getName()).getDeclaredField("primaryCpuAbi");
                    declaredField.setAccessible(true);
                    q = (String) declaredField.get(applicationInfo);
                } catch (Throwable th) {
                    dg.a(th, "ConfigManager", "getcpu");
                }
                if (TextUtils.isEmpty(q)) {
                    q = Build.CPU_ABI;
                }
                hashMap.put("abitype", q);
                hashMap.put("ext", this.b.c());
                return hashMap;
            }
            q = null;
            if (TextUtils.isEmpty(q)) {
                q = Build.CPU_ABI;
            }
            hashMap.put("abitype", q);
            hashMap.put("ext", this.b.c());
            return hashMap;
        }

        public Map<String, String> b() {
            return null;
        }

        public String c() {
            return "https://restapi.amap.com/v3/iasdkauth";
        }

        protected String f() {
            return "3.0";
        }

        public byte[] g() {
            return null;
        }

        public byte[] h() {
            return dd.a(dd.a(k()));
        }
    }

    public static a a(Context context, dc dcVar, String str, Map<String, String> map) {
        byte[] a;
        String a2;
        ct e;
        JSONObject jSONObject;
        int i;
        a aVar;
        JSONObject jSONObject2;
        c cVar;
        b bVar;
        Throwable th;
        String str2 = null;
        a aVar2 = new a();
        try {
            a = new aj().a(new b(context, dcVar, str, map));
            try {
                Object obj = new byte[16];
                Object obj2 = new byte[(a.length - 16)];
                System.arraycopy(a, 0, obj, 0, 16);
                System.arraycopy(a, 16, obj2, 0, a.length - 16);
                Key secretKeySpec = new SecretKeySpec(obj, "AES");
                Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(2, secretKeySpec, new IvParameterSpec(dd.a()));
                a2 = dd.a(instance.doFinal(obj2));
            } catch (ct e2) {
                e = e2;
                aVar2.a = e.a();
                a2 = str2;
                if (a != null) {
                    return aVar2;
                }
                if (TextUtils.isEmpty(a2)) {
                    a2 = dd.a(a);
                }
                try {
                    jSONObject = new JSONObject(a2);
                    if (jSONObject.has("status")) {
                        i = jSONObject.getInt("status");
                        if (i != 1) {
                            a = 1;
                        } else if (i == 0) {
                            a = 0;
                            if (jSONObject.has("info")) {
                                b = jSONObject.getString("info");
                            }
                            if (a == 0) {
                                aVar2.a = b;
                                return aVar2;
                            }
                        }
                        if (jSONObject.has("ver")) {
                            aVar2.b = jSONObject.getInt("ver");
                        }
                        if (dd.a(jSONObject, "result")) {
                            aVar = new a();
                            aVar.a = false;
                            aVar.b = false;
                            aVar2.l = aVar;
                            jSONObject = jSONObject.getJSONObject("result");
                            if (dd.a(jSONObject, "11K")) {
                                aVar.a = a(jSONObject.getJSONObject("11K").getString("able"), false);
                            }
                            if (dd.a(jSONObject, "11B")) {
                                aVar2.c = jSONObject.getJSONObject("11B");
                            }
                            if (dd.a(jSONObject, "11C")) {
                                aVar2.d = jSONObject.getJSONObject("11C");
                            }
                            if (dd.a(jSONObject, "11I")) {
                                aVar2.e = jSONObject.getJSONObject("11I");
                            }
                            if (dd.a(jSONObject, "11H")) {
                                aVar2.f = jSONObject.getJSONObject("11H");
                            }
                            if (dd.a(jSONObject, "11E")) {
                                aVar2.g = jSONObject.getJSONObject("11E");
                            }
                            if (dd.a(jSONObject, "11F")) {
                                aVar2.h = jSONObject.getJSONObject("11F");
                            }
                            if (dd.a(jSONObject, "11G")) {
                                aVar2.i = jSONObject.getJSONObject("11G");
                            }
                            if (dd.a(jSONObject, "001")) {
                                jSONObject2 = jSONObject.getJSONObject("001");
                                cVar = new c();
                                a(jSONObject2, cVar);
                                aVar2.m = cVar;
                            }
                            if (dd.a(jSONObject, "002")) {
                                jSONObject2 = jSONObject.getJSONObject("002");
                                bVar = new b();
                                a(jSONObject2, bVar);
                                aVar2.n = bVar;
                            }
                            if (dd.a(jSONObject, "006")) {
                                aVar2.j = jSONObject.getJSONObject("006");
                            }
                            if (dd.a(jSONObject, "010")) {
                                aVar2.k = jSONObject.getJSONObject("010");
                            }
                        }
                        return aVar2;
                    }
                } catch (Throwable th2) {
                    dg.a(th2, "AuthConfigManager", "loadConfig");
                }
                return aVar2;
            } catch (IllegalBlockSizeException e3) {
                a2 = str2;
                if (a != null) {
                    return aVar2;
                }
                if (TextUtils.isEmpty(a2)) {
                    a2 = dd.a(a);
                }
                jSONObject = new JSONObject(a2);
                if (jSONObject.has("status")) {
                    i = jSONObject.getInt("status");
                    if (i != 1) {
                        a = 1;
                    } else if (i == 0) {
                        a = 0;
                        if (jSONObject.has("info")) {
                            b = jSONObject.getString("info");
                        }
                        if (a == 0) {
                            aVar2.a = b;
                            return aVar2;
                        }
                    }
                    if (jSONObject.has("ver")) {
                        aVar2.b = jSONObject.getInt("ver");
                    }
                    if (dd.a(jSONObject, "result")) {
                        aVar = new a();
                        aVar.a = false;
                        aVar.b = false;
                        aVar2.l = aVar;
                        jSONObject = jSONObject.getJSONObject("result");
                        if (dd.a(jSONObject, "11K")) {
                            aVar.a = a(jSONObject.getJSONObject("11K").getString("able"), false);
                        }
                        if (dd.a(jSONObject, "11B")) {
                            aVar2.c = jSONObject.getJSONObject("11B");
                        }
                        if (dd.a(jSONObject, "11C")) {
                            aVar2.d = jSONObject.getJSONObject("11C");
                        }
                        if (dd.a(jSONObject, "11I")) {
                            aVar2.e = jSONObject.getJSONObject("11I");
                        }
                        if (dd.a(jSONObject, "11H")) {
                            aVar2.f = jSONObject.getJSONObject("11H");
                        }
                        if (dd.a(jSONObject, "11E")) {
                            aVar2.g = jSONObject.getJSONObject("11E");
                        }
                        if (dd.a(jSONObject, "11F")) {
                            aVar2.h = jSONObject.getJSONObject("11F");
                        }
                        if (dd.a(jSONObject, "11G")) {
                            aVar2.i = jSONObject.getJSONObject("11G");
                        }
                        if (dd.a(jSONObject, "001")) {
                            jSONObject2 = jSONObject.getJSONObject("001");
                            cVar = new c();
                            a(jSONObject2, cVar);
                            aVar2.m = cVar;
                        }
                        if (dd.a(jSONObject, "002")) {
                            jSONObject2 = jSONObject.getJSONObject("002");
                            bVar = new b();
                            a(jSONObject2, bVar);
                            aVar2.n = bVar;
                        }
                        if (dd.a(jSONObject, "006")) {
                            aVar2.j = jSONObject.getJSONObject("006");
                        }
                        if (dd.a(jSONObject, "010")) {
                            aVar2.k = jSONObject.getJSONObject("010");
                        }
                    }
                    return aVar2;
                }
                return aVar2;
            } catch (Throwable th3) {
                th2 = th3;
                dg.a(th2, "ConfigManager", "loadConfig");
                a2 = str2;
                if (a != null) {
                    return aVar2;
                }
                if (TextUtils.isEmpty(a2)) {
                    a2 = dd.a(a);
                }
                jSONObject = new JSONObject(a2);
                if (jSONObject.has("status")) {
                    i = jSONObject.getInt("status");
                    if (i != 1) {
                        a = 1;
                    } else if (i == 0) {
                        a = 0;
                        if (jSONObject.has("info")) {
                            b = jSONObject.getString("info");
                        }
                        if (a == 0) {
                            aVar2.a = b;
                            return aVar2;
                        }
                    }
                    if (jSONObject.has("ver")) {
                        aVar2.b = jSONObject.getInt("ver");
                    }
                    if (dd.a(jSONObject, "result")) {
                        aVar = new a();
                        aVar.a = false;
                        aVar.b = false;
                        aVar2.l = aVar;
                        jSONObject = jSONObject.getJSONObject("result");
                        if (dd.a(jSONObject, "11K")) {
                            aVar.a = a(jSONObject.getJSONObject("11K").getString("able"), false);
                        }
                        if (dd.a(jSONObject, "11B")) {
                            aVar2.c = jSONObject.getJSONObject("11B");
                        }
                        if (dd.a(jSONObject, "11C")) {
                            aVar2.d = jSONObject.getJSONObject("11C");
                        }
                        if (dd.a(jSONObject, "11I")) {
                            aVar2.e = jSONObject.getJSONObject("11I");
                        }
                        if (dd.a(jSONObject, "11H")) {
                            aVar2.f = jSONObject.getJSONObject("11H");
                        }
                        if (dd.a(jSONObject, "11E")) {
                            aVar2.g = jSONObject.getJSONObject("11E");
                        }
                        if (dd.a(jSONObject, "11F")) {
                            aVar2.h = jSONObject.getJSONObject("11F");
                        }
                        if (dd.a(jSONObject, "11G")) {
                            aVar2.i = jSONObject.getJSONObject("11G");
                        }
                        if (dd.a(jSONObject, "001")) {
                            jSONObject2 = jSONObject.getJSONObject("001");
                            cVar = new c();
                            a(jSONObject2, cVar);
                            aVar2.m = cVar;
                        }
                        if (dd.a(jSONObject, "002")) {
                            jSONObject2 = jSONObject.getJSONObject("002");
                            bVar = new b();
                            a(jSONObject2, bVar);
                            aVar2.n = bVar;
                        }
                        if (dd.a(jSONObject, "006")) {
                            aVar2.j = jSONObject.getJSONObject("006");
                        }
                        if (dd.a(jSONObject, "010")) {
                            aVar2.k = jSONObject.getJSONObject("010");
                        }
                    }
                    return aVar2;
                }
                return aVar2;
            }
        } catch (ct e4) {
            e = e4;
            a = str2;
            aVar2.a = e.a();
            a2 = str2;
            if (a != null) {
                return aVar2;
            }
            if (TextUtils.isEmpty(a2)) {
                a2 = dd.a(a);
            }
            jSONObject = new JSONObject(a2);
            if (jSONObject.has("status")) {
                i = jSONObject.getInt("status");
                if (i != 1) {
                    a = 1;
                } else if (i == 0) {
                    a = 0;
                    if (jSONObject.has("info")) {
                        b = jSONObject.getString("info");
                    }
                    if (a == 0) {
                        aVar2.a = b;
                        return aVar2;
                    }
                }
                if (jSONObject.has("ver")) {
                    aVar2.b = jSONObject.getInt("ver");
                }
                if (dd.a(jSONObject, "result")) {
                    aVar = new a();
                    aVar.a = false;
                    aVar.b = false;
                    aVar2.l = aVar;
                    jSONObject = jSONObject.getJSONObject("result");
                    if (dd.a(jSONObject, "11K")) {
                        aVar.a = a(jSONObject.getJSONObject("11K").getString("able"), false);
                    }
                    if (dd.a(jSONObject, "11B")) {
                        aVar2.c = jSONObject.getJSONObject("11B");
                    }
                    if (dd.a(jSONObject, "11C")) {
                        aVar2.d = jSONObject.getJSONObject("11C");
                    }
                    if (dd.a(jSONObject, "11I")) {
                        aVar2.e = jSONObject.getJSONObject("11I");
                    }
                    if (dd.a(jSONObject, "11H")) {
                        aVar2.f = jSONObject.getJSONObject("11H");
                    }
                    if (dd.a(jSONObject, "11E")) {
                        aVar2.g = jSONObject.getJSONObject("11E");
                    }
                    if (dd.a(jSONObject, "11F")) {
                        aVar2.h = jSONObject.getJSONObject("11F");
                    }
                    if (dd.a(jSONObject, "11G")) {
                        aVar2.i = jSONObject.getJSONObject("11G");
                    }
                    if (dd.a(jSONObject, "001")) {
                        jSONObject2 = jSONObject.getJSONObject("001");
                        cVar = new c();
                        a(jSONObject2, cVar);
                        aVar2.m = cVar;
                    }
                    if (dd.a(jSONObject, "002")) {
                        jSONObject2 = jSONObject.getJSONObject("002");
                        bVar = new b();
                        a(jSONObject2, bVar);
                        aVar2.n = bVar;
                    }
                    if (dd.a(jSONObject, "006")) {
                        aVar2.j = jSONObject.getJSONObject("006");
                    }
                    if (dd.a(jSONObject, "010")) {
                        aVar2.k = jSONObject.getJSONObject("010");
                    }
                }
                return aVar2;
            }
            return aVar2;
        } catch (IllegalBlockSizeException e5) {
            a = str2;
            a2 = str2;
            if (a != null) {
                return aVar2;
            }
            if (TextUtils.isEmpty(a2)) {
                a2 = dd.a(a);
            }
            jSONObject = new JSONObject(a2);
            if (jSONObject.has("status")) {
                i = jSONObject.getInt("status");
                if (i != 1) {
                    a = 1;
                } else if (i == 0) {
                    a = 0;
                    if (jSONObject.has("info")) {
                        b = jSONObject.getString("info");
                    }
                    if (a == 0) {
                        aVar2.a = b;
                        return aVar2;
                    }
                }
                if (jSONObject.has("ver")) {
                    aVar2.b = jSONObject.getInt("ver");
                }
                if (dd.a(jSONObject, "result")) {
                    aVar = new a();
                    aVar.a = false;
                    aVar.b = false;
                    aVar2.l = aVar;
                    jSONObject = jSONObject.getJSONObject("result");
                    if (dd.a(jSONObject, "11K")) {
                        aVar.a = a(jSONObject.getJSONObject("11K").getString("able"), false);
                    }
                    if (dd.a(jSONObject, "11B")) {
                        aVar2.c = jSONObject.getJSONObject("11B");
                    }
                    if (dd.a(jSONObject, "11C")) {
                        aVar2.d = jSONObject.getJSONObject("11C");
                    }
                    if (dd.a(jSONObject, "11I")) {
                        aVar2.e = jSONObject.getJSONObject("11I");
                    }
                    if (dd.a(jSONObject, "11H")) {
                        aVar2.f = jSONObject.getJSONObject("11H");
                    }
                    if (dd.a(jSONObject, "11E")) {
                        aVar2.g = jSONObject.getJSONObject("11E");
                    }
                    if (dd.a(jSONObject, "11F")) {
                        aVar2.h = jSONObject.getJSONObject("11F");
                    }
                    if (dd.a(jSONObject, "11G")) {
                        aVar2.i = jSONObject.getJSONObject("11G");
                    }
                    if (dd.a(jSONObject, "001")) {
                        jSONObject2 = jSONObject.getJSONObject("001");
                        cVar = new c();
                        a(jSONObject2, cVar);
                        aVar2.m = cVar;
                    }
                    if (dd.a(jSONObject, "002")) {
                        jSONObject2 = jSONObject.getJSONObject("002");
                        bVar = new b();
                        a(jSONObject2, bVar);
                        aVar2.n = bVar;
                    }
                    if (dd.a(jSONObject, "006")) {
                        aVar2.j = jSONObject.getJSONObject("006");
                    }
                    if (dd.a(jSONObject, "010")) {
                        aVar2.k = jSONObject.getJSONObject("010");
                    }
                }
                return aVar2;
            }
            return aVar2;
        } catch (Throwable th4) {
            th2 = th4;
            a = str2;
            dg.a(th2, "ConfigManager", "loadConfig");
            a2 = str2;
            if (a != null) {
                return aVar2;
            }
            if (TextUtils.isEmpty(a2)) {
                a2 = dd.a(a);
            }
            jSONObject = new JSONObject(a2);
            if (jSONObject.has("status")) {
                i = jSONObject.getInt("status");
                if (i != 1) {
                    a = 1;
                } else if (i == 0) {
                    a = 0;
                    if (jSONObject.has("info")) {
                        b = jSONObject.getString("info");
                    }
                    if (a == 0) {
                        aVar2.a = b;
                        return aVar2;
                    }
                }
                if (jSONObject.has("ver")) {
                    aVar2.b = jSONObject.getInt("ver");
                }
                if (dd.a(jSONObject, "result")) {
                    aVar = new a();
                    aVar.a = false;
                    aVar.b = false;
                    aVar2.l = aVar;
                    jSONObject = jSONObject.getJSONObject("result");
                    if (dd.a(jSONObject, "11K")) {
                        aVar.a = a(jSONObject.getJSONObject("11K").getString("able"), false);
                    }
                    if (dd.a(jSONObject, "11B")) {
                        aVar2.c = jSONObject.getJSONObject("11B");
                    }
                    if (dd.a(jSONObject, "11C")) {
                        aVar2.d = jSONObject.getJSONObject("11C");
                    }
                    if (dd.a(jSONObject, "11I")) {
                        aVar2.e = jSONObject.getJSONObject("11I");
                    }
                    if (dd.a(jSONObject, "11H")) {
                        aVar2.f = jSONObject.getJSONObject("11H");
                    }
                    if (dd.a(jSONObject, "11E")) {
                        aVar2.g = jSONObject.getJSONObject("11E");
                    }
                    if (dd.a(jSONObject, "11F")) {
                        aVar2.h = jSONObject.getJSONObject("11F");
                    }
                    if (dd.a(jSONObject, "11G")) {
                        aVar2.i = jSONObject.getJSONObject("11G");
                    }
                    if (dd.a(jSONObject, "001")) {
                        jSONObject2 = jSONObject.getJSONObject("001");
                        cVar = new c();
                        a(jSONObject2, cVar);
                        aVar2.m = cVar;
                    }
                    if (dd.a(jSONObject, "002")) {
                        jSONObject2 = jSONObject.getJSONObject("002");
                        bVar = new b();
                        a(jSONObject2, bVar);
                        aVar2.n = bVar;
                    }
                    if (dd.a(jSONObject, "006")) {
                        aVar2.j = jSONObject.getJSONObject("006");
                    }
                    if (dd.a(jSONObject, "010")) {
                        aVar2.k = jSONObject.getJSONObject("010");
                    }
                }
                return aVar2;
            }
            return aVar2;
        }
        if (a != null) {
            return aVar2;
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = dd.a(a);
        }
        jSONObject = new JSONObject(a2);
        if (jSONObject.has("status")) {
            i = jSONObject.getInt("status");
            if (i != 1) {
                a = 1;
            } else if (i == 0) {
                a = 0;
                if (jSONObject.has("info")) {
                    b = jSONObject.getString("info");
                }
                if (a == 0) {
                    aVar2.a = b;
                    return aVar2;
                }
            }
            if (jSONObject.has("ver")) {
                aVar2.b = jSONObject.getInt("ver");
            }
            if (dd.a(jSONObject, "result")) {
                aVar = new a();
                aVar.a = false;
                aVar.b = false;
                aVar2.l = aVar;
                jSONObject = jSONObject.getJSONObject("result");
                if (dd.a(jSONObject, "11K")) {
                    aVar.a = a(jSONObject.getJSONObject("11K").getString("able"), false);
                }
                if (dd.a(jSONObject, "11B")) {
                    aVar2.c = jSONObject.getJSONObject("11B");
                }
                if (dd.a(jSONObject, "11C")) {
                    aVar2.d = jSONObject.getJSONObject("11C");
                }
                if (dd.a(jSONObject, "11I")) {
                    aVar2.e = jSONObject.getJSONObject("11I");
                }
                if (dd.a(jSONObject, "11H")) {
                    aVar2.f = jSONObject.getJSONObject("11H");
                }
                if (dd.a(jSONObject, "11E")) {
                    aVar2.g = jSONObject.getJSONObject("11E");
                }
                if (dd.a(jSONObject, "11F")) {
                    aVar2.h = jSONObject.getJSONObject("11F");
                }
                if (dd.a(jSONObject, "11G")) {
                    aVar2.i = jSONObject.getJSONObject("11G");
                }
                if (dd.a(jSONObject, "001")) {
                    jSONObject2 = jSONObject.getJSONObject("001");
                    cVar = new c();
                    a(jSONObject2, cVar);
                    aVar2.m = cVar;
                }
                if (dd.a(jSONObject, "002")) {
                    jSONObject2 = jSONObject.getJSONObject("002");
                    bVar = new b();
                    a(jSONObject2, bVar);
                    aVar2.n = bVar;
                }
                if (dd.a(jSONObject, "006")) {
                    aVar2.j = jSONObject.getJSONObject("006");
                }
                if (dd.a(jSONObject, "010")) {
                    aVar2.k = jSONObject.getJSONObject("010");
                }
            }
            return aVar2;
        }
        return aVar2;
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null) {
            return "";
        }
        String str2 = "";
        return (!jSONObject.has(str) || jSONObject.getString(str).equals("[]")) ? str2 : jSONObject.optString(str);
    }

    public static void a(String str) {
        cu.c(str);
    }

    private static void a(JSONObject jSONObject, b bVar) {
        if (jSONObject != null) {
            try {
                String a = a(jSONObject, n.K);
                String a2 = a(jSONObject, "url");
                bVar.b = a;
                bVar.a = a2;
            } catch (Throwable th) {
                dg.a(th, "ConfigManager", "parseSDKCoordinate");
            }
        }
    }

    private static void a(JSONObject jSONObject, c cVar) {
        if (jSONObject != null) {
            try {
                Object a = a(jSONObject, n.K);
                Object a2 = a(jSONObject, "url");
                Object a3 = a(jSONObject, "sdkversion");
                if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3)) {
                    cVar.a = a2;
                    cVar.b = a;
                    cVar.c = a3;
                }
            } catch (Throwable th) {
                dg.a(th, "ConfigManager", "parseSDKUpdate");
            }
        }
    }

    public static boolean a(String str, boolean z) {
        try {
            String[] split = URLDecoder.decode(str).split(d.t);
            return split[split.length + -1].charAt(4) % 2 == 1;
        } catch (Throwable th) {
            return z;
        }
    }
}
