package dji.pilot2.welcome.fragment;

import android.content.Intent;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.welcome.fragment.DJIWebviewFragment.WebBaseJsHandler;

class DJIWebviewFragment$WebBaseJsHandler$1 implements Runnable {
    final /* synthetic */ WebBaseJsHandler a;

    DJIWebviewFragment$WebBaseJsHandler$1(WebBaseJsHandler webBaseJsHandler) {
        this.a = webBaseJsHandler;
    }

    public void run() {
        Intent intent = new Intent(this.a.this$0.getActivity(), DJIAccountSignActivity.class);
        intent.putExtra(DJIAccountSignActivity.a, 1004);
        this.a.this$0.startActivity(intent);
    }
}
