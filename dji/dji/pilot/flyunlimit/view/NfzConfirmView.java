package dji.pilot.flyunlimit.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.flyunlimit.b;
import dji.pilot.flyunlimit.b.e;
import dji.pilot.fpv.d.c$p;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.usercenter.b.f;
import dji.pilot2.mine.activity.WebActivity;
import dji.pilot2.publics.b.a$f;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class NfzConfirmView extends DJIRelativeLayout implements dji.pilot.fpv.view.DJIStageView.a {
    private DJIStateTextView a;
    private DJIStateTextView b;
    private DJIStateTextView c;
    private DJITextView d;
    private CheckBox e;
    private CheckBox f;
    private View g;
    private OnClickListener h;
    private OnCheckedChangeListener i;
    private boolean j = false;
    private boolean k = false;

    public class a extends ClickableSpan {
        String a;
        String b;
        final /* synthetic */ NfzConfirmView c;

        public a(NfzConfirmView nfzConfirmView, String str, String str2) {
            this.c = nfzConfirmView;
            this.a = str;
            this.b = str2;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.c.getContext(), WebActivity.class);
            intent.putExtra(DJIWebviewFragment.o, this.b);
            this.c.getContext().startActivity(intent);
        }
    }

    public NfzConfirmView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @SuppressLint({"NewApi"})
    public void setIsType13(boolean z) {
        this.j = z;
        if (this.j) {
            this.a.go();
            this.g.setVisibility(8);
            this.d.setTextSize(0, getResources().getDimension(R.dimen.rq));
            this.d.setText(R.string.nfz_confirm_enhance_warning_content_txt);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (DJIStateTextView) findViewById(R.id.bex);
            this.b = (DJIStateTextView) findViewById(R.id.bez);
            this.c = (DJIStateTextView) findViewById(R.id.bev);
            this.d = (DJITextView) findViewById(R.id.bes);
            this.g = findViewById(R.id.bey);
            this.c.setText("");
            String string = getContext().getString(R.string.nfz_confirm_tip_txt);
            CharSequence spannableString = new SpannableString(string);
            spannableString.setSpan(new a(this, string, a$f.k), 0, string.length(), 33);
            this.c.append(spannableString, 0, string.length());
            this.c.setMovementMethod(LinkMovementMethod.getInstance());
            this.e = (CheckBox) findViewById(R.id.bet);
            this.f = (CheckBox) findViewById(R.id.beu);
            this.i = new OnCheckedChangeListener(this) {
                final /* synthetic */ NfzConfirmView a;

                {
                    this.a = r1;
                }

                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z && this.a.e.isChecked() && this.a.f.isChecked()) {
                        this.a.b.setEnabled(true);
                    } else {
                        this.a.b.setEnabled(false);
                    }
                }
            };
            this.h = new OnClickListener(this) {
                final /* synthetic */ NfzConfirmView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    View view2 = (View) this.a.getParent();
                    if (view2 instanceof DJIStageView) {
                        switch (view.getId()) {
                            case R.id.bex:
                                ((DJIStageView) view2).stop();
                                return;
                            case R.id.bez:
                                if (this.a.j) {
                                    DJIFlyForbidController.getInstance().unlockSWArea(DataOsdGetPushCommon.getInstance().getLatitude(), DataOsdGetPushCommon.getInstance().getLongitude(), dji.pilot.flyforbid.a.getInstance(this.a.getContext()).d());
                                    ((DJIStageView) view2).stop();
                                    return;
                                }
                                this.a.a();
                                return;
                            default:
                                return;
                        }
                    }
                }
            };
            this.a.setOnClickListener(this.h);
            this.b.setOnClickListener(this.h);
            this.e.setOnCheckedChangeListener(this.i);
            this.f.setOnCheckedChangeListener(this.i);
            this.b.setEnabled(false);
        }
    }

    private void a() {
        if (!this.k) {
            c();
            final b instance = b.getInstance(getContext());
            instance.a(new e(this) {
                final /* synthetic */ NfzConfirmView b;

                public void a() {
                    this.b.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            dji.pilot.fpv.d.e.c(c$p.g);
                            this.a.b.d();
                            this.a.b.b();
                        }
                    });
                }

                public void c() {
                }

                public void a(String str) {
                    dji.pilot.fpv.d.e.c(c$p.h + instance.b() + " user:" + f.getInstance().j());
                    DJILogHelper.getInstance().LOGD("", "DJIUnlimitListener error:" + instance.b() + ", reason: " + str, false, true);
                    Toast.makeText(this.b.getContext(), str, 1).show();
                    this.b.b();
                }

                public void b() {
                }
            });
        }
    }

    private void b() {
        this.b.setEnabled(true);
        this.a.setEnabled(true);
        this.e.setEnabled(true);
        this.f.setEnabled(true);
        this.k = false;
    }

    private void c() {
        this.b.setEnabled(false);
        this.a.setEnabled(false);
        this.e.setEnabled(false);
        this.f.setEnabled(false);
        this.k = true;
    }

    private void d() {
        View view = (View) getParent();
        if (view instanceof DJIStageView) {
            ((DJIStageView) view).createStageView(R.layout.nfz_set_success_view, R.string.nfz_all_title, true);
        }
    }

    public void dispatchOnStart() {
        this.e.setChecked(false);
        this.f.setChecked(false);
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        this.e.setChecked(false);
        this.f.setChecked(false);
        dji.pilot.flyforbid.a.getInstance(getContext()).d();
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
