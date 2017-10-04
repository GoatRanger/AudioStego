package dji.pilot.fpv.model;

import android.content.Context;
import android.widget.TextView;
import dji.pilot.R;

public class p {
    public static void a(Context context, TextView textView, boolean z) {
        if (z) {
            textView.setTextColor(context.getResources().getColor(R.color.om));
        } else {
            textView.setTextColor(context.getResources().getColor(R.color.gj));
        }
    }
}
