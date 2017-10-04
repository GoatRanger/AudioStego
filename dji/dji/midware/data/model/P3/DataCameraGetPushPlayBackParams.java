package dji.midware.data.model.P3;

import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.manager.P3.n;

public class DataCameraGetPushPlayBackParams extends n {
    private static DataCameraGetPushPlayBackParams instance = null;

    public enum DelFileStatus {
        NORMAL(0),
        DELETING(2),
        COMPLETED(3);
        
        private int data;

        private DelFileStatus(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static DelFileStatus find(int i) {
            DelFileStatus delFileStatus = NORMAL;
            for (DelFileStatus delFileStatus2 : values()) {
                if (delFileStatus2._equals(i)) {
                    return delFileStatus2;
                }
            }
            return delFileStatus;
        }
    }

    public enum FileType {
        JPEG(0),
        DNG(1),
        VIDEO(2),
        OTHER(100);
        
        private int data;

        private FileType(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static FileType find(int i) {
            FileType fileType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return fileType;
        }
    }

    public enum MODE {
        Single(0),
        SingleLarge(1),
        SinglePlay(2),
        SinglePause(3),
        MultipleDel(4),
        Multiple(5),
        Download(6),
        SingleOver(7),
        OTHER(100);
        
        private int data;

        private MODE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static MODE find(int i) {
            MODE mode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return mode;
        }
    }

    public static synchronized DataCameraGetPushPlayBackParams getInstance() {
        DataCameraGetPushPlayBackParams dataCameraGetPushPlayBackParams;
        synchronized (DataCameraGetPushPlayBackParams.class) {
            if (instance == null) {
                instance = new DataCameraGetPushPlayBackParams();
            }
            dataCameraGetPushPlayBackParams = instance;
        }
        return dataCameraGetPushPlayBackParams;
    }

    public MODE getMode() {
        return MODE.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    public FileType getFileType() {
        return FileType.find(((Integer) get(1, 2, Integer.class)).intValue());
    }

    public FileType[] getFileTypes(int i) {
        int intValue = ((Integer) get(1, 2, Integer.class)).intValue();
        if (i > 8) {
            i = 8;
        }
        FileType[] fileTypeArr = new FileType[i];
        for (int i2 = 0; i2 < i; i2++) {
            fileTypeArr[i2] = ((1 << i2) & intValue) == 1 ? FileType.VIDEO : FileType.JPEG;
        }
        return fileTypeArr;
    }

    public int getFileNum() {
        return ((Integer) get(3, 1, Integer.class)).intValue();
    }

    public int getTotalNum() {
        return ((Integer) get(4, 2, Integer.class)).intValue();
    }

    public int getIndex() {
        return ((Integer) get(6, 2, Integer.class)).intValue();
    }

    public int getProgress() {
        return ((Integer) get(8, 1, Integer.class)).intValue();
    }

    public int getTotalTime() {
        return ((Integer) get(9, 2, Integer.class)).intValue();
    }

    public int getTotalTimeForWM() {
        return ((Integer) get(9, 4, Integer.class)).intValue();
    }

    public int getCurrent() {
        return ((Integer) get(11, 2, Integer.class)).intValue();
    }

    public int getCurrentForWM() {
        return ((Integer) get(13, 4, Integer.class)).intValue();
    }

    public int getDeleteChioceNum() {
        return ((Integer) get(13, 2, Integer.class)).intValue();
    }

    public float getZoomSize() {
        return (((float) ((Integer) get(15, 2, Integer.class)).intValue()) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity;
    }

    public int getTotalPhotoNum() {
        return ((Integer) get(17, 2, Integer.class)).intValue();
    }

    public int getTotalVideoNum() {
        return ((Integer) get(19, 2, Integer.class)).intValue();
    }

    public int getPhotoHeight() {
        return ((Integer) get(25, 4, Integer.class)).intValue();
    }

    public int getPhotoWidth() {
        return ((Integer) get(21, 4, Integer.class)).intValue();
    }

    public int getCenterX() {
        return ((Integer) get(29, 4, Integer.class)).intValue();
    }

    public int getCenterY() {
        return ((Integer) get(33, 4, Integer.class)).intValue();
    }

    public String getFileName() {
        return "";
    }

    public boolean isCurPageSelected() {
        return ((Integer) get(37, 1, Integer.class)).intValue() == 1;
    }

    public DelFileStatus getDelFileStatus() {
        return DelFileStatus.find(((Integer) get(38, 1, Integer.class)).intValue());
    }

    public boolean isSelectFileValid() {
        return ((Integer) get(39, 1, Integer.class)).intValue() == 0;
    }

    public boolean isSingleDownloaded() {
        return ((Integer) get(40, 1, Integer.class)).intValue() == 1;
    }

    protected void doPack() {
    }
}
