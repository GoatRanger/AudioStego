package dji.pilot2.simulator;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.pilot.R;
import dji.pilot.publics.widget.a;

class d$4 implements Runnable {
    final /* synthetic */ d a;

    d$4(d dVar) {
        this.a = dVar;
    }

    public void run() {
        if (d.b(this.a) == null) {
            d.a(this.a, a.a(d.a(this.a), "", "", "", null));
        }
        d.b(this.a).a((int) R.string.v2_smlt_disconnect);
        d.b(this.a).b(d.a(this.a).getResources().getString(R.string.v2_smlt_disconnect_content));
        d.b(this.a).d(null);
        d.b(this.a).b((int) R.string.app_enter);
        d.b(this.a).a();
        d.b(this.a).a(new OnClickListener(this) {
            final /* synthetic */ d$4 a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                d.b(this.a.a).dismiss();
                ((DJISimulatorActivity) d.a(this.a.a)).q();
            }
        });
        d.b(this.a).show();
    }
}
