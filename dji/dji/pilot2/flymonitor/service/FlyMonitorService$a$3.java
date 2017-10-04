package dji.pilot2.flymonitor.service;

import dji.log.DJILogHelper;
import dji.thirdparty.afinal.f.a;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class FlyMonitorService$a$3 extends a<String> {
    final /* synthetic */ FlyMonitorService.a a;

    FlyMonitorService$a$3(FlyMonitorService.a aVar) {
        this.a = aVar;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        DJILogHelper.getInstance().LOGD(FlyMonitorService.a, "FLY_RECORD_UPLOAD response: " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != null && jSONObject.getInt("result") == 0) {
                JSONArray jSONArray = jSONObject.getJSONArray("data");
                if (jSONArray != null) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        if (!(jSONObject2 == null || jSONObject2.getString("type") == null)) {
                            FlyMonitorService.a(this.a.a, jSONObject2.getString("type"), jSONObject2.toString());
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void a(Throwable th, int i, String str) {
        DJILogHelper.getInstance().LOGE(FlyMonitorService.a, "FLY_RECORD_UPLOAD failed");
    }
}
