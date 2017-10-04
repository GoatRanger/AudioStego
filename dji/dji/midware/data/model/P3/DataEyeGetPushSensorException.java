package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataEyeGetPushSensorException extends n {
    private static DataEyeGetPushSensorException instance = null;

    public static synchronized DataEyeGetPushSensorException getInstance() {
        DataEyeGetPushSensorException dataEyeGetPushSensorException;
        synchronized (DataEyeGetPushSensorException.class) {
            if (instance == null) {
                instance = new DataEyeGetPushSensorException();
            }
            dataEyeGetPushSensorException = instance;
        }
        return dataEyeGetPushSensorException;
    }

    public int getCntIndex() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public boolean isBottomImageExposureTooLong() {
        return (((Integer) get(1, 2, Integer.class)).intValue() & 8) != 0;
    }

    public boolean isBottomImageDiff() {
        return (((Integer) get(1, 2, Integer.class)).intValue() & 16) != 0;
    }

    public boolean isBottomUnderExposure() {
        return (((Integer) get(1, 2, Integer.class)).intValue() & 32) != 0;
    }

    public boolean isBottomOverExposure() {
        return (((Integer) get(1, 2, Integer.class)).intValue() & 64) != 0;
    }

    public boolean isBottomImageException() {
        return (((Integer) get(1, 2, Integer.class)).intValue() & 128) != 0;
    }

    public boolean isFrontImageExposureTooLong() {
        return (((Integer) get(3, 2, Integer.class)).intValue() & 8) != 0;
    }

    public boolean isFrontImageDiff() {
        return (((Integer) get(3, 2, Integer.class)).intValue() & 16) != 0;
    }

    public boolean isFrontUnderExposure() {
        return (((Integer) get(3, 2, Integer.class)).intValue() & 32) != 0;
    }

    public boolean isFrontOverExposure() {
        return (((Integer) get(3, 2, Integer.class)).intValue() & 64) != 0;
    }

    public boolean isFrontImageException() {
        return (((Integer) get(3, 2, Integer.class)).intValue() & 128) != 0;
    }

    public boolean isBackImageExposureTooLong() {
        return (((Integer) get(5, 2, Integer.class)).intValue() & 8) != 0;
    }

    public boolean isBackImageDiff() {
        return (((Integer) get(5, 2, Integer.class)).intValue() & 16) != 0;
    }

    public boolean isBackUnderExposure() {
        return (((Integer) get(5, 2, Integer.class)).intValue() & 32) != 0;
    }

    public boolean isBackOverExposure() {
        return (((Integer) get(5, 2, Integer.class)).intValue() & 64) != 0;
    }

    public boolean isBackImageException() {
        return (((Integer) get(5, 2, Integer.class)).intValue() & 128) != 0;
    }

    public boolean isRight3DTOFAbnormal() {
        return (((Integer) get(7, 2, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isRightImageExposureTooLong() {
        return (((Integer) get(7, 2, Integer.class)).intValue() & 8) != 0;
    }

    public boolean isRightImageDiff() {
        return (((Integer) get(7, 2, Integer.class)).intValue() & 16) != 0;
    }

    public boolean isRightUnderExposure() {
        return (((Integer) get(7, 2, Integer.class)).intValue() & 32) != 0;
    }

    public boolean isRightOverExposure() {
        return (((Integer) get(7, 2, Integer.class)).intValue() & 64) != 0;
    }

    public boolean isRightImageException() {
        return (((Integer) get(7, 2, Integer.class)).intValue() & 128) != 0;
    }

    public boolean isLeft3DTOFAbnormal() {
        return (((Integer) get(9, 2, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isLeftImageExposureTooLong() {
        return (((Integer) get(9, 2, Integer.class)).intValue() & 8) != 0;
    }

    public boolean isLeftImageDiff() {
        return (((Integer) get(9, 2, Integer.class)).intValue() & 16) != 0;
    }

    public boolean isLeftUnderExposure() {
        return (((Integer) get(9, 2, Integer.class)).intValue() & 32) != 0;
    }

    public boolean isLeftOverExposure() {
        return (((Integer) get(9, 2, Integer.class)).intValue() & 64) != 0;
    }

    public boolean isLeftImageException() {
        return (((Integer) get(9, 2, Integer.class)).intValue() & 128) != 0;
    }

    protected void doPack() {
    }
}
