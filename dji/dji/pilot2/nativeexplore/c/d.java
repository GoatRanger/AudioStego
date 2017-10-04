package dji.pilot2.nativeexplore.c;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import dji.pilot.R;

public class d extends Dialog {
    Handler a;

    public d(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public d(Context context, int i) {
        super(context, i);
    }

    public d(Context context) {
        super(context, R.style.e);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = new Handler();
        a();
    }

    private void a() {
        Window window = getWindow();
        window.getAttributes().windowAnimations = 16973826;
        window.setFlags(-1, 1024);
        setContentView(R.layout.v2_explore_network_error_notice);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    public void show() {
        super.show();
        this.a.postDelayed(new Runnable(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.isShowing()) {
                    this.a.dismiss();
                }
            }
        }, 1000);
    }
}
