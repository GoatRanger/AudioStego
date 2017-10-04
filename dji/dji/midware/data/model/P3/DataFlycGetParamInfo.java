package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.manager.P3.n;
import dji.midware.data.manager.P3.n.a;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.data.params.P3.b;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.c;
import java.util.HashMap;

public class DataFlycGetParamInfo extends n implements e {
    private static HashMap<Integer, DataFlycGetParamInfo> hashMap = new HashMap();
    private Integer index;

    public enum Attribute {
        READ_ONLY(0),
        READ_WRITE(1),
        EEPROM_WRITE(2),
        EEPROM_SPECIFIC(4),
        IMPORT_EXPORT(8),
        EEPROM_RW(EEPROM_WRITE.value() | READ_WRITE.value()),
        EEPROM_RW_IE((EEPROM_WRITE.value() | READ_WRITE.value()) | IMPORT_EXPORT.value()),
        OTHER(100);
        
        private int data;

        private Attribute(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static Attribute find(int i) {
            Attribute attribute = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return attribute;
        }
    }

    public enum TypeId {
        INT08U(0),
        INT16U(1),
        INT32U(2),
        INT64U(3),
        INT08S(4),
        INT16S(5),
        INT32S(6),
        INT64S(7),
        FLOAT(8),
        DOUBLE(9),
        BYTE(10),
        OTHER(100);
        
        private int data;

        private TypeId(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static TypeId find(int i) {
            TypeId typeId = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return typeId;
        }
    }

    private static synchronized DataFlycGetParamInfo getInstance(Integer num) {
        DataFlycGetParamInfo dataFlycGetParamInfo;
        synchronized (DataFlycGetParamInfo.class) {
            dataFlycGetParamInfo = (DataFlycGetParamInfo) hashMap.get(num);
            if (dataFlycGetParamInfo == null) {
                dataFlycGetParamInfo = new DataFlycGetParamInfo(num);
                hashMap.put(num, dataFlycGetParamInfo);
            }
        }
        return dataFlycGetParamInfo;
    }

    public DataFlycGetParamInfo(Integer num) {
        this.index = num;
    }

    protected a getDataType() {
        return a.b;
    }

    private void setRange(b bVar, Class<? extends Number> cls) {
        bVar.a = get(7, 4, cls);
        bVar.b = get(11, 4, cls);
        bVar.c = get(15, 4, cls);
    }

    public ParamInfo getInfo() {
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
        paramInfo.index = this.index.intValue();
        paramInfo.typeId = find;
        paramInfo.size = ((Integer) get(3, 2, Integer.class)).intValue();
        paramInfo.attribute = Attribute.find(((Integer) get(5, 2, Integer.class)).intValue());
        setRange(bVar, paramInfo.type);
        paramInfo.range = bVar;
        paramInfo.name = get(19, this._recData.length - 19);
        return paramInfo;
    }

    protected void doPack() {
        this._sendData = new byte[2];
        this._sendData = c.b(this.index.intValue());
    }

    public void start(d dVar) {
        dji.midware.data.a.a.c cVar = new dji.midware.data.a.a.c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = q.a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = q.b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.aM.a();
        start(cVar, dVar);
    }
}
