package dji.pilot.flyunlimit.a;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import dji.pilot.usercenter.b.f;
import dji.pilot2.account.sign.DJIAccountSignFragment.c;
import dji.pilot2.utils.k;

protected class a$b {
    public static final String a = "getLogoutStateFromApp";
    public static final String b = "djinfj://verifyCallBack#1";
    public static final String c = "var personal_info = {user_id: '%s'}; function callback(verified){if(verified){var url = 'djinfj://verifyCallBack#1'; window.location=url}};setup('%s', '%s', personal_info, callback);";
    protected Context d;
    protected String e = "JsHandler";
    protected WebView f;
    protected String g = null;
    protected String h = null;
    final /* synthetic */ a i;

    public a$b(a aVar, Context context, WebView webView) {
        this.i = aVar;
        this.d = context;
        this.f = webView;
    }

    public void a(c cVar) {
        if (cVar == c.a) {
            k.a(this.d, f.getInstance().n());
            this.f.loadUrl(this.g);
        } else if (cVar == c.b && !this.h.equals("")) {
        }
    }

    @JavascriptInterface
    public void user_login(String str, String str2) {
        this.g = str;
        this.h = str2;
    }

    @JavascriptInterface
    public void user_logout() {
    }
}
