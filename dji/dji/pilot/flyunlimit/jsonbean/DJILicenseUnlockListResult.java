package dji.pilot.flyunlimit.jsonbean;

import java.util.List;

public class DJILicenseUnlockListResult {
    public List<ListData> data;
    public ListExtra extra;
    public String signature = "";
    public int status;
    public boolean success;
    public long time;

    public static class ListData {
        public String account;
        public String areas_id;
        public String areas_type;
        public String begin_at;
        public long begin_time;
        public String city;
        public String country;
        public String disable;
        public String end_at;
        public long end_time;
        public String id;
        public String location;
        public String os = "";
        public String places;
        public String signature;
        public String sn;
        public String status;
        public String timezone;
        public String type;
    }

    public static class ListExtra {
        public String count = "";
        public int page;
    }
}
