package cn.sharesdk.tumblr;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import cn.sharesdk.framework.authorize.b;
import cn.sharesdk.framework.authorize.e;
import cn.sharesdk.framework.utils.d;
import com.mob.tools.utils.R;

public class a extends b {
    private boolean d;

    public a(e eVar) {
        super(eVar);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.b == null || !str.startsWith(this.b)) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        webView.stopLoading();
        this.a.finish();
        final String valueOf = String.valueOf(R.urlToBundle(str).get("oauth_verifier"));
        new Thread(this) {
            final /* synthetic */ a b;

            public void run() {
                try {
                    this.b.a(valueOf);
                } catch (Throwable th) {
                    d.a().d(th);
                }
            }
        }.start();
        return true;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.b != null && str.startsWith(this.b)) {
            webView.stopLoading();
            this.a.finish();
            final String valueOf = String.valueOf(R.urlToBundle(str).get("oauth_verifier"));
            new Thread(this) {
                final /* synthetic */ a b;

                public void run() {
                    try {
                        this.b.a(valueOf);
                    } catch (Throwable th) {
                        d.a().d(th);
                    }
                }
            }.start();
        }
        super.onPageStarted(webView, str, bitmap);
    }

    public void a(String str) {
        if (!this.d) {
            this.d = true;
            String a = b.a(this.a.a().getPlatform()).a(str);
            if (a != null && a.length() > 0) {
                String[] split = a.split(com.alipay.sdk.h.a.b);
                Bundle bundle = new Bundle();
                for (String str2 : split) {
                    if (str2 != null) {
                        String[] split2 = str2.split("=");
                        if (split2.length >= 2) {
                            bundle.putString(split2[0], split2[1]);
                        }
                    }
                }
                if (bundle == null || bundle.size() <= 0) {
                    if (this.c != null) {
                        this.c.onError(new Throwable());
                    }
                } else if (this.c != null) {
                    this.c.onComplete(bundle);
                }
            } else if (this.c != null) {
                this.c.onError(new Throwable());
            }
        }
    }
}
