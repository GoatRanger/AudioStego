package dji.pilot.flyunlimit.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.media.TransportMediator;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.dji.frame.c.l;
import dji.pilot.R;
import dji.pilot.flyunlimit.b;
import dji.pilot.fpv.d.c$p;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.objects.c;
import dji.pilot.publics.objects.g;
import dji.pilot.usercenter.b.f;

@SuppressLint({"SetJavaScriptEnabled"})
public class a extends c {
    protected b a = null;
    private WebView b = null;

    private final class a extends WebViewClient {
        final /* synthetic */ a a;

        private a(a aVar) {
            this.a = aVar;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.equals("djinfj://verifyCallBack#1")) {
                this.a.dismiss();
                dji.thirdparty.a.c.a().e(dji.pilot.flyunlimit.a.d.a.GO_TO_CONFIRM_VIEW);
                e.c(c$p.e);
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }

        @SuppressLint({"NewApi"})
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (str.contains(b.d)) {
                String b = g.b(this.a.getContext(), dji.pilot.flyforbid.a.b, "");
                String d = b.getInstance(this.a.N).d();
                if ("".equals(d)) {
                    d = f.getInstance().j();
                }
                if (VERSION.SDK_INT >= 19) {
                    webView.evaluateJavascript(String.format("var personal_info = {user_id: '%s'}; function callback(verified){if(verified){var url = 'djinfj://verifyCallBack#1'; window.location=url}};setup('%s', '%s', personal_info, callback);", new Object[]{d, b, this.a.getContext().getString(R.string.versionname)}), null);
                    return;
                }
                webView.loadUrl("javascript:" + String.format("var personal_info = {user_id: '%s'}; function callback(verified){if(verified){var url = 'djinfj://verifyCallBack#1'; window.location=url}};setup('%s', '%s', personal_info, callback);", new Object[]{d, b, this.a.getContext().getString(R.string.versionname)}));
            }
        }
    }

    public a(Context context) {
        super(context);
        setContentView(R.layout.airmap_verify_dialog);
        findViewById(R.id.ie).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.dismiss();
                e.c(c$p.f);
            }
        });
        this.b = (WebView) findViewById(R.id.id);
        this.b.getSettings().setJavaScriptEnabled(true);
        this.b.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.b.getSettings().setDomStorageEnabled(true);
        this.b.getSettings().setBuiltInZoomControls(true);
        this.b.getSettings().setDisplayZoomControls(false);
        this.b.getSettings().setPluginState(PluginState.ON);
        this.b.getSettings().setAllowFileAccess(true);
        this.b.getSettings().setAppCacheMaxSize(8388608);
        this.b.getSettings().setAppCacheEnabled(true);
        this.b.getSettings().setAppCachePath(getContext().getDir(MapTilsCacheAndResManager.MAP_CACHE_PATH_NAME, 0).getPath());
        this.b.setVerticalScrollBarEnabled(false);
        this.b.getSettings().setUseWideViewPort(true);
        this.b.getSettings().setLoadWithOverviewMode(true);
        this.b.setWebChromeClient(new WebChromeClient());
        this.b.setWebViewClient(new WebViewClient());
        this.b.requestFocus(TransportMediator.KEYCODE_MEDIA_RECORD);
        setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
                this.a.b.destroy();
            }
        });
        this.a = new b(this, getContext(), this.b);
        this.b.setWebViewClient(new a());
        this.b.addJavascriptInterface(this.a, dji.pilot2.publics.object.c.dc_);
    }

    public void a(String str) {
        if (!l.a(str)) {
            this.b.loadUrl(str);
        }
    }

    protected void onCreate(Bundle bundle) {
        a(dji.pilot.fpv.model.b.a(this.N, R.dimen.l_), dji.pilot.fpv.model.b.a(this.N, R.dimen.l9), 0, 17, false, false);
    }

    protected void onStart() {
        super.onStart();
        try {
            this.b.resumeTimers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.b.onResume();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    protected void onStop() {
        try {
            this.b.onPause();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.b.pauseTimers();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        super.onStop();
    }
}
