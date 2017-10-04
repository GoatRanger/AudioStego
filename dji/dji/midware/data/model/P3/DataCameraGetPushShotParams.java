package dji.midware.data.model.P3;

import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetImageSize.SizeType;
import dji.midware.data.model.P3.DataCameraGetIso.TYPE;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetVideoEncode.VideoEncodeType;
import dji.midware.data.model.a.a;

public class DataCameraGetPushShotParams extends a {
    private static DataCameraGetPushShotParams instance = null;

    public static synchronized DataCameraGetPushShotParams getInstance() {
        DataCameraGetPushShotParams dataCameraGetPushShotParams;
        synchronized (DataCameraGetPushShotParams.class) {
            if (instance == null) {
                instance = new DataCameraGetPushShotParams();
            }
            dataCameraGetPushShotParams = instance;
        }
        return dataCameraGetPushShotParams;
    }

    public int getApertureSize() {
        return ((Integer) get(0, 2, Integer.class)).intValue();
    }

    public int getRealApertureSize() {
        return ((Integer) get(38, 2, Integer.class)).intValue();
    }

    public int getUserRawShutter() {
        return ((Integer) get(2, 3, Integer.class)).intValue();
    }

    public int getShutter() {
        return ((Integer) get(2, 2, Integer.class)).intValue() & -32769;
    }

    public boolean isReciprocal() {
        return (((Integer) get(2, 2, Integer.class)).intValue() >> 15) == 1;
    }

    public int getShutterSpeedDecimal() {
        return ((Integer) get(4, 1, Integer.class)).intValue();
    }

    public String getShutterString() {
        int shutter = getShutter();
        int shutterSpeedDecimal = getShutterSpeedDecimal();
        String str = shutterSpeedDecimal == 0 ? shutter + "" : shutter + "." + shutterSpeedDecimal;
        if (isReciprocal()) {
            return "1/" + str;
        }
        return str;
    }

    public int getRelShutter() {
        return ((Integer) get(40, 2, Integer.class)).intValue() & -32769;
    }

    public int getRealRawShutter() {
        return ((Integer) get(40, 3, Integer.class)).intValue();
    }

    public boolean isRelReciprocal() {
        return (((Integer) get(40, 2, Integer.class)).intValue() >> 15) == 1;
    }

    public int getRelShutterSpeedDecimal() {
        return ((Integer) get(42, 1, Integer.class)).intValue();
    }

    public String getRelShutterString() {
        int relShutter = getRelShutter();
        int relShutterSpeedDecimal = getRelShutterSpeedDecimal();
        String str = relShutterSpeedDecimal == 0 ? relShutter + "" : relShutter + "." + relShutterSpeedDecimal;
        if (isRelReciprocal()) {
            return "1/" + str;
        }
        return str;
    }

    public int getISO() {
        return TYPE.find(((Integer) get(5, 1, Integer.class)).intValue()).value();
    }

    public int getExposureCompensation() {
        return ((Integer) get(6, 1, Integer.class)).intValue();
    }

    public int getRelISO() {
        return ((Integer) get(43, 4, Integer.class)).intValue();
    }

    public int getRelExposureCompensation() {
        return ((Integer) get(47, 1, Integer.class)).intValue();
    }

    public int getTimeCountdown() {
        return ((Integer) get(48, 1, Integer.class)).intValue();
    }

    public int getCtrObjectForOne() {
        return ((Integer) get(7, 1, Integer.class)).intValue();
    }

    public int getCtrObjectForTwo() {
        return ((Integer) get(8, 1, Integer.class)).intValue();
    }

    public SizeType getImageSize() {
        return SizeType.find(((Integer) get(9, 1, Integer.class)).intValue());
    }

    public RatioType getImageRatio() {
        return RatioType.find(((Integer) get(10, 1, Integer.class)).intValue());
    }

    public int getImageQuality() {
        return ((Integer) get(11, 1, Integer.class)).intValue();
    }

    public int getImageFormat() {
        return ((Integer) get(12, 1, Integer.class)).intValue();
    }

    public int getVideoFormat() {
        return ((Integer) get(13, 1, Integer.class)).intValue();
    }

    public int getVideoFps() {
        return ((Integer) get(14, 1, Integer.class)).intValue();
    }

    public int getVideoFov() {
        return ((Integer) get(15, 1, Integer.class)).intValue();
    }

    public int getVideoRecordMode() {
        return ((Integer) get(73, 1, Integer.class)).intValue();
    }

    public int getVideoContastEnhance() {
        return ((Integer) get(72, 1, Integer.class)).intValue();
    }

    public int getTimelapseSaveType() {
        return ((Integer) get(74, 1, Integer.class)).intValue();
    }

    public int getVideoRecordIntervalTime() {
        return ((Integer) get(75, 2, Integer.class)).intValue();
    }

    public int getTimelapseDuration() {
        return ((Integer) get(77, 4, Integer.class)).intValue();
    }

    public int getTimelapseTimeCountDown() {
        return ((Integer) get(81, 2, Integer.class)).intValue();
    }

    public int getTimelapseRecordedFrame() {
        return ((Integer) get(83, 4, Integer.class)).intValue();
    }

    public int getOpticsScale() {
        return ((Integer) get(87, 2, Integer.class)).intValue();
    }

    public int getDigitalZoomScale() {
        return ((Integer) get(89, 2, Integer.class)).intValue();
    }

    public int getVideoSecondOpen() {
        return ((Integer) get(16, 1, Integer.class)).intValue();
    }

    public int getVideoSecondRatio() {
        return ((Integer) get(17, 1, Integer.class)).intValue();
    }

    public int getVideoQuality() {
        return ((Integer) get(18, 1, Integer.class)).intValue();
    }

    public int getVideoStoreFormat() {
        return ((Integer) get(19, 1, Integer.class)).intValue();
    }

    public ExposureMode getExposureMode() {
        return ExposureMode.find(((Integer) get(20, 1, Integer.class)).intValue());
    }

    public int getSceneMode() {
        return ((Integer) get(21, 1, Integer.class)).intValue();
    }

    public int getMetering() {
        return ((Integer) get(22, 1, Integer.class)).intValue();
    }

    public int getWhiteBalance() {
        return ((Integer) get(23, 1, Integer.class)).intValue();
    }

    public int getColorTemp() {
        return ((Integer) get(24, 1, Integer.class)).intValue();
    }

    public boolean isMCTFEnable() {
        return ((Integer) get(25, 1, Integer.class)).intValue() != 0;
    }

    public int getMCTFStrength() {
        return ((Integer) get(26, 1, Integer.class)).intValue();
    }

    public int getSharpe() {
        return ((Integer) get(27, 1, Integer.class)).intValue();
    }

    public int getContrast() {
        return ((Integer) get(28, 1, Integer.class)).intValue();
    }

    public int getSaturation() {
        return ((Integer) get(29, 1, Integer.class)).intValue();
    }

    public int getTonal() {
        return ((Integer) get(30, 1, Integer.class)).intValue();
    }

    public int getDigitalFilter() {
        return ((Integer) get(31, 1, Integer.class)).intValue();
    }

    public int getAntiFlicker() {
        return ((Integer) get(32, 1, Integer.class)).intValue();
    }

    public int getContinuous() {
        return ((Integer) get(33, 1, Integer.class)).intValue();
    }

    public int getTimeParamsType() {
        return ((Integer) get(34, 1, Integer.class)).intValue();
    }

    public int getTimeParamsNum() {
        return ((Integer) get(35, 1, Integer.class)).intValue();
    }

    public int getTimeParamsPeriod() {
        return ((Integer) get(36, 2, Integer.class)).intValue();
    }

    public int getCapMinShutter() {
        return ((Integer) get(49, 2, Integer.class)).intValue() & -32769;
    }

    public boolean isCapMinShutterReciprocal() {
        return (((Integer) get(49, 2, Integer.class)).intValue() >> 15) == 1;
    }

    public int getCapMinShutterDecimal() {
        return ((Integer) get(51, 1, Integer.class)).intValue();
    }

    public String getCapMinShutterStr() {
        int capMinShutter = getCapMinShutter();
        int capMinShutterDecimal = getCapMinShutterDecimal();
        String str = capMinShutterDecimal == 0 ? capMinShutter + "" : capMinShutter + "." + capMinShutterDecimal;
        if (isCapMinShutterReciprocal()) {
            return "1/" + str;
        }
        return str;
    }

    public int getCapMaxShutter() {
        return ((Integer) get(52, 2, Integer.class)).intValue() & -32769;
    }

    public boolean isCapMaxShutterReciprocal() {
        return (((Integer) get(52, 2, Integer.class)).intValue() >> 15) == 1;
    }

    public int getCapMaxShutterDecimal() {
        return ((Integer) get(54, 1, Integer.class)).intValue();
    }

    public String getCapMaxShutterStr() {
        int capMaxShutter = getCapMaxShutter();
        int capMaxShutterDecimal = getCapMaxShutterDecimal();
        String str = capMaxShutterDecimal == 0 ? capMaxShutter + "" : capMaxShutter + "." + capMaxShutterDecimal;
        if (isCapMaxShutterReciprocal()) {
            return "1/" + str;
        }
        return str;
    }

    public int getRawCapMinShutter() {
        return ((Integer) get(49, 3, Integer.class)).intValue();
    }

    public int getRawCapMaxShutter() {
        return ((Integer) get(52, 3, Integer.class)).intValue();
    }

    public int getVideoStandard() {
        return ((Integer) get(55, 1, Integer.class)).intValue();
    }

    public boolean isAELock() {
        return ((Integer) get(56, 1, Integer.class)).intValue() == 1;
    }

    public int getAEBNumber() {
        return ((Integer) get(60, 1, Integer.class)).intValue();
    }

    public int getConstrastEhance() {
        return ((Integer) get(72, 1, Integer.class)).intValue();
    }

    public DataCameraSetPhoto.TYPE getPhotoType() {
        return DataCameraSetPhoto.TYPE.find(((Integer) get(57, 1, Integer.class)).intValue());
    }

    public int getSpotAreaBottomRightPos() {
        return ((Integer) get(58, 1, Integer.class)).intValue();
    }

    public int getCapMinAperture() {
        return ((Integer) get(62, 2, Integer.class)).intValue();
    }

    public int getCapMaxAperture() {
        return ((Integer) get(64, 2, Integer.class)).intValue();
    }

    public boolean autoTurnOffForeLed() {
        return (((Integer) get(66, 1, Integer.class)).intValue() & 1) != 0;
    }

    public int getExposureStatus() {
        return ((Integer) get(67, 1, Integer.class)).intValue();
    }

    public boolean isLockedGimbalWhenShot() {
        return ((Integer) get(68, 1, Integer.class)).intValue() == 1;
    }

    public VideoEncodeType getPrimaryVideoEncodeType() {
        return VideoEncodeType.find(((Integer) get(69, 1, Integer.class)).intValue() & 15);
    }

    public VideoEncodeType getSecondaryVideoEncodeType() {
        return VideoEncodeType.find((((Integer) get(69, 1, Integer.class)).intValue() & 240) >> 4);
    }

    public boolean autoAEUnlock() {
        return ((Integer) get(70, 1, Integer.class)).intValue() == 0;
    }

    public PanoMode getPanoMode() {
        return PanoMode.find(((Integer) get(61, 1, Integer.class)).intValue());
    }

    protected void doPack() {
    }
}
