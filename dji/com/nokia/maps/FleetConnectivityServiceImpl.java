package com.nokia.maps;

import com.here.android.mpa.fce.FleetConnectivityCustomMessage;
import com.here.android.mpa.fce.FleetConnectivityError;
import com.here.android.mpa.fce.FleetConnectivityError.Issue;
import com.here.android.mpa.fce.FleetConnectivityEvent;
import com.here.android.mpa.fce.FleetConnectivityJobCancelledEvent;
import com.here.android.mpa.fce.FleetConnectivityJobFinishedEvent;
import com.here.android.mpa.fce.FleetConnectivityJobMessage;
import com.here.android.mpa.fce.FleetConnectivityJobRejectedEvent;
import com.here.android.mpa.fce.FleetConnectivityJobStartedEvent;
import com.here.android.mpa.fce.FleetConnectivityMessage;
import com.here.android.mpa.fce.FleetConnectivityService;
import com.here.android.mpa.fce.FleetConnectivityService.Listener;
import com.nokia.maps.annotation.HybridPlus;
import java.security.AccessControlException;
import java.util.Map;

@HybridPlus
public class FleetConnectivityServiceImpl extends BaseNativeObject implements Listener {
    private static k<FleetConnectivityService, FleetConnectivityServiceImpl> a = null;
    private FleetConnectivityServiceBridge b;
    private p c;
    private Listener d;
    private final ApplicationContext$c e = new ApplicationContext$c(this) {
        final /* synthetic */ FleetConnectivityServiceImpl a;

        {
            this.a = r1;
        }

        public void a() {
            throw new AccessControlException("Access to this operation is denied. Contact your HERE representative for more information.");
        }

        public void b() {
        }
    };

    private native void createFleetConnectivityServiceNative(String str, String str2, FleetConnectivityServiceBridge fleetConnectivityServiceBridge);

    private native void destroyFleetConnectivityServiceNative();

    private native boolean forcePollNative();

    private native String getAssetIdNative();

    private native String getDispatcherIdNative();

    private native long getPollingIntervalNative();

    private native String getRunningJobIdNative();

    private native boolean notifyJobCancelledNative(Map<String, String> map);

    private native boolean notifyJobFinishedNative(Map<String, String> map);

    private native boolean notifyJobRejectedNative(String str, Map<String, String> map);

    private native boolean notifyJobStartedNative(String str, long j, Map<String, String> map);

    private native boolean sendCustomEventNative(String str, Map<String, String> map);

    private native void setAssetIdNative(String str);

    private native void setDispatcherIdNative(String str);

    private native void setPollingIntervalNative(long j);

    private native boolean startNative();

    private native boolean stopNative();

    static {
        ce.a(FleetConnectivityService.class);
        ce.a(FleetConnectivityMessage.class);
        ce.a(FleetConnectivityJobMessage.class);
        ce.a(FleetConnectivityCustomMessage.class);
        ce.a(FleetConnectivityEvent.class);
        ce.a(FleetConnectivityJobStartedEvent.class);
        ce.a(FleetConnectivityJobRejectedEvent.class);
        ce.a(FleetConnectivityJobCancelledEvent.class);
        ce.a(FleetConnectivityJobFinishedEvent.class);
        ce.a(FleetConnectivityError.class);
        ce.a(Issue.class);
    }

    public static void a(k<FleetConnectivityService, FleetConnectivityServiceImpl> kVar) {
        a = kVar;
    }

    public FleetConnectivityServiceImpl() {
        BaseNativeObject.u();
        String appId = ApplicationContext.b().getAppId();
        String appToken = ApplicationContext.b().getAppToken();
        this.b = new FleetConnectivityServiceBridge();
        createFleetConnectivityServiceNative(appId, appToken, this.b);
        this.b.a((Listener) this);
        this.c = l.a();
        ApplicationContext.b().check(42, this.e);
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyFleetConnectivityServiceNative();
        }
    }

    public boolean a() {
        boolean startNative = startNative();
        if (startNative) {
            this.c.b();
        }
        return startNative;
    }

    public boolean b() {
        return stopNative();
    }

    public boolean a(String str, long j, Map<String, String> map) {
        return notifyJobStartedNative(str, j, map);
    }

    public boolean a(String str, Map<String, String> map) {
        return notifyJobRejectedNative(str, map);
    }

    public boolean a(Map<String, String> map) {
        return notifyJobCancelledNative(map);
    }

    public boolean b(Map<String, String> map) {
        return notifyJobFinishedNative(map);
    }

    public boolean b(String str, Map<String, String> map) {
        return sendCustomEventNative(str, map);
    }

    public boolean c() {
        return forcePollNative();
    }

    public String d() {
        return getRunningJobIdNative();
    }

    public String e() {
        return getAssetIdNative();
    }

    public void a(String str) {
        setAssetIdNative(str);
    }

    public String f() {
        return getDispatcherIdNative();
    }

    public void b(String str) {
        setDispatcherIdNative(str);
    }

    public long g() {
        return getPollingIntervalNative();
    }

    public void a(long j) {
        setPollingIntervalNative(j);
    }

    public synchronized void a(Listener listener) {
        this.d = listener;
    }

    public synchronized void onMessageReceived(FleetConnectivityMessage fleetConnectivityMessage) {
        this.c.c();
        if (this.d != null) {
            this.d.onMessageReceived(fleetConnectivityMessage);
        }
    }

    public synchronized void onEventAcknowledged(FleetConnectivityEvent fleetConnectivityEvent, FleetConnectivityError fleetConnectivityError) {
        if (fleetConnectivityError != null) {
            this.c.e();
        } else {
            this.c.d();
        }
        if (this.d != null) {
            this.d.onEventAcknowledged(fleetConnectivityEvent, fleetConnectivityError);
        }
    }
}
