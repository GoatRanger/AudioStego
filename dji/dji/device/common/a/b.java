package dji.device.common.a;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class b {
    public static final String a = "http://chat10.live800.com/live800/chatClient/chatbox.jsp?jid=2964301564&companyID=409811&prechatinfoexist=1&subject=%E6%89%8B%E6%9C%BAAPP";
    public static final String b = "Inspire1@dji.com";
    public static final String c = "http://chat32.live800.com/live800/chatClient/chatbox.jsp?companyID=493623&configID=72904&jid=5418788219";
    public static final String d = "support@dji.com";
    private static final String e = "[A-Z0-9a-z._-]+@[A-Za-z0-9.-]+(?:[-.][a-zA-Z0-9]+)*\\.[A-Za-z]{2,4}";

    public static boolean a(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean a(String... strArr) {
        for (String a : strArr) {
            if (a(a)) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(String str, String str2) {
        boolean z = str == str2;
        if (z || str == null) {
            return z;
        }
        return str.equals(str2);
    }

    public static boolean b(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        return Pattern.compile(e).matcher(str.trim()).matches();
    }

    public static int a() {
        return TimeZone.getDefault().getOffset(new Date().getTime()) / 3600000;
    }

    public static long a(byte[] bArr, int i, int i2) {
        long j = 0;
        if (bArr != null && bArr.length >= i + i2) {
            int i3 = (i + i2) - 1;
            while (i3 >= i) {
                long j2 = ((long) (bArr[i3] & 255)) + (j * 256);
                i3--;
                j = j2;
            }
        }
        return j;
    }

    public static long c(String str) {
        long j = 0;
        for (String parseInt : str.split("\\.")) {
            j = (j * 256) + ((long) Integer.parseInt(parseInt, 10));
        }
        return j;
    }

    public static String a(Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        if (obj != null) {
            try {
                a(obj, stringBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    private static String a(Object obj, StringBuffer stringBuffer) throws Exception {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        String str = "";
        stringBuffer.append("------  begin ------\n");
        for (Field field : declaredFields) {
            stringBuffer.append(field.getName());
            stringBuffer.append(" : ");
            Object a = a(obj, field.getName(), field.getType(), null);
            if (a != null) {
                if (a.getClass().isArray()) {
                    for (int i = 0; i < Array.getLength(a); i++) {
                        Object obj2 = Array.get(a, i);
                        if (obj2.getClass().isPrimitive()) {
                            stringBuffer.append(obj2.toString());
                        } else if (obj2 instanceof String) {
                            stringBuffer.append(obj2.toString());
                        } else if (obj2 instanceof Date) {
                            stringBuffer.append(obj2.toString());
                        } else if (obj2 instanceof Number) {
                            stringBuffer.append(obj2.toString());
                        } else {
                            a(obj2, stringBuffer);
                        }
                    }
                }
                if (a.getClass().getName().indexOf("com.cignacmb.core.model.") > -1) {
                    a(a, stringBuffer);
                }
            }
            stringBuffer.append(a);
            stringBuffer.append("\n");
        }
        stringBuffer.append("------  end ------\n");
        return stringBuffer.toString();
    }

    private static Object a(Object obj, String str, Class cls, Object[] objArr) throws Exception {
        Class cls2 = obj.getClass();
        String str2 = str.substring(0, 1).toUpperCase() + str.substring(1);
        Method method = null;
        try {
            if (cls == Boolean.TYPE) {
                method = cls2.getMethod("is" + str2, new Class[0]);
            } else {
                method = cls2.getMethod("get" + str2, new Class[0]);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            return " can't find 'get" + str2 + "' method";
        }
        return method.invoke(obj, new Object[0]);
    }
}
