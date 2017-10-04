package dji.pilot2.academy.model;

import dji.pilot.college.b.b;
import dji.pilot.usercenter.mode.n;
import dji.thirdparty.afinal.a.a.a;
import org.json.JSONObject;

public class AcademyDocInfo$DocInfo {
    public String appid;
    public String created_at;
    @a
    public int id;
    public String language;
    public String listUrl = "";
    public int mDownloadState = 0;
    public int mProgress = 0;
    public boolean mbNew = false;
    public String name;
    public String size;
    public boolean status;
    public String updated_at;
    public String url;

    public String toString() {
        return "DocInfo : id = " + this.id + ",name=" + this.name + ",size=" + this.size + ",language=" + this.language + ",appid=" + this.appid + ",url=" + this.url + ",created_at=" + this.created_at + ",updated_at=" + this.updated_at;
    }

    public static AcademyDocInfo$DocInfo parse(JSONObject jSONObject) {
        AcademyDocInfo$DocInfo academyDocInfo$DocInfo = null;
        if (jSONObject != null) {
            academyDocInfo$DocInfo = new AcademyDocInfo$DocInfo();
            try {
                academyDocInfo$DocInfo.id = jSONObject.optInt("id");
                academyDocInfo$DocInfo.name = jSONObject.optString("name", "");
                academyDocInfo$DocInfo.size = jSONObject.optString(b.l, "0");
                academyDocInfo$DocInfo.language = jSONObject.optString(b.n, "");
                academyDocInfo$DocInfo.status = jSONObject.optBoolean("status");
                academyDocInfo$DocInfo.appid = jSONObject.optString(b.o, "");
                academyDocInfo$DocInfo.url = jSONObject.optString("url", "");
                academyDocInfo$DocInfo.created_at = jSONObject.optString(n.L, "");
                academyDocInfo$DocInfo.updated_at = jSONObject.optString(n.M, "");
            } catch (Exception e) {
            }
        }
        return academyDocInfo$DocInfo;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String str) {
        this.size = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getAppid() {
        return this.appid;
    }

    public void setAppid(String str) {
        this.appid = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean z) {
        this.status = z;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(String str) {
        this.created_at = str;
    }

    public String getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(String str) {
        this.updated_at = str;
    }

    public int getmProgress() {
        return this.mProgress;
    }

    public void setmProgress(int i) {
        this.mProgress = i;
    }

    public boolean isMbNew() {
        return this.mbNew;
    }

    public void setMbNew(boolean z) {
        this.mbNew = z;
    }

    public int getmDownloadState() {
        return this.mDownloadState;
    }

    public void setmDownloadState(int i) {
        this.mDownloadState = i;
    }
}
