package dji.pilot.flyunlimit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.Toast;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.forbid.FlyForbidElement;
import dji.pilot.R;
import dji.pilot.flyunlimit.a.c;
import dji.pilot.flyunlimit.b;
import dji.pilot.flyunlimit.b.g;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.widget.DJIEditText;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.usercenter.b.f;
import dji.publics.DJIUI.DJIRelativeLayout;
import java.util.ArrayList;
import java.util.Iterator;

public class NfzReportErrorView extends DJIRelativeLayout implements a {
    ArrayList<CheckBox> a = new ArrayList();
    private DJIStateTextView b;
    private DJIStateTextView c;
    private DJIStateTextView d;
    private OnClickListener e;
    private CheckBox f;
    private CheckBox g;
    private CheckBox h;
    private DJIEditText i;
    private boolean j = false;

    public NfzReportErrorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void a() {
        this.e = new OnClickListener(this) {
            final /* synthetic */ NfzReportErrorView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                View view2 = (View) this.a.getParent();
                if (view2 instanceof DJIStageView) {
                    switch (view.getId()) {
                        case R.id.bfl:
                            ((DJIStageView) view2).createStageView(R.layout.nfz_report_error_others_view, R.string.nfz_all_title, true);
                            return;
                        case R.id.bfn:
                            ((DJIStageView) view2).destoryStageView(true);
                            return;
                        case R.id.bfo:
                            if (f.getInstance().c()) {
                                this.a.e();
                                return;
                            }
                            Toast.makeText(this.a.getContext(), R.string.fpv_fly_unlimit_not_login_message, 1).show();
                            new c(this.a.getContext()).show();
                            return;
                        default:
                            return;
                    }
                }
            }
        };
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            a();
            b();
        }
    }

    private void b() {
        this.b = (DJIStateTextView) findViewById(R.id.bfn);
        this.c = (DJIStateTextView) findViewById(R.id.bfo);
        this.f = (CheckBox) findViewById(R.id.bfh);
        this.a.add(this.f);
        this.g = (CheckBox) findViewById(R.id.bfi);
        this.a.add(this.g);
        this.h = (CheckBox) findViewById(R.id.bfj);
        this.a.add(this.h);
        this.i = (DJIEditText) findViewById(R.id.bfe);
        this.d = (DJIStateTextView) findViewById(R.id.bfl);
        this.b.setOnClickListener(this.e);
        this.c.setOnClickListener(this.e);
        this.d.setOnClickListener(this.e);
    }

    private String getSubmitedString() {
        String str = "";
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            String str2;
            CheckBox checkBox = (CheckBox) it.next();
            if (checkBox.isChecked()) {
                str2 = str + checkBox.getText() + "\n";
            } else {
                str2 = str;
            }
            str = str2;
        }
        return str;
    }

    private void c() {
        this.j = false;
        this.c.setEnabled(true);
        this.b.setEnabled(true);
    }

    private void d() {
        this.j = true;
        this.c.setEnabled(false);
        this.b.setEnabled(false);
    }

    private void e() {
        final View view = (View) getParent();
        if ((view instanceof DJIStageView) && !this.j) {
            String submitedString = getSubmitedString();
            if (submitedString.length() == 0) {
                Toast.makeText(getContext(), R.string.flight_forbid_report_error_empty_tip, 1).show();
                return;
            }
            d();
            FlyForbidElement curForbidArea = DJIFlyForbidController.getInstance().getCurForbidArea();
            b.getInstance(getContext()).a(curForbidArea.lat, curForbidArea.lng, submitedString, curForbidArea.area_id, new g(this) {
                final /* synthetic */ NfzReportErrorView b;

                public void a() {
                    this.b.c();
                    Toast.makeText(this.b.getContext(), R.string.flight_forbid_report_error_successfully, 1).show();
                    ((DJIStageView) view).stop();
                }

                public void a(int i, String str) {
                    this.b.c();
                    Toast.makeText(this.b.getContext(), str, 1).show();
                }
            });
        }
    }

    public void dispatchOnStart() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((CheckBox) it.next()).setChecked(false);
        }
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
