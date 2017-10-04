package com.facebook.internal;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.facebook.internal.aj.a;
import com.facebook.internal.aj.c;
import com.facebook.o;

public class k extends DialogFragment {
    public static final String a = "FacebookDialogFragment";
    private Dialog b;

    public void a(Dialog dialog) {
        this.b = dialog;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.b == null) {
            Dialog mVar;
            Context activity = getActivity();
            Bundle d = ab.d(activity.getIntent());
            String string;
            if (d.getBoolean(ab.aA, false)) {
                string = d.getString("url");
                if (ah.a(string)) {
                    ah.c(a, "Cannot start a fallback WebDialog with an empty/missing 'url'");
                    activity.finish();
                    return;
                }
                mVar = new m(activity, string, String.format("fb%s://bridge/", new Object[]{o.k()}));
                mVar.a(new c(this) {
                    final /* synthetic */ k a;

                    {
                        this.a = r1;
                    }

                    public void a(Bundle bundle, com.facebook.k kVar) {
                        this.a.a(bundle);
                    }
                });
            } else {
                string = d.getString("action");
                d = d.getBundle("params");
                if (ah.a(string)) {
                    ah.c(a, "Cannot start a WebDialog with an empty/missing 'actionName'");
                    activity.finish();
                    return;
                }
                mVar = new a(activity, string, d).a(new c(this) {
                    final /* synthetic */ k a;

                    {
                        this.a = r1;
                    }

                    public void a(Bundle bundle, com.facebook.k kVar) {
                        this.a.a(bundle, kVar);
                    }
                }).a();
            }
            this.b = mVar;
        }
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        if (this.b == null) {
            a(null, null);
            setShowsDialog(false);
        }
        return this.b;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.b instanceof aj) {
            ((aj) this.b).e();
        }
    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    private void a(Bundle bundle, com.facebook.k kVar) {
        Activity activity = getActivity();
        activity.setResult(kVar == null ? -1 : 0, ab.a(activity.getIntent(), bundle, kVar));
        activity.finish();
    }

    private void a(Bundle bundle) {
        Activity activity = getActivity();
        Intent intent = new Intent();
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        activity.setResult(-1, intent);
        activity.finish();
    }
}
