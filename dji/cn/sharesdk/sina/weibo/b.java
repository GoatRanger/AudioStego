package cn.sharesdk.sina.weibo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.authorize.e;
import cn.sharesdk.framework.utils.d;
import com.facebook.internal.ab;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import java.util.HashMap;

public class b extends cn.sharesdk.framework.authorize.b {
    private boolean d;

    public b(e eVar) {
        super(eVar);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!TextUtils.isEmpty(this.b) && str.startsWith(this.b)) {
            webView.stopLoading();
            this.a.finish();
            a(str);
            return true;
        } else if (!str.startsWith("sms:")) {
            return super.shouldOverrideUrlLoading(webView, str);
        } else {
            String substring = str.substring(4);
            try {
                Intent b = b(substring);
                b.setPackage("com.android.mms");
                webView.getContext().startActivity(b);
                return true;
            } catch (Throwable th) {
                if (this.c == null) {
                    return true;
                }
                this.c.onError(th);
                return true;
            }
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (!TextUtils.isEmpty(this.b) && str.startsWith(this.b)) {
            webView.stopLoading();
            this.a.finish();
            a(str);
        } else if (str.startsWith("sms:")) {
            String substring = str.substring(4);
            try {
                Intent b = b(substring);
                b.setPackage("com.android.mms");
                webView.getContext().startActivity(b);
            } catch (Throwable th) {
                if (this.c != null) {
                    this.c.onError(th);
                }
            }
        } else {
            super.onPageStarted(webView, str, bitmap);
        }
    }

    protected void a(String str) {
        if (!this.d) {
            this.d = true;
            Bundle urlToBundle = R.urlToBundle(str);
            String string = urlToBundle.getString("error");
            String string2 = urlToBundle.getString(ab.an);
            if (this.c == null) {
                return;
            }
            if (string == null && string2 == null) {
                Object string3 = urlToBundle.getString("code");
                if (TextUtils.isEmpty(string3)) {
                    this.c.onError(new Throwable("Authorize code is empty"));
                }
                a(this.a.a().getPlatform(), string3);
            } else if (string.equals("access_denied")) {
                this.c.onCancel();
            } else {
                int i = 0;
                try {
                    i = R.parseInt(string2);
                } catch (Throwable th) {
                    d.a().d(th);
                }
                this.c.onError(new Throwable(string + " (" + i + ")"));
            }
        }
    }

    private void a(final Platform platform, final String str) {
        new Thread(this) {
            final /* synthetic */ b c;

            public void run() {
                try {
                    String a = d.a(platform).a(platform.getContext(), str);
                } catch (Throwable th) {
                    d.a().d(th);
                    return;
                }
                if (a == null) {
                    this.c.c.onError(new Throwable("Authorize token is empty"));
                    return;
                }
                HashMap fromJson = new Hashon().fromJson(a);
                Bundle bundle = new Bundle();
                bundle.putString("uid", String.valueOf(fromJson.get("uid")));
                bundle.putString("remind_in", String.valueOf(fromJson.get("remind_in")));
                bundle.putString("expires_in", String.valueOf(fromJson.get("expires_in")));
                bundle.putString("access_token", String.valueOf(fromJson.get("access_token")));
                this.c.c.onComplete(bundle);
            }
        }.start();
    }

    private Intent b(String str) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
        intent.putExtra("sms_body", "");
        intent.setFlags(268435456);
        return intent;
    }
}
