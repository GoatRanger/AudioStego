package com.here.android.mpa.venues3d;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.annotation.Internal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Internal
public final class AnimationController extends BaseNativeObject {
    private final ExecutorService a = Executors.newCachedThreadPool();

    @HybridPlusNative
    private native void animateFloorChangeNative(VenueController venueController, Level level, Level level2);

    @HybridPlusNative
    private native void animateVenueEnteringNative(VenueController venueController, GeoCoordinate geoCoordinate, float f, float f2, float f3, float f4);

    @HybridPlusNative
    private native a getEnteringParamsNative(VenueController venueController, GeoCoordinate geoCoordinate, float f, float f2, float f3, float f4);

    @HybridPlusNative
    private native a getFloorChangingParamsNative(VenueController venueController, Level level, Level level2);

    @HybridPlusNative
    protected AnimationController(int i) {
        this.nativeptr = i;
    }

    public void a(final VenueController venueController, final Level level, final Level level2) {
        this.a.execute(new Runnable(this) {
            final /* synthetic */ AnimationController d;

            public void run() {
                this.d.animateFloorChangeNative(venueController, level, level2);
            }
        });
    }

    public void a(final VenueController venueController, final GeoCoordinate geoCoordinate, final Margin margin) {
        this.a.execute(new Runnable(this) {
            final /* synthetic */ AnimationController d;

            public void run() {
                this.d.animateVenueEnteringNative(venueController, geoCoordinate, (float) margin.getLeft(), (float) margin.getTop(), (float) margin.getRight(), (float) margin.getBottom());
            }
        });
    }
}
