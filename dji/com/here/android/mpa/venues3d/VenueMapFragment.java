package com.here.android.mpa.venues3d;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.Display;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.OnEngineInitListener.Error;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapFragment;
import com.here.android.mpa.venues3d.VenueService.InitStatus;
import com.here.android.mpa.venues3d.VenueService.VenueServiceListener;
import com.nokia.maps.ApplicationContext;
import com.nokia.maps.ApplicationContext$c;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.aq;
import com.nokia.maps.ez;
import com.nokia.maps.fc;
import com.nokia.maps.fc$a;
import com.nokia.maps.l;
import java.lang.ref.WeakReference;
import java.security.AccessControlException;

@HybridPlus
public class VenueMapFragment extends MapFragment {
    private VenueMapLayer a = null;
    private boolean b = false;
    private boolean c = false;
    private GeoCoordinate d = null;
    private Margin e = new Margin(0, 0, 0, 0);
    private int f = 0;
    private int g = 0;
    private long h = 0;
    private final fc<VenueListener> i = new fc();
    private final fc<VenueZoomListener> j = new fc();
    private b k = new b(this) {
        final /* synthetic */ VenueMapFragment a;

        {
            this.a = r1;
        }

        public void a(VenueController venueController, final boolean z) {
            final Venue venue = venueController != null ? venueController.getVenue() : null;
            this.a.i.a(new fc$a<VenueListener>(this) {
                final /* synthetic */ AnonymousClass1 c;

                public void a(VenueListener venueListener) {
                    venueListener.onVenueVisibleInViewport(venue, z);
                }
            });
        }

        public void a(final VenueController venueController, final DeselectionSource deselectionSource) {
            this.a.c();
            this.a.d = null;
            this.a.i.a(new fc$a<VenueListener>(this) {
                final /* synthetic */ AnonymousClass1 c;

                public void a(VenueListener venueListener) {
                    venueListener.onVenueDeselected(venueController.getVenue(), deselectionSource);
                }
            });
        }

        public void a(final VenueController venueController, final float f, final float f2) {
            Map map = this.a.getMap();
            if (map != null) {
                this.a.d = map.pixelToGeo(new PointF(f, f2));
            }
            this.a.i.a(new fc$a<VenueListener>(this) {
                final /* synthetic */ AnonymousClass1 d;

                public void a(VenueListener venueListener) {
                    venueListener.onVenueTapped(venueController.getVenue(), f, f2);
                }
            });
        }

        public void a(final VenueController venueController) {
            this.a.b();
            if (this.a.c && this.a.a != null) {
                this.a.a.b().a(venueController, this.a.d, this.a.e);
            }
            this.a.i.a(new fc$a<VenueListener>(this) {
                final /* synthetic */ AnonymousClass1 b;

                public void a(VenueListener venueListener) {
                    venueListener.onVenueSelected(venueController.getVenue());
                }
            });
        }

        public void b(final VenueController venueController, final Space space) {
            this.a.i.a(new fc$a<VenueListener>(this) {
                final /* synthetic */ AnonymousClass1 c;

                public void a(VenueListener venueListener) {
                    venueListener.onSpaceDeselected(venueController.getVenue(), space);
                }
            });
        }

        public void a(final VenueController venueController, final Space space) {
            this.a.f = this.a.f + 1;
            this.a.i.a(new fc$a<VenueListener>(this) {
                final /* synthetic */ AnonymousClass1 c;

                public void a(VenueListener venueListener) {
                    venueListener.onSpaceSelected(venueController.getVenue(), space);
                }
            });
        }

        public void a(final VenueController venueController, final Level level, final Level level2) {
            this.a.g = this.a.g + 1;
            if (this.a.b && this.a.a != null) {
                this.a.a.b().a(venueController, level, level2);
            }
            this.a.i.a(new fc$a<VenueListener>(this) {
                final /* synthetic */ AnonymousClass1 d;

                public void a(VenueListener venueListener) {
                    venueListener.onFloorChanged(venueController.getVenue(), level, level2);
                }
            });
        }
    };
    private c l = new c(this) {
        final /* synthetic */ VenueMapFragment a;

        {
            this.a = r1;
        }

        public void a(VenueController venueController, final boolean z) {
            final Venue venue = venueController != null ? venueController.getVenue() : null;
            this.a.j.a(new fc$a<VenueZoomListener>(this) {
                final /* synthetic */ AnonymousClass2 c;

                public void a(VenueZoomListener venueZoomListener) {
                    venueZoomListener.onVenueZoomUpdated(venue, z);
                }
            });
        }
    };
    private volatile boolean m = false;

    @HybridPlus
    public interface VenueListener {
        void onFloorChanged(Venue venue, Level level, Level level2);

        void onSpaceDeselected(Venue venue, Space space);

        void onSpaceSelected(Venue venue, Space space);

        void onVenueDeselected(Venue venue, DeselectionSource deselectionSource);

        void onVenueSelected(Venue venue);

        void onVenueTapped(Venue venue, float f, float f2);

        void onVenueVisibleInViewport(Venue venue, boolean z);
    }

    @HybridPlus
    public interface VenueZoomListener {
        void onVenueZoomUpdated(Venue venue, boolean z);
    }

    public VenueMapFragment() {
        setRetainInstance(true);
    }

    public void init(OnEngineInitListener onEngineInitListener) {
        init(getActivity(), onEngineInitListener, null);
    }

    public void init(Context context, OnEngineInitListener onEngineInitListener) {
        init(context, onEngineInitListener, null);
    }

    public void init(OnEngineInitListener onEngineInitListener, VenueServiceListener venueServiceListener) {
        init(getActivity(), onEngineInitListener, venueServiceListener);
    }

    public void init(Context context, final OnEngineInitListener onEngineInitListener, final VenueServiceListener venueServiceListener) {
        super.init(context, new OnEngineInitListener(this) {
            final /* synthetic */ VenueMapFragment c;

            public void onEngineInitializationCompleted(Error error) {
                if (error == Error.NONE) {
                    ApplicationContext.b().check(7, new ApplicationContext$c(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void b() {
                            this.a.c.m = true;
                            Activity activity = this.a.c.getActivity();
                            if (activity == null || activity.isFinishing()) {
                                onEngineInitListener.onEngineInitializationCompleted(aq.a(Error.OPERATION_NOT_ALLOWED, "Activity has finished"));
                                return;
                            }
                            this.a.c.a(venueServiceListener);
                            onEngineInitListener.onEngineInitializationCompleted(Error.NONE);
                            this.a.c.a.a(this.a.c.getMapGesture());
                            this.a.c.a.c();
                        }

                        public void a() {
                            this.a.c.m = false;
                            ez.a(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    onEngineInitListener.onEngineInitializationCompleted(aq.a(Error.OPERATION_NOT_ALLOWED, "Venue Maps 3D permission missing."));
                                }
                            });
                        }
                    });
                } else {
                    onEngineInitListener.onEngineInitializationCompleted(error);
                }
            }
        });
    }

    private void a(VenueServiceListener venueServiceListener) {
        if (this.a == null) {
            this.a = new VenueMapLayer(getActivity(), getMap());
        } else {
            this.a.a(getMap(), null);
        }
        this.a.a().a(true);
        this.a.a(this.k);
        this.a.a(this.l);
        if (venueServiceListener != null) {
            this.a.a().addListener(venueServiceListener);
        }
    }

    public void onResume() {
        if (getSelectedVenue() != null) {
            b();
        }
        super.onResume();
    }

    public void onPause() {
        c();
        super.onPause();
    }

    public void onDestroy() {
        if (this.a != null) {
            this.a.b(this.k);
            this.a.d();
        }
        super.onDestroy();
    }

    public void addListener(VenueListener venueListener) {
        if (this.m && venueListener != null) {
            this.i.b(venueListener);
            this.i.a(new WeakReference(venueListener));
        }
    }

    public void removeListener(VenueListener venueListener) {
        if (this.m && venueListener != null) {
            this.i.b(venueListener);
        }
    }

    public void addVenueZoomListener(VenueZoomListener venueZoomListener) {
        if (this.m && venueZoomListener != null) {
            this.j.b(venueZoomListener);
            this.j.a(new WeakReference(venueZoomListener));
        }
    }

    public void removeListener(VenueZoomListener venueZoomListener) {
        if (this.m && venueZoomListener != null) {
            this.j.b(venueZoomListener);
        }
    }

    public void setVenueLayerVisible(boolean z) {
        if (this.a != null) {
            this.a.a(z);
        }
    }

    public boolean isVenueLayerVisible() {
        if (this.a != null) {
            return this.a.e();
        }
        return false;
    }

    public VenueController getVenueController(Venue venue) {
        a();
        if (this.a == null || venue == null) {
            return null;
        }
        return this.a.getVenueControllerNative(venue.getId());
    }

    public boolean selectVenue(Venue venue) {
        a();
        if (this.a != null) {
            VenueController venueControllerNative = this.a.getVenueControllerNative(venue.getId());
            if (venueControllerNative != null) {
                this.a.openVenueNative(venueControllerNative);
                return true;
            }
        }
        return false;
    }

    public VenueInfo selectVenueAsync(String str) {
        a();
        if (this.a != null) {
            return this.a.a(str);
        }
        return null;
    }

    public VenueInfo selectAsync(String str, String str2) {
        a();
        if (this.a != null) {
            return this.a.a(str, str2);
        }
        return null;
    }

    public boolean cancelVenueSelection() {
        if (this.a != null) {
            return this.a.cancelVenueOpeningNative();
        }
        return false;
    }

    public void deselectVenue() {
        if (this.a != null) {
            this.a.closeVenueNative();
        }
    }

    public Venue getSelectedVenue() {
        if (this.a != null) {
            VenueController selectedVenueNative = this.a.getSelectedVenueNative();
            if (selectedVenueNative != null) {
                return selectedVenueNative.getVenue();
            }
        }
        return null;
    }

    public boolean isVenueVisible(String str) {
        if (this.a != null) {
            return this.a.isVenueVisibleNative(str);
        }
        return false;
    }

    public boolean isVenueInViewportCallbackEnabled() {
        if (this.a != null) {
            return this.a.getCheckVenuesInViewportNative();
        }
        return false;
    }

    public void setVenuesInViewportCallback(boolean z) {
        if (this.a != null) {
            this.a.setCheckVenuesInViewportNative(z);
        }
    }

    public VenueService getVenueService() {
        if (this.a != null) {
            return this.a.a();
        }
        return null;
    }

    public RoutingController getRoutingController() {
        if (this.a != null) {
            return this.a.f();
        }
        return null;
    }

    public boolean isFloorChangingAnimationEnabled() {
        return this.b;
    }

    public void setFloorChangingAnimation(boolean z) {
        this.b = z;
    }

    public boolean isVenueEnteringAnimationEnabled() {
        return this.c;
    }

    public void setVenueEnteringAnimation(boolean z) {
        this.c = z;
    }

    public Margin getMargin() {
        return this.e;
    }

    public void setMargin(Margin margin) {
        int top;
        Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i = point.x / 3;
        int i2 = point.y / 3;
        int left = margin.getLeft() < i ? margin.getLeft() : i;
        if (margin.getRight() < i) {
            i = margin.getRight();
        }
        if (margin.getTop() < i2) {
            top = margin.getTop();
        } else {
            top = i2;
        }
        if (margin.getBottom() < i2) {
            i2 = margin.getBottom();
        }
        this.e = new Margin(left, top, i, i2);
    }

    private void a() {
        if (this.m) {
            VenueService venueService = getVenueService();
            Object obj = (venueService == null || !(venueService.getInitStatus() == InitStatus.ONLINE_SUCCESS || venueService.getInitStatus() == InitStatus.OFFLINE_SUCCESS)) ? null : 1;
            if (obj == null) {
                throw new RuntimeException("Venue service has yet been initialized.  Try again later.");
            }
            return;
        }
        throw new AccessControlException("Access to this operation is denied. Contact your HERE representative for more information.");
    }

    private void b() {
        this.h = System.currentTimeMillis();
        this.f = 0;
        this.g = 0;
    }

    private void c() {
        if (this.h != 0) {
            l.a().a(this.g, this.f, (int) (((float) (System.currentTimeMillis() - this.h)) * 0.001f));
            this.h = 0;
        }
    }
}
