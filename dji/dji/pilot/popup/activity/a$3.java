package dji.pilot.popup.activity;

import android.content.Context;
import dji.pilot.popup.model.PopupModel.Result;
import dji.pilot.publics.objects.g;

class a$3 implements a$a {
    final /* synthetic */ Result a;
    final /* synthetic */ Context b;

    a$3(Result result, Context context) {
        this.a = result;
        this.b = context;
    }

    public void onClick() {
        if (this.a.left_button_command == 1) {
            g.a(this.b, a.a + this.a.id, false);
        }
    }
}
