package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import dji.thirdparty.afinal.c.c;

public class DataEyeGetPushPointAvoidance extends n {
    private static DataEyeGetPushPointAvoidance instance = null;

    public static synchronized DataEyeGetPushPointAvoidance getInstance() {
        DataEyeGetPushPointAvoidance dataEyeGetPushPointAvoidance;
        synchronized (DataEyeGetPushPointAvoidance.class) {
            if (instance == null) {
                instance = new DataEyeGetPushPointAvoidance();
            }
            dataEyeGetPushPointAvoidance = instance;
        }
        return dataEyeGetPushPointAvoidance;
    }

    public int getAlertLevel() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getObserveCount() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int[] getObserveValues() {
        int observeCount = getObserveCount();
        if (observeCount == 0) {
            return null;
        }
        int[] iArr = new int[observeCount];
        c.b(iArr, Integer.MAX_VALUE);
        int i = 0;
        while (i < observeCount && i + 2 < this._recData.length) {
            iArr[i] = ((Integer) get(i + 2, 1, Integer.class)).intValue();
            i++;
        }
        return iArr;
    }

    protected void doPack() {
    }
}
