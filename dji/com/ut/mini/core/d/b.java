package com.ut.mini.core.d;

import com.ut.mini.base.UTLogFieldsScheme;
import com.ut.mini.base.c;
import com.ut.mini.core.a;
import com.ut.mini.core.e;
import com.ut.mini.d.n;
import java.util.HashMap;
import java.util.Map;

public class b {
    private static long s_default_session_timestamp = System.currentTimeMillis();

    private static String _checkField(String str) {
        if (n.a(str)) {
            return "-";
        }
        return str;
    }

    public static String assemble(Map<String, String> map) {
        if (map != null && map.size() > 0) {
            if (!n.a(c.a().i())) {
                map.put(UTLogFieldsScheme.USERNICK.toString(), c.a().i());
            }
            if (!n.a(c.a().f())) {
                map.put(UTLogFieldsScheme.LL_USERNICK.toString(), c.a().f());
            }
            if (!n.a(c.a().j())) {
                map.put(UTLogFieldsScheme.USERID.toString(), c.a().j());
            }
            if (!n.a(c.a().g())) {
                map.put(UTLogFieldsScheme.LL_USERID.toString(), c.a().g());
            }
            if (!map.containsKey(UTLogFieldsScheme.SDKVERSION.toString())) {
                map.put(UTLogFieldsScheme.SDKVERSION.toString(), c.a().c().getFullSDKVersion());
            }
            if (!map.containsKey(UTLogFieldsScheme.APPKEY.toString())) {
                map.put(UTLogFieldsScheme.APPKEY.toString(), c.a().l());
            }
            if (!n.a(c.a().h())) {
                map.put(UTLogFieldsScheme.CHANNEL.toString(), c.a().h());
            }
            if (!n.a(c.a().b())) {
                map.put(UTLogFieldsScheme.APPVERSION.toString(), c.a().b());
            }
            if (!map.containsKey(UTLogFieldsScheme.RECORD_TIMESTAMP.toString())) {
                map.put(UTLogFieldsScheme.RECORD_TIMESTAMP.toString(), "" + System.currentTimeMillis());
            }
            if (!map.containsKey(UTLogFieldsScheme.START_SESSION_TIMESTAMP.toString())) {
                map.put(UTLogFieldsScheme.START_SESSION_TIMESTAMP.toString(), "" + s_default_session_timestamp);
            }
            Map a = a.a(c.a().k());
            if (a != null) {
                String str;
                Object obj;
                Map hashMap = new HashMap();
                hashMap.putAll(a);
                for (String str2 : map.keySet()) {
                    if (!(str2.equals(UTLogFieldsScheme.BRAND.toString()) || str2.equals(UTLogFieldsScheme.DEVICE_MODEL.toString()) || str2.equals(UTLogFieldsScheme.RESOLUTION.toString()) || str2.equals(UTLogFieldsScheme.OS.toString()) || str2.equals(UTLogFieldsScheme.OSVERSION.toString()) || str2.equals(UTLogFieldsScheme.UTDID.toString()))) {
                        str = (String) map.get(str2);
                        if (!(n.a(str2) || str == null)) {
                            hashMap.put(str2, str);
                        }
                    }
                }
                String str22 = (String) hashMap.get(UTLogFieldsScheme.RESERVES.toString());
                str = (String) hashMap.get("_mac");
                if (str != null) {
                    if (str22 != null) {
                        str22 = str22 + ",_mac=" + str;
                    } else {
                        str22 = "_mac=" + str;
                    }
                    hashMap.remove("_mac");
                }
                str = str22;
                str22 = (String) hashMap.get(com.ut.mini.base.b.DEVICE_ID.toString());
                if (str22 != null) {
                    if (str != null) {
                        obj = str + ",_did=" + str22;
                    } else {
                        obj = "_did=" + str22;
                    }
                    hashMap.remove(com.ut.mini.base.b.DEVICE_ID.toString());
                } else {
                    str22 = str;
                }
                str = c.a(c.a().k());
                if (str != null) {
                    if (hashMap.containsKey(UTLogFieldsScheme.UTDID.toString()) && str.equals(hashMap.get(UTLogFieldsScheme.UTDID.toString()))) {
                        str = com.alipay.sdk.b.b.g;
                    }
                    if (obj != null) {
                        obj = obj + ",_umid=" + str;
                    } else {
                        obj = "_umid=" + str;
                    }
                }
                if (c.a().n()) {
                    if (obj != null) {
                        obj = String.format("%s,_io=%s", new Object[]{obj, "1"});
                    } else {
                        obj = String.format("_io=%s", new Object[]{"1"});
                    }
                }
                Map d = e.a().d();
                if (d != null && d.size() > 0) {
                    str = n.b(d);
                    if (!n.a(str)) {
                        if (obj != null) {
                            obj = String.format("%s,%s", new Object[]{obj, str});
                        } else {
                            str22 = str;
                        }
                    }
                }
                if (obj != null) {
                    hashMap.put(UTLogFieldsScheme.RESERVES.toString(), obj);
                }
                return assembleWithFullFields(hashMap);
            }
        }
        return null;
    }

    public static String assembleWithFullFields(Map<String, String> map) {
        String a;
        StringBuffer stringBuffer = new StringBuffer();
        for (UTLogFieldsScheme uTLogFieldsScheme : UTLogFieldsScheme.values()) {
            if (uTLogFieldsScheme == UTLogFieldsScheme.ARGS) {
                break;
            }
            if (map.containsKey(uTLogFieldsScheme.toString())) {
                a = n.a(map.get(uTLogFieldsScheme.toString()));
                map.remove(uTLogFieldsScheme.toString());
            } else {
                a = null;
            }
            stringBuffer.append(_checkField(a)).append("||");
        }
        Object obj = 1;
        if (map.containsKey(UTLogFieldsScheme.ARGS.toString())) {
            stringBuffer.append(_checkField(n.a(map.get(UTLogFieldsScheme.ARGS.toString()))));
            map.remove(UTLogFieldsScheme.ARGS.toString());
            obj = null;
        }
        Object obj2 = obj;
        for (String a2 : map.keySet()) {
            String a3;
            if (map.containsKey(a2)) {
                a3 = n.a(map.get(a2));
            } else {
                a3 = null;
            }
            if (obj2 != null) {
                if ("StackTrace".equals(a2)) {
                    stringBuffer.append("StackTrace=====>").append(a3);
                } else {
                    stringBuffer.append(_checkField(a2)).append("=").append(a3);
                }
                obj = null;
            } else if ("StackTrace".equals(a2)) {
                stringBuffer.append(",").append("StackTrace=====>").append(a3);
                obj = obj2;
            } else {
                stringBuffer.append(",").append(_checkField(a2)).append("=").append(a3);
                obj = obj2;
            }
            obj2 = obj;
        }
        a2 = stringBuffer.toString();
        if (n.a(a2) || !a2.endsWith("||")) {
            return a2;
        }
        return a2 + "-";
    }

    public static Map<String, String> disassemble(String str) {
        int i = 0;
        if (n.a(str)) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        String[] split = str.split("\\|\\|");
        if (split == null || split.length <= 0) {
            return hashMap;
        }
        UTLogFieldsScheme[] values = UTLogFieldsScheme.values();
        int length = values.length;
        int i2 = 0;
        while (i < length) {
            UTLogFieldsScheme uTLogFieldsScheme = values[i];
            if (i2 < split.length && split[i2] != null) {
                hashMap.put(uTLogFieldsScheme.toString(), split[i2]);
            }
            i2++;
            i++;
        }
        return hashMap;
    }
}
