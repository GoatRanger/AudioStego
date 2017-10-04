package dji.pilot2.publics.object;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ProgressBar;
import android.widget.Toast;
import dji.pilot.R;
import dji.pilot.publics.objects.g;
import dji.pilot.publics.widget.DJIScrollTextView;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.main.activity.DJIMainFragmentActivity;
import dji.pilot2.mine.activity.WebActivity;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.pilot2.widget.InhaleRelativeLayout;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lecho.lib.hellocharts.model.l;

public class DJINotificationDialog extends dji.pilot.publics.objects.c {
    public static boolean a = false;
    public static boolean b;
    private InhaleRelativeLayout c = null;
    private DJIImageView d = null;
    private ProgressBar e = null;
    private WebView f = null;
    private DJIScrollTextView g = null;
    private CheckBox h;
    private DJITextView i;
    private String j = "";
    private String k;
    private String l;

    public class WebviewJsHandler {
        @JavascriptInterface
        public void redirect_to(String str) {
            Toast.makeText(DJINotificationDialog.this.N, "notify " + str, 0).show();
            ((Activity) DJINotificationDialog.this.N).runOnUiThread(new 1(this, str));
        }
    }

    private final class a extends WebChromeClient {
        final /* synthetic */ DJINotificationDialog a;

        private a(DJINotificationDialog dJINotificationDialog) {
            this.a = dJINotificationDialog;
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            this.a.e.setProgress(i);
        }
    }

    private final class b extends WebViewClient {
        final /* synthetic */ DJINotificationDialog a;

        private b(DJINotificationDialog dJINotificationDialog) {
            this.a = dJINotificationDialog;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            this.a.f.loadUrl(str);
            return true;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.a.e.setVisibility(0);
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            this.a.e.setVisibility(8);
        }
    }

    public class c extends ClickableSpan {
        String a;
        final /* synthetic */ DJINotificationDialog b;

        public c(DJINotificationDialog dJINotificationDialog, String str) {
            this.b = dJINotificationDialog;
            this.a = str;
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(this.b.N.getResources().getColor(R.color.aw));
            textPaint.setUnderlineText(false);
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.b.N, WebActivity.class);
            intent.putExtra(DJIWebviewFragment.o, this.a);
            this.b.N.startActivity(intent);
        }
    }

    public DJINotificationDialog(Context context) {
        super(context);
        b = false;
        setContentView(R.layout.v2_dialog_notification);
        d();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void c() {
        WebSettings settings = this.f.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setPluginState(PluginState.ON);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        this.f.setWebChromeClient(new a());
        this.f.setWebViewClient(new b());
        this.f.addJavascriptInterface(new WebviewJsHandler(), c.dc_);
    }

    private void d() {
        this.c = (InhaleRelativeLayout) findViewById(R.id.cjj);
        this.h = (CheckBox) findViewById(R.id.cjm);
        this.d = (DJIImageView) findViewById(R.id.sl);
        this.i = (DJITextView) findViewById(R.id.cjl);
        this.e = (ProgressBar) findViewById(R.id.cjo);
        this.f = (WebView) findViewById(R.id.cjn);
        this.g = (DJIScrollTextView) findViewById(R.id.cjk);
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJINotificationDialog a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.l != null) {
                    Intent intent = new Intent(this.a.N, DJISupportShareWebviewActivity.class);
                    intent.putExtra(DJIWebviewFragment.o, this.a.l);
                    this.a.N.startActivity(intent);
                }
            }
        });
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJINotificationDialog a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int i = DJIMainFragmentActivity.R;
                if (i == 0) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    this.a.getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    i = (int) (((float) displayMetrics.heightPixels) - this.a.N.getResources().getDimension(R.dimen.o));
                }
                this.a.c.setEndPosition((float) ((this.a.c.getWidth() * 5) / 8), ((float) i) + (this.a.getContext().getResources().getDimension(R.dimen.o) / 2.0f));
                this.a.c.setOnInhaleFinishListener(new 1(this));
                this.a.c.inhale();
            }
        });
        this.h.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ DJINotificationDialog a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    DJINotificationDialog.a = true;
                } else {
                    DJINotificationDialog.a = false;
                }
                g.a(this.a.N, "tips", DJINotificationDialog.a);
            }
        });
        if (a) {
            this.h.setChecked(true);
        }
    }

    public void b() {
        if (this.l != null) {
            this.i.show();
        } else {
            this.i.go();
        }
        if ("".equals(this.k)) {
            dismiss();
        } else if (!this.j.equals(this.k)) {
            this.j = this.k;
            Log.i("zyx", "content :" + this.j);
            Matcher matcher = Pattern.compile("(http|https)://[0-9A-Za-z:/[-]_#[?][=][.][&]]*").matcher(this.k);
            this.g.setText("");
            String str = new String();
            while (matcher.find()) {
                str = matcher.group(0);
                if (this.g.length() != this.k.indexOf(str)) {
                    this.g.append(this.k.subSequence(this.g.length(), this.k.indexOf(str)));
                }
                CharSequence spannableString = new SpannableString(str);
                spannableString.setSpan(new c(this, str), 0, str.length(), 33);
                this.g.append(spannableString, 0, str.length());
            }
            if (this.g.length() != 0) {
                if (this.g.length() != this.k.length() && this.g.length() < this.k.length()) {
                    this.g.append(this.k.subSequence(this.g.length(), this.k.length()));
                }
                this.g.append("\n");
                this.g.setMovementMethod(LinkMovementMethod.getInstance());
                return;
            }
            this.g.setText(this.k);
        }
    }

    public void show() {
        b = true;
        b();
        getWindow().setGravity(17);
        super.show();
    }

    public void dismiss() {
        b = false;
        super.dismiss();
    }

    public DJINotificationDialog(Context context, String str) {
        super(context, false);
        this.k = str;
        setContentView(R.layout.v2_dialog_notification);
        d();
    }

    public void a(String str) {
        this.k = str;
    }

    public void b(String str) {
        this.l = str;
    }

    protected void onCreate(Bundle bundle) {
        a((int) this.N.getResources().getDimension(R.dimen.gb), (int) (((float) this.N.getResources().getDisplayMetrics().heightPixels) * l.n), 0, 17, true, true);
        a(0.4f);
        getWindow().setLayout(-1, -1);
    }

    protected void onStart() {
        super.onStart();
        g();
        e();
    }

    protected void onStop() {
        f();
        h();
        super.onStop();
    }

    protected boolean a() {
        return true;
    }

    public void onDetachedFromWindow() {
        i();
        super.onDetachedFromWindow();
    }

    private void e() {
        try {
            this.f.resumeTimers();
        } catch (Exception e) {
        }
    }

    private void f() {
        try {
            this.f.pauseTimers();
        } catch (Exception e) {
        }
    }

    private void g() {
        try {
            this.f.onResume();
        } catch (Exception e) {
        }
    }

    private void h() {
        try {
            this.f.onPause();
        } catch (Exception e) {
        }
    }

    private void i() {
        try {
            this.f.destroy();
        } catch (Exception e) {
        }
    }
}
