package com.here.services.positioning.analytics;

import android.content.Context;
import com.here.services.Api;
import com.here.services.Api.Options;
import com.here.services.Api.Options.None;
import com.here.services.HereLocationApiClient;
import com.here.services.internal.ServiceController;
import com.here.services.internal.ServiceController.Provider;
import com.here.services.positioning.analytics.internal.UsageTrackingClient;
import com.here.services.positioning.analytics.internal.UsageTrackingServicesController;

public class UsageTrackingServices {
    public static final Api<None> API = new Api<None>() {
        public ServiceController createController(Context context, Options options) {
            UsageTrackingClient open = UsageTrackingClient.open(context);
            if (open == null) {
                return null;
            }
            ServiceController usageTrackingServicesController = new UsageTrackingServicesController(open);
            UsageTrackingServices.UsageTrackingProvider = new UsageTrackingProvider(context, new Provider<UsageTrackingServicesController>() {
                public UsageTrackingServicesController getController(HereLocationApiClient hereLocationApiClient) {
                    try {
                        return (UsageTrackingServicesController) hereLocationApiClient.getServiceController(UsageTrackingServices.API);
                    } catch (Exception e) {
                        return null;
                    }
                }
            });
            return usageTrackingServicesController;
        }
    };
    private static final String TAG = "services.positioning.UsageTrackingServices";
    public static UsageTrackingApi UsageTrackingProvider;
}
