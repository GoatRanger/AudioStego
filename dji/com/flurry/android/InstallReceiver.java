package com.flurry.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.flurry.sdk.gh;
import com.flurry.sdk.in;
import com.flurry.sdk.jz;

public final class InstallReceiver extends BroadcastReceiver {
    static final String a = InstallReceiver.class.getSimpleName();

    public void onReceive(Context context, Intent intent) {
        in.a(4, a, "Received an Install nofication of " + intent.getAction());
        String string = intent.getExtras().getString("referrer");
        in.a(4, a, "Received an Install referrer of " + string);
        if (string == null || !"com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            in.a(5, a, "referrer is null");
            return;
        }
        if (!string.contains("=")) {
            in.a(4, a, "referrer is before decoding: " + string);
            string = jz.d(string);
            in.a(4, a, "referrer is: " + string);
        }
        new gh(context).a(string);
    }
}
