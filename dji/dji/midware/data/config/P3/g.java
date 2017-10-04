package dji.midware.data.config.P3;

import android.support.v4.media.TransportMediator;
import com.here.posclient.analytics.TrackerEvent;
import com.loopj.android.http.BuildConfig;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataA2PushCommom;
import dji.midware.data.model.P3.DataFlycGetPushActiveRequest;
import dji.midware.data.model.P3.DataFlycGetPushAgpsStatus;
import dji.midware.data.model.P3.DataFlycGetPushAvoid;
import dji.midware.data.model.P3.DataFlycGetPushAvoidParam;
import dji.midware.data.model.P3.DataFlycGetPushBoardRecv;
import dji.midware.data.model.P3.DataFlycGetPushDeformStatus;
import dji.midware.data.model.P3.DataFlycGetPushFaultInject;
import dji.midware.data.model.P3.DataFlycGetPushFlycInstallError;
import dji.midware.data.model.P3.DataFlycGetPushForbidStatus;
import dji.midware.data.model.P3.DataFlycGetPushGpsSnr;
import dji.midware.data.model.P3.DataFlycGetPushLedStatus;
import dji.midware.data.model.P3.DataFlycGetPushLimitState;
import dji.midware.data.model.P3.DataFlycGetPushParamsByHash;
import dji.midware.data.model.P3.DataFlycGetPushPowerParam;
import dji.midware.data.model.P3.DataFlycGetPushRTKLocationData;
import dji.midware.data.model.P3.DataFlycGetPushRequestLimitUpdate;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataFlycGetPushUnlimitState;
import dji.midware.data.model.P3.DataFlycGetPushWayPointMissionCurrentEvent;
import dji.midware.data.model.P3.DataFlycGetPushWayPointMissionInfo;
import dji.midware.data.model.P3.DataFlycPushRedundancyStatus;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushHome;

public class g implements dji.midware.e.a {

    public enum a {
        SimScan(0),
        SimGetParams(1),
        SimParams(2),
        SimCommand(5),
        SetFlyForbidArea(8),
        GetPushFlyForbidStatus(9, false, DataFlycGetPushForbidStatus.class),
        SetHaveCheckedStruct(13),
        A2PushCommom(16, false, DataA2PushCommom.class),
        SimRc(17),
        SetDate(28),
        SimStatus(22),
        ExecFly(39),
        FunctionControl(42, true),
        SetIoc(43),
        GetIoc(44),
        SetLimits(45),
        GetLimits(46),
        SetVoltageWarnning(47),
        GetVoltageWarnning(48),
        SetHomePoint(49),
        GetPushDeformStatus(50, false, DataFlycGetPushDeformStatus.class),
        GetPlaneName(52, true),
        SetPlaneName(51, true),
        SetReadFlyDataMode(57, true),
        FormatDataRecorder(58, true),
        SetFsAction(59),
        GetFsAction(60),
        SetTimeZone(61),
        GetPushRequestLimitUpdate(62, false, DataFlycGetPushRequestLimitUpdate.class),
        SetFlyForbidAreaData(63),
        UploadUnlimitAreas(65),
        EnableUnlimitAreas(71),
        GetPushUnlimitAreas(66, false, DataFlycGetPushUnlimitState.class),
        GetPushCommon(67, false, DataOsdGetPushCommon.class),
        GetPushHome(68, false, DataOsdGetPushHome.class),
        GetPushGpsSnr(69, false, DataFlycGetPushGpsSnr.class),
        SetPushGpsSnr(70),
        GetPushSmartBattery(81, false, DataFlycGetPushSmartBattery.class),
        SmartLowBatteryAck(82),
        GetPushAvoidParam(83, false, DataFlycGetPushAvoidParam.class),
        GetPushLimitState(85, false, DataFlycGetPushLimitState.class),
        GetPushLedStatus(86, false, DataFlycGetPushLedStatus.class),
        GetPushActiveRequest(97, false, DataFlycGetPushActiveRequest.class),
        SetActiveResult(98),
        GetPushOnBoardRecv(99, false, DataFlycGetPushBoardRecv.class),
        SetSendOnBoard(100),
        RTKSwitch(105),
        GetPowerParam(103, false, DataFlycGetPushPowerParam.class),
        SetRTKState(105),
        GetPushAvoid(106, false, DataFlycGetPushAvoid.class),
        GetPushRTKLocationData(108, false, DataFlycGetPushRTKLocationData.class),
        SetBatteryValidStste(106),
        NavigationSwitch(128),
        UploadWayPointMissionMsg(TransportMediator.KEYCODE_MEDIA_RECORD),
        DownloadWayPointMissionMsg(TrackerEvent.PositioningOfflineOutdoor),
        UploadWayPointMsgByIndex(TrackerEvent.PositioningOfflineCommonIndoor),
        DownloadWayPointMsgByIndex(TrackerEvent.PositioningOfflinePrivateIndoor),
        WayPointMissionSwitch(134),
        NoeMissionPauseOrResume(143),
        WayPointMissionPauseOrResume(135),
        StartNoeMission(148),
        StopNoeMission(149),
        WayPointMissionSetIdleSpeed(156),
        GetPushWayPointMissionInfo(136, false, DataFlycGetPushWayPointMissionInfo.class),
        GetPushWayPointMissionCurrentEvent(137, false, DataFlycGetPushWayPointMissionCurrentEvent.class),
        StartHotPointMissionWithInfo(138),
        CancelHotPointMission(139),
        HotPointMissionSwitch(140),
        HotPointMissionDownload(150),
        HotPointResetParams(153),
        HotPointResetCamera(155),
        JoyStick(142),
        StartFollowMeWithInfo(144),
        CancelFollowMeMission(145),
        FollowMeMissionSwitch(BuildConfig.VERSION_CODE),
        SendGpsInfo(147),
        StartIoc(151),
        StopIoc(152),
        SendAgpsData(160, true),
        GetPushAgpsState(161, false, DataFlycGetPushAgpsStatus.class),
        GetPushFlycInstallError(173, false, DataFlycGetPushFlycInstallError.class),
        SetFlightIdleSpeed(156),
        GetBatteryGroupsSingleInfo(176),
        FaultInject(181),
        GetPushFaultInject(182, false, DataFlycGetPushFaultInject.class),
        SetAndGetRedundancyIMUIndex(183),
        RedundancyStatus(184),
        PushRedundancyStatus(185, false, DataFlycPushRedundancyStatus.class),
        SetFlyforbidData(233),
        SetEscEcho(237),
        GetParamInfoByIndex(240),
        GetParamsByIndex(241),
        SetParamsByIndex(242),
        ResetParamsByIndex(243),
        GetPushParamsByIndex(244, false, null),
        GetParamInfoByHash(247),
        GetParamsByHash(248),
        SetParamsByHash(249),
        ResetParamsByHash(250),
        GetPushParamsByHash(251, false, DataFlycGetPushParamsByHash.class),
        SetPushParams(252),
        Other(511);
        
        private static a[] bc;
        private int aY;
        private boolean aZ;
        private boolean ba;
        private Class<? extends n> bb;

        static {
            bc = values();
        }

        private a(int i) {
            this.aZ = true;
            this.ba = false;
            this.aY = i;
        }

        private a(int i, boolean z, Class<? extends n> cls) {
            this.aZ = true;
            this.ba = false;
            this.aY = i;
            this.aZ = z;
            this.bb = cls;
        }

        private a(int i, boolean z) {
            this.aZ = true;
            this.ba = false;
            this.aY = i;
            this.ba = z;
        }

        public int a() {
            return this.aY;
        }

        public boolean b() {
            return this.aZ;
        }

        public Class<? extends n> c() {
            return this.bb;
        }

        public boolean d() {
            return this.ba;
        }

        public boolean a(int i) {
            return this.aY == i;
        }

        public static a find(int i) {
            a aVar = Other;
            for (int i2 = 0; i2 < bc.length; i2++) {
                if (bc[i2].a(i)) {
                    return bc[i2];
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
        return a.find(i).d();
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
