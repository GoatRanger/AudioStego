package dji.pilot2.account.forget;

import android.os.Bundle;
import dji.pilot.R;
import dji.pilot2.DJIActivityNoFullScreen;

public class DJIAccountForgetActivity extends DJIActivityNoFullScreen {
    private DJIAccountForgetFragment a;
    private b b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_account_forget);
        a();
    }

    protected void onResume() {
        super.onResume();
    }

    private void a() {
        this.a = (DJIAccountForgetFragment) getFragmentManager().findFragmentById(R.id.c6e);
        this.b = new b(this.a, this);
    }

    protected void onStart() {
        super.onStart();
        this.b.a();
    }

    protected void onStop() {
        super.onStop();
        this.b.b();
    }
}
