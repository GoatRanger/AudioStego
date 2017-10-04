package com.here.services.playback.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.here.odnp.posclient.IPosClientManager;
import com.here.services.internal.IBoundService;
import com.here.services.playback.internal.IMeasurementPlaybackClient.Stub;

public class MeasurementPlaybackClientService extends Stub implements IBoundService {
    private static final String TAG = "services.playback.internal.MeasurementPlaybackClientService";
    private final PosClientPlaybackManager mPosClientPlaybackManager;

    public MeasurementPlaybackClientService(IPosClientManager iPosClientManager, Intent intent) {
        this.mPosClientPlaybackManager = PosClientPlaybackManager.create(iPosClientManager);
    }

    public boolean startNetworkMeasurementPlayback(Bundle bundle) throws RemoteException {
        return this.mPosClientPlaybackManager.startNetworkMeasurementPlayback(bundle);
    }

    public void stopNetworkMeasurementPlayback() throws RemoteException {
        this.mPosClientPlaybackManager.stopNetworkMeasurementPlayback();
    }

    public void unBind() {
        this.mPosClientPlaybackManager.close();
    }
}
