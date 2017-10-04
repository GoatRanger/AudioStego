package com.nokia.maps;

import com.here.android.mpa.fce.FleetConnectivityError;
import com.here.android.mpa.fce.FleetConnectivityEvent;
import com.here.android.mpa.fce.FleetConnectivityMessage;
import com.here.android.mpa.fce.FleetConnectivityService.Listener;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
class FleetConnectivityServiceBridge extends BaseNativeObject {
    private Listener a;

    private native void createFleetConnectivityServiceBridgeNative();

    private native void destroyFleetConnectivityServiceBridgeNative();

    public FleetConnectivityServiceBridge() {
        BaseNativeObject.u();
        createFleetConnectivityServiceBridgeNative();
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyFleetConnectivityServiceBridgeNative();
        }
    }

    synchronized void a(Listener listener) {
        this.a = listener;
    }

    @HybridPlusNative
    private void onMessageReceived(final FleetConnectivityMessage fleetConnectivityMessage) {
        ce.a(new Runnable(this) {
            final /* synthetic */ FleetConnectivityServiceBridge b;

            public void run() {
                synchronized (FleetConnectivityServiceBridge.class) {
                    Listener a = this.b.a;
                }
                if (a != null) {
                    a.onMessageReceived(fleetConnectivityMessage);
                }
            }
        });
    }

    @HybridPlusNative
    private void onEventAcknowledged(final FleetConnectivityEvent fleetConnectivityEvent, final FleetConnectivityError fleetConnectivityError) {
        ce.a(new Runnable(this) {
            final /* synthetic */ FleetConnectivityServiceBridge c;

            public void run() {
                synchronized (FleetConnectivityServiceBridge.class) {
                    Listener a = this.c.a;
                }
                if (a != null) {
                    a.onEventAcknowledged(fleetConnectivityEvent, fleetConnectivityError);
                }
            }
        });
    }

    @HybridPlusNative
    private NavigationManagerImpl getNavigationManager() {
        return NavigationManagerImpl.a();
    }
}
