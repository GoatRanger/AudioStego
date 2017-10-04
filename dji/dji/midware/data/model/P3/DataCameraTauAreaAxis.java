package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;
import dji.midware.util.c;

public class DataCameraTauAreaAxis extends DataCameraTauParamer {
    public DataCameraTauAreaAxis() {
        this.b = ParamCmd.AREA_AXIS;
    }

    public DataCameraTauAreaAxis a(short s, short s2, short s3, short s4) {
        this.c = new byte[8];
        System.arraycopy(c.b(s), 0, this.c, 0, 2);
        System.arraycopy(c.b(s2), 0, this.c, 2, 2);
        System.arraycopy(c.b(s3), 0, this.c, 4, 2);
        System.arraycopy(c.b(s4), 0, this.c, 6, 2);
        return this;
    }
}
