package dji.pilot.usercenter.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import dji.pilot.usercenter.b.a;
import java.util.Locale;

public class DJIMediaReceiver extends BroadcastReceiver {
    private static final String a = "file://";

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String dataString = intent.getDataString();
        if (dataString != null) {
            if (dataString.toLowerCase(Locale.US).startsWith("file://")) {
                dataString = dataString.substring("file://".length());
            }
            if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
                a(dataString);
            } else if ("android.intent.action.MEDIA_REMOVED".equals(action) || "android.intent.action.MEDIA_BAD_REMOVAL".equals(action) || "android.intent.action.MEDIA_EJECT".equals(action) || "android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
                b(dataString);
            }
        }
    }

    private static void a(String str) {
        a.getInstance().d(str);
    }

    private static void b(String str) {
        a.getInstance().e(str);
    }
}
