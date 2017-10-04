package dji.pilot2.main.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.c.a;
import dji.pilot.main.activity.DJISplashActivity;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.io.IOException;
import java.util.Locale;

public class DJILegalAgreement extends DJIBaseActivity {
    public static final String a = "jump_to_main";
    private DJITextView b = null;
    private boolean c = true;
    private OnClickListener d = new 1(this);

    protected void onCreate(Bundle bundle) {
        boolean z = true;
        super.onCreate(bundle);
        setContentView(R.layout.activity_legal_agreement);
        this.b = (DJITextView) findViewById(R.id.h_);
        this.c = getIntent().getBooleanExtra(a, true);
        if (!a.E || Locale.SIMPLIFIED_CHINESE.getLanguage().equals(Locale.getDefault().getLanguage()) || Locale.TRADITIONAL_CHINESE.getLanguage().equals(Locale.getDefault().getLanguage()) || new Locale("ru", "RU").getLanguage().equals(Locale.getDefault().getLanguage())) {
            if (this.c) {
                startActivity(new Intent(this, DJISplashActivity.class));
            }
            finish();
        }
        DJIOriLayout.setOrientationByDevice(this);
        AssetManager assets = getAssets();
        String language = getResources().getConfiguration().locale.getLanguage();
        try {
            for (String compareTo : assets.list("terms")) {
                if (compareTo.compareTo(language) == 0) {
                    break;
                }
            }
            z = false;
            if (!z) {
                language = "en";
            }
            CharSequence a = a(assets.open(String.format("terms/%s/1.txt", new Object[]{language})), "utf-8");
            String a2 = a(assets.open(String.format("terms/%s/2.txt", new Object[]{language})), "utf-8");
            CharSequence a3 = a(assets.open(String.format("terms/%s/3.txt", new Object[]{language})), "utf-8");
            CharSequence spannableString = new SpannableString(a2);
            spannableString.setSpan(new a(this, a2, this), 0, a2.length(), 17);
            this.b.setText(a);
            this.b.append(spannableString);
            this.b.append(a3);
            this.b.setMovementMethod(LinkMovementMethod.getInstance());
        } catch (IOException e) {
            e.printStackTrace();
        }
        findViewById(R.id.f0).setOnClickListener(this.d);
        findViewById(R.id.ha).setOnClickListener(this.d);
    }

    public void onBackPressed() {
        dji.pilot2.publics.a.a.getInstance().b();
        c.a().e(dji.pilot2.publics.a.a.a.exitApp);
        finish();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.io.InputStream r6, java.lang.String r7) {
        /*
        r5 = this;
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r0 = new char[r0];
        if (r7 == 0) goto L_0x000e;
    L_0x0006:
        r1 = "";
        r1 = r7.equals(r1);	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        if (r1 == 0) goto L_0x0010;
    L_0x000e:
        r7 = "utf-8";
    L_0x0010:
        r1 = new java.io.BufferedReader;	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        r2 = new java.io.InputStreamReader;	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        r2.<init>(r6, r7);	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        r1.<init>(r2);	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        r2 = new java.lang.StringBuffer;	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        r2.<init>();	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
    L_0x001f:
        r3 = r1.read(r0);	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        r4 = -1;
        if (r3 == r4) goto L_0x0032;
    L_0x0026:
        r4 = 0;
        r2.append(r0, r4, r3);	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        goto L_0x001f;
    L_0x002b:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x002f:
        r0 = "";
    L_0x0031:
        return r0;
    L_0x0032:
        r0 = r2.toString();	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        goto L_0x0031;
    L_0x0037:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot2.main.activity.DJILegalAgreement.a(java.io.InputStream, java.lang.String):java.lang.String");
    }
}
