package com.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alipay.sdk.j.i;
import java.io.File;
import java.util.List;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class cx {
    private static String a = "";
    private static boolean b = false;
    private static String c = "";
    private static String d = "";
    private static String e = "";
    private static String f = "";

    static class a extends DefaultHandler {
        a() {
        }

        public void characters(char[] cArr, int i, int i2) throws SAXException {
            if (cx.b) {
                cx.a = new String(cArr, i, i2);
            }
        }

        public void endElement(String str, String str2, String str3) throws SAXException {
            cx.b = false;
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (str2.equals("string") && "UTDID".equals(attributes.getValue("name"))) {
                cx.b = true;
            }
        }
    }

    static String a(Context context) {
        try {
            return u(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static List<ScanResult> a(List<ScanResult> list) {
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            for (int i2 = 1; i2 < size - i; i2++) {
                if (((ScanResult) list.get(i2 - 1)).level > ((ScanResult) list.get(i2)).level) {
                    ScanResult scanResult = (ScanResult) list.get(i2 - 1);
                    list.set(i2 - 1, list.get(i2));
                    list.set(i2, scanResult);
                }
            }
        }
        return list;
    }

    public static void a() {
        try {
            if (VERSION.SDK_INT > 14) {
                TrafficStats.class.getDeclaredMethod("setThreadStatsTag", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(40964)});
            }
        } catch (Throwable th) {
            dg.a(th, "DeviceInfo", "setTraficTag");
        }
    }

    private static boolean a(Context context, String str) {
        return context != null && context.checkCallingOrSelfPermission(str) == 0;
    }

    static String b(Context context) {
        String str = "";
        try {
            str = x(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str;
    }

    static int c(Context context) {
        int i = -1;
        try {
            i = y(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return i;
    }

    static int d(Context context) {
        int i = -1;
        try {
            i = v(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return i;
    }

    static String e(Context context) {
        try {
            return t(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String f(Context context) {
        try {
            if (a != null && !"".equals(a)) {
                return a;
            }
            if (a(context, "android.permission.WRITE_SETTINGS")) {
                a = System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
            }
            if (!(a == null || "".equals(a))) {
                return a;
            }
            try {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                    if (file.exists()) {
                        SAXParserFactory.newInstance().newSAXParser().parse(file, new a());
                    }
                }
            } catch (Throwable th) {
                dg.a(th, "DeviceInfo", "getUTDID");
            }
            return a;
        } catch (Throwable th2) {
            dg.a(th2, "DeviceInfo", "getUTDID");
        }
    }

    static String g(Context context) {
        if (context != null) {
            try {
                if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager.isWifiEnabled()) {
                        return wifiManager.getConnectionInfo().getBSSID();
                    }
                    return null;
                }
            } catch (Throwable th) {
                dg.a(th, "DeviceInfo", "getWifiMacs");
            }
        }
        return null;
    }

    static String h(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        if (context != null) {
            try {
                if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager.isWifiEnabled()) {
                        List scanResults = wifiManager.getScanResults();
                        if (scanResults == null || scanResults.size() == 0) {
                            return stringBuilder.toString();
                        }
                        List a = a(scanResults);
                        Object obj = 1;
                        int i = 0;
                        while (i < a.size() && i < 7) {
                            ScanResult scanResult = (ScanResult) a.get(i);
                            if (obj != null) {
                                obj = null;
                            } else {
                                stringBuilder.append(i.b);
                            }
                            stringBuilder.append(scanResult.BSSID);
                            i++;
                        }
                    }
                    return stringBuilder.toString();
                }
            } catch (Throwable th) {
                dg.a(th, "DeviceInfo", "getWifiMacs");
            }
        }
        return stringBuilder.toString();
    }

    public static String i(Context context) {
        try {
            if (c != null && !"".equals(c)) {
                return c;
            }
            if (!a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return c;
            }
            c = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            return c;
        } catch (Throwable th) {
            dg.a(th, "DeviceInfo", "getDeviceMac");
        }
    }

    static String[] j(Context context) {
        try {
            if (a(context, "android.permission.READ_PHONE_STATE") && a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                CellLocation cellLocation = ((TelephonyManager) context.getSystemService("phone")).getCellLocation();
                int cid;
                int lac;
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    cid = gsmCellLocation.getCid();
                    lac = gsmCellLocation.getLac();
                    return new String[]{lac + "||" + cid, "gsm"};
                }
                if (cellLocation instanceof CdmaCellLocation) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                    cid = cdmaCellLocation.getSystemId();
                    lac = cdmaCellLocation.getNetworkId();
                    int baseStationId = cdmaCellLocation.getBaseStationId();
                    if (cid < 0 || lac < 0 || baseStationId < 0) {
                    }
                    return new String[]{cid + "||" + lac + "||" + baseStationId, "cdma"};
                }
                return new String[]{"", ""};
            }
            return new String[]{"", ""};
        } catch (Throwable th) {
            dg.a(th, "DeviceInfo", "cellInfo");
        }
    }

    static String k(Context context) {
        String str = "";
        try {
            if (a(context, "android.permission.READ_PHONE_STATE")) {
                String networkOperator = z(context).getNetworkOperator();
                if (!TextUtils.isEmpty(networkOperator) || networkOperator.length() >= 3) {
                    str = networkOperator.substring(3);
                }
            }
        } catch (Throwable th) {
            dg.a(th, "DeviceInfo", "getMNC");
        }
        return str;
    }

    public static int l(Context context) {
        int i = -1;
        try {
            i = y(context);
        } catch (Throwable th) {
            dg.a(th, "DeviceInfo", "getNetWorkType");
        }
        return i;
    }

    public static int m(Context context) {
        int i = -1;
        try {
            i = v(context);
        } catch (Throwable th) {
            dg.a(th, "DeviceInfo", "getActiveNetWorkType");
        }
        return i;
    }

    public static NetworkInfo n(Context context) {
        if (!a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return null;
        }
        ConnectivityManager w = w(context);
        return w != null ? w.getActiveNetworkInfo() : null;
    }

    static String o(Context context) {
        String str = null;
        try {
            NetworkInfo n = n(context);
            if (n != null) {
                str = n.getExtraInfo();
            }
        } catch (Throwable th) {
            dg.a(th, "DeviceInfo", "getNetworkExtraInfo");
        }
        return str;
    }

    static String p(Context context) {
        try {
            if (d != null && !"".equals(d)) {
                return d;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            d = i2 > i ? i + "*" + i2 : i2 + "*" + i;
            return d;
        } catch (Throwable th) {
            dg.a(th, "DeviceInfo", "getReslution");
        }
    }

    public static String q(Context context) {
        try {
            if (e != null && !"".equals(e)) {
                return e;
            }
            if (!a(context, "android.permission.READ_PHONE_STATE")) {
                return e;
            }
            e = z(context).getDeviceId();
            if (e == null) {
                e = "";
            }
            return e;
        } catch (Throwable th) {
            dg.a(th, "DeviceInfo", "getDeviceID");
        }
    }

    public static String r(Context context) {
        String str = "";
        try {
            str = t(context);
        } catch (Throwable th) {
            dg.a(th, "DeviceInfo", "getSubscriberId");
        }
        return str;
    }

    static String s(Context context) {
        try {
            return u(context);
        } catch (Throwable th) {
            dg.a(th, "DeviceInfo", "getNetworkOperatorName");
            return "";
        }
    }

    private static String t(Context context) {
        if (f != null && !"".equals(f)) {
            return f;
        }
        if (!a(context, "android.permission.READ_PHONE_STATE")) {
            return f;
        }
        f = z(context).getSubscriberId();
        if (f == null) {
            f = "";
        }
        return f;
    }

    private static String u(Context context) {
        if (!a(context, "android.permission.READ_PHONE_STATE")) {
            return null;
        }
        Object simOperatorName = z(context).getSimOperatorName();
        return TextUtils.isEmpty(simOperatorName) ? z(context).getNetworkOperatorName() : simOperatorName;
    }

    private static int v(Context context) {
        if (context == null || !a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return -1;
        }
        ConnectivityManager w = w(context);
        if (w == null) {
            return -1;
        }
        NetworkInfo activeNetworkInfo = w.getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.getType() : -1;
    }

    private static ConnectivityManager w(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    private static String x(Context context) {
        String str = "";
        String r = r(context);
        return (r == null || r.length() < 5) ? str : r.substring(3, 5);
    }

    private static int y(Context context) {
        return !a(context, "android.permission.READ_PHONE_STATE") ? -1 : z(context).getNetworkType();
    }

    private static TelephonyManager z(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }
}
