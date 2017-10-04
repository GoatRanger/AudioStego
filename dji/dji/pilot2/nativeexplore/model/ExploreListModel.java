package dji.pilot2.nativeexplore.model;

import java.util.List;

public class ExploreListModel {
    public List<ExploreItemModel> items;
    public int page;
    public int page_size;
    public int status;
    public String status_msg;
    public int total_count;
    public int total_page;

    public static class ExploreItemModel {
        public String account_avatar;
        public String account_city;
        public String account_country;
        public String account_id;
        public String account_location;
        public String account_name;
        public String camera;
        public String city;
        public int comment_count;
        public String country;
        public long created_at;
        public String description;
        public int duration;
        public String embed_url;
        public String id;
        public String image_status;
        public boolean is_360;
        public boolean is_favorited;
        public boolean is_follow;
        public boolean is_liked;
        public boolean is_public;
        public double lat;
        public int likes_count;
        public double lng;
        public String original_url;
        public String page_link;
        public String province;
        public List<TagModel> tags;
        public int tags_count;
        public String thumb_h_url;
        public String thumb_l_url;
        public String thumb_s_url;
        public String thumbnail_large_url;
        public String thumbnail_medium_url;
        public String thumbnail_small_url;
        public String title;
        public String type;
        public long updated_at;
        public String video_status;
        public int views_count;
    }
}
