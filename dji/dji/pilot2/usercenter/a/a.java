package dji.pilot2.usercenter.a;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import dji.pilot.R;
import dji.pilot.flyforbid.FlyforbidUpdateService;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot2.account.forget.DJIAccountForgetActivity;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.mine.activity.WebActivity;
import dji.pilot2.publics.b.a$h;
import dji.pilot2.utils.k;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.Random;

public class a implements a$h {
    private static final int G = 1000;
    private static final int M = -1;
    private static final int N = 0;
    private static final int O = 1;
    private static final String aG = "https://www.skypixel.com/forget_password";
    private static final String aH = k.k();
    private a H = null;
    private c I = null;
    private b J = b.ACTIVE;
    private DJIRelativeLayout K;
    private Context L;
    private DJIStateTextView P;
    private DJITextView aA;
    private ProgressBar aB;
    private AnimatorSet aC = new AnimatorSet();
    private AnimatorSet aD = new AnimatorSet();
    private LayoutTransition aE = new LayoutTransition();
    private final int aF = 300;
    private SpannableString aI = null;
    private SpannableString aJ = null;
    private String aK = null;
    private String aL = null;
    private DJITextView aa;
    private DJITextView ab;
    private DJIRelativeLayout ac;
    private DJILinearLayout ad;
    private DJIRelativeLayout ae;
    private View af;
    private View ag;
    private View ah;
    private EditText ai;
    private EditText aj;
    private EditText ak;
    private View al;
    private EditText am;
    private ImageView an;
    private boolean ao = false;
    private String ap;
    private DJITextView aq;
    private TextWatcher ar = null;
    private OnClickListener as = null;
    private int at = -1;
    private f au = f.getInstance();
    private e$a av = null;
    private OnEditorActionListener aw = null;
    private dji.pilot.publics.widget.k ax = null;
    private int ay = 500;
    private DJIStateImageView az;

    public interface a {
        void a();

        void b();

        void c();
    }

    public interface c {
        void a();
    }

    public enum b {
        NORMAL,
        ACTIVE
    }

    private class d extends ClickableSpan {
        final /* synthetic */ a a;
        private String b;

        public d(a aVar, String str) {
            this.a = aVar;
            this.b = str;
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(this.a.L.getResources().getColor(R.color.jt));
            textPaint.setUnderlineText(false);
        }

        public void onClick(View view) {
            Intent intent = null;
            String str = "";
            Location e = dji.a.a.getInstance().e();
            Intent intent2;
            String B;
            if (this.b.equals(this.a.L.getString(R.string.v2_terms_string))) {
                intent2 = new Intent(this.a.L, DJISupportShareWebviewActivity.class);
                if (e != null) {
                    B = k.B();
                } else {
                    B = k.B();
                }
                intent2.putExtra(DJIWebviewFragment.o, B);
                intent = intent2;
            } else if (this.b.equals(this.a.L.getString(R.string.v2_privacy_string))) {
                intent2 = new Intent(this.a.L, DJISupportShareWebviewActivity.class);
                if (e != null) {
                    B = k.b(true, e.getLatitude(), e.getLongitude());
                } else {
                    B = k.b(false, 0.0d, 0.0d);
                }
                intent2.putExtra(DJIWebviewFragment.o, B);
                intent = intent2;
            }
            if (intent != null) {
                this.a.L.startActivity(intent);
            }
        }
    }

    public a(Context context, DJIRelativeLayout dJIRelativeLayout, b bVar) {
        this.L = context;
        this.K = dJIRelativeLayout;
        this.J = bVar;
        this.ad = (DJILinearLayout) dJIRelativeLayout.findViewById(R.id.c4);
        this.ae = (DJIRelativeLayout) dJIRelativeLayout.findViewById(R.id.bf2);
        this.af = dJIRelativeLayout.findViewById(R.id.c5);
        this.ag = dJIRelativeLayout.findViewById(R.id.c9);
        this.ah = dJIRelativeLayout.findViewById(R.id.cc);
        this.P = (DJIStateTextView) dJIRelativeLayout.findViewById(R.id.c1);
        this.aa = (DJITextView) dJIRelativeLayout.findViewById(R.id.c2);
        this.ab = (DJITextView) dJIRelativeLayout.findViewById(R.id.cg);
        this.ac = (DJIRelativeLayout) dJIRelativeLayout.findViewById(R.id.bf6);
        this.az = (DJIStateImageView) dJIRelativeLayout.findViewById(R.id.ci);
        this.aA = (DJITextView) dJIRelativeLayout.findViewById(R.id.c3);
        this.ai = (EditText) dJIRelativeLayout.findViewById(R.id.c7);
        this.aj = (EditText) dJIRelativeLayout.findViewById(R.id.ca);
        this.ak = (EditText) dJIRelativeLayout.findViewById(R.id.ce);
        this.aB = (ProgressBar) dJIRelativeLayout.findViewById(R.id.ch);
        this.aq = (DJITextView) dJIRelativeLayout.findViewById(R.id.c4d);
        this.al = dJIRelativeLayout.findViewById(R.id.bf3);
        this.am = (EditText) dJIRelativeLayout.findViewById(R.id.bf4);
        this.an = (ImageView) dJIRelativeLayout.findViewById(R.id.bf5);
        this.an.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.e();
            }
        });
        h();
        i();
        l();
        g();
    }

    @SuppressLint({"NewApi"})
    private void g() {
        if (this.L.getResources().getConfiguration().orientation == 2) {
            LayoutParams layoutParams = this.ae.getLayoutParams();
            layoutParams.width = (int) this.L.getResources().getDimension(R.dimen.ge);
            this.ae.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.ac.getLayoutParams();
            layoutParams2.leftMargin = 0;
            layoutParams2.rightMargin = 0;
            int paddingTop = this.aa.getPaddingTop();
            this.aa.setPadding(0, paddingTop, paddingTop, paddingTop);
        }
    }

    public void a(boolean z) {
        if (!z) {
            p();
        }
    }

    private void h() {
        if (this.J == b.ACTIVE) {
            this.az.go();
        } else {
            this.az.show();
        }
    }

    public void a(a aVar) {
        this.H = aVar;
    }

    public void a(c cVar) {
        this.I = cVar;
    }

    public void a() {
        if (this.J != b.ACTIVE && this.au.c()) {
            a((int) dji.pilot.usercenter.protocol.d.l);
        }
        this.au.a(this.av);
    }

    public void b() {
        this.au.b(this.av);
    }

    private void i() {
        this.ar = new TextWatcher(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
            }
        };
        this.aw = new OnEditorActionListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (textView == this.a.ak && i == 1000) {
                    this.a.t();
                    return true;
                } else if (textView != this.a.aj || i != 1000) {
                    return false;
                } else {
                    this.a.t();
                    return true;
                }
            }
        };
        this.as = new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.c1:
                        this.a.o();
                        this.a.n();
                        return;
                    case R.id.c2:
                        if (this.a.at == 0) {
                            this.a.r();
                            return;
                        }
                        return;
                    case R.id.cg:
                        this.a.t();
                        return;
                    case R.id.ci:
                        if (this.a.H != null) {
                            this.a.H.c();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        this.av = new e$a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(int i, long j, long j2, int i2, Object obj) {
            }

            public void a(int i, int i2, int i3, Object obj, Object obj2) {
                this.a.j();
                this.a.a(i);
            }

            public void a(int i, boolean z, int i2, Object obj) {
            }

            public void a(int i, int i2, int i3, Object obj) {
                this.a.j();
                this.a.a(i, i2, obj);
            }
        };
    }

    private void j() {
        this.P.setEnabled(true);
    }

    private void k() {
        this.P.setEnabled(false);
    }

    private void l() {
        this.P.setOnClickListener(this.as);
        this.aa.setOnClickListener(this.as);
        this.ab.setOnClickListener(this.as);
        this.az.setOnClickListener(this.as);
        this.ai.addTextChangedListener(this.ar);
        this.aj.addTextChangedListener(this.ar);
        this.ak.addTextChangedListener(this.ar);
        this.aj.setOnEditorActionListener(this.aw);
        this.ak.setOnEditorActionListener(this.aw);
        if (this.J == b.NORMAL && this.ai.getText().length() == 0) {
            this.ai.setText(this.au.j());
        }
        this.ak.setImeOptions(MediaHttpDownloader.MAXIMUM_CHUNK_SIZE);
        this.ak.setInputType(129);
        this.ak.setImeActionLabel(this.L.getString(R.string.home_account_signup), 1000);
        m();
        o();
        v();
    }

    private void m() {
        this.ad.setLayoutTransition(this.aE);
        this.aE.setStagger(0, 30);
        this.aE.setStagger(1, 30);
        this.aE.setDuration(300);
        Animator ofFloat = ObjectAnimator.ofFloat(null, "scaleY", new float[]{0.0f, 1.0f});
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.setDuration(300);
        this.aE.setAnimator(2, ofFloat);
        ofFloat = ObjectAnimator.ofFloat(null, "scaleY", new float[]{1.0f, 0.0f});
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.setDuration(200);
        this.aE.setAnimator(3, ofFloat);
        float dimension = this.L.getResources().getDimension(R.dimen.m);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.ac, "translationY", new float[]{-dimension, 0.0f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.P, "translationY", new float[]{-dimension, 0.0f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.aa, "translationY", new float[]{-dimension, 0.0f});
        ofFloat2.setInterpolator(new AccelerateInterpolator());
        ofFloat2.setDuration(200);
        ofFloat3.setInterpolator(new AccelerateInterpolator());
        ofFloat3.setDuration(200);
        ofFloat4.setInterpolator(new AccelerateInterpolator());
        ofFloat4.setDuration(200);
        this.aD.playTogether(new Animator[]{ofFloat2, ofFloat3, ofFloat4});
        ofFloat2 = ObjectAnimator.ofFloat(this.ac, "translationY", new float[]{dimension, 0.0f});
        ofFloat3 = ObjectAnimator.ofFloat(this.P, "translationY", new float[]{dimension, 0.0f});
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.aa, "translationY", new float[]{dimension, 0.0f});
        ofFloat2.setInterpolator(new DecelerateInterpolator());
        ofFloat2.setDuration(300);
        ofFloat3.setInterpolator(new DecelerateInterpolator());
        ofFloat3.setDuration(300);
        ofFloat5.setInterpolator(new DecelerateInterpolator());
        ofFloat5.setDuration(300);
        this.aC.playTogether(new Animator[]{ofFloat2, ofFloat3, ofFloat5});
    }

    private void n() {
        if (this.at == 0) {
            this.aC.start();
        } else if (this.at == 1 || this.at == -1) {
            this.aD.start();
        }
    }

    private void o() {
        if (this.at == 0) {
            p();
        } else if (this.at == 1 || this.at == -1) {
            q();
        }
    }

    public void b(boolean z) {
        if (z) {
            q();
        } else {
            p();
        }
    }

    private void p() {
        this.at = 1;
        this.ah.setVisibility(0);
        this.aj.setImeActionLabel(this.L.getString(R.string.app_next), 5);
        this.aj.setImeOptions(5);
        this.aj.setInputType(129);
        this.ab.setText(R.string.home_account_signup);
        this.P.setText(R.string.home_account_signin);
        this.aq.setText(R.string.v2_create_account_txt);
        w();
    }

    private void q() {
        this.at = 0;
        this.ah.setVisibility(8);
        this.aj.setImeOptions(MediaHttpDownloader.MAXIMUM_CHUNK_SIZE);
        this.aj.setImeActionLabel(this.L.getString(R.string.home_account_signin), 1000);
        this.aj.setInputType(129);
        this.ab.setText(R.string.home_account_signin);
        this.P.setText(R.string.home_account_signup);
        this.aq.setText(R.string.v2_login_title_txt);
        this.aa.setText(R.string.home_account_forgot_pwd);
    }

    public void c() {
        this.P.show();
        this.aa.show();
        this.ad.show();
        this.aA.go();
        b(true);
    }

    private void r() {
        this.L.startActivity(new Intent(this.L, DJIAccountForgetActivity.class));
    }

    private void s() {
        Intent intent = new Intent(this.L, WebActivity.class);
        intent.putExtra("title_text", this.L.getString(R.string.v2_login_terms_privacy_text));
        intent.putExtra(DJIWebviewFragment.o, aH);
        this.L.startActivity(intent);
    }

    private void t() {
        String obj = this.ai.getText().toString();
        if (u() && a(obj)) {
            String obj2 = this.aj.getText().toString();
            if (c(obj2)) {
                String obj3 = this.am.getText().toString();
                if (!this.ao || b(obj3)) {
                    if (this.at == 0) {
                        if (this.ao) {
                            this.au.b(obj, obj2, obj3, this.ap);
                        } else {
                            this.au.b(obj, obj2);
                        }
                        c((int) R.string.home_account_signining);
                    } else if (this.at == 1) {
                        if (a(obj2, this.ak.getText().toString())) {
                            this.au.a(obj, obj2);
                            c((int) R.string.home_account_signuping);
                        } else {
                            return;
                        }
                    }
                    k();
                }
            }
        }
    }

    private boolean u() {
        boolean c = dji.pilot.fpv.d.b.c(this.L.getApplicationContext());
        if (!c) {
            Toast.makeText(this.L.getApplicationContext(), R.string.home_account_nonet, 0).show();
            j();
        }
        return c;
    }

    private boolean a(String str) {
        if (str.length() == 0) {
            Toast.makeText(this.L.getApplicationContext(), R.string.home_account_email_empty, 0).show();
            return false;
        } else if (str.trim().length() <= 128 && dji.pilot.publics.e.d.b(str)) {
            return true;
        } else {
            Toast.makeText(this.L.getApplicationContext(), R.string.home_account_email_illegal, 0).show();
            return false;
        }
    }

    private boolean b(String str) {
        if (str.length() != 0) {
            return true;
        }
        Toast.makeText(this.L.getApplicationContext(), R.string.home_account_input_verification_code, 0).show();
        return false;
    }

    private boolean c(String str) {
        if (str.trim().getBytes().length >= 6 && str.trim().getBytes().length <= 20) {
            return true;
        }
        Toast.makeText(this.L.getApplicationContext(), R.string.home_account_pwd_length_none, 0).show();
        return false;
    }

    private boolean a(String str, String str2) {
        if (str.equals(str2)) {
            return true;
        }
        Toast.makeText(this.L.getApplicationContext(), R.string.home_account_conpwd_error, 0).show();
        return false;
    }

    private void a(int i) {
        if (dji.pilot.usercenter.protocol.d.l == i) {
            x();
            if (this.H != null) {
                this.H.a();
            }
            if (this.J == b.ACTIVE) {
                this.P.go();
                this.aa.go();
                this.ad.go();
                this.aA.setText(this.au.j());
                this.aA.show();
            }
        } else if (dji.pilot.usercenter.protocol.d.m == i) {
            x();
            if (this.I != null) {
                this.I.a();
            }
        }
        FlyforbidUpdateService.d = false;
    }

    private void a(int i, int i2, Object obj) {
        if (this.H != null) {
            this.H.b();
        }
        x();
        if (dji.pilot.usercenter.protocol.d.l == i) {
            if (this.at == 0) {
                String string;
                if (i2 == dji.pilot.usercenter.protocol.d.b || i2 == 500) {
                    string = this.L.getResources().getString(R.string.home_account_pwd_invalid);
                } else if (i2 == 107) {
                    string = this.L.getResources().getString(R.string.home_account_email_illegal);
                } else if (i2 == -1) {
                    string = this.L.getResources().getString(R.string.account_network_error);
                } else if (i2 == 300) {
                    string = this.L.getResources().getString(R.string.home_account_verification_code_invalid);
                } else if (i2 == dji.pilot.usercenter.protocol.d.k) {
                    string = this.L.getResources().getString(R.string.home_account_input_verification_code);
                    f();
                } else if (obj instanceof String) {
                    string = (String) obj;
                } else {
                    string = this.L.getResources().getString(R.string.home_account_sign_fail);
                }
                d(string);
                if (this.ao) {
                    e();
                }
            } else if (this.at != 1) {
            } else {
                if (i2 == 200) {
                    b((int) R.string.home_account_email_illegal);
                } else if (i2 == dji.pilot.usercenter.protocol.d.f) {
                    b((int) R.string.home_account_email_registered);
                } else {
                    b((int) R.string.home_account_sign_fail);
                }
            }
        } else if (dji.pilot.usercenter.protocol.d.m != i) {
        } else {
            if (i2 == 200) {
                b((int) R.string.home_account_email_illegal);
            } else if (i2 == dji.pilot.usercenter.protocol.d.f) {
                b((int) R.string.home_account_email_registered);
            } else {
                b((int) R.string.home_account_singup_fail);
            }
        }
    }

    private void v() {
        String string = this.L.getString(R.string.v2_terms_and_privacy_main);
        String string2 = this.L.getString(R.string.v2_terms_string);
        String string3 = this.L.getString(R.string.v2_privacy_string);
        string = String.format(string, new Object[]{string2, string3});
        this.aK = string.substring(0, string.indexOf(string2));
        this.aL = string.substring(string.indexOf(string2) + string2.length(), string.indexOf(string3));
        this.aI = new SpannableString(string2);
        this.aI.setSpan(new d(this, string2), 0, string2.length(), 33);
        this.aJ = new SpannableString(string3);
        this.aJ.setSpan(new d(this, string3), 0, string3.length(), 33);
        this.aa.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void w() {
        this.aa.setText(this.aK);
        this.aa.append(this.aI);
        this.aa.append(this.aL);
        this.aa.append(this.aJ);
        this.aa.append("\n");
    }

    private void b(int i) {
        Toast.makeText(this.L.getApplicationContext(), i, 0).show();
    }

    private void d(String str) {
        Toast.makeText(this.L.getApplicationContext(), str, 0).show();
    }

    private void c(int i) {
        if (!this.aB.isShown()) {
            this.aB.setVisibility(0);
        }
        this.ak.setEnabled(false);
        this.ai.setEnabled(false);
        this.aj.setEnabled(false);
    }

    private void x() {
        if (this.aB.isShown()) {
            this.aB.setVisibility(8);
        }
        this.ak.setEnabled(true);
        this.ai.setEnabled(true);
        this.aj.setEnabled(true);
    }

    public void d() {
        o();
        n();
    }

    public void e() {
        this.ap = z();
        String str = "https://www.skypixel.com/api/website/validate_code_img?key=" + this.ap;
        this.am.setText("");
        com.dji.frame.c.c.a(this.L).a(this.an, str);
    }

    public void f() {
        this.ao = true;
        y();
    }

    private void y() {
        e();
        this.am.setText("");
        if (this.al.getVisibility() != 0) {
            this.al.setVisibility(0);
        }
    }

    private String z() {
        String str = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            stringBuffer.append(str.charAt(random.nextInt(36)));
        }
        return stringBuffer.toString();
    }
}
