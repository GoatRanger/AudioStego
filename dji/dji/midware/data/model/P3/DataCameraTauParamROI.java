package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;

public class DataCameraTauParamROI extends DataCameraTauParamer {

    public enum ROIType {
        FULL(0),
        SKY_EXCLUDED_33(1),
        SKY_EXCLUDED_50(2),
        OTHER(100);
        
        private int e;

        private ROIType(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public static ROIType find(int i) {
            ROIType rOIType = FULL;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return rOIType;
        }
    }

    public DataCameraTauParamROI() {
        this.b = ParamCmd.INTEREST_REGION;
    }

    public DataCameraTauParamROI a(ROIType rOIType) {
        this.c = new byte[1];
        this.c[0] = (byte) rOIType.a();
        return this;
    }

    public ROIType a() {
        if (this._recData == null || this._recData.length <= 0) {
            return ROIType.FULL;
        }
        return ROIType.find(((Integer) get(0, 1, Integer.class)).intValue());
    }
}
