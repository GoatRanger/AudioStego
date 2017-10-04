package com.facebook.internal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.AccessToken;
import com.facebook.R;
import com.facebook.j;
import com.facebook.k;
import com.facebook.m;
import com.facebook.n;
import com.facebook.o;
import com.facebook.s;
import dji.gs.c.e;
import dji.pilot.usercenter.protocol.d;
import java.util.Locale;

public class aj extends Dialog {
    static final String a = "fbconnect://success";
    static final String b = "fbconnect://cancel";
    static final boolean c = false;
    public static final int d = 16973840;
    private static final String e = "FacebookSDK.WebDialog";
    private static final String f = "touch";
    private static final int g = 4201;
    private static final int h = 480;
    private static final int i = 800;
    private static final int j = 800;
    private static final int k = 1280;
    private static final double l = 0.5d;
    private static final int m = -872415232;
    private String n;
    private String o;
    private c p;
    private WebView q;
    private ProgressDialog r;
    private ImageView s;
    private FrameLayout t;
    private boolean u;
    private boolean v;
    private boolean w;

    public static class a {
        private Context a;
        private String b;
        private String c;
        private int d;
        private c e;
        private Bundle f;
        private AccessToken g;

        public a(Context context, String str, Bundle bundle) {
            this.g = AccessToken.a();
            if (this.g == null) {
                String a = ah.a(context);
                if (a != null) {
                    this.b = a;
                } else {
                    throw new k("Attempted to create a builder without a valid access token or a valid default Application ID.");
                }
            }
            a(context, str, bundle);
        }

        public a(Context context, String str, String str2, Bundle bundle) {
            if (str == null) {
                str = ah.a(context);
            }
            ai.a(str, "applicationId");
            this.b = str;
            a(context, str2, bundle);
        }

        public a a(int i) {
            this.d = i;
            return this;
        }

        public a a(c cVar) {
            this.e = cVar;
            return this;
        }

        public aj a() {
            if (this.g != null) {
                this.f.putString("app_id", this.g.i());
                this.f.putString("access_token", this.g.c());
            } else {
                this.f.putString("app_id", this.b);
            }
            return new aj(this.a, this.c, this.f, this.d, this.e);
        }

        public String b() {
            return this.b;
        }

        public Context c() {
            return this.a;
        }

        public int d() {
            return this.d;
        }

        public Bundle e() {
            return this.f;
        }

        public c f() {
            return this.e;
        }

        private void a(Context context, String str, Bundle bundle) {
            this.a = context;
            this.c = str;
            if (bundle != null) {
                this.f = bundle;
            } else {
                this.f = new Bundle();
            }
        }
    }

    private class b extends WebViewClient {
        final /* synthetic */ aj a;

        private b(aj ajVar) {
            this.a = ajVar;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            ah.c(aj.e, "Redirect URL: " + str);
            if (str.startsWith(this.a.o)) {
                int i;
                Bundle a = this.a.a(str);
                String string = a.getString("error");
                if (string == null) {
                    string = a.getString(ab.al);
                }
                String string2 = a.getString("error_msg");
                if (string2 == null) {
                    string2 = a.getString(a.X);
                }
                if (string2 == null) {
                    string2 = a.getString(ab.am);
                }
                String string3 = a.getString(ab.an);
                if (ah.a(string3)) {
                    i = -1;
                } else {
                    try {
                        i = Integer.parseInt(string3);
                    } catch (NumberFormatException e) {
                        i = -1;
                    }
                }
                if (ah.a(string) && ah.a(string2) && i == -1) {
                    this.a.a(a);
                } else if (string != null && (string.equals("access_denied") || string.equals("OAuthAccessDeniedException"))) {
                    this.a.cancel();
                } else if (i == aj.g) {
                    this.a.cancel();
                } else {
                    this.a.a(new s(new n(i, string, string2), string2));
                }
                return true;
            } else if (str.startsWith("fbconnect://cancel")) {
                this.a.cancel();
                return true;
            } else if (str.contains("touch")) {
                return false;
            } else {
                try {
                    this.a.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                } catch (ActivityNotFoundException e2) {
                    return false;
                }
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            this.a.a(new j(str, i, str2));
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            sslErrorHandler.cancel();
            this.a.a(new j(null, -11, null));
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            ah.c(aj.e, "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
            if (!this.a.v) {
                this.a.r.show();
            }
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (!this.a.v) {
                this.a.r.dismiss();
            }
            this.a.t.setBackgroundColor(0);
            this.a.q.setVisibility(0);
            this.a.s.setVisibility(0);
            this.a.w = true;
        }
    }

    public interface c {
        void a(Bundle bundle, k kVar);
    }

    public aj(Context context, String str) {
        this(context, str, o.n());
    }

    public aj(Context context, String str, int i) {
        if (i == 0) {
            i = o.n();
        }
        super(context, i);
        this.o = "fbconnect://success";
        this.u = false;
        this.v = false;
        this.w = false;
        this.n = str;
    }

    public aj(Context context, String str, Bundle bundle, int i, c cVar) {
        if (i == 0) {
            i = o.n();
        }
        super(context, i);
        this.o = "fbconnect://success";
        this.u = false;
        this.v = false;
        this.w = false;
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("redirect_uri", "fbconnect://success");
        bundle.putString("display", "touch");
        bundle.putString(af.o, String.format(Locale.ROOT, "android-%s", new Object[]{o.i()}));
        this.n = ah.a(af.a(), af.d() + d.t + af.a + str, bundle).toString();
        this.p = cVar;
    }

    public void a(c cVar) {
        this.p = cVar;
    }

    public c a() {
        return this.p;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            cancel();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void dismiss() {
        if (this.q != null) {
            this.q.stopLoading();
        }
        if (!(this.v || this.r == null || !this.r.isShowing())) {
            this.r.dismiss();
        }
        super.dismiss();
    }

    protected void onStart() {
        super.onStart();
        e();
    }

    public void onDetachedFromWindow() {
        this.v = true;
        super.onDetachedFromWindow();
    }

    public void onAttachedToWindow() {
        this.v = false;
        super.onAttachedToWindow();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.r = new ProgressDialog(getContext());
        this.r.requestWindowFeature(1);
        this.r.setMessage(getContext().getString(R.string.com_facebook_loading));
        this.r.setCanceledOnTouchOutside(false);
        this.r.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ aj a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.cancel();
            }
        });
        requestWindowFeature(1);
        this.t = new FrameLayout(getContext());
        e();
        getWindow().setGravity(17);
        getWindow().setSoftInputMode(16);
        f();
        a((this.s.getDrawable().getIntrinsicWidth() / 2) + 1);
        this.t.addView(this.s, new LayoutParams(-2, -2));
        setContentView(this.t);
    }

    protected void b(String str) {
        this.o = str;
    }

    protected Bundle a(String str) {
        Uri parse = Uri.parse(str);
        Bundle d = ah.d(parse.getQuery());
        d.putAll(ah.d(parse.getFragment()));
        return d;
    }

    protected boolean b() {
        return this.u;
    }

    protected boolean c() {
        return this.w;
    }

    protected WebView d() {
        return this.q;
    }

    public void e() {
        Display defaultDisplay = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        getWindow().setLayout(Math.min(a(displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels, displayMetrics.density, h, e.g), displayMetrics.widthPixels), Math.min(a(displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.heightPixels : displayMetrics.widthPixels, displayMetrics.density, e.g, 1280), displayMetrics.heightPixels));
    }

    private int a(int i, float f, int i2, int i3) {
        double d = 0.5d;
        int i4 = (int) (((float) i) / f);
        if (i4 <= i2) {
            d = 1.0d;
        } else if (i4 < i3) {
            d = 0.5d + ((((double) (i3 - i4)) / ((double) (i3 - i2))) * 0.5d);
        }
        return (int) (d * ((double) i));
    }

    protected void a(Bundle bundle) {
        if (this.p != null && !this.u) {
            this.u = true;
            this.p.a(bundle, null);
            dismiss();
        }
    }

    protected void a(Throwable th) {
        if (this.p != null && !this.u) {
            this.u = true;
            if (th instanceof k) {
                th = (k) th;
            } else {
                th = new k(th);
            }
            this.p.a(null, th);
            dismiss();
        }
    }

    public void cancel() {
        if (this.p != null && !this.u) {
            a(new m());
        }
    }

    private void f() {
        this.s = new ImageView(getContext());
        this.s.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ aj a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.cancel();
            }
        });
        this.s.setImageDrawable(getContext().getResources().getDrawable(R.drawable.com_facebook_close));
        this.s.setVisibility(4);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void a(int i) {
        View linearLayout = new LinearLayout(getContext());
        this.q = new WebDialog$3(this, getContext().getApplicationContext());
        this.q.setVerticalScrollBarEnabled(false);
        this.q.setHorizontalScrollBarEnabled(false);
        this.q.setWebViewClient(new b());
        this.q.getSettings().setJavaScriptEnabled(true);
        this.q.loadUrl(this.n);
        this.q.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.q.setVisibility(4);
        this.q.getSettings().setSavePassword(false);
        this.q.getSettings().setSaveFormData(false);
        this.q.setFocusable(true);
        this.q.setFocusableInTouchMode(true);
        this.q.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ aj a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!view.hasFocus()) {
                    view.requestFocus();
                }
                return false;
            }
        });
        linearLayout.setPadding(i, i, i, i);
        linearLayout.addView(this.q);
        linearLayout.setBackgroundColor(m);
        this.t.addView(linearLayout);
    }
}
