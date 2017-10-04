package dji.pilot2.welcome.fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ProgressBar;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.usercenter.b.f;
import dji.pilot2.account.sign.DJIAccountSignFragment;
import dji.pilot2.mine.jsonbean.RepairEvent.a;
import dji.pilot2.publics.b.a$j;
import dji.pilot2.publics.object.c;
import dji.pilot2.utils.k;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class DJIWebviewFragment extends c {
    public static String o = "DJIWebviewFragment_Url";
    public static String p = "DJIWebviewFragment_PostData";
    public static String q = "DJIWebviewFragment_ShareBridge";
    @Deprecated
    public static String r = "DJIWebviewFragment_SetDJIUserAgent";
    public static String s = "DJIWebviewFragment_IsDDSWebview";
    public static String t = "DJIWebviewFragment_IsEnterFromExplore";
    public static String u = "DJIWebviewFragment_IsBannerAds";
    public static String v = "DJIWebviewFragment_IsWhatsnewFlightJournal";
    public static String w = "DJIWebviewFragment_NeedUpload";
    public static String x = "DJIWevviewFragment_Is2015Page";
    protected boolean cV_ = false;
    protected boolean cW_ = false;
    protected boolean cX_ = false;
    protected boolean cY_ = false;
    protected boolean cZ_ = false;
    protected boolean da_ = false;
    protected WebBaseJsHandler db_ = null;

    protected class WebBaseJsHandler {
        public static final String AIRMAP_CALLBACK_URL = "djinfj://verifyCallBack#1";
        public static final String GET_LOGOUT_STATE_JS_FUNCTION_NAME = "getLogoutStateFromApp";
        public static final String SET_UP_AIRMAP_WITH_KEY = "var personal_info = {user_id: '%s'}; function callback(verified){if(verified){var url = 'djinfj://verifyCallBack#1'; window.location=url}};setup('%s', '%s', personal_info, callback);";
        protected String TAG = "JsHandler";
        protected Activity mActivity;
        protected WebView mJSWebView;
        protected String mLoginFailUrl = null;
        protected String mLoginSucceedUrl = null;

        public WebBaseJsHandler(Activity activity, WebView webView) {
            this.mActivity = activity;
            this.mJSWebView = webView;
        }

        private String convertUrlWithParams(String str) {
            StringBuffer stringBuffer = new StringBuffer(255);
            Matcher matcher = Pattern.compile("\\{.*?\\}").matcher(str);
            while (matcher.find()) {
                String substring = str.substring(matcher.start(), matcher.end());
                if (substring.equals("{dji_token}")) {
                    if (f.getInstance().c()) {
                        matcher.appendReplacement(stringBuffer, f.getInstance().n());
                    } else {
                        matcher.appendReplacement(stringBuffer, "");
                    }
                } else if (substring.equals("{dji_email}")) {
                    if (f.getInstance().c()) {
                        matcher.appendReplacement(stringBuffer, f.getInstance().j());
                    } else {
                        matcher.appendReplacement(stringBuffer, "");
                    }
                } else if (!substring.equals("{dji_lang}")) {
                    matcher.appendReplacement(stringBuffer, "");
                } else if ("CN".equals(Locale.getDefault().getCountry())) {
                    matcher.appendReplacement(stringBuffer, "cn");
                } else if ("JP".equals(Locale.getDefault().getCountry())) {
                    matcher.appendReplacement(stringBuffer, a$j.e);
                } else if ("MO,TW,HK".contains(Locale.getDefault().getCountry())) {
                    matcher.appendReplacement(stringBuffer, a$j.g);
                } else {
                    matcher.appendReplacement(stringBuffer, "en");
                }
            }
            matcher.appendTail(stringBuffer);
            return stringBuffer.toString();
        }

        public void handleLogin(DJIAccountSignFragment.c cVar) {
            if (cVar == DJIAccountSignFragment.c.a) {
                k.a(this.mActivity, f.getInstance().n());
                this.mJSWebView.loadUrl(convertUrlWithParams(this.mLoginSucceedUrl));
            } else if (cVar == DJIAccountSignFragment.c.b && !this.mLoginFailUrl.equals("")) {
            }
        }

        @JavascriptInterface
        public void user_login(String str, String str2) {
            this.mLoginSucceedUrl = str;
            this.mLoginFailUrl = str2;
            this.mActivity.runOnUiThread(new 1(this));
        }

        @JavascriptInterface
        public void user_logout() {
            this.mActivity.runOnUiThread(new 2(this));
        }

        @JavascriptInterface
        public void app_upload_photo(String str) {
            try {
                dji.thirdparty.a.c.a().e(new a(new JSONObject(str).getString("objectId")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void openInBrowser(String str) {
            try {
                DJIWebviewFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } catch (ActivityNotFoundException e) {
            }
        }

        private void sendLogoutCmdToJS(boolean z) {
            this.mJSWebView.loadUrl("javascript:getLogoutStateFromApp(" + Boolean.toString(z) + ")");
        }
    }

    public static DJIWebviewFragment b(Bundle bundle) {
        DJIWebviewFragment dJIWebviewFragment = new DJIWebviewFragment();
        dJIWebviewFragment.setArguments(bundle);
        return dJIWebviewFragment;
    }

    public static DJIWebviewFragment c(String str) {
        DJIWebviewFragment dJIWebviewFragment = new DJIWebviewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(o, str);
        dJIWebviewFragment.setArguments(bundle);
        return dJIWebviewFragment;
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.v2_fragment_base_webview, null);
        b();
        return inflate;
    }

    protected void a() {
    }

    protected void b() {
        if (getArguments() != null) {
            String string = getArguments().getString(o, null);
            String string2 = getArguments().getString(p, null);
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
            if (string2 == null) {
                b(string);
            } else {
                a(string, string2);
            }
        }
    }

    protected void c() {
        super.c();
        this.db_ = new WebBaseJsHandler(getActivity(), this.de_);
        this.de_.setWebViewClient(new a(this, null));
        this.de_.addJavascriptInterface(this.db_, c.dc_);
    }

    protected void a(View view) {
        this.de_ = (WebView) view.findViewById(R.id.cno);
        this.df_ = (ProgressBar) view.findViewById(R.id.cnp);
        this.dh_ = (DJIRelativeLayout) view.findViewById(R.id.d5a);
        this.di_ = (DJIStateTextView) view.findViewById(R.id.d5b);
        this.dj_ = (DJIImageView) view.findViewById(R.id.d5c);
    }

    public void onEventMainThread(DJIAccountSignFragment.c cVar) {
        this.db_.handleLogin(cVar);
    }
}
