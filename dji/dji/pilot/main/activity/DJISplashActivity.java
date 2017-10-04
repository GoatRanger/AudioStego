package dji.pilot.main.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import dji.pilot.R;
import dji.pilot.fpv.d.c.d;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.objects.g;
import dji.pilot2.main.a.a;
import dji.pilot2.main.activity.DJIMainFragmentActivity;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.afinal.a.b.c;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DJISplashActivity extends DJIBaseActivity implements d {
    public static final String f = "splash_jump_ads_url";
    public static final String g = "key_splash_have_showed_today";
    private static int u = 1000;
    @c(a = 2131366872)
    private DJIRelativeLayout h;
    @c(a = 2131366873)
    private DJIImageView i;
    @c(a = 2131366880)
    private DJILinearLayout j;
    @c(a = 2131366882)
    private DJITextView k;
    @c(a = 2131366881)
    private DJITextView l;
    @c(a = 2131366883)
    private DJIRelativeLayout m;
    @c(a = 2131366876)
    private DJITextView n;
    @c(a = 2131366875)
    private DJIRelativeLayout o;
    @c(a = 2131366877)
    private DJIRelativeLayout p;
    @c(a = 2131366874)
    private DJIImageView q;
    private boolean r = false;
    private String s;
    private String t;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_splash);
        DJIOriLayout.setOrientationByDevice(this);
        a();
        e();
        a.b(getApplicationContext());
        if (c()) {
            d();
        } else {
            new Handler().postDelayed(new 1(this), 500);
        }
    }

    private void a() {
        if (this.n != null) {
            this.n.setOnClickListener(new 2(this));
        }
        if (this.i != null) {
            this.i.setOnClickListener(new 3(this));
        }
    }

    private String b() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    private void a(boolean z) {
        if (!this.r) {
            this.r = true;
            Intent intent = new Intent(this, DJIMainFragmentActivity.class);
            if (z) {
                e.b(d.cz_);
                if (!(this.s == null || this.s.equals(""))) {
                    intent.putExtra(f, this.s);
                }
            }
            startActivity(intent);
            overridePendingTransition(R.anim.bv, 0);
            finish();
        }
    }

    private boolean c() {
        if (b().equals(g.b((Context) this, g, ""))) {
            return false;
        }
        return true;
    }

    private void d() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.h, "alpha", new float[]{0.0f, 1.0f});
        ofFloat.setDuration(1000);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.m, "alpha", new float[]{1.0f, 0.0f});
        ofFloat2.setDuration(500);
        ObjectAnimator ofFloat3;
        ObjectAnimator ofFloat4;
        if (a.l.equals(this.t)) {
            ofFloat3 = ObjectAnimator.ofFloat(this.i, "scaleX", new float[]{1.0f, 1.05f});
            ofFloat4 = ObjectAnimator.ofFloat(this.i, "scaleY", new float[]{1.0f, 1.05f});
            ofFloat3.setDuration((long) u);
            ofFloat4.setDuration((long) u);
            ofFloat3.addListener(new 4(this));
            new Handler().postDelayed(new 5(this, new AnimatorSet(), ofFloat, ofFloat2, ofFloat3, ofFloat4), 1000);
        } else {
            ofFloat3 = ObjectAnimator.ofFloat(this.i, "scaleX", new float[]{1.0f, 1.05f});
            ofFloat4 = ObjectAnimator.ofFloat(this.i, "scaleY", new float[]{1.0f, 1.05f});
            ofFloat3.setDuration((long) u);
            ofFloat4.setDuration((long) u);
            ofFloat3.addListener(new 4(this));
            new Handler().postDelayed(new 5(this, new AnimatorSet(), ofFloat, ofFloat2, ofFloat3, ofFloat4), 1000);
        }
    }

    @SuppressLint({"NewApi"})
    private void e() {
        String c = a.c(this);
        if (c != null) {
            String str = a.a(this) + c + a.n;
            if (new File(str).exists()) {
                Options options = new Options();
                options.inPreferredConfig = Config.ARGB_8888;
                Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
                if (decodeFile != null) {
                    if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                        float dimension;
                        int width = decodeFile.getWidth();
                        float height = (((float) decodeFile.getHeight()) * ((float) getResources().getDisplayMetrics().widthPixels)) / ((float) width);
                        float f = ((float) getResources().getDisplayMetrics().heightPixels) - height;
                        if (f < getResources().getDimension(R.dimen.gz)) {
                            dimension = getResources().getDimension(R.dimen.gz);
                        } else {
                            dimension = f;
                        }
                        LayoutParams layoutParams = this.i.getLayoutParams();
                        layoutParams.height = (int) height;
                        this.i.setLayoutParams(layoutParams);
                        if (this.q != null) {
                            layoutParams = this.q.getLayoutParams();
                            layoutParams.height = (int) height;
                            this.q.setLayoutParams(layoutParams);
                            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.o.getLayoutParams();
                            layoutParams2.height = (int) dimension;
                            layoutParams2.addRule(3, R.id.czr);
                            this.o.setLayoutParams(layoutParams2);
                        }
                    }
                    this.i.setImageBitmap(decodeFile);
                    this.i.setScaleType(ScaleType.CENTER_CROP);
                    if (c.equals(g.b((Context) this, "last_splash_time_available", ""))) {
                        str = a.d;
                    } else {
                        str = a.e;
                    }
                    String b = g.b((Context) this, str + a.f, "");
                    if (!b.equals("")) {
                        this.l.setText(getString(R.string.v2_splash_author) + b);
                    }
                    c = g.b((Context) this, str + "device", "");
                    if (!c.equals("")) {
                        this.k.setText(getString(R.string.v2_splash_device) + c);
                    }
                    if (b.equals("") && c.equals("")) {
                        this.j.setBackgroundColor(getResources().getColor(R.color.j5));
                    }
                    this.s = g.b((Context) this, str + a.j, "");
                    u = g.b((Context) this, str + "duration", 1);
                    if (u >= 5) {
                        u = 4;
                    }
                    if (u <= 0) {
                        u = 0;
                    }
                    u *= 1000;
                    this.t = g.b((Context) this, str + "type", "");
                }
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        f();
    }

    private void f() {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.i.getDrawable();
        if (bitmapDrawable != null && bitmapDrawable.getBitmap() != null) {
            bitmapDrawable.getBitmap().recycle();
            this.i.setImageDrawable(null);
        }
    }

    public void onBackPressed() {
    }
}
