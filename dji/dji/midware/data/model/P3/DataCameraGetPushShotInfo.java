package dji.midware.data.model.P3;

import dji.midware.data.model.a.a;

public class DataCameraGetPushShotInfo extends a {
    private static DataCameraGetPushShotInfo instance = null;

    public enum FuselageFocusMode {
        Manual(0),
        OneAuto(1),
        ContinuousAuto(2),
        ManualFine(3),
        OTHER(6);
        
        private int data;

        private FuselageFocusMode(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static FuselageFocusMode find(int i) {
            FuselageFocusMode fuselageFocusMode = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return fuselageFocusMode;
        }
    }

    public enum ZoomFocusType {
        ManualZoomFocus(0),
        AutoZoomFocus(1),
        OTHER(6);
        
        private int data;

        private ZoomFocusType(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static ZoomFocusType find(int i) {
            ZoomFocusType zoomFocusType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return zoomFocusType;
        }
    }

    public static synchronized DataCameraGetPushShotInfo getInstance() {
        DataCameraGetPushShotInfo dataCameraGetPushShotInfo;
        synchronized (DataCameraGetPushShotInfo.class) {
            if (instance == null) {
                instance = new DataCameraGetPushShotInfo();
            }
            dataCameraGetPushShotInfo = instance;
        }
        return dataCameraGetPushShotInfo;
    }

    protected void setPushRecData(byte[] bArr) {
        super.setPushRecData(bArr);
    }

    public boolean isShotConnected() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 128) != 0;
    }

    public ShotType getShotType() {
        return ShotType.find((((Integer) get(0, 1, Integer.class)).intValue() >>> 5) & 1);
    }

    public ShotFDType getShotFDType() {
        return ShotFDType.find((((Integer) get(0, 1, Integer.class)).intValue() >>> 6) & 1);
    }

    public ZoomFocusType getZoomFocusType() {
        ZoomFocusType zoomFocusType = ZoomFocusType.ManualZoomFocus;
        if (getShotFDType() == ShotFDType.ZoomShotFD) {
            return ZoomFocusType.find((((Integer) get(0, 1, Integer.class)).intValue() >>> 4) & 1);
        }
        return zoomFocusType;
    }

    public ShotFocusMode getShotFocusMode() {
        return ShotFocusMode.find((((Integer) get(0, 1, Integer.class)).intValue() >>> 2) & 3);
    }

    public FuselageFocusMode getFuselageFocusMode() {
        return FuselageFocusMode.find(((Integer) get(0, 1, Integer.class)).intValue() & 3);
    }

    public int getShotFocusMaxStroke() {
        return ((Integer) get(1, 2, Integer.class)).intValue();
    }

    public int getShotFocusCurStroke() {
        return ((Integer) get(3, 2, Integer.class)).intValue();
    }

    public float getObjDistance() {
        return ((Float) get(5, 4, Float.class)).floatValue();
    }

    public int getMinAperture() {
        return ((Integer) get(9, 2, Integer.class)).intValue();
    }

    public int getMaxAperture() {
        return ((Integer) get(11, 2, Integer.class)).intValue();
    }

    public float getSpotAFAxisX() {
        return ((Float) get(13, 4, Float.class)).floatValue();
    }

    public float getSpotAFAxisY() {
        return ((Float) get(17, 4, Float.class)).floatValue();
    }

    public int getFocusStatus() {
        return ((Integer) get(21, 1, Integer.class)).intValue() & 3;
    }

    public float getMFFocusProbability() {
        return (((float) ((Integer) get(22, 1, Integer.class)).intValue()) * 1.0f) / 255.0f;
    }

    public int getMinFocusDistance() {
        return ((Integer) get(23, 2, Integer.class)).intValue();
    }

    public int getMaxFocusDistance() {
        return ((Integer) get(25, 2, Integer.class)).intValue();
    }

    public int getCurFocusDistance() {
        return ((Integer) get(27, 2, Integer.class)).intValue();
    }

    public int getMinFocusDistanceStep() {
        return ((Integer) get(29, 2, Integer.class)).intValue();
    }

    public boolean isDigitalFocusEnable() {
        return (((Integer) get(31, 1, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isDigitalFocusMEnable() {
        return (((Integer) get(31, 1, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isDigitalFocusAEnable() {
        return ((((Integer) get(31, 1, Integer.class)).intValue() >> 1) & 1) != 0;
    }

    public int getXAxisFocusWindowNum() {
        return ((Integer) get(32, 1, Integer.class)).intValue();
    }

    public int getYAxisFocusWindowNum() {
        return ((Integer) get(33, 1, Integer.class)).intValue();
    }

    public int getMFFocusStatus() {
        return ((Integer) get(34, 1, Integer.class)).intValue();
    }

    public int getFocusWindowStartX() {
        return ((Integer) get(35, 1, Integer.class)).intValue();
    }

    public int getFocusWindowRealNumX() {
        return ((Integer) get(36, 1, Integer.class)).intValue();
    }

    public int getFocusWindowStartY() {
        return ((Integer) get(37, 1, Integer.class)).intValue();
    }

    public int getFocusWindowRealNumY() {
        return ((Integer) get(38, 1, Integer.class)).intValue();
    }

    public int getSupportType() {
        return ((Integer) get(39, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
