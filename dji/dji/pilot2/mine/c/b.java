package dji.pilot2.mine.c;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import dji.pilot.R;

public class b extends Dialog {
    Handler a = null;

    public b(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        a();
    }

    public b(Context context, int i) {
        super(context, i);
        a();
    }

    public b(Context context) {
        super(context, R.style.c6);
        a();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_profile_problem_dialog_layout);
        Window window = getWindow();
        window.getAttributes().dimAmount = 0.0f;
        window.getAttributes().windowAnimations = 16973828;
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    private void a() {
        this.a = new Handler();
    }

    public void show() {
        super.show();
        this.a.postDelayed(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.dismiss();
            }
        }, 2000);
    }
}
