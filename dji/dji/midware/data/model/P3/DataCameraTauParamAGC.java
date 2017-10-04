package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;

public class DataCameraTauParamAGC extends DataCameraTauParamer {

    public enum AGCType {
        LINEAR(0),
        DEFAULT(1),
        SEASKY(2),
        OUTDOOR(3),
        INDOOR(4),
        MANUAL(5),
        USER1(6),
        USER2(7),
        USER3(8),
        OTHER(100);
        
        private int k;

        private AGCType(int i) {
            this.k = i;
        }

        public int a() {
            return this.k;
        }

        public boolean a(int i) {
            return this.k == i;
        }

        public static AGCType find(int i) {
            AGCType aGCType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return aGCType;
        }
    }

    public DataCameraTauParamAGC() {
        this.b = ParamCmd.AGC;
    }

    public DataCameraTauParamAGC a(AGCType aGCType) {
        this.c = new byte[1];
        this.c[0] = (byte) aGCType.a();
        return this;
    }

    public AGCType a() {
        if (this._recData == null || this._recData.length <= 0) {
            return AGCType.LINEAR;
        }
        return AGCType.find(((Integer) get(0, 1, Integer.class)).intValue());
    }
}
