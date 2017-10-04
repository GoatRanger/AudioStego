package com.facebook.login;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.AccessToken;
import com.facebook.FacebookActivity;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.b;
import com.facebook.R;
import com.facebook.c;
import com.facebook.internal.ah;
import com.facebook.internal.ah.e;
import com.facebook.k;
import com.facebook.login.LoginClient.Request;
import com.facebook.n;
import com.facebook.o;
import com.facebook.t;
import com.facebook.v;
import com.facebook.w;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

public class DeviceAuthDialog extends DialogFragment {
    private static final String a = "oauth/device";
    private static final String b = "request_state";
    private ProgressBar c;
    private TextView d;
    private DeviceAuthMethodHandler e;
    private AtomicBoolean f = new AtomicBoolean();
    private volatile t g;
    private volatile ScheduledFuture h;
    private volatile RequestState i;
    private Dialog j;
    private boolean k = false;

    private static class RequestState implements Parcelable {
        public static final Creator<RequestState> CREATOR = new Creator<RequestState>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public RequestState a(Parcel parcel) {
                return new RequestState(parcel);
            }

            public RequestState[] a(int i) {
                return new RequestState[i];
            }
        };
        private String a;
        private String b;
        private long c;
        private long d;

        RequestState() {
        }

        public String a() {
            return this.a;
        }

        public void a(String str) {
            this.a = str;
        }

        public String b() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public long c() {
            return this.c;
        }

        public void a(long j) {
            this.c = j;
        }

        public void b(long j) {
            this.d = j;
        }

        protected RequestState(Parcel parcel) {
            this.a = parcel.readString();
            this.b = parcel.readString();
            this.c = parcel.readLong();
            this.d = parcel.readLong();
        }

        public boolean d() {
            if (this.d != 0 && (new Date().getTime() - this.d) - (this.c * 1000) < 0) {
                return true;
            }
            return false;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
            parcel.writeString(this.b);
            parcel.writeLong(this.c);
            parcel.writeLong(this.d);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.e = (DeviceAuthMethodHandler) ((d) ((FacebookActivity) getActivity()).a()).a().g();
        if (bundle != null) {
            RequestState requestState = (RequestState) bundle.getParcelable(b);
            if (requestState != null) {
                a(requestState);
            }
        }
        return onCreateView;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.j = new Dialog(getActivity(), R.style.com_facebook_auth_dialog);
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.com_facebook_device_auth_dialog_fragment, null);
        this.c = (ProgressBar) inflate.findViewById(R.id.progress_bar);
        this.d = (TextView) inflate.findViewById(R.id.confirmation_code);
        ((Button) inflate.findViewById(R.id.cancel_button)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DeviceAuthDialog a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d();
            }
        });
        ((TextView) inflate.findViewById(R.id.com_facebook_device_auth_instructions)).setText(Html.fromHtml(getString(R.string.com_facebook_device_auth_instructions)));
        this.j.setContentView(inflate);
        return this.j;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (!this.k) {
            d();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.i != null) {
            bundle.putParcelable(b, this.i);
        }
    }

    public void onDestroy() {
        this.k = true;
        this.f.set(true);
        super.onDestroy();
        if (this.g != null) {
            this.g.cancel(true);
        }
        if (this.h != null) {
            this.h.cancel(true);
        }
    }

    public void a(Request request) {
        Bundle bundle = new Bundle();
        bundle.putString("type", "device_code");
        bundle.putString("client_id", o.k());
        bundle.putString("scope", TextUtils.join(",", request.a()));
        new GraphRequest(null, a, bundle, w.b, new b(this) {
            final /* synthetic */ DeviceAuthDialog a;

            {
                this.a = r1;
            }

            public void onCompleted(v vVar) {
                if (vVar.a() != null) {
                    this.a.a(vVar.a().n());
                    return;
                }
                JSONObject b = vVar.b();
                RequestState requestState = new RequestState();
                try {
                    requestState.a(b.getString("user_code"));
                    requestState.b(b.getString("code"));
                    requestState.a(b.getLong("interval"));
                    this.a.a(requestState);
                } catch (Throwable e) {
                    this.a.a(new k(e));
                }
            }
        }).n();
    }

    private void a(RequestState requestState) {
        this.i = requestState;
        this.d.setText(requestState.a());
        this.d.setVisibility(0);
        this.c.setVisibility(8);
        if (requestState.d()) {
            b();
        } else {
            a();
        }
    }

    private void a() {
        this.i.b(new Date().getTime());
        this.g = c().n();
    }

    private void b() {
        this.h = DeviceAuthMethodHandler.b().schedule(new Runnable(this) {
            final /* synthetic */ DeviceAuthDialog a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a();
            }
        }, this.i.c(), TimeUnit.SECONDS);
    }

    private GraphRequest c() {
        Bundle bundle = new Bundle();
        bundle.putString("type", "device_token");
        bundle.putString("client_id", o.k());
        bundle.putString("code", this.i.b());
        return new GraphRequest(null, a, bundle, w.b, new b(this) {
            final /* synthetic */ DeviceAuthDialog a;

            {
                this.a = r1;
            }

            public void onCompleted(v vVar) {
                if (!this.a.f.get()) {
                    n a = vVar.a();
                    if (a != null) {
                        String f = a.f();
                        if (f.equals("authorization_pending") || f.equals("slow_down")) {
                            this.a.b();
                            return;
                        } else if (f.equals("authorization_declined") || f.equals("code_expired")) {
                            this.a.d();
                            return;
                        } else {
                            this.a.a(vVar.a().n());
                            return;
                        }
                    }
                    try {
                        this.a.a(vVar.b().getString("access_token"));
                    } catch (Throwable e) {
                        this.a.a(new k(e));
                    }
                }
            }
        });
    }

    private void a(final String str) {
        Bundle bundle = new Bundle();
        bundle.putString(GraphRequest.c, "id,permissions");
        new GraphRequest(new AccessToken(str, o.k(), "0", null, null, null, null, null), "me", bundle, w.a, new b(this) {
            final /* synthetic */ DeviceAuthDialog b;

            public void onCompleted(v vVar) {
                if (!this.b.f.get()) {
                    if (vVar.a() != null) {
                        this.b.a(vVar.a().n());
                        return;
                    }
                    try {
                        JSONObject b = vVar.b();
                        String string = b.getString("id");
                        e b2 = ah.b(b);
                        this.b.e.a(str, o.k(), string, b2.a(), b2.b(), c.DEVICE_AUTH, null, null);
                        this.b.j.dismiss();
                    } catch (Throwable e) {
                        this.b.a(new k(e));
                    }
                }
            }
        }).n();
    }

    private void a(k kVar) {
        if (this.f.compareAndSet(false, true)) {
            this.e.a((Exception) kVar);
            this.j.dismiss();
        }
    }

    private void d() {
        if (this.f.compareAndSet(false, true)) {
            if (this.e != null) {
                this.e.c_();
            }
            this.j.dismiss();
        }
    }
}
