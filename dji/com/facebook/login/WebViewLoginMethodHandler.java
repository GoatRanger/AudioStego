package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.facebook.AccessToken;
import com.facebook.internal.af;
import com.facebook.internal.ah;
import com.facebook.internal.aj;
import com.facebook.internal.aj.c;
import com.facebook.k;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import com.facebook.m;
import com.facebook.s;
import java.util.Locale;

class WebViewLoginMethodHandler extends LoginMethodHandler {
    public static final Creator<WebViewLoginMethodHandler> CREATOR = new Creator() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public WebViewLoginMethodHandler a(Parcel parcel) {
            return new WebViewLoginMethodHandler(parcel);
        }

        public WebViewLoginMethodHandler[] a(int i) {
            return new WebViewLoginMethodHandler[i];
        }
    };
    private static final String c = "com.facebook.login.AuthorizationClient.WebViewAuthHandler.TOKEN_STORE_KEY";
    private static final String d = "TOKEN";
    private aj e;
    private String f;

    static class a extends com.facebook.internal.aj.a {
        static final String a = "fbconnect://success";
        private static final String b = "oauth";
        private String c;
        private boolean d;

        public a(Context context, String str, Bundle bundle) {
            super(context, str, b, bundle);
        }

        public a a(String str) {
            this.c = str;
            return this;
        }

        public a a(boolean z) {
            this.d = z;
            return this;
        }

        public aj a() {
            Bundle e = e();
            e.putString("redirect_uri", "fbconnect://success");
            e.putString("client_id", b());
            e.putString("e2e", this.c);
            e.putString("response_type", af.r);
            e.putString(af.l, "true");
            if (this.d) {
                e.putString(af.d, af.q);
            }
            return new aj(c(), b, e, d(), f());
        }
    }

    WebViewLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    String c() {
        return "web_view";
    }

    boolean d() {
        return true;
    }

    void a() {
        if (this.e != null) {
            this.e.cancel();
            this.e = null;
        }
    }

    boolean a(final Request request) {
        String join;
        Bundle bundle = new Bundle();
        if (!ah.a(request.a())) {
            join = TextUtils.join(",", request.a());
            bundle.putString("scope", join);
            a("scope", join);
        }
        bundle.putString(af.n, request.c().a());
        AccessToken a = AccessToken.a();
        join = a != null ? a.c() : null;
        if (join == null || !join.equals(b())) {
            ah.b(this.b.b());
            a("access_token", "0");
        } else {
            bundle.putString("access_token", join);
            a("access_token", "1");
        }
        c anonymousClass1 = new c(this) {
            final /* synthetic */ WebViewLoginMethodHandler b;

            public void a(Bundle bundle, k kVar) {
                this.b.a(request, bundle, kVar);
            }
        };
        this.f = LoginClient.o();
        a("e2e", this.f);
        bundle.putString("state", a(request.e()));
        Context b = this.b.b();
        this.e = new a(b, request.d(), bundle).a(this.f).a(request.f()).a(anonymousClass1).a();
        com.facebook.internal.k kVar = new com.facebook.internal.k();
        kVar.setRetainInstance(true);
        kVar.a(this.e);
        kVar.show(b.getFragmentManager(), com.facebook.internal.k.a);
        return true;
    }

    void a(Request request, Bundle bundle, k kVar) {
        Result a;
        if (bundle != null) {
            if (bundle.containsKey("e2e")) {
                this.f = bundle.getString("e2e");
            }
            try {
                AccessToken a2 = LoginMethodHandler.a(request.a(), bundle, com.facebook.c.WEB_VIEW, request.d());
                a = Result.a(this.b.c(), a2);
                CookieSyncManager.createInstance(this.b.b()).sync();
                c(a2.c());
            } catch (k e) {
                a = Result.a(this.b.c(), null, e.getMessage());
            }
        } else if (kVar instanceof m) {
            a = Result.a(this.b.c(), "User canceled log in.");
        } else {
            String format;
            this.f = null;
            String message = kVar.getMessage();
            if (kVar instanceof s) {
                format = String.format(Locale.ROOT, "%d", new Object[]{Integer.valueOf(((s) kVar).a().c())});
                message = r0.toString();
            } else {
                format = null;
            }
            a = Result.a(this.b.c(), null, message, format);
        }
        if (!ah.a(this.f)) {
            b(this.f);
        }
        this.b.a(a);
    }

    private void c(String str) {
        this.b.b().getSharedPreferences(c, 0).edit().putString(d, str).apply();
    }

    private String b() {
        return this.b.b().getSharedPreferences(c, 0).getString(d, "");
    }

    WebViewLoginMethodHandler(Parcel parcel) {
        super(parcel);
        this.f = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f);
    }
}
