package dji.pilot2.mine.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.databinding.k;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.e.a;
import dji.pilot.main.activity.DJILauncherActivity;
import dji.pilot.publics.objects.g;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.publics.b.a$j;
import java.util.Locale;

public class LanguageSettingActivity extends DJIActivityNoFullScreen implements OnClickListener {
    public static final String a = "mineLanguageKey";
    public static final String b = "auto";
    private a c;
    private String d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_language_setting);
        this.d = g.b(getApplicationContext(), a, "auto");
        this.c = (a) k.a(this, R.layout.activity_language_setting);
        this.c.d.setOnClickListener(this);
        this.c.f.setOnClickListener(this);
        this.c.i.setOnClickListener(this);
        this.c.l.setOnClickListener(this);
        this.c.o.setOnClickListener(this);
        this.c.n.setOnClickListener(this);
        if (this.d.equals("auto")) {
            this.c.e.setVisibility(0);
        } else if (this.d.equals("cn")) {
            this.c.m.setVisibility(0);
        } else if (this.d.equals("tw")) {
            this.c.p.setVisibility(0);
        } else if (this.d.equals(a$j.e)) {
            this.c.j.setVisibility(0);
        } else if (this.d.equals("en")) {
            this.c.g.setVisibility(0);
        }
        this.c.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LanguageSettingActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(this.a.d);
                Intent intent = new Intent(this.a, DJILauncherActivity.class);
                intent.setFlags(268468224);
                intent.putExtra(DJILauncherActivity.b, true);
                this.a.startActivity(intent);
            }
        });
    }

    protected void a(String str) {
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (str.equals("en")) {
            configuration.locale = Locale.ENGLISH;
            Locale.setDefault(Locale.ENGLISH);
        } else if (str.equals("cn")) {
            configuration.locale = Locale.SIMPLIFIED_CHINESE;
            Locale.setDefault(Locale.ENGLISH);
        } else if (str.equals(a$j.e)) {
            configuration.locale = Locale.JAPAN;
            Locale.setDefault(Locale.ENGLISH);
        } else if (str.equals("tw")) {
            configuration.locale = Locale.TRADITIONAL_CHINESE;
            Locale.setDefault(Locale.ENGLISH);
        } else {
            configuration.locale = Resources.getSystem().getConfiguration().locale;
            str = "auto";
        }
        resources.updateConfiguration(configuration, displayMetrics);
        g.a(getApplicationContext(), a, str);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.gy) {
            finish();
            return;
        }
        this.c.e.setVisibility(4);
        this.c.g.setVisibility(4);
        this.c.j.setVisibility(4);
        this.c.m.setVisibility(4);
        this.c.p.setVisibility(4);
        switch (id) {
            case R.id.h0:
                this.d = "auto";
                this.c.e.setVisibility(0);
                return;
            case R.id.h2:
                this.d = "cn";
                this.c.m.setVisibility(0);
                return;
            case R.id.h4:
                this.d = "tw";
                this.c.p.setVisibility(0);
                return;
            case R.id.h6:
                this.d = "en";
                this.c.g.setVisibility(0);
                return;
            case R.id.h8:
                this.d = a$j.e;
                this.c.j.setVisibility(0);
                return;
            default:
                return;
        }
    }
}
