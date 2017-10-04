package com.facebook;

import com.alipay.sdk.j.i;
import com.facebook.internal.ah;
import com.facebook.internal.l;
import com.facebook.internal.x;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class v {
    public static final String a = "FACEBOOK_NON_JSON_RESULT";
    public static final String b = "success";
    private static final String i = "code";
    private static final String j = "body";
    private static final String k = "Response";
    private final HttpURLConnection c;
    private final JSONObject d;
    private final JSONArray e;
    private final n f;
    private final String g;
    private final GraphRequest h;

    public enum a {
        NEXT,
        PREVIOUS
    }

    v(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject) {
        this(graphRequest, httpURLConnection, str, jSONObject, null, null);
    }

    v(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONArray jSONArray) {
        this(graphRequest, httpURLConnection, str, null, jSONArray, null);
    }

    v(GraphRequest graphRequest, HttpURLConnection httpURLConnection, n nVar) {
        this(graphRequest, httpURLConnection, null, null, null, nVar);
    }

    v(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject, JSONArray jSONArray, n nVar) {
        this.h = graphRequest;
        this.c = httpURLConnection;
        this.g = str;
        this.d = jSONObject;
        this.e = jSONArray;
        this.f = nVar;
    }

    public final n a() {
        return this.f;
    }

    public final JSONObject b() {
        return this.d;
    }

    public final JSONArray c() {
        return this.e;
    }

    public final HttpURLConnection d() {
        return this.c;
    }

    public GraphRequest e() {
        return this.h;
    }

    public String f() {
        return this.g;
    }

    public GraphRequest a(a aVar) {
        String optString;
        if (this.d != null) {
            JSONObject optJSONObject = this.d.optJSONObject("paging");
            if (optJSONObject != null) {
                if (aVar == a.NEXT) {
                    optString = optJSONObject.optString("next");
                } else {
                    optString = optJSONObject.optString("previous");
                }
                if (ah.a(optString)) {
                    return null;
                }
                if (optString == null && optString.equals(this.h.p())) {
                    return null;
                }
                try {
                    return new GraphRequest(this.h.f(), new URL(optString));
                } catch (MalformedURLException e) {
                    return null;
                }
            }
        }
        optString = null;
        if (ah.a(optString)) {
            return null;
        }
        if (optString == null) {
        }
        return new GraphRequest(this.h.f(), new URL(optString));
    }

    public String toString() {
        String format;
        try {
            Locale locale = Locale.US;
            String str = "%d";
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(this.c != null ? this.c.getResponseCode() : 200);
            format = String.format(locale, str, objArr);
        } catch (IOException e) {
            format = "unknown";
        }
        return "{Response: " + " responseCode: " + format + ", graphObject: " + this.d + ", error: " + this.f + i.d;
    }

    static List<v> a(HttpURLConnection httpURLConnection, u uVar) {
        List<v> a;
        Closeable closeable = null;
        try {
            if (httpURLConnection.getResponseCode() >= 400) {
                closeable = httpURLConnection.getErrorStream();
            } else {
                closeable = httpURLConnection.getInputStream();
            }
            a = a((InputStream) closeable, httpURLConnection, uVar);
        } catch (k e) {
            x.a(y.REQUESTS, k, "Response <Error>: %s", e);
            a = a((List) uVar, httpURLConnection, e);
        } catch (Throwable e2) {
            x.a(y.REQUESTS, k, "Response <Error>: %s", e2);
            a = a((List) uVar, httpURLConnection, new k(e2));
        } catch (Throwable e22) {
            x.a(y.REQUESTS, k, "Response <Error>: %s", e22);
            a = a((List) uVar, httpURLConnection, new k(e22));
        } catch (Throwable e222) {
            x.a(y.REQUESTS, k, "Response <Error>: %s", e222);
            a = a((List) uVar, httpURLConnection, new k(e222));
        } finally {
            ah.a(closeable);
        }
        return a;
    }

    static List<v> a(InputStream inputStream, HttpURLConnection httpURLConnection, u uVar) throws k, JSONException, IOException {
        x.a(y.INCLUDE_RAW_RESPONSES, k, "Response (raw)\n  Size: %d\n  Response:\n%s\n", Integer.valueOf(ah.a(inputStream).length()), r0);
        return a(ah.a(inputStream), httpURLConnection, uVar);
    }

    static List<v> a(String str, HttpURLConnection httpURLConnection, u uVar) throws k, JSONException, IOException {
        x.a(y.REQUESTS, k, "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", uVar.b(), Integer.valueOf(str.length()), a(httpURLConnection, (List) uVar, new JSONTokener(str).nextValue()));
        return a(httpURLConnection, (List) uVar, new JSONTokener(str).nextValue());
    }

    private static List<v> a(HttpURLConnection httpURLConnection, List<GraphRequest> list, Object obj) throws k, JSONException {
        JSONArray jSONArray;
        int i = 0;
        int size = list.size();
        List<v> arrayList = new ArrayList(size);
        if (size == 1) {
            GraphRequest graphRequest = (GraphRequest) list.get(0);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(j, obj);
                jSONObject.put("code", httpURLConnection != null ? httpURLConnection.getResponseCode() : 200);
                jSONArray = new JSONArray();
                jSONArray.put(jSONObject);
            } catch (Exception e) {
                arrayList.add(new v(graphRequest, httpURLConnection, new n(httpURLConnection, e)));
                jSONArray = obj;
            } catch (Exception e2) {
                arrayList.add(new v(graphRequest, httpURLConnection, new n(httpURLConnection, e2)));
            }
            if ((jSONArray instanceof JSONArray) || jSONArray.length() != size) {
                throw new k("Unexpected number of results");
            }
            jSONArray = jSONArray;
            while (i < jSONArray.length()) {
                graphRequest = (GraphRequest) list.get(i);
                try {
                    arrayList.add(a(graphRequest, httpURLConnection, jSONArray.get(i), obj));
                } catch (Exception e3) {
                    arrayList.add(new v(graphRequest, httpURLConnection, new n(httpURLConnection, e3)));
                } catch (Exception e32) {
                    arrayList.add(new v(graphRequest, httpURLConnection, new n(httpURLConnection, e32)));
                }
                i++;
            }
            return arrayList;
        }
        jSONArray = obj;
        if (jSONArray instanceof JSONArray) {
        }
        throw new k("Unexpected number of results");
    }

    private static v a(GraphRequest graphRequest, HttpURLConnection httpURLConnection, Object obj, Object obj2) throws JSONException {
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            n a = n.a(jSONObject, obj2, httpURLConnection);
            if (a != null) {
                if (a.c() == l.f && ah.a(graphRequest.f())) {
                    AccessToken.a(null);
                }
                return new v(graphRequest, httpURLConnection, a);
            }
            Object a2 = ah.a(jSONObject, j, a);
            if (a2 instanceof JSONObject) {
                return new v(graphRequest, httpURLConnection, a2.toString(), (JSONObject) a2);
            }
            if (a2 instanceof JSONArray) {
                return new v(graphRequest, httpURLConnection, a2.toString(), (JSONArray) a2);
            }
            obj = JSONObject.NULL;
        }
        if (obj == JSONObject.NULL) {
            return new v(graphRequest, httpURLConnection, obj.toString(), (JSONObject) null);
        }
        throw new k("Got unexpected object type in response, class: " + obj.getClass().getSimpleName());
    }

    static List<v> a(List<GraphRequest> list, HttpURLConnection httpURLConnection, k kVar) {
        int size = list.size();
        List<v> arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new v((GraphRequest) list.get(i), httpURLConnection, new n(httpURLConnection, kVar)));
        }
        return arrayList;
    }
}
