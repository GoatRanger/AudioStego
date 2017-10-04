package dji.pilot.flyunlimit.a;

import android.content.Context;
import android.os.Bundle;
import dji.pilot.R;
import dji.pilot.flyforbid.a;
import dji.pilot.flyunlimit.view.NfzConfirmView;
import dji.pilot.fpv.model.b;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.publics.objects.c;
import dji.pilot.publics.objects.g;

public class e extends c {
    private DJIStageView a;

    public e(Context context) {
        super(context);
        b();
    }

    private void b() {
        setContentView(R.layout.dlg_nfz_stage_view);
        this.a = (DJIStageView) findViewById(R.id.se);
        this.a.setOnStageChangeListener(new dji.pilot.fpv.view.DJIStageView.e(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(int i) {
                this.a.dismiss();
                g.a(this.a.N, a.d, System.currentTimeMillis());
            }

            public void a(int i, int i2, int i3) {
                DJIStageView.a currentStageView = this.a.a.getCurrentStageView();
                if (i == 1 && (currentStageView instanceof NfzConfirmView)) {
                    ((NfzConfirmView) currentStageView).setIsType13(true);
                }
            }
        });
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(b.a(this.N, R.dimen.lc), b.a(this.N, R.dimen.lb), 0, 17, false, false);
        this.a.createStageView(R.layout.nfz_confirm_view, R.string.nfz_all_title, false);
    }

    protected void onStart() {
        super.onStart();
        this.a.dispatchOnStart(false);
    }

    protected void onStop() {
        this.a.dispatchOnStop(false);
        super.onStop();
    }
}
