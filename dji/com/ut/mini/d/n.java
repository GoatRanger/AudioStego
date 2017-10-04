package com.ut.mini.d;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class n {
    public static boolean a(String str) {
        if (str == null || str.length() <= 0) {
            return true;
        }
        return false;
    }

    public static String a(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return ((String) obj).toString();
        }
        if (obj instanceof Integer) {
            return "" + ((Integer) obj).intValue();
        }
        if (obj instanceof Long) {
            return "" + ((Long) obj).longValue();
        }
        if (obj instanceof Double) {
            return "" + ((Double) obj).doubleValue();
        }
        if (obj instanceof Float) {
            return "" + ((Float) obj).floatValue();
        }
        if (obj instanceof Short) {
            return "" + ((Short) obj).shortValue();
        }
        if (obj instanceof Byte) {
            return "" + ((Byte) obj).byteValue();
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).toString();
        }
        if (obj instanceof Character) {
            return ((Character) obj).toString();
        }
        return obj.toString();
    }

    public static int b(String str) {
        int i = 0;
        if (str.length() <= 0) {
            return 0;
        }
        char[] toCharArray = str.toCharArray();
        int i2 = 0;
        while (i < toCharArray.length) {
            i2 = (i2 * 31) + toCharArray[i];
            i++;
        }
        return i2;
    }

    public static String a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        Object obj = 1;
        for (Object obj2 : map.keySet()) {
            Object obj22;
            String a = a(map.get(obj22));
            String a2 = a(obj22);
            if (!(a == null || a2 == null)) {
                if (obj != null) {
                    stringBuffer.append(a2 + "=" + a);
                    obj22 = null;
                    obj = obj22;
                } else {
                    stringBuffer.append(",").append(a2 + "=" + a);
                }
            }
            obj22 = obj;
            obj = obj22;
        }
        return stringBuffer.toString();
    }

    public static String b(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        Object obj = 1;
        for (Object obj2 : map.keySet()) {
            Object obj22;
            String a = a(map.get(obj22));
            String a2 = a(obj22);
            if (!(a == null || a2 == null)) {
                if (obj != null) {
                    try {
                        stringBuffer.append(URLEncoder.encode(a2, "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8"));
                        obj22 = null;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        obj22 = obj;
                    }
                } else {
                    try {
                        stringBuffer.append(",").append(URLEncoder.encode(a2, "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8"));
                        obj22 = obj;
                    } catch (UnsupportedEncodingException e2) {
                        e2.printStackTrace();
                    }
                }
                obj = obj22;
            }
            obj22 = obj;
            obj = obj22;
        }
        return stringBuffer.toString();
    }
}
