package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataSingleVisualParam.TrackingMode;

public class DataEyeGetPushTrackStatus extends n {
    private static DataEyeGetPushTrackStatus instance = null;

    public enum TargetAction {
        Non(0),
        JUMP(1),
        OTHER(100);
        
        private final int data;

        private TargetAction(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static TargetAction find(int i) {
            TargetAction targetAction = Non;
            for (TargetAction targetAction2 : values()) {
                if (targetAction2._equals(i)) {
                    return targetAction2;
                }
            }
            return targetAction;
        }
    }

    public enum TargetObjType {
        UNKNOWN(0),
        PERSON(1),
        CAR(2),
        VAN(3),
        BIKE(4),
        ANIMAL(5),
        BOAT(6),
        OTHER(100);
        
        private final int data;

        private TargetObjType(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static TargetObjType find(int i) {
            TargetObjType targetObjType = UNKNOWN;
            for (TargetObjType targetObjType2 : values()) {
                if (targetObjType2._equals(i)) {
                    return targetObjType2;
                }
            }
            return targetObjType;
        }
    }

    public enum TrackException {
        NONE(0),
        SHAKE(-1),
        LOW_DETECT(-2),
        RC_HALT(-3),
        APP_HALT(-4),
        TOO_HIGH(-11),
        OBSTACLE(-12),
        PITCH_LOW(-13),
        TOO_FAR(-14),
        TOO_CLOSE(-15),
        DRONE_TOO_HIGH(-16),
        DRONE_TOO_LOW(-17),
        RADAR_FAILED(-18),
        TOO_SMALL(-21),
        TOO_LARGE(-22),
        NO_FEATURE(-23),
        APP_STOP(10),
        EXIT_BYSELF(11),
        OTHER(100);
        
        private int data;

        private TrackException(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static TrackException find(int i) {
            TrackException trackException = NONE;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return trackException;
        }
    }

    public enum TrackMode {
        LOST(0),
        NORMAL(1),
        WEAK(2),
        DETECT_AFTER_LOST(3),
        TRACKING(4),
        CONFIRM(5),
        PERSON(6),
        OTHER(100);
        
        private int data;

        private TrackMode(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static TrackMode find(int i) {
            TrackMode trackMode = LOST;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return trackMode;
        }
    }

    public static synchronized DataEyeGetPushTrackStatus getInstance() {
        DataEyeGetPushTrackStatus dataEyeGetPushTrackStatus;
        synchronized (DataEyeGetPushTrackStatus.class) {
            if (instance == null) {
                instance = new DataEyeGetPushTrackStatus();
            }
            dataEyeGetPushTrackStatus = instance;
        }
        return dataEyeGetPushTrackStatus;
    }

    public TrackMode getRectMode() {
        return TrackMode.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    public float getCenterX() {
        return ((Float) get(1, 4, Float.class)).floatValue();
    }

    public float getCenterY() {
        return ((Float) get(5, 4, Float.class)).floatValue();
    }

    public float getWidth() {
        return ((Float) get(9, 4, Float.class)).floatValue();
    }

    public float getHeight() {
        return ((Float) get(13, 4, Float.class)).floatValue();
    }

    public TrackException getException() {
        if (this._recData == null || this._recData.length <= 17) {
            return TrackException.NONE;
        }
        return TrackException.find(this._recData[17]);
    }

    public short getSessionId() {
        return ((Short) get(18, 2, Short.class)).shortValue();
    }

    public boolean isHumanTarget() {
        return (((Integer) get(20, 1, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isHeadLock() {
        return (((Integer) get(20, 1, Integer.class)).intValue() & 2) != 0;
    }

    public TrackingMode getTrackingMode() {
        return TrackingMode.find(((Integer) get(21, 1, Integer.class)).intValue());
    }

    public TargetObjType getTargetType() {
        if (this._recData != null && this._recData.length > 22) {
            return TargetObjType.find(((Integer) get(22, 1, Integer.class)).intValue());
        }
        if (isHumanTarget()) {
            return TargetObjType.PERSON;
        }
        return TargetObjType.UNKNOWN;
    }

    public TargetAction getTargetAction() {
        return TargetAction.find(((Integer) get(23, 1, Integer.class)).intValue());
    }

    protected void setPushRecData(byte[] bArr) {
        setRecData(bArr);
        if (isWantPush()) {
            post();
        }
    }

    protected void doPack() {
    }
}
