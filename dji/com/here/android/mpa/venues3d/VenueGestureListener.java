package com.here.android.mpa.venues3d;

import android.graphics.PointF;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.mapping.Map$Animation;
import com.here.android.mpa.mapping.MapGesture.OnGestureListener;
import com.here.android.mpa.venues3d.VenueMapFragment.VenueListener;
import com.nokia.maps.annotation.HybridPlus;
import java.util.List;

@HybridPlus
public class VenueGestureListener implements OnGestureListener, VenueListener {
    private VenueMapFragment a = null;
    private VenueController b = null;
    private double c = 0.0d;
    private double d;
    private double e;
    private boolean f = false;
    private boolean g = false;

    public VenueGestureListener(VenueMapFragment venueMapFragment, int i) {
        this.a = venueMapFragment;
        this.a.addListener(this);
        a(i);
    }

    public boolean onDoubleTapEvent(PointF pointF) {
        if (!a() || this.g || this.a.getMap().getZoomLevel() < this.d - 0.1d) {
            return false;
        }
        this.b.useVenueZoom(true);
        this.a.getMap().setZoomLevel(this.d - 2.0d);
        this.a.getMap().setCenter(this.b.getScaledGeoCoordinate(this.a.getMap().getCenter()), Map$Animation.NONE);
        this.g = true;
        this.a.getMap().setZoomLevel(this.d, Map$Animation.LINEAR);
        this.c = this.e;
        return true;
    }

    public void onPinchLocked() {
    }

    public boolean onPinchZoomEvent(float f, PointF pointF) {
        if (!a()) {
            return false;
        }
        if (f > 1.0f) {
            f = 1.006f;
            this.f = false;
        } else if (f < 1.0f) {
            f = 0.993f;
        }
        if (this.c < this.d) {
            this.c = this.a.getMap().getZoomLevel() * ((double) f);
            if (this.g) {
                this.b.useVenueZoom(false);
                this.a.getMap().setZoomLevel(this.d - 0.10000000149011612d);
                this.a.getMap().setCenter(this.b.getNormalGeoCoordinate(this.a.getMap().getCenter()), Map$Animation.NONE);
                this.f = true;
                this.g = false;
            } else if (!this.f) {
                return false;
            } else {
                this.c = this.a.getMap().getZoomLevel() * ((double) f);
                this.a.getMap().setZoomLevel(this.c);
                return true;
            }
        } else if (this.c <= this.e) {
            if (!this.g) {
                this.b.useVenueZoom(true);
                this.a.getMap().setCenter(this.b.getScaledGeoCoordinate(this.a.getMap().getCenter()), Map$Animation.NONE);
                this.g = true;
            }
            this.c *= (double) f;
            this.a.getMap().setZoomLevel(this.c - 2.0d);
        } else {
            this.c = this.e;
        }
        return true;
    }

    public void onRotateLocked() {
    }

    public boolean onRotateEvent(float f) {
        return false;
    }

    public boolean onTiltEvent(float f) {
        return false;
    }

    public boolean onLongPressEvent(PointF pointF) {
        return false;
    }

    public void onLongPressRelease() {
    }

    public boolean onTwoFingerTapEvent(PointF pointF) {
        if (!a() || !this.g) {
            return false;
        }
        this.a.getMap().setCenter(this.b.getNormalGeoCoordinate(this.a.getMap().getCenter()), Map$Animation.NONE);
        b();
        return true;
    }

    public void onPanStart() {
    }

    public void onPanEnd() {
    }

    public void onMultiFingerManipulationStart() {
    }

    public void onMultiFingerManipulationEnd() {
    }

    public boolean onMapObjectsSelected(List<ViewObject> list) {
        return false;
    }

    public boolean onTapEvent(PointF pointF) {
        return false;
    }

    public void onVenueTapped(Venue venue, float f, float f2) {
    }

    public void onVenueSelected(Venue venue) {
    }

    public void onVenueDeselected(Venue venue, DeselectionSource deselectionSource) {
        b();
    }

    public void onSpaceSelected(Venue venue, Space space) {
    }

    public void onSpaceDeselected(Venue venue, Space space) {
    }

    public void onFloorChanged(Venue venue, Level level, Level level2) {
    }

    public void onVenueVisibleInViewport(Venue venue, boolean z) {
    }

    private void a(int i) {
        if (i > 320) {
            this.d = 18.8d;
            this.e = 20.8d;
            return;
        }
        this.d = 19.5d;
        this.e = 21.5d;
    }

    private boolean a() {
        Venue selectedVenue = this.a.getSelectedVenue();
        VenueService venueService = this.a.getVenueService();
        if (selectedVenue == null || venueService == null) {
            return false;
        }
        this.b = this.a.getVenueController(selectedVenue);
        if (venueService.isVenueZoomEnabled()) {
            return true;
        }
        return false;
    }

    private void b() {
        if (this.b != null) {
            this.b.useVenueZoom(false);
        }
        this.g = false;
        this.c = 0.0d;
    }
}
