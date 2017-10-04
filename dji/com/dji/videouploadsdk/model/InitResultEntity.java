package com.dji.videouploadsdk.model;

public class InitResultEntity {
    private String access_token;
    private String msg;
    private int offset;
    private int slice_length;
    private String slice_task_id;
    private int status;
    private String upload_server;
    private String upload_token;
    private String upload_url;
    private String video_desc;
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

    public String getMsg() {
        return this.msg;
    }

    public int getOffset() {
        return this.offset;
    }

    public int getSlice_length() {
        return this.slice_length;
    }

    public String getSlice_task_id() {
        return this.slice_task_id;
    }

    public int getStatus() {
        return this.status;
    }

    public String getUpload_server() {
        return this.upload_server;
    }

    public String getUpload_token() {
        return this.upload_token;
    }

    public String getUpload_url() {
        return this.upload_url;
    }

    public void setAccess_token(String str) {
        this.access_token = str;
    }

    public void setVideo_title(String str) {
        this.video_title = str;
    }

    public void setVideo_desc(String str) {
        this.video_desc = str;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setOffset(int i) {
        this.offset = i;
    }

    public void setSlice_length(int i) {
        this.slice_length = i;
    }

    public void setSlice_task_id(String str) {
        this.slice_task_id = str;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setUpload_server(String str) {
        this.upload_server = str;
    }

    public void setUpload_token(String str) {
        this.upload_token = str;
    }

    public void setUpload_url(String str) {
        this.upload_url = str;
    }
}
