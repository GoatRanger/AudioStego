package com.facebook.login;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.c;
import com.facebook.internal.a;
import com.facebook.internal.ab;
import com.facebook.internal.af;
import com.facebook.internal.ah;
import com.facebook.k;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;

class KatanaProxyLoginMethodHandler extends LoginMethodHandler {
    public static final Creator<KatanaProxyLoginMethodHandler> CREATOR = new Creator() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public KatanaProxyLoginMethodHandler a(Parcel parcel) {
            return new KatanaProxyLoginMethodHandler(parcel);
        }

        public KatanaProxyLoginMethodHandler[] a(int i) {
            return new KatanaProxyLoginMethodHandler[i];
        }
    };

    KatanaProxyLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    String c() {
        return "katana_proxy_auth";
    }

    boolean a(Request request) {
        String o = LoginClient.o();
        Intent a = ab.a(this.b.b(), request.d(), request.a(), o, request.f(), request.g(), request.c(), a(request.e()));
        a("e2e", o);
        return a(a, LoginClient.d());
    }

    boolean a(int i, int i2, Intent intent) {
        Result a;
        Request c = this.b.c();
        if (intent == null) {
            a = Result.a(c, "Operation canceled");
        } else if (i2 == 0) {
            a = b(c, intent);
        } else if (i2 != -1) {
            a = Result.a(c, "Unexpected resultCode from authorization.", null);
        } else {
            a = a(c, intent);
        }
        if (a != null) {
            this.b.a(a);
        } else {
            this.b.i();
        }
        return true;
    }

    private Result a(Request request, Intent intent) {
        String str = null;
        Bundle extras = intent.getExtras();
        String a = a(extras);
        String string = extras.getString(ab.an);
        String b = b(extras);
        String string2 = extras.getString("e2e");
        if (!ah.a(string2)) {
            b(string2);
        }
        if (a == null && string == null && b == null) {
            try {
                return Result.a(request, LoginMethodHandler.a(request.a(), extras, c.FACEBOOK_APPLICATION_WEB, request.d()));
            } catch (k e) {
                return Result.a(request, str, e.getMessage());
            }
        } else if (af.D.contains(a)) {
            return str;
        } else {
            if (af.E.contains(a)) {
                return Result.a(request, str);
            }
            return Result.a(request, a, b, string);
        }
    }

    private Result b(Request request, Intent intent) {
        Bundle extras = intent.getExtras();
        String a = a(extras);
        String string = extras.getString(ab.an);
        if (af.F.equals(string)) {
            return Result.a(request, a, b(extras), string);
        }
        return Result.a(request, a);
    }

    private String a(Bundle bundle) {
        String string = bundle.getString("error");
        if (string == null) {
            return bundle.getString(ab.al);
        }
        return string;
    }

    private String b(Bundle bundle) {
        String string = bundle.getString(a.X);
        if (string == null) {
            return bundle.getString(ab.am);
        }
        return string;
    }

    protected boolean a(Intent intent, int i) {
        if (intent == null) {
            return false;
        }
        try {
            this.b.a().startActivityForResult(intent, i);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }

    KatanaProxyLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
