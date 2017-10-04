package dji.pilot.usercenter.f;

import dji.pilot.usercenter.protocol.d;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class c {
    public static boolean a(String str) {
        if (str != null) {
            return new File(str).exists();
        }
        return false;
    }

    public static boolean b(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            return true;
        }
        return false;
    }

    public static boolean a(File file) {
        if (file != null && file.exists() && file.isFile()) {
            return true;
        }
        return false;
    }

    public static boolean c(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        return false;
    }

    public static boolean b(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            return true;
        }
        return false;
    }

    public static int a(String str, FilenameFilter filenameFilter) {
        if (str == null) {
            return 0;
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return 0;
        }
        File[] listFiles = file.listFiles(filenameFilter);
        if (listFiles != null) {
            return listFiles.length;
        }
        return 0;
    }

    public static boolean d(String str) {
        if (str != null) {
            return c(new File(str));
        }
        return false;
    }

    public static boolean c(File file) {
        if (file != null) {
            if (file.exists() && file.isFile()) {
                return true;
            }
            if (e(file.getParentFile())) {
                try {
                    file.createNewFile();
                    if (file.exists() && file.isFile()) {
                        return true;
                    }
                    return false;
                } catch (Exception e) {
                    if (file.exists() && file.isFile()) {
                        return true;
                    }
                    return false;
                } catch (Throwable th) {
                    if (!file.exists() || file.isFile()) {
                    }
                }
            }
        }
        return false;
    }

    public static boolean e(String str) {
        if (str != null) {
            d(new File(str));
        }
        return false;
    }

    public static boolean d(File file) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (File d : listFiles) {
                        d(d);
                    }
                }
                file.delete();
            }
        }
        return true;
    }

    public static boolean f(String str) {
        if (str != null) {
            return e(new File(str));
        }
        return false;
    }

    public static boolean e(File file) {
        boolean z = true;
        if (file == null) {
            return false;
        }
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        boolean z2;
        try {
            file.mkdirs();
            z2 = file.exists() && file.isDirectory();
            if (!z2) {
                try {
                    file.mkdirs();
                } catch (Exception e) {
                }
            }
            if (file.exists() && file.isDirectory()) {
                return true;
            }
            return false;
        } catch (Exception e2) {
            z2 = file.exists() && file.isDirectory();
            if (!z2) {
                try {
                    file.mkdirs();
                } catch (Exception e3) {
                }
            }
            if (file.exists() && file.isDirectory()) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            if (!(file.exists() && file.isDirectory())) {
                z = false;
            }
            if (!z) {
                try {
                    file.mkdirs();
                } catch (Exception e4) {
                }
            }
            if (!file.exists() || file.isDirectory()) {
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String g(java.lang.String r5) {
        /*
        r1 = 0;
        if (r5 == 0) goto L_0x002e;
    L_0x0003:
        r2 = new java.io.File;
        r2.<init>(r5);
        r0 = a(r2);
        if (r0 == 0) goto L_0x002e;
    L_0x000e:
        r0 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0052, all -> 0x003d }
        r3 = new java.io.FileReader;	 Catch:{ Exception -> 0x0052, all -> 0x003d }
        r3.<init>(r2);	 Catch:{ Exception -> 0x0052, all -> 0x003d }
        r0.<init>(r3);	 Catch:{ Exception -> 0x0052, all -> 0x003d }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0055, all -> 0x004d }
        r2.<init>();	 Catch:{ Exception -> 0x0055, all -> 0x004d }
    L_0x001d:
        r1 = r0.readLine();	 Catch:{ Exception -> 0x0027, all -> 0x004d }
        if (r1 == 0) goto L_0x0033;
    L_0x0023:
        r2.append(r1);	 Catch:{ Exception -> 0x0027, all -> 0x004d }
        goto L_0x001d;
    L_0x0027:
        r1 = move-exception;
        r1 = r2;
    L_0x0029:
        if (r0 == 0) goto L_0x002e;
    L_0x002b:
        r0.close();	 Catch:{ Exception -> 0x0049 }
    L_0x002e:
        if (r1 != 0) goto L_0x0044;
    L_0x0030:
        r0 = "";
    L_0x0032:
        return r0;
    L_0x0033:
        if (r0 == 0) goto L_0x0057;
    L_0x0035:
        r0.close();	 Catch:{ Exception -> 0x003a }
        r1 = r2;
        goto L_0x002e;
    L_0x003a:
        r0 = move-exception;
        r1 = r2;
        goto L_0x002e;
    L_0x003d:
        r0 = move-exception;
    L_0x003e:
        if (r1 == 0) goto L_0x0043;
    L_0x0040:
        r1.close();	 Catch:{ Exception -> 0x004b }
    L_0x0043:
        throw r0;
    L_0x0044:
        r0 = r1.toString();
        goto L_0x0032;
    L_0x0049:
        r0 = move-exception;
        goto L_0x002e;
    L_0x004b:
        r1 = move-exception;
        goto L_0x0043;
    L_0x004d:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x003e;
    L_0x0052:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0029;
    L_0x0055:
        r2 = move-exception;
        goto L_0x0029;
    L_0x0057:
        r1 = r2;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.usercenter.f.c.g(java.lang.String):java.lang.String");
    }

    public static boolean a(String str, String str2, boolean z) {
        BufferedWriter bufferedWriter;
        Throwable th;
        boolean z2 = false;
        if (str != null && d(str) && str2 != null && str2.length() > 0) {
            BufferedWriter bufferedWriter2 = null;
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(str, z), 1024);
                try {
                    bufferedWriter.write(str2);
                    bufferedWriter.flush();
                    z2 = true;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e2) {
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (Exception e3) {
                        }
                    }
                    return z2;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedWriter2 = bufferedWriter;
                    if (bufferedWriter2 != null) {
                        try {
                            bufferedWriter2.close();
                        } catch (Exception e4) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                bufferedWriter = null;
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                return z2;
            } catch (Throwable th3) {
                th = th3;
                if (bufferedWriter2 != null) {
                    bufferedWriter2.close();
                }
                throw th;
            }
        }
        return z2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
        r0 = 0;
        r6 = 0;
        if (r9 == 0) goto L_0x007c;
    L_0x0004:
        r1 = b(r9);
        if (r1 == 0) goto L_0x007c;
    L_0x000a:
        if (r10 == 0) goto L_0x007c;
    L_0x000c:
        r1 = r10.length();
        if (r1 <= 0) goto L_0x007c;
    L_0x0012:
        r7 = new java.io.RandomAccessFile;	 Catch:{ Exception -> 0x0046, all -> 0x0057 }
        r1 = "rw";
        r7.<init>(r9, r1);	 Catch:{ Exception -> 0x0046, all -> 0x0057 }
        r0 = r7.getChannel();	 Catch:{ Exception -> 0x0079, all -> 0x006f }
        r1 = java.nio.channels.FileChannel.MapMode.READ_WRITE;	 Catch:{ Exception -> 0x0079, all -> 0x0074 }
        r2 = 0;
        r4 = r10.length();	 Catch:{ Exception -> 0x0079, all -> 0x0074 }
        r4 = r4 + 4;
        r4 = (long) r4;	 Catch:{ Exception -> 0x0079, all -> 0x0074 }
        r1 = r0.map(r1, r2, r4);	 Catch:{ Exception -> 0x0079, all -> 0x0074 }
        r2 = r10.getBytes(r11);	 Catch:{ Exception -> 0x0079, all -> 0x0074 }
        r1.put(r2);	 Catch:{ Exception -> 0x0079, all -> 0x0074 }
        r1.force();	 Catch:{ Exception -> 0x0079, all -> 0x0074 }
        r1 = 1;
        if (r0 == 0) goto L_0x003c;
    L_0x0039:
        r0.close();	 Catch:{ Exception -> 0x0067 }
    L_0x003c:
        if (r7 == 0) goto L_0x007e;
    L_0x003e:
        r7.close();	 Catch:{ Exception -> 0x0043 }
        r0 = r1;
    L_0x0042:
        return r0;
    L_0x0043:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0042;
    L_0x0046:
        r1 = move-exception;
        r1 = r0;
    L_0x0048:
        if (r0 == 0) goto L_0x004d;
    L_0x004a:
        r0.close();	 Catch:{ Exception -> 0x0069 }
    L_0x004d:
        if (r1 == 0) goto L_0x007c;
    L_0x004f:
        r1.close();	 Catch:{ Exception -> 0x0054 }
        r0 = r6;
        goto L_0x0042;
    L_0x0054:
        r0 = move-exception;
        r0 = r6;
        goto L_0x0042;
    L_0x0057:
        r1 = move-exception;
        r7 = r0;
        r8 = r0;
        r0 = r1;
        r1 = r8;
    L_0x005c:
        if (r1 == 0) goto L_0x0061;
    L_0x005e:
        r1.close();	 Catch:{ Exception -> 0x006b }
    L_0x0061:
        if (r7 == 0) goto L_0x0066;
    L_0x0063:
        r7.close();	 Catch:{ Exception -> 0x006d }
    L_0x0066:
        throw r0;
    L_0x0067:
        r0 = move-exception;
        goto L_0x003c;
    L_0x0069:
        r0 = move-exception;
        goto L_0x004d;
    L_0x006b:
        r1 = move-exception;
        goto L_0x0061;
    L_0x006d:
        r1 = move-exception;
        goto L_0x0066;
    L_0x006f:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x005c;
    L_0x0074:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x005c;
    L_0x0079:
        r1 = move-exception;
        r1 = r7;
        goto L_0x0048;
    L_0x007c:
        r0 = r6;
        goto L_0x0042;
    L_0x007e:
        r0 = r1;
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.usercenter.f.c.a(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    public static boolean a(InputStream inputStream, String str) {
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream;
        Throwable th;
        BufferedOutputStream bufferedOutputStream2 = null;
        boolean z = false;
        if (!(inputStream == null || str == null)) {
            BufferedInputStream bufferedInputStream2;
            try {
                bufferedInputStream2 = new BufferedInputStream(inputStream);
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
                } catch (Exception e) {
                    bufferedOutputStream = null;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e2) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (Exception e4) {
                        }
                    }
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (Exception e5) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e6) {
                        }
                    }
                    if (bufferedOutputStream2 != null) {
                        try {
                            bufferedOutputStream2.close();
                        } catch (Exception e7) {
                        }
                    }
                    throw th;
                }
                try {
                    byte[] bArr = new byte[2048];
                    while (true) {
                        int read = bufferedInputStream2.read(bArr, 0, 2048);
                        if (read <= 0) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                    bufferedOutputStream.flush();
                    z = true;
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (Exception e8) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e9) {
                        }
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (Exception e10) {
                        }
                    }
                } catch (Exception e11) {
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    return z;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedOutputStream2 = bufferedOutputStream;
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    throw th;
                }
            } catch (Exception e12) {
                bufferedOutputStream = null;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                return z;
            } catch (Throwable th4) {
                th = th4;
                bufferedInputStream2 = null;
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                throw th;
            }
        }
        return z;
    }

    public static boolean b(InputStream inputStream, String str) {
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        BufferedReader bufferedReader2;
        Throwable th;
        BufferedWriter bufferedWriter2 = null;
        boolean z = false;
        if (!(inputStream == null || str == null)) {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(str), 1024);
                } catch (Exception e) {
                    bufferedWriter = null;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception e2) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (Exception e4) {
                        }
                    }
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e5) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e6) {
                        }
                    }
                    if (bufferedWriter2 != null) {
                        try {
                            bufferedWriter2.close();
                        } catch (Exception e7) {
                        }
                    }
                    throw th;
                }
                try {
                    char[] cArr = new char[1024];
                    while (true) {
                        int read = bufferedReader.read(cArr, 0, 1024);
                        if (read <= 0) {
                            break;
                        }
                        bufferedWriter.write(cArr, 0, read);
                    }
                    bufferedWriter.flush();
                    z = true;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e8) {
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e9) {
                        }
                    }
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (Exception e10) {
                        }
                    }
                } catch (Exception e11) {
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    return z;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedWriter2 = bufferedWriter;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                    throw th;
                }
            } catch (Exception e12) {
                bufferedWriter = null;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                return z;
            } catch (Throwable th4) {
                th = th4;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedWriter2 != null) {
                    bufferedWriter2.close();
                }
                throw th;
            }
        }
        return z;
    }

    public static boolean a(String str, String str2) {
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        BufferedReader bufferedReader2;
        Throwable th;
        BufferedWriter bufferedWriter2 = null;
        boolean z = false;
        if (!(str == null || str2 == null)) {
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                try {
                    bufferedReader = new BufferedReader(new FileReader(str), 1024);
                    try {
                        bufferedWriter = new BufferedWriter(new FileWriter(str2), 1024);
                        while (true) {
                            try {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                bufferedWriter.write(readLine);
                            } catch (Exception e) {
                                bufferedReader2 = bufferedReader;
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedWriter2 = bufferedWriter;
                            }
                        }
                        bufferedWriter.flush();
                        z = true;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e2) {
                            }
                        }
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (Exception e3) {
                            }
                        }
                    } catch (Exception e4) {
                        bufferedWriter = null;
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Exception e5) {
                            }
                        }
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (Exception e6) {
                            }
                        }
                        return z;
                    } catch (Throwable th3) {
                        th = th3;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e7) {
                            }
                        }
                        if (bufferedWriter2 != null) {
                            try {
                                bufferedWriter2.close();
                            } catch (Exception e8) {
                            }
                        }
                        throw th;
                    }
                } catch (Exception e9) {
                    bufferedWriter = null;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    return z;
                } catch (Throwable th4) {
                    th = th4;
                    bufferedReader = null;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                    throw th;
                }
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(java.lang.String r9, java.lang.String r10) {
        /*
        r0 = 1;
        r2 = 0;
        r1 = 0;
        if (r9 == 0) goto L_0x00c8;
    L_0x0005:
        if (r10 == 0) goto L_0x00c8;
    L_0x0007:
        r3 = new java.io.File;
        r3.<init>(r9);
        r4 = r3.exists();
        if (r4 == 0) goto L_0x00c8;
    L_0x0012:
        r4 = r3.isFile();
        if (r4 == 0) goto L_0x00c8;
    L_0x0018:
        r5 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x00b3, all -> 0x007d }
        r5.<init>(r3);	 Catch:{ Exception -> 0x00b3, all -> 0x007d }
        r4 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x00b8, all -> 0x00aa }
        r4.<init>(r10);	 Catch:{ Exception -> 0x00b8, all -> 0x00aa }
        r3 = r5.getChannel();	 Catch:{ Exception -> 0x00bd, all -> 0x00ae }
        r2 = r4.getChannel();	 Catch:{ Exception -> 0x00c2, all -> 0x00b1 }
        r6 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r6 = java.nio.ByteBuffer.allocate(r6);	 Catch:{ Exception -> 0x0044, all -> 0x00b1 }
        r6.clear();	 Catch:{ Exception -> 0x0044, all -> 0x00b1 }
    L_0x0033:
        r7 = -1;
        r8 = r3.read(r6);	 Catch:{ Exception -> 0x0044, all -> 0x00b1 }
        if (r7 == r8) goto L_0x005f;
    L_0x003a:
        r6.flip();	 Catch:{ Exception -> 0x0044, all -> 0x00b1 }
        r2.write(r6);	 Catch:{ Exception -> 0x0044, all -> 0x00b1 }
        r6.clear();	 Catch:{ Exception -> 0x0044, all -> 0x00b1 }
        goto L_0x0033;
    L_0x0044:
        r0 = move-exception;
        r0 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r5;
    L_0x0049:
        if (r0 == 0) goto L_0x004e;
    L_0x004b:
        r0.close();	 Catch:{ Exception -> 0x009c }
    L_0x004e:
        if (r2 == 0) goto L_0x0053;
    L_0x0050:
        r2.close();	 Catch:{ Exception -> 0x009e }
    L_0x0053:
        if (r4 == 0) goto L_0x0058;
    L_0x0055:
        r4.close();	 Catch:{ Exception -> 0x00a0 }
    L_0x0058:
        if (r3 == 0) goto L_0x00c8;
    L_0x005a:
        r3.close();	 Catch:{ Exception -> 0x007a }
        r0 = r1;
    L_0x005e:
        return r0;
    L_0x005f:
        r6 = 1;
        r2.force(r6);	 Catch:{ Exception -> 0x0044, all -> 0x00b1 }
        if (r2 == 0) goto L_0x0068;
    L_0x0065:
        r2.close();	 Catch:{ Exception -> 0x0096 }
    L_0x0068:
        if (r3 == 0) goto L_0x006d;
    L_0x006a:
        r3.close();	 Catch:{ Exception -> 0x0098 }
    L_0x006d:
        if (r5 == 0) goto L_0x0072;
    L_0x006f:
        r5.close();	 Catch:{ Exception -> 0x009a }
    L_0x0072:
        if (r4 == 0) goto L_0x005e;
    L_0x0074:
        r4.close();	 Catch:{ Exception -> 0x0078 }
        goto L_0x005e;
    L_0x0078:
        r1 = move-exception;
        goto L_0x005e;
    L_0x007a:
        r0 = move-exception;
        r0 = r1;
        goto L_0x005e;
    L_0x007d:
        r0 = move-exception;
        r3 = r2;
        r4 = r2;
        r5 = r2;
    L_0x0081:
        if (r2 == 0) goto L_0x0086;
    L_0x0083:
        r2.close();	 Catch:{ Exception -> 0x00a2 }
    L_0x0086:
        if (r3 == 0) goto L_0x008b;
    L_0x0088:
        r3.close();	 Catch:{ Exception -> 0x00a4 }
    L_0x008b:
        if (r5 == 0) goto L_0x0090;
    L_0x008d:
        r5.close();	 Catch:{ Exception -> 0x00a6 }
    L_0x0090:
        if (r4 == 0) goto L_0x0095;
    L_0x0092:
        r4.close();	 Catch:{ Exception -> 0x00a8 }
    L_0x0095:
        throw r0;
    L_0x0096:
        r1 = move-exception;
        goto L_0x0068;
    L_0x0098:
        r1 = move-exception;
        goto L_0x006d;
    L_0x009a:
        r1 = move-exception;
        goto L_0x0072;
    L_0x009c:
        r0 = move-exception;
        goto L_0x004e;
    L_0x009e:
        r0 = move-exception;
        goto L_0x0053;
    L_0x00a0:
        r0 = move-exception;
        goto L_0x0058;
    L_0x00a2:
        r1 = move-exception;
        goto L_0x0086;
    L_0x00a4:
        r1 = move-exception;
        goto L_0x008b;
    L_0x00a6:
        r1 = move-exception;
        goto L_0x0090;
    L_0x00a8:
        r1 = move-exception;
        goto L_0x0095;
    L_0x00aa:
        r0 = move-exception;
        r3 = r2;
        r4 = r2;
        goto L_0x0081;
    L_0x00ae:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0081;
    L_0x00b1:
        r0 = move-exception;
        goto L_0x0081;
    L_0x00b3:
        r0 = move-exception;
        r0 = r2;
        r3 = r2;
        r4 = r2;
        goto L_0x0049;
    L_0x00b8:
        r0 = move-exception;
        r0 = r2;
        r3 = r2;
        r4 = r5;
        goto L_0x0049;
    L_0x00bd:
        r0 = move-exception;
        r0 = r2;
        r3 = r4;
        r4 = r5;
        goto L_0x0049;
    L_0x00c2:
        r0 = move-exception;
        r0 = r2;
        r2 = r3;
        r3 = r4;
        r4 = r5;
        goto L_0x0049;
    L_0x00c8:
        r0 = r1;
        goto L_0x005e;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.usercenter.f.c.b(java.lang.String, java.lang.String):boolean");
    }

    public static boolean c(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return a(new File(str), new File(str2));
    }

    public static boolean a(File file, File file2) {
        int i = 0;
        if (file == null || file2 == null || !b(file) || !e(file2)) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return true;
        }
        int length = listFiles.length;
        while (i < length) {
            File file3 = listFiles[i];
            if (file3.isFile()) {
                b(file3.getAbsolutePath(), file2.getAbsolutePath() + d.t + file3.getName());
            } else if (file3.isDirectory()) {
                a(file3, new File(file2.getAbsolutePath() + File.separator + file3.getName()));
            }
            i++;
        }
        return true;
    }
}
