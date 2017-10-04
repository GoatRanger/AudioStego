package com.here.services.test.location;

import android.content.Context;
import com.here.posclient.ClientConfiguration;
import com.here.posclient.PositioningFeature;
import com.here.services.HereLocationApiClient;
import com.here.services.internal.ServiceController.Provider;
import com.here.services.test.internal.ILocationTest;
import com.here.services.test.internal.LocationTestServicesController;

public class LocationTestTestProvider implements LocationTestApi {
    private static final String TAG = "services.test.location.LocationTestTestProvider";
    final Provider<LocationTestServicesController> mProvider;

    public LocationTestTestProvider(Context context, Provider<LocationTestServicesController> provider) {
        this.mProvider = provider;
    }

    public int availableFeatures(HereLocationApiClient hereLocationApiClient) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest == null) {
            return 0;
        }
        return positioningTest.availableFeatures();
    }

    public void toggleFeature(HereLocationApiClient hereLocationApiClient, PositioningFeature positioningFeature, boolean z) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest != null) {
            positioningTest.toggleFeature(positioningFeature, z);
        }
    }

    public void toggleTechnology(HereLocationApiClient hereLocationApiClient, int i, boolean z) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest != null) {
            positioningTest.toggleTechnology(i, z);
        }
    }

    public int enabledTechnologies(HereLocationApiClient hereLocationApiClient) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest == null) {
            return 0;
        }
        return positioningTest.enabledTechnologies();
    }

    public void clearData(HereLocationApiClient hereLocationApiClient, int i) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest != null) {
            positioningTest.clearData(i);
        }
    }

    public void dumpData(HereLocationApiClient hereLocationApiClient) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest != null) {
            positioningTest.dumpData();
        }
    }

    public void dumpCachedData(HereLocationApiClient hereLocationApiClient) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest != null) {
            positioningTest.dumpCachedData();
        }
    }

    public void dumpHeapData(HereLocationApiClient hereLocationApiClient) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest != null) {
            positioningTest.dumpHeapData();
        }
    }

    public ClientConfiguration getClientConfiguration(HereLocationApiClient hereLocationApiClient) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest == null) {
            return null;
        }
        return positioningTest.getClientConfiguration();
    }

    private ILocationTest getPositioningTest(HereLocationApiClient hereLocationApiClient) {
        LocationTestServicesController locationTestServicesController = (LocationTestServicesController) this.mProvider.getController(hereLocationApiClient);
        if (locationTestServicesController == null) {
            return null;
        }
        return locationTestServicesController.getLocationTest();
    }
}
