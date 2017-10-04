package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import java.util.Arrays;

public class DataCameraGetPushChartInfo extends n {
    public static final int MAX_LENGTH = 64;
    private static DataCameraGetPushChartInfo instance = null;
    private final short[] mLightValues = new short[64];

    public static synchronized DataCameraGetPushChartInfo getInstance() {
        DataCameraGetPushChartInfo dataCameraGetPushChartInfo;
        synchronized (DataCameraGetPushChartInfo.class) {
            if (instance == null) {
                instance = new DataCameraGetPushChartInfo();
            }
            dataCameraGetPushChartInfo = instance;
        }
        return dataCameraGetPushChartInfo;
    }

    public short[] getParams() {
        int i = 0;
        Arrays.fill(this.mLightValues, (short) 0);
        if (this._recData != null && this._recData.length > 0) {
            int length = this._recData.length;
            while (i < length) {
                this.mLightValues[i] = (short) (this._recData[i] & 255);
                i++;
            }
        }
        return this.mLightValues;
    }

    protected void doPack() {
    }
}
