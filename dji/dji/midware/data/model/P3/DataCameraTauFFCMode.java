package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;

public class DataCameraTauFFCMode extends DataCameraTauParamer {

    public enum FFCMode {
        MANUAL(0),
        AUTO(1),
        OTHER(100);
        
        private int d;

        private FFCMode(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }

        public static FFCMode find(int i) {
            FFCMode fFCMode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return fFCMode;
        }
    }

    public DataCameraTauFFCMode() {
        this.b = ParamCmd.FFC_MODE;
    }

    public DataCameraTauFFCMode a(FFCMode fFCMode) {
        this.c = new byte[1];
        this.c[0] = (byte) fFCMode.a();
        return this;
    }

    public FFCMode a() {
        if (this._recData == null || this._recData.length <= 0) {
            return FFCMode.AUTO;
        }
        return FFCMode.find(((Integer) get(0, 1, Integer.class)).intValue());
    }
}
