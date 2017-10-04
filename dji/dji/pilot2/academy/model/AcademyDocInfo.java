package dji.pilot2.academy.model;

import dji.log.DJILogHelper;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AcademyDocInfo {
    public static final int STATE_FINISHED = 3;
    public static final int STATE_INIT = 0;
    public static final int STATE_RUNNING = 2;
    public static final int STATE_START = 1;
    public List<DocInfo> mDocInfos;
    public long mGetTime;

    public static List<DocInfo> parsel(String str) {
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
            List<DocInfo> arrayList = new ArrayList();
            if (optJSONArray != null && optJSONArray.length() > 0) {
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    DocInfo parse = DocInfo.parse(optJSONArray.getJSONObject(i));
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
