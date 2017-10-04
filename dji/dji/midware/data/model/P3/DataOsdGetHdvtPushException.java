package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.n;

public class DataOsdGetHdvtPushException extends n {
    private static DataOsdGetHdvtPushException instance = null;
    private boolean isChannelEncryptException = false;
    private boolean isGndRfException = false;
    private boolean isUavRfException = false;

    public static synchronized DataOsdGetHdvtPushException getInstance() {
        DataOsdGetHdvtPushException dataOsdGetHdvtPushException;
        synchronized (DataOsdGetHdvtPushException.class) {
            if (instance == null) {
                instance = new DataOsdGetHdvtPushException();
            }
            dataOsdGetHdvtPushException = instance;
        }
        return dataOsdGetHdvtPushException;
    }

    protected void doPack() {
    }

    protected void post() {
        boolean z;
        if (this.pack.f == DeviceType.OFDM.value()) {
            if ((((Integer) get(0, 1, Integer.class)).intValue() & 1) != 0) {
                this.isUavRfException = true;
            } else {
                this.isUavRfException = false;
            }
        } else if (this.pack.f == DeviceType.OSD.value()) {
            if ((((Integer) get(0, 1, Integer.class)).intValue() & 1) != 0) {
                this.isGndRfException = true;
            } else {
                this.isGndRfException = false;
            }
        }
        if ((((Integer) get(0, 1, Integer.class)).intValue() & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.isChannelEncryptException = z;
        super.post();
    }

    public int getSenderType() {
        return this.pack.f;
    }

    public boolean getUavRfStatus() {
        return this.isUavRfException;
    }

    public boolean getGndRfStatus() {
        return this.isGndRfException;
    }

    public boolean getChannelEncryptStatus() {
        return this.isChannelEncryptException;
    }
}
