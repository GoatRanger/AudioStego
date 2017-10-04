package com.here.odnp.posclient.rmm;

import com.here.odnp.posclient.CloseableSession;
import com.here.odnp.posclient.PosClientManager;
import com.here.posclient.RadioMapManager.IRadioMapStorageActionListener;
import com.here.posclient.RadioMapManager.RadioMapQueryAction;
import com.here.posclient.RadioMapManager.RadioMapStorageAction;
import com.here.services.radiomap.common.GeoArea;

public class RmDownloadSession extends CloseableSession implements IRmDownloadSession {
    private static final String TAG = "odnp.posclient.RmDownloadSession";
    private final IRadioMapStorageActionListener mListener;
    private boolean mStarted;

    public RmDownloadSession(PosClientManager posClientManager, IRadioMapStorageActionListener iRadioMapStorageActionListener) {
        super(posClientManager);
        if (iRadioMapStorageActionListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.mListener = iRadioMapStorageActionListener;
    }

    protected void onOpen() {
        this.mPosClientManager.addRmDownloader(this);
    }

    protected void onClose() {
        if (this.mPosClientManager.removeRmDownloader(this)) {
            this.mListener.onClosed();
        } else {
            this.mListener.onClosed();
        }
    }

    public boolean updateRadioMapCoverage(RadioMapStorageAction radioMapStorageAction, long j, GeoArea[] geoAreaArr, int i) {
        stopRadioMapUpdate();
        this.mStarted = this.mPosClientManager.onUpdateRadioMapCoverage(radioMapStorageAction, j, geoAreaArr, i, this.mListener);
        return this.mStarted;
    }

    public boolean startRadioMapQuery(RadioMapQueryAction radioMapQueryAction, long j, GeoArea[] geoAreaArr, int i) {
        stopRadioMapUpdate();
        this.mStarted = this.mPosClientManager.onStartRadioMapQuery(radioMapQueryAction, j, geoAreaArr, i, this.mListener);
        return this.mStarted;
    }

    public void stopRadioMapUpdate() {
        if (this.mStarted) {
            this.mStarted = false;
            this.mPosClientManager.onStopRadioMapUpdate(this.mListener);
        }
    }
}
