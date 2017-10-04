package com.e;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.SystemClock;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Base64;
import com.amap.api.location.AMapLocation;
import com.autonavi.aps.amapapi.model.AmapLoc;
import com.tencent.android.tpush.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Random;
import org.json.JSONObject;

public class br {
    private static int a = 0;
    private static String[] b = null;
    private static Hashtable<String, Long> c = new Hashtable();
    private static DecimalFormat d = null;
    private static SimpleDateFormat e = null;

    public static float a(AmapLoc amapLoc, AmapLoc amapLoc2) {
        return a(new double[]{amapLoc.getLat(), amapLoc.getLon(), amapLoc2.getLat(), amapLoc2.getLon()});
    }

    public static float a(double[] dArr) {
        if (dArr.length != 4) {
            return 0.0f;
        }
        float[] fArr = new float[1];
        Location.distanceBetween(dArr[0], dArr[1], dArr[2], dArr[3], fArr);
        return fArr[0];
    }

    public static int a(int i) {
        return (i * 2) - 113;
    }

    public static int a(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    public static int a(NetworkInfo networkInfo) {
        return (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) ? networkInfo.getType() : -1;
    }

    public static int a(boolean z, CellLocation cellLocation, Context context) {
        if (z || cellLocation == null) {
            return 9;
        }
        if (cellLocation instanceof GsmCellLocation) {
            return 1;
        }
        try {
            Class.forName("android.telephony.cdma.CdmaCellLocation");
            return 2;
        } catch (Throwable th) {
            bc.a(th, "Utils", "getCellLocT");
            return 9;
        }
    }

    public static long a() {
        return System.currentTimeMillis();
    }

    public static Object a(Context context, String str) {
        Object obj = null;
        if (context != null) {
            try {
                obj = context.getApplicationContext().getSystemService(str);
            } catch (Throwable th) {
                bc.a(th, "Utils", "getServ");
            }
        }
        return obj;
    }

    public static synchronized String a(long j, String str) {
        String format;
        synchronized (br.class) {
            if (TextUtils.isEmpty(str)) {
                str = "yyyy-MM-dd HH:mm:ss";
            }
            if (e == null) {
                try {
                    e = new SimpleDateFormat(str, Locale.CHINA);
                } catch (Throwable th) {
                    bc.a(th, "Utils", "formatUTC");
                }
            } else {
                e.applyPattern(str);
            }
            if (j <= 0) {
                j = a();
            }
            format = e == null ? "NULL" : e.format(Long.valueOf(j));
        }
        return format;
    }

    public static String a(String str, int i) {
        byte[] bArr = null;
        try {
            bArr = str.getBytes("UTF-8");
        } catch (Throwable th) {
            bc.a(th, "Utils", "str2Base64");
        }
        return Base64.encodeToString(bArr, i);
    }

    public static boolean a(double d) {
        return d <= 180.0d && d >= -180.0d;
    }

    public static synchronized boolean a(long j, long j2) {
        boolean z;
        synchronized (br.class) {
            String str = "yyyyMMdd";
            z = false;
            if (e == null) {
                try {
                    e = new SimpleDateFormat(str, Locale.CHINA);
                } catch (Throwable th) {
                    bc.a(th, "Utils", "isSameDay part1");
                }
            } else {
                e.applyPattern(str);
            }
            try {
                if (e != null) {
                    z = e.format(Long.valueOf(j)).equals(e.format(Long.valueOf(j2)));
                }
            } catch (Throwable th2) {
                bc.a(th2, "Utils", "isSameDay");
            }
        }
        return z;
    }

    public static boolean a(Context context) {
        boolean z = false;
        if (context != null) {
            if (c() < 17) {
                try {
                    z = b(context, "android.provider.Settings$System");
                } catch (Throwable th) {
                }
            } else {
                try {
                    z = b(context, "android.provider.Settings$Global");
                } catch (Throwable th2) {
                }
            }
        }
        return z;
    }

    public static boolean a(ScanResult scanResult) {
        return (scanResult == null || TextUtils.isEmpty(scanResult.BSSID) || scanResult.BSSID.equals("00:00:00:00:00:00") || scanResult.BSSID.contains(" :")) ? false : true;
    }

    public static boolean a(AMapLocation aMapLocation) {
        if (aMapLocation == null || aMapLocation.getErrorCode() != 0) {
            return false;
        }
        double longitude = aMapLocation.getLongitude();
        double latitude = aMapLocation.getLatitude();
        return !(longitude == 0.0d && latitude == 0.0d && ((double) aMapLocation.getAccuracy()) == 0.0d) && longitude <= 180.0d && latitude <= 90.0d && longitude >= -180.0d && latitude >= -90.0d;
    }

    public static boolean a(AmapLoc amapLoc) {
        if (amapLoc == null || amapLoc.getRetype().equals("8") || amapLoc.getRetype().equals("5") || amapLoc.getRetype().equals("6")) {
            return false;
        }
        double lon = amapLoc.getLon();
        double lat = amapLoc.getLat();
        return !(lon == 0.0d && lat == 0.0d && ((double) amapLoc.getAccuracy()) == 0.0d) && lon <= 180.0d && lat <= 90.0d && lon >= -180.0d && lat >= -90.0d;
    }

    public static boolean a(String str) {
        return (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str)) ? ",111,123,134,199,202,204,206,208,212,213,214,216,218,219,220,222,225,226,228,230,231,232,234,235,238,240,242,244,246,247,248,250,255,257,259,260,262,266,268,270,272,274,276,278,280,282,283,284,286,288,289,290,292,293,294,295,297,302,308,310,311,312,313,314,315,316,310,330,332,334,338,340,342,344,346,348,350,352,354,356,358,360,362,363,364,365,366,368,370,372,374,376,400,401,402,404,405,406,410,412,413,414,415,416,417,418,419,420,421,422,424,425,426,427,428,429,430,431,432,434,436,437,438,440,441,450,452,454,455,456,457,466,467,470,472,502,505,510,514,515,520,525,528,530,534,535,536,537,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,555,560,598,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642,643,645,646,647,648,649,650,651,652,653,654,655,657,659,665,702,704,706,708,710,712,714,716,722,724,730,732,734,736,738,740,742,744,746,748,750,850,901,".contains("," + str + ",") : false;
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return dd.a(jSONObject, str);
    }

    public static byte[] a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) ((int) ((j >> (i * 8)) & 255));
        }
        return bArr;
    }

    public static final byte[] a(File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new IOException("can't operate on null");
        }
        byte[] bArr = new byte[2048];
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                fileInputStream.close();
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            bArr2 = dd.b(bArr);
        } catch (Throwable th) {
            bc.a(th, "Utils", "gz");
        }
        return bArr2;
    }

    public static String[] a(TelephonyManager telephonyManager) {
        int parseInt;
        Object obj = null;
        if (telephonyManager != null) {
            obj = telephonyManager.getNetworkOperator();
        }
        String[] strArr = new String[]{"0", "0"};
        int i = TextUtils.isEmpty(obj) ? 0 : !TextUtils.isDigitsOnly(obj) ? 0 : obj.length() <= 4 ? 0 : 1;
        if (i != 0) {
            strArr[0] = obj.substring(0, 3);
            char[] toCharArray = obj.substring(3).toCharArray();
            i = 0;
            while (i < toCharArray.length && Character.isDigit(toCharArray[i])) {
                i++;
            }
            strArr[1] = obj.substring(3, i + 3);
        }
        try {
            parseInt = Integer.parseInt(strArr[0]);
        } catch (Throwable th) {
            bc.a(th, "Utils", "getMccMnc");
            parseInt = 0;
        }
        if (parseInt == 0) {
            strArr[0] = "0";
        }
        if (strArr[0].equals("0") || strArr[1].equals("0")) {
            return (strArr[0].equals("0") && strArr[1].equals("0") && b != null) ? b : strArr;
        } else {
            b = strArr;
            return strArr;
        }
    }

    public static long b() {
        return SystemClock.elapsedRealtime();
    }

    public static final long b(byte[] bArr) {
        long j = 0;
        for (int i = 0; i < 8; i++) {
            j = (j << 8) | ((long) (bArr[i] & 255));
        }
        return j;
    }

    public static String b(Context context) {
        CharSequence charSequence = null;
        if (!TextUtils.isEmpty(bc.j)) {
            return bc.j;
        }
        if (context == null) {
            return null;
        }
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 64);
        } catch (Throwable th) {
            bc.a(th, "Utils", "getAppName part");
            packageInfo = null;
        }
        try {
            if (TextUtils.isEmpty(bc.k)) {
                bc.k = null;
            }
        } catch (Throwable th2) {
            bc.a(th2, "Utils", "getAppName");
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (packageInfo != null) {
            if (packageInfo.applicationInfo != null) {
                charSequence = packageInfo.applicationInfo.loadLabel(context.getPackageManager());
            }
            if (charSequence != null) {
                stringBuilder.append(charSequence.toString());
            }
            if (!TextUtils.isEmpty(packageInfo.versionName)) {
                stringBuilder.append(packageInfo.versionName);
            }
        }
        if (!TextUtils.isEmpty(bc.g)) {
            stringBuilder.append(",").append(bc.g);
        }
        if (!TextUtils.isEmpty(bc.k)) {
            stringBuilder.append(",").append(bc.k);
        }
        String stringBuilder2 = stringBuilder.toString();
        bc.j = stringBuilder2;
        return stringBuilder2;
    }

    public static String b(TelephonyManager telephonyManager) {
        int i = 0;
        if (telephonyManager != null) {
            i = telephonyManager.getNetworkType();
        }
        return (String) bc.q.get(i, "UNKWN");
    }

    public static boolean b(double d) {
        return d <= 90.0d && d >= -90.0d;
    }

    public static synchronized boolean b(long j, long j2) {
        boolean z = true;
        boolean z2 = false;
        synchronized (br.class) {
            if (a(j, j2)) {
                Calendar instance = Calendar.getInstance(Locale.CHINA);
                instance.setTimeInMillis(j);
                int i = instance.get(11);
                instance.setTimeInMillis(j2);
                int i2 = instance.get(11);
                if (i <= 12 ? i2 <= 12 : i2 > 12) {
                    z = false;
                }
                z2 = z;
            }
        }
        return z2;
    }

    private static boolean b(Context context, String str) throws Throwable {
        ContentResolver contentResolver = context.getContentResolver();
        String str2 = ((String) bp.a(str, "AIRPLANE_MODE_ON")).toString();
        return ((Integer) bp.a(str, "getInt", new Object[]{contentResolver, str2}, new Class[]{ContentResolver.class, String.class})).intValue() == 1;
    }

    public static byte[] b(int i) {
        byte[] bArr = new byte[2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & 255);
        }
        return bArr;
    }

    public static byte[] b(String str) {
        byte[] bArr = new byte[6];
        String[] split = str.split(":");
        for (int i = 0; i < split.length; i++) {
            bArr[i] = (byte) Integer.parseInt(split[i], 16);
        }
        return bArr;
    }

    public static double c(double d) {
        return ((double) ((long) (d * 1000000.0d))) / 1000000.0d;
    }

    public static int c() {
        if (a > 0) {
            return a;
        }
        int i = 0;
        String str = "android.os.Build$VERSION";
        try {
            return bp.b(str, "SDK_INT");
        } catch (Throwable th) {
            return i;
        }
    }

    public static final int c(byte[] bArr) {
        return ((bArr[0] & 255) << 8) | (bArr[1] & 255);
    }

    public static NetworkInfo c(Context context) {
        NetworkInfo networkInfo = null;
        try {
            networkInfo = cx.n(context);
        } catch (Throwable th) {
            bc.a(th, "Utils", "getNetWorkInfo");
        }
        return networkInfo;
    }

    public static String c(String str) {
        return a(str, 0);
    }

    public static byte[] c(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & 255);
        }
        return bArr;
    }

    public static final int d(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (i < 4) {
            i2 = (i2 << 8) | (bArr[i] & 255);
            i++;
        }
        return i2;
    }

    public static String d() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static String d(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        try {
            return new String(Base64.decode(str, 0), "UTF-8");
        } catch (Throwable th) {
            bc.a(th, "Utils", "base642Str");
            return null;
        }
    }

    public static boolean d(Context context) {
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                    return runningAppProcessInfo.importance != 100;
                }
            }
            return false;
        } catch (Throwable th) {
            bc.a(th, "Utils", "isApplicationBroughtToBackground");
            return true;
        }
    }

    public static String e() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(d()).append(File.separator);
        stringBuilder.append("amaplocationapi").append(File.separator);
        return stringBuilder.toString();
    }

    public static byte[] e(String str) {
        return b(Integer.parseInt(str));
    }

    public static String f() {
        return Build.MODEL;
    }

    public static byte[] f(String str) {
        return c(Integer.parseInt(str));
    }

    public static String g() {
        return VERSION.RELEASE;
    }

    public static boolean h() {
        return a(0, 1) == 1;
    }

    public static void i() {
        c.clear();
    }

    public static String j() {
        String str = "";
        try {
            return cy.a(bc.e.getBytes("UTF-8")).substring(20);
        } catch (Throwable th) {
            return "";
        }
    }

    public static boolean k() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}
