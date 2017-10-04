package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.MapsInitializer;
import dji.pilot.usercenter.protocol.d;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class bk extends cj<String, bj> {
    protected /* synthetic */ Object b(String str) throws AMapException {
        return a(str);
    }

    public bk(Context context, String str) {
        super(context, str);
        getClass();
        a(5000);
        getClass();
        b(50000);
    }

    public String a() {
        return "http://restapi.amap.com/v3/config/version";
    }

    protected bj a(String str) throws AMapException {
        bj bjVar = new bj();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("offlinemap")) {
                jSONObject = jSONObject.getJSONObject("offlinemap");
                String optString = jSONObject.optString("update", "");
                if (optString.equals("0")) {
                    bjVar.a(false);
                } else if (optString.equals("1")) {
                    bjVar.a(true);
                }
                bjVar.a(jSONObject.optString("version", ""));
            }
        } catch (Throwable th) {
            ee.a(th, "OfflineInitHandler", "loadData parseJson");
        }
        return bjVar;
    }

    public Map<String, String> b() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("mapver", this.a);
        hashMap.put("output", "json");
        if (!TextUtils.isEmpty(MapsInitializer.KEY)) {
            dm.a(MapsInitializer.KEY);
        }
        hashMap.put(d.M, dl.f(this.d));
        hashMap.put("opertype", "offlinemap_with_province_vfour");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("mapver=").append((String) this.a);
        stringBuffer.append("&output=json");
        stringBuffer.append("&key=").append(dl.f(this.d));
        stringBuffer.append("&opertype=offlinemap_with_province_vfour");
        String d = dx.d(stringBuffer.toString());
        String a = dn.a();
        hashMap.put("ts", a);
        hashMap.put("scode", dn.a(this.d, a, d));
        return hashMap;
    }
}
