package dji.setting.ui.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import dji.pilot.setting.ui.R;

public class b extends ProgressDialog {
    protected b(Context context) {
        super(context, R.style.setting_ui_dialog);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (a.a != -1126) {
            getWindow().setFlags(8, 8);
            getWindow().getDecorView().setSystemUiVisibility(a.a);
        }
    }

    public void show() {
        super.show();
        if (a.a != -1126) {
            getWindow().clearFlags(8);
        }
    }
}
