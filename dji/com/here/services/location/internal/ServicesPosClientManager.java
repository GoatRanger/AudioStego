package com.here.services.location.internal;

import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import com.here.odnp.posclient.IPosClientManager;
import com.here.odnp.posclient.pos.IPositioningSession;
import com.here.odnp.posclient.pos.IPositioningSession.ILocationListener;
import com.here.odnp.util.DeviceMonitor;
import com.here.odnp.util.DeviceMonitor.Builder;
import com.here.odnp.util.DeviceMonitor.Listener;
import com.here.odnp.util.DeviceMonitor.Listener.MonitorType;
import com.here.posclient.ClientConfiguration;
import com.here.posclient.PositioningFeature;
import com.here.posclient.Status;
import com.here.posclient.UpdateOptions;
import com.here.services.common.Types.Technology;
import com.here.services.location.util.LocationHelper;
import com.here.services.util.HereServicesUtil;

public class ServicesPosClientManager implements ISdkPosClientManager {
    private static final long MONITOR_CHANGE_BATCH_DELAY = 1000;
    private static final String TAG = "services.location.internal.ServicesPosClientManager";
    private final ListenerProxy mListenerProxy;
    private final IPosClientManager mPosClientManager;
    private IPositioningSession mPositioning;

    class ListenerProxy implements ILocationListener, Listener {
        UpdateOptions mEffectiveOptions;
        final Handler mHandler = new Handler(Looper.getMainLooper());
        final ILocationListener mListener;
        DeviceMonitor mMonitor;
        Runnable mOptionsChangedTask = new OptionsChangedTask();
        UpdateOptions mReducedRequestedOptions;
        UpdateOptions mRequestedOptions;

        class OptionsChangedTask implements Runnable {
            UpdateOptions mPreviousEffectiveOptions;
            UpdateOptions mPreviousReducedRequestOptions;

            OptionsChangedTask() {
            }

            public void run() {
                if (!ListenerProxy.this.mEffectiveOptions.isEqual(this.mPreviousEffectiveOptions)) {
                    this.mPreviousEffectiveOptions = ListenerProxy.this.mEffectiveOptions.clone();
                    ListenerProxy.this.onOptionsChanged(ListenerProxy.this.mRequestedOptions, ListenerProxy.this.mEffectiveOptions);
                }
                if (!ListenerProxy.this.mReducedRequestedOptions.isEqual(this.mPreviousReducedRequestOptions)) {
                    this.mPreviousReducedRequestOptions = ListenerProxy.this.mReducedRequestedOptions.clone();
                    synchronized (ServicesPosClientManager.this) {
                        if (ServicesPosClientManager.this.mPositioning != null) {
                            ServicesPosClientManager.this.mPositioning.setUpdateOptions(ListenerProxy.this.mReducedRequestedOptions);
                        }
                    }
                }
            }
        }

        ListenerProxy(ILocationListener iLocationListener) {
            this.mListener = iLocationListener;
        }

        void setRequestedOptions(UpdateOptions updateOptions) {
            stopMonitoring();
            this.mOptionsChangedTask = new OptionsChangedTask();
            this.mRequestedOptions = updateOptions.clone();
            this.mReducedRequestedOptions = buildReducedOptions(this.mRequestedOptions);
            this.mEffectiveOptions = buildEffectiveOptions(this.mRequestedOptions);
            this.mMonitor = createDeviceMonitor(this.mRequestedOptions);
            startMonitoring();
            if (!this.mRequestedOptions.isEqual(this.mEffectiveOptions)) {
                onOptionsChanged(this.mRequestedOptions, this.mEffectiveOptions);
            }
        }

        void close() {
            this.mHandler.removeCallbacks(this.mOptionsChangedTask);
            this.mOptionsChangedTask = null;
            stopMonitoring();
        }

        public void onLocationChanged(Location location) {
            this.mListener.onLocationChanged(location);
        }

        public void onOptionsChanged(UpdateOptions updateOptions, UpdateOptions updateOptions2) {
            this.mListener.onOptionsChanged(updateOptions, updateOptions2);
        }

        public void onLocationResolvingFailed(Status status) {
        }

        public void onClosed() {
            synchronized (ServicesPosClientManager.this) {
                ServicesPosClientManager.this.mPositioning = null;
            }
            this.mListener.onClosed();
        }

        public void onMonitoringStarted(MonitorType monitorType, boolean z) {
        }

        public void onMonitorStateChanged(MonitorType monitorType, boolean z) {
            UpdateOptions buildEffectiveOptions = buildEffectiveOptions(this.mRequestedOptions);
            UpdateOptions buildReducedOptions = buildReducedOptions(this.mRequestedOptions);
            if (!buildEffectiveOptions.isEqual(this.mEffectiveOptions) || !buildReducedOptions.isEqual(this.mReducedRequestedOptions)) {
                this.mHandler.removeCallbacks(this.mOptionsChangedTask);
                this.mEffectiveOptions = buildEffectiveOptions;
                this.mReducedRequestedOptions = buildReducedOptions;
                this.mHandler.postDelayed(this.mOptionsChangedTask, 1000);
            }
        }

        public void onMonitoringStopped(MonitorType monitorType) {
        }

        private DeviceMonitor createDeviceMonitor(UpdateOptions updateOptions) {
            Builder builder = new Builder(ServicesPosClientManager.this.mPosClientManager.getContext(), this);
            if (updateOptions.isSourceAllowed(8) || updateOptions.isSourceAllowed(2) || updateOptions.isSourceAllowed(4) || updateOptions.isSourceAllowed(16)) {
                builder.setMonitorNetworkLocation(true);
                builder.setMonitorWifi(updateOptions.isTechnologyAllowed(2));
            }
            if (updateOptions.isSourceAllowed(2) || updateOptions.isSourceAllowed(4) || updateOptions.isSourceAllowed(16)) {
                builder.setMonitorCell(updateOptions.isTechnologyAllowed(12));
            }
            if (updateOptions.isSourceAllowed(8)) {
                builder.setMonitorBluetoothLE(updateOptions.isTechnologyAllowed(16384));
            }
            if (updateOptions.isSourceAllowed(64)) {
                builder.setMonitorGps(updateOptions.isTechnologyAllowed(1));
            }
            return builder.build();
        }

        private void startMonitoring() {
            if (this.mMonitor != null) {
                this.mMonitor.startMonitoring();
            }
        }

        private void stopMonitoring() {
            if (this.mMonitor != null) {
                this.mMonitor.stopMonitoring();
                this.mMonitor = null;
            }
        }

        private UpdateOptions buildEffectiveOptions(UpdateOptions updateOptions) {
            UpdateOptions clone = updateOptions.clone();
            if (!HereServicesUtil.isNetworkLocationEnabled(ServicesPosClientManager.this.mPosClientManager.getContext())) {
                clone.disableSources(30);
                clone.disableOptions(15);
            }
            if (!HereServicesUtil.isGpsLocationEnabled(ServicesPosClientManager.this.mPosClientManager.getContext())) {
                clone.disableSources(64);
                clone.disableTechnologies(1);
            }
            if (!HereServicesUtil.isBluetoothLeEnabled(ServicesPosClientManager.this.mPosClientManager.getContext())) {
                clone.disableTechnologies(16384);
            }
            if (!HereServicesUtil.isPhoneEnabled(ServicesPosClientManager.this.mPosClientManager.getContext())) {
                clone.disableTechnologies(12);
            }
            if (!HereServicesUtil.isWifiScanEnabled(ServicesPosClientManager.this.mPosClientManager.getContext())) {
                clone.disableTechnologies(2);
            }
            return clone;
        }

        private UpdateOptions buildReducedOptions(UpdateOptions updateOptions) {
            UpdateOptions clone = updateOptions.clone();
            if (!HereServicesUtil.isNetworkLocationEnabled(ServicesPosClientManager.this.mPosClientManager.getContext())) {
                clone.disableSources(30);
                clone.disableOptions(15);
            }
            return clone;
        }

        UpdateOptions getEffectiveOptions() {
            return this.mEffectiveOptions;
        }

        UpdateOptions getRequestedOptions() {
            return this.mReducedRequestedOptions;
        }
    }

    private ServicesPosClientManager(IPosClientManager iPosClientManager, ILocationListener iLocationListener) {
        this.mListenerProxy = new ListenerProxy(iLocationListener);
        this.mPosClientManager = iPosClientManager;
    }

    public static ISdkPosClientManager create(IPosClientManager iPosClientManager, ILocationListener iLocationListener) {
        return new ServicesPosClientManager(iPosClientManager, iLocationListener);
    }

    public synchronized boolean startLocationUpdates(UpdateOptions updateOptions) {
        boolean z;
        if (this.mPositioning == null) {
            this.mPositioning = createOpenedPositioningSession();
            if (this.mPositioning == null) {
                z = false;
            }
        }
        this.mListenerProxy.setRequestedOptions(cleanUpdateOptions(updateOptions));
        this.mPositioning.setUpdateOptions(this.mListenerProxy.getRequestedOptions());
        z = this.mPositioning.startPositionUpdates();
        return z;
    }

    public synchronized void stopLocationUpdates() {
        if (this.mPositioning != null) {
            this.mListenerProxy.close();
            this.mPositioning.stopPositionUpdates();
            this.mPositioning.setUpdateOptions(new UpdateOptions().setDisabledPowerOptions());
            this.mPositioning.close();
            this.mPositioning = null;
        }
    }

    public synchronized Location getLastPosition() {
        Location location = null;
        synchronized (this) {
            Location lastPosition;
            if (this.mPositioning == null) {
                IPositioningSession createOpenedPositioningSession = createOpenedPositioningSession();
                if (createOpenedPositioningSession != null) {
                    try {
                        lastPosition = createOpenedPositioningSession.getLastPosition();
                        createOpenedPositioningSession.close();
                    } catch (Throwable th) {
                        createOpenedPositioningSession.close();
                    }
                } else {
                    lastPosition = null;
                }
            } else {
                lastPosition = this.mPositioning.getLastPosition();
            }
            if (isValidLocation(lastPosition)) {
                location = lastPosition;
            }
        }
        return location;
    }

    public synchronized void positioningConsentRevoked() {
        if (this.mPositioning != null) {
            this.mPositioning.positioningConsentRevoked();
            this.mPositioning = null;
        }
    }

    IPositioningSession createOpenedPositioningSession() {
        IPositioningSession createPositionerSession = this.mPosClientManager.createPositionerSession(this.mListenerProxy);
        if (createPositionerSession == null || createPositionerSession.open()) {
            return createPositionerSession;
        }
        return null;
    }

    boolean isValidLocation(Location location) {
        if (location == null) {
            return false;
        }
        boolean contains = LocationHelper.getTechnologies(location).contains(Technology.Gnss);
        boolean isNetworkLocationEnabled = HereServicesUtil.isNetworkLocationEnabled(this.mPosClientManager.getContext());
        boolean isGpsLocationEnabled = HereServicesUtil.isGpsLocationEnabled(this.mPosClientManager.getContext());
        if (!isGpsLocationEnabled && !isNetworkLocationEnabled) {
            return false;
        }
        if (isNetworkLocationEnabled && !contains) {
            return true;
        }
        if (isGpsLocationEnabled && contains) {
            return true;
        }
        return false;
    }

    UpdateOptions cleanUpdateOptions(UpdateOptions updateOptions) {
        ClientConfiguration clientConfiguration = this.mPosClientManager.getClientConfiguration();
        if (!(clientConfiguration == null || updateOptions == null)) {
            if (!(PositioningFeature.isFeatureSet(clientConfiguration.features, PositioningFeature.HighAccuracy) || PositioningFeature.isFeatureSet(clientConfiguration.features, PositioningFeature.HighAccuracyCustom))) {
                updateOptions.disableSources(8);
                updateOptions.disableTechnologies(16384);
            }
            if (!PositioningFeature.isFeatureSet(clientConfiguration.features, PositioningFeature.Online)) {
                updateOptions.disableSources(2);
            }
            if (!PositioningFeature.isFeatureSet(clientConfiguration.features, PositioningFeature.Offline)) {
                updateOptions.disableSources(4);
            }
            if (!PositioningFeature.isFeatureSet(clientConfiguration.features, PositioningFeature.Cache)) {
                updateOptions.disableSources(16);
            }
        }
        return updateOptions;
    }
}
