package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataFlycGetParamInfo.Attribute;
import dji.midware.data.model.P3.DataFlycGetParamInfo.TypeId;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.data.params.P3.b;
import dji.midware.e.e;

public class DataFlycGetParamInfoByHash extends n implements e {
    private static DataFlycGetParamInfoByHash mInstance = null;
    private long hash;
    private String mIndex = null;

    public static DataFlycGetParamInfoByHash getInstance() {
        if (mInstance == null) {
            mInstance = new DataFlycGetParamInfoByHash();
        }
        return mInstance;
    }

    public DataFlycGetParamInfoByHash setIndex(String str) {
        ParamInfo read = d.read(str);
        this.mIndex = str;
        this.hash = read.hash;
        return this;
    }

    private void setRange(b bVar, Class<? extends Number> cls) {
        bVar.a = get(7, 4, cls);
        bVar.b = get(11, 4, cls);
        bVar.c = get(15, 4, cls);
    }

    public ParamInfo getParamInfo() {
        TypeId find = TypeId.find(((Integer) get(1, 2, Integer.class)).intValue());
        ParamInfo paramInfo = new ParamInfo();
        b bVar = new b();
        switch (find) {
            case INT08S:
            case INT16S:
            case INT32S:
            case INT08U:
            case INT16U:
                paramInfo.type = Integer.class;
                break;
            case INT64S:
            case INT32U:
            case INT64U:
                paramInfo.type = Long.class;
                break;
            case BYTE:
                paramInfo.type = Byte.class;
                break;
            case DOUBLE:
                paramInfo.type = Double.class;
                break;
            default:
                paramInfo.type = Float.class;
                break;
        }
        paramInfo.typeId = find;
        paramInfo.size = ((Integer) get(3, 2, Integer.class)).intValue();
        paramInfo.attribute = Attribute.find(((Integer) get(5, 2, Integer.class)).intValue());
        setRange(bVar, paramInfo.type);
        paramInfo.range = bVar;
        if (this._recData == null || this._recData.length - 19 < 0) {
            paramInfo.name = "";
        } else {
            paramInfo.name = get(19, this._recData.length - 19);
        }
        return paramInfo;
    }

    public void start(dji.midware.e.d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = q.b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.aR.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[4];
        dji.midware.util.c.a(dji.midware.util.c.b(this.hash), this._sendData, 0);
    }
}
