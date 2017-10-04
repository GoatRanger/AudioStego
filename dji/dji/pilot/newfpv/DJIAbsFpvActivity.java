package dji.pilot.newfpv;

import android.os.Bundle;
import dji.pilot.fpv.activity.DJIPreviewActivityBaseForMC;

public abstract class DJIAbsFpvActivity<T extends a> extends DJIPreviewActivityBaseForMC implements e {
    protected T l_ = null;

    protected abstract Class<T> u();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            this.l_ = (a) u().newInstance();
            this.l_.a(this, 0);
            this.l_.f();
            a();
            this.l_.a(b());
        } catch (InstantiationException e) {
            throw new RuntimeException("create FpvPresenter error");
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("create FpvPresenter error");
        }
    }

    protected void onDestroy() {
        this.l_.g();
        super.onDestroy();
    }

    protected void onPause() {
        this.l_.i();
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        this.l_.h();
    }
}
