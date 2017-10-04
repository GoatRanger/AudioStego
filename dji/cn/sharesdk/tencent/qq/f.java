package cn.sharesdk.tencent.qq;

import android.app.Activity;
import android.app.Instrumentation;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.RegisterView;
import cn.sharesdk.framework.utils.d;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import com.sina.weibo.sdk.constant.WBConstants;

public class f extends FakeActivity {
    private String a;
    private PlatformActionListener b;
    private String c;
    private QQWebShareAdapter d;
    private RegisterView e;
    private WebView f;
    private boolean g;
    private boolean h;

    public void a(String str) {
        this.a = str;
    }

    public void a(PlatformActionListener platformActionListener) {
        this.b = platformActionListener;
    }

    public void b(String str) {
        this.c = "tencent" + str;
    }

    public void setActivity(Activity activity) {
        super.setActivity(activity);
        if (this.d == null) {
            this.d = b();
            if (this.d == null) {
                this.d = new QQWebShareAdapter();
            }
        }
        this.d.setActivity(activity);
    }

    private QQWebShareAdapter b() {
        try {
            String string = this.activity.getPackageManager().getActivityInfo(this.activity.getComponentName(), 128).metaData.getString("QQWebShareAdapter");
            if (string == null || string.length() <= 0) {
                return null;
            }
            Object newInstance = Class.forName(string).newInstance();
            if (newInstance instanceof QQWebShareAdapter) {
                return (QQWebShareAdapter) newInstance;
            }
            return null;
        } catch (Throwable th) {
            d.a().d(th);
            return null;
        }
    }

    public void onCreate() {
        this.e = a();
        try {
            int stringRes = R.getStringRes(getContext(), "ssdk_share_to_qq");
            if (stringRes > 0) {
                this.e.c().getTvTitle().setText(stringRes);
            }
        } catch (Throwable th) {
            d.a().d(th);
            this.e.c().setVisibility(8);
        }
        this.d.setBodyView(this.e.d());
        this.d.setWebView(this.e.b());
        this.d.setTitleView(this.e.c());
        this.d.onCreate();
        this.activity.setContentView(this.e);
        if ("none".equals(DeviceHelper.getInstance(this.activity).getDetailNetworkTypeForStatic())) {
            this.g = true;
            finish();
            this.b.onError(null, 0, new Throwable("failed to load webpage, network disconnected."));
            return;
        }
        this.e.b().loadUrl(this.a);
    }

    protected RegisterView a() {
        RegisterView registerView = new RegisterView(this.activity);
        registerView.c().getChildAt(registerView.c().getChildCount() - 1).setVisibility(8);
        registerView.a().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                new Thread(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            new Instrumentation().sendKeyDownUpSync(4);
                        } catch (Throwable th) {
                            d.a().d(th);
                            this.a.a.finish();
                            this.a.a.b.onCancel(null, 0);
                        }
                    }
                }.start();
            }
        });
        this.f = registerView.b();
        WebSettings settings = this.f.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(1);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setSavePassword(false);
        settings.setDatabasePath(this.activity.getDir("database", 0).getPath());
        this.f.setVerticalScrollBarEnabled(false);
        this.f.setHorizontalScrollBarEnabled(false);
        this.f.setWebViewClient(new cn.sharesdk.framework.d(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str != null && str.startsWith(this.a.c)) {
                    this.a.c(str);
                } else if (str != null && str.startsWith("http://www.myapp.com/down/")) {
                    this.a.h = true;
                } else if (str != null && str.startsWith("wtloginmqq://")) {
                    int stringRes = R.getStringRes(this.a.activity, "ssdk_use_login_button");
                    if (stringRes <= 0) {
                        return true;
                    }
                    Toast.makeText(this.a.activity, stringRes, 0).show();
                    return true;
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                if (str == null || !str.startsWith("wtloginmqq://")) {
                    super.onPageStarted(webView, str, bitmap);
                    return;
                }
                int stringRes = R.getStringRes(this.a.activity, "ssdk_use_login_button");
                if (stringRes > 0) {
                    Toast.makeText(this.a.activity, stringRes, 0).show();
                }
            }
        });
        return registerView;
    }

    private void c(String str) {
        String str2 = str == null ? "" : new String(str);
        Bundle urlToBundle = R.urlToBundle(str);
        if (urlToBundle == null) {
            this.g = true;
            finish();
            this.b.onError(null, 0, new Throwable("failed to parse callback uri: " + str2));
            return;
        }
        String string = urlToBundle.getString("action");
        if (WBConstants.ACTION_LOG_TYPE_SHARE.equals(string) || "shareToQQ".equals(string)) {
            string = urlToBundle.getString("result");
            if ("cancel".equals(string)) {
                finish();
                this.b.onCancel(null, 0);
                return;
            } else if ("complete".equals(string)) {
                Object string2 = urlToBundle.getString("response");
                if (TextUtils.isEmpty(string2)) {
                    this.g = true;
                    finish();
                    this.b.onError(null, 0, new Throwable("response empty" + str2));
                    return;
                }
                this.h = true;
                finish();
                this.b.onComplete(null, 0, new Hashon().fromJson(string2));
                return;
            } else {
                this.g = true;
                finish();
                this.b.onError(null, 0, new Throwable("operation failed: " + str2));
                return;
            }
        }
        this.g = true;
        finish();
        this.b.onError(null, 0, new Throwable("action error: " + str2));
    }

    public void onStart() {
        if (this.d != null) {
            this.d.onStart();
        }
    }

    public void onPause() {
        if (this.d != null) {
            this.d.onPause();
        }
    }

    public void onResume() {
        if (this.d != null) {
            this.d.onResume();
        }
    }

    public void onStop() {
        if (this.d != null) {
            this.d.onStop();
        }
    }

    public void onRestart() {
        if (this.d != null) {
            this.d.onRestart();
        }
    }

    public void onDestroy() {
        if (!(this.g || this.h)) {
            this.b.onCancel(null, 0);
        }
        if (this.d != null) {
            this.d.onDestroy();
        }
    }

    public boolean onFinish() {
        if (this.d != null) {
            return this.d.onFinish();
        }
        return super.onFinish();
    }
}
