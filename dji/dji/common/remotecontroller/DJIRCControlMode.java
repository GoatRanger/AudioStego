package dji.common.remotecontroller;

public class DJIRCControlMode {
    private static final int DJI_RC_CONTROL_CHANNEL_SIZE = 4;
    public DJIRCControlChannel[] controlChannel;
    public DJIRCControlStyle controlStyle;

    public DJIRCControlMode() {
        this.controlChannel = new DJIRCControlChannel[4];
    }

    public DJIRCControlMode(DJIRCControlStyle dJIRCControlStyle, DJIRCControlChannel[] dJIRCControlChannelArr) {
        this.controlChannel = new DJIRCControlChannel[4];
        this.controlStyle = dJIRCControlStyle;
        this.controlChannel = dJIRCControlChannelArr;
    }
}
