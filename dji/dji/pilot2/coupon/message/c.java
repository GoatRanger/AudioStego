package dji.pilot2.coupon.message;

import android.os.Handler;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot.usercenter.b.f;
import dji.pilot2.coupon.message.b.a;
import dji.pilot2.coupon.message.b.b;
import dji.pilot2.coupon.model.CouponGiftCardMsg;
import dji.pilot2.utils.k;

public class c implements a {
    protected b a;

    public void a(int i) {
        com.dji.frame.c.c.a().a(k.a(f.getInstance().n(), true), new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                this.a.a.a(true);
            }

            public void a(long j, long j2) {
            }

            public void a(final String str) {
                DJILogHelper.getInstance().LOGI("bob", "getCouponGiftCards onSuccess " + str);
                new Handler().post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public void run() {
                        if (str != null) {
                            CouponGiftCardMsg couponGiftCardMsg = (CouponGiftCardMsg) h.b(str, CouponGiftCardMsg.class);
                            if (!(couponGiftCardMsg == null || couponGiftCardMsg.status != 0 || couponGiftCardMsg.data == null)) {
                                this.b.a.a.a(couponGiftCardMsg.data);
                            }
                        }
                        this.b.a.a.a(false);
                        this.b.a.a.c(true);
                    }
                });
            }

            public void a(Throwable th, int i, String str) {
                DJILogHelper.getInstance().LOGI("bob", "getCouponGiftCards onFailure");
                new Handler().post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a.a(false);
                        this.a.a.a.c(false);
                    }
                });
            }
        });
    }

    public c(b bVar) {
        this.a = bVar;
    }

    public void a() {
        com.dji.frame.c.c.a().b(k.l(f.getInstance().n()), new dji.thirdparty.afinal.f.b("token", f.getInstance().n()), new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                if (this.a.a != null) {
                    this.a.a.b(true);
                }
                DJILogHelper.getInstance().LOGI("bob", "clearMsg suc " + str);
            }

            public void a(Throwable th, int i, String str) {
                if (this.a.a != null) {
                    this.a.a.b(false);
                }
                DJILogHelper.getInstance().LOGI("bob", "clearMsg suc strMsg" + str);
            }
        });
    }
}
