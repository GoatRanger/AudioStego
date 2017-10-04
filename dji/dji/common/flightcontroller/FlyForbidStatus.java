package dji.common.flightcontroller;

public enum FlyForbidStatus {
    NoRestriction(0),
    ApproachingFlyForbidArea(2),
    AlreadyInWarningArea(3),
    AlreadyInFlightForbidArea(4),
    Unknown(255);
    
    private int data;

    private FlyForbidStatus(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static FlyForbidStatus find(int i) {
        FlyForbidStatus flyForbidStatus = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return flyForbidStatus;
    }
}
