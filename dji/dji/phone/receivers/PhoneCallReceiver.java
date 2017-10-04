package dji.phone.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import dji.phone.e.a.a;
import dji.phone.e.b;
import dji.thirdparty.a.c;

public class PhoneCallReceiver extends BroadcastReceiver {
    private static boolean incomingFlag = false;
    final PhoneStateListener listener = new PhoneStateListener(this) {
        final /* synthetic */ PhoneCallReceiver a;

        {
            this.a = r1;
        }

        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
            switch (i) {
                case 0:
                    if (PhoneCallReceiver.incomingFlag) {
                        Log.i("PhoneReceiver", "CALL IDLE");
                        return;
                    }
                    return;
                case 1:
                    PhoneCallReceiver.incomingFlag = true;
                    c.a().e(new b(a.ACTION_PHONE_CALL, dji.phone.e.a.c.NONE));
                    Log.i("PhoneReceiver", "CALL IN RINGING :" + str);
                    return;
                case 2:
                    if (PhoneCallReceiver.incomingFlag) {
                        Log.i("PhoneReceiver", "CALL IN ACCEPT :" + str);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            incomingFlag = false;
            Log.d("PhoneReceiver", "phoneNum: " + intent.getStringExtra("android.intent.extra.PHONE_NUMBER"));
            return;
        }
        ((TelephonyManager) context.getSystemService("phone")).listen(this.listener, 32);
    }
}
