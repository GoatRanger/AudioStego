package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;
import dji.midware.util.c;

public class DataCameraTauParamIsothermValue extends DataCameraTauParamer {
    public DataCameraTauParamIsothermValue a(ParamCmd paramCmd) {
        this.b = paramCmd;
        return this;
    }

    public DataCameraTauParamIsothermValue a(short s) {
        this.c = c.b(s);
        return this;
    }

    public short a() {
        return ((Short) get(0, 2, Short.class)).shortValue();
    }
}
