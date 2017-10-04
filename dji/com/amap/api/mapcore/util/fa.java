package com.amap.api.mapcore.util;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class fa {

    public static class a {
        public static void a(ek ekVar, fd fdVar, String str) {
            ekVar.a((Object) fdVar, str);
        }

        public static fd a(ek ekVar, String str) {
            List b = ekVar.b(fd.b(str), fd.class);
            if (b == null || b.size() <= 0) {
                return null;
            }
            return (fd) b.get(0);
        }

        public static List<fd> a(ek ekVar, String str, String str2) {
            return ekVar.b(fd.b(str, str2), fd.class);
        }
    }

    static String a(String str) {
        return str + ".dex";
    }

    static String a(Context context, String str, String str2) {
        return ds.b(str + str2 + dq.q(context)) + ".jar";
    }

    static String b(Context context, String str, String str2) {
        return a(context, a(context, str, str2));
    }

    static String a(Context context, String str) {
        return a(context) + File.separator + str;
    }

    static String a(Context context) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "dex";
    }

    static boolean a(String str, String str2) {
        String a = ds.a(str);
        if (a == null || !a.equalsIgnoreCase(str2)) {
            return false;
        }
        return true;
    }

    static void a(Context context, ek ekVar, String str) {
        b(context, ekVar, str);
        b(context, ekVar, a(str));
    }

    static void b(Context context, ek ekVar, String str) {
        File file = new File(a(context, str));
        if (file.exists()) {
            file.delete();
        }
        ekVar.a(fd.b(str), fd.class);
    }

    static void c(final Context context, final String str, final String str2) {
        new Thread() {
            public void run() {
                try {
                    ek ekVar = new ek(context, fc.a());
                    List<fd> b = ekVar.b(fd.a(str), fd.class);
                    if (b != null && b.size() > 0) {
                        for (fd fdVar : b) {
                            if (!str2.equalsIgnoreCase(fdVar.c())) {
                                fa.b(context, ekVar, fdVar.a());
                            }
                        }
                    }
                } catch (Throwable th) {
                    eb.a(th, "DexFileManager", "clearUnSuitableVersion");
                }
            }
        }.start();
    }

    public static void a(ek ekVar, Context context, dv dvVar) {
        String a = dvVar.a();
        String a2 = a(context, a, dvVar.b());
        fd a3 = a.a(ekVar, a2);
        if (a3 != null) {
            a(context, ekVar, a2);
            List b = ekVar.b(fd.a(a, a3.d()), fd.class);
            if (b != null && b.size() > 0) {
                fd fdVar = (fd) b.get(0);
                fdVar.c("errorstatus");
                a.a(ekVar, fdVar, fd.b(fdVar.a()));
                File file = new File(a(context, fdVar.a()));
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    static void a(Context context, ek ekVar, dv dvVar, fd fdVar, String str) throws Throwable {
        IOException e;
        FileNotFoundException e2;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        InputStream fileInputStream;
        FileOutputStream fileOutputStream2;
        try {
            String a = dvVar.a();
            a(context, ekVar, fdVar.a());
            fileInputStream = new FileInputStream(new File(str));
            try {
                fileOutputStream2 = new FileOutputStream(new File(b(context, a, dvVar.b())), true);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream2.write(bArr, 0, read);
                    }
                    a.a(ekVar, fdVar, fd.b(fdVar.a()));
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e4) {
                    e2 = e4;
                    fileOutputStream = fileInputStream;
                    try {
                        throw e2;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileOutputStream;
                        fileOutputStream = fileOutputStream2;
                    }
                } catch (IOException e5) {
                    e32 = e5;
                    fileOutputStream = fileOutputStream2;
                    throw e32;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = fileOutputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e62) {
                            e62.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e7) {
                e2 = e7;
                fileOutputStream2 = null;
                Object obj = fileInputStream;
                throw e2;
            } catch (IOException e8) {
                e32 = e8;
                throw e32;
            } catch (Throwable th4) {
                th = th4;
                throw th;
            }
        } catch (FileNotFoundException e9) {
            e2 = e9;
            fileOutputStream2 = null;
            throw e2;
        } catch (IOException e10) {
            e32 = e10;
            fileInputStream = null;
            throw e32;
        } catch (Throwable th5) {
            th = th5;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    static boolean a(Context context, ek ekVar, String str, dv dvVar) {
        return a(ekVar, str, a(context, str), dvVar);
    }

    static boolean a(ek ekVar, String str, String str2, dv dvVar) {
        fd a = a.a(ekVar, str);
        if (a != null && dvVar.b().equals(a.c()) && a(str2, a.b())) {
            return true;
        }
        return false;
    }

    static String a(Context context, ek ekVar, dv dvVar) {
        List b = ekVar.b(fd.b(dvVar.a(), "copy"), fd.class);
        if (b == null || b.size() == 0) {
            return null;
        }
        a(b);
        int i = 0;
        while (i < b.size()) {
            fd fdVar = (fd) b.get(i);
            if (a(context, ekVar, fdVar.a(), dvVar)) {
                try {
                    a(context, ekVar, dvVar, new com.amap.api.mapcore.util.fd.a(a(context, dvVar.a(), dvVar.b()), fdVar.b(), dvVar.a(), dvVar.b(), fdVar.d()).a("usedex").a(), a(context, fdVar.a()));
                    return fdVar.d();
                } catch (Throwable th) {
                    eb.a(th, "DexFileManager", "loadAvailableDynamicSDKFile");
                }
            } else {
                b(context, ekVar, fdVar.a());
                i++;
            }
        }
        return null;
    }

    static void a(List<fd> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int i2 = i + 1; i2 < list.size(); i2++) {
                fd fdVar = (fd) list.get(i);
                fd fdVar2 = (fd) list.get(i2);
                if (ff.a(fdVar2.d(), fdVar.d()) > 0) {
                    list.set(i, fdVar2);
                    list.set(i2, fdVar);
                }
            }
        }
    }
}
