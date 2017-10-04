package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;

public class DataCameraTauParamGainMode extends DataCameraTauParamer {

    public enum GainMode {
        AUTO(0),
        LOW(1),
        HIGH(2),
        OTHER(100);
        
        private int e;

        private GainMode(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public static GainMode find(int i) {
            GainMode gainMode = AUTO;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return gainMode;
        }
    }

    public DataCameraTauParamGainMode() {
        this.b = ParamCmd.GAIN_MODE;
    }

    public DataCameraTauParamGainMode a(GainMode gainMode) {
        this.c = new byte[1];
        this.c[0] = (byte) gainMode.a();
        return this;
    }

    public GainMode a() {
        return GainMode.find(((Integer) get(0, 1, Integer.class)).intValue());
    }
}
