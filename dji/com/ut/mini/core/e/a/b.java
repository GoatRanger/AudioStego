package com.ut.mini.core.e.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import com.ut.mini.base.c;
import com.ut.mini.core.e;
import com.ut.mini.d.d;
import com.ut.mini.d.i;
import com.ut.mini.d.m;
import com.ut.mini.d.n;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private Vector<a> a = new Vector();

    private class a extends Thread {
        final /* synthetic */ b a;
        private String b = null;
        private int[] c = new int[]{2, 2, 4, 4, 8, 8, 16, 16, 32, 64};

        public a(b bVar, String str) {
            this.a = bVar;
            this.b = str;
        }

        public void run() {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (SystemClock.elapsedRealtime() - elapsedRealtime <= 10000) {
                JSONObject a = this.a.b();
                if (a != null) {
                    this.a.a(a);
                }
                com.ut.mini.b.a.b(1, "url", this.b);
                JSONObject a2 = this.a.b();
                String b = this.a.c(a2);
                Map hashMap = new HashMap();
                hashMap.put("cf", b);
                String str = "";
                int i = 0;
                while (n.a(str)) {
                    byte[] a3 = d.a(3, com.ut.mini.core.f.b.b("http://adash.m.taobao.com/rest/gc", null, hashMap), hashMap, true);
                    if (a3 != null && a3.length > 0) {
                        try {
                            String str2 = new String(a3, 0, a3.length, "UTF-8");
                            com.ut.mini.b.a.b(1, "result", str2);
                            if (com.ut.mini.d.b.a(str2)) {
                                b = str2;
                                break;
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                    if (n.a(str)) {
                        try {
                            com.ut.mini.b.a.b(2, "SyncConfThread", "sleep " + (this.c[i] * 1000) + "ms");
                            Thread.sleep((long) (this.c[i] * 1000));
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        i++;
                        if (i >= this.c.length) {
                            com.ut.mini.b.a.b(2, "SyncConfThread", "try end.");
                            b = str;
                            break;
                        }
                    }
                }
                b = str;
                if (n.a(b)) {
                    this.a.c();
                } else {
                    this.a.a(b, a2);
                    e.a().a(true);
                    return;
                }
            }
            e.a().a(true);
        }
    }

    public synchronized void a(a aVar) {
        if (aVar != null) {
            this.a.add(aVar);
        }
    }

    public void a() {
        a aVar = new a(this, null);
        aVar.setDaemon(true);
        aVar.start();
    }

    private synchronized void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            if (this.a != null) {
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    if (aVar != null) {
                        List<String> d = aVar.d();
                        if (d != null && d.size() > 0) {
                            for (String str : d) {
                                if (jSONObject.has(str)) {
                                    try {
                                        aVar.a(str, ((JSONObject) jSONObject.get(str)).getString("content"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    try {
                                        aVar.b(str);
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                            continue;
                        }
                    }
                }
            }
        }
    }

    private void a(String str, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            if (jSONObject2 != null) {
                JSONObject jSONObject3 = null;
                if (jSONObject2.has("data") && !n.a(jSONObject2.getString("data"))) {
                    jSONObject3 = jSONObject2.getJSONObject("data");
                }
                if (jSONObject3 == null) {
                    jSONObject3 = new JSONObject();
                }
                jSONObject3 = a(jSONObject3, jSONObject);
                b(jSONObject3);
                a(jSONObject3);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            JSONObject jSONObject3 = new JSONObject();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                JSONObject optJSONObject = jSONObject.optJSONObject(str);
                if (optJSONObject != null && optJSONObject.has("content")) {
                    JSONObject jSONObject4 = new JSONObject();
                    if ("gc_304".equals(optJSONObject.optString("content"))) {
                        optJSONObject = jSONObject2.optJSONObject(str);
                        jSONObject4.put("t", optJSONObject.getLong("t"));
                        jSONObject4.put("content", optJSONObject.get("content"));
                    } else {
                        jSONObject4.put("t", optJSONObject.getLong("t"));
                        jSONObject4.put("content", optJSONObject.get("content"));
                    }
                    jSONObject3.put(str, jSONObject4);
                }
            }
            return jSONObject3;
        } catch (Exception e) {
            return new JSONObject();
        }
    }

    @TargetApi(9)
    private synchronized void b(JSONObject jSONObject) {
        if (jSONObject != null) {
            Context k = c.a().k();
            SharedPreferences sharedPreferences = k.getSharedPreferences(i.a(k, "UTMCConf"), 0);
            if (sharedPreferences != null) {
                Editor edit = sharedPreferences.edit();
                edit.putString("conf_cache", com.ut.mini.d.c.b(jSONObject.toString().getBytes(), 2));
                m.a(edit);
            }
        }
    }

    private synchronized JSONObject b() {
        JSONObject jSONObject;
        try {
            Context k = c.a().k();
            SharedPreferences sharedPreferences = k.getSharedPreferences(i.a(k, "UTMCConf"), 0);
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString("conf_cache", "");
                if (!n.a(string)) {
                    byte[] a = com.ut.mini.d.c.a(string.getBytes("UTF-8"), 2);
                    if (a != null && a.length > 0) {
                        jSONObject = new JSONObject(new String(a, "UTF-8"));
                    }
                }
            }
        } catch (Exception e) {
        }
        jSONObject = null;
        return jSONObject;
    }

    private String c(JSONObject jSONObject) {
        if (jSONObject == null) {
            return "";
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                JSONObject optJSONObject = jSONObject.optJSONObject(str);
                if (optJSONObject != null && optJSONObject.has("t") && optJSONObject.optLong("t") > 0) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("t", optJSONObject.getLong("t"));
                    jSONObject2.put(str, jSONObject3);
                }
            }
            return jSONObject2.toString();
        } catch (Exception e) {
            return "";
        }
    }

    private void c() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            List<String> d = aVar.d();
            if (d != null && d.size() > 0) {
                for (String b : d) {
                    aVar.b(b);
                }
            }
        }
    }
}
