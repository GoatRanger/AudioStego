package dji.pilot.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import dji.log.DJILogHelper;
import dji.midware.usb.P3.DJIUsbAccessoryReceiver;

public class DJIAoaActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(new View(this));
        Intent intent;
        if (DJILauncherActivity.a) {
            DJILogHelper.getInstance().LOGE("", "--------------DJIAoaActivity onCreate-------------", true, true);
            intent = getIntent();
            if (intent != null) {
                String action = intent.getAction();
                DJILogHelper.getInstance().LOGE("", "action=" + action, true, true);
                if (action == "android.hardware.usb.action.USB_ACCESSORY_ATTACHED" || action == "android.intent.action.MAIN") {
                    intent = new Intent();
                    intent.setAction(DJIUsbAccessoryReceiver.ACTION_USB_ACCESSORY_ATTACHED);
                    sendBroadcast(intent);
                    DJILogHelper.getInstance().LOGE("", "action=send", true, true);
                }
            }
        } else {
            intent = new Intent(this, DJILauncherActivity.class);
            intent.setFlags(268468224);
            intent.putExtra(DJILauncherActivity.b, true);
            startActivity(intent);
        }
        finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        DJILogHelper.getInstance().LOGE("", "---------------DJIAoaActivity onDestroy---------------", true, true);
    }
}
