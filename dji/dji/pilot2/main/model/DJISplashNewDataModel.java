package dji.pilot2.main.model;

public class DJISplashNewDataModel {
    public SplashImages last_splash;
    public SplashImages next_splash;
    public int status;

    public static class SplashImages {
        public String author;
        public long created_at;
        public String device;
        public int duration;
        public String mobile_img;
        public String mobile_md5;
        public String pad_img;
        public String pad_md5;
        public String target_url;
        public String time_available;
        public String type;
        public long updated_at;
    }
}
