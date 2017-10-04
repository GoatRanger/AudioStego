package com.facebook;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.facebook.internal.ab;
import com.facebook.internal.k;
import com.facebook.login.d;

public class FacebookActivity extends Activity {
    public static String a = "PassThrough";
    private static String b = "SingleFragment";
    private Fragment c;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.com_facebook_activity_layout);
        Intent intent = getIntent();
        if (a.equals(intent.getAction())) {
            b();
            return;
        }
        FragmentManager fragmentManager = getFragmentManager();
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(b);
        if (findFragmentByTag == null) {
            if (k.a.equals(intent.getAction())) {
                findFragmentByTag = new k();
                findFragmentByTag.setRetainInstance(true);
                findFragmentByTag.show(fragmentManager, b);
            } else {
                findFragmentByTag = new d();
                findFragmentByTag.setRetainInstance(true);
                fragmentManager.beginTransaction().add(R.id.com_facebook_fragment_container, findFragmentByTag, b).commit();
            }
        }
        this.c = findFragmentByTag;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.c != null) {
            this.c.onConfigurationChanged(configuration);
        }
    }

    private void b() {
        Intent intent = getIntent();
        setResult(0, ab.a(intent, null, ab.a(ab.d(intent))));
        finish();
    }

    public Fragment a() {
        return this.c;
    }
}
