package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.n;
import dji.midware.data.params.P3.ParamInfo;

public class DataFlycGetPushParamsByHash extends n {
    private static DataFlycGetPushParamsByHash instance = null;

    public static synchronized DataFlycGetPushParamsByHash getInstance() {
        DataFlycGetPushParamsByHash dataFlycGetPushParamsByHash;
        synchronized (DataFlycGetPushParamsByHash.class) {
            if (instance == null) {
                instance = new DataFlycGetPushParamsByHash();
            }
            dataFlycGetPushParamsByHash = instance;
        }
        return dataFlycGetPushParamsByHash;
    }

    public String getFirstIndex() {
        return d.getNameByHash(((Long) get(1, 4, Long.class)).longValue());
    }

    protected void setPushRecData(byte[] bArr) {
        super.setPushRecData(bArr);
        int i = 1;
        while (i < bArr.length) {
            ParamInfo readByHash = d.readByHash(((Long) get(i, 4, Long.class)).longValue());
            i += 4;
            d.write(readByHash.name, get(i, readByHash.size, readByHash.type));
            i = readByHash.size + i;
        }
    }

    protected void doPack() {
    }
}
