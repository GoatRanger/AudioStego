package dji.pilot.flyunlimit.jsonbean;

import java.util.List;

public class DJIUnlimitUserVerifyResult {
    public String country = "";
    public List<VerifyData> data;
    public VerifyExtra extra;
    public String signature = "";
    public long status;
    public boolean success;
    public long time;
    public String type = "";
    public String url = "";
    public String url_key = "";

    public static class VerifyData {
        public String account;
        public String address;
        public String agreed;
        public String company;
        public String created_at;
        public String date;
        public String ext1;
        public String ext2;
        public int id;
        public String name;
        public String title;
        public String updated_at;
    }

    public static class VerifyExtra {
        public String msg;
        public String user_id;
    }
}
