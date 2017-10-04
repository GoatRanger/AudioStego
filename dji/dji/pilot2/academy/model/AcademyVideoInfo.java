package dji.pilot2.academy.model;

import dji.log.DJILogHelper;
import dji.pilot.college.b.b;
import dji.pilot.usercenter.mode.n;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AcademyVideoInfo {
    public long mGetTime;
    public List<VideoInfo> mVideoInfos;

    public static class VideoInfo {
        public String appid;
        public String created_at;
        public int id;
        public String language;
        public String name;
        public String remark;
        public String size;
        public boolean status;
        public String thumbnails;
        public String updated_at;
        public String url;

        public static VideoInfo parse(JSONObject jSONObject) {
            VideoInfo videoInfo = null;
            if (jSONObject != null) {
                videoInfo = new VideoInfo();
                try {
                    videoInfo.id = jSONObject.optInt("id");
                    videoInfo.name = jSONObject.optString("name", "");
                    videoInfo.size = jSONObject.optString(b.l, "0");
                    videoInfo.language = jSONObject.optString(b.n, "");
                    videoInfo.status = jSONObject.optBoolean("status");
                    videoInfo.appid = jSONObject.optString(b.o, "");
                    videoInfo.url = jSONObject.optString("url", "");
                    videoInfo.thumbnails = jSONObject.optString(b.t, "");
                    videoInfo.remark = jSONObject.optString(b.u, "");
                    videoInfo.created_at = jSONObject.optString(n.L, "");
                    videoInfo.updated_at = jSONObject.optString(n.M, "");
                } catch (Exception e) {
                }
            }
            return videoInfo;
        }
    }

    public static List<VideoInfo> parsel(String str) {
        DJILogHelper.getInstance().LOGI("bob", "json = " + str);
        if (str == null || str.length() <= 0) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("result") != 0) {
                DJILogHelper.getInstance().LOGI("bob", "json err");
                return null;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray == null) {
                return null;
            }
            List<VideoInfo> arrayList = new ArrayList();
            if (optJSONArray != null && optJSONArray.length() > 0) {
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    VideoInfo parse = VideoInfo.parse(optJSONArray.getJSONObject(i));
                    if (parse != null) {
                        arrayList.add(parse);
                    }
                }
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
