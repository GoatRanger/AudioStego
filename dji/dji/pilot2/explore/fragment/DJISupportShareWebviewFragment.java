package dji.pilot2.explore.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.ProgressBar;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c$f;
import dji.pilot.fpv.d.c$m;
import dji.pilot.fpv.d.c.o;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.usercenter.b.d;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot2.account.sign.DJIAccountSignFragment;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.mine.activity.WebActivity;
import dji.pilot2.nativeexplore.activity.DJI360WebViewActivity;
import dji.pilot2.publics.object.c;
import dji.pilot2.publics.object.c$e;
import dji.pilot2.utils.k;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJISupportShareWebviewFragment extends DJIWebviewFragment implements c$f, c$m, o {
    public static final String T = "explore";
    private View U;
    private DJITextView V;
    private DJIStateImageView W;
    private DJIStateImageView X;
    private DJIStateImageView Y;
    private DJIStateTextView Z;
    private OnClickListener aa = null;
    private SecExploreJsHandler ab = null;
    private boolean ac = false;
    private boolean ad = false;
    private boolean ae = false;
    private String af;
    private String ag;
    private dji.pilot2.mine.e.a.a ah = new dji.pilot2.mine.e.a.a();
    private e$a ai = null;
    private d aj = null;

    private class a extends c$a {
        final /* synthetic */ DJISupportShareWebviewFragment a;

        private a(DJISupportShareWebviewFragment dJISupportShareWebviewFragment) {
            this.a = dJISupportShareWebviewFragment;
            super(dJISupportShareWebviewFragment);
        }

        public void onReceivedTitle(WebView webView, String str) {
            this.a.i();
            super.onReceivedTitle(webView, str);
        }
    }

    private final class b extends c$b {
        final /* synthetic */ DJISupportShareWebviewFragment a;

        private b(DJISupportShareWebviewFragment dJISupportShareWebviewFragment) {
            this.a = dJISupportShareWebviewFragment;
            super(dJISupportShareWebviewFragment);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.a.ac = false;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (!this.a.ac) {
                this.a.a(webView);
            }
            if (this.a.cV_) {
                if (webView.canGoBack()) {
                    this.a.Z.go();
                } else {
                    this.a.Z.show();
                }
            }
            this.a.i();
        }
    }

    public static DJISupportShareWebviewFragment a(Bundle bundle) {
        DJISupportShareWebviewFragment dJISupportShareWebviewFragment = new DJISupportShareWebviewFragment();
        dJISupportShareWebviewFragment.setArguments(bundle);
        return dJISupportShareWebviewFragment;
    }

    public static DJISupportShareWebviewFragment a(String str) {
        DJISupportShareWebviewFragment dJISupportShareWebviewFragment = new DJISupportShareWebviewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DJIWebviewFragment.o, str);
        dJISupportShareWebviewFragment.setArguments(bundle);
        return dJISupportShareWebviewFragment;
    }

    protected void a() {
        DJILogHelper.getInstance().LOGI("bob", "onIsWhatsnewFlightJournal");
        super.a();
        if (this.cZ_) {
            this.aj = d.getInstance();
            this.ai = new e$a(this) {
                final /* synthetic */ DJISupportShareWebviewFragment a;

                {
                    this.a = r1;
                }

                public void a(int i, int i2, int i3, Object obj, Object obj2) {
                    DJILogHelper.getInstance().LOGI("bob", "success flightdata");
                    this.a.ae = true;
                    this.a.aj.b(true);
                    this.a.e();
                }

                public void a(int i, int i2, int i3, Object obj) {
                    DJILogHelper.getInstance().LOGI("bob", "fail flightdata");
                    this.a.ae = true;
                    this.a.aj.b(true);
                    this.a.e();
                }

                public void a(int i, long j, long j2, int i2, Object obj) {
                    DJILogHelper.getInstance().LOGI("bob", "current:" + j);
                }

                public void a(int i, boolean z, int i2, Object obj) {
                }
            };
            this.aj.c(this.ai);
            this.aj.b(false);
            this.aj.a(getActivity());
            this.aj.a(false, new dji.pilot.usercenter.b.d.b());
        }
    }

    private void e() {
        if (this.af == null) {
            b(this.ag);
        } else {
            a(this.ag, this.af);
        }
    }

    protected void b() {
        if (getArguments() != null) {
            this.ag = getArguments().getString(o, null);
            this.af = getArguments().getString(p, null);
            this.dm_ = getArguments().getBoolean(r, false);
            this.cV_ = getArguments().getBoolean(s, false);
            this.cW_ = getArguments().getBoolean(t, false);
            this.cX_ = getArguments().getBoolean(u, false);
            this.cY_ = getArguments().getBoolean(v, false);
            this.cZ_ = getArguments().getBoolean(w);
            this.da_ = getArguments().getBoolean(x, false);
            if (this.cY_) {
                a();
            }
            if (!this.cZ_) {
                e();
            }
        }
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.v2_main_explore_fragment, null);
        b();
        b(inflate);
        f();
        g();
        j();
        return inflate;
    }

    protected void a(View view) {
        this.de_ = (WebView) view.findViewById(R.id.crn);
        this.df_ = (ProgressBar) view.findViewById(R.id.cro);
        this.dh_ = (DJIRelativeLayout) view.findViewById(R.id.d5a);
        this.di_ = (DJIStateTextView) view.findViewById(R.id.d5b);
        this.dj_ = (DJIImageView) view.findViewById(R.id.d5c);
    }

    protected void c() {
        super.c();
        this.de_.setWebViewClient(new b());
        this.de_.setWebChromeClient(new a());
        this.ab = new SecExploreJsHandler(this, getActivity(), this.de_);
        this.de_.addJavascriptInterface(this.ab, c.dc_);
    }

    private void b(View view) {
        this.U = view.findViewById(R.id.i4);
        this.V = (DJITextView) view.findViewById(R.id.clk);
        this.W = (DJIStateImageView) view.findViewById(R.id.cll);
        this.X = (DJIStateImageView) view.findViewById(R.id.clj);
        this.Y = (DJIStateImageView) view.findViewById(R.id.c98);
        this.Z = (DJIStateTextView) view.findViewById(R.id.crl);
        if (this.cV_) {
            this.W.go();
            this.Z.show();
        }
    }

    private void f() {
        this.aa = new OnClickListener(this) {
            final /* synthetic */ DJISupportShareWebviewFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.c98:
                        this.a.getActivity().finish();
                        return;
                    case R.id.clj:
                        this.a.h();
                        return;
                    case R.id.cll:
                        if (this.a.ac) {
                            this.a.ab.sendShareCmdToJs();
                            return;
                        } else if (this.a.ad) {
                            if (this.a.cX_) {
                                e.b(o.dS_);
                            }
                            ((DJISupportShareWebviewActivity) this.a.getActivity()).a(this.a.ah);
                            return;
                        } else {
                            return;
                        }
                    case R.id.crl:
                        Intent intent = new Intent(this.a.getActivity(), WebActivity.class);
                        intent.putExtra(DJIWebviewFragment.o, k.p());
                        this.a.startActivity(intent);
                        return;
                    default:
                        return;
                }
            }
        };
        this.dl_ = new c$e(this) {
            final /* synthetic */ DJISupportShareWebviewFragment a;

            {
                this.a = r1;
            }

            public void a(String str) {
                this.a.V.setText(str);
            }
        };
    }

    private void g() {
        this.W.setOnClickListener(this.aa);
        this.X.setOnClickListener(this.aa);
        this.Y.setOnClickListener(this.aa);
        if (this.Z != null) {
            this.Z.setOnClickListener(this.aa);
        }
        dji.thirdparty.a.c.a().a(this);
    }

    private void h() {
        if (!d()) {
            getActivity().finish();
        }
    }

    public void onDestroy() {
        dji.thirdparty.a.c.a().d(this);
        super.onDestroy();
    }

    public void onEventMainThread(DJIAccountSignFragment.c cVar) {
        this.ab.handleLogin(cVar);
    }

    @SuppressLint({"NewApi"})
    private void a(WebView webView) {
        if (VERSION.SDK_INT >= 19) {
            webView.evaluateJavascript(DJISupportShareWebviewFragment$SecExploreJsHandler.V19_GET_DESC_JS_FUNCTION_NAME, new ValueCallback<String>(this) {
                final /* synthetic */ DJISupportShareWebviewFragment a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onReceiveValue(Object obj) {
                    a((String) obj);
                }

                public void a(String str) {
                    this.a.ah.d = this.a.d(str);
                }
            });
            webView.evaluateJavascript(DJISupportShareWebviewFragment$SecExploreJsHandler.V19_GET_FIRST_IMGSRC_JS_FUNCTION_NAME, new ValueCallback<String>(this) {
                final /* synthetic */ DJISupportShareWebviewFragment a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onReceiveValue(Object obj) {
                    a((String) obj);
                }

                public void a(String str) {
                    String substring = str.substring(1, str.length() - 1);
                    if (!substring.startsWith(".")) {
                        this.a.ah.a = substring;
                    }
                }
            });
        } else {
            webView.loadUrl(DJISupportShareWebviewFragment$SecExploreJsHandler.GET_DESC_JS_FUNCTION_NAME);
            webView.loadUrl(DJISupportShareWebviewFragment$SecExploreJsHandler.GET_FIRST_IMGSRC_JS_FUNCTION_NAME);
        }
        this.ah.e = "";
        this.ah.c = webView.getTitle();
        if (webView.getUrl().equals(k.p(f.getInstance().n()))) {
            this.ah.b = k.q(f.getInstance().n());
        } else {
            this.ah.b = webView.getUrl();
        }
        this.ad = true;
    }

    private String d(String str) {
        int i;
        int i2;
        int i3 = 1;
        if (str.startsWith("\"")) {
            i = 1;
        } else {
            i = 0;
        }
        if (str.endsWith("\"")) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (i == 0) {
            i3 = 0;
        }
        return str.substring(i3, i2 != 0 ? str.length() - 1 : str.length());
    }

    private void i() {
        if (this.de_.canGoBack()) {
            this.Y.show();
        } else {
            this.Y.go();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (!(getActivity() instanceof DJI360WebViewActivity) || !DJIOriLayout.getDeviceType().equals(DJIDeviceType.Phone)) {
            return;
        }
        if (configuration.orientation == 1) {
            ((View) this.W.getParent()).setVisibility(0);
            this.U.setFitsSystemWindows(true);
            return;
        }
        ((View) this.W.getParent()).setVisibility(8);
        this.U.setFitsSystemWindows(false);
        this.U.setPadding(0, 0, 0, 0);
    }

    private void j() {
        if (!(getActivity() instanceof DJI360WebViewActivity) || !DJIOriLayout.getDeviceType().equals(DJIDeviceType.Phone)) {
            return;
        }
        if (getResources().getConfiguration().orientation == 1) {
            ((View) this.W.getParent()).setVisibility(0);
            this.U.setFitsSystemWindows(true);
            return;
        }
        ((View) this.W.getParent()).setVisibility(8);
        this.U.setFitsSystemWindows(false);
        this.U.setPadding(0, 0, 0, 0);
    }
}
