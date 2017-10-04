package com.facebook.login;

import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.R;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import com.facebook.login.LoginClient.b;

public class d extends Fragment {
    static final String a = "com.facebook.LoginFragment:Result";
    static final String b = "request";
    private static final String c = "LoginFragment";
    private static final String d = "Cannot call LoginFragment with a null calling package. This can occur if the launchMode of the caller is singleInstance.";
    private static final String e = "loginClient";
    private String f;
    private LoginClient g;
    private Request h;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.g = (LoginClient) bundle.getParcelable(e);
            this.g.a((Fragment) this);
        } else {
            this.g = new LoginClient((Fragment) this);
        }
        this.g.a(new b(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(Result result) {
                this.a.a(result);
            }
        });
        Activity activity = getActivity();
        if (activity != null) {
            a(activity);
            if (activity.getIntent() != null) {
                Intent intent = activity.getIntent();
                intent.setExtrasClassLoader(Request.class.getClassLoader());
                this.h = (Request) intent.getParcelableExtra("request");
            }
        }
    }

    public void onDestroy() {
        this.g.f();
        super.onDestroy();
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        final View inflate = layoutInflater.inflate(R.layout.com_facebook_login_fragment, viewGroup, false);
        this.g.a(new a(this) {
            final /* synthetic */ d b;

            public void a() {
                inflate.findViewById(R.id.com_facebook_login_activity_progress_bar).setVisibility(0);
            }

            public void b() {
                inflate.findViewById(R.id.com_facebook_login_activity_progress_bar).setVisibility(8);
            }
        });
        return inflate;
    }

    private void a(Result result) {
        this.h = null;
        int i = result.a == a.CANCEL ? 0 : -1;
        Bundle bundle = new Bundle();
        bundle.putParcelable(a, result);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        if (isAdded()) {
            getActivity().setResult(i, intent);
            getActivity().finish();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.f == null) {
            Log.e(c, d);
            getActivity().finish();
            return;
        }
        this.g.a(this.h);
    }

    public void onPause() {
        super.onPause();
        getActivity().findViewById(R.id.com_facebook_login_activity_progress_bar).setVisibility(8);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.g.a(i, i2, intent);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(e, this.g);
    }

    private void a(Activity activity) {
        ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity != null) {
            this.f = callingActivity.getPackageName();
        }
    }

    LoginClient a() {
        return this.g;
    }
}
