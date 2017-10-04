package dji.pilot2.publics.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import dji.pilot.publics.objects.g;
import dji.pilot2.mine.activity.NetworkStatActivity;

public class NetworkStatShutdownReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.ACTION_SHUTDOWN")) {
            g.a(context, NetworkStatActivity.t, false);
        }
    }
}
