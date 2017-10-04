package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.n;
import dji.midware.data.params.P3.ParamInfo;

public class DataFlycGetPushParamsByIndex extends n {
    private static DataFlycGetPushParamsByIndex instance = null;

    public static synchronized DataFlycGetPushParamsByIndex getInstance() {
        DataFlycGetPushParamsByIndex dataFlycGetPushParamsByIndex;
        synchronized (DataFlycGetPushParamsByIndex.class) {
            if (instance == null) {
                instance = new DataFlycGetPushParamsByIndex();
            }
            dataFlycGetPushParamsByIndex = instance;
        }
        return dataFlycGetPushParamsByIndex;
    }

    public String getFirstIndex() {
        return d.getNameByIndex(((Integer) get(1, 2, Integer.class)).intValue());
    }

    protected void setPushRecData(byte[] bArr) {
        super.setPushRecData(bArr);
        int i = 1;
        while (i < bArr.length) {
            ParamInfo readByIndex = d.readByIndex(((Integer) get(i, 2, Integer.class)).intValue());
            i += 2;
            d.write(readByIndex.name, get(i, readByIndex.size, readByIndex.type));
            i = readByIndex.size + i;
        }
    }

    public ParamInfo getInfo(ParamInfo paramInfo) {
        paramInfo.value = get(3, paramInfo.size, paramInfo.type);
        return paramInfo;
    }

    protected void doPack() {
    }
}
