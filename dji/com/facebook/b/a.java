package com.facebook.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.GraphRequest;
import com.facebook.a.b;
import com.facebook.internal.ah;
import com.facebook.internal.ai;
import com.facebook.internal.d;
import com.facebook.k;
import com.facebook.o;
import com.tencent.android.tpush.common.Constants;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    public static final String a = "com.facebook.platform.APPLINK_TAP_TIME_UTC";
    public static final String b = "referer_data";
    public static final String c = "com.facebook.platform.APPLINK_NATIVE_CLASS";
    public static final String d = "com.facebook.platform.APPLINK_NATIVE_URL";
    static final String e = "com.facebook.platform.APPLINK_ARGS";
    private static final String f = "al_applink_data";
    private static final String g = "bridge_args";
    private static final String h = "method_args";
    private static final String i = "version";
    private static final String j = "method";
    private static final String k = "DEFERRED_APP_LINK";
    private static final String l = "%s/activities";
    private static final String m = "applink_args";
    private static final String n = "applink_class";
    private static final String o = "click_time";
    private static final String p = "applink_url";
    private static final String q = "target_url";
    private static final String r = "ref";
    private static final String s = "fb_ref";
    private static final String t = a.class.getCanonicalName();
    private String u;
    private Uri v;
    private JSONObject w;
    private Bundle x;

    public interface a {
        void a(a aVar);
    }

    public static void a(Context context, a aVar) {
        a(context, null, aVar);
    }

    public static void a(Context context, String str, final a aVar) {
        Object a;
        ai.a((Object) context, "context");
        ai.a((Object) aVar, "completionHandler");
        if (str == null) {
            a = ah.a(context);
        }
        ai.a(a, "applicationId");
        final Context applicationContext = context.getApplicationContext();
        o.f().execute(new Runnable() {
            public void run() {
                a.c(applicationContext, a, aVar);
            }
        });
    }

    private static void c(Context context, String str, a aVar) {
        a aVar2 = null;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("event", k);
            ah.a(jSONObject, d.a(context), b.d(context), o.b(context));
            jSONObject.put("application_package_name", context.getPackageName());
            try {
                jSONObject = GraphRequest.a(null, String.format(l, new Object[]{str}), jSONObject, null).m().b();
                if (jSONObject != null) {
                    String optString = jSONObject.optString(m);
                    long optLong = jSONObject.optLong(o, -1);
                    String optString2 = jSONObject.optString(n);
                    String optString3 = jSONObject.optString(p);
                    if (!TextUtils.isEmpty(optString)) {
                        aVar2 = a(optString);
                        if (optLong != -1) {
                            try {
                                if (aVar2.w != null) {
                                    aVar2.w.put(a, optLong);
                                }
                                if (aVar2.x != null) {
                                    aVar2.x.putString(a, Long.toString(optLong));
                                }
                            } catch (JSONException e) {
                                Log.d(t, "Unable to put tap time in AppLinkData.arguments");
                            }
                        }
                        if (optString2 != null) {
                            try {
                                if (aVar2.w != null) {
                                    aVar2.w.put(c, optString2);
                                }
                                if (aVar2.x != null) {
                                    aVar2.x.putString(c, optString2);
                                }
                            } catch (JSONException e2) {
                                Log.d(t, "Unable to put tap time in AppLinkData.arguments");
                            }
                        }
                        if (optString3 != null) {
                            try {
                                if (aVar2.w != null) {
                                    aVar2.w.put(d, optString3);
                                }
                                if (aVar2.x != null) {
                                    aVar2.x.putString(d, optString3);
                                }
                            } catch (JSONException e3) {
                                Log.d(t, "Unable to put tap time in AppLinkData.arguments");
                            }
                        }
                    }
                }
            } catch (Exception e4) {
                ah.c(t, "Unable to fetch deferred applink from server");
            }
            aVar.a(aVar2);
        } catch (Throwable e5) {
            throw new k("An error occurred while preparing deferred app link", e5);
        }
    }

    public static a a(Activity activity) {
        ai.a((Object) activity, Constants.FLAG_ACTIVITY_NAME);
        Intent intent = activity.getIntent();
        if (intent == null) {
            return null;
        }
        a a = a(intent);
        if (a == null) {
            a = a(intent.getStringExtra(e));
        }
        if (a == null) {
            return a(intent.getData());
        }
        return a;
    }

    public static a a(Intent intent) {
        if (intent == null) {
            return null;
        }
        Bundle bundleExtra = intent.getBundleExtra(f);
        if (bundleExtra == null) {
            return null;
        }
        a aVar = new a();
        aVar.v = intent.getData();
        if (aVar.v == null) {
            String string = bundleExtra.getString("target_url");
            if (string != null) {
                aVar.v = Uri.parse(string);
            }
        }
        aVar.x = bundleExtra;
        aVar.w = null;
        Bundle bundle = bundleExtra.getBundle(b);
        if (bundle != null) {
            aVar.u = bundle.getString(s);
        }
        return aVar;
    }

    private static a a(String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("version");
            if (!jSONObject.getJSONObject("bridge_args").getString("method").equals("applink") || !string.equals("2")) {
                return null;
            }
            a aVar = new a();
            aVar.w = jSONObject.getJSONObject("method_args");
            if (aVar.w.has(r)) {
                aVar.u = aVar.w.getString(r);
            } else if (aVar.w.has(b)) {
                jSONObject = aVar.w.getJSONObject(b);
                if (jSONObject.has(s)) {
                    aVar.u = jSONObject.getString(s);
                }
            }
            if (aVar.w.has("target_url")) {
                aVar.v = Uri.parse(aVar.w.getString("target_url"));
            }
            aVar.x = a(aVar.w);
            return aVar;
        } catch (Throwable e) {
            Log.d(t, "Unable to parse AppLink JSON", e);
            return null;
        } catch (Throwable e2) {
            Log.d(t, "Unable to parse AppLink JSON", e2);
            return null;
        }
    }

    private static a a(Uri uri) {
        if (uri == null) {
            return null;
        }
        a aVar = new a();
        aVar.v = uri;
        return aVar;
    }

    private static Bundle a(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object obj = jSONObject.get(str);
            if (obj instanceof JSONObject) {
                bundle.putBundle(str, a((JSONObject) obj));
            } else if (obj instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj;
                if (jSONArray.length() == 0) {
                    bundle.putStringArray(str, new String[0]);
                } else {
                    Object obj2 = jSONArray.get(0);
                    int i;
                    if (obj2 instanceof JSONObject) {
                        Parcelable[] parcelableArr = new Bundle[jSONArray.length()];
                        for (i = 0; i < jSONArray.length(); i++) {
                            parcelableArr[i] = a(jSONArray.getJSONObject(i));
                        }
                        bundle.putParcelableArray(str, parcelableArr);
                    } else if (obj2 instanceof JSONArray) {
                        throw new k("Nested arrays are not supported.");
                    } else {
                        String[] strArr = new String[jSONArray.length()];
                        for (i = 0; i < jSONArray.length(); i++) {
                            strArr[i] = jSONArray.get(i).toString();
                        }
                        bundle.putStringArray(str, strArr);
                    }
                }
            } else {
                bundle.putString(str, obj.toString());
            }
        }
        return bundle;
    }

    private a() {
    }

    public Uri a() {
        return this.v;
    }

    public String b() {
        return this.u;
    }

    public Bundle c() {
        return this.x;
    }

    public Bundle d() {
        if (this.x != null) {
            return this.x.getBundle(b);
        }
        return null;
    }
}
