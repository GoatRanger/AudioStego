package com.facebook.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;

public class o {
    private Fragment a;
    private Fragment b;

    public o(Fragment fragment) {
        ai.a((Object) fragment, "fragment");
        this.a = fragment;
    }

    public Fragment a() {
        return this.b;
    }

    public Fragment b() {
        return this.a;
    }

    public void a(Intent intent, int i) {
        if (this.a != null) {
            this.a.startActivityForResult(intent, i);
        } else {
            this.b.startActivityForResult(intent, i);
        }
    }

    public final Activity c() {
        if (this.a != null) {
            return this.a.getActivity();
        }
        return this.b.getActivity();
    }
}
