package dji.pilot2;

import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot2.coupon.c;
import dji.pilot2.coupon.model.CouponGiftCardPopup;
import dji.thirdparty.afinal.f.a;

class DJIFragmentActivityNoFullScreen$8 extends a<String> {
    final /* synthetic */ DJIFragmentActivityNoFullScreen a;

    DJIFragmentActivityNoFullScreen$8(DJIFragmentActivityNoFullScreen dJIFragmentActivityNoFullScreen) {
        this.a = dJIFragmentActivityNoFullScreen;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        DJILogHelper.getInstance().LOGI("bob", "checkAndshowcoupNotice onSuccess = " + str);
        try {
            CouponGiftCardPopup couponGiftCardPopup = (CouponGiftCardPopup) h.b(str, CouponGiftCardPopup.class);
            if (couponGiftCardPopup.status == 0 && couponGiftCardPopup.has_new_giftcard) {
                c.a(this.a, couponGiftCardPopup.data.desc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Throwable th, int i, String str) {
        DJILogHelper.getInstance().LOGI("bob", "checkAndshowcoupNotice onfailure");
    }
}
