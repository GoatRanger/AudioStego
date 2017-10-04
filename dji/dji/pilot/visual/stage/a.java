package dji.pilot.visual.stage;

import android.content.Context;
import dji.pilot.R;
import dji.pilot.fpv.flightmode.c;
import dji.pilot.fpv.flightmode.c$b;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.objects.DJIBaseActivity;

public class a extends dji.pilot.fpv.activity.a {
    public a(Context context) {
        super(context);
        this.b.go();
        this.g.createStageView(R.layout.visual_general_view, R.string.app_name, false);
    }

    protected void onStart() {
        super.onStart();
        if (c.getInstance().a() == c$b.TRACK_SELFIE) {
            a(DJIBaseActivity.screenWidth / 3, DJIBaseActivity.screenHeight, 0, 5, true, true);
        } else {
            a(b.a(this.N, R.dimen.fm), DJIBaseActivity.screenHeight, 0, 5, true, true);
        }
        o();
    }
}
