package dji.pilot2.mine.db;

import dji.thirdparty.afinal.a.a.a;

public class DraftBean {
    private String createTime;
    private String description;
    private String duration;
    @a
    private String filePath;
    private String shareUrl;
    private int status;
    private String tag;
    private String thumbnailPath;
    private String title;
    private String type;
    @a
    private String userEmail;

    public DraftBean(String str, String str2, String str3, String str4, String str5, int i, String str6, String str7, String str8) {
        setUserEmail(str);
        setFilePath(str2);
        setTitle(str3);
        setDescription(str4);
        setType(str5);
        setStatus(i);
        setThumbnailPath(str6);
        setDuration(str7);
        setShareUrl("");
        setCreateTime(str8);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String str) {
        this.userEmail = str;
    }

    public String getThumbnailPath() {
        return this.thumbnailPath;
    }

    public void setThumbnailPath(String str) {
        this.thumbnailPath = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String str) {
        this.duration = str;
    }

    public String getShareUrl() {
        return this.shareUrl;
    }

    public void setShareUrl(String str) {
        this.shareUrl = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }
}
