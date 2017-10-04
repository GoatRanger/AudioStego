package dji.pilot2.coupon.couponmain;

import android.content.Context;
import android.os.Handler;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot.usercenter.b.f;
import dji.pilot2.coupon.couponmain.b.a;
import dji.pilot2.coupon.couponmain.b.b;
import dji.pilot2.coupon.model.CouponGiftCards;
import dji.pilot2.utils.k;

public class c implements b {
    protected a a;
    protected Context b;

    public c(a aVar, Context context) {
        this.a = aVar;
        this.b = context;
    }

    public void a(final int i) {
        com.dji.frame.c.c.a().a(k.a(f.getInstance().n(), this.b), new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ c b;

            public void a(boolean z) {
                this.b.a.c();
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                DJILogHelper.getInstance().LOGI("bob", "getCouponGiftCards onSuccess " + str);
                if (str != null) {
                    final CouponGiftCards couponGiftCards = (CouponGiftCards) h.b(str, CouponGiftCards.class);
                    if (!(couponGiftCards == null || couponGiftCards.data == null)) {
                        new Handler().post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                this.b.b.a.a(couponGiftCards);
                                if (couponGiftCards.data.account_info != null) {
                                    this.b.b.a.a(couponGiftCards.data.account_info.format);
                                }
                                if (couponGiftCards.data.has_giftcard_msg) {
                                    this.b.b.a.b();
                                }
                            }
                        });
                    }
                }
                new Handler().post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (i == 1) {
                            this.a.b.a.a();
                        }
                        this.a.b.a.b(true);
                    }
                });
            }

            public void a(Throwable th, int i, String str) {
                new Handler().post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (i == 1) {
                            this.a.b.a.a();
                        }
                        this.a.b.a.b(false);
                    }
                });
                DJILogHelper.getInstance().LOGI("bob", "getCouponGiftCards onFailure errorNo= " + i + " strMsg= " + str);
            }
        });
    }
}
