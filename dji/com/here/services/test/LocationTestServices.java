package com.here.services.test;

import android.content.Context;
import com.here.services.Api;
import com.here.services.Api.Options;
import com.here.services.Api.Options.None;
import com.here.services.HereLocationApiClient;
import com.here.services.internal.ServiceController;
import com.here.services.internal.ServiceController.Provider;
import com.here.services.internal.ServiceNotFoundException;
import com.here.services.test.fingerprint.FingerprintCollectionTestApi;
import com.here.services.test.fingerprint.FingerprintCollectionTestProvider;
import com.here.services.test.internal.ILocationTest;
import com.here.services.test.internal.LocationTestFactory;
import com.here.services.test.internal.LocationTestServicesController;
import com.here.services.test.location.LocationTestApi;
import com.here.services.test.location.LocationTestTestProvider;

public class LocationTestServices {
    public static final Api<None> API = new Api<None>() {
        public ServiceController createController(Context context, Options options) {
            try {
                ILocationTest open = LocationTestFactory.open(context);
                if (open == null) {
                    throw new ServiceNotFoundException("LocationTestFactory.open: returned null");
                }
                ServiceController locationTestServicesController = new LocationTestServicesController(open);
                LocationTestServices.LocationTest = new LocationTestTestProvider(context, new Provider<LocationTestServicesController>() {
                    public LocationTestServicesController getController(HereLocationApiClient hereLocationApiClient) {
                        try {
                            return (LocationTestServicesController) hereLocationApiClient.getServiceController(LocationTestServices.API);
                        } catch (Exception e) {
                            return null;
                        }
                    }
                });
                LocationTestServices.FingerprintCollectionTest = new FingerprintCollectionTestProvider(context, new Provider<LocationTestServicesController>() {
                    public LocationTestServicesController getController(HereLocationApiClient hereLocationApiClient) {
                        try {
                            return (LocationTestServicesController) hereLocationApiClient.getServiceController(LocationTestServices.API);
                        } catch (Exception e) {
                            return null;
                        }
                    }
                });
                return locationTestServicesController;
            } catch (Exception e) {
                return null;
            }
        }
    };
    public static FingerprintCollectionTestApi FingerprintCollectionTest = null;
    public static LocationTestApi LocationTest = null;
    private static final String TAG = "services.test.LocationTestServices";
}
