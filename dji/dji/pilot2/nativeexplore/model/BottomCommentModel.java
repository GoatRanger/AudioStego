package dji.pilot2.nativeexplore.model;

import java.util.List;

public class BottomCommentModel {
    public List<CommentItemModel> items;
    public int page_size;
    public String status_msg;
    public int total_count;
    public int total_page;

    public static class CommentItemModel {
        public String account_id;
        public String avatar;
        public String content;
        public String created_at;
        public int id;
        public String user;
    }
}
