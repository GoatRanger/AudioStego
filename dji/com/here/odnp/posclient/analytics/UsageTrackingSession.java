package com.here.odnp.posclient.analytics;

import com.here.odnp.posclient.CloseableSession;
import com.here.odnp.posclient.PosClientManager;

public abstract class UsageTrackingSession extends CloseableSession implements IUsageTrackingSession {
    private static final String TAG = "odnp.posclient.analytics.UsageTrackingSession";

    public UsageTrackingSession(PosClientManager posClientManager) {
        super(posClientManager);
    }

    protected void onOpen() {
        this.mPosClientManager.addUsageTrackingSession(this);
    }

    protected void onClose() {
        if (!this.mPosClientManager.removeUsageTrackingSession(this)) {
        }
    }
}
