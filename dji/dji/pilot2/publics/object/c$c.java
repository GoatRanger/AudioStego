package dji.pilot2.publics.object;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

protected class c$c implements DownloadListener {
    final /* synthetic */ c a;

    protected c$c(c cVar) {
        this.a = cVar;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }
}
