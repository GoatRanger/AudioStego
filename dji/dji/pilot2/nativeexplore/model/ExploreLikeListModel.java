package dji.pilot2.nativeexplore.model;

import dji.pilot2.nativeexplore.model.FollowListModel.AccountModel;
import java.util.List;

public class ExploreLikeListModel {
    public List<AccountModel> accounts;
    public int page;
    public int page_size;
    public int status;
    public String status_msg;
    public int total_count;

    public static class LikeListAccountModel {
        public String avatar;
        public String city;
        public String country;
        public int followers_count;
        public String id;
        public boolean is_company;
        public boolean is_follow;
        public boolean is_vip;
        public String name;
        public String state;
    }
}
