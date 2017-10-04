package dji.pilot2.coupon.couponmain;

import android.annotation.TargetApi;
import android.os.Bundle;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.usercenter.b.f;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.coupon.couponmain.b.a;

public class DJICouponMainActivity extends DJIActivityNoFullScreen {
    protected a a;
    protected c b;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_couponmain);
        this.a = (a) getFragmentManager().findFragmentById(R.id.c7i);
        this.b = new c(this.a, this);
        this.a.a(this.b);
    }

    protected void onResume() {
        super.onResume();
        DJILogHelper.getInstance().LOGI("bob", "Token = " + f.getInstance().n());
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
