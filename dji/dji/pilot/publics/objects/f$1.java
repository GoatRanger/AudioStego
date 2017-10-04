package dji.pilot.publics.objects;

import android.view.View;
import android.view.View.OnClickListener;

class f$1 implements OnClickListener {
    final /* synthetic */ f a;

    f$1(f fVar) {
        this.a = fVar;
    }

    public void onClick(View view) {
        this.a.cancel();
    }
}
