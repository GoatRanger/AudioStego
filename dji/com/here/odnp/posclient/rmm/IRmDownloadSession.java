package com.here.odnp.posclient.rmm;

import com.here.odnp.posclient.ICloseableSession;
import com.here.posclient.RadioMapManager.RadioMapQueryAction;
import com.here.posclient.RadioMapManager.RadioMapStorageAction;
import com.here.services.radiomap.common.GeoArea;

public interface IRmDownloadSession extends ICloseableSession {
    boolean startRadioMapQuery(RadioMapQueryAction radioMapQueryAction, long j, GeoArea[] geoAreaArr, int i);

    void stopRadioMapUpdate();

    boolean updateRadioMapCoverage(RadioMapStorageAction radioMapStorageAction, long j, GeoArea[] geoAreaArr, int i);
}
