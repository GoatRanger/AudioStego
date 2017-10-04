package dji.pilot2.usercenter.activate;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.dbox.upgrade.p4.a.a;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.publics.c.d;
import dji.pilot2.academy.activity.DJINewAcademyVideoActivity;
import dji.pilot2.simulator.DJISimulatorActivity;
import dji.pilot2.upgrade.P3cUpgradeActivity;
import dji.pilot2.upgrade.P4UpgradeTipBannerView;
import java.util.Locale;

public class ActivateFinishView extends ActivateBaseView implements OnClickListener {
    public static final String f = "key_product_index";
    ImageView c;
    ImageView d;
    ImageView e;
    private TextView g;
    private TextView h;
    private Button i;
    private Button j;
    private Button k;
    private TextView l;
    private RelativeLayout m;
    private RelativeLayout n;
    private LinearLayout o;
    private CheckBox p;

    public ActivateFinishView(Context context) {
        super(context);
    }

    public ActivateFinishView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActivateFinishView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean canShowTopView() {
        return false;
    }

    public void setData() {
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        int i;
        this.h = (TextView) findViewById(R.id.ds);
        this.i = (Button) findViewById(R.id.du);
        this.i.setOnClickListener(this);
        this.j = (Button) findViewById(R.id.dv);
        this.j.setOnClickListener(this);
        this.k = (Button) findViewById(R.id.dt);
        this.k.setOnClickListener(this);
        this.l = (TextView) findViewById(R.id.do);
        this.l.setOnClickListener(this);
        this.g = (TextView) findViewById(R.id.dq);
        this.p = (CheckBox) findViewById(R.id.dw);
        this.p.setOnClickListener(this);
        ProductType c = i.getInstance().c();
        this.g.setText(d.getInstance().p(c) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + d.getInstance().q(c));
        if (b.h(c)) {
            this.h.setVisibility(8);
            this.j.setVisibility(8);
            this.i.setVisibility(8);
        } else {
            this.h.setVisibility(0);
            this.j.setVisibility(0);
            this.i.setVisibility(0);
        }
        if (b.h()) {
            if (a.s) {
                i = 1;
            }
            i = 0;
        } else {
            if (dji.pilot.publics.control.a.getInstance().o()) {
                i = 1;
            }
            i = 0;
        }
        if (i != 0) {
            this.h.setVisibility(8);
            this.k.setVisibility(0);
            this.j.setVisibility(8);
        }
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.do:
                this.a.a();
                return;
            case R.id.dt:
                this.k.setEnabled(false);
                if (b.g()) {
                    if (b.h()) {
                        P4UpgradeTipBannerView.enterP4UpgradeActivity(getContext());
                    } else {
                        dji.pilot2.upgrade.b.getInstance().b();
                        getContext().startActivity(new Intent(getContext(), P3cUpgradeActivity.class));
                    }
                    this.k.setVisibility(8);
                } else {
                    try {
                        b();
                        c();
                    } catch (Exception e) {
                        if (this.a != null) {
                            this.a.a();
                        }
                    }
                }
                this.k.setEnabled(true);
                return;
            case R.id.du:
                this.i.setEnabled(false);
                intent = new Intent(getContext(), DJINewAcademyVideoActivity.class);
                intent.putExtra("key_product_index", i.getInstance().c().value());
                getContext().startActivity(intent);
                this.i.setEnabled(true);
                return;
            case R.id.dv:
                this.j.setEnabled(false);
                if (!ServiceManager.getInstance().isRemoteOK() || b.h(null)) {
                    a(getContext(), R.string.v2_smlt_academy_cannot_enter_title, R.string.v2_smlt_academy_cannot_enter_content);
                } else if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
                    a(getContext(), -1, R.string.v2_smlt_not_enter_motorup_tip);
                } else {
                    ServiceManager.getInstance().pauseService(true);
                    dji.pilot2.simulator.d.b(true);
                    intent = new Intent(getContext(), DJISimulatorActivity.class);
                    intent.putExtra(DJISimulatorActivity.r, DJISimulatorActivity.s);
                    getContext().startActivity(intent);
                }
                this.j.setEnabled(true);
                return;
            case R.id.dw:
                if (this.p.isChecked()) {
                    com.dji.a.a.b(true);
                } else {
                    com.dji.a.a.b(false);
                }
                c.a("checkboxUserExpePlan" + this.p.isChecked());
                return;
            case R.id.d1k:
            case R.id.d1p:
                d();
                return;
            default:
                return;
        }
    }

    private void a(Context context, int i, int i2) {
        Builder bVar = new dji.pilot2.publics.object.b(context);
        if (i != -1) {
            View textView = new TextView(context);
            textView.setText(i);
            textView.setGravity(17);
            textView.setTextColor(context.getResources().getColor(17170444));
            textView.setPadding(0, 15, 0, 15);
            textView.setTextSize(20.0f);
            bVar.setCustomTitle(textView);
        }
        bVar.setMessage(i2);
        bVar.setPositiveButton(17039379, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ ActivateFinishView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        bVar.show();
    }

    private void b() {
        this.o = (LinearLayout) findViewById(R.id.dx);
        this.n = (RelativeLayout) findViewById(R.id.d1h);
        this.m = (RelativeLayout) findViewById(R.id.d1m);
        String language;
        if (dji.pilot.publics.control.rc.b.getInstance().g()) {
            this.n.setVisibility(0);
            this.m.setVisibility(8);
            language = Locale.getDefault().getLanguage();
            this.c = (ImageView) findViewById(R.id.d1l);
            this.d = (ImageView) findViewById(R.id.d1j);
            if (!"zh".equals(language)) {
                this.c.setImageResource(R.drawable.upgrade_tutorial_magnify_app_en);
                this.d.setImageResource(R.drawable.active_upgrade_firmware_en);
            }
            this.e = (ImageView) findViewById(R.id.d1k);
            findViewById(R.id.d1s).setVisibility(0);
            findViewById(R.id.d1t).setVisibility(8);
        } else {
            this.n.setVisibility(8);
            this.m.setVisibility(0);
            language = Locale.getDefault().getLanguage();
            this.c = (ImageView) findViewById(R.id.d1o);
            if (!"zh".equals(language)) {
                this.c.setImageResource(R.drawable.active_upgrade_firmware_en);
            }
            this.c = (ImageView) findViewById(R.id.d1o);
            this.d = (ImageView) findViewById(R.id.d1q);
            this.e = (ImageView) findViewById(R.id.d1p);
            findViewById(R.id.d1s).setVisibility(8);
            findViewById(R.id.d1t).setVisibility(0);
        }
        this.e.setOnClickListener(this);
    }

    private void c() {
        final Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.aa);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(getContext(), R.anim.aw);
        loadAnimation2.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ ActivateFinishView b;

            public void onAnimationStart(Animation animation) {
                this.b.o.setVisibility(0);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.b.c.setVisibility(0);
                this.b.d.setVisibility(0);
                this.b.c.startAnimation(loadAnimation);
                this.b.d.startAnimation(loadAnimation);
            }
        });
        this.o.startAnimation(loadAnimation2);
    }

    private void d() {
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.ay);
        loadAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ ActivateFinishView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.o.setVisibility(8);
                this.a.c.setVisibility(4);
                this.a.d.setVisibility(4);
            }
        });
        this.o.startAnimation(loadAnimation);
    }
}
