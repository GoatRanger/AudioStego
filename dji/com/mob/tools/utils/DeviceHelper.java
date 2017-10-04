package com.mob.tools.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.UiModeManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.support.v4.widget.AutoScrollHelper;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.facebook.internal.l;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.mob.tools.MobLog;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;

public class DeviceHelper {
    private static DeviceHelper deviceHelper;
    private Context context;

    private class GSConnection implements ServiceConnection {
        boolean got;
        private final BlockingQueue<IBinder> iBinders;

        private GSConnection() {
            this.got = false;
            this.iBinders = new LinkedBlockingQueue();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.iBinders.put(iBinder);
            } catch (Throwable th) {
                MobLog.getInstance().w(th);
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public IBinder takeBinder() throws InterruptedException {
            if (this.got) {
                throw new IllegalStateException();
            }
            this.got = true;
            return (IBinder) this.iBinders.poll(1500, TimeUnit.MILLISECONDS);
        }
    }

    public static synchronized DeviceHelper getInstance(Context context) {
        DeviceHelper deviceHelper;
        synchronized (DeviceHelper.class) {
            if (deviceHelper == null && context != null) {
                deviceHelper = new DeviceHelper(context);
            }
            deviceHelper = deviceHelper;
        }
        return deviceHelper;
    }

    private DeviceHelper(Context context) {
        this.context = context.getApplicationContext();
    }

    public boolean isRooted() {
        return false;
    }

    public String getSSID() {
        try {
            if (!checkPermission("android.permission.ACCESS_WIFI_STATE")) {
                return null;
            }
            WifiManager wifiManager = (WifiManager) getSystemService("wifi");
            if (wifiManager == null) {
                return null;
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo == null) {
                return null;
            }
            String replace = connectionInfo.getSSID().replace("\"", "");
            if (replace == null) {
                replace = null;
            }
            return replace;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return null;
        }
    }

    public String getBssid() {
        try {
            if (!checkPermission("android.permission.ACCESS_WIFI_STATE")) {
                return null;
            }
            WifiManager wifiManager = (WifiManager) getSystemService("wifi");
            if (wifiManager == null) {
                return null;
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo == null) {
                return null;
            }
            String bssid = connectionInfo.getBSSID();
            if (bssid == null) {
                bssid = null;
            }
            return bssid;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return null;
        }
    }

    public String getMacAddress() {
        String hardwareAddressFromShell;
        if (VERSION.SDK_INT >= 23) {
            try {
                hardwareAddressFromShell = getHardwareAddressFromShell("wlan0");
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
                hardwareAddressFromShell = null;
            }
            if (hardwareAddressFromShell == null) {
                try {
                    hardwareAddressFromShell = getCurrentNetworkHardwareAddress();
                } catch (Throwable th2) {
                    MobLog.getInstance().d(th2);
                    hardwareAddressFromShell = null;
                }
            }
            if (hardwareAddressFromShell == null) {
                try {
                    String[] listNetworkHardwareAddress = listNetworkHardwareAddress();
                    if (listNetworkHardwareAddress.length > 0) {
                        hardwareAddressFromShell = listNetworkHardwareAddress[0];
                    }
                } catch (Throwable th22) {
                    MobLog.getInstance().d(th22);
                    hardwareAddressFromShell = null;
                }
            }
            if (hardwareAddressFromShell != null) {
                return hardwareAddressFromShell;
            }
        }
        WifiManager wifiManager = (WifiManager) getSystemService("wifi");
        if (wifiManager == null) {
            return null;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return null;
        }
        hardwareAddressFromShell = connectionInfo.getMacAddress();
        if (hardwareAddressFromShell == null) {
            hardwareAddressFromShell = null;
        }
        return hardwareAddressFromShell;
    }

    private String getCurrentNetworkHardwareAddress() throws Throwable {
        Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
        if (networkInterfaces == null) {
            return null;
        }
        for (NetworkInterface networkInterface : Collections.list(networkInterfaces)) {
            Enumeration inetAddresses = networkInterface.getInetAddresses();
            if (inetAddresses != null) {
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        byte[] hardwareAddress = networkInterface.getHardwareAddress();
                        if (hardwareAddress != null) {
                            StringBuilder stringBuilder = new StringBuilder();
                            int length = hardwareAddress.length;
                            for (int i = 0; i < length; i++) {
                                stringBuilder.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                            }
                            if (stringBuilder.length() > 0) {
                                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                            }
                            return stringBuilder.toString();
                        }
                    }
                }
                continue;
            }
        }
        return null;
    }

    private String[] listNetworkHardwareAddress() throws Throwable {
        int i = 0;
        Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
        if (networkInterfaces == null) {
            return null;
        }
        List<NetworkInterface> list = Collections.list(networkInterfaces);
        HashMap hashMap = new HashMap();
        for (NetworkInterface networkInterface : list) {
            byte[] hardwareAddress = networkInterface.getHardwareAddress();
            if (hardwareAddress != null) {
                StringBuilder stringBuilder = new StringBuilder();
                int length = hardwareAddress.length;
                for (int i2 = 0; i2 < length; i2++) {
                    stringBuilder.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i2])}));
                }
                if (stringBuilder.length() > 0) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
                hashMap.put(networkInterface.getName(), stringBuilder.toString());
            }
        }
        ArrayList arrayList = new ArrayList(hashMap.keySet());
        Collection arrayList2 = new ArrayList();
        Collection arrayList3 = new ArrayList();
        Collection arrayList4 = new ArrayList();
        Collection arrayList5 = new ArrayList();
        Collection arrayList6 = new ArrayList();
        Collection arrayList7 = new ArrayList();
        Collection arrayList8 = new ArrayList();
        while (arrayList.size() > 0) {
            String str = (String) arrayList.remove(0);
            if (str.startsWith("wlan")) {
                arrayList2.add(str);
            } else if (str.startsWith("eth")) {
                arrayList3.add(str);
            } else if (str.startsWith("rev_rmnet")) {
                arrayList4.add(str);
            } else if (str.startsWith("dummy")) {
                arrayList5.add(str);
            } else if (str.startsWith("usbnet")) {
                arrayList6.add(str);
            } else if (str.startsWith("rmnet_usb")) {
                arrayList7.add(str);
            } else {
                arrayList8.add(str);
            }
        }
        Collections.sort(arrayList2);
        Collections.sort(arrayList3);
        Collections.sort(arrayList4);
        Collections.sort(arrayList5);
        Collections.sort(arrayList6);
        Collections.sort(arrayList7);
        Collections.sort(arrayList8);
        arrayList.addAll(arrayList2);
        arrayList.addAll(arrayList3);
        arrayList.addAll(arrayList4);
        arrayList.addAll(arrayList5);
        arrayList.addAll(arrayList6);
        arrayList.addAll(arrayList7);
        arrayList.addAll(arrayList8);
        String[] strArr = new String[arrayList.size()];
        while (i < strArr.length) {
            strArr[i] = (String) hashMap.get(arrayList.get(i));
            i++;
        }
        return strArr;
    }

    private String getHardwareAddressFromShell(String str) {
        CharSequence readLine;
        Throwable th;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2;
        try {
            bufferedReader2 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/" + str + "/address").getInputStream()));
            try {
                readLine = bufferedReader2.readLine();
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Throwable th2) {
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    MobLog.getInstance().d(th);
                    if (bufferedReader2 == null) {
                        try {
                            bufferedReader2.close();
                            readLine = null;
                        } catch (Throwable th4) {
                            readLine = null;
                        }
                    } else {
                        readLine = null;
                    }
                    if (TextUtils.isEmpty(readLine)) {
                        return readLine;
                    }
                    return null;
                } catch (Throwable th5) {
                    th = th5;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable th6) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th7) {
            th = th7;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        if (TextUtils.isEmpty(readLine)) {
            return null;
        }
        return readLine;
    }

    public String getModel() {
        return Build.MODEL;
    }

    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public String getDeviceId() {
        String imei = getIMEI();
        if (!TextUtils.isEmpty(imei) || VERSION.SDK_INT < 9) {
            return imei;
        }
        return getSerialno();
    }

    public String getMime() {
        return getIMEI();
    }

    public String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        CharSequence deviceId;
        try {
            if (checkPermission("android.permission.READ_PHONE_STATE")) {
                deviceId = telephonyManager.getDeviceId();
                if (TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
                return null;
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        deviceId = null;
        if (TextUtils.isEmpty(deviceId)) {
            return null;
        }
        return deviceId;
    }

    public String getSerialno() {
        if (VERSION.SDK_INT < 9) {
            return null;
        }
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.serialno", "unknown"});
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return null;
        }
    }

    public String getDeviceData() {
        return Base64AES(getModel() + "|" + getOSVersionInt() + "|" + getManufacturer() + "|" + getCarrier() + "|" + getScreenSize(), getDeviceKey().substring(0, 16));
    }

    public String getDeviceDataNotAES() {
        return getModel() + "|" + getOSVersion() + "|" + getManufacturer() + "|" + getCarrier() + "|" + getScreenSize();
    }

    public String Base64AES(String str, String str2) {
        String encodeToString;
        Throwable th;
        try {
            encodeToString = Base64.encodeToString(Data.AES128Encode(str2, str), 0);
            try {
                if (encodeToString.contains("\n")) {
                    encodeToString = encodeToString.replace("\n", "");
                }
            } catch (Throwable th2) {
                th = th2;
                MobLog.getInstance().w(th);
                return encodeToString;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            encodeToString = null;
            th = th4;
            MobLog.getInstance().w(th);
            return encodeToString;
        }
        return encodeToString;
    }

    public String getOSVersion() {
        return String.valueOf(getOSVersionInt());
    }

    public int getOSVersionInt() {
        return VERSION.SDK_INT;
    }

    public String getOSVersionName() {
        return VERSION.RELEASE;
    }

    public String getOSLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public String getAppLanguage() {
        return this.context.getResources().getConfiguration().locale.getLanguage();
    }

    public String getOSCountry() {
        return Locale.getDefault().getCountry();
    }

    public String getScreenSize() {
        int[] screenSize = R.getScreenSize(this.context);
        if (this.context.getResources().getConfiguration().orientation == 1) {
            return screenSize[0] + "x" + screenSize[1];
        }
        return screenSize[1] + "x" + screenSize[0];
    }

    public String getCarrier() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (telephonyManager == null) {
            return WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        }
        String simOperator = telephonyManager.getSimOperator();
        if (TextUtils.isEmpty(simOperator)) {
            return WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        }
        return simOperator;
    }

    public String getCarrierName() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        try {
            if (!checkPermission("android.permission.READ_PHONE_STATE")) {
                return null;
            }
            CharSequence simOperatorName = telephonyManager.getSimOperatorName();
            if (TextUtils.isEmpty(simOperatorName)) {
                simOperatorName = null;
            }
            return simOperatorName;
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    public String getMCC() {
        if (((TelephonyManager) getSystemService("phone")) == null) {
            return null;
        }
        String imsi = getIMSI();
        return (imsi == null || imsi.length() < 3) ? null : imsi.substring(0, 3);
    }

    public String getMNC() {
        if (((TelephonyManager) getSystemService("phone")) == null) {
            return null;
        }
        String imsi = getIMSI();
        return (imsi == null || imsi.length() < 5) ? null : imsi.substring(3, 5);
    }

    public String getSimSerialNumber() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (telephonyManager == null) {
            return WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        }
        return telephonyManager.getSimSerialNumber();
    }

    public String getLine1Number() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (telephonyManager == null) {
            return WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        }
        return telephonyManager.getLine1Number();
    }

    public String getBluetoothName() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null && checkPermission("android.permission.BLUETOOTH")) {
                return defaultAdapter.getName();
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return null;
    }

    public String getSignMD5() {
        try {
            return Data.MD5(this.context.getPackageManager().getPackageInfo(getPackageName(), 64).signatures[0].toByteArray());
        } catch (Throwable e) {
            MobLog.getInstance().w(e);
            return null;
        }
    }

    private Object getSystemService(String str) {
        try {
            return this.context.getSystemService(str);
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return null;
        }
    }

    public String getNetworkType() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
        if (connectivityManager == null) {
            return "none";
        }
        try {
            if (!checkPermission("android.permission.ACCESS_NETWORK_STATE")) {
                return "none";
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return "none";
            }
            int type = activeNetworkInfo.getType();
            switch (type) {
                case 0:
                    if (is4GMobileNetwork()) {
                        return "4G";
                    }
                    return isFastMobileNetwork() ? "3G" : "2G";
                case 1:
                    return "wifi";
                case 6:
                    return "wimax";
                case 7:
                    return "bluetooth";
                case 8:
                    return "dummy";
                case 9:
                    return "ethernet";
                default:
                    return String.valueOf(type);
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return "none";
        }
    }

    public String getNetworkTypeForStatic() {
        String toLowerCase = getNetworkType().toLowerCase();
        if (TextUtils.isEmpty(toLowerCase) || "none".equals(toLowerCase)) {
            return "none";
        }
        if (toLowerCase.startsWith("4g") || toLowerCase.startsWith("3g") || toLowerCase.startsWith("2g")) {
            return "cell";
        }
        if (toLowerCase.startsWith("wifi")) {
            return "wifi";
        }
        return l.j;
    }

    public String getDetailNetworkTypeForStatic() {
        String toLowerCase = getNetworkType().toLowerCase();
        if (TextUtils.isEmpty(toLowerCase) || "none".equals(toLowerCase)) {
            return "none";
        }
        if (toLowerCase.startsWith("wifi")) {
            return "wifi";
        }
        if (toLowerCase.startsWith("4g")) {
            return "4g";
        }
        if (toLowerCase.startsWith("3g")) {
            return "3g";
        }
        if (toLowerCase.startsWith("2g")) {
            return "2g";
        }
        if (toLowerCase.startsWith("bluetooth")) {
            return "bluetooth";
        }
        return toLowerCase;
    }

    public int getPlatformCode() {
        return 1;
    }

    private boolean is4GMobileNetwork() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (telephonyManager == null) {
            return false;
        }
        return telephonyManager.getNetworkType() == 13;
    }

    private boolean isFastMobileNetwork() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (telephonyManager == null) {
            return false;
        }
        switch (telephonyManager.getNetworkType()) {
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                return false;
            case 3:
                return true;
            case 4:
                return false;
            case 5:
                return true;
            case 6:
                return true;
            case 7:
                return false;
            case 8:
                return true;
            case 9:
                return true;
            case 10:
                return true;
            case 11:
                return false;
            case 12:
                return true;
            case 13:
                return true;
            case 14:
                return true;
            case 15:
                return true;
            default:
                return false;
        }
    }

    public JSONArray getRunningApp() {
        JSONArray jSONArray = new JSONArray();
        ActivityManager activityManager = (ActivityManager) getSystemService(Constants.FLAG_ACTIVITY_NAME);
        if (activityManager == null) {
            return jSONArray;
        }
        List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return jSONArray;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            jSONArray.put(runningAppProcessInfo.processName);
        }
        return jSONArray;
    }

    public String getRunningAppStr() throws JSONException {
        JSONArray runningApp = getRunningApp();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < runningApp.length(); i++) {
            if (i > 0) {
                stringBuilder.append(',');
            }
            stringBuilder.append(String.valueOf(runningApp.get(i)));
        }
        return stringBuilder.toString();
    }

    public String getDeviceKey() {
        String localDeviceKey;
        String str = null;
        try {
            localDeviceKey = getLocalDeviceKey();
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            localDeviceKey = str;
        }
        if (TextUtils.isEmpty(localDeviceKey) || localDeviceKey.length() < 40) {
            try {
                localDeviceKey = getMacAddress();
                String deviceId = getDeviceId();
                str = Data.byteToHex(Data.SHA1(localDeviceKey + ":" + deviceId + ":" + getModel()));
            } catch (Throwable th2) {
                MobLog.getInstance().d(th2);
            }
            if (TextUtils.isEmpty(str) || str.length() < 40) {
                localDeviceKey = getCharAndNumr(40);
            } else {
                localDeviceKey = str;
            }
            if (localDeviceKey != null) {
                try {
                    saveLocalDeviceKey(localDeviceKey);
                } catch (Throwable th3) {
                    MobLog.getInstance().w(th3);
                }
            }
        }
        return localDeviceKey;
    }

    public String getCharAndNumr(int i) {
        long currentTimeMillis = System.currentTimeMillis() ^ SystemClock.elapsedRealtime();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(currentTimeMillis);
        Random random = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            if ("char".equalsIgnoreCase(random.nextInt(2) % 2 == 0 ? "char" : "num")) {
                stringBuffer.insert(i2 + 1, (char) (random.nextInt(26) + 97));
            } else {
                stringBuffer.insert(stringBuffer.length(), random.nextInt(10));
            }
        }
        return stringBuffer.toString().substring(0, 40);
    }

    private String getLocalDeviceKey() throws Throwable {
        if (!getSdcardState()) {
            return null;
        }
        File file;
        File file2 = new File(getSdcardPath(), "ShareSDK");
        if (file2.exists()) {
            file = new File(file2, ".dk");
            if (file.exists() && file.renameTo(new File(R.getCacheRoot(this.context), ".dk"))) {
                file.delete();
            }
        }
        file = new File(R.getCacheRoot(this.context), ".dk");
        if (!file.exists()) {
            return null;
        }
        String str;
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        Object readObject = objectInputStream.readObject();
        if (readObject == null || !(readObject instanceof char[])) {
            str = null;
        } else {
            str = String.valueOf((char[]) readObject);
        }
        objectInputStream.close();
        return str;
    }

    private void saveLocalDeviceKey(String str) throws Throwable {
        if (getSdcardState()) {
            File file = new File(R.getCacheRoot(this.context), ".dk");
            if (file.exists()) {
                file.delete();
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(str.toCharArray());
            objectOutputStream.flush();
            objectOutputStream.close();
        }
    }

    public String getPackageName() {
        return this.context.getPackageName();
    }

    public String getAppName() {
        String str = this.context.getApplicationInfo().name;
        if (str != null) {
            return str;
        }
        int i = this.context.getApplicationInfo().labelRes;
        if (i > 0) {
            return this.context.getString(i);
        }
        return String.valueOf(this.context.getApplicationInfo().nonLocalizedLabel);
    }

    public int getAppVersion() {
        int i = 0;
        try {
            return this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionCode;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return i;
        }
    }

    public String getAppVersionName() {
        try {
            return this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return "1.0";
        }
    }

    public ArrayList<HashMap<String, String>> getInstalledApp(boolean z) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            Process exec = Runtime.getRuntime().exec("pm list packages");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream(), "utf-8"));
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                readLine = readLine.toLowerCase().trim();
                if (readLine.startsWith("package:")) {
                    arrayList.add(readLine.substring("package:".length()).trim());
                }
            }
            bufferedReader.close();
            exec.destroy();
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return new ArrayList();
        }
        ArrayList<HashMap<String, String>> arrayList2 = new ArrayList();
        PackageManager packageManager = this.context.getPackageManager();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            PackageInfo packageInfo = null;
            try {
                packageInfo = packageManager.getPackageInfo((String) it.next(), 0);
            } catch (Throwable th2) {
                MobLog.getInstance().w(th2);
            }
            if (packageInfo != null) {
                if (!z) {
                    if (isSystemApp(packageInfo)) {
                    }
                }
                HashMap hashMap = new HashMap();
                hashMap.put("pkg", packageInfo.packageName);
                Object obj = packageInfo.applicationInfo.name;
                if (obj == null) {
                    int i = packageInfo.applicationInfo.labelRes;
                    if (i > 0) {
                        CharSequence text = packageManager.getText(packageInfo.packageName, i, packageInfo.applicationInfo);
                        if (text != null) {
                            obj = text.toString().trim();
                        }
                    }
                    if (obj == null) {
                        obj = String.valueOf(packageInfo.applicationInfo.nonLocalizedLabel);
                    }
                }
                hashMap.put("name", obj);
                hashMap.put("version", packageInfo.versionName);
                arrayList2.add(hashMap);
            }
        }
        return arrayList2;
    }

    private boolean isSystemApp(PackageInfo packageInfo) {
        boolean z;
        if ((packageInfo.applicationInfo.flags & 1) == 1) {
            z = true;
        } else {
            z = false;
        }
        boolean z2;
        if ((packageInfo.applicationInfo.flags & 128) == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || r3) {
            return true;
        }
        return false;
    }

    public String getNetworkOperator() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getNetworkOperator();
    }

    public boolean checkPermission(String str) throws Throwable {
        int intValue;
        if (VERSION.SDK_INT >= 23) {
            try {
                ReflectHelper.importClass("android.content.Context");
                Integer num = (Integer) ReflectHelper.invokeInstanceMethod(this.context, "checkSelfPermission", str);
                intValue = num == null ? -1 : num.intValue();
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
                intValue = -1;
            }
        } else {
            this.context.checkPermission(str, Process.myPid(), Process.myUid());
            intValue = this.context.getPackageManager().checkPermission(str, getPackageName());
        }
        if (intValue == 0) {
            return true;
        }
        return false;
    }

    public String getTopTaskPackageName() {
        boolean checkPermission;
        try {
            checkPermission = checkPermission("android.permission.GET_TASKS");
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            checkPermission = false;
        }
        if (checkPermission) {
            try {
                ActivityManager activityManager = (ActivityManager) getSystemService(Constants.FLAG_ACTIVITY_NAME);
                if (activityManager == null) {
                    return null;
                }
                if (VERSION.SDK_INT <= 20) {
                    return ((RunningTaskInfo) activityManager.getRunningTasks(1).get(0)).topActivity.getPackageName();
                }
                return ((RunningAppProcessInfo) activityManager.getRunningAppProcesses().get(0)).processName.split(":")[0];
            } catch (Throwable th2) {
                MobLog.getInstance().w(th2);
            }
        }
        return null;
    }

    public boolean getSdcardState() {
        try {
            return checkPermission("android.permission.WRITE_EXTERNAL_STORAGE") && "mounted".equals(Environment.getExternalStorageState());
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
            return false;
        }
    }

    public String getSdcardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public String getAndroidID() {
        String string = Secure.getString(this.context.getContentResolver(), "android_id");
        MobLog.getInstance().i("getAndroidID === " + string, new Object[0]);
        return string;
    }

    public String getAdvertisingID() {
        String readString;
        try {
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            ServiceConnection gSConnection = new GSConnection();
            this.context.bindService(intent, gSConnection, 1);
            IBinder takeBinder = gSConnection.takeBinder();
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            takeBinder.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            readString = obtain2.readString();
            obtain2.recycle();
            obtain.recycle();
            MobLog.getInstance().i("getAdvertisingID === " + readString, new Object[0]);
            this.context.unbindService(gSConnection);
            return readString;
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
            return null;
        }
    }

    public void hideSoftInput(View view) {
        Object systemService = getSystemService("input_method");
        if (systemService != null) {
            ((InputMethodManager) systemService).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showSoftInput(View view) {
        Object systemService = getSystemService("input_method");
        if (systemService != null) {
            ((InputMethodManager) systemService).toggleSoftInputFromWindow(view.getWindowToken(), 2, 0);
        }
    }

    public boolean isMainProcess(int i) {
        ActivityManager activityManager = (ActivityManager) getSystemService(Constants.FLAG_ACTIVITY_NAME);
        if (activityManager.getRunningAppProcesses() == null) {
            return i <= 0;
        } else {
            Object obj;
            if (i <= 0) {
                i = Process.myPid();
            }
            for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == i) {
                    obj = runningAppProcessInfo.processName;
                    break;
                }
            }
            obj = null;
            return getPackageName().equals(obj);
        }
    }

    public String getIMSI() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        CharSequence subscriberId;
        try {
            if (checkPermission("android.permission.READ_PHONE_STATE")) {
                subscriberId = telephonyManager.getSubscriberId();
                if (TextUtils.isEmpty(subscriberId)) {
                    return subscriberId;
                }
                return null;
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        subscriberId = null;
        if (TextUtils.isEmpty(subscriberId)) {
            return null;
        }
        return subscriberId;
    }

    public String getIPAddress() {
        try {
            if (checkPermission("android.permission.INTERNET")) {
                Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().w(th);
        }
        return "0.0.0.0";
    }

    public float[] getLocation(int i, int i2) {
        if (getLocation(i, i2, true) == null) {
            return null;
        }
        return new float[]{(float) getLocation(i, i2, true).getLatitude(), (float) getLocation(i, i2, true).getLongitude()};
    }

    public Location getLocation(int i, int i2, boolean z) {
        try {
            if (checkPermission("android.permission.ACCESS_FINE_LOCATION")) {
                return new LocationHelper().getLocation(this.context, i, i2, z);
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return null;
    }

    public HashMap<String, String> ping(String str, int i, int i2) {
        int indexOf;
        float f;
        float floatValue;
        float f2 = 0.0f;
        ArrayList arrayList = new ArrayList();
        int i3 = i2 + 8;
        Process exec = Runtime.getRuntime().exec("ping -c " + i + " -s " + i2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        String readLine = bufferedReader.readLine();
        while (readLine != null) {
            if (readLine.startsWith(i3 + " bytes from")) {
                if (readLine.endsWith("ms")) {
                    readLine = readLine.substring(0, readLine.length() - 2).trim();
                } else if (readLine.endsWith("s")) {
                    readLine = readLine.substring(0, readLine.length() - 1).trim() + "000";
                }
                indexOf = readLine.indexOf("time=");
                if (indexOf > 0) {
                    try {
                        arrayList.add(Float.valueOf(Float.parseFloat(readLine.substring(indexOf + 5).trim())));
                    } catch (Throwable th) {
                        MobLog.getInstance().d(th);
                    }
                }
            }
            readLine = bufferedReader.readLine();
        }
        exec.waitFor();
        indexOf = arrayList.size();
        int size = i - arrayList.size();
        if (indexOf > 0) {
            float f3 = AutoScrollHelper.NO_MAX;
            int i4 = 0;
            float f4 = 0.0f;
            f = 0.0f;
            while (i4 < indexOf) {
                floatValue = ((Float) arrayList.get(i4)).floatValue();
                if (floatValue < f3) {
                    f3 = floatValue;
                }
                if (floatValue > f) {
                    f2 = floatValue;
                } else {
                    f2 = f;
                }
                i4++;
                f4 += floatValue;
                f = f2;
            }
            f2 = f4 / ((float) indexOf);
            floatValue = f3;
        } else {
            f = 0.0f;
            floatValue = 0.0f;
        }
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("address", str);
        hashMap.put("transmitted", String.valueOf(i));
        hashMap.put("received", String.valueOf(indexOf));
        hashMap.put("loss", String.valueOf(size));
        hashMap.put(MessageKey.MSG_ACCEPT_TIME_MIN, String.valueOf(floatValue));
        hashMap.put("max", String.valueOf(f));
        hashMap.put("avg", String.valueOf(f2));
        return hashMap;
    }

    public int getCellId() {
        try {
            if (checkPermission("android.permission.ACCESS_COARSE_LOCATION")) {
                TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
                if (telephonyManager != null) {
                    return ((GsmCellLocation) telephonyManager.getCellLocation()).getCid();
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return -1;
    }

    public int getCellLac() {
        try {
            if (checkPermission("android.permission.ACCESS_COARSE_LOCATION")) {
                TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
                if (telephonyManager != null) {
                    return ((GsmCellLocation) telephonyManager.getCellLocation()).getLac();
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
        return -1;
    }

    public String getDeviceType() {
        UiModeManager uiModeManager = (UiModeManager) getSystemService("uimode");
        if (uiModeManager != null) {
            switch (uiModeManager.getCurrentModeType()) {
                case 1:
                    return "NO_UI";
                case 2:
                    return "DESK";
                case 3:
                    return "CAR";
                case 4:
                    return "TELEVISION";
                case 5:
                    return "APPLIANCE";
                case 6:
                    return "WATCH";
            }
        }
        return "UNDEFINED";
    }
}
