package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraSetFocusParam.ZoomMode;
import dji.midware.data.model.P3.DataCameraTauExterParamType.ExterParamType;
import dji.midware.data.model.P3.DataCameraTauFFCMode.FFCMode;
import dji.midware.data.model.P3.DataCameraTauParamAGC.AGCType;
import dji.midware.data.model.P3.DataCameraTauParamGainMode.GainMode;
import dji.midware.data.model.P3.DataCameraTauParamROI.ROIType;
import dji.midware.data.model.P3.DataCameraTauParamThermometricEnable.ThermometricType;
import dji.midware.data.model.a.a;

public class DataCameraGetPushTauParam extends a {
    private static DataCameraGetPushTauParam instance = null;

    public enum LenFocusLength {
        LFL_68(0),
        LFL_75(1),
        LFL_90(2),
        LFL_130(3),
        LFL_190(4),
        UNKNOWN(255);
        
        private int data;

        private LenFocusLength(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static LenFocusLength find(int i) {
            LenFocusLength lenFocusLength = UNKNOWN;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return lenFocusLength;
        }
    }

    public enum LenFps {
        FPS_LESS_9(0),
        FPS_30(4),
        UNKNOWN(255);
        
        private int data;

        private LenFps(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static LenFps find(int i) {
            LenFps lenFps = UNKNOWN;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return lenFps;
        }
    }

    public enum VideoResolution {
        VR_640(0),
        VR_336(1),
        UNKNOWN(255);
        
        private int data;

        private VideoResolution(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static VideoResolution find(int i) {
            VideoResolution videoResolution = UNKNOWN;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return videoResolution;
        }
    }

    public static synchronized DataCameraGetPushTauParam getInstance() {
        DataCameraGetPushTauParam dataCameraGetPushTauParam;
        synchronized (DataCameraGetPushTauParam.class) {
            if (instance == null) {
                instance = new DataCameraGetPushTauParam();
            }
            dataCameraGetPushTauParam = instance;
        }
        return dataCameraGetPushTauParam;
    }

    protected void setPushRecData(byte[] bArr) {
        super.setPushRecData(bArr);
    }

    public int getImageFormat() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public int getVideoFormat() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    public int getVideoFps() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public ZoomMode getZoomMode() {
        return ZoomMode.find(((Integer) get(3, 1, Integer.class)).intValue());
    }

    public int getZoomScale() {
        ZoomMode zoomMode = getZoomMode();
        if (zoomMode == ZoomMode.STEP || zoomMode == ZoomMode.CONTINUOUS) {
            return ((Integer) get(4, 2, Integer.class)).intValue() & 255;
        }
        return ((Integer) get(4, 2, Integer.class)).intValue();
    }

    public int getDigitalFilter() {
        return ((Integer) get(6, 1, Integer.class)).intValue();
    }

    public AGCType getAGC() {
        return AGCType.find(((Integer) get(7, 1, Integer.class)).intValue());
    }

    public int getDDE() {
        return ((Short) get(8, 2, Short.class)).shortValue();
    }

    public int getACE() {
        return ((Short) get(10, 2, Short.class)).shortValue();
    }

    public int getSSO() {
        return ((Integer) get(12, 2, Integer.class)).intValue();
    }

    public int getContrast() {
        return ((Integer) get(14, 1, Integer.class)).intValue();
    }

    public int getBrightness() {
        return ((Integer) get(15, 2, Integer.class)).intValue();
    }

    public float getThermometricXAxis() {
        return ((Float) get(17, 4, Float.class)).floatValue();
    }

    public float getThermometricYAxis() {
        return ((Float) get(21, 4, Float.class)).floatValue();
    }

    public float getThermometricTemp() {
        return ((Float) get(25, 4, Float.class)).floatValue();
    }

    public int getShotCountDown() {
        return ((Integer) get(29, 1, Integer.class)).intValue();
    }

    public ROIType getROIType() {
        return ROIType.find(((Integer) get(30, 1, Integer.class)).intValue());
    }

    public boolean isIsothermEnable() {
        return (((Integer) get(31, 1, Integer.class)).intValue() & 1) != 0;
    }

    public int getIsothermUnit() {
        return ((Integer) get(32, 1, Integer.class)).intValue();
    }

    public short getIsothermLower() {
        return ((Short) get(33, 2, Short.class)).shortValue();
    }

    public short getIsothermMiddle() {
        return ((Short) get(35, 2, Short.class)).shortValue();
    }

    public short getIsothermUpper() {
        return ((Short) get(37, 2, Short.class)).shortValue();
    }

    public ThermometricType getThermometricType() {
        return ThermometricType.find(((Integer) get(39, 1, Integer.class)).intValue());
    }

    public boolean isThermometricEnable() {
        return (((Integer) get(39, 1, Integer.class)).intValue() & 1) != 0;
    }

    public int getObjectControl() {
        return ((Integer) get(40, 1, Integer.class)).intValue();
    }

    public GainMode getGainMode() {
        return GainMode.find(((Integer) get(41, 1, Integer.class)).intValue());
    }

    public VideoResolution getVideoResolution() {
        return VideoResolution.find(((Integer) get(42, 1, Integer.class)).intValue());
    }

    public LenFocusLength getLenFocusLength() {
        return LenFocusLength.find(((Integer) get(43, 1, Integer.class)).intValue());
    }

    public LenFps getLenFps() {
        return LenFps.find(((Integer) get(44, 1, Integer.class)).intValue());
    }

    public int getPhotoInterval() {
        return ((Integer) get(45, 1, Integer.class)).intValue();
    }

    public FFCMode getFFCMode() {
        return FFCMode.find(((Integer) get(47, 1, Integer.class)).intValue());
    }

    public boolean supportSpotThermometric() {
        return (((Integer) get(48, 1, Integer.class)).intValue() & 1) != 0;
    }

    public boolean isThermometricValid() {
        return (((Integer) get(48, 1, Integer.class)).intValue() & 128) != 0;
    }

    public ExterParamType getExterParamType() {
        return ExterParamType.find(((Integer) get(49, 1, Integer.class)).intValue());
    }

    public short getTargetEmissivity() {
        return ((Short) get(50, 2, Short.class)).shortValue();
    }

    public short getAtmosphereTransmission() {
        return ((Short) get(52, 2, Short.class)).shortValue();
    }

    public short getAtmosphereTemperature() {
        return ((Short) get(54, 2, Short.class)).shortValue();
    }

    public short getBackgroundTemperature() {
        return ((Short) get(56, 2, Short.class)).shortValue();
    }

    public short getWindowTransmission() {
        return ((Short) get(58, 2, Short.class)).shortValue();
    }

    public short getWindowTemperature() {
        return ((Short) get(60, 2, Short.class)).shortValue();
    }

    public short getWindowReflection() {
        return ((Short) get(62, 2, Short.class)).shortValue();
    }

    public short getWindowReflectedTemperature() {
        return ((Short) get(64, 2, Short.class)).shortValue();
    }

    public int getAreaThermometricLeft() {
        return ((Integer) get(66, 2, Integer.class)).intValue();
    }

    public int getAreaThermometricTop() {
        return ((Integer) get(68, 2, Integer.class)).intValue();
    }

    public int getAreaThermometricRight() {
        return ((Integer) get(70, 2, Integer.class)).intValue();
    }

    public int getAreaThermometricBottom() {
        return ((Integer) get(72, 2, Integer.class)).intValue();
    }

    public float getAreaThermometricAverage() {
        return ((Float) get(74, 4, Float.class)).floatValue();
    }

    public float getAreaThermometricMin() {
        return ((Float) get(78, 4, Float.class)).floatValue();
    }

    public float getAreaThermometricMax() {
        return ((Float) get(82, 4, Float.class)).floatValue();
    }

    public int getAreaThermometricMinX() {
        return ((Integer) get(86, 2, Integer.class)).intValue();
    }

    public int getAreaThermometricMinY() {
        return ((Integer) get(88, 2, Integer.class)).intValue();
    }

    public int getAreaThermometricMaxX() {
        return ((Integer) get(90, 2, Integer.class)).intValue();
    }

    public int getAreaThermometricMaxY() {
        return ((Integer) get(92, 2, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
