package dji.sdk.api.simulator;

public class DJISimulatorTypeDef {

    public enum DJISimulatorDroneType {
        QuadcopterI(0),
        QuadcopterX(1),
        HexrcopterYI(2),
        HexrcopterYX(3),
        HexrcopterI(4),
        HexrcopterX(5),
        OctalcopterI(6),
        OctalcopterX(7),
        Unknown(255);
        
        private int value;

        private DJISimulatorDroneType(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static DJISimulatorDroneType find(int i) {
            DJISimulatorDroneType dJISimulatorDroneType = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return dJISimulatorDroneType;
        }
    }

    public enum DJISimulatorProductType {
        Inspire(0),
        Phantom3(1),
        M100(2),
        A3(3),
        Unknown(255);
        
        private int value;

        private DJISimulatorProductType(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static DJISimulatorProductType find(int i) {
            DJISimulatorProductType dJISimulatorProductType = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return dJISimulatorProductType;
        }
    }
}
