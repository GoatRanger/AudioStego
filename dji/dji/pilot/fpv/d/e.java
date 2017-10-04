package dji.pilot.fpv.d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.flurry.android.FlurryAgent;
import dji.apppublic.reflect.AppPublicReflect;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.i;
import dji.pilot.fpv.d.c.g;
import dji.sdksharedlib.b.h;
import dji.sdksharedlib.hardware.abstractions.c.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class e implements c, g {
    public static final String a = "X529Q7SM6P224YP253M4";
    private static boolean b = true;

    public static void a(Context context) {
        FlurryAgent.setLogEnabled(AppPublicReflect.getBuildConfig_DEBUG());
        FlurryAgent.setLogLevel(2);
        FlurryAgent.init(context, "X529Q7SM6P224YP253M4");
    }

    public static void b(Context context) {
        if (b) {
            FlurryAgent.onStartSession(context, "X529Q7SM6P224YP253M4");
        }
    }

    public static void c(Context context) {
        if (b) {
            FlurryAgent.onEndSession(context);
        }
    }

    public static void a(String str) {
        if (b) {
            a(str, true);
        }
    }

    public static void b(String str) {
        if (b) {
            FlurryAgent.logEvent(str);
            a.getInstance().c(str);
        }
    }

    public static void c(String str) {
        if (b) {
            Map hashMap = new HashMap();
            hashMap.put(d.dI, i.getInstance().c().toString());
            FlurryAgent.logEvent(str, hashMap);
            a.getInstance().a(str, hashMap);
        }
    }

    public static void a(String str, Map<String, String> map) {
        if (b) {
            map.put(d.dI, i.getInstance().c().toString());
            FlurryAgent.logEvent(str, (Map) map);
            a.getInstance().a(str, (Map) map);
        }
    }

    public static void a(String str, boolean z) {
        if (!b) {
            return;
        }
        if (z) {
            Object obj;
            String str2 = "";
            switch (1.a[i.getInstance().c().ordinal()]) {
                case 1:
                    obj = "LitchiX";
                    break;
                case 2:
                    obj = "LitchiS";
                    break;
                case 3:
                    obj = "LitchiC";
                    break;
                case 4:
                    obj = "P34K";
                    break;
                case 5:
                    obj = "Banana";
                    break;
                case 6:
                    obj = "N1";
                    break;
                case 7:
                    obj = "BigBanana";
                    break;
                case 8:
                    obj = "Olives";
                    break;
                case 9:
                    obj = "OrangeRAW";
                    break;
                case 10:
                    obj = "Phantom4";
                    break;
                case 11:
                    obj = "Inspire CV600";
                    break;
                case 12:
                    obj = "Phantom4 Professional";
                    break;
                default:
                    obj = "None";
                    break;
            }
            Map hashMap = new HashMap();
            hashMap.put(h.a, obj);
            FlurryAgent.logEvent(str, hashMap);
            a.getInstance().a(str, hashMap);
            return;
        }
        FlurryAgent.logEvent(str);
        a.getInstance().c(str);
    }

    public static void a(String str, HashMap<String, String> hashMap) {
        if (b) {
            FlurryAgent.logEvent(str, (Map) hashMap);
            a.getInstance().a(str, (Map) hashMap);
        }
    }

    public static void b(String str, boolean z) {
        if (!b) {
            return;
        }
        if (z) {
            FlurryAgent.logEvent(str, true);
            a.getInstance().a(str);
            return;
        }
        FlurryAgent.endTimedEvent(str);
        a.getInstance().b(str);
    }

    public static void a(long j) {
        if (b) {
            a(g.D, g.v, "" + j);
            String str = "";
            switch (1.a[i.getInstance().c().ordinal()]) {
                case 1:
                    str = "LitchiX";
                    break;
                case 2:
                    str = "LitchiS";
                    break;
                case 3:
                    str = "LitchiC";
                    break;
                case 4:
                    str = "P34K";
                    break;
                case 5:
                    str = "Banana";
                    break;
                case 6:
                    str = "N1";
                    break;
                case 7:
                    str = "BigBanana";
                    break;
                case 8:
                    str = a.j;
                    break;
                case 9:
                    str = "OrangeRAW";
                    break;
                case 10:
                    str = "Phantom4";
                    break;
                case 11:
                    str = "Inspire CV600";
                    break;
                case 12:
                    str = "Phantom4 Professional";
                    break;
                default:
                    str = "None";
                    break;
            }
            String dJIMemberManager_getUID = AppPublicReflect.getDJIMemberManager_getUID();
            if (dji.pilot.publics.e.a.b(dJIMemberManager_getUID)) {
                dJIMemberManager_getUID = "unknown";
            }
            long j2 = j / 1000;
            dJIMemberManager_getUID = dJIMemberManager_getUID + ":" + str + ":" + j2 + "s";
            Map hashMap = new HashMap();
            hashMap.put(g.A, dJIMemberManager_getUID);
            hashMap.put(str, j2 + "");
            hashMap.put("FlightTimeStatistics", j2 + "");
            DJILogHelper.getInstance().LOGD("", "Email&ProductType&FlightTime " + dJIMemberManager_getUID, false, true);
            FlurryAgent.logEvent("FlightTimeRecord", hashMap);
            a.getInstance().a("FlightTimeRecord", hashMap);
        }
    }

    public static void a(String str, String str2, String str3) {
        if (b) {
            String productType = i.getInstance().c().toString();
            Object obj = AppPublicReflect.getDJIMemberManager_getUID() + ":" + productType;
            if (!str3.equals("")) {
                obj = obj + ":" + str3;
            }
            Map hashMap = new HashMap();
            Object obj2 = g.z;
            if (str2.equals("")) {
                hashMap.put(d.dI, productType);
            } else {
                obj2 = obj2 + com.alipay.sdk.h.a.b + str2;
                hashMap.put(str2, "" + str3);
            }
            hashMap.put(obj2, obj);
            FlurryAgent.logEvent(str, hashMap);
            a.getInstance().a(str, hashMap);
        }
    }

    public static void b(long j) {
        String str = AppPublicReflect.getDJIMemberManager_getUID() + ":" + i.getInstance().c().toString() + ":" + j + "s";
        Map hashMap = new HashMap();
        hashMap.put(g.v, "" + j);
        hashMap.put(g.A, str);
        FlurryAgent.logEvent(g.D, hashMap);
        a.getInstance().a(g.D, hashMap);
    }

    public static void d(String str) {
        if (b) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    a(jSONObject, keys.next().toString());
                }
            } catch (JSONException e) {
                DJILogHelper.getInstance().LOGE("DJIFlurry", "v2 flurry error");
                e.printStackTrace();
            }
        }
    }

    private static void a(JSONObject jSONObject, String str) {
        if (b) {
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            if (optJSONObject != null) {
                Iterator keys = optJSONObject.keys();
                String str2 = "";
                str2 = "";
                Map hashMap = new HashMap();
                while (keys.hasNext()) {
                    String obj = keys.next().toString();
                    if (!(obj == null || "".equals(obj))) {
                        str2 = optJSONObject.optString(obj);
                    }
                    hashMap.put(obj, str2);
                    DJILogHelper.getInstance().LOGI("DJIFlurry", "key: " + obj + "   value: " + str2);
                }
                FlurryAgent.logEvent(str, hashMap);
                a.getInstance().a(str, hashMap);
            }
        }
    }

    public static Resources d(Context context) {
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(Locale.US);
        return context.createConfigurationContext(configuration).getResources();
    }
}
