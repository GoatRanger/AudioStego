package dji.pilot2.nativeaudio.model;

import java.util.List;

public class NetWorkBigFilmModel {
    public int result;
    public List<MultiBigFilmModel> templates;

    public static class MultiBigFilmModel {
        public String author;
        public String createdAt;
        public String desc;
        public String duration;
        public int grid_count;
        public int id;
        public MutiBigFilmLinkModel linkModel;
        public int order;
        public MutiBigFilmPersonModel personModel;
        public boolean status;
        public String subtitle;
        public int template_id;
        public String title;
        public String updatedAt;
        public String video;
        public String video_md5;
        public String zip_Android;
        public String zip_Android_md5;
        public String zip_iOS;
        public String zip_iOS_md5;
    }

    public static class MutiBigFilmContent {
        public String cn;
        public String en;
    }

    public static class MutiBigFilmLinkModel {
        public MutiBigFilmContent videoLink;
        public MutiBigFilmContent zipLink;
    }

    public static class MutiBigFilmPersonModel {
        public String avatar;
        public String name;
        public String self_intro;
    }
}
