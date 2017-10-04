package com.here.services.positioning.analytics.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.here.odnp.posclient.IPosClientManager;
import com.here.odnp.posclient.analytics.IUsageTrackingSession;
import com.here.posclient.analytics.Tracker;
import com.here.posclient.analytics.UsageTrackingListener;
import com.here.services.internal.IBoundService;
import com.here.services.positioning.analytics.internal.IUsageTrackingClient.Stub;

public class UsageTrackingClientService extends Stub implements IBoundService {
    private static final String TAG = "services.positioning.analytics.internal.UsageTrackingClientService";
    private final IUsageTrackingSession mSession;

    private static class ListenerBridge implements UsageTrackingListener {
        private UsageTrackingListener mListener;

        ListenerBridge(UsageTrackingListener usageTrackingListener) {
            this.mListener = usageTrackingListener;
        }

        public void onTrackerUpdated(int i, long[] jArr) {
            if (this.mListener != null) {
                try {
                    this.mListener.onTrackerUpdated(UsageTrackingUtils.trackerUpdateToBundle(i, jArr));
                } catch (RemoteException e) {
                    this.mListener = null;
                }
            }
        }
    }

    public UsageTrackingClientService(IPosClientManager iPosClientManager, Intent intent) {
        if (iPosClientManager == null) {
            throw new IllegalArgumentException("posClientManager is null");
        }
        this.mSession = iPosClientManager.createUsageTrackingSession();
    }

    public Bundle getSupportedTrackers() throws RemoteException {
        Tracker[] trackerArr = (Tracker[]) this.mSession.getSupportedTrackers().toArray(new Tracker[0]);
        if (trackerArr == null || trackerArr.length < 1) {
            return new Bundle();
        }
        return UsageTrackingUtils.trackersToBundle(trackerArr);
    }

    public int subscribe(UsageTrackingListener usageTrackingListener, Bundle bundle) throws RemoteException {
        return this.mSession.subscribe(new ListenerBridge(usageTrackingListener), UsageTrackingUtils.bundleToArray(bundle)).toInt();
    }

    public int unsubscribe() throws RemoteException {
        return this.mSession.unsubscribe().toInt();
    }

    public void unBind() {
        try {
            unsubscribe();
        } catch (RemoteException e) {
        }
    }
}
