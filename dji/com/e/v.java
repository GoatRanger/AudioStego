package com.e;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class v {

    public static class a {
        public static y a(f fVar, String str) {
            List b = fVar.b(y.b(str), y.class);
            return (b == null || b.size() <= 0) ? null : (y) b.get(0);
        }

        public static List<y> a(f fVar, String str, String str2) {
            return fVar.b(y.b(str, str2), y.class);
        }

        public static void a(f fVar, y yVar, String str) {
            fVar.a((Object) yVar, str);
        }
    }

    static String a(Context context) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "dex";
    }

    static String a(Context context, f fVar, dc dcVar) {
        List b = fVar.b(y.b(dcVar.a(), "copy"), y.class);
        if (b == null || b.size() == 0) {
            return null;
        }
        a(b);
        int i = 0;
        while (i < b.size()) {
            y yVar = (y) b.get(i);
            if (a(context, fVar, yVar.a(), dcVar)) {
                try {
                    a(context, fVar, dcVar, new com.e.y.a(a(context, dcVar.a(), dcVar.b()), yVar.b(), dcVar.a(), dcVar.b(), yVar.d()).a("usedex").a(), a(context, yVar.a()));
                    return yVar.d();
                } catch (Throwable th) {
                    dg.a(th, "DexFileManager", "loadAvailableDynamicSDKFile");
                }
            } else {
                b(context, fVar, yVar.a());
                i++;
            }
        }
        return null;
    }

    static String a(Context context, String str) {
        return a(context) + File.separator + str;
    }

    static String a(Context context, String str, String str2) {
        return cz.b(str + str2 + cx.q(context)) + ".jar";
    }

    static String a(String str) {
        return str + ".dex";
    }

    static void a(Context context, f fVar, dc dcVar, y yVar, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        IOException e;
        FileNotFoundException e2;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        InputStream fileInputStream;
        try {
            String a = dcVar.a();
            a(context, fVar, yVar.a());
            fileInputStream = new FileInputStream(new File(str));
            try {
                fileOutputStream = new FileOutputStream(new File(b(context, a, dcVar.b())), true);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    a.a(fVar, yVar, y.b(yVar.a()));
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e4) {
                    e2 = e4;
                    fileOutputStream2 = fileInputStream;
                    try {
                        throw e2;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileOutputStream2;
                        fileOutputStream2 = fileOutputStream;
                    }
                } catch (IOException e5) {
                    e32 = e5;
                    fileOutputStream2 = fileOutputStream;
                    throw e32;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream2 = fileOutputStream;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e62) {
                            e62.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e7) {
                e2 = e7;
                fileOutputStream = null;
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
            fileOutputStream = null;
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
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    static void a(Context context, f fVar, String str) {
        b(context, fVar, str);
        b(context, fVar, a(str));
    }

    public static void a(f fVar, Context context, dc dcVar) {
        String a = dcVar.a();
        String a2 = a(context, a, dcVar.b());
        y a3 = a.a(fVar, a2);
        if (a3 != null) {
            a(context, fVar, a2);
            List b = fVar.b(y.a(a, a3.d()), y.class);
            if (b != null && b.size() > 0) {
                y yVar = (y) b.get(0);
                yVar.c("errorstatus");
                a.a(fVar, yVar, y.b(yVar.a()));
                File file = new File(a(context, yVar.a()));
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    static void a(List<y> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int i2 = i + 1; i2 < list.size(); i2++) {
                y yVar = (y) list.get(i);
                y yVar2 = (y) list.get(i2);
                if (aa.a(yVar2.d(), yVar.d()) > 0) {
                    list.set(i, yVar2);
                    list.set(i2, yVar);
                }
            }
        }
    }

    static boolean a(Context context, f fVar, String str, dc dcVar) {
        return a(fVar, str, a(context, str), dcVar);
    }

    static boolean a(f fVar, String str, String str2, dc dcVar) {
        y a = a.a(fVar, str);
        return a != null && dcVar.b().equals(a.c()) && a(str2, a.b());
    }

    static boolean a(String str, String str2) {
        String a = cz.a(str);
        return a != null && a.equalsIgnoreCase(str2);
    }

    static String b(Context context, String str, String str2) {
        return a(context, a(context, str, str2));
    }

    static void b(Context context, f fVar, String str) {
        File file = new File(a(context, str));
        if (file.exists()) {
            file.delete();
        }
        fVar.a(y.b(str), y.class);
    }

    static void c(final Context context, final String str, final String str2) {
        new Thread() {
            public void run() {
                try {
                    f fVar = new f(context, x.c());
                    List<y> b = fVar.b(y.a(str), y.class);
                    if (b != null && b.size() > 0) {
                        for (y yVar : b) {
                            if (!str2.equalsIgnoreCase(yVar.c())) {
                                v.b(context, fVar, yVar.a());
                            }
                        }
                    }
                } catch (Throwable th) {
                    dg.a(th, "DexFileManager", "clearUnSuitableVersion");
                }
            }
        }.start();
    }
}
