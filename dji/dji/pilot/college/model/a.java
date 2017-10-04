package dji.pilot.college.model;

import dji.log.DJILogHelper;
import dji.pilot.college.b.b;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class a implements b {
    public final ArrayList<CollegeInfo> a = new ArrayList();
    public final ArrayList<CollegeInfo> b = new ArrayList();

    public static a a(String str) {
        int i = 0;
        a aVar = new a();
        if (str != null && str.length() > 0) {
            try {
                JSONObject optJSONObject = new JSONObject(str).optJSONObject(b.g).optJSONObject(b.h);
                JSONArray optJSONArray = optJSONObject.optJSONArray(b.i);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        CollegeInfo parse = CollegeInfo.parse(optJSONArray.getJSONObject(i2), 0);
                        if (parse != null) {
                            aVar.a.add(parse);
                        }
                    }
                }
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("video");
                if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                    int length2 = optJSONArray2.length();
                    while (i < length2) {
                        CollegeInfo parse2 = CollegeInfo.parse(optJSONArray2.getJSONObject(i), 1);
                        if (parse2 != null) {
                            aVar.b.add(parse2);
                        }
                        i++;
                    }
                }
            } catch (Exception e) {
                DJILogHelper.getInstance().LOGE("CollegeResult", e.getMessage());
            }
        }
        return aVar;
    }
}
