package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.internal.ai;

public abstract class ab {
    private final BroadcastReceiver a;
    private final LocalBroadcastManager b;
    private boolean c = false;

    private class a extends BroadcastReceiver {
        final /* synthetic */ ab a;

        private a(ab abVar) {
            this.a = abVar;
        }

        public void onReceive(Context context, Intent intent) {
            if ("com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED".equals(intent.getAction())) {
                this.a.a((Profile) intent.getParcelableExtra("com.facebook.sdk.EXTRA_OLD_PROFILE"), (Profile) intent.getParcelableExtra("com.facebook.sdk.EXTRA_NEW_PROFILE"));
            }
        }
    }

    protected abstract void a(Profile profile, Profile profile2);

    public ab() {
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
        intentFilter.addAction("com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED");
        this.b.registerReceiver(this.a, intentFilter);
    }
}
