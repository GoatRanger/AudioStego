package com.here.android.mpa.fce;

import com.nokia.maps.FleetConnectivityServiceImpl;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.Map;

@HybridPlus
public final class FleetConnectivityService {
    private static volatile FleetConnectivityService a = null;
    private static Object b = new Object();
    private final FleetConnectivityServiceImpl c = new FleetConnectivityServiceImpl();

    @HybridPlus
    public interface Listener {
        void onEventAcknowledged(FleetConnectivityEvent fleetConnectivityEvent, FleetConnectivityError fleetConnectivityError);

        void onMessageReceived(FleetConnectivityMessage fleetConnectivityMessage);
    }

    static {
        FleetConnectivityServiceImpl.a(new k<FleetConnectivityService, FleetConnectivityServiceImpl>() {
            public FleetConnectivityServiceImpl a(FleetConnectivityService fleetConnectivityService) {
                return fleetConnectivityService != null ? fleetConnectivityService.c : null;
            }
        });
    }

    public static FleetConnectivityService getInstance() {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    a = new FleetConnectivityService();
                }
            }
        }
        return a;
    }

    private FleetConnectivityService() {
    }

    public boolean start() {
        return this.c.a();
    }

    public boolean stop() {
        return this.c.b();
    }

    public boolean forcePoll() {
        return this.c.c();
    }

    public boolean sendEvent(FleetConnectivityEvent fleetConnectivityEvent) {
        return fleetConnectivityEvent.dispatch(this);
    }

    public String getRunningJobId() {
        return this.c.d();
    }

    public String getAssetId() {
        return this.c.e();
    }

    public void setAssetId(String str) {
        this.c.a(str);
    }

    public String getDispatcherId() {
        return this.c.f();
    }

    public void setDispatcherId(String str) {
        this.c.b(str);
    }

    public long getPollingInterval() {
        return this.c.g();
    }

    public void setPollingInterval(long j) {
        this.c.a(j);
    }

    public void setListener(Listener listener) {
        this.c.a(listener);
    }

    boolean a(String str, long j, Map<String, String> map) {
        return this.c.a(str, j, map);
    }

    boolean a(String str, Map<String, String> map) {
        return this.c.a(str, map);
    }

    boolean a(Map<String, String> map) {
        return this.c.a((Map) map);
    }

    boolean b(Map<String, String> map) {
        return this.c.b((Map) map);
    }

    boolean b(String str, Map<String, String> map) {
        return this.c.b(str, map);
    }
}
