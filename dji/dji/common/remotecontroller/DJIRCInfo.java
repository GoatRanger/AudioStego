package dji.common.remotecontroller;

public class DJIRCInfo {
    public DJIRCControlPermission controlPermission;
    public int identifier;
    public String name;
    public String password;
    public short signalQuality;

    public DJIRCInfo(int i, String str, String str2, short s, DJIRCControlPermission dJIRCControlPermission) {
        this.identifier = i;
        this.name = str;
        this.password = str2;
        this.signalQuality = s;
        this.controlPermission = dJIRCControlPermission;
    }
}
