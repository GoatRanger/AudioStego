package com.facebook.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.a.b;

public class BoltsMeasurementEventListener extends BroadcastReceiver {
    private static BoltsMeasurementEventListener a = null;
    private static final String b = "com.parse.bolts.measurement_event";
    private static final String c = "event_name";
    private static final String d = "event_args";
    private static final String e = "bf_";
    private Context f;

    private BoltsMeasurementEventListener(Context context) {
        this.f = context.getApplicationContext();
    }

    private void a() {
        LocalBroadcastManager.getInstance(this.f).registerReceiver(this, new IntentFilter("com.parse.bolts.measurement_event"));
    }

    private void b() {
        LocalBroadcastManager.getInstance(this.f).unregisterReceiver(this);
    }

    public static BoltsMeasurementEventListener getInstance(Context context) {
        if (a != null) {
            return a;
        }
        a = new BoltsMeasurementEventListener(context);
        a.a();
        return a;
    }

    protected void finalize() throws Throwable {
        try {
            b();
        } finally {
            super.finalize();
        }
    }

    public void onReceive(Context context, Intent intent) {
        b c = b.c(context);
        String str = e + intent.getStringExtra("event_name");
        Bundle bundleExtra = intent.getBundleExtra("event_args");
        Bundle bundle = new Bundle();
        for (String str2 : bundleExtra.keySet()) {
            bundle.putString(str2.replaceAll("[^0-9a-zA-Z _-]", "-").replaceAll("^[ -]*", "").replaceAll("[ -]*$", ""), (String) bundleExtra.get(str2));
        }
        c.a(str, bundle);
    }
}
