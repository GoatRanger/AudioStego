package dji.common.util;

public class DJIParamMinMaxCapability extends DJIParamCapability {
    protected Number max;
    protected Number min;

    public DJIParamMinMaxCapability(boolean z, Number number, Number number2) {
        super(z);
        this.min = number;
        this.max = number2;
    }

    public Number getMin() {
        return this.min;
    }

    public Number getMax() {
        return this.max;
    }
}
