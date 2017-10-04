package dji.pilot.flyunlimit.a;

import android.content.Context;
import android.os.Bundle;
import dji.pilot.R;
import dji.pilot.flyunlimit.view.NfzRedAlertView;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.fpv.view.DJIStageView.e;
import dji.pilot.publics.objects.c;

public class b extends c {
    private DJIStageView a;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private String e = "";

    public b(Context context) {
        super(context);
        b();
    }

    private void b() {
        setContentView(R.layout.dlg_nfz_stage_view);
        this.a = (DJIStageView) findViewById(R.id.se);
        this.a.setOnStageChangeListener(new e(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(int i) {
                this.a.dismiss();
            }

            public void a(int i, int i2, int i3) {
                a currentStageView = this.a.a.getCurrentStageView();
                if (currentStageView instanceof NfzRedAlertView) {
                    ((NfzRedAlertView) currentStageView).setContent(this.a.b, this.a.c, this.a.d, this.a.e);
                }
                this.a.a(i2);
            }
        });
    }

    public void a(int i, int i2, int i3, String str) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = str;
    }

    protected void onCreate(Bundle bundle) {
        a((int) R.string.nfz_all_title);
        this.a.createStageView(R.layout.nfz_red_alert_view, R.string.nfz_all_title, false);
    }

    private void a(int i) {
        if (i == 2) {
            a(dji.pilot.fpv.model.b.a(this.N, R.dimen.lc), dji.pilot.fpv.model.b.a(this.N, R.dimen.la), 0, 17, true, false);
            return;
        }
        a(dji.pilot.fpv.model.b.a(this.N, R.dimen.lc), dji.pilot.fpv.model.b.a(this.N, R.dimen.lb), 0, 17, true, false);
    }

    protected void onStart() {
        super.onStart();
        this.a.dispatchOnStart(false);
        a currentStageView = this.a.getCurrentStageView();
        if (currentStageView instanceof NfzRedAlertView) {
            ((NfzRedAlertView) currentStageView).setContent(this.b, this.c, this.d, this.e);
        }
    }

    protected void onStop() {
        this.a.dispatchOnStop(false);
        super.onStop();
    }
}
