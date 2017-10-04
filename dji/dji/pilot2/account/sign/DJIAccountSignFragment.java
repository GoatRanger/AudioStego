package dji.pilot2.account.sign;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.c.d;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.widget.DJIEditText;
import dji.pilot.store.DJIStoreActivity;
import dji.pilot.usercenter.b.f;
import dji.pilot2.account.forget.DJIAccountForgetActivity;
import dji.pilot2.account.profile.DJIEditProfileActivity;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.main.activity.DJILegalAgreement;
import dji.pilot2.nativeexplore.model.ExploreEvent;
import dji.pilot2.usercenter.activity.DJIFlightRecordActivity;
import dji.pilot2.utils.k;
import dji.pilot2.utils.m;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;

public class DJIAccountSignFragment extends Fragment implements OnClickListener, d, dji.pilot2.account.sign.a.b {
    private static final a T = new a() {
        public String toString() {
            return "now state: STATE_SIGN_IN";
        }

        public void a(DJIAccountSignFragment dJIAccountSignFragment) {
            dJIAccountSignFragment.z();
        }

        public void a(Editable editable, DJIAccountSignFragment dJIAccountSignFragment) {
            if (m.c(dJIAccountSignFragment.s.getText().toString()) || m.c(dJIAccountSignFragment.t.getText().toString()) || (dJIAccountSignFragment.p && m.c(dJIAccountSignFragment.K.getText().toString()))) {
                dJIAccountSignFragment.b(dJIAccountSignFragment.u);
            } else {
                dJIAccountSignFragment.a(dJIAccountSignFragment.u);
            }
        }

        public void b(DJIAccountSignFragment dJIAccountSignFragment) {
            dJIAccountSignFragment.w();
            dJIAccountSignFragment.u.setText(dJIAccountSignFragment.getResources().getText(R.string.home_account_signin_doing));
            if (dJIAccountSignFragment.p) {
                dJIAccountSignFragment.N.a(dJIAccountSignFragment.s.getText().toString(), dJIAccountSignFragment.t.getText().toString(), dJIAccountSignFragment.K.getText().toString());
            } else {
                dJIAccountSignFragment.N.a(dJIAccountSignFragment.s.getText().toString(), dJIAccountSignFragment.t.getText().toString());
            }
            dJIAccountSignFragment.u.setCompoundDrawables(dJIAccountSignFragment.getActivity().getResources().getDrawable(R.drawable.sign_loading), null, null, null);
        }
    };
    private static final a U = new a() {
        public void a(DJIAccountSignFragment dJIAccountSignFragment) {
            dJIAccountSignFragment.z();
        }

        public void a(Editable editable, DJIAccountSignFragment dJIAccountSignFragment) {
            if (m.c(dJIAccountSignFragment.s.getText().toString()) || (dJIAccountSignFragment.q && m.c(dJIAccountSignFragment.K.getText().toString()))) {
                dJIAccountSignFragment.b(dJIAccountSignFragment.u);
            } else {
                dJIAccountSignFragment.a(dJIAccountSignFragment.u);
            }
        }

        public void b(DJIAccountSignFragment dJIAccountSignFragment) {
            dJIAccountSignFragment.w();
            dJIAccountSignFragment.N.b(dJIAccountSignFragment.s.getText().toString(), dJIAccountSignFragment.K.getText().toString());
        }
    };
    private static final a V = new a() {
        public void a(DJIAccountSignFragment dJIAccountSignFragment) {
            dJIAccountSignFragment.M = DJIAccountSignFragment.U;
            dJIAccountSignFragment.P.start();
            dJIAccountSignFragment.M.a(dJIAccountSignFragment.s.getText(), dJIAccountSignFragment);
            dJIAccountSignFragment.E.setVisibility(0);
        }

        public void a(Editable editable, DJIAccountSignFragment dJIAccountSignFragment) {
            if (m.c(dJIAccountSignFragment.D.getText().toString()) || (dJIAccountSignFragment.r && m.c(dJIAccountSignFragment.K.getText().toString()))) {
                dJIAccountSignFragment.b(dJIAccountSignFragment.u);
            } else {
                dJIAccountSignFragment.a(dJIAccountSignFragment.u);
            }
        }

        public void b(DJIAccountSignFragment dJIAccountSignFragment) {
            String obj = dJIAccountSignFragment.D.getText().toString();
            if (dJIAccountSignFragment.b(obj)) {
                dJIAccountSignFragment.w();
                if (dJIAccountSignFragment.r) {
                    dJIAccountSignFragment.N.c(obj.trim(), dJIAccountSignFragment.K.getText().toString());
                } else {
                    dJIAccountSignFragment.N.a(obj.trim());
                }
            }
        }
    };
    public static final int f = 0;
    public static final int g = -1;
    public static final int h = 1;
    private static final String i = "DJIAccountSignFragment";
    private static final int o = 3;
    private RelativeLayout A;
    private RelativeLayout B;
    private RelativeLayout C;
    private DJIEditText D;
    private DJITextView E;
    private DJITextView F;
    private DJIImageView G;
    private DJITextView H;
    private int I = 1003;
    private ViewGroup J;
    private EditText K;
    private ImageView L;
    private a M = T;
    private dji.pilot2.account.sign.a.a N;
    private AnimatorSet O;
    private AnimatorSet P;
    private AnimatorSet Q;
    private AnimatorSet R;
    private TextWatcher S = new TextWatcher(this) {
        final /* synthetic */ DJIAccountSignFragment a;

        {
            this.a = r1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (m.c(this.a.t.getText().toString())) {
                this.a.w.setVisibility(8);
            } else {
                this.a.w.setVisibility(0);
            }
            if (m.c(this.a.s.getText().toString())) {
                this.a.x.setVisibility(8);
            } else {
                this.a.x.setVisibility(0);
            }
            if (m.c(this.a.D.getText().toString())) {
                this.a.y.setVisibility(8);
            } else {
                this.a.y.setVisibility(0);
            }
            this.a.M.a(null, this.a);
        }
    };
    private final int j = 500;
    private boolean k = true;
    private boolean l = false;
    private dji.pilot2.account.a.b m;
    private int n = 0;
    private boolean p = false;
    private boolean q = true;
    private boolean r = false;
    private DJIEditText s;
    private DJIEditText t;
    private Button u;
    private DJITextView v;
    private ImageView w;
    private ImageView x;
    private ImageView y;
    private RelativeLayout z;

    private interface a {
        void a(Editable editable, DJIAccountSignFragment dJIAccountSignFragment);

        void a(DJIAccountSignFragment dJIAccountSignFragment);

        void b(DJIAccountSignFragment dJIAccountSignFragment);
    }

    private class b extends ClickableSpan {
        final /* synthetic */ DJIAccountSignFragment a;
        private String b;

        public b(DJIAccountSignFragment dJIAccountSignFragment, String str) {
            this.a = dJIAccountSignFragment;
            this.b = str;
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(this.a.getActivity().getResources().getColor(R.color.jt));
            textPaint.setUnderlineText(false);
        }

        public void onClick(View view) {
            Intent intent = null;
            String str = "";
            Location e = dji.a.a.getInstance().e();
            Intent intent2;
            String a;
            if (this.b.equals(this.a.getActivity().getString(R.string.v2_terms_string))) {
                intent2 = new Intent(this.a.getActivity(), DJISupportShareWebviewActivity.class);
                if (e != null) {
                    a = k.a(true, e.getLatitude(), e.getLongitude());
                } else {
                    a = k.a(false, 0.0d, 0.0d);
                }
                intent2.putExtra(DJIWebviewFragment.o, a);
                intent = intent2;
            } else if (this.b.equals(this.a.getActivity().getString(R.string.v2_privacy_string))) {
                intent2 = new Intent(this.a.getActivity(), DJISupportShareWebviewActivity.class);
                if (e != null) {
                    a = k.b(true, e.getLatitude(), e.getLongitude());
                } else {
                    a = k.b(false, 0.0d, 0.0d);
                }
                intent2.putExtra(DJIWebviewFragment.o, a);
                intent = intent2;
            }
            if (intent != null) {
                this.a.getActivity().startActivity(intent);
            }
        }
    }

    public enum c {
        SUCCEED,
        FAIL
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        s();
        u();
        new Handler().post(new Runnable(this) {
            final /* synthetic */ DJIAccountSignFragment a;

            {
                this.a = r1;
            }

            public void run() {
                e.b(d.cv_);
            }
        });
        if (this.l) {
            m();
            this.I = -1;
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.v2_fragment_account_sign, viewGroup);
        this.s = (DJIEditText) inflate.findViewById(R.id.cn6);
        this.s.addTextChangedListener(this.S);
        this.t = (DJIEditText) inflate.findViewById(R.id.cnf);
        this.t.addTextChangedListener(this.S);
        this.u = (Button) inflate.findViewById(R.id.cnl);
        this.u.setOnClickListener(this);
        this.x = (ImageView) inflate.findViewById(R.id.cn9);
        this.x.setOnClickListener(this);
        this.w = (ImageView) inflate.findViewById(R.id.cng);
        this.w.setOnClickListener(this);
        this.z = (RelativeLayout) inflate.findViewById(R.id.cn8);
        this.A = (RelativeLayout) inflate.findViewById(R.id.cne);
        this.B = (RelativeLayout) inflate.findViewById(R.id.cnk);
        this.E = (DJITextView) inflate.findViewById(R.id.cnn);
        this.E.setOnClickListener(this);
        this.H = (DJITextView) inflate.findViewById(R.id.cn_);
        r();
        this.v = (DJITextView) inflate.findViewById(R.id.c1);
        this.v.setOnClickListener(this);
        this.C = (RelativeLayout) inflate.findViewById(R.id.cna);
        this.D = (DJIEditText) inflate.findViewById(R.id.cnb);
        this.D.addTextChangedListener(this.S);
        this.y = (ImageView) inflate.findViewById(R.id.cnc);
        this.y.setOnClickListener(this);
        this.G = (DJIImageView) inflate.findViewById(R.id.cnm);
        this.L = (ImageView) inflate.findViewById(R.id.cnj);
        this.J = (ViewGroup) inflate.findViewById(R.id.cnh);
        this.K = (EditText) inflate.findViewById(R.id.cni);
        this.K.addTextChangedListener(this.S);
        this.L.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIAccountSignFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.N.a();
            }
        });
        inflate.findViewById(R.id.ci).setOnClickListener(this);
        this.F = (DJITextView) inflate.findViewById(R.id.c4d);
        return inflate;
    }

    public void a(boolean z, String str) {
        DJILogHelper.getInstance().LOGD(i, "signInResult:" + z);
        x();
        this.u.setText(getActivity().getResources().getText(R.string.home_account_signin));
        if (z) {
            f.getInstance().j();
            dji.pilot.fpv.d.a.getInstance().a(f.getInstance().m(), f.getInstance().i());
            dji.thirdparty.a.c.a().e(ExploreEvent.USER_LOGIN);
            e();
            return;
        }
        this.t.setText("");
        this.K.setText("");
        Toast.makeText(getActivity().getApplicationContext(), str, 0).show();
    }

    public void b(boolean z, String str) {
        DJILogHelper.getInstance().LOGD(i, "showEmailValid:" + z);
        x();
        if (z) {
            p();
            return;
        }
        this.K.setText("");
        Toast.makeText(getActivity().getApplicationContext(), str, 0).show();
    }

    public void c(boolean z, String str) {
        DJILogHelper.getInstance().LOGD(i, "showSignUpResult:" + z);
        x();
        if (z) {
            dji.pilot.fpv.d.a.getInstance().d(f.getInstance().m());
            y();
            return;
        }
        this.K.setText("");
        Toast.makeText(getActivity(), str, 0).show();
    }

    public void a() {
        t();
    }

    public void a(boolean z, int i) {
        DJILogHelper.getInstance().LOGD(i, "signInResult:" + z);
        x();
        this.u.setText(getActivity().getResources().getText(R.string.home_account_signin));
        if (!z) {
            if (i == 107) {
                k();
            } else if (i == dji.pilot.usercenter.protocol.d.b || i == 500) {
                this.n++;
                i();
            } else if (i == 300) {
                j();
            }
            this.t.setText("");
            this.K.setText("");
        }
    }

    public void a(String str) {
        com.dji.frame.c.c.a(getActivity()).a(this.L, str);
    }

    public void b() {
        this.p = true;
        g();
    }

    public void c() {
        this.q = true;
        g();
    }

    public void d() {
        this.r = true;
        g();
    }

    private void g() {
        this.N.a();
        this.K.setText("");
        if (this.J.getVisibility() != 0) {
            this.J.setVisibility(0);
            this.M.a(this.K.getText(), this);
        }
    }

    private void h() {
        if (this.J.getVisibility() == 0) {
            this.J.setVisibility(8);
            this.M.a(this.K.getText(), this);
        }
    }

    private void i() {
        if (this.m == null) {
            this.m = new dji.pilot2.account.a.b(getActivity());
        }
        this.m.a(a((int) R.string.home_account_pwd_invalid));
        this.m.c(a((int) R.string.account_dialog_dismiss));
        if (this.n >= 2) {
            this.m.b(a((int) R.string.home_account_forgot_pwd));
        } else {
            this.m.b("");
        }
        this.m.a(new dji.pilot2.account.a.b.a(this) {
            final /* synthetic */ DJIAccountSignFragment a;

            {
                this.a = r1;
            }

            public void a(dji.pilot2.account.a.b bVar) {
                this.a.q();
                bVar.dismiss();
            }

            public void b(dji.pilot2.account.a.b bVar) {
                bVar.dismiss();
            }
        });
        this.m.show();
    }

    private void j() {
        if (this.m == null) {
            this.m = new dji.pilot2.account.a.b(getActivity());
        }
        this.m.a(a((int) R.string.home_account_verification_code_invalid));
        this.m.c(a((int) R.string.account_dialog_dismiss));
        this.m.show();
    }

    private String a(int i) {
        return getActivity().getResources().getString(i);
    }

    private void k() {
        if (this.m == null) {
            this.m = new dji.pilot2.account.a.b(getActivity());
        }
        this.m.a(a((int) R.string.account_email_not_found));
        this.m.b(getString(R.string.account_sign_up_now));
        this.m.c(a((int) R.string.account_dialog_dismiss));
        this.m.a(new dji.pilot2.account.a.b.a(this) {
            final /* synthetic */ DJIAccountSignFragment a;

            {
                this.a = r1;
            }

            public void a(dji.pilot2.account.a.b bVar) {
                bVar.dismiss();
                this.a.m();
            }

            public void b(dji.pilot2.account.a.b bVar) {
                bVar.dismiss();
            }
        });
        this.m.show();
    }

    public void a(Object obj) {
        this.N = (dji.pilot2.account.sign.a.a) obj;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.c1:
                m();
                return;
            case R.id.ci:
                t();
                return;
            case R.id.cn9:
                this.s.getText().clear();
                return;
            case R.id.cnc:
                if (this.D.getInputType() == 144) {
                    this.D.setInputType(129);
                } else {
                    this.D.setInputType(144);
                }
                this.D.setSelection(this.D.length());
                return;
            case R.id.cng:
                if (this.t.getInputType() == 144) {
                    this.t.setInputType(129);
                } else {
                    this.t.setInputType(144);
                }
                this.t.setSelection(this.t.length());
                return;
            case R.id.cnl:
                l();
                return;
            case R.id.cnn:
                if (this.M == T) {
                    q();
                    return;
                } else {
                    o();
                    return;
                }
            default:
                return;
        }
    }

    private void l() {
        this.M.b(this);
    }

    private void m() {
        this.M = U;
        this.v.setVisibility(8);
        this.E.setText(R.string.sign_activity_go_signin);
        this.F.setText(R.string.home_account_signup);
        this.u.setText(R.string.sign_btn_next_step);
        this.Q.start();
        this.M.a(this.s.getText(), this);
    }

    private void n() {
        this.M = U;
        this.v.setVisibility(8);
        this.E.setText(R.string.sign_activity_go_signin);
        this.F.setText(R.string.home_account_signup);
        this.u.setText(R.string.sign_btn_next_step);
        this.H.setVisibility(0);
        this.A.setScaleY(0.0f);
        this.M.a(this.s.getText(), this);
        this.A.setVisibility(8);
        if (this.q) {
            g();
        }
    }

    private void o() {
        this.M = T;
        this.v.setVisibility(0);
        this.A.setVisibility(0);
        this.F.setText(R.string.home_account_signin);
        this.E.setText(R.string.home_account_forgot_pwd);
        this.u.setText(R.string.home_account_signin);
        this.H.setVisibility(8);
        this.R.start();
        this.M.a(this.s.getText(), this);
    }

    private void p() {
        this.M = V;
        this.O.start();
        this.M.a(this.t.getText(), this);
        this.E.setVisibility(8);
    }

    private void q() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), DJIAccountForgetActivity.class);
        startActivity(intent);
    }

    private void r() {
        String string = getActivity().getString(R.string.home_account_signup_protocol);
        String string2 = getActivity().getString(R.string.v2_terms_string);
        String string3 = getActivity().getString(R.string.v2_privacy_string);
        string = String.format(string, new Object[]{string2, string3});
        CharSequence substring = string.substring(0, string.indexOf(string2));
        CharSequence substring2 = string.substring(string.indexOf(string2) + string2.length(), string.indexOf(string3));
        CharSequence spannableString = new SpannableString(string2);
        spannableString.setSpan(new b(this, string2), 0, string2.length(), 33);
        CharSequence spannableString2 = new SpannableString(string3);
        spannableString2.setSpan(new b(this, string3), 0, string3.length(), 33);
        this.H.setMovementMethod(LinkMovementMethod.getInstance());
        this.H.setText(substring);
        this.H.append(spannableString);
        this.H.append(substring2);
        this.H.append(spannableString2);
        this.H.append("\n");
    }

    private void s() {
        int width;
        this.Q = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.A, "scaleY", new float[]{0.0f});
        this.Q.playTogether(new Animator[]{ofFloat});
        this.Q.setDuration(500);
        this.Q.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ DJIAccountSignFragment a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animator animator) {
                this.a.h();
            }

            public void onAnimationEnd(Animator animator) {
                this.a.H.setVisibility(0);
                this.a.A.setVisibility(8);
                this.a.s.requestFocus();
                if (this.a.q) {
                    this.a.g();
                }
            }
        });
        this.R = new AnimatorSet();
        ofFloat = ObjectAnimator.ofFloat(this.A, "scaleY", new float[]{1.0f});
        this.R.playTogether(new Animator[]{ofFloat});
        this.R.setDuration(500);
        this.R.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ DJIAccountSignFragment a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animator animator) {
                this.a.h();
                this.a.A.setVisibility(0);
            }

            public void onAnimationEnd(Animator animator) {
                if (this.a.p) {
                    this.a.g();
                }
            }
        });
        if (getActivity().getWindowManager().getDefaultDisplay().getWidth() > getActivity().getWindowManager().getDefaultDisplay().getHeight()) {
            width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        } else {
            width = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        }
        this.C.setTranslationX((float) width);
        this.O = new AnimatorSet();
        ObjectAnimator.ofFloat(this.z, "translationX", new float[]{(float) (-width)}).setDuration(500);
        ObjectAnimator.ofFloat(this.C, "translationX", new float[]{0.0f}).setDuration(500);
        this.O.playTogether(new Animator[]{r1, r2});
        this.O.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ DJIAccountSignFragment a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animator animator) {
                this.a.h();
            }

            public void onAnimationEnd(Animator animator) {
                this.a.D.requestFocus();
                if (this.a.r) {
                    this.a.g();
                }
            }
        });
        this.P = new AnimatorSet();
        ObjectAnimator.ofFloat(this.z, "translationX", new float[]{0.0f}).setDuration(500);
        ObjectAnimator.ofFloat(this.C, "translationX", new float[]{(float) width}).setDuration(500);
        this.P.playTogether(new Animator[]{r1, ofFloat});
        this.P.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ DJIAccountSignFragment a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animator animator) {
                this.a.h();
            }

            public void onAnimationEnd(Animator animator) {
                if (this.a.q) {
                    this.a.g();
                }
                this.a.s.requestFocus();
            }
        });
    }

    private void a(Button button) {
        button.setBackgroundResource(R.drawable.btn_sign_enable);
        button.setTextColor(getActivity().getResources().getColor(R.color.om));
        button.setEnabled(true);
    }

    private void b(Button button) {
        button.setBackgroundResource(R.drawable.btn_sign_disable);
        button.setTextColor(getActivity().getResources().getColor(R.color.ju));
        button.setEnabled(false);
    }

    private void t() {
        this.M.a(this);
    }

    private void u() {
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            this.I = intent.getIntExtra(DJIAccountSignActivity.a, 1003);
            this.k = intent.getBooleanExtra(DJIAccountSignActivity.b, true);
            this.l = intent.getBooleanExtra(DJIAccountSignActivity.c, false);
        }
        if (!this.k) {
            n();
        }
    }

    public void e() {
        v();
        Intent intent = new Intent();
        if (1002 == this.I) {
            intent.setClass(getActivity(), DJIStoreActivity.class);
        } else if (1003 == this.I) {
            getActivity().finish();
            return;
        } else if (1004 == this.I) {
            dji.thirdparty.a.c.a().e(c.SUCCEED);
            getActivity().finish();
            return;
        } else if (1005 == this.I) {
            getActivity().setResult(2);
            getActivity().finish();
            return;
        } else if (3 == this.I) {
            getActivity().setResult(-1);
            getActivity().finish();
            return;
        } else if (1006 == this.I) {
            intent.setClass(getActivity(), DJIFlightRecordActivity.class);
        } else if (-1 == this.I) {
            getActivity().finish();
            return;
        } else if (1008 == this.I) {
            getActivity().setResult(-1);
            getActivity().finish();
            return;
        }
        intent.setFlags(67108864);
        startActivity(intent);
        com.dji.frame.c.b.a(getActivity(), 3);
        getActivity().finish();
    }

    private boolean b(String str) {
        if (str != null && str.length() >= 6 && str.length() <= 20) {
            return true;
        }
        Toast.makeText(getActivity().getApplicationContext(), R.string.home_account_pwd_length_none, 0).show();
        return false;
    }

    private void v() {
        dji.dbox.upgrade.p4.a.a.l = f.getInstance().n();
        dji.pilot.b.a.a(getActivity().getApplicationContext(), f.getInstance().f().g + "_" + f.getInstance().f().j, f.getInstance().f().i, f.getInstance().f().H);
    }

    private void w() {
        this.u.setEnabled(false);
        this.G.setVisibility(0);
    }

    private void x() {
        this.u.setEnabled(true);
        this.G.setVisibility(8);
    }

    private void y() {
        v();
        Intent intent = new Intent();
        intent.setClass(getActivity(), DJIEditProfileActivity.class);
        intent.putExtra(DJIAccountSignActivity.a, this.I);
        intent.setFlags(67108864);
        intent.putExtra(DJIEditProfileActivity.a, true);
        startActivityForResult(intent, 0);
        com.dji.frame.c.b.a(getActivity(), 3);
        getActivity().finish();
    }

    public void onEventMainThread(dji.pilot2.publics.a.a.a aVar) {
        if (aVar == dji.pilot2.publics.a.a.a.a) {
            Intent intent = new Intent(getActivity(), DJILegalAgreement.class);
            intent.putExtra(DJILegalAgreement.a, false);
            startActivity(intent);
        }
    }

    private void z() {
        if (1005 == this.I) {
            getActivity().setResult(2);
        } else if (1004 == this.I) {
            dji.thirdparty.a.c.a().e(c.FAIL);
        } else {
            getActivity().setResult(0);
        }
        getActivity().finish();
    }
}
