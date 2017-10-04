package dji.pilot2.coupon.coupondetail;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot2.DJIActivityNoFullScreen;

public class DJICouponDetailActivity extends DJIActivityNoFullScreen {
    public static String a = ParamKey.CARDID;
    public static String b = "iteminfo";
    protected DJICouponDetailFragment c;
    protected b d;
    protected String t;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_coupondetail);
        this.c = (DJICouponDetailFragment) getFragmentManager().findFragmentById(R.id.c7i);
        this.d = new b(this.c);
        this.c.a(this.d);
        Intent intent = getIntent();
        if (intent != null) {
            this.t = intent.getStringExtra(a);
            DJILogHelper.getInstance().LOGI("bob", "onCreate mCardID = " + this.t);
        }
    }

    public String a() {
        return this.t;
    }

    protected void onResume() {
        super.onResume();
    }
}
