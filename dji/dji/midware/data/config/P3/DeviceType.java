package dji.midware.data.config.P3;

public enum DeviceType {
    WHO(0),
    CAMERA(1),
    APP(2),
    FLYC(3),
    GIMBAL(4),
    CENTER(5),
    RC(6),
    WIFI(7),
    DM368(8),
    OFDM((String) 9, (int) "OSD"),
    PC(10),
    BATTERY(11),
    DIGITAL(12),
    DM368_G((String) 13, (int) false),
    OSD((String) 14, (int) false),
    TRANSFORM(15),
    TRANSFORM_G((String) 16, (int) false),
    SINGLE(17),
    DOUBLE(18),
    FPGA(19),
    FPGA_G((String) 20, (int) false),
    WIFI_G(27),
    GLASS(28),
    BROADCAST(31),
    OTHER(100);
    
    private static DeviceType[] items;
    private int data;
    private boolean isRemote;
    private String name;

    static {
        items = values();
    }

    private DeviceType(int i) {
        this.isRemote = true;
        this.name = null;
        this.data = i;
    }

    private DeviceType(int i, boolean z) {
        this.isRemote = true;
        this.name = null;
        this.data = i;
        this.isRemote = z;
    }

    private DeviceType(int i, String str) {
        this.isRemote = true;
        this.name = null;
        this.data = i;
        this.name = str;
    }

    private DeviceType(int i, String str, boolean z) {
        this.isRemote = true;
        this.name = null;
        this.data = i;
        this.name = str;
        this.isRemote = z;
    }

    public int value() {
        return this.data;
    }

    public boolean isRemote() {
        return this.isRemote;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public String toString() {
        if (this.name == null) {
            return super.toString();
        }
        return this.name;
    }

    public static DeviceType find(int i) {
        DeviceType deviceType = OTHER;
        for (int i2 = 0; i2 < items.length; i2++) {
            if (items[i2]._equals(i)) {
                return items[i2];
            }
        }
        return deviceType;
    }

    public static boolean isGround(int i) {
        return DM368_G.value() == i || FPGA_G.value() == i || TRANSFORM_G.value() == i || RC.value() == i || WIFI_G.value() == i || OSD.value() == i;
    }

    public static boolean isGround(DeviceType deviceType) {
        return isGround(deviceType.value());
    }

    public static boolean isRemote(int i) {
        return i == FLYC.value() || i == DIGITAL.value() || i == FPGA.value() || i == CENTER.value() || i == DM368.value() || i == OFDM.value() || i == SINGLE.value() || i == TRANSFORM.value() || i == WIFI.value() || i == CAMERA.value() || i == GIMBAL.value() || i == BATTERY.value();
    }

    public static boolean isRemote(DeviceType deviceType) {
        return isRemote(deviceType.value());
    }
}
