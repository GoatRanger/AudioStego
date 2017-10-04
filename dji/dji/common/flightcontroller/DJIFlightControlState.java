package dji.common.flightcontroller;

public enum DJIFlightControlState {
    Manual(0),
    Atti(1),
    Landing(2),
    TakeOff(3),
    GoHome(4),
    Joystick(5),
    Navi(6),
    ClickGo(7),
    P_Atti(8),
    P_Opti(9),
    P_GPS(10),
    F_Atti(11),
    F_Opti(12),
    F_GPS(13),
    Tracking(14),
    Pointing(15),
    Sport(16),
    Novice(17),
    N_A(255);
    
    private int data;

    private DJIFlightControlState(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIFlightControlState find(int i) {
        DJIFlightControlState dJIFlightControlState = N_A;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIFlightControlState;
    }
}
