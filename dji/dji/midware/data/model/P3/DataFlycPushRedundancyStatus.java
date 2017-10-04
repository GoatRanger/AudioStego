package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataFlycRedundancyStatus.IMUStatus;
import dji.midware.data.model.P3.DataFlycRedundancyStatus.RS_CMD_TYPE;

public class DataFlycPushRedundancyStatus extends n {
    private static DataFlycPushRedundancyStatus a = null;

    public static class RedundancySwitchInfo {
        public long dstErrCode;
        public int dstImuIndex;
        public int id;
        public int reqID;
        public int resultCode;
        public long srcErrCode;
        public int srcImuIndex;
        public long time;
    }

    public static synchronized DataFlycPushRedundancyStatus getInstance() {
        DataFlycPushRedundancyStatus dataFlycPushRedundancyStatus;
        synchronized (DataFlycPushRedundancyStatus.class) {
            if (a == null) {
                a = new DataFlycPushRedundancyStatus();
            }
            dataFlycPushRedundancyStatus = a;
        }
        return dataFlycPushRedundancyStatus;
    }

    protected void doPack() {
    }

    public RS_CMD_TYPE a() {
        return RS_CMD_TYPE.ofValue(((Integer) get(0, 1, Integer.class)).intValue());
    }

    public IMUStatus b() {
        boolean z = false;
        RS_CMD_TYPE ofValue = RS_CMD_TYPE.ofValue(((Integer) get(0, 1, Integer.class)).intValue());
        IMUStatus iMUStatus = new IMUStatus();
        if (ofValue == RS_CMD_TYPE.SEND_ERR_STATE) {
            boolean z2;
            iMUStatus.colorStatus = ((Integer) get(1, 1, Integer.class)).intValue();
            long longValue = ((Long) get(2, 4, Long.class)).longValue();
            iMUStatus.isRealInAir = ((longValue >> 2) & 1) == 1;
            iMUStatus.imuIndex = (int) ((longValue >> 4) & 15);
            iMUStatus.devType = (int) ((longValue >> 8) & 255);
            iMUStatus.devIndex = (int) ((longValue >> 12) & 15);
            iMUStatus.devErrCode = (int) ((longValue >> 24) & 255);
            int intValue = ((Integer) get(6, 2, Integer.class)).intValue();
            if ((intValue & 1) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            iMUStatus.isNeedRefreshLed = z2;
            if ((intValue & 2) == 2) {
                z2 = true;
            } else {
                z2 = false;
            }
            iMUStatus.isNeedShowAtStatusBar = z2;
            if ((intValue & 4) == 4) {
                z2 = true;
            } else {
                z2 = false;
            }
            iMUStatus.canRepairForFree = z2;
            if ((intValue & 8) == 8) {
                z2 = true;
            } else {
                z2 = false;
            }
            iMUStatus.isNeedAnalyseByApp = z2;
            if ((intValue & 16) == 16) {
                z = true;
            }
            iMUStatus.canProduction = z;
            iMUStatus.userAction = ((Integer) get(8, 1, Integer.class)).intValue();
        }
        return iMUStatus;
    }

    public RedundancySwitchInfo c() {
        RedundancySwitchInfo redundancySwitchInfo = new RedundancySwitchInfo();
        redundancySwitchInfo.reqID = ((Integer) get(1, 1, Integer.class)).intValue();
        redundancySwitchInfo.srcImuIndex = ((Integer) get(2, 1, Integer.class)).intValue();
        redundancySwitchInfo.dstImuIndex = ((Integer) get(3, 1, Integer.class)).intValue();
        redundancySwitchInfo.resultCode = ((Integer) get(4, 1, Integer.class)).intValue();
        redundancySwitchInfo.srcErrCode = ((Long) get(5, 4, Long.class)).longValue();
        redundancySwitchInfo.dstErrCode = ((Long) get(9, 4, Long.class)).longValue();
        return redundancySwitchInfo;
    }
}
