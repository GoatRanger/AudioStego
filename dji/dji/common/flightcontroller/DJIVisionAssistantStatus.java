package dji.common.flightcontroller;

public enum DJIVisionAssistantStatus {
    Closed(0),
    Disabled(1),
    Invalid(2),
    Normal(3),
    Unknown(255);
    
    private int value;

    private DJIVisionAssistantStatus(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIVisionAssistantStatus find(int i) {
        DJIVisionAssistantStatus dJIVisionAssistantStatus = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIVisionAssistantStatus;
    }
}
