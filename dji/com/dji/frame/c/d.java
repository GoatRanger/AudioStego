package com.dji.frame.c;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import java.io.File;
import java.util.HashMap;

public class d {
    public static String a = "/mnt/";
    public static String b = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static String c = "V_DiskUtil";
    private static String d = (Environment.getExternalStorageDirectory() + "/DJI/");

    public static String a(Context context, String str) {
        return d + context.getPackageName() + dji.pilot.usercenter.protocol.d.t + str;
    }

    public static void a(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static File b(Context context, String str) {
        return new File(c(context, str));
    }

    public static String c(Context context, String str) {
        String b = b();
        return (b != null ? a(context, b) : context.getCacheDir().getPath()) + File.separator + str;
    }

    public static HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap();
        try {
            File file = new File("/mnt");
            if (file.exists() && file.isDirectory() && file.listFiles() != null && file.listFiles().length > 0) {
                int i = 1;
                for (File file2 : file.listFiles()) {
                    if (file2.isDirectory()) {
                        String absolutePath = file2.getAbsolutePath();
                        String str = "";
                        if (absolutePath.startsWith("/mnt/sdcard")) {
                            str = "sdcard";
                        } else if (absolutePath.startsWith("/mnt/sda")) {
                            str = "sda" + i;
                            i++;
                        } else if (absolutePath.startsWith("/mnt/nand")) {
                            str = "nand";
                        }
                        if (!str.equals("")) {
                            hashMap.put(str, absolutePath);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    private static String b() {
        HashMap a = a();
        if (a.containsKey("sdcard")) {
            return (String) a.get("sdcard");
        }
        if (a.containsKey("nand")) {
            return (String) a.get("nand");
        }
        if (a.containsKey("sda1")) {
            return (String) a.get("sda1");
        }
        return null;
    }

    public static File d(Context context, String str) {
        return new File(b + a(context, str));
    }

    public static long a(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Exception e) {
            Log.e(c, "获取 sdcard 缓存大小 出错，请查看AndroidManifest.xml 是否添加了sdcard的访问权限");
            e.printStackTrace();
            return -1;
        }
    }
}
