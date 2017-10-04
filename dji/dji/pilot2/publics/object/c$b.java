package dji.pilot2.publics.object;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.j.a;
import dji.log.DJILogHelper;
import dji.pilot.R;

protected class c$b extends WebViewClient {
    final /* synthetic */ c b;

    protected c$b(c cVar) {
        this.b = cVar;
    }

    public boolean shouldOverrideUrlLoading(final WebView webView, String str) {
        if (str.startsWith("tel:")) {
            try {
                this.b.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(str)));
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this.b.getActivity(), R.string.mine_contact_dji_no_call_app, 0).show();
            }
            webView.reload();
            return true;
        } else if (str.startsWith("weixin:")) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            try {
                this.b.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException e2) {
                e2.printStackTrace();
                Toast.makeText(this.b.getActivity(), R.string.v2_repair_wechat_notfound, 0).show();
                return true;
            }
        } else {
            final PayTask payTask = new PayTask(this.b.getActivity());
            final Object fetchOrderInfoFromH5PayUrl = payTask.fetchOrderInfoFromH5PayUrl(str);
            DJILogHelper.getInstance().LOGI("bob", "paytask:::::11" + fetchOrderInfoFromH5PayUrl + "  url=" + str);
            if (TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl)) {
                return false;
            }
            DJILogHelper.getInstance().LOGI("bob", "paytask:::::" + str);
            new Thread(new Runnable(this) {
                final /* synthetic */ c$b d;

                public void run() {
                    DJILogHelper.getInstance().LOGI("bob", "payTask:::" + fetchOrderInfoFromH5PayUrl);
                    final a h5Pay = payTask.h5Pay(fetchOrderInfoFromH5PayUrl, true);
                    if (!TextUtils.isEmpty(h5Pay.a())) {
                        this.d.b.getActivity().runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                webView.loadUrl(h5Pay.a());
                            }
                        });
                    }
                }
            }).start();
            return true;
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        if (this.b.df_ != null) {
            this.b.df_.setVisibility(0);
        }
        this.b.df_.setVisibility(8);
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        c.a(this.b);
        if (this.b.df_ != null) {
            this.b.df_.setVisibility(8);
        }
        c.a(this.b, webView.getTitle());
        DJILogHelper.getInstance().LOGI("bob", "onPageFinished url");
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.b.dh_.show();
        c.a(this.b);
        super.onReceivedError(webView, i, str, str2);
    }
}
