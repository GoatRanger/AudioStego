package dji.pilot2.coupon.model;

import java.util.List;

public class CouponGiftCardMsg {
    public List<CardItem> data;
    public int status;

    public static class CardItem {
        public CoupOnParam coupon;
        public String created_at;
        public int id;
        public String msg_type;
        public Rebate rebate;
        public String used_by;
    }

    public static class CoupOnParam {
        public String code;
        public String coupon_type;
        public DiscountParam discount_params;
        public String discount_type;
        public String end_at;
        public int id;
        public String limit_desc;
        public String start_at;
        public String status;
        public String title;
    }

    public static class Data {
        public List<CardItem> data;
    }

    public static class DiscountParam {
        public List<String> countries;
        public String currency;
        public int least_cost;
        public String least_cost_format;
        public int off;
        public int reduce_cost;
        public String reduce_cost_format;
    }

    public static class Rebate {
        public String currency;
        public String format;
    }
}
