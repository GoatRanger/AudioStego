package com.here.android.mpa.guidance;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.routing.Maneuver;
import com.here.android.mpa.routing.Route;
import com.here.android.mpa.routing.Route$TrafficPenaltyMode;
import com.here.android.mpa.routing.RouteTta;
import com.nokia.maps.NavigationManagerImpl;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Internal;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class NavigationManager {
    private static volatile NavigationManager c;
    private AudioPlayer a = new AudioPlayer(this, null);
    private RoadView b = new RoadView();
    private NavigationManagerImpl d;

    @HybridPlus
    @Deprecated
    public static abstract class LaneInfoListener {
        @Deprecated
        public void onShowLaneInfo(List<LaneInfo> list, GeoCoordinate geoCoordinate) {
        }

        @Deprecated
        public void onHideLaneInfo(List<LaneInfo> list) {
        }

        @Deprecated
        public void onEnhancedLaneInfo(List<EnhancedRoadLane> list) {
        }
    }

    public static class RoadView {
        private a a;

        @HybridPlus
        public Error zoomIn() {
            return NavigationManager.getInstance().d.zoomIn();
        }

        @HybridPlus
        public Error zoomOut() {
            return NavigationManager.getInstance().d.zoomOut();
        }

        @HybridPlus
        public void setOrientation(Orientation orientation) {
            NavigationManager.getInstance().d.a(orientation);
        }

        @HybridPlus
        public Orientation getOrientation() {
            return NavigationManager.getInstance().d.y();
        }

        @HybridPlus
        public void addListener(WeakReference<Listener> weakReference) {
            NavigationManager.getInstance().d.a(weakReference);
        }

        @HybridPlus
        public void removeListener(Listener listener) {
            NavigationManager.getInstance().d.a(listener);
        }

        @HybridPlus
        @Deprecated
        public void setUseEstimatedPosition(boolean z) {
            NavigationManager.getInstance().d.setUseEstimatedPosition(z);
        }

        @HybridPlus
        @Deprecated
        public boolean getUseEstimatedPosition() {
            return NavigationManager.getInstance().d.getUseEstimatedPosition();
        }

        @HybridPlus
        @Deprecated
        public void setUseAnimation(boolean z) {
            NavigationManager.getInstance().d.setAnimationEnabled(z);
        }

        @HybridPlus
        @Deprecated
        public boolean getUseAnimation() {
            return NavigationManager.getInstance().d.getAnimationEnabled();
        }

        @HybridPlus
        public void setAnimationEnabled(boolean z) {
            NavigationManager.getInstance().d.setAnimationEnabled(z);
        }

        @HybridPlus
        public boolean getAnimationEnabled() {
            return NavigationManager.getInstance().d.getAnimationEnabled();
        }

        @Internal
        public boolean a() {
            return NavigationManager.getInstance().d.z();
        }

        private RoadView() {
            this.a = new a(null);
        }
    }

    @HybridPlus
    public AudioPlayer getAudioPlayer() {
        return this.a;
    }

    @HybridPlus
    public Maneuver getNextManeuver() {
        return this.d.f();
    }

    @HybridPlus
    public Error setVoiceSkin(VoiceSkin voiceSkin) {
        return this.d.a(voiceSkin.getId());
    }

    @HybridPlus
    public VoiceSkin getVoiceSkin() {
        return VoiceCatalog.getInstance().getLocalVoiceSkin(this.d.g());
    }

    @HybridPlus
    public Error simulate(Route route, long j) {
        return this.d.a(route, j);
    }

    @HybridPlus
    public Error startNavigation(Route route) {
        return this.d.a(route);
    }

    @HybridPlus
    public Error setRoute(Route route) {
        return this.d.b(route);
    }

    @HybridPlus
    public Error setRouteRequestInterval(int i) {
        return this.d.setRouteRequestInterval(i);
    }

    @HybridPlus
    public Error startTracking() {
        return this.d.h();
    }

    @HybridPlus
    public void stop() {
        this.d.i();
    }

    @HybridPlus
    public void pause() {
        this.d.j();
    }

    @HybridPlus
    public Error resume() {
        return this.d.k();
    }

    @HybridPlus
    public long getNextManeuverDistance() {
        return this.d.getNextManeuverDistance();
    }

    @HybridPlus
    public Maneuver getAfterNextManeuver() {
        return this.d.o();
    }

    @HybridPlus
    public long getAfterNextManeuverDistance() {
        return this.d.getAfterNextManeuverDistance();
    }

    @HybridPlus
    public long getDestinationDistance() {
        return this.d.getDestinationDistance();
    }

    @HybridPlus
    public long getElapsedDistance() {
        return this.d.getElapsedDistance();
    }

    @HybridPlus
    public double getAverageSpeed() {
        return this.d.getAverageSpeed();
    }

    @HybridPlus
    public RouteTta getTta(Route$TrafficPenaltyMode route$TrafficPenaltyMode, boolean z) {
        return this.d.a(z, route$TrafficPenaltyMode);
    }

    @HybridPlus
    public Error setDistanceUnit(UnitSystem unitSystem) {
        return this.d.a(unitSystem);
    }

    @HybridPlus
    public UnitSystem getDistanceUnit() {
        return this.d.p();
    }

    @HybridPlus
    public void repeatVoiceCommand() {
        this.d.q();
    }

    @HybridPlus
    public boolean setNaturalGuidanceMode(EnumSet<NaturalGuidanceMode> enumSet) {
        return this.d.a(enumSet);
    }

    @HybridPlus
    public EnumSet<NaturalGuidanceMode> getNaturalGuidanceMode() {
        return this.d.w();
    }

    @HybridPlus
    public RealisticViewMode getRealisticViewMode() {
        return this.d.r();
    }

    @HybridPlus
    public void setRealisticViewMode(RealisticViewMode realisticViewMode) {
        this.d.a(realisticViewMode);
    }

    @HybridPlus
    public void addRealisticViewAspectRatio(AspectRatio aspectRatio) {
        this.d.a(aspectRatio);
    }

    @HybridPlus
    public EnumSet<AspectRatio> getRealisticViewAspectRatios() {
        return this.d.s();
    }

    @HybridPlus
    public void clearRealisticViewAspectRatios() {
        this.d.v();
    }

    @HybridPlus
    public MapUpdateMode getMapUpdateMode() {
        return this.d.l();
    }

    @HybridPlus
    public Error setMapUpdateMode(MapUpdateMode mapUpdateMode) {
        return this.d.a(mapUpdateMode);
    }

    @HybridPlus
    public NavigationMode getNavigationMode() {
        return this.d.n();
    }

    @HybridPlus
    public Error setSpeedWarningOptions(float f, float f2, float f3) {
        return this.d.setSpeedWarningOptions(f, f2, f3);
    }

    @HybridPlus
    public float getLowSpeedWarningOffset() {
        return this.d.getLowSpeedWarningOffset();
    }

    @HybridPlus
    public float getHighSpeedWarningOffset() {
        return this.d.getHighSpeedWarningOffset();
    }

    @HybridPlus
    public float getHighSpeedWarningBoundary() {
        return this.d.getHighSpeedWarningBoundary();
    }

    @HybridPlus
    public boolean setSpeedWarningEnabled(boolean z) {
        return this.d.setSpeedWarningState(z);
    }

    @HybridPlus
    public boolean isSpeedWarningEnabled() {
        return this.d.getSpeedWarningState();
    }

    @HybridPlus
    public void stopSpeedWarning() {
        this.d.stopSpeedWarning();
    }

    @HybridPlus
    public EnumSet<AudioEvent> getEnabledAudioEvents() {
        return a(this.d.getAudioEvents());
    }

    private static EnumSet<AudioEvent> a(int i) {
        EnumSet<AudioEvent> noneOf = EnumSet.noneOf(AudioEvent.class);
        Iterator it = EnumSet.allOf(AudioEvent.class).iterator();
        while (it.hasNext()) {
            AudioEvent audioEvent = (AudioEvent) it.next();
            if ((AudioEvent.a(audioEvent) & i) != 0) {
                noneOf.add(audioEvent);
            }
        }
        return noneOf;
    }

    @HybridPlus
    public void setEnabledAudioEvents(EnumSet<AudioEvent> enumSet) {
        this.d.setAudioEvents(a((EnumSet) enumSet));
    }

    private static int a(EnumSet<AudioEvent> enumSet) {
        Iterator it = enumSet.iterator();
        int i = 0;
        while (it.hasNext()) {
            i = AudioEvent.a((AudioEvent) it.next()) | i;
        }
        return i;
    }

    @HybridPlus
    public NavigationState getRunningState() {
        return this.d.m();
    }

    @HybridPlus
    public TrafficWarner getTrafficWarner() {
        return this.d.b();
    }

    @HybridPlus
    public void setMap(Map map) {
        this.d.a(map);
    }

    @HybridPlus
    public Date getEta(boolean z, Route$TrafficPenaltyMode route$TrafficPenaltyMode) {
        return new Date(TimeUnit.SECONDS.toMillis(this.d.b(z, route$TrafficPenaltyMode)));
    }

    @HybridPlus
    public static NavigationManager getInstance() {
        if (c == null) {
            synchronized (NavigationManager.class) {
                if (c == null) {
                    NavigationManagerImpl a = NavigationManagerImpl.a();
                    if (a != null) {
                        c = new NavigationManager(a);
                    }
                }
            }
        }
        return c;
    }

    @HybridPlus
    public TrafficAvoidanceMode getTrafficAvoidanceMode() {
        return this.d.x();
    }

    @HybridPlus
    public Error setTrafficAvoidanceMode(TrafficAvoidanceMode trafficAvoidanceMode) {
        return this.d.a(trafficAvoidanceMode);
    }

    @HybridPlus
    public int isTtsLanguageAvailable(Locale locale) {
        return this.d.c().b(locale);
    }

    @HybridPlus
    public String getCountryCode() {
        return this.d.getCountryCode();
    }

    @HybridPlus
    public RoadView getRoadView() {
        return this.b;
    }

    @HybridPlus
    public void addTrafficRerouteListener(WeakReference<TrafficRerouteListener> weakReference) {
        this.d.b(weakReference);
    }

    @HybridPlus
    public void removeTrafficRerouteListener(TrafficRerouteListener trafficRerouteListener) {
        this.d.a(trafficRerouteListener);
    }

    @HybridPlus
    public void addRealisticViewListener(WeakReference<RealisticViewListener> weakReference) {
        this.d.b(weakReference);
    }

    @HybridPlus
    public void removeRealisticViewListener(RealisticViewListener realisticViewListener) {
        this.d.a(realisticViewListener);
    }

    @HybridPlus
    public void addAudioFeedbackListener(WeakReference<AudioFeedbackListener> weakReference) {
        this.d.b(weakReference);
    }

    @HybridPlus
    public void removeAudioFeedbackListener(AudioFeedbackListener audioFeedbackListener) {
        this.d.a(audioFeedbackListener);
    }

    @HybridPlus
    public void addRerouteListener(WeakReference<RerouteListener> weakReference) {
        this.d.b(weakReference);
    }

    @HybridPlus
    public void removeRerouteListener(RerouteListener rerouteListener) {
        this.d.a(rerouteListener);
    }

    @HybridPlus
    public void addGpsSignalListener(WeakReference<GpsSignalListener> weakReference) {
        this.d.b(weakReference);
    }

    @HybridPlus
    public void removeGpsSignalListener(GpsSignalListener gpsSignalListener) {
        this.d.a(gpsSignalListener);
    }

    @HybridPlus
    @Deprecated
    public void addLaneInfoListener(WeakReference<LaneInfoListener> weakReference) {
        this.d.b(weakReference);
    }

    @HybridPlus
    @Deprecated
    public void removeLaneInfoListener(LaneInfoListener laneInfoListener) {
        this.d.a(laneInfoListener);
    }

    @HybridPlus
    public void addLaneInformationListener(WeakReference<LaneInformationListener> weakReference) {
        this.d.b(weakReference);
    }

    @HybridPlus
    public void removeLaneInformationListener(LaneInformationListener laneInformationListener) {
        this.d.a(laneInformationListener);
    }

    @HybridPlus
    public void addNavigationManagerEventListener(WeakReference<NavigationManagerEventListener> weakReference) {
        this.d.b(weakReference);
    }

    @HybridPlus
    public void removeNavigationManagerEventListener(NavigationManagerEventListener navigationManagerEventListener) {
        this.d.a(navigationManagerEventListener);
    }

    @HybridPlus
    public void addNewInstructionEventListener(WeakReference<NewInstructionEventListener> weakReference) {
        this.d.b(weakReference);
    }

    @HybridPlus
    public void removeNewInstructionEventListener(NewInstructionEventListener newInstructionEventListener) {
        this.d.a(newInstructionEventListener);
    }

    @HybridPlus
    public void addSafetySpotListener(WeakReference<SafetySpotListener> weakReference) {
        this.d.b(weakReference);
    }

    @HybridPlus
    public void removeSafetySpotListener(SafetySpotListener safetySpotListener) {
        this.d.a(safetySpotListener);
    }

    @HybridPlus
    public void addSpeedWarningListener(WeakReference<SpeedWarningListener> weakReference) {
        this.d.b(weakReference);
    }

    @HybridPlus
    public void removeSpeedWarningListener(SpeedWarningListener speedWarningListener) {
        this.d.a(speedWarningListener);
    }

    @HybridPlus
    public void addPositionListener(WeakReference<PositionListener> weakReference) {
        this.d.b(weakReference);
    }

    @HybridPlus
    public void removePositionListener(PositionListener positionListener) {
        this.d.a(positionListener);
    }

    @HybridPlus
    public void addManeuverEventListener(WeakReference<ManeuverEventListener> weakReference) {
        this.d.b(weakReference);
    }

    @HybridPlus
    public void removeManeuverEventListener(ManeuverEventListener maneuverEventListener) {
        this.d.a(maneuverEventListener);
    }

    private NavigationManager(NavigationManagerImpl navigationManagerImpl) {
        this.d = navigationManagerImpl;
    }
}
