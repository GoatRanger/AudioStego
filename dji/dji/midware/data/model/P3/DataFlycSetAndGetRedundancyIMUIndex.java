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

public class DataFlycSetAndGetRedundancyIMUIndex extends n implements e {
    private static DataFlycSetAndGetRedundancyIMUIndex mInstance = null;
    private CMD_ACTION cmdAction = CMD_ACTION.SET;
    private int deviceIndex = 0;
    private DEVICE_TYPE deviceType = DEVICE_TYPE.IMU;

    public enum CMD_ACTION {
        GET(1),
        SET(2);
        
        private int mValue;

        private CMD_ACTION(int i) {
            this.mValue = 0;
            this.mValue = i;
        }

        public int value() {
            return this.mValue;
        }

        public boolean belongs(byte b) {
            return this.mValue == b;
        }

        public static CMD_ACTION ofValue(byte b) {
            for (CMD_ACTION cmd_action : values()) {
                if (cmd_action.belongs(b)) {
                    return cmd_action;
                }
            }
            return SET;
        }
    }

    public enum DEVICE_TYPE {
        IMU(6);
        
        private int mValue;

        private DEVICE_TYPE(int i) {
            this.mValue = 0;
            this.mValue = i;
        }

        public int value() {
            return this.mValue;
        }

        public boolean belongs(byte b) {
            return this.mValue == b;
        }

        public static DEVICE_TYPE ofValue(byte b) {
            for (DEVICE_TYPE device_type : values()) {
                if (device_type.belongs(b)) {
                    return device_type;
                }
            }
            return IMU;
        }
    }

    public static DataFlycSetAndGetRedundancyIMUIndex getInstance() {
        if (mInstance == null) {
            mInstance = new DataFlycSetAndGetRedundancyIMUIndex();
        }
        return mInstance;
    }

    public int getResult() {
        return ((Integer) get(3, 1, Integer.class)).intValue();
    }

    public int getCurIMUIndex() {
        return ((Integer) get(2, 1, Integer.class)).intValue();
    }

    public DataFlycSetAndGetRedundancyIMUIndex setDeviceIndex(int i) {
        this.deviceIndex = i;
        return this;
    }

    public DataFlycSetAndGetRedundancyIMUIndex setCmdAction(CMD_ACTION cmd_action) {
        this.cmdAction = cmd_action;
        return this;
    }

    protected void doPack() {
        this._sendData = new byte[3];
        this._sendData[0] = (byte) this.cmdAction.value();
        this._sendData[1] = (byte) this.deviceType.value();
        this._sendData[2] = (byte) this.deviceIndex;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.aH.a();
        start(cVar, dVar);
    }
}
