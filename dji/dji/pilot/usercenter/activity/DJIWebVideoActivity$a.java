package dji.pilot.usercenter.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebView;

final class DJIWebVideoActivity$a extends WebChromeClient {
    final /* synthetic */ DJIWebVideoActivity a;

    private DJIWebVideoActivity$a(DJIWebVideoActivity dJIWebVideoActivity) {
        this.a = dJIWebVideoActivity;
    }

    public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
        onShowCustomView(view, this.a.getRequestedOrientation(), customViewCallback);
        super.onShowCustomView(view, customViewCallback);
    }

    public void onShowCustomView(View view, int i, CustomViewCallback customViewCallback) {
        if (DJIWebVideoActivity.a(this.a) != null) {
            super.onShowCustomView(view, i, customViewCallback);
            return;
        }
        DJIWebVideoActivity.a(this.a, view);
        DJIWebVideoActivity.b(this.a).addView(DJIWebVideoActivity.a(this.a));
        DJIWebVideoActivity.a(this.a, customViewCallback);
        DJIWebVideoActivity.c(this.a).setVisibility(4);
        DJIWebVideoActivity.b(this.a).setVisibility(0);
    }

    public void onHideCustomView() {
        if (DJIWebVideoActivity.a(this.a) != null) {
            DJIWebVideoActivity.c(this.a).setVisibility(0);
            DJIWebVideoActivity.a(this.a).setVisibility(8);
            DJIWebVideoActivity.b(this.a).removeView(DJIWebVideoActivity.a(this.a));
            DJIWebVideoActivity.a(this.a, null);
            DJIWebVideoActivity.b(this.a).setVisibility(8);
            try {
                DJIWebVideoActivity.d(this.a).onCustomViewHidden();
                return;
            } catch (Exception e) {
                return;
            }
        }
        super.onHideCustomView();
    }

    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        DJIWebVideoActivity.e(this.a).setProgress(i);
    }
}
