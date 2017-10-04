package dji.common.remotecontroller;

public class DJIRCHardwareState {
    public DJIRCHardwareButton customButton1;
    public DJIRCHardwareButton customButton2;
    public DJIRCHardwareFlightModeSwitch flightModeSwitch;
    public DJIRCHardwareButton goHomeButton;
    public DJIRCHardwareJoystick leftHorizontal;
    public DJIRCHardwareJoystick leftVertical;
    public DJIRCHardwareLeftWheel leftWheel;
    public DJIRCHardwareButton pauseButton;
    public DJIRCHardwareButton playbackButton;
    public DJIRCHardwareButton recordButton;
    public DJIRCHardwareJoystick rightHorizontal;
    public DJIRCHardwareJoystick rightVertical;
    public DJIRCHardwareRightWheel rightWheel;
    public DJIRCHardwareButton shutterButton;
    public DJIRCHardwareTransformationSwitch transformSwitch;

    public static class DJIRCHardwareButton {
        public boolean buttonDown;
        public boolean isPresent;

        public DJIRCHardwareButton(boolean z) {
            this.buttonDown = z;
        }
    }

    public static class DJIRCHardwareFlightModeSwitch {
        public boolean isPresent;
        public DJIRCHardwareFlightModeSwitchState mode;

        public DJIRCHardwareFlightModeSwitch(DJIRCHardwareFlightModeSwitchState dJIRCHardwareFlightModeSwitchState) {
            this.mode = dJIRCHardwareFlightModeSwitchState;
        }
    }

    public enum DJIRCHardwareFlightModeSwitchState {
        A(0),
        P(1),
        F(2),
        S(3);
        
        private int value;

        private DJIRCHardwareFlightModeSwitchState(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static DJIRCHardwareFlightModeSwitchState find(int i) {
            DJIRCHardwareFlightModeSwitchState dJIRCHardwareFlightModeSwitchState = F;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return dJIRCHardwareFlightModeSwitchState;
        }
    }

    public static class DJIRCHardwareJoystick {
        public int value;

        public DJIRCHardwareJoystick(int i) {
            this.value = i;
        }
    }

    public static class DJIRCHardwareLeftWheel {
        public int value;
    }

    public static class DJIRCHardwareRightWheel {
        public boolean isPresent;
        public int value;
        public boolean wheelButtonDown;
        public boolean wheelChanged;
        public boolean wheelDirection;

        public DJIRCHardwareRightWheel(boolean z, boolean z2, boolean z3, short s) {
            this.wheelChanged = z;
            this.wheelButtonDown = z2;
            this.wheelDirection = z3;
            this.value = s;
        }

        public String toString() {
            return "wheel changed: " + this.wheelChanged + "\nwheel button down: " + this.wheelButtonDown + "\nwheel offset sign: " + this.wheelDirection + "\nwheel offset: " + this.value;
        }
    }

    public static class DJIRCHardwareTransformationSwitch {
        public boolean isPresent;
        public DJIRCHardwareTransformationSwitchState transformationSwitchState;

        public DJIRCHardwareTransformationSwitch(DJIRCHardwareTransformationSwitchState dJIRCHardwareTransformationSwitchState) {
            this.transformationSwitchState = dJIRCHardwareTransformationSwitchState;
        }
    }

    public enum DJIRCHardwareTransformationSwitchState {
        Retract(0),
        Deploy(1);
        
        private int value;

        private DJIRCHardwareTransformationSwitchState(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static DJIRCHardwareTransformationSwitchState find(int i) {
            DJIRCHardwareTransformationSwitchState dJIRCHardwareTransformationSwitchState = Retract;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return dJIRCHardwareTransformationSwitchState;
        }
    }

    public DJIRCHardwareState() {
        this.leftHorizontal = new DJIRCHardwareJoystick(0);
        this.leftVertical = new DJIRCHardwareJoystick(0);
        this.rightVertical = new DJIRCHardwareJoystick(0);
        this.rightHorizontal = new DJIRCHardwareJoystick(0);
        this.leftWheel = new DJIRCHardwareLeftWheel();
        this.rightWheel = new DJIRCHardwareRightWheel();
        this.transformSwitch = new DJIRCHardwareTransformationSwitch(DJIRCHardwareTransformationSwitchState.Retract);
        this.flightModeSwitch = new DJIRCHardwareFlightModeSwitch(DJIRCHardwareFlightModeSwitchState.F);
        this.goHomeButton = new DJIRCHardwareButton(false);
        this.recordButton = new DJIRCHardwareButton(false);
        this.shutterButton = new DJIRCHardwareButton(false);
        this.playbackButton = new DJIRCHardwareButton(false);
        this.pauseButton = new DJIRCHardwareButton(false);
        this.customButton1 = new DJIRCHardwareButton(false);
        this.customButton2 = new DJIRCHardwareButton(false);
    }

    public DJIRCHardwareState(DJIRCHardwareJoystick dJIRCHardwareJoystick, DJIRCHardwareJoystick dJIRCHardwareJoystick2, DJIRCHardwareJoystick dJIRCHardwareJoystick3, DJIRCHardwareJoystick dJIRCHardwareJoystick4, DJIRCHardwareLeftWheel dJIRCHardwareLeftWheel, DJIRCHardwareRightWheel dJIRCHardwareRightWheel, DJIRCHardwareTransformationSwitch dJIRCHardwareTransformationSwitch, DJIRCHardwareFlightModeSwitch dJIRCHardwareFlightModeSwitch, DJIRCHardwareButton dJIRCHardwareButton, DJIRCHardwareButton dJIRCHardwareButton2, DJIRCHardwareButton dJIRCHardwareButton3, DJIRCHardwareButton dJIRCHardwareButton4, DJIRCHardwareButton dJIRCHardwareButton5, DJIRCHardwareButton dJIRCHardwareButton6) {
        this.leftHorizontal = dJIRCHardwareJoystick;
        this.leftVertical = dJIRCHardwareJoystick2;
        this.rightVertical = dJIRCHardwareJoystick3;
        this.rightHorizontal = dJIRCHardwareJoystick4;
        this.leftWheel = dJIRCHardwareLeftWheel;
        this.rightWheel = dJIRCHardwareRightWheel;
        this.transformSwitch = dJIRCHardwareTransformationSwitch;
        this.flightModeSwitch = dJIRCHardwareFlightModeSwitch;
        this.goHomeButton = dJIRCHardwareButton;
        this.recordButton = dJIRCHardwareButton2;
        this.shutterButton = dJIRCHardwareButton3;
        this.playbackButton = dJIRCHardwareButton4;
        this.customButton1 = dJIRCHardwareButton5;
        this.customButton2 = dJIRCHardwareButton6;
    }
}
