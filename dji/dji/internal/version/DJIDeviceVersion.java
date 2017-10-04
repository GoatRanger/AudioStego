package dji.internal.version;

import dji.midware.data.config.P3.DeviceType;

public class DJIDeviceVersion {
    private static final String TAG = "FirmwareVersion";
    public DeviceType deviceType = null;
    public String firmware = null;
    public int moduleId = -1;
    public long version = -1;
    public String versionStr = null;

    public DJIDeviceVersion(String str, String str2) {
        setFirmware(str);
        setVersion(str2);
    }

    public DJIDeviceVersion(String str) {
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
        }
        if (this.deviceType == DeviceType.CAMERA) {
            this.version = (long) (Integer.valueOf(split[2] + split[3]).intValue() + ((((Integer.valueOf(split[0]).intValue() * 256) * 256) * 256) + ((Integer.valueOf(split[1]).intValue() * 256) * 256)));
            return;
        }
        this.version = (long) (Integer.valueOf(split[3]).intValue() + (((((Integer.valueOf(split[0]).intValue() * 256) * 256) * 256) + ((Integer.valueOf(split[1]).intValue() * 256) * 256)) + (Integer.valueOf(split[2]).intValue() * 256)));
    }

    public String toString() {
        return this.firmware + ":" + this.versionStr;
    }
}
