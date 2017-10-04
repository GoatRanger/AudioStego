package dji.midware.data.model.P3;

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

public class DataFlycStartFollowMeWithInfo extends n implements e {
    private static DataFlycStartFollowMeWithInfo instance = null;
    private FOLLOWMODE followMode = FOLLOWMODE.Relative_mode;
    private short mAltitude = (short) 0;
    private int mAppSource = 1;
    private short mHeading = (short) 0;
    private int mSensitivity = 0;
    private double mUserLatitude;
    private double mUserLongitude;
    private YAWMODE yawMode = YAWMODE.Use_Remote_Controll;

    public enum FOLLOWMODE {
        Relative_mode(0),
        Route_mode(1),
        Smart_mode(2);
        
        private int data;

        private FOLLOWMODE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static FOLLOWMODE find(int i) {
            FOLLOWMODE followmode = Relative_mode;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return followmode;
        }
    }

    public enum YAWMODE {
        Point_To_Target(0),
        Use_Remote_Controll(1);
        
        private int data;

        private YAWMODE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static YAWMODE find(int i) {
            YAWMODE yawmode = Point_To_Target;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return yawmode;
        }
    }

    public static synchronized DataFlycStartFollowMeWithInfo getInstance() {
        DataFlycStartFollowMeWithInfo dataFlycStartFollowMeWithInfo;
        synchronized (DataFlycStartFollowMeWithInfo.class) {
            if (instance == null) {
                instance = new DataFlycStartFollowMeWithInfo();
            }
            dataFlycStartFollowMeWithInfo = instance;
        }
        return dataFlycStartFollowMeWithInfo;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public DataFlycStartFollowMeWithInfo setFollowMode(FOLLOWMODE followmode) {
        this.followMode = followmode;
        return this;
    }

    public DataFlycStartFollowMeWithInfo setYawMode(YAWMODE yawmode) {
        this.yawMode = yawmode;
        return this;
    }

    public DataFlycStartFollowMeWithInfo setUserLatitude(double d) {
        this.mUserLatitude = d;
        return this;
    }

    public DataFlycStartFollowMeWithInfo setUserLongitude(double d) {
        this.mUserLongitude = d;
        return this;
    }

    public DataFlycStartFollowMeWithInfo setAltitude(short s) {
        this.mAltitude = s;
        return this;
    }

    public DataFlycStartFollowMeWithInfo setHeading(short s) {
        this.mHeading = s;
        return this;
    }

    public DataFlycStartFollowMeWithInfo setSensitivity(int i) {
        this.mSensitivity = i;
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
        cVar.n = g.a.au.a();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        super.start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[23];
        this._sendData[0] = (byte) this.followMode.value();
        this._sendData[1] = (byte) this.yawMode.value();
        System.arraycopy(dji.midware.util.c.a(this.mUserLatitude), 0, this._sendData, 2, 8);
        System.arraycopy(dji.midware.util.c.a(this.mUserLongitude), 0, this._sendData, 10, 8);
        System.arraycopy(dji.midware.util.c.b(this.mAltitude), 0, this._sendData, 18, 2);
        System.arraycopy(dji.midware.util.c.b(this.mHeading), 0, this._sendData, 20, 2);
        this._sendData[22] = (byte) ((this.mSensitivity & 63) | (this.mAppSource << 6));
    }
}
