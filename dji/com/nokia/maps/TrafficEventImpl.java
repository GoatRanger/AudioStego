package com.nokia.maps;

import android.text.TextUtils;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.mapping.TrafficEvent;
import com.here.android.mpa.mapping.TrafficEvent.Listener;
import com.here.android.mpa.mapping.TrafficEvent.Severity;
import com.here.android.mpa.routing.Route;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@HybridPlus
public class TrafficEventImpl extends BaseNativeObject {
    public static final String a = TrafficEventImpl.class.getName();
    private static k<TrafficEvent, TrafficEventImpl> k;
    private static am<TrafficEvent, TrafficEventImpl> l;
    private List<String> b;
    private String c;
    private List<String> d;
    private List<String> e;
    private a<List<String>> f = new a(new c(this) {
        final /* synthetic */ TrafficEventImpl a;

        {
            this.a = r1;
        }

        public String a() {
            return this.a.getAffectedStreetsNative();
        }

        public void a(String str) {
            if (str == null) {
                bj.f(TrafficEventImpl.a, "ERROR: result argument is null", new Object[0]);
            }
            this.a.b = TrafficEventImpl.b(str);
        }
    }, b.STRINGLIST);
    private a<String> g = new a(new c(this) {
        final /* synthetic */ TrafficEventImpl a;

        {
            this.a = r1;
        }

        public String a() {
            return this.a.getFirstAffectedStreetNative();
        }

        public void a(String str) {
            if (str == null) {
                bj.f(TrafficEventImpl.a, "ERROR: result argument is null", new Object[0]);
            }
            this.a.c = str;
        }
    }, b.STRING);
    private a<List<String>> h = new a(new c(this) {
        final /* synthetic */ TrafficEventImpl a;

        {
            this.a = r1;
        }

        public String a() {
            return this.a.getToStreetsNative();
        }

        public void a(String str) {
            if (str == null) {
                bj.f(TrafficEventImpl.a, "ERROR: result argument is null", new Object[0]);
            }
            this.a.d = TrafficEventImpl.b(str);
        }
    }, b.STRINGLIST);
    private a<List<String>> i = new a(new c(this) {
        final /* synthetic */ TrafficEventImpl a;

        {
            this.a = r1;
        }

        public String a() {
            return this.a.getFromStreetsNative();
        }

        public void a(String str) {
            if (str == null) {
                bj.f(TrafficEventImpl.a, "ERROR: result argument is null", new Object[0]);
            }
            this.a.e = TrafficEventImpl.b(str);
        }
    }, b.STRINGLIST);
    private cq j = new cq(TrafficEventImpl.class.getName());

    private interface c {
        String a();

        void a(String str);
    }

    private static class a<T> extends Thread {
        private boolean a = false;
        private final b b;
        private final c c;
        private List<Listener<T>> d = new ArrayList();

        public a(c cVar, b bVar) {
            this.c = cVar;
            this.b = bVar;
            setName("TrafficEventThread");
        }

        public synchronized boolean a(Listener<T> listener) {
            boolean z;
            if (this.a) {
                z = false;
            } else {
                this.d.add(listener);
                z = true;
            }
            return z;
        }

        public void run() {
            String str = null;
            long currentTimeMillis = System.currentTimeMillis() + 30000;
            while (str == null && System.currentTimeMillis() < currentTimeMillis) {
                str = this.c.a();
                if (str == null) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                }
            }
            synchronized (this) {
                this.a = true;
                this.c.a(str);
                for (final Listener listener : this.d) {
                    Object a;
                    if (this.b == b.STRINGLIST) {
                        a = TrafficEventImpl.b(str);
                    } else {
                        String str2 = str;
                    }
                    ez.a(new Runnable(this) {
                        final /* synthetic */ a c;

                        public void run() {
                            listener.onComplete(a);
                        }
                    });
                }
            }
        }
    }

    private enum b {
        STRING,
        STRINGLIST
    }

    private native void destroyTrafficEventNative();

    private native long getActivationDateNative();

    private native GeoBoundingBoxImpl getAffectedAreaNative();

    private native RoadElementImpl[] getAffectedRoadElementsNative();

    private final native String getAffectedStreetsNative();

    private final native int getDistanceToNative(GeoCoordinateImpl geoCoordinateImpl);

    private final native String getFirstAffectedStreetNative();

    private final native String getFromStreetsNative();

    private native ImageImpl getIconOffRouteNative();

    private native ImageImpl getIconOnRouteNative();

    private final native int getSeverityNative();

    private final native String getToStreetsNative();

    private native int getUpdateDateNative();

    public final native int getAffectedLength();

    public final native int getEstimatedSpeedLimit();

    public final native String getEventText();

    public native long getId();

    public native short getPenalty();

    public native String getShortText();

    public final native int getSpeedLimit();

    public final native boolean isActive();

    public final native boolean isFlow();

    public final native boolean isIncident();

    public final native boolean isOnRouteNative(RouteImpl routeImpl);

    public final native boolean isReroutable();

    public final native boolean isVisible();

    static {
        ce.a(TrafficEvent.class);
    }

    @HybridPlusNative
    private TrafficEventImpl(int i) {
        this.nativeptr = i;
        this.f.start();
        this.g.start();
        this.h.start();
        this.i.start();
    }

    protected void finalize() {
        destroyTrafficEventNative();
    }

    public final int a(GeoCoordinate geoCoordinate) {
        if (geoCoordinate.isValid()) {
            return getDistanceToNative(GeoCoordinateImpl.get(geoCoordinate));
        }
        return 64000;
    }

    public final boolean a(Route route) {
        if (route != null) {
            return isOnRouteNative(RouteImpl.a(route));
        }
        return false;
    }

    public final List<String> a() {
        return this.b;
    }

    public final void a(Listener<List<String>> listener) {
        if (!this.f.a(listener)) {
            listener.onComplete(this.b);
        }
    }

    public final String b() {
        return this.c;
    }

    public final void b(Listener<String> listener) {
        if (!this.g.a(listener)) {
            listener.onComplete(this.c);
        }
    }

    public List<String> c() {
        return this.d;
    }

    public final void c(Listener<List<String>> listener) {
        if (!this.h.a(listener)) {
            listener.onComplete(this.d);
        }
    }

    public List<String> d() {
        return this.e;
    }

    public final void d(Listener<List<String>> listener) {
        if (!this.i.a(listener)) {
            listener.onComplete(this.e);
        }
    }

    public final GeoBoundingBox e() {
        return GeoBoundingBoxImpl.create(getAffectedAreaNative());
    }

    public final Image f() {
        return ImageImpl.a(getIconOnRouteNative());
    }

    public final Image g() {
        return ImageImpl.a(getIconOffRouteNative());
    }

    public final List<RoadElement> h() {
        List<RoadElement> arrayList = new ArrayList();
        RoadElementImpl[] affectedRoadElementsNative = getAffectedRoadElementsNative();
        if (affectedRoadElementsNative != null) {
            for (RoadElementImpl a : affectedRoadElementsNative) {
                RoadElement a2 = RoadElementImpl.a(a);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
        }
        return arrayList;
    }

    public final Date i() {
        long activationDateNative = getActivationDateNative();
        if (activationDateNative > 0) {
            return new Date(TimeUnit.SECONDS.toMillis(activationDateNative));
        }
        return null;
    }

    public final Date j() {
        int updateDateNative = getUpdateDateNative();
        if (updateDateNative > 0) {
            return new Date(TimeUnit.SECONDS.toMillis((long) updateDateNative));
        }
        return null;
    }

    public Severity k() {
        return a(getSeverityNative());
    }

    static Severity a(int i) {
        switch (i) {
            case 1:
                return Severity.NORMAL;
            case 2:
                return Severity.HIGH;
            case 4:
                return Severity.VERY_HIGH;
            case 8:
                return Severity.BLOCKING;
            default:
                return Severity.UNDEFINED;
        }
    }

    public static void a(k<TrafficEvent, TrafficEventImpl> kVar, am<TrafficEvent, TrafficEventImpl> amVar) {
        k = kVar;
        l = amVar;
    }

    static TrafficEventImpl a(TrafficEvent trafficEvent) {
        return (TrafficEventImpl) k.a(trafficEvent);
    }

    static TrafficEvent a(TrafficEventImpl trafficEventImpl) {
        if (trafficEventImpl != null) {
            return (TrafficEvent) l.a(trafficEventImpl);
        }
        return null;
    }

    public String toString() {
        String str;
        StringBuilder a = a("Severity: ", k().toString(), a("Short Text: ", getShortText(), a("Event Text: ", getEventText(), new StringBuilder())));
        String str2 = "Affected Streets: ";
        if (this.b == null) {
            str = "null";
        } else {
            str = this.b.toString();
        }
        return a("Penalty: ", Integer.toString(getPenalty()), a("Visible: ", isVisible() ? "True" : "False", a("Incident: ", isIncident() ? "True" : "False", a("Flow: ", isFlow() ? "True" : "False", a("Speed Limit: ", Integer.toString(getSpeedLimit()), a("To Streets: ", this.d == null ? "null" : this.d.toString(), a("From Streets: ", this.e == null ? "null" : this.e.toString(), a("Affected Length: ", Integer.toString(getAffectedLength()), a(str2, str, a))))))))).toString();
    }

    private StringBuilder a(String str, String str2, StringBuilder stringBuilder) {
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append(str);
            stringBuilder.append(str2);
            stringBuilder.append("\n");
        }
        return stringBuilder;
    }

    private static List<String> b(String str) {
        if (str == null || str.isEmpty()) {
            return new ArrayList();
        }
        return Arrays.asList(str.split(","));
    }
}
