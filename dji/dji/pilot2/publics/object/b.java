package dji.pilot2.publics.object;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import dji.log.DJILogHelper;

public class b extends Builder {
    private final Context a;

    public b(Context context, int i) {
        super(context, i);
        this.a = context;
    }

    public b(Context context) {
        super(context);
        this.a = context;
    }

    @Deprecated
    public AlertDialog create() {
        return super.create();
    }

    public AlertDialog show() {
        AlertDialog show = super.show();
        TextView textView = (TextView) show.findViewById(16908299);
        if (textView == null) {
            DJILogHelper.getInstance().LOGI("Lyric", "messageView == null");
            return show;
        }
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, 0, 0, 0);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(17);
        return show;
    }
}
