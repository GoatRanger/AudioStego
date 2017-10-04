package dji.phone.set.gimbalplan;

import android.content.Context;
import android.view.MotionEvent;
import dji.phone.set.gimbalplan.a.a;
import dji.phone.set.gimbalplan.a.c;

public class b implements c {
    private Context a;
    private DJILPGimbalRotationPlanView b;

    public b(Context context, DJILPGimbalRotationPlanView dJILPGimbalRotationPlanView) {
        this.a = context;
        this.b = dJILPGimbalRotationPlanView;
        dji.thirdparty.a.c.a().a(this);
        this.b.setPresenter(this);
    }

    public void onEventMainThread(dji.phone.set.gimbalplan.a.b bVar) {
        switch (bVar) {
            case SHOW:
                this.b.show();
                return;
            case HIDE:
                this.b.hide();
                return;
            default:
                this.b.hide();
                return;
        }
    }

    public void onEventMainThread(a aVar) {
        switch (aVar) {
            case STRENGTHEN:
                this.b.strengthenAlpha();
                return;
            case WEAKEN:
                this.b.weakenAlpha();
                return;
            default:
                return;
        }
    }

    private void b() {
        if (dji.pilot.phonecamera.a.c.a().h()) {
            this.b.setAngularSpeedWheelViewGoneIfVisible();
        }
    }

    public void a() {
        dji.thirdparty.a.c.a().d(this);
    }

    public void a(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 1:
                b();
                return;
            default:
                return;
        }
    }
}
