package dji.pilot2.nativeaudio.model;

import java.util.List;

public class NetworkAudioListModel {
    public int result;
    public List<NetworkAudioTemplateModel> templates;

    public static class AccountModel {
        public String avatar;
        public String name;
        public String self_intro;
    }

    public static class MultiLangugeNameModel {
        public String cn;
        public String en;
        public String jp;
    }

    public static class NetworkAudioTemplateModel {
        public String author;
        public AccountModel authorModel;
        public String createdAt;
        public String desc;
        public MultiLangugeNameModel descModel;
        public String duration;
        public int grid_count;
        public int id;
        public String online_at;
        public int order;
        public boolean status;
        public String subtitle;
        public MultiLangugeNameModel subtitleModel;
        public String thumb_large;
        public String thumb_large_md5;
        public String thumb_small;
        public String thumb_small_md5;
        public String title;
        public MultiLangugeNameModel titleModel;
        public String updatedAt;
        public String video;
        public String video_md5;
        public String zip_Android;
        public String zip_Android_md5;
        public String zip_iOS;
        public String zip_iOS_md5;
    }
}
