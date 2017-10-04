package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;

public class DataCameraTauParamIsothermEnable extends DataCameraTauParamer {
    public DataCameraTauParamIsothermEnable() {
        this.b = ParamCmd.ISOTHERM_ENABLE;
    }

    public DataCameraTauParamIsothermEnable a(boolean z) {
        byte b = (byte) 1;
        this.c = new byte[1];
        byte[] bArr = this.c;
        if (!z) {
            b = (byte) 0;
        }
        bArr[0] = b;
        return this;
    }

    public boolean a() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 1) != 0;
    }
}
