package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.ag;
import com.tencent.bugly.proguard.z;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class b {
    protected static Map<String, Integer> a(String str) {
        if (str == null) {
            return null;
        }
        try {
            Map<String, Integer> hashMap = new HashMap();
            for (String split : str.split(",")) {
                String[] split2 = split.split(":");
                if (split2.length != 2) {
                    z.e("error format at %s", split);
                    return null;
                }
                hashMap.put(split2[0], Integer.valueOf(Integer.parseInt(split2[1])));
            }
            return hashMap;
        } catch (Exception e) {
            z.e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }

    protected static String b(String str) {
        if (str == null) {
            return "";
        }
        String[] split = str.split("\n");
        if (split == null && split.length == 0) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : split) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                stringBuilder.append(str2).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    protected static CrashDetailBean a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        if (map == null) {
            return null;
        }
        if (a.a(context) == null) {
            z.e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str = (String) map.get("intStateStr");
        if (str == null || str.trim().length() <= 0) {
            z.e("no intStateStr", new Object[0]);
            return null;
        }
        Map a = a(str);
        if (a == null) {
            z.e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            int intValue = ((Integer) a.get("ep")).intValue();
            int intValue2 = ((Integer) a.get("et")).intValue();
            ((Integer) a.get("sino")).intValue();
            int intValue3 = ((Integer) a.get("sico")).intValue();
            ((Integer) a.get("spd")).intValue();
            ((Integer) a.get("sud")).intValue();
            long intValue4 = (long) ((Integer) a.get("ets")).intValue();
            long intValue5 = (long) ((Integer) a.get("etms")).intValue();
            String str2 = (String) map.get("soVersion");
            if (str2 == null) {
                z.e("error format at version", new Object[0]);
                return null;
            }
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            str = (String) map.get("errorAddr");
            String str9 = str == null ? "unknown2" : str;
            str = (String) map.get("codeMsg");
            if (str == null) {
                str3 = "unknown2";
            } else {
                str3 = str;
            }
            str = (String) map.get("tombPath");
            if (str == null) {
                str4 = "unknown2";
            } else {
                str4 = str;
            }
            str = (String) map.get("signalName");
            if (str == null) {
                str5 = "unknown2";
            } else {
                str5 = str;
            }
            str = (String) map.get("errnoMsg");
            str = (String) map.get("stack");
            if (str == null) {
                str6 = "unknown2";
            } else {
                str6 = str;
            }
            str = (String) map.get("jstack");
            if (str != null) {
                str = str6 + "java:\n" + str;
            } else {
                str = str6;
            }
            intValue4 = (intValue4 * 1000) + (intValue5 / 1000);
            String b = b(str);
            str = "UNKNOWN";
            String str10 = "UNKNOWN(" + intValue + ")";
            if (intValue3 > 0) {
                str5 = str5 + "(" + str3 + ")";
                str3 = "KERNEL";
            }
            str = (String) map.get("nativeLog");
            byte[] bArr = null;
            if (!(str == null || str.isEmpty())) {
                bArr = ag.a(null, str);
            }
            str = (String) map.get("processName");
            if (str == null) {
                str7 = "unknown(" + intValue + ")";
            } else {
                str7 = str;
            }
            str = (String) map.get("threadName");
            if (str == null) {
                str8 = "unknown(" + intValue2 + ")";
            } else {
                str8 = str;
            }
            Map map2 = null;
            str = (String) map.get("key-value");
            if (str != null) {
                map2 = new HashMap();
                for (String split : str.split("\n")) {
                    String[] split2 = split.split("=");
                    if (split2.length == 2) {
                        map2.put(split2[0], split2[1]);
                    }
                }
            }
            CrashDetailBean packageCrashDatas = nativeExceptionHandler.packageCrashDatas(str7, str8, intValue4, str5, str9, b, str3, str10, str4, str2, bArr, map2, false);
            if (packageCrashDatas == null) {
                return packageCrashDatas;
            }
            packageCrashDatas.y = null;
            packageCrashDatas.k = true;
            return packageCrashDatas;
        } catch (Throwable th) {
            z.e("error format", new Object[0]);
            th.printStackTrace();
            return null;
        }
    }

    protected static String a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            if (read == 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append((char) read);
        }
    }

    public static CrashDetailBean a(Context context, String str, NativeExceptionHandler nativeExceptionHandler) {
        IOException e;
        Throwable th;
        CrashDetailBean crashDetailBean = null;
        if (context == null || str == null || nativeExceptionHandler == null) {
            z.e("get eup record file args error", new Object[0]);
        } else {
            File file = new File(str, "rqd_record.eup");
            if (file.exists() && file.canRead()) {
                FileInputStream fileInputStream;
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        String a = a((InputStream) fileInputStream);
                        if (a == null || !a.equals("NATIVE_RQD_REPORT")) {
                            z.e("record read fail! %s", a);
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        } else {
                            Map hashMap = new HashMap();
                            Object obj = crashDetailBean;
                            while (true) {
                                String a2 = a((InputStream) fileInputStream);
                                if (a2 == null) {
                                    break;
                                } else if (obj == null) {
                                    obj = a2;
                                } else {
                                    hashMap.put(obj, a2);
                                    obj = crashDetailBean;
                                }
                            }
                            if (obj != null) {
                                z.e("record not pair! drop! %s", obj);
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e22) {
                                        e22.printStackTrace();
                                    }
                                }
                            } else {
                                crashDetailBean = a(context, hashMap, nativeExceptionHandler);
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e222) {
                                        e222.printStackTrace();
                                    }
                                }
                            }
                        }
                    } catch (IOException e3) {
                        e222 = e3;
                        try {
                            e222.printStackTrace();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e2222) {
                                    e2222.printStackTrace();
                                }
                            }
                            return crashDetailBean;
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e22222) {
                                    e22222.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (IOException e4) {
                    e22222 = e4;
                    fileInputStream = crashDetailBean;
                    e22222.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return crashDetailBean;
                } catch (Throwable th3) {
                    fileInputStream = crashDetailBean;
                    th = th3;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            }
        }
        return crashDetailBean;
    }

    public static void c(String str) {
        File file = new File(str, "rqd_record.eup");
        if (file.exists() && file.canWrite()) {
            file.delete();
            z.c("delete record file %s", file.getAbsoluteFile());
        }
    }
}
