package dji.pilot2.mine.jsonbean;

public class UpdateJsonBean {
    public String msg;
    public UpdateNotice notice;
    public int ret;

    public static class UpdateNotice {
        public int update;
    }
}
