package dji.pilot2.flymonitor.model.request;

import dji.pilot2.flymonitor.model.response.FlyLimitAreaModel.PositionModel;

public class FlyOrderModel {
    public static final int DEFAULT_FLYING_HEIGHT = 120;
    public static final String DEFAULT_FLYING_RANGE_TYPE = "circle";
    public static final String DEFAULT_FLYING_TYPE = "娱乐航拍";
    public static final int DEFAULT_RADIUS = 500;
    public static final String PLATFORM_STRING = "android";
    public long begin_at;
    public long date;
    public long end_at;
    public int flying_height = 120;
    public FlyingRangeModel flying_range;
    public String flying_type = DEFAULT_FLYING_TYPE;
    public String order_number;
    public String pilot_contact;
    public String pilot_name;
    public String plant_number;
    public String plant_type;
    public String platform = "android";

    public void setDroneInfo(String str, String str2) {
        this.plant_type = str;
        this.plant_number = str2;
    }

    public void setUserInfo(String str, String str2) {
        this.pilot_name = str;
        this.pilot_contact = str2;
    }

    public void setBeginAtTime(long j) {
        this.begin_at = j;
        this.end_at = this.begin_at + 3600000;
        this.date = this.begin_at;
    }

    public void setFlyingCenter(double d, double d2) {
        this.flying_range = new FlyingRangeModel();
        this.flying_range.type = "circle";
        this.flying_range.radius = 500;
        this.flying_range.center = new PositionModel(d, d2);
    }

    public void setOrderNumber(String str) {
        this.order_number = str;
    }
}
