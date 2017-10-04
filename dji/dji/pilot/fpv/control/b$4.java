package dji.pilot.fpv.control;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;

class b$4 implements AnimationListener {
    final /* synthetic */ b a;

    b$4(b bVar) {
        this.a = bVar;
    }

    public void onAnimationStart(Animation animation) {
        b.a(this.a, true);
        DJILogHelper.getInstance().LOGE("", "savingAnim start", false, true);
        if (b.n(this.a) == MODE.RECORD) {
            b.j(this.a).hide();
            b.i(this.a).show();
        } else if (b.n(this.a) == MODE.TAKEPHOTO) {
            this.a.i.show();
            this.a.j.hide();
        }
    }

    public void onAnimationRepeat(Animation animation) {
        b.a(this.a, true);
    }

    public void onAnimationEnd(Animation animation) {
        b.a(this.a, false);
        DJILogHelper.getInstance().LOGE("", "savingAnim end", false, true);
    }
}
