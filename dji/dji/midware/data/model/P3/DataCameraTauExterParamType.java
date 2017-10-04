package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;

public class DataCameraTauExterParamType extends DataCameraTauParamer {

    public enum ExterParamType {
        USER1(0),
        USER2(1),
        USER3(2),
        OTHER(99);
        
        private final int e;

        private ExterParamType(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        private boolean a(int i) {
            return i == this.e;
        }

        public static ExterParamType find(int i) {
            for (ExterParamType exterParamType : values()) {
                if (exterParamType.a(i)) {
                    return exterParamType;
                }
            }
            return USER1;
        }
    }

    public DataCameraTauExterParamType() {
        this.b = ParamCmd.EXTER_PARAM_TYPE;
    }

    public DataCameraTauExterParamType a(ExterParamType exterParamType) {
        this.c = new byte[1];
        this.c[0] = (byte) exterParamType.a();
        return this;
    }

    public ExterParamType a() {
        return ExterParamType.find(((Integer) get(0, 1, Integer.class)).intValue());
    }
}
