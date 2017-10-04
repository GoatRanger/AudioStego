package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.util.Base64;
import com.tencent.bugly.crashreport.common.info.a;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ag {
    public static String a(String str) throws IllegalArgumentException {
        return new String(Base64.decode(str, 0));
    }

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            Writer stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            if (!z.a(th2)) {
                th2.printStackTrace();
            }
            return "fail";
        }
    }

    public static String a() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date());
    }

    public static String a(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
    }

    public static byte[] a(byte[] bArr, int i, String str) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        z.c("rqdp{  enD:} %d %d", Integer.valueOf(bArr.length), Integer.valueOf(i));
        try {
            aq a = ap.a(i);
            if (a == null) {
                return null;
            }
            a.a(str);
            return a.b(bArr);
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] b(byte[] bArr, int i, String str) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        try {
            aq a = ap.a(i);
            if (a == null) {
                return null;
            }
            a.a(str);
            return a.a(bArr);
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            z.d("encrytype %d %s", Integer.valueOf(i), str);
            return null;
        }
    }

    public static byte[] a(File file, String str) {
        FileInputStream fileInputStream;
        ByteArrayInputStream byteArrayInputStream;
        OutputStream byteArrayOutputStream;
        ZipOutputStream zipOutputStream;
        byte[] bArr;
        int read;
        Throwable th;
        Throwable th2;
        byte[] bArr2 = null;
        z.c("rqdp{  ZF start}", new Object[0]);
        String str2 = "buglyCacheLog.txt";
        if (file != null) {
            try {
                if (file.exists() && file.canRead()) {
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        str2 = file.getName();
                        fileInputStream = fileInputStream2;
                        byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
                        try {
                            zipOutputStream.setMethod(8);
                            zipOutputStream.putNextEntry(new ZipEntry(str2));
                            bArr = new byte[1024];
                            if (fileInputStream != null) {
                                while (true) {
                                    read = fileInputStream.read(bArr);
                                    if (read > 0) {
                                        zipOutputStream.write(bArr, 0, read);
                                    }
                                }
                                read = byteArrayInputStream.read(bArr);
                                if (read <= 0) {
                                    zipOutputStream.write(bArr, 0, read);
                                } else {
                                    zipOutputStream.closeEntry();
                                    zipOutputStream.flush();
                                    zipOutputStream.finish();
                                    bArr2 = byteArrayOutputStream.toByteArray();
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if (zipOutputStream != null) {
                                        try {
                                            zipOutputStream.close();
                                        } catch (IOException e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                    z.c("rqdp{  ZF end}", new Object[0]);
                                    return bArr2;
                                }
                            }
                            while (true) {
                                read = byteArrayInputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                zipOutputStream.write(bArr, 0, read);
                            }
                            zipOutputStream.closeEntry();
                            zipOutputStream.flush();
                            zipOutputStream.finish();
                            bArr2 = byteArrayOutputStream.toByteArray();
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (zipOutputStream != null) {
                                zipOutputStream.close();
                            }
                            z.c("rqdp{  ZF end}", new Object[0]);
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        fileInputStream = fileInputStream2;
                        zipOutputStream = bArr2;
                        th2 = th4;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (zipOutputStream != null) {
                            zipOutputStream.close();
                        }
                        z.c("rqdp{  ZF end}", new Object[0]);
                        throw th2;
                    }
                    return bArr2;
                }
            } catch (Throwable th42) {
                zipOutputStream = bArr2;
                fileInputStream = bArr2;
                th2 = th42;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
                z.c("rqdp{  ZF end}", new Object[0]);
                throw th2;
            }
        }
        fileInputStream = bArr2;
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
            byteArrayOutputStream = new ByteArrayOutputStream();
            zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
            zipOutputStream.setMethod(8);
            zipOutputStream.putNextEntry(new ZipEntry(str2));
            bArr = new byte[1024];
            if (fileInputStream != null) {
                while (true) {
                    read = fileInputStream.read(bArr);
                    if (read > 0) {
                        zipOutputStream.write(bArr, 0, read);
                    }
                }
                read = byteArrayInputStream.read(bArr);
                if (read <= 0) {
                    zipOutputStream.write(bArr, 0, read);
                } else {
                    zipOutputStream.closeEntry();
                    zipOutputStream.flush();
                    zipOutputStream.finish();
                    bArr2 = byteArrayOutputStream.toByteArray();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                    z.c("rqdp{  ZF end}", new Object[0]);
                    return bArr2;
                }
            }
            while (true) {
                read = byteArrayInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                zipOutputStream.write(bArr, 0, read);
            }
            zipOutputStream.closeEntry();
            zipOutputStream.flush();
            zipOutputStream.finish();
            bArr2 = byteArrayOutputStream.toByteArray();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
            z.c("rqdp{  ZF end}", new Object[0]);
        } catch (Throwable th422) {
            zipOutputStream = bArr2;
            th2 = th422;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
            z.c("rqdp{  ZF end}", new Object[0]);
            throw th2;
        }
        return bArr2;
    }

    public static byte[] a(byte[] bArr, int i) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        z.c("rqdp{  zp:} %s rqdp{  len:} %s", Integer.valueOf(i), Integer.valueOf(bArr.length));
        try {
            ak a = aj.a(i);
            if (a == null) {
                return null;
            }
            return a.a(bArr);
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] b(byte[] bArr, int i) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        z.c("rqdp{  unzp:} %s rqdp{  len:} %s", Integer.valueOf(i), Integer.valueOf(bArr.length));
        try {
            ak a = aj.a(i);
            if (a == null) {
                return null;
            }
            return a.b(bArr);
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] a(byte[] bArr, int i, int i2, String str) {
        byte[] bArr2 = null;
        if (bArr != null) {
            try {
                bArr2 = a(a(bArr, i), i2, str);
            } catch (Throwable th) {
                if (!z.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return bArr2;
    }

    public static byte[] b(byte[] bArr, int i, int i2, String str) {
        try {
            return b(b(bArr, i2, str), i);
        } catch (Throwable e) {
            if (!z.a(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static long b() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            return simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime();
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    private static String c(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        String str = "";
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(toHexString);
        }
        return stringBuffer.toString().toUpperCase();
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "NULL";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(bArr);
            return c(instance.digest());
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static boolean a(File file, File file2, int i) {
        Throwable th;
        FileInputStream fileInputStream;
        ZipOutputStream zipOutputStream;
        FileInputStream fileInputStream2 = null;
        z.c("rqdp{  ZF start}", new Object[0]);
        if (file == null || file2 == null || file.equals(file2)) {
            z.d("rqdp{  err ZF 1R!}", new Object[0]);
            return false;
        } else if (file.exists() && file.canRead()) {
            try {
                if (!(file2.getParentFile() == null || file2.getParentFile().exists())) {
                    file2.getParentFile().mkdirs();
                }
                if (!file2.exists()) {
                    file2.createNewFile();
                }
            } catch (Throwable th2) {
                if (!z.a(th2)) {
                    th2.printStackTrace();
                }
            }
            if (!file2.exists() || !file2.canRead()) {
                return false;
            }
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    zipOutputStream = new ZipOutputStream(new FileOutputStream(file2));
                    try {
                        zipOutputStream.setMethod(8);
                        zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                        if (i <= 1000) {
                            i = 1000;
                        }
                        byte[] bArr = new byte[i];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            zipOutputStream.write(bArr, 0, read);
                        }
                        zipOutputStream.flush();
                        zipOutputStream.closeEntry();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (zipOutputStream != null) {
                            try {
                                zipOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        z.c("rqdp{  ZF end}", new Object[0]);
                        return true;
                    } catch (Throwable th3) {
                        th2 = th3;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (zipOutputStream != null) {
                            zipOutputStream.close();
                        }
                        z.c("rqdp{  ZF end}", new Object[0]);
                        throw th2;
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    zipOutputStream = null;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (zipOutputStream != null) {
                        zipOutputStream.close();
                    }
                    z.c("rqdp{  ZF end}", new Object[0]);
                    throw th2;
                }
            } catch (Throwable th5) {
                th2 = th5;
                zipOutputStream = null;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
                z.c("rqdp{  ZF end}", new Object[0]);
                throw th2;
            }
        } else {
            z.d("rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}", new Object[0]);
            return false;
        }
    }

    public static ArrayList<String> a(Context context, String[] strArr) {
        BufferedReader bufferedReader;
        Throwable th;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3 = null;
        ArrayList<String> arrayList = new ArrayList();
        if (a.a(context).P()) {
            arrayList = new ArrayList();
            arrayList.add(new String("unknown(low memory)"));
            return arrayList;
        }
        try {
            Process exec = Runtime.getRuntime().exec(strArr);
            bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    arrayList.add(readLine);
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
            while (true) {
                try {
                    String readLine2 = bufferedReader2.readLine();
                    if (readLine2 == null) {
                        break;
                    }
                    arrayList.add(readLine2);
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader2 == null) {
                return arrayList;
            }
            try {
                bufferedReader2.close();
                return arrayList;
            } catch (IOException e2) {
                e2.printStackTrace();
                return arrayList;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedReader3 != null) {
                bufferedReader3.close();
            }
            throw th;
        }
    }

    public static String a(Context context, String str) {
        if (str == null || str.trim().equals("")) {
            return "";
        }
        List a = a(context, new String[]{"/system/bin/sh", "-c", "getprop " + str});
        if (a == null || a.size() <= 0) {
            return "fail";
        }
        return (String) a.get(0);
    }

    public static void a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void c() {
        Process.killProcess(Process.myPid());
        System.exit(1);
    }

    public static boolean b(String str) {
        if (str == null || str.trim().length() <= 0) {
            return true;
        }
        return false;
    }

    public static void c(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.isFile() && file.exists() && file.canWrite()) {
                file.delete();
            }
        }
    }

    public static byte[] b(long j) {
        try {
            return ("" + j).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long b(byte[] bArr) {
        long j = -1;
        if (!(bArr == null && bArr.length == 0)) {
            try {
                j = Long.parseLong(new String(bArr, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return j;
    }

    public static Context a(Context context) {
        if (context == null) {
            return context;
        }
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }

    public static String b(Throwable th) {
        if (th == null) {
            return "";
        }
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }
}
