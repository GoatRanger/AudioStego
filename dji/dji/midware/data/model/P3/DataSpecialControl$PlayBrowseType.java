package dji.midware.data.model.P3;

public enum DataSpecialControl$PlayBrowseType {
    CANCEL(0),
    ENTER(1),
    DELETE(2),
    PAGEDOWN(3),
    PAGEUP(4),
    RIGHT(5),
    LEFT(6),
    DOWN(7),
    UP(8),
    ZOOMOUT(9),
    ZOOMIN(10),
    MULTIPLY(11),
    SINGLE(12),
    MULTIPLY_DEL(13),
    SCALE(14),
    DRAG(15),
    OTHER(100);
    
    private int data;

    private DataSpecialControl$PlayBrowseType(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DataSpecialControl$PlayBrowseType find(int i) {
        DataSpecialControl$PlayBrowseType dataSpecialControl$PlayBrowseType = OTHER;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dataSpecialControl$PlayBrowseType;
    }
}
