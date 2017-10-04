package com.here.odnp.posclient;

import android.content.Context;
import android.os.Bundle;
import com.here.odnp.posclient.analytics.IUsageTrackingSession;
import com.here.odnp.posclient.pos.IPositioningSession;
import com.here.odnp.posclient.pos.IPositioningSession.ILocationListener;
import com.here.odnp.posclient.rmm.IRmDownloadSession;
import com.here.odnp.posclient.test.IPosClientTesterSession;
import com.here.posclient.ClientConfiguration;
import com.here.posclient.PositioningFeature;
import com.here.posclient.RadioMapManager.IRadioMapStorageActionListener;

public interface IPosClientManager {
    void close();

    IPositioningSession createPositionerSession(ILocationListener iLocationListener);

    IRmDownloadSession createRmDownloaderSession(IRadioMapStorageActionListener iRadioMapStorageActionListener);

    IPosClientTesterSession createTesterSession();

    IUsageTrackingSession createUsageTrackingSession();

    ClientConfiguration getClientConfiguration();

    Context getContext();

    void toggleFeature(PositioningFeature positioningFeature, boolean z);

    boolean updateOptions(Bundle bundle);
}
