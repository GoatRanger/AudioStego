package dji.midware.data.model.P3;

import android.support.v4.view.InputDeviceCompat;
import dji.midware.data.manager.P3.n;

public class DataOsdGetPushSDRNfParams extends n {
    private static DataOsdGetPushSDRNfParams instance = null;

    public enum DisLossEvent {
        NONE(0),
        GROUND_INTERFERED(1),
        UAV_INTERFERED(2),
        SIGNAL_BLOCK(3);
        
        private int value;

        private DisLossEvent(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static DisLossEvent find(int i) {
            DisLossEvent disLossEvent = NONE;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return disLossEvent;
        }
    }

    public static synchronized DataOsdGetPushSDRNfParams getInstance() {
        DataOsdGetPushSDRNfParams dataOsdGetPushSDRNfParams;
        synchronized (DataOsdGetPushSDRNfParams.class) {
            if (instance == null) {
                instance = new DataOsdGetPushSDRNfParams();
            }
            dataOsdGetPushSDRNfParams = instance;
        }
        return dataOsdGetPushSDRNfParams;
    }

    public int get1KmOffset() {
        return ((Integer) get(0, 1, Integer.class)).intValue() + InputDeviceCompat.SOURCE_ANY;
    }

    public int getPathLossOffset() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getRcLinkOffset() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public int getTxPowerOffset() {
        return ((Integer) get(3, 1, Integer.class)).intValue();
    }

    public DisLossEvent getDisLossInd() {
        return DisLossEvent.find(((Integer) get(4, 1, Integer.class)).intValue());
    }

    public int getSigBarInd() {
        return ((Integer) get(5, 1, Integer.class)).intValue();
    }

    public int getDlPwrAccu() {
        return ((Integer) get(6, 1, Integer.class)).intValue();
    }

    public int getMaxNf20M() {
        return ((Integer) get(7, 2, Integer.class)).intValue();
    }

    public int getMinNf20M() {
        return ((Integer) get(9, 2, Integer.class)).intValue();
    }

    public int getMaxNf10M() {
        return ((Integer) get(11, 2, Integer.class)).intValue();
    }

    public int getMinNf10M() {
        return ((Integer) get(13, 2, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
