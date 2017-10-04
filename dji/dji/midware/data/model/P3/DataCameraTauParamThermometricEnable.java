package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;

public class DataCameraTauParamThermometricEnable extends DataCameraTauParamer {

    public enum ThermometricType {
        DISABLED(0),
        SPOT(1),
        AREA(2),
        OTHER(99);
        
        private final int e;

        private ThermometricType(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        private boolean a(int i) {
            return i == this.e;
        }

        public static ThermometricType find(int i) {
            for (ThermometricType thermometricType : values()) {
                if (thermometricType.a(i)) {
                    return thermometricType;
                }
            }
            return DISABLED;
        }
    }

    public DataCameraTauParamThermometricEnable() {
        this.b = ParamCmd.THERMOMETRIC_TYPE;
    }

    public DataCameraTauParamThermometricEnable a(ThermometricType thermometricType) {
        this.c = new byte[1];
        this.c[0] = (byte) thermometricType.a();
        return this;
    }

    public ThermometricType a() {
        return ThermometricType.find(((Integer) get(0, 1, Integer.class)).intValue());
    }
}
