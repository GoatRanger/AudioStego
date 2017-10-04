package cn.sharesdk.facebook;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.RegisterView;
import cn.sharesdk.framework.utils.d;
import com.facebook.internal.ab;
import com.facebook.internal.af;
import com.facebook.share.internal.n;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.R;
import java.util.HashMap;

public class e extends FakeActivity {
    private String a;
    private PlatformActionListener b;
    private a c;
    private RegisterView d;
    private WebView e;
    private boolean f;
    private boolean g;

    public void a(String str) {
        this.a = str;
    }

    public void a(PlatformActionListener platformActionListener) {
        this.b = platformActionListener;
    }

    public void setActivity(Activity activity) {
        super.setActivity(activity);
        if (this.c == null) {
            this.c = b();
            if (this.c == null) {
                this.c = new a();
            }
        }
        this.c.a(activity);
    }

    private a b() {
        try {
            String string = this.activity.getPackageManager().getActivityInfo(this.activity.getComponentName(), 128).metaData.getString("FBWebShareAdapter");
            if (string == null || string.length() <= 0) {
                return null;
            }
            Object newInstance = Class.forName(string).newInstance();
            if (newInstance instanceof a) {
                return (a) newInstance;
            }
            return null;
        } catch (Throwable th) {
            d.a().d(th);
            return null;
        }
    }

    public void onCreate() {
        this.d = a();
        try {
            int stringRes = R.getStringRes(getContext(), "ssdk_share_to_facebook");
            if (stringRes > 0) {
                this.d.c().getTvTitle().setText(stringRes);
            }
        } catch (Throwable th) {
            d.a().d(th);
            this.d.c().setVisibility(8);
        }
        this.c.a(this.d.d());
        this.c.a(this.d.b());
        this.c.a(this.d.c());
        this.c.a();
        this.activity.setContentView(this.d);
        if ("none".equals(DeviceHelper.getInstance(this.activity).getDetailNetworkTypeForStatic())) {
            this.f = true;
            finish();
            this.b.onError(null, 0, new Throwable("failed to load webpage, network disconnected."));
            return;
        }
        this.d.b().loadUrl(this.a);
    }

    protected RegisterView a() {
        RegisterView registerView = new RegisterView(this.activity);
        registerView.c().getChildAt(registerView.c().getChildCount() - 1).setVisibility(8);
        registerView.a().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e a;

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
        this.e = registerView.b();
        WebSettings settings = this.e.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(1);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setSavePassword(false);
        settings.setDatabasePath(this.activity.getDir("database", 0).getPath());
        this.e.setVerticalScrollBarEnabled(false);
        this.e.setHorizontalScrollBarEnabled(false);
        this.e.setWebViewClient(new cn.sharesdk.framework.d(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str != null && str.startsWith(af.t)) {
                    this.a.b(str);
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
            this.f = true;
            finish();
            this.b.onError(null, 0, new Throwable("failed to parse callback uri: " + str2));
            return;
        }
        CharSequence string = urlToBundle.getString(n.q);
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(string)) {
            hashMap.put(n.q, string);
        }
        if (urlToBundle.containsKey(ab.an) || urlToBundle.containsKey("error")) {
            if (this.b != null) {
                this.b.onError(null, 9, new Throwable(R.encodeUrl(urlToBundle)));
            }
            this.f = true;
            finish();
            return;
        }
        this.g = true;
        finish();
        this.b.onComplete(null, 0, hashMap);
    }

    public void onStart() {
        if (this.c != null) {
            this.c.c();
        }
    }

    public void onPause() {
        if (this.c != null) {
            this.c.d();
        }
    }

    public void onResume() {
        if (this.c != null) {
            this.c.e();
        }
    }

    public void onStop() {
        if (this.c != null) {
            this.c.f();
        }
    }

    public void onRestart() {
        if (this.c != null) {
            this.c.g();
        }
    }

    public void onDestroy() {
        if (!(this.f || this.g)) {
            this.b.onCancel(null, 0);
        }
        if (this.c != null) {
            this.c.b();
        }
    }

    public boolean onFinish() {
        if (this.c != null) {
            return this.c.h();
        }
        return super.onFinish();
    }
}
