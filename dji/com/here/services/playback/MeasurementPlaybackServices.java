package com.here.services.playback;

import android.content.Context;
import com.here.services.Api;
import com.here.services.Api.Options;
import com.here.services.Api.Options.None;
import com.here.services.HereLocationApiClient;
import com.here.services.internal.ServiceController;
import com.here.services.internal.ServiceController.Provider;
import com.here.services.playback.internal.MeasurementPlaybackClient;
import com.here.services.playback.internal.MeasurementPlaybackServicesController;

public class MeasurementPlaybackServices {
    public static final Api<None> API = new Api<None>() {
        public ServiceController createController(Context context, Options options) {
            try {
                MeasurementPlaybackClient measurementPlaybackClient = new MeasurementPlaybackClient(context);
                if (measurementPlaybackClient.initialize()) {
                    ServiceController measurementPlaybackServicesController = new MeasurementPlaybackServicesController(measurementPlaybackClient);
                    MeasurementPlaybackServices.MeasurementPlayback = new MeasurementPlaybackProvider(context, new Provider<MeasurementPlaybackServicesController>() {
                        public MeasurementPlaybackServicesController getController(HereLocationApiClient hereLocationApiClient) {
                            try {
                                return (MeasurementPlaybackServicesController) hereLocationApiClient.getServiceController(MeasurementPlaybackServices.API);
                            } catch (Exception e) {
                                return null;
                            }
                        }
                    });
                    return measurementPlaybackServicesController;
                }
                throw new Exception("MeasurementPlaybackClient initialization failed");
            } catch (Exception e) {
                return null;
            }
        }
    };
    public static MeasurementPlaybackApi MeasurementPlayback = null;
    private static final String TAG = "services.playback.MeasurementPlaybackServices";
}
