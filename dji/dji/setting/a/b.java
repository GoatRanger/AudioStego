package dji.setting.a;

import android.content.Context;
import android.widget.TextView;
import dji.pilot.setting.ui.R;

public class b {
    public static void a(Context context, TextView textView, boolean z) {
        if (z) {
            textView.setTextColor(context.getResources().getColor(R.color.setting_ui_edittext_selected));
        } else {
            textView.setTextColor(context.getResources().getColor(R.color.setting_ui_edittext_invalid));
        }
    }
}
