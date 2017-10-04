package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataGimbalControl.MODE;

public class DataGimbalGetPushParams extends n {
    private static DataGimbalGetPushParams instance = null;

    public static synchronized DataGimbalGetPushParams getInstance() {
        DataGimbalGetPushParams dataGimbalGetPushParams;
        synchronized (DataGimbalGetPushParams.class) {
            if (instance == null) {
                instance = new DataGimbalGetPushParams();
                instance.isNeedPushLosed = true;
                instance.isRemoteModel = true;
            }
            dataGimbalGetPushParams = instance;
        }
        return dataGimbalGetPushParams;
    }

    public DataGimbalGetPushParams(boolean z) {
        super(z);
    }

    public int getPitch() {
        return ((Short) get(0, 2, Short.class)).shortValue();
    }

    public int getRoll() {
        return ((Short) get(2, 2, Short.class)).shortValue();
    }

    public int getYaw() {
        return ((Short) get(4, 2, Short.class)).shortValue();
    }

    public byte getRollAdjust() {
        return (byte) ((Short) get(7, 1, Short.class)).shortValue();
    }

    public int getYawAngle() {
        return ((Short) get(8, 2, Short.class)).shortValue();
    }

    public int getJoystickVerDirection() {
        return ((Integer) get(8, 1, Integer.class)).intValue() & 3;
    }

    public int getJoystickHorDirection() {
        return (((Integer) get(8, 1, Integer.class)).intValue() >> 2) & 3;
    }

    public boolean isAutoCalibration() {
        return (((Integer) get(10, 1, Integer.class)).intValue() & 8) != 0;
    }

    public boolean autoCalibrationResult() {
        return (((Integer) get(10, 1, Integer.class)).intValue() & 16) != 0;
    }

    public boolean isPitchInLimit() {
        return (((Integer) get(10, 1, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isRollInLimit() {
        return (((Integer) get(10, 1, Integer.class)).intValue() & 2) != 0;
    }

    public boolean isYawInLimit() {
        return (((Integer) get(10, 1, Integer.class)).intValue() & 4) != 0;
    }

    public boolean isStuck() {
        return (((Integer) get(10, 1, Integer.class)).intValue() & 64) != 0;
    }

    public MODE getMode() {
        return MODE.find(((Integer) get(6, 1, Integer.class)).intValue() >> 6);
    }

    public int getSubMode() {
        return (((Integer) get(6, 1, Integer.class)).intValue() >> 5) & 1;
    }

    public int getVersion() {
        return ((Short) get(11, 1, Short.class)).shortValue() & 15;
    }

    public boolean isDoubleClick() {
        return (((Short) get(11, 1, Short.class)).shortValue() & 32) != 0;
    }

    public boolean isTripleClick() {
        return (((Short) get(11, 1, Short.class)).shortValue() & 64) != 0;
    }

    public boolean isSingleClick() {
        return (((Short) get(11, 1, Short.class)).shortValue() & 128) != 0;
    }

    protected void doPack() {
    }
}
