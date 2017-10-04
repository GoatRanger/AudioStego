package com.e;

import android.content.Context;
import android.os.Build;
import com.e.af.b;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class de {
    private static boolean a = true;

    public static void a(Context context) {
        try {
            if (g(context)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()));
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                stringBuffer.append(UUID.randomUUID().toString());
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                if (stringBuffer.length() == 53) {
                    Object a = dd.a(stringBuffer.toString());
                    Object b = b(context);
                    byte[] bArr = new byte[(a.length + b.length)];
                    System.arraycopy(a, 0, bArr, 0, a.length);
                    System.arraycopy(b, 0, bArr, a.length, b.length);
                    aj.a().b(new di(dd.c(bArr), "2"));
                }
            }
        } catch (Throwable e) {
            dg.a(e, "StatisticsManager", "updateStaticsData");
        } catch (Throwable e2) {
            dg.a(e2, "StatisticsManager", "updateStaticsData");
        }
    }

    private static void a(Context context, long j) {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileNotFoundException e;
        IOException e2;
        File file = new File(dh.a(context, "c.log"));
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(dd.a(String.valueOf(j)));
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (FileNotFoundException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th3) {
                            th = th3;
                            th.printStackTrace();
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th5) {
                            th5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e2 = e4;
                e2.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th6) {
                        th = th6;
                        th.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            fileOutputStream = null;
            e.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (IOException e6) {
            e2 = e6;
            fileOutputStream = null;
            e2.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th7) {
            th = th7;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    private static byte[] a(Context context, byte[] bArr) {
        try {
            return cw.a(context, bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static byte[] a(af afVar, String str) {
        b a;
        byte[] bArr;
        Throwable th;
        Throwable th2;
        InputStream inputStream = null;
        byte[] bArr2 = new byte[0];
        try {
            a = afVar.a(str);
            try {
                inputStream = a.a(0);
                bArr = new byte[inputStream.available()];
            } catch (Throwable th3) {
                Throwable th4 = th3;
                bArr = bArr2;
                th2 = th4;
                try {
                    th2.printStackTrace();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th22) {
                            th22.printStackTrace();
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable th5) {
                            th22 = th5;
                            th22.printStackTrace();
                            return bArr;
                        }
                    }
                    return bArr;
                } catch (Throwable th6) {
                    th3 = th6;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th222) {
                            th222.printStackTrace();
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable th2222) {
                            th2222.printStackTrace();
                        }
                    }
                    throw th3;
                }
            }
            try {
                inputStream.read(bArr);
                afVar.c(str);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th22222) {
                        th22222.printStackTrace();
                    }
                }
                if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable th7) {
                        th22222 = th7;
                        th22222.printStackTrace();
                        return bArr;
                    }
                }
            } catch (Throwable th8) {
                th22222 = th8;
                th22222.printStackTrace();
                if (inputStream != null) {
                    inputStream.close();
                }
                if (a != null) {
                    a.close();
                }
                return bArr;
            }
        } catch (Throwable th9) {
            th3 = th9;
            a = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (a != null) {
                a.close();
            }
            throw th3;
        }
        return bArr;
    }

    private static byte[] b(Context context) {
        Object c = c(context);
        Object e = e(context);
        byte[] bArr = new byte[(c.length + e.length)];
        System.arraycopy(c, 0, bArr, 0, c.length);
        System.arraycopy(e, 0, bArr, c.length, e.length);
        return a(context, bArr);
    }

    private static byte[] c(Context context) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        try {
            dd.a(byteArrayOutputStream, "1.2.12.5");
            dd.a(byteArrayOutputStream, cx.q(context));
            dd.a(byteArrayOutputStream, cx.i(context));
            dd.a(byteArrayOutputStream, cx.f(context));
            dd.a(byteArrayOutputStream, Build.MANUFACTURER);
            dd.a(byteArrayOutputStream, Build.MODEL);
            dd.a(byteArrayOutputStream, Build.DEVICE);
            dd.a(byteArrayOutputStream, cx.r(context));
            dd.a(byteArrayOutputStream, cu.c(context));
            dd.a(byteArrayOutputStream, cu.d(context));
            dd.a(byteArrayOutputStream, cu.f(context));
            byteArrayOutputStream.write(new byte[]{(byte) 0});
            bArr = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return bArr;
            }
        } catch (Throwable th3) {
            th = th3;
            th.printStackTrace();
        }
        return bArr;
    }

    private static int d(Context context) {
        int i = 0;
        try {
            File file = new File(dh.a(context, dh.e));
            if (file.exists()) {
                i = file.list().length;
            }
        } catch (Throwable th) {
            dg.a(th, "StatisticsManager", "getFileNum");
        }
        return i;
    }

    private static byte[] e(Context context) {
        Throwable th;
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[0];
        String a = dh.a(context, dh.e);
        af afVar = null;
        try {
            afVar = af.a(new File(a), 1, 1, 10240);
            File file = new File(a);
            if (file != null && file.exists()) {
                String[] list = file.list();
                int length = list.length;
                while (i < length) {
                    String str = list[i];
                    if (str.contains(".0")) {
                        byteArrayOutputStream.write(a(afVar, str.split("\\.")[0]));
                    }
                    i++;
                }
            }
            bArr = byteArrayOutputStream.toByteArray();
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (afVar != null) {
                try {
                    afVar.close();
                } catch (Throwable th2) {
                    th = th2;
                    th.printStackTrace();
                    return bArr;
                }
            }
        } catch (Throwable th3) {
            dg.a(th3, "StatisticsManager", "getContent");
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (afVar != null) {
                afVar.close();
            }
        } catch (Throwable th4) {
            th3 = th4;
            th3.printStackTrace();
        }
        return bArr;
    }

    private static long f(android.content.Context r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.e.de.f(android.content.Context):long. bs: [B:17:0x0038, B:40:0x0063]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r0 = 0;
        r2 = "c.log";
        r2 = com.e.dh.a(r7, r2);
        r5 = new java.io.File;
        r5.<init>(r2);
        r2 = r5.exists();
        if (r2 != 0) goto L_0x0014;
    L_0x0013:
        return r0;
    L_0x0014:
        r4 = 0;
        r3 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0036, IOException -> 0x0047, Throwable -> 0x0058, all -> 0x0085 }
        r3.<init>(r5);	 Catch:{ FileNotFoundException -> 0x0036, IOException -> 0x0047, Throwable -> 0x0058, all -> 0x0085 }
        r2 = r3.available();	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
        r2 = new byte[r2];	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
        r3.read(r2);	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
        r2 = com.e.dd.a(r2);	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
        r0 = java.lang.Long.parseLong(r2);	 Catch:{ FileNotFoundException -> 0x008c, IOException -> 0x008a, Throwable -> 0x0088 }
        if (r3 == 0) goto L_0x0013;
    L_0x002d:
        r3.close();	 Catch:{ Throwable -> 0x0031 }
        goto L_0x0013;
    L_0x0031:
        r2 = move-exception;
    L_0x0032:
        r2.printStackTrace();
        goto L_0x0013;
    L_0x0036:
        r2 = move-exception;
        r3 = r4;
    L_0x0038:
        r4 = "StatisticsManager";	 Catch:{ all -> 0x0079 }
        r5 = "getUpdateTime";	 Catch:{ all -> 0x0079 }
        com.e.dg.a(r2, r4, r5);	 Catch:{ all -> 0x0079 }
        if (r3 == 0) goto L_0x0013;
    L_0x0041:
        r3.close();	 Catch:{ Throwable -> 0x0045 }
        goto L_0x0013;
    L_0x0045:
        r2 = move-exception;
        goto L_0x0032;
    L_0x0047:
        r2 = move-exception;
        r3 = r4;
    L_0x0049:
        r4 = "StatisticsManager";	 Catch:{ all -> 0x0079 }
        r5 = "getUpdateTime";	 Catch:{ all -> 0x0079 }
        com.e.dg.a(r2, r4, r5);	 Catch:{ all -> 0x0079 }
        if (r3 == 0) goto L_0x0013;
    L_0x0052:
        r3.close();	 Catch:{ Throwable -> 0x0056 }
        goto L_0x0013;
    L_0x0056:
        r2 = move-exception;
        goto L_0x0032;
    L_0x0058:
        r2 = move-exception;
        r3 = r4;
    L_0x005a:
        r4 = "StatisticsManager";	 Catch:{ all -> 0x0079 }
        r6 = "getUpdateTime";	 Catch:{ all -> 0x0079 }
        com.e.dg.a(r2, r4, r6);	 Catch:{ all -> 0x0079 }
        if (r5 == 0) goto L_0x006c;
    L_0x0063:
        r2 = r5.exists();	 Catch:{ Throwable -> 0x0074 }
        if (r2 == 0) goto L_0x006c;	 Catch:{ Throwable -> 0x0074 }
    L_0x0069:
        r5.delete();	 Catch:{ Throwable -> 0x0074 }
    L_0x006c:
        if (r3 == 0) goto L_0x0013;
    L_0x006e:
        r3.close();	 Catch:{ Throwable -> 0x0072 }
        goto L_0x0013;
    L_0x0072:
        r2 = move-exception;
        goto L_0x0032;
    L_0x0074:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x0079 }
        goto L_0x006c;
    L_0x0079:
        r0 = move-exception;
    L_0x007a:
        if (r3 == 0) goto L_0x007f;
    L_0x007c:
        r3.close();	 Catch:{ Throwable -> 0x0080 }
    L_0x007f:
        throw r0;
    L_0x0080:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x007f;
    L_0x0085:
        r0 = move-exception;
        r3 = r4;
        goto L_0x007a;
    L_0x0088:
        r2 = move-exception;
        goto L_0x005a;
    L_0x008a:
        r2 = move-exception;
        goto L_0x0049;
    L_0x008c:
        r2 = move-exception;
        goto L_0x0038;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.de.f(android.content.Context):long");
    }

    private static boolean g(Context context) {
        try {
            if (cx.m(context) != 1 || !a || d(context) < 100) {
                return false;
            }
            long f = f(context);
            long time = new Date().getTime();
            if (time - f < 3600000) {
                return false;
            }
            a(context, time);
            a = false;
            return true;
        } catch (Throwable th) {
            dg.a(th, "StatisticsManager", "isUpdate");
            return false;
        }
    }
}
