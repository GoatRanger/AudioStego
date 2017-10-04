package cn.sharesdk.instagram;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import cn.sharesdk.framework.authorize.b;
import cn.sharesdk.framework.authorize.e;
import cn.sharesdk.framework.utils.d;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import dji.pilot.usercenter.mode.n;
import java.util.HashMap;

public class a extends b {
    private boolean d;

    public a(e eVar) {
        super(eVar);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!str.startsWith(this.b)) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        webView.setVisibility(4);
        webView.stopLoading();
        this.a.finish();
        a(str);
        return true;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (str.startsWith(this.b)) {
            webView.setVisibility(4);
            webView.stopLoading();
            this.a.finish();
            a(str);
            return;
        }
        super.onPageStarted(webView, str, bitmap);
    }

    protected void a(String str) {
        if (!this.d) {
            this.d = true;
            Bundle urlToBundle = R.urlToBundle(str);
            if (urlToBundle.containsKey("code")) {
                final String string = urlToBundle.getString("code");
                new Thread(this) {
                    final /* synthetic */ a b;

                    public void run() {
                        String a;
                        try {
                            a = b.a(this.b.a.a().getPlatform()).a(string);
                        } catch (Throwable th) {
                            this.b.c.onError(th);
                            a = null;
                        }
                        if (a == null) {
                            this.b.c.onError(new Throwable("Authorize token is empty"));
                            return;
                        }
                        try {
                            HashMap fromJson = new Hashon().fromJson(a);
                            Bundle bundle = new Bundle();
                            bundle.putString("access_token", String.valueOf(fromJson.get("access_token")));
                            fromJson = (HashMap) fromJson.get("user");
                            bundle.putString("username", String.valueOf(fromJson.get("username")));
                            bundle.putString(n.W, String.valueOf(fromJson.get(n.W)));
                            bundle.putString("website", String.valueOf(fromJson.get("website")));
                            bundle.putString("profile_picture", String.valueOf(fromJson.get("profile_picture")));
                            bundle.putString("full_name", String.valueOf(fromJson.get("full_name")));
                            bundle.putString("id", String.valueOf(fromJson.get("id")));
                            this.b.c.onComplete(bundle);
                        } catch (Throwable th2) {
                            d.a().d(th2);
                        }
                    }
                }.start();
            } else if (this.c != null) {
                this.c.onError(new Throwable("code is null"));
            }
        }
    }
}
