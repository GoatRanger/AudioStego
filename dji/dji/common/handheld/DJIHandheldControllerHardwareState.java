package dji.common.handheld;

public class DJIHandheldControllerHardwareState {
    private DJIHandheldButtonStatus handheldButtonStatus;
    private boolean isTriggerBeingPressed;
    private JoystickHorizontalDirection joystickHorizontalDirection;
    private JoystickVerticalDirection joystickVerticalDirection;
    private DJIHandheldTriggerStatus triggerState;

    public DJIHandheldButtonStatus getHandheldButtonStatus() {
        return this.handheldButtonStatus;
    }

    public void setHandheldButtonStatus(DJIHandheldButtonStatus dJIHandheldButtonStatus) {
        this.handheldButtonStatus = dJIHandheldButtonStatus;
    }

    public DJIHandheldTriggerStatus getTriggerState() {
        return this.triggerState;
    }

    public void setTriggerState(DJIHandheldTriggerStatus dJIHandheldTriggerStatus) {
        this.triggerState = dJIHandheldTriggerStatus;
    }

    public JoystickVerticalDirection getJoystickVerticalDirection() {
        return this.joystickVerticalDirection;
    }

    public void setJoystickVerticalDirection(JoystickVerticalDirection joystickVerticalDirection) {
        this.joystickVerticalDirection = joystickVerticalDirection;
    }

    public JoystickHorizontalDirection getJoystickHorizontalDirection() {
        return this.joystickHorizontalDirection;
    }

    public void setJoystickHorizontalDirection(JoystickHorizontalDirection joystickHorizontalDirection) {
        this.joystickHorizontalDirection = joystickHorizontalDirection;
    }

    public boolean isTriggerBeingPressed() {
        return this.isTriggerBeingPressed;
    }

    public void setTriggerBeingPressed(boolean z) {
        this.isTriggerBeingPressed = z;
    }
}
