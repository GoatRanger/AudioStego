package dji.pilot2.coupon.message;

import android.annotation.TargetApi;
import android.os.Bundle;
import dji.pilot.R;
import dji.pilot2.DJIActivityNoFullScreen;

public class DJICouponMessageActivity extends DJIActivityNoFullScreen {
    protected DJICouponMessageFragment a;
    protected c b;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_couponmessage);
        this.a = (DJICouponMessageFragment) getFragmentManager().findFragmentById(R.id.c7i);
        this.b = new c(this.a);
        this.a.a(this.b);
    }
}
