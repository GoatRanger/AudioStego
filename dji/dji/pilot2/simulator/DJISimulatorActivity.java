package dji.pilot2.simulator;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.n;
import dji.pilot.R;
import dji.pilot.fpv.activity.DJIPreviewActivityLitchi;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.main.activity.DJIHubActivity.a;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.welcome.activity.DJIActiveActivity;
import dji.pilot2.academy.activity.DJINewAcademyActivity;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.afinal.a.b.c;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DJISimulatorActivity extends DJIPreviewActivityLitchi {
    public static final String r = "activity_from";
    public static final String s = "DJIActiveActivity";
    @c(a = 2131362565)
    private DJITextView A;
    @c(a = 2131362566)
    private DJIStateTextView B;
    @c(a = 2131362567)
    private DJIStateTextView C;
    @c(a = 2131362912)
    private DJIImageView D;
    private float E = 0.0f;
    private int F = 0;
    private Animation G;
    private Animation H;
    private boolean I = false;
    private ProductType J = ProductType.OTHER;
    private List<Object> K = new ArrayList();
    private d t;
    private b u;
    private a v;
    private String w = "";
    @c(a = 2131363246)
    private DJIImageView x;
    @c(a = 2131362563)
    private DJILinearLayout y;
    @c(a = 2131362564)
    private DJIImageView z;

    protected void onCreate(Bundle bundle) {
        this.o = Boolean.valueOf(true);
        this.t = new d(this);
        this.t.b();
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        this.t.a((LinearLayout) findViewById(R.id.a3b));
        this.t.c();
        this.w = getIntent().getStringExtra(r);
        d.a(this);
        u();
        p();
    }

    public void p() {
        if (this.K.isEmpty()) {
            int i = 0;
            Class cls = getClass();
            while (i < 3 && cls != null) {
                for (Field field : cls.getDeclaredFields()) {
                    field.getName();
                    try {
                        boolean isAccessible = field.isAccessible();
                        if (!isAccessible) {
                            field.setAccessible(true);
                        }
                        field.setAccessible(true);
                        Object obj = field.get(this);
                        if (dji.thirdparty.a.c.a().c(obj) && !(obj instanceof n)) {
                            this.K.add(obj);
                        }
                        if (!isAccessible) {
                            field.setAccessible(false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                i++;
                cls = cls.getSuperclass();
            }
        }
    }

    private void s() {
        for (Object next : this.K) {
            if (!dji.thirdparty.a.c.a().c(next)) {
                dji.thirdparty.a.c.a().a(next);
            }
        }
    }

    private void t() {
        for (Object next : this.K) {
            if (dji.thirdparty.a.c.a().c(next)) {
                dji.thirdparty.a.c.a().d(next);
            }
        }
    }

    private void u() {
        this.x.setOnClickListener(this);
        this.y.show();
        this.y.setOnClickListener(this);
        this.B.setOnClickListener(this);
        this.B.show();
        this.D.setOnClickListener(this);
        this.D.show();
        this.C.setOnClickListener(this);
        this.C.show();
        this.A.setText(String.format(getString(R.string.v2_smlt_windspeed_txt), new Object[]{Float.valueOf(this.E)}));
        this.t.a(new 1(this));
        this.t.a(100000.0f, 0);
        v();
    }

    private void v() {
        this.G = AnimationUtils.loadAnimation(this, R.anim.ab);
        this.G.setDuration(800);
        this.G.setAnimationListener(new 2(this));
        this.H = AnimationUtils.loadAnimation(this, R.anim.a_);
        this.H.setDuration(800);
        this.H.setAnimationListener(new 3(this));
        this.D.startAnimation(this.G);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.u7:
                if (this.u == null) {
                    this.u = new b(this);
                    this.u.setOnDismissListener(new 4(this));
                }
                this.u.b(this.E);
                this.u.a(this.F);
                this.u.show();
                return;
            case R.id.u_:
                this.t.f();
                return;
            case R.id.ua:
                this.t.i();
                return;
            case R.id.a3m:
                this.I = true;
                this.D.setImageDrawable(getResources().getDrawable(R.drawable.v2_smlt_help_clicked));
                if (this.v == null || this.J != i.getInstance().c()) {
                    this.J = i.getInstance().c();
                    this.v = new a(this, this.D, this.J);
                    this.v.setOnDismissListener(new 5(this));
                }
                this.v.show();
                return;
            case R.id.abn:
                q();
                return;
            default:
                return;
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.w = intent.getStringExtra(r);
    }

    public void q() {
        Intent intent;
        DJINewAcademyActivity.b = true;
        d.b(false);
        if (this.w == null || !this.w.equals(s)) {
            intent = new Intent(this, DJINewAcademyActivity.class);
        } else {
            intent = new Intent(this, DJIActiveActivity.class);
        }
        intent.setFlags(536870912);
        startActivity(intent);
    }

    public void onBackPressed() {
        q();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.t.a(configuration);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.t.a(z);
    }

    protected void onResume() {
        super.onResume();
        d.b(true);
        if (d.h()) {
            dji.thirdparty.a.c.a().e(a.a);
        }
        this.t.d();
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
        s();
    }

    protected void onPause() {
        if (!d.h()) {
            dji.thirdparty.a.c.a().e(a.b);
        }
        this.t.e();
        if (dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().d((Object) this);
        }
        t();
        super.onPause();
    }

    protected void h() {
        if (d.h()) {
            b bVar = new b();
            bVar.a = d.WARNING;
            bVar.b = R.string.v2_smlt_not_support_playback;
            dji.thirdparty.a.c.a().e(bVar);
        }
    }

    public void r() {
        runOnUiThread(new 6(this));
    }

    protected void a(boolean z) {
        super.a(z);
        this.D.go();
    }

    protected void b(boolean z) {
        super.b(z);
        this.D.show();
        if (!this.I) {
            this.D.startAnimation(this.G);
        }
    }
}
