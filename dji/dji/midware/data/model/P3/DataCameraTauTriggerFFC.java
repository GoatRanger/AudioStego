package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;

public class DataCameraTauTriggerFFC extends DataCameraTauParamer {
    public DataCameraTauTriggerFFC() {
        this.b = ParamCmd.TRIGGER_FFC;
        this.a = false;
    }
}
