package dji.midware.data.model.P3;

public enum DataOsdGetPushDebugInfo$DebugType {
    OFDM(0),
    OFDM_G(1),
    SWEEP_G(2),
    OTHER(100);
    
    private final int data;

    private DataOsdGetPushDebugInfo$DebugType(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DataOsdGetPushDebugInfo$DebugType find(int i) {
        DataOsdGetPushDebugInfo$DebugType dataOsdGetPushDebugInfo$DebugType = OFDM;
        for (DataOsdGetPushDebugInfo$DebugType dataOsdGetPushDebugInfo$DebugType2 : values()) {
            if (dataOsdGetPushDebugInfo$DebugType2._equals(i)) {
                return dataOsdGetPushDebugInfo$DebugType2;
            }
        }
        return dataOsdGetPushDebugInfo$DebugType;
    }
}
