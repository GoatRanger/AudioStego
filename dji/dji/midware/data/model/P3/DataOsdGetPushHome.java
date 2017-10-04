package dji.midware.data.model.P3;

import android.support.v4.view.GravityCompat;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataFlycGetIoc.MODE;
import dji.pilot.usercenter.protocol.d;

public class DataOsdGetPushHome extends n {
    public static final int COUNT_MOTOR_ESCM = 8;
    private static DataOsdGetPushHome instance = null;
    private final MotorEscmState[] mMotorEscmStates;

    public enum HeightLimitStatus {
        NON_LIMIT(0),
        NON_GPS(1),
        ORIENTATION_NEED_CALI(2),
        ORIENTATION_GO(3),
        AVOID_GROUND(4),
        NORMAL_LIMIT(5);
        
        private int _value;

        private HeightLimitStatus(int i) {
            this._value = 0;
            this._value = i;
        }

        public int value() {
            return this._value;
        }

        private boolean _equals(int i) {
            return this._value == i;
        }

        public static HeightLimitStatus find(int i) {
            for (HeightLimitStatus heightLimitStatus : values()) {
                if (heightLimitStatus._equals(i)) {
                    return heightLimitStatus;
                }
            }
            return NON_LIMIT;
        }
    }

    public enum MotorEscmState {
        NON_SMART(0),
        DISCONNECT(1),
        SIGNAL_ERROR(2),
        RESISTANCE_ERROR(3),
        BLOCK(4),
        NON_BALANCE(5),
        ESCM_ERROR(6),
        PROPELLER_OFF(7),
        MOTOR_IDLE(8),
        MOTOR_UP(9),
        MOTOR_OFF(10),
        NON_CONNECT(11),
        OTHER(100);
        
        private int _value;

        private MotorEscmState(int i) {
            this._value = 0;
            this._value = i;
        }

        public int value() {
            return this._value;
        }

        private boolean _equals(int i) {
            return this._value == i;
        }

        public static MotorEscmState find(int i) {
            for (MotorEscmState motorEscmState : values()) {
                if (motorEscmState._equals(i)) {
                    return motorEscmState;
                }
            }
            return NON_CONNECT;
        }
    }

    public static synchronized DataOsdGetPushHome getInstance() {
        DataOsdGetPushHome dataOsdGetPushHome;
        synchronized (DataOsdGetPushHome.class) {
            if (instance == null) {
                instance = new DataOsdGetPushHome();
            }
            dataOsdGetPushHome = instance;
        }
        return dataOsdGetPushHome;
    }

    public DataOsdGetPushHome() {
        this.mMotorEscmStates = new MotorEscmState[8];
    }

    public DataOsdGetPushHome(boolean z) {
        super(z);
        this.mMotorEscmStates = new MotorEscmState[8];
    }

    public double getLongitude() {
        return (((Double) get(0, 8, Double.class)).doubleValue() * 180.0d) / 3.141592653589793d;
    }

    public double getLatitude() {
        return (((Double) get(8, 8, Double.class)).doubleValue() * 180.0d) / 3.141592653589793d;
    }

    public float getHeight() {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() >= 8) {
            return ((Float) get(16, 4, Float.class)).floatValue() * 0.1f;
        }
        return ((Float) get(16, 4, Float.class)).floatValue();
    }

    public MODE getIOCMode() {
        return MODE.find((((Integer) get(20, 2, Integer.class)).intValue() & 57344) >>> 13);
    }

    public boolean isIOCEnabled() {
        return ((((Integer) get(20, 2, Integer.class)).intValue() & 4096) >>> 12) != 0;
    }

    public boolean isBeginnerMode() {
        return ((((Integer) get(20, 2, Integer.class)).intValue() >> 11) & 1) != 0;
    }

    public boolean isCompassCeleing() {
        return ((((Integer) get(20, 2, Integer.class)).intValue() & 1024) >>> 10) != 0;
    }

    public int getCompassCeleStatus() {
        return (((Integer) get(20, 2, Integer.class)).intValue() & 768) >>> 8;
    }

    public boolean hasGoHome() {
        return ((((Integer) get(20, 2, Integer.class)).intValue() & 128) >>> 7) != 0;
    }

    public int getGoHomeStatus() {
        return (((Integer) get(20, 2, Integer.class)).intValue() & d.k) >>> 4;
    }

    public boolean isMultipleModeOpen() {
        return ((((Integer) get(20, 2, Integer.class)).intValue() >> 6) & 1) != 0 && DataOsdGetPushCommon.getInstance().getFlycVersion() >= 3;
    }

    public boolean isReatchLimitHeight() {
        return ((((Integer) get(20, 2, Integer.class)).intValue() & 32) >>> 5) != 0;
    }

    public boolean isReatchLimitDistance() {
        return ((((Integer) get(20, 2, Integer.class)).intValue() & 16) >>> 4) != 0;
    }

    public boolean isDynamicHomePiontEnable() {
        return ((((Integer) get(20, 2, Integer.class)).intValue() & 8) >>> 3) != 0;
    }

    public int getAircraftHeadDirection() {
        return (((Integer) get(20, 2, Integer.class)).intValue() & 4) >>> 2;
    }

    public int getGoHomeMode() {
        return (((Integer) get(20, 2, Integer.class)).intValue() & 2) >>> 1;
    }

    public boolean isHomeRecord() {
        return (((Integer) get(20, 2, Integer.class)).intValue() & 1) != 0;
    }

    public int getGoHomeHeight() {
        return ((Integer) get(22, 2, Integer.class)).intValue();
    }

    public short getCourseLockAngle() {
        return ((Short) get(24, 2, Short.class)).shortValue();
    }

    public int getDataRecorderStatus() {
        return ((Integer) get(26, 1, Integer.class)).intValue();
    }

    public HeightLimitStatus getHeightLimitStatus() {
        return HeightLimitStatus.find(((Integer) get(35, 1, Integer.class)).intValue() & 31);
    }

    public boolean useAbsoluteHeight() {
        return (((Integer) get(35, 1, Integer.class)).intValue() & 32) != 0;
    }

    public float getHeightLimitValue() {
        return ((Float) get(36, 4, Float.class)).floatValue();
    }

    public int getDataRecorderRemainCapacity() {
        return ((Integer) get(27, 1, Integer.class)).intValue();
    }

    public int getDataRecorderRemainTime() {
        return ((Integer) get(28, 2, Integer.class)).intValue();
    }

    public int getCurDataRecorderFileIndex() {
        return ((Integer) get(30, 2, Integer.class)).intValue();
    }

    public boolean isFlycInSimulationMode() {
        return (((Integer) get(32, 4, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isFlycInNavigationMode() {
        return (((Integer) get(32, 4, Integer.class)).intValue() & 2) != 0;
    }

    public boolean isWingBroken() {
        return (((Integer) get(32, 4, Integer.class)).intValue() & 4096) != 0;
    }

    public boolean isBigGale() {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 16) {
            return false;
        }
        if ((((Integer) get(32, 4, Integer.class)).intValue() & 16384) != 0) {
            return true;
        }
        return false;
    }

    public boolean isBigGaleWarning() {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 16) {
            return false;
        }
        if ((((Integer) get(32, 4, Integer.class)).intValue() & 1048576) != 0) {
            return true;
        }
        return false;
    }

    public boolean isCompassInstallErr() {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 18) {
            return false;
        }
        if ((((Integer) get(32, 4, Integer.class)).intValue() & GravityCompat.RELATIVE_LAYOUT_DIRECTION) != 0) {
            return true;
        }
        return false;
    }

    public MotorEscmState[] getMotorEscmState() {
        int intValue = ((Integer) get(41, 4, Integer.class)).intValue();
        for (int i = 0; i < 8; i++) {
            this.mMotorEscmStates[i] = MotorEscmState.find((intValue >> (i * 4)) & 15);
        }
        return this.mMotorEscmStates;
    }

    public int getForceLandingHeight() {
        if (this._recData == null || this._recData.length <= 45) {
            return Integer.MIN_VALUE;
        }
        return ((Integer) get(45, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
