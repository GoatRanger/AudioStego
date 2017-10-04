package dji.pilot2.mine.activity;

import android.os.Handler;
import android.os.Message;
import com.here.odnp.config.OdnpConfigStatic;

public class NetworkStatActivity$a extends Handler {
    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                if (message.obj instanceof Runnable) {
                    ((Runnable) message.obj).run();
                }
                Message obtainMessage = obtainMessage();
                obtainMessage.what = 1;
                obtainMessage.obj = message.obj;
                sendMessageDelayed(obtainMessage, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                return;
            default:
                return;
        }
    }
}
