package com.here.services.location;

import android.content.Context;
import com.here.services.Api;
import com.here.services.Api.Options;
import com.here.services.Api.Options.None;
import com.here.services.HereLocationApiClient;
import com.here.services.internal.ServiceController;
import com.here.services.internal.ServiceController.Provider;
import com.here.services.location.highaccuracy.HighAccuracyLocationApi;
import com.here.services.location.highaccuracy.HighAccuracyLocationProvider;
import com.here.services.location.hybrid.HybridLocationApi;
import com.here.services.location.hybrid.HybridLocationProvider;
import com.here.services.location.internal.IPositioning;
import com.here.services.location.internal.LocationServicesController;
import com.here.services.location.internal.Positioning;
import com.here.services.location.network.NetworkLocationApi;
import com.here.services.location.network.NetworkLocationProvider;

public class LocationServices {
    public static final Api<None> API = new Api<None>() {
        public ServiceController createController(Context context, Options options) {
            IPositioning open = Positioning.open(context);
            if (open == null) {
                return null;
            }
            ServiceController locationServicesController = new LocationServicesController(open);
            LocationServices.HybridLocationProvider = new HybridLocationProvider(context, new Provider<LocationServicesController>() {
                public LocationServicesController getController(HereLocationApiClient hereLocationApiClient) {
                    try {
                        return (LocationServicesController) hereLocationApiClient.getServiceController(LocationServices.API);
                    } catch (Exception e) {
                        return null;
                    }
                }
            });
            LocationServices.NetworkLocationProvider = new NetworkLocationProvider(context, new Provider<LocationServicesController>() {
                public LocationServicesController getController(HereLocationApiClient hereLocationApiClient) {
                    try {
                        return (LocationServicesController) hereLocationApiClient.getServiceController(LocationServices.API);
                    } catch (Exception e) {
                        return null;
                    }
                }
            });
            LocationServices.HighAccuracyLocationProvider = new HighAccuracyLocationProvider(context, new Provider<LocationServicesController>() {
                public LocationServicesController getController(HereLocationApiClient hereLocationApiClient) {
                    try {
                        return (LocationServicesController) hereLocationApiClient.getServiceController(LocationServices.API);
                    } catch (Exception e) {
                        return null;
                    }
                }
            });
            return locationServicesController;
        }
    };
    public static HighAccuracyLocationApi HighAccuracyLocationProvider = null;
    public static HybridLocationApi HybridLocationProvider = null;
    public static NetworkLocationApi NetworkLocationProvider = null;
    private static final String TAG = "services.location.LocationServices";
}
