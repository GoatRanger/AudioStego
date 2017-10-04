package dji.pilot2.academy.model;

import java.util.List;

public class FrequentlyQuestionModel {
    public Data data;
    public int result;

    public static class Cover2 {
        public String cover_183x110;
        public String cover_230x138;
        public String cover_365x219;
        public String cover_770x462;
    }

    public static class Cover {
        public String mini;
        public String retina_mini;
        public String retina_small;
        public String small;
        public String thumb_200;
        public String thumb_290;
        public String thumb_374;
        public String url;
    }

    public static class Data2 {
        public Cover cover;
        public boolean download;
        public List<Faq> faq;
        public List<GroupVideos> group_videos;
        public Live800 live_800;
        public String product_name;
        public String product_slug;
        public String published_at;
        public String title;
    }

    public static class Data {
        public List<Data2> data;
        public int status;
        public boolean success;
    }

    public static class Faq {
        public List<Questions> questions;
        public String title;
    }

    public static class GroupVideos {
        public String author;
        public Cover2 cover;
        public String locale;
        public String location;
        public String published_at;
        public String slug;
        public String title;
        public String video_category;
        public String video_link;
        public List<Object> video_tags;
    }

    public static class Live800 {
        public String day_url;
        public String night_url;
    }

    public static class Questions {
        public String answer;
        public String question;
    }
}
