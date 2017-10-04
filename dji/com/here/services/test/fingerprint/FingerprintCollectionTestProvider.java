package com.here.services.test.fingerprint;

import android.content.Context;
import com.here.services.HereLocationApiClient;
import com.here.services.internal.ServiceController.Provider;
import com.here.services.test.internal.ILocationTest;
import com.here.services.test.internal.LocationTestServicesController;

public class FingerprintCollectionTestProvider implements FingerprintCollectionTestApi {
    private static final String TAG = "services.test.fingerprint.FingerprintCollectionTestProvider";
    final Provider<LocationTestServicesController> mProvider;

    public FingerprintCollectionTestProvider(Context context, Provider<LocationTestServicesController> provider) {
        this.mProvider = provider;
    }

    public void setUsername(HereLocationApiClient hereLocationApiClient, String str) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest != null) {
            positioningTest.setUsername(str);
        }
    }

    public boolean getCollectionStatus(HereLocationApiClient hereLocationApiClient) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest == null) {
            return false;
        }
        return positioningTest.getCollectionStatus();
    }

    public void sendFingerprints(HereLocationApiClient hereLocationApiClient) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest != null) {
            positioningTest.sendFingerprints();
        }
    }

    public void dumpFingerprints(HereLocationApiClient hereLocationApiClient) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest != null) {
            positioningTest.dumpFingerprints();
        }
    }

    public boolean getActiveCollection(HereLocationApiClient hereLocationApiClient) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest == null) {
            return false;
        }
        return positioningTest.getActiveCollection();
    }

    public boolean setActiveCollection(HereLocationApiClient hereLocationApiClient, boolean z) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest == null) {
            return false;
        }
        return positioningTest.setActiveCollection(z);
    }

    public boolean getAutoUpload(HereLocationApiClient hereLocationApiClient) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest == null) {
            return false;
        }
        return positioningTest.getAutoUpload();
    }

    public boolean setAutoUpload(HereLocationApiClient hereLocationApiClient, boolean z) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest == null) {
            return false;
        }
        return positioningTest.setAutoUpload(z);
    }

    public long getGnssFingerprintCount(HereLocationApiClient hereLocationApiClient) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest == null) {
            return 0;
        }
        return positioningTest.getGnssFingerprintCount();
    }

    public long getNonGnssFingerprintCount(HereLocationApiClient hereLocationApiClient) {
        ILocationTest positioningTest = getPositioningTest(hereLocationApiClient);
        if (positioningTest == null) {
            return 0;
        }
        return positioningTest.getNonGnssFingerprintCount();
    }

    private ILocationTest getPositioningTest(HereLocationApiClient hereLocationApiClient) {
        LocationTestServicesController locationTestServicesController = (LocationTestServicesController) this.mProvider.getController(hereLocationApiClient);
        if (locationTestServicesController == null) {
            return null;
        }
        return locationTestServicesController.getLocationTest();
    }
}
