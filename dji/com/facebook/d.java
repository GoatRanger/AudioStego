package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.internal.ai;

public abstract class d {
    private final BroadcastReceiver a;
    private final LocalBroadcastManager b;
    private boolean c = false;

    private class a extends BroadcastReceiver {
        final /* synthetic */ d a;

        private a(d dVar) {
            this.a = dVar;
        }

        public void onReceive(Context context, Intent intent) {
            if ("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED".equals(intent.getAction())) {
                this.a.a((AccessToken) intent.getParcelableExtra("com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN"), (AccessToken) intent.getParcelableExtra("com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN"));
            }
        }
    }

    protected abstract void a(AccessToken accessToken, AccessToken accessToken2);

    public d() {
        ai.b();
        this.a = new a();
        this.b = LocalBroadcastManager.getInstance(o.h());
        a();
    }

    public void a() {
        if (!this.c) {
            d();
            this.c = true;
        }
    }

    public void b() {
        if (this.c) {
            this.b.unregisterReceiver(this.a);
            this.c = false;
        }
    }

    public boolean c() {
        return this.c;
    }

    private void d() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
        this.b.registerReceiver(this.a, intentFilter);
    }
}
