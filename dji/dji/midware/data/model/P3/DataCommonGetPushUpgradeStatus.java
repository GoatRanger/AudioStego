package dji.midware.data.model.P3;

import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.n;
import dji.midware.util.c;
import java.util.ArrayList;

public class DataCommonGetPushUpgradeStatus extends n {
    private static DataCommonGetPushUpgradeStatus instance = null;

    public enum DJIUpgradeCompleteReason {
        Success(1),
        Failure(2),
        FirmwareError(3),
        SameVersion(4),
        UserCancel(5),
        TimeOut(6),
        MotorWorking(7),
        FirmNotMatch(8),
        IllegalDegrade(9),
        NoConnectRC(10),
        OTHER(100);
        
        private int data;

        private DJIUpgradeCompleteReason(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static DJIUpgradeCompleteReason find(int i) {
            DJIUpgradeCompleteReason dJIUpgradeCompleteReason = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return dJIUpgradeCompleteReason;
        }
    }

    public enum DJIUpgradeStep {
        Verify(1),
        UserConfirm(2),
        Upgrading(3),
        Complete(4),
        OTHER(100);
        
        private int data;

        private DJIUpgradeStep(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static DJIUpgradeStep find(int i) {
            DJIUpgradeStep dJIUpgradeStep = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return dJIUpgradeStep;
        }
    }

    public static class DataCommonGetPushUpgradeStatusDescInfo {
        public int mDeviceId;
        public int mFirmwareType;
        public int mFirmwareVersion;
        public int mUpgradeProcess;
        public int mUpgradeStatus;

        public static DataCommonGetPushUpgradeStatusDescInfo parseItem(byte[] bArr, int i) {
            DataCommonGetPushUpgradeStatusDescInfo dataCommonGetPushUpgradeStatusDescInfo = new DataCommonGetPushUpgradeStatusDescInfo();
            dataCommonGetPushUpgradeStatusDescInfo.mDeviceId = bArr[i] & 255;
            dataCommonGetPushUpgradeStatusDescInfo.mFirmwareType = bArr[i + 1] & 255;
            dataCommonGetPushUpgradeStatusDescInfo.mFirmwareVersion = c.b(bArr, 2);
            dataCommonGetPushUpgradeStatusDescInfo.mUpgradeStatus = bArr[i + 6] & 255;
            dataCommonGetPushUpgradeStatusDescInfo.mUpgradeProcess = bArr[i + 7] & 255;
            return dataCommonGetPushUpgradeStatusDescInfo;
        }

        public String toString() {
            return String.format("mDeviceId = %d, mFirmwareType = %d, mFirmwareVersion = %d, mUpgradeStatus = %d, mUpgradeProcess = %d", new Object[]{Integer.valueOf(this.mDeviceId), Integer.valueOf(this.mFirmwareType), Integer.valueOf(this.mFirmwareVersion), Integer.valueOf(this.mUpgradeStatus), Integer.valueOf(this.mUpgradeProcess)});
        }
    }

    public static class DataCommonGetPushUpgradeStatusInfo {
        public DJIUpgradeCompleteReason completeReason;
        public int mCurUpgradeIndex;
        public ArrayList<DataCommonGetPushUpgradeStatusDescInfo> mUpgradeDescList;
        public int mUpgradeProcess;
        public int mUpgradeResult;
        public int mUpgradeState;
        public DJIUpgradeStep mUpgradeStep;
        public int mUpgradeTimes;
        public int mUserReserve;
        public int mUserTime;

        public DataCommonGetPushUpgradeStatusInfo(byte[] bArr) {
            int i = 0;
            if (bArr != null) {
                this.mUpgradeState = bArr[0] & 255;
                this.mUpgradeStep = DJIUpgradeStep.find(this.mUpgradeState);
                switch (this.mUpgradeStep) {
                    case UserConfirm:
                        this.mUserTime = c.a(bArr[1]);
                        this.mUserReserve = c.a(bArr[2]);
                        break;
                    case Upgrading:
                        this.mCurUpgradeIndex = (bArr[2] >> 5) & 7;
                        this.mUpgradeTimes = bArr[2] & 31;
                        this.mUpgradeProcess = bArr[1] & 255;
                        break;
                    case Complete:
                        this.mUpgradeTimes = bArr[2] & 255;
                        this.mUpgradeResult = bArr[1] & 255;
                        this.completeReason = DJIUpgradeCompleteReason.find(this.mUpgradeResult);
                        break;
                }
                this.mUpgradeDescList = new ArrayList();
                while (i < this.mUpgradeTimes) {
                    this.mUpgradeDescList.add(DataCommonGetPushUpgradeStatusDescInfo.parseItem(bArr, (i * 8) + 3));
                    i++;
                }
            }
        }
    }

    public static synchronized DataCommonGetPushUpgradeStatus getInstance() {
        DataCommonGetPushUpgradeStatus dataCommonGetPushUpgradeStatus;
        synchronized (DataCommonGetPushUpgradeStatus.class) {
            if (instance == null) {
                instance = new DataCommonGetPushUpgradeStatus();
            }
            dataCommonGetPushUpgradeStatus = instance;
        }
        return dataCommonGetPushUpgradeStatus;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    protected void doPack() {
    }

    public DataCommonGetPushUpgradeStatusInfo getDescList() {
        try {
            return new DataCommonGetPushUpgradeStatusInfo(this._recData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] getBytes() {
        return this._recData;
    }

    public DeviceType getSenderDeviceType() {
        if (this.pack != null) {
            return DeviceType.find(this.pack.f);
        }
        return null;
    }
}
