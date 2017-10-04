package dji.pilot2.library.model;

import dji.thirdparty.afinal.a.a.a;

public class MediaInfoBean {
    private String absPath;
    private String addDate;
    private String date;
    private long duration;
    private String filePath;
    @a
    private int id;
    private int mediaId;
    private String subNailpath;
    private String thumbnailPath;
    private String title;
    private String type;

    public String getAddDate() {
        return this.addDate;
    }

    public void setAddDate(String str) {
        this.addDate = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getdate() {
        return this.date;
    }

    public void setdate(String str) {
        this.date = str;
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

    public String getThumbnailPath() {
        return this.thumbnailPath;
    }

    public void setThumbnailPath(String str) {
        this.thumbnailPath = str;
    }

    public int getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(int i) {
        this.mediaId = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public String getAbsPath() {
        return this.absPath;
    }

    public void setAbsPath(String str) {
        this.absPath = str;
    }

    public String getSubNailpath() {
        return this.subNailpath;
    }

    public void setSubNailpath(String str) {
        this.subNailpath = str;
    }
}
