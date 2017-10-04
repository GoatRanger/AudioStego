package dji.midware.data.model.P3;

public enum DataSpecialControl$FlyCtrlCmd {
    INIT(1),
    TAKEOFF(2),
    LAND(3),
    OTHER(100);
    
    private final int data;

    private DataSpecialControl$FlyCtrlCmd(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DataSpecialControl$FlyCtrlCmd find(int i) {
        DataSpecialControl$FlyCtrlCmd dataSpecialControl$FlyCtrlCmd = INIT;
        for (DataSpecialControl$FlyCtrlCmd dataSpecialControl$FlyCtrlCmd2 : values()) {
            if (dataSpecialControl$FlyCtrlCmd2._equals(i)) {
                return dataSpecialControl$FlyCtrlCmd2;
            }
        }
        return dataSpecialControl$FlyCtrlCmd;
    }
}
