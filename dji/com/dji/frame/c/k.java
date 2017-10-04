package com.dji.frame.c;

import com.alipay.sdk.h.a;
import dji.pilot.usercenter.protocol.d;
import java.util.Map;

public class k {
    public static String a(String str) {
        return str.replace(d.t, "//").replace("'", "''").replace(d.G, "/[").replace(d.H, "/]").replace("%", "/%").replace(a.b, "/&").replace("_", "/_").replace("(", "/(").replace(")", "/)");
    }

    public static String a(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : map.keySet()) {
            if (stringBuffer.equals(null)) {
                stringBuffer.append(" AND ");
            }
            stringBuffer.append(str).append(" = '").append((String) map.get(str)).append("'");
        }
        return stringBuffer.toString();
    }
}
