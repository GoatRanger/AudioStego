package dji.pilot2.welcome.fragment;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.pilot.R;
import dji.pilot.usercenter.b.f;
import dji.pilot2.publics.object.b;
import dji.pilot2.utils.k;
import dji.pilot2.welcome.fragment.DJIWebviewFragment.WebBaseJsHandler;

class DJIWebviewFragment$WebBaseJsHandler$2 implements Runnable {
    final /* synthetic */ WebBaseJsHandler a;

    DJIWebviewFragment$WebBaseJsHandler$2(WebBaseJsHandler webBaseJsHandler) {
        this.a = webBaseJsHandler;
    }

    public void run() {
        Builder bVar = new b(this.a.mActivity);
        bVar.setMessage(R.string.mine_settings_confirm_sign_out_detail);
        bVar.setPositiveButton(17039379, new OnClickListener(this) {
            final /* synthetic */ DJIWebviewFragment$WebBaseJsHandler$2 a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                f.getInstance().p();
                k.a(this.a.a.mActivity);
                WebBaseJsHandler.access$100(this.a.a, true);
            }
        });
        bVar.setNegativeButton(17039369, new OnClickListener(this) {
            final /* synthetic */ DJIWebviewFragment$WebBaseJsHandler$2 a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                WebBaseJsHandler.access$100(this.a.a, false);
            }
        });
        bVar.show();
    }
}
