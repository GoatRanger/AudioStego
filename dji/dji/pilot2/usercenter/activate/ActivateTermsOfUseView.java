package dji.pilot2.usercenter.activate;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import dji.pilot.R;
import dji.pilot2.usercenter.activate.a.a;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.pilot2.widget.DJINewStateTextView;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class ActivateTermsOfUseView extends ActivateBaseView implements OnClickListener {
    private static final String c = "DJIActiveTermsActivity";
    private DJIWebviewFragment d = null;
    private RelativeLayout e;
    private RelativeLayout f;
    private DJINewStateTextView g;
    private DJINewStateTextView h;
    private DJITextView i;
    private DJITextView j;
    private CheckBox k;
    private Fragment l;
    private FragmentManager m;
    private String n = "file:///android_asset/";
    private String o = (this.n + "htmls/dji_usa_hardware_tos_2.0.html");
    private String p = (this.n + "htmls/privacy_policy.html");

    public ActivateTermsOfUseView(Context context) {
        super(context);
    }

    public ActivateTermsOfUseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActivateTermsOfUseView(Context context, AttributeSet attributeSet, int i) {
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
            this.m = ((Activity) getContext()).getFragmentManager();
            a();
            Fragment c = DJIWebviewFragment.c(this.o);
            this.d = c;
            this.l = c;
            this.m.beginTransaction().add(R.id.ck, this.l).commit();
            this.e = (RelativeLayout) findViewById(R.id.ew);
            this.f = (RelativeLayout) findViewById(R.id.ex);
            this.g = (DJINewStateTextView) findViewById(R.id.et);
            this.h = (DJINewStateTextView) findViewById(R.id.eu);
            this.i = (DJITextView) findViewById(R.id.ey);
            this.k = (CheckBox) findViewById(R.id.ez);
            this.j = (DJITextView) findViewById(R.id.f0);
            this.g.setOnClickListener(this);
            this.h.setOnClickListener(this);
            this.k.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
                final /* synthetic */ ActivateTermsOfUseView a;

                {
                    this.a = r1;
                }

                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        this.a.j.setEnabled(true);
                    } else {
                        this.a.j.setEnabled(false);
                    }
                }
            });
            c.a().a(this);
            findViewById(R.id.f0).setOnClickListener(this);
        }
    }

    private void a() {
        if (c.a()) {
            c.a("term j");
            this.o = this.n + "htmls/dji_jp_hardware_tos_1.0.html";
            this.p = this.n + "htmls/japanese_privacy_policy.html";
            a.getInstance().c("1.0");
            a.getInstance().b("1.1");
            a.getInstance().a(true);
        } else if (c.b()) {
            c.a("term c");
            a.getInstance().a(false);
        } else {
            c.a("term e");
            this.o = this.n + "htmls/dji_usa_hardware_tos_2.0.html";
            this.p = this.n + "htmls/privacy_policy.html";
            a.getInstance().c("2.0");
            a.getInstance().b("1.1");
            a.getInstance().a(true);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void onEventMainThread(a aVar) {
        if (aVar == a.BACK_PRESSED) {
            this.d.d();
        }
    }

    public void onClick(View view) {
        Fragment c;
        switch (view.getId()) {
            case R.id.et:
                this.m.beginTransaction().remove(this.l).commit();
                c = DJIWebviewFragment.c(this.o);
                this.d = c;
                this.l = c;
                this.m.beginTransaction().add(R.id.ck, this.l).commit();
                this.e.setVisibility(0);
                this.f.setVisibility(4);
                this.i.setText(R.string.fly_unlimit_legal_agreement_terms_of_use_title);
                return;
            case R.id.eu:
                this.m.beginTransaction().remove(this.l).commit();
                c = DJIWebviewFragment.c(this.p);
                this.d = c;
                this.l = c;
                this.m.beginTransaction().add(R.id.ck, this.l).commit();
                this.e.setVisibility(4);
                this.f.setVisibility(0);
                this.i.setText(R.string.fly_unlimit_legal_agreement_privacy_policy_title);
                return;
            case R.id.f0:
                if (this.k.isChecked()) {
                    this.a.a();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
