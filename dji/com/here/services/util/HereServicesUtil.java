package com.here.services.util;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ContentResolver;
import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import dji.pilot.usercenter.mode.n;

public class HereServicesUtil {
    private static final String TAG = "services.util.HereServicesUtil";

    private HereServicesUtil() {
    }

    public static boolean hasWifi(Context context) {
        return hasSystemFeature(context, "android.hardware.wifi");
    }

    @TargetApi(18)
    public static boolean isWifiScanEnabled(Context context) {
        if (!hasWifi(context)) {
            return false;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        boolean isWifiEnabled = wifiManager.isWifiEnabled();
        boolean z = (VERSION.SDK_INT < 18 || isWifiEnabled) ? isWifiEnabled : wifiManager.isScanAlwaysAvailable() && !isAirplaneModeEnabled(context);
        return z;
    }

    @TargetApi(18)
    public static boolean hasBluetoothLe(Context context) {
        return VERSION.SDK_INT >= 18 && hasSystemFeature(context, "android.hardware.bluetooth_le");
    }

    @TargetApi(18)
    public static boolean isBluetoothLeEnabled(Context context) {
        if (!hasBluetoothLe(context)) {
            return false;
        }
        BluetoothAdapter adapter = ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter();
        boolean z = adapter != null && adapter.getState() == 12;
        return z;
    }

    public static boolean hasGsmPhone(Context context) {
        return hasSystemFeature(context, "android.hardware.telephony.gsm");
    }

    public static boolean hasCdmaPhone(Context context) {
        return hasSystemFeature(context, "android.hardware.telephony.cdma");
    }

    public static boolean hasCellularData(Context context) {
        return hasSystemFeature(context, "android.hardware.telephony");
    }

    public static boolean hasPhone(Context context) {
        return hasGsmPhone(context) || hasCdmaPhone(context);
    }

    public static boolean hasCell(Context context) {
        return hasPhone(context) || hasCellularData(context);
    }

    public static boolean isPhoneEnabled(Context context) {
        return hasPhone(context) && !isAirplaneModeEnabled(context);
    }

    public static boolean isCellEnabled(Context context) {
        return hasCell(context) && !isAirplaneModeEnabled(context);
    }

    public static boolean hasGps(Context context) {
        return hasSystemFeature(context, "android.hardware.location.gps");
    }

    public static boolean isGpsLocationEnabled(Context context) {
        return hasGps(context) && isLocationProviderEnabled(context, "gps");
    }

    @TargetApi(19)
    public static boolean isNetworkLocationEnabled(Context context) {
        if (VERSION.SDK_INT < 19) {
            return Secure.isLocationProviderEnabled(context.getContentResolver(), "network");
        }
        try {
            int i = Secure.getInt(context.getContentResolver(), "location_mode");
            if (i == 2 || i == 3) {
                return true;
            }
            return false;
        } catch (SettingNotFoundException e) {
            return false;
        }
    }

    @TargetApi(17)
    public static boolean isLocationProviderEnabledForUser(Context context, int i) {
        try {
            return ((Boolean) Secure.class.getDeclaredMethod("isLocationProviderEnabledForUser", new Class[]{ContentResolver.class, String.class, Integer.TYPE}).invoke(null, new Object[]{context.getContentResolver(), "network", Integer.valueOf(i)})).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    @TargetApi(17)
    public static boolean isAirplaneModeEnabled(Context context) {
        if (VERSION.SDK_INT < 17) {
            if (System.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0) {
                return true;
            }
            return false;
        } else if (Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean hasExternalWritableStorage() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    static boolean hasSystemFeature(Context context, String str) {
        return context.getPackageManager().hasSystemFeature(str);
    }

    static boolean isLocationProviderEnabled(Context context, String str) {
        return ((LocationManager) context.getSystemService(n.C)).isProviderEnabled(str);
    }
}
