package dji.common.remotecontroller;

public class DJIRCRemoteFocusState {
    public DJIRCRemoteFocusControlType controlType;
    public DJIRCRemoteFocusControlDirection direction;
    public boolean isFocusControlWorks = false;

    public enum DJIRCRemoteFocusControlDirection {
        Clockwise,
        CounterClockwise,
        Unknown
    }

    public enum DJIRCRemoteFocusControlType {
        Aperture,
        FocalLength,
        Unknown
    }
}
