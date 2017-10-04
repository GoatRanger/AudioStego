package dji.pilot.liveshare.Youtube;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.webkit.JavascriptInterface;

class BasicModeActivity$JavaScriptInterfaceHigh {
    final /* synthetic */ BasicModeActivity this$0;

    BasicModeActivity$JavaScriptInterfaceHigh(BasicModeActivity basicModeActivity) {
        this.this$0 = basicModeActivity;
    }

    @JavascriptInterface
    public void processHTML(String str) {
        if (str == null || !str.startsWith("<head><head><title>Success code=")) {
            Log.d(this.this$0.TAG, "processHTML: accessToken false");
            Log.i("BasicMode", "accessToken false");
            Bundle bundle = new Bundle();
            bundle.putBoolean("result", false);
            Message message = new Message();
            message.setData(bundle);
            message.what = BasicModeActivity.access$800();
            this.this$0.mHandleForUpdateYoutubeState.sendMessage(message);
            return;
        }
        Log.d(this.this$0.TAG, "processHTML:Success code= ");
        int indexOf = str.indexOf("</title>");
        if (indexOf > 32) {
            BasicModeActivity.access$500().a(str.substring(32, indexOf), new 1(this));
        }
    }
}
