package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.internal.ab;

public class FacebookBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(ab.t);
        String stringExtra2 = intent.getStringExtra(ab.s);
        if (stringExtra != null && stringExtra2 != null) {
            Bundle extras = intent.getExtras();
            if (ab.f(intent)) {
                b(stringExtra, stringExtra2, extras);
            } else {
                a(stringExtra, stringExtra2, extras);
            }
        }
    }

    protected void a(String str, String str2, Bundle bundle) {
    }

    protected void b(String str, String str2, Bundle bundle) {
    }
}
