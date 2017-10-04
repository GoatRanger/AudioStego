package dji.playback.entryActivity;

import android.content.Intent;
import dji.playback.entryActivity.a.a;
import dji.thirdparty.afinal.FinalActivity;

public class DJIPlaybackMainActivity extends FinalActivity {
    public static final String a = "intent_filename";
    private DJIPlaybackFragment b;
    private a c;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.TargetApi(19)
    protected void onCreate(android.os.Bundle r6) {
        /*
        r5 = this;
        super.onCreate(r6);
        r0 = 2130969561; // 0x7f0403d9 float:1.7547807E38 double:1.0528388524E-314;
        r5.setContentView(r0);
        r1 = dji.pilot.storage.a.e(r5);
        r0 = r5.getIntent();
        if (r0 == 0) goto L_0x0023;
    L_0x0013:
        r2 = "intent_filename";
        r0 = r0.getStringExtra(r2);
        if (r0 == 0) goto L_0x0023;
    L_0x001b:
        r2 = "";
        r0 = r0.equals(r2);
        if (r0 != 0) goto L_0x0023;
    L_0x0023:
        r0 = r5.getFragmentManager();
        r2 = 2131362066; // 0x7f0a0112 float:1.8343902E38 double:1.0530327757E-314;
        r0 = r0.findFragmentById(r2);
        r0 = (dji.playback.entryActivity.DJIPlaybackFragment) r0;
        r5.b = r0;
        r0 = dji.log.DJILogHelper.getInstance();
        r2 = "bob";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "pathString = ";
        r3 = r3.append(r4);
        r3 = r3.append(r1);
        r3 = r3.toString();
        r0.LOGI(r2, r3);
        r0 = new dji.playback.entryActivity.e;
        r2 = r5.b;
        r0.<init>(r2, r1);
        r5.c = r0;
        r0 = r5.b;
        r1 = r5.c;
        r0.a(r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.playback.entryActivity.DJIPlaybackMainActivity.onCreate(android.os.Bundle):void");
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
        b.getInstance().a();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}
