package com.dji.b;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.os.Environment;
import dji.midware.usb.P3.DJIUsbAccessoryReceiver;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class a {
    private static String a = (Environment.getExternalStorageDirectory() + File.separator + DJIUsbAccessoryReceiver.c + File.separator + "assistant");
    private static String b = (a + File.separator + "config.properties");
    private boolean c;
    private Properties d;

    private static class a {
        private static a a = new a();

        private a() {
        }
    }

    public static a a() {
        return a.a;
    }

    private a() {
        this.c = false;
        this.c = b();
        File file = new File(a);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (this.c) {
            try {
                file = new File(b);
                if (!file.exists()) {
                    file.createNewFile();
                }
                InputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(b));
                this.d = new Properties();
                this.d.load(bufferedInputStream);
            } catch (Exception e) {
                e.printStackTrace();
                this.d = null;
            }
        }
    }

    public void a(Class cls) {
        a(cls, null);
    }

    public void a(Object obj) {
        if (obj != null) {
            a(obj.getClass(), obj);
        }
    }

    public void a(Class cls, Object obj) {
        if (this.c && cls != null) {
            try {
                for (Field field : cls.getDeclaredFields()) {
                    b bVar = (b) field.getAnnotation(b.class);
                    if (!(bVar == null || bVar.a() == null || bVar.a().isEmpty())) {
                        String a = bVar.a();
                        if (a(a)) {
                            field.setAccessible(true);
                            Class type = field.getType();
                            if (type == Boolean.class || type == Boolean.TYPE) {
                                field.setBoolean(obj, b(a).booleanValue());
                            } else if (type == Integer.class || type == Integer.TYPE) {
                                field.setInt(obj, c(a).intValue());
                            } else if (type == String.class) {
                                field.set(obj, d(a));
                            }
                        } else {
                            continue;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean a(String str) {
        if (this.d == null) {
            return false;
        }
        return this.d.containsKey(str);
    }

    private Boolean b(String str) {
        String property = this.d.getProperty(str);
        if (!(property == null || property.isEmpty())) {
            try {
                return Boolean.valueOf(property);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Boolean.valueOf(false);
    }

    private Integer c(String str) {
        String property = this.d.getProperty(str);
        if (!(property == null || property.isEmpty())) {
            try {
                return Integer.valueOf(property);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Integer.valueOf(-1);
    }

    private String d(String str) {
        return this.d.getProperty(str);
    }

    private boolean b() {
        try {
            List installedPackages = ((Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, (Object[]) null)).getPackageManager().getInstalledPackages(0);
            List arrayList = new ArrayList();
            if (installedPackages != null) {
                for (int i = 0; i < installedPackages.size(); i++) {
                    arrayList.add(((PackageInfo) installedPackages.get(i)).packageName);
                }
            }
            return arrayList.contains("com.dji.configassistant");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
