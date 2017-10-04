package dji.pilot2.publics.object;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.media.TransportMediator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.usercenter.b.f;
import dji.pilot2.b;
import dji.pilot2.utils.k;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import org.apache.http.util.EncodingUtils;

public abstract class c extends Fragment {
    public static final String dc_ = "ibg_js_manager";
    public static final int dd_ = 1;
    public static final String l = "DJI-App-pilot";
    public static final String m = "DJI-App-pilot-pad";
    protected WebView de_ = null;
    protected ProgressBar df_ = null;
    protected String dg_ = null;
    protected DJIRelativeLayout dh_;
    protected DJIStateTextView di_;
    protected DJIImageView dj_;
    protected View dk_ = null;
    protected e dl_ = null;
    protected boolean dm_ = false;
    protected ValueCallback<Uri[]> dn_;
    private String o = null;
    private d p = d.a;
    private String q = null;
    private final int r = 18;
    private final int s = 30;
    private int t = 18;

    protected abstract View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    protected abstract void a(View view);

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.dk_ == null) {
            this.dk_ = a(layoutInflater, viewGroup, bundle);
        }
        a(this.dk_);
        c();
        a();
        i();
        return this.dk_;
    }

    public void b(String str) {
        this.o = str;
        this.p = d.a;
        a();
    }

    @Deprecated
    public void a(String str, String str2) {
        this.o = str;
        this.q = str2;
        this.p = d.b;
        a();
    }

    private void a() {
        if (this.de_ != null && this.o != null) {
            this.p = d.a;
            if (f.getInstance().c()) {
                Context activity = getActivity();
                if (activity == null) {
                    activity = b.a.a();
                }
                k.a(activity, f.getInstance().n());
            }
            if (this.p == d.a) {
                this.de_.loadUrl(k.w(this.o));
            } else {
                this.de_.postUrl(k.w(this.o), EncodingUtils.getBytes(this.q, "UTF-8"));
            }
            if (VERSION.SDK_INT >= 21) {
                CookieManager.getInstance().setAcceptThirdPartyCookies(this.de_, true);
            }
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    @TargetApi(19)
    protected void c() {
        this.de_.getSettings().setJavaScriptEnabled(true);
        this.de_.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.de_.getSettings().setDomStorageEnabled(true);
        this.de_.getSettings().setBuiltInZoomControls(true);
        this.de_.getSettings().setDisplayZoomControls(false);
        this.de_.getSettings().setPluginState(PluginState.ON);
        this.de_.getSettings().setAllowFileAccess(true);
        this.de_.getSettings().setAppCacheMaxSize(8388608);
        this.de_.getSettings().setAppCacheEnabled(true);
        this.de_.getSettings().setAppCachePath(getActivity().getDir(MapTilsCacheAndResManager.MAP_CACHE_PATH_NAME, 0).getPath());
        this.de_.setVerticalScrollBarEnabled(false);
        this.de_.getSettings().setUseWideViewPort(true);
        this.de_.getSettings().setLoadWithOverviewMode(true);
        this.de_.setWebChromeClient(new a(this));
        this.de_.setWebViewClient(new b(this));
        this.de_.setDownloadListener(new c(this));
        if (VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        this.de_.requestFocus(TransportMediator.KEYCODE_MEDIA_RECORD);
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.t = 30;
        }
        this.di_.setOnClickListener(new 1(this));
        String userAgentString = this.de_.getSettings().getUserAgentString();
        this.dm_ = true;
        if (this.dm_ && DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            this.de_.getSettings().setUserAgentString(userAgentString + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + l);
        } else if (this.dm_ && DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.de_.getSettings().setUserAgentString(userAgentString + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + m);
        }
    }

    public boolean d() {
        if (this.de_ == null || !this.de_.canGoBack()) {
            return false;
        }
        this.de_.goBack();
        return true;
    }

    private void b() {
        try {
            this.de_.resumeTimers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void e() {
        try {
            this.de_.pauseTimers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void f() {
        try {
            this.de_.onResume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void g() {
        try {
            this.de_.onPause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void h() {
        try {
            this.de_.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onResume() {
        super.onResume();
        f();
        b();
    }

    public void onPause() {
        g();
        super.onPause();
    }

    public void onDestroy() {
        h();
        super.onDestroy();
    }

    private void a(String str) {
        if (str != null && this.dl_ != null) {
            this.dl_.a(str);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && this.dn_ != null) {
            if (i2 == -1) {
                if (intent.getData() != null) {
                    this.dn_.onReceiveValue(new Uri[]{r0});
                } else {
                    this.dn_.onReceiveValue(null);
                }
            } else {
                this.dn_.onReceiveValue(null);
            }
            this.dn_ = null;
        }
    }

    private void i() {
        if (!this.dj_.isShown()) {
            this.dj_.show();
            ((AnimationDrawable) this.dj_.getBackground()).start();
        }
    }

    private void j() {
        this.dj_.go();
    }

    public void a(e eVar) {
        this.dl_ = eVar;
    }
}
