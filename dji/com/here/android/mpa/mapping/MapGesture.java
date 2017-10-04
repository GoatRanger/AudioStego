package com.here.android.mpa.mapping;

import android.graphics.PointF;
import com.here.android.mpa.common.ViewObject;
import com.nokia.maps.annotation.Online;
import java.util.List;

@Online
public interface MapGesture {

    @Online
    public interface OnGestureListener {

        @Online
        public static abstract class OnGestureListenerAdapter implements OnGestureListener {
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

            public boolean onDoubleTapEvent(PointF pointF) {
                return false;
            }

            public void onPinchLocked() {
            }

            public boolean onPinchZoomEvent(float f, PointF pointF) {
                return false;
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

            public boolean onTwoFingerTapEvent(PointF pointF) {
                return false;
            }

            public void onLongPressRelease() {
            }
        }

        boolean onDoubleTapEvent(PointF pointF);

        boolean onLongPressEvent(PointF pointF);

        void onLongPressRelease();

        boolean onMapObjectsSelected(List<ViewObject> list);

        void onMultiFingerManipulationEnd();

        void onMultiFingerManipulationStart();

        void onPanEnd();

        void onPanStart();

        void onPinchLocked();

        boolean onPinchZoomEvent(float f, PointF pointF);

        boolean onRotateEvent(float f);

        void onRotateLocked();

        boolean onTapEvent(PointF pointF);

        boolean onTiltEvent(float f);

        boolean onTwoFingerTapEvent(PointF pointF);
    }

    void addOnGestureListener(OnGestureListener onGestureListener);

    void cancelKineticPanning();

    boolean isDoubleTapEnabled();

    boolean isFixedMapCenterOnMapRotateZoom();

    boolean isKineticFlickEnabled();

    boolean isLongPressEnabled();

    boolean isPanningEnabled();

    boolean isPinchEnabled();

    boolean isRotateEnabled();

    boolean isSingleTapEnabled();

    boolean isTiltEnabled();

    boolean isTwoFingerPanningEnabled();

    boolean isTwoFingerTapEnabled();

    void removeOnGestureListener(OnGestureListener onGestureListener);

    MapGesture setAllGesturesEnabled(boolean z);

    MapGesture setDoubleTapEnabled(boolean z);

    MapGesture setFixedMapCenterOnMapRotateZoom(boolean z);

    MapGesture setKineticFlickEnabled(boolean z);

    MapGesture setLongPressEnabled(boolean z);

    MapGesture setPanningEnabled(boolean z);

    MapGesture setPinchEnabled(boolean z);

    MapGesture setRotateEnabled(boolean z);

    MapGesture setSingleTapEnabled(boolean z);

    MapGesture setTiltEnabled(boolean z);

    MapGesture setTwoFingerPanningEnabled(boolean z);

    MapGesture setTwoFingerTapEnabled(boolean z);
}
