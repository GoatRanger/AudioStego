package com.here.services.positioning.analytics;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.here.posclient.Status;
import com.here.posclient.analytics.Counters.Handler;
import com.here.posclient.analytics.PositioningCounters;
import com.here.posclient.analytics.RadiomapCounters;
import com.here.posclient.analytics.Tracker;
import com.here.services.HereLocationApiClient;
import com.here.services.internal.ServiceController.Provider;
import com.here.services.positioning.analytics.UsageTrackingApi.Listener;
import com.here.services.positioning.analytics.internal.UsageTrackingClient;
import com.here.services.positioning.analytics.internal.UsageTrackingListener.Stub;
import com.here.services.positioning.analytics.internal.UsageTrackingServicesController;
import com.here.services.positioning.analytics.internal.UsageTrackingUtils;
import java.util.EnumSet;

public class UsageTrackingProvider implements UsageTrackingApi {
    private static final String TAG = "services.positioning.analytics.UsageTrackingProvider";
    private final Provider<UsageTrackingServicesController> mProvider;

    static class ListenerBridge extends Stub {
        final Listener mListener;

        ListenerBridge(Listener listener) {
            this.mListener = listener;
        }

        public void onTrackerUpdated(Bundle bundle) throws RemoteException {
            if (this.mListener != null) {
                UsageTrackingUtils.trackerUpdateFromBundle(bundle, new Handler() {
                    public void onHandleRadiomapCounters(RadiomapCounters radiomapCounters) {
                        ListenerBridge.this.mListener.onRadiomapCountersUpdated(radiomapCounters);
                    }

                    public void onHandlePositioningCounters(PositioningCounters positioningCounters) {
                        ListenerBridge.this.mListener.onPositioningCountersUpdated(positioningCounters);
                    }
                });
            }
        }
    }

    public UsageTrackingProvider(Context context, Provider<UsageTrackingServicesController> provider) {
        if (provider == null) {
            throw new IllegalArgumentException("provider is null");
        }
        this.mProvider = provider;
    }

    public EnumSet<Tracker> getSupportedTrackers(HereLocationApiClient hereLocationApiClient) {
        UsageTrackingClient client = getClient(hereLocationApiClient);
        return client == null ? null : client.getSupportedTrackers();
    }

    public Status subscribe(HereLocationApiClient hereLocationApiClient, Listener listener, Tracker... trackerArr) {
        UsageTrackingClient client = getClient(hereLocationApiClient);
        return client == null ? Status.UsageError : client.subscribe(new ListenerBridge(listener), trackerArr);
    }

    UsageTrackingClient getClient(HereLocationApiClient hereLocationApiClient) {
        UsageTrackingServicesController usageTrackingServicesController = (UsageTrackingServicesController) this.mProvider.getController(hereLocationApiClient);
        if (usageTrackingServicesController == null) {
            return null;
        }
        return usageTrackingServicesController.getUsageTrackingClient();
    }
}
