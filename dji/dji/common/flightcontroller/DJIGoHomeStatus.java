package dji.common.flightcontroller;

public enum DJIGoHomeStatus {
    None(0),
    TurnDirectionToHomePoint(2),
    GoUpToHeight(1),
    AutoFlyToHomePoint(4),
    GoDownToGround(7),
    Completion(8),
    Braking(5),
    Bypassing(6),
    Failed(255);
    
    private int data;

    private DJIGoHomeStatus(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIGoHomeStatus find(int i) {
        DJIGoHomeStatus dJIGoHomeStatus = Failed;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIGoHomeStatus;
    }
}
