package com.dji.videouploadsdk.model;

public class commitResultEntity {
    private String message;
    private SkypixelCommitResult skypixel;
    private int state;
    private String timestamp;
    private String vid;

    public static class SkypixelCommitResult {
        private int status;
        private String vid;

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public String getVid() {
            return this.vid;
        }

        public void setVid(String str) {
            this.vid = str;
        }
    }

    public SkypixelCommitResult getSkypixel() {
        return this.skypixel;
    }

    public void setSkypixel(SkypixelCommitResult skypixelCommitResult) {
        this.skypixel = skypixelCommitResult;
    }

    public String getVid() {
        return this.vid;
    }

    public void setVid(String str) {
        this.vid = str;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public int getState() {
        return this.state;
    }

    public String getMessage() {
        return this.message;
    }

    public void setState(int i) {
        this.state = i;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
