package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;
import java.util.ArrayList;
import java.util.List;

public class DataFlycRedundancyStatus extends n implements e {
    public static final int a = 1;
    public static final int b = 1;
    public static final int c = 1;
    public static final int d = 1;
    public static final int e = 1;
    public static final int f = 1;
    public static final int g = 1;
    public static final int h = 1;
    public static final int i = 1;
    public static final int j = 1;
    public static final int k = 1;
    private static DataFlycRedundancyStatus l = null;
    private RS_CMD_TYPE m = RS_CMD_TYPE.ASK_VERSION;

    public enum COLOR_STATUS {
        GRAY(0),
        GREEN(1),
        YELLOW(2),
        RED(3),
        GREEN_FLASH(17),
        YELLOW_FLASH(18),
        RED_FLASH(19);
        
        private int h;

        private COLOR_STATUS(int i) {
            this.h = 0;
            this.h = i;
        }

        public int a() {
            return this.h;
        }

        public boolean a(int i) {
            return this.h == i;
        }

        public static COLOR_STATUS ofValue(int i) {
            for (COLOR_STATUS color_status : values()) {
                if (color_status.a(i)) {
                    return color_status;
                }
            }
            return GRAY;
        }
    }

    public static class IMUStatus {
        public boolean canProduction;
        public boolean canRepairForFree;
        public int colorStatus;
        public int devErrCode;
        public int devIndex;
        public int devType;
        public int id;
        public int imuIndex;
        public boolean isCtrlUsed;
        public boolean isNSUsed;
        public boolean isNeedAnalyseByApp;
        public boolean isNeedRefreshLed;
        public boolean isNeedShowAtStatusBar;
        public boolean isRealInAir;
        public long time;
        public int userAction;
    }

    public enum RS_CMD_TYPE {
        ASK_VERSION(1),
        ASK_ERR_STATE(2),
        SEND_ERR_STATE(3),
        SEND_SWITCH_STATE(4);
        
        private int e;

        private RS_CMD_TYPE(int i) {
            this.e = 0;
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public boolean a(int i) {
            return this.e == i;
        }

        public static RS_CMD_TYPE ofValue(int i) {
            for (RS_CMD_TYPE rs_cmd_type : values()) {
                if (rs_cmd_type.a(i)) {
                    return rs_cmd_type;
                }
            }
            return ASK_VERSION;
        }
    }

    public enum USER_ACTION {
        None(0),
        CALC_IMU(1),
        CALC_COMPASS(2),
        WAIT_WARM_UP(3),
        FILL_INSTALL(4),
        CHECK_INSTALL(5),
        STAY_GROUND(6),
        CHECK_CONNECTION(7),
        REBOOT_PILOT(8),
        UPGRATE(9),
        LANDING_FOR_CHECKING(10),
        STABLE_FLY(11),
        SWITCH_ATTI_MODE(12),
        REPAIR(13),
        WAIT_FILOT_COLD(14),
        SWITCH_IMU(15),
        FIND_LARGE_PLACE(16),
        REQUEST_PERMISSION(17),
        UNLOCK(18),
        FILL_RIGHT_PARAMS(19);
        
        private int u;

        private USER_ACTION(int i) {
            this.u = 0;
            this.u = i;
        }

        public int a() {
            return this.u;
        }

        public boolean a(int i) {
            return this.u == i;
        }

        public static USER_ACTION ofValue(int i) {
            for (USER_ACTION user_action : values()) {
                if (user_action.a(i)) {
                    return user_action;
                }
            }
            return None;
        }
    }

    public static class VersionResult {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        public int j;
        public int k;

        public boolean a() {
            return this.a == 0 && this.b == 0 && this.c == 0 && this.d == 0 && this.e == 0 && this.f == 0 && this.g == 0 && this.h == 0 && this.i == 0 && this.j == 0 && this.k == 0;
        }
    }

    public static synchronized DataFlycRedundancyStatus getInstance() {
        DataFlycRedundancyStatus dataFlycRedundancyStatus;
        synchronized (DataFlycRedundancyStatus.class) {
            if (l == null) {
                l = new DataFlycRedundancyStatus();
            }
            dataFlycRedundancyStatus = l;
        }
        return dataFlycRedundancyStatus;
    }

    public DataFlycRedundancyStatus a(RS_CMD_TYPE rs_cmd_type) {
        this.m = rs_cmd_type;
        return this;
    }

    public RS_CMD_TYPE a() {
        return RS_CMD_TYPE.ofValue(((Integer) get(0, 1, Integer.class)).intValue());
    }

    public VersionResult b() {
        VersionResult versionResult = new VersionResult();
        versionResult.a = ((Integer) get(1, 1, Integer.class)).intValue();
        versionResult.b = ((Integer) get(2, 1, Integer.class)).intValue();
        versionResult.c = ((Integer) get(3, 1, Integer.class)).intValue();
        versionResult.d = ((Integer) get(4, 1, Integer.class)).intValue();
        versionResult.e = ((Integer) get(5, 1, Integer.class)).intValue();
        versionResult.f = ((Integer) get(6, 1, Integer.class)).intValue();
        versionResult.g = ((Integer) get(7, 1, Integer.class)).intValue();
        versionResult.h = ((Integer) get(8, 1, Integer.class)).intValue();
        versionResult.i = ((Integer) get(9, 1, Integer.class)).intValue();
        versionResult.j = ((Integer) get(10, 1, Integer.class)).intValue();
        versionResult.k = ((Integer) get(11, 1, Integer.class)).intValue();
        return versionResult;
    }

    public List<IMUStatus> c() {
        List<IMUStatus> arrayList = new ArrayList();
        if (RS_CMD_TYPE.ofValue(((Integer) get(0, 1, Integer.class)).intValue()) == RS_CMD_TYPE.ASK_ERR_STATE) {
            for (int i = 0; i < 3; i++) {
                boolean z;
                IMUStatus iMUStatus = new IMUStatus();
                iMUStatus.colorStatus = ((Integer) get((i * 8) + 1, 1, Integer.class)).intValue();
                long longValue = ((Long) get((i * 8) + 2, 4, Long.class)).longValue();
                if (((longValue >> 2) & 1) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                iMUStatus.isRealInAir = z;
                iMUStatus.imuIndex = (int) ((longValue >> 4) & 15);
                iMUStatus.devType = (int) ((longValue >> 8) & 255);
                iMUStatus.devIndex = (int) ((longValue >> 12) & 15);
                iMUStatus.devErrCode = (int) ((longValue >> 24) & 255);
                int intValue = ((Integer) get((i * 8) + 6, 2, Integer.class)).intValue();
                if ((intValue & 1) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                iMUStatus.isNeedRefreshLed = z;
                if ((intValue & 2) == 2) {
                    z = true;
                } else {
                    z = false;
                }
                iMUStatus.isNeedShowAtStatusBar = z;
                if ((intValue & 4) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                iMUStatus.canRepairForFree = z;
                if ((intValue & 8) == 8) {
                    z = true;
                } else {
                    z = false;
                }
                iMUStatus.isNeedAnalyseByApp = z;
                if ((intValue & 16) == 16) {
                    z = true;
                } else {
                    z = false;
                }
                iMUStatus.canProduction = z;
                iMUStatus.userAction = ((Integer) get((i * 8) + 8, 1, Integer.class)).intValue();
                arrayList.add(iMUStatus);
            }
        }
        return arrayList;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.aI.a();
        start(cVar, dVar);
    }

    protected void doPack() {
        if (this.m == RS_CMD_TYPE.ASK_VERSION) {
            this._sendData = new byte[12];
            this._sendData[1] = (byte) 1;
            this._sendData[2] = (byte) 1;
            this._sendData[3] = (byte) 1;
            this._sendData[4] = (byte) 1;
            this._sendData[5] = (byte) 1;
            this._sendData[6] = (byte) 1;
            this._sendData[7] = (byte) 1;
            this._sendData[8] = (byte) 1;
            this._sendData[9] = (byte) 1;
            this._sendData[10] = (byte) 1;
            this._sendData[11] = (byte) 1;
        } else {
            this._sendData = new byte[1];
        }
        this._sendData[0] = (byte) this.m.a();
    }
}
