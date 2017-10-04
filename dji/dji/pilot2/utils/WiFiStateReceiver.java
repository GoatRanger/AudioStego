package dji.pilot2.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.util.ArrayList;
import java.util.Iterator;

public class WiFiStateReceiver extends BroadcastReceiver {
    private ArrayList<a> a = new ArrayList();

    public interface a {
        void a(String str);

        void e();
    }

    public void a(a aVar) {
        if (this.a != null) {
            this.a.remove(aVar);
        }
    }

    public void b(a aVar) {
        if (this.a != null) {
            this.a.add(aVar);
        }
    }

    public void onReceive(Context context, Intent intent) {
        a aVar;
        if (intent.getAction().equals("android.net.wifi.STATE_CHANGE") && ((NetworkInfo) intent.getParcelableExtra("networkInfo")).getState().equals(State.CONNECTED)) {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                aVar = (a) it.next();
                if (aVar != null) {
                    aVar.a(connectionInfo.getSSID());
                }
            }
        }
        if (intent.getAction().equals("supplicantError")) {
            Iterator it2 = this.a.iterator();
            while (it2.hasNext()) {
                aVar = (a) it2.next();
                if (aVar != null) {
                    aVar.e();
                }
            }
        }
        if (intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
            int intExtra = intent.getIntExtra("wifi_state", 1);
            if (intExtra == 1) {
            }
            if (intExtra != 3) {
            }
        }
    }
}
