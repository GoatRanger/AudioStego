package dji.midware.data.model.P3;

import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import dji.midware.data.manager.P3.n;

public class Data2100GetPushCheckStatus extends n {
    private static Data2100GetPushCheckStatus instance = null;

    public static synchronized Data2100GetPushCheckStatus getInstance() {
        Data2100GetPushCheckStatus data2100GetPushCheckStatus;
        synchronized (Data2100GetPushCheckStatus.class) {
            if (instance == null) {
                instance = new Data2100GetPushCheckStatus();
            }
            data2100GetPushCheckStatus = instance;
        }
        return data2100GetPushCheckStatus;
    }

    public boolean hasException() {
        return isForeSightLeftAbnormal() || isForeSightRightAbnormal() || isDownSightLeftAbnormal() || isDownSightRightAbnormal() || isDownSightDemarkAbnormal() || isForeSightDemarkAbnormal() || isBackSightLeftAbnormal() || isBackSightRightAbnormal() || isBackSightDemarkAbnormal() || isStoreAbnormal() || is1860UsbAbnormal() || isMCUARTAbnormal() || isSwaveAbnormal() || isInnerAbnormal() || isAutoExpAbnormal() || isDepthImageAbnormal() || isVOAbnormal() || isAvoidanceAbnormal() || isCPLDConnAbnormal() || isMCUARTSendAbnormal() || isLRTAbnormal() || isPropellerCover() || isEasySelfCalResult() || needPcCalibrate();
    }

    public boolean isDownSightLeftAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isDownSightRightAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 2) != 0;
    }

    public boolean isForeSightLeftAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 4) != 0;
    }

    public boolean isForeSightRightAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 8) != 0;
    }

    public boolean isBackSightLeftAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 64) != 0;
    }

    public boolean isBackSightRightAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 128) != 0;
    }

    public boolean isDownSightDemarkAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 1024) != 0;
    }

    public boolean isForeSightDemarkAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 2048) != 0;
    }

    public boolean isBackSightDemarkAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 8192) != 0;
    }

    public boolean isStoreAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 32768) != 0;
    }

    public boolean is1860UsbAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 65536) != 0;
    }

    public boolean isMCUARTAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 131072) != 0;
    }

    public boolean isSwaveAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 262144) != 0;
    }

    public boolean isInnerAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 524288) != 0;
    }

    public boolean isAutoExpAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 1048576) != 0;
    }

    public boolean isDepthImageAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 2097152) != 0;
    }

    public boolean isVOAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 4194304) != 0;
    }

    public boolean isAvoidanceAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & GravityCompat.RELATIVE_LAYOUT_DIRECTION) != 0;
    }

    public boolean isCPLDConnAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & ViewCompat.MEASURED_STATE_TOO_SMALL) != 0;
    }

    public boolean isMCUARTSendAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & MediaHttpDownloader.MAXIMUM_CHUNK_SIZE) != 0;
    }

    public boolean isLRTAbnormal() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 67108864) != 0;
    }

    public boolean isPropellerCover() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 134217728) != 0;
    }

    public boolean isEasySelfCalResult() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 268435456) != 0;
    }

    public boolean needPcCalibrate() {
        return (((Integer) get(0, 4, Integer.class)).intValue() & 536870912) != 0;
    }

    protected void doPack() {
    }
}
