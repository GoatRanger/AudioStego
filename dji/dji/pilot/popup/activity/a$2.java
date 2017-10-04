package dji.pilot.popup.activity;

import android.view.View;
import android.view.View.OnClickListener;

class a$2 implements OnClickListener {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    public void onClick(View view) {
        if (a.b(this.a) != null) {
            a.b(this.a).onClick();
            this.a.dismiss();
        }
    }
}
