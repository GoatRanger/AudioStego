package com.facebook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.alipay.sdk.j.i;
import com.facebook.internal.ah;
import com.facebook.internal.ah.c;
import com.facebook.internal.ai;
import com.here.odnp.posclient.pos.IPositioningSession;
import dji.pilot.usercenter.protocol.d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AccessToken implements Parcelable {
    public static final Creator<AccessToken> CREATOR = new Creator() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public AccessToken a(Parcel parcel) {
            return new AccessToken(parcel);
        }

        public AccessToken[] a(int i) {
            return new AccessToken[i];
        }
    };
    public static final String a = "access_token";
    public static final String b = "expires_in";
    public static final String c = "user_id";
    private static final Date d = new Date(IPositioningSession.NotSet);
    private static final Date e = d;
    private static final Date f = new Date();
    private static final c g = c.FACEBOOK_APPLICATION_WEB;
    private static final int h = 1;
    private static final String i = "version";
    private static final String j = "expires_at";
    private static final String k = "permissions";
    private static final String l = "declined_permissions";
    private static final String m = "token";
    private static final String n = "source";
    private static final String o = "last_refresh";
    private static final String p = "application_id";
    private final Date q;
    private final Set<String> r;
    private final Set<String> s;
    private final String t;
    private final c u;
    private final Date v;
    private final String w;
    private final String x;

    public interface a {
        void a(AccessToken accessToken);

        void a(k kVar);
    }

    public interface b {
        void a(AccessToken accessToken);

        void a(k kVar);
    }

    public AccessToken(String str, String str2, String str3, @Nullable Collection<String> collection, @Nullable Collection<String> collection2, @Nullable c cVar, @Nullable Date date, @Nullable Date date2) {
        ai.a(str, "accessToken");
        ai.a(str2, "applicationId");
        ai.a(str3, "userId");
        if (date == null) {
            date = e;
        }
        this.q = date;
        this.r = Collections.unmodifiableSet(collection != null ? new HashSet(collection) : new HashSet());
        this.s = Collections.unmodifiableSet(collection2 != null ? new HashSet(collection2) : new HashSet());
        this.t = str;
        if (cVar == null) {
            cVar = g;
        }
        this.u = cVar;
        if (date2 == null) {
            date2 = f;
        }
        this.v = date2;
        this.w = str2;
        this.x = str3;
    }

    public static AccessToken a() {
        return b.a().b();
    }

    public static void a(AccessToken accessToken) {
        b.a().a(accessToken);
    }

    public static void b() {
        b.a().a(null);
    }

    public static void a(b bVar) {
        b.a().a(bVar);
    }

    public String c() {
        return this.t;
    }

    public Date d() {
        return this.q;
    }

    public Set<String> e() {
        return this.r;
    }

    public Set<String> f() {
        return this.s;
    }

    public c g() {
        return this.u;
    }

    public Date h() {
        return this.v;
    }

    public String i() {
        return this.w;
    }

    public String j() {
        return this.x;
    }

    public static void a(Intent intent, final String str, final a aVar) {
        ai.a((Object) intent, "intent");
        if (intent.getExtras() == null) {
            aVar.a(new k("No extras found on intent"));
            return;
        }
        final Bundle bundle = new Bundle(intent.getExtras());
        String string = bundle.getString("access_token");
        if (string == null || string.isEmpty()) {
            aVar.a(new k("No access token found on intent"));
            return;
        }
        String string2 = bundle.getString("user_id");
        if (string2 == null || string2.isEmpty()) {
            ah.a(string, new c() {
                public void onSuccess(JSONObject jSONObject) {
                    try {
                        bundle.putString("user_id", jSONObject.getString("id"));
                        aVar.a(AccessToken.b(null, bundle, c.FACEBOOK_APPLICATION_WEB, new Date(), str));
                    } catch (JSONException e) {
                        aVar.a(new k("Unable to generate access token due to missing user id"));
                    }
                }

                public void onFailure(k kVar) {
                    aVar.a(kVar);
                }
            });
        } else {
            aVar.a(b(null, bundle, c.FACEBOOK_APPLICATION_WEB, new Date(), str));
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{AccessToken");
        stringBuilder.append(" token:").append(m());
        a(stringBuilder);
        stringBuilder.append(i.d);
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        if (r4 != r5) goto L_0x0005;
    L_0x0004:
        return r0;
    L_0x0005:
        r2 = r5 instanceof com.facebook.AccessToken;
        if (r2 != 0) goto L_0x000b;
    L_0x0009:
        r0 = r1;
        goto L_0x0004;
    L_0x000b:
        r5 = (com.facebook.AccessToken) r5;
        r2 = r4.q;
        r3 = r5.q;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0017:
        r2 = r4.r;
        r3 = r5.r;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0021:
        r2 = r4.s;
        r3 = r5.s;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x002b:
        r2 = r4.t;
        r3 = r5.t;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0035:
        r2 = r4.u;
        r3 = r5.u;
        if (r2 != r3) goto L_0x0057;
    L_0x003b:
        r2 = r4.v;
        r3 = r5.v;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0045:
        r2 = r4.w;
        if (r2 != 0) goto L_0x0059;
    L_0x0049:
        r2 = r5.w;
        if (r2 != 0) goto L_0x0057;
    L_0x004d:
        r2 = r4.x;
        r3 = r5.x;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0004;
    L_0x0057:
        r0 = r1;
        goto L_0x0004;
    L_0x0059:
        r2 = r4.w;
        r3 = r5.w;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0063:
        goto L_0x004d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.AccessToken.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return (((this.w == null ? 0 : this.w.hashCode()) + ((((((((((((this.q.hashCode() + 527) * 31) + this.r.hashCode()) * 31) + this.s.hashCode()) * 31) + this.t.hashCode()) * 31) + this.u.hashCode()) * 31) + this.v.hashCode()) * 31)) * 31) + this.x.hashCode();
    }

    @SuppressLint({"FieldGetter"})
    static AccessToken a(AccessToken accessToken, Bundle bundle) {
        if (accessToken.u == c.FACEBOOK_APPLICATION_WEB || accessToken.u == c.FACEBOOK_APPLICATION_NATIVE || accessToken.u == c.FACEBOOK_APPLICATION_SERVICE) {
            Date a = ah.a(bundle, "expires_in", new Date(0));
            String string = bundle.getString("access_token");
            if (ah.a(string)) {
                return null;
            }
            return new AccessToken(string, accessToken.w, accessToken.j(), accessToken.e(), accessToken.f(), accessToken.u, a, new Date());
        }
        throw new k("Invalid token source: " + accessToken.u);
    }

    static AccessToken a(Bundle bundle) {
        Collection a = a(bundle, x.e);
        Collection a2 = a(bundle, x.f);
        String j = x.j(bundle);
        if (ah.a(j)) {
            j = o.k();
        }
        String c = x.c(bundle);
        try {
            return new AccessToken(c, j, ah.f(c).getString("id"), a, a2, x.g(bundle), x.c(bundle, x.b), x.c(bundle, x.c));
        } catch (JSONException e) {
            return null;
        }
    }

    static List<String> a(Bundle bundle, String str) {
        Collection stringArrayList = bundle.getStringArrayList(str);
        if (stringArrayList == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList(stringArrayList));
    }

    public boolean k() {
        return new Date().after(this.q);
    }

    JSONObject l() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("version", 1);
        jSONObject.put("token", this.t);
        jSONObject.put(j, this.q.getTime());
        jSONObject.put("permissions", new JSONArray(this.r));
        jSONObject.put(l, new JSONArray(this.s));
        jSONObject.put(o, this.v.getTime());
        jSONObject.put("source", this.u.name());
        jSONObject.put(p, this.w);
        jSONObject.put("user_id", this.x);
        return jSONObject;
    }

    static AccessToken a(JSONObject jSONObject) throws JSONException {
        if (jSONObject.getInt("version") > 1) {
            throw new k("Unknown AccessToken serialization format.");
        }
        String string = jSONObject.getString("token");
        Date date = new Date(jSONObject.getLong(j));
        JSONArray jSONArray = jSONObject.getJSONArray("permissions");
        JSONArray jSONArray2 = jSONObject.getJSONArray(l);
        Date date2 = new Date(jSONObject.getLong(o));
        return new AccessToken(string, jSONObject.getString(p), jSONObject.getString("user_id"), ah.a(jSONArray), ah.a(jSONArray2), c.valueOf(jSONObject.getString("source")), date, date2);
    }

    private static AccessToken b(List<String> list, Bundle bundle, c cVar, Date date, String str) {
        String string = bundle.getString("access_token");
        Date a = ah.a(bundle, "expires_in", date);
        String string2 = bundle.getString("user_id");
        if (ah.a(string) || a == null) {
            return null;
        }
        return new AccessToken(string, str, string2, list, null, cVar, a, new Date());
    }

    private String m() {
        if (this.t == null) {
            return "null";
        }
        if (o.c(y.INCLUDE_ACCESS_TOKENS)) {
            return this.t;
        }
        return "ACCESS_TOKEN_REMOVED";
    }

    private void a(StringBuilder stringBuilder) {
        stringBuilder.append(" permissions:");
        if (this.r == null) {
            stringBuilder.append("null");
            return;
        }
        stringBuilder.append(d.G);
        stringBuilder.append(TextUtils.join(", ", this.r));
        stringBuilder.append(d.H);
    }

    AccessToken(Parcel parcel) {
        this.q = new Date(parcel.readLong());
        Collection arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        this.r = Collections.unmodifiableSet(new HashSet(arrayList));
        arrayList.clear();
        parcel.readStringList(arrayList);
        this.s = Collections.unmodifiableSet(new HashSet(arrayList));
        this.t = parcel.readString();
        this.u = c.valueOf(parcel.readString());
        this.v = new Date(parcel.readLong());
        this.w = parcel.readString();
        this.x = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.q.getTime());
        parcel.writeStringList(new ArrayList(this.r));
        parcel.writeStringList(new ArrayList(this.s));
        parcel.writeString(this.t);
        parcel.writeString(this.u.name());
        parcel.writeLong(this.v.getTime());
        parcel.writeString(this.w);
        parcel.writeString(this.x);
    }
}
