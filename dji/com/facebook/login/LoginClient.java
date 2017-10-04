package com.facebook.login;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.facebook.AccessToken;
import com.facebook.R;
import com.facebook.internal.ab;
import com.facebook.internal.ah;
import com.facebook.internal.ai;
import com.facebook.k;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class LoginClient implements Parcelable {
    public static final Creator<LoginClient> CREATOR = new Creator() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public LoginClient a(Parcel parcel) {
            return new LoginClient(parcel);
        }

        public LoginClient[] a(int i) {
            return new LoginClient[i];
        }
    };
    LoginMethodHandler[] a;
    int b = -1;
    Fragment c;
    b d;
    a e;
    boolean f;
    Request g;
    Map<String, String> h;
    private e i;

    public static class Request implements Parcelable {
        public static final Creator<Request> CREATOR = new Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public Request a(Parcel parcel) {
                return new Request(parcel);
            }

            public Request[] a(int i) {
                return new Request[i];
            }
        };
        private final c a;
        private Set<String> b;
        private final a c;
        private final String d;
        private final String e;
        private boolean f;

        Request(c cVar, Set<String> set, a aVar, String str, String str2) {
            this.f = false;
            this.a = cVar;
            if (set == null) {
                set = new HashSet();
            }
            this.b = set;
            this.c = aVar;
            this.d = str;
            this.e = str2;
        }

        Set<String> a() {
            return this.b;
        }

        void a(Set<String> set) {
            ai.a((Object) set, ab.ac);
            this.b = set;
        }

        c b() {
            return this.a;
        }

        a c() {
            return this.c;
        }

        String d() {
            return this.d;
        }

        String e() {
            return this.e;
        }

        boolean f() {
            return this.f;
        }

        void a(boolean z) {
            this.f = z;
        }

        boolean g() {
            for (String a : this.b) {
                if (f.a(a)) {
                    return true;
                }
            }
            return false;
        }

        private Request(Parcel parcel) {
            boolean z;
            a aVar = null;
            this.f = false;
            String readString = parcel.readString();
            this.a = readString != null ? c.valueOf(readString) : null;
            Collection arrayList = new ArrayList();
            parcel.readStringList(arrayList);
            this.b = new HashSet(arrayList);
            readString = parcel.readString();
            if (readString != null) {
                aVar = a.valueOf(readString);
            }
            this.c = aVar;
            this.d = parcel.readString();
            this.e = parcel.readString();
            if (parcel.readByte() != (byte) 0) {
                z = true;
            } else {
                z = false;
            }
            this.f = z;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            String str = null;
            parcel.writeString(this.a != null ? this.a.name() : null);
            parcel.writeStringList(new ArrayList(this.b));
            if (this.c != null) {
                str = this.c.name();
            }
            parcel.writeString(str);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
            parcel.writeByte((byte) (this.f ? 1 : 0));
        }
    }

    public static class Result implements Parcelable {
        public static final Creator<Result> CREATOR = new Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public Result a(Parcel parcel) {
                return new Result(parcel);
            }

            public Result[] a(int i) {
                return new Result[i];
            }
        };
        final a a;
        final AccessToken b;
        final String c;
        final String d;
        final Request e;
        public Map<String, String> f;

        enum a {
            SUCCESS("success"),
            CANCEL("cancel"),
            ERROR("error");
            
            private final String d;

            private a(String str) {
                this.d = str;
            }

            String a() {
                return this.d;
            }
        }

        Result(Request request, a aVar, AccessToken accessToken, String str, String str2) {
            ai.a((Object) aVar, "code");
            this.e = request;
            this.b = accessToken;
            this.c = str;
            this.a = aVar;
            this.d = str2;
        }

        static Result a(Request request, AccessToken accessToken) {
            return new Result(request, a.SUCCESS, accessToken, null, null);
        }

        static Result a(Request request, String str) {
            return new Result(request, a.CANCEL, null, str, null);
        }

        static Result a(Request request, String str, String str2) {
            return a(request, str, str2, null);
        }

        static Result a(Request request, String str, String str2, String str3) {
            return new Result(request, a.ERROR, null, TextUtils.join(": ", ah.d(str, str2)), str3);
        }

        private Result(Parcel parcel) {
            this.a = a.valueOf(parcel.readString());
            this.b = (AccessToken) parcel.readParcelable(AccessToken.class.getClassLoader());
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.e = (Request) parcel.readParcelable(Request.class.getClassLoader());
            this.f = ah.a(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a.name());
            parcel.writeParcelable(this.b, i);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
            parcel.writeParcelable(this.e, i);
            ah.a(parcel, this.f);
        }
    }

    interface a {
        void a();

        void b();
    }

    public interface b {
        void a(Result result);
    }

    public LoginClient(Fragment fragment) {
        this.c = fragment;
    }

    public Fragment a() {
        return this.c;
    }

    void a(Fragment fragment) {
        if (this.c != null) {
            throw new k("Can't set fragment once it is already set.");
        }
        this.c = fragment;
    }

    Activity b() {
        return this.c.getActivity();
    }

    public Request c() {
        return this.g;
    }

    public static int d() {
        return com.facebook.internal.f.b.Login.a();
    }

    void a(Request request) {
        if (!e()) {
            b(request);
        }
    }

    void b(Request request) {
        if (request != null) {
            if (this.g != null) {
                throw new k("Attempted to authorize while a request is pending.");
            } else if (AccessToken.a() == null || h()) {
                this.g = request;
                this.a = c(request);
                i();
            }
        }
    }

    boolean e() {
        return this.g != null && this.b >= 0;
    }

    void f() {
        if (this.b >= 0) {
            g().a();
        }
    }

    LoginMethodHandler g() {
        if (this.b >= 0) {
            return this.a[this.b];
        }
        return null;
    }

    public boolean a(int i, int i2, Intent intent) {
        if (this.g != null) {
            return g().a(i, i2, intent);
        }
        return false;
    }

    private LoginMethodHandler[] c(Request request) {
        ArrayList arrayList = new ArrayList();
        c b = request.b();
        if (b.a()) {
            arrayList.add(new GetTokenLoginMethodHandler(this));
            arrayList.add(new KatanaProxyLoginMethodHandler(this));
        }
        if (b.b()) {
            arrayList.add(new WebViewLoginMethodHandler(this));
        }
        if (b.c()) {
            arrayList.add(new DeviceAuthMethodHandler(this));
        }
        LoginMethodHandler[] loginMethodHandlerArr = new LoginMethodHandler[arrayList.size()];
        arrayList.toArray(loginMethodHandlerArr);
        return loginMethodHandlerArr;
    }

    boolean h() {
        if (this.f) {
            return true;
        }
        if (a("android.permission.INTERNET") != 0) {
            Activity b = b();
            b(Result.a(this.g, b.getString(R.string.com_facebook_internet_permission_error_title), b.getString(R.string.com_facebook_internet_permission_error_message)));
            return false;
        }
        this.f = true;
        return true;
    }

    void i() {
        if (this.b >= 0) {
            a(g().c(), "skipped", null, null, g().a);
        }
        while (this.a != null && this.b < this.a.length - 1) {
            this.b++;
            if (j()) {
                return;
            }
        }
        if (this.g != null) {
            p();
        }
    }

    private void p() {
        b(Result.a(this.g, "Login attempt failed.", null));
    }

    private void a(String str, String str2, boolean z) {
        if (this.h == null) {
            this.h = new HashMap();
        }
        if (this.h.containsKey(str) && z) {
            str2 = ((String) this.h.get(str)) + "," + str2;
        }
        this.h.put(str, str2);
    }

    boolean j() {
        boolean z = false;
        LoginMethodHandler g = g();
        if (!g.d() || h()) {
            z = g.a(this.g);
            if (z) {
                q().a(this.g.e(), g.c());
            } else {
                a("not_tried", g.c(), true);
            }
        } else {
            a("no_internet_permission", "1", false);
        }
        return z;
    }

    void a(Result result) {
        if (result.b == null || AccessToken.a() == null) {
            b(result);
        } else {
            c(result);
        }
    }

    void b(Result result) {
        LoginMethodHandler g = g();
        if (g != null) {
            a(g.c(), result, g.a);
        }
        if (this.h != null) {
            result.f = this.h;
        }
        this.a = null;
        this.b = -1;
        this.g = null;
        this.h = null;
        d(result);
    }

    b k() {
        return this.d;
    }

    void a(b bVar) {
        this.d = bVar;
    }

    a l() {
        return this.e;
    }

    void a(a aVar) {
        this.e = aVar;
    }

    int a(String str) {
        return b().checkCallingOrSelfPermission(str);
    }

    void c(Result result) {
        if (result.b == null) {
            throw new k("Can't validate without a token");
        }
        Result a;
        AccessToken a2 = AccessToken.a();
        AccessToken accessToken = result.b;
        if (!(a2 == null || accessToken == null)) {
            try {
                if (a2.j().equals(accessToken.j())) {
                    a = Result.a(this.g, result.b);
                    b(a);
                }
            } catch (Exception e) {
                b(Result.a(this.g, "Caught exception", e.getMessage()));
                return;
            }
        }
        a = Result.a(this.g, "User logged in as different Facebook user.", null);
        b(a);
    }

    private static AccessToken a(AccessToken accessToken, Collection<String> collection, Collection<String> collection2) {
        return new AccessToken(accessToken.c(), accessToken.i(), accessToken.j(), collection, collection2, accessToken.g(), accessToken.d(), accessToken.h());
    }

    private e q() {
        if (this.i == null || !this.i.a().equals(this.g.d())) {
            this.i = new e(b(), this.g.d());
        }
        return this.i;
    }

    private void d(Result result) {
        if (this.d != null) {
            this.d.a(result);
        }
    }

    void m() {
        if (this.e != null) {
            this.e.a();
        }
    }

    void n() {
        if (this.e != null) {
            this.e.b();
        }
    }

    private void a(String str, Result result, Map<String, String> map) {
        a(str, result.a.a(), result.c, result.d, map);
    }

    private void a(String str, String str2, String str3, String str4, Map<String, String> map) {
        if (this.g == null) {
            q().a("fb_mobile_login_method_complete", "Unexpected call to logCompleteLogin with null pendingAuthorizationRequest.", str);
        } else {
            q().a(this.g.e(), str, str2, str3, str4, map);
        }
    }

    static String o() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("init", System.currentTimeMillis());
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }

    public LoginClient(Parcel parcel) {
        Parcelable[] readParcelableArray = parcel.readParcelableArray(LoginMethodHandler.class.getClassLoader());
        this.a = new LoginMethodHandler[readParcelableArray.length];
        for (int i = 0; i < readParcelableArray.length; i++) {
            this.a[i] = (LoginMethodHandler) readParcelableArray[i];
            this.a[i].a(this);
        }
        this.b = parcel.readInt();
        this.g = (Request) parcel.readParcelable(Request.class.getClassLoader());
        this.h = ah.a(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelableArray(this.a, i);
        parcel.writeInt(this.b);
        parcel.writeParcelable(this.g, i);
        ah.a(parcel, this.h);
    }
}
