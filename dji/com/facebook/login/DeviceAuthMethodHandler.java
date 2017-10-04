package com.facebook.login;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.AccessToken;
import com.facebook.c;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;

class DeviceAuthMethodHandler extends LoginMethodHandler {
    public static final Creator<DeviceAuthMethodHandler> CREATOR = new Creator() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public DeviceAuthMethodHandler a(Parcel parcel) {
            return new DeviceAuthMethodHandler(parcel);
        }

        public DeviceAuthMethodHandler[] a(int i) {
            return new DeviceAuthMethodHandler[i];
        }
    };
    private static ScheduledThreadPoolExecutor c;

    DeviceAuthMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    boolean a(Request request) {
        b(request);
        return true;
    }

    private void b(Request request) {
        DeviceAuthDialog deviceAuthDialog = new DeviceAuthDialog();
        deviceAuthDialog.show(this.b.b().getFragmentManager(), "login_with_facebook");
        deviceAuthDialog.a(request);
    }

    public void c_() {
        this.b.a(Result.a(this.b.c(), "User canceled log in."));
    }

    public void a(Exception exception) {
        this.b.a(Result.a(this.b.c(), null, exception.getMessage()));
    }

    public void a(String str, String str2, String str3, Collection<String> collection, Collection<String> collection2, c cVar, Date date, Date date2) {
        this.b.a(Result.a(this.b.c(), new AccessToken(str, str2, str3, collection, collection2, cVar, date, date2)));
    }

    public static synchronized ScheduledThreadPoolExecutor b() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        synchronized (DeviceAuthMethodHandler.class) {
            if (c == null) {
                c = new ScheduledThreadPoolExecutor(1);
            }
            scheduledThreadPoolExecutor = c;
        }
        return scheduledThreadPoolExecutor;
    }

    protected DeviceAuthMethodHandler(Parcel parcel) {
        super(parcel);
    }

    String c() {
        return "device_auth";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
