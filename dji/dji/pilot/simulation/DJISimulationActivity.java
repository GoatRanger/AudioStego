package dji.pilot.simulation;

import android.os.Bundle;
import dji.midware.data.model.P3.DataRcGetSimFlyStatus;
import dji.midware.data.model.P3.DataRcGetSimFlyStatus.FlySimStatus;
import dji.midware.data.model.P3.DataRcSetSimulation;
import dji.midware.data.model.P3.DataRcSimPushParams;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.afinal.a.b.c;

public class DJISimulationActivity extends DJIBaseActivity {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 0;
    private static final int f = 1;
    private static final int g = 4;
    private static final String h = "A:%1$d;T:%2$d;E:%3$d;R:%4$d";
    @c(a = 2131365491)
    private DJITextView i;
    @c(a = 2131365492)
    private DJITextView j;
    @c(a = 2131365493)
    private DJITextView k;
    @c(a = 2131365494)
    private DJITextView l;
    @c(a = 2131365495)
    private DJITextView m;
    @c(a = 2131365496)
    private DJITextView n;
    private DataRcSetSimulation o = DataRcSetSimulation.getInstance();
    private DataRcGetSimFlyStatus p = DataRcGetSimFlyStatus.getInstance();
    private DataRcSimPushParams q = DataRcSimPushParams.getInstance();
    private FlySimStatus r = FlySimStatus.NORMAL;
    private int s = 0;
    private a t = null;

    private void a() {
        this.i.setEnabled(false);
        this.p.start(new 1(this));
    }

    private void a(boolean z) {
        this.k.setEnabled(false);
        if (z) {
            this.o.a().start(new 2(this));
        } else {
            this.o.b().start(new 3(this));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        setContentView(R.layout.simulation_view);
        this.t = new a(this);
        this.k.setEnabled(false);
        a();
        this.i.setOnClickListener(new 4(this));
        this.k.setOnClickListener(new 5(this));
        dji.thirdparty.a.c.a().a((Object) this);
    }

    public void onEventBackgroundThread(DataRcSimPushParams dataRcSimPushParams) {
        if (!this.t.hasMessages(3)) {
            this.t.sendEmptyMessage(3);
        }
    }

    protected void onDestroy() {
        dji.thirdparty.a.c.a().d((Object) this);
        super.onDestroy();
    }
}
