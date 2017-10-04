package dji.pilot2.account.sign;

import android.content.Intent;
import android.os.Bundle;
import dji.pilot.R;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.account.sign.a.a;

public class DJIAccountSignActivity extends DJIActivityNoFullScreen {
    public static final int A = 2;
    public static final int B = 3;
    public static final String a = "key_goto";
    public static final String b = "key_type";
    public static final String c = "key_from_nfz";
    public static final int d = 1001;
    public static final int t = 1002;
    public static final int u = 1003;
    public static final int v = 1004;
    public static final int w = 1005;
    public static final int x = 1006;
    public static final int y = 1007;
    public static final int z = 1008;
    private a C;
    private DJIAccountSignFragment D;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_sign);
        a();
    }

    protected void onResume() {
        super.onResume();
    }

    private void a() {
        this.D = (DJIAccountSignFragment) getFragmentManager().findFragmentById(R.id.cgi);
        this.C = new b(this.D, this);
    }

    protected void onStart() {
        super.onStart();
        this.C.a(0);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.C.b();
    }

    public void onBackPressed() {
        this.D.a();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case -1:
                this.D.e();
                return;
            default:
                return;
        }
    }
}
