package dji.pilot.publics.control.upgrade;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.natives.GroudStation;
import dji.pilot.publics.e.d;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

public class e {
    public static final int a = 64;
    public static final int b = 52;
    public static final int c = 272;
    public static final int d = 16;
    private static final String e = dji.pilot.publics.control.rc.a.class.getSimpleName();

    public static final class a {
        public int a = -1;
        public int b = -1;
        public int c = -1;
        public int d = -1;
        public int e = -1;
        public long f = 0;
        public int g = 0;
        public int h = 0;
        public int i = 0;
        public final byte[] j = new byte[16];
        public final byte[] k = new byte[16];
        public String l;

        public String toString() {
            return String.format("deviceId = %d, moduleId = %d", new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b)});
        }
    }

    public static final class b {
        public int a = 0;
        public int b = 0;
        public int c = 0;
        public int d = 0;
        public String e = null;
        public String f = null;
        public int g = 0;
        public byte[] h = new byte[18];
    }

    public static final class c {
        public boolean a = false;
        public b b = null;
        public final ArrayList<a> c = new ArrayList();

        public a a(String str) {
            if (str == null || str.length() != 4 || this.c == null || this.c.size() == 0) {
                return null;
            }
            int intValue = Integer.valueOf(str.substring(0, 2)).intValue();
            int intValue2 = Integer.valueOf(str.substring(2, 4)).intValue();
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar.a == intValue && aVar.b == intValue2) {
                    return aVar;
                }
            }
            return null;
        }

        public a a(DeviceType deviceType, int i) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar.a == deviceType.value() && aVar.b == i) {
                    return aVar;
                }
            }
            return null;
        }

        public String b(String str) {
            int intValue = Integer.valueOf(str.substring(0, 2)).intValue();
            int intValue2 = Integer.valueOf(str.substring(2, 3)).intValue();
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar.a == intValue && aVar.b == intValue2) {
                    return aVar.l;
                }
            }
            return "";
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static dji.pilot.publics.control.upgrade.e.c a(java.lang.String r13, dji.midware.data.config.P3.ProductType r14, boolean r15) {
        /*
        r12 = 64;
        r11 = 52;
        r10 = 2;
        r3 = 0;
        r0 = 0;
        r4 = dji.logic.f.d.f(r14);
        if (r13 == 0) goto L_0x0013;
    L_0x000d:
        r1 = dji.pilot.publics.e.d.a(r4);
        if (r1 == 0) goto L_0x0014;
    L_0x0013:
        return r0;
    L_0x0014:
        r5 = new java.io.File;
        r5.<init>(r13);
        r1 = r5.exists();
        if (r1 == 0) goto L_0x0013;
    L_0x001f:
        r1 = r5.isFile();
        if (r1 == 0) goto L_0x0013;
    L_0x0025:
        r6 = r5.length();
        r8 = 272; // 0x110 float:3.81E-43 double:1.344E-321;
        r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r1 < 0) goto L_0x0013;
    L_0x002f:
        r2 = 0;
        r1 = new java.io.RandomAccessFile;	 Catch:{ Exception -> 0x0117, all -> 0x010b }
        r6 = "r";
        r1.<init>(r5, r6);	 Catch:{ Exception -> 0x0117, all -> 0x010b }
        r2 = 64;
        r5 = new byte[r2];	 Catch:{ Exception -> 0x011a }
        r2 = 0;
        r6 = 64;
        r2 = r1.read(r5, r2, r6);	 Catch:{ Exception -> 0x011a }
        if (r2 != r12) goto L_0x00e2;
    L_0x0044:
        r2 = new dji.pilot.publics.control.upgrade.e$c;	 Catch:{ Exception -> 0x011a }
        r2.<init>();	 Catch:{ Exception -> 0x011a }
        r0 = a(r5);	 Catch:{ Exception -> 0x00f2 }
        r2.b = r0;	 Catch:{ Exception -> 0x00f2 }
        r0 = r2.b;	 Catch:{ Exception -> 0x00f2 }
        r0 = r0.f;	 Catch:{ Exception -> 0x00f2 }
        r0 = r4.equalsIgnoreCase(r0);	 Catch:{ Exception -> 0x00f2 }
        if (r0 == 0) goto L_0x00e1;
    L_0x0059:
        r4 = 0;
        r1.seek(r4);	 Catch:{ Exception -> 0x00f2 }
        r0 = r2.b;	 Catch:{ Exception -> 0x00f2 }
        r0 = r0.c;	 Catch:{ Exception -> 0x00f2 }
        r0 = r0 + -2;
        r0 = new byte[r0];	 Catch:{ Exception -> 0x00f2 }
        r4 = 0;
        r5 = r2.b;	 Catch:{ Exception -> 0x00f2 }
        r5 = r5.c;	 Catch:{ Exception -> 0x00f2 }
        r5 = r5 + -2;
        r4 = r1.read(r0, r4, r5);	 Catch:{ Exception -> 0x00f2 }
        r5 = r2.b;	 Catch:{ Exception -> 0x00f2 }
        r5 = r5.c;	 Catch:{ Exception -> 0x00f2 }
        r5 = r5 + -2;
        if (r4 != r5) goto L_0x00e1;
    L_0x0079:
        r0 = dji.midware.natives.GroudStation.native_calcCrc16(r0);	 Catch:{ Exception -> 0x00f2 }
        r4 = 2;
        r4 = new byte[r4];	 Catch:{ Exception -> 0x00f2 }
        r5 = 0;
        r6 = 2;
        r5 = r1.read(r4, r5, r6);	 Catch:{ Exception -> 0x00f2 }
        if (r5 != r10) goto L_0x00e1;
    L_0x0088:
        r5 = 0;
        r4 = dji.midware.util.c.a(r4, r5);	 Catch:{ Exception -> 0x00f2 }
        if (r4 != r0) goto L_0x00e1;
    L_0x008f:
        r0 = r2.b;	 Catch:{ Exception -> 0x00f2 }
        r0 = r0.g;	 Catch:{ Exception -> 0x00f2 }
        r0 = r0 * 52;
        r0 = r0 + 64;
        r0 = r0 + 2;
        r4 = r2.b;	 Catch:{ Exception -> 0x00f2 }
        r4 = r4.c;	 Catch:{ Exception -> 0x00f2 }
        if (r0 != r4) goto L_0x00e1;
    L_0x009f:
        r4 = 64;
        r1.seek(r4);	 Catch:{ Exception -> 0x00f2 }
        r0 = 52;
        r4 = new byte[r0];	 Catch:{ Exception -> 0x00f2 }
        r0 = r3;
    L_0x00a9:
        r3 = r0 + 1;
        r5 = r2.b;	 Catch:{ Exception -> 0x00f2 }
        r5 = r5.g;	 Catch:{ Exception -> 0x00f2 }
        if (r0 >= r5) goto L_0x00de;
    L_0x00b1:
        r0 = 0;
        r5 = 52;
        r0 = r1.read(r4, r0, r5);	 Catch:{ Exception -> 0x00f2 }
        if (r0 != r11) goto L_0x00de;
    L_0x00ba:
        r0 = b(r4);	 Catch:{ Exception -> 0x00f2 }
        if (r15 == 0) goto L_0x00ec;
    L_0x00c0:
        r5 = a(r1, r0);	 Catch:{ Exception -> 0x00f2 }
        if (r5 == 0) goto L_0x00db;
    L_0x00c6:
        r5 = r2.c;	 Catch:{ Exception -> 0x00f2 }
        r5.add(r0);	 Catch:{ Exception -> 0x00f2 }
    L_0x00cb:
        r0 = r2.c;	 Catch:{ Exception -> 0x00f2 }
        r0 = r0.size();	 Catch:{ Exception -> 0x00f2 }
        r0 = r0 * 52;
        r0 = r0 + 64;
        r6 = (long) r0;	 Catch:{ Exception -> 0x00f2 }
        r1.seek(r6);	 Catch:{ Exception -> 0x00f2 }
        r0 = r3;
        goto L_0x00a9;
    L_0x00db:
        r0 = 0;
        r2.a = r0;	 Catch:{ Exception -> 0x00f2 }
    L_0x00de:
        r0 = 1;
        r2.a = r0;	 Catch:{ Exception -> 0x00f2 }
    L_0x00e1:
        r0 = r2;
    L_0x00e2:
        if (r1 == 0) goto L_0x0013;
    L_0x00e4:
        r1.close();	 Catch:{ Exception -> 0x00e9 }
        goto L_0x0013;
    L_0x00e9:
        r1 = move-exception;
        goto L_0x0013;
    L_0x00ec:
        r5 = r2.c;	 Catch:{ Exception -> 0x00f2 }
        r5.add(r0);	 Catch:{ Exception -> 0x00f2 }
        goto L_0x00cb;
    L_0x00f2:
        r0 = move-exception;
        r0 = r2;
    L_0x00f4:
        r2 = dji.log.DJILogHelper.getInstance();	 Catch:{ all -> 0x0115 }
        r3 = e;	 Catch:{ all -> 0x0115 }
        r4 = "read package exception";
        r5 = 0;
        r6 = 1;
        r2.LOGD(r3, r4, r5, r6);	 Catch:{ all -> 0x0115 }
        if (r1 == 0) goto L_0x0013;
    L_0x0103:
        r1.close();	 Catch:{ Exception -> 0x0108 }
        goto L_0x0013;
    L_0x0108:
        r1 = move-exception;
        goto L_0x0013;
    L_0x010b:
        r0 = move-exception;
        r1 = r2;
    L_0x010d:
        if (r1 == 0) goto L_0x0112;
    L_0x010f:
        r1.close();	 Catch:{ Exception -> 0x0113 }
    L_0x0112:
        throw r0;
    L_0x0113:
        r1 = move-exception;
        goto L_0x0112;
    L_0x0115:
        r0 = move-exception;
        goto L_0x010d;
    L_0x0117:
        r1 = move-exception;
        r1 = r2;
        goto L_0x00f4;
    L_0x011a:
        r2 = move-exception;
        goto L_0x00f4;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.publics.control.upgrade.e.a(java.lang.String, dji.midware.data.config.P3.ProductType, boolean):dji.pilot.publics.control.upgrade.e$c");
    }

    private static boolean a(byte[] bArr, byte[] bArr2, int i) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                i2++;
                return false;
            }
        }
        return true;
    }

    public static boolean a(b bVar, ArrayList<a> arrayList, String str, String str2) {
        boolean z;
        RandomAccessFile randomAccessFile;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        if (bVar == null || arrayList == null || arrayList.size() <= 0 || d.a(str) || d.a(str2)) {
            return false;
        }
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        File file2 = new File(str2);
        if (file2.exists()) {
            file2.delete();
        }
        RandomAccessFile randomAccessFile2;
        FileOutputStream fileOutputStream2;
        try {
            randomAccessFile2 = new RandomAccessFile(file, "r");
            try {
                fileOutputStream2 = new FileOutputStream(file2);
                try {
                    fileOutputStream2.write(a(bVar, (ArrayList) arrayList));
                    byte[] bArr = new byte[8192];
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        int i;
                        a aVar = (a) it.next();
                        if (aVar.h < 8192) {
                            i = aVar.h;
                        } else {
                            i = 8192;
                        }
                        randomAccessFile2.seek((long) aVar.g);
                        int i2 = 0;
                        do {
                            int read = randomAccessFile2.read(bArr, 0, i);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream2.write(bArr, 0, read);
                            i2 += read;
                            if (aVar.h - i2 < i) {
                                i = aVar.h - i2;
                            }
                        } while (i2 < aVar.h);
                    }
                    fileOutputStream2.flush();
                    randomAccessFile2.close();
                    fileOutputStream2.close();
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (Exception e) {
                        }
                    }
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                            z = true;
                        } catch (Exception e2) {
                            z = true;
                        }
                    } else {
                        z = true;
                    }
                } catch (Exception e3) {
                    fileOutputStream = fileOutputStream2;
                    randomAccessFile = randomAccessFile2;
                    try {
                        DJILogHelper.getInstance().LOGD(e, "assembleNewBinFile exception", false, true);
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (Exception e4) {
                            }
                        }
                        if (fileOutputStream != null) {
                            z = false;
                        } else {
                            try {
                                fileOutputStream.close();
                                z = false;
                            } catch (Exception e5) {
                                z = false;
                            }
                        }
                        return z;
                    } catch (Throwable th2) {
                        fileOutputStream2 = fileOutputStream;
                        randomAccessFile2 = randomAccessFile;
                        th = th2;
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (Exception e6) {
                            }
                        }
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (Exception e7) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (randomAccessFile2 != null) {
                        randomAccessFile2.close();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th;
                }
            } catch (Exception e8) {
                randomAccessFile = randomAccessFile2;
                DJILogHelper.getInstance().LOGD(e, "assembleNewBinFile exception", false, true);
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                    z = false;
                } else {
                    z = false;
                }
                return z;
            } catch (Throwable th4) {
                fileOutputStream2 = null;
                th = th4;
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
        } catch (Exception e9) {
            randomAccessFile = null;
            DJILogHelper.getInstance().LOGD(e, "assembleNewBinFile exception", false, true);
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
                z = false;
            } else {
                z = false;
            }
            return z;
        } catch (Throwable th42) {
            fileOutputStream2 = null;
            randomAccessFile2 = null;
            th = th42;
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
        return z;
    }

    public static boolean a(a aVar, String str, String str2) {
        FileOutputStream fileOutputStream;
        Throwable th;
        RandomAccessFile randomAccessFile = null;
        boolean z = true;
        if (d.a(str) || d.a(str2)) {
            return false;
        }
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        File file2 = new File(str2);
        if (file2.exists()) {
            file2.delete();
        }
        RandomAccessFile randomAccessFile2;
        FileOutputStream fileOutputStream2;
        try {
            randomAccessFile2 = new RandomAccessFile(file, "r");
            try {
                fileOutputStream2 = new FileOutputStream(file2);
                try {
                    int i;
                    byte[] bArr = new byte[8192];
                    if (aVar.h < 8192) {
                        i = aVar.h;
                    } else {
                        i = 8192;
                    }
                    randomAccessFile2.seek((long) aVar.g);
                    int i2 = 0;
                    do {
                        int read = randomAccessFile2.read(bArr, 0, i);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream2.write(bArr, 0, read);
                        i2 += read;
                        if (aVar.h - i2 < i) {
                            i = aVar.h - i2;
                        }
                    } while (i2 < aVar.h);
                    fileOutputStream2.flush();
                    randomAccessFile2.close();
                    fileOutputStream2.close();
                    if (randomAccessFile2 != null) {
                        try {
                            randomAccessFile2.close();
                        } catch (Exception e) {
                        }
                    }
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Exception e2) {
                        }
                    }
                } catch (Exception e3) {
                    fileOutputStream = fileOutputStream2;
                    randomAccessFile = randomAccessFile2;
                    try {
                        DJILogHelper.getInstance().LOGD(e, "assembleNewBinFile exception", false, true);
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (Exception e4) {
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                                z = false;
                            } catch (Exception e5) {
                                z = false;
                            }
                        } else {
                            z = false;
                        }
                        return z;
                    } catch (Throwable th2) {
                        fileOutputStream2 = fileOutputStream;
                        randomAccessFile2 = randomAccessFile;
                        th = th2;
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (Exception e6) {
                            }
                        }
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (Exception e7) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (randomAccessFile2 != null) {
                        randomAccessFile2.close();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th;
                }
            } catch (Exception e8) {
                fileOutputStream = null;
                randomAccessFile = randomAccessFile2;
                DJILogHelper.getInstance().LOGD(e, "assembleNewBinFile exception", false, true);
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                if (fileOutputStream != null) {
                    z = false;
                } else {
                    fileOutputStream.close();
                    z = false;
                }
                return z;
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream2 = null;
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
        } catch (Exception e9) {
            fileOutputStream = null;
            DJILogHelper.getInstance().LOGD(e, "assembleNewBinFile exception", false, true);
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
                z = false;
            } else {
                z = false;
            }
            return z;
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream2 = null;
            randomAccessFile2 = null;
            if (randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
        return z;
    }

    private static byte[] a(b bVar, ArrayList<a> arrayList) {
        int size = ((arrayList.size() * 52) + 64) + 2;
        b bVar2 = new b();
        bVar2.a = bVar.a;
        bVar2.b = bVar.b;
        bVar2.c = size;
        bVar2.d = bVar.d;
        bVar2.e = bVar.e;
        bVar2.f = bVar.f;
        bVar2.g = arrayList.size();
        System.arraycopy(bVar.h, 0, bVar2.h, 0, 18);
        byte[] bArr = new byte[(size - 2)];
        Object obj = new byte[size];
        a(bVar2, bArr, 0);
        Iterator it = arrayList.iterator();
        int i = size;
        int i2 = 64;
        while (it.hasNext()) {
            a aVar = (a) it.next();
            a(aVar, bArr, i2, i);
            i2 += 52;
            i = aVar.h + i;
        }
        short native_calcCrc16 = GroudStation.native_calcCrc16(bArr);
        System.arraycopy(bArr, 0, obj, 0, size - 2);
        System.arraycopy(dji.midware.util.c.b(native_calcCrc16), 0, obj, size - 2, 2);
        return obj;
    }

    private static String b(byte[] bArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            if (bArr[i3] == (byte) 0) {
                i2 = i3 - i;
                break;
            }
        }
        return new String(bArr, i, i2, Charset.forName("UTF-8"));
    }

    private static boolean a(RandomAccessFile randomAccessFile, a aVar) throws IOException {
        int i = 8192;
        byte[] bArr = new byte[8192];
        randomAccessFile.seek((long) aVar.g);
        int i2 = aVar.h;
        if (i2 < 8192) {
            i = i2;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            int i3 = 0;
            do {
                int read = randomAccessFile.read(bArr, 0, i);
                if (read == -1) {
                    break;
                }
                instance.update(bArr, 0, read);
                i3 += read;
                if (i2 - i3 < i) {
                    i = i2 - i3;
                    continue;
                }
            } while (i3 < i2);
            return dji.thirdparty.afinal.c.c.a(aVar.j, instance.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static b a(byte[] bArr) {
        b bVar = new b();
        bVar.a = dji.midware.util.c.b(bArr, 0);
        bVar.b = dji.midware.util.c.a(bArr, 4);
        bVar.c = dji.midware.util.c.a(bArr, 6);
        bVar.d = dji.midware.util.c.b(bArr, 8);
        bVar.e = b(bArr, 12, 16);
        bVar.f = b(bArr, 28, 16);
        bVar.g = dji.midware.util.c.a(bArr, 44);
        System.arraycopy(bArr, 46, bVar.h, 0, 18);
        return bVar;
    }

    private static byte[] a(b bVar, byte[] bArr, int i) {
        if (bVar == null && bArr.length - i < 64) {
            return null;
        }
        System.arraycopy(dji.midware.util.c.a(bVar.a), 0, bArr, i, 4);
        int i2 = i + 4;
        System.arraycopy(dji.midware.util.c.b((short) bVar.b), 0, bArr, i2, 2);
        i2 += 2;
        System.arraycopy(dji.midware.util.c.b((short) bVar.c), 0, bArr, i2, 2);
        i2 += 2;
        System.arraycopy(dji.midware.util.c.a(bVar.d), 0, bArr, i2, 4);
        i2 += 4;
        System.arraycopy(a(dji.midware.util.c.b(bVar.e), 16), 0, bArr, i2, 16);
        i2 += 16;
        System.arraycopy(a(dji.midware.util.c.b(bVar.f), 16), 0, bArr, i2, 16);
        i2 += 16;
        System.arraycopy(dji.midware.util.c.b((short) bVar.g), 0, bArr, i2, 2);
        i2 += 2;
        System.arraycopy(bVar.h, 0, bArr, 46, 18);
        return bArr;
    }

    private static byte[] a(byte[] bArr, int i) {
        int length = i - bArr.length;
        if (length > 0) {
            return dji.midware.util.c.a(bArr, new byte[length]);
        }
        return bArr;
    }

    private static a b(byte[] bArr) {
        a aVar = new a();
        aVar.a = bArr[0] & 31;
        aVar.b = (bArr[0] & dji.thirdparty.g.b.a.a.fw_) >>> 5;
        aVar.c = bArr[1] & 255;
        aVar.d = bArr[2] & 255;
        aVar.e = bArr[3] & 255;
        aVar.f = d.a(bArr, 4, 4);
        aVar.l = a(aVar.a, ".", bArr, 4);
        aVar.g = dji.midware.util.c.b(bArr, 8);
        aVar.h = dji.midware.util.c.b(bArr, 12);
        aVar.i = dji.midware.util.c.b(bArr, 16);
        System.arraycopy(bArr, 20, aVar.j, 0, 16);
        System.arraycopy(bArr, 36, aVar.k, 0, 16);
        return aVar;
    }

    public static String a(int i, String str, byte[] bArr, int i2) {
        if (i == DeviceType.CAMERA.value()) {
            int intValue = a(bArr, i2, 2).intValue();
            return String.format("%02d" + str + "%02d" + str + "%02d" + str + "%02d", new Object[]{a(bArr, i2 + 3, 1), a(bArr, i2 + 2, 1), Integer.valueOf(intValue / 100), Integer.valueOf(intValue % 100)});
        }
        return String.format("%02d" + str + "%02d" + str + "%02d" + str + "%02d", new Object[]{a(bArr, i2 + 3, 1), a(bArr, i2 + 2, 1), a(bArr, i2 + 1, 1), a(bArr, i2, 1)});
    }

    protected static Integer a(byte[] bArr, int i, int i2) {
        byte[] bArr2;
        if (bArr.length < i + i2) {
            bArr2 = new byte[i2];
        } else {
            bArr2 = dji.midware.util.c.e(bArr, i, i2);
        }
        return Integer.valueOf(dji.midware.util.c.b(bArr2));
    }

    private static byte[] a(a aVar, byte[] bArr, int i, int i2) {
        if (aVar == null && bArr.length - i < 52) {
            return null;
        }
        bArr[i] = dji.midware.util.c.c(dji.midware.util.c.c(aVar.a) | (dji.midware.util.c.c(aVar.b) << 5));
        int i3 = i + 1;
        bArr[i3] = dji.midware.util.c.c(aVar.c);
        i3++;
        bArr[i3] = dji.midware.util.c.c(aVar.d);
        i3++;
        bArr[i3] = dji.midware.util.c.c(aVar.e);
        i3++;
        System.arraycopy(a(aVar.f), 0, bArr, i3, 4);
        i3 += 4;
        System.arraycopy(dji.midware.util.c.a(i2), 0, bArr, i3, 4);
        i3 += 4;
        System.arraycopy(dji.midware.util.c.a(aVar.h), 0, bArr, i3, 4);
        i3 += 4;
        System.arraycopy(dji.midware.util.c.a(aVar.i), 0, bArr, i3, 4);
        i3 += 4;
        System.arraycopy(aVar.j, 0, bArr, i3, 16);
        i3 += 16;
        System.arraycopy(aVar.k, 0, bArr, i3, 16);
        i3 += 16;
        return bArr;
    }

    private static byte[] a(long j) {
        return new byte[]{(byte) ((int) (255 & j)), (byte) ((int) ((65280 & j) >> 8)), (byte) ((int) ((16711680 & j) >> 16)), (byte) ((int) ((-16777216 & j) >> 24))};
    }
}
