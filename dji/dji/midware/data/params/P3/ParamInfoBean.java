package dji.midware.data.params.P3;

import dji.midware.data.model.P3.DataFlycGetParamInfo.Attribute;
import dji.midware.data.model.P3.DataFlycGetParamInfo.TypeId;
import dji.midware.natives.GroudStation;
import dji.midware.util.c;

public class ParamInfoBean {
    public int attribute;
    public String defaultValue;
    public int index;
    public String maxValue;
    public String minValue;
    public String name;
    public int size;
    public int typeID;

    public ParamInfo getParamInfo() {
        TypeId find = TypeId.find(this.typeID);
        if (find.value() == TypeId.OTHER.value()) {
            return null;
        }
        ParamInfo paramInfo = new ParamInfo();
        b bVar = new b();
        switch (find) {
            case INT08S:
            case INT16S:
            case INT32S:
            case INT08U:
            case INT16U:
                paramInfo.type = Integer.class;
                bVar.a = Integer.valueOf(this.minValue);
                bVar.b = Integer.valueOf(this.maxValue);
                bVar.c = Integer.valueOf(this.defaultValue);
                paramInfo.range = bVar;
                break;
            case INT64S:
            case INT32U:
            case INT64U:
                paramInfo.type = Long.class;
                bVar.a = Long.valueOf(this.minValue);
                bVar.b = Long.valueOf(this.maxValue);
                bVar.c = Long.valueOf(this.defaultValue);
                paramInfo.range = bVar;
                break;
            case BYTE:
                paramInfo.type = Byte.class;
                bVar.a = Byte.valueOf(this.minValue);
                bVar.b = Byte.valueOf(this.maxValue);
                bVar.c = Byte.valueOf(this.defaultValue);
                paramInfo.range = bVar;
                break;
            case DOUBLE:
                paramInfo.type = Double.class;
                bVar.a = Double.valueOf(this.minValue);
                bVar.b = Double.valueOf(this.maxValue);
                bVar.c = Double.valueOf(this.defaultValue);
                paramInfo.range = bVar;
                break;
            default:
                paramInfo.type = Float.class;
                bVar.a = Float.valueOf(this.minValue);
                bVar.b = Float.valueOf(this.maxValue);
                bVar.c = Float.valueOf(this.defaultValue);
                paramInfo.range = bVar;
                break;
        }
        paramInfo.index = this.index;
        paramInfo.typeId = find;
        paramInfo.size = this.size;
        paramInfo.attribute = Attribute.find(this.attribute);
        paramInfo.name = this.name;
        paramInfo.value = Integer.valueOf(0);
        paramInfo.hash = GroudStation.native_hashFromString(c.a(this.name));
        return paramInfo;
    }
}
