package dji.midware.data.params.P3;

import dji.midware.data.model.P3.DataFlycGetParamInfo.Attribute;
import dji.midware.data.model.P3.DataFlycGetParamInfo.TypeId;

public class ParamInfo {
    public Attribute attribute;
    public long hash;
    public int index;
    public String name;
    public b range;
    public Number setvalue;
    public int size;
    public Class<? extends Number> type;
    public TypeId typeId;
    public Number value;

    public String toString() {
        return "name=" + this.name + " hash=" + this.hash + " index=" + this.index + " value=" + this.value + " typeid=" + this.typeId.toString() + " type=" + this.type.getSimpleName() + " size=" + this.size + " attr=" + this.attribute.toString() + " range=" + this.range.toString();
    }

    public ParamInfoBean getBean() {
        ParamInfoBean paramInfoBean = new ParamInfoBean();
        paramInfoBean.index = this.index;
        paramInfoBean.typeID = this.typeId.value();
        paramInfoBean.size = this.size;
        paramInfoBean.attribute = this.attribute.value();
        paramInfoBean.maxValue = this.range.b + "";
        paramInfoBean.minValue = this.range.a + "";
        paramInfoBean.defaultValue = this.range.c + "";
        paramInfoBean.name = this.name;
        return paramInfoBean;
    }

    public boolean isCorrect(Number number) {
        switch (1.a[this.typeId.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                if (this.range.b.intValue() >= number.intValue() && this.range.a.intValue() <= number.intValue()) {
                    return true;
                }
            case 6:
            case 7:
            case 8:
                if (this.range.b.longValue() >= number.longValue() && this.range.a.longValue() <= number.longValue()) {
                    return true;
                }
            case 9:
                if (this.range.b.byteValue() >= number.byteValue() && this.range.a.byteValue() <= number.byteValue()) {
                    return true;
                }
            case 10:
                if (this.range.b.doubleValue() >= number.doubleValue() && this.range.a.doubleValue() <= number.doubleValue()) {
                    return true;
                }
            default:
                if (this.range.b.floatValue() >= number.floatValue() && this.range.a.floatValue() <= number.floatValue()) {
                    return true;
                }
        }
        return false;
    }
}
