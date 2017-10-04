package com.here.services.location.util;

import android.content.Context;
import com.here.services.common.Types.Source;
import com.here.services.common.Types.Technology;
import com.here.services.location.OptionsChangedEvent;
import com.here.services.util.HereServicesUtil;
import java.util.EnumSet;

public class OptionsChangeHelper {

    public interface Callbacks {
        void onAirplaneModeEnabled();

        void onBluetoothLEDisabled();

        void onCellDisabled();

        void onGnssLocationDisabled();

        void onNetworkLocationDisabled();

        void onWifiScansDisabled();
    }

    public static void onOptionsChanged(Context context, Callbacks callbacks, OptionsChangedEvent optionsChangedEvent) {
        Object obj = null;
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        } else if (callbacks == null) {
            throw new IllegalArgumentException("handler is null");
        } else {
            EnumSet disabledSources = optionsChangedEvent.getDisabledSources();
            if (disabledSources != null && ((disabledSources.contains(Source.HighAccuracy) || disabledSources.contains(Source.Offline) || disabledSources.contains(Source.Online) || disabledSources.contains(Source.Cache)) && !HereServicesUtil.isNetworkLocationEnabled(context))) {
                callbacks.onNetworkLocationDisabled();
            }
            EnumSet disabledTechnologies = optionsChangedEvent.getDisabledTechnologies();
            if (disabledTechnologies != null) {
                disabledSources = optionsChangedEvent.getRequestedTechnologies();
                disabledSources.removeAll(disabledTechnologies);
                Object obj2 = (disabledSources.contains(Technology.Wlan) || disabledSources.contains(Technology.BluetoothLE) || disabledSources.contains(Technology.Cell)) ? 1 : null;
                if (HereServicesUtil.hasWifi(context) || HereServicesUtil.hasBluetoothLe(context) || HereServicesUtil.hasCell(context)) {
                    obj = 1;
                }
                if (obj != null && obj2 == null && HereServicesUtil.isAirplaneModeEnabled(context)) {
                    callbacks.onAirplaneModeEnabled();
                }
                if (HereServicesUtil.hasWifi(context) && disabledTechnologies.contains(Technology.Wlan)) {
                    callbacks.onWifiScansDisabled();
                }
                if (HereServicesUtil.hasBluetoothLe(context) && disabledTechnologies.contains(Technology.BluetoothLE)) {
                    callbacks.onBluetoothLEDisabled();
                }
                if (HereServicesUtil.hasCell(context) && disabledTechnologies.contains(Technology.Cell)) {
                    callbacks.onCellDisabled();
                }
                if (HereServicesUtil.hasGps(context) && disabledTechnologies.contains(Technology.Gnss)) {
                    callbacks.onGnssLocationDisabled();
                }
            }
        }
    }
}
