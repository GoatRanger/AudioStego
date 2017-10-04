package cn.sharesdk.tencent.qzone;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.RegisterView;
import com.mob.tools.FakeActivity;
import com.mob.tools.MobUIShell;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import com.sina.weibo.sdk.constant.WBConstants;
import java.util.List;

public class d extends FakeActivity {
    private String a;
    private boolean b;
    private PlatformActionListener c;
    private RegisterView d;
    private WebView e;
    private String f;
    private boolean g;
    private boolean h;
    private QZoneWebShareAdapter i;

    public void a(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public void a(PlatformActionListener platformActionListener) {
        this.c = platformActionListener;
    }

    public void a(String str) {
        this.f = "tencent" + str;
    }

    public void setActivity(Activity activity) {
        super.setActivity(activity);
        if (this.i == null) {
            this.i = b();
            if (this.i == null) {
                this.i = new QZoneWebShareAdapter();
            }
        }
        this.i.setActivity(activity);
    }

    private QZoneWebShareAdapter b() {
        try {
            String string = this.activity.getPackageManager().getActivityInfo(this.activity.getComponentName(), 128).metaData.getString("QZoneWebShareAdapter");
            if (string == null || string.length() <= 0) {
                return null;
            }
            Object newInstance = Class.forName(string).newInstance();
            if (newInstance instanceof QZoneWebShareAdapter) {
                return (QZoneWebShareAdapter) newInstance;
            }
            return null;
        } catch (Throwable th) {
            cn.sharesdk.framework.utils.d.a().d(th);
            return null;
        }
    }

    public void onCreate() {
        Intent intent = this.activity.getIntent();
        String scheme = intent.getScheme();
        if (scheme != null && scheme.startsWith(this.f)) {
            finish();
            Bundle urlToBundle = R.urlToBundle(intent.getDataString());
            scheme = String.valueOf(urlToBundle.get("result"));
            String valueOf = String.valueOf(urlToBundle.get("action"));
            if ("shareToQQ".equals(valueOf) || "shareToQzone".equals(valueOf)) {
                if ("complete".equals(scheme)) {
                    if (this.c != null) {
                        this.c.onComplete(null, 9, new Hashon().fromJson(String.valueOf(urlToBundle.get("response"))));
                    }
                } else if ("error".equals(scheme)) {
                    if (this.c != null) {
                        this.c.onError(null, 9, new Throwable(String.valueOf(urlToBundle.get("response"))));
                    }
                } else if (this.c != null) {
                    this.c.onCancel(null, 9);
                }
            }
            intent = new Intent("android.intent.action.VIEW");
            intent.setClass(this.activity, MobUIShell.class);
            intent.setFlags(335544320);
            startActivity(intent);
        } else if (this.b) {
            c();
        } else {
            d();
        }
    }

    private void c() {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(this.a));
            intent.putExtra("pkg_name", this.activity.getPackageName());
            if (VERSION.SDK_INT >= 11) {
                intent.setFlags(268468224);
            }
            this.activity.startActivity(intent);
            FakeActivity.registerExecutor(this.f, this);
            finish();
        } catch (Throwable th) {
            if (this.c != null) {
                this.c.onError(null, 0, th);
            }
        }
    }

    private void d() {
        this.d = a();
        try {
            int stringRes = R.getStringRes(getContext(), "ssdk_share_to_qzone");
            if (stringRes > 0) {
                this.d.c().getTvTitle().setText(stringRes);
            }
        } catch (Throwable th) {
            cn.sharesdk.framework.utils.d.a().d(th);
            this.d.c().setVisibility(8);
        }
        this.i.setBodyView(this.d.d());
        this.i.setWebView(this.d.b());
        this.i.setTitleView(this.d.c());
        this.i.onCreate();
        this.activity.setContentView(this.d);
        if ("none".equals(DeviceHelper.getInstance(this.activity).getDetailNetworkTypeForStatic())) {
            this.h = true;
            finish();
            this.c.onError(null, 0, new Throwable("failed to load webpage, network disconnected."));
            return;
        }
        this.d.b().loadUrl(this.a);
    }

    protected RegisterView a() {
        RegisterView registerView = new RegisterView(this.activity);
        registerView.c().getChildAt(registerView.c().getChildCount() - 1).setVisibility(8);
        registerView.a().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

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
                            cn.sharesdk.framework.utils.d.a().d(th);
                            this.a.a.finish();
                            this.a.a.c.onCancel(null, 0);
                        }
                    }
                }.start();
            }
        });
        this.e = registerView.b();
        WebSettings settings = this.e.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(1);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(this.activity.getDir("database", 0).getPath());
        settings.setSavePassword(false);
        this.e.setVerticalScrollBarEnabled(false);
        this.e.setHorizontalScrollBarEnabled(false);
        this.e.setWebViewClient(new cn.sharesdk.framework.d(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str != null && str.startsWith(this.a.f)) {
                    this.a.b(str);
                } else if (str != null && str.startsWith("mqzone://")) {
                    this.a.c(str);
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }
        });
        return registerView;
    }

    private void b(String str) {
        String str2 = str == null ? "" : new String(str);
        Bundle urlToBundle = R.urlToBundle(str);
        if (urlToBundle == null) {
            this.h = true;
            finish();
            this.c.onError(null, 0, new Throwable("failed to parse callback uri: " + str2));
            return;
        }
        String string = urlToBundle.getString("action");
        if (WBConstants.ACTION_LOG_TYPE_SHARE.equals(string) || "shareToQzone".equals(string)) {
            string = urlToBundle.getString("result");
            if ("cancel".equals(string)) {
                finish();
                this.c.onCancel(null, 0);
                return;
            } else if ("complete".equals(string)) {
                Object string2 = urlToBundle.getString("response");
                if (TextUtils.isEmpty(string2)) {
                    this.h = true;
                    finish();
                    this.c.onError(null, 0, new Throwable("response empty" + str2));
                    return;
                }
                this.g = true;
                finish();
                this.c.onComplete(null, 0, new Hashon().fromJson(string2));
                return;
            } else {
                this.h = true;
                finish();
                this.c.onError(null, 0, new Throwable("operation failed: " + str2));
                return;
            }
        }
        this.h = true;
        finish();
        this.c.onError(null, 0, new Throwable("action error: " + str2));
    }

    private void c(String str) {
        List queryIntentActivities;
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        try {
            queryIntentActivities = this.activity.getPackageManager().queryIntentActivities(intent, 1);
        } catch (Throwable th) {
            cn.sharesdk.framework.utils.d.a().d(th);
            queryIntentActivities = null;
        }
        if (queryIntentActivities != null && queryIntentActivities.size() > 0) {
            startActivity(intent);
        }
    }

    public void onStart() {
        if (this.i != null) {
            this.i.onStart();
        }
    }

    public void onPause() {
        if (this.i != null) {
            this.i.onPause();
        }
    }

    public void onResume() {
        if (this.i != null) {
            this.i.onResume();
        }
    }

    public void onStop() {
        if (this.i != null) {
            this.i.onStop();
        }
    }

    public void onRestart() {
        if (this.i != null) {
            this.i.onRestart();
        }
    }

    public void onDestroy() {
        if (!(this.b || this.h || this.g)) {
            this.c.onCancel(null, 0);
        }
        if (this.i != null) {
            this.i.onDestroy();
        }
    }

    public boolean onFinish() {
        if (this.i != null) {
            return this.i.onFinish();
        }
        return super.onFinish();
    }
}
