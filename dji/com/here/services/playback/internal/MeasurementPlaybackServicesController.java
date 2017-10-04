package com.here.services.playback.internal;

import com.here.services.internal.Manager;
import com.here.services.internal.ServiceController;
import com.here.services.internal.ServiceController.ConnectionListener;
import com.here.services.playback.MeasurementPlaybackServices;

public class MeasurementPlaybackServicesController implements ServiceController {
    private final MeasurementPlaybackClient mMeasurementPlaybackClient;

    public MeasurementPlaybackServicesController(MeasurementPlaybackClient measurementPlaybackClient) {
        if (measurementPlaybackClient == null) {
            throw new IllegalArgumentException("client is null");
        }
        this.mMeasurementPlaybackClient = measurementPlaybackClient;
    }

    public void connect(final ConnectionListener connectionListener) {
        this.mMeasurementPlaybackClient.connect(new Manager.ConnectionListener() {
            public void onDisconnected() {
                connectionListener.onServiceDisconnect(MeasurementPlaybackServices.API);
            }

            public void onConnectionFailed() {
                connectionListener.onServiceConnectionFailed(MeasurementPlaybackServices.API);
            }

            public void onConnected() {
                connectionListener.onServiceConnected(MeasurementPlaybackServices.API);
            }
        });
    }

    public void disconnect() {
        this.mMeasurementPlaybackClient.disconnect();
    }

    public MeasurementPlaybackClient getMeasurementPlaybackClient() {
        return this.mMeasurementPlaybackClient;
    }
}
