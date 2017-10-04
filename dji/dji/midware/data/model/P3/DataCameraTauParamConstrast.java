package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;
import dji.midware.util.c;

public class DataCameraTauParamConstrast extends DataCameraTauParamer {
    public DataCameraTauParamConstrast() {
        this.b = ParamCmd.SCENE_CONTRAST;
    }

    public DataCameraTauParamConstrast a(int i) {
        this.c = c.b((short) i);
        return this;
    }

    public int a() {
        return ((Integer) get(0, 2, Integer.class)).intValue();
    }
}
