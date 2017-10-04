package dji.common.flightcontroller;

public enum DJIFlightControllerNoFlyStatus {
    FlyingNormally(0),
    TakeOffProhibited(1),
    ForceAutoLanding(2),
    ApproachingNoFlyZone(3),
    ReachMaxFlyingHeight(4),
    ReachMaxFlyingDistance(5),
    UnderLimitFlyZone(6),
    HeightLimited(8),
    UnknownStatus(255);
    
    private int data;

    private DJIFlightControllerNoFlyStatus(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIFlightControllerNoFlyStatus find(int i) {
        DJIFlightControllerNoFlyStatus dJIFlightControllerNoFlyStatus = UnknownStatus;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIFlightControllerNoFlyStatus;
    }
}
