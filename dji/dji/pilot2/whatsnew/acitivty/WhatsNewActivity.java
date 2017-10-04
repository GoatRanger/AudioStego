package dji.pilot2.whatsnew.acitivty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.usercenter.protocol.c;
import dji.pilot.usercenter.protocol.e;
import dji.pilot2.main.activity.DJILegalAgreement;
import dji.pilot2.whatsnew.a.a;
import dji.publics.DJIUI.DJIOriLayout;

public class WhatsNewActivity extends DJIBaseActivity {
    public static final boolean a = false;
    private static final int h = 2130841750;
    private static final int i = 2130841751;
    private ViewPager b;
    private a c;
    private LinearLayout d;
    private int e;
    private int f;
    private Context g;
    private e.a j = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_whatsnew);
        DJIOriLayout.setOrientationByDevice(this);
        dji.pilot2.main.a.a.b(getApplicationContext());
        this.g = this;
        a();
        b();
    }

    private void a() {
        this.c = new a(this);
        this.c.a(this.j);
        this.e = (int) getResources().getDimension(R.dimen.h7);
        this.f = (int) getResources().getDimension(R.dimen.gw);
        this.j = new 1(this);
    }

    private void a(int i, int i2, int i3, Object obj) {
        if (i != c.g) {
        }
    }

    private void a(int i, int i2, Object obj) {
        if (i != c.h) {
        }
    }

    private void a(int i, long j, long j2, Object obj) {
        if (i == c.i) {
            DJILogHelper.getInstance().LOGI("bob", "" + j);
        }
    }

    private void b() {
        this.d = (LinearLayout) findViewById(R.id.ch9);
        this.b = (ViewPager) findViewById(R.id.ch8);
        this.b.setAdapter(this.c);
        for (int i = 0; i < this.c.getCount(); i++) {
            View view = new View(this);
            if (i == 0) {
                view.setBackgroundResource(R.drawable.v2_whatsnew_indicator_active);
            } else {
                view.setBackgroundResource(R.drawable.v2_whatsnew_indicator_inactive);
            }
            LayoutParams layoutParams = new LinearLayout.LayoutParams(this.e, this.e);
            layoutParams.setMargins(this.f, this.f, this.f, this.f);
            this.d.addView(view, layoutParams);
        }
        this.b.setOnPageChangeListener(new 2(this));
    }

    public void onBackPressed() {
        startActivity(new Intent(this, DJILegalAgreement.class));
        finish();
    }
}
