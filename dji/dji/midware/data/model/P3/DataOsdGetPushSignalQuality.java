package dji.midware.data.model.P3;

import android.support.v4.media.TransportMediator;
import dji.midware.data.a.a.a;
import dji.midware.data.manager.P3.n;
import dji.midware.util.c;

public class DataOsdGetPushSignalQuality extends n {
    private static DataOsdGetPushSignalQuality instance = null;
    private int Aerial1DownSignalQuality;
    private int Aerial1UpSignalQuality;
    private int Aerial2DownSignalQuality;
    private int Aerial2UpSignalQuality;
    private int downSignalQuality;
    private int upSignalQuality;

    public static synchronized DataOsdGetPushSignalQuality getInstance() {
        DataOsdGetPushSignalQuality dataOsdGetPushSignalQuality;
        synchronized (DataOsdGetPushSignalQuality.class) {
            if (instance == null) {
                instance = new DataOsdGetPushSignalQuality();
                instance.isNeedPushLosed = true;
                instance.isRemoteModel = false;
            }
            dataOsdGetPushSignalQuality = instance;
        }
        return dataOsdGetPushSignalQuality;
    }

    protected void setPushRecPack(a aVar) {
        super.setPushRecPack(aVar);
    }

    public boolean isGetRcQuality() {
        return (this._recData == null || (this._recData[0] & 128) == 0) ? false : true;
    }

    public int getUpSignalQuality() {
        if (!(this._recData == null || (this._recData[0] & 128) == 0)) {
            this.upSignalQuality = ((Integer) get(0, 1, Integer.class)).intValue() & TransportMediator.KEYCODE_MEDIA_PAUSE;
        }
        return this.upSignalQuality;
    }

    public int getDownSignalQuality() {
        if (this._recData != null && (this._recData[0] & 128) == 0) {
            this.downSignalQuality = ((Integer) get(0, 1, Integer.class)).intValue() & TransportMediator.KEYCODE_MEDIA_PAUSE;
        }
        return this.downSignalQuality;
    }

    public int getAerial1UpSignalQuality() {
        if (!(this._recData == null || (this._recData[0] & 128) == 0)) {
            this.Aerial1UpSignalQuality = c.b(this._recData[1]);
        }
        return this.Aerial1UpSignalQuality;
    }

    public int getAerial1DownSignalQuality() {
        if (this._recData != null && (this._recData[0] & 128) == 0) {
            this.Aerial1DownSignalQuality = c.b(this._recData[1]);
        }
        return this.Aerial1DownSignalQuality;
    }

    public int getAerial2UpSignalQuality() {
        if (!(this._recData == null || (this._recData[0] & 128) == 0)) {
            this.Aerial2UpSignalQuality = c.b(this._recData[2]);
        }
        return this.Aerial2UpSignalQuality;
    }

    public int getAerial2DownSignalQuality() {
        if (this._recData != null && (this._recData[0] & 128) == 0) {
            this.Aerial2DownSignalQuality = c.b(this._recData[2]);
        }
        return this.Aerial2DownSignalQuality;
    }

    public byte[] getResource() {
        return this._recData;
    }

    protected void doPack() {
    }
}
