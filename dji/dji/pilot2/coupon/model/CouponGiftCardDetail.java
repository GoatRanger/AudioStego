package dji.pilot2.coupon.model;

import java.util.List;

public class CouponGiftCardDetail {
    public Data data;
    public int status;

    public static class Data {
        public String code;
        public String coupon_type;
        public DiscountParam discount_params;
        public String discount_type;
        public String end_at;
        public int id;
        public String limit_desc;
        public String owner_name;
        public String share_desc;
        public String share_sms;
        public String share_title;
        public String start_at;
        public String status;
        public String subtitle;
        public String title;
    }

    public static class DiscountParam {
        public List<String> countries;
        public String currency;
        public String desc;
        public int least_cost;
        public String least_cost_format;
        public int off;
        public int reduce_cost;
        public String reduce_cost_format;
    }
}
