package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import org.json.JSONObject;

public class m extends aj {
    private static final String e = m.class.getName();
    private static final int f = 1500;
    private boolean g;

    public m(Context context, String str, String str2) {
        super(context, str);
        b(str2);
    }

    protected Bundle a(String str) {
        Bundle d = ah.d(Uri.parse(str).getQuery());
        String string = d.getString(af.w);
        d.remove(af.w);
        if (!ah.a(string)) {
            try {
                d.putBundle(ab.v, e.a(new JSONObject(string)));
            } catch (Throwable e) {
                ah.a(e, "Unable to parse bridge_args JSON", e);
            }
        }
        string = d.getString(af.z);
        d.remove(af.z);
        if (!ah.a(string)) {
            if (ah.a(string)) {
                string = "{}";
            }
            try {
                d.putBundle(ab.x, e.a(new JSONObject(string)));
            } catch (Throwable e2) {
                ah.a(e, "Unable to parse bridge_args JSON", e2);
            }
        }
        d.remove("version");
        d.putInt(ab.r, ab.a());
        return d;
    }

    public void cancel() {
        WebView d = d();
        if (!c() || b() || d == null || !d.isShown()) {
            super.cancel();
        } else if (!this.g) {
            this.g = true;
            d.loadUrl("javascript:" + "(function() {  var event = document.createEvent('Event');  event.initEvent('fbPlatformDialogMustClose',true,true);  document.dispatchEvent(event);})();");
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                final /* synthetic */ m a;

                {
                    this.a = r1;
                }

                public void run() {
                    super.cancel();
                }
            }, 1500);
        }
    }
}
