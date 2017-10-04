package com.here.services.radiomap.internal;

import com.here.posclient.RadioMapManager.IRadioMapStorageActionListener;
import com.here.posclient.RadioMapManager.RadioMapQueryAction;
import com.here.posclient.RadioMapManager.RadioMapStorageAction;
import com.here.services.radiomap.common.GeoArea;

public interface IRmmPosClientManager {
    void close();

    boolean startRadioMapQuery(RadioMapQueryAction radioMapQueryAction, long j, GeoArea[] geoAreaArr, int i, IRadioMapStorageActionListener iRadioMapStorageActionListener);

    boolean startRadioMapUpdate(RadioMapStorageAction radioMapStorageAction, long j, GeoArea[] geoAreaArr, int i, IRadioMapStorageActionListener iRadioMapStorageActionListener);

    void stopRadioMapActions(IRadioMapStorageActionListener iRadioMapStorageActionListener);
}
