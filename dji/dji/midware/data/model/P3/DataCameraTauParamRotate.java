package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;

public class DataCameraTauParamRotate extends DataCameraTauParamer {

    public enum RotateType {
        ANGLE_0(0),
        ANGLE_90(1),
        ANGLE_180(2),
        ANGLE_270(3),
        OTHER(100);
        
        private int f;

        private RotateType(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }

        public boolean a(int i) {
            return this.f == i;
        }

        public static RotateType find(int i) {
            RotateType rotateType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return rotateType;
        }
    }

    public DataCameraTauParamRotate() {
        this.b = ParamCmd.PICTURE_ROTATE;
    }

    public DataCameraTauParamRotate a(RotateType rotateType) {
        this.c = new byte[1];
        this.c[0] = (byte) rotateType.a();
        return this;
    }

    public RotateType a() {
        if (this._recData == null || this._recData.length <= 0) {
            return RotateType.ANGLE_0;
        }
        return RotateType.find(((Integer) get(0, 1, Integer.class)).intValue());
    }
}
