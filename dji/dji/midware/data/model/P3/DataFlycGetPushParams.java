package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.n;

public class DataFlycGetPushParams extends n {
    private static DataFlycGetPushParams instance = null;

    public static synchronized DataFlycGetPushParams getInstance() {
        DataFlycGetPushParams dataFlycGetPushParams;
        synchronized (DataFlycGetPushParams.class) {
            if (instance == null) {
                instance = new DataFlycGetPushParams();
                DataFlycGetPushParamsByHash.getInstance();
                DataFlycGetPushParamsByIndex.getInstance();
            }
            dataFlycGetPushParams = instance;
        }
        return dataFlycGetPushParams;
    }

    public String getFirstIndex() {
        if (d.isNew()) {
            return DataFlycGetPushParamsByHash.getInstance().getFirstIndex();
        }
        return DataFlycGetPushParamsByIndex.getInstance().getFirstIndex();
    }

    protected void doPack() {
    }
}
