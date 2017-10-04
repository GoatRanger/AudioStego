package dji.pilot.flyunlimit.a;

import android.content.Context;
import android.os.Bundle;
import dji.pilot.R;
import dji.pilot.flyunlimit.view.NfzAlertView;
import dji.pilot.fpv.model.b;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.e;
import dji.pilot.publics.objects.c;

public class d extends c {
    public static final int a = 2;
    public static final int b = 3;
    private DJIStageView c;
    private String d = "";

    public enum a {
        GO_TO_CONFIRM_VIEW
    }

    public d(Context context) {
        super(context);
        b();
    }

    private void b() {
        setContentView(R.layout.dlg_nfz_stage_view);
        this.c = (DJIStageView) findViewById(R.id.se);
        this.c.setOnStageChangeListener(new e(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(int i) {
                this.a.dismiss();
                if (i == 3) {
                    this.a.show();
                }
            }

            public void a(int i, int i2, int i3) {
                dji.pilot.fpv.view.DJIStageView.a currentStageView = this.a.c.getCurrentStageView();
                if (i == 1 && (currentStageView instanceof NfzAlertView)) {
                    ((NfzAlertView) currentStageView).setContentTv(this.a.d);
                }
                this.a.a(i2);
            }
        });
    }

    public void a(String str) {
        this.d = str;
    }

    protected void onCreate(Bundle bundle) {
        a((int) R.string.nfz_all_title);
        this.c.createStageView(R.layout.flight_forbid_alert_view, R.string.nfz_all_title, false);
    }

    private void a(int i) {
        if (i == 2) {
            a(b.a(this.N, R.dimen.lc), b.a(this.N, R.dimen.la), 0, 17, true, false);
            return;
        }
        a(b.a(this.N, R.dimen.lc), b.a(this.N, R.dimen.lb), 0, 17, true, false);
    }

    protected void onStart() {
        super.onStart();
        this.c.dispatchOnStart(false);
        dji.pilot.fpv.view.DJIStageView.a currentStageView = this.c.getCurrentStageView();
        if (currentStageView instanceof NfzAlertView) {
            ((NfzAlertView) currentStageView).setContentTv(this.d);
        }
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
    }

    protected void onStop() {
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        this.c.dispatchOnStop(false);
        super.onStop();
    }

    public void onEventMainThread(a aVar) {
        if (aVar.equals(a.GO_TO_CONFIRM_VIEW)) {
            this.c.createStageView(R.layout.nfz_confirm_view, R.string.nfz_all_title, true);
        }
    }
}
