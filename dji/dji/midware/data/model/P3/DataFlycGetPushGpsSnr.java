package dji.midware.data.model.P3;

import android.support.v4.media.TransportMediator;
import dji.midware.data.manager.P3.n;
import java.util.Arrays;

public class DataFlycGetPushGpsSnr extends n {
    public static final int MAX_LENGTH = 32;
    private static DataFlycGetPushGpsSnr instance = null;
    private final int[] mGlonassSnr = new int[32];
    private final int[] mSnrValue = new int[32];

    public static synchronized DataFlycGetPushGpsSnr getInstance() {
        DataFlycGetPushGpsSnr dataFlycGetPushGpsSnr;
        synchronized (DataFlycGetPushGpsSnr.class) {
            if (instance == null) {
                instance = new DataFlycGetPushGpsSnr();
            }
            dataFlycGetPushGpsSnr = instance;
        }
        return dataFlycGetPushGpsSnr;
    }

    public int[] getSnrValues() {
        int i = 0;
        if (this._recData == null) {
            return null;
        }
        Arrays.fill(this.mSnrValue, 0);
        if (this._recData != null && this._recData.length > 0) {
            int length = this._recData.length;
            while (i < length && i < 32) {
                this.mSnrValue[i] = this._recData[i] & TransportMediator.KEYCODE_MEDIA_PAUSE;
                i++;
            }
        }
        return this.mSnrValue;
    }

    public int[] getGlonassValues() {
        if (this._recData == null) {
            return null;
        }
        Arrays.fill(this.mGlonassSnr, 0);
        if (this._recData != null && this._recData.length > 0) {
            int i = 32;
            int length = this._recData.length;
            while (i < length && i < 64) {
                this.mGlonassSnr[i - 32] = this._recData[i] & TransportMediator.KEYCODE_MEDIA_PAUSE;
                i++;
            }
        }
        return this.mGlonassSnr;
    }

    public int getSnrUsed() {
        int i = 0;
        if (!(this._recData == null || this._recData == null || this._recData.length <= 0)) {
            int length = this._recData.length;
            int i2 = 0;
            while (i2 < length && i2 < 32) {
                if ((this._recData[i2] & 128) != 0) {
                    i++;
                }
                i2++;
            }
        }
        return i;
    }

    public int getGlonassSnrUsed() {
        int i = 0;
        if (!(this._recData == null || this._recData == null || this._recData.length <= 0)) {
            int i2 = 32;
            int length = this._recData.length;
            while (i2 < length && i2 < 64) {
                if ((this._recData[i2] & 128) != 0) {
                    i++;
                }
                i2++;
            }
        }
        return i;
    }

    protected void doPack() {
    }
}
