package dji.pilot.publics.control.rc;

import android.util.Log;
import dji.log.DJILogHelper;
import dji.midware.natives.GroudStation;
import dji.pilot.usercenter.protocol.d;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class a {
    public static final String a = "/sdcard/DJI/WM610_FW_V01.02.00.16.bin";
    public static final int b = 64;
    public static final int c = 52;
    public static final int d = 272;
    public static final int e = 16;
    private static final String f = a.class.getSimpleName();

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
        public byte[] j = new byte[16];
        public byte[] k = new byte[16];
    }

    public static final class b {
        public int a = 0;
        public int b = 0;
        public int c = 0;
        public int d = 0;
        public String e = null;
        public String f = null;
        public int g = 0;
        public int[] h = new int[18];
    }

    public static final class c {
        public boolean a = false;
        public b b = null;
        public final ArrayList<a> c = new ArrayList();
    }

    public static void a() {
        c a = a(a, "WM610");
        if (a != null) {
            Log.d(f, "result[" + a.a + d.H);
        }
    }

    public static c a(String str, String str2) {
        Throwable th;
        c cVar = null;
        if (!(str == null || dji.pilot.publics.e.d.a(str2))) {
            File file = new File(str);
            if (file.exists() && file.isFile() && file.length() >= 272) {
                RandomAccessFile randomAccessFile;
                try {
                    randomAccessFile = new RandomAccessFile(file, "r");
                    try {
                        byte[] bArr = new byte[64];
                        if (randomAccessFile.read(bArr, 0, 64) == 64) {
                            c cVar2 = new c();
                            try {
                                cVar2.b = a(bArr);
                                if (str2.equalsIgnoreCase(cVar2.b.f)) {
                                    randomAccessFile.seek(0);
                                    byte[] bArr2 = new byte[(cVar2.b.c - 2)];
                                    if (randomAccessFile.read(bArr2, 0, cVar2.b.c - 2) == cVar2.b.c - 2) {
                                        short native_calcCrc16 = GroudStation.native_calcCrc16(bArr2);
                                        bArr = new byte[2];
                                        if (randomAccessFile.read(bArr, 0, 2) == 2 && dji.midware.util.c.a(bArr, 0) == native_calcCrc16 && ((cVar2.b.g * 52) + 64) + 2 == cVar2.b.c) {
                                            cVar2.a = true;
                                            randomAccessFile.seek(64);
                                            bArr = new byte[52];
                                            int i = 0;
                                            while (true) {
                                                int i2 = i + 1;
                                                if (i >= cVar2.b.g || randomAccessFile.read(bArr, 0, 52) != 52) {
                                                    break;
                                                }
                                                cVar2.c.add(b(bArr));
                                                i = i2;
                                            }
                                        }
                                    }
                                }
                                cVar = cVar2;
                            } catch (Exception e) {
                                cVar = cVar2;
                                try {
                                    DJILogHelper.getInstance().LOGD(f, "read package exception", false, true);
                                    if (randomAccessFile != null) {
                                        try {
                                            randomAccessFile.close();
                                        } catch (Exception e2) {
                                        }
                                    }
                                    return cVar;
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (randomAccessFile != null) {
                                        try {
                                            randomAccessFile.close();
                                        } catch (Exception e3) {
                                        }
                                    }
                                    throw th;
                                }
                            }
                        }
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (Exception e4) {
                            }
                        }
                    } catch (Exception e5) {
                        DJILogHelper.getInstance().LOGD(f, "read package exception", false, true);
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        return cVar;
                    }
                } catch (Exception e6) {
                    randomAccessFile = null;
                    DJILogHelper.getInstance().LOGD(f, "read package exception", false, true);
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    return cVar;
                } catch (Throwable th3) {
                    th = th3;
                    randomAccessFile = null;
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw th;
                }
            }
        }
        return cVar;
    }

    private static String a(byte[] bArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            if (bArr[i3] == (byte) 0) {
                i2 = i3 - i;
                break;
            }
        }
        return new String(bArr, i, i2, Charset.forName("UTF-8"));
    }

    private static b a(byte[] bArr) {
        b bVar = new b();
        bVar.a = dji.midware.util.c.b(bArr, 0);
        bVar.b = dji.midware.util.c.a(bArr, 4);
        bVar.c = dji.midware.util.c.a(bArr, 6);
        bVar.d = dji.midware.util.c.b(bArr, 8);
        bVar.e = a(bArr, 12, 16);
        bVar.f = a(bArr, 28, 16);
        bVar.g = dji.midware.util.c.a(bArr, 44);
        return bVar;
    }

    private static a b(byte[] bArr) {
        a aVar = new a();
        aVar.a = bArr[0] & 31;
        aVar.b = (bArr[0] & dji.thirdparty.g.b.a.a.fw_) >>> 5;
        aVar.c = bArr[1] & 255;
        aVar.d = bArr[2] & 255;
        aVar.e = bArr[3] & 255;
        aVar.f = dji.pilot.publics.e.d.a(bArr, 4, 4);
        aVar.g = dji.midware.util.c.b(bArr, 8);
        aVar.h = dji.midware.util.c.b(bArr, 12);
        aVar.i = dji.midware.util.c.b(bArr, 16);
        System.arraycopy(bArr, 20, aVar.j, 0, 16);
        System.arraycopy(bArr, 36, aVar.k, 0, 16);
        return aVar;
    }
}
