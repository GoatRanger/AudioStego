package dji.pilot.popup.activity;

import android.content.Context;
import android.content.Intent;
import dji.pilot.popup.c.a;
import dji.pilot.popup.model.PopupModel.Result;

class a$4 implements a$b {
    final /* synthetic */ Context a;
    final /* synthetic */ Result b;
    final /* synthetic */ Context c;

    a$4(Context context, Result result, Context context2) {
        this.a = context;
        this.b = result;
        this.c = context2;
    }

    public void onClick() {
        a.a(this.a, "model.right_button_command=" + this.b.right_button_command);
        if (2 == this.b.right_button_command) {
            PopupHtmlActivity.a = this.b.jump_url;
            Intent intent = new Intent(this.c, PopupHtmlActivity.class);
            intent.setFlags(268435456);
            this.c.startActivity(intent);
        }
    }
}
