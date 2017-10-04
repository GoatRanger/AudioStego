package dji.common.camera;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.gs.c.e;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.util.a.b;
import dji.pilot.visual.a.d;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.util.Set;
import lecho.lib.hellocharts.d.c;

public class DJICameraSettingsDef {

    public enum CameraAntiFlicker {
        Auto(0),
        AntiFlicker_60Hz(1),
        AntiFlicker_50Hz(2),
        Unknown(255);
        
        private int value;

        private CameraAntiFlicker(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraAntiFlicker find(int i) {
            CameraAntiFlicker cameraAntiFlicker = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraAntiFlicker;
        }
    }

    public enum CameraAperture {
        F_1p6(160),
        F_1p7(170),
        F_1p8(180),
        F_2p0(200),
        F_2p2(FTPCodes.SERVICE_READY_FOR_NEW_USER),
        F_2p4(240),
        F_2p5(250),
        F_2p8(280),
        F_3p2(320),
        F_3p4(340),
        F_3p5(FTPCodes.PENDING_FURTHER_INFORMATION),
        F_4p0(400),
        F_4p5(FTPCodes.FILE_ACTION_NOT_TAKEN),
        F_4p8(480),
        F_5p0(500),
        F_5p6(560),
        F_6p3(630),
        F_6p8(680),
        F_7p1(710),
        F_8p0(e.g),
        F_9p0(900),
        F_9p6(960),
        F_10p0(1000),
        F_11p0(1100),
        F_13p0(1300),
        F_14p0(1400),
        F_16p0(1600),
        F_18p0(1800),
        F_20p0(2000),
        F_22p0(2200),
        Unknown(255);
        
        private int value;

        private CameraAperture(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraAperture find(int i) {
            CameraAperture cameraAperture = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraAperture;
        }
    }

    public static class CameraApertureRange extends BaseRange<CameraAperture> {
        public CameraApertureRange(int i) {
            super(i);
        }

        public CameraApertureRange(Set<CameraAperture> set) {
            super((Set) set);
        }

        public CameraApertureRange(CameraAperture cameraAperture, CameraAperture cameraAperture2) {
            super(cameraAperture, cameraAperture2);
        }
    }

    public enum CameraContrast {
        Standard(0),
        Hard(1),
        Soft(2),
        Unknown(255);
        
        private int value;

        private CameraContrast(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraContrast find(int i) {
            CameraContrast cameraContrast = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraContrast;
        }
    }

    public enum CameraCustomSettings {
        Default(0),
        Profile1(1),
        Profile2(2),
        Profile3(3),
        Profile4(4),
        Unknown(255);
        
        private int value;

        private CameraCustomSettings(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraCustomSettings find(int i) {
            CameraCustomSettings cameraCustomSettings = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraCustomSettings;
        }
    }

    public enum CameraDeletePhotoOperation {
        SingleDeleteConfirmed(0),
        MultipleDeleteConfirmed(1),
        EnterMultipleDeleteMode(2),
        ExitMultipleDeleteMode(3),
        MultipleDeleteSelected(4),
        Unknown(255);
        
        private int value;

        private CameraDeletePhotoOperation(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraDeletePhotoOperation find(int i) {
            CameraDeletePhotoOperation cameraDeletePhotoOperation = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraDeletePhotoOperation;
        }
    }

    public enum CameraDigitalFilter {
        None(0),
        Art(1),
        Reminiscence(2),
        Inverse(3),
        BlackAndWhite(4),
        Bright(5),
        Movie(6),
        Portrait(7),
        M31(14),
        Delta(15),
        kDX(16),
        DK79(17),
        Prismo(18),
        Jugo(19),
        Vision4(20),
        Vision6(21),
        VisionX(22),
        Neutral(23),
        Unknown(255);
        
        private int value;

        private CameraDigitalFilter(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraDigitalFilter find(int i) {
            CameraDigitalFilter cameraDigitalFilter = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraDigitalFilter;
        }
    }

    public enum CameraExposureCompensation {
        N_5_0(1),
        N_4_7(2),
        N_4_3(3),
        N_4_0(4),
        N_3_7(5),
        N_3_3(6),
        N_3_0(7),
        N_2_7(8),
        N_2_3(9),
        N_2_0(10),
        N_1_7(11),
        N_1_3(12),
        N_1_0(13),
        N_0_7(14),
        N_0_3(15),
        N_0_0(16),
        P_0_3(17),
        P_0_7(18),
        P_1_0(19),
        P_1_3(20),
        P_1_7(21),
        P_2_0(22),
        P_2_3(23),
        P_2_7(24),
        P_3_0(25),
        P_3_3(26),
        P_3_7(27),
        P_4_0(28),
        P_4_3(29),
        P_4_7(30),
        P_5_0(31),
        Unknown(255);
        
        private int value;

        private CameraExposureCompensation(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraExposureCompensation find(int i) {
            CameraExposureCompensation cameraExposureCompensation = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraExposureCompensation;
        }
    }

    public static class CameraExposureCompensationRange extends BaseRange<CameraExposureCompensation> {
        public CameraExposureCompensationRange(int i) {
            super(i);
        }

        public CameraExposureCompensationRange(Set<CameraExposureCompensation> set) {
            super((Set) set);
        }

        public CameraExposureCompensationRange(CameraExposureCompensation cameraExposureCompensation, CameraExposureCompensation cameraExposureCompensation2) {
            super(cameraExposureCompensation, cameraExposureCompensation2);
        }
    }

    public enum CameraExposureMode {
        Program(1),
        ShutterPriority(2),
        AperturePriority(3),
        Manual(4),
        Cine(7),
        Unknown(255);
        
        private int value;

        private CameraExposureMode(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraExposureMode find(int i) {
            CameraExposureMode cameraExposureMode = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraExposureMode;
        }
    }

    public static class CameraExposureModeRange extends BaseRange<CameraExposureMode> {
        public CameraExposureModeRange(int i) {
            super(i);
        }

        public CameraExposureModeRange(Set<CameraExposureMode> set) {
            super((Set) set);
        }

        public CameraExposureModeRange(CameraExposureMode cameraExposureMode, CameraExposureMode cameraExposureMode2) {
            super(cameraExposureMode, cameraExposureMode2);
        }
    }

    public enum CameraFileIndexMode {
        Reset(0),
        Sequence(1),
        Unknown(255);
        
        private int value;

        private CameraFileIndexMode(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraFileIndexMode find(int i) {
            CameraFileIndexMode cameraFileIndexMode = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraFileIndexMode;
        }
    }

    public enum CameraISO {
        Auto(0),
        ISO_100(3),
        ISO_200(4),
        ISO_400(5),
        ISO_800(6),
        ISO_1600(7),
        ISO_3200(8),
        ISO_6400(9),
        ISO_12800(10),
        ISO_25600(11),
        Unknown(255);
        
        private int value;

        private CameraISO(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraISO find(int i) {
            CameraISO cameraISO = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraISO;
        }
    }

    public static class CameraISORange extends BaseRange<CameraISO> {
        public CameraISORange(int i) {
            super(i);
        }

        public CameraISORange(Set<CameraISO> set) {
            super((Set) set);
        }

        public CameraISORange(CameraISO cameraISO, CameraISO cameraISO2) {
            super(cameraISO, cameraISO2);
        }
    }

    public enum CameraLensFocusMode {
        Manual(0),
        Auto(1),
        Unknown(255);
        
        private int value;

        private CameraLensFocusMode(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraLensFocusMode find(int i) {
            CameraLensFocusMode cameraLensFocusMode = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraLensFocusMode;
        }
    }

    public enum CameraLensFocusStatus {
        Idle(0),
        Focusing(1),
        Success(2),
        Failure(3),
        Unknown(255);
        
        private int value;

        private CameraLensFocusStatus(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraLensFocusStatus find(int i) {
            CameraLensFocusStatus cameraLensFocusStatus = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraLensFocusStatus;
        }
    }

    public enum CameraLensType {
        AF(0),
        MF(1),
        Unknown(255);
        
        private int value;

        private CameraLensType(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraLensType find(int i) {
            CameraLensType cameraLensType = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraLensType;
        }
    }

    public enum CameraMediaFileDeleteStatus {
        Failed(1),
        Deleting(2),
        Successed(3),
        Unknown(255);
        
        private int value;

        private CameraMediaFileDeleteStatus(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraMediaFileDeleteStatus find(int i) {
            CameraMediaFileDeleteStatus cameraMediaFileDeleteStatus = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraMediaFileDeleteStatus;
        }
    }

    public enum CameraMediaFileType {
        JPEG(0),
        DNG(1),
        VIDEO(2),
        Unknown(255);
        
        private int value;

        private CameraMediaFileType(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraMediaFileType find(int i) {
            CameraMediaFileType cameraMediaFileType = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraMediaFileType;
        }
    }

    public enum CameraMeteringMode {
        Center(0),
        Average(1),
        Spot(2),
        Unknown(255);
        
        private int value;

        private CameraMeteringMode(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraMeteringMode find(int i) {
            CameraMeteringMode cameraMeteringMode = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraMeteringMode;
        }
    }

    public enum CameraMode {
        ShootPhoto(0),
        RecordVideo(1),
        Playback(2),
        MediaDownload(4),
        Unknown(255);
        
        private int value;

        private CameraMode(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraMode find(int i) {
            CameraMode cameraMode = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraMode;
        }
    }

    public static class CameraModeRange extends BaseRange<CameraMode> {
        public CameraModeRange(int i) {
            super(i);
        }

        public CameraModeRange(Set<CameraMode> set) {
            super((Set) set);
        }

        public CameraModeRange(CameraMode cameraMode, CameraMode cameraMode2) {
            super(cameraMode, cameraMode2);
        }
    }

    public static class CameraOpticalZoomSpec {
        public int focalLengthStep;
        public int maxFocalLength;
        public int minFocalLength;
    }

    public enum CameraOrientation {
        Landscape,
        Portrait,
        Unknown
    }

    public static class CameraPhotoAEBParam {
        public int captureCount;
        public int exposureOffset;
    }

    public enum CameraPhotoAspectRatio {
        AspectRatio_4_3(0),
        AspectRatio_16_9(1),
        Unknown(255);
        
        private int value;

        private CameraPhotoAspectRatio(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraPhotoAspectRatio find(int i) {
            CameraPhotoAspectRatio cameraPhotoAspectRatio = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraPhotoAspectRatio;
        }
    }

    public enum CameraPhotoBurstCount {
        BurstCount_3(3),
        BurstCount_5(5),
        BurstCount_7(7),
        Unknown(255);
        
        private int value;

        private CameraPhotoBurstCount(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraPhotoBurstCount find(int i) {
            CameraPhotoBurstCount cameraPhotoBurstCount = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraPhotoBurstCount;
        }
    }

    public enum CameraPhotoFileFormat {
        RAW(0),
        JPEG(1),
        RAWAndJPEG(2),
        TIFF14Bit(4),
        RadiometricJPEG(5),
        TIFF14BitLinearLowTempResolution(6),
        TIFF14BitLinearHighTempResolution(7),
        Unknown(255);
        
        private int value;

        private CameraPhotoFileFormat(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraPhotoFileFormat find(int i) {
            CameraPhotoFileFormat cameraPhotoFileFormat = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraPhotoFileFormat;
        }
    }

    public static class CameraPhotoIntervalParam {
        public int captureCount;
        public int timeIntervalInSeconds;

        public String toString() {
            return this.captureCount + "," + this.timeIntervalInSeconds;
        }
    }

    public enum CameraPhotoQuality {
        Normal(0),
        Fine(1),
        Excellent(2),
        Unknown(255);
        
        private int value;

        private CameraPhotoQuality(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraPhotoQuality find(int i) {
            CameraPhotoQuality cameraPhotoQuality = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraPhotoQuality;
        }
    }

    public enum CameraPhotoTimeLapseFileFormat {
        Video(0),
        JPEGAndVideo(1),
        Unknown(255);
        
        private int value;

        private CameraPhotoTimeLapseFileFormat(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraPhotoTimeLapseFileFormat find(int i) {
            CameraPhotoTimeLapseFileFormat cameraPhotoTimeLapseFileFormat = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraPhotoTimeLapseFileFormat;
        }
    }

    public enum CameraPlaybackMode {
        SinglePhotoPlayback(0),
        SinglePhotoZoomMode(1),
        SingleVideoPlaybackStart(2),
        SingleVideoPlaybackStop(3),
        MultipleMediaFilesDelete(4),
        MultipleMediaFilesDisplay(5),
        MediaFilesDownload(6),
        Unknown(255);
        
        private int value;

        private CameraPlaybackMode(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraPlaybackMode find(int i) {
            CameraPlaybackMode cameraPlaybackMode = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraPlaybackMode;
        }
    }

    public enum CameraSharpness {
        Standard(0),
        Hard(1),
        Soft(2),
        Unknown(255);
        
        private int value;

        private CameraSharpness(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraSharpness find(int i) {
            CameraSharpness cameraSharpness = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraSharpness;
        }
    }

    public enum CameraShootPhotoMode {
        Single(0, TYPE.SINGLE),
        HDR(1, TYPE.HDR),
        Burst(2, TYPE.BURST),
        AEBCapture(3, TYPE.AEB),
        Interval(4, TYPE.TIME),
        TimeLapse(5, TYPE.TIME),
        Panorama(6, TYPE.APP_FULLVIEW);
        
        private TYPE type;
        private int value;

        private CameraShootPhotoMode(int i, TYPE type) {
            this.value = i;
            this.type = type;
        }

        public int value() {
            return this.value;
        }

        public int getInernalTypeValue() {
            if (this.type != null) {
                return this.type.a();
            }
            return -1;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraShootPhotoMode find(int i) {
            CameraShootPhotoMode cameraShootPhotoMode = Interval;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraShootPhotoMode;
        }

        public static CameraShootPhotoMode find(TYPE type) {
            if (type == null) {
                return Single;
            }
            CameraShootPhotoMode cameraShootPhotoMode = Single;
            for (int i = 0; i < values().length; i++) {
                if (values()[i].getInernalTypeValue() == type.a()) {
                    return values()[i];
                }
            }
            return cameraShootPhotoMode;
        }
    }

    public enum CameraShutterSpeed {
        ShutterSpeed1_8000(1.25E-4f),
        ShutterSpeed1_6400(1.5625E-4f),
        ShutterSpeed1_6000(1.6666666E-4f),
        ShutterSpeed1_5000(2.0E-4f),
        ShutterSpeed1_4000(2.5E-4f),
        ShutterSpeed1_3200(3.125E-4f),
        ShutterSpeed1_3000(3.3333333E-4f),
        ShutterSpeed1_2500(4.0E-4f),
        ShutterSpeed1_2000(5.0E-4f),
        ShutterSpeed1_1600(6.25E-4f),
        ShutterSpeed1_1500(6.6666666E-4f),
        ShutterSpeed1_1250(8.0E-4f),
        ShutterSpeed1_1000(0.001f),
        ShutterSpeed1_800(0.00125f),
        ShutterSpeed1_725(0.0013793104f),
        ShutterSpeed1_640(0.0015625f),
        ShutterSpeed1_500(0.002f),
        ShutterSpeed1_400(0.0025f),
        ShutterSpeed1_350(0.0028571428f),
        ShutterSpeed1_320(0.003125f),
        ShutterSpeed1_250(0.004f),
        ShutterSpeed1_240(0.004166667f),
        ShutterSpeed1_200(0.005f),
        ShutterSpeed1_180(0.0055555557f),
        ShutterSpeed1_160(0.00625f),
        ShutterSpeed1_125(0.008f),
        ShutterSpeed1_120(0.008333334f),
        ShutterSpeed1_100(0.01f),
        ShutterSpeed1_90(0.011111111f),
        ShutterSpeed1_80(0.0125f),
        ShutterSpeed1_60(0.016666668f),
        ShutterSpeed1_50(0.02f),
        ShutterSpeed1_40(0.025f),
        ShutterSpeed1_30(0.033333335f),
        ShutterSpeed1_25(0.04f),
        ShutterSpeed1_20(0.05f),
        ShutterSpeed1_15(0.06666667f),
        ShutterSpeed1_12p5(0.08f),
        ShutterSpeed1_10(0.1f),
        ShutterSpeed1_8(0.125f),
        ShutterSpeed1_6p25(0.16f),
        ShutterSpeed1_5(0.2f),
        ShutterSpeed1_4(c.a),
        ShutterSpeed1_3(0.33333334f),
        ShutterSpeed1_2p5(0.4f),
        ShutterSpeed1_2(d.c),
        ShutterSpeed1_1p67(0.5988024f),
        ShutterSpeed1_1p25(0.8f),
        ShutterSpeed1p0(1.0f),
        ShutterSpeed1p3(1.3f),
        ShutterSpeed1p6(b.d),
        ShutterSpeed2p0(2.0f),
        ShutterSpeed2p5(2.5f),
        ShutterSpeed3p0(3.0f),
        ShutterSpeed3p2(3.2f),
        ShutterSpeed4p0(DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity),
        ShutterSpeed5p0(5.0f),
        ShutterSpeed6p0(6.0f),
        ShutterSpeed7p0(7.0f),
        ShutterSpeed8p0(8.0f);
        
        public float shutterSpeed;

        private CameraShutterSpeed(float f) {
            this.shutterSpeed = f;
        }

        public float value() {
            return this.shutterSpeed;
        }

        public static CameraShutterSpeed find(float f) {
            for (CameraShutterSpeed cameraShutterSpeed : values()) {
                if (cameraShutterSpeed.shutterSpeed == f) {
                    return cameraShutterSpeed;
                }
            }
            return null;
        }
    }

    public static class CameraShutterSpeedRange extends BaseRange<CameraShutterSpeed> {
        public CameraShutterSpeedRange(int i) {
            super(i);
        }

        public CameraShutterSpeedRange(Set<CameraShutterSpeed> set) {
            super((Set) set);
        }

        public CameraShutterSpeedRange(CameraShutterSpeed cameraShutterSpeed, CameraShutterSpeed cameraShutterSpeed2) {
            super(cameraShutterSpeed, cameraShutterSpeed2);
        }
    }

    public enum CameraThermalDigitalZoomScale {
        x1(0),
        x2(1),
        x4(2),
        x8(3),
        Unknown(255);
        
        private int value;

        private CameraThermalDigitalZoomScale(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }
    }

    public enum CameraThermalExternalParamProfile {
        Profile1(0),
        Profile2(1),
        Profile3(2),
        Unknown(255);
        
        private int value;

        private CameraThermalExternalParamProfile(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraThermalExternalParamProfile find(int i) {
            CameraThermalExternalParamProfile cameraThermalExternalParamProfile = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraThermalExternalParamProfile;
        }
    }

    public enum CameraThermalFFCMode {
        Auto(0),
        Manual(1),
        Unknown(255);
        
        private int value;

        private CameraThermalFFCMode(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }
    }

    public enum CameraThermalFrameRateUpperBound {
        UpperBound_8p3Hz(0),
        UpperBound_30Hz(1),
        Unknown(255);
        
        private int value;

        private CameraThermalFrameRateUpperBound(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }
    }

    public enum CameraThermalGainMode {
        Auto(0),
        Low(1),
        High(2),
        Unknown(255);
        
        private int value;

        private CameraThermalGainMode(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }
    }

    public enum CameraThermalIsothermUnit {
        Percentage(0),
        Celsius(1),
        Unknown(255);
        
        private int value;

        private CameraThermalIsothermUnit(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }
    }

    public enum CameraThermalLensFocalLength {
        Length_6p8mm(0),
        Length_7p5mm(1),
        Length_9mm(2),
        Length_13mm(3),
        Length_19mm(4),
        Unknown(255);
        
        private int value;

        private CameraThermalLensFocalLength(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }
    }

    public enum CameraThermalPalette {
        WhiteHot(0),
        BlackHot(1),
        Fusion(2),
        Rainbow(3),
        Ironbow_1(4),
        IceFire(5),
        Sepia(6),
        Globow(7),
        Ironbow_2(8),
        Color_1(9),
        Color_2(10),
        Rain(11),
        RedHot(12),
        GreenHot(13),
        Unknown(255);
        
        private int value;

        private CameraThermalPalette(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }
    }

    public static class CameraThermalProfile {
        public CameraThermalLensFocalLength focalLength;
        public CameraThermalFrameRateUpperBound frameRateUpperBound;
        public CameraThermalResolution thermalResolution;

        public String toString() {
            return this.thermalResolution.toString() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.frameRateUpperBound.toString() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.focalLength.toString();
        }
    }

    public enum CameraThermalROI {
        Full(0),
        SkyExcluded_33(1),
        SkyExcluded_50(2),
        Unknown(255);
        
        private int value;

        private CameraThermalROI(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }
    }

    public enum CameraThermalResolution {
        Resolution_336x256(0),
        Resolution_640x512(1),
        Unknown(255);
        
        private int value;

        private CameraThermalResolution(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }
    }

    public enum CameraThermalScene {
        Linear(0),
        Default(1),
        SeaSky(2),
        Outdoor(3),
        Indoor(4),
        Manual(5),
        User_1(6),
        User_2(7),
        User_3(8),
        Unknown(255);
        
        private int value;

        private CameraThermalScene(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }
    }

    public enum CameraVideoFileFormat {
        MOV(0),
        MP4(1),
        Unknown(255);
        
        private int value;

        private CameraVideoFileFormat(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraVideoFileFormat find(int i) {
            CameraVideoFileFormat cameraVideoFileFormat = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraVideoFileFormat;
        }
    }

    public enum CameraVideoFrameRate {
        FrameRate_24fps(0),
        FrameRate_25fps(1),
        FrameRate_30fps(2),
        FrameRate_48fps(3),
        FrameRate_50fps(4),
        FrameRate_60fps(5),
        FrameRate_96fps(6),
        FrameRate_120fps(7),
        Unknown(255);
        
        private int value;

        private CameraVideoFrameRate(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraVideoFrameRate find(int i) {
            CameraVideoFrameRate cameraVideoFrameRate = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraVideoFrameRate;
        }
    }

    public static class CameraVideoFrameRateRange extends BaseRange<CameraVideoFrameRate> {
        public CameraVideoFrameRateRange(int i) {
            super(i);
        }

        public CameraVideoFrameRateRange(Set<CameraVideoFrameRate> set) {
            super((Set) set);
        }

        public CameraVideoFrameRateRange(CameraVideoFrameRate cameraVideoFrameRate, CameraVideoFrameRate cameraVideoFrameRate2) {
            super(cameraVideoFrameRate, cameraVideoFrameRate2);
        }
    }

    public enum CameraVideoPlaybackOperation {
        Stop(0),
        Start(1),
        FastForward(2),
        FastBackward(3),
        Seeking(4),
        Unknown(255);
        
        private int value;

        private CameraVideoPlaybackOperation(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraVideoPlaybackOperation find(int i) {
            CameraVideoPlaybackOperation cameraVideoPlaybackOperation = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraVideoPlaybackOperation;
        }
    }

    public enum CameraVideoResolution {
        Resolution_1280x720(0),
        Resolution_1920x1080(1),
        Resolution_2704X1520(2),
        Resolution_2720x1530(3),
        Resolution_3840x2160(4),
        Resolution_4096x2160(5),
        Unknown(255);
        
        private int value;

        private CameraVideoResolution(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraVideoResolution find(int i) {
            CameraVideoResolution cameraVideoResolution = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraVideoResolution;
        }
    }

    public static class CameraVideoResolutionAndFrameRateRange extends BaseRange<CameraVideoResolutionAndFrameRate> {
        public CameraVideoResolutionAndFrameRateRange(int i) {
            super(i);
        }

        public CameraVideoResolutionAndFrameRateRange(Set<CameraVideoResolutionAndFrameRate> set) {
            super((Set) set);
        }

        public CameraVideoResolutionAndFrameRateRange(CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate, CameraVideoResolutionAndFrameRate cameraVideoResolutionAndFrameRate2) {
            super(cameraVideoResolutionAndFrameRate, cameraVideoResolutionAndFrameRate2);
        }
    }

    public static class CameraVideoResolutionRange extends BaseRange<CameraVideoResolution> {
        public CameraVideoResolutionRange(int i) {
            super(i);
        }

        public CameraVideoResolutionRange(Set<CameraVideoResolution> set) {
            super((Set) set);
        }

        public CameraVideoResolutionRange(CameraVideoResolution cameraVideoResolution, CameraVideoResolution cameraVideoResolution2) {
            super(cameraVideoResolution, cameraVideoResolution2);
        }
    }

    public enum CameraVideoStandard {
        PAL(0),
        NTSC(1),
        Unknown(255);
        
        private int value;

        private CameraVideoStandard(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraVideoStandard find(int i) {
            CameraVideoStandard cameraVideoStandard = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraVideoStandard;
        }
    }

    public enum CameraWhiteBalance {
        Auto(0),
        Sunny(1),
        Cloudy(2),
        WaterSuface(3),
        IndoorIncandescent(4),
        IndoorFluorescent(5),
        CustomColorTemperature(6),
        Unknown(255);
        
        private int value;

        private CameraWhiteBalance(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }

        public static CameraWhiteBalance find(int i) {
            CameraWhiteBalance cameraWhiteBalance = Unknown;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return cameraWhiteBalance;
        }
    }

    public enum OpticalZoomDirection {
        ZoomOut,
        ZoomIn
    }

    public enum OpticalZoomSpeed {
        Slowest(72),
        Slow(73),
        ModeratelySlow(74),
        Normal(75),
        ModeratelyFast(76),
        Fast(77),
        Fastest(78);
        
        private int value;

        private OpticalZoomSpeed(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }

        public boolean _equals(int i) {
            return this.value == i;
        }
    }
}
