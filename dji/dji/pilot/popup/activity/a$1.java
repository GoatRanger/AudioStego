package dji.pilot.popup.activity;

import android.view.View;
import android.view.View.OnClickListener;

class a$1 implements OnClickListener {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void onClick(View view) {
        if (a.a(this.a) != null) {
            a.a(this.a).onClick();
            this.a.dismiss();
        }
    }
}
