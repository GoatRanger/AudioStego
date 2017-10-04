package com.here.android.mpa.venues3d;

import android.content.Context;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.ApplicationContext;
import com.nokia.maps.ApplicationContext$c;
import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.MapsEngine;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.dy;
import com.nokia.maps.fc;
import com.nokia.maps.fc$a;
import java.lang.ref.WeakReference;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class VenueService extends BaseNativeObject {
    private static volatile VenueService a;
    private static Object b = new Object();
    private final fc<VenueServiceListener> c;
    private InitStatus d;
    private final ExecutorService e;
    private boolean f;
    private boolean g;
    private boolean h;
    private volatile boolean i;
    private final ApplicationContext$c j;

    @HybridPlus
    public enum InitStatus {
        ONLINE_SUCCESS,
        OFFLINE_SUCCESS,
        AUTH_FAILED,
        INIT_STYLES_FAILED,
        INIT_ICONS_FAILED,
        INIT_INDEX_FAILED,
        NOT_STARTED,
        IN_PROGRESS
    }

    @HybridPlus
    public interface VenueServiceListener {
        void onGetVenueCompleted(Venue venue);

        void onInitializationCompleted(InitStatus initStatus);
    }

    @HybridPlusNative
    private native void createNative(String str, String str2, int i, int i2);

    @HybridPlusNative
    private native void enableVenueZoomNative(boolean z);

    @HybridPlusNative
    private native int getInitStatusNative();

    @HybridPlusNative
    private native boolean getIsOnlineNative();

    @HybridPlusNative
    private native VenueInfo getVenueByIdNative(String str);

    @HybridPlusNative
    private native boolean isVenueZoomEnabledNative();

    @HybridPlusNative
    private native List<VenueInfo> searchVenuesNative(GeoBoundingBox geoBoundingBox);

    @HybridPlusNative
    private native void setIsOnlineNative(boolean z);

    @HybridPlusNative
    private native void startNative(String str, boolean z, boolean z2);

    @HybridPlusNative
    native void getVenueNative(VenueInfo venueInfo);

    @HybridPlusNative
    native void getVenuesNative(List<VenueInfo> list);

    @HybridPlusNative
    native void getVenuesNative(List<VenueInfo> list, GeoCoordinate geoCoordinate);

    @HybridPlusNative
    public native void setHereAccountToken(String str);

    @HybridPlus
    public static VenueService getInstance(Context context) {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    String appId = ApplicationContext.b().getAppId();
                    String appToken = ApplicationContext.b().getAppToken();
                    int i = context.getResources().getDisplayMetrics().densityDpi;
                    a = new VenueService(appId, appToken, a(i), i);
                }
            }
        }
        return a;
    }

    private VenueService(String str, String str2, int i, int i2) {
        this();
        createNative(str, str2, i, i2);
    }

    private VenueService() {
        super(true);
        this.c = new fc();
        this.d = InitStatus.AUTH_FAILED;
        this.f = true;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = new ApplicationContext$c(this) {
            final /* synthetic */ VenueService a;

            {
                this.a = r1;
            }

            public void a() {
                throw new AccessControlException("Access to this operation is denied. Contact your HERE representative for more information.");
            }

            public void b() {
                this.a.i = true;
            }
        };
        ApplicationContext.b().check(7, this.j);
        this.e = Executors.newCachedThreadPool();
    }

    @HybridPlusNative
    private VenueService(int i) {
        this();
        this.nativeptr = i;
    }

    private static int a(int i) {
        if (i >= 480) {
            return 3;
        }
        if (i >= 320) {
            return 2;
        }
        if (i >= 240) {
            return 1;
        }
        return 0;
    }

    @HybridPlusNative
    public void startAsync() {
        if (this.i) {
            this.e.execute(new Runnable(this) {
                final /* synthetic */ VenueService a;

                {
                    this.a = r1;
                }

                public void run() {
                    String r;
                    if (this.a.h) {
                        r = MapsEngine.r();
                    } else {
                        r = MapsEngine.q();
                    }
                    this.a.startNative(r, this.a.g, this.a.f);
                }
            });
        }
    }

    @HybridPlus
    public void addListener(VenueServiceListener venueServiceListener) {
        if (this.i && venueServiceListener != null) {
            this.c.b(venueServiceListener);
            this.c.a(new WeakReference(venueServiceListener));
        }
    }

    @HybridPlus
    public void removeListener(VenueServiceListener venueServiceListener) {
        if (this.i && venueServiceListener != null) {
            this.c.b(venueServiceListener);
        }
    }

    @HybridPlusNative
    private void onInitializationCompleted(int i) {
        this.d = InitStatus.values()[i];
        this.c.b(new fc$a<VenueServiceListener>(this) {
            final /* synthetic */ VenueService a;

            {
                this.a = r1;
            }

            public void a(VenueServiceListener venueServiceListener) {
                venueServiceListener.onInitializationCompleted(this.a.d);
            }
        });
    }

    @HybridPlus
    public void getVenueAsync(final VenueInfo venueInfo) {
        a();
        if (venueInfo != null) {
            this.e.execute(new Runnable(this) {
                final /* synthetic */ VenueService b;

                public void run() {
                    this.b.getVenueNative(venueInfo);
                }
            });
        }
    }

    @HybridPlus
    public void getVenuesAsync(final List<VenueInfo> list) {
        a();
        boolean z = list != null && list.size() > 0;
        dy.a(z, "The list of venues can't be null or empty.");
        this.e.execute(new Runnable(this) {
            final /* synthetic */ VenueService b;

            public void run() {
                this.b.getVenuesNative(list);
            }
        });
    }

    @HybridPlus
    public void getVenuesAsync(final List<VenueInfo> list, final GeoCoordinate geoCoordinate) {
        a();
        boolean z = list != null && list.size() > 0;
        dy.a(z, "The list of venues can't be null or empty.");
        this.e.execute(new Runnable(this) {
            final /* synthetic */ VenueService c;

            public void run() {
                this.c.getVenuesNative(list, geoCoordinate);
            }
        });
    }

    @HybridPlusNative
    private void onGetVenueCompletedSync(final Venue venue) {
        this.c.b(new fc$a<VenueServiceListener>(this) {
            final /* synthetic */ VenueService b;

            public void a(VenueServiceListener venueServiceListener) {
                venueServiceListener.onGetVenueCompleted(venue);
            }
        });
    }

    @HybridPlus
    public List<VenueInfo> getVenuesIn(GeoBoundingBox geoBoundingBox) {
        a();
        List<VenueInfo> searchVenuesNative = searchVenuesNative(geoBoundingBox);
        return searchVenuesNative != null ? searchVenuesNative : new ArrayList();
    }

    @HybridPlus
    public List<VenueInfo> getVenuesAt(GeoCoordinate geoCoordinate) {
        return getVenuesIn(new GeoBoundingBox(geoCoordinate, geoCoordinate));
    }

    @HybridPlus
    public VenueInfo getVenueAt(GeoCoordinate geoCoordinate) {
        return a(geoCoordinate, getVenuesAt(geoCoordinate));
    }

    @HybridPlus
    public List<VenueInfo> getVenuesAt(GeoCoordinate geoCoordinate, float f) {
        List<VenueInfo> venuesIn = getVenuesIn(new GeoBoundingBox(geoCoordinate, f * 2.0f, 2.0f * f));
        int i = 0;
        while (i < venuesIn.size()) {
            GeoBoundingBox boundingBox = ((VenueInfo) venuesIn.get(i)).getBoundingBox();
            if (boundingBox == null || geoCoordinate.distanceTo(boundingBox.getCenter()) > ((double) f)) {
                venuesIn.remove(i);
                i--;
            }
            i++;
        }
        return venuesIn;
    }

    @HybridPlus
    public VenueInfo getVenueAt(GeoCoordinate geoCoordinate, float f) {
        return a(geoCoordinate, getVenuesAt(geoCoordinate, f));
    }

    private VenueInfo a(GeoCoordinate geoCoordinate, List<VenueInfo> list) {
        VenueInfo venueInfo = null;
        if (list == null) {
            return null;
        }
        if (list.size() == 1) {
            return (VenueInfo) list.get(0);
        }
        double d = Double.MAX_VALUE;
        for (VenueInfo venueInfo2 : list) {
            VenueInfo venueInfo3;
            double d2;
            GeoBoundingBox boundingBox = venueInfo2.getBoundingBox();
            if (boundingBox != null) {
                double distanceTo = geoCoordinate.distanceTo(boundingBox.getCenter());
                if (distanceTo < d) {
                    double d3 = distanceTo;
                    venueInfo3 = venueInfo2;
                    d2 = d3;
                    d = d2;
                    venueInfo = venueInfo3;
                }
            }
            venueInfo3 = venueInfo;
            d2 = d;
            d = d2;
            venueInfo = venueInfo3;
        }
        return venueInfo;
    }

    @HybridPlus
    public InitStatus getInitStatus() {
        return InitStatus.values()[getInitStatusNative()];
    }

    @HybridPlus
    public VenueInfo getVenueById(String str) {
        a();
        return getVenueByIdNative(str);
    }

    @HybridPlus
    public void setTestEnv(boolean z) {
        if (this.i) {
            this.h = z;
        }
    }

    @HybridPlus
    public void enableVenueZoom(boolean z) {
        if (this.i) {
            enableVenueZoomNative(z);
        }
    }

    @HybridPlus
    public boolean isVenueZoomEnabled() {
        return isVenueZoomEnabledNative();
    }

    @HybridPlus
    public void setPrivateContent(boolean z) {
        if (this.i) {
            this.g = z;
        }
    }

    @Internal
    public void a(boolean z) {
        if (this.i) {
            this.f = z;
        }
    }

    private void a() {
        if (this.i) {
            InitStatus initStatus = getInitStatus();
            if (initStatus != InitStatus.ONLINE_SUCCESS && initStatus != InitStatus.OFFLINE_SUCCESS) {
                throw new RuntimeException("Venue service has yet been initialized.  Try again later.");
            }
            return;
        }
        throw new AccessControlException("Access to this operation is denied. Contact your HERE representative for more information.");
    }
}
