package com.here.services.internal;

import android.app.Service;
import android.content.Intent;
import com.here.odnp.posclient.IPosClientManager;
import com.here.services.internal.IServiceNotAvailable.Stub;

public abstract class ServiceBase extends Service {
    private static final String FINGERPRINT_COLLECTION_TEST_SERVICE_CLASS = "com.here.services.test.internal.FingerprintCollectionTestClientService";
    private static final String LOCATION_SERVICE_CLASS = "com.here.services.location.internal.LocationClientService";
    private static final String LOCATION_TEST_SERVICE_CLASS = "com.here.services.test.internal.LocationTestClientService";
    private static final String MEASUREMENT_PLAYBACK_SERVICE_CLASS = "com.here.services.playback.internal.MeasurementPlaybackClientService";
    private static final String RADIOMAP_MANAGER_SERVICE_CLASS = "com.here.services.radiomap.internal.RadioMapManagerClientService";
    private static final String TAG = "here.services.internal.ServiceBase";
    private static final String USAGE_TRACKING_SERVICE_CLASS = "com.here.services.positioning.analytics.internal.UsageTrackingClientService";
    private static final IBoundService mServiceNotAvailable = new ServiceNotAvailable();

    static class ServiceNotAvailable extends Stub implements IBoundService {
        ServiceNotAvailable() {
        }

        public void unBind() {
        }
    }

    protected boolean isLocationServiceControllerAction(Intent intent) {
        return IBoundService.ACTION_BIND_CONTROLLER.equals(intent.getAction());
    }

    protected boolean isLocationServiceAction(Intent intent) {
        return IBoundService.ACTION_BIND_LOCATION_SERVICE.equals(intent.getAction());
    }

    protected IBoundService createLocationService(IPosClientManager iPosClientManager, Intent intent) {
        return createBoundService(iPosClientManager, intent, LOCATION_SERVICE_CLASS);
    }

    protected boolean isRadiomapManagerServiceAction(Intent intent) {
        return IBoundService.ACTION_BIND_RADIOMAP_MANAGER_SERVICE.equals(intent.getAction());
    }

    protected IBoundService createRadioMapManagerService(IPosClientManager iPosClientManager, Intent intent) {
        return createBoundService(iPosClientManager, intent, RADIOMAP_MANAGER_SERVICE_CLASS);
    }

    protected boolean isLocationTestServiceAction(Intent intent) {
        return IBoundService.ACTION_BIND_LOCATION_TEST_SERVICE.equals(intent.getAction());
    }

    protected IBoundService createLocationTestService(IPosClientManager iPosClientManager, Intent intent) {
        return createBoundService(iPosClientManager, intent, LOCATION_TEST_SERVICE_CLASS);
    }

    protected IBoundService createFingerprintCollectionTestService(IPosClientManager iPosClientManager, Intent intent) {
        return createBoundService(iPosClientManager, intent, FINGERPRINT_COLLECTION_TEST_SERVICE_CLASS);
    }

    protected boolean isMeasurementPlaybackServiceAction(Intent intent) {
        return IBoundService.ACTION_BIND_MEASUREMENT_PLAYBACK_SERVICE.equals(intent.getAction());
    }

    protected IBoundService createMeasurementPlaybackService(IPosClientManager iPosClientManager, Intent intent) {
        return createBoundService(iPosClientManager, intent, MEASUREMENT_PLAYBACK_SERVICE_CLASS);
    }

    protected IBoundService serviceNotAvailable() {
        return mServiceNotAvailable;
    }

    protected boolean isUsageTrackingServiceAction(Intent intent) {
        return IBoundService.ACTION_BIND_USAGE_TRACKING_SERVICE.equals(intent.getAction());
    }

    protected IBoundService createUsageTrackingService(IPosClientManager iPosClientManager, Intent intent) {
        return createBoundService(iPosClientManager, intent, USAGE_TRACKING_SERVICE_CLASS);
    }

    private IBoundService createBoundService(IPosClientManager iPosClientManager, Intent intent, String str) {
        try {
            return (IBoundService) Class.forName(str).getConstructor(new Class[]{IPosClientManager.class, Intent.class}).newInstance(new Object[]{iPosClientManager, intent});
        } catch (Exception e) {
            return mServiceNotAvailable;
        }
    }
}
