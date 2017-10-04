package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.util.c;

public class DataOsdGetPushWirelessState extends n {
    private static DataOsdGetPushWirelessState instance = null;

    public enum SdrWirelessState {
        STRONG_DISTURBANCE(0),
        VIDEO_DISTURBANCE(1),
        RC_DISTURBANCE(2),
        LOW_SIGNAL_POWER(3),
        CUSTOM_SIGNAL_DISTURBANCE(4),
        RC_TO_GLASS_DIST(5),
        UAV_HAL_RESTART(6),
        GLASS_DIST_RC_ANTENNA(7),
        DISCONNECT_RC_DISTURB(8),
        DISCONNECT_UAV_DISTURB(9),
        DISCONNECT_WEEK_SIGNAL(10),
        INTERNAL_EVENT(255),
        NONE(256);
        
        private int mValue;

        private SdrWirelessState(int i) {
            this.mValue = i;
        }

        public int value() {
            return this.mValue;
        }

        public static SdrWirelessState find(int i) {
            SdrWirelessState[] values = values();
            int length = values.length;
            for (int i2 = 0; i2 != length; i2++) {
                if (i == values[i2].value()) {
                    return values[i2];
                }
            }
            return NONE;
        }
    }

    public static synchronized DataOsdGetPushWirelessState getInstance() {
        DataOsdGetPushWirelessState dataOsdGetPushWirelessState;
        synchronized (DataOsdGetPushWirelessState.class) {
            if (instance == null) {
                instance = new DataOsdGetPushWirelessState();
            }
            dataOsdGetPushWirelessState = instance;
        }
        return dataOsdGetPushWirelessState;
    }

    public SdrWirelessState getEventCode() {
        return SdrWirelessState.find(((Integer) get(0, 2, Integer.class)).intValue());
    }

    public String getInternalEvent() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this._recData == null) {
            return stringBuilder.toString();
        }
        int i = 2;
        while (i < this._recData.length) {
            if (this._recData[i] == (byte) 0) {
                stringBuilder.append((char) this._recData[i]);
                break;
            }
            if (c.e(this._recData[i]) || this._recData[i] == (byte) 61) {
                stringBuilder.append((char) this._recData[i]);
            } else {
                stringBuilder.append(this._recData[i]);
            }
            i++;
        }
        return stringBuilder.toString();
    }

    protected void doPack() {
    }
}
