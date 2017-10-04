package dji.midware.data.model.P3;

import dji.midware.data.a.a.a;
import dji.midware.data.config.P3.d;
import dji.midware.data.manager.P3.n;
import dji.midware.util.k;

public class DataOsdGetPushCheckStatus extends n {
    private static DataOsdGetPushCheckStatus instance = null;

    public static synchronized DataOsdGetPushCheckStatus getInstance() {
        DataOsdGetPushCheckStatus dataOsdGetPushCheckStatus;
        synchronized (DataOsdGetPushCheckStatus.class) {
            if (instance == null) {
                instance = new DataOsdGetPushCheckStatus();
            }
            dataOsdGetPushCheckStatus = instance;
        }
        return dataOsdGetPushCheckStatus;
    }

    public boolean isOK() {
        return getFPGAinitStatus() || get58GinitStatus() || getF330initStatus() || getGPSinitStatus() || getEncryptStatus() || getStickMiddleStatus() || getPowerStatus() || getTimeoutStatus() || getResetStatus() || isInHighTemperature();
    }

    protected void setPushRecPack(a aVar) {
        if (aVar != null && aVar.n == d.a.C.a()) {
            super.setPushRecPack(aVar);
        } else if (aVar != null && aVar.n == d.a.D.a()) {
            a a = k.a(aVar);
            if (a != null) {
                super.setPushRecPack(a);
            }
        }
    }

    public boolean getStickMiddleStatus() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 1) == 1;
    }

    public boolean getFPGAinitStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 1) & 1) == 1;
    }

    public boolean get58GinitStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 2) & 1) == 1;
    }

    public boolean getF330initStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 3) & 1) == 1;
    }

    public boolean getGPSinitStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 4) & 1) == 1;
    }

    public boolean getEncryptStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 5) & 1) == 1;
    }

    public boolean getResetStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 6) & 1) == 1;
    }

    public boolean getPowerStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 7) & 1) == 1;
    }

    public boolean getTimeoutStatus() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 8) & 1) == 1;
    }

    public boolean isInHighTemperature() {
        return ((((Integer) get(0, 4, Integer.class)).intValue() >> 9) & 1) == 1;
    }

    protected void doPack() {
    }
}
