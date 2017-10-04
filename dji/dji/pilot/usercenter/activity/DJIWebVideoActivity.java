package dji.pilot.usercenter.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.dji.frame.c.b;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.usercenter.mode.WebVideoInfo;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.afinal.a.b.c;

public class DJIWebVideoActivity extends DJIBaseActivity {
    private static final String a = "key_videoinfo";
    @c(a = 2131367309)
    private View b;
    @c(a = 2131367310)
    private DJIRelativeLayout c;
    @c(a = 2131367311)
    private DJIImageView d;
    @c(a = 2131367312)
    private DJITextView e;
    @c(a = 2131367314)
    private ProgressBar f;
    @c(a = 2131367313)
    private WebView g;
    @c(a = 2131367315)
    private ViewGroup h;
    private View i = null;
    private CustomViewCallback j = null;
    private WebVideoInfo k = null;
    private OnClickListener l = null;

    public static void a(Context context, WebVideoInfo webVideoInfo, int i) {
        if (webVideoInfo != null && webVideoInfo.j != null && webVideoInfo.j.length() > 0) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("key_videoinfo", webVideoInfo);
            b.a(context, DJIWebVideoActivity.class, bundle, i);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        setContentView(R.layout.web_video_view);
        a();
        c();
        d();
    }

    protected void onResume() {
        super.onResume();
        e();
        g();
    }

    protected void onPause() {
        h();
        super.onPause();
    }

    protected void onDestroy() {
        i();
        super.onDestroy();
    }

    public void onBackPressed() {
        finish();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    private void a() {
        Intent intent = getIntent();
        if (intent != null) {
            this.k = (WebVideoInfo) intent.getParcelableExtra("key_videoinfo");
        }
    }

    private void b() {
        this.l = new 1(this);
    }

    private void c() {
        b();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void d() {
        this.e.setText(this.k.e);
        this.d.setOnClickListener(this.l);
        WebSettings settings = this.g.getSettings();
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setPluginState(PluginState.ON);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        this.g.setWebChromeClient(new a(this, null));
        this.g.setWebViewClient(new b(this, null));
        this.g.loadUrl(this.k.j);
    }

    private void e() {
        try {
            this.g.resumeTimers();
        } catch (Exception e) {
        }
    }

    private void f() {
        try {
            this.g.pauseTimers();
        } catch (Exception e) {
        }
    }

    private void g() {
        try {
            this.g.onResume();
        } catch (Exception e) {
        }
    }

    private void h() {
        try {
            this.g.onPause();
        } catch (Exception e) {
        }
    }

    private void i() {
        try {
            this.g.destroy();
        } catch (Exception e) {
        }
    }
}
