package dji.pilot2.mine.jsonbean;

import java.util.List;

public class NewArtworkListModel {
    public List<NewArtworkModel> items;
    public int status;
    public int total_count;

    public static class AccountModel {
        public String avatar;
        public String id;
        public boolean is_company;
        public boolean is_vip;
        public String name;
    }

    public static class NewArtworkModel {
        public AccountModel account;
        public int comment_count;
        public String country;
        public long created_at;
        public int duration;
        public String duration_str;
        public int favorites_count;
        public String id;
        public String image_url;
        public boolean is_favorited;
        public boolean is_liked;
        public int likes_count;
        public String title;
        public String type;
        public long updated_at;
        public String video_status;
        public int views_count;
    }
}
