package dji.pilot.usercenter.activity;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

final class DJIWebVideoActivity$b extends WebViewClient {
    final /* synthetic */ DJIWebVideoActivity a;

    private DJIWebVideoActivity$b(DJIWebVideoActivity dJIWebVideoActivity) {
        this.a = dJIWebVideoActivity;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        DJIWebVideoActivity.f(this.a).loadUrl(str);
        return true;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        DJIWebVideoActivity.e(this.a).setVisibility(0);
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        DJIWebVideoActivity.e(this.a).setVisibility(8);
    }
}
