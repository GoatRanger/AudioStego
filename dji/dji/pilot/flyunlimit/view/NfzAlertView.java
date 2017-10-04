package dji.pilot.flyunlimit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.flyunlimit.a.c;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.d.c$p;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.usercenter.b.f;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class NfzAlertView extends DJIRelativeLayout implements a {
    private DJIStateTextView a;
    private DJIStateTextView b;
    private DJIStateTextView c;
    private TextView d;
    private DJITextView e;
    private OnClickListener f;
    private boolean g = false;

    public NfzAlertView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (DJIStateTextView) findViewById(R.id.t5);
            this.b = (DJIStateTextView) findViewById(R.id.t6);
            this.c = (DJIStateTextView) findViewById(R.id.t4);
            this.e = (DJITextView) findViewById(R.id.t1);
            this.d = (TextView) findViewById(R.id.t3);
            this.d.getPaint().setFlags(8);
            this.c.getPaint().setFlags(8);
            this.f = new OnClickListener(this) {
                final /* synthetic */ NfzAlertView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    View view2 = (View) this.a.getParent();
                    switch (view.getId()) {
                        case R.id.t3:
                            if (view2 instanceof DJIStageView) {
                                ((DJIStageView) view2).createStageView(R.layout.flight_forbid_detail_view, R.string.nfz_all_title, true);
                                return;
                            }
                            return;
                        case R.id.t4:
                            if (view2 instanceof DJIStageView) {
                                ((DJIStageView) view2).createStageView(R.layout.nfz_report_error_view, 2, true);
                                return;
                            }
                            return;
                        case R.id.t5:
                            if (view2 instanceof DJIStageView) {
                                ((DJIStageView) view2).stop();
                                return;
                            }
                            return;
                        case R.id.t6:
                            e.c(c$p.c);
                            if (!b.c(this.a.getContext())) {
                                Toast.makeText(this.a.getContext(), R.string.nfz_retry_txt, 1).show();
                                return;
                            } else if (f.getInstance().c()) {
                                this.a.c();
                                return;
                            } else {
                                new c(this.a.getContext()).show();
                                return;
                            }
                        default:
                            return;
                    }
                }
            };
            this.a.setOnClickListener(this.f);
            this.c.setOnClickListener(this.f);
            this.b.setOnClickListener(this.f);
            this.d.setOnClickListener(this.f);
            e.c(c$p.a);
        }
    }

    public void setContentTv(String str) {
        this.e.setText(str);
    }

    private void a() {
        this.b.setText(R.string.gs_enable_mult_flight_mode_dialog_right_btn);
        this.b.setEnabled(true);
        this.a.setEnabled(true);
        this.g = false;
    }

    private void b() {
        this.b.setText(R.string.nfz_checking_txt);
        this.b.setEnabled(false);
        this.a.setEnabled(false);
        this.g = true;
    }

    private void c() {
        if (!this.g) {
            b();
            final dji.pilot.flyunlimit.b instance = dji.pilot.flyunlimit.b.getInstance(getContext());
            instance.a(new dji.pilot.flyunlimit.b.e(this) {
                final /* synthetic */ NfzAlertView b;

                public void a() {
                    DJILogHelper.getInstance().LOGD("", "DJIUnlimitListener: success", false, true);
                    this.b.a();
                }

                public void a(String str) {
                    Toast.makeText(this.b.getContext(), str, 0).show();
                    DJILogHelper.getInstance().LOGD("", String.format("DJIUnlimitListener error:%d", new Object[]{Integer.valueOf(instance.b())}), false, true);
                    this.b.a();
                }

                public void b() {
                    this.b.a();
                    this.b.d();
                }

                public void c() {
                    this.b.a();
                    this.b.e();
                }
            }, getContext());
        }
    }

    private void d() {
        View view = (View) getParent();
        if (view instanceof DJIStageView) {
            ((DJIStageView) view).createStageView(R.layout.nfz_confirm_view, R.string.nfz_all_title, true);
        }
    }

    private void e() {
        dji.pilot.flyunlimit.a.a aVar = new dji.pilot.flyunlimit.a.a(getContext());
        aVar.a(dji.pilot.flyforbid.b.h());
        aVar.show();
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
