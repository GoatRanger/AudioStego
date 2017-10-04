package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataRcGetPushRcCustomButtonsStatus extends n {
    private static DataRcGetPushRcCustomButtonsStatus instance = null;

    public static synchronized DataRcGetPushRcCustomButtonsStatus getInstance() {
        DataRcGetPushRcCustomButtonsStatus dataRcGetPushRcCustomButtonsStatus;
        synchronized (DataRcGetPushRcCustomButtonsStatus.class) {
            if (instance == null) {
                instance = new DataRcGetPushRcCustomButtonsStatus();
            }
            dataRcGetPushRcCustomButtonsStatus = instance;
        }
        return dataRcGetPushRcCustomButtonsStatus;
    }

    public int gets() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public boolean isUp() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 1) == 1;
    }

    public boolean isDown() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 2) == 2;
    }

    public boolean isPressed() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 4) == 4;
    }

    public boolean isLeft() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 8) == 8;
    }

    public boolean isRight() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 16) == 16;
    }

    public boolean isC1Pressed() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 32) == 32;
    }

    public boolean isC2Pressed() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 64) == 64;
    }

    protected void doPack() {
    }
}
