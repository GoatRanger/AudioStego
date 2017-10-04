package dji.pilot.publics.control.upgrade;

import dji.midware.data.config.P3.DeviceType;
import java.util.ArrayList;
import java.util.Iterator;

public class DJIProductUpgradeListModel {
    public ArrayList<DJIUpgradeModel> products;
    public String version;

    public static class DJIUpgradeGroupModel {
        public ArrayList<String> devices;
        public String extraStartFile;
        public String ftpDstFileName;
        public String ftpPwd;
        public String ftpUrl;
        public String ftpUsername;
        public String groupName;
        public boolean isCameraGroup;
        public boolean isSingleFile;
        public int pushDevice;
        public int upgradeMode;
        public int weight;

        public DeviceType getDeviceType(int i) {
            if (this.devices == null || i >= this.devices.size()) {
                return null;
            }
            return DeviceType.find(Integer.valueOf(((String) this.devices.get(i)).substring(0, 2)).intValue());
        }

        public int getDeviceModule(int i) {
            if (this.devices == null || i >= this.devices.size()) {
                return 0;
            }
            return Integer.valueOf(((String) this.devices.get(i)).substring(2, 4)).intValue();
        }
    }

    public static class DJIUpgradeModel {
        public ArrayList<DJIUpgradeGroupModel> groups;
        public int productId;

        public DJIUpgradeGroupModel getCameraModel() {
            if (this.groups == null) {
                return null;
            }
            Iterator it = this.groups.iterator();
            while (it.hasNext()) {
                DJIUpgradeGroupModel dJIUpgradeGroupModel = (DJIUpgradeGroupModel) it.next();
                if (dJIUpgradeGroupModel.isCameraGroup) {
                    return dJIUpgradeGroupModel;
                }
            }
            return null;
        }
    }
}
