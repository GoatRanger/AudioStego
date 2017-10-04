package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataEyeGetPushException extends n {
    private static DataEyeGetPushException instance = null;

    public enum AdvanceGoHomeState {
        NO_ACTION(0),
        TURNING_YAW(1),
        EXECUTING_GO_HOME(2),
        HOVERING_AT_SAFE_POINT(3),
        PLANING(4),
        OTHER(100);
        
        private int data;

        private AdvanceGoHomeState(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static AdvanceGoHomeState find(int i) {
            AdvanceGoHomeState advanceGoHomeState = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return advanceGoHomeState;
        }
    }

    public enum AdvanceGoHomeStrategy {
        NO_STRATEGY(0),
        SAFE_STRATEGY(1),
        EXPLORE_STRATEGY(2),
        OTHER(100);
        
        private int data;

        private AdvanceGoHomeStrategy(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static AdvanceGoHomeStrategy find(int i) {
            AdvanceGoHomeStrategy advanceGoHomeStrategy = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return advanceGoHomeStrategy;
        }
    }

    public enum PreciseLandingState {
        NO_ACTION(0),
        TURNING_YAW(1),
        LANDING(2),
        OTHER(100);
        
        private int data;

        private PreciseLandingState(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static PreciseLandingState find(int i) {
            PreciseLandingState preciseLandingState = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return preciseLandingState;
        }
    }

    public enum TrackExceptionStatus {
        NORMAL(0),
        LOST_TIMEOUT(1),
        INVALID_SPEED(2),
        NONE_IMAGE(3),
        LOW_FRAME(4),
        NFZ(5),
        RCCONN_TIMEOUT(6),
        APPCONN_TIMEOUT(7),
        LOST_CONTROL(9),
        OTHER(100);
        
        private int data;

        private TrackExceptionStatus(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static TrackExceptionStatus find(int i) {
            TrackExceptionStatus trackExceptionStatus = NORMAL;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return trackExceptionStatus;
        }
    }

    public static synchronized DataEyeGetPushException getInstance() {
        DataEyeGetPushException dataEyeGetPushException;
        synchronized (DataEyeGetPushException.class) {
            if (instance == null) {
                instance = new DataEyeGetPushException();
            }
            dataEyeGetPushException = instance;
        }
        return dataEyeGetPushException;
    }

    public boolean isTrackSystemAbnormal() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isPointSystemAbnormal() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 2) != 0;
    }

    public boolean isDisparityPackLost() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 4) != 0;
    }

    public boolean isIMUPackLost() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 8) != 0;
    }

    public boolean isGimbalPackLost() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 16) != 0;
    }

    public boolean isRCPackLost() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 32) != 0;
    }

    public boolean isVisualDataAbnormal() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 64) != 0;
    }

    public boolean isFronImageOverExposure() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 256) != 0;
    }

    public boolean isFronImageUnderExposure() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 512) != 0;
    }

    public boolean isFrontImageDiff() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 1024) != 0;
    }

    public boolean isFrontSensorDemarkAbnormal() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 2048) != 0;
    }

    public boolean isNonFlying() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 4096) != 0;
    }

    public boolean isUserTapStop() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 8192) != 0;
    }

    public boolean isTripodFolded() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 16384) != 0;
    }

    public boolean isRCDisconnect() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 65536) != 0;
    }

    public boolean isAPPDisconnect() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 131072) != 0;
    }

    public boolean isOutOfControl() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 262144) != 0;
    }

    public boolean isInNonFlyZone() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 524288) != 0;
    }

    public boolean isFusionDataAbnormal() {
        return (((Integer) get(0, 3, Integer.class)).intValue() & 1048576) != 0;
    }

    public boolean isInTracking() {
        return (((Integer) get(3, 1, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isInTapFly() {
        return (((Integer) get(3, 1, Integer.class)).intValue() & 2) != 0;
    }

    public boolean isInAdvanceHoming() {
        return (((Integer) get(3, 1, Integer.class)).intValue() & 4) != 0;
    }

    public boolean isInPreciseLanding() {
        return (((Integer) get(3, 1, Integer.class)).intValue() & 8) != 0;
    }

    public boolean isFaceDetectEnable() {
        return (((Integer) get(3, 1, Integer.class)).intValue() & 16) != 0;
    }

    public boolean isMovingObjectDetectEnable() {
        return (((Integer) get(3, 1, Integer.class)).intValue() & 32) != 0;
    }

    public boolean isGpsTrackingEnable() {
        return (((Integer) get(3, 1, Integer.class)).intValue() & 64) != 0;
    }

    public TrackExceptionStatus getTrackStatus() {
        return TrackExceptionStatus.find(((Integer) get(4, 1, Integer.class)).intValue() & 15);
    }

    public boolean isAircraftGpsAbnormal() {
        return (((Integer) get(4, 1, Integer.class)).intValue() & 16) != 0;
    }

    public boolean isPhoneGpsAbnormal() {
        return (((Integer) get(4, 1, Integer.class)).intValue() & 32) != 0;
    }

    public boolean isGpsTrackingFlusionAbnormal() {
        return (((Integer) get(4, 1, Integer.class)).intValue() & 64) != 0;
    }

    public boolean isAvoidOkInTracking() {
        return (((Integer) get(5, 1, Integer.class)).intValue() & 1) != 0;
    }

    public boolean cantDetourInTracking() {
        return (((Integer) get(5, 1, Integer.class)).intValue() & 2) != 0;
    }

    public boolean isDeceleratingInTracking() {
        return (((Integer) get(5, 1, Integer.class)).intValue() & 4) != 0;
    }

    public boolean brakedByCollisionInTracking() {
        return (((Integer) get(5, 1, Integer.class)).intValue() & 8) != 0;
    }

    public boolean detourUpInTracking() {
        return (((Integer) get(5, 1, Integer.class)).intValue() & 16) != 0;
    }

    public boolean detourDownInTracking() {
        return (((Integer) get(5, 1, Integer.class)).intValue() & 32) != 0;
    }

    public boolean detourLeftInTracking() {
        return (((Integer) get(5, 1, Integer.class)).intValue() & 64) != 0;
    }

    public boolean detourRightInTracking() {
        return (((Integer) get(5, 1, Integer.class)).intValue() & 128) != 0;
    }

    public boolean rcNotInFMode() {
        return (((Integer) get(6, 2, Integer.class)).intValue() & 1) != 0;
    }

    public boolean cantDetour() {
        return (((Integer) get(6, 2, Integer.class)).intValue() & 2) != 0;
    }

    public boolean brakedByCollision() {
        return (((Integer) get(6, 2, Integer.class)).intValue() & 4) != 0;
    }

    public boolean detourUp() {
        return (((Integer) get(6, 2, Integer.class)).intValue() & 8) != 0;
    }

    public boolean detourLeft() {
        return (((Integer) get(6, 2, Integer.class)).intValue() & 16) != 0;
    }

    public boolean detourRight() {
        return (((Integer) get(6, 2, Integer.class)).intValue() & 32) != 0;
    }

    public boolean isStickAdd() {
        return (((Integer) get(6, 2, Integer.class)).intValue() & 64) != 0;
    }

    public boolean isOutOfRange() {
        return (((Integer) get(6, 2, Integer.class)).intValue() & 128) != 0;
    }

    public boolean isUserQuickPullPitch() {
        return (((Integer) get(6, 2, Integer.class)).intValue() & 256) != 0;
    }

    public boolean isInLowFlying() {
        return (((Integer) get(6, 2, Integer.class)).intValue() & 512) != 0;
    }

    public boolean isRunningDelay() {
        return (((Integer) get(6, 2, Integer.class)).intValue() & 1024) != 0;
    }

    public boolean isInPointing() {
        return (((Integer) get(6, 2, Integer.class)).intValue() & 2048) != 0;
    }

    public long getVisionVersion() {
        return ((Long) get(8, 4, Long.class)).longValue();
    }

    public boolean isGPSError() {
        return (((Integer) get(4, 2, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isFrontVisoinError() {
        return (((Integer) get(4, 2, Integer.class)).intValue() & 2) != 0;
    }

    public AdvanceGoHomeState getAdvanceGoHomeState() {
        return AdvanceGoHomeState.find((((Integer) get(4, 2, Integer.class)).intValue() >> 2) & 7);
    }

    public AdvanceGoHomeStrategy getAdvanceGoHomeStrategy() {
        return AdvanceGoHomeStrategy.find((((Integer) get(4, 2, Integer.class)).intValue() >> 5) & 3);
    }

    public boolean isEffectedByObstacle() {
        return (((Integer) get(6, 2, Integer.class)).intValue() & 1) != 0;
    }

    public PreciseLandingState getPreciseLandingState() {
        return PreciseLandingState.find((((Integer) get(6, 2, Integer.class)).intValue() & 6) >> 1);
    }

    public boolean isAdjustingPreciseLanding() {
        return (((Integer) get(6, 2, Integer.class)).intValue() & 8) != 0;
    }

    public boolean isExecutingPreciseLanding() {
        return (((Integer) get(6, 2, Integer.class)).intValue() & 16) != 0;
    }

    protected void doPack() {
    }
}
