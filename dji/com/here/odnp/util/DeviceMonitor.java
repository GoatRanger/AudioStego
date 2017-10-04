package com.here.odnp.util;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import com.here.services.util.HereServicesUtil;
import java.util.ArrayList;
import java.util.List;

public class DeviceMonitor {
    private static final String TAG = "odnp.util.DeviceMonitor";
    final List<Monitor> mMonitors;
    State mState;

    private interface Monitor {
        void start();

        void stop();
    }

    static abstract class MonitorBase implements Monitor {
        final Context mContext;
        boolean mEnabled;
        final Listener mListener;
        final MonitorType mMonitor;
        final BroadcastReceiver mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                MonitorBase.this.setEnabled(MonitorBase.this.getState());
            }
        };
        private boolean mStarted;

        abstract boolean getState();

        abstract void onStart();

        abstract void onStop();

        protected MonitorBase(Context context, Listener listener, MonitorType monitorType) {
            this.mContext = context;
            this.mListener = listener;
            this.mMonitor = monitorType;
            this.mEnabled = getState();
        }

        void setEnabled(boolean z) {
            if (z != this.mEnabled) {
                this.mEnabled = z;
                this.mListener.onMonitorStateChanged(this.mMonitor, this.mEnabled);
            }
        }

        public void start() {
            if (!this.mStarted) {
                this.mStarted = true;
                onStart();
                this.mListener.onMonitoringStarted(this.mMonitor, getState());
            }
        }

        public void stop() {
            if (this.mStarted) {
                this.mStarted = false;
                onStop();
                this.mListener.onMonitoringStopped(this.mMonitor);
            }
        }
    }

    static class AirplaneModeMonitor extends MonitorBase {
        AirplaneModeMonitor(Context context, Listener listener) {
            super(context, listener, MonitorType.AirplaneMode);
        }

        boolean getState() {
            return HereServicesUtil.isAirplaneModeEnabled(this.mContext);
        }

        void onStart() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
            this.mContext.registerReceiver(this.mReceiver, intentFilter);
        }

        void onStop() {
            this.mContext.unregisterReceiver(this.mReceiver);
        }
    }

    static class BleMonitor extends MonitorBase {
        BleMonitor(Context context, Listener listener) {
            super(context, listener, MonitorType.BluetoothLE);
        }

        boolean getState() {
            return HereServicesUtil.isBluetoothLeEnabled(this.mContext);
        }

        void onStart() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            this.mContext.registerReceiver(this.mReceiver, intentFilter);
        }

        void onStop() {
            this.mContext.unregisterReceiver(this.mReceiver);
        }
    }

    public static class Builder {
        final Context mContext;
        final Listener mListener;
        boolean mMonitorAirplaneMode = false;
        boolean mMonitorBle = false;
        boolean mMonitorCell = false;
        boolean mMonitorGps = false;
        boolean mMonitorNetwokLocation = false;
        boolean mMonitorWifi = false;

        public Builder(Context context, Listener listener) {
            if (context == null) {
                throw new IllegalArgumentException("context is null");
            } else if (listener == null) {
                throw new IllegalArgumentException("listener is null");
            } else {
                this.mContext = context;
                this.mListener = listener;
            }
        }

        public Builder setMonitorGps(boolean z) {
            this.mMonitorGps = z;
            return this;
        }

        public Builder setMonitorBluetoothLE(boolean z) {
            this.mMonitorBle = z;
            return this;
        }

        public Builder setMonitorCell(boolean z) {
            this.mMonitorCell = z;
            return this;
        }

        public Builder setMonitorWifi(boolean z) {
            this.mMonitorWifi = z;
            return this;
        }

        public Builder setMonitorNetworkLocation(boolean z) {
            this.mMonitorNetwokLocation = z;
            return this;
        }

        public Builder setAirplaneMode(boolean z) {
            this.mMonitorAirplaneMode = z;
            return this;
        }

        public DeviceMonitor build() {
            return new DeviceMonitor();
        }
    }

    static class CellMonitor extends MonitorBase {
        CellMonitor(Context context, Listener listener) {
            super(context, listener, MonitorType.Cell);
        }

        boolean getState() {
            return HereServicesUtil.isPhoneEnabled(this.mContext);
        }

        void onStart() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
            this.mContext.registerReceiver(this.mReceiver, intentFilter);
        }

        void onStop() {
            this.mContext.unregisterReceiver(this.mReceiver);
        }
    }

    static class GpsMonitor extends MonitorBase {
        GpsMonitor(Context context, Listener listener) {
            super(context, listener, MonitorType.Gps);
        }

        boolean getState() {
            return HereServicesUtil.isGpsLocationEnabled(this.mContext);
        }

        void onStart() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.location.PROVIDERS_CHANGED");
            this.mContext.registerReceiver(this.mReceiver, intentFilter);
        }

        void onStop() {
            this.mContext.unregisterReceiver(this.mReceiver);
        }
    }

    public interface Listener {

        public enum MonitorType {
            BluetoothLE,
            Gps,
            NetworkLocation,
            Cell,
            Wifi,
            AirplaneMode
        }

        void onMonitorStateChanged(MonitorType monitorType, boolean z);

        void onMonitoringStarted(MonitorType monitorType, boolean z);

        void onMonitoringStopped(MonitorType monitorType);
    }

    static class NetworkLocationMonitor extends MonitorBase {
        NetworkLocationMonitor(Context context, Listener listener) {
            super(context, listener, MonitorType.NetworkLocation);
        }

        boolean getState() {
            return HereServicesUtil.isNetworkLocationEnabled(this.mContext);
        }

        @TargetApi(19)
        void onStart() {
            IntentFilter intentFilter = new IntentFilter();
            if (VERSION.SDK_INT < 19) {
                intentFilter.addAction("android.location.PROVIDERS_CHANGED");
            } else {
                intentFilter.addAction("android.location.MODE_CHANGED");
            }
            this.mContext.registerReceiver(this.mReceiver, intentFilter);
        }

        void onStop() {
            this.mContext.unregisterReceiver(this.mReceiver);
        }
    }

    enum State {
        Idle,
        Monitoring
    }

    static class WifiMonitor extends MonitorBase {
        WifiMonitor(Context context, Listener listener) {
            super(context, listener, MonitorType.Wifi);
        }

        boolean getState() {
            return HereServicesUtil.isWifiScanEnabled(this.mContext);
        }

        void onStart() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
            this.mContext.registerReceiver(this.mReceiver, intentFilter);
        }

        void onStop() {
            this.mContext.unregisterReceiver(this.mReceiver);
        }
    }

    private DeviceMonitor(Builder builder) {
        this.mMonitors = new ArrayList();
        this.mState = State.Idle;
        if (builder.mMonitorBle && HereServicesUtil.hasBluetoothLe(builder.mContext)) {
            this.mMonitors.add(new BleMonitor(builder.mContext, builder.mListener));
        }
        if (builder.mMonitorGps && HereServicesUtil.hasGps(builder.mContext)) {
            this.mMonitors.add(new GpsMonitor(builder.mContext, builder.mListener));
        }
        if (builder.mMonitorNetwokLocation) {
            this.mMonitors.add(new NetworkLocationMonitor(builder.mContext, builder.mListener));
        }
        if (builder.mMonitorAirplaneMode) {
            this.mMonitors.add(new AirplaneModeMonitor(builder.mContext, builder.mListener));
        }
        if (builder.mMonitorCell) {
            this.mMonitors.add(new CellMonitor(builder.mContext, builder.mListener));
        }
        if (builder.mMonitorWifi && HereServicesUtil.hasWifi(builder.mContext)) {
            this.mMonitors.add(new WifiMonitor(builder.mContext, builder.mListener));
        }
    }

    public void startMonitoring() {
        if (this.mState == State.Idle) {
            for (Monitor start : this.mMonitors) {
                try {
                    start.start();
                } catch (Exception e) {
                }
            }
            this.mState = State.Monitoring;
        }
    }

    public void stopMonitoring() {
        if (this.mState == State.Monitoring) {
            for (Monitor stop : this.mMonitors) {
                try {
                    stop.stop();
                } catch (Exception e) {
                }
            }
            this.mState = State.Idle;
        }
    }
}
