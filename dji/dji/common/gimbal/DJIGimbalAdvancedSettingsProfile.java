package dji.common.gimbal;

public enum DJIGimbalAdvancedSettingsProfile {
    Custom1(0),
    Custom2(1),
    Fast(3),
    Medium(4),
    Slow(5),
    Unknown(255);
    
    private int value;

    private DJIGimbalAdvancedSettingsProfile(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIGimbalAdvancedSettingsProfile find(int i) {
        DJIGimbalAdvancedSettingsProfile dJIGimbalAdvancedSettingsProfile = Custom1;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIGimbalAdvancedSettingsProfile;
    }
}
