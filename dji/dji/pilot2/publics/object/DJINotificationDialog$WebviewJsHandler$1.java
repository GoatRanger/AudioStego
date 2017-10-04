package dji.pilot2.publics.object;

import android.content.Intent;
import dji.pilot2.mine.activity.WebActivity;
import dji.pilot2.publics.object.DJINotificationDialog.WebviewJsHandler;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;

class DJINotificationDialog$WebviewJsHandler$1 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ WebviewJsHandler b;

    DJINotificationDialog$WebviewJsHandler$1(WebviewJsHandler webviewJsHandler, String str) {
        this.b = webviewJsHandler;
        this.a = str;
    }

    public void run() {
        Intent intent = new Intent(DJINotificationDialog.m(this.b.this$0), WebActivity.class);
        intent.putExtra(DJIWebviewFragment.o, this.a);
        DJINotificationDialog.n(this.b.this$0).startActivity(intent);
    }
}
