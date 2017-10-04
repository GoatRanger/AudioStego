package dji.pilot.flyunlimit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.forbid.FlyForbidElement;
import dji.pilot.R;
import dji.pilot.flyunlimit.a.c;
import dji.pilot.flyunlimit.b;
import dji.pilot.flyunlimit.b.g;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.pilot.usercenter.b.f;

public class NfzReportOthersView extends RelativeLayout implements a {
    private DJIStateTextView a;
    private EditText b;
    private boolean c = false;

    public NfzReportOthersView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (DJIStateTextView) findViewById(R.id.bff);
            this.b = (EditText) findViewById(R.id.bfe);
            findViewById(R.id.bfd).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NfzReportOthersView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    View view2 = (View) this.a.getParent();
                    if (view2 instanceof DJIStageView) {
                        ((DJIStageView) view2).destoryStageView(true);
                    }
                }
            });
            this.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NfzReportOthersView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (f.getInstance().c()) {
                        this.a.c();
                        return;
                    }
                    Toast.makeText(this.a.getContext(), R.string.fpv_fly_unlimit_not_login_message, 1).show();
                    new c(this.a.getContext()).show();
                }
            });
        }
    }

    private void a() {
        this.c = false;
        this.a.setEnabled(true);
    }

    private void b() {
        this.c = true;
        this.a.setEnabled(false);
    }

    private void c() {
        final View view = (View) getParent();
        if ((view instanceof DJIStageView) && !this.c) {
            String obj = this.b.getText().toString();
            if (obj.length() == 0) {
                Toast.makeText(getContext(), R.string.flight_forbid_report_error_empty_tip, 1).show();
                return;
            }
            b();
            FlyForbidElement curForbidArea = DJIFlyForbidController.getInstance().getCurForbidArea();
            b.getInstance(getContext()).a(curForbidArea.lat, curForbidArea.lng, obj, curForbidArea.area_id, new g(this) {
                final /* synthetic */ NfzReportOthersView b;

                public void a() {
                    this.b.a();
                    Toast.makeText(this.b.getContext(), R.string.flight_forbid_report_error_successfully, 1).show();
                    ((DJIStageView) view).stop();
                }

                public void a(int i, String str) {
                    this.b.a();
                    Toast.makeText(this.b.getContext(), str, 1).show();
                }
            });
        }
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
