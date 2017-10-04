package dji.pilot2.publics.object;

import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebView;
import dji.pilot.R;

protected class c$a extends WebChromeClient {
    final /* synthetic */ c b;

    protected c$a(c cVar) {
        this.b = cVar;
    }

    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        if (this.b.df_ != null) {
            this.b.df_.setProgress(i);
        }
    }

    public void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
        if (this.b.dh_.isShown() && this.b.dg_ == null) {
            this.b.dg_ = str;
        }
        if (!str.equals(this.b.dg_)) {
            this.b.dh_.go();
            this.b.dj_.go();
            if (this.b.df_.getProgress() != this.b.df_.getMax()) {
                this.b.df_.setVisibility(0);
            } else {
                this.b.df_.setVisibility(8);
            }
        }
        c.a(this.b, str);
    }

    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
        this.b.dn_ = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        this.b.startActivityForResult(Intent.createChooser(intent, this.b.getString(R.string.library_select_item)), 1);
        return true;
    }
}
