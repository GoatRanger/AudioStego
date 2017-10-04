package com.facebook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.location.Location;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.alibaba.sdk.android.man.util.MANConfig;
import com.alipay.sdk.j.i;
import com.facebook.internal.ab;
import com.facebook.internal.af;
import com.facebook.internal.ah;
import com.facebook.internal.ai;
import com.facebook.internal.u;
import com.facebook.internal.x;
import com.facebook.share.internal.k;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.SharePhoto;
import com.google.api.client.http.UrlEncodedParser;
import com.here.odnp.debug.DebugFile;
import com.loopj.android.http.AsyncHttpClient;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import dji.pilot.usercenter.mode.n;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphRequest {
    private static final String A = "file";
    private static final String B = "attached_files";
    private static final String C = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String D = "debug";
    private static final String E = "info";
    private static final String F = "warning";
    private static final String G = "__debug__";
    private static final String H = "messages";
    private static final String I = "message";
    private static final String J = "type";
    private static final String K = "link";
    private static final String L = "picture";
    private static final String M = "caption";
    private static final String N = "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f";
    private static final String O = "%s/%s";
    private static String P = null;
    private static Pattern Q = Pattern.compile("^/?v\\d+\\.\\d+/(.*)");
    public static final int a = 50;
    private static volatile String ae = null;
    public static final String b = GraphRequest.class.getSimpleName();
    public static final String c = "fields";
    private static final String d = "/videos";
    private static final String e = "me";
    private static final String f = "me/friends";
    private static final String g = "me/photos";
    private static final String h = "search";
    private static final String i = "FBAndroidSDK";
    private static final String j = "User-Agent";
    private static final String k = "Content-Type";
    private static final String l = "Accept-Language";
    private static final String m = "Content-Encoding";
    private static final String n = "format";
    private static final String o = "json";
    private static final String p = "sdk";
    private static final String q = "android";
    private static final String r = "access_token";
    private static final String s = "name";
    private static final String t = "omit_response_on_success";
    private static final String u = "depends_on";
    private static final String v = "batch_app_id";
    private static final String w = "relative_url";
    private static final String x = "body";
    private static final String y = "method";
    private static final String z = "batch";
    private AccessToken R;
    private w S;
    private String T;
    private JSONObject U;
    private String V;
    private String W;
    private boolean X;
    private Bundle Y;
    private b Z;
    private String aa;
    private Object ab;
    private String ac;
    private boolean ad;

    public interface b {
        void onCompleted(v vVar);
    }

    private interface e {
        void a(String str, String str2) throws IOException;
    }

    public static class ParcelableResourceWithMimeType<RESOURCE extends Parcelable> implements Parcelable {
        public static final Creator<ParcelableResourceWithMimeType> CREATOR = new Creator<ParcelableResourceWithMimeType>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public ParcelableResourceWithMimeType a(Parcel parcel) {
                return new ParcelableResourceWithMimeType(parcel);
            }

            public ParcelableResourceWithMimeType[] a(int i) {
                return new ParcelableResourceWithMimeType[i];
            }
        };
        private final String a;
        private final RESOURCE b;

        public String a() {
            return this.a;
        }

        public RESOURCE b() {
            return this.b;
        }

        public int describeContents() {
            return 1;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
            parcel.writeParcelable(this.b, i);
        }

        public ParcelableResourceWithMimeType(RESOURCE resource, String str) {
            this.a = str;
            this.b = resource;
        }

        private ParcelableResourceWithMimeType(Parcel parcel) {
            this.a = parcel.readString();
            this.b = parcel.readParcelable(o.h().getClassLoader());
        }
    }

    private static class a {
        private final GraphRequest a;
        private final Object b;

        public a(GraphRequest graphRequest, Object obj) {
            this.a = graphRequest;
            this.b = obj;
        }

        public GraphRequest a() {
            return this.a;
        }

        public Object b() {
            return this.b;
        }
    }

    public interface c {
        void a(JSONArray jSONArray, v vVar);
    }

    public interface d {
        void a(JSONObject jSONObject, v vVar);
    }

    public interface f extends b {
        void a(long j, long j2);
    }

    private static class g implements e {
        private final OutputStream a;
        private final x b;
        private boolean c = true;
        private boolean d = false;

        public g(OutputStream outputStream, x xVar, boolean z) {
            this.a = outputStream;
            this.b = xVar;
            this.d = z;
        }

        public void a(String str, Object obj, GraphRequest graphRequest) throws IOException {
            if (this.a instanceof ae) {
                ((ae) this.a).a(graphRequest);
            }
            if (GraphRequest.e(obj)) {
                a(str, GraphRequest.f(obj));
            } else if (obj instanceof Bitmap) {
                a(str, (Bitmap) obj);
            } else if (obj instanceof byte[]) {
                a(str, (byte[]) obj);
            } else if (obj instanceof Uri) {
                a(str, (Uri) obj, null);
            } else if (obj instanceof ParcelFileDescriptor) {
                a(str, (ParcelFileDescriptor) obj, null);
            } else if (obj instanceof ParcelableResourceWithMimeType) {
                ParcelableResourceWithMimeType parcelableResourceWithMimeType = (ParcelableResourceWithMimeType) obj;
                Parcelable b = parcelableResourceWithMimeType.b();
                String a = parcelableResourceWithMimeType.a();
                if (b instanceof ParcelFileDescriptor) {
                    a(str, (ParcelFileDescriptor) b, a);
                } else if (b instanceof Uri) {
                    a(str, (Uri) b, a);
                } else {
                    throw b();
                }
            } else {
                throw b();
            }
        }

        private RuntimeException b() {
            return new IllegalArgumentException("value is not a supported type.");
        }

        public void a(String str, JSONArray jSONArray, Collection<GraphRequest> collection) throws IOException, JSONException {
            if (this.a instanceof ae) {
                ae aeVar = (ae) this.a;
                a(str, null, null);
                a(dji.pilot.usercenter.protocol.d.G, new Object[0]);
                int i = 0;
                for (GraphRequest graphRequest : collection) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    aeVar.a(graphRequest);
                    if (i > 0) {
                        a(",%s", jSONObject.toString());
                    } else {
                        a("%s", jSONObject.toString());
                    }
                    i++;
                }
                a(dji.pilot.usercenter.protocol.d.H, new Object[0]);
                if (this.b != null) {
                    this.b.a("    " + str, jSONArray.toString());
                    return;
                }
                return;
            }
            a(str, jSONArray.toString());
        }

        public void a(String str, String str2) throws IOException {
            a(str, null, null);
            b("%s", str2);
            a();
            if (this.b != null) {
                this.b.a("    " + str, (Object) str2);
            }
        }

        public void a(String str, Bitmap bitmap) throws IOException {
            a(str, str, "image/png");
            bitmap.compress(CompressFormat.PNG, 100, this.a);
            b("", new Object[0]);
            a();
            if (this.b != null) {
                this.b.a("    " + str, (Object) "<Image>");
            }
        }

        public void a(String str, byte[] bArr) throws IOException {
            a(str, str, "content/unknown");
            this.a.write(bArr);
            b("", new Object[0]);
            a();
            if (this.b != null) {
                this.b.a("    " + str, String.format(Locale.ROOT, "<Data: %d>", new Object[]{Integer.valueOf(bArr.length)}));
            }
        }

        public void a(String str, Uri uri, String str2) throws IOException {
            int i;
            if (str2 == null) {
                str2 = "content/unknown";
            }
            a(str, str, str2);
            if (this.a instanceof ac) {
                ((ac) this.a).a(ah.e(uri));
                i = 0;
            } else {
                i = ah.a(o.h().getContentResolver().openInputStream(uri), this.a) + 0;
            }
            b("", new Object[0]);
            a();
            if (this.b != null) {
                this.b.a("    " + str, String.format(Locale.ROOT, "<Data: %d>", new Object[]{Integer.valueOf(i)}));
            }
        }

        public void a(String str, ParcelFileDescriptor parcelFileDescriptor, String str2) throws IOException {
            int i;
            if (str2 == null) {
                str2 = "content/unknown";
            }
            a(str, str, str2);
            if (this.a instanceof ac) {
                ((ac) this.a).a(parcelFileDescriptor.getStatSize());
                i = 0;
            } else {
                i = ah.a(new AutoCloseInputStream(parcelFileDescriptor), this.a) + 0;
            }
            b("", new Object[0]);
            a();
            if (this.b != null) {
                this.b.a("    " + str, String.format(Locale.ROOT, "<Data: %d>", new Object[]{Integer.valueOf(i)}));
            }
        }

        public void a() throws IOException {
            if (this.d) {
                this.a.write(com.alipay.sdk.h.a.b.getBytes());
                return;
            }
            b("--%s", GraphRequest.N);
        }

        public void a(String str, String str2, String str3) throws IOException {
            if (this.d) {
                this.a.write(String.format("%s=", new Object[]{str}).getBytes());
                return;
            }
            a("Content-Disposition: form-data; name=\"%s\"", str);
            if (str2 != null) {
                a("; filename=\"%s\"", str2);
            }
            b("", new Object[0]);
            if (str3 != null) {
                b("%s: %s", "Content-Type", str3);
            }
            b("", new Object[0]);
        }

        public void a(String str, Object... objArr) throws IOException {
            if (this.d) {
                this.a.write(URLEncoder.encode(String.format(Locale.US, str, objArr), "UTF-8").getBytes());
                return;
            }
            if (this.c) {
                this.a.write("--".getBytes());
                this.a.write(GraphRequest.N.getBytes());
                this.a.write(DebugFile.EOL.getBytes());
                this.c = false;
            }
            this.a.write(String.format(str, objArr).getBytes());
        }

        public void b(String str, Object... objArr) throws IOException {
            a(str, objArr);
            if (!this.d) {
                a(DebugFile.EOL, new Object[0]);
            }
        }
    }

    public GraphRequest() {
        this(null, null, null, null, null);
    }

    public GraphRequest(AccessToken accessToken, String str) {
        this(accessToken, str, null, null, null);
    }

    public GraphRequest(AccessToken accessToken, String str, Bundle bundle, w wVar) {
        this(accessToken, str, bundle, wVar, null);
    }

    public GraphRequest(AccessToken accessToken, String str, Bundle bundle, w wVar, b bVar) {
        this(accessToken, str, bundle, wVar, bVar, null);
    }

    public GraphRequest(AccessToken accessToken, String str, Bundle bundle, w wVar, b bVar, String str2) {
        this.X = true;
        this.ad = false;
        this.R = accessToken;
        this.T = str;
        this.ac = str2;
        a(bVar);
        a(wVar);
        if (bundle != null) {
            this.Y = new Bundle(bundle);
        } else {
            this.Y = new Bundle();
        }
        if (this.ac == null) {
            this.ac = af.d();
        }
    }

    GraphRequest(AccessToken accessToken, URL url) {
        this.X = true;
        this.ad = false;
        this.R = accessToken;
        this.aa = url.toString();
        a(w.a);
        this.Y = new Bundle();
    }

    public static GraphRequest a(AccessToken accessToken, String str, b bVar) {
        return new GraphRequest(accessToken, str, null, w.c, bVar);
    }

    public static GraphRequest a(AccessToken accessToken, final d dVar) {
        return new GraphRequest(accessToken, e, null, null, new b() {
            public void onCompleted(v vVar) {
                if (dVar != null) {
                    dVar.a(vVar.b(), vVar);
                }
            }
        });
    }

    public static GraphRequest a(AccessToken accessToken, String str, JSONObject jSONObject, b bVar) {
        GraphRequest graphRequest = new GraphRequest(accessToken, str, null, w.b, bVar);
        graphRequest.a(jSONObject);
        return graphRequest;
    }

    public static GraphRequest a(AccessToken accessToken, final c cVar) {
        return new GraphRequest(accessToken, f, null, null, new b() {
            public void onCompleted(v vVar) {
                if (cVar != null) {
                    JSONObject b = vVar.b();
                    cVar.a(b != null ? b.optJSONArray("data") : null, vVar);
                }
            }
        });
    }

    public static GraphRequest b(AccessToken accessToken, String str, b bVar) {
        return new GraphRequest(accessToken, str, null, null, bVar);
    }

    public static GraphRequest a(AccessToken accessToken, Location location, int i, int i2, String str, final c cVar) {
        if (location == null && ah.a(str)) {
            throw new k("Either location or searchText must be specified.");
        }
        Bundle bundle = new Bundle(5);
        bundle.putString("type", "place");
        bundle.putInt("limit", i2);
        if (location != null) {
            bundle.putString("center", String.format(Locale.US, "%f,%f", new Object[]{Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude())}));
            bundle.putInt(n.ay, i);
        }
        if (!ah.a(str)) {
            bundle.putString(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, str);
        }
        return new GraphRequest(accessToken, h, bundle, w.a, new b() {
            public void onCompleted(v vVar) {
                if (cVar != null) {
                    JSONObject b = vVar.b();
                    cVar.a(b != null ? b.optJSONArray("data") : null, vVar);
                }
            }
        });
    }

    public static GraphRequest a(AccessToken accessToken, String str, Bitmap bitmap, String str2, Bundle bundle, b bVar) {
        String f = f(str);
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putParcelable("picture", bitmap);
        if (!(str2 == null || str2.isEmpty())) {
            bundle2.putString("caption", str2);
        }
        return new GraphRequest(accessToken, f, bundle2, w.b, bVar);
    }

    public static GraphRequest a(AccessToken accessToken, String str, File file, String str2, Bundle bundle, b bVar) throws FileNotFoundException {
        String f = f(str);
        Parcelable open = ParcelFileDescriptor.open(file, 268435456);
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        bundle2.putParcelable("picture", open);
        if (!(str2 == null || str2.isEmpty())) {
            bundle2.putString("caption", str2);
        }
        return new GraphRequest(accessToken, f, bundle2, w.b, bVar);
    }

    public static GraphRequest a(AccessToken accessToken, String str, Uri uri, String str2, Bundle bundle, b bVar) throws FileNotFoundException {
        String f = f(str);
        if (ah.d(uri)) {
            return a(accessToken, f, new File(uri.getPath()), str2, bundle, bVar);
        } else if (ah.c(uri)) {
            Bundle bundle2 = new Bundle();
            if (bundle != null) {
                bundle2.putAll(bundle);
            }
            bundle2.putParcelable("picture", uri);
            return new GraphRequest(accessToken, f, bundle2, w.b, bVar);
        } else {
            throw new k("The photo Uri must be either a file:// or content:// Uri");
        }
    }

    public static GraphRequest a(AccessToken accessToken, Context context, String str, b bVar) {
        String str2;
        if (str != null || accessToken == null) {
            str2 = str;
        } else {
            str2 = accessToken.i();
        }
        if (str2 == null) {
            str2 = ah.a(context);
        }
        if (str2 == null) {
            throw new k("Facebook App ID cannot be determined");
        }
        String str3 = str2 + "/custom_audience_third_party_id";
        com.facebook.internal.d a = com.facebook.internal.d.a(context);
        Bundle bundle = new Bundle();
        if (accessToken == null) {
            if (a == null) {
                throw new k("There is no access token and attribution identifiers could not be retrieved");
            }
            str2 = a.a() != null ? a.a() : a.b();
            if (a.a() != null) {
                bundle.putString("udid", str2);
            }
        }
        if (o.b(context) || (a != null && a.d())) {
            bundle.putString("limit_event_usage", "1");
        }
        return new GraphRequest(accessToken, str3, bundle, w.a, bVar);
    }

    public static GraphRequest a(AccessToken accessToken, Context context, b bVar) {
        return a(accessToken, context, null, bVar);
    }

    public final JSONObject a() {
        return this.U;
    }

    public final void a(JSONObject jSONObject) {
        this.U = jSONObject;
    }

    public final String b() {
        return this.T;
    }

    public final void a(String str) {
        this.T = str;
    }

    public final w c() {
        return this.S;
    }

    public final void a(w wVar) {
        if (this.aa == null || wVar == w.a) {
            if (wVar == null) {
                wVar = w.a;
            }
            this.S = wVar;
            return;
        }
        throw new k("Can't change HTTP method on request with overridden URL.");
    }

    public final String d() {
        return this.ac;
    }

    public final void b(String str) {
        this.ac = str;
    }

    public final void a(boolean z) {
        this.ad = z;
    }

    public final Bundle e() {
        return this.Y;
    }

    public final void a(Bundle bundle) {
        this.Y = bundle;
    }

    public final AccessToken f() {
        return this.R;
    }

    public final void a(AccessToken accessToken) {
        this.R = accessToken;
    }

    public final String g() {
        return this.V;
    }

    public final void c(String str) {
        this.V = str;
    }

    public final String h() {
        return this.W;
    }

    public final void d(String str) {
        this.W = str;
    }

    public final boolean i() {
        return this.X;
    }

    public final void b(boolean z) {
        this.X = z;
    }

    public static final String j() {
        return P;
    }

    public static final void e(String str) {
        P = str;
    }

    public final b k() {
        return this.Z;
    }

    public final void a(final b bVar) {
        if (o.c(y.GRAPH_API_DEBUG_INFO) || o.c(y.GRAPH_API_DEBUG_WARNING)) {
            this.Z = new b(this) {
                final /* synthetic */ GraphRequest b;

                public void onCompleted(v vVar) {
                    JSONArray optJSONArray;
                    JSONObject b = vVar.b();
                    b = b != null ? b.optJSONObject(GraphRequest.G) : null;
                    if (b != null) {
                        optJSONArray = b.optJSONArray(GraphRequest.H);
                    } else {
                        optJSONArray = null;
                    }
                    if (optJSONArray != null) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            String optString;
                            String optString2;
                            String optString3;
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                optString = optJSONObject.optString("message");
                            } else {
                                optString = null;
                            }
                            if (optJSONObject != null) {
                                optString2 = optJSONObject.optString("type");
                            } else {
                                optString2 = null;
                            }
                            if (optJSONObject != null) {
                                optString3 = optJSONObject.optString("link");
                            } else {
                                optString3 = null;
                            }
                            if (!(optString == null || optString2 == null)) {
                                y yVar = y.GRAPH_API_DEBUG_INFO;
                                if (optString2.equals(GraphRequest.F)) {
                                    yVar = y.GRAPH_API_DEBUG_WARNING;
                                }
                                if (!ah.a(optString3)) {
                                    optString = optString + " Link: " + optString3;
                                }
                                x.a(yVar, GraphRequest.b, optString);
                            }
                        }
                    }
                    if (bVar != null) {
                        bVar.onCompleted(vVar);
                    }
                }
            };
        } else {
            this.Z = bVar;
        }
    }

    public final void a(Object obj) {
        this.ab = obj;
    }

    public final Object l() {
        return this.ab;
    }

    public final v m() {
        return a(this);
    }

    public final t n() {
        return c(this);
    }

    public static HttpURLConnection a(GraphRequest... graphRequestArr) {
        return a(Arrays.asList(graphRequestArr));
    }

    public static HttpURLConnection a(Collection<GraphRequest> collection) {
        ai.d(collection, "requests");
        return a(new u((Collection) collection));
    }

    public static HttpURLConnection a(u uVar) {
        Throwable e;
        d(uVar);
        try {
            URL url;
            if (uVar.size() == 1) {
                url = new URL(uVar.b(0).p());
            } else {
                url = new URL(af.b());
            }
            URLConnection uRLConnection = null;
            try {
                uRLConnection = a(url);
                a(uVar, (HttpURLConnection) uRLConnection);
                return uRLConnection;
            } catch (IOException e2) {
                e = e2;
                ah.a(uRLConnection);
                throw new k("could not construct request body", e);
            } catch (JSONException e3) {
                e = e3;
                ah.a(uRLConnection);
                throw new k("could not construct request body", e);
            }
        } catch (Throwable e4) {
            throw new k("could not construct URL for request", e4);
        }
    }

    public static v a(GraphRequest graphRequest) {
        List b = b(graphRequest);
        if (b != null && b.size() == 1) {
            return (v) b.get(0);
        }
        throw new k("invalid state: expected a single response");
    }

    public static List<v> b(GraphRequest... graphRequestArr) {
        ai.a((Object) graphRequestArr, "requests");
        return b(Arrays.asList(graphRequestArr));
    }

    public static List<v> b(Collection<GraphRequest> collection) {
        return b(new u((Collection) collection));
    }

    public static List<v> b(u uVar) {
        List<v> a;
        URLConnection uRLConnection = null;
        ai.d(uVar, "requests");
        try {
            uRLConnection = a(uVar);
            a = a((HttpURLConnection) uRLConnection, uVar);
        } catch (Throwable e) {
            a = v.a(uVar.d(), null, new k(e));
            a(uVar, (List) a);
        } finally {
            ah.a(uRLConnection);
        }
        return a;
    }

    public static t c(GraphRequest... graphRequestArr) {
        ai.a((Object) graphRequestArr, "requests");
        return c(Arrays.asList(graphRequestArr));
    }

    public static t c(Collection<GraphRequest> collection) {
        return c(new u((Collection) collection));
    }

    public static t c(u uVar) {
        ai.d(uVar, "requests");
        t tVar = new t(uVar);
        tVar.executeOnExecutor(o.f(), new Void[0]);
        return tVar;
    }

    public static List<v> a(HttpURLConnection httpURLConnection, Collection<GraphRequest> collection) {
        return a(httpURLConnection, new u((Collection) collection));
    }

    public static List<v> a(HttpURLConnection httpURLConnection, u uVar) {
        List a = v.a(httpURLConnection, uVar);
        ah.a((URLConnection) httpURLConnection);
        if (uVar.size() != a.size()) {
            throw new k(String.format(Locale.US, "Received %d responses while expecting %d", new Object[]{Integer.valueOf(a.size()), Integer.valueOf(uVar.size())}));
        }
        a(uVar, a);
        b.a().d();
        return a;
    }

    public static t b(HttpURLConnection httpURLConnection, u uVar) {
        return a(null, httpURLConnection, uVar);
    }

    public static t a(Handler handler, HttpURLConnection httpURLConnection, u uVar) {
        ai.a((Object) httpURLConnection, "connection");
        t tVar = new t(httpURLConnection, uVar);
        uVar.a(handler);
        tVar.executeOnExecutor(o.f(), new Void[0]);
        return tVar;
    }

    public String toString() {
        return "{Request: " + " accessToken: " + (this.R == null ? "null" : this.R) + ", graphPath: " + this.T + ", graphObject: " + this.U + ", httpMethod: " + this.S + ", parameters: " + this.Y + i.d;
    }

    static void a(final u uVar, List<v> list) {
        int size = uVar.size();
        final ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            GraphRequest b = uVar.b(i);
            if (b.Z != null) {
                arrayList.add(new Pair(b.Z, list.get(i)));
            }
        }
        if (arrayList.size() > 0) {
            Runnable anonymousClass5 = new Runnable() {
                public void run() {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        Pair pair = (Pair) it.next();
                        ((b) pair.first).onCompleted((v) pair.second);
                    }
                    for (com.facebook.u.a a : uVar.e()) {
                        a.a(uVar);
                    }
                }
            };
            Handler c = uVar.c();
            if (c == null) {
                anonymousClass5.run();
            } else {
                c.post(anonymousClass5);
            }
        }
    }

    private static String f(String str) {
        return str == null ? "me/photos" : str;
    }

    private static HttpURLConnection a(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty(j, t());
        httpURLConnection.setRequestProperty(l, Locale.getDefault().toString());
        httpURLConnection.setChunkedStreamingMode(0);
        return httpURLConnection;
    }

    private void q() {
        String c;
        if (this.R != null) {
            if (!this.Y.containsKey("access_token")) {
                c = this.R.c();
                x.a(c);
                this.Y.putString("access_token", c);
            }
        } else if (!(this.ad || this.Y.containsKey("access_token"))) {
            c = o.k();
            String m = o.m();
            if (ah.a(c) || ah.a(m)) {
                Log.d(b, "Warning: Request without access token missing application ID or client token.");
            } else {
                this.Y.putString("access_token", c + "|" + m);
            }
        }
        this.Y.putString("sdk", "android");
        this.Y.putString(n, o);
        if (o.c(y.GRAPH_API_DEBUG_INFO)) {
            this.Y.putString(D, E);
        } else if (o.c(y.GRAPH_API_DEBUG_WARNING)) {
            this.Y.putString(D, F);
        }
    }

    private String g(String str) {
        Builder encodedPath = new Builder().encodedPath(str);
        for (String str2 : this.Y.keySet()) {
            Object obj = this.Y.get(str2);
            if (obj == null) {
                obj = "";
            }
            if (e(obj)) {
                encodedPath.appendQueryParameter(str2, f(obj).toString());
            } else if (this.S == w.a) {
                throw new IllegalArgumentException(String.format(Locale.US, "Unsupported parameter type for GET request: %s", new Object[]{obj.getClass().getSimpleName()}));
            }
        }
        return encodedPath.toString();
    }

    final String o() {
        if (this.aa != null) {
            throw new k("Can't override URL for a batch request");
        }
        String r = r();
        q();
        return g(r);
    }

    final String p() {
        if (this.aa != null) {
            return this.aa.toString();
        }
        String c;
        if (c() == w.b && this.T != null && this.T.endsWith(d)) {
            c = af.c();
        } else {
            c = af.b();
        }
        c = String.format(O, new Object[]{c, r()});
        q();
        return g(c);
    }

    private String r() {
        if (Q.matcher(this.T).matches()) {
            return this.T;
        }
        return String.format(O, new Object[]{this.ac, this.T});
    }

    private void a(JSONArray jSONArray, Map<String, a> map) throws JSONException, IOException {
        JSONObject jSONObject = new JSONObject();
        if (this.V != null) {
            jSONObject.put("name", this.V);
            jSONObject.put(t, this.X);
        }
        if (this.W != null) {
            jSONObject.put(u, this.W);
        }
        String o = o();
        jSONObject.put(w, o);
        jSONObject.put("method", this.S);
        if (this.R != null) {
            x.a(this.R.c());
        }
        Iterable arrayList = new ArrayList();
        for (String str : this.Y.keySet()) {
            Object obj = this.Y.get(str);
            if (d(obj)) {
                String format = String.format(Locale.ROOT, "%s%d", new Object[]{"file", Integer.valueOf(map.size())});
                arrayList.add(format);
                map.put(format, new a(this, obj));
            }
        }
        if (!arrayList.isEmpty()) {
            jSONObject.put(B, TextUtils.join(",", arrayList));
        }
        if (this.U != null) {
            final Iterable arrayList2 = new ArrayList();
            a(this.U, o, new e(this) {
                final /* synthetic */ GraphRequest b;

                public void a(String str, String str2) throws IOException {
                    arrayList2.add(String.format(Locale.US, "%s=%s", new Object[]{str, URLEncoder.encode(str2, "UTF-8")}));
                }
            });
            jSONObject.put(x, TextUtils.join(com.alipay.sdk.h.a.b, arrayList2));
        }
        jSONArray.put(jSONObject);
    }

    private static boolean e(u uVar) {
        for (com.facebook.u.a aVar : uVar.e()) {
            if (aVar instanceof com.facebook.u.b) {
                return true;
            }
        }
        Iterator it = uVar.iterator();
        while (it.hasNext()) {
            if (((GraphRequest) it.next()).k() instanceof f) {
                return true;
            }
        }
        return false;
    }

    private static void a(HttpURLConnection httpURLConnection, boolean z) {
        if (z) {
            httpURLConnection.setRequestProperty("Content-Type", UrlEncodedParser.CONTENT_TYPE);
            httpURLConnection.setRequestProperty("Content-Encoding", AsyncHttpClient.ENCODING_GZIP);
            return;
        }
        httpURLConnection.setRequestProperty("Content-Type", s());
    }

    private static boolean f(u uVar) {
        Iterator it = uVar.iterator();
        while (it.hasNext()) {
            GraphRequest graphRequest = (GraphRequest) it.next();
            for (String str : graphRequest.Y.keySet()) {
                if (d(graphRequest.Y.get(str))) {
                    return false;
                }
            }
        }
        return true;
    }

    static final boolean b(GraphRequest graphRequest) {
        String d = graphRequest.d();
        if (ah.a(d)) {
            return true;
        }
        if (d.startsWith("v")) {
            d = d.substring(1);
        }
        String[] split = d.split("\\.");
        boolean z = (split.length >= 2 && Integer.parseInt(split[0]) > 2) || (Integer.parseInt(split[0]) >= 2 && Integer.parseInt(split[1]) >= 4);
        return z;
    }

    static final void d(u uVar) {
        Iterator it = uVar.iterator();
        while (it.hasNext()) {
            GraphRequest graphRequest = (GraphRequest) it.next();
            if (w.a.equals(graphRequest.c()) && b(graphRequest)) {
                Bundle e = graphRequest.e();
                if (!e.containsKey(c) || ah.a(e.getString(c))) {
                    x.a(y.DEVELOPER_ERRORS, 5, "Request", "starting with Graph API v2.4, GET requests for /%s should contain an explicit \"fields\" parameter.", graphRequest.b());
                }
            }
        }
    }

    static final void a(u uVar, HttpURLConnection httpURLConnection) throws IOException, JSONException {
        Throwable th;
        x xVar = new x(y.REQUESTS, "Request");
        int size = uVar.size();
        boolean f = f(uVar);
        w wVar = size == 1 ? uVar.b(0).S : w.b;
        httpURLConnection.setRequestMethod(wVar.name());
        a(httpURLConnection, f);
        URL url = httpURLConnection.getURL();
        xVar.c("Request:\n");
        xVar.a("Id", uVar.b());
        xVar.a("URL", (Object) url);
        xVar.a(MANConfig.NETWORK_SINGLE_REQUEST_METHOD_KEY, httpURLConnection.getRequestMethod());
        xVar.a(j, httpURLConnection.getRequestProperty(j));
        xVar.a("Content-Type", httpURLConnection.getRequestProperty("Content-Type"));
        httpURLConnection.setConnectTimeout(uVar.a());
        httpURLConnection.setReadTimeout(uVar.a());
        if (wVar == w.b) {
            httpURLConnection.setDoOutput(true);
            OutputStream bufferedOutputStream;
            try {
                OutputStream acVar;
                bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                if (f) {
                    try {
                        bufferedOutputStream = new GZIPOutputStream(bufferedOutputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                        throw th;
                    }
                }
                if (e(uVar)) {
                    acVar = new ac(uVar.c());
                    a(uVar, null, size, url, acVar, f);
                    acVar = new ad(bufferedOutputStream, uVar, acVar.b(), (long) acVar.a());
                } else {
                    acVar = bufferedOutputStream;
                }
                try {
                    a(uVar, xVar, size, url, acVar, f);
                    if (acVar != null) {
                        acVar.close();
                    }
                    xVar.c();
                    return;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedOutputStream = acVar;
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedOutputStream = null;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        }
        xVar.c();
    }

    private static void a(u uVar, x xVar, int i, URL url, OutputStream outputStream, boolean z) throws IOException, JSONException {
        g gVar = new g(outputStream, xVar, z);
        String g;
        if (i == 1) {
            GraphRequest b = uVar.b(0);
            Map hashMap = new HashMap();
            for (String g2 : b.Y.keySet()) {
                Object obj = b.Y.get(g2);
                if (d(obj)) {
                    hashMap.put(g2, new a(b, obj));
                }
            }
            if (xVar != null) {
                xVar.c("  Parameters:\n");
            }
            a(b.Y, gVar, b);
            if (xVar != null) {
                xVar.c("  Attachments:\n");
            }
            a(hashMap, gVar);
            if (b.U != null) {
                a(b.U, url.getPath(), (e) gVar);
                return;
            }
            return;
        }
        g2 = g(uVar);
        if (ah.a(g2)) {
            throw new k("App ID was not specified at the request or Settings.");
        }
        gVar.a(v, g2);
        Map hashMap2 = new HashMap();
        a(gVar, (Collection) uVar, hashMap2);
        if (xVar != null) {
            xVar.c("  Attachments:\n");
        }
        a(hashMap2, gVar);
    }

    private static boolean h(String str) {
        Matcher matcher = Q.matcher(str);
        if (matcher.matches()) {
            str = matcher.group(1);
        }
        if (str.startsWith("me/") || str.startsWith("/me/")) {
            return true;
        }
        return false;
    }

    private static void a(JSONObject jSONObject, String str, e eVar) throws IOException {
        Object obj;
        if (h(str)) {
            int indexOf = str.indexOf(":");
            int indexOf2 = str.indexOf("?");
            Object obj2 = (indexOf <= 3 || (indexOf2 != -1 && indexOf >= indexOf2)) ? null : 1;
            obj = obj2;
        } else {
            obj = null;
        }
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            boolean z;
            String str2 = (String) keys.next();
            Object opt = jSONObject.opt(str2);
            if (obj == null || !str2.equalsIgnoreCase("image")) {
                z = false;
            } else {
                z = true;
            }
            a(str2, opt, eVar, z);
        }
    }

    public static GraphRequest a(ShareOpenGraphObject shareOpenGraphObject) throws k {
        String j;
        String j2 = shareOpenGraphObject.j("type");
        if (j2 == null) {
            j = shareOpenGraphObject.j("og:type");
        } else {
            j = j2;
        }
        if (j == null) {
            throw new k("Open graph object type cannot be null");
        }
        try {
            JSONObject jSONObject = (JSONObject) k.a((Object) shareOpenGraphObject, new com.facebook.share.internal.k.a() {
                public JSONObject a(SharePhoto sharePhoto) {
                    Uri c = sharePhoto.c();
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("url", c.toString());
                        return jSONObject;
                    } catch (Throwable e) {
                        throw new k("Unable to attach images", e);
                    }
                }
            });
            Bundle bundle = new Bundle();
            bundle.putString("object", jSONObject.toString());
            return new GraphRequest(AccessToken.a(), String.format(Locale.ROOT, O, new Object[]{e, "objects/" + j}), bundle, w.b);
        } catch (JSONException e) {
            throw new k(e.getMessage());
        }
    }

    private static void a(String str, Object obj, e eVar, boolean z) throws IOException {
        Class cls = obj.getClass();
        if (JSONObject.class.isAssignableFrom(cls)) {
            JSONObject jSONObject = (JSONObject) obj;
            if (z) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    Object[] objArr = new Object[]{str, (String) keys.next()};
                    a(String.format("%s[%s]", objArr), jSONObject.opt((String) keys.next()), eVar, z);
                }
            } else if (jSONObject.has("id")) {
                a(str, jSONObject.optString("id"), eVar, z);
            } else if (jSONObject.has("url")) {
                a(str, jSONObject.optString("url"), eVar, z);
            } else if (jSONObject.has(ab.ad)) {
                a(str, jSONObject.toString(), eVar, z);
            }
        } else if (JSONArray.class.isAssignableFrom(cls)) {
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                a(String.format(Locale.ROOT, "%s[%d]", new Object[]{str, Integer.valueOf(i)}), jSONArray.opt(i), eVar, z);
            }
        } else if (String.class.isAssignableFrom(cls) || Number.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls)) {
            eVar.a(str, obj.toString());
        } else if (Date.class.isAssignableFrom(cls)) {
            eVar.a(str, new SimpleDateFormat(C, Locale.US).format((Date) obj));
        }
    }

    private static void a(Bundle bundle, g gVar, GraphRequest graphRequest) throws IOException {
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (e(obj)) {
                gVar.a(str, obj, graphRequest);
            }
        }
    }

    private static void a(Map<String, a> map, g gVar) throws IOException {
        for (String str : map.keySet()) {
            a aVar = (a) map.get(str);
            if (d(aVar.b())) {
                gVar.a(str, aVar.b(), aVar.a());
            }
        }
    }

    private static void a(g gVar, Collection<GraphRequest> collection, Map<String, a> map) throws JSONException, IOException {
        JSONArray jSONArray = new JSONArray();
        for (GraphRequest a : collection) {
            a.a(jSONArray, (Map) map);
        }
        gVar.a(z, jSONArray, (Collection) collection);
    }

    private static String s() {
        return String.format("multipart/form-data; boundary=%s", new Object[]{N});
    }

    private static String t() {
        if (ae == null) {
            ae = String.format("%s.%s", new Object[]{i, q.a});
            if (!ah.a(u.a())) {
                ae = String.format(Locale.ROOT, O, new Object[]{ae, r0});
            }
        }
        return ae;
    }

    private static String g(u uVar) {
        if (!ah.a(uVar.f())) {
            return uVar.f();
        }
        Iterator it = uVar.iterator();
        while (it.hasNext()) {
            AccessToken accessToken = ((GraphRequest) it.next()).R;
            if (accessToken != null) {
                String i = accessToken.i();
                if (i != null) {
                    return i;
                }
            }
        }
        if (ah.a(P)) {
            return o.k();
        }
        return P;
    }

    private static boolean d(Object obj) {
        return (obj instanceof Bitmap) || (obj instanceof byte[]) || (obj instanceof Uri) || (obj instanceof ParcelFileDescriptor) || (obj instanceof ParcelableResourceWithMimeType);
    }

    private static boolean e(Object obj) {
        return (obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Number) || (obj instanceof Date);
    }

    private static String f(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if ((obj instanceof Boolean) || (obj instanceof Number)) {
            return obj.toString();
        }
        if (obj instanceof Date) {
            return new SimpleDateFormat(C, Locale.US).format(obj);
        }
        throw new IllegalArgumentException("Unsupported parameter type.");
    }
}
