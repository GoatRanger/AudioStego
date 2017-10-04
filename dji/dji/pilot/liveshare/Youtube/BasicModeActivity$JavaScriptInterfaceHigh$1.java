package dji.pilot.liveshare.Youtube;

import android.util.Log;
import dji.pilot.liveshare.Youtube.BasicModeActivity.JavaScriptInterfaceHigh;
import dji.pilot2.share.c.d;

class BasicModeActivity$JavaScriptInterfaceHigh$1 implements d {
    final /* synthetic */ JavaScriptInterfaceHigh this$1;

    BasicModeActivity$JavaScriptInterfaceHigh$1(JavaScriptInterfaceHigh javaScriptInterfaceHigh) {
        this.this$1 = javaScriptInterfaceHigh;
    }

    public void onTokenGet(String str) {
        Log.i("BasicMode", "accessToken 1 " + str);
        if (!str.isEmpty()) {
            this.this$1.this$0.getUserInfo();
        }
    }
}
