package com.here.services.radiomap.internal;

import com.here.odnp.posclient.IPosClientManager;
import com.here.odnp.posclient.rmm.IRmDownloadSession;
import com.here.posclient.RadioMapManager.IRadioMapStorageActionListener;
import com.here.posclient.RadioMapManager.RadioMapQueryAction;
import com.here.posclient.RadioMapManager.RadioMapStorageAction;
import com.here.services.radiomap.common.GeoArea;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RmmPosClientManager implements IRmmPosClientManager {
    private static final String TAG = "services.radiomap.internal.RmmPosClientManager";
    private final IPosClientManager mPosClientManager;
    private final Map<IRadioMapStorageActionListener, IRmDownloadSession> mRmDownloaders = new HashMap();

    private RmmPosClientManager(IPosClientManager iPosClientManager) {
        this.mPosClientManager = iPosClientManager;
    }

    public static IRmmPosClientManager create(IPosClientManager iPosClientManager) {
        return new RmmPosClientManager(iPosClientManager);
    }

    public boolean startRadioMapUpdate(RadioMapStorageAction radioMapStorageAction, long j, GeoArea[] geoAreaArr, int i, IRadioMapStorageActionListener iRadioMapStorageActionListener) {
        IRmDownloadSession rmDownload = getRmDownload(iRadioMapStorageActionListener, true);
        if (!rmDownload.open()) {
            releaseRmDownload(iRadioMapStorageActionListener);
            return false;
        } else if (rmDownload.updateRadioMapCoverage(radioMapStorageAction, j, geoAreaArr, i)) {
            return true;
        } else {
            releaseRmDownload(iRadioMapStorageActionListener);
            return false;
        }
    }

    public boolean startRadioMapQuery(RadioMapQueryAction radioMapQueryAction, long j, GeoArea[] geoAreaArr, int i, IRadioMapStorageActionListener iRadioMapStorageActionListener) {
        IRmDownloadSession rmDownload = getRmDownload(iRadioMapStorageActionListener, true);
        if (!rmDownload.open()) {
            releaseRmDownload(iRadioMapStorageActionListener);
            return false;
        } else if (rmDownload.startRadioMapQuery(radioMapQueryAction, j, geoAreaArr, i)) {
            return true;
        } else {
            releaseRmDownload(iRadioMapStorageActionListener);
            return false;
        }
    }

    public void stopRadioMapActions(IRadioMapStorageActionListener iRadioMapStorageActionListener) {
        IRmDownloadSession rmDownload = getRmDownload(iRadioMapStorageActionListener, false);
        if (rmDownload != null) {
            rmDownload.stopRadioMapUpdate();
            releaseRmDownload(iRadioMapStorageActionListener);
        }
    }

    public void close() {
        for (Entry value : this.mRmDownloaders.entrySet()) {
            ((IRmDownloadSession) value.getValue()).close();
        }
        this.mRmDownloaders.clear();
    }

    private IRmDownloadSession getRmDownload(IRadioMapStorageActionListener iRadioMapStorageActionListener, boolean z) {
        IRmDownloadSession iRmDownloadSession = (IRmDownloadSession) this.mRmDownloaders.get(iRadioMapStorageActionListener);
        if (iRmDownloadSession != null || !z) {
            return iRmDownloadSession;
        }
        iRmDownloadSession = this.mPosClientManager.createRmDownloaderSession(iRadioMapStorageActionListener);
        this.mRmDownloaders.put(iRadioMapStorageActionListener, iRmDownloadSession);
        return iRmDownloadSession;
    }

    private void releaseRmDownload(IRadioMapStorageActionListener iRadioMapStorageActionListener) {
        IRmDownloadSession iRmDownloadSession = (IRmDownloadSession) this.mRmDownloaders.remove(iRadioMapStorageActionListener);
        if (iRmDownloadSession != null) {
            iRmDownloadSession.close();
        }
    }
}
