package dji.pilot2.scan.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;

public final class c implements OnCancelListener, OnClickListener {
    private final Activity a;

    public c(Activity activity) {
        this.a = activity;
    }

    public void onCancel(DialogInterface dialogInterface) {
        a();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        a();
    }

    private void a() {
        this.a.finish();
    }
}
