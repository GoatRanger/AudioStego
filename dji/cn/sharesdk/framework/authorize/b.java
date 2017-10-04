package cn.sharesdk.framework.authorize;

import android.webkit.WebView;
import cn.sharesdk.framework.d;

public abstract class b extends d {
    protected e a;
    protected String b;
    protected AuthorizeListener c;

    public b(e eVar) {
        this.a = eVar;
        AuthorizeHelper a = eVar.a();
        this.b = a.getRedirectUri();
        this.c = a.getAuthorizeListener();
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        webView.stopLoading();
        AuthorizeListener authorizeListener = this.a.a().getAuthorizeListener();
        this.a.finish();
        if (authorizeListener != null) {
            authorizeListener.onError(new Throwable(str + " (" + i + "): " + str2));
        }
    }
}
