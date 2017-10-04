package dji.pilot.college.model;

import dji.pilot.college.b.b;
import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.afinal.a.a.a;
import java.util.GregorianCalendar;
import org.json.JSONObject;

public class CollegeInfo implements b {
    private static long DIVIDER_TIME = 0;
    public static final int STATE_FINISHED = 3;
    public static final int STATE_INIT = 0;
    public static final int STATE_RUNNING = 2;
    public static final int STATE_START = 1;
    public static final int TYPE_DOCUMENT = 0;
    public static final int TYPE_VIDEO = 1;
    public String mAppId = "";
    public String mAppVersion = "";
    public long mCreateTime = 0;
    public int mDataType = 0;
    public int mDownloadState = 0;
    @a
    public String mGuid = "";
    public String mLanguage = "";
    public String mName = "";
    public int mProgress = 0;
    public String mRemark = "";
    public long mSize = 0;
    public String mThumnailUrl = "";
    private String mUrl = "";
    public String mVersion = "";
    public boolean mbNew = false;

    static {
        DIVIDER_TIME = 0;
        DIVIDER_TIME = new GregorianCalendar(2015, 0, 15, 0, 0, 0).getTimeInMillis() / 1000;
    }

    public static CollegeInfo parse(JSONObject jSONObject, int i) {
        CollegeInfo collegeInfo = null;
        if (jSONObject != null) {
            collegeInfo = new CollegeInfo();
            collegeInfo.mDataType = i;
            try {
                collegeInfo.mGuid = jSONObject.optString(b.j, "");
                collegeInfo.mName = jSONObject.optString("name", "");
                collegeInfo.mSize = jSONObject.optLong(b.l, 0);
                collegeInfo.mVersion = jSONObject.optString("version", "");
                if (!jSONObject.isNull(b.n)) {
                    collegeInfo.mLanguage = jSONObject.optString(b.n, "");
                }
                collegeInfo.mAppId = jSONObject.optString(b.o, "");
                collegeInfo.mAppVersion = jSONObject.optString(b.p, "");
                collegeInfo.mCreateTime = jSONObject.optLong("createtime", System.currentTimeMillis());
                if (collegeInfo.mCreateTime > DIVIDER_TIME) {
                    collegeInfo.mbNew = true;
                }
                collegeInfo.mUrl = jSONObject.optString("url", "");
                collegeInfo.mThumnailUrl = jSONObject.optString(b.t, "");
                collegeInfo.mRemark = jSONObject.optString(b.u, "");
            } catch (Exception e) {
            }
        }
        return collegeInfo;
    }

    public void copyFromServer(CollegeInfo collegeInfo) {
        this.mGuid = collegeInfo.mGuid;
        this.mName = collegeInfo.mName;
        this.mSize = collegeInfo.mSize;
        this.mVersion = collegeInfo.mVersion;
        this.mLanguage = collegeInfo.mLanguage;
        this.mAppId = collegeInfo.mAppId;
        this.mAppVersion = collegeInfo.mAppVersion;
        this.mCreateTime = collegeInfo.mCreateTime;
        this.mUrl = collegeInfo.mUrl;
        this.mThumnailUrl = collegeInfo.mThumnailUrl;
        this.mRemark = collegeInfo.mRemark;
    }

    public boolean beDownload() {
        return this.mDownloadState == 1 || this.mDownloadState == 2;
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof CollegeInfo)) {
            return equals;
        }
        CollegeInfo collegeInfo = (CollegeInfo) obj;
        if (collegeInfo.mGuid == null || !collegeInfo.mGuid.equals(this.mGuid)) {
            return equals;
        }
        return true;
    }

    public int hashCode() {
        if (this.mGuid != null) {
            return this.mGuid.hashCode() + 527;
        }
        return 17;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("guid[").append(this.mGuid).append(d.H);
        stringBuilder.append("name[").append(this.mName).append(d.H);
        stringBuilder.append("language[").append(this.mLanguage).append(d.H);
        stringBuilder.append("url[").append(this.mUrl).append(d.H);
        return super.toString();
    }

    public String getUrl() {
        return this.mUrl.trim();
    }
}
