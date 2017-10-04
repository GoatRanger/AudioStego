package com.nokia.maps;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPosition;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.common.PositioningManager.LocationMethod;
import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.guidance.NavigationManager$AspectRatio;
import com.here.android.mpa.guidance.NavigationManager$AudioFeedbackListener;
import com.here.android.mpa.guidance.NavigationManager$Error;
import com.here.android.mpa.guidance.NavigationManager$GpsSignalListener;
import com.here.android.mpa.guidance.NavigationManager$LaneInformationListener;
import com.here.android.mpa.guidance.NavigationManager$ManeuverEventListener;
import com.here.android.mpa.guidance.NavigationManager$MapUpdateMode;
import com.here.android.mpa.guidance.NavigationManager$NaturalGuidanceMode;
import com.here.android.mpa.guidance.NavigationManager$NavigationManagerEventListener;
import com.here.android.mpa.guidance.NavigationManager$NavigationMode;
import com.here.android.mpa.guidance.NavigationManager$NavigationState;
import com.here.android.mpa.guidance.NavigationManager$NewInstructionEventListener;
import com.here.android.mpa.guidance.NavigationManager$PositionListener;
import com.here.android.mpa.guidance.NavigationManager$RealisticViewListener;
import com.here.android.mpa.guidance.NavigationManager$RealisticViewMode;
import com.here.android.mpa.guidance.NavigationManager$RerouteListener;
import com.here.android.mpa.guidance.NavigationManager$RoadView$Listener;
import com.here.android.mpa.guidance.NavigationManager$RoadView$Orientation;
import com.here.android.mpa.guidance.NavigationManager$SafetySpotListener;
import com.here.android.mpa.guidance.NavigationManager$SpeedWarningListener;
import com.here.android.mpa.guidance.NavigationManager$TrafficAvoidanceMode;
import com.here.android.mpa.guidance.NavigationManager$TrafficRerouteListener;
import com.here.android.mpa.guidance.NavigationManager$UnitSystem;
import com.here.android.mpa.guidance.NavigationManager.LaneInfoListener;
import com.here.android.mpa.guidance.NavigationManager.TrafficRerouteListener.TrafficEnabledRoutingState;
import com.here.android.mpa.guidance.TrafficWarner;
import com.here.android.mpa.guidance.VoiceCatalog;
import com.here.android.mpa.guidance.VoiceSkin;
import com.here.android.mpa.guidance.VoiceSkin.OutputType;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.Map$Animation;
import com.here.android.mpa.routing.Maneuver;
import com.here.android.mpa.routing.Route;
import com.here.android.mpa.routing.Route.TrafficPenaltyMode;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RouteOptions.TransportMode;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteTta;
import com.here.android.mpa.routing.UMRoute;
import com.here.android.mpa.routing.UMRouteOptions;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.lang.ref.WeakReference;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@HybridPlus
public class NavigationManagerImpl extends BaseNativeObject {
    private static final long a = ((Integer.valueOf(Integer.MAX_VALUE).longValue() * 2) + 1);
    private static final String b = NavigationManagerImpl.class.getSimpleName();
    private static int f = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
    private static NavigationManagerImpl g;
    private EnumSet<NavigationManager$AspectRatio> A = EnumSet.noneOf(NavigationManager$AspectRatio.class);
    private com.nokia.maps.AudioPlayer.a B = new com.nokia.maps.AudioPlayer.a(this) {
        final /* synthetic */ NavigationManagerImpl a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.E.b(new fc$a<NavigationManager$AudioFeedbackListener>(this) {
                final /* synthetic */ AnonymousClass26 a;

                {
                    this.a = r1;
                }

                public void a(NavigationManager$AudioFeedbackListener navigationManager$AudioFeedbackListener) {
                    navigationManager$AudioFeedbackListener.onAudioStart();
                }
            });
        }

        public void b() {
            this.a.E.b(new fc$a<NavigationManager$AudioFeedbackListener>(this) {
                final /* synthetic */ AnonymousClass26 a;

                {
                    this.a = r1;
                }

                public void a(NavigationManager$AudioFeedbackListener navigationManager$AudioFeedbackListener) {
                    navigationManager$AudioFeedbackListener.onAudioEnd();
                }
            });
        }
    };
    private fc<NavigationManager$TrafficRerouteListener> C = new fc();
    private fc<NavigationManager$RealisticViewListener> D = new fc();
    private fc<NavigationManager$AudioFeedbackListener> E = new fc();
    private fc<NavigationManager$RerouteListener> F = new fc();
    private fc<NavigationManager$GpsSignalListener> G = new fc();
    private fc<LaneInfoListener> J = new fc();
    private fc<NavigationManager$LaneInformationListener> K = new fc();
    private fc<NavigationManager$NavigationManagerEventListener> L = new fc();
    private fc<NavigationManager$NewInstructionEventListener> M = new fc();
    private fc<NavigationManager$SafetySpotListener> N = new fc();
    private fc<NavigationManager$SpeedWarningListener> O = new fc();
    private fc<NavigationManager$PositionListener> P = new fc();
    private fc<NavigationManager$ManeuverEventListener> Q = new fc();
    private NavigationManager$NavigationState c = NavigationManager$NavigationState.IDLE;
    private NavigationManager$MapUpdateMode d = NavigationManager$MapUpdateMode.NONE;
    private NavigationManager$NavigationMode e = NavigationManager$NavigationMode.NONE;
    private AudioPlayer h;
    private Vibra i;
    private MapImpl j;
    private Timer k;
    private LocationMethod l = LocationMethod.NONE;
    private TrafficWarner m;
    private Object n = new Object();
    private t o = null;
    private p p = l.a();
    private RouteImpl q;
    private ch r;
    private ch s;
    private ch t;
    private a u = new a(this) {
        final /* synthetic */ NavigationManagerImpl a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.d();
        }

        public void b() {
            this.a.e();
        }
    };
    private b v;
    private a w;
    private fc<NavigationManager$RoadView$Listener> x = new fc();
    private NavigationManager$RealisticViewMode y = NavigationManager$RealisticViewMode.OFF;
    private AtomicBoolean z = new AtomicBoolean(false);

    private class a extends Thread {
        final /* synthetic */ NavigationManagerImpl a;
        private final Semaphore b = new Semaphore(0, true);
        private long c = 0;

        public a(NavigationManagerImpl navigationManagerImpl) {
            this.a = navigationManagerImpl;
            navigationManagerImpl.v = new b();
            setName("NavigationManager");
            setPriority(4);
            start();
        }

        public void a(long j) {
            this.c = j;
        }

        public void a() {
            this.b.release();
        }

        public void run() {
            while (true) {
                try {
                    this.b.acquire();
                    this.b.drainPermits();
                    synchronized (this.a) {
                        if (this.a.j == null || !this.a.j.r()) {
                            if (this.a.v.c) {
                                this.a.v.c = false;
                                this.a.b(this.a.v.a);
                                this.a.a(this.a.v.b);
                            }
                        } else if (this.a.z()) {
                            this.a.v.a = this.a.l();
                            this.a.v.b = this.a.y();
                            this.a.v.c = true;
                            this.a.b(NavigationManager$MapUpdateMode.NONE);
                        }
                        if (this.c != 0) {
                            this.c = (long) this.a.pollNavigationManager(this.a.h, this.a.i, TrafficWarnerImpl.a(this.a.m), this.a.o);
                        }
                        if (this.a.j != null) {
                            if (this.a.z()) {
                                this.a.j.e(true);
                            } else {
                                this.a.j.redraw();
                            }
                        }
                    }
                    if (this.c != 0) {
                        sleep(this.c);
                        this.b.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class b {
        NavigationManager$MapUpdateMode a;
        NavigationManager$RoadView$Orientation b;
        boolean c;

        private b() {
            this.a = NavigationManager$MapUpdateMode.NONE;
            this.b = NavigationManager$RoadView$Orientation.DYNAMIC;
            this.c = false;
        }
    }

    private native synchronized boolean addRealisticViewAspectRatioNative(int i);

    private native synchronized boolean clearRealisticViewAspectRatiosNative();

    private native void createNavigationManagerNative();

    private native int getAutoZoomRangeNative(int i, int i2);

    private native synchronized int getDistanceUnitNative();

    private native long getEtaNative(boolean z, int i);

    private native int getNaturalGuidanceNative();

    private native int getOrientationNative();

    private native int getTrafficAvoidanceModeNative();

    private native synchronized RouteTtaImpl getTtaNative(boolean z, int i);

    private native synchronized ManeuverImpl native_getAfterNextManeuver();

    private native long native_getVoiceSkinId();

    private native synchronized NavigationManager$Error native_navigateRoute(RouteImpl routeImpl);

    private native synchronized ManeuverImpl native_nextManeuver();

    private native synchronized void native_pauseNavigation();

    private native synchronized void native_removeFromMap(MapImpl mapImpl);

    private native synchronized NavigationManager$Error native_resumeNavigation();

    private native NavigationManager$Error native_setVoiceSkinId(long j);

    private native synchronized void native_showOnMap(MapImpl mapImpl);

    private native synchronized NavigationManager$Error native_simulate(RouteImpl routeImpl, long j);

    private native synchronized NavigationManager$Error native_startTracking();

    private native synchronized void native_stopNavigation();

    private native synchronized int pollNavigationManager(AudioPlayer audioPlayer, Vibra vibra, TrafficWarnerImpl trafficWarnerImpl, t tVar);

    private native synchronized void repeatVoiceCommand_native(AudioPlayer audioPlayer);

    private native void setAutoZoomRangeNative(int i, int i2, int i3);

    private native void setDebugNuanceNative(boolean z);

    private native synchronized NavigationManager$Error setDistanceUnit(int i);

    private native synchronized NavigationManager$Error setMapUpdateMode(int i);

    private native boolean setNaturalGuidanceNative(int i);

    private native void setOrientationNative(int i);

    private native synchronized boolean setRealisticViewModeNative(int i);

    private native NavigationManager$Error setTrafficAvoidanceMode(int i);

    public native synchronized long getAfterNextManeuverDistance();

    public native boolean getAnimationEnabled();

    public native synchronized int getAudioEvents();

    public native synchronized double getAverageSpeed();

    public native String getCountryCode();

    public native synchronized long getDestinationDistance();

    public native synchronized long getElapsedDistance();

    public native synchronized float getHighSpeedWarningBoundary();

    public native synchronized float getHighSpeedWarningOffset();

    public native synchronized float getLowSpeedWarningOffset();

    public native synchronized long getNextManeuverDistance();

    public native synchronized boolean getSpeedWarningState();

    public native boolean getUseEstimatedPosition();

    public native void setAnimationEnabled(boolean z);

    public native synchronized void setAudioEvents(int i);

    public native NavigationManager$Error setDefaultVoiceSkin();

    public native NavigationManager$Error setRouteNative(RouteImpl routeImpl);

    public native NavigationManager$Error setRouteRequestInterval(int i);

    public native synchronized NavigationManager$Error setSpeedWarningOptions(float f, float f2, float f3);

    public native synchronized boolean setSpeedWarningState(boolean z);

    public native void setUseEstimatedPosition(boolean z);

    public native synchronized void stopSpeedWarning();

    public native NavigationManager$Error zoomIn();

    public native NavigationManager$Error zoomOut();

    public static synchronized NavigationManagerImpl a() {
        NavigationManagerImpl navigationManagerImpl;
        synchronized (NavigationManagerImpl.class) {
            if (g == null) {
                try {
                    g = new NavigationManagerImpl();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            navigationManagerImpl = g;
        }
        return navigationManagerImpl;
    }

    private NavigationManagerImpl() throws Exception {
        super(true);
        createNavigationManagerNative();
        PositioningManager.getInstance();
        this.h = new AudioPlayer(MapsEngine.e());
        this.h.a(this.B);
        this.i = new Vibra(MapsEngine.e(), this.u);
        this.m = TrafficWarnerImpl.a(new TrafficWarnerImpl());
    }

    public TrafficWarner b() {
        return this.m;
    }

    public AudioPlayer c() {
        return this.h;
    }

    private synchronized void B() {
        bj.a(b, ">>", new Object[0]);
        if (this.w == null) {
            this.w = new a(this);
        }
        this.w.a(150);
        this.w.a();
        as.b(true);
        bj.a(b, "<<", new Object[0]);
    }

    public void a(WeakReference<NavigationManager$RoadView$Listener> weakReference) {
        this.x.a(weakReference);
    }

    public void a(NavigationManager$RoadView$Listener navigationManager$RoadView$Listener) {
        this.x.b(navigationManager$RoadView$Listener);
    }

    private void a(GeoPositionImpl geoPositionImpl) {
        bj.a(b, "IN - loc=%s", new Object[]{geoPositionImpl.toString()});
        synchronized (this) {
            MapImpl mapImpl = this.j;
        }
        if (!(!geoPositionImpl.isValid() || mapImpl == null || mapImpl.r())) {
            NavigationManager$MapUpdateMode l = l();
            if (l == NavigationManager$MapUpdateMode.POSITION || l == NavigationManager$MapUpdateMode.POSITION_ANIMATION) {
                float heading;
                bj.a(b, "Calling m_map.setCenter(%s) to move map ...", new Object[]{geoPositionImpl.toString()});
                GeoCoordinateImpl geoCoordinateImpl = GeoCoordinateImpl.get(geoPositionImpl.a());
                Map$Animation map$Animation = l == NavigationManager$MapUpdateMode.POSITION ? Map$Animation.NONE : Map$Animation.BOW;
                if (geoPositionImpl.getHeading() != 1.073741824E9d) {
                    heading = (float) ((int) geoPositionImpl.getHeading());
                } else {
                    heading = -1.0f;
                }
                mapImpl.a(geoCoordinateImpl, map$Animation, Map.MOVE_PRESERVE_ZOOM_LEVEL, heading, -1.0f);
            }
        }
        bj.a(b, "OUT", new Object[0]);
    }

    @HybridPlusNative
    private synchronized void positionUpdated(final MatchedGeoPositionImpl matchedGeoPositionImpl) {
        final GeoPosition a = MatchedGeoPositionImpl.a(matchedGeoPositionImpl);
        if (a.isValid()) {
            this.P.a(new fc$a<NavigationManager$PositionListener>(this) {
                final /* synthetic */ NavigationManagerImpl b;

                public void a(NavigationManager$PositionListener navigationManager$PositionListener) {
                    bj.a(NavigationManagerImpl.b, "call listener(%s - 0x%08x).onNavigationPositionUpdated...", new Object[]{navigationManager$PositionListener.getClass().getSimpleName(), Integer.valueOf(navigationManager$PositionListener.hashCode())});
                    navigationManager$PositionListener.onPositionUpdated(a);
                }
            }, new Runnable(this) {
                final /* synthetic */ NavigationManagerImpl b;

                public void run() {
                    this.b.a(matchedGeoPositionImpl);
                }
            });
        }
    }

    @HybridPlusNative
    private synchronized void navigationEnded(int i) {
        boolean z = true;
        synchronized (this) {
            bj.a(b, ">> navigationEnded state=%s mode=%s", new Object[]{m().toString(), n().toString()});
            final NavigationManager$NavigationMode b = b(i);
            if (this.q != null) {
                p pVar = this.p;
                TransportMode transportMode = this.q.c().getRouteOptions().getTransportMode();
                if (x() != NavigationManager$TrafficAvoidanceMode.DYNAMIC) {
                    z = false;
                }
                pVar.a(transportMode, z, getElapsedDistance(), false, true, false);
                this.q = null;
                if (!(ck.b() || this.r == null)) {
                    this.r.a(cj.a("guidance", "reached"), 0.0d, true);
                }
            }
            if (b == NavigationManager$NavigationMode.TRACKING) {
                this.p.a(getElapsedDistance());
            }
            as.b(false);
            E();
            this.L.b(new fc$a<NavigationManager$NavigationManagerEventListener>(this) {
                final /* synthetic */ NavigationManagerImpl b;

                public void a(NavigationManager$NavigationManagerEventListener navigationManager$NavigationManagerEventListener) {
                    navigationManager$NavigationManagerEventListener.onEnded(b);
                }
            });
            b().clear();
            bj.a(b, "<< state=%s mode=%s", new Object[]{m().toString(), n().toString()});
        }
    }

    @HybridPlusNative
    private synchronized void newInstruction() {
        a("maneuver");
        this.M.b(new fc$a<NavigationManager$NewInstructionEventListener>(this) {
            final /* synthetic */ NavigationManagerImpl a;

            {
                this.a = r1;
            }

            public void a(NavigationManager$NewInstructionEventListener navigationManager$NewInstructionEventListener) {
                navigationManager$NewInstructionEventListener.onNewInstructionEvent();
            }
        });
    }

    @HybridPlusNative
    private synchronized void gpsLost() {
        a("gps-lost");
        if (!ck.b()) {
            this.s = new ch();
        }
        this.G.b(new fc$a<NavigationManager$GpsSignalListener>(this) {
            final /* synthetic */ NavigationManagerImpl a;

            {
                this.a = r1;
            }

            public void a(NavigationManager$GpsSignalListener navigationManager$GpsSignalListener) {
                navigationManager$GpsSignalListener.onGpsLost();
            }
        });
    }

    @HybridPlusNative
    private synchronized void gpsRestored() {
        if (!(ck.b() || this.s == null)) {
            this.s.a(cj.a("guidance", "gps-restored"), 0.0d, true);
        }
        this.G.b(new fc$a<NavigationManager$GpsSignalListener>(this) {
            final /* synthetic */ NavigationManagerImpl a;

            {
                this.a = r1;
            }

            public void a(NavigationManager$GpsSignalListener navigationManager$GpsSignalListener) {
                navigationManager$GpsSignalListener.onGpsRestored();
            }
        });
    }

    @HybridPlusNative
    private synchronized void rerouteBegin() {
        if (!ck.b()) {
            this.t = new ch();
        }
        this.F.b(new fc$a<NavigationManager$RerouteListener>(this) {
            final /* synthetic */ NavigationManagerImpl a;

            {
                this.a = r1;
            }

            public void a(NavigationManager$RerouteListener navigationManager$RerouteListener) {
                navigationManager$RerouteListener.onRerouteBegin();
            }
        });
    }

    @HybridPlusNative
    private synchronized void rerouteEnd(final RouteImpl routeImpl) {
        boolean z = true;
        synchronized (this) {
            if (!(ck.b() || this.t == null)) {
                this.t.a(cj.a("guidance", "reroute"), 0.0d, true);
            }
            p pVar = this.p;
            TransportMode transportMode = routeImpl.c().getRouteOptions().getTransportMode();
            if (x() != NavigationManager$TrafficAvoidanceMode.DYNAMIC) {
                z = false;
            }
            pVar.a(transportMode, z, getElapsedDistance(), true, false, false);
            this.F.b(new fc$a<NavigationManager$RerouteListener>(this) {
                final /* synthetic */ NavigationManagerImpl b;

                public void a(NavigationManager$RerouteListener navigationManager$RerouteListener) {
                    navigationManager$RerouteListener.onRerouteEnd(RouteImpl.create(routeImpl));
                }
            });
        }
    }

    @HybridPlusNative
    private synchronized void routeUpdated(RouteImpl routeImpl) {
        a("route-updated");
        final Route create = RouteImpl.create(routeImpl);
        this.L.b(new fc$a<NavigationManager$NavigationManagerEventListener>(this) {
            final /* synthetic */ NavigationManagerImpl b;

            public void a(NavigationManager$NavigationManagerEventListener navigationManager$NavigationManagerEventListener) {
                navigationManager$NavigationManagerEventListener.onRouteUpdated(create);
            }
        });
    }

    @HybridPlusNative
    private synchronized void runningStateChanged(int i) {
        this.c = a(i);
        if (this.j != null && (l() == NavigationManager$MapUpdateMode.ROADVIEW || l() == NavigationManager$MapUpdateMode.ROADVIEW_NOZOOM)) {
            if (m() != NavigationManager$NavigationState.RUNNING) {
                this.j.f(false);
            } else {
                this.j.f(true);
            }
        }
        this.L.b(new fc$a<NavigationManager$NavigationManagerEventListener>(this) {
            final /* synthetic */ NavigationManagerImpl a;

            {
                this.a = r1;
            }

            public void a(NavigationManager$NavigationManagerEventListener navigationManager$NavigationManagerEventListener) {
                navigationManager$NavigationManagerEventListener.onRunningStateChanged();
            }
        });
    }

    @HybridPlusNative
    private synchronized void rerouteFailed() {
        this.F.b(new fc$a<NavigationManager$RerouteListener>(this) {
            final /* synthetic */ NavigationManagerImpl a;

            {
                this.a = r1;
            }

            public void a(NavigationManager$RerouteListener navigationManager$RerouteListener) {
                navigationManager$RerouteListener.onRerouteFailed();
            }
        });
    }

    @HybridPlusNative
    private synchronized void navigationModeChanged(int i) {
        this.e = b(i);
        this.L.b(new fc$a<NavigationManager$NavigationManagerEventListener>(this) {
            final /* synthetic */ NavigationManagerImpl a;

            {
                this.a = r1;
            }

            public void a(NavigationManager$NavigationManagerEventListener navigationManager$NavigationManagerEventListener) {
                navigationManager$NavigationManagerEventListener.onNavigationModeChanged();
            }
        });
    }

    @HybridPlusNative
    private synchronized void speedExceeded(final String str, final float f) {
        a("speed-alert");
        this.O.b(new fc$a<NavigationManager$SpeedWarningListener>(this) {
            final /* synthetic */ NavigationManagerImpl c;

            public void a(NavigationManager$SpeedWarningListener navigationManager$SpeedWarningListener) {
                navigationManager$SpeedWarningListener.onSpeedExceeded(str, f);
            }
        });
    }

    @HybridPlusNative
    private synchronized void speedExceededEnd(final String str, final float f) {
        this.O.b(new fc$a<NavigationManager$SpeedWarningListener>(this) {
            final /* synthetic */ NavigationManagerImpl c;

            public void a(NavigationManager$SpeedWarningListener navigationManager$SpeedWarningListener) {
                navigationManager$SpeedWarningListener.onSpeedExceededEnd(str, f);
            }
        });
    }

    @HybridPlusNative
    private synchronized void showLaneInfo(List<LaneInfoImpl> list, GeoCoordinateImpl geoCoordinateImpl) {
        final GeoCoordinate create = GeoCoordinateImpl.create(geoCoordinateImpl);
        final List a = LaneInfoImpl.a((List) list);
        this.J.b(new fc$a<LaneInfoListener>(this) {
            final /* synthetic */ NavigationManagerImpl c;

            public void a(LaneInfoListener laneInfoListener) {
                laneInfoListener.onShowLaneInfo(a, create);
            }
        });
        if (!(this.q == null || this.J.b())) {
            this.p.a(this.q.c().getRouteOptions().getTransportMode(), x() == NavigationManager$TrafficAvoidanceMode.DYNAMIC);
        }
    }

    @HybridPlusNative
    private synchronized void hideLaneInfo(List<LaneInfoImpl> list) {
        final List a = LaneInfoImpl.a((List) list);
        this.J.b(new fc$a<LaneInfoListener>(this) {
            final /* synthetic */ NavigationManagerImpl b;

            public void a(LaneInfoListener laneInfoListener) {
                laneInfoListener.onHideLaneInfo(a);
            }
        });
    }

    @HybridPlusNative
    private synchronized void laneInformation(List<LaneInformationImpl> list, RoadElementImpl roadElementImpl) {
        final List a = LaneInformationImpl.a((List) list);
        final RoadElement a2 = RoadElementImpl.a(roadElementImpl);
        this.K.b(new fc$a<NavigationManager$LaneInformationListener>(this) {
            final /* synthetic */ NavigationManagerImpl c;

            public void a(NavigationManager$LaneInformationListener navigationManager$LaneInformationListener) {
                navigationManager$LaneInformationListener.onLaneInformation(a, a2);
            }
        });
        if (!(this.q == null || this.K.b())) {
            this.p.a(this.q.c().getRouteOptions().getTransportMode(), x() == NavigationManager$TrafficAvoidanceMode.DYNAMIC);
        }
    }

    @HybridPlusNative
    private synchronized void enhancedLaneInfo(List<EnhancedRoadLaneImpl> list) {
        final List a = EnhancedRoadLaneImpl.a((List) list);
        this.J.b(new fc$a<LaneInfoListener>(this) {
            final /* synthetic */ NavigationManagerImpl b;

            public void a(LaneInfoListener laneInfoListener) {
                laneInfoListener.onEnhancedLaneInfo(a);
            }
        });
    }

    @HybridPlusNative
    private synchronized void countryInfo(final String str, final String str2) {
        this.L.b(new fc$a<NavigationManager$NavigationManagerEventListener>(this) {
            final /* synthetic */ NavigationManagerImpl c;

            public void a(NavigationManager$NavigationManagerEventListener navigationManager$NavigationManagerEventListener) {
                navigationManager$NavigationManagerEventListener.onCountryInfo(str, str2);
            }
        });
    }

    @HybridPlusNative
    private synchronized void realisticViewShow(int i, ImageImpl imageImpl, ImageImpl imageImpl2) {
        boolean z = true;
        synchronized (this) {
            final NavigationManager$AspectRatio d = d(i);
            final Image a = ImageImpl.a(imageImpl);
            final Image a2 = ImageImpl.a(imageImpl2);
            this.D.b(new fc$a<NavigationManager$RealisticViewListener>(this) {
                final /* synthetic */ NavigationManagerImpl d;

                public void a(NavigationManager$RealisticViewListener navigationManager$RealisticViewListener) {
                    navigationManager$RealisticViewListener.onRealisticViewShow(d, a, a2);
                }
            });
            if (!(a == null || this.q == null || this.D.b())) {
                this.p.d(this.q.c().getRouteOptions().getTransportMode(), x() == NavigationManager$TrafficAvoidanceMode.DYNAMIC);
            }
            if (!(a2 == null || this.q == null || this.D.b())) {
                p pVar = this.p;
                TransportMode transportMode = this.q.c().getRouteOptions().getTransportMode();
                if (x() != NavigationManager$TrafficAvoidanceMode.DYNAMIC) {
                    z = false;
                }
                pVar.c(transportMode, z);
            }
        }
    }

    @HybridPlusNative
    private synchronized void realisticViewNextManeuver(int i, ImageImpl imageImpl, ImageImpl imageImpl2) {
        boolean z = true;
        synchronized (this) {
            final NavigationManager$AspectRatio d = d(i);
            final Image a = ImageImpl.a(imageImpl);
            final Image a2 = ImageImpl.a(imageImpl2);
            this.D.b(new fc$a<NavigationManager$RealisticViewListener>(this) {
                final /* synthetic */ NavigationManagerImpl d;

                public void a(NavigationManager$RealisticViewListener navigationManager$RealisticViewListener) {
                    navigationManager$RealisticViewListener.onRealisticViewNextManeuver(d, a, a2);
                }
            });
            if (!(a == null || this.q == null || this.D.b())) {
                this.p.d(this.q.c().getRouteOptions().getTransportMode(), x() == NavigationManager$TrafficAvoidanceMode.DYNAMIC);
            }
            if (!(a2 == null || this.q == null || this.D.b())) {
                p pVar = this.p;
                TransportMode transportMode = this.q.c().getRouteOptions().getTransportMode();
                if (x() != NavigationManager$TrafficAvoidanceMode.DYNAMIC) {
                    z = false;
                }
                pVar.c(transportMode, z);
            }
        }
    }

    @HybridPlusNative
    private synchronized void realisticViewHide() {
        this.D.b(new fc$a<NavigationManager$RealisticViewListener>(this) {
            final /* synthetic */ NavigationManagerImpl a;

            {
                this.a = r1;
            }

            public void a(NavigationManager$RealisticViewListener navigationManager$RealisticViewListener) {
                navigationManager$RealisticViewListener.onRealisticViewHide();
            }
        });
    }

    @HybridPlusNative
    private synchronized void safetySpotNotification(final SafetySpotNotificationImpl safetySpotNotificationImpl) {
        this.N.b(new fc$a<NavigationManager$SafetySpotListener>(this) {
            final /* synthetic */ NavigationManagerImpl b;

            public void a(NavigationManager$SafetySpotListener navigationManager$SafetySpotListener) {
                navigationManager$SafetySpotListener.onSafetySpot(SafetySpotNotificationImpl.a(safetySpotNotificationImpl));
            }
        });
        if (!(this.q == null || this.N.b())) {
            this.p.b(this.q.c().getRouteOptions().getTransportMode(), x() == NavigationManager$TrafficAvoidanceMode.DYNAMIC);
        }
    }

    @HybridPlusNative
    private synchronized void rerouteDueToTraffic(RouteImpl routeImpl) {
        final Route create = RouteImpl.create(routeImpl);
        this.C.b(new fc$a<NavigationManager$TrafficRerouteListener>(this) {
            final /* synthetic */ NavigationManagerImpl b;

            public void a(NavigationManager$TrafficRerouteListener navigationManager$TrafficRerouteListener) {
                navigationManager$TrafficRerouteListener.onTrafficRerouted(create);
            }
        });
    }

    @HybridPlusNative
    private synchronized void roadViewPositionChanged(GeoCoordinateImpl geoCoordinateImpl) {
        final GeoCoordinate create = GeoCoordinateImpl.create(geoCoordinateImpl);
        this.x.b(new fc$a<NavigationManager$RoadView$Listener>(this) {
            final /* synthetic */ NavigationManagerImpl b;

            public void a(NavigationManager$RoadView$Listener navigationManager$RoadView$Listener) {
                navigationManager$RoadView$Listener.onPositionChanged(create);
            }
        });
    }

    synchronized void d() {
        this.E.b(new fc$a<NavigationManager$AudioFeedbackListener>(this) {
            final /* synthetic */ NavigationManagerImpl a;

            {
                this.a = r1;
            }

            public void a(NavigationManager$AudioFeedbackListener navigationManager$AudioFeedbackListener) {
                navigationManager$AudioFeedbackListener.onVibrationStart();
            }
        });
    }

    synchronized void e() {
        this.E.b(new fc$a<NavigationManager$AudioFeedbackListener>(this) {
            final /* synthetic */ NavigationManagerImpl a;

            {
                this.a = r1;
            }

            public void a(NavigationManager$AudioFeedbackListener navigationManager$AudioFeedbackListener) {
                navigationManager$AudioFeedbackListener.onVibrationEnd();
            }
        });
    }

    @HybridPlusNative
    private synchronized void maneuver() {
        this.Q.b(new fc$a<NavigationManager$ManeuverEventListener>(this) {
            final /* synthetic */ NavigationManagerImpl a;

            {
                this.a = r1;
            }

            public void a(NavigationManager$ManeuverEventListener navigationManager$ManeuverEventListener) {
                navigationManager$ManeuverEventListener.onManeuverEvent();
            }
        });
    }

    public Maneuver f() {
        return ManeuverImpl.a(native_nextManeuver());
    }

    public synchronized NavigationManager$Error a(long j) {
        NavigationManager$Error navigationManager$Error;
        bj.e(b, ">> id=" + j, new Object[0]);
        if (j > a || j < 0) {
            bj.c(b, "Voice Skin id(" + j + ") is out of bounds.", new Object[0]);
            navigationManager$Error = NavigationManager$Error.INVALID_PARAMETERS;
        } else {
            VoiceCatalogImpl a = VoiceCatalogImpl.a(VoiceCatalog.getInstance());
            if (a == null) {
                bj.b(b, "Voice Catalog does not exist.", new Object[0]);
                navigationManager$Error = NavigationManager$Error.NOT_READY;
            } else {
                VoiceSkin b = a.b(j);
                if (b != null) {
                    VoiceSkinImpl a2 = VoiceSkinImpl.a(b);
                    if (b.getOutputType() == OutputType.TTS) {
                        this.h.a(new Locale(a2.getLanguageCode()));
                    }
                    navigationManager$Error = native_setVoiceSkinId(j);
                    bj.e(b, "<< returns " + navigationManager$Error.name(), new Object[0]);
                } else {
                    bj.b(b, "Voice skin has not been downloaded yet.", new Object[0]);
                    bj.e(b, "<< returns ERROR_NOT_READY", new Object[0]);
                    navigationManager$Error = NavigationManager$Error.NOT_READY;
                }
            }
        }
        return navigationManager$Error;
    }

    public synchronized long g() {
        return native_getVoiceSkinId();
    }

    private void C() {
        if (this.z.get()) {
            a(this.y);
        }
        if (!this.A.isEmpty()) {
            Iterator it = this.A.iterator();
            while (it.hasNext()) {
                a((NavigationManager$AspectRatio) it.next());
            }
        }
    }

    public synchronized NavigationManager$Error a(Route route, long j) {
        NavigationManager$Error navigationManager$Error;
        bj.e(b, ">> speed=%d", new Object[]{Long.valueOf(j)});
        if (j > a || j < 0 || c(route)) {
            navigationManager$Error = NavigationManager$Error.INVALID_PARAMETERS;
        } else {
            if (!(m() == NavigationManager$NavigationState.IDLE || n() == NavigationManager$NavigationMode.SIMULATION)) {
                j();
            }
            navigationManager$Error = NavigationManager$Error.NONE;
            RouteImpl a = RouteImpl.a(route);
            if (a(D())) {
                navigationManager$Error = native_simulate(a, j);
                if (navigationManager$Error == NavigationManager$Error.NONE) {
                    B();
                    C();
                }
            } else {
                navigationManager$Error = NavigationManager$Error.POSITIONING_FAILED;
            }
            bj.e(b, "<< returns " + navigationManager$Error.name(), new Object[0]);
        }
        return navigationManager$Error;
    }

    public synchronized NavigationManager$Error a(Route route) {
        NavigationManager$Error navigationManager$Error;
        boolean z = true;
        synchronized (this) {
            bj.a(b, ">> startNavigation", new Object[0]);
            if (!ck.b()) {
                this.r = new ch();
                a("start-navigation");
            }
            if (c(route)) {
                navigationManager$Error = NavigationManager$Error.INVALID_PARAMETERS;
            } else {
                if (!(m() == NavigationManager$NavigationState.IDLE || n() == NavigationManager$NavigationMode.NAVIGATION)) {
                    j();
                }
                navigationManager$Error = NavigationManager$Error.NONE;
                RouteImpl a = RouteImpl.a(route);
                if (a(D())) {
                    navigationManager$Error = native_navigateRoute(a);
                    if (navigationManager$Error == NavigationManager$Error.NONE) {
                        B();
                        C();
                    }
                } else {
                    navigationManager$Error = NavigationManager$Error.POSITIONING_FAILED;
                }
                bj.a(b, "<< returns " + navigationManager$Error.name(), new Object[0]);
                this.q = a;
                p pVar;
                TransportMode transportMode;
                if (navigationManager$Error != NavigationManager$Error.NONE) {
                    pVar = this.p;
                    transportMode = a.c().getRouteOptions().getTransportMode();
                    if (x() != NavigationManager$TrafficAvoidanceMode.DYNAMIC) {
                        z = false;
                    }
                    pVar.a(transportMode, z, 0, false, false, true);
                } else if (PositioningManagerImpl.a(PositioningManager.getInstance()).i() == com.nokia.maps.PositioningManagerImpl.a.AUTOMOTIVE.ordinal()) {
                    pVar = this.p;
                    transportMode = a.c().getRouteOptions().getTransportMode();
                    if (x() != NavigationManager$TrafficAvoidanceMode.DYNAMIC) {
                        z = false;
                    }
                    pVar.f(transportMode, z);
                } else {
                    boolean z2;
                    p pVar2 = this.p;
                    transportMode = a.c().getRouteOptions().getTransportMode();
                    if (x() == NavigationManager$TrafficAvoidanceMode.DYNAMIC) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    pVar2.e(transportMode, z2);
                }
            }
        }
        return navigationManager$Error;
    }

    public synchronized NavigationManager$Error h() {
        NavigationManager$Error navigationManager$Error;
        bj.a(b, ">> startTracking mode=%s state=%s", new Object[]{n().toString(), m().toString()});
        if (!(m() == NavigationManager$NavigationState.IDLE || n() == NavigationManager$NavigationMode.TRACKING)) {
            j();
        }
        navigationManager$Error = NavigationManager$Error.NONE;
        if (a(D())) {
            navigationManager$Error = native_startTracking();
            if (navigationManager$Error == NavigationManager$Error.NONE) {
                if (this.j != null) {
                    this.j.k(true);
                }
                B();
            }
        } else {
            navigationManager$Error = NavigationManager$Error.POSITIONING_FAILED;
        }
        bj.a(b, "<< mode=%s state=%s", new Object[]{n().toString(), m().toString()});
        return navigationManager$Error;
    }

    public synchronized void a(Map map) {
        if (map != null) {
            if (this.j != null) {
                MapImpl mapImpl = MapImpl.get(map);
                if (!(mapImpl == null || this.j == mapImpl)) {
                    native_removeFromMap(this.j);
                    this.j = mapImpl;
                    this.j.k(true);
                    native_showOnMap(this.j);
                }
            } else {
                this.j = MapImpl.get(map);
                if (this.j != null) {
                    this.j.k(true);
                    native_showOnMap(this.j);
                }
            }
        } else if (this.j != null) {
            native_removeFromMap(this.j);
            this.j.k(false);
            this.j.f(false);
            this.j = null;
        }
    }

    public synchronized void i() {
        boolean z = true;
        synchronized (this) {
            bj.a(b, ">>", new Object[0]);
            if (!(ck.b() || this.r == null)) {
                this.r.a(cj.a("guidance", "stop-navigation"), 0.0d, true);
            }
            if (this.q != null) {
                p pVar = this.p;
                TransportMode transportMode = this.q.c().getRouteOptions().getTransportMode();
                if (x() != NavigationManager$TrafficAvoidanceMode.DYNAMIC) {
                    z = false;
                }
                pVar.a(transportMode, z, getElapsedDistance(), false, false, false);
                this.q = null;
            }
            native_stopNavigation();
            as.b(false);
            bj.a(b, "<<", new Object[0]);
        }
    }

    public synchronized void j() {
        bj.a(b, ">> pauseNavigation", new Object[0]);
        E();
        native_pauseNavigation();
        as.b(false);
    }

    public synchronized NavigationManager$Error k() {
        NavigationManager$Error navigationManager$Error;
        bj.a(b, ">> resumeNavigation", new Object[0]);
        navigationManager$Error = NavigationManager$Error.NONE;
        if (!a(D())) {
            navigationManager$Error = NavigationManager$Error.POSITIONING_FAILED;
        }
        if (navigationManager$Error == NavigationManager$Error.NONE) {
            navigationManager$Error = native_resumeNavigation();
            if (navigationManager$Error == NavigationManager$Error.NONE) {
                B();
            }
        }
        bj.a(b, "<< returns " + navigationManager$Error.name(), new Object[0]);
        return navigationManager$Error;
    }

    public NavigationManager$MapUpdateMode l() {
        return this.d;
    }

    public NavigationManager$Error a(NavigationManager$MapUpdateMode navigationManager$MapUpdateMode) {
        if (this.v != null && this.v.c) {
            this.v.c = false;
        }
        return b(navigationManager$MapUpdateMode);
    }

    private NavigationManager$Error b(final NavigationManager$MapUpdateMode navigationManager$MapUpdateMode) {
        NavigationManager$Error mapUpdateMode = setMapUpdateMode(navigationManager$MapUpdateMode.value());
        if (mapUpdateMode != NavigationManager$Error.NONE) {
            bj.c(b, "ERROR: failed to set map update mode to %s. (%s)", new Object[]{navigationManager$MapUpdateMode.toString(), mapUpdateMode.toString()});
        } else {
            synchronized (this) {
                this.d = navigationManager$MapUpdateMode;
                if (this.j != null && m() == NavigationManager$NavigationState.RUNNING) {
                    if (navigationManager$MapUpdateMode == NavigationManager$MapUpdateMode.ROADVIEW || navigationManager$MapUpdateMode == NavigationManager$MapUpdateMode.ROADVIEW_NOZOOM) {
                        this.j.f(true);
                    } else {
                        this.j.f(false);
                    }
                }
            }
            this.L.b(new fc$a<NavigationManager$NavigationManagerEventListener>(this) {
                final /* synthetic */ NavigationManagerImpl b;

                public void a(NavigationManager$NavigationManagerEventListener navigationManager$NavigationManagerEventListener) {
                    navigationManager$NavigationManagerEventListener.onMapUpdateModeChanged(navigationManager$MapUpdateMode);
                }
            });
        }
        return mapUpdateMode;
    }

    public NavigationManager$NavigationState m() {
        return this.c;
    }

    private NavigationManager$NavigationState a(int i) {
        switch (i) {
            case 1:
                return NavigationManager$NavigationState.RUNNING;
            case 2:
                return NavigationManager$NavigationState.PAUSED;
            default:
                return NavigationManager$NavigationState.IDLE;
        }
    }

    public NavigationManager$NavigationMode n() {
        return this.e;
    }

    private NavigationManager$NavigationMode b(int i) {
        switch (i) {
            case 0:
                return NavigationManager$NavigationMode.NONE;
            case 1:
                return NavigationManager$NavigationMode.SIMULATION;
            case 2:
                return NavigationManager$NavigationMode.NAVIGATION;
            case 3:
                return NavigationManager$NavigationMode.TRACKING;
            default:
                return NavigationManager$NavigationMode.NONE;
        }
    }

    private LocationMethod D() {
        return PositioningManager.getInstance().isActive() ? PositioningManager.getInstance().getLocationMethod() : LocationMethod.GPS_NETWORK;
    }

    public Maneuver o() {
        return ManeuverImpl.a(native_getAfterNextManeuver());
    }

    public RouteTta a(boolean z, TrafficPenaltyMode trafficPenaltyMode) {
        if (n() == NavigationManager$NavigationMode.SIMULATION || n() == NavigationManager$NavigationMode.NAVIGATION) {
            return RouteTtaImpl.a(getTtaNative(z, trafficPenaltyMode.value()));
        }
        return null;
    }

    public NavigationManager$Error a(NavigationManager$UnitSystem navigationManager$UnitSystem) {
        NavigationManager$Error distanceUnit = setDistanceUnit(navigationManager$UnitSystem.value());
        if (distanceUnit != NavigationManager$Error.NONE) {
            bj.c(b, "unit system(", new Object[]{navigationManager$UnitSystem.toString(), ") returns ", distanceUnit.toString()});
        }
        return distanceUnit;
    }

    public NavigationManager$UnitSystem p() {
        NavigationManager$UnitSystem navigationManager$UnitSystem = NavigationManager$UnitSystem.METRIC;
        int distanceUnitNative = getDistanceUnitNative();
        switch (distanceUnitNative) {
            case 0:
                return navigationManager$UnitSystem;
            case 1:
                return NavigationManager$UnitSystem.IMPERIAL;
            case 2:
                return NavigationManager$UnitSystem.IMPERIAL_US;
            default:
                throw new RuntimeException("Unknown distance unit: " + distanceUnitNative);
        }
    }

    public void q() {
        synchronized (this) {
            if (this.k == null) {
                this.k = new Timer();
                repeatVoiceCommand_native(this.h);
                this.k.schedule(new TimerTask(this) {
                    final /* synthetic */ NavigationManagerImpl a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        synchronized (this.a) {
                            this.a.k = null;
                        }
                    }
                }, (long) f);
            }
        }
    }

    public void a(NavigationManager$RealisticViewMode navigationManager$RealisticViewMode) {
        setRealisticViewModeNative(navigationManager$RealisticViewMode.value());
        this.y = navigationManager$RealisticViewMode;
        this.z.set(true);
    }

    public NavigationManager$RealisticViewMode r() {
        return this.y;
    }

    public EnumSet<NavigationManager$AspectRatio> s() {
        return this.A;
    }

    public boolean a(NavigationManager$AspectRatio navigationManager$AspectRatio) {
        this.A.add(navigationManager$AspectRatio);
        return addRealisticViewAspectRatioNative(navigationManager$AspectRatio.value());
    }

    public boolean v() {
        this.A.clear();
        return clearRealisticViewAspectRatiosNative();
    }

    public boolean a(EnumSet<NavigationManager$NaturalGuidanceMode> enumSet) {
        Iterator it = enumSet.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = ((NavigationManager$NaturalGuidanceMode) it.next()).value() | i;
        }
        return setNaturalGuidanceNative(i);
    }

    public EnumSet<NavigationManager$NaturalGuidanceMode> w() {
        int naturalGuidanceNative = getNaturalGuidanceNative();
        EnumSet<NavigationManager$NaturalGuidanceMode> noneOf = EnumSet.noneOf(NavigationManager$NaturalGuidanceMode.class);
        for (NavigationManager$NaturalGuidanceMode navigationManager$NaturalGuidanceMode : NavigationManager$NaturalGuidanceMode.values()) {
            if ((navigationManager$NaturalGuidanceMode.value() & naturalGuidanceNative) > 0) {
                noneOf.add(navigationManager$NaturalGuidanceMode);
            }
        }
        return noneOf;
    }

    public NavigationManager$TrafficAvoidanceMode x() {
        switch (getTrafficAvoidanceModeNative()) {
            case 0:
                return NavigationManager$TrafficAvoidanceMode.DYNAMIC;
            case 1:
                return NavigationManager$TrafficAvoidanceMode.MANUAL;
            case 2:
                return NavigationManager$TrafficAvoidanceMode.DISABLE;
            default:
                return NavigationManager$TrafficAvoidanceMode.DISABLE;
        }
    }

    public NavigationManager$Error a(NavigationManager$TrafficAvoidanceMode navigationManager$TrafficAvoidanceMode) {
        NavigationManager$Error trafficAvoidanceMode = setTrafficAvoidanceMode(navigationManager$TrafficAvoidanceMode.value());
        if (trafficAvoidanceMode != NavigationManager$Error.NONE) {
            bj.c(b, "ERROR: failed to set traffic avoidance mode to %s. (%s)", new Object[]{navigationManager$TrafficAvoidanceMode.toString(), trafficAvoidanceMode.toString()});
        }
        return trafficAvoidanceMode;
    }

    public NavigationManager$Error b(Route route) {
        if (c(route)) {
            return NavigationManager$Error.INVALID_PARAMETERS;
        }
        return setRouteNative(RouteImpl.a(route));
    }

    private boolean a(LocationMethod locationMethod) {
        boolean isActive = PositioningManager.getInstance().isActive();
        synchronized (this.n) {
            if (!(locationMethod == LocationMethod.NONE || locationMethod == this.l)) {
                isActive = PositioningManager.getInstance().start(locationMethod);
                if (isActive) {
                    this.l = locationMethod;
                }
            }
        }
        return isActive;
    }

    private void E() {
        synchronized (this.n) {
            if (this.l != LocationMethod.NONE) {
                PositioningManager.getInstance().stop();
                this.l = LocationMethod.NONE;
            }
        }
    }

    private static void a(String str) {
        if (!ck.b()) {
            ck.a().a(cj.a("guidance", str), 0.0d, 0.0d, true);
        }
    }

    public long b(boolean z, TrafficPenaltyMode trafficPenaltyMode) {
        return getEtaNative(z, trafficPenaltyMode.value());
    }

    public void a(NavigationManager$RoadView$Orientation navigationManager$RoadView$Orientation) {
        setOrientationNative(navigationManager$RoadView$Orientation.value());
    }

    public NavigationManager$RoadView$Orientation y() {
        return c(getOrientationNative());
    }

    private static NavigationManager$RoadView$Orientation c(int i) {
        switch (i) {
            case 1:
                return NavigationManager$RoadView$Orientation.NORTH_UP;
            default:
                return NavigationManager$RoadView$Orientation.DYNAMIC;
        }
    }

    @HybridPlusNative
    private static NavigationManager$Error getError(int i) {
        switch (i) {
            case 0:
                return NavigationManager$Error.NONE;
            case 1:
                return NavigationManager$Error.GUIDANCE_NOT_READY;
            case 2:
                return NavigationManager$Error.POSITIONING_FAILED;
            case 3:
                return NavigationManager$Error.NOT_READY;
            case 4:
                return NavigationManager$Error.OUT_OF_MEMORY;
            case 5:
                return NavigationManager$Error.INVALID_PARAMETERS;
            case 6:
                return NavigationManager$Error.INVALID_OPERATION;
            case 7:
                return NavigationManager$Error.NOT_FOUND;
            case 8:
                return NavigationManager$Error.ABORTED;
            case 9:
                return NavigationManager$Error.OPERATION_NOT_ALLOWED;
            case 10:
                return NavigationManager$Error.INVALID_CREDENTIALS;
            case 11:
                return NavigationManager$Error.UNKNOWN;
            default:
                return NavigationManager$Error.UNKNOWN;
        }
    }

    private static NavigationManager$AspectRatio d(int i) {
        switch (i) {
            case 0:
                return NavigationManager$AspectRatio.AR_16x9;
            case 2:
                return NavigationManager$AspectRatio.AR_5x3;
            case 3:
                return NavigationManager$AspectRatio.AR_4x3;
            default:
                return NavigationManager$AspectRatio.AR_3x5;
        }
    }

    private boolean c(Route route) {
        if (route != null) {
            RouteOptions routeOptions = null;
            RoutePlan routePlan = route.getRoutePlan();
            if (routePlan != null) {
                routeOptions = routePlan.getRouteOptions();
            }
            if (routeOptions == null || routeOptions.getTransportMode() == TransportMode.PUBLIC_TRANSPORT || (routeOptions instanceof UMRouteOptions) || (route instanceof UMRoute)) {
                return true;
            }
        }
        return false;
    }

    public boolean z() {
        if (m() != NavigationManager$NavigationState.RUNNING) {
            return false;
        }
        if (l() == NavigationManager$MapUpdateMode.ROADVIEW || l() == NavigationManager$MapUpdateMode.ROADVIEW_NOZOOM) {
            return true;
        }
        return false;
    }

    @HybridPlusNative
    private void onTrafficRerouteState(final int i) {
        this.C.b(new fc$a<NavigationManager$TrafficRerouteListener>(this) {
            final /* synthetic */ NavigationManagerImpl b;

            public void a(NavigationManager$TrafficRerouteListener navigationManager$TrafficRerouteListener) {
                navigationManager$TrafficRerouteListener.onTrafficRerouteState(TrafficEnabledRoutingState.values()[i]);
            }
        });
    }

    @HybridPlusNative
    private void onTrafficRerouteBegin(final TrafficNotificationImpl trafficNotificationImpl) {
        this.C.b(new fc$a<NavigationManager$TrafficRerouteListener>(this) {
            final /* synthetic */ NavigationManagerImpl b;

            public void a(NavigationManager$TrafficRerouteListener navigationManager$TrafficRerouteListener) {
                navigationManager$TrafficRerouteListener.onTrafficRerouteBegin(TrafficNotificationImpl.a(trafficNotificationImpl));
            }
        });
    }

    @HybridPlusNative
    private void onTrafficRerouteFailed(final TrafficNotificationImpl trafficNotificationImpl) {
        this.C.b(new fc$a<NavigationManager$TrafficRerouteListener>(this) {
            final /* synthetic */ NavigationManagerImpl b;

            public void a(NavigationManager$TrafficRerouteListener navigationManager$TrafficRerouteListener) {
                navigationManager$TrafficRerouteListener.onTrafficRerouteFailed(TrafficNotificationImpl.a(trafficNotificationImpl));
            }
        });
    }

    public void b(WeakReference<?> weakReference) {
        dy.a((Object) weakReference, "Cannot add null WeakReference listener");
        Object obj = weakReference.get();
        dy.a(obj, "Cannot add null listener");
        if (NavigationManager$AudioFeedbackListener.class.isInstance(obj)) {
            this.E.a(new WeakReference((NavigationManager$AudioFeedbackListener) obj));
        } else if (NavigationManager$GpsSignalListener.class.isInstance(obj)) {
            this.G.a(new WeakReference((NavigationManager$GpsSignalListener) obj));
        } else if (LaneInfoListener.class.isInstance(obj)) {
            this.J.a(new WeakReference((LaneInfoListener) obj));
        } else if (NavigationManager$LaneInformationListener.class.isInstance(obj)) {
            this.K.a(new WeakReference((NavigationManager$LaneInformationListener) obj));
        } else if (NavigationManager$NavigationManagerEventListener.class.isInstance(obj)) {
            this.L.a(new WeakReference((NavigationManager$NavigationManagerEventListener) obj));
        } else if (NavigationManager$NewInstructionEventListener.class.isInstance(obj)) {
            this.M.a(new WeakReference((NavigationManager$NewInstructionEventListener) obj));
        } else if (NavigationManager$PositionListener.class.isInstance(obj)) {
            this.P.a(new WeakReference((NavigationManager$PositionListener) obj));
        } else if (NavigationManager$RealisticViewListener.class.isInstance(obj)) {
            this.D.a(new WeakReference((NavigationManager$RealisticViewListener) obj));
        } else if (NavigationManager$RerouteListener.class.isInstance(obj)) {
            this.F.a(new WeakReference((NavigationManager$RerouteListener) obj));
        } else if (NavigationManager$SafetySpotListener.class.isInstance(obj)) {
            this.N.a(new WeakReference((NavigationManager$SafetySpotListener) obj));
        } else if (NavigationManager$SpeedWarningListener.class.isInstance(obj)) {
            this.O.a(new WeakReference((NavigationManager$SpeedWarningListener) obj));
        } else if (NavigationManager$TrafficRerouteListener.class.isInstance(obj)) {
            this.C.a(new WeakReference((NavigationManager$TrafficRerouteListener) obj));
        } else if (NavigationManager$ManeuverEventListener.class.isInstance(obj)) {
            this.Q.a(new WeakReference((NavigationManager$ManeuverEventListener) obj));
        }
    }

    public void a(Object obj) {
        if (obj != null) {
            if (NavigationManager$AudioFeedbackListener.class.isInstance(obj)) {
                this.E.b((NavigationManager$AudioFeedbackListener) obj);
            } else if (NavigationManager$GpsSignalListener.class.isInstance(obj)) {
                this.G.b((NavigationManager$GpsSignalListener) obj);
            } else if (LaneInfoListener.class.isInstance(obj)) {
                this.J.b((LaneInfoListener) obj);
            } else if (NavigationManager$LaneInformationListener.class.isInstance(obj)) {
                this.K.b((NavigationManager$LaneInformationListener) obj);
            } else if (NavigationManager$NavigationManagerEventListener.class.isInstance(obj)) {
                this.L.b((NavigationManager$NavigationManagerEventListener) obj);
            } else if (NavigationManager$NewInstructionEventListener.class.isInstance(obj)) {
                this.M.b((NavigationManager$NewInstructionEventListener) obj);
            } else if (NavigationManager$PositionListener.class.isInstance(obj)) {
                this.P.b((NavigationManager$PositionListener) obj);
            } else if (NavigationManager$RealisticViewListener.class.isInstance(obj)) {
                this.D.b((NavigationManager$RealisticViewListener) obj);
            } else if (NavigationManager$RerouteListener.class.isInstance(obj)) {
                this.F.b((NavigationManager$RerouteListener) obj);
            } else if (NavigationManager$SafetySpotListener.class.isInstance(obj)) {
                this.N.b((NavigationManager$SafetySpotListener) obj);
            } else if (NavigationManager$SpeedWarningListener.class.isInstance(obj)) {
                this.O.b((NavigationManager$SpeedWarningListener) obj);
            } else if (NavigationManager$TrafficRerouteListener.class.isInstance(obj)) {
                this.C.b((NavigationManager$TrafficRerouteListener) obj);
            } else if (NavigationManager$ManeuverEventListener.class.isInstance(obj)) {
                this.Q.b((NavigationManager$ManeuverEventListener) obj);
            }
        }
    }
}
