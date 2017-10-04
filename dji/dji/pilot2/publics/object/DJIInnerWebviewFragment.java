package dji.pilot2.publics.object;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import dji.pilot.R;
import dji.pilot2.share.LoadingView;

public class DJIInnerWebviewFragment extends c {
    private LoadingView o = null;

    private class a extends c$b {
        final /* synthetic */ DJIInnerWebviewFragment a;

        private a(DJIInnerWebviewFragment dJIInnerWebviewFragment) {
            this.a = dJIInnerWebviewFragment;
            super(dJIInnerWebviewFragment);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.a.o.setVisibility(0);
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            this.a.o.setVisibility(8);
        }
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.v2_fragment_inner_webview, null);
    }

    protected void a(View view) {
        this.de_ = (WebView) view.findViewById(R.id.cno);
        this.o = (LoadingView) view.findViewById(R.id.i6);
    }

    protected void c() {
        super.c();
        this.de_.setWebViewClient(new a());
    }
}
