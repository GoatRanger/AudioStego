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

public class DataFlycStartHotPointMissionWithInfo extends n implements e {
    private static DataFlycStartHotPointMissionWithInfo instance = null;
    private double altitude;
    private float angleSpeed;
    private CAMERA_DIR cameraDir;
    private double latitude;
    private double longitude;
    private double radious;
    private ROTATION_DIR rotationDir;
    private TO_START_POINT_MODE toStartPointMode;
    private byte version = (byte) 0;

    public enum CAMERA_DIR {
        SAME_DIR_AS_SPEED(0),
        Forwards_Hot_Point(1),
        Backforwards_Hot_Point(2),
        Remote_Control(3),
        INVERSE_DIR_AS_SPEED(5);
        
        private int data;

        private CAMERA_DIR(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static CAMERA_DIR find(int i) {
            CAMERA_DIR camera_dir = SAME_DIR_AS_SPEED;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return camera_dir;
        }
    }

    public enum ROTATION_DIR {
        Anti_Clockwise(0),
        Clockwise(1);
        
        private int data;

        private ROTATION_DIR(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static ROTATION_DIR find(int i) {
            ROTATION_DIR rotation_dir = Anti_Clockwise;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return rotation_dir;
        }
    }

    public enum TO_START_POINT_MODE {
        North(0),
        South(1),
        West(2),
        Ease(3),
        Initi(4);
        
        private int data;

        private TO_START_POINT_MODE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static TO_START_POINT_MODE find(int i) {
            TO_START_POINT_MODE to_start_point_mode = Initi;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return to_start_point_mode;
        }
    }

    public static synchronized DataFlycStartHotPointMissionWithInfo getInstance() {
        DataFlycStartHotPointMissionWithInfo dataFlycStartHotPointMissionWithInfo;
        synchronized (DataFlycStartHotPointMissionWithInfo.class) {
            if (instance == null) {
                instance = new DataFlycStartHotPointMissionWithInfo();
            }
            dataFlycStartHotPointMissionWithInfo = instance;
        }
        return dataFlycStartHotPointMissionWithInfo;
    }

    public DataFlycStartHotPointMissionWithInfo setLatitude(double d) {
        this.latitude = d;
        return this;
    }

    public void printResult() {
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public float getMaxRadius() {
        return ((Float) get(1, 4, Float.class)).floatValue();
    }

    public DataFlycStartHotPointMissionWithInfo setLongitude(double d) {
        this.longitude = d;
        return this;
    }

    public DataFlycStartHotPointMissionWithInfo setAltitude(double d) {
        this.altitude = d;
        return this;
    }

    public DataFlycStartHotPointMissionWithInfo setRadious(double d) {
        this.radious = d;
        return this;
    }

    public DataFlycStartHotPointMissionWithInfo setVelocity(float f) {
        this.angleSpeed = f;
        return this;
    }

    public DataFlycStartHotPointMissionWithInfo setRotationDir(ROTATION_DIR rotation_dir) {
        this.rotationDir = rotation_dir;
        return this;
    }

    public DataFlycStartHotPointMissionWithInfo setToStartPointMode(TO_START_POINT_MODE to_start_point_mode) {
        this.toStartPointMode = to_start_point_mode;
        return this;
    }

    public DataFlycStartHotPointMissionWithInfo setCameraDir(CAMERA_DIR camera_dir) {
        this.cameraDir = camera_dir;
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
        cVar.n = g.a.an.a();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        super.start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[51];
        this._sendData[0] = this.version;
        System.arraycopy(dji.midware.util.c.a(this.latitude), 0, this._sendData, 1, 8);
        System.arraycopy(dji.midware.util.c.a(this.longitude), 0, this._sendData, 9, 8);
        System.arraycopy(dji.midware.util.c.a(this.altitude), 0, this._sendData, 17, 8);
        System.arraycopy(dji.midware.util.c.a(this.radious), 0, this._sendData, 25, 8);
        System.arraycopy(dji.midware.util.c.a(this.angleSpeed), 0, this._sendData, 33, 4);
        this._sendData[37] = (byte) this.rotationDir.value();
        this._sendData[38] = (byte) this.toStartPointMode.value();
        this._sendData[39] = (byte) this.cameraDir.value();
    }
}
