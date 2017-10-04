package dji.pilot2.nativeexplore.model;

import java.util.List;

public class FollowListModel {
    public List<AccountModel> items;
    public int page;
    public int page_size;
    public int size;
    public int status;
    public int total_count;

    public static class AccountModel {
        public String avatar;
        public String country;
        public int followers_count;
        public String id;
        public boolean is_company;
        public boolean is_follow;
        public boolean is_vip;
        public String name;
        public int photos_count;
        public int videos_count;
    }
}
