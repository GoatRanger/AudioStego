package dji.pilot.upgrade;

import android.util.Log;
import dji.midware.data.config.P3.DeviceType;

public class FirmwareVersion {
    private static final String TAG = "FirmwareVersion";
    public DeviceType deviceType = null;
    public String firmware = null;
    public int moduleId = -1;
    public long version = -1;
    public String versionStr = null;

    public FirmwareVersion(String str, String str2) {
        setFirmware(str);
        setVersion(str2);
    }

    public FirmwareVersion(String str) {
        setFirmware(str);
    }

    public void setFirmware(String str) {
        this.firmware = str;
        this.deviceType = DeviceType.find(Integer.valueOf(str.substring(0, 2)).intValue());
        this.moduleId = Integer.valueOf(str.substring(2, 4)).intValue();
    }

    public void setVersion(String str) {
        this.versionStr = str;
        String[] split = str.split("\\.");
        if (split.length != 4) {
            Log.d("TAG", str);
        }
        if (this.deviceType == DeviceType.CAMERA && (this.moduleId == 0 || this.moduleId == 1)) {
            this.version = Long.valueOf(split[2] + split[3]).longValue() + ((((Long.valueOf(split[0]).longValue() * 256) * 256) * 256) + ((Long.valueOf(split[1]).longValue() * 256) * 256));
            return;
        }
        this.version = Long.valueOf(split[3]).longValue() + (((((Long.valueOf(split[0]).longValue() * 256) * 256) * 256) + ((Long.valueOf(split[1]).longValue() * 256) * 256)) + (Long.valueOf(split[2]).longValue() * 256));
    }

    public String toString() {
        return this.firmware + ":" + this.versionStr + "(" + this.version + ")";
    }
}
