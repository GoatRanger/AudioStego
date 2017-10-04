package dji.pilot.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.usb.P3.DJIUsbAccessoryReceiver;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.usercenter.b.f;
import dji.pilot2.main.activity.DJILegalAgreement;
import dji.pilot2.main.activity.DJIMainFragmentActivity;
import dji.pilot2.mine.b.e;
import dji.pilot2.publics.a.a;
import dji.pilot2.publics.a.b;
import dji.pilot2.whatsnew.acitivty.WhatsNewActivity;
import dji.thirdparty.a.c;

public class DJILauncherActivity extends Activity {
    public static boolean a = false;
    public static final String b = "reinit_changelanguage";
    private static boolean d = false;
    private Handler c;

    protected void onCreate(Bundle bundle) {
        Log.e(DJIApplication.a, "step 1 DJILauncherActivity  onCreate " + System.currentTimeMillis());
        super.onCreate(bundle);
        if (!c.a().c((Object) this)) {
            c.a().a((Object) this);
        }
        setContentView(new View(this));
        a = true;
        this.c = new Handler();
        a();
    }

    protected void onResume() {
        super.onResume();
    }

    private void a() {
        Intent intent = getIntent();
        boolean booleanExtra = intent.getBooleanExtra(b, false);
        DJILogHelper.getInstance().init(getApplicationContext());
        b.getInstance().a(getBaseContext());
        ServiceManager.getInstance();
        if (booleanExtra) {
            startActivity(new Intent(getBaseContext(), DJIMainFragmentActivity.class));
        } else if (e.getInstance().b()) {
            if (f.getInstance().c()) {
                startActivity(new Intent(this, WhatsNewActivity.class));
            } else if (a.getInstance().a()) {
                startActivity(new Intent(this, DJILegalAgreement.class));
            }
        } else if (a.getInstance().a()) {
            startActivity(new Intent(this, DJILegalAgreement.class));
        } else {
            startActivity(new Intent(this, DJISplashActivity.class));
        }
        DJILogHelper.getInstance().LOGE("", "--------------DJIAoaActivity onCreate 1-------------", true, true);
        if (intent != null) {
            String action = intent.getAction();
            DJILogHelper.getInstance().LOGE("", "action=" + action, true, true);
            if (action == "android.hardware.usb.action.USB_ACCESSORY_ATTACHED" || action == "android.intent.action.MAIN" || booleanExtra) {
                intent = new Intent();
                intent.setAction(DJIUsbAccessoryReceiver.ACTION_USB_ACCESSORY_ATTACHED);
                sendBroadcast(intent);
                DJILogHelper.getInstance().LOGE("", "action=send", true, true);
            }
        }
    }

    public void onEventMainThread(dji.pilot2.main.activity.a aVar) {
        if (aVar.equals(dji.pilot2.main.activity.a.FINISH_LAUNCHER) && d) {
            d = false;
            finish();
        }
    }

    protected void onStart() {
        super.onStart();
        d = true;
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (c.a().c((Object) this)) {
            c.a().d((Object) this);
        }
        DJILogHelper.getInstance().LOGE("", "---------------DJIAoaActivity onDestroy---------------", true, true);
    }
}
