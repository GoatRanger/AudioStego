package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataCameraGetPushFiles extends n {
    private static DataCameraGetPushFiles instance = null;
    private byte[] data = new byte[495];

    public static synchronized DataCameraGetPushFiles getInstance() {
        DataCameraGetPushFiles dataCameraGetPushFiles;
        synchronized (DataCameraGetPushFiles.class) {
            if (instance == null) {
                instance = new DataCameraGetPushFiles();
            }
            dataCameraGetPushFiles = instance;
        }
        return dataCameraGetPushFiles;
    }

    public int getIndex() {
        return ((Integer) get(0, 4, Integer.class)).intValue();
    }

    public byte[] getData() {
        if (this._recData == null) {
            return new byte[0];
        }
        System.arraycopy(this._recData, 4, this.data, 0, 495);
        return this.data;
    }

    protected void doPack() {
    }
}
