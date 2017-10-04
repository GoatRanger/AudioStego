package dji.pilot.countrycode.activity;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.publics.object.b;

public class CountryCodeActivity extends DJIActivityNoFullScreen {
    public static final String a = "title";
    public static final String b = "msg";
    public static boolean c = false;
    public static int d = 0;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_country_code);
        Intent intent = getIntent();
        CharSequence stringExtra = intent.getStringExtra("title");
        CharSequence stringExtra2 = intent.getStringExtra("msg");
        if (!TextUtils.isEmpty(stringExtra)) {
            ((TextView) findViewById(R.id.g8)).setText(stringExtra);
        }
        if (!TextUtils.isEmpty(stringExtra2)) {
            ((TextView) findViewById(R.id.g9)).setText(stringExtra2);
        }
        a();
    }

    private void a() {
        findViewById(R.id.g_).setOnClickListener(new 1(this));
    }

    @Deprecated
    private void b() {
        if (!c) {
            d++;
            Builder bVar = new b(this, R.style.hk);
            bVar.setTitle("CNT = " + d);
            bVar.setMessage(R.string.v2_country_code_fail_tip_wm220);
            bVar.setPositiveButton(R.string.app_ok, new 2(this));
            bVar.show();
            c = true;
        }
    }
}
