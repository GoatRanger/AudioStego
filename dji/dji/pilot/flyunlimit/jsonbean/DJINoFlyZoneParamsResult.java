package dji.pilot.flyunlimit.jsonbean;

import java.util.List;

public class DJINoFlyZoneParamsResult {
    public String country = "";
    public List<ParamsData> data;
    public ParamsExtra extra;
    public String mark = "";
    public String signature = "";
    public long status;
    public boolean success;
    public long time;
    public String type = "";
    public String url = "";
    public String url_key = "";

    public static class ParamsData {
        public int id;
    }

    public static class ParamsExtra {
        public int geo_status;
        public String msg = "";
    }
}
