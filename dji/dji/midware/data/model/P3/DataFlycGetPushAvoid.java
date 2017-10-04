package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import dji.thirdparty.afinal.c.c;

public class DataFlycGetPushAvoid extends n {
    private static DataFlycGetPushAvoid instance = null;
    private final int[] mDistances = new int[4];

    public static synchronized DataFlycGetPushAvoid getInstance() {
        DataFlycGetPushAvoid dataFlycGetPushAvoid;
        synchronized (DataFlycGetPushAvoid.class) {
            if (instance == null) {
                instance = new DataFlycGetPushAvoid();
            }
            dataFlycGetPushAvoid = instance;
        }
        return dataFlycGetPushAvoid;
    }

    public void setRecData(byte[] bArr) {
        super.setRecData(bArr);
    }

    public boolean isVisualSensorEnable() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isVisualSensorWork() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 2) != 0;
    }

    public boolean isInStop() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 4) != 0;
    }

    public int[] getDistance() {
        c.b(this.mDistances, 0);
        int i;
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() >= 10) {
            if (this._recData != null && this._recData.length >= 9) {
                for (i = 0; i < this.mDistances.length; i++) {
                    this.mDistances[i] = ((Integer) get((i * 2) + 1, 2, Integer.class)).intValue();
                }
            }
        } else if (this._recData != null && this._recData.length >= 5) {
            for (i = 0; i < this.mDistances.length; i++) {
                this.mDistances[i] = ((Integer) get(i + 1, 1, Integer.class)).intValue();
            }
        }
        return this.mDistances;
    }

    protected void doPack() {
    }
}
