package com.facebook.b;

import android.net.Uri;
import android.os.Bundle;
import bolts.AppLink;
import bolts.AppLink.Target;
import bolts.AppLinkResolver;
import bolts.Continuation;
import bolts.Task;
import bolts.Task.TaskCompletionSource;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.n;
import com.facebook.v;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements AppLinkResolver {
    private static final String a = "app_links";
    private static final String b = "android";
    private static final String c = "web";
    private static final String d = "package";
    private static final String e = "class";
    private static final String f = "app_name";
    private static final String g = "url";
    private static final String h = "should_fallback";
    private final HashMap<Uri, AppLink> i = new HashMap();

    public Task<AppLink> getAppLinkFromUrlInBackground(final Uri uri) {
        List arrayList = new ArrayList();
        arrayList.add(uri);
        return a(arrayList).onSuccess(new Continuation<Map<Uri, AppLink>, AppLink>(this) {
            final /* synthetic */ b b;

            public /* synthetic */ Object then(Task task) throws Exception {
                return a(task);
            }

            public AppLink a(Task<Map<Uri, AppLink>> task) throws Exception {
                return (AppLink) ((Map) task.getResult()).get(uri);
            }
        });
    }

    public Task<Map<Uri, AppLink>> a(List<Uri> list) {
        final Map hashMap = new HashMap();
        final HashSet hashSet = new HashSet();
        StringBuilder stringBuilder = new StringBuilder();
        for (Uri uri : list) {
            synchronized (this.i) {
                AppLink appLink = (AppLink) this.i.get(uri);
            }
            if (appLink != null) {
                hashMap.put(uri, appLink);
            } else {
                if (!hashSet.isEmpty()) {
                    stringBuilder.append(',');
                }
                stringBuilder.append(uri.toString());
                hashSet.add(uri);
            }
        }
        if (hashSet.isEmpty()) {
            return Task.forResult(hashMap);
        }
        final TaskCompletionSource create = Task.create();
        Bundle bundle = new Bundle();
        bundle.putString("ids", stringBuilder.toString());
        bundle.putString(GraphRequest.c, String.format("%s.fields(%s,%s)", new Object[]{a, "android", "web"}));
        new GraphRequest(AccessToken.a(), "", bundle, null, new com.facebook.GraphRequest.b(this) {
            final /* synthetic */ b d;

            public void onCompleted(v vVar) {
                n a = vVar.a();
                if (a != null) {
                    create.setError(a.n());
                    return;
                }
                JSONObject b = vVar.b();
                if (b == null) {
                    create.setResult(hashMap);
                    return;
                }
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    Uri uri = (Uri) it.next();
                    if (b.has(uri.toString())) {
                        try {
                            JSONObject jSONObject = b.getJSONObject(uri.toString()).getJSONObject(b.a);
                            JSONArray jSONArray = jSONObject.getJSONArray("android");
                            int length = jSONArray.length();
                            List arrayList = new ArrayList(length);
                            for (int i = 0; i < length; i++) {
                                Target a2 = b.b(jSONArray.getJSONObject(i));
                                if (a2 != null) {
                                    arrayList.add(a2);
                                }
                            }
                            AppLink appLink = new AppLink(uri, arrayList, b.b(uri, jSONObject));
                            hashMap.put(uri, appLink);
                            synchronized (this.d.i) {
                                this.d.i.put(uri, appLink);
                            }
                        } catch (JSONException e) {
                        }
                    }
                }
                create.setResult(hashMap);
            }
        }).n();
        return create.getTask();
    }

    private static Target b(JSONObject jSONObject) {
        Uri uri = null;
        String a = a(jSONObject, d, null);
        if (a == null) {
            return null;
        }
        String a2 = a(jSONObject, e, null);
        String a3 = a(jSONObject, "app_name", null);
        String a4 = a(jSONObject, "url", null);
        if (a4 != null) {
            uri = Uri.parse(a4);
        }
        return new Target(a, a2, uri, a3);
    }

    private static Uri b(Uri uri, JSONObject jSONObject) {
        Uri uri2 = null;
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("web");
            if (!a(jSONObject2, h, true)) {
                return null;
            }
            String a = a(jSONObject2, "url", null);
            if (a != null) {
                uri2 = Uri.parse(a);
            }
            if (uri2 != null) {
                return uri2;
            }
            return uri;
        } catch (JSONException e) {
            return uri;
        }
    }

    private static String a(JSONObject jSONObject, String str, String str2) {
        try {
            str2 = jSONObject.getString(str);
        } catch (JSONException e) {
        }
        return str2;
    }

    private static boolean a(JSONObject jSONObject, String str, boolean z) {
        try {
            z = jSONObject.getBoolean(str);
        } catch (JSONException e) {
        }
        return z;
    }
}
