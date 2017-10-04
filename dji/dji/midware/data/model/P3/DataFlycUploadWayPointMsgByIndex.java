package dji.midware.data.model.P3;

import com.autonavi.amap.mapcore.MapCore;
import com.google.android.gms.common.api.CommonStatusCodes;
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

public class DataFlycUploadWayPointMsgByIndex extends n implements e {
    private static DataFlycUploadWayPointMsgByIndex instance = null;
    private ArrayList<ACTION> actionList;
    private int actionNum;
    private int actionRepeat;
    private int actionTimeTimit = MapCore.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER;
    private float altitude;
    private float dampingDis = 0.0f;
    private boolean hasAction;
    private int index;
    private double latitude;
    private double longitude;
    private ArrayList<Integer> paramsList;
    private short tgtPitch = (short) 0;
    private short tgtYaw;
    private TURNMODE turnMode;

    public enum ACTION {
        WP_ACTION_STAY(0),
        WP_ACTION_SIMPLE_SHOT(1),
        WP_ACTION_VIDEO_START(2),
        WP_ACTION_VIDEO_STOP(3),
        WP_ACTION_CRAFT_YAW(4),
        WP_ACTION_GIMBAL_YAW(5),
        WP_ACTION_GIMBAL_PITCH(6);
        
        private int data;

        private ACTION(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static ACTION find(int i) {
            ACTION action = WP_ACTION_STAY;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return action;
        }
    }

    public enum TURNMODE {
        CLOCKWISE(0),
        ANTI_CLOSEWISE(1);
        
        private int data;

        private TURNMODE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static TURNMODE find(int i) {
            TURNMODE turnmode = CLOCKWISE;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return turnmode;
        }
    }

    public static synchronized DataFlycUploadWayPointMsgByIndex getInstance() {
        DataFlycUploadWayPointMsgByIndex dataFlycUploadWayPointMsgByIndex;
        synchronized (DataFlycUploadWayPointMsgByIndex.class) {
            if (instance == null) {
                instance = new DataFlycUploadWayPointMsgByIndex();
            }
            dataFlycUploadWayPointMsgByIndex = instance;
        }
        return dataFlycUploadWayPointMsgByIndex;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public DataFlycUploadWayPointMsgByIndex setIndex(int i) {
        this.index = i;
        return this;
    }

    public DataFlycUploadWayPointMsgByIndex setLatitude(double d) {
        this.latitude = d;
        return this;
    }

    public DataFlycUploadWayPointMsgByIndex setLongtitude(double d) {
        this.longitude = d;
        return this;
    }

    public DataFlycUploadWayPointMsgByIndex setAltitude(float f) {
        this.altitude = f;
        return this;
    }

    public DataFlycUploadWayPointMsgByIndex setDampingDis(float f) {
        this.dampingDis = f;
        return this;
    }

    public DataFlycUploadWayPointMsgByIndex setTgtYaw(short s) {
        this.tgtYaw = s;
        return this;
    }

    public DataFlycUploadWayPointMsgByIndex setTgtPitch(short s) {
        this.tgtPitch = s;
        return this;
    }

    public DataFlycUploadWayPointMsgByIndex setActionTimeTimit(short s) {
        this.actionTimeTimit = s;
        return this;
    }

    public DataFlycUploadWayPointMsgByIndex setTurnMode(TURNMODE turnmode) {
        this.turnMode = turnmode;
        return this;
    }

    public DataFlycUploadWayPointMsgByIndex setHasAction(boolean z) {
        this.hasAction = z;
        return this;
    }

    public DataFlycUploadWayPointMsgByIndex setActionNum(int i) {
        this.actionNum = i;
        return this;
    }

    public DataFlycUploadWayPointMsgByIndex setActionRepeat(int i) {
        this.actionRepeat = i;
        return this;
    }

    public DataFlycUploadWayPointMsgByIndex setActionList(ArrayList<ACTION> arrayList) {
        this.actionList = arrayList;
        return this;
    }

    public DataFlycUploadWayPointMsgByIndex setParamsList(ArrayList<Integer> arrayList) {
        this.paramsList = arrayList;
        return this;
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.a.a();
        cVar.k = q.c.a.a();
        cVar.l = b.a.a();
        cVar.m = p.d.a();
        cVar.n = g.a.ad.a();
        cVar.p = getSendData();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        start(cVar, dVar);
    }

    protected void doPack() {
        int i = 1;
        this._sendData = new byte[90];
        this._sendData[0] = dji.midware.util.c.c(this.index);
        System.arraycopy(dji.midware.util.c.a(this.latitude), 0, this._sendData, 1, 8);
        System.arraycopy(dji.midware.util.c.a(this.longitude), 0, this._sendData, 9, 8);
        System.arraycopy(dji.midware.util.c.a(this.altitude), 0, this._sendData, 17, 4);
        System.arraycopy(dji.midware.util.c.a(this.dampingDis), 0, this._sendData, 21, 4);
        System.arraycopy(dji.midware.util.c.b(this.tgtYaw), 0, this._sendData, 25, 2);
        System.arraycopy(dji.midware.util.c.b(this.tgtPitch), 0, this._sendData, 27, 2);
        this._sendData[29] = (byte) this.turnMode.value();
        byte[] bArr = this._sendData;
        if (!this.hasAction) {
            i = 0;
        }
        bArr[38] = (byte) i;
        System.arraycopy(dji.midware.util.c.a(this.actionTimeTimit), 0, this._sendData, 39, 2);
        if (this.hasAction) {
            this._sendData[41] = (byte) ((this.actionRepeat << 4) + this.actionNum);
            for (int i2 = 0; i2 < 16; i2++) {
                if (i2 < this.actionNum) {
                    this._sendData[i2 + 42] = (byte) ((ACTION) this.actionList.get(i2)).value();
                    System.arraycopy(dji.midware.util.c.a(((Integer) this.paramsList.get(i2)).intValue()), 0, this._sendData, (i2 * 2) + 58, 2);
                } else {
                    this._sendData[i2 + 42] = (byte) 0;
                    System.arraycopy(dji.midware.util.c.a(0), 0, this._sendData, (i2 * 2) + 58, 2);
                }
            }
            return;
        }
        this._sendData[41] = (byte) 0;
        for (i = 0; i < 16; i++) {
            this._sendData[i + 42] = (byte) 0;
            System.arraycopy(dji.midware.util.c.a(0), 0, this._sendData, (i * 2) + 58, 2);
        }
    }
}
