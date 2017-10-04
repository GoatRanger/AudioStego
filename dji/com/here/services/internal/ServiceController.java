package com.here.services.internal;

import com.here.services.Api;
import com.here.services.Api.Options;
import com.here.services.HereLocationApiClient;

public interface ServiceController {

    public interface ConnectionListener {
        void onServiceConnected(Api<? extends Options> api);

        void onServiceConnectionFailed(Api<? extends Options> api);

        void onServiceDisconnect(Api<? extends Options> api);
    }

    public interface Provider<T> {
        T getController(HereLocationApiClient hereLocationApiClient);
    }

    void connect(ConnectionListener connectionListener);

    void disconnect();
}
