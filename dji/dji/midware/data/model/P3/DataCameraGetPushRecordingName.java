package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraGetPushPlayBackParams.FileType;

public class DataCameraGetPushRecordingName extends n {
    private static DataCameraGetPushRecordingName instance = null;

    public static synchronized DataCameraGetPushRecordingName getInstance() {
        DataCameraGetPushRecordingName dataCameraGetPushRecordingName;
        synchronized (DataCameraGetPushRecordingName.class) {
            if (instance == null) {
                instance = new DataCameraGetPushRecordingName();
            }
            dataCameraGetPushRecordingName = instance;
        }
        return dataCameraGetPushRecordingName;
    }

    public FileType getType() {
        return FileType.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    public int getIndex() {
        return ((Integer) get(1, 4, Integer.class)).intValue();
    }

    public long getTime() {
        return ((Long) get(13, 4, Long.class)).longValue();
    }

    public int getFileType() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public long getSize() {
        return ((Long) get(5, 8, Long.class)).longValue();
    }

    protected void doPack() {
    }
}
