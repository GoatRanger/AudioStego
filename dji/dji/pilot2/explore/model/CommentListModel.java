package dji.pilot2.explore.model;

import java.util.List;

public class CommentListModel {
    public int current_page;
    public List<Comment> items;
    public int page_size;
    public int status;
    public String status_msg;
    public int total_count;
    public int total_page;

    public static class Comment {
        public String account_id;
        public String avatar;
        public String content;
        public String created_at;
        public int id;
        public boolean is_current_user_liked;
        public int like_count;
        public String user;
    }
}
