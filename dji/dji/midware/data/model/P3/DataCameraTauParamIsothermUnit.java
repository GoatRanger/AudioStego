package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;

public class DataCameraTauParamIsothermUnit extends DataCameraTauParamer {
    public DataCameraTauParamIsothermUnit() {
        this.b = ParamCmd.ISOTHERM_UNIT;
    }

    public DataCameraTauParamIsothermUnit a(int i) {
        this.c = new byte[1];
        this.c[0] = (byte) i;
        return this;
    }

    public int a() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }
}
