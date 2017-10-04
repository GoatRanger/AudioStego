package dji.pilot2.coupon.model;

import dji.log.DJILogHelper;
import java.io.Serializable;
import java.util.List;

public class CouponGiftCards {
    public Data data;
    public int status;

    public static class AccountInfo {
        public int available;
        public String currency;
        public String format;
        public int level;
    }

    public static class Data {
        public AccountInfo account_info;
        public List<GiftItem> giftcards;
        public boolean has_giftcard_msg;
        public String owner_name;
    }

    public static class DiscountParam {
        public List<String> countries;
        public String currency;
        public int least_cost;
        public String least_cost_format;
        public int off;
        public int reduce_cost;
        public String reduce_cost_format;

        public void printself() {
            DJILogHelper.getInstance().LOGI("bob", "least_cost = " + this.least_cost);
            DJILogHelper.getInstance().LOGI("bob", "reduce_cost = " + this.reduce_cost);
            DJILogHelper.getInstance().LOGI("bob", "off = " + this.off);
            DJILogHelper.getInstance().LOGI("bob", "currency = " + this.currency);
            if (this.countries != null) {
                for (int i = 0; i < this.countries.size(); i++) {
                    DJILogHelper.getInstance().LOGI("bob", "countries i " + ((String) this.countries.get(i)));
                }
            }
        }
    }

    public static class GiftItem implements Serializable {
        public String code;
        public String coupon_type;
        public DiscountParam discount_params;
        public String discount_type;
        public String end_at;
        public int id;
        public String limit_desc;
        public String start_at;
        public String status;
        public String subtitle;
        public String title;
        public String type;
    }

    public void printself() {
        DJILogHelper.getInstance().LOGI("bob", "status = " + this.status);
        DJILogHelper.getInstance().LOGI("bob", "data = " + this.data);
    }
}
