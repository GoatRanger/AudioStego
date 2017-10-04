package com.here.android.mpa.streetlevel;

import android.graphics.PointF;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.cu;
import com.nokia.maps.k;
import java.util.List;

@HybridPlus
public class StreetLevelGesture {
    private cu a;

    @HybridPlus
    public interface OnGestureListener {

        @HybridPlus
        public static abstract class OnGestureListenerAdapter implements OnGestureListener {
            public boolean onObjectsSelected(List<StreetLevelSelectedObject> list) {
                return false;
            }

            public boolean onCompassSelected() {
                return false;
            }

            public boolean onTap(PointF pointF) {
                return false;
            }

            public boolean onDoubleTap(PointF pointF) {
                return false;
            }

            public boolean onPinchZoom(float f) {
                return false;
            }

            public boolean onRotate(PointF pointF, PointF pointF2) {
                return false;
            }
        }

        boolean onCompassSelected();

        boolean onDoubleTap(PointF pointF);

        boolean onObjectsSelected(List<StreetLevelSelectedObject> list);

        boolean onPinchZoom(float f);

        boolean onRotate(PointF pointF, PointF pointF2);

        boolean onTap(PointF pointF);
    }

    public void addOnGestureListener(OnGestureListener onGestureListener) {
        this.a.a(onGestureListener);
    }

    public void removeOnGestureListener(OnGestureListener onGestureListener) {
        this.a.b(onGestureListener);
    }

    private StreetLevelGesture(cu cuVar) {
        this.a = cuVar;
    }

    public StreetLevelGesture setPinchEnabled(boolean z) {
        this.a.a(z);
        return this;
    }

    public boolean isPinchEnabled() {
        return this.a.b();
    }

    public StreetLevelGesture setRotationEnabled(boolean z) {
        this.a.b(z);
        return this;
    }

    public boolean isRotationEnabled() {
        return this.a.c();
    }

    public StreetLevelGesture setTapEnabled(boolean z) {
        this.a.c(z);
        return this;
    }

    public boolean isTapEnabled() {
        return this.a.s();
    }

    public StreetLevelGesture setDoubleTapEnabled(boolean z) {
        this.a.d(z);
        return this;
    }

    public boolean isDoubleTapEnabled() {
        return this.a.t();
    }

    public StreetLevelGesture setAllGesturesEnabled(boolean z) {
        this.a.e(z);
        return this;
    }

    static {
        cu.a(new k<StreetLevelGesture, cu>() {
            public cu a(StreetLevelGesture streetLevelGesture) {
                return streetLevelGesture.a;
            }
        }, new am<StreetLevelGesture, cu>() {
            public StreetLevelGesture a(cu cuVar) {
                if (cuVar != null) {
                    return new StreetLevelGesture(cuVar);
                }
                return null;
            }
        });
    }
}
