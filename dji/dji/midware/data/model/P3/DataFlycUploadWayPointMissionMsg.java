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

public class DataFlycUploadWayPointMissionMsg extends n implements e {
    private static DataFlycUploadWayPointMissionMsg instance = null;
    private ACTION_ON_RC_LOST actionOnRCLost = ACTION_ON_RC_LOST.EXIT_WP;
    private float cmdSpeed = 3.0f;
    private FINISH_ACTION finishAction;
    private GIMBAL_PITCH_MODE gimbalPitchMode = GIMBAL_PITCH_MODE.PITCH_SMOOTH;
    private GOTO_FIRST_POINT_ACTION gotoFirstFlag = GOTO_FIRST_POINT_ACTION.MAX_HEIGHT;
    private double hpHeight = 0.0d;
    private double hpLat = 0.0d;
    private double hpLng = 0.0d;
    private float idleSpeed = 10.0f;
    private int repeatNum;
    private TRACE_MODE traceMode;
    private int wayPointsCount = 0;
    private YAW_MODE yawMode;

    public enum ACTION_ON_RC_LOST {
        EXIT_WP(0),
        CONTINUE_WP(1);
        
        private int data;

        private ACTION_ON_RC_LOST(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static ACTION_ON_RC_LOST find(int i) {
            ACTION_ON_RC_LOST action_on_rc_lost = EXIT_WP;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return action_on_rc_lost;
        }
    }

    public enum FINISH_ACTION {
        NONE(0),
        GOHOME(1),
        LAND(2),
        BACK_TO_FIRST_WAY_POINT(3),
        NO_LIMIT(4);
        
        private int data;

        private FINISH_ACTION(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static FINISH_ACTION find(int i) {
            FINISH_ACTION finish_action = NONE;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return finish_action;
        }
    }

    public enum GIMBAL_PITCH_MODE {
        PITCH_FREE(0),
        PITCH_SMOOTH(1);
        
        private int data;

        private GIMBAL_PITCH_MODE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static GIMBAL_PITCH_MODE find(int i) {
            GIMBAL_PITCH_MODE gimbal_pitch_mode = PITCH_SMOOTH;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return gimbal_pitch_mode;
        }
    }

    public enum GOTO_FIRST_POINT_ACTION {
        MAX_HEIGHT(0),
        POINT_TO_POINT(1);
        
        private int data;

        private GOTO_FIRST_POINT_ACTION(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static GOTO_FIRST_POINT_ACTION find(int i) {
            GOTO_FIRST_POINT_ACTION goto_first_point_action = MAX_HEIGHT;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return goto_first_point_action;
        }
    }

    public enum TRACE_MODE {
        EXEC_MESSION(0),
        SMOOTH_PATH(1);
        
        private int data;

        private TRACE_MODE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static TRACE_MODE find(int i) {
            TRACE_MODE trace_mode = EXEC_MESSION;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return trace_mode;
        }
    }

    public enum YAW_MODE {
        AUTO_COURSE(0),
        FREE_COURSE(1),
        REMOTE_CONTROL(2),
        PATH_COURSE(3),
        HP_MODE(4);
        
        private int data;

        private YAW_MODE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static YAW_MODE find(int i) {
            YAW_MODE yaw_mode = AUTO_COURSE;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return yaw_mode;
        }
    }

    public static synchronized DataFlycUploadWayPointMissionMsg getInstance() {
        DataFlycUploadWayPointMissionMsg dataFlycUploadWayPointMissionMsg;
        synchronized (DataFlycUploadWayPointMissionMsg.class) {
            if (instance == null) {
                instance = new DataFlycUploadWayPointMissionMsg();
            }
            dataFlycUploadWayPointMissionMsg = instance;
        }
        return dataFlycUploadWayPointMissionMsg;
    }

    public int getResult() {
        return ((Integer) get(0, 1, Integer.class)).intValue();
    }

    public DataFlycUploadWayPointMissionMsg setWayPointCount(int i) {
        this.wayPointsCount = i;
        return this;
    }

    public DataFlycUploadWayPointMissionMsg setCmdSpeed(float f) {
        this.cmdSpeed = f;
        return this;
    }

    public DataFlycUploadWayPointMissionMsg setIdleSpeed(float f) {
        this.idleSpeed = f;
        return this;
    }

    public DataFlycUploadWayPointMissionMsg setFinishAction(FINISH_ACTION finish_action) {
        this.finishAction = finish_action;
        return this;
    }

    public DataFlycUploadWayPointMissionMsg setRepeatNum(int i) {
        this.repeatNum = i;
        return this;
    }

    public DataFlycUploadWayPointMissionMsg setYawMode(YAW_MODE yaw_mode) {
        this.yawMode = yaw_mode;
        return this;
    }

    public DataFlycUploadWayPointMissionMsg setTraceMOde(TRACE_MODE trace_mode) {
        this.traceMode = trace_mode;
        return this;
    }

    public DataFlycUploadWayPointMissionMsg setActionOnRCLost(ACTION_ON_RC_LOST action_on_rc_lost) {
        this.actionOnRCLost = action_on_rc_lost;
        return this;
    }

    public DataFlycUploadWayPointMissionMsg seGimbalPitchMode(GIMBAL_PITCH_MODE gimbal_pitch_mode) {
        this.gimbalPitchMode = gimbal_pitch_mode;
        return this;
    }

    public DataFlycUploadWayPointMissionMsg seHPLat(double d) {
        this.hpLat = d;
        return this;
    }

    public DataFlycUploadWayPointMissionMsg seHPLng(double d) {
        this.hpLng = d;
        return this;
    }

    public DataFlycUploadWayPointMissionMsg seHPHeight(double d) {
        this.hpHeight = d;
        return this;
    }

    public DataFlycUploadWayPointMissionMsg seGotoFirstFlag(GOTO_FIRST_POINT_ACTION goto_first_point_action) {
        this.gotoFirstFlag = goto_first_point_action;
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
        cVar.n = g.a.ab.a();
        cVar.p = getSendData();
        cVar.v = CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS;
        start(cVar, dVar);
    }

    protected void doPack() {
        this._sendData = new byte[51];
        this._sendData[0] = dji.midware.util.c.c(this.wayPointsCount);
        System.arraycopy(dji.midware.util.c.a(this.cmdSpeed), 0, this._sendData, 1, 4);
        System.arraycopy(dji.midware.util.c.a(this.idleSpeed), 0, this._sendData, 5, 4);
        this._sendData[9] = (byte) this.finishAction.value();
        this._sendData[10] = (byte) this.repeatNum;
        this._sendData[11] = (byte) this.yawMode.value();
        this._sendData[12] = (byte) this.traceMode.value();
        this._sendData[13] = (byte) this.actionOnRCLost.value();
        this._sendData[14] = (byte) this.gimbalPitchMode.value();
        System.arraycopy(dji.midware.util.c.a(this.hpLat), 0, this._sendData, 15, 8);
        System.arraycopy(dji.midware.util.c.a(this.hpLng), 0, this._sendData, 23, 8);
        System.arraycopy(dji.midware.util.c.a(this.hpHeight), 0, this._sendData, 31, 4);
        this._sendData[35] = (byte) this.gotoFirstFlag.value();
    }
}
