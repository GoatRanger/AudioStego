package dji.pilot.fpv.model;

import android.content.Context;
import android.util.Log;
import com.dji.frame.c.d;
import dji.log.DJILogHelper;
import dji.midware.natives.FREncrypt;
import dji.midware.util.c;
import dji.pilot.fpv.model.h.a;
import dji.pilot.usercenter.mode.m;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class i {
    public static String a = "DJIFlightRecord_";
    public static String b = ".txt";
    public static String c = "yyyy-MM-dd_[HH-mm-ss]";
    private static final int d = 10485760;
    private static final int e = 128;
    private static final String f = "FlightRecord/";
    private static long k = 0;
    private String g;
    private String h;
    private File i;
    private FileOutputStream j;

    public i(Context context, f fVar) {
        this.g = d.a(context, "FlightRecord/");
        File file = new File(this.g);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.h = a(fVar.C);
        fVar.r = this.h;
        this.i = new File(this.g + this.h);
        if (this.i.exists()) {
            this.i.delete();
            try {
                this.i.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.j = new FileOutputStream(this.i);
            this.j.write(new byte[100]);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public i(Context context, f fVar, boolean z) {
        this.g = d.a(context, "FlightRecord/");
        File file = new File(this.g);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.h = a(fVar.C);
        this.i = new File(this.g + this.h);
        if (this.i.exists()) {
            try {
                this.j = new FileOutputStream(this.i, true);
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        try {
            this.i.createNewFile();
            fVar.r = this.h;
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            this.j = new FileOutputStream(this.i);
            this.j.write(new byte[100]);
        } catch (IOException e22) {
            e22.printStackTrace();
        }
    }

    private String a(long j) {
        return a + new SimpleDateFormat(c, Locale.US).format(new Date(j)) + b;
    }

    public synchronized void a(f fVar) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.i, "rw");
            randomAccessFile.setLength((fVar.k - ((long) fVar.d())) - ((long) fVar.c()));
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] a(byte[] bArr, int i, f fVar) {
        byte[] bArr2 = new byte[(bArr.length + 2)];
        return FREncrypt.encryptFRData(bArr2, bArr, bArr.length, i, fVar.o) == ((long) (bArr.length + 2)) ? bArr2 : bArr2;
    }

    public static byte[] b(byte[] bArr, int i, f fVar) {
        if (bArr.length <= 2) {
            Log.d("Flightreocrd", "FR decrypt fail, size <= 2");
            return bArr;
        }
        byte[] bArr2 = new byte[(bArr.length - 2)];
        if (FREncrypt.decryptFRData(bArr2, bArr, bArr.length, i, fVar.o) == ((long) (bArr.length - 2))) {
        }
        return bArr2;
    }

    public synchronized void a(h hVar, f fVar) {
        byte[] bArr;
        byte[] recData = hVar.a == null ? null : hVar.a.getRecData();
        byte[] recData2 = hVar.b == null ? null : hVar.b.getRecData();
        byte[] recData3 = hVar.c == null ? null : hVar.c.getRecData();
        byte[] recData4 = hVar.d == null ? null : hVar.d.getRecData();
        byte[] recData5 = hVar.e == null ? null : hVar.e.getRecData();
        byte[] recData6 = hVar.f == null ? null : hVar.f.getRecData();
        byte[] recData7 = hVar.g == null ? null : hVar.g.getRecData();
        byte[] recData8 = hVar.j == null ? null : hVar.j.getRecData();
        byte[] recData9 = hVar.k == null ? null : hVar.k.getRecData();
        byte[] recData10 = hVar.l == null ? null : hVar.l.getRecData();
        byte[] recData11 = hVar.m == null ? null : hVar.m.getRecData();
        byte[] recData12 = hVar.n == null ? null : hVar.n.getRecData();
        byte[] recData13 = hVar.o == null ? null : hVar.o.getRecData();
        if (hVar.p == null) {
            bArr = null;
        } else {
            bArr = hVar.p.getRecData();
        }
        if (recData != null) {
            try {
                recData = a(recData, a.OSD.a(), fVar);
                this.j.write(a.OSD.a());
                this.j.write(recData.length);
                this.j.write(recData);
                this.j.write(a.END.a());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (recData2 != null) {
            recData2 = a(recData2, a.HOME.a(), fVar);
            this.j.write(a.HOME.a());
            this.j.write(recData2.length);
            this.j.write(recData2);
            this.j.write(a.END.a());
        }
        if (recData3 != null) {
            recData3 = a(recData3, a.GIMBAL.a(), fVar);
            this.j.write(a.GIMBAL.a());
            this.j.write(recData3.length);
            this.j.write(recData3);
            this.j.write(a.END.a());
        }
        if (recData4 != null) {
            recData4 = a(recData4, a.RC.a(), fVar);
            this.j.write(a.RC.a());
            this.j.write(recData4.length);
            this.j.write(recData4);
            this.j.write(a.END.a());
        }
        if (recData5 != null) {
            recData5 = a(recData5, a.DEFORM.a(), fVar);
            this.j.write(a.DEFORM.a());
            this.j.write(recData5.length);
            this.j.write(recData5);
            this.j.write(a.END.a());
        }
        if (recData6 != null) {
            recData6 = a(recData6, a.CENTER_BATTERY.a(), fVar);
            this.j.write(a.CENTER_BATTERY.a());
            this.j.write(recData6.length);
            this.j.write(recData6);
            this.j.write(a.END.a());
        }
        if (recData7 != null) {
            recData7 = a(recData7, a.SMART_BATTERY.a(), fVar);
            this.j.write(a.SMART_BATTERY.a());
            this.j.write(recData7.length);
            this.j.write(recData7);
            this.j.write(a.END.a());
        }
        if (recData8 != null) {
            recData8 = a(recData8, a.RC_GPS.a(), fVar);
            this.j.write(a.RC_GPS.a());
            this.j.write(recData8.length);
            this.j.write(recData8);
            this.j.write(a.END.a());
        }
        if (!hVar.h.equals("")) {
            recData8 = a(c.b(hVar.h), a.APP_TIP.a(), fVar);
            this.j.write(a.APP_TIP.a());
            this.j.write(recData8.length);
            this.j.write(recData8);
            this.j.write(a.END.a());
        }
        if (!hVar.i.equals("")) {
            recData8 = a(c.b(hVar.i), a.APP_WARN.a(), fVar);
            this.j.write(a.APP_WARN.a());
            this.j.write(recData8.length);
            this.j.write(recData8);
            this.j.write(a.END.a());
        }
        if (recData9 != null) {
            recData9 = a(recData9, a.CUSTOM.a(), fVar);
            this.j.write(a.CUSTOM.a());
            this.j.write(recData9.length);
            this.j.write(recData9);
            this.j.write(a.END.a());
        }
        if (recData10 != null) {
            recData10 = a(recData10, a.RECOVER.a(), fVar);
            this.j.write(a.RECOVER.a());
            this.j.write(recData10.length);
            this.j.write(recData10);
            this.j.write(a.END.a());
        }
        if (recData11 != null) {
            recData11 = a(recData11, a.APP_GPS.a(), fVar);
            this.j.write(a.APP_GPS.a());
            this.j.write(recData11.length);
            this.j.write(recData11);
            this.j.write(a.END.a());
        }
        if (recData12 != null) {
            recData12 = a(recData12, a.FIRMWARE.a(), fVar);
            this.j.write(a.FIRMWARE.a());
            this.j.write(recData12.length);
            this.j.write(recData12);
            this.j.write(a.END.a());
        }
        if (recData13 != null) {
            recData13 = a(recData13, a.VISION_GROUP.a(), fVar);
            this.j.write(a.VISION_GROUP.a());
            this.j.write(recData13.length);
            this.j.write(recData13);
            this.j.write(a.END.a());
        }
        if (bArr != null) {
            bArr = a(bArr, a.MC_PARAM.a(), fVar);
            this.j.write(a.MC_PARAM.a());
            this.j.write(bArr.length);
            this.j.write(bArr);
            this.j.write(a.END.a());
        }
        this.j.flush();
    }

    public synchronized void a(a aVar, byte[] bArr, f fVar) {
        try {
            byte[] a = a(bArr, aVar.a(), fVar);
            this.j.write(aVar.a());
            this.j.write(a.length);
            this.j.write(a);
            this.j.write(a.END.a());
            this.j.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a() {
        try {
            this.j.flush();
            this.j.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context, f fVar) {
        File file = new File(d.a(context, "FlightRecord/") + fVar.r);
        if (file.exists()) {
            file.delete();
            System.gc();
        }
    }

    public static void a(Context context, f fVar, String[] strArr) {
        fVar.w = strArr[0];
        fVar.v = strArr[1];
        fVar.u = strArr[2];
        fVar.t = strArr[3];
        b(context, fVar);
    }

    public static void b(Context context, f fVar) {
        a(context, fVar, null);
    }

    public static void a(Context context, f fVar, k kVar) {
        File file = new File(d.a(context, "FlightRecord/") + fVar.r);
        if (file.exists()) {
            a(file, fVar);
        }
    }

    private static void a(File file, f fVar) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            long length = randomAccessFile.length();
            if (fVar.m > (short) 8) {
                length -= 128;
                randomAccessFile.seek(length - ((long) fVar.l));
                randomAccessFile.write(fVar.i());
                randomAccessFile.seek(length + 64);
                randomAccessFile.write(a(fVar.i()));
            } else {
                randomAccessFile.seek(length - ((long) fVar.l));
                randomAccessFile.write(fVar.i());
            }
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void c(Context context, f fVar) {
        File file = new File(d.a(context, "FlightRecord/") + fVar.r);
        if (file.exists()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(randomAccessFile.length());
                randomAccessFile.write(fVar.h());
                randomAccessFile.write(fVar.g());
                fVar.k = randomAccessFile.length();
                randomAccessFile.seek(100);
                byte[] bArr = new byte[(((int) fVar.k) - 100)];
                randomAccessFile.read(bArr);
                randomAccessFile.seek(randomAccessFile.length());
                randomAccessFile.write(fVar.i());
                randomAccessFile.write(a(bArr));
                randomAccessFile.write(a(fVar.i()));
                randomAccessFile.seek(0);
                randomAccessFile.write(fVar.j());
                randomAccessFile.getFD().sync();
                randomAccessFile.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    private static byte[] a(byte[] bArr) {
        Object obj = new byte[64];
        Object b = c.b(FREncrypt.getVerifyStr(bArr));
        System.arraycopy(b, 0, obj, 0, b.length);
        return obj;
    }

    private static int b(float f, float f2) {
        if (f > f2) {
            return 1;
        }
        if (f < f2) {
            return -1;
        }
        return 0;
    }

    private static int b(long j, long j2) {
        if (j > j2) {
            return 1;
        }
        if (j < j2) {
            return -1;
        }
        return 0;
    }

    public static void a(List<f> list, f.a aVar) {
        a(list, aVar, false, false);
    }

    public static void a(List<f> list, f.a aVar, boolean z, boolean z2) {
        Collections.sort(list, new 1(aVar, z, z2));
    }

    public static void a(List<f> list) {
        Collections.reverse(list);
    }

    public static boolean a(Context context) {
        File file = new File(d.a(context, "FlightRecord/"));
        if (!file.exists()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        for (File file2 : listFiles) {
            if (file2.isFile() && !file2.getName().startsWith(".")) {
                return true;
            }
        }
        return false;
    }

    public static List<f> a(Context context, ArrayList<m> arrayList) {
        k = 0;
        List<f> arrayList2 = new ArrayList(1000);
        String a = d.a(context, "FlightRecord/");
        if (!new File(a).exists() || arrayList.size() == 0) {
            return arrayList2;
        }
        File[] fileArr = new File[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            fileArr[i] = new File(a + ((m) arrayList.get(i)).a);
        }
        DJILogHelper.getInstance().LOGD("SYC", "files size:" + fileArr.length, false, true);
        byte[] bArr = new byte[500];
        byte[] bArr2 = new byte[524288];
        for (File file : fileArr) {
            if (file.isFile() && !file.getName().startsWith(".")) {
                f a2 = a(context, file, bArr, bArr2, true);
                if (a2 != null) {
                    arrayList2.add(a2);
                } else {
                    DJILogHelper.getInstance().LOGD("SYC", "infoModel error", false, true);
                }
            }
        }
        return arrayList2;
    }

    public static List<f> b(Context context) {
        k = 0;
        List<f> arrayList = new ArrayList(1000);
        File file = new File(d.a(context, "FlightRecord/"));
        if (!file.exists()) {
            return arrayList;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            byte[] bArr = new byte[500];
            byte[] bArr2 = new byte[524288];
            for (File file2 : listFiles) {
                if (!(file2 == null || !file2.isFile() || file2.getName().startsWith("."))) {
                    f a = a(context, file2, bArr, bArr2, false);
                    if (a != null) {
                        arrayList.add(a);
                    }
                }
            }
        }
        return arrayList;
    }

    private static f a(Context context, File file, byte[] bArr, byte[] bArr2, boolean z) {
        f fVar = new f();
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            fVar.q = file.getAbsolutePath();
            fVar.r = file.getName();
            byte[] bArr3 = new byte[100];
            randomAccessFile.read(bArr3, 0, 100);
            fVar.c(bArr3);
            if (fVar.l <= (short) 0) {
                new dji.pilot.flightrecord.a(context).a(file);
                randomAccessFile.close();
                return null;
            } else if (fVar.m > (short) 9) {
                randomAccessFile.close();
                return null;
            } else {
                long length = randomAccessFile.length() - ((long) fVar.l);
                if (fVar.m > (short) 8) {
                    length -= 128;
                }
                if (length != fVar.k) {
                    if (randomAccessFile.length() - fVar.k == 400) {
                        randomAccessFile.seek(0);
                        length = fVar.k;
                        fVar.l = (short) 400;
                        randomAccessFile.write(fVar.j());
                    } else {
                        randomAccessFile.seek(0);
                        fVar.k = length;
                        randomAccessFile.write(fVar.j());
                    }
                    fVar.af = true;
                }
                randomAccessFile.seek(length);
                randomAccessFile.read(bArr, 0, fVar.l);
                if (fVar.a(bArr, z)) {
                    int length2;
                    if (fVar.m <= (short) 2 && fVar.ac == 0 && fVar.O == 0) {
                        fVar.af = true;
                        if (fVar.ad != 0) {
                            Object l = fVar.l();
                            System.arraycopy(l, 0, bArr, fVar.ad, l.length);
                        }
                        int i = fVar.ab + 4;
                        for (length2 = bArr.length - 1; length2 >= i; length2--) {
                            if (length2 < i + 4) {
                                bArr[length2] = (byte) 0;
                            } else {
                                bArr[length2] = bArr[length2 - 4];
                            }
                        }
                        bArr[bArr.length - 1] = (byte) 1;
                        randomAccessFile.seek(length);
                        randomAccessFile.write(bArr);
                        fVar.a(bArr, z);
                        randomAccessFile.getFD().sync();
                    }
                    try {
                        length2 = fVar.d();
                        if (length2 > 0) {
                            k += (long) length2;
                            if (k < 10485760) {
                                if (fVar.m > (short) 8) {
                                    randomAccessFile.seek(((randomAccessFile.length() - ((long) fVar.l)) - 128) - ((long) length2));
                                } else {
                                    randomAccessFile.seek((randomAccessFile.length() - ((long) fVar.l)) - ((long) length2));
                                }
                                randomAccessFile.read(bArr2, 0, length2);
                                fVar.a(bArr2);
                            }
                        }
                        if (randomAccessFile == null) {
                            return fVar;
                        }
                        try {
                            randomAccessFile.close();
                            return fVar;
                        } catch (Exception e) {
                            return fVar;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        if (randomAccessFile == null) {
                            return fVar;
                        }
                        try {
                            randomAccessFile.close();
                            return fVar;
                        } catch (Exception e3) {
                            return fVar;
                        }
                    } catch (OutOfMemoryError e4) {
                        e4.printStackTrace();
                        if (randomAccessFile == null) {
                            return fVar;
                        }
                        try {
                            randomAccessFile.close();
                            return fVar;
                        } catch (Exception e5) {
                            return fVar;
                        }
                    } catch (Throwable th) {
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (Exception e6) {
                            }
                        }
                    }
                } else {
                    randomAccessFile.close();
                    return null;
                }
            }
        } catch (FileNotFoundException e7) {
            e7.printStackTrace();
            return null;
        } catch (IOException e8) {
            e8.printStackTrace();
            return null;
        } catch (Exception e9) {
            e9.printStackTrace();
            return null;
        }
    }

    public static f d(Context context, f fVar) {
        File file = new File(d.a(context, "FlightRecord/") + fVar.r);
        if (file.exists()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                int d = fVar.d();
                int c = fVar.c();
                if (fVar.m > (short) 8) {
                    randomAccessFile.seek((((randomAccessFile.length() - ((long) fVar.l)) - ((long) d)) - ((long) c)) - 128);
                } else {
                    randomAccessFile.seek(((randomAccessFile.length() - ((long) fVar.l)) - ((long) d)) - ((long) c));
                }
                byte[] bArr = new byte[c];
                randomAccessFile.read(bArr, 0, c);
                fVar.b(bArr);
                randomAccessFile.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (OutOfMemoryError e3) {
                e3.printStackTrace();
            }
        }
        return fVar;
    }

    @Deprecated
    public static List<h> e(Context context, f fVar) {
        Object obj = 1;
        List<h> arrayList = new ArrayList(1000);
        String a = d.a(context, "FlightRecord/");
        if (!new File(a).exists()) {
            return arrayList;
        }
        File file = new File(a + fVar.r);
        if (!file.exists()) {
            return arrayList;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            h hVar = new h();
            randomAccessFile.seek(100);
            Object obj2 = 1;
            while (obj2 != null && !Thread.interrupted()) {
                a find = a.find(randomAccessFile.read());
                int read = randomAccessFile.read();
                if (read >= 0) {
                    byte[] bArr = new byte[read];
                    randomAccessFile.read(bArr);
                    switch (2.b[find.ordinal()]) {
                        case 1:
                            h hVar2;
                            if (obj == null) {
                                arrayList.add(hVar);
                                hVar2 = new h();
                            } else {
                                hVar2 = hVar;
                            }
                            if (a.find(randomAccessFile.read()) != a.END) {
                                obj2 = null;
                                hVar = hVar2;
                                obj = null;
                                break;
                            }
                            hVar2.a.setRecData(bArr);
                            obj2 = null;
                            hVar = hVar2;
                            obj = null;
                            break;
                        case 2:
                            if (a.find(randomAccessFile.read()) != a.END) {
                                obj2 = null;
                                break;
                            }
                            hVar.b.setRecData(bArr);
                            break;
                        case 3:
                            if (a.find(randomAccessFile.read()) != a.END) {
                                obj2 = null;
                                break;
                            }
                            hVar.c.setRecData(bArr);
                            break;
                        case 4:
                            if (a.find(randomAccessFile.read()) != a.END) {
                                obj2 = null;
                                break;
                            }
                            hVar.d.setRecData(bArr);
                            break;
                        case 5:
                            if (a.find(randomAccessFile.read()) != a.END) {
                                obj2 = null;
                                break;
                            }
                            hVar.e.setRecData(bArr);
                            break;
                        case 6:
                            if (a.find(randomAccessFile.read()) != a.END) {
                                obj2 = null;
                                break;
                            }
                            hVar.f.setRecData(bArr);
                            break;
                        case 7:
                            if (a.find(randomAccessFile.read()) != a.END) {
                                obj2 = null;
                                break;
                            }
                            hVar.g.setRecData(bArr);
                            break;
                        case 8:
                            if (a.find(randomAccessFile.read()) != a.END) {
                                obj2 = null;
                                break;
                            }
                            hVar.h = c.g(bArr);
                            break;
                        case 9:
                            if (a.find(randomAccessFile.read()) != a.END) {
                                obj2 = null;
                                break;
                            }
                            hVar.i = c.g(bArr);
                            break;
                        case 10:
                            if (a.find(randomAccessFile.read()) != a.END) {
                                obj2 = null;
                                break;
                            }
                            hVar.j.setRecData(bArr);
                            break;
                        case 11:
                            if (a.find(randomAccessFile.read()) != a.END) {
                                obj2 = null;
                                break;
                            }
                            hVar.k.a(bArr, fVar.ae);
                            break;
                        case 12:
                            if (a.find(randomAccessFile.read()) != a.END) {
                                obj2 = null;
                                break;
                            }
                            hVar.m.setRecData(bArr);
                            break;
                        case 13:
                            if (a.find(randomAccessFile.read()) == a.END) {
                                if (hVar.n == null) {
                                    break;
                                }
                                hVar.n.setRecData(bArr);
                                break;
                            }
                            obj2 = null;
                            break;
                        default:
                            if (a.find(randomAccessFile.read()) == a.END) {
                                break;
                            }
                            obj2 = null;
                            break;
                    }
                }
            }
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    private static int a(byte[] bArr, int i, long j) {
        int i2 = 0;
        while (((long) (i2 + i)) < j && a.find(c.a(bArr[i2 + i])) != a.END) {
            i2++;
        }
        return i2;
    }

    public static List<h> f(Context context, f fVar) {
        List<h> arrayList = new ArrayList();
        String a = d.a(context, "FlightRecord/");
        if (!new File(a).exists()) {
            return arrayList;
        }
        File file = new File(a + fVar.r);
        if (!file.exists()) {
            return arrayList;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            FileChannel channel = randomAccessFile.getChannel();
            long size = channel.size();
            byte[] bArr = new byte[((int) size)];
            channel.map(MapMode.READ_ONLY, 0, size).get(bArr, 0, (int) size);
            int i = 100;
            if (fVar.m < (short) 7) {
                i = 12;
            }
            Log.d("Flightrecord", "header长度：" + i);
            h hVar = new h();
            Object obj = 1;
            Object obj2 = 1;
            while (obj != null && !Thread.interrupted() && ((long) i) < size) {
                a find = a.find(c.a(bArr[i]));
                i++;
                short a2 = c.a(bArr[i]);
                int i2 = i + 1;
                if (((long) (a2 + i2)) < size) {
                    byte[] b;
                    int i3;
                    h hVar2;
                    Object obj3;
                    Object obj4;
                    Object obj5 = new byte[a2];
                    System.arraycopy(bArr, i2, obj5, 0, a2);
                    if (fVar.m > (short) 6) {
                        b = b(obj5, find.a(), fVar);
                    } else {
                        Object obj6 = obj5;
                    }
                    i = i2 + a2;
                    a find2;
                    switch (2.b[find.ordinal()]) {
                        case 1:
                            if (obj2 == null) {
                                hVar = new h();
                            }
                            arrayList.add(hVar);
                            find = a.find(c.a(bArr[i]));
                            i++;
                            if (find != a.END) {
                                obj2 = null;
                                i3 = i;
                                hVar2 = hVar;
                                obj3 = null;
                                break;
                            }
                            hVar.a.setRecData(b);
                            obj2 = obj;
                            i3 = i;
                            hVar2 = hVar;
                            obj3 = null;
                            break;
                        case 2:
                            find = a.find(c.a(bArr[i]));
                            i++;
                            if (find != a.END) {
                                obj4 = obj2;
                                obj2 = null;
                                i3 = i;
                                hVar2 = hVar;
                                obj3 = obj4;
                                break;
                            }
                            hVar.b.setRecData(b);
                            obj4 = obj2;
                            obj2 = obj;
                            i3 = i;
                            hVar2 = hVar;
                            obj3 = obj4;
                            break;
                        case 3:
                            find = a.find(c.a(bArr[i]));
                            i++;
                            if (find != a.END) {
                                obj4 = obj2;
                                obj2 = null;
                                i3 = i;
                                hVar2 = hVar;
                                obj3 = obj4;
                                break;
                            }
                            hVar.c.setRecData(b);
                            obj4 = obj2;
                            obj2 = obj;
                            i3 = i;
                            hVar2 = hVar;
                            obj3 = obj4;
                            break;
                        case 4:
                            find = a.find(c.a(bArr[i]));
                            i++;
                            if (find != a.END) {
                                obj4 = obj2;
                                obj2 = null;
                                i3 = i;
                                hVar2 = hVar;
                                obj3 = obj4;
                                break;
                            }
                            hVar.d.setRecData(b);
                            obj4 = obj2;
                            obj2 = obj;
                            i3 = i;
                            hVar2 = hVar;
                            obj3 = obj4;
                            break;
                        case 5:
                            find = a.find(c.a(bArr[i]));
                            i++;
                            if (find != a.END) {
                                obj4 = obj2;
                                obj2 = null;
                                i3 = i;
                                hVar2 = hVar;
                                obj3 = obj4;
                                break;
                            }
                            hVar.e.setRecData(b);
                            obj4 = obj2;
                            obj2 = obj;
                            i3 = i;
                            hVar2 = hVar;
                            obj3 = obj4;
                            break;
                        case 6:
                            find = a.find(c.a(bArr[i]));
                            i++;
                            if (find != a.END) {
                                obj4 = obj2;
                                obj2 = null;
                                i3 = i;
                                hVar2 = hVar;
                                obj3 = obj4;
                                break;
                            }
                            hVar.f.setRecData(b);
                            obj4 = obj2;
                            obj2 = obj;
                            i3 = i;
                            hVar2 = hVar;
                            obj3 = obj4;
                            break;
                        case 7:
                            find = a.find(c.a(bArr[i]));
                            i++;
                            if (find != a.END) {
                                obj4 = obj2;
                                obj2 = null;
                                i3 = i;
                                hVar2 = hVar;
                                obj3 = obj4;
                                break;
                            }
                            hVar.g.setRecData(b);
                            obj4 = obj2;
                            obj2 = obj;
                            i3 = i;
                            hVar2 = hVar;
                            obj3 = obj4;
                            break;
                        case 8:
                            find = a.find(c.a(bArr[i]));
                            i++;
                            if (find != a.END) {
                                obj4 = obj2;
                                obj2 = null;
                                i3 = i;
                                hVar2 = hVar;
                                obj3 = obj4;
                                break;
                            }
                            hVar.h = c.g(b);
                            obj4 = obj2;
                            obj2 = obj;
                            i3 = i;
                            hVar2 = hVar;
                            obj3 = obj4;
                            break;
                        case 9:
                            find = a.find(c.a(bArr[i]));
                            i++;
                            if (find != a.END) {
                                obj4 = obj2;
                                obj2 = null;
                                i3 = i;
                                hVar2 = hVar;
                                obj3 = obj4;
                                break;
                            }
                            hVar.i = c.g(b);
                            obj4 = obj2;
                            obj2 = obj;
                            i3 = i;
                            hVar2 = hVar;
                            obj3 = obj4;
                            break;
                        case 10:
                            find = a.find(c.a(bArr[i]));
                            i++;
                            if (find != a.END) {
                                obj4 = obj2;
                                obj2 = null;
                                i3 = i;
                                hVar2 = hVar;
                                obj3 = obj4;
                                break;
                            }
                            hVar.j.setRecData(b);
                            obj4 = obj2;
                            obj2 = obj;
                            i3 = i;
                            hVar2 = hVar;
                            obj3 = obj4;
                            break;
                        case 11:
                            find = a.find(c.a(bArr[i]));
                            i++;
                            if (find != a.END) {
                                obj4 = obj2;
                                obj2 = null;
                                i3 = i;
                                hVar2 = hVar;
                                obj3 = obj4;
                                break;
                            }
                            hVar.k.a(b, fVar.ae);
                            obj4 = obj2;
                            obj2 = obj;
                            i3 = i;
                            hVar2 = hVar;
                            obj3 = obj4;
                            break;
                        case 12:
                            find = a.find(c.a(bArr[i]));
                            i++;
                            if (find != a.END) {
                                obj4 = obj2;
                                obj2 = null;
                                i3 = i;
                                hVar2 = hVar;
                                obj3 = obj4;
                                break;
                            }
                            hVar.m.setRecData(b);
                            obj4 = obj2;
                            obj2 = obj;
                            i3 = i;
                            hVar2 = hVar;
                            obj3 = obj4;
                            break;
                        case 13:
                            find = a.find(c.a(bArr[i]));
                            i++;
                            if (find == a.END && hVar != null) {
                                if (hVar.n == null) {
                                    obj4 = obj2;
                                    obj2 = obj;
                                    i3 = i;
                                    hVar2 = hVar;
                                    obj3 = obj4;
                                    break;
                                }
                                hVar.n.setRecData(b);
                                obj4 = obj2;
                                obj2 = obj;
                                i3 = i;
                                hVar2 = hVar;
                                obj3 = obj4;
                                break;
                            }
                            obj4 = obj2;
                            obj2 = null;
                            i3 = i;
                            hVar2 = hVar;
                            obj3 = obj4;
                            break;
                            break;
                        case 14:
                            find2 = a.find(c.a(bArr[i]));
                            i++;
                            if (find2 != a.END) {
                                obj4 = obj2;
                                obj2 = null;
                                i3 = i;
                                hVar2 = hVar;
                                obj3 = obj4;
                                break;
                            }
                            obj4 = obj2;
                            obj2 = obj;
                            i3 = i;
                            hVar2 = hVar;
                            obj3 = obj4;
                            break;
                        default:
                            find2 = a.find(c.a(bArr[i]));
                            i++;
                            if (find2 != a.END) {
                                obj4 = obj2;
                                obj2 = null;
                                i3 = i;
                                hVar2 = hVar;
                                obj3 = obj4;
                                break;
                            }
                            obj4 = obj2;
                            obj2 = obj;
                            i3 = i;
                            hVar2 = hVar;
                            obj3 = obj4;
                            break;
                    }
                    if (obj2 == null) {
                        obj2 = 1;
                        i3 += a(bArr, i3, size) + 1;
                    }
                    obj4 = obj3;
                    hVar = hVar2;
                    i = i3;
                    obj = obj2;
                    obj2 = obj4;
                }
            }
            channel.close();
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }
}
