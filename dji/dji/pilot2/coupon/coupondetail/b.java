package dji.pilot2.coupon.coupondetail;

import com.dji.frame.c.c;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot2.coupon.coupondetail.a.a;
import dji.pilot2.coupon.model.CouponGiftCardDetail;
import dji.pilot2.utils.k;

public class b implements a {
    protected dji.pilot2.coupon.coupondetail.a.b a;

    public void a(String str) {
        c.a().a(k.k(str), new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                this.a.a.a(true);
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                DJILogHelper.getInstance().LOGI("bob", "getCouponGiftCardsDetail onSuccess" + str);
                if (str != null) {
                    CouponGiftCardDetail couponGiftCardDetail = (CouponGiftCardDetail) h.b(str, CouponGiftCardDetail.class);
                    if (!(couponGiftCardDetail == null || couponGiftCardDetail.status != 0 || couponGiftCardDetail.data == null)) {
                        this.a.a.a(couponGiftCardDetail);
                    }
                }
                this.a.a.a(false);
                this.a.a.b(false);
            }

            public void a(Throwable th, int i, String str) {
                DJILogHelper.getInstance().LOGI("bob", "getCouponGiftCardsDetail onFail");
                this.a.a.a(false);
                this.a.a.b(true);
            }
        });
    }

    public b(dji.pilot2.coupon.coupondetail.a.b bVar) {
        this.a = bVar;
    }
}
