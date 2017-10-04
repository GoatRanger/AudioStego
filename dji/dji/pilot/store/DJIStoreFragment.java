package dji.pilot.store;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.objects.d;
import dji.pilot.usercenter.b.f;
import dji.publics.DJIUI.DJIImageView;
import java.util.Locale;
import org.apache.http.util.EncodingUtils;

public class DJIStoreFragment extends d {
    private static final String a = "https://www.dji.com/user/login_from_other";
    private static final String b = "http://www.dbeta.me/user/login_from_other";
    private static final String c = "from=dji-pilot-app&token=%1$s&display_type=%2$s&locale=%3$s";
    private static final String d = "tablet";
    private static final String e = "mobile";
    private DJIImageView f = null;
    private DJIImageView g = null;
    private DJIImageView h = null;
    private ProgressBar i = null;
    private WebView l = null;
    private OnClickListener m = null;

    private final class a extends WebChromeClient {
        final /* synthetic */ DJIStoreFragment a;

        private a(DJIStoreFragment dJIStoreFragment) {
            this.a = dJIStoreFragment;
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            this.a.i.setProgress(i);
        }
    }

    private final class b extends WebViewClient {
        final /* synthetic */ DJIStoreFragment a;

        private b(DJIStoreFragment dJIStoreFragment) {
            this.a = dJIStoreFragment;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            DJILogHelper.getInstance().LOGD("", "url[" + str + "}time1[" + System.currentTimeMillis() + dji.pilot.usercenter.protocol.d.H, false, true);
            this.a.l.loadUrl(str);
            return true;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.a.i.setVisibility(0);
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            this.a.i.setVisibility(8);
            this.a.g.setEnabled(this.a.l.canGoForward());
        }
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.k = layoutInflater.inflate(R.layout.store_fragment_view, viewGroup, false);
        a();
        c();
        return this.k;
    }

    protected void l() {
    }

    public boolean a(KeyEvent keyEvent) {
        return super.a(keyEvent);
    }

    public boolean m() {
        if (!this.l.canGoBack()) {
            return super.m();
        }
        this.l.goBack();
        return true;
    }

    public void onResume() {
        super.onResume();
        f();
        d();
    }

    public void onPause() {
        e();
        g();
        super.onPause();
    }

    public void q() {
        h();
        super.q();
    }

    private void a() {
        this.m = new OnClickListener(this) {
            final /* synthetic */ DJIStoreFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.c06) {
                    e.a("UserCenter_ShopView_Button_Back");
                    this.a.a(false);
                } else if (id == R.id.c03) {
                    this.a.a(true);
                } else if (id == R.id.c05 && this.a.l.canGoForward()) {
                    this.a.l.goForward();
                }
            }
        };
    }

    private String b() {
        String n = f.getInstance().n();
        String a = dji.pilot.fpv.model.b.a();
        String str = "mobile";
        if ("large".equals(a) || "xlarge".equals(a)) {
            str = "tablet";
        }
        Locale locale = getResources().getConfiguration().locale;
        a = "en";
        if (Locale.SIMPLIFIED_CHINESE.getLanguage().equals(locale.getLanguage()) && Locale.SIMPLIFIED_CHINESE.getCountry().equals(locale.getCountry())) {
            a = dji.pilot2.publics.b.a.p;
        }
        return String.format(Locale.US, c, new Object[]{n, str, a});
    }

    private void c() {
        this.f = (DJIImageView) b(R.id.c06);
        this.g = (DJIImageView) b(R.id.c05);
        this.h = (DJIImageView) b(R.id.c03);
        this.i = (ProgressBar) b(R.id.c08);
        this.l = (WebView) b(R.id.c07);
        this.f.setOnClickListener(this.m);
        this.h.setOnClickListener(this.m);
        this.g.setOnClickListener(this.m);
        this.g.setEnabled(false);
        WebSettings settings = this.l.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setPluginState(PluginState.ON);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        this.l.setWebChromeClient(new a());
        this.l.setWebViewClient(new b());
        this.l.postUrl("https://www.dji.com/user/login_from_other", EncodingUtils.getBytes(b(), "UTF-8"));
        DJILogHelper.getInstance().LOGD("", "time1[" + System.currentTimeMillis() + dji.pilot.usercenter.protocol.d.H, false, true);
    }

    private void d() {
        try {
            this.l.resumeTimers();
        } catch (Exception e) {
        }
    }

    private void e() {
        try {
            this.l.pauseTimers();
        } catch (Exception e) {
        }
    }

    private void f() {
        try {
            this.l.onResume();
        } catch (Exception e) {
        }
    }

    private void g() {
        try {
            this.l.onPause();
        } catch (Exception e) {
        }
    }

    private void h() {
        if (isDetached()) {
            try {
                this.l.destroy();
            } catch (Exception e) {
            }
        }
    }

    private void a(boolean z) {
        if (z) {
            if (this.j != null) {
                this.j.finishThis();
            }
        } else if (this.l.canGoBack()) {
            this.l.goBack();
        } else if (this.j != null) {
            this.j.finishThis();
        }
    }
}
