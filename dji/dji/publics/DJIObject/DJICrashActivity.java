package dji.publics.DJIObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class DJICrashActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.e("DJICrashActivity", "DJICrashActivity");
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(268468224);
        startActivity(intent);
        finish();
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
