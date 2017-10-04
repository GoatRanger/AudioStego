package dji.midware.data.config.P3;

import android.support.v4.media.TransportMediator;
import com.here.posclient.analytics.TrackerEvent;
import com.loopj.android.http.BuildConfig;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraGetPushChartInfo;
import dji.midware.data.model.P3.DataCameraGetPushCurPanoFileName;
import dji.midware.data.model.P3.DataCameraGetPushFovParam;
import dji.midware.data.model.P3.DataCameraGetPushPlayBackParams;
import dji.midware.data.model.P3.DataCameraGetPushPrepareOpenFan;
import dji.midware.data.model.P3.DataCameraGetPushRawParams;
import dji.midware.data.model.P3.DataCameraGetPushRecordingName;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushShutterCmd;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushTapZoomStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushTauParam;
import dji.midware.data.model.P3.DataCameraGetPushTimelapseParms;
import dji.midware.data.model.P3.DataCameraGetPushTrackingStatus;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode;
import dji.pilot.usercenter.protocol.d;
import it.sauronsoftware.ftp4j.FTPCodes;

public class b implements dji.midware.e.a {

    public enum a {
        SetPhoto(1),
        SetRecord(2),
        SetHeart(3),
        VirtualKey(5),
        SwitchUSB(4),
        GetUSB(6),
        SetMode(16),
        GetMode(17),
        SetImageSize(18),
        GetImageSize(19),
        SetImageQuality(20),
        GetImageQuality(21),
        SetImageFormat(22),
        GetImageFormat(23),
        SetVideoFormat(24),
        GetVideoFormat(25),
        SetVideoQuality(26),
        GetVideoQuality(27),
        SetVideoStoreFormat(28),
        GetVideoStoreFormat(29),
        SetExposureMode(30),
        GetExposureMode(31),
        SetSceneMode(32),
        GetSceneMode(33),
        SetMetering(34),
        GetMetering(35),
        SetFocusMode(36),
        GetFocusMode(37),
        SetAperture(38),
        GetAperture(39),
        SetShutterSpeed(40),
        GetShutterSpeed(41),
        SetIso(42),
        GetIso(43),
        SetWhiteBalance(44),
        GetWhiteBalance(45),
        SetExposureCompensation(46),
        GetExposureCompensation(47),
        SetFocusArea(48),
        SetMeteringArea(50),
        GetMeteringArea(51),
        SetFocusParam(52),
        SetZoomParams(52),
        SetSharpe(56),
        GetSharpe(57),
        SetContrast(58),
        GetContrast(59),
        SetSaturation(60),
        GetSaturation(61),
        SetTonal(62),
        GetTonal(63),
        SetDigitalFilter(66),
        GetDigitalFilter(67),
        SetAntiFlicker(70),
        GetAntiFlicker(71),
        SetContinuous(72),
        GetContinuous(73),
        SetTimeParams(74),
        GetTimeParams(75),
        SetVOutParams(76),
        GetVOutParams(77),
        SetQuickPlayBack(78),
        GetQuickPlayBack(79),
        SetDate(84),
        SetAEBParms(94),
        GetAEBParams(95),
        SetFileIndexMode(92),
        GetFileIndexMode(93),
        SetPushChart(96),
        GetPushChart(97),
        SetVideoCaption(98),
        GetVideoCaption(99),
        SetStandard(102),
        GetStandard(103),
        SetVideoRecordMode(108),
        GetVideoRecordMode(109),
        SetPanoMode(FTPCodes.RESTART_MARKER),
        SetAELock(104),
        GetAELock(105),
        SetPhotoMode(106),
        GetPhotoMode(107),
        GetStateInfo(d.k),
        GetSDCardParams(113),
        FormatSDCard(114),
        SaveParams(119),
        LoadParams(120),
        DeletePhoto(TrackerEvent.PositioningHybridOutdoor),
        VideoControl(122),
        SingleChoice(123),
        ResponseRc(124, false, DataCameraGetPushShutterCmd.class),
        ScaleGesture(FTPCodes.DATA_CONNECTION_ALREADY_OPEN),
        DragGesture(TransportMediator.KEYCODE_MEDIA_PLAY),
        GetPushStateInfo(128, false, DataCameraGetPushStateInfo.class),
        GetPushShotParams(129, false, DataCameraGetPushShotParams.class),
        GetPushPlayBackParams(TransportMediator.KEYCODE_MEDIA_RECORD, false, DataCameraGetPushPlayBackParams.class),
        GetPushChartParams(TrackerEvent.PositioningOfflineOutdoor, false, DataCameraGetPushChartInfo.class),
        GetPushRecordingName(TrackerEvent.PositioningOfflineCommonIndoor, false, DataCameraGetPushRecordingName.class),
        GetPushCurPanoFileName(134, false, DataCameraGetPushCurPanoFileName.class),
        GetPushTimelapseParms(136, false, DataCameraGetPushTimelapseParms.class),
        GetPushRawParams(TrackerEvent.PositioningOfflinePrivateIndoor, false, DataCameraGetPushRawParams.class),
        GetPushShotInfo(135, false, DataCameraGetPushShotInfo.class),
        GetPushTrackingStatus(137, false, DataCameraGetPushTrackingStatus.class),
        GetPushFovParam(138, false, DataCameraGetPushFovParam.class),
        GetVideoParams(BuildConfig.VERSION_CODE),
        ControlTransCode(147),
        SetFocusStroke(149),
        GetFileParams(152),
        SetVideoContrastEnhance(155),
        GetVideoContrastEnhance(156),
        SetAudioParma(159),
        GetAudioParam(160),
        FormatSSD(161),
        GetTrackingParms(165),
        SetTrackingParms(166),
        SetAEUnlockMode(168),
        GetAEUnlockMode(169),
        GetPanoFileParams(170),
        SetVideoEncode(171),
        GetVideoEncode(172),
        SetSSDVideoFormat(175),
        GetSSDVideoFormat(176),
        GetShotInfo(153),
        SetFocusAid(154),
        SetWhiteBalanceArea(157),
        GetWhiteBalanceArea(158),
        FormatRawSSD(161),
        SetFocusDistance(162),
        SetFocusWindow(164),
        SetIris(167),
        SetMCTF(173),
        GetMCTF(174),
        SetRecordFan(177),
        GetRecordFan(178),
        RequestIFrame(179),
        GetPushPrepareOpenFan(180, false, DataCameraGetPushPrepareOpenFan.class),
        SetForeArmLed(182),
        GetForeArmLed(183),
        SetOpticsZoom(184, false, DataCameraSetOpticsZoomMode.class),
        SetCameraRotationMode(185),
        GetCameraRotationMode(186),
        SetLockGimbalWhenShot(187),
        SetLogMode(193),
        SetParamName(194),
        GetParamName(195),
        SetTapZoom(196),
        GetTapZoom(197),
        SetTapZoomTarget(198),
        GetPushTapZoom(199, false, DataCameraGetPushTapZoomStateInfo.class),
        SetDefogEnabled(200),
        GetDefogEnabled(dji.pilot.usercenter.f.d.y),
        TauParam(241),
        GetPushTauParam(242, false, DataCameraGetPushTauParam.class),
        GetFocusInfinite(249),
        SetFocusInfinite(250),
        ERROR(511);
        
        private static a[] cc;
        private int bZ;
        private boolean ca;
        private Class<? extends n> cb;

        static {
            cc = values();
        }

        private a(int i) {
            this.ca = true;
            this.bZ = i;
        }

        private a(int i, boolean z, Class<? extends n> cls) {
            this.ca = true;
            this.bZ = i;
            this.ca = z;
            this.cb = cls;
        }

        public int a() {
            return this.bZ;
        }

        public boolean b() {
            return this.ca;
        }

        public Class<? extends n> c() {
            return this.cb;
        }

        public boolean a(int i) {
            return this.bZ == i;
        }

        public static a find(int i) {
            a aVar = ERROR;
            for (int i2 = 0; i2 < cc.length; i2++) {
                if (cc[i2].a(i)) {
                    return cc[i2];
                }
            }
            return aVar;
        }
    }

    public boolean a(int i) {
        return false;
    }

    public boolean b(int i) {
        return a.find(i).b();
    }

    public boolean c(int i) {
        return true;
    }

    public Class<? extends n> d(int i) {
        return a.find(i).c();
    }

    public String a(int i, int i2) {
        return r.getDataModelName(DeviceType.find(i).toString(), a.find(i2).toString());
    }

    public String a(int i, int i2, int i3) {
        return a(i, i3);
    }

    public n e(int i) {
        return null;
    }
}
