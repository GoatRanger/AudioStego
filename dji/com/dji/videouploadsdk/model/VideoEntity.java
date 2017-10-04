package com.dji.videouploadsdk.model;

public class VideoEntity {
    private String access_token;
    private String[] drones;
    private String file_md5 = null;
    private String file_size = null;
    private PositionModel position;
    private String upload_mode = null;
    private String video_desc;
    private String[] video_tag_list;
    private String video_title;

    public String getAccess_token() {
        return this.access_token;
    }

    public String getVideo_title() {
        return this.video_title;
    }

    public String getVideo_desc() {
        return this.video_desc;
    }

    public String[] getDrones() {
        return this.drones;
    }

    public String[] getVideo_tag_list() {
        return this.video_tag_list;
    }

    public PositionModel getPosition() {
        return this.position;
    }

    public void setAccess_token(String str) {
        this.access_token = str;
    }

    public void setVideo_tag_list(String[] strArr) {
        this.video_tag_list = strArr;
    }

    public void setDrones(String[] strArr) {
        this.drones = strArr;
    }

    public void setPosition(PositionModel positionModel) {
        this.position = positionModel;
    }

    public void setVideo_desc(String str) {
        this.video_desc = str;
    }

    public void setVideo_title(String str) {
        this.video_title = str;
    }

    public String getFile_md5() {
        return this.file_md5;
    }

    public String getFile_size() {
        return this.file_size;
    }

    public String getUpload_mode() {
        return this.upload_mode;
    }

    public void setFile_md5(String str) {
        this.file_md5 = str;
    }

    public void setFile_size(String str) {
        this.file_size = str;
    }

    public void setUpload_mode(String str) {
        this.upload_mode = str;
    }
}
