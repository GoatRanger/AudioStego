package dji.common.airlink;

import dji.sdksharedlib.b.b.f;

@f
public enum SDRInterferedTerminal {
    Ground(1),
    UAV(2),
    Non(255);
    
    private int value;

    private SDRInterferedTerminal(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static SDRInterferedTerminal find(int i) {
        SDRInterferedTerminal sDRInterferedTerminal = Non;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return sDRInterferedTerminal;
    }
}
