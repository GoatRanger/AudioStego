package com.here.odnp.wifi.util;

import android.net.wifi.ScanResult;
import com.here.posclient.WifiMeasurement;
import java.util.ArrayList;
import java.util.List;

public class WifiUtils {
    private static final String TAG = "odnp.wifi.util.WifiUtils";

    public static List<WifiMeasurement> toWifiMeasurements(List<ScanResult> list) {
        if (list == null) {
            return new ArrayList(0);
        }
        List<WifiMeasurement> arrayList = new ArrayList(list.size());
        for (ScanResult wifiMeasurement : list) {
            try {
                arrayList.add(new WifiMeasurement(wifiMeasurement));
            } catch (IllegalArgumentException e) {
            }
        }
        return arrayList;
    }
}
