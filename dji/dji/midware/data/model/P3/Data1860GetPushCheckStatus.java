package dji.midware.data.model.P3;

import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import dji.midware.data.manager.P3.n;

public class Data1860GetPushCheckStatus extends n {
    private static Data1860GetPushCheckStatus instance = null;

    public static synchronized Data1860GetPushCheckStatus getInstance() {
        Data1860GetPushCheckStatus data1860GetPushCheckStatus;
        synchronized (Data1860GetPushCheckStatus.class) {
            if (instance == null) {
                instance = new Data1860GetPushCheckStatus();
            }
            data1860GetPushCheckStatus = instance;
        }
        return data1860GetPushCheckStatus;
    }

    public boolean hasException() {
        if (isSystemUpgradeAbnormal()) {
            return false;
        }
        if (isRebootStatusAbnormal() || isThreadMonitorAbnormal() || isVideoEncodeAbnormal() || isSystemStoreAbnormal() || isHPIAbnormal() || isCPLDI2CAbnormal() || isSwaveUARTAbnormal() || isVisualUSBAbnormal() || isVisualSPIAbnormal() || isUSBOTGAbnormal() || isUSBHubAbnormal() || isMCUSBAbnormal() || isMCUARTAbnormal() || isCameraUSBAbnormal() || isCameraMIPIAbnormal() || isThermalAbnormal()) {
            return true;
        }
        return false;
    }

    public int getStatus() {
        return ((Integer) get(0, 4, Integer.class)).intValue();
    }

    public boolean isRebootStatusAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isThreadMonitorAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 2) != 0;
    }

    public boolean isSystemUpgradeAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 4) != 0;
    }

    public boolean isVideoEncodeAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 8) != 0;
    }

    public boolean isSystemStoreAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 16) != 0;
    }

    public boolean isHPIAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 32) != 0;
    }

    public boolean isCPLDI2CAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 64) != 0;
    }

    public boolean isSwaveUARTAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 128) != 0;
    }

    public boolean isVisualUSBAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 1792) != 0;
    }

    public boolean isVisualSPIAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 2048) != 0;
    }

    public boolean isUSBOTGAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 28672) != 0;
    }

    public boolean isUSBHubAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 32768) != 0;
    }

    public boolean isMCUSBAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 458752) != 0;
    }

    public boolean isMCUARTAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 524288) != 0;
    }

    public boolean isCameraUSBAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 7340032) != 0;
    }

    public boolean isCameraMIPIAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & GravityCompat.RELATIVE_LAYOUT_DIRECTION) != 0;
    }

    public boolean isThermalAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & ViewCompat.MEASURED_STATE_TOO_SMALL) != 0;
    }

    protected void doPack() {
    }
}
