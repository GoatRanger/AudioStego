package com.e;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import org.json.JSONObject;

public class cq {
    private static cq e;
    Context a;
    SharedPreferences b = null;
    Editor c = null;
    private String d = null;

    private cq(Context context) {
        this.a = context;
        if (this.d == null) {
            this.d = bb.a("MD5", this.a.getPackageName());
        }
    }

    public static cq a(Context context) {
        if (e == null) {
            e = new cq(context);
        }
        return e;
    }

    public AMapLocation a() {
        AMapLocation aMapLocation;
        AMapLocation aMapLocation2 = null;
        if (this.a != null) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("pref", 0);
            if (sharedPreferences != null) {
                Object str;
                String str2 = "lastfix" + bc.e;
                if (sharedPreferences.contains(str2)) {
                    try {
                        String string = sharedPreferences.getString(str2, null);
                        if (string != null) {
                            str = new String(bb.d(cy.b(string), this.d), "UTF-8");
                        } else {
                            String str3 = string;
                        }
                    } catch (Throwable th) {
                        bc.a(th, "LastLocationManager", "getLastFix part1");
                        sharedPreferences.edit().remove(str2).commit();
                        aMapLocation = aMapLocation2;
                    }
                } else {
                    aMapLocation = aMapLocation2;
                }
                if (!TextUtils.isEmpty(str)) {
                    try {
                        aMapLocation2 = bc.a(new JSONObject(str));
                    } catch (Throwable th2) {
                        bc.a(th2, "LastLocationManager", "getLastFix part2");
                    }
                }
            }
        }
        return aMapLocation2;
    }

    public void a(AMapLocation aMapLocation) {
        Object obj = null;
        if (this.a != null && br.a(aMapLocation) && aMapLocation.getLocationType() != 2) {
            if (this.b == null) {
                this.b = this.a.getSharedPreferences("pref", 0);
            }
            if (this.c == null) {
                this.c = this.b.edit();
            }
            if (!TextUtils.isEmpty(aMapLocation.toStr())) {
                byte[] c;
                try {
                    c = bb.c(aMapLocation.toStr().getBytes("UTF-8"), this.d);
                } catch (Throwable th) {
                    bc.a(th, "LastLocationManager", "setLastFix");
                }
                obj = cy.a(c);
            }
            if (!TextUtils.isEmpty(obj)) {
                this.c.putString("lastfix" + bc.e, obj);
                bq.a(this.c);
            }
        }
    }
}
