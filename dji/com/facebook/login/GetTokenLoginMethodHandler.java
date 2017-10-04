package com.facebook.login;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.facebook.c;
import com.facebook.internal.ab;
import com.facebook.internal.ad.a;
import com.facebook.internal.ah;
import com.facebook.k;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class GetTokenLoginMethodHandler extends LoginMethodHandler {
    public static final Creator<GetTokenLoginMethodHandler> CREATOR = new Creator() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public GetTokenLoginMethodHandler a(Parcel parcel) {
            return new GetTokenLoginMethodHandler(parcel);
        }

        public GetTokenLoginMethodHandler[] a(int i) {
            return new GetTokenLoginMethodHandler[i];
        }
    };
    private b c;

    GetTokenLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    String c() {
        return "get_token";
    }

    void a() {
        if (this.c != null) {
            this.c.c();
            this.c.a(null);
            this.c = null;
        }
    }

    boolean a(final Request request) {
        this.c = new b(this.b.b(), request.d());
        if (!this.c.b()) {
            return false;
        }
        this.b.m();
        this.c.a(new a(this) {
            final /* synthetic */ GetTokenLoginMethodHandler b;

            public void a(Bundle bundle) {
                this.b.a(request, bundle);
            }
        });
        return true;
    }

    void a(Request request, Bundle bundle) {
        if (this.c != null) {
            this.c.a(null);
        }
        this.c = null;
        this.b.n();
        if (bundle != null) {
            ArrayList stringArrayList = bundle.getStringArrayList(ab.U);
            Object<String> a = request.a();
            if (stringArrayList == null || !(a == null || stringArrayList.containsAll(a))) {
                Set hashSet = new HashSet();
                for (String str : a) {
                    if (!stringArrayList.contains(str)) {
                        hashSet.add(str);
                    }
                }
                if (!hashSet.isEmpty()) {
                    a("new_permissions", TextUtils.join(",", hashSet));
                }
                request.a(hashSet);
            } else {
                c(request, bundle);
                return;
            }
        }
        this.b.i();
    }

    void b(Request request, Bundle bundle) {
        this.b.a(Result.a(this.b.c(), LoginMethodHandler.a(bundle, c.FACEBOOK_APPLICATION_SERVICE, request.d())));
    }

    void c(final Request request, final Bundle bundle) {
        String string = bundle.getString(ab.X);
        if (string == null || string.isEmpty()) {
            this.b.m();
            ah.a(bundle.getString(ab.Y), new ah.c(this) {
                final /* synthetic */ GetTokenLoginMethodHandler c;

                public void onSuccess(JSONObject jSONObject) {
                    try {
                        bundle.putString(ab.X, jSONObject.getString("id"));
                        this.c.b(request, bundle);
                    } catch (JSONException e) {
                        this.c.b.b(Result.a(this.c.b.c(), "Caught exception", e.getMessage()));
                    }
                }

                public void onFailure(k kVar) {
                    this.c.b.b(Result.a(this.c.b.c(), "Caught exception", kVar.getMessage()));
                }
            });
            return;
        }
        b(request, bundle);
    }

    GetTokenLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
