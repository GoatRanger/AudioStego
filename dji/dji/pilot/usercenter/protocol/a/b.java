package dji.pilot.usercenter.protocol.a;

import com.dji.frame.c.h;
import dji.pilot.publics.e.d;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.FlightOverviewInfo;
import dji.pilot.usercenter.mode.i;
import dji.pilot.usercenter.mode.m;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.c;
import dji.pilot2.mine.b.e;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class b implements c {
    public static Object a(String str) {
        i iVar = new i();
        try {
            JSONObject jSONObject = new JSONObject(str);
            iVar.bo = jSONObject.optInt(c.M, 1);
            iVar.bp = jSONObject.optString(c.N, "");
            iVar.a = jSONObject.optInt(n.al, 0);
            iVar.b = jSONObject.optInt(n.am, 0);
            iVar.c = jSONObject.optInt(n.an, 0);
            iVar.d = jSONObject.optInt(n.ao, 0);
            if (iVar.bo == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray(c.O);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    List arrayList = new ArrayList(length);
                    for (int i = 0; i < length; i++) {
                        m mVar = new m();
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                        mVar.a = jSONObject2.optString(c.Q, "");
                        if (!jSONObject2.isNull(c.R)) {
                            mVar.b = jSONObject2.optString(c.R, "");
                        }
                        if (!jSONObject2.isNull(c.S)) {
                            mVar.c = jSONObject2.optInt(c.S, 2);
                        }
                        arrayList.add(mVar);
                    }
                    iVar.bq = arrayList;
                }
            }
        } catch (Exception e) {
        }
        return iVar;
    }

    public static Object b(String str) {
        i iVar = new i();
        try {
            JSONObject jSONObject = new JSONObject(str);
            iVar.bo = jSONObject.optInt(c.M, 1);
            iVar.bp = jSONObject.optString(c.N, "");
        } catch (Exception e) {
        }
        return iVar;
    }

    public static Object c(String str) {
        i iVar = new i();
        try {
            JSONObject jSONObject = new JSONObject(str);
            iVar.bo = jSONObject.optInt(c.M, 1);
            iVar.bp = jSONObject.optString(c.N, "");
        } catch (Exception e) {
        }
        return iVar;
    }

    public static Object a(String str, boolean z) {
        i iVar = new i();
        try {
            JSONObject jSONObject = new JSONObject(str);
            iVar.bo = jSONObject.optInt(c.M, 1);
            iVar.bp = jSONObject.optString(c.N, "");
            if (iVar.bo == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray(c.P);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    ArrayList arrayList = new ArrayList(length);
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                        FlightOverviewInfo flightOverviewInfo = new FlightOverviewInfo();
                        flightOverviewInfo.mSubStreet = jSONObject2.optString(c.U, "");
                        flightOverviewInfo.mStreet = jSONObject2.optString(c.V, "");
                        flightOverviewInfo.mCity = jSONObject2.optString(c.W, "");
                        flightOverviewInfo.mArea = jSONObject2.optString(c.X, "");
                        flightOverviewInfo.mLongitude = jSONObject2.optDouble("longitude", 0.0d);
                        flightOverviewInfo.mLatitude = jSONObject2.optDouble("latitude", 0.0d);
                        flightOverviewInfo.mTotalTime = jSONObject2.optLong(c.aa, 0);
                        flightOverviewInfo.mTotalDistance = jSONObject2.optDouble(c.ab, 0.0d);
                        flightOverviewInfo.mTotalFlightTime = jSONObject2.optInt(c.ac, 0);
                        flightOverviewInfo.mTimeStamp = jSONObject2.optLong(c.ad, 0);
                        flightOverviewInfo.mBoardNum = jSONObject2.optString(c.ae, c.T);
                        flightOverviewInfo.mVersion = jSONObject2.optInt("version", 1);
                        arrayList.add(flightOverviewInfo);
                    }
                    iVar.bq = arrayList;
                }
                if (z) {
                    return iVar;
                }
            }
        } catch (Exception e) {
        }
        return iVar;
    }

    public static Object d(String str) {
        i iVar = new i();
        try {
            iVar.bo = new JSONObject(str).optInt(c.ag, 0);
        } catch (Exception e) {
        }
        return iVar;
    }

    public static Object b(String str, boolean z) {
        i iVar = new i();
        if (!d.a(str)) {
            try {
                UserLevelJsonBean userLevelJsonBean = (UserLevelJsonBean) h.b(str, UserLevelJsonBean.class);
                if (!(userLevelJsonBean == null || userLevelJsonBean.level == null)) {
                    iVar.bo = userLevelJsonBean.ret;
                    iVar.bp = userLevelJsonBean.msg;
                    if (z) {
                        e.getInstance().a(f.getInstance().j(), str);
                    }
                }
                iVar.bq = userLevelJsonBean;
            } catch (Exception e) {
            }
        }
        return iVar;
    }
}
