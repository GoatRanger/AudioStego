package dji.pilot2.flymonitor.model.response;

import java.util.List;

public class FlyLimitAreaModel {
    public List<LimitAreaModel> data;
    public String type;

    public static class PositionModel {
        public double latitude;
        public double longitude;

        public PositionModel(double d, double d2) {
            this.latitude = d;
            this.longitude = d2;
        }
    }
}
