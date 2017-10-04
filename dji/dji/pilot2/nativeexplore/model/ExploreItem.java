package dji.pilot2.nativeexplore.model;

import dji.log.DJILogHelper;
import dji.pilot2.mine.e.a.b;
import dji.pilot2.nativeexplore.model.ExploreBannerAdsModel.AdsModel;
import dji.pilot2.nativeexplore.model.ExploreListModel.ExploreItemModel;
import dji.pilot2.utils.k;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import java.util.List;

public class ExploreItem {
    private static final String EXPLORE_TYPE_PHOTO = "photos";
    private static final String EXPLORE_TYPE_VIDEO = "videos";
    public String adsImageUrl;
    public String adsName;
    public int adsPosition;
    public String adsRedirectUrl;
    public int commentCount;
    public long created_at;
    public String description;
    public String displayImageUrl;
    public int duration;
    public String embedUrl;
    public String id;
    public String image_status;
    public boolean isAdsVisible;
    public boolean isFavorite;
    public boolean isFollowed;
    public boolean isLiked;
    public boolean is_360;
    public int likeCount;
    public String orignalImageUrl;
    public String page_link;
    public String shareUrl;
    public List<TagModel> tags;
    public String title;
    public ExploreType type;
    public long updated_at;
    public String userAvatarUrl;
    public String userCountry;
    public String userId;
    public String userName;
    public String videoStatus;

    public enum ExploreType {
        video,
        photo,
        ads,
        unknown
    }

    public ExploreItem(ExploreItemModel exploreItemModel) {
        this.userName = exploreItemModel.account_name;
        this.userId = exploreItemModel.account_id;
        this.userAvatarUrl = exploreItemModel.account_avatar;
        this.userCountry = exploreItemModel.account_country;
        this.isFollowed = exploreItemModel.is_follow;
        this.id = exploreItemModel.id;
        if (exploreItemModel.type != null && exploreItemModel.type.equals("photos")) {
            this.type = ExploreType.photo;
            this.shareUrl = k.d("photo", this.id);
            this.displayImageUrl = appendHttpIfNeeded(exploreItemModel.thumb_l_url);
            this.orignalImageUrl = appendHttpIfNeeded(exploreItemModel.original_url);
        } else if (exploreItemModel.type == null || !exploreItemModel.type.equals("videos")) {
            this.type = ExploreType.unknown;
        } else {
            this.type = ExploreType.video;
            this.shareUrl = k.d("video", this.id);
            this.displayImageUrl = appendHttpIfNeeded(exploreItemModel.thumbnail_large_url);
            this.embedUrl = appendHttpIfNeeded(exploreItemModel.embed_url);
            this.videoStatus = exploreItemModel.video_status;
        }
        this.is_360 = exploreItemModel.is_360;
        this.page_link = exploreItemModel.page_link;
        this.image_status = exploreItemModel.image_status;
        this.title = exploreItemModel.title;
        this.description = exploreItemModel.description;
        this.duration = exploreItemModel.duration;
        this.isLiked = exploreItemModel.is_liked;
        this.likeCount = exploreItemModel.likes_count;
        this.commentCount = exploreItemModel.comment_count;
        this.tags = exploreItemModel.tags;
        this.isFavorite = exploreItemModel.is_favorited;
        this.created_at = exploreItemModel.created_at;
        this.updated_at = exploreItemModel.updated_at;
    }

    public ExploreItem(AdsModel adsModel) {
        this.type = ExploreType.ads;
        DJIDeviceType deviceType = DJIOriLayout.getDeviceType();
        if (deviceType.equals(DJIDeviceType.Pad)) {
            this.adsImageUrl = appendHttpIfNeeded(adsModel.pad_img);
        } else if (deviceType.equals(DJIDeviceType.Phone)) {
            this.adsImageUrl = appendHttpIfNeeded(adsModel.mobile_img);
        }
        this.adsRedirectUrl = appendHttpIfNeeded(adsModel.target_url);
        this.adsName = adsModel.name;
        this.isAdsVisible = true;
        this.adsPosition = adsModel.position;
    }

    public ExploreItem(ArtworkDetail artworkDetail, int i) {
        if (artworkDetail.type.equals("videos")) {
            this.type = ExploreType.video;
            this.displayImageUrl = appendHttpIfNeeded(artworkDetail.image_url) + b.ThumbnailSize_Large;
            this.shareUrl = appendHttpIfNeeded(artworkDetail.mobile_share_url);
            this.orignalImageUrl = appendHttpIfNeeded(artworkDetail.full_screen_image);
            this.videoStatus = "";
        } else if (artworkDetail.type.equals("photos")) {
            this.type = ExploreType.photo;
            this.displayImageUrl = appendHttpIfNeeded(artworkDetail.image_url) + b.ThumbnailSize_Large;
            this.orignalImageUrl = appendHttpIfNeeded(artworkDetail.full_screen_image);
            this.shareUrl = appendHttpIfNeeded(artworkDetail.mobile_share_url);
            this.embedUrl = appendHttpIfNeeded(artworkDetail.play_url);
        } else {
            this.type = ExploreType.unknown;
        }
        DJILogHelper.getInstance().LOGI("bob", "displayImageUrl = " + this.displayImageUrl);
        DJILogHelper.getInstance().LOGI("bob", "shareUrl = " + this.shareUrl);
        DJILogHelper.getInstance().LOGI("bob", "orignalImageUrl = " + this.orignalImageUrl);
        DJILogHelper.getInstance().LOGI("bob", "isFollowed = " + artworkDetail.is_follow);
        this.userName = artworkDetail.account.name;
        this.duration = i;
        this.userAvatarUrl = artworkDetail.account.avatar;
        this.userCountry = artworkDetail.location.country;
        this.likeCount = artworkDetail.likes_count;
        this.tags = artworkDetail.tags;
        this.isLiked = artworkDetail.is_liked;
        this.isFollowed = artworkDetail.is_follow;
        this.isAdsVisible = false;
        this.id = artworkDetail.id;
        this.title = artworkDetail.title;
        this.description = artworkDetail.description;
        this.userId = artworkDetail.account.id;
        this.isFavorite = artworkDetail.is_favorited;
    }

    private String appendHttpIfNeeded(String str) {
        if (str == null || str.equals("") || str.startsWith("http:") || str.startsWith("https:")) {
            return str;
        }
        return "http:" + str;
    }
}
