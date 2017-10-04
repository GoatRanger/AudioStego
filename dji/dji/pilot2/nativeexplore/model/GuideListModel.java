package dji.pilot2.nativeexplore.model;

import java.util.List;

public class GuideListModel {
    public List<GuideModel> items;
    public int photo_count;
    public int status;
    public List<GuideModel> stories;
    public int story_count;
    public int video_count;

    public static class AccountModel {
        public String avatar;
        public String country_code;
        public String id;
        public boolean is_company;
        public boolean is_follow;
        public boolean is_vip;
        public String name;
    }

    public static class GuideModel {
        public AccountModel account;
        public String bg_image;
        public String city;
        public int comments_count;
        public String country;
        public String cover_image;
        public String description;
        public int favorites_count;
        public String id;
        public int like_count;
        public int nodes_count;
        public String title;
        public String type;
        public int views_count;
    }

    public List<GuideModel> getItems() {
        if (this.stories != null) {
            return this.stories;
        }
        return this.items;
    }
}
