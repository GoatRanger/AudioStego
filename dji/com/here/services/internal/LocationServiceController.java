package com.here.services.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.here.odnp.posclient.IPosClientManager;
import com.here.odnp.posclient.PosClientManager;
import com.here.odnp.util.DeviceMonitor;
import com.here.odnp.util.DeviceMonitor.Builder;
import com.here.odnp.util.DeviceMonitor.Listener;
import com.here.odnp.util.DeviceMonitor.Listener.MonitorType;
import com.here.odnp.util.OdnpAssetManager.CopyTask;
import com.here.posclient.InitOptions;
import com.here.posclient.PositioningFeature;
import com.here.services.internal.ILocationServiceController.Stub;

public class LocationServiceController extends Stub implements Listener, IBoundService {
    private static final long ASSET_COPY_TASK_MAX_WAIT_TIME = 10000;
    private static final String TAG = "here.services.internal.LocationServiceController";
    private boolean mIsActiveStorageFeatureAllowed;
    private boolean mIsCollectorFeatureAllowed;
    private IBoundService mLocationClientService;
    private IBoundService mLocationTestClientService;
    private IBoundService mMeasurementPlaybackClientService;
    private final DeviceMonitor mMonitor;
    private volatile IPosClientManager mPosClientManager;
    private IBoundService mRadioMapManagerClientService;
    private final LocationService mService;
    private IBoundService mUsageTrackingClientService;

    LocationServiceController(LocationService locationService) {
        this.mService = locationService;
        Builder builder = new Builder(this.mService, this);
        builder.setMonitorNetworkLocation(true);
        this.mMonitor = builder.build();
    }

    LocationServiceController openController(CopyTask copyTask, Bundle bundle) {
        boolean z = true;
        if (!copyTask.waitForCompletion(ASSET_COPY_TASK_MAX_WAIT_TIME)) {
            return null;
        }
        this.mPosClientManager = PosClientManager.open(this.mService, bundle);
        if (bundle == null || !bundle.containsKey(InitOptions.KEY_OPTION_FEATURES)) {
            this.mIsCollectorFeatureAllowed = true;
            this.mIsActiveStorageFeatureAllowed = true;
        } else {
            long j = bundle.getLong(InitOptions.KEY_OPTION_FEATURES, 0);
            this.mIsCollectorFeatureAllowed = (PositioningFeature.Collector.value & j) != 0;
            if ((j & PositioningFeature.ActiveStorage.value) == 0) {
                z = false;
            }
            this.mIsActiveStorageFeatureAllowed = z;
        }
        this.mMonitor.startMonitoring();
        return this;
    }

    public boolean updateOptions(Bundle bundle) throws RemoteException {
        if (this.mPosClientManager == null) {
            return false;
        }
        return this.mPosClientManager.updateOptions(bundle);
    }

    public void unBind() {
        unbindServices();
    }

    public void onMonitoringStarted(MonitorType monitorType, boolean z) {
        handleMonitorState(monitorType, z);
    }

    public void onMonitorStateChanged(MonitorType monitorType, boolean z) {
        handleMonitorState(monitorType, z);
    }

    public void onMonitoringStopped(MonitorType monitorType) {
    }

    IBinder onServiceBind(Intent intent) {
        if (this.mService.isLocationServiceAction(intent)) {
            if (this.mLocationClientService == null) {
                this.mLocationClientService = this.mService.createLocationService(this.mPosClientManager, intent);
            }
            return this.mLocationClientService;
        } else if (this.mService.isRadiomapManagerServiceAction(intent)) {
            if (this.mRadioMapManagerClientService == null) {
                this.mRadioMapManagerClientService = this.mService.createRadioMapManagerService(this.mPosClientManager, intent);
            }
            return this.mRadioMapManagerClientService;
        } else if (this.mService.isMeasurementPlaybackServiceAction(intent)) {
            if (this.mMeasurementPlaybackClientService == null) {
                this.mMeasurementPlaybackClientService = this.mService.createMeasurementPlaybackService(this.mPosClientManager, intent);
            }
            return this.mMeasurementPlaybackClientService;
        } else if (!this.mService.isUsageTrackingServiceAction(intent)) {
            return this.mService.serviceNotAvailable();
        } else {
            if (this.mUsageTrackingClientService == null) {
                this.mUsageTrackingClientService = this.mService.createUsageTrackingService(this.mPosClientManager, intent);
            }
            return this.mUsageTrackingClientService;
        }
    }

    boolean onServiceUnbind(Intent intent) {
        try {
            if (this.mService.isLocationServiceAction(intent)) {
                if (this.mLocationClientService != null) {
                    this.mLocationClientService.unBind();
                    this.mLocationClientService = null;
                }
                checkIfAllBindingsClosed();
            } else if (this.mService.isRadiomapManagerServiceAction(intent)) {
                if (this.mRadioMapManagerClientService != null) {
                    this.mRadioMapManagerClientService.unBind();
                    this.mRadioMapManagerClientService = null;
                }
                checkIfAllBindingsClosed();
            } else if (this.mService.isMeasurementPlaybackServiceAction(intent)) {
                if (this.mMeasurementPlaybackClientService != null) {
                    this.mMeasurementPlaybackClientService.unBind();
                    this.mMeasurementPlaybackClientService = null;
                }
                checkIfAllBindingsClosed();
            } else {
                checkIfAllBindingsClosed();
            }
            return false;
        } catch (Throwable th) {
            checkIfAllBindingsClosed();
        }
    }

    void checkIfAllBindingsClosed() {
        Object obj = (this.mLocationClientService == null && this.mRadioMapManagerClientService == null && this.mLocationTestClientService == null && this.mMeasurementPlaybackClientService == null) ? 1 : null;
        if (obj != null) {
            this.mMonitor.stopMonitoring();
            if (this.mPosClientManager != null) {
                this.mPosClientManager.close();
                this.mPosClientManager = null;
            }
            this.mService.onAllBindingsClosed();
        }
    }

    private void unbindServices() {
        try {
            if (this.mLocationClientService != null) {
                this.mLocationClientService.unBind();
                this.mLocationClientService = null;
            }
            if (this.mRadioMapManagerClientService != null) {
                this.mRadioMapManagerClientService.unBind();
                this.mRadioMapManagerClientService = null;
            }
            if (this.mLocationTestClientService != null) {
                this.mLocationTestClientService.unBind();
                this.mLocationTestClientService = null;
            }
            if (this.mMeasurementPlaybackClientService != null) {
                this.mMeasurementPlaybackClientService.unBind();
                this.mMeasurementPlaybackClientService = null;
            }
            checkIfAllBindingsClosed();
        } catch (Throwable th) {
            checkIfAllBindingsClosed();
        }
    }

    private void handleMonitorState(MonitorType monitorType, boolean z) {
        boolean z2 = true;
        if (this.mPosClientManager != null) {
            switch (monitorType) {
                case NetworkLocation:
                    boolean z3;
                    IPosClientManager iPosClientManager = this.mPosClientManager;
                    PositioningFeature positioningFeature = PositioningFeature.Collector;
                    if (z && this.mIsCollectorFeatureAllowed) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    iPosClientManager.toggleFeature(positioningFeature, z3);
                    IPosClientManager iPosClientManager2 = this.mPosClientManager;
                    PositioningFeature positioningFeature2 = PositioningFeature.ActiveStorage;
                    if (!(z && this.mIsActiveStorageFeatureAllowed)) {
                        z2 = false;
                    }
                    iPosClientManager2.toggleFeature(positioningFeature2, z2);
                    return;
                default:
                    return;
            }
        }
    }
}
