package dji.pilot2.nativeexplore.model;

import dji.pilot.usercenter.mode.a;
import dji.pilot2.mine.jsonbean.NewArtworkListModel.AccountModel;
import java.util.List;

public class ArtworkDetail {
    public AccountModel account;
    public List<AuthorRecommendeInfo> author_recommende;
    public String category;
    public CommentsInfo comments;
    public String created_at;
    public String description;
    public List<String> equipment;
    public int favorites_count;
    public String full_screen_image;
    public String id;
    public String image;
    public String image_url;
    public boolean is_favorited;
    public boolean is_follow;
    public boolean is_liked;
    public boolean is_transcoding;
    public String latitude;
    public String license;
    public int likes_count;
    public List<String> links;
    public LocationInfo location;
    public String longitude;
    public String mobile_share_url;
    public String play_url;
    public String provider;
    public List<RelatedRecommende> related_recommende;
    public double score;
    public String share_url;
    public int status;
    public List<TagModel> tags;
    public String title;
    public String type;
    public String updated_at;
    public String video_status;
    public int views_count;

    public static class AuthorRecommendeInfo {
        public String id;
        public String image;
    }

    public class CommentsInfo {
        public int current_page;
        public List<ListInfo> list;
        public int page_size;
        public int total_count;
        public int total_page;
    }

    public class ListInfo {
        public a account;
        public String content;
        public String created_at;
        public int id;
        public boolean is_liked;
        public int likes_count;
    }

    public static class LocationInfo {
        public String country;
        public String location;
    }

    public static class RelatedRecommende {
        public a account;
        public int comments_count;
        public String country;
        public long created_at;
        public int favorites_count;
        public String id;
        public String image_url;
        public boolean is_favorited;
        public boolean is_liked;
        public int likes_count;
        public String title;
        public String type;
        public long updated_at;
        public int views_count;
    }
}
