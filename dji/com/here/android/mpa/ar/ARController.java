package com.here.android.mpa.ar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.PointF;
import android.graphics.RectF;
import android.hardware.Camera;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Size;
import com.here.android.mpa.common.Vector3f;
import com.here.android.mpa.common.ViewRect;
import com.here.android.mpa.mapping.Map;
import com.nokia.maps.GeoCoordinateImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.k;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@TargetApi(14)
public final class ARController {
    @HybridPlus
    public static final CameraParams CameraParams = new CameraParams();
    @HybridPlus
    public final DownViewParams DownViewParams;
    @HybridPlus
    public final ExternalSensors ExternalSensors;
    @HybridPlus
    public final FilterParams HeadingFilterParams;
    @HybridPlus
    public final IconParams IconParams;
    @HybridPlus
    public final InfoParams InfoParams;
    @HybridPlus
    public final IntroAnimationParams IntroParams;
    @HybridPlus
    public final FilterParams PitchFilterParams;
    @HybridPlus
    public final SelectedItemParams SelectedItemParams;
    @HybridPlus
    public final UpViewParams UpViewParams;
    @HybridPlus
    public final UpViewTransitionParams UpViewTransitionParams;
    @HybridPlus
    public final FilterParams ZoomFilterParams;
    @Internal
    protected com.nokia.maps.b a;

    @HybridPlus
    public static final class CameraParams {
        private CameraParams() {
        }

        public CameraParams setSize(Size size) {
            com.nokia.maps.a.a(size);
            return ARController.CameraParams;
        }

        public Size getSize() {
            return com.nokia.maps.a.c();
        }

        public CameraParams setFps(int i) {
            com.nokia.maps.a.a(i);
            return ARController.CameraParams;
        }

        public int getFps() {
            return com.nokia.maps.a.d();
        }

        public float getHorizontalFov() {
            return com.nokia.maps.a.m();
        }

        public float getVerticalFov() {
            return com.nokia.maps.a.n();
        }

        public List<Camera.Size> getSupportedSizes() {
            return com.nokia.maps.a.e();
        }
    }

    @HybridPlus
    public final class DownViewParams {
        final /* synthetic */ ARController a;

        private DownViewParams(ARController aRController) {
            this.a = aRController;
        }

        public DownViewParams setMaxZoomOutScale(float f, boolean z, boolean z2) {
            this.a.a.a(f, z, z2);
            return this;
        }

        public float getMaxZoomOutScale() {
            return this.a.a.M();
        }

        public DownViewParams setMinPitch(float f) {
            this.a.a.l(f);
            return this;
        }

        public float getMinPitch() {
            return this.a.a.N();
        }

        public DownViewParams setAutoControlOnEntryExit(boolean z) {
            this.a.a.e(z);
            return this;
        }

        public DownViewParams setAutoZoomEnabled(boolean z, boolean z2) {
            this.a.a.a(z, z2);
            return this;
        }

        public boolean isAutoZoomEnabled() {
            return this.a.a.m();
        }

        public DownViewParams setAutoPitchEnabled(boolean z, boolean z2) {
            this.a.a.b(z, z2);
            return this;
        }

        public boolean isAutoPitchEnabled() {
            return this.a.a.n();
        }

        public DownViewParams setAutoHeadingEnabled(boolean z, boolean z2) {
            this.a.a.c(z, z2);
            return this;
        }

        public boolean isAutoHeadingEnabled() {
            return this.a.a.o();
        }

        public DownViewParams setAutoTFCEnabled(boolean z, boolean z2) {
            this.a.a.d(z, z2);
            return this;
        }

        public boolean isAutoTFCEnabled() {
            return this.a.a.p();
        }

        public DownViewParams setAutoGeoCenterEnabled(boolean z, boolean z2) {
            this.a.a.e(z, z2);
            return this;
        }

        public boolean isAutoGeoCenterEnabled() {
            return this.a.a.q();
        }

        public DownViewParams setPitchThreshold(float f) {
            this.a.a.d(f);
            return this;
        }

        public float getPitchThreshold() {
            return this.a.a.f();
        }

        public DownViewParams setMinAlpha(float f) {
            this.a.a.h(f);
            return this;
        }

        public float getMinAlpha() {
            return this.a.a.H();
        }

        public DownViewParams setMaxAlpha(float f) {
            this.a.a.i(f);
            return this;
        }

        public float getMaxAlpha() {
            return this.a.a.I();
        }

        public DownViewParams setFadeOutAnimationDelay(long j) {
            this.a.a.a(com.nokia.maps.b.a.MAP_FADE_OUT, j);
            return this;
        }

        public long getFadeOutAnimationDelay() {
            return this.a.a.a(com.nokia.maps.b.a.MAP_FADE_OUT);
        }

        public DownViewParams setFadeOutAnimationTime(long j) {
            this.a.a.b(com.nokia.maps.b.a.MAP_FADE_OUT, j);
            return this;
        }

        public long getFadeOutAnimationTime() {
            return this.a.a.b(com.nokia.maps.b.a.MAP_FADE_OUT);
        }

        public DownViewParams setFadeInAnimationDelay(long j) {
            this.a.a.a(com.nokia.maps.b.a.MAP_FADE_IN, j);
            return this;
        }

        public long getFadeInAnimationDelay() {
            return this.a.a.a(com.nokia.maps.b.a.MAP_FADE_IN);
        }

        public DownViewParams setFadeInAnimationTime(long j) {
            this.a.a.b(com.nokia.maps.b.a.MAP_FADE_IN, j);
            return this;
        }

        public long getFadeInAnimationTime() {
            return this.a.a.b(com.nokia.maps.b.a.MAP_FADE_IN);
        }

        public DownViewParams setFadeOutInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.MAP_FADE_OUT, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getFadeOutInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.MAP_FADE_OUT);
        }

        public DownViewParams setFadeInInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.MAP_FADE_IN, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getFadeInInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.MAP_FADE_IN);
        }

        public DownViewParams setHeadingInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.HEADING, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getHeadingInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.HEADING);
        }

        public DownViewParams setPitchInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.PITCH, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getPitchInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.PITCH);
        }

        public DownViewParams setZoomInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.ZOOM, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getZoomInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.ZOOM);
        }

        public DownViewParams setGeoCenterInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.GPS, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getGeoCenterInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.GPS);
        }

        public DownViewParams setCenterInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.TFC, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getCenterInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.TFC);
        }

        public DownViewParams setTransformCenter(PointF pointF, boolean z) {
            this.a.a.a(pointF, z);
            return this;
        }
    }

    @HybridPlus
    public enum Error {
        NONE,
        INVALID_OPERATION,
        INVALID_PARAMETERS,
        OPERATION_NOT_ALLOWED,
        CAMERA_UNAVAILABLE,
        SENSORS_UNAVAILABLE,
        STOPPED
    }

    public final class ExternalSensors {
        final /* synthetic */ ARController a;

        private ExternalSensors(ARController aRController) {
            this.a = aRController;
        }

        @HybridPlus
        public ExternalSensors utilize(SensorType sensorType, boolean z) {
            this.a.a.a(sensorType, z);
            return this;
        }

        @HybridPlus
        public void pushData(SensorType sensorType, double d, double d2, double d3, long j) {
            this.a.a.a(sensorType, d, d2, d3, j);
        }
    }

    @HybridPlus
    public final class FilterParams {
        static final /* synthetic */ boolean a = (!ARController.class.desiredAssertionStatus());
        final /* synthetic */ ARController b;
        private final int c;

        @SuppressLint({"Assert"})
        private FilterParams(ARController aRController, int i) {
            this.b = aRController;
            if (a || (i >= 0 && i <= 2)) {
                this.c = i;
                return;
            }
            throw new AssertionError();
        }

        public FilterParams setSize(int i) {
            this.b.a.e(this.c, i);
            return this;
        }

        public int getSize() {
            return this.b.a.b(this.c);
        }

        public FilterParams setCoeff(float f) {
            this.b.a.a(this.c, f);
            return this;
        }

        public float getCoeff() {
            return this.b.a.c(this.c);
        }
    }

    @HybridPlus
    public final class IconParams {
        final /* synthetic */ ARController a;

        private IconParams(ARController aRController) {
            this.a = aRController;
        }

        public IconParams setDefaultIcons(int i, int i2, int i3) {
            this.a.a.a(i, i2, i3);
            return this;
        }

        public IconParams setFlyIconSizeOnMap(ARObject aRObject, int i, int i2) {
            this.a.a.a(aRObject, i, i2);
            return this;
        }

        public IconParams setFrontIconMaxSize(int i, int i2) {
            this.a.a.a(i, i2);
            return this;
        }

        public IconParams setBackToFrontIconSizeRatio(float f) {
            this.a.a.a(f);
            return this;
        }

        public IconParams setBackIconMaxSize(int i, int i2) {
            this.a.a.b(i, i2);
            return this;
        }

        public IconParams setPressAnimationDelay(long j) {
            this.a.a.a(com.nokia.maps.b.a.ICON_PRESS, j);
            return this;
        }

        public long getPressAnimationDelay() {
            return this.a.a.a(com.nokia.maps.b.a.ICON_PRESS);
        }

        public IconParams setPressAnimationTime(long j) {
            this.a.a.b(com.nokia.maps.b.a.ICON_PRESS, j);
            return this;
        }

        public long getPressAnimationTime() {
            return this.a.a.b(com.nokia.maps.b.a.ICON_PRESS);
        }

        public IconParams setDepressAnimationDelay(long j) {
            this.a.a.a(com.nokia.maps.b.a.ICON_DEPRESS, j);
            return this;
        }

        public long getDepressAnimationDelay() {
            return this.a.a.a(com.nokia.maps.b.a.ICON_DEPRESS);
        }

        public IconParams setDepressAnimationTime(long j) {
            this.a.a.b(com.nokia.maps.b.a.ICON_DEPRESS, j);
            return this;
        }

        public long getDepressAnimationTime() {
            return this.a.a.b(com.nokia.maps.b.a.ICON_DEPRESS);
        }

        public IconParams setPopUpAnimationDelay(long j) {
            this.a.a.a(com.nokia.maps.b.a.ICON_POP_UP, j);
            return this;
        }

        public long getPopUpAnimationDelay() {
            return this.a.a.a(com.nokia.maps.b.a.ICON_POP_UP);
        }

        public IconParams setPopUpAnimationTime(long j) {
            this.a.a.b(com.nokia.maps.b.a.ICON_POP_UP, j);
            return this;
        }

        public long getPopUpAnimationTime() {
            return this.a.a.b(com.nokia.maps.b.a.ICON_POP_UP);
        }

        public IconParams setFlyAnimationDelay(long j) {
            this.a.a.a(com.nokia.maps.b.a.ICON_FLY, j);
            return this;
        }

        public long getFlyAnimationDelay() {
            return this.a.a.a(com.nokia.maps.b.a.ICON_FLY);
        }

        public IconParams setFlyAnimationTime(long j) {
            this.a.a.b(com.nokia.maps.b.a.ICON_FLY, j);
            return this;
        }

        public long getFlyAnimationTime() {
            return this.a.a.b(com.nokia.maps.b.a.ICON_FLY);
        }

        public IconParams setFlyRotateAnimationDelay(long j) {
            this.a.a.a(com.nokia.maps.b.a.ICON_TURN, j);
            return this;
        }

        public long getFlyRotateAnimationDelay() {
            return this.a.a.a(com.nokia.maps.b.a.ICON_TURN);
        }

        public IconParams setFlyRotateAnimationTime(long j) {
            this.a.a.b(com.nokia.maps.b.a.ICON_TURN, j);
            return this;
        }

        public long getFlyRotateAnimationTime() {
            return this.a.a.b(com.nokia.maps.b.a.ICON_TURN);
        }

        public IconParams setFlyRotateAngles(Vector3f vector3f) {
            this.a.a.b(0, vector3f.getX());
            this.a.a.b(1, vector3f.getY());
            this.a.a.b(2, vector3f.getZ());
            return this;
        }

        public Vector3f getFlyRotateAngles() {
            return new Vector3f(this.a.a.d(0), this.a.a.d(1), this.a.a.d(2));
        }

        public IconParams setFlyInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.ICON_FLY, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getFlyInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.ICON_FLY);
        }

        public IconParams setPopUpInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.ICON_POP_UP, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getPopUpInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.ICON_POP_UP);
        }

        public IconParams setTurnInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.ICON_TURN, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getTurnInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.ICON_TURN);
        }

        public IconParams setUseDownIconOpacity(boolean z) {
            this.a.a.k(z);
            return this;
        }

        public boolean getUseDownIconOpacity() {
            return this.a.a.A();
        }

        public IconParams setDownIconOpacity(float f) {
            this.a.a.f(f);
            return this;
        }

        public float getDownIconOpacity() {
            return this.a.a.B();
        }
    }

    @HybridPlus
    public final class InfoParams {
        final /* synthetic */ ARController a;

        private InfoParams(ARController aRController) {
            this.a = aRController;
        }

        public InfoParams setOpenAnimationDelay(long j) {
            this.a.a.a(com.nokia.maps.b.a.INFO_OPEN, j);
            return this;
        }

        public long getOpenAnimationDelay() {
            return this.a.a.a(com.nokia.maps.b.a.INFO_OPEN);
        }

        public InfoParams setOpenAnimationTime(long j) {
            this.a.a.b(com.nokia.maps.b.a.INFO_OPEN, j);
            return this;
        }

        public long getOpenAnimationTime() {
            return this.a.a.b(com.nokia.maps.b.a.INFO_OPEN);
        }

        public InfoParams setCloseAnimationDelay(long j) {
            this.a.a.a(com.nokia.maps.b.a.INFO_CLOSE, j);
            return this;
        }

        public long getCloseAnimationDelay() {
            return this.a.a.a(com.nokia.maps.b.a.INFO_CLOSE);
        }

        public InfoParams setCloseAnimationTime(long j) {
            this.a.a.b(com.nokia.maps.b.a.INFO_CLOSE, j);
            return this;
        }

        public long getCloseAnimationTime() {
            return this.a.a.b(com.nokia.maps.b.a.INFO_CLOSE);
        }

        public InfoParams setOpenInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.INFO_OPEN, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getOpenInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.INFO_OPEN);
        }

        public InfoParams setCloseInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.INFO_CLOSE, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getCloseInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.INFO_CLOSE);
        }

        public InfoParams setAnimationMinWidthFactor(float f) {
            this.a.a.g(f);
            return this;
        }

        public float getAnimationMinWidthFactor() {
            return this.a.a.G();
        }
    }

    @HybridPlus
    public enum IntroAnimationMode {
        DEFAULT,
        FLY_TO_LOCATION
    }

    @HybridPlus
    public final class IntroAnimationParams {
        final /* synthetic */ ARController a;

        private IntroAnimationParams(ARController aRController) {
            this.a = aRController;
        }

        public IntroAnimationParams setAnimationTime(long j) {
            this.a.a.c(j);
            return this;
        }

        public long getAnimationTime() {
            return this.a.a.D();
        }

        public IntroAnimationParams setPitchInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.INTRO_PITCH, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getPitchInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.INTRO_PITCH);
        }

        public IntroAnimationParams setZoomInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.INTRO_ZOOM, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getZoomInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.INTRO_ZOOM);
        }

        public IntroAnimationParams setHeadingInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.INTRO_HEADING, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getHeadingInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.INTRO_HEADING);
        }

        public IntroAnimationParams setCenterInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.INTRO_TFC, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getCenterInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.INTRO_TFC);
        }

        public IntroAnimationParams setPositionInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.INTRO_GPS, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getPositionInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.INTRO_GPS);
        }

        public IntroAnimationParams setIntroAnimationMode(IntroAnimationMode introAnimationMode) {
            this.a.a.a(introAnimationMode);
            return this;
        }

        public IntroAnimationMode getIntroAnimationMode() {
            return this.a.a.aa();
        }
    }

    @HybridPlus
    public interface OnCameraEnteredListener {
        void onCameraEntered();
    }

    @HybridPlus
    public interface OnCameraExitedListener {
        void onCameraExited();
    }

    @HybridPlus
    public interface OnCompassCalibrationChangedListener {
        void onCompassCalibrationChange();
    }

    @HybridPlus
    public interface OnLivesightStatusListener {
        void onLivesightStatus(Error error);
    }

    @HybridPlus
    public interface OnMapEnteredListener {
        void onMapEntered();
    }

    @HybridPlus
    public interface OnMapExitedListener {
        void onMapExited();
    }

    @HybridPlus
    public interface OnObjectTappedListener {
        boolean onObjectTapped(List<ARObject> list);
    }

    @HybridPlus
    public interface OnPanListener {
        boolean onPan(PointF pointF, PointF pointF2);
    }

    @HybridPlus
    public interface OnPitchFunction {
        float onPitchFunction(float f);
    }

    @HybridPlus
    public interface OnPoseListener {
        void onPose(ARPoseReading aRPoseReading);
    }

    @HybridPlus
    public interface OnPostPresentListener {
        void onPostPresent();
    }

    @HybridPlus
    public interface OnPreDrawListener {
        void onPreDraw();
    }

    @HybridPlus
    public interface OnPreDrawMapListener {
        void onPreDrawMap(float f, float f2, GeoCoordinate geoCoordinate);
    }

    @HybridPlus
    public interface OnPrePresentListener {
        void onPrePresent();
    }

    @HybridPlus
    public interface OnProjectionCameraUpdatedListener {
        void onProjectionCameraUpdated();
    }

    @HybridPlus
    public interface OnRadarUpdateListener {
        void onRadarUpdate(ARRadarProperties aRRadarProperties);
    }

    @HybridPlus
    public interface OnSensorCalibrationChangedListener {
        void onSensorCalibrationChanged(int i, int i2);
    }

    @HybridPlus
    public interface OnTapListener {
        boolean onTap(PointF pointF);
    }

    @HybridPlus
    public interface OnTouchDownListener {
        boolean onTouchDown(PointF pointF);
    }

    @HybridPlus
    public interface OnTouchUpListener {
        boolean onTouchUp(PointF pointF);
    }

    @HybridPlus
    public enum ProjectionType {
        NEAR_FAR,
        DIRECT_3D,
        HORIZONTAL,
        MAP,
        USE_GLOBAL_PROJECTION_TYPE
    }

    @HybridPlus
    public final class SelectedItemParams {
        final /* synthetic */ ARController a;

        private SelectedItemParams(ARController aRController) {
            this.a = aRController;
        }

        public SelectedItemParams setSize(int i, int i2) {
            this.a.a.c(i, i2);
            return this;
        }

        public Size getSize() {
            return this.a.a.z();
        }

        public SelectedItemParams setBoundingBox(RectF rectF) {
            this.a.a.a(rectF);
            return this;
        }

        public SelectedItemParams setOpacity(float f) {
            this.a.a.j(f);
            return this;
        }

        public float getOpacity() {
            return this.a.a.K();
        }

        public SelectedItemParams setNonSelectedItemsOpacity(float f) {
            this.a.a.k(f);
            return this;
        }

        public float getNonSelectedItemsOpacity() {
            return this.a.a.L();
        }

        public SelectedItemParams setSizeInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.SELECTED_ITEM_SIZE, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getSizeInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.SELECTED_ITEM_SIZE);
        }

        public SelectedItemParams setBoundingBoxInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.SELECTED_ITEM_BOUNDING_BOX, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getBoundingBoxInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.SELECTED_ITEM_BOUNDING_BOX);
        }

        public SelectedItemParams setSizeAnimationDelay(long j) {
            this.a.a.a(com.nokia.maps.b.a.SELECTED_ITEM_SIZE, j);
            return this;
        }

        public long getSizeAnimationDelay() {
            return this.a.a.a(com.nokia.maps.b.a.SELECTED_ITEM_SIZE);
        }

        public SelectedItemParams setSizeAnimationTime(long j) {
            this.a.a.b(com.nokia.maps.b.a.SELECTED_ITEM_SIZE, j);
            return this;
        }

        public long getSizeAnimationTime() {
            return this.a.a.b(com.nokia.maps.b.a.SELECTED_ITEM_SIZE);
        }

        public SelectedItemParams setBoundingBoxAnimationDelay(long j) {
            this.a.a.a(com.nokia.maps.b.a.SELECTED_ITEM_BOUNDING_BOX, j);
            return this;
        }

        public long getBoundingBoxAnimationDelay() {
            return this.a.a.a(com.nokia.maps.b.a.SELECTED_ITEM_BOUNDING_BOX);
        }

        public SelectedItemParams setBoundingBoxAnimationTime(long j) {
            this.a.a.b(com.nokia.maps.b.a.SELECTED_ITEM_BOUNDING_BOX, j);
            return this;
        }

        public long getBoundingBoxAnimationTime() {
            return this.a.a.b(com.nokia.maps.b.a.SELECTED_ITEM_BOUNDING_BOX);
        }

        public SelectedItemParams setMaxViewAngle(float f) {
            this.a.a.b(f);
            return this;
        }

        public float getMaxViewAngle() {
            return this.a.a.d();
        }
    }

    @HybridPlus
    public enum SensorType {
        GPS,
        COMPASS,
        ACCELEROMETER,
        GYROSCOPE,
        CAMERA
    }

    public final class UpViewParams {
        final /* synthetic */ ARController a;

        private UpViewParams(ARController aRController) {
            this.a = aRController;
        }

        @HybridPlus
        public UpViewParams setPitchLocked(boolean z) {
            this.a.a.f(z);
            return this;
        }

        @HybridPlus
        public boolean isPitchLocked() {
            return this.a.a.s();
        }

        @HybridPlus
        public UpViewParams setPanEnabled(boolean z) {
            this.a.a.h(z);
            return this;
        }

        @HybridPlus
        public boolean isPanEnabled() {
            return this.a.a.u();
        }

        @HybridPlus
        public UpViewParams setPinchEnabled(boolean z) {
            this.a.a.i(z);
            return this;
        }

        @HybridPlus
        public boolean isPinchEnabled() {
            return this.a.a.v();
        }

        @HybridPlus
        public UpViewParams setCameraFrameZoomEnabled(boolean z) {
            this.a.a.j(z);
            return this;
        }

        @HybridPlus
        public boolean isCameraFrameZoomEnabled() {
            return this.a.a.w();
        }

        @HybridPlus
        public UpViewParams setCameraFrameMaxZoomScale(float f) {
            this.a.a.e(f);
            return this;
        }

        @HybridPlus
        public float getCameraFrameMaxZoomScale() {
            return this.a.a.x();
        }

        @HybridPlus
        public UpViewParams setPitchThreshold(float f) {
            this.a.a.c(f);
            return this;
        }

        @HybridPlus
        public float getPitchThreshold() {
            return this.a.a.e();
        }

        @HybridPlus
        public UpViewParams setShowGridEnabled(boolean z) {
            this.a.a.c(z);
            return this;
        }

        @HybridPlus
        public boolean isShowGridEnabled() {
            return this.a.a.g();
        }

        @HybridPlus
        public void setEdgeDetectionEnabled(boolean z) {
            this.a.a.n(z);
        }

        @HybridPlus
        public boolean isEdgeDetectionEnabled() {
            return this.a.a.Z();
        }
    }

    @HybridPlus
    public final class UpViewTransitionParams {
        final /* synthetic */ ARController a;

        private UpViewTransitionParams(ARController aRController) {
            this.a = aRController;
        }

        public UpViewTransitionParams setMaxAnimationTime(long j) {
            this.a.a.d(j);
            return this;
        }

        public long getMaxAnimationTime() {
            return this.a.a.E();
        }

        public UpViewTransitionParams setMinAnimationTime(long j) {
            this.a.a.e(j);
            return this;
        }

        public long getMinAnimationTime() {
            return this.a.a.F();
        }

        public UpViewTransitionParams setPitchInterpolator(AnimationInterpolator animationInterpolator) {
            this.a.a.a(com.nokia.maps.b.a.TILT_UP_PITCH, animationInterpolator);
            return this;
        }

        public AnimationInterpolator getPitchInterpolator() {
            return this.a.a.c(com.nokia.maps.b.a.TILT_UP_PITCH);
        }
    }

    @HybridPlus
    public enum ViewType {
        AUTO,
        MAP,
        CAMERA
    }

    @Internal
    public interface a {
        void a();
    }

    @Internal
    public interface b {
        void a();
    }

    @Internal
    public enum c {
        CAMERA_LIVE,
        CAMERA_RECORDING,
        CAMERA_PLAYBACK,
        SLI
    }

    static {
        com.nokia.maps.b.a(new k<ARController, com.nokia.maps.b>() {
            public com.nokia.maps.b a(ARController aRController) {
                return aRController != null ? aRController.a : null;
            }
        }, new am<ARController, com.nokia.maps.b>() {
            public ARController a(com.nokia.maps.b bVar) {
                if (bVar != null) {
                    return new ARController(bVar);
                }
                return null;
            }
        });
    }

    @Internal
    private ARController(com.nokia.maps.b bVar) {
        this.IntroParams = new IntroAnimationParams();
        this.HeadingFilterParams = new FilterParams(0);
        this.PitchFilterParams = new FilterParams(1);
        this.ZoomFilterParams = new FilterParams(2);
        this.DownViewParams = new DownViewParams();
        this.UpViewParams = new UpViewParams();
        this.UpViewTransitionParams = new UpViewTransitionParams();
        this.IconParams = new IconParams();
        this.InfoParams = new InfoParams();
        this.SelectedItemParams = new SelectedItemParams();
        this.ExternalSensors = new ExternalSensors();
        this.a = bVar;
    }

    @HybridPlus
    public Error start() {
        return this.a.a();
    }

    @HybridPlus
    public Error stop(boolean z) {
        return this.a.a(z);
    }

    @HybridPlus
    public void setMap(Map map) {
        this.a.a(map);
    }

    @HybridPlus
    public void addARObject(ARObject aRObject) {
        this.a.a(aRObject);
    }

    @HybridPlus
    public boolean removeARObject(ARObject aRObject) {
        return this.a.b(aRObject);
    }

    @HybridPlus
    public void addARObject(ARPolylineObject aRPolylineObject) {
        this.a.a(aRPolylineObject);
    }

    @HybridPlus
    public boolean removeARObject(ARPolylineObject aRPolylineObject) {
        return this.a.b(aRPolylineObject);
    }

    @HybridPlus
    public void addARObject(ARModelObject aRModelObject) {
        this.a.a(aRModelObject);
    }

    @HybridPlus
    public boolean removeARObject(ARModelObject aRModelObject) {
        return this.a.b(aRModelObject);
    }

    @HybridPlus
    public long getObjectId(ARObject aRObject) {
        return this.a.i(aRObject);
    }

    @HybridPlus
    public void focus(ARObject aRObject) {
        this.a.e(aRObject);
    }

    @HybridPlus
    public void defocus() {
        this.a.b();
    }

    @HybridPlus
    public ARObject press(PointF pointF) {
        return this.a.a(pointF);
    }

    @HybridPlus
    public void press(ARObject aRObject) {
        this.a.f(aRObject);
    }

    @HybridPlus
    public void depress(ARObject aRObject) {
        this.a.g(aRObject);
    }

    @HybridPlus
    public void select(ARObject aRObject) {
        this.a.h(aRObject);
    }

    @HybridPlus
    public void select(ARObject aRObject, boolean z, float f) {
        this.a.a(aRObject, z, f);
    }

    @HybridPlus
    public void unselect() {
        this.a.c();
    }

    @HybridPlus
    public boolean isVisible(ARObject aRObject) {
        return this.a.c(aRObject);
    }

    @HybridPlus
    public boolean isOccluded(ARObject aRObject) {
        return this.a.d(aRObject);
    }

    @HybridPlus
    public void showFrontItemsOnly(boolean z) {
        this.a.d(z);
    }

    @HybridPlus
    public List<ARObject> getObjects(PointF pointF) {
        return this.a.b(pointF);
    }

    @HybridPlus
    public List<ARObject> getObjects(ViewRect viewRect) {
        return this.a.a(viewRect);
    }

    @HybridPlus
    public void pan(PointF pointF, PointF pointF2) {
        this.a.a(pointF, pointF2);
    }

    @HybridPlus
    public void panTo(GeoCoordinate geoCoordinate) {
        this.a.a(geoCoordinate);
    }

    @HybridPlus
    public void showView(ViewType viewType) {
        this.a.a(viewType);
    }

    @HybridPlus
    public ViewType getViewType() {
        return this.a.k();
    }

    @HybridPlus
    public ARPoseReading getPose() {
        return this.a.l();
    }

    @HybridPlus
    public GeoCoordinate getPosition() {
        GeoCoordinateImpl r = this.a.r();
        if (r == null) {
            return null;
        }
        return new GeoCoordinate(r.a(), r.b(), r.c());
    }

    @HybridPlus
    public GeoCoordinate getPosition(AtomicBoolean atomicBoolean) {
        GeoCoordinateImpl a = this.a.a(atomicBoolean);
        if (a == null) {
            return null;
        }
        return new GeoCoordinate(a.a(), a.b(), a.c());
    }

    @HybridPlus
    public int getAccelerometerCalibrationStatus() {
        return this.a.h();
    }

    @HybridPlus
    public int getCompassCalibrationStatus() {
        return this.a.i();
    }

    @HybridPlus
    public int getGyroscopeCalibrationStatus() {
        return this.a.j();
    }

    @HybridPlus
    public void setAlternativeCenter(GeoCoordinate geoCoordinate) {
        this.a.b(geoCoordinate);
    }

    @HybridPlus
    public boolean isUsingAlternativeCenter() {
        return this.a.y();
    }

    @HybridPlus
    public void setUseDownIconsOnMap(boolean z) {
        this.a.b(z);
    }

    @HybridPlus
    public void setPlanesParameters(float f, float f2, float f3, float f4) {
        this.a.a(f, f2, f3, f4);
    }

    @HybridPlus
    public void setTapArea(int i, int i2) {
        this.a.d(i, i2);
    }

    @HybridPlus
    public void setUpdateDistanceDelta(int i) {
        this.a.a(i);
    }

    @HybridPlus
    public int getUpdateDistanceDelta() {
        return this.a.C();
    }

    @HybridPlus
    public void setProjectionType(ProjectionType projectionType) {
        this.a.a(projectionType);
    }

    @HybridPlus
    public ProjectionType getProjectionType() {
        return this.a.J();
    }

    @HybridPlus
    public void setSensorsWaitTimeout(long j) {
        this.a.f(j);
    }

    @HybridPlus
    public long getSensorsWaitTimeout() {
        return this.a.O();
    }

    @HybridPlus
    public PointF getScreenViewPoint() {
        return this.a.P();
    }

    @HybridPlus
    public void setOcclusionEnabled(boolean z) {
        this.a.g(z);
    }

    @HybridPlus
    public boolean isOcclusionEnabled() {
        return this.a.t();
    }

    @HybridPlus
    public void setOcclusionOpacity(float f) {
        this.a.m(f);
    }

    @HybridPlus
    public float getOcclusionOpacity() {
        return this.a.Q();
    }

    @HybridPlus
    public void setInfoAnimationInUpViewOnly(boolean z) {
        this.a.l(z);
    }

    @HybridPlus
    public void addOnCameraEnteredListener(OnCameraEnteredListener onCameraEnteredListener) {
        this.a.a(onCameraEnteredListener);
    }

    @HybridPlus
    public void removeOnCameraEnteredListener(OnCameraEnteredListener onCameraEnteredListener) {
        this.a.b(onCameraEnteredListener);
    }

    @HybridPlus
    public void addOnCameraExitedListener(OnCameraExitedListener onCameraExitedListener) {
        this.a.a(onCameraExitedListener);
    }

    @HybridPlus
    public void removeOnCameraExitedListener(OnCameraExitedListener onCameraExitedListener) {
        this.a.b(onCameraExitedListener);
    }

    @HybridPlus
    public void addOnMapEnteredListener(OnMapEnteredListener onMapEnteredListener) {
        this.a.a(onMapEnteredListener);
    }

    @HybridPlus
    public void removeOnMapEnteredListener(OnMapEnteredListener onMapEnteredListener) {
        this.a.b(onMapEnteredListener);
    }

    @HybridPlus
    public void addOnMapExitedListener(OnMapExitedListener onMapExitedListener) {
        this.a.a(onMapExitedListener);
    }

    @HybridPlus
    public void removeOnMapExitedListener(OnMapExitedListener onMapExitedListener) {
        this.a.b(onMapExitedListener);
    }

    @HybridPlus
    public void addOnPanListener(OnPanListener onPanListener) {
        this.a.a(onPanListener);
    }

    @HybridPlus
    public void removeOnPanListener(OnPanListener onPanListener) {
        this.a.b(onPanListener);
    }

    @HybridPlus
    public void addOnTapListener(OnTapListener onTapListener) {
        this.a.a(onTapListener);
    }

    @HybridPlus
    public void removeOnTapListener(OnTapListener onTapListener) {
        this.a.b(onTapListener);
    }

    @HybridPlus
    public void addOnTouchDownListener(OnTouchDownListener onTouchDownListener) {
        this.a.a(onTouchDownListener);
    }

    @HybridPlus
    public void removeOnTouchDownListener(OnTouchDownListener onTouchDownListener) {
        this.a.b(onTouchDownListener);
    }

    @HybridPlus
    public void addOnTouchUpListener(OnTouchUpListener onTouchUpListener) {
        this.a.a(onTouchUpListener);
    }

    @HybridPlus
    public void removeOnTouchUpListener(OnTouchUpListener onTouchUpListener) {
        this.a.b(onTouchUpListener);
    }

    @HybridPlus
    public void addOnSensorCalibrationChangedListener(OnSensorCalibrationChangedListener onSensorCalibrationChangedListener) {
        this.a.a(onSensorCalibrationChangedListener);
    }

    @HybridPlus
    public void removeOnSensorCalibrationChangedListener(OnSensorCalibrationChangedListener onSensorCalibrationChangedListener) {
        this.a.b(onSensorCalibrationChangedListener);
    }

    @HybridPlus
    public void addOnCompassCalibrationChangedListener(OnCompassCalibrationChangedListener onCompassCalibrationChangedListener) {
        this.a.a(onCompassCalibrationChangedListener);
    }

    @HybridPlus
    public void removeOnCompassCalibrationChangedListener(OnCompassCalibrationChangedListener onCompassCalibrationChangedListener) {
        this.a.b(onCompassCalibrationChangedListener);
    }

    @HybridPlus
    public void addOnObjectTappedListener(OnObjectTappedListener onObjectTappedListener) {
        this.a.a(onObjectTappedListener);
    }

    @HybridPlus
    public void removeOnObjectTappedListener(OnObjectTappedListener onObjectTappedListener) {
        this.a.b(onObjectTappedListener);
    }

    @HybridPlus
    public void addOnRadarUpdateListener(OnRadarUpdateListener onRadarUpdateListener) {
        this.a.a(onRadarUpdateListener);
    }

    @HybridPlus
    public void removeOnRadarUpdateListener(OnRadarUpdateListener onRadarUpdateListener) {
        this.a.b(onRadarUpdateListener);
    }

    @HybridPlus
    public void addOnPoseListener(OnPoseListener onPoseListener) {
        this.a.a(onPoseListener);
    }

    @HybridPlus
    public void removeOnPoseListener(OnPoseListener onPoseListener) {
        this.a.b(onPoseListener);
    }

    @HybridPlus
    public void addOnPreDrawListener(OnPreDrawListener onPreDrawListener) {
        this.a.a(onPreDrawListener);
    }

    @HybridPlus
    public void removeOnPreDrawListener(OnPreDrawListener onPreDrawListener) {
        this.a.b(onPreDrawListener);
    }

    @HybridPlus
    public void addOnPreDrawMapListener(OnPreDrawMapListener onPreDrawMapListener) {
        this.a.a(onPreDrawMapListener);
    }

    @HybridPlus
    public void removeOnPreDrawMapListener(OnPreDrawMapListener onPreDrawMapListener) {
        this.a.b(onPreDrawMapListener);
    }

    @HybridPlus
    public void addOnPrePresentListener(OnPrePresentListener onPrePresentListener) {
        this.a.a(onPrePresentListener);
    }

    @HybridPlus
    public void removeOnPrePresentListener(OnPrePresentListener onPrePresentListener) {
        this.a.b(onPrePresentListener);
    }

    @HybridPlus
    public void addOnPostPresentListener(OnPostPresentListener onPostPresentListener) {
        this.a.a(onPostPresentListener);
    }

    @HybridPlus
    public void removeOnPostPresentListener(OnPostPresentListener onPostPresentListener) {
        this.a.b(onPostPresentListener);
    }

    @HybridPlus
    public void addOnLivesightStatusListener(OnLivesightStatusListener onLivesightStatusListener) {
        this.a.a(onLivesightStatusListener);
    }

    @HybridPlus
    public void removeOnLivesightStatusListener(OnLivesightStatusListener onLivesightStatusListener) {
        this.a.b(onLivesightStatusListener);
    }

    @HybridPlus
    public void setPitchFunction(OnPitchFunction onPitchFunction) {
        this.a.a(onPitchFunction);
    }

    @HybridPlus
    public void removePitchFunction() {
        this.a.S();
    }

    @HybridPlus
    public void addOnProjectionCameraUpdatedListener(OnProjectionCameraUpdatedListener onProjectionCameraUpdatedListener) {
        this.a.a(onProjectionCameraUpdatedListener);
    }

    @HybridPlus
    public void removeOnProjectionCameraUpdatedListener(OnProjectionCameraUpdatedListener onProjectionCameraUpdatedListener) {
        this.a.b(onProjectionCameraUpdatedListener);
    }

    @HybridPlus
    public void setOrientationAnimation(boolean z) {
        this.a.m(z);
    }

    @HybridPlus
    public void setFixedAltitude(float f, boolean z) {
        this.a.a(f, z);
    }

    @HybridPlus
    public float getFixedAltitude() {
        return this.a.X();
    }

    @HybridPlus
    public void setCompassAccuracy(float f) {
        this.a.n(f);
    }

    @HybridPlus
    public float getCompassAccuracy() {
        return this.a.Y();
    }

    @HybridPlus
    public boolean pixelTo3dPosition(float f, PointF pointF, Vector3f vector3f) {
        return this.a.a(f, pointF, vector3f);
    }

    @HybridPlus
    public boolean geoTo3dPosition(GeoCoordinate geoCoordinate, Vector3f vector3f) {
        return this.a.a(geoCoordinate, vector3f);
    }
}
