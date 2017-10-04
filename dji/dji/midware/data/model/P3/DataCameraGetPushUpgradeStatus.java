package dji.midware.data.model.P3;

import android.util.SparseArray;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.model.a.a;
import dji.midware.util.c;

public class DataCameraGetPushUpgradeStatus extends a {
    private static DataCameraGetPushUpgradeStatus instance = null;
    private SparseArray<UpgradeStatusModel> list = new SparseArray();

    public enum FirmwareType {
        Loader(1),
        Sys(2),
        App(3),
        OTHER(100);
        
        private int data;

        private FirmwareType(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static FirmwareType find(int i) {
            FirmwareType firmwareType = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return firmwareType;
        }
    }

    public enum FirmwareUpgradeStatus {
        Success(0),
        Progressing(1),
        Waiting(2),
        Error(3),
        CanotCheck(4),
        OTHER(100);
        
        private int data;

        private FirmwareUpgradeStatus(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static FirmwareUpgradeStatus find(int i) {
            FirmwareUpgradeStatus firmwareUpgradeStatus = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return firmwareUpgradeStatus;
        }
    }

    public enum UpgradeEndCause {
        Success(1),
        Failed(2),
        FirmwareError(3),
        VersionSame(4),
        UserCancel(5),
        TimeoutCancel(6),
        MotorUp(7),
        OTHER(100);
        
        private int data;

        private UpgradeEndCause(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static UpgradeEndCause find(int i) {
            UpgradeEndCause upgradeEndCause = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return upgradeEndCause;
        }
    }

    public static class UpgradeStatusModel {
        public int degree;
        public DeviceType deviceType;
        public FirmwareUpgradeStatus status;
        public FirmwareType type;
        public String version;
    }

    public enum UpgradeStep {
        Check(1),
        Ack(2),
        Progress(3),
        End(4),
        OTHER(100);
        
        private int data;

        private UpgradeStep(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static UpgradeStep find(int i) {
            UpgradeStep upgradeStep = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return upgradeStep;
        }
    }

    public static synchronized DataCameraGetPushUpgradeStatus getInstance() {
        DataCameraGetPushUpgradeStatus dataCameraGetPushUpgradeStatus;
        synchronized (DataCameraGetPushUpgradeStatus.class) {
            if (instance == null) {
                instance = new DataCameraGetPushUpgradeStatus();
            }
            dataCameraGetPushUpgradeStatus = instance;
        }
        return dataCameraGetPushUpgradeStatus;
    }

    public SparseArray<UpgradeStatusModel> getList() {
        if (this._recData == null) {
            return this.list;
        }
        int length = (this._recData.length - 3) / 8;
        for (int i = 0; i < length; i++) {
            UpgradeStatusModel upgradeStatusModel = new UpgradeStatusModel();
            upgradeStatusModel.deviceType = DeviceType.find(((Integer) get(((i * 8) + 0) + 3, 1, Integer.class)).intValue());
            upgradeStatusModel.type = FirmwareType.find(((Integer) get(((i * 8) + 1) + 3, 1, Integer.class)).intValue());
            upgradeStatusModel.version = "v " + c.c(this._recData[((i * 8) + 2) + 3]) + "." + c.c(this._recData[((i * 8) + 3) + 3]) + "." + c.c(this._recData[((i * 8) + 4) + 3]) + "." + c.c(this._recData[((i * 8) + 5) + 3]);
            upgradeStatusModel.status = FirmwareUpgradeStatus.find(((Integer) get(((i * 8) + 6) + 3, 1, Integer.class)).intValue());
            upgradeStatusModel.degree = ((Integer) get(((i * 8) + 7) + 3, 1, Integer.class)).intValue();
            this.list.append(i, upgradeStatusModel);
        }
        return this.list;
    }

    public UpgradeStep getStep() {
        return UpgradeStep.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    public int getCountdown() {
        return ((Integer) get(1, 2, Integer.class)).intValue() & 255;
    }

    public int getRound() {
        return ((Integer) get(1, 2, Integer.class)).intValue() & 7;
    }

    public int getFirmwareCount() {
        return (((Integer) get(1, 2, Integer.class)).intValue() >> 8) & 31;
    }

    public int getProgress() {
        return ((Integer) get(1, 2, Integer.class)).intValue() & 255;
    }

    public UpgradeEndCause getEndCause() {
        return UpgradeEndCause.find(((Integer) get(1, 2, Integer.class)).intValue() & 255);
    }

    protected void doPack() {
    }
}
