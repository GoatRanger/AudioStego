package cn.sharesdk.tencent.qzone;

import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import cn.sharesdk.framework.authorize.b;
import cn.sharesdk.framework.authorize.e;
import cn.sharesdk.framework.utils.d;
import com.facebook.internal.ab;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;

public class a extends b {
    public a(e eVar) {
        super(eVar);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, final String str) {
        if (str.startsWith(this.b)) {
            webView.setVisibility(4);
            webView.stopLoading();
            this.a.finish();
            new Thread(this) {
                final /* synthetic */ a b;

                public void run() {
                    try {
                        this.b.a(str);
                    } catch (Throwable th) {
                        d.a().d(th);
                    }
                }
            }.start();
        } else {
            webView.loadUrl(str);
        }
        return true;
    }

    protected void a(String str) {
        if (str.startsWith(this.b)) {
            str = str.substring(str.indexOf(35) + 1);
        }
        String[] split = str.split(com.alipay.sdk.h.a.b);
        HashMap hashMap = new HashMap();
        for (String split2 : split) {
            String[] split3 = split2.split("=");
            if (split3.length < 2) {
                hashMap.put(URLDecoder.decode(split3[0]), "");
            } else {
                hashMap.put(URLDecoder.decode(split3[0]), URLDecoder.decode(split3[1] == null ? "" : split3[1]));
            }
        }
        a(hashMap);
    }

    private void a(HashMap<String, String> hashMap) {
        String str = (String) hashMap.get("access_token");
        String str2 = (String) hashMap.get("expires_in");
        String str3 = (String) hashMap.get("error");
        String str4 = (String) hashMap.get(ab.am);
        String str5 = (String) hashMap.get("pf");
        String str6 = (String) hashMap.get("pfkey");
        String str7 = (String) hashMap.get("pay_token");
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap e = b.a(this.a.a().getPlatform()).e(str);
                if (e == null || e.size() <= 0) {
                    if (this.c != null) {
                        this.c.onError(new Throwable());
                    }
                } else if (e.containsKey("openid")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("access_token", str);
                    bundle.putString("open_id", String.valueOf(e.get("openid")));
                    bundle.putString("expires_in", str2);
                    bundle.putString("pf", str5);
                    bundle.putString("pfkey", str6);
                    bundle.putString("pay_token", str7);
                    if (this.c != null) {
                        this.c.onComplete(bundle);
                    }
                } else if (this.c != null) {
                    this.c.onError(new Throwable());
                }
            } catch (Throwable th) {
                if (this.c != null) {
                    this.c.onError(th);
                }
            }
        } else if (TextUtils.isEmpty(str3)) {
            this.c.onError(new Throwable());
        } else {
            str = str4 + " (" + str3 + ")";
            if (this.c != null) {
                this.c.onError(new Throwable(str));
            }
        }
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        try {
            Method method = sslErrorHandler.getClass().getMethod("proceed", new Class[0]);
            method.setAccessible(true);
            method.invoke(sslErrorHandler, new Object[0]);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
