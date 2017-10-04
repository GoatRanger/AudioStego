package com.here.services.radiomap;

import android.content.Context;
import com.here.services.Api;
import com.here.services.Api.Options;
import com.here.services.Api.Options.None;
import com.here.services.HereLocationApiClient;
import com.here.services.internal.ServiceController;
import com.here.services.internal.ServiceController.Provider;
import com.here.services.internal.ServiceNotFoundException;
import com.here.services.radiomap.internal.IRadioMapManager;
import com.here.services.radiomap.internal.RadioMapManager;
import com.here.services.radiomap.internal.RadioMapServicesController;
import com.here.services.radiomap.manager.RadioMapManagerApi;
import com.here.services.radiomap.manager.RadioMapManagerProvider;

public class RadioMapServices {
    public static final Api<None> API = new Api<None>() {
        public ServiceController createController(Context context, Options options) {
            try {
                IRadioMapManager open = RadioMapManager.open(context);
                if (open == null) {
                    throw new ServiceNotFoundException();
                }
                ServiceController radioMapServicesController = new RadioMapServicesController(open);
                RadioMapServices.RadioMapManager = new RadioMapManagerProvider(context, new Provider<RadioMapServicesController>() {
                    public RadioMapServicesController getController(HereLocationApiClient hereLocationApiClient) {
                        try {
                            return (RadioMapServicesController) hereLocationApiClient.getServiceController(RadioMapServices.API);
                        } catch (Exception e) {
                            return null;
                        }
                    }
                });
                return radioMapServicesController;
            } catch (ServiceNotFoundException e) {
                return null;
            }
        }
    };
    public static RadioMapManagerApi RadioMapManager = null;
    private static final String TAG = "services.radiomap.RadioMapServices";
}
