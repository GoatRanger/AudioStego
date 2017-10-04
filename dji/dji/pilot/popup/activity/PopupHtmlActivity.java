package dji.pilot.popup.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import dji.pilot.R;

public class PopupHtmlActivity extends Activity {
    public static String a = "";
    private WebView b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_html_activity);
        a();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStart() {
        super.onStart();
    }

    private void a() {
        this.b = (WebView) findViewById(R.id.bj0);
        this.b.setWebViewClient(new WebViewClient(this) {
            final /* synthetic */ PopupHtmlActivity a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }
        });
        this.b.loadUrl(a);
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
