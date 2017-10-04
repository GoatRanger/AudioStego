package dji.midware.data.model.P3;

import android.util.SparseArray;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.n;
import dji.midware.data.model.P3.DataCameraGetPushUpgradeStatus.FirmwareType;
import dji.midware.util.c;

public class DataCameraGetPushRequestUpgrade extends n {
    private static DataCameraGetPushRequestUpgrade instance = null;
    private SparseArray<UpgradeRequestModel> list = new SparseArray();

    public static class UpgradeRequestModel {
        public DeviceType deviceType;
        public long size;
        public FirmwareType type;
        public String version;
    }

    public static synchronized DataCameraGetPushRequestUpgrade getInstance() {
        DataCameraGetPushRequestUpgrade dataCameraGetPushRequestUpgrade;
        synchronized (DataCameraGetPushRequestUpgrade.class) {
            if (instance == null) {
                instance = new DataCameraGetPushRequestUpgrade();
            }
            dataCameraGetPushRequestUpgrade = instance;
        }
        return dataCameraGetPushRequestUpgrade;
    }

    public SparseArray<UpgradeRequestModel> getList() {
        if (this._recData == null) {
            return this.list;
        }
        int length = this._recData.length / 10;
        for (int i = 0; i < length; i++) {
            UpgradeRequestModel upgradeRequestModel = new UpgradeRequestModel();
            upgradeRequestModel.deviceType = DeviceType.find(((Integer) get((i * 10) + 0, 1, Integer.class)).intValue());
            upgradeRequestModel.type = FirmwareType.find(((Integer) get((i * 10) + 1, 1, Integer.class)).intValue());
            upgradeRequestModel.version = "v " + c.c(this._recData[(i * 10) + 2]) + "." + c.c(this._recData[(i * 10) + 3]) + "." + c.c(this._recData[(i * 10) + 4]) + "." + c.c(this._recData[(i * 10) + 5]);
            upgradeRequestModel.size = ((Long) get((i * 10) + 6, 4, Long.class)).longValue();
            this.list.append(i, upgradeRequestModel);
        }
        return this.list;
    }

    protected void doPack() {
    }
}
