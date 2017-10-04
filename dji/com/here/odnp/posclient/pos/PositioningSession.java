package com.here.odnp.posclient.pos;

import android.location.Location;
import com.here.odnp.posclient.CloseableSession;
import com.here.odnp.posclient.PosClientManager;
import com.here.odnp.posclient.pos.IPositioningSession.ILocationListener;
import com.here.posclient.Status;
import com.here.posclient.UpdateOptions;

public class PositioningSession extends CloseableSession implements IPositioningSession {
    private static final String TAG = "odnp.posclient.PositioningSession";
    private final ILocationListener mListener;
    private boolean mStarted;

    public PositioningSession(PosClientManager posClientManager, ILocationListener iLocationListener) {
        super(posClientManager);
        if (iLocationListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.mListener = iLocationListener;
    }

    protected void onOpen() {
        this.mPosClientManager.addPositioner(this);
    }

    protected void onClose() {
        if (this.mPosClientManager.removePositioner(this)) {
            this.mListener.onClosed();
        } else {
            this.mListener.onClosed();
        }
    }

    public void locationCalculationFailed(Status status) {
        this.mListener.onLocationResolvingFailed(status);
    }

    public boolean startPositionUpdates() {
        if (!this.mStarted) {
            switch (this.mPosClientManager.onStartPositionUpdates()) {
                case Ok:
                    this.mStarted = true;
                    break;
            }
        }
        return this.mStarted;
    }

    public void stopPositionUpdates() {
        if (this.mStarted) {
            this.mStarted = false;
            this.mPosClientManager.onStopPositionUpdates();
        }
    }

    public void resetMeasurements() {
        this.mPosClientManager.onResetMeasurements();
    }

    public Location getLastPosition() {
        return this.mPosClientManager.onGetLastLocation();
    }

    public void requestLastPosition() {
        this.mPosClientManager.onRequestLastPosition();
    }

    public void requestPosition() {
        this.mPosClientManager.onRequestPosition();
    }

    public UpdateOptions getUpdateOptions() {
        return this.mPosClientManager.onGetUpdateOptions().clone();
    }

    public void setUpdateOptions(UpdateOptions updateOptions) {
        this.mPosClientManager.onSetUpdateOptions(updateOptions.clone());
    }

    public void handleGlobalLocationSettingChanged(boolean z) {
        this.mPosClientManager.onHandleGlobalLocationSettingChanged(z);
    }

    public void onNetworkLocationEnabled() {
        this.mPosClientManager.onNetworkLocationEnabled();
    }

    public void onNetworkLocationDisabled(boolean z) {
        this.mPosClientManager.onNetworkLocationDisabled(z);
    }

    public void setOfflineMode(boolean z) {
        this.mPosClientManager.setOfflineMode(z);
    }

    public boolean isOfflineModeSet() {
        return this.mPosClientManager.isOfflineModeSet();
    }

    public void positioningConsentRevoked() {
        this.mPosClientManager.positioningConsentRevoked();
    }

    public void locationChanged(Location location) {
        this.mListener.onLocationChanged(location);
    }

    public boolean isStarted() {
        return this.mStarted;
    }
}
