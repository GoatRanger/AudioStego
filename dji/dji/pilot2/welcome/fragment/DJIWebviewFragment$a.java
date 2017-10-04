package dji.pilot2.welcome.fragment;

import android.annotation.SuppressLint;
import android.webkit.WebView;
import dji.pilot.R;
import dji.pilot.flyunlimit.a.d.a;
import dji.pilot.flyunlimit.b;
import dji.pilot.publics.objects.g;
import dji.pilot.usercenter.b.f;
import dji.thirdparty.a.c;

final class DJIWebviewFragment$a extends c$b {
    final /* synthetic */ DJIWebviewFragment a;

    private DJIWebviewFragment$a(DJIWebviewFragment dJIWebviewFragment) {
        this.a = dJIWebviewFragment;
        super(dJIWebviewFragment);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.equals("djinfj://verifyCallBack#1")) {
            this.a.getActivity().finish();
            c.a().e(a.GO_TO_CONFIRM_VIEW);
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }

    @SuppressLint({"NewApi"})
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        if (str.equals(b.c)) {
            String b = g.b(this.a.getActivity(), dji.pilot.flyforbid.a.b, "");
            webView.evaluateJavascript(String.format("var personal_info = {user_id: '%s'}; function callback(verified){if(verified){var url = 'djinfj://verifyCallBack#1'; window.location=url}};setup('%s', '%s', personal_info, callback);", new Object[]{f.getInstance().j(), b, this.a.getActivity().getString(R.string.versionname)}), null);
        }
    }
}
