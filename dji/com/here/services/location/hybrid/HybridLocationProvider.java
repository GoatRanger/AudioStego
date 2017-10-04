package com.here.services.location.hybrid;

import android.content.Context;
import android.location.Location;
import com.here.services.HereLocationApiClient;
import com.here.services.internal.ServiceController.Provider;
import com.here.services.location.LocationListener;
import com.here.services.location.hybrid.HybridLocationApi.Options;
import com.here.services.location.internal.IPositioning;
import com.here.services.location.internal.IPositioning.IPositionListener;
import com.here.services.location.internal.ListenerProxy;
import com.here.services.location.internal.LocationServicesController;
import java.util.HashMap;
import java.util.Map;

public class HybridLocationProvider implements HybridLocationApi {
    private static final String TAG = "services.location.hybrid.HybridLocationProvider";
    private final Map<LocationListener, ListenerProxy> mListenerProxies = new HashMap();
    private final Provider<LocationServicesController> mProvider;

    public HybridLocationProvider(Context context, Provider<LocationServicesController> provider) {
        if (provider == null) {
            throw new IllegalArgumentException("provider is null");
        }
        this.mProvider = provider;
    }

    public Location getLastLocation(HereLocationApiClient hereLocationApiClient) {
        IPositioning positioning = getPositioning(hereLocationApiClient);
        return positioning == null ? null : positioning.getLastPosition();
    }

    public boolean startLocationUpdates(HereLocationApiClient hereLocationApiClient, Options options, LocationListener locationListener) {
        IPositioning positioning = getPositioning(hereLocationApiClient);
        if (positioning == null) {
            return false;
        }
        IPositionListener iPositionListener = (ListenerProxy) this.mListenerProxies.get(locationListener);
        if (iPositionListener == null) {
            iPositionListener = new ListenerProxy(locationListener);
            this.mListenerProxies.put(locationListener, iPositionListener);
        }
        if (positioning.startPositionUpdates(options.build(), iPositionListener)) {
            return true;
        }
        this.mListenerProxies.remove(locationListener);
        return false;
    }

    public void stopLocationUpdates(HereLocationApiClient hereLocationApiClient, LocationListener locationListener) {
        IPositioning positioning = getPositioning(hereLocationApiClient);
        if (positioning != null) {
            ListenerProxy listenerProxy = (ListenerProxy) this.mListenerProxies.remove(locationListener);
            if (listenerProxy != null) {
                positioning.stopPositionUpdates(listenerProxy);
            }
        }
    }

    IPositioning getPositioning(HereLocationApiClient hereLocationApiClient) {
        LocationServicesController locationServicesController = (LocationServicesController) this.mProvider.getController(hereLocationApiClient);
        if (locationServicesController == null) {
            return null;
        }
        return locationServicesController.getPositioning();
    }
}
