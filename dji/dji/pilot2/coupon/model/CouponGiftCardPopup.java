package dji.pilot2.coupon.model;

public class CouponGiftCardPopup {
    public PopData data;
    public boolean has_new_giftcard;
    public int status;

    public static class PopData {
        public String desc;
        public String title;
    }
}
