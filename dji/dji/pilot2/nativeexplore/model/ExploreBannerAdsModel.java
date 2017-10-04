package dji.pilot2.nativeexplore.model;

import java.util.List;

public class ExploreBannerAdsModel {
    public int activities_count;
    public List<AdsModel> ads;
    public int ads_count;
    public List<BannerModel> banners;
    public int banners_count;
    public List<ExploreActivityModel> event_banners;
    public int status;

    public static class AdsModel {
        public String mobile_img;
        public String mobile_md5;
        public String name;
        public String pad_img;
        public String pad_md5;
        public int position;
        public String target_url;
    }

    public static class BannerModel {
        public String mobile_img;
        public String mobile_md5;
        public String name;
        public int order;
        public String pad_img;
        public String pad_md5;
        public String target_url;
    }

    public static class ExploreActivityModel {
        public String mobile_img;
        public String name;
        public int order;
        public String pad_img;
        public String target_url;
    }
}
