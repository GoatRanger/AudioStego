package dji.pilot2.share.mode;

import java.util.List;

public class ContestsModel {
    public List<Event> events;
    public int status;

    public static class Event {
        public String cn_mobile_banner_url;
        public String cn_pad_banner_url;
        public String en_mobile_banner_url;
        public String en_pad_banner_url;
        public int id;
        public String learn_more;
        public String title;
    }
}
