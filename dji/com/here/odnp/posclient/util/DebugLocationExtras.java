package com.here.odnp.posclient.util;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import com.here.odnp.ble.BleScanResult;
import com.here.posclient.WifiMeasurement;
import java.util.ArrayList;
import java.util.List;

public class DebugLocationExtras {
    private static final String AP_SEPARATOR = "|";
    private static final String BLE_SCAN_RESULTS_AVAILABLE_ACTION = "com.here.odnp.util.test.BLE_SCAN_RESULTS";
    public static final String DEBUG_EXTRA_KEY_LOCATION_SOURCE = "dbg:sourceId";
    public static final String DEBUG_EXTRA_KEY_LOCATION_SOURCE_POSCLIENT = "posclient ODNP";
    private static final String EXTRA_KEY_AP_LIST = "ap_list";
    public static final String EXTRA_KEY_MANAGED_MEMORY_STATUS = "dbg:memory-managed";
    public static final String EXTRA_KEY_NATIVE_MEMORY_STATUS = "dbg:memory-native";
    private static final String EXTRA_KEY_PLAYBACK = "dbg:playback";
    public static final String EXTRA_KEY_SERVICE_VERSION = "dbg:service-version";
    private static final int INVALID_RX_VALUE = Integer.MAX_VALUE;
    private static final String PLAYBACK_WIFI_SCAN_RESULTS_AVAILABLE_ACTION = "com.here.odnp.util.test.PLAYBACK_WIFI_SCAN_RESULTS";

    public static void addLocationExtras(Location location, String str, String str2) {
    }

    public static void sendWifiScanResultDebugInfo(Context context, List<WifiMeasurement> list) {
    }

    public static void sendBleScanResultDebugInfo(Context context, List<BleScanResult> list, boolean z) {
    }

    private static void addScanResultToList(ArrayList<String> arrayList, String str, String str2, int i) {
        arrayList.add(str + AP_SEPARATOR + str2 + AP_SEPARATOR + (i == Integer.MAX_VALUE ? "" : String.valueOf(i)));
    }

    private static void sendScanResultIntent(Context context, String str, ArrayList<String> arrayList, boolean z) {
        Intent intent = new Intent(str);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(EXTRA_KEY_AP_LIST, arrayList);
        bundle.putBoolean(EXTRA_KEY_PLAYBACK, z);
        intent.putExtras(bundle);
        context.sendBroadcast(intent);
    }
}
