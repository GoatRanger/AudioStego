package dji.midware.data.model.P3;

public enum DataNotifyDisconnect$RebootReason {
    For1860(1),
    ForAircraft(2),
    OTHER(7);
    
    private int d;

    private DataNotifyDisconnect$RebootReason(int i) {
        this.d = i;
    }

    public int a() {
        return this.d;
    }

    public boolean a(int i) {
        return this.d == i;
    }

    public static DataNotifyDisconnect$RebootReason find(int i) {
        DataNotifyDisconnect$RebootReason dataNotifyDisconnect$RebootReason = OTHER;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2].a(i)) {
                return values()[i2];
            }
        }
        return dataNotifyDisconnect$RebootReason;
    }
}
