package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraTauParamer.ParamCmd;
import dji.midware.util.c;
import java.util.ArrayList;

public class DataCameraTauExterParams extends DataCameraTauParamer {
    private final ArrayList<ExterParamInfo> d;
    private int e;

    public enum ExterParamId {
        DEFAULT(0),
        TARGET_EMISSIVITY(1),
        BACKGROUND_TEMP(4),
        ATMOSPHERE_TRANSMISSION(2),
        ATMOSPHERE_TEMP(3),
        WINDOW_TRANSMISSION(5),
        WINDOW_REFLECTION(7),
        WINDOW_TEMP(6),
        WINDOW_REFLECTED_TEMP(8),
        OTHER(99);
        
        private final int k;

        private ExterParamId(int i) {
            this.k = i;
        }

        public int a() {
            return this.k;
        }

        private boolean a(int i) {
            return i == this.k;
        }

        public static ExterParamId find(int i) {
            for (ExterParamId exterParamId : values()) {
                if (exterParamId.a(i)) {
                    return exterParamId;
                }
            }
            return DEFAULT;
        }
    }

    private static final class ExterParamInfo {
        private ExterParamId a;
        private short b;

        private ExterParamInfo(ExterParamId exterParamId, short s) {
            this.a = ExterParamId.DEFAULT;
            this.b = (short) 0;
            this.a = exterParamId;
            this.b = s;
        }

        private int a() {
            return ExterParamId.DEFAULT == this.a ? 4 : 4;
        }

        private byte[] b() {
            Object obj = new byte[a()];
            obj[0] = (byte) this.a.a();
            if (ExterParamId.DEFAULT == this.a) {
                obj[1] = 2;
                System.arraycopy(c.b(this.b), 0, obj, 2, 2);
            } else {
                obj[1] = 2;
                System.arraycopy(c.b(this.b), 0, obj, 2, 2);
            }
            return obj;
        }
    }

    public DataCameraTauExterParams() {
        this.d = new ArrayList(1);
        this.e = 0;
        this.b = ParamCmd.EXTER_PARAMS;
    }

    public DataCameraTauExterParams a(ExterParamId exterParamId, short s) {
        ExterParamInfo exterParamInfo = new ExterParamInfo(exterParamId, s);
        this.e += exterParamInfo.a();
        this.d.add(exterParamInfo);
        return this;
    }

    protected void doPack() {
        this.c = new byte[this.e];
        int size = this.d.size();
        int i = 0;
        int i2 = 0;
        while (i2 < size) {
            ExterParamInfo exterParamInfo = (ExterParamInfo) this.d.get(i2);
            int a = exterParamInfo.a();
            System.arraycopy(exterParamInfo.b(), 0, this.c, i, a);
            i2++;
            i += a;
        }
        super.doPack();
    }
}
