package dji.pilot.visual.selfcal;

import android.content.Context;
import android.os.Bundle;
import dji.pilot.R;
import dji.pilot.publics.objects.c;

public class a extends c {
    private DJIVisionSelfCalView a = null;
    private dji.pilot.visual.selfcal.DJIVisionSelfCalView.a b = null;

    public a(Context context) {
        super(context);
        b();
    }

    private void b() {
        setContentView(R.layout.vision_self_calibration_view);
        this.b = new dji.pilot.visual.selfcal.DJIVisionSelfCalView.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(int i) {
                this.a.dismiss();
            }
        };
        this.a = (DJIVisionSelfCalView) findViewById(R.id.d7q);
        this.a.setOnSelfCalListener(this.b);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(-1, -1, 0, 17, false, false);
    }
}
